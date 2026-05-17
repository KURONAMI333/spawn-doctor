package com.kuronami.spawndoctor.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.GameRules;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.RegisterCommandsEvent;

/**
 * {@code /spawndoctor} — "why won't my mob farm spawn anything?", in
 * plain words. Open to everyone (it diagnoses the player's own
 * surroundings); a 30s server-wide cooldown stops the loaded-entity
 * scan from being spammed (ops bypass it).
 *
 * <p>Same discipline as Lag Whisperer / Death Forensics: report only
 * certain vanilla facts (difficulty, the doMobSpawning gamerule, loaded
 * hostile counts, distances). The mob-cap formula is version-sensitive
 * and easy to get wrong, so this never claims an exact "cap X/Y" —
 * instead it surfaces the certain, explanatory facts (cap theft from
 * far-loaded hostiles is the real #1 cause) and the universal spawn
 * rules. A wrong number would be worse than none.
 */
public class SpawnDoctorCommand {

    private static final long COOLDOWN_MS = 30_000L;
    /** Hard floor even for ops — the scan is a full-dimension entity
     *  sweep; a looping op/command-block must not sweep every tick. */
    private static final long OP_FLOOR_MS = 3_000L;
    private static final double NEAR_SQ = 128.0 * 128.0;
    private static final int CAP_THEFT_MIN = 30;

    private volatile long lastRunMs = 0L;

    @SubscribeEvent
    public void onRegisterCommands(RegisterCommandsEvent event) {
        event.getDispatcher().register(
            Commands.literal("spawndoctor").executes(this::run));
    }

    private int run(CommandContext<CommandSourceStack> ctx) {
        CommandSourceStack src = ctx.getSource();
        if (!(src.getEntity() instanceof ServerPlayer player)) {
            src.sendFailure(Component.translatable("spawndoctor.playeronly"));
            return 0;
        }
        boolean op = src.hasPermission(2);
        long now = System.currentTimeMillis();
        long since = now - lastRunMs;
        // Ops get a short floor instead of a full bypass: each call does
        // a full-dimension getAllEntities() sweep on the server thread,
        // so an op / command-block / script looping it must not be able
        // to sweep every tick. Latency for a human op stays fine at 3s.
        long cd = op ? OP_FLOOR_MS : COOLDOWN_MS;
        if (lastRunMs != 0L && since < cd) {
            int wait = (int) Math.ceil((cd - since) / 1000.0);
            src.sendSuccess(() -> Component.translatable("spawndoctor.cooldown", wait)
                .withStyle(ChatFormatting.YELLOW), false);
            return Command.SINGLE_SUCCESS;
        }
        lastRunMs = now;

        if (player.getServer() == null) {
            return Command.SINGLE_SUCCESS;
        }
        ServerLevel level = player.serverLevel();
        boolean peaceful = level.getDifficulty() == Difficulty.PEACEFUL;
        boolean mobSpawn = level.getGameRules().getBoolean(GameRules.RULE_DOMOBSPAWNING);
        int simDist = player.getServer().getPlayerList().getSimulationDistance();

        int near = 0;
        int far = 0;
        for (Entity e : level.getAllEntities()) {
            if (e instanceof Monster) {
                if (player.distanceToSqr(e) <= NEAR_SQ) {
                    near++;
                } else {
                    far++;
                }
            }
        }
        final int fNear = near, fFar = far;

        src.sendSuccess(() -> Component.translatable("spawndoctor.title")
            .withStyle(ChatFormatting.GOLD, ChatFormatting.BOLD), false);

        boolean causeShown = false;
        if (peaceful) {
            src.sendSuccess(() -> Component.translatable("spawndoctor.peaceful")
                .withStyle(ChatFormatting.DARK_RED), false);
            causeShown = true;
        } else if (!mobSpawn) {
            src.sendSuccess(() -> Component.translatable("spawndoctor.nomobspawning")
                .withStyle(ChatFormatting.DARK_RED), false);
            causeShown = true;
        } else if (far >= CAP_THEFT_MIN && far > near) {
            src.sendSuccess(() -> Component.translatable(
                "spawndoctor.captheft", fFar, fNear).withStyle(ChatFormatting.RED),
                false);
            causeShown = true;
        }
        if (!causeShown) {
            src.sendSuccess(() -> Component.translatable("spawndoctor.none")
                .withStyle(ChatFormatting.GREEN), false);
        }

        // Universal certain guidance, always shown.
        src.sendSuccess(() -> Component.literal(" ┃ ").withStyle(ChatFormatting.DARK_GRAY)
            .append(Component.translatable("spawndoctor.info.ring")
                .withStyle(ChatFormatting.GRAY)), false);
        src.sendSuccess(() -> Component.literal(" ┃ ").withStyle(ChatFormatting.DARK_GRAY)
            .append(Component.translatable("spawndoctor.info.sim", simDist)
                .withStyle(ChatFormatting.GRAY)), false);
        return Command.SINGLE_SUCCESS;
    }
}

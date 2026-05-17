package com.kuronami.spawndoctor;

import com.kuronami.spawndoctor.command.SpawnDoctorCommand;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Spawn Doctor — entry point (Fabric 1.21.1).
 *
 * <p>One read-only, on-demand command, {@code /spawndoctor}: explains in
 * plain words why a correctly-built mob farm isn't spawning (Peaceful,
 * doMobSpawning off, far-loaded hostiles stealing the spawn budget, AFK
 * outside the 24-128 ring, short simulation distance). No mixin, no
 * config, no passive task — it does nothing until asked.
 *
 * <p>Fabric variant: the command is registered via {@code
 * CommandRegistrationCallback}; the Brigadier tree is identical to the
 * Forge/NeoForge builds.
 */
public class SpawnDoctorFabric implements ModInitializer {

    public static final String MOD_ID = "spawndoctor";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        LOGGER.info("Spawn Doctor ready — /spawndoctor (read-only).");
        CommandRegistrationCallback.EVENT.register(
            (dispatcher, registryAccess, environment) ->
                SpawnDoctorCommand.register(dispatcher));
    }
}

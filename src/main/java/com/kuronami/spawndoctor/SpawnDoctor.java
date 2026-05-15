package com.kuronami.spawndoctor;

import com.kuronami.spawndoctor.command.SpawnDoctorCommand;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Spawn Doctor — entry point.
 *
 * <p>One read-only, on-demand command, {@code /spawndoctor}: explains in
 * plain words why a correctly-built mob farm isn't spawning (Peaceful,
 * doMobSpawning off, far-loaded hostiles stealing the spawn budget, AFK
 * outside the 24-128 ring, short simulation distance). No mixin, no
 * config, no passive task — it does nothing until asked.
 */
@Mod(SpawnDoctor.MOD_ID)
public class SpawnDoctor {

    public static final String MOD_ID = "spawndoctor";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public SpawnDoctor(IEventBus modBus, ModContainer container) {
        LOGGER.info("Spawn Doctor ready — /spawndoctor (read-only).");
        NeoForge.EVENT_BUS.register(new SpawnDoctorCommand());
    }
}

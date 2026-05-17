package com.kuronami.spawndoctor;

import com.kuronami.spawndoctor.command.SpawnDoctorCommand;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Spawn Doctor — entry point (Forge 1.21.1).
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

    public SpawnDoctor(FMLJavaModLoadingContext context) {
        LOGGER.info("Spawn Doctor ready — /spawndoctor (read-only).");
        MinecraftForge.EVENT_BUS.register(new SpawnDoctorCommand());
    }
}

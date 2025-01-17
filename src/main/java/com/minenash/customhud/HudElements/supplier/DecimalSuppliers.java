package com.minenash.customhud.HudElements.supplier;

import com.minenash.customhud.ComplexData;
import com.minenash.customhud.MusicAndRecordTracker;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.server.integrated.IntegratedServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.source.util.MultiNoiseUtil;
import net.minecraft.world.biome.source.util.VanillaBiomeParameters;
import net.minecraft.world.gen.densityfunction.DensityFunction;
import net.minecraft.world.gen.densityfunction.DensityFunctions;
import net.minecraft.world.gen.noise.NoiseRouter;

import java.util.function.Supplier;

import static com.minenash.customhud.HudElements.supplier.NumberSupplierElement.*;

public class DecimalSuppliers {

    private static final MinecraftClient client = MinecraftClient.getInstance();
    private static final Runtime runtime = Runtime.getRuntime();
    public static final VanillaBiomeParameters par = new VanillaBiomeParameters();

    private static Entity cameraEntity() { return client.getCameraEntity(); }
    private static boolean inNether() { return client.world.getRegistryKey().getValue().equals(World.NETHER.getValue()); }
    private static double toMiB(long bytes) { return bytes / 1024D / 1024L; }
    private static Entity hooked() { return client.player.fishHook == null ? null : client.player.fishHook.getHookedEntity(); }
    public static NoiseRouter sampler() { return ComplexData.serverWorld.getChunkManager().getNoiseConfig().getNoiseRouter(); }
    public static double sample(DensityFunction function) {
        BlockPos pos = client.player.getBlockPos();
        return function.sample(new DensityFunction.UnblendedNoisePos(pos.getX(), pos.getY(), pos.getZ()));
    }

    public static final Entry X = of( () -> cameraEntity().getX(), 3);
    public static final Entry Y = of( () -> cameraEntity().getY(), 3);
    public static final Entry Z = of( () -> cameraEntity().getZ(), 3);
    public static final Entry NETHER_X = of( () -> inNether() ? cameraEntity().getX() * 8 : cameraEntity().getX() / 8, 0);
    public static final Entry NETHER_Z = of( () -> inNether() ? cameraEntity().getZ() * 8 : cameraEntity().getZ() / 8, 0);

    public static final Entry TARGET_ENTITY_X = of( () -> ComplexData.targetEntity == null ? null : ComplexData.targetEntity.getX(), 0);
    public static final Entry TARGET_ENTITY_Y = of( () -> ComplexData.targetEntity == null ? null : ComplexData.targetEntity.getY(), 0);
    public static final Entry TARGET_ENTITY_Z = of( () -> ComplexData.targetEntity == null ? null : ComplexData.targetEntity.getZ(), 0);
    public static final Entry TARGET_ENTITY_DISTANCE = of( () -> ComplexData.targetEntity == null ? null : ComplexData.targetEntity.getPos().distanceTo(client.cameraEntity.getPos()), 1);

    public static final Entry REACH_DISTANCE = of ( () -> client.interactionManager.getReachDistance(), 1);
    public static final Entry FISHING_HOOK_DISTANCE = of ( () -> client.player.fishHook.distanceTo(client.player), 1);
    public static final Entry HOOKED_ENTITY_X = of ( () -> hooked() == null ? null : hooked().getX(), 0);
    public static final Entry HOOKED_ENTITY_Y = of ( () -> hooked() == null ? null : hooked().getY(), 0);
    public static final Entry HOOKED_ENTITY_Z = of ( () -> hooked() == null ? null : hooked().getZ(), 0);
    public static final Entry HOOKED_ENTITY_DISTANCE = of( () -> hooked() == null ? null : hooked().getPos().distanceTo(client.cameraEntity.getPos()), 1);

    public static final Entry VELOCITY_XZ = of( () -> ComplexData.velocityXZ, 1);
    public static final Entry VELOCITY_Y = of( () -> ComplexData.velocityY, 1);
    public static final Entry VELOCITY_XYZ = of( () -> ComplexData.velocityXYZ, 1);
    public static final Entry VELOCITY_XZ_KMH = of( () -> ComplexData.velocityXZ * 3.6, 1);
    public static final Entry VELOCITY_Y_KMH = of( () -> ComplexData.velocityY * 3.6, 1);
    public static final Entry VELOCITY_XYZ_KMH = of( () -> ComplexData.velocityXYZ * 3.6, 1);

    public static final Entry YAW = of( () -> MathHelper.wrapDegrees(cameraEntity().getYaw()), 1);
    public static final Entry PITCH = of( () -> MathHelper.wrapDegrees(cameraEntity().getPitch()), 1);

    public static final Entry LOCAL_DIFFICULTY = of( () -> ComplexData.localDifficulty.getLocalDifficulty(), 2);
    public static final Entry CLAMPED_LOCAL_DIFFICULTY = of( () -> ComplexData.localDifficulty.getClampedLocalDifficulty(), 2);
    public static final Entry MOOD = of( () -> client.player.getMoodPercentage() * 100.0F, 0);

    public static final Entry FRAME_MS_MIN = of( () -> ComplexData.frameTimeMetrics[1], 0);
    public static final Entry FRAME_MS_MAX = of( () -> ComplexData.frameTimeMetrics[2], 0);
    public static final Entry FRAME_MS_AVG = of( () -> ComplexData.frameTimeMetrics[0], 1);
    public static final Entry FRAME_MS_SAMPLES = of( () -> ComplexData.frameTimeMetrics[3], 0);

    public static final Entry FPS_MIN = of( () -> 1000 / ComplexData.frameTimeMetrics[2], 0);
    public static final Entry FPS_MAX = of( () -> 1000 / ComplexData.frameTimeMetrics[1], 0);
    public static final Entry FPS_AVG = of( () -> 1000 / ComplexData.frameTimeMetrics[0], 1);

    public static final Entry TICK_MS = of( () -> client.getServer() == null ? null : client.getServer().getTickTime(), 0);
    public static final Entry TICK_MS_MIN = of( () -> ComplexData.tickTimeMetrics[1], 0);
    public static final Entry TICK_MS_MAX = of( () -> ComplexData.tickTimeMetrics[2], 0);
    public static final Entry TICK_MS_AVG = of( () -> ComplexData.tickTimeMetrics[0], 1);
    public static final Entry TICK_MS_SAMPLES = of( () -> ComplexData.tickTimeMetrics[3], 0);

    public static final Entry TPS_MIN = of( () -> ComplexData.tpsMetrics[1], 0);
    public static final Entry TPS_MAX = of( () -> ComplexData.tpsMetrics[2], 0);
    public static final Entry TPS_AVG = of( () -> ComplexData.tpsMetrics[0], 1);

    public static final Entry PING_MIN = of( () -> ComplexData.pingMetrics[1], 0);
    public static final Entry PING_MAX = of( () -> ComplexData.pingMetrics[2], 0);
    public static final Entry PING_AVG = of( () -> ComplexData.pingMetrics[0], 1);
    public static final Entry PING_SAMPLES = of( () -> ComplexData.pingMetrics[3], 0);

    public static final Entry PACKET_SIZE_MIN = of( () -> ComplexData.packetSizeMetrics[1], 0);
    public static final Entry PACKET_SIZE_MAX = of( () -> ComplexData.packetSizeMetrics[2], 0);
    public static final Entry PACKET_SIZE_AVG = of( () -> ComplexData.packetSizeMetrics[0], 1);
    public static final Entry PACKET_SIZE_SAMPLES = of( () -> ComplexData.packetSizeMetrics[3], 0);

    public static final Entry SLOTS_PERCENTAGE = of( () -> 100F * ComplexData.slots_used / client.player.getInventory().main.size(), 0);

    public static final Entry RECORD_LENGTH = of( () -> MusicAndRecordTracker.isRecordPlaying ? MusicAndRecordTracker.recordLength / 20F : 0, 0);
    public static final Entry RECORD_ELAPSED = of( () -> MusicAndRecordTracker.isRecordPlaying ? MusicAndRecordTracker.recordElapsed / 20F : 0, 0);
    public static final Entry RECORD_REMAINING = of( () -> MusicAndRecordTracker.isRecordPlaying ? (MusicAndRecordTracker.recordLength - MusicAndRecordTracker.recordElapsed) / 20F : 0, 0);
    public static final Entry RECORD_ELAPSED_PER = of( () -> 100F * MusicAndRecordTracker.recordElapsed / MusicAndRecordTracker.recordLength, 0);

    public static final Entry XP_POINTS_PER = of( () -> client.player.experienceProgress * 100, 0);
    public static final Entry AIR_LEVEL_PERCENTAGE = of( () -> 100F * client.player.getAir() / client.player.getMaxAir(), 0);
    public static final Entry HEALTH_PERCENTAGE = of( () -> 100F * (client.player.getHealth() + client.player.getAbsorptionAmount()) / client.player.getMaxHealth(), 0);

    public static final Entry NOISE_ROUTER_TEMPERATURE = of( () -> sample(sampler().temperature()), 3);
    public static final Entry NOISE_ROUTER_VEGETATION = of( () -> sample(sampler().vegetation()), 3);
    public static final Entry NOISE_ROUTER_CONTINENTS = of( () -> sample(sampler().continents()), 3);
    public static final Entry NOISE_ROUTER_EROSION = of( () -> sample(sampler().erosion()), 3);
    public static final Entry NOISE_ROUTER_DEPTH = of( () -> sample(sampler().depth()), 3);
    public static final Entry NOISE_ROUTER_RIDGES = of( () -> sample(sampler().ridges()), 3);
    public static final Entry NOISE_ROUTER_PEAKS = of( () -> DensityFunctions.getPeaksValleysNoise((float)sample(sampler().ridges())), 3);
    public static final Entry NOISE_ROUTER_INIT_DENSITY = of( () -> sample(sampler().initialDensityWithoutJaggedness()), 3);
    public static final Entry NOISE_ROUTER_FINAL_DENSITY = of( () -> sample(sampler().finalDensity()), 3);

    @Deprecated public static final Entry ITEM_DURABILITY_PERCENT = of( () -> client.player.getMainHandStack().getDamage() / (float) client.player.getMainHandStack().getMaxDamage() * 100, 0);
    @Deprecated public static final Entry OFFHAND_ITEM_DURABILITY_PERCENT = of( () -> client.player.getOffHandStack().getDamage() / (float) client.player.getOffHandStack().getMaxDamage() * 100, 0);

    public static final Entry DAY = of( () -> client.world.getTimeOfDay() / 24000L, 0);

    public static final Entry TPS = of( () -> {
        IntegratedServer server = client.getServer();
        if (server == null) return null;
        float ms_tics = client.getServer().getTickTime();
        return ms_tics < 50 ? 20 : 1000/ms_tics;
    }, 0);

    public static final Entry CPU_USAGE = of( () -> ComplexData.cpuLoad, 0);
    public static final Entry GPU_USAGE = of(() -> ComplexData.gpuUsage, 0);
    public static final Entry MEMORY_USED_PERCENTAGE = of( () -> (runtime.totalMemory() - runtime.freeMemory())*100D / runtime.maxMemory(), 0);
    public static final Entry MEMORY_USED = of( () -> toMiB(runtime.totalMemory() - runtime.freeMemory()), 0);
    public static final Entry TOTAL_MEMORY = of( () -> toMiB(runtime.maxMemory()), 0);
    public static final Entry ALLOCATED_PERCENTAGE = of( () -> runtime.totalMemory() * 100 / runtime.maxMemory(), 0);
    public static final Entry ALLOCATED = of( () -> toMiB(runtime.totalMemory()), 0);
//    public static final Entry OFF_HEAP = of( () -> toMiB(ManagementFactory.getMemoryMXBean().getNonHeapMemoryUsage().getUsed() + NativeBuffer.getTotalAllocated()), 0);

}

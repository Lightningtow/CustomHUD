package com.minenash.customhud.NewHudElements.supplier;

import com.minenash.customhud.NewHudElements.HudElement2;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.Identifier;
import net.minecraft.world.dimension.DimensionType;

import java.util.function.Supplier;

public class BooleanSupplierElement implements HudElement2 {

    private static final MinecraftClient client = MinecraftClient.getInstance();
    private static boolean isInDim(Identifier id) { return client.world.getRegistryKey().getValue().equals(id); }

    public static final Supplier<Boolean> VSYNC = () -> client.options.enableVsync;

    public static final Supplier<Boolean> CHUNK_CULLING = () -> client.chunkCullingEnabled;
    public static final Supplier<Boolean> IN_OVERWORLD = () -> isInDim(DimensionType.OVERWORLD_ID);
    public static final Supplier<Boolean> IN_NETHER = () -> isInDim(DimensionType.THE_NETHER_ID);
    public static final Supplier<Boolean> IN_END = () -> isInDim(DimensionType.THE_END_ID);

    private final Supplier<Boolean> supplier;

    public BooleanSupplierElement(Supplier<Boolean> supplier) {
        this.supplier = supplier;
    }

    @Override
    public String getString() {
        return sanitize(supplier, false) ? "true" : "false";
    }

    @Override
    public Number getNumber() {
        return sanitize(supplier, false) ? 1 : 0;
    }

    @Override
    public boolean getBoolean() {
        return sanitize(supplier, false);
    }
}
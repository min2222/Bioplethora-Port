package io.github.bioplethora.registry;

import io.github.bioplethora.Bioplethora;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = Bioplethora.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class BPWoodTypes {
    public static final WoodType ENIVILE = WoodType.create(new ResourceLocation(Bioplethora.MOD_ID, "enivile").toString());
    public static final WoodType CAERULWOOD = WoodType.create(new ResourceLocation(Bioplethora.MOD_ID, "caerulwood").toString());
    public static final WoodType PETRAWOOD = WoodType.create(new ResourceLocation(Bioplethora.MOD_ID, "petrawood").toString());

    public static void registerWoodType(final FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            WoodType.register(ENIVILE);
            WoodType.register(CAERULWOOD);
            WoodType.register(PETRAWOOD);
        });
    }
}

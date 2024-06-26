package io.github.bioplethora.registry;

import io.github.bioplethora.Bioplethora;
import io.github.bioplethora.entity.creatures.AlphemEntity;
import io.github.bioplethora.entity.creatures.AlphemKingEntity;
import io.github.bioplethora.entity.creatures.AltyrusEntity;
import io.github.bioplethora.entity.creatures.CavernFleignarEntity;
import io.github.bioplethora.entity.creatures.CrephoxlEntity;
import io.github.bioplethora.entity.creatures.CuttlefishEntity;
import io.github.bioplethora.entity.creatures.DwarfMossadileEntity;
import io.github.bioplethora.entity.creatures.EurydnEntity;
import io.github.bioplethora.entity.creatures.FrostbiteGolemEntity;
import io.github.bioplethora.entity.creatures.GaugalemEntity;
import io.github.bioplethora.entity.creatures.GrylynenEntity;
import io.github.bioplethora.entity.creatures.MyliothanEntity;
import io.github.bioplethora.entity.creatures.MyuthineEntity;
import io.github.bioplethora.entity.creatures.NandbriEntity;
import io.github.bioplethora.entity.creatures.OnofishEntity;
import io.github.bioplethora.entity.creatures.PeaguinEntity;
import io.github.bioplethora.entity.creatures.ShachathEntity;
import io.github.bioplethora.entity.creatures.TelemreyeEntity;
import io.github.bioplethora.entity.creatures.TerraithEntity;
import io.github.bioplethora.entity.creatures.TrapjawEntity;
import io.github.bioplethora.entity.creatures.VoidjawEntity;
import io.github.bioplethora.entity.others.PrimordialRingEntity;
import io.github.bioplethora.enums.BPGrylynenTier;
import net.minecraft.world.entity.animal.AbstractFish;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Bioplethora.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class BPEntityAttributes {

    @SubscribeEvent
    public static void registerEntityAttributes(EntityAttributeCreationEvent event) {

        //Ecoharmless
        event.put(BPEntities.CUTTLEFISH.get(), CuttlefishEntity.setCustomAttributes().build());
        event.put(BPEntities.ONOFISH.get(), OnofishEntity.setCustomAttributes().build());
        event.put(BPEntities.TRIGGERFISH.get(), AbstractFish.createAttributes().build());

        event.put(BPEntities.SOUL_EURYDN.get(), EurydnEntity.setCustomAttributes().build());
        event.put(BPEntities.FIERY_EURYDN.get(), EurydnEntity.setCustomAttributes().build());

        //Plethoneutral
        event.put(BPEntities.PEAGUIN.get(), PeaguinEntity.setCustomAttributes().build());
        event.put(BPEntities.NANDBRI.get(), NandbriEntity.setCustomAttributes().build());
        event.put(BPEntities.CAVERN_FLEIGNAR.get(), CavernFleignarEntity.setCustomAttributes().build());

        //Dangerum
        event.put(BPEntities.ALPHEM.get(), AlphemEntity.setCustomAttributes().build());
        event.put(BPEntities.GAUGALEM.get(), GaugalemEntity.setCustomAttributes().build());
        event.put(BPEntities.DWARF_MOSSADILE.get(), DwarfMossadileEntity.setCustomAttributes().build());
        event.put(BPEntities.TRAPJAW.get(), TrapjawEntity.setCustomAttributes().build());
        event.put(BPEntities.TERRAITH.get(), TerraithEntity.setCustomAttributes().build());
        event.put(BPEntities.MYUTHINE.get(), MyuthineEntity.setCustomAttributes().build());

        event.put(BPEntities.WOODEN_GRYLYNEN.get(), GrylynenEntity.setCustomAttributes(BPGrylynenTier.WOODEN).build());
        event.put(BPEntities.STONE_GRYLYNEN.get(), GrylynenEntity.setCustomAttributes(BPGrylynenTier.STONE).build());
        event.put(BPEntities.GOLDEN_GRYLYNEN.get(), GrylynenEntity.setCustomAttributes(BPGrylynenTier.GOLDEN).build());
        event.put(BPEntities.IRON_GRYLYNEN.get(), GrylynenEntity.setCustomAttributes(BPGrylynenTier.IRON).build());
        event.put(BPEntities.DIAMOND_GRYLYNEN.get(), GrylynenEntity.setCustomAttributes(BPGrylynenTier.DIAMOND).build());
        event.put(BPEntities.NETHERITE_GRYLYNEN.get(), GrylynenEntity.setCustomAttributes(BPGrylynenTier.NETHERITE).build());

        //Hellsent
        event.put(BPEntities.SHACHATH.get(), ShachathEntity.setCustomAttributes().build());
        event.put(BPEntities.CREPHOXL.get(), CrephoxlEntity.setCustomAttributes().build());
        event.put(BPEntities.FROSTBITE_GOLEM.get(), FrostbiteGolemEntity.setCustomAttributes().build());
        event.put(BPEntities.TELEMREYE.get(), TelemreyeEntity.setCustomAttributes().build());
        event.put(BPEntities.VOIDJAW.get(), VoidjawEntity.setCustomAttributes().build());

        //Elderia
        event.put(BPEntities.ALTYRUS.get(), AltyrusEntity.setCustomAttributes().build());
        event.put(BPEntities.MYLIOTHAN.get(), MyliothanEntity.setCustomAttributes().build());
        event.put(BPEntities.ALPHEM_KING.get(), AlphemKingEntity.setCustomAttributes().build());

        //Others
        event.put(BPEntities.PRIMORDIAL_RING.get(), PrimordialRingEntity.setCustomAttributes().build());
    }
}

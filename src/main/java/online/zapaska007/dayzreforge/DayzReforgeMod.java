package online.zapaska007.dayzreforge;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import online.zapaska007.dayzreforge.registry.ModBlocks;
import online.zapaska007.dayzreforge.registry.ModCreativeTabs;
import online.zapaska007.dayzreforge.registry.ModEffects;
import online.zapaska007.dayzreforge.registry.ModItems;
import online.zapaska007.dayzreforge.registry.ModRecipes;
import online.zapaska007.dayzreforge.registry.ModSounds;

@Mod(DayzReforgeMod.MOD_ID)
public class DayzReforgeMod {
    public static final String MOD_ID = "dayzreforge";

    public DayzReforgeMod(FMLJavaModLoadingContext context) {
        IEventBus modEventBus = context.getModEventBus();

        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModRecipes.register(modEventBus);
        ModCreativeTabs.register(modEventBus);
        ModEffects.register(modEventBus);
        ModSounds.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);

        modEventBus.addListener(this::setup);
    }

    private void setup(final net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            online.zapaska007.dayzreforge.network.ModMessages.register();
        });
    }
}

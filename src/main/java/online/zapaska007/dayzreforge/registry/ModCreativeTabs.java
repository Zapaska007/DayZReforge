package online.zapaska007.dayzreforge.registry;

import online.zapaska007.dayzreforge.DayzReforgeMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeTabs {
        public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister
                        .create(Registries.CREATIVE_MODE_TAB, DayzReforgeMod.MOD_ID);

        public static final RegistryObject<CreativeModeTab> MEDICINE_TAB = CREATIVE_MODE_TABS.register("medicine_tab",
                        () -> CreativeModeTab.builder()
                                        .icon(() -> new ItemStack(ModItems.TAB_ICON_MEDICINE.get()))
                                        .title(Component.translatable("creativetab.dayzreforge_medicine"))
                                        .displayItems((parameters, output) -> {
                                                output.accept(ModItems.MEDICAL_CHELATED_TABLETS.get());
                                                output.accept(ModItems.MEDICAL_DISINFECTANT_SPRAY.get());
                                                output.accept(ModItems.MEDICAL_ANTIDOTE_POX.get());
                                                output.accept(ModItems.MEDICAL_ALCOHOLIC_TINCTURE.get());
                                                output.accept(ModItems.MEDICAL_IODINE_TINCTURE.get());
                                                output.accept(ModItems.MEDICAL_SEWING_KIT.get());
                                                output.accept(ModItems.MEDICAL_ACTIVATED_CARBON.get());
                                                output.accept(ModItems.MEDICAL_CODEINE_TABLETS.get());
                                                output.accept(ModItems.MEDICAL_CHLORINE_TABLETS.get());
                                                output.accept(ModItems.MEDICAL_TETRACYCLINE_TABLETS.get());
                                                output.accept(ModItems.MEDICAL_MULTIVITAMIN_PILLS.get());
                                                output.accept(ModItems.MEDICAL_THERMOMETER.get());
                                                output.accept(ModItems.MEDICAL_ADRENALINE.get());
                                                output.accept(ModItems.MEDICAL_MORPHINE.get());
                                                output.accept(ModItems.MEDICAL_CHEMICAL_HEATING_PAD.get());
                                                output.accept(ModItems.MEDICAL_BANDAGE.get());
                                                output.accept(ModItems.MEDICAL_RAGS.get());
                                        })
                                        .build());

        public static final RegistryObject<CreativeModeTab> FOOD_TAB = CREATIVE_MODE_TABS.register("food_tab",
                        () -> CreativeModeTab.builder()
                                        .icon(() -> new ItemStack(ModItems.TAB_ICON_FOOD.get()))
                                        .title(Component.translatable("creativetab.dayzreforge_food"))
                                        .displayItems((parameters, output) -> {
                                                output.accept(ModItems.FOOD_CANNED_CRAB.get());
                                                output.accept(ModItems.FOOD_BOTTLE_FILTER.get());
                                                output.accept(ModItems.FOOD_CHIPS.get());
                                                output.accept(ModItems.FOOD_SALTY_STICKS.get());
                                                output.accept(ModItems.FOOD_CRACKERS.get());
                                                output.accept(ModItems.FOOD_ZAGORKY.get());
                                                output.accept(ModItems.FOOD_ZAGORKY_CHOCOLATE.get());
                                                output.accept(ModItems.FOOD_ZAGORKY_PEANUTS.get());
                                                output.accept(ModItems.FOOD_PAJKA.get());
                                                output.accept(ModItems.FOOD_LIVER_PATE.get());
                                                output.accept(ModItems.FOOD_PORK_PATE.get());
                                                output.accept(ModItems.FOOD_CANNED_PORK.get());
                                                output.accept(ModItems.FOOD_COLD_CUTS.get());
                                                output.accept(ModItems.FOOD_DOG_FOOD.get());
                                                output.accept(ModItems.FOOD_CANNED_CAT_FOOD.get());
                                                output.accept(ModItems.FOOD_MYSTERIOUS_CANNED_GOODS.get());
                                                output.accept(ModItems.FOOD_CANNED_SARDINES.get());
                                                output.accept(ModItems.FOOD_STRAWBERRY_JAM.get());
                                                output.accept(ModItems.FOOD_HONEY.get());
                                                output.accept(ModItems.FOOD_POWDERED_MILK.get());
                                                output.accept(ModItems.FOOD_RICE.get());
                                                output.accept(ModItems.FOOD_CANNED_SPAGHETTI.get());
                                                output.accept(ModItems.FOOD_BRAISED_BEANS.get());
                                                output.accept(ModItems.FOOD_CANNED_PEACHES.get());
                                                output.accept(ModItems.FOOD_CANNED_BACON.get());
                                                output.accept(ModItems.FOOD_CANNED_TUNA.get());
                                                output.accept(ModItems.FOOD_MAD_MONK_KVASS.get());
                                                output.accept(ModItems.FOOD_FRONTA_LEMONADE.get());
                                                output.accept(ModItems.FOOD_SPITE_LEMONADE.get());
                                                output.accept(ModItems.FOOD_NOTA_COLA.get());
                                                output.accept(ModItems.FOOD_PIPSI_COLA.get());
                                                output.accept(ModItems.FOOD_FLAKES.get());
                                                output.accept(ModItems.FOOD_FLASK.get());
                                                output.accept(ModItems.FOOD_PLASTIC_BOTTLE.get());
                                                output.accept(ModItems.FOOD_HUMAN_MEAT.get());
                                        })
                                        .build());

        public static final RegistryObject<CreativeModeTab> WEAPON_TAB = CREATIVE_MODE_TABS.register("weapon_tab",
                        () -> CreativeModeTab.builder()
                                        .withTabsBefore(FOOD_TAB.getId())
                                        .icon(() -> new ItemStack(ModItems.TAB_ICON_WEAPON.get()))
                                        .title(Component.translatable("creativetab.dayzreforge_weapon"))
                                        .displayItems((parameters, output) -> {
                                        })
                                        .build());

        public static final RegistryObject<CreativeModeTab> BACKPACKS_TAB = CREATIVE_MODE_TABS.register("backpacks_tab",
                        () -> CreativeModeTab.builder()
                                        .withTabsBefore(WEAPON_TAB.getId())
                                        .icon(() -> new ItemStack(ModItems.TAB_ICON_BACKPACKS.get()))
                                        .title(Component.translatable("creativetab.dayzreforge_backpacks"))
                                        .displayItems((parameters, output) -> {
                                        })
                                        .build());

        public static final RegistryObject<CreativeModeTab> ARMORS_TAB = CREATIVE_MODE_TABS.register("armors_tab",
                        () -> CreativeModeTab.builder()
                                        .withTabsBefore(BACKPACKS_TAB.getId())
                                        .icon(() -> new ItemStack(ModItems.TAB_ICON_ARMORS.get()))
                                        .title(Component.translatable("creativetab.dayzreforge_armors"))
                                        .displayItems((parameters, output) -> {
                                        })
                                        .build());

        public static final RegistryObject<CreativeModeTab> BLOCKS_TAB = CREATIVE_MODE_TABS.register("blocks_tab",
                        () -> CreativeModeTab.builder()
                                        .withTabsBefore(ARMORS_TAB.getId())
                                        .icon(() -> new ItemStack(ModItems.TAB_ICON_BLOCKS.get()))
                                        .title(Component.translatable("creativetab.dayzreforge_blocks"))
                                        .displayItems((parameters, output) -> {
                                                output.accept(ModBlocks.WATER_PUMP.get());
                                        })
                                        .build());

        public static void register(IEventBus eventBus) {
                CREATIVE_MODE_TABS.register(eventBus);
        }
}

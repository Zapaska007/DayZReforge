package online.zapaska007.dayzreforge.registry;

import online.zapaska007.dayzreforge.DayzReforgeMod;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import online.zapaska007.dayzreforge.item.CanteenItem;
import online.zapaska007.dayzreforge.item.DrinkItem;
import online.zapaska007.dayzreforge.item.MedicalItem;
import online.zapaska007.dayzreforge.registry.ModEffects;
import net.minecraft.world.food.FoodProperties;

public class ModItems {
        public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS,
                        DayzReforgeMod.MOD_ID);

        // =============================
        // TAB ICONS (Скрытые предметы для иконок)
        // =============================
        public static final RegistryObject<Item> TAB_ICON_MEDICINE = ITEMS.register("dayzreforge_medicine",
                        () -> new Item(new Item.Properties()));
        public static final RegistryObject<Item> TAB_ICON_FOOD = ITEMS.register("dayzreforge_food",
                        () -> new Item(new Item.Properties()));
        public static final RegistryObject<Item> TAB_ICON_WEAPON = ITEMS.register("dayzreforge_weapon",
                        () -> new Item(new Item.Properties()));
        public static final RegistryObject<Item> TAB_ICON_BACKPACKS = ITEMS.register("dayzreforge_backpacks",
                        () -> new Item(new Item.Properties()));
        public static final RegistryObject<Item> TAB_ICON_ARMORS = ITEMS.register("dayzreforge_armors",
                        () -> new Item(new Item.Properties()));
        public static final RegistryObject<Item> TAB_ICON_BLOCKS = ITEMS.register("dayzreforge_blocks",
                        () -> new Item(new Item.Properties()));

        // =============================
        // MEDICINE (Медикаменты)
        // =============================
        public static final RegistryObject<Item> MEDICAL_CHELATED_TABLETS = ITEMS.register("medical_chelated_tablets",
                        () -> new Item(new Item.Properties().stacksTo(20)));
        public static final RegistryObject<Item> MEDICAL_DISINFECTANT_SPRAY = ITEMS.register(
                        "medical_disinfectant_spray",
                        () -> new online.zapaska007.dayzreforge.item.DisinfectantItem(
                                        new Item.Properties().stacksTo(1)));
        public static final RegistryObject<Item> MEDICAL_ANTIDOTE_POX = ITEMS.register("medical_antidote_pox",
                        () -> new Item(new Item.Properties().stacksTo(1)));
        public static final RegistryObject<Item> MEDICAL_ALCOHOLIC_TINCTURE = ITEMS.register(
                        "medical_alcoholic_tincture",
                        () -> new online.zapaska007.dayzreforge.item.DisinfectantItem(
                                        new Item.Properties().stacksTo(1)));
        public static final RegistryObject<Item> MEDICAL_IODINE_TINCTURE = ITEMS.register("medical_iodine_tincture",
                        () -> new online.zapaska007.dayzreforge.item.DisinfectantItem(
                                        new Item.Properties().stacksTo(1)));
        public static final RegistryObject<Item> MEDICAL_ACTIVATED_CARBON = ITEMS.register("medical_activated_carbon",
                        () -> new Item(new Item.Properties().stacksTo(12)));
        public static final RegistryObject<Item> MEDICAL_CODEINE_TABLETS = ITEMS.register("medical_codeine_tablets",
                        () -> new Item(new Item.Properties().stacksTo(12)));
        public static final RegistryObject<Item> MEDICAL_CHLORINE_TABLETS = ITEMS.register("medical_chlorine_tablets",
                        () -> new Item(new Item.Properties().stacksTo(10)));
        public static final RegistryObject<Item> MEDICAL_TETRACYCLINE_TABLETS = ITEMS
                        .register("medical_tetracycline_tablets", () -> new Item(new Item.Properties().stacksTo(12)));
        public static final RegistryObject<Item> MEDICAL_MULTIVITAMIN_PILLS = ITEMS.register(
                        "medical_multivitamin_pills",
                        () -> new Item(new Item.Properties().stacksTo(50)));
        public static final RegistryObject<Item> MEDICAL_THERMOMETER = ITEMS.register("medical_thermometer",
                        () -> new Item(new Item.Properties().stacksTo(1)));
        public static final RegistryObject<Item> MEDICAL_ADRENALINE = ITEMS.register("medical_adrenaline",
                        () -> new Item(new Item.Properties().stacksTo(1)));
        public static final RegistryObject<Item> MEDICAL_MORPHINE = ITEMS.register("medical_morphine",
                        () -> new Item(new Item.Properties().stacksTo(1)));
        public static final RegistryObject<Item> MEDICAL_CHEMICAL_HEATING_PAD = ITEMS
                        .register("medical_chemical_heating_pad", () -> new Item(new Item.Properties().stacksTo(1)));
        public static final RegistryObject<Item> MEDICAL_BANDAGE = ITEMS.register("medical_bandage",
                        () -> new MedicalItem(new Item.Properties().stacksTo(1).defaultDurability(3)));
        public static final RegistryObject<Item> MEDICAL_RAGS = ITEMS.register("medical_rags",
                        () -> new MedicalItem(new Item.Properties().stacksTo(5)));
        public static final RegistryObject<Item> MEDICAL_SEWING_KIT = ITEMS.register("medical_sewing_kit",
                        () -> new MedicalItem(new Item.Properties().stacksTo(1).defaultDurability(3)));

        // =============================
        // FOOD & DRINKS & CONTAINERS
        // =============================
        public static final RegistryObject<Item> FOOD_CANNED_CRAB = ITEMS.register("food_canned_crab",
                        () -> new Item(new Item.Properties().stacksTo(1).food(createFood(8, 4.2f))));
        public static final RegistryObject<Item> FOOD_BOTTLE_FILTER = ITEMS.register("food_bottle_filter",
                        () -> new CanteenItem(new Item.Properties().stacksTo(1), 10, true)); // Контейнер, всегда
                                                                                             // очищенная вода
        public static final RegistryObject<Item> FOOD_CHIPS = ITEMS.register("food_chips",
                        () -> new Item(new Item.Properties().stacksTo(1).food(createFood(2, 0.8f))));
        public static final RegistryObject<Item> FOOD_SALTY_STICKS = ITEMS.register("food_salty_sticks",
                        () -> new Item(new Item.Properties().stacksTo(1).food(createFood(1, 0.4f))));
        public static final RegistryObject<Item> FOOD_CRACKERS = ITEMS.register("food_crackers",
                        () -> new Item(new Item.Properties().stacksTo(1).food(createFood(1, 0.6f))));
        public static final RegistryObject<Item> FOOD_ZAGORKY = ITEMS.register("food_zagorky",
                        () -> new Item(new Item.Properties().stacksTo(1).food(createFood(2, 0.8f))));
        public static final RegistryObject<Item> FOOD_ZAGORKY_CHOCOLATE = ITEMS.register("food_zagorky_chocolate",
                        () -> new Item(new Item.Properties().stacksTo(1).food(createFood(3, 1.6f))));
        public static final RegistryObject<Item> FOOD_ZAGORKY_PEANUTS = ITEMS.register("food_zagorky_peanuts",
                        () -> new Item(new Item.Properties().stacksTo(1).food(createFood(3, 1.2f))));
        public static final RegistryObject<Item> FOOD_PAJKA = ITEMS.register("food_pajka",
                        () -> new Item(new Item.Properties().stacksTo(1).food(createFood(3, 1.2f))));
        public static final RegistryObject<Item> FOOD_LIVER_PATE = ITEMS.register("food_liver_pate",
                        () -> new Item(new Item.Properties().stacksTo(1).food(createFood(4, 2.1f))));
        public static final RegistryObject<Item> FOOD_PORK_PATE = ITEMS.register("food_prok_pate",
                        () -> new Item(new Item.Properties().stacksTo(1).food(createFood(4, 2.1f))));
        public static final RegistryObject<Item> FOOD_CANNED_PORK = ITEMS.register("food_canned_pork",
                        () -> new Item(new Item.Properties().stacksTo(1).food(createFood(9, 4.7f))));
        public static final RegistryObject<Item> FOOD_COLD_CUTS = ITEMS.register("food_cold_cuts",
                        () -> new Item(new Item.Properties().stacksTo(1).food(createFood(4, 2.0f)))); // Дефолт
        public static final RegistryObject<Item> FOOD_DOG_FOOD = ITEMS.register("food_dog_food",
                        () -> new Item(new Item.Properties().stacksTo(1).food(createFood(6, 3.1f))));
        public static final RegistryObject<Item> FOOD_CANNED_CAT_FOOD = ITEMS.register("food_canned_cat_food",
                        () -> new Item(new Item.Properties().stacksTo(1).food(createFood(5, 2.6f))));
        public static final RegistryObject<Item> FOOD_MYSTERIOUS_CANNED_GOODS = ITEMS.register(
                        "food_mysterious_canned_goods",
                        () -> new Item(new Item.Properties().stacksTo(1).food(createFood(13, 6.7f))));
        public static final RegistryObject<Item> FOOD_CANNED_SARDINES = ITEMS.register("food_canned_sardines",
                        () -> new Item(new Item.Properties().stacksTo(1).food(createFood(4, 2.1f))));
        public static final RegistryObject<Item> FOOD_STRAWBERRY_JAM = ITEMS.register("food_strawberry_jam",
                        () -> new Item(new Item.Properties().stacksTo(1).food(createFood(10, 5.0f))));
        public static final RegistryObject<Item> FOOD_HONEY = ITEMS.register("food_honey",
                        () -> new Item(new Item.Properties().stacksTo(1).food(createFood(17, 8.3f))));
        public static final RegistryObject<Item> FOOD_POWDERED_MILK = ITEMS.register("food_powdered_milk",
                        () -> new Item(new Item.Properties().stacksTo(1).food(createFood(13, 6.7f))));
        public static final RegistryObject<Item> FOOD_RICE = ITEMS.register("food_rice",
                        () -> new Item(new Item.Properties().stacksTo(1).food(createFood(8, 4.2f))));
        public static final RegistryObject<Item> FOOD_CANNED_SPAGHETTI = ITEMS.register("food_canned_spaghetti",
                        () -> new Item(new Item.Properties().stacksTo(1).food(createFood(12, 5.8f))));
        public static final RegistryObject<Item> FOOD_BRAISED_BEANS = ITEMS.register("food_braised_beans",
                        () -> new Item(new Item.Properties().stacksTo(1).food(createFood(13, 6.7f))));
        public static final RegistryObject<Item> FOOD_CANNED_PEACHES = ITEMS.register("food_canned_peaches",
                        () -> new Item(new Item.Properties().stacksTo(1).food(createFood(10, 5.0f))));
        public static final RegistryObject<Item> FOOD_CANNED_BACON = ITEMS.register("food_canned_bacon",
                        () -> new Item(new Item.Properties().stacksTo(1).food(createFood(20, 10.0f))));
        public static final RegistryObject<Item> FOOD_CANNED_TUNA = ITEMS.register("food_canned_tuna",
                        () -> new Item(new Item.Properties().stacksTo(1).food(createFood(6, 3.1f))));

        // Drinks
        public static final RegistryObject<Item> FOOD_MAD_MONK_KVASS = ITEMS.register("food_mad_monk_kvass",
                        () -> new DrinkItem(new Item.Properties().stacksTo(1).food(createDrink(3, 1.4f))));
        public static final RegistryObject<Item> FOOD_FRONTA_LEMONADE = ITEMS.register("food_fronta_lemonade",
                        () -> new DrinkItem(new Item.Properties().stacksTo(1).food(createDrink(3, 1.4f))));
        public static final RegistryObject<Item> FOOD_SPITE_LEMONADE = ITEMS.register("food_spite_lemonade",
                        () -> new DrinkItem(new Item.Properties().stacksTo(1).food(createDrink(3, 1.4f))));
        public static final RegistryObject<Item> FOOD_NOTA_COLA = ITEMS.register("food_nota_cola",
                        () -> new DrinkItem(new Item.Properties().stacksTo(1).food(createDrink(3, 1.4f))));
        public static final RegistryObject<Item> FOOD_PIPSI_COLA = ITEMS.register("food_pipsi_cola",
                        () -> new DrinkItem(new Item.Properties().stacksTo(1).food(createDrink(3, 1.4f))));

        public static final RegistryObject<Item> FOOD_FLAKES = ITEMS.register("food_flakes",
                        () -> new Item(new Item.Properties().stacksTo(1).food(createFood(17, 8.3f))));
        public static final RegistryObject<Item> FOOD_FLASK = ITEMS.register("food_flask",
                        () -> new CanteenItem(new Item.Properties().stacksTo(1), 10, false)); // Фляжка
        public static final RegistryObject<Item> FOOD_PLASTIC_BOTTLE = ITEMS.register("food_plastic_bottle",
                        () -> new CanteenItem(new Item.Properties().stacksTo(1), 10, false)); // Пластиковая бутылка
        public static final RegistryObject<Item> FOOD_HUMAN_MEAT = ITEMS.register("food_human_meat",
                        () -> new Item(new Item.Properties().stacksTo(1).food(
                                        new FoodProperties.Builder().nutrition(1).saturationMod(0.6f)
                                                        .meat().build())));

        private static FoodProperties createFood(int nutrition, float saturationRestored) {
                float modifier = (nutrition > 0) ? (saturationRestored / (nutrition * 2.0f)) : 0;
                return new FoodProperties.Builder().nutrition(nutrition).saturationMod(modifier).build();
        }

        private static FoodProperties createDrink(int nutrition, float saturationRestored) {
                float modifier = (nutrition > 0) ? (saturationRestored / (nutrition * 2.0f)) : 0;
                return new FoodProperties.Builder().nutrition(nutrition).saturationMod(modifier).alwaysEat().build();
        }

        public static void register(IEventBus eventBus) {
                ITEMS.register(eventBus);
        }
}

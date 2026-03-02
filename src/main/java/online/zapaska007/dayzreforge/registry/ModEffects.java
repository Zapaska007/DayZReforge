package online.zapaska007.dayzreforge.registry;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import online.zapaska007.dayzreforge.DayzReforgeMod;
import online.zapaska007.dayzreforge.effect.BleedingEffect;

public class ModEffects {
        public static final DeferredRegister<MobEffect> MOB_EFFECTS = DeferredRegister.create(
                        ForgeRegistries.MOB_EFFECTS,
                        DayzReforgeMod.MOD_ID);

        public static final RegistryObject<MobEffect> BLEEDING = MOB_EFFECTS.register("bleeding",
                        () -> new BleedingEffect(MobEffectCategory.HARMFUL, 0x8A0303)); // Dark Red Blood Color

        public static final RegistryObject<MobEffect> BLOOD_INFECTION = MOB_EFFECTS.register("blood_infection",
                        () -> new online.zapaska007.dayzreforge.effect.BloodInfectionEffect(MobEffectCategory.HARMFUL,
                                        0x5C0000));

        public static final RegistryObject<MobEffect> KURU = MOB_EFFECTS.register("kuru",
                        () -> new online.zapaska007.dayzreforge.effect.KuruEffect(MobEffectCategory.HARMFUL, 0x4D3300));

        public static final RegistryObject<MobEffect> PNEUMONIA = MOB_EFFECTS.register("pneumonia",
                        () -> new online.zapaska007.dayzreforge.effect.PneumoniaEffect(MobEffectCategory.HARMFUL,
                                        0x00FFDD));

        public static final RegistryObject<MobEffect> MULTIVITAMIN_BUFF = MOB_EFFECTS.register("multivitamin_buff",
                        () -> new online.zapaska007.dayzreforge.effect.MultivitaminEffect(MobEffectCategory.BENEFICIAL,
                                        0xFFE100));

        public static final RegistryObject<MobEffect> HEATING_PAD = MOB_EFFECTS.register("heating_pad",
                        () -> new online.zapaska007.dayzreforge.effect.HeatingPadEffect(MobEffectCategory.BENEFICIAL,
                                        0xFF4400));

        public static void register(IEventBus eventBus) {
                MOB_EFFECTS.register(eventBus);
        }
}

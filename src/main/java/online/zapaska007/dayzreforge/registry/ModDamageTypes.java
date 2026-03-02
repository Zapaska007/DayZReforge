package online.zapaska007.dayzreforge.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageType;
import online.zapaska007.dayzreforge.DayzReforgeMod;

public class ModDamageTypes {
        public static final ResourceKey<DamageType> KURU = ResourceKey.create(Registries.DAMAGE_TYPE,
                        ResourceLocation.fromNamespaceAndPath(DayzReforgeMod.MOD_ID, "kuru"));

        public static final ResourceKey<DamageType> SUICIDE = ResourceKey.create(Registries.DAMAGE_TYPE,
                        ResourceLocation.fromNamespaceAndPath(DayzReforgeMod.MOD_ID, "suicide"));

        public static final ResourceKey<DamageType> PNEUMONIA = ResourceKey.create(Registries.DAMAGE_TYPE,
                        ResourceLocation.fromNamespaceAndPath(DayzReforgeMod.MOD_ID, "pneumonia"));
}

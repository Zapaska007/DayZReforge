package online.zapaska007.dayzreforge.event;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import online.zapaska007.dayzreforge.DayzReforgeMod;
import online.zapaska007.dayzreforge.registry.ModEffects;
import sfiomn.legendarysurvivaloverhaul.api.bodydamage.BodyPartEnum;
import sfiomn.legendarysurvivaloverhaul.api.bodydamage.IBodyDamageCapability;
import sfiomn.legendarysurvivaloverhaul.util.CapabilityUtil;

@Mod.EventBusSubscriber(modid = DayzReforgeMod.MOD_ID)
public class DayzReforgeEvents {

    @SubscribeEvent
    public static void onPlayerDamage(LivingDamageEvent event) {
        LivingEntity entity = event.getEntity();
        if (entity.level().isClientSide() || !(entity instanceof Player player)) {
            return;
        }

        // Only apply bleeding from certain damage types or if damage is high enough
        float damageAmount = event.getAmount();
        if (damageAmount <= 1.0f) {
            return;
        }

        // Get LSO Body Damage capability
        IBodyDamageCapability capability = CapabilityUtil.getBodyDamageCapability(player);
        if (capability == null)
            return;

        // Check the health of specific parts
        float armLHealth = capability.getBodyPartHealthRatio(BodyPartEnum.LEFT_ARM);
        float armRHealth = capability.getBodyPartHealthRatio(BodyPartEnum.RIGHT_ARM);
        float legLHealth = capability.getBodyPartHealthRatio(BodyPartEnum.LEFT_LEG);
        float legRHealth = capability.getBodyPartHealthRatio(BodyPartEnum.RIGHT_LEG);
        float chestHealth = capability.getBodyPartHealthRatio(BodyPartEnum.CHEST);
        float headHealth = capability.getBodyPartHealthRatio(BodyPartEnum.HEAD);

        // Find the lowest health among these parts (ignoring feet)
        float minHealth = Math.min(chestHealth, Math.min(headHealth,
                Math.min(legLHealth, Math.min(legRHealth,
                        Math.min(armLHealth, armRHealth)))));

        // If taking significant damage and limb health is low, apply bleeding
        if (player.getRandom().nextFloat() < 0.4f) { // 40% chance on taking >1 damage
            int amplifier = 0; // Stage 1 (0)

            if (minHealth <= 0.3f) {
                amplifier = 2; // Stage 3 (2)
            } else if (minHealth <= 0.6f) {
                amplifier = 1; // Stage 2 (1)
            }

            // Apply Bleeding Effect: infinite duration (-1), cured by bandage
            player.addEffect(new MobEffectInstance(ModEffects.BLEEDING.get(), -1, amplifier, false, false, true));
        }
    }

    @SubscribeEvent
    public static void onHealItemUsed(LivingEntityUseItemEvent.Finish event) {
        if (!event.getEntity().level().isClientSide() && event.getEntity() instanceof Player player) {
            // LSO's Bandages trigger item use finish event when healing is successful
            if (event.getItem().getItem() instanceof online.zapaska007.dayzreforge.item.MedicalItem) {
                // Remove Bleeding
                if (player.hasEffect(ModEffects.BLEEDING.get())) {
                    player.removeEffect(ModEffects.BLEEDING.get());
                }

                // Apply Blood Infection if treating with dirty items
                if (event.getItem().getItem() == online.zapaska007.dayzreforge.registry.ModItems.MEDICAL_RAGS.get() ||
                        event.getItem().getItem() == online.zapaska007.dayzreforge.registry.ModItems.MEDICAL_SEWING_KIT
                                .get()) {

                    boolean isSterilized = event.getItem().hasTag()
                            && event.getItem().getTag().getBoolean("Sterilized");

                    if (!isSterilized && !player.hasEffect(ModEffects.BLOOD_INFECTION.get())) {
                        // 15 minutes (18000 ticks), stage 1 (0)
                        player.addEffect(
                                new MobEffectInstance(ModEffects.BLOOD_INFECTION.get(), 18000, 0, false, false, true));
                    }
                }
            }

            // Handle eating Kuru-infected Human Meat
            if (event.getItem().getItem() == online.zapaska007.dayzreforge.registry.ModItems.FOOD_HUMAN_MEAT.get()) {
                if (!player.hasEffect(ModEffects.KURU.get())) {
                    player.addEffect(new MobEffectInstance(ModEffects.KURU.get(), 12000, 0, false, false, true));
                }
            }
        }
    }
}

package online.zapaska007.dayzreforge.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import sfiomn.legendarysurvivaloverhaul.util.CapabilityUtil;
import online.zapaska007.dayzreforge.registry.ModEffects;

public class MultivitaminEffect extends MobEffect {
    public MultivitaminEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return duration % 20 == 0; // Check every second (20 ticks)
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        if (!entity.level().isClientSide() && entity instanceof Player player) {

            boolean foodIsGood = player.getFoodData().getFoodLevel() >= 18; // 9 shanks

            boolean waterIsGood = false;
            sfiomn.legendarysurvivaloverhaul.common.capabilities.thirst.ThirstCapability thirstCapability = CapabilityUtil
                    .getThirstCapability(player);
            if (thirstCapability != null) {
                waterIsGood = thirstCapability.getHydrationLevel() >= 18;
            }

            boolean tempIsGood = false;
            sfiomn.legendarysurvivaloverhaul.common.capabilities.temperature.TemperatureCapability tempCapability = CapabilityUtil
                    .getTempCapability(player);
            if (tempCapability != null) {
                float temp = tempCapability.getTemperatureLevel();
                // Normal temperature in LSO usually hovers around 0. Let's assume > -5 is not
                // freezing.
                tempIsGood = temp > -5.0f;
            }

            int currentHealthyTicks = player.getPersistentData().getInt("PneumoniaCureTicks");

            if (foodIsGood && waterIsGood && tempIsGood) {
                currentHealthyTicks += 20; // Added 1 real second
                player.getPersistentData().putInt("PneumoniaCureTicks", currentHealthyTicks);

                if (currentHealthyTicks >= 3600) { // 3 minutes = 180 seconds * 20 = 3600 ticks
                    if (player.hasEffect(ModEffects.PNEUMONIA.get())) {
                        player.removeEffect(ModEffects.PNEUMONIA.get());
                    }
                    player.getPersistentData().remove("PneumoniaCureTicks"); // Reset
                }
            } else {
                // If conditions are not met, the progress resets!
                if (currentHealthyTicks > 0) {
                    player.getPersistentData().putInt("PneumoniaCureTicks", 0);
                }
            }
        }
    }
}

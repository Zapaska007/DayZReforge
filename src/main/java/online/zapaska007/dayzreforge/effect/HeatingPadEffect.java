package online.zapaska007.dayzreforge.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import sfiomn.legendarysurvivaloverhaul.util.CapabilityUtil;

public class HeatingPadEffect extends MobEffect {
    public HeatingPadEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return duration % 20 == 0;
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        if (!entity.level().isClientSide() && entity instanceof Player player) {
            sfiomn.legendarysurvivaloverhaul.common.capabilities.temperature.TemperatureCapability tempCapability = CapabilityUtil
                    .getTempCapability(player);
            if (tempCapability != null) {
                float currentTemp = tempCapability.getTemperatureLevel();
                if (currentTemp < 15.0f) { // Actively warm the player
                    tempCapability.addTemperatureLevel(0.25f);
                }
            }
        }
    }
}

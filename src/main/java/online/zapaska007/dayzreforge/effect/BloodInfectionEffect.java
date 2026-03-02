package online.zapaska007.dayzreforge.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;

public class BloodInfectionEffect extends MobEffect {
    public BloodInfectionEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        // Tick every 20 ticks (1 second) to check duration and apply slowness if needed
        return duration % 20 == 0 || duration <= 2;
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        if (!entity.level().isClientSide() && entity instanceof Player player) {
            MobEffectInstance effectInstance = player.getEffect(this);
            if (effectInstance != null) {
                int duration = effectInstance.getDuration();

                // If duration is at the very end (1 tick left), kill the player
                if (duration <= 2) {
                    player.kill();
                    return;
                }

                // If less than 2 minutes remain (2400 ticks), apply Slowness 4 and Hunger 2
                if (duration <= 2400) {
                    player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 60, 3, false, false, true));
                    player.addEffect(new MobEffectInstance(MobEffects.HUNGER, 60, 1, false, false, true));
                }
                // If less than 5 minutes remain (6000 ticks), apply Slowness 2 and Hunger 1
                else if (duration <= 6000) {
                    player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 60, 1, false, false, true));
                    player.addEffect(new MobEffectInstance(MobEffects.HUNGER, 60, 0, false, false, true));
                }
                // If less than 10 minutes remain (12000 ticks), apply Hunger 1
                else if (duration <= 12000) {
                    player.addEffect(new MobEffectInstance(MobEffects.HUNGER, 60, 0, false, false, true));
                }
            }
        }
    }
}

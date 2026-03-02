package online.zapaska007.dayzreforge.effect;

import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import online.zapaska007.dayzreforge.registry.ModSounds;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.core.registries.Registries;
import online.zapaska007.dayzreforge.registry.ModDamageTypes;

public class KuruEffect extends MobEffect {
    public KuruEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        // Tick every 20 ticks (1 second) or on the exact boundary to kill
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
                    player.hurt(new DamageSource(player.level().registryAccess().registryOrThrow(Registries.DAMAGE_TYPE)
                            .getHolderOrThrow(ModDamageTypes.KURU)), Float.MAX_VALUE);
                    return;
                }

                // If less than 8 minutes remain (9600 ticks), there's a chance to play the
                // spooky laugh
                if (duration <= 9600) {
                    // ~10% chance every second to laugh
                    if (player.getRandom().nextFloat() < 0.1f) {
                        player.level().playSound(null, player.blockPosition(), ModSounds.KURU_LAUGH.get(),
                                SoundSource.PLAYERS, 1.0f, player.getRandom().nextFloat() * 0.4f + 0.8f);
                    }
                }

                // Apply Nausea intermittently depending on severity
                if (duration <= 6000) { // 5 minutes left
                    if (player.getRandom().nextFloat() < 0.3f) { // 30% chance every second to get a burst of nausea
                        player.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 100, 0, false, false, true));
                    }
                }
            }
        }
    }
}

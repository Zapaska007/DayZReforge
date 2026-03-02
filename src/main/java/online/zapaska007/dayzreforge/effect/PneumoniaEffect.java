package online.zapaska007.dayzreforge.effect;

import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import online.zapaska007.dayzreforge.registry.ModDamageTypes;

public class PneumoniaEffect extends MobEffect {
    public PneumoniaEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return true;
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        if (!entity.level().isClientSide() && entity instanceof Player player) {
            MobEffectInstance effectInstance = player.getEffect(this);
            if (effectInstance != null) {
                int duration = effectInstance.getDuration();

                if (duration <= 2) {
                    DamageSource pneumoniaSource = new DamageSource(player.level().registryAccess()
                            .registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(ModDamageTypes.PNEUMONIA));
                    player.hurt(pneumoniaSource, Float.MAX_VALUE);
                }

                // Randomly apply symptoms
                if (player.getRandom().nextFloat() < 0.05f && player.tickCount % 40 == 0) {
                    player.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 60, 0, false, false, false));
                    player.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 100, 0, false, false, false));
                    player.level().playSound(null, player.blockPosition(), SoundEvents.PLAYER_HURT_FREEZE,
                            SoundSource.PLAYERS, 1.0F, 1.0F);
                }
            }
        }
    }
}

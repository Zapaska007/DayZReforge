package online.zapaska007.dayzreforge.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.damagesource.DamageSource;

public class BleedingEffect extends MobEffect {
    public BleedingEffect(MobEffectCategory pCategory, int pColor) {
        super(pCategory, pColor);
    }

    @Override
    public void applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {
        if (!pLivingEntity.level().isClientSide()) {
            // Amplifier 0 = Stage 1 (1 damage), Amplifier 1 = Stage 2 (2 damage), Amplifier
            // 2 = Stage 3 (3 damage)
            float damageAmount = 1.0F + pAmplifier;

            // Deal generic damage that bypasses some armors to simulate internal bleeding
            pLivingEntity.hurt(pLivingEntity.damageSources().generic(), damageAmount);
        }
    }

    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier) {
        // Tick every 100 ticks (5 seconds) for Stage 1, 80 ticks (4 seconds) for Stage
        // 2, 60 ticks (3 seconds) for Stage 3
        int tickRate = 100 - (pAmplifier * 20);
        if (tickRate <= 0)
            tickRate = 20; // fallback safety

        int j = pDuration % tickRate;
        return j == 0;
    }
}

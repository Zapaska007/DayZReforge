package online.zapaska007.dayzreforge.item;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.tags.FluidTags;

public class CanteenItem extends Item {
    private final int maxSips;
    private final boolean autoPurify;

    public CanteenItem(Properties properties, int maxSips, boolean autoPurify) {
        super(properties);
        this.maxSips = maxSips;
        this.autoPurify = autoPurify;
    }

    public int getMaxSips() {
        return this.maxSips;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);

        BlockHitResult hitresult = getPlayerPOVHitResult(level, player, ClipContext.Fluid.SOURCE_ONLY);
        if (hitresult.getType() == HitResult.Type.MISS || hitresult.getType() != HitResult.Type.BLOCK) {
            // Если не смотрим на блок воды, пробуем попить
            int currentCapacity = 0;
            if (itemstack.hasTag() && itemstack.getTag().contains("legendarysurvivaloverhaul:HydrationCapacity")) {
                currentCapacity = itemstack.getTag().getInt("legendarysurvivaloverhaul:HydrationCapacity");
            }
            if (currentCapacity > 0) {
                player.startUsingItem(hand);
                return InteractionResultHolder.consume(itemstack);
            }
            return InteractionResultHolder.pass(itemstack);
        } else {
            BlockPos blockpos = hitresult.getBlockPos();
            if (level.mayInteract(player, blockpos) && level.getFluidState(blockpos).is(FluidTags.WATER)) {
                int currentCapacity = 0;
                if (itemstack.hasTag() && itemstack.getTag().contains("legendarysurvivaloverhaul:HydrationCapacity")) {
                    currentCapacity = itemstack.getTag().getInt("legendarysurvivaloverhaul:HydrationCapacity");
                }

                if (currentCapacity < maxSips) {
                    currentCapacity++;
                    itemstack.getOrCreateTag().putInt("legendarysurvivaloverhaul:HydrationCapacity", currentCapacity);

                    // Любое смешивание с обычной/грязной водой дает грязную воду (даже если там
                    // была чистая)
                    // ИСКЛЮЧЕНИЕ: если это autoPurify (фильтр), вода сразу становится очищенной
                    // (без задержек анимации)
                    if (this.autoPurify) {
                        itemstack.getTag().putString("legendarysurvivaloverhaul:HydrationPurity", "purified");
                    } else {
                        itemstack.getTag().putString("legendarysurvivaloverhaul:HydrationPurity", "normal");
                    }

                    level.playSound(player, player.getX(), player.getY(), player.getZ(), SoundEvents.BOTTLE_FILL,
                            SoundSource.NEUTRAL, 1.0F, 1.0F);
                    return InteractionResultHolder.sidedSuccess(itemstack, level.isClientSide());
                }
            }

            return InteractionResultHolder.pass(itemstack);
        }
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return 32;
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.DRINK;
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, net.minecraft.world.entity.LivingEntity entity) {
        if (entity instanceof Player) {
            if (stack.hasTag() && stack.getTag().contains("legendarysurvivaloverhaul:HydrationCapacity")) {
                int capacity = stack.getTag().getInt("legendarysurvivaloverhaul:HydrationCapacity");
                if (capacity > 0) {
                    capacity--;
                    stack.getTag().putInt("legendarysurvivaloverhaul:HydrationCapacity", capacity);
                    if (capacity == 0) {
                        stack.getTag().remove("legendarysurvivaloverhaul:HydrationCapacity");
                        stack.getTag().remove("legendarysurvivaloverhaul:HydrationPurity");
                    }
                }
            }
        }
        return super.finishUsingItem(stack, level, entity);
    }

    @Override
    public boolean isBarVisible(ItemStack stack) {
        return stack.hasTag() && stack.getTag().contains("legendarysurvivaloverhaul:HydrationCapacity");
    }

    @Override
    public int getBarWidth(ItemStack stack) {
        if (stack.hasTag() && stack.getTag().contains("legendarysurvivaloverhaul:HydrationCapacity")) {
            int capacity = stack.getTag().getInt("legendarysurvivaloverhaul:HydrationCapacity");
            return Math.round(13.0F * capacity / maxSips); // 13 is max bar width
        }
        return 0;
    }

    @Override
    public int getBarColor(ItemStack stack) {
        if (stack.hasTag() && stack.getTag().contains("legendarysurvivaloverhaul:HydrationPurity")) {
            String purity = stack.getTag().getString("legendarysurvivaloverhaul:HydrationPurity");
            if ("purified".equals(purity)) {
                return 0x00FFFF; // Голубой (очищенная)
            }
        }
        return 0x0055FF; // Синий (обычная/грязная)
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slotId, boolean isSelected) {
        // Логика фильтрации: если это бутылка с фильтром, она автоматически делает воду
        // purified
        if (!level.isClientSide && autoPurify && stack.hasTag()
                && stack.getTag().contains("legendarysurvivaloverhaul:HydrationPurity")) {
            String purity = stack.getTag().getString("legendarysurvivaloverhaul:HydrationPurity");
            if (!"purified".equals(purity)) {
                stack.getTag().putString("legendarysurvivaloverhaul:HydrationPurity", "purified");
            }
        }
        super.inventoryTick(stack, level, entity, slotId, isSelected);
    }

}

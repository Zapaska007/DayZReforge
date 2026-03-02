package online.zapaska007.dayzreforge.block;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import online.zapaska007.dayzreforge.item.CanteenItem;

public class WaterPumpBlock extends Block {

    public WaterPumpBlock(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand,
            BlockHitResult hit) {
        if (!level.isClientSide) {
            ItemStack stack = player.getItemInHand(hand);

            if (stack.getItem() instanceof CanteenItem canteenItem) {
                int maxSips = canteenItem.getMaxSips();
                int currentCapacity = 0;

                if (stack.hasTag() && stack.getTag().contains("legendarysurvivaloverhaul:HydrationCapacity")) {
                    currentCapacity = stack.getTag().getInt("legendarysurvivaloverhaul:HydrationCapacity");
                }

                if (currentCapacity < maxSips) {
                    currentCapacity++;
                    stack.getOrCreateTag().putInt("legendarysurvivaloverhaul:HydrationCapacity", currentCapacity);
                    // Если контейнер пустой, вода будет чистой. Но если там уже есть обычная вода
                    // ("normal"),
                    // то смешивание с чистой водой не очистит её (останется "normal")
                    if (!stack.getTag().contains("legendarysurvivaloverhaul:HydrationPurity")) {
                        stack.getTag().putString("legendarysurvivaloverhaul:HydrationPurity", "purified");
                    }

                    level.playSound(null, pos, SoundEvents.BOTTLE_FILL, SoundSource.BLOCKS, 1.0F, 1.0F);
                    return InteractionResult.SUCCESS;
                }
            } else if (stack.isEmpty()) {
                // Если рука пустая, игрок пьёт напрямую — LSO перехватит этот звук или мы
                // просто дадим отыгрыш РП
                level.playSound(null, pos, SoundEvents.GENERIC_DRINK, SoundSource.BLOCKS, 1.0F, 1.0F);
                return InteractionResult.SUCCESS;
            }
        }
        return InteractionResult.sidedSuccess(level.isClientSide);
    }
}

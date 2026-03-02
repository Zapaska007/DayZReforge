package online.zapaska007.dayzreforge.item;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import online.zapaska007.dayzreforge.registry.ModEffects;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class HeatingPadItem extends Item {
    public HeatingPadItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);

        if (!level.isClientSide()) {
            boolean active = itemstack.hasTag() && itemstack.getTag().getBoolean("Active");
            boolean used = itemstack.hasTag() && itemstack.getTag().getBoolean("Used");

            if (!active && !used) {
                // Activate the heating pad
                itemstack.getOrCreateTag().putBoolean("Active", true);
                itemstack.getTag().putLong("ActivationTime", level.getGameTime());
                return InteractionResultHolder.success(itemstack);
            }
        }

        return InteractionResultHolder.pass(itemstack);
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slotId, boolean isSelected) {
        if (!level.isClientSide() && entity instanceof Player player) {
            if (stack.hasTag() && stack.getTag().getBoolean("Active")) {
                long activationTime = stack.getTag().getLong("ActivationTime");
                long currentTime = level.getGameTime();

                // 24000 ticks = 20 real-time minutes
                if (currentTime - activationTime >= 24000) {
                    stack.getTag().remove("Active");
                    stack.getTag().putBoolean("Used", true);
                } else {
                    // Apply Heating Pad effect continuously while it's in the inventory and active
                    player.addEffect(new MobEffectInstance(ModEffects.HEATING_PAD.get(), 40, 0, false, false, true));
                }
            }
        }
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltipComponents,
            TooltipFlag isAdvanced) {
        if (stack.hasTag()) {
            if (stack.getTag().getBoolean("Used")) {
                tooltipComponents
                        .add(Component.translatable("tooltip.dayzreforge.used").withStyle(ChatFormatting.DARK_GRAY));
            } else if (stack.getTag().getBoolean("Active")) {
                tooltipComponents
                        .add(Component.translatable("tooltip.dayzreforge.activated").withStyle(ChatFormatting.RED));
            }
        }
        super.appendHoverText(stack, level, tooltipComponents, isAdvanced);
    }
}

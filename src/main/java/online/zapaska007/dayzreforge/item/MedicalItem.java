package online.zapaska007.dayzreforge.item;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.network.chat.Component;
import net.minecraft.ChatFormatting;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import java.util.List;
import sfiomn.legendarysurvivaloverhaul.common.items.heal.BandageItem;

public class MedicalItem extends BandageItem {

    public MedicalItem(Properties properties) {
        super(properties);
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.BOW;
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltipComponents,
            TooltipFlag isAdvanced) {
        super.appendHoverText(stack, level, tooltipComponents, isAdvanced);

        if (stack.hasTag() && stack.getTag().getBoolean("Sterilized")) {
            tooltipComponents
                    .add(Component.translatable("tooltip.dayzreforge.sterilized").withStyle(ChatFormatting.AQUA));
        }
    }
}

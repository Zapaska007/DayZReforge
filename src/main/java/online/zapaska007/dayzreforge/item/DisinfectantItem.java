package online.zapaska007.dayzreforge.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class DisinfectantItem extends Item {

    public DisinfectantItem(Properties properties) {
        super(properties.defaultDurability(3).setNoRepair());
    }

    @Override
    public boolean hasCraftingRemainingItem(ItemStack stack) {
        return true;
    }

    @Override
    public ItemStack getCraftingRemainingItem(ItemStack itemStack) {
        ItemStack copy = itemStack.copy();
        copy.setDamageValue(copy.getDamageValue() + 1);

        // If it reaches max damage, it breaks (returns empty)
        if (copy.getDamageValue() >= copy.getMaxDamage()) {
            return ItemStack.EMPTY;
        }

        return copy;
    }
}

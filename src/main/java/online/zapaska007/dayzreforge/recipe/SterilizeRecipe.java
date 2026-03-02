package online.zapaska007.dayzreforge.recipe;

import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;
import online.zapaska007.dayzreforge.item.DisinfectantItem;
import online.zapaska007.dayzreforge.item.MedicalItem;
import online.zapaska007.dayzreforge.registry.ModItems;
import online.zapaska007.dayzreforge.registry.ModRecipes;

public class SterilizeRecipe extends CustomRecipe {
    public SterilizeRecipe(ResourceLocation id, CraftingBookCategory category) {
        super(id, category);
    }

    @Override
    public boolean matches(CraftingContainer container, Level level) {
        boolean hasMedicalItem = false;
        boolean hasDisinfectant = false;

        for (int i = 0; i < container.getContainerSize(); i++) {
            ItemStack stack = container.getItem(i);
            if (!stack.isEmpty()) {
                if (stack.getItem() instanceof MedicalItem) {
                    // Only Rags and Sewing Kits can be sterilized
                    if (stack.getItem() == ModItems.MEDICAL_RAGS.get()
                            || stack.getItem() == ModItems.MEDICAL_SEWING_KIT.get()) {
                        // Let's not sterilize if already sterilized
                        if (stack.hasTag() && stack.getTag().getBoolean("Sterilized"))
                            return false;

                        if (hasMedicalItem)
                            return false;
                        hasMedicalItem = true;
                    } else {
                        return false;
                    }
                } else if (stack.getItem() instanceof DisinfectantItem) {
                    if (hasDisinfectant)
                        return false;
                    hasDisinfectant = true;
                } else {
                    return false;
                }
            }
        }
        return hasMedicalItem && hasDisinfectant;
    }

    @Override
    public ItemStack assemble(CraftingContainer container, RegistryAccess registryAccess) {
        ItemStack medicalItemStack = ItemStack.EMPTY;

        for (int i = 0; i < container.getContainerSize(); i++) {
            ItemStack stack = container.getItem(i);
            if (!stack.isEmpty() && stack.getItem() instanceof MedicalItem) {
                medicalItemStack = stack;
                break;
            }
        }

        if (medicalItemStack.isEmpty()) {
            return ItemStack.EMPTY;
        }

        // Copy the item but limit output size to 1 if we are consuming 1 stack at a
        // time
        ItemStack result = medicalItemStack.copy();
        result.setCount(1);
        result.getOrCreateTag().putBoolean("Sterilized", true);
        return result;
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return width * height >= 2;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipes.STERILIZE_SERIALIZER.get();
    }
}

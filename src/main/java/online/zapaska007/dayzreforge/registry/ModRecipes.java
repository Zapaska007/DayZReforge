package online.zapaska007.dayzreforge.registry;

import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SimpleCraftingRecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import online.zapaska007.dayzreforge.DayzReforgeMod;
import online.zapaska007.dayzreforge.recipe.SterilizeRecipe;

public class ModRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS = DeferredRegister
            .create(ForgeRegistries.RECIPE_SERIALIZERS, DayzReforgeMod.MOD_ID);

    public static final RegistryObject<RecipeSerializer<SterilizeRecipe>> STERILIZE_SERIALIZER = SERIALIZERS
            .register("sterilize", () -> new SimpleCraftingRecipeSerializer<>(SterilizeRecipe::new));

    public static void register(IEventBus eventBus) {
        SERIALIZERS.register(eventBus);
    }
}

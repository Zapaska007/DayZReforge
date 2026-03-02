package online.zapaska007.dayzreforge.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.NetworkEvent;
import online.zapaska007.dayzreforge.DayzReforgeMod;
import online.zapaska007.dayzreforge.registry.ModDamageTypes;

import java.util.function.Supplier;

public class SuicidePacket {

    public static final TagKey<Item> SUICIDE_WEAPONS = ItemTags
            .create(ResourceLocation.fromNamespaceAndPath(DayzReforgeMod.MOD_ID, "suicide_weapons"));

    public SuicidePacket() {
    }

    public SuicidePacket(FriendlyByteBuf buf) {
    }

    public void toBytes(FriendlyByteBuf buf) {
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            ServerPlayer player = context.getSender();
            if (player != null) {
                ItemStack mainHandItem = player.getItemInHand(InteractionHand.MAIN_HAND);

                // Check if the item is a valid suicide weapon using the item tag
                if (mainHandItem.is(SUICIDE_WEAPONS)) {
                    DamageSource suicideSource = new DamageSource(player.level().registryAccess()
                            .registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(ModDamageTypes.SUICIDE));
                    player.hurt(suicideSource, Float.MAX_VALUE);
                }
            }
        });
        return true;
    }
}

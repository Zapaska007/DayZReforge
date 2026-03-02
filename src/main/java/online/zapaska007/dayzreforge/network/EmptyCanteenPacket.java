package online.zapaska007.dayzreforge.network;

import net.minecraftforge.network.NetworkEvent;
import online.zapaska007.dayzreforge.item.CanteenItem;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.item.ItemStack;

import java.util.function.Supplier;

public class EmptyCanteenPacket {

    public EmptyCanteenPacket() {
    }

    // Constructor for decoding
    public EmptyCanteenPacket(net.minecraft.network.FriendlyByteBuf buf) {
    }

    public void toBytes(net.minecraft.network.FriendlyByteBuf buf) {
    }

    public void handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            // This runs on the server side
            ServerPlayer player = context.getSender();
            if (player != null) {
                // Check main hand
                ItemStack stack = player.getMainHandItem();
                if (stack.getItem() instanceof CanteenItem) {
                    emptyCanteen(stack, player);
                } else {
                    // Check offhand
                    stack = player.getOffhandItem();
                    if (stack.getItem() instanceof CanteenItem) {
                        emptyCanteen(stack, player);
                    }
                }
            }
        });
        context.setPacketHandled(true);
    }

    private void emptyCanteen(ItemStack stack, ServerPlayer player) {
        if (stack.hasTag() && stack.getTag().contains("legendarysurvivaloverhaul:HydrationCapacity")) {
            int capacity = stack.getTag().getInt("legendarysurvivaloverhaul:HydrationCapacity");
            if (capacity > 0) {
                // Empty one sip per press
                capacity--;
                if (capacity > 0) {
                    stack.getTag().putInt("legendarysurvivaloverhaul:HydrationCapacity", capacity);
                } else {
                    stack.getTag().remove("legendarysurvivaloverhaul:HydrationCapacity");
                    stack.getTag().remove("legendarysurvivaloverhaul:HydrationPurity");
                }

                // Play splash sound
                player.level().playSound(null, player.blockPosition(), SoundEvents.BUCKET_EMPTY, SoundSource.PLAYERS,
                        1.0F, 1.0F);
            }
        }
    }
}

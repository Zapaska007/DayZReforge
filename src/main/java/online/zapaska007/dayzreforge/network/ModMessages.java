package online.zapaska007.dayzreforge.network;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;
import online.zapaska007.dayzreforge.DayzReforgeMod;

public class ModMessages {
    private static SimpleChannel INSTANCE;

    private static int packetId = 0;

    private static int id() {
        return packetId++;
    }

    public static void register() {
        SimpleChannel net = NetworkRegistry.ChannelBuilder
                .named(ResourceLocation.fromNamespaceAndPath(DayzReforgeMod.MOD_ID, "messages"))
                .networkProtocolVersion(() -> "1.0")
                .clientAcceptedVersions(s -> true)
                .serverAcceptedVersions(s -> true)
                .simpleChannel();

        INSTANCE = net;

        net.messageBuilder(EmptyCanteenPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(EmptyCanteenPacket::new)
                .encoder(EmptyCanteenPacket::toBytes)
                .consumerMainThread(EmptyCanteenPacket::handle)
                .add();

        net.messageBuilder(SuicidePacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(SuicidePacket::new)
                .encoder(SuicidePacket::toBytes)
                .consumerMainThread(SuicidePacket::handle)
                .add();
    }

    public static <MSG> void sendToServer(MSG message) {
        INSTANCE.sendToServer(message);
    }

    public static <MSG> void sendToPlayer(MSG message, ServerPlayer player) {
        INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), message);
    }
}

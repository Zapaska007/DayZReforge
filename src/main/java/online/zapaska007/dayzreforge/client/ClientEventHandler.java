package online.zapaska007.dayzreforge.client;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.MovementInputUpdateEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import online.zapaska007.dayzreforge.DayzReforgeMod;
import online.zapaska007.dayzreforge.network.EmptyCanteenPacket;
import online.zapaska007.dayzreforge.network.SuicidePacket;
import online.zapaska007.dayzreforge.network.ModMessages;
import online.zapaska007.dayzreforge.registry.ModEffects;

@Mod.EventBusSubscriber(modid = DayzReforgeMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ClientEventHandler {

    @SubscribeEvent
    public static void onKeyInput(InputEvent.Key event) {
        if (ModKeybinds.EMPTY_CANTEEN_KEY.consumeClick()) {
            ModMessages.sendToServer(new EmptyCanteenPacket());
        }
        if (ModKeybinds.SUICIDE_KEY.consumeClick()) {
            ModMessages.sendToServer(new SuicidePacket());
        }
    }

    @SubscribeEvent
    public static void onMouseInput(InputEvent.MouseButton.Pre event) {
        if (ModKeybinds.EMPTY_CANTEEN_KEY.consumeClick()) {
            ModMessages.sendToServer(new EmptyCanteenPacket());
        }
        if (ModKeybinds.SUICIDE_KEY.consumeClick()) {
            ModMessages.sendToServer(new SuicidePacket());
        }
    }

    @SubscribeEvent
    public static void onMovementInputUpdate(MovementInputUpdateEvent event) {
        LocalPlayer player = Minecraft.getInstance().player;
        if (player != null && player.hasEffect(ModEffects.KURU.get())) {
            // Invert the controls due to Kuru disease
            event.getInput().forwardImpulse *= -1.0f;
            event.getInput().leftImpulse *= -1.0f;
        }
    }
}

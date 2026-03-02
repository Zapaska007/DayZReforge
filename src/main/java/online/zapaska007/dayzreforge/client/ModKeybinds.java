package online.zapaska007.dayzreforge.client;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import online.zapaska007.dayzreforge.DayzReforgeMod;
import org.lwjgl.glfw.GLFW;

@Mod.EventBusSubscriber(modid = DayzReforgeMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModKeybinds {

    public static final String KEY_CATEGORY_DAYZREFORGE = "key.category.dayzreforge";
    public static final String KEY_EMPTY_CANTEEN = "key.dayzreforge.empty_canteen";
    public static final String KEY_SUICIDE = "key.dayzreforge.suicide";

    public static final KeyMapping EMPTY_CANTEEN_KEY = new KeyMapping(
            KEY_EMPTY_CANTEEN,
            KeyConflictContext.IN_GAME,
            InputConstants.Type.MOUSE,
            GLFW.GLFW_MOUSE_BUTTON_MIDDLE,
            KEY_CATEGORY_DAYZREFORGE);

    public static final KeyMapping SUICIDE_KEY = new KeyMapping(
            KEY_SUICIDE,
            KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_F11,
            KEY_CATEGORY_DAYZREFORGE);

    @SubscribeEvent
    public static void registerKeyBindings(RegisterKeyMappingsEvent event) {
        event.register(EMPTY_CANTEEN_KEY);
        event.register(SUICIDE_KEY);
    }
}

package com.skaggsm.startthemusic;

import com.skaggsm.startthemusic.mixin.MusicTrackerMixin;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.sound.MusicTracker;
import org.lwjgl.glfw.GLFW;

public class FabricStartTheMusicClient implements ClientModInitializer {
    public static final String MOD_ID = "fabric-start-the-music";
    private KeyBinding resetMusicKeyBinding;

    @Override
    public void onInitializeClient() {
        resetMusicKeyBinding = new KeyBinding(
                "key.%s.reset_music_key_binding".formatted(MOD_ID),
                GLFW.GLFW_KEY_G,
                "key.categories.misc"
        );

        KeyBindingHelper.registerKeyBinding(resetMusicKeyBinding);

        ClientTickEvents.START_CLIENT_TICK.register(client -> {
            if (resetMusicKeyBinding.isPressed()) {
                MusicTracker musicTracker = client.getMusicTracker();
                MusicTrackerMixin musicTrackerMixin = (MusicTrackerMixin) musicTracker;

                if (client.options.sneakKey.isPressed()) {
                    client.getMusicTracker().stop();
                    musicTrackerMixin.setTimeUntilNextSong(Integer.MAX_VALUE);
                } else {
                    musicTrackerMixin.setTimeUntilNextSong(0);
                }
            }
        });
    }
}

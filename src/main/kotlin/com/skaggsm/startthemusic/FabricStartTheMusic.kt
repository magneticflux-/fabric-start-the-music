package com.skaggsm.startthemusic

import com.skaggsm.startthemusic.FabricStartTheMusic.MOD_ID
import com.skaggsm.startthemusic.mixin.MusicTrackerMixin
import net.fabricmc.api.ClientModInitializer
import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper
import net.minecraft.client.option.KeyBinding
import org.lwjgl.glfw.GLFW

/**
 * Created by Mitchell Skaggs on 8/14/2019.
 */
object FabricStartTheMusic : ModInitializer {
    const val MOD_ID = "fabric-start-the-music"
    override fun onInitialize() {
    }
}

object FabricStartTheMusicClient : ClientModInitializer {
    lateinit var resetMusicKeyBinding: KeyBinding

    override fun onInitializeClient() {
        resetMusicKeyBinding = KeyBinding(
            "key.$MOD_ID.reset_music_key_binding",
            GLFW.GLFW_KEY_G,
            "key.categories.misc"
        )

        KeyBindingHelper.registerKeyBinding(resetMusicKeyBinding)

        ClientTickEvents.START_CLIENT_TICK.register(ClientTickEvents.StartTick {
            if (resetMusicKeyBinding.isPressed)
                if (it.options.keySneak.isPressed) {
                    it.musicTracker.stop()
                    (it.musicTracker as MusicTrackerMixin).timeUntilNextSong = Int.MAX_VALUE
                } else {
                    (it.musicTracker as MusicTrackerMixin).timeUntilNextSong = 0
                }
        })
    }
}

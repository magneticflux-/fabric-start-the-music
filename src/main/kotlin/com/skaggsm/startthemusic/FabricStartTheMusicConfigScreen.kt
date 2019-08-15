package com.skaggsm.startthemusic

import io.github.prospector.modmenu.api.ModMenuApi
import me.sargunvohra.mcmods.autoconfig1.AutoConfig
import net.minecraft.client.gui.screen.Screen
import java.util.function.Function

/**
 * Created by Mitchell Skaggs on 8/14/2019.
 */
class FabricStartTheMusicConfigScreen : ModMenuApi {
    override fun getModId(): String {
        return FabricStartTheMusic.MOD_ID
    }

    override fun getConfigScreenFactory(): Function<Screen, out Screen> {
        return Function<Screen, Screen> { screen -> AutoConfig.getConfigScreen(FabricStartTheMusicConfig::class.java, screen).get() }
    }
}

package com.skaggsm.startthemusic.mixin;

import net.minecraft.client.sound.MusicTracker;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

/**
 * Created by Mitchell Skaggs on 8/14/2019.
 */
@Mixin(MusicTracker.class)
public interface MusicTrackerMixin {
    @Accessor("timeUntilNextSong")
    int getTimeUntilNextSong();

    @Accessor("timeUntilNextSong")
    void setTimeUntilNextSong(int i);
}

package com.apakhun.arabicverbstestssecond.controllers;

import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;

import com.apakhun.arabicverbstestssecond.App;

import java.io.IOException;

public class Sounder {
    private static MediaPlayer player = new MediaPlayer();

    public synchronized static void sound(String filepath) {
        sound(filepath, null);
    }

    public synchronized static void sound(String filepath, MediaPlayer.OnCompletionListener onComplete) {
        if (player.isPlaying()) {
            player.stop();
        }

        player.release();
        player = new MediaPlayer();

        player.setOnCompletionListener(onComplete);

        AssetFileDescriptor afd = null;
        try {
            afd = App.getAppContext().getAssets().openFd(filepath);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            player.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            player.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        player.start();
    }
}

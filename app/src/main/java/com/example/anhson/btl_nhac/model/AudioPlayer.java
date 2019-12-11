package com.example.anhson.btl_nhac.model;

/**
 * Created by son on 14/9/2016.
 */
public class AudioPlayer {
    private String path;
    private String name;
    private String tenCS;
    private String album;
    private int duration;

    public AudioPlayer(String path, String name, String tenCS, String album, int duration) {
        this.path = path;
        this.name = name;
        this.tenCS = tenCS;
        this.album = album;
        this.duration = duration;
    }

    public String getPath() {
        return path;
    }

    public String getName() {
        return name;
    }

    public String getTenCS() {
        return tenCS;
    }

    public String getAlbum() {
        return album;
    }

    public int getDuration() {
        return duration;
    }
}

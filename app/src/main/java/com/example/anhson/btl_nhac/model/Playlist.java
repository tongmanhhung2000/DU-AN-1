package com.example.anhson.btl_nhac.model;

import java.util.List;

/**
 * Created by Anh Son on 4/13/2017.
 */

public class Playlist {
    private int id;
    private String name;
    private List<AudioPlayer> audioPlayers;

    public Playlist(int id, String name, List<AudioPlayer> audioPlayers) {
        this.id = id;
        this.name = name;
        this.audioPlayers = audioPlayers;
    }

    public Playlist( int id,String name) {
        this.name = name;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<AudioPlayer> getAudioPlayers() {
        return audioPlayers;
    }

    public void setAudioPlayers(List<AudioPlayer> audioPlayers) {
        this.audioPlayers = audioPlayers;
    }
}

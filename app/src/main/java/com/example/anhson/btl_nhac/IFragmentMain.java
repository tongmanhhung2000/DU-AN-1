package com.example.anhson.btl_nhac;

import com.example.anhson.btl_nhac.model.Album;
import com.example.anhson.btl_nhac.model.AudioPlayer;
import com.example.anhson.btl_nhac.model.Playlist;
import com.example.anhson.btl_nhac.model.Singer;

import java.util.List;

/**
 * Created by Anh Son on 4/7/2017.
 */

public interface IFragmentMain {
    void showNgoaiTuyenFragment();

    void showMucPhatFragment(Singer singer);

    void showMucPhatFragment(Album album);

    void setPlaying(List<AudioPlayer> audioList, int i);

    void showListYeuThich(Playlist listYeuThich);

    void addIntoYeuThich(AudioPlayer audioPlayer);

    void sendSing(AudioPlayer audioPlayer);

    void addIntoPlayList(AudioPlayer audioPlayer);
}

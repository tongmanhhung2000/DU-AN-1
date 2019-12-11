package com.example.anhson.btl_nhac;

import com.example.anhson.btl_nhac.model.AudioPlayer;

/**
 * Created by Anh Son on 4/13/2017.
 */

public interface IDialogOfFragment {
    void xuly(String name);

    void addIntoYeuThich(AudioPlayer audioPlayer);

    void addIntoPlayList(AudioPlayer audioPlayer);

    void sendSing(AudioPlayer audioPlayer);
}

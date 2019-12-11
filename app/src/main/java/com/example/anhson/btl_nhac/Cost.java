package com.example.anhson.btl_nhac;

import android.os.Environment;

import java.io.File;

/**
 * Created by Anh Son on 4/11/2017.
 */

public class Cost {
    public final static String MUCPHATALBUM="MUCPHATALBUM";
    public final static String MUCPHATARTIST="MUCPHATARTIST";
    public final static String DANHSACHPHATFRAGMENT="DANHSACHPHATFRAGMENT";
    public static final String NGOAITUYENFRAGMENT = "NGOAITUYENFRAGMENT";
    public static final String MUCPHATFRAGMENT = "MUCPHATFRAGMENT";

    public static final int IMPAUSE=R.drawable.pause_button;
    public static final int IMPLAY=R.drawable.music_player_play;
    public static final int IMNEXT=R.drawable.play_next;

    public static final String PLAYSTATUS="PLAYSTATUS";
    public static final String PAUSESTATUS="PAUSESTATUS";
    public static final String DATABASE="qlPlaylist";
    public static final String PATH_DATA= Environment.getDataDirectory() +
            File.separator + "data" + File.separator +
            "com.example.anhson.btl_nhac" + File.separator
            + "database";

    public static final String MUCPHATPLAYLIST ="MUCPHATPLAYLIST" ;
    public static final String MY_APP = "myApp";

    public static final String PLAY = "PLAY";
    public static final String PAUSE = "PAUSE";
    public static final String SING = "SING";
    public static final String STATUSOFMESIA="STATUSOFMESIA";

    public static final String REPEAT = "REPEAT";
    public static final String SHUFFLE = "SHUFFLE";
    public static final String NOREPEAT = "NOREPEAT";

    public static final String GREEN="#00965A";
    public static final String PRIMARY="#e6e5db";
}

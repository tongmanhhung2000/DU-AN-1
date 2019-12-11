package com.example.anhson.btl_nhac.model;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by son on 14/9/2016.
 */
public class ManagerMedia {
    private static final String TAG = ManagerMedia.class.getSimpleName();
    private Context mContext;

    public ManagerMedia(Context mContext) {
        //la lop dung de lay thong tin bai hat , ca si , album
        this.mContext = mContext;
    }

    public List<Album> getListAlbum(){
        List<Album> albumList=new ArrayList<>();
        List<AudioPlayer> audioPlayers=getListAudioPlayer();

        for (int i=0;i<audioPlayers.size();i++){
            String nameAlbum=audioPlayers.get(i).getAlbum();
            String nameArtist=audioPlayers.get(i).getTenCS();
            int index=0;
            for (int j=0;j<albumList.size();j++){
                if (nameAlbum.equals(albumList.get(j).getName())){
                    index=1;
                }
            }
            if (index==0){
                Album album=new Album(nameAlbum,nameArtist);
                albumList.add(album);
            }
        }
        return albumList;
    }



    public List<Singer> getListSinger(){
        List<Singer> singers=new ArrayList<>();
        List<AudioPlayer> audioPlayers=getListAudioPlayer();

        for (int i=0;i<audioPlayers.size();i++){
            String nameSinger=audioPlayers.get(i).getTenCS();
            Log.d(TAG,"name singer:"+nameSinger);
            int index=0;
            for (int j=0;j<singers.size();j++){
                if (nameSinger.equals(singers.get(j).getName())){
                    singers.get(j).addNumberOfSing();
                    index=1;
                }
            }
            if (index==0){
                Singer singer=new Singer(nameSinger,1);
                Log.d(TAG,"aaaaaaaaaaa singer:"+singer.getName());
                singers.add(singer);
            }
        }
        return singers;
    }


    public List<AudioPlayer> getListAudioPlayer(){

        List<AudioPlayer> players = new ArrayList<>();
        // get con tro file(Uri) den bang audio
        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        String[] projects = new String[]{
                MediaStore.Audio.Media.DATA,
                MediaStore.Audio.Media.TITLE,
                MediaStore.Audio.Media.DURATION,
                MediaStore.Audio.Media.ALBUM,
                MediaStore.Audio.Media.ARTIST,

        };
        //lay ra ra contro cursor tro den tung hang
        Cursor c = mContext.getContentResolver().query(uri,
                projects, null, null, null);
        // lay ra ten tat ca cac cot trong bang
//        String[] columnNames = c.getColumnNames();
//        for (String columnName : columnNames) {
//            // in ra ten cot
//            Log.d(TAG, "getAudioPlayers columnName: " +
//                    columnName);
//        }
        if ( c == null || c.getCount() == 0) {
            return players;
        }
        int indexData = c.getColumnIndex(MediaStore.Audio.Media.DATA);
        int indexName = c.getColumnIndex(MediaStore.Audio.Media.TITLE);
        int indexDuration = c.getColumnIndex(MediaStore.Audio.Media.DURATION);
        int indexAlbum = c.getColumnIndex(MediaStore.Audio.Media.ALBUM);
        int indexArtist = c.getColumnIndex(MediaStore.Audio.Media.ARTIST);
        c.moveToFirst();

        while ( !c.isAfterLast() ) {
            String data = c.getString(indexData);
            String name = c.getString(indexName);
            int duration = c.getInt(indexDuration);
            String album = c.getString(indexAlbum);
            String artist = c.getString(indexArtist);

            //them phan tu AudioPlayer vao list
            players.add(new AudioPlayer(data, name, artist,album, duration));

            //tro den hang tiep theo
            c.moveToNext();
        }

        return players;
    }

    public List<AudioPlayer> getListSing0fSinger(Singer singer) {
        //lay list bai hat tu ca si
        List<AudioPlayer> audioPlayers=new ArrayList<>();
        for (AudioPlayer audioPlayer:getListAudioPlayer()){
            if (audioPlayer.getTenCS().equals(singer.getName())){
                audioPlayers.add(audioPlayer);
            }
        }
        return audioPlayers;
    }

    public List<AudioPlayer> getListSing0fAlbum(Album album) {
        //lay list bai hat theo album
        List<AudioPlayer> audioPlayers=new ArrayList<>();
        for (AudioPlayer audioPlayer:getListAudioPlayer()){
            if (audioPlayer.getAlbum().equals(album.getName())){
                audioPlayers.add(audioPlayer);
            }
        }
        return audioPlayers;
    }

    public AudioPlayer getAudioPlayser(String s) {
        //lay bai het theo ten
        List<AudioPlayer> audioPlayers=new ArrayList<>();
        for (AudioPlayer audioPlayer:getListAudioPlayer()){
            if (audioPlayer.getName().equals(s)){
                return audioPlayer;
            }
        }
        return null;
    }
}

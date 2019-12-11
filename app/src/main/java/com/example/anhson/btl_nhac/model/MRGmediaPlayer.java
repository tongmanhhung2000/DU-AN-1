package com.example.anhson.btl_nhac.model;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.TimedText;
import android.os.Handler;
import android.util.Log;

import java.io.IOException;

/**
 * Created by son on 14/9/2016.
 */
public class MRGmediaPlayer implements MediaPlayer.OnCompletionListener, MediaPlayer.OnPreparedListener, Runnable {
    private static final String TAG = MRGmediaPlayer.class.getSimpleName();
    private State state;
    private MediaPlayer mediaPlayer;
    private IMRGMedia imrgMedia;


    public MRGmediaPlayer() {
//        mPlayer = new MediaPlayer();
        state = State.IDLE;

    }

    public void setImrgMedia(IMRGMedia imrgMedia) {
        this.imrgMedia = imrgMedia;
    }

    public void setDataSource(String path)throws IOException {
        mediaPlayer.setDataSource(path);
        state= State.INIT;
    }
    public void prepareAsyn(){
        //bat su kien khi ket thuc
        mediaPlayer.setOnCompletionListener(this);
        //ham bat su kien khi thanh cong
        mediaPlayer.setOnPreparedListener(this);
        state= State.PREPARING;
        mediaPlayer.prepareAsync();
    }
    public State getState(){
        return state;
    }

    public void creatMediplayer(){
        if (mediaPlayer!=null){
            mediaPlayer.release();
        }
        mediaPlayer=new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        state= State.IDLE;
    }
    public void release(){
        mediaPlayer.release();
        mediaPlayer=null;
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        //ket thuc bai hat
        imrgMedia.hetBai();
    }

    private Handler handler=new Handler();
    @Override
    public void onPrepared(MediaPlayer mp) {
        //link den bai hat thanh cong
        state= State.PREPARED;
        mediaPlayer.start();
        state= State.START;
        imrgMedia.setSeekBar(mediaPlayer.getDuration());
        handler.postDelayed(this,100);
    }
    public void start() {
        if (state == State.PREPARED ||
                state == State.STOP ||
                state == State.PAUSE) {
            mediaPlayer.start();
            state = State.START;
        }
    }

    public void pause() {
        if (state == State.START) {
            mediaPlayer.pause();
            state = State.PAUSE;
        }
    }

    public void stop() {
        if (state == State.START || state == State.PAUSE) {
            mediaPlayer.stop();
            state = State.STOP;
        }

    }

    public void seek(int position) {
        if (position < mediaPlayer.getDuration() ||
                state == State.PAUSE || state == State.START ||
                state == State.PREPARED) {
            mediaPlayer.seekTo(position);
        }
    }

    public void loop(boolean loop) {
        if (state != State.IDLE) {
            mediaPlayer.setLooping(loop);
        }
    }

    @Override
    public void run() {
        //get thoi gian dang phat hien tai
        imrgMedia.setSeekBar2(mediaPlayer.getCurrentPosition());
        handler.postDelayed(this,100);
    }

    public enum State{
        PREPARING(0),
        START(1),
        STOP(2),
        PAUSE(3),
        RELEASE(4),
        IDLE(5),
        INIT(6),
        PREPARED(7);

        private int value;

        State(int value) {
            this.value = value;
        }
    }
    public interface IMRGMedia{
        void hetBai();

        void setSeekBar(int duration);

        void setSeekBar2(int currentPosition);
    }
}

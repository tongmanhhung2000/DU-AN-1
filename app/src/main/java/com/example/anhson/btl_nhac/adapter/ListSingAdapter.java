package com.example.anhson.btl_nhac.adapter;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.anhson.btl_nhac.Cost;
import com.example.anhson.btl_nhac.R;
import com.example.anhson.btl_nhac.model.AudioPlayer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anh Son on 4/7/2017.
 */

public class ListSingAdapter extends BaseAdapter {
    private static final String TAG = ListSingAdapter.class.getSimpleName();
    private List<AudioPlayer> audioPlayers;

    private List<ImageView> imageViews;
    private Context context;

    private int playing=-1;
    private String status= Cost.PAUSE;

    public ListSingAdapter(Context context,List<AudioPlayer> audioPlayers) {
        this.context=context;
        this.audioPlayers=audioPlayers;
        imageViews=new ArrayList<>();
    }

    public void setAudioPlayers(List<AudioPlayer> audioPlayers) {
        this.audioPlayers = audioPlayers;
    }

    @Override
    public int getCount() {
        return audioPlayers.size();
    }

    @Override
    public Object getItem(int position) {
        return audioPlayers.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        HollerView hollerView;
        if (convertView==null){
            LayoutInflater layoutInflater=LayoutInflater.from(context);
            convertView=layoutInflater.inflate(R.layout.item_sing,parent,false);
            hollerView=new HollerView();
            hollerView.tvTenBaiHat=(TextView) convertView.findViewById(R.id.tv_title_of_sing);
            hollerView.tvAlbum=(TextView) convertView.findViewById(R.id.tv_album_of_sing1);
            hollerView.tvTenCaSi=(TextView) convertView.findViewById(R.id.tv_artist_of_sing);
            hollerView.imPlaying=(ImageView)convertView.findViewById(R.id.im_sing_is_playing);
            convertView.setTag(hollerView);
        }else {
            hollerView=(HollerView)convertView.getTag();
        }
        AudioPlayer audioPlayer= (AudioPlayer) getItem(position);
        hollerView.tvTenCaSi.setText(audioPlayer.getTenCS());
        hollerView.tvTenBaiHat.setText(audioPlayer.getName());
        hollerView.tvAlbum.setText(audioPlayer.getAlbum());
        imageViews.add(hollerView.imPlaying);
        if (position!=playing||status==Cost.PAUSE){
            hollerView.imPlaying.setVisibility(View.INVISIBLE);
        }else {

            hollerView.imPlaying.setVisibility(View.VISIBLE);
            AnimationDrawable animationUtils=(AnimationDrawable)hollerView.imPlaying.getDrawable();
            animationUtils.start();
        }

        return convertView;
    }

    public void setItemPlaying(View view, int position) {
        status=Cost.PLAY;
        View view1=view;
        TextView textView= (TextView) view.findViewById(R.id.tv_title_of_sing);
        Toast.makeText(context,textView.getText().toString()+"="+position+((AudioPlayer)getItem(position)).getName(),Toast.LENGTH_SHORT).show();

        //set image tat di
        if (playing!=-1){
            if (playing==position){
                return;
            }else {
                //tat item dang chay image
                imageViews.get(position).setVisibility(View.INVISIBLE);
            }
        }
        //chay image
        playing=position;
        ImageView imageView=(ImageView)view.findViewById(R.id.im_sing_is_playing);
        imageView.setVisibility(View.VISIBLE);
        AnimationDrawable animationUtils=(AnimationDrawable)imageView.getDrawable();
        animationUtils.start();
        this.notifyDataSetChanged();

    }

    public void setItemPause() {
        status=Cost.PAUSE;
        Log.d(TAG,"pause"+playing);
        if (playing==-1||playing>=imageViews.size()) return;
        AnimationDrawable animationUtils=(AnimationDrawable)imageViews.get(playing).getDrawable();
        animationUtils.stop();
        imageViews.get(playing).setVisibility(View.INVISIBLE);
    }

    public void setItemPlaying() {
        if (playing==-1||playing>=imageViews.size()) return;
        imageViews.get(playing).setVisibility(View.VISIBLE);
        AnimationDrawable animationUtils=(AnimationDrawable)imageViews.get(playing).getDrawable();
        animationUtils.start();
        this.notifyDataSetChanged();
    }

    public void setNextImage() {
        setItemPause();
        playing++;
        if (playing==getCount()){
            playing=0;
        }
        setItemPlaying();
        status=Cost.PLAY;
    }

    private class HollerView{
        private TextView tvTenBaiHat;
        private TextView tvTenCaSi;
        private TextView tvAlbum;
        private ImageView imPlaying;
    }
}

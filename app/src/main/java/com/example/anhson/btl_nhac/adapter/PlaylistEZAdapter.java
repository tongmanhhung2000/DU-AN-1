package com.example.anhson.btl_nhac.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.anhson.btl_nhac.R;
import com.example.anhson.btl_nhac.model.Playlist;

import java.util.List;

/**
 * Created by Anh Son on 4/25/2017.
 */

public class PlaylistEZAdapter extends BaseAdapter {
    private Context mContext;
    private List<Playlist> playlists;
    public PlaylistEZAdapter(Context context,List<Playlist> playlists) {
        this.mContext=context;
        this.playlists=playlists;
    }

    @Override
    public int getCount() {
        return playlists.size();
    }

    @Override
    public Playlist getItem(int position) {
        return playlists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater=LayoutInflater.from(mContext);
        convertView=layoutInflater.inflate(R.layout.item_playlist_ez,parent,false);
        TextView textView=(TextView)convertView.findViewById(R.id.tv_playlist_ez);
        textView.setText(getItem(position).getName());
        return textView;
    }
}

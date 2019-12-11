package com.example.anhson.btl_nhac.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.anhson.btl_nhac.R;
import com.example.anhson.btl_nhac.model.Album;
import com.example.anhson.btl_nhac.model.Singer;

import java.util.List;

/**
 * Created by Anh Son on 4/11/2017.
 */

public class AlbumAdapter extends BaseAdapter{
    private List<Album> albumList;
    private Context context;

    public AlbumAdapter(Context context, List<Album> albumList) {
        this.context = context;
        this.albumList = albumList;
    }

    @Override
    public int getCount() {
        return albumList.size();
    }

    @Override
    public Object getItem(int position) {
        return albumList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        HollerView hollerView;
        if (convertView == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            convertView = layoutInflater.inflate(R.layout.item_album, parent, false);
            hollerView = new HollerView();
            hollerView.tvNameAlbum = (TextView) convertView.findViewById(R.id.tv_name_album);
            hollerView.tvNameArtist = (TextView) convertView.findViewById(R.id.tv_name_singer_of_album);
            convertView.setTag(hollerView);
        } else {
            hollerView = (HollerView) convertView.getTag();
        }
        Album album= (Album) getItem(position);
        hollerView.tvNameAlbum.setText(album.getName()+"");
        hollerView.tvNameArtist.setText(album.getArtist()+"");

        return convertView;
    }

    private class HollerView {
        private TextView tvNameAlbum;
        private TextView tvNameArtist;
    }
}

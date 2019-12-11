package com.example.anhson.btl_nhac.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.anhson.btl_nhac.R;
import com.example.anhson.btl_nhac.fragment.BaseFragment;
import com.example.anhson.btl_nhac.model.Playlist;

import java.util.List;

/**
 * Created by Anh Son on 4/13/2017.
 */

public class PlayListAdapter extends BaseAdapter {
    private List<Playlist> playlists;
    private Context context;
    public PlayListAdapter(Context context,List<Playlist> playlists) {
        this.context=context;
        this.playlists=playlists;
    }

    public void setPlaylists(List<Playlist> playlists) {
        this.playlists = playlists;
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
        ViewHoller viewHoller;
        if (convertView==null){
            LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
            convertView=layoutInflater.inflate(R.layout.item_playlist,parent,false);
            viewHoller=new ViewHoller();
            viewHoller.tvName=(TextView)convertView.findViewById(R.id.tv_name_playlist);
            viewHoller.tvSoBaiHat=(TextView)convertView.findViewById(R.id.tv_sing_of_playlist);

            convertView.setTag(viewHoller);
        }else {
            viewHoller= (ViewHoller) convertView.getTag();
        }
        Playlist playlist= (Playlist) getItem(position);
        viewHoller.tvName.setText(playlist.getName());
        viewHoller.tvSoBaiHat.setText(playlist.getAudioPlayers().size()+"");

        return convertView;
    }
    public class ViewHoller{
        private TextView tvName;
        private TextView tvSoBaiHat;
    }
}

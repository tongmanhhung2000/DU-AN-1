package com.example.anhson.btl_nhac.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.anhson.btl_nhac.IDialogOfFragment;
import com.example.anhson.btl_nhac.R;
import com.example.anhson.btl_nhac.adapter.PlaylistEZAdapter;
import com.example.anhson.btl_nhac.model.AudioPlayer;
import com.example.anhson.btl_nhac.model.Playlist;

import java.util.List;

/**
 * Created by Anh Son on 4/25/2017.
 */

public class AddSingInPlayListDialog extends Dialog implements AdapterView.OnItemClickListener {


    private IAddSingInPlayListDialog iAddSingInPlayListDialog;

    private AudioPlayer audioPlayer;
    private List<Playlist> playlists;
    private ListView listView;
    private PlaylistEZAdapter playlistEZAdapter;

    public void setAudioPlayer(AudioPlayer audioPlayer) {
        this.audioPlayer = audioPlayer;
    }

    public void setPlaylists(List<Playlist> playlists) {
        this.playlists = playlists;
        playlistEZAdapter=new PlaylistEZAdapter(getContext(),playlists);
        listView.setAdapter(playlistEZAdapter);
        playlistEZAdapter.notifyDataSetChanged();
    }

    public void setiAddSingInPlayListDialog(IAddSingInPlayListDialog iAddSingInPlayListDialog) {
        this.iAddSingInPlayListDialog = iAddSingInPlayListDialog;
    }

    public AddSingInPlayListDialog(Context context) {
        super(context);
        inits();
    }

    public AddSingInPlayListDialog(Context context, int themeResId) {
        super(context, themeResId);
        inits();
    }

    private void inits() {
        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        View view = layoutInflater.inflate(R.layout.dialog_add_sing_in_playlist, null, false);
        setTitle("Thêm");
        setContentView(view);
        initViews(view);
    }

    private void initViews(View view) {
        listView=(ListView)view.findViewById(R.id.list_playlist);

        listView.setOnItemClickListener(this);
    }



    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        iAddSingInPlayListDialog.xuly(audioPlayer,playlistEZAdapter.getItem(position));
        cancel();
    }
    public interface IAddSingInPlayListDialog{
        void xuly(AudioPlayer audioPlayer,Playlist playlist);
    }
}
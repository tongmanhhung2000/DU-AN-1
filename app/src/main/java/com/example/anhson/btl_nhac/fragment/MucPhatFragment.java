package com.example.anhson.btl_nhac.fragment;

import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.anhson.btl_nhac.Cost;
import com.example.anhson.btl_nhac.IFragmentMain;
import com.example.anhson.btl_nhac.R;
import com.example.anhson.btl_nhac.adapter.ListSingAdapter;
import com.example.anhson.btl_nhac.adapter.SingerAdapter;
import com.example.anhson.btl_nhac.dao.ManagerPlayList;
import com.example.anhson.btl_nhac.model.Album;
import com.example.anhson.btl_nhac.model.AudioPlayer;
import com.example.anhson.btl_nhac.model.ManagerMedia;
import com.example.anhson.btl_nhac.model.Playlist;
import com.example.anhson.btl_nhac.model.Singer;

import java.util.List;

/**
 * Created by Anh Son on 4/11/2017.
 */

public class MucPhatFragment extends BaseFragment implements AdapterView.OnItemClickListener, View.OnClickListener {
    private static final String TAG = MucPhatFragment.class.getSimpleName();
    private ListView listSing;
    private LinearLayout hearder;
    private ListSingAdapter listSingAdapter;
    private List<AudioPlayer> audioPlayers;

    private IFragmentMain iFragmentMain;

    @Override
    public void setiFragmentMain(IFragmentMain iFragmentMain) {
        this.iFragmentMain = iFragmentMain;
    }

    public void setAudioPlayers(List<AudioPlayer> audioPlayers) {
        this.audioPlayers = audioPlayers;
    }

    @Override
    protected void initsView(View view) {
        listSing=(ListView)view.findViewById(R.id.lv_list_sing2);
        LayoutInflater layoutInflater=LayoutInflater.from(getContext());
        hearder= (LinearLayout) layoutInflater.inflate(R.layout.item_hearder_phat_tuy_chon,listSing,false);
        listSing.addHeaderView(hearder);
        listSingAdapter=new ListSingAdapter(getContext(),audioPlayers);
        listSing.setAdapter(listSingAdapter);
        listSing.setOnItemClickListener(this);
        hearder.findViewById(R.id.btn_play_all_sing1).setOnClickListener(this);
    }

    @Override
    protected int getViews() {
        return R.layout.fragment_muc_phat_tuy_chon;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (position==0)return;
        position=position-1;

        iFragmentMain.setPlaying(audioPlayers,position);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_play_all_sing1:
                iFragmentMain.setPlaying(audioPlayers,0);
                break;
        }
    }
}

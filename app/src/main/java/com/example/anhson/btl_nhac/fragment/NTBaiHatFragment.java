package com.example.anhson.btl_nhac.fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.anhson.btl_nhac.IDialogOfFragment;
import com.example.anhson.btl_nhac.R;
import com.example.anhson.btl_nhac.adapter.ListSingAdapter;
import com.example.anhson.btl_nhac.dialog.ChooseSingDialog;
import com.example.anhson.btl_nhac.model.Album;
import com.example.anhson.btl_nhac.model.AudioPlayer;
import com.example.anhson.btl_nhac.model.ManagerMedia;
import com.example.anhson.btl_nhac.model.Singer;

import java.util.List;

/**
 * Created by Anh Son on 4/7/2017.
 */

public class NTBaiHatFragment extends BaseFragment implements View.OnClickListener, AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener, IDialogOfFragment {
    private static final String TAG = NTBaiHatFragment.class.getSimpleName();
    private ListView listSing;
    private ListSingAdapter listSingAdapter;
    private LinearLayout hearderListSing;
    private List<AudioPlayer> audioList;
    private ChooseSingDialog chooseSingDialog;

    public void setAudioList(List<AudioPlayer> audioList) {
        this.audioList = audioList;
    }

    @Override
    protected void initsView(View view) {
        chooseSingDialog=new ChooseSingDialog(getContext());
        chooseSingDialog.setiDialogOfFragment(this);

        Log.d("NTBaiHatFragment","initsView");
        //set adapter
        listSingAdapter=new ListSingAdapter(getContext(),audioList);
        listSing=(ListView)view.findViewById(R.id.list_sing);
        listSing.setAdapter(listSingAdapter);

        //add hearder
        //lay hearder
        LayoutInflater layoutInflater=LayoutInflater.from(getContext());//khoi tao layoutinflate
        hearderListSing= (LinearLayout) layoutInflater.inflate(R.layout.item_hearder_ntsing,listSing,false);
        listSing.addHeaderView(hearderListSing);
        hearderListSing.findViewById(R.id.btn_play_all_sing).setOnClickListener(this);
        hearderListSing.findViewById(R.id.btn_choose_sing).setOnClickListener(this);
        hearderListSing.findViewById(R.id.btn_ss_ten).setOnClickListener(this);
        // add su kien
        listSing.setOnItemClickListener(this);
        listSing.setOnItemLongClickListener(this);
    }

    @Override
    protected int getViews() {
        return R.layout.fragment_ngoai_tuyen_bai_hat;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_play_all_sing:
                //phat tat ca
                iFragmentMain.setPlaying(audioList,0);
                break;
            case R.id.btn_choose_sing:
                //show fragment choose

                break;
            case R.id.btn_ss_ten:
                //sap xep theo ten

                break;
        }

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (position==0) return;
        //add image on item sing
//        listSingAdapter.setItemPlaying(view,position-1);//tru 1 do co hearder
        // add image on control bottom
        iFragmentMain.setPlaying(audioList,position-1);
    }

    public void setImagePause() {
        //dung hieu ung tren list sing
        listSingAdapter.setItemPause();
    }

    public void setImagePlay() {
        listSingAdapter.setItemPlaying();//tru 1 do co hearder
    }

    public void setImageNext() {
        listSingAdapter.setNextImage();
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        chooseSingDialog.setAudioPlayer((AudioPlayer) listSingAdapter.getItem(position));
        chooseSingDialog.show();
        return false;
    }

    @Override
    public void xuly(String name) {
        //ko lam gi ca

    }

    @Override
    public void addIntoYeuThich(AudioPlayer audioPlayer) {
        iFragmentMain.addIntoYeuThich(audioPlayer);
    }

    @Override
    public void addIntoPlayList(AudioPlayer audioPlayer) {
//        iFragmentMain.addIntoYeuThich(audioPlayer);
        iFragmentMain.addIntoPlayList(audioPlayer);
    }

    @Override
    public void sendSing(AudioPlayer audioPlayer) {
        iFragmentMain.sendSing(audioPlayer);
    }
}

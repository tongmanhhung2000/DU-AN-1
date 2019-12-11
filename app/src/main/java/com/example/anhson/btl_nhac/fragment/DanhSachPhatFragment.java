package com.example.anhson.btl_nhac.fragment;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.anhson.btl_nhac.IDialogOfFragment;
import com.example.anhson.btl_nhac.IFragmentMain;
import com.example.anhson.btl_nhac.R;
import com.example.anhson.btl_nhac.adapter.PlayListAdapter;
import com.example.anhson.btl_nhac.dao.ManagerPlayList;
import com.example.anhson.btl_nhac.dialog.NewPlayListDialog;
import com.example.anhson.btl_nhac.model.AudioPlayer;
import com.example.anhson.btl_nhac.model.ManagerMedia;
import com.example.anhson.btl_nhac.model.Playlist;

/**
 * Created by Anh Son on 4/7/2017.
 */

public class DanhSachPhatFragment extends BaseFragment implements View.OnClickListener, IDialogOfFragment, AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {
    private static final String TAG = NgoaiTuyenFragment.class.getSimpleName();
    private LinearLayout llNgoaiTuyen;
    private LinearLayout llYeuThich;

    private IFragmentMain iFragmentMain;

    private ListView listPlaylist;
    private PlayListAdapter playListAdapter;

    private TextView tvSoBaiHatNgoaiTuyen;
    private LinearLayout footerPlaylist;

    private ManagerPlayList managerPlayList;
    private ManagerMedia managerMedia;

    private NewPlayListDialog newPlayListDialog;

    private Playlist listYeuThich;
    private TextView tvSoBaiYeuThich;

    public void setManagerPlayList(ManagerPlayList managerPlayList) {
        this.managerPlayList = managerPlayList;
    }

    public void setManagerMedia(ManagerMedia managerMedia) {
        this.managerMedia = managerMedia;
    }

    @Override
    protected void initsView(View view) {
        //khoi tao csdl media

        tvSoBaiHatNgoaiTuyen=(TextView)view.findViewById(R.id.tv_so_bai_ngoai_tuyen);
        tvSoBaiHatNgoaiTuyen.setText(managerMedia.getListAudioPlayer().size()+"");

        llNgoaiTuyen =(LinearLayout)view.findViewById(R.id.ll_ngoai_tuyen);
        llNgoaiTuyen.setBackgroundResource(R.drawable.bachgrounk_item_list);
        llNgoaiTuyen.setOnClickListener(this);

        llYeuThich =(LinearLayout)view.findViewById(R.id.ll_yeu_thich);
        llYeuThich.setBackgroundResource(R.drawable.bachgrounk_item_list);
        llYeuThich.setOnClickListener(this);

        //lay list yeu thich
        listYeuThich=managerPlayList.getListYeuThich();
        managerPlayList.closeDataBase();
        if (listYeuThich!=null){
            tvSoBaiYeuThich=(TextView)view.findViewById(R.id.tv_so_bai_yeu_thich);
            tvSoBaiYeuThich.setText(listYeuThich.getAudioPlayers().size()+"");
        }

        //khoi tao dialog
        newPlayListDialog=new NewPlayListDialog(getContext());
        newPlayListDialog.setiDialogOfFragment(this);

        //khoi tao adapter
        listPlaylist=(ListView)view.findViewById(R.id.list_danh_sach_phat) ;

        playListAdapter=new PlayListAdapter(getContext(),managerPlayList.getPlaylists());
        managerPlayList.closeDataBase();
        listPlaylist.setAdapter(playListAdapter);

        //set footer cho listvire
        LayoutInflater layoutInflater=LayoutInflater.from(getContext());
        footerPlaylist= (LinearLayout) layoutInflater.inflate(R.layout.itemfooter_play_list,listPlaylist,false);
        listPlaylist.addFooterView(footerPlaylist);
        listPlaylist.setOnItemClickListener(this);
        listPlaylist.setOnItemLongClickListener(this);

        footerPlaylist.findViewById(R.id.btn_add_new_dsbh).setOnClickListener(this);
    }

    public void setiFragmentMain(IFragmentMain iFragmentMain) {
        this.iFragmentMain = iFragmentMain;
    }

    @Override
    protected int getViews() {
        return R.layout.fragment_dsphat;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_ngoai_tuyen:
                iFragmentMain.showNgoaiTuyenFragment();
                break;
            case R.id.ll_yeu_thich:
                Log.d(TAG,"yeu thich show");
                if (listYeuThich!=null){
                    iFragmentMain.showListYeuThich(listYeuThich);
                }
                break;
            case R.id.btn_add_new_dsbh:
                newPlayListDialog.setDanhSach(managerPlayList.getPlaylists().size());
                newPlayListDialog.show();
                break;

        }
    }

    @Override
    public void xuly(String name) {
        Toast.makeText(getContext(),"hello",Toast.LENGTH_LONG).show();
        //them moi 1 Playlist
        Playlist playlist =new Playlist(0,name);
        managerPlayList.addPlaylist(playlist);
        playListAdapter.setPlaylists(managerPlayList.getPlaylists());
        playListAdapter.notifyDataSetChanged();
    }



    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (position==playListAdapter.getCount()){
            return;
        }
        iFragmentMain.showListYeuThich(playListAdapter.getItem(position));
    }

    public void update() {
        listYeuThich=managerPlayList.getListYeuThich();
        managerPlayList.closeDataBase();
        if (listYeuThich!=null){
            tvSoBaiYeuThich.setText(listYeuThich.getAudioPlayers().size()+"");
        }
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
        final AlertDialog.Builder builder=new AlertDialog.Builder(getContext());

        builder.setMessage("ban co muon xoa "+playListAdapter.getItem(position).getName()+" ko?");

        builder.setPositiveButton("CO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                managerPlayList.deletePlaylist(playListAdapter.getItem(position));
                playListAdapter.setPlaylists(managerPlayList.getPlaylists());
                playListAdapter.notifyDataSetChanged();
            }
        });
        builder.setNegativeButton("KO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
        return true;
    }
    @Override
    public void addIntoYeuThich(AudioPlayer audioPlayer) {

    }

    @Override
    public void addIntoPlayList(AudioPlayer audioPlayer) {

    }

    @Override
    public void sendSing(AudioPlayer audioPlayer) {

    }
}

package com.example.anhson.btl_nhac.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.anhson.btl_nhac.Cost;
import com.example.anhson.btl_nhac.IFragmentMain;
import com.example.anhson.btl_nhac.R;
import com.example.anhson.btl_nhac.adapter.ListSingAdapter;
import com.example.anhson.btl_nhac.dao.ManagerPlayList;
import com.example.anhson.btl_nhac.dialog.AddSingInPlayListDialog;
import com.example.anhson.btl_nhac.fragment.DanhSachPhatFragment;
import com.example.anhson.btl_nhac.fragment.MucPhatFragment;
import com.example.anhson.btl_nhac.fragment.NTAlbumFragment;
import com.example.anhson.btl_nhac.fragment.NTBaiHatFragment;
import com.example.anhson.btl_nhac.fragment.NTCaSiFragment;
import com.example.anhson.btl_nhac.fragment.NTThuMucFragment;
import com.example.anhson.btl_nhac.fragment.NgoaiTuyenFragment;
import com.example.anhson.btl_nhac.model.Album;
import com.example.anhson.btl_nhac.model.AudioPlayer;
import com.example.anhson.btl_nhac.model.MRGmediaPlayer;
import com.example.anhson.btl_nhac.model.ManagerMedia;
import com.example.anhson.btl_nhac.model.Playlist;
import com.example.anhson.btl_nhac.model.Singer;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;

import java.io.IOException;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements IFragmentMain, View.OnClickListener,
        MRGmediaPlayer.IMRGMedia, SeekBar.OnSeekBarChangeListener, AdapterView.OnItemClickListener,
        AddSingInPlayListDialog.IAddSingInPlayListDialog {

    private static final String TAG = MainActivity.class.getSimpleName();
    private DanhSachPhatFragment danhSachPhatFragment;
    private NgoaiTuyenFragment ngoaiTuyenFragment;
    private MucPhatFragment mucPhatFragment;

    private NTBaiHatFragment ntBaiHatFragment;
    private NTCaSiFragment ntCaSiFragment;
    private NTThuMucFragment ntThuMucFragment;
    private NTAlbumFragment ntAlbumFragment;

    private RotateAnimation rotateAnimation;
    private ImageView imPlaydisc;
    private ImageButton imPlay;
    private ImageButton imNext;

    private SharedPreferences preferences;

    private SlidingUpPanelLayout mLayout;
    private SeekBar seekBar;

    private ImageButton btnBackPlay;
    private ImageButton btnPauseAndPlay;
    private ImageButton btnNextAndPlay;
    private ImageButton btnPressAndPlay;
    private ImageButton btnShare;
    private ImageButton btnLap;
    private ImageButton btnKoLap;
    private ImageButton btnTron;
    private ImageButton btnHenGio;
    private ImageButton btnYeuThich;

    private TextView tvSingNamePlay;
    private TextView tvArtistNamePlay;
    private TextView tvAlbumNamePlay;
    private TextView tvNameSing;
    private TextView tvNameArtist;
    private TextView tvTimePlaying;
    private TextView tvTimeMax;

    private ManagerMedia managerMedia;
    private MRGmediaPlayer mMediaPlayer;
    private ManagerPlayList managerPlayList;

    private List<AudioPlayer> listSing;
    private int baiSo=-1;
    private ListView listPlaying;
    private ListSingAdapter listPlayingAdapter;

    private AddSingInPlayListDialog addSingInPlayListDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initsView();
        initFragments();
        initListSing();
        initDialog();
    }

    private void initDialog() {

    }

    private void initListSing() {
        listSing=managerMedia.getListAudioPlayer();
        baiSo=-1;
        listPlayingAdapter=new ListSingAdapter(this,listSing);
        listPlaying.setAdapter(listPlayingAdapter);
        listPlaying.setOnItemClickListener(this);
//        listPlaying.setAnimation(AnimationUtils.loadAnimation(this,R.anim.open_enter));
//        listPlaying.startAnimation(AnimationUtils.loadAnimation(this,R.anim.open_enter));
    }


    private void initFragments() {
        //khoi tao managers
        managerMedia=new ManagerMedia(this);
        managerPlayList=new ManagerPlayList(this);
        mMediaPlayer=new MRGmediaPlayer();
        mMediaPlayer.setImrgMedia(this );

        danhSachPhatFragment=new DanhSachPhatFragment();
        danhSachPhatFragment.setiFragmentMain(this);
        danhSachPhatFragment.setManagerPlayList(managerPlayList);
        danhSachPhatFragment.setManagerMedia(managerMedia);

        //cac fragment ngoai tuyen con
        ntAlbumFragment =new NTAlbumFragment();
        ntAlbumFragment.setiFragmentMain(this);
        ntAlbumFragment.setAlbumList(managerMedia.getListAlbum());

        ntBaiHatFragment=new NTBaiHatFragment();
        ntBaiHatFragment.setiFragmentMain(this);
        ntBaiHatFragment.setAudioList(managerMedia.getListAudioPlayer());

        ntCaSiFragment=new NTCaSiFragment();
        ntCaSiFragment.setiFragmentMain(this);
        ntCaSiFragment.setSingerList(managerMedia.getListSinger());

        ntThuMucFragment=new NTThuMucFragment();
        ntThuMucFragment.setiFragmentMain(this);

        //fragment 1 muc phat list nao do
        mucPhatFragment =new MucPhatFragment();
        mucPhatFragment.setiFragmentMain(this);


        //add cac frag ment ngoai tuyen con vao fragment ngoai tuyen cha
        ngoaiTuyenFragment=new NgoaiTuyenFragment();
        ngoaiTuyenFragment.setiFragmentMain(this);

        ngoaiTuyenFragment.setNtBaiHatFragment(ntBaiHatFragment);
        ngoaiTuyenFragment.setNtCaSiFragment(ntCaSiFragment);
        ngoaiTuyenFragment.setNtAlbumFragment(ntAlbumFragment);
        ngoaiTuyenFragment.setNtThuMucFragment(ntThuMucFragment);

        //set action bar
        setActionBar(false,"Music");
        //set fragment dau tien hien thi
        getSupportFragmentManager().beginTransaction()
                .add(R.id.conten_main,danhSachPhatFragment,DanhSachPhatFragment.class.getName())
                .commit();
    }

    private void initsView() {
        //khoi tao actionBar
        setSupportActionBar((Toolbar) findViewById(R.id.main_toolbar));
        //khoi tao Layout
        mLayout=(SlidingUpPanelLayout)findViewById(R.id.sliding_layout_main);
        mLayout.setPanelHeight(0);
        mLayout.setPanelState(SlidingUpPanelLayout.PanelState.HIDDEN);
        //khoi tao list dang phat
        listPlaying=(ListView)findViewById(R.id.listPlaying);

        //khoi tao seekbar
        seekBar=(SeekBar)findViewById(R.id.seekBar);
        seekBar.setOnSeekBarChangeListener(this);

        //khoi tao SharedPreferences
        preferences=getSharedPreferences(Cost.MY_APP,MODE_PRIVATE);

        //set image chuyen dong khi playing
        imPlaydisc=(ImageView)findViewById(R.id.image_sing);
        rotateAnimation= (RotateAnimation) AnimationUtils.loadAnimation(this,R.anim.rotale_item_play);
        imPlaydisc.setAnimation(rotateAnimation);
        rotateAnimation.cancel();
//        rotateAnimation.start();

        imPlay=(ImageButton)findViewById(R.id.btn_play);
        imNext=(ImageButton)findViewById(R.id.btn_next);

        imPlay.setOnClickListener(this);
        imNext.setOnClickListener(this);

        btnBackPlay=(ImageButton)findViewById(R.id.btn_hiden_layout_play);
        btnNextAndPlay=(ImageButton)findViewById(R.id.btn_next_play);
        btnPauseAndPlay=(ImageButton)findViewById(R.id.btn_play_and_pause);
        btnPressAndPlay=(ImageButton)findViewById(R.id.btn_press_play);
        btnShare=(ImageButton)findViewById(R.id.btn_share);
        btnLap=(ImageButton)findViewById(R.id.btn_lap_bai);
        btnKoLap=(ImageButton)findViewById(R.id.btn_ko_lap);
        btnTron=(ImageButton)findViewById(R.id.btn_tron);
        btnHenGio=(ImageButton)findViewById(R.id.btn_hen_gio);
        btnYeuThich=(ImageButton)findViewById(R.id.btn_yeu_thich);

        btnBackPlay.setOnClickListener(this);
        btnNextAndPlay.setOnClickListener(this);
        btnPauseAndPlay.setOnClickListener(this);
        btnPressAndPlay.setOnClickListener(this);
        btnShare.setOnClickListener(this);
        btnLap.setOnClickListener(this);
        btnKoLap.setOnClickListener(this);
        btnTron.setOnClickListener(this);
        btnHenGio.setOnClickListener(this);
        btnYeuThich.setOnClickListener(this);

        //mac dinh la che do hat toan bai
        btnKoLap.setImageTintList(ColorStateList.valueOf(Color.parseColor(Cost.GREEN)));

        tvAlbumNamePlay=(TextView)findViewById(R.id.tv_album_name_play);
        tvArtistNamePlay=(TextView)findViewById(R.id.tv_artist_name_play);
        tvSingNamePlay=(TextView)findViewById(R.id.tv_sing_name_play);
        tvNameSing=(TextView)findViewById(R.id.tv_name_sing);
        tvNameArtist=(TextView)findViewById(R.id.tv_artist);
        tvTimePlaying=(TextView)findViewById(R.id.tv_time_playing);
        tvTimeMax=(TextView)findViewById(R.id.tv_time_max);

        findViewById(R.id.btn_show_layout_play).setOnClickListener(this);
        findViewById(R.id.dragView).setOnClickListener(this);
    }

    @Override
    public void showNgoaiTuyenFragment() {

        //set action bar
        setActionBar(true,"ngoai tuyen");
        fragmentStatus=Cost.NGOAITUYENFRAGMENT;
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(
                        R.anim.open_enter,
                        R.anim.open_exit,
                        R.anim.close_enter,
                        R.anim.close_exit)
                .replace(R.id.conten_main,ngoaiTuyenFragment,NgoaiTuyenFragment.class.getName())
                .addToBackStack(Cost.NGOAITUYENFRAGMENT)
                .commit();
    }

    @Override
    public void showMucPhatFragment(Singer singer) {

        //set action bar
        setActionBar(true,singer.getName());


        fragmentStatus=Cost.MUCPHATFRAGMENT;
        mucPhatFragment.setAudioPlayers(managerMedia.getListSing0fSinger(singer));
        Log.d(TAG,"show muc phat"+singer.getName());
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(
                        R.anim.open_enter,
                        R.anim.open_exit,
                        R.anim.close_enter,
                        R.anim.close_exit)
                .replace(R.id.conten_main,mucPhatFragment,MucPhatFragment.class.getName())
                .addToBackStack(Cost.MUCPHATFRAGMENT)
                .commit();

    }

    @Override
    public void showMucPhatFragment(Album album) {

        //set action bar
        setActionBar(true,album.getName());


        fragmentStatus=Cost.MUCPHATFRAGMENT;
        mucPhatFragment.setAudioPlayers(managerMedia.getListSing0fAlbum(album));
        Log.d(TAG,"show muc phat"+album.getName());
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(
                        R.anim.open_enter,
                        R.anim.open_exit,
                        R.anim.close_enter,
                        R.anim.close_exit)
                .replace(R.id.conten_main,mucPhatFragment,MucPhatFragment.class.getName())
                .addToBackStack(Cost.MUCPHATFRAGMENT)
                .commit();

    }

    @Override
    public void setPlaying(List<AudioPlayer> audioList, int i) {
//        imPlay.setImageResource(R.drawable.pause_button);
//        status=Cost.PLAYSTATUS;
        this.baiSo=i;
        this.listSing=audioList;
        listPlayingAdapter.setAudioPlayers(audioList);
        listPlayingAdapter.notifyDataSetChanged();
        playMusic(audioList.get(i));
    }

    @Override
    public void showListYeuThich(Playlist playlist) {
        //set action bar
        if (playlist.getId()==1){
            setActionBar(true,"yeu thich");
        }else {
            setActionBar(true,playlist.getName());
        }

        fragmentStatus=Cost.MUCPHATFRAGMENT;
        mucPhatFragment.setAudioPlayers(playlist.getAudioPlayers());

        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(
                        R.anim.open_enter,
                        R.anim.open_exit,
                        R.anim.close_enter,
                        R.anim.close_exit)
                .replace(R.id.conten_main,mucPhatFragment,MucPhatFragment.class.getName())
                .addToBackStack(Cost.MUCPHATFRAGMENT)
                .commit();

    }

    @Override
    public void addIntoYeuThich(AudioPlayer audioPlayer) {
        if (managerPlayList.checkYeuThich(listSing.get(baiSo))){
            Toast.makeText(this,"Bài hát đã có trong mục yêu thích!",Toast.LENGTH_LONG).show();
        }else {
            managerPlayList.addAudioOnPlaylist(managerPlayList.getListYeuThich(),audioPlayer);
        }
        danhSachPhatFragment.update();
        managerPlayList.closeDataBase();
    }

    @Override
    public void sendSing(AudioPlayer audioPlayer) {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("audio/*");
        String path = audioPlayer.getPath();
        shareIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse(path));
        startActivity(Intent.createChooser(shareIntent, "Share audio"));
    }

    @Override
    public void addIntoPlayList(AudioPlayer audioPlayer) {
        AddSingInPlayListDialog addSingInPlayListDialog=new AddSingInPlayListDialog(this);
        addSingInPlayListDialog.setiAddSingInPlayListDialog(this);

        addSingInPlayListDialog.setPlaylists(managerPlayList.getPlaylists());
        addSingInPlayListDialog.setAudioPlayer(audioPlayer);
        managerPlayList.closeDataBase();
        addSingInPlayListDialog.show();
    }

    private String status=Cost.PAUSESTATUS;
    private String fragmentStatus=Cost.DANHSACHPHATFRAGMENT;

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_play:
                playAndPauseMusic();
                break;
            case R.id.btn_next:
                nextMusic();
                break;
            case R.id.btn_hiden_layout_play:
               hidenLayoutPlay();
                break;
            case R.id.btn_next_play:
                nextMusic();
                break;
            case R.id.btn_press_play:
                pressMusic();
                break;
            case R.id.btn_play_and_pause:
                playAndPauseMusic();
                break;
            case R.id.btn_show_layout_play:
                showLayoutPlay();
                break;
            case R.id.btn_share:
                shareSing();
                break;
            case R.id.btn_lap_bai:
                setLapLai();
                break;
            case R.id.btn_ko_lap:
                setNolapLai();
                break;
            case R.id.btn_tron:
                setTronBai();
                break;
            case R.id.btn_hen_gio:
                henGioTat();
                break;
            case R.id.btn_yeu_thich:
                editYeuThichList();
                break;

        }
    }

    private void editYeuThichList() {
        if (managerPlayList.checkYeuThich(listSing.get(baiSo))){
            btnYeuThich.setImageTintList(ColorStateList.valueOf(Color.parseColor(Cost.PRIMARY)));
            managerPlayList.deleteSingOfPlayList(listSing.get(baiSo),managerPlayList.getListYeuThich());
        }else {
            btnYeuThich.setImageTintList(ColorStateList.valueOf(Color.parseColor(Cost.GREEN)));
            managerPlayList.addAudioOnPlaylist(managerPlayList.getListYeuThich(),listSing.get(baiSo));

        }
        danhSachPhatFragment.update();
        managerPlayList.closeDataBase();

    }

    private String typePlayMusic=Cost.NOREPEAT;
    private void henGioTat() {
//        if (mMediaPlayer.getState() == MRGmediaPlayer.State.START) {
//            mMediaPlayer.pause();
//            Log.d(TAG, "pause");
//            rotateAnimation.cancel();
//            btnPauseAndPlay.setImageResource(R.drawable.music_player_play);
//            imPlay.setImageResource(R.drawable.music_player_play);
//        }
        //dang nghien cuu
    }

    private void setTronBai() {
        typePlayMusic=Cost.SHUFFLE;
        btnTron.setImageTintList(ColorStateList.valueOf(Color.parseColor(Cost.GREEN)));

        btnLap.setImageTintList(ColorStateList.valueOf(Color.parseColor(Cost.PRIMARY)));

        btnKoLap.setImageTintList(ColorStateList.valueOf(Color.parseColor(Cost.PRIMARY)));
    }

    private void setNolapLai() {
        typePlayMusic=Cost.NOREPEAT;
        btnTron.setImageTintList(ColorStateList.valueOf(Color.parseColor(Cost.PRIMARY)));

        btnLap.setImageTintList(ColorStateList.valueOf(Color.parseColor(Cost.PRIMARY)));

        btnKoLap.setImageTintList(ColorStateList.valueOf(Color.parseColor(Cost.GREEN)));
    }

    private void setLapLai() {
        typePlayMusic=Cost.REPEAT;
        btnTron.setImageTintList(ColorStateList.valueOf(Color.parseColor(Cost.PRIMARY)));

        btnLap.setImageTintList(ColorStateList.valueOf(Color.parseColor(Cost.GREEN)));

        btnKoLap.setImageTintList(ColorStateList.valueOf(Color.parseColor(Cost.PRIMARY)));
    }

    private void shareSing() {
        if (baiSo==-1){
            Toast.makeText(this,"Chưa có bài hát được phát!",Toast.LENGTH_LONG).show();
            return;
        }
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("audio/*");
        String path = listSing.get(baiSo).getPath();
        shareIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse(path));
        startActivity(Intent.createChooser(shareIntent, "Share audio"));
    }

    private void playMusic(AudioPlayer audioPlayer){
        //set trang thai
        status=Cost.PLAY;

        //set hinh anh
        rotateAnimation.start();
        imPlay.setImageResource(R.drawable.pause_button);
        btnPauseAndPlay.setImageResource(R.drawable.pause_button);
        tvSingNamePlay.setText(audioPlayer.getName());
        tvArtistNamePlay.setText(audioPlayer.getTenCS());
        tvAlbumNamePlay.setText(audioPlayer.getAlbum());
        tvNameSing.setText(audioPlayer.getName());
        tvNameArtist.setText(audioPlayer.getTenCS());

        //check yeu thich hay chua
        if(managerPlayList.checkYeuThich(audioPlayer)){
            btnYeuThich.setImageTintList(ColorStateList.valueOf(Color.parseColor(Cost.GREEN)));
        }else{
            btnYeuThich.setImageTintList(ColorStateList.valueOf(Color.parseColor(Cost.PRIMARY)));
        }

        //set âm thanh
        mMediaPlayer.creatMediplayer();
        try {
            mMediaPlayer.setDataSource(audioPlayer.getPath());
            mMediaPlayer.prepareAsyn();

            //set seekBar
            int tile=0;
//            tvTimeMax.setText(mMediaPlayer.getDuration()+"");
//            tvTimePlaying.setText("");

        } catch (IOException e) {
            e.printStackTrace();
        }



    }
    private void playAndPauseMusic(){
        if (baiSo == -1) {
            baiSo=0;
            playMusic(listSing.get(baiSo));
            Log.d(TAG, "play -1");
            return;
        }
        //pause or start
        if (mMediaPlayer.getState() == MRGmediaPlayer.State.START) {
            mMediaPlayer.pause();
            Log.d(TAG, "pause");
            rotateAnimation.cancel();
            btnPauseAndPlay.setImageResource(R.drawable.music_player_play);
            imPlay.setImageResource(R.drawable.music_player_play);
        } else {
            if (mMediaPlayer.getState() == MRGmediaPlayer.State.PAUSE ||
                    mMediaPlayer.getState() == MRGmediaPlayer.State.STOP
                    ) {

                mMediaPlayer.start();
                rotateAnimation.start();
                btnPauseAndPlay.setImageResource(R.drawable.pause_button);
                imPlay.setImageResource(R.drawable.pause_button);
            }
        }
    }
    private void nextMusic(){
        //chinh bai hat
        if (typePlayMusic==Cost.SHUFFLE){
            //truong hop tron lung tung
            Random random=new Random();
            int bai;
            do {
                bai=random.nextInt(listSing.size());
            }while (baiSo==bai);
            baiSo=bai;
        }else {
            //truong hop ko tron
            if (baiSo == -1 && listSing.size() > 0) {
                baiSo = 0;
            } else {
                if (baiSo == listSing.size() - 1) {
                    baiSo = 0;
                } else {
                    baiSo = baiSo + 1;
                }
            }
        }
        playMusic(listSing.get(baiSo));

    }
    private void pressMusic(){
        //chinh bai hat
        if (typePlayMusic==Cost.SHUFFLE){
            //truong hop tron lung tung
            Random random=new Random();
            int bai;
            do {
                bai=random.nextInt(listSing.size());
            }while (baiSo==bai);
            baiSo=bai;
        }else {
            if (baiSo == -1 || baiSo == 0) {
                baiSo = listSing.size() - 1;

            } else {
                baiSo = baiSo - 1;
            }
        }
       playMusic(listSing.get(baiSo));

    }
    private void hidenLayoutPlay(){
        if (mLayout.getPanelState()!= SlidingUpPanelLayout.PanelState.COLLAPSED||
                mLayout.getPanelState()!= SlidingUpPanelLayout.PanelState.HIDDEN){
            mLayout.setPanelState(SlidingUpPanelLayout.PanelState.HIDDEN);
        }
    }
    private void showLayoutPlay(){
        mLayout.setPanelState(SlidingUpPanelLayout.PanelState.EXPANDED);
    }

    private void setActionBar(boolean back,String tittle){
        getSupportActionBar().setTitle(tittle);
        if (back){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }else {
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            getSupportActionBar().setDisplayShowHomeEnabled(false);
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        //an layout play
        if (mLayout.getPanelState()!= SlidingUpPanelLayout.PanelState.COLLAPSED&&
                mLayout.getPanelState()!= SlidingUpPanelLayout.PanelState.HIDDEN){
            hidenLayoutPlay();
        }else {
            super.onBackPressed();
            Fragment fragment=getSupportFragmentManager().findFragmentByTag(DanhSachPhatFragment.class.getName());
            if (fragment!=null&&fragment.isVisible()){
                setActionBar(false,"Music");
                Log.d(TAG,Cost.DANHSACHPHATFRAGMENT);
            }
            fragment=getSupportFragmentManager().findFragmentByTag(MucPhatFragment.class.getName());
            if (fragment!=null&&fragment.isVisible()){
                setActionBar(true,"hello");
                Log.d(TAG,Cost.MUCPHATFRAGMENT);
            }
            fragment=getSupportFragmentManager().findFragmentByTag(NgoaiTuyenFragment.class.getName());
            if (fragment!=null&&fragment.isVisible()){
                setActionBar(true,"ngoai tuyen");
                Log.d(TAG,Cost.NGOAITUYENFRAGMENT);
            }

        }

    }

    @Override
    public void hetBai() {
        if (typePlayMusic==Cost.REPEAT){
            //ko thay doi
            // lap lai 1 bai thoi
        }
        if (typePlayMusic==Cost.NOREPEAT){
            baiSo=baiSo+1;
            if (baiSo>=listSing.size()){
                baiSo=0;
                return;
            }
        }
        if (typePlayMusic==Cost.SHUFFLE){
            Random random=new Random();
            int bai;
            do {
                bai=random.nextInt(listSing.size());
            }while (baiSo==bai);
            baiSo=bai;
        }
        playMusic(listSing.get(baiSo));
    }

    @Override
    public void setSeekBar(int duration) {
        String timeMax=duration/1000/60+":"+duration/1000%60;
        tvTimeMax.setText(timeMax+"");
        seekBar.setProgress(0);
        seekBar.setMax(duration/1000);
        tvTimePlaying.setText("0:00");
    }

    @Override
    public void setSeekBar2(int currentPosition) {
        String time=currentPosition/1000/60+":"+currentPosition/1000%60;
        seekBar.setProgress(currentPosition/1000);
        tvTimePlaying.setText(time);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        //

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        if (baiSo<0||baiSo>=listSing.size()){
            return;
        }
        mMediaPlayer.seek(seekBar.getProgress()*1000);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        setPlaying(listSing,position);
    }

    @Override
    public void xuly(AudioPlayer audioPlayer, Playlist playlist) {
        //xu ly add bai hat vao playlist
        managerPlayList.addAudioOnPlaylist(playlist, audioPlayer);
    }
}

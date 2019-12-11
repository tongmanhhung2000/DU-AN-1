package com.example.anhson.btl_nhac.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.anhson.btl_nhac.IDialogOfFragment;
import com.example.anhson.btl_nhac.R;
import com.example.anhson.btl_nhac.model.AudioPlayer;

/**
 * Created by Anh Son on 4/25/2017.
 */

public class ChooseSingDialog extends Dialog implements View.OnClickListener {

    private TextView tvThem;
    private TextView tvGui;
    private TextView tvYeuThich;

    private IDialogOfFragment iDialogOfFragment;

    private AudioPlayer audioPlayer;

    public void setAudioPlayer(AudioPlayer audioPlayer) {
        this.audioPlayer = audioPlayer;
        setTitle(audioPlayer.getName());
    }

    public ChooseSingDialog(Context context) {
        super(context);
        inits();
    }

    public ChooseSingDialog(Context context, int themeResId) {
        super(context, themeResId);
        inits();
    }

    private void inits() {
        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        View view = layoutInflater.inflate(R.layout.dialog_choose_sing, null, false);
        setContentView(view);
        initViews(view);
    }

    private void initViews(View view) {
        tvThem=(TextView)view.findViewById(R.id.tv_add_sing_in_playlist);
        tvGui=(TextView)view.findViewById(R.id.tv_send_sing_to);
        tvYeuThich=(TextView)view.findViewById(R.id.tv_add_sing_in_yeu_thich);

        tvThem.setOnClickListener(this);
        tvGui.setOnClickListener(this);
        tvYeuThich.setOnClickListener(this);
    }

    public void setiDialogOfFragment(IDialogOfFragment iDialogOfFragment) {
        this.iDialogOfFragment = iDialogOfFragment;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_add_sing_in_playlist:
                iDialogOfFragment.addIntoPlayList(audioPlayer);
                cancel();
                break;
            case R.id.tv_send_sing_to:
                iDialogOfFragment.sendSing(audioPlayer);
                cancel();
                break;
            case R.id.tv_add_sing_in_yeu_thich:
                iDialogOfFragment.addIntoYeuThich(audioPlayer);
                cancel();
                break;
        }
    }
}

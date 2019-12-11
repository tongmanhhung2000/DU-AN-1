package com.example.anhson.btl_nhac.dialog;

import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.anhson.btl_nhac.IDialogOfFragment;
import com.example.anhson.btl_nhac.R;
import com.example.anhson.btl_nhac.dao.ManagerPlayList;

/**
 * Created by Anh Son on 4/13/2017.
 */

public class NewPlayListDialog extends Dialog implements View.OnClickListener {

    private IDialogOfFragment iDialogOfFragment;
    private EditText editText;
    private Button btButton;
    private Button btButton1;
    private int danhSach=0;

    public void setDanhSach(int danhSach) {
        this.danhSach = danhSach;
        if (editText!=null){
            editText.setText("Danh sách phát nhạc "+danhSach);
        }
    }

    public NewPlayListDialog(Context context) {
        super(context);
        inits();
    }

    public NewPlayListDialog(Context context, int themeResId) {
        super(context, themeResId);
        inits();
    }

    private void inits() {
        LayoutInflater layoutInflater=LayoutInflater.from(getContext());
        View view=layoutInflater.inflate(R.layout.dialog_add_new_playlist,null,false);
        setTitle("Danh Sach Phat");
        setContentView(view);
        initViews(view);
    }

    private void initViews(View view) {
        editText=(EditText)view.findViewById(R.id.ed_name_playlist);
        editText.setText("Danh sách phát nhạc "+danhSach);
        btButton=(Button)view.findViewById(R.id.btn_choose_add);
        btButton1=(Button)view.findViewById(R.id.btn_choose_canel);
        btButton.setOnClickListener(this);
        btButton1.setOnClickListener(this);
    }

    public void setiDialogOfFragment(IDialogOfFragment iDialogOfFragment) {
        this.iDialogOfFragment = iDialogOfFragment;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_choose_add:
                String name=editText.getText().toString();
                if (name.equals("")){
                    return;
                }
                cancel();
                iDialogOfFragment.xuly(name);
                break;
            case R.id.btn_choose_canel:
                cancel();
                break;

        }

    }
}

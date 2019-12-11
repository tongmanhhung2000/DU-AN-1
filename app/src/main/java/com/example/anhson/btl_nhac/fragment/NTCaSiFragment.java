package com.example.anhson.btl_nhac.fragment;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.anhson.btl_nhac.IFragmentMain;
import com.example.anhson.btl_nhac.R;
import com.example.anhson.btl_nhac.adapter.SingerAdapter;
import com.example.anhson.btl_nhac.model.Album;
import com.example.anhson.btl_nhac.model.ManagerMedia;
import com.example.anhson.btl_nhac.model.Singer;

import java.util.List;

/**
 * Created by Anh Son on 4/7/2017.
 */

public class NTCaSiFragment extends BaseFragment implements AdapterView.OnItemClickListener {
    private static final String TAG = NTCaSiFragment.class.getSimpleName();
    private SingerAdapter singerAdapter;
    private ListView listView;
    private List<Singer> singerList;

    public void setSingerList(List<Singer> singerList) {
        this.singerList = singerList;
    }

    @Override
    protected void initsView(View view) {
        listView=(ListView)view.findViewById(R.id.list_ca_si);

        singerAdapter=new SingerAdapter(getContext(),singerList);
        listView.setAdapter(singerAdapter);
        listView.setOnItemClickListener(this);
    }

    @Override
    protected int getViews() {
        return R.layout.fragment_ngoai_tuyen_ca_si;
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getContext(),"click "+position,Toast.LENGTH_SHORT).show();
        iFragmentMain.showMucPhatFragment(singerList.get(position));
    }


}

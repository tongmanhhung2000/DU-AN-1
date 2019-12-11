package com.example.anhson.btl_nhac.fragment;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.anhson.btl_nhac.R;
import com.example.anhson.btl_nhac.adapter.AlbumAdapter;
import com.example.anhson.btl_nhac.model.Album;
import com.example.anhson.btl_nhac.model.ManagerMedia;
import com.example.anhson.btl_nhac.model.Singer;

import java.util.List;

/**
 * Created by Anh Son on 4/7/2017.
 */

public class NTAlbumFragment extends BaseFragment implements AdapterView.OnItemClickListener {
    private GridView gridView;
    private AlbumAdapter albumAdapter;
    private List<Album> albumList;

    public void setAlbumList(List<Album> albumList) {
        this.albumList = albumList;
    }

    @Override
    protected void initsView(View view) {
        Log.d("NTAlbumFragment","initsView");
        gridView=(GridView)view.findViewById(R.id.gridview_album);
        albumAdapter=new AlbumAdapter(getContext(),albumList);
        gridView.setAdapter(albumAdapter);
        gridView.setOnItemClickListener(this);
    }

    @Override
    protected int getViews() {
        return R.layout.fragment_ngoai_tuyen_album;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        iFragmentMain.showMucPhatFragment(albumList.get(position));
    }

}

package com.example.anhson.btl_nhac.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.anhson.btl_nhac.fragment.NTAlbumFragment;
import com.example.anhson.btl_nhac.fragment.NTBaiHatFragment;
import com.example.anhson.btl_nhac.fragment.NTCaSiFragment;
import com.example.anhson.btl_nhac.fragment.NTThuMucFragment;
import com.example.anhson.btl_nhac.model.Album;
import com.example.anhson.btl_nhac.model.Singer;

/**
 * Created by Anh Son on 4/7/2017.
 */

public class NgoaiTuyenAdapter extends FragmentPagerAdapter  {
    private NTAlbumFragment ntAlbumFragment;
    private NTBaiHatFragment ntBaiHatFragment;
    private NTCaSiFragment ntCaSiFragment;
    private NTThuMucFragment ntThuMucFragment;

    public void setNtAlbumFragment(NTAlbumFragment ntAlbumFragment) {
        this.ntAlbumFragment = ntAlbumFragment;
    }

    public void setNtCaSiFragment(NTCaSiFragment ntCaSiFragment) {
        this.ntCaSiFragment = ntCaSiFragment;
    }

    public void setNtThuMucFragment(NTThuMucFragment ntThuMucFragment) {
        this.ntThuMucFragment = ntThuMucFragment;
    }

    public void setNtBaiHatFragment(NTBaiHatFragment ntBaiHatFragment) {
        this.ntBaiHatFragment = ntBaiHatFragment;
    }

    public NgoaiTuyenAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return ntBaiHatFragment;
            case 1:
                return ntCaSiFragment;
            case 2:
                return ntAlbumFragment;
            case 3:
                return ntThuMucFragment;
        }
        return null;
    }
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "Bài hát";
            case 1:
                return "Ca sĩ";
            case 2:
                return "Album";
            case 3:
                return "Thư mục";
        }
        return null;
    }
    @Override
    public int getCount() {
        return 4;
    }

}

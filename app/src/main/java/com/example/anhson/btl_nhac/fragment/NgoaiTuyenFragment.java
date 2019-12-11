package com.example.anhson.btl_nhac.fragment;


import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import com.example.anhson.btl_nhac.IFragmentMain;
import com.example.anhson.btl_nhac.R;
import com.example.anhson.btl_nhac.adapter.NgoaiTuyenAdapter;
import com.example.anhson.btl_nhac.model.Album;
import com.example.anhson.btl_nhac.model.Singer;

/**
 * Created by Anh Son on 4/4/2017.
 */

public class NgoaiTuyenFragment extends BaseFragment {
    private static final String TAG = NgoaiTuyenFragment.class.getSimpleName();
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private NgoaiTuyenAdapter ngoaiTuyenAdapter;
    private NTBaiHatFragment ntBaiHatFragment;
    private NTCaSiFragment ntCaSiFragment;
    private NTThuMucFragment ntThuMucFragment;
    private NTAlbumFragment ntAlbumFragment;

    @Override
    protected void initsView(View view) {
        tabLayout=(TabLayout)view.findViewById(R.id.tablayout_ngoai_tuyen);
        viewPager=(ViewPager)view.findViewById(R.id.viewpager_ngoai_tuyen);
        ngoaiTuyenAdapter=new NgoaiTuyenAdapter(getChildFragmentManager());

        ngoaiTuyenAdapter.setNtBaiHatFragment(ntBaiHatFragment);
        ngoaiTuyenAdapter.setNtCaSiFragment(ntCaSiFragment);
        ngoaiTuyenAdapter.setNtAlbumFragment(ntAlbumFragment);
        ngoaiTuyenAdapter.setNtThuMucFragment(ntThuMucFragment);


        viewPager.setAdapter(ngoaiTuyenAdapter);
        tabLayout.setupWithViewPager(viewPager);

    }

    public void setNtBaiHatFragment(NTBaiHatFragment ntBaiHatFragment) {
        this.ntBaiHatFragment = ntBaiHatFragment;
    }

    public void setNtCaSiFragment(NTCaSiFragment ntCaSiFragment) {
        this.ntCaSiFragment = ntCaSiFragment;
    }

    public void setNtThuMucFragment(NTThuMucFragment ntThuMucFragment) {
        this.ntThuMucFragment = ntThuMucFragment;
    }

    public void setNtAlbumFragment(NTAlbumFragment ntAlbumFragment) {
        this.ntAlbumFragment = ntAlbumFragment;
    }

    @Override
    protected int getViews() {
        return R.layout.fragment_ngoai_tuyen;
    }

    public void setiFragmentMain(IFragmentMain iFragmentMain) {
        this.iFragmentMain = iFragmentMain;
    }

}

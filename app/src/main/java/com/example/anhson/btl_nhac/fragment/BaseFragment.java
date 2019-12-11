package com.example.anhson.btl_nhac.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.anhson.btl_nhac.IFragmentMain;

/**
 * Created by Anh Son on 4/4/2017.
 */

public abstract class BaseFragment extends Fragment{
    private View view;
    protected IFragmentMain iFragmentMain;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(getViews(),container,false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initsView(view);
    }

    public void setiFragmentMain(IFragmentMain iFragmentMain) {
        this.iFragmentMain = iFragmentMain;
    }

    protected abstract void initsView(View view);

    protected abstract int getViews();
}

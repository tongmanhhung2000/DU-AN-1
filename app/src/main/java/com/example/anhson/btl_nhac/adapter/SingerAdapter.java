package com.example.anhson.btl_nhac.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.anhson.btl_nhac.R;
import com.example.anhson.btl_nhac.model.Singer;

import java.util.List;

/**
 * Created by Anh Son on 4/11/2017.
 */

public class SingerAdapter extends BaseAdapter {
    private List<Singer> singers;
    private Context context;

    public SingerAdapter(Context context, List<Singer> singers) {
        this.context = context;
        this.singers = singers;
    }

    @Override
    public int getCount() {
        return singers.size();
    }

    @Override
    public Object getItem(int position) {
        return singers.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        HollerView hollerView;
        if (convertView == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            convertView = layoutInflater.inflate(R.layout.item_casi, parent, false);
            hollerView = new HollerView();
            hollerView.tvNumberOfSing = (TextView) convertView.findViewById(R.id.tv_sing_of_artist);
            hollerView.tvTenCaSi = (TextView) convertView.findViewById(R.id.tv_name_artist);
            convertView.setTag(hollerView);
        } else {
            hollerView = (HollerView) convertView.getTag();
        }
        Singer singer = (Singer) getItem(position);
        hollerView.tvTenCaSi.setText(singer.getName()+"");
        hollerView.tvNumberOfSing.setText(singer.getNumberOfSing()+"");

        return convertView;
    }

    private class HollerView {
        private TextView tvTenCaSi;
        private TextView tvNumberOfSing;
    }
}

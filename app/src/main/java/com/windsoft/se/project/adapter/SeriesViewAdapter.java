package com.windsoft.se.project.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.mattheusbrito.projetoes.R;
import com.windsoft.se.project.series.SeriesMock;
import com.windsoft.se.project.series.SeriesMockObserver;

/**
 * Created by GersonSales on 1/27/2018.
 */

public class SeriesViewAdapter extends BaseAdapter implements SeriesMockObserver {

    private Context mContext;

    public SeriesViewAdapter(Context mContext) {
        this.mContext = mContext;
        SeriesMock.addObserver(this);
    }

    @Override
    public int getCount() {
        return SeriesMock.size();
    }

    @Override
    public Object getItem(int position) {
        return SeriesMock.getByPosition(position);
    }

    @Override
    public long getItemId(int position) {
        return SeriesMock.getByPosition(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(R.layout.view_item_serie_grid, null);
        }

        TextView textView = convertView.findViewById(R.id.series_textView);
        textView.setText(SeriesMock.getByPosition(position).getName());
        return convertView;
    }

    @Override
    public void update() {
        notifyDataSetChanged();
    }
}

package com.windsoft.se.project.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.windsoft.se.project.R;
import com.windsoft.se.project.model.series.SeriesMock;
import com.windsoft.se.project.model.series.SeriesMockObserver;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by GersonSales on 1/27/2018.
 */

public class SeriesViewAdapter extends BaseAdapter implements SeriesMockObserver {

    @BindView(R.id.seriesName_textView)
    TextView seriesName;

    @BindView(R.id.seriesThumbnail_imageView)
    ImageView seriesThumbnail;

    private SeriesMock mSeriesMock;


    public SeriesViewAdapter() {
        mSeriesMock = SeriesMock.getInstance();
        mSeriesMock.addObserver(this);
    }

    @Override
    public int getCount() {
        return mSeriesMock.size();
    }

    @Override
    public Object getItem(int position) {
        return mSeriesMock.getByPosition(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;//mSeriesMock.getByPosition(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            convertView = inflater.inflate(R.layout.view_item_serie_grid, null);
        }

        ButterKnife.bind(this, convertView);

        seriesName.setText(mSeriesMock.getByPosition(position).getName());
        seriesThumbnail.setImageBitmap(mSeriesMock.getByPosition(position).getThumbnail());

        return convertView;
    }

    @Override
    public void update() {
        notifyDataSetChanged();
    }
}

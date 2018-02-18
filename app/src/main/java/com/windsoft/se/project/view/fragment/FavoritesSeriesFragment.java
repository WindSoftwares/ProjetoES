package com.windsoft.se.project.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.windsoft.se.project.adapter.FavoriteSeriesViewAdapter;
import com.windsoft.se.project.adapter.SeriesViewAdapter;


/**
 * Created by GersonSales on 1/27/2018.
 */

public class FavoritesSeriesFragment extends SeriesScreenFragment{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @NonNull
    @Override
    SeriesViewAdapter getAdapter() {
        return new FavoriteSeriesViewAdapter();
    }
}

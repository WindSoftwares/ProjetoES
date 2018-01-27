package com.windsoft.se.project.view;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mattheusbrito.projetoes.R;

/**
 * Created by GersonSales on 1/27/2018.
 */

public class FavoritesSeriesFragment extends Fragment{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorite_series, null);//TODO
        return view;
    }
}

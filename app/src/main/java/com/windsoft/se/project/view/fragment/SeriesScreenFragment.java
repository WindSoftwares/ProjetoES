package com.windsoft.se.project.view.fragment;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.windsoft.se.project.R;
import com.windsoft.se.project.adapter.SeriesViewAdapter;
import com.windsoft.se.project.model.series.Series;
import com.windsoft.se.project.util.StaticFlow;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;

public class SeriesScreenFragment extends Fragment {

    @BindView(R.id.series_gridView)
    GridView seriesGridView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.view_serie_screen, container, false);
        ButterKnife.bind(this, view);
        seriesGridView.setAdapter(getAdapter());

        tempGridBinder();
        return view;
    }

    @NonNull
    SeriesViewAdapter getAdapter() {
        return new SeriesViewAdapter();
    }

    @SuppressLint("ResourceType")
    private void tempGridBinder() {
        seriesGridView.setOnItemClickListener((parent, view, position, id) -> {
            Series series = (Series) seriesGridView.getAdapter().getItem(position);
            StaticFlow.setActualSeries(series);
            getActivity()
                    .getFragmentManager()
                    .beginTransaction()
                    .setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right)
                    .replace(R.id.mainFragment, new SeasonScreenFragment())
                    .commit();
        });
    }


    @OnItemClick(R.id.series_gridView)
    public void onGridItemClick(int position) {
    }

}

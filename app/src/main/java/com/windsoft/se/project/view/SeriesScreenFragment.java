package com.windsoft.se.project.view;

import android.app.Fragment;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;

import com.mattheusbrito.projetoes.R;
import com.windsoft.se.project.adapter.SeriesViewAdapter;
import com.windsoft.se.project.model.series.Series;
import com.windsoft.se.project.model.series.SeriesMock;
import com.windsoft.se.project.model.series.season.Season;
import com.windsoft.se.project.model.series.season.SeasonMock;

import java.util.List;

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
        seriesGridView.setAdapter(new SeriesViewAdapter(view.getContext()));

        tempGridBinder();
        return view;
    }

    private void tempGridBinder() {
        seriesGridView.setOnItemClickListener((parent, view, position, id) -> {
            SeasonScreenFragment fragment = new SeasonScreenFragment();
            Series series = (Series) seriesGridView.getAdapter().getItem(position);
            fragment.setOwner(series);
            getActivity()
                    .getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.mainFragment, fragment)
                    .commit();
        });
    }


    @OnItemClick(R.id.series_gridView)
    public void onGridItemClick(int position) {
        System.out.println("CLICKED");
        SeriesMock.getInstance().getByPosition(position).setName("Clicked");
        SeriesMock.getInstance().notifyAllObservers();
    }

}
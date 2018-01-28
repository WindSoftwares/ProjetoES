package com.windsoft.se.project.view;

import android.app.Fragment;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;

import com.mattheusbrito.projetoes.R;
import com.windsoft.se.project.adapter.SeriesViewAdapter;
import com.windsoft.se.project.model.series.Series;
import com.windsoft.se.project.model.series.SeriesMock;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;

public class SeriesScreenFragment extends Fragment {



    @BindView(R.id.series_gridView)
    GridView seriesGridView;


    private List<Series> mSeries;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.view_serie_screen, container, false);
        ButterKnife.bind(this, view);
        seriesGridView.setAdapter(new SeriesViewAdapter(view.getContext()));

        tempGridBinder();



        Button buttonTest = view.findViewById(R.id.button_test);
        buttonTest.setOnClickListener(listener -> SeriesMock.addSeries(SeriesMock.getNewSeries()));

//        buttonTest.setOnClickListener(listener -> getActivity()
//                .getFragmentManager()
//                .beginTransaction()
//                .replace(R.id.mainFragment, new FavoritesSeriesFragment())
//                .commit());
        return view;
    }

    private void tempGridBinder() {
        seriesGridView.setOnItemClickListener((parent, view1, position, id) -> getActivity()
                .getFragmentManager()
                .beginTransaction()
                .replace(R.id.mainFragment, new SeasonScreenFragment())
                .commit());
    }


    @OnItemClick(R.id.series_gridView)
    public void onGridItemClick(int position) {
        System.out.println("CLICKED");
        SeriesMock.getByPosition(position).setName("Clicked");
        SeriesMock.notifyAllObservers();
    }

}

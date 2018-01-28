package com.windsoft.se.project.view;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mattheusbrito.projetoes.R;
import com.windsoft.se.project.adapter.SeasonViewAdapter;
import com.windsoft.se.project.model.series.season.OnClickSeasonListener;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by GersonSales on 1/27/2018.
 */

public class SeasonScreenFragment extends Fragment {


    @BindView(R.id.seasonView_recyclerView)
    RecyclerView seasonView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_series_season, container, false);

        ButterKnife.bind(this, view);

        seasonView.setAdapter(new SeasonViewAdapter(getOnClickListener()));
        seasonView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        return view;
    }


    private OnClickSeasonListener getOnClickListener() {
        return position -> getActivity().getFragmentManager()
                .beginTransaction()
                .replace(R.id.mainFragment, new QuizFragment())
                .commit();
    }
}

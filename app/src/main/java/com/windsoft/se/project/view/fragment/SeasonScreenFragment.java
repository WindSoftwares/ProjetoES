package com.windsoft.se.project.view.fragment;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.windsoft.se.project.R;
import com.windsoft.se.project.adapter.SeasonViewAdapter;
import com.windsoft.se.project.model.series.Series;
import com.windsoft.se.project.model.series.factory.Difficulty;
import com.windsoft.se.project.model.series.factory.QuizFactory;
import com.windsoft.se.project.model.series.season.OnClickSeasonListener;
import com.windsoft.se.project.model.series.season.Season;
import com.windsoft.se.project.model.series.season.SeasonMock;
import com.windsoft.se.project.quiz.Quiz;
import com.windsoft.se.project.util.StaticFlow;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by GersonSales on 1/27/2018.
 */

public class SeasonScreenFragment extends Fragment {


    @BindView(R.id.serieSeason_textView)
    TextView seasonsOwner;

    @BindView(R.id.seasonView_recyclerView)
    RecyclerView seasonView;

    public SeasonScreenFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_series_season, container, false);

        ButterKnife.bind(this, view);
        bindView(view);

        return view;
    }

    private void bindView(View view) {
        seasonView.setAdapter(new SeasonViewAdapter(getOnClickListener()));
        seasonView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        seasonsOwner.setText(StaticFlow.getActualSeries().getName());
    }


    @SuppressLint("ResourceType")
    private OnClickSeasonListener getOnClickListener() {
        return position -> {
            StaticFlow.setActualSeason(StaticFlow.getActualSeries().getSeasonByPosition(position));
            getActivity().getFragmentManager()
                .beginTransaction()
                .setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right)
                .replace(R.id.mainFragment, new QuizFragment())
                .commit();};
    }

}
package com.windsoft.se.project.view.fragment;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
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
import com.windsoft.se.project.model.series.season.OnClickSeasonListener;
import com.windsoft.se.project.model.series.season.Season;
import com.windsoft.se.project.util.StaticFlow;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by GersonSales on 1/27/2018.
 */

public class SeasonScreenFragment extends Fragment {


    @BindView(R.id.seasonView_recyclerView)
    RecyclerView seasonView;

    public SeasonScreenFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_series_season, container, false);
        getActivity().setTitle(StaticFlow.getActualSeries().getName());


        ButterKnife.bind(this, view);
        bindView(view);

        return view;
    }

    private void bindView(View view) {
        seasonView.setAdapter(new SeasonViewAdapter(getOnClickListener()));
        seasonView.setLayoutManager(new LinearLayoutManager(view.getContext()));
    }


    private OnClickSeasonListener getOnClickListener() {
        return position -> {

            Season actualSeason = StaticFlow.getActualSeries().getSeasonByPosition(position);
            StaticFlow.setActualSeason(actualSeason);

            if (actualSeason.getAnsweredCount() == actualSeason.getQuestionsCount()) {
                new AlertDialog.Builder(SeasonScreenFragment.this.getContext())
                        .setMessage(R.string.wants_to_reset_the_quiz)
                        .setPositiveButton(R.string.yes, (dialog, which) -> {
                            StaticFlow.getActualQuiz().reset();
                            goToQuizScreen();
                        })
                        .setNegativeButton(R.string.no, (dialog, which) -> {})
                        .show();
            }else {
                goToQuizScreen();
            }




        };
    }

    @SuppressLint("ResourceType")
    private void goToQuizScreen() {
        getActivity().getFragmentManager()
                .beginTransaction()
                .setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right)
                .replace(R.id.mainFragment, new QuizFragment())
                .commitAllowingStateLoss();
    }

}

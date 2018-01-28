package com.windsoft.se.project.view;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mattheusbrito.projetoes.R;
import com.windsoft.se.project.adapter.SeasonViewAdapter;
import com.windsoft.se.project.model.series.Series;
import com.windsoft.se.project.model.series.season.OnClickSeasonListener;
import com.windsoft.se.project.model.series.season.Season;

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

    private Series mOwner;

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

        if (mOwner != null) {
            seasonsOwner.setText(mOwner.getName());
        }

    }


    private OnClickSeasonListener getOnClickListener() {
        return position -> getActivity().getFragmentManager()
                .beginTransaction()
                .remove(this)
                .replace(R.id.mainFragment, new QuizFragment())
                .commit();
    }

    public void setOwner(Series owner) {
        mOwner = owner;
    }

}

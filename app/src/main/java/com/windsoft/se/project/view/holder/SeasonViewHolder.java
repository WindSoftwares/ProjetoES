package com.windsoft.se.project.view.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.windsoft.se.project.R;
import com.windsoft.se.project.model.series.season.Season;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by GersonSales on 1/27/2018.
 */

public class SeasonViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    @BindView(R.id.seasonName_textView)
    TextView seasonName;

    @BindView(R.id.seasonScore_textView)
    TextView seasonScore;


    public SeasonViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }


    public void bind(Season season) {
        seasonName.setText(season.getName());
        int answeredQuestionsCount = season.getAnsweredCount();
        int totalQuestionsCount = season.getQuestionsCount();
        String result = String.valueOf(answeredQuestionsCount)
                .concat("/")
                .concat(String.valueOf(totalQuestionsCount));
        seasonScore.setText(result);

    }

    @Override
    public void onClick(View view) {
        System.out.println("clicked");

    }
}

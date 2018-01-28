package com.windsoft.se.project.view.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.mattheusbrito.projetoes.R;
import com.windsoft.se.project.model.series.season.OnClickSeasonListener;
import com.windsoft.se.project.model.series.season.Season;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by GersonSales on 1/27/2018.
 */

public class SeasonViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    @BindView(R.id.seasonName_textView)
    TextView seasonName;


    public SeasonViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }


    public void bind(Season season) {
        seasonName.setText(season.getName());

    }

    @Override
    public void onClick(View view) {
        System.out.println("clicked");

    }
}

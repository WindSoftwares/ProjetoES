package com.windsoft.se.project.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.windsoft.se.project.R;
import com.windsoft.se.project.model.series.season.OnClickSeasonListener;
import com.windsoft.se.project.model.series.season.SeasonMock;
import com.windsoft.se.project.view.holder.SeasonViewHolder;

/**
 * Created by GersonSales on 1/27/2018.
 */

public class SeasonViewAdapter extends RecyclerView.Adapter<SeasonViewHolder> {

    private OnClickSeasonListener mListener;
    public SeasonViewAdapter(OnClickSeasonListener listener) {
        mListener = listener;
    }

    @Override
    public SeasonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View seasonRowView = inflater.inflate(R.layout.row_list_season, parent, false);

        return new SeasonViewHolder(seasonRowView);
    }

    @Override
    public void onBindViewHolder(SeasonViewHolder holder, int position) {
        holder.bind(SeasonMock.getInstance().getSeasonByPosition(position));
        holder.itemView.setOnClickListener(v -> mListener.OnClick(position));


    }

    @Override
    public int getItemCount() {
        return SeasonMock.size();
    }
}

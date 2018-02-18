package com.windsoft.se.project.adapter;

import com.windsoft.se.project.model.series.FavoriteSeriesMock;
import com.windsoft.se.project.model.series.SeriesMock;

/**
 * Created by GersonSales on 2/11/2018.
 */

public class FavoriteSeriesViewAdapter extends SeriesViewAdapter {

    public FavoriteSeriesViewAdapter() {
        super(FavoriteSeriesMock.getInstance());
    }
}

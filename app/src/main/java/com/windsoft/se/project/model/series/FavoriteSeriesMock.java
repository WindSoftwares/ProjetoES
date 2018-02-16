package com.windsoft.se.project.model.series;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by GersonSales on 2/11/2018.
 */

public class FavoriteSeriesMock extends SeriesMock {
    private static FavoriteSeriesMock instance;
    private  ArrayList<Series> newMSeries;



    public static FavoriteSeriesMock getInstance() {
        if (instance == null) {
            instance = new FavoriteSeriesMock();
        }
        return instance;
    }


    private FavoriteSeriesMock() {
        super(SeriesMock.getInstance().getAllSeries());
        newMSeries = new ArrayList<>();
        if (mSeries != null) {
            mSeries.forEach(seriesItem -> {
                if (seriesItem.isFavorite()) {
                    newMSeries.add(seriesItem);
                }
            });
        }
        mSeries = newMSeries;
    }


    @Override
    List<Series> getSeriesDataBase() {
        return newMSeries;
    }
}

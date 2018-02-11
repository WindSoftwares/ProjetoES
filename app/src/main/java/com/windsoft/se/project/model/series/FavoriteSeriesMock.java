package com.windsoft.se.project.model.series;

import com.google.firebase.FirebaseApp;
import com.windsoft.se.project.model.series.factory.SeriesFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by GersonSales on 2/11/2018.
 */

public class FavoriteSeriesMock extends SeriesMock {
    private static FavoriteSeriesMock instance;

    public static FavoriteSeriesMock getInstance() {
        if (instance == null) {
            instance = new FavoriteSeriesMock();
        }
        return instance;
    }


    private FavoriteSeriesMock() {
        super(SeriesMock.getInstance().getAllSeries());
        List<Series> newMSeries = new ArrayList<>();
        if (mSeries != null) {
            mSeries.forEach(seriesItem -> {
                if (seriesItem.isFavorite()) {
                    newMSeries.add(seriesItem);
                }
            });
        }
        System.out.println(newMSeries);

        mSeries = newMSeries;
    }
}
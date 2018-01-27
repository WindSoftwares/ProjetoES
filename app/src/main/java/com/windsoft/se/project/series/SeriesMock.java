package com.windsoft.se.project.series;

import android.os.Build;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by GersonSales on 1/27/2018.
 */

public class SeriesMock {
    private static int count;

    private static List<Series> mSeries = new ArrayList<>();
    private static Set<SeriesMockObserver> mObservers = new HashSet<>();
    private static Set<Series> seriesTree;

    public static Series getNewSeries() {
        return new Series("Series Name" + count ++ , null, null);
    }

    public static void addSeries(Series series) {
        mSeries.add(series);
        notifyAllObservers();
    }

    public static Series getByPosition(int position) {
        return mSeries.get(position);
    }

    public static List<Series> getSeriesList() {
        return mSeries;
    }

    public static void addObserver(SeriesMockObserver observer) {
        mObservers.add(observer);
    }



    public static void notifyAllObservers() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            mObservers.iterator().forEachRemaining(SeriesMockObserver::update);
        }

    }

    public static int size() {
        return mSeries.size();
    }
}

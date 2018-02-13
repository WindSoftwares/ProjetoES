package com.windsoft.se.project.model.series;

import android.os.Build;

import com.windsoft.se.project.model.series.factory.SeriesFactory;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by GersonSales on 1/27/2018.
 */

public class SeriesMock {

    private static SeriesMock instance;

    private  int count;
    private  Set<SeriesMockObserver> mObservers = new HashSet<>();
    List<Series> mSeries = new ArrayList<>();



    private SeriesMock() {
        List<Series> series = SeriesFactory.getInstance().getSeriesList();
        mSeries = new ArrayList<>();
        if (series != null) {
            mSeries.addAll(series);
        }
    }

    SeriesMock(List<Series> series) {
        mSeries = new ArrayList<>();
        if (series != null) {
            mSeries.addAll(series);
        }
    }

    static public SeriesMock getInstance() {
        if (instance == null) {
            instance = new SeriesMock();
        }
        return instance;
    }

    public  Series getNewSeries() {
        return new Series("Series Name" + count ++ , null, null);
    }

    public  void addSeries(Series series) {
        mSeries.add(series);
        notifyAllObservers();
    }

    public  Series getByPosition(int position) {
        return mSeries.get(position);
    }

    public  void addObserver(SeriesMockObserver observer) {
        mObservers.add(observer);
    }



    private void notifyAllObservers() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            mObservers.iterator().forEachRemaining(SeriesMockObserver::update);
        }
    }

    public  int size() {
        return mSeries.size();
    }

    public List<Series> getAllSeries() {
        return mSeries;
    }
}



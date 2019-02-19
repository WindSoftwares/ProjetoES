package com.windsoft.se.project.util.factory;

import android.graphics.Bitmap;

import com.google.firebase.database.DataSnapshot;
import com.windsoft.se.project.model.series.Series;
import com.windsoft.se.project.model.series.season.Season;
import com.windsoft.se.project.util.Constant;
import com.windsoft.se.project.util.FileUtil;
import com.windsoft.se.project.util.MediaUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.windsoft.se.project.util.Constant.FAVORITE;
import static com.windsoft.se.project.util.Constant.SEASONS;
import static com.windsoft.se.project.util.Constant.SERIES;
import static com.windsoft.se.project.util.Constant.THUMBNAIL;
import static com.windsoft.se.project.util.Constant.TRUE;

/**
 * Created by GersonSales on 2/8/2018.
 */

public class SeriesFactory {

    private static SeriesFactory instance;
    private DataSnapshot mSeriesSnapshot;
    List<Series> mSeries;
    private List<Series> favoriteSeriesList;

    public synchronized  static SeriesFactory getInstance() {
        if(instance == null) {
            instance = new SeriesFactory();
        }
        return instance;
    }

    private SeriesFactory() {
    }

    public List<Series> getSeriesList() {
        return mSeries;
    }

    private Bitmap getBitmap(String seriesName, String thumbnailLink) {
        Bitmap thumbnail = FileUtil.getSeriesThumbnail(seriesName);
        if (thumbnail != null) {
            return thumbnail;
        }
        thumbnail = MediaUtil.getBitmapFromURL2(thumbnailLink);
        FileUtil.persistBitmap(seriesName, thumbnail);
        return thumbnail;
    }

    private ArrayList<Season> getSeasonListFrom(DataSnapshot seriesSnapshot) {
        ArrayList<Season> result = new ArrayList<>();
        if (seriesSnapshot.hasChild(SEASONS)) {
            result.addAll(SeasonFactory.getInstance()
                    .getSeasonListFrom(seriesSnapshot.child(SEASONS)));
        }
        return result;
    }

    public void setSeriesSnapshot(DataSnapshot seriesDatabase) {
        mSeriesSnapshot = seriesDatabase;
        loadSeries();
    }

    public void loadSeries() {
        if (mSeries == null) {
            mSeries = new ArrayList<>();
        }

        if (mSeriesSnapshot == null) return;

        for (DataSnapshot seriesSnapshot : mSeriesSnapshot.getChildren()) {

            String seriesName = seriesSnapshot.getKey();
            String thumbnailLink = seriesSnapshot.child(THUMBNAIL).getValue().toString();
            Bitmap seriesThumbnail = getBitmap(seriesName, thumbnailLink);
            boolean isFavorite = seriesSnapshot.child(FAVORITE).getValue().toString().equals(TRUE);
            ArrayList<Season> seasons = getSeasonListFrom(seriesSnapshot);
            Series series = new Series(seriesName, seriesThumbnail, seasons, isFavorite);//TODO
            mSeries.add(series);
        }



    }
}

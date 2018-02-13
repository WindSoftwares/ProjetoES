package com.windsoft.se.project.model.series.factory;

import android.graphics.Bitmap;
import android.os.Build;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.windsoft.se.project.model.series.Series;
import com.windsoft.se.project.model.series.season.Season;
import com.windsoft.se.project.util.Constant;
import com.windsoft.se.project.util.MediaUtil;

import java.util.ArrayList;
import java.util.List;

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
        if (mSeriesSnapshot == null) return null;
        List<Series> seriesList = new ArrayList<>();
        for (DataSnapshot seriesSnapshot : mSeriesSnapshot.getChildren()) {
            String seriesName = seriesSnapshot.getKey();
            String thumbnailLink = seriesSnapshot.child(THUMBNAIL).getValue().toString();
            Bitmap seriesThumbnail = MediaUtil.getBitmapFromURL2(thumbnailLink);
            boolean isFavorite = seriesSnapshot.child(FAVORITE).getValue().toString().equals(TRUE);
            ArrayList<Season> seasons = getSeasonListFrom(seriesSnapshot);
            Series series = new Series(seriesName, seriesThumbnail, seasons, isFavorite);//TODO
            seriesList.add(series);
        }
        return seriesList;
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
    }

}

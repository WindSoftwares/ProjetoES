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
import com.windsoft.se.project.util.MediaUtil;

import java.util.ArrayList;
import java.util.List;

import static com.windsoft.se.project.util.Constant.SERIES;

/**
 * Created by GersonSales on 2/8/2018.
 */

public class SeriesFactory {

    private static SeriesFactory instance;
    private final FirebaseDatabase mDatabase;
    private final DatabaseReference mReference;
    private DataSnapshot mSeriesSnapshot;
    List<Series> mSeries;

    public static SeriesFactory getInstance() {
        if(instance == null) {
            instance = new SeriesFactory();
        }
        return instance;
    }

    private SeriesFactory() {
        mDatabase = FirebaseDatabase.getInstance();
        mReference = mDatabase.getReference();
        mSeries = new ArrayList<>();
    }

    public List<Series> getSeries() {
        mReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot != null && dataSnapshot.hasChild(SERIES)) {
                    mSeries = getSeriesList(dataSnapshot.child(SERIES));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return mSeries;
    }


    private List<Series> getSeriesList(DataSnapshot seriesSnapshots) {
        List<Series> seriesList = new ArrayList<>();
        for (DataSnapshot seriesSnapshot : seriesSnapshots.getChildren()) {
            String seriesName = seriesSnapshot.getKey();
            String thumbnailLink = seriesSnapshot.child("thumbnail").getValue().toString();
            Bitmap seriesThumbnail = MediaUtil.getBitmapFromURL(thumbnailLink);
            ArrayList<Season> seasons = SeasonFactory.getInstance().getSeasonListFrom(seriesSnapshot.child("seasons"));
            Series series = new Series(seriesName, seriesThumbnail, seasons);//TODO
            seriesList.add(series);
            System.out.println(series);
        }
        return seriesList;
    }

}

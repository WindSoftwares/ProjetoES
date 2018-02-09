package com.windsoft.se.project.model.series.factory;

import android.graphics.Bitmap;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.windsoft.se.project.model.series.Series;
import com.windsoft.se.project.model.series.season.Season;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static com.windsoft.se.project.util.Constant.SERIES;

/**
 * Created by GersonSales on 2/8/2018.
 */

public class SeriesFactory {

    public static SeriesFactory instance;
    private DataSnapshot mSeriesSnapshot;

    public static SeriesFactory getInstance() {
        if(instance == null) {
            instance = new SeriesFactory();
        }
        return instance;
    }

    private SeriesFactory() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference();
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot != null && dataSnapshot.hasChild(SERIES)) {
                    mSeriesSnapshot = dataSnapshot.child(SERIES);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public Set<Series> getSeriesSet() {
        Set<Series> result = new HashSet<>();
        for (DataSnapshot seriesSnapshot : mSeriesSnapshot.getChildren()) {
            String seriesName = seriesSnapshot.getKey();
            Bitmap seriesThumbnail = null;
            ArrayList<Season> seriesSeason = SeasonFactory.getInstance(mSeriesSnapshot).getSeasonList();
            Series series = new Series(seriesSnapshot.getKey(), null, null);//TODO
            result.add(series);
        }
        return result;
    }

}

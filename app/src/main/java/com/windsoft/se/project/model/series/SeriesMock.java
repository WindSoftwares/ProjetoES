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
    private  List<Series> mSeries = new ArrayList<>();
    private  Set<SeriesMockObserver> mObservers = new HashSet<>();
    private  Set<Series> seriesTree;

    private SeriesMock() {
        for (int i = 0; i < 10; i++) {
            mSeries.add(getNewSeries());
        }
    }

    static synchronized public SeriesMock getInstance() {
        if (instance == null) {
            instance = new SeriesMock();
            instance.populate();
        }
        return instance;
    }

    private void populate() {


        SeriesFactory.getInstance().getSeries();

//        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        DatabaseReference reference = database.getReference();
//        reference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//
//                DataSnapshot series = dataSnapshot.child("series");
//                for (DataSnapshot serie : series.getChildren()) {
//                    System.out.println("Series name :" + serie.getKey());
//                    for (DataSnapshot season : serie.getChildren()) {
//                        System.out.println("Season name: " + season.getKey());
//                        for (DataSnapshot question : season.getChildren()) {
//                            System.out.println("Question text: " + question.getKey());
//                            for (DataSnapshot answerType : question.getChildren()) {
//                                System.out.println("Answer type: " + answerType.getKey());
//                                for (DataSnapshot answer : answerType.getChildren()) {
//                                    System.out.println("Answer: " + answer.getValue());
//                                }
//                            }
//                        }
//                    }
//
//                }
//
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
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

    public  List<Series> getSeriesList() {
        return mSeries;
    }

    public  void addObserver(SeriesMockObserver observer) {
        mObservers.add(observer);
    }



    public  void notifyAllObservers() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            mObservers.iterator().forEachRemaining(SeriesMockObserver::update);
        }
    }

    public  int size() {
        return mSeries.size();
    }
}

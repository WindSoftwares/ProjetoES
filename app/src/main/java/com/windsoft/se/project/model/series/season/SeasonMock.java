package com.windsoft.se.project.model.series.season;

/**
 * Created by GersonSales on 1/27/2018.
 */

public class SeasonMock {
    private static SeasonMock instance;

    synchronized public static SeasonMock getInstance() {
        if (instance == null) {
            instance = new SeasonMock();
        }
        return instance;
    }


    public Season getSeasonByPosition(int position) {
        return new Season("Name", 1, null);
    }

    public static int size() {
        return 20;
    }
}

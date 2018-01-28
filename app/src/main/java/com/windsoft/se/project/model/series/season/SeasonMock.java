package com.windsoft.se.project.model.series.season;

/**
 * Created by GersonSales on 1/27/2018.
 */

public class SeasonMock {
    public static Season getSeasonByPosition(int position) {
        return new Season("Name", 1);
    }

    public static int size() {
        return 20;
    }
}

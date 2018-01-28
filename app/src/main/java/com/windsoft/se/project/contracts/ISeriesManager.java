package com.windsoft.se.project.contracts;

import com.windsoft.se.project.model.series.Series;

import java.util.Set;

/**
 * Created by gersonsales on 07/11/17.
 */

public interface ISeriesManager {

    public Series getSerie(String seriesName);
    public Set<Series> getSeries();
    public Set<Series> getStarredSeries();
    public void starASerie(String seriesName);
    public void unstarASerie(String seriesName);
    public String getSerieInformation(String seriesName);
}

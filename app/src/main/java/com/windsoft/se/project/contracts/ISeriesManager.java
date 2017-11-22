package com.windsoft.se.project.contracts;

import com.windsoft.se.project.series.Serie;

import java.util.Set;

/**
 * Created by gersonsales on 07/11/17.
 */

public interface ISeriesManager {

    public Serie getSerie(String seriesName);
    public Set<Serie> getSeries();
    public Set<Serie> getStarredSeries();
    public void starASerie(String seriesName);
    public void unstarASerie(String seriesName);
    public String getSerieInformation(String seriesName);
}

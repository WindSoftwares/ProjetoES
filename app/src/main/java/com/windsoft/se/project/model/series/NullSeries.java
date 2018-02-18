package com.windsoft.se.project.model.series;

import android.graphics.Bitmap;

import com.windsoft.se.project.model.series.season.Season;
import com.windsoft.se.project.util.MediaUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by GersonSales on 2/10/2018.
 */

public class NullSeries extends Series {
    public NullSeries() {
        super("NullSeries", null, new ArrayList<>());
    }
}

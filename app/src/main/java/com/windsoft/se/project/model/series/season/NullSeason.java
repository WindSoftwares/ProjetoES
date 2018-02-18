package com.windsoft.se.project.model.series.season;

import com.windsoft.se.project.model.quiz.Question;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by GersonSales on 2/10/2018.
 */

public class NullSeason extends Season {

    public NullSeason() {
        super("Null Season", -1, new ArrayList<>());
    }
}

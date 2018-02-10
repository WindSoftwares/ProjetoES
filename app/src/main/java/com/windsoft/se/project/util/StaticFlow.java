package com.windsoft.se.project.util;

import com.windsoft.se.project.model.series.NullSeries;
import com.windsoft.se.project.model.series.Series;
import com.windsoft.se.project.model.series.factory.Difficulty;
import com.windsoft.se.project.model.series.factory.QuizFactory;
import com.windsoft.se.project.model.series.season.NullSeason;
import com.windsoft.se.project.model.series.season.Season;
import com.windsoft.se.project.quiz.NullQuiz;
import com.windsoft.se.project.quiz.Quiz;

/**
 * Created by GersonSales on 2/10/2018.
 */

public class StaticFlow {
    private static Series mActualSeries;
    private static Season mActualSeason;
    private static Quiz mActualQuiz;


    public static Series getActualSeries() {
        if (mActualSeries == null) {
            mActualSeries = new NullSeries();
        }
        return mActualSeries;
    }

    public static void setActualSeries(Series series) {
        StaticFlow.mActualSeries = series;
    }

    public static Season getActualSeason() {
        if (mActualSeason == null) {
            mActualSeason = new NullSeason();
        }
        return mActualSeason;
    }

    public static void setActualSeason(Season actualSeason) {
        mActualSeason = actualSeason;
        StaticFlow.setActualQuiz(QuizFactory.getInstance().createQuiz(Difficulty.EASY)); // TODO
    }

    public static Quiz getActualQuiz() {
        if (mActualQuiz == null) {
            mActualQuiz = new NullQuiz();
        }
        return mActualQuiz;
    }

    private static void setActualQuiz(Quiz quiz) {
        mActualQuiz = quiz;
    }
}
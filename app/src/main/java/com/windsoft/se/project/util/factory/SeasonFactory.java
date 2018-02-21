package com.windsoft.se.project.util.factory;

import com.google.firebase.database.DataSnapshot;
import com.windsoft.se.project.model.quiz.Question;
import com.windsoft.se.project.model.series.season.Season;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by GersonSales on 2/9/2018.
 */

class SeasonFactory {
    private static SeasonFactory instance;

    static SeasonFactory getInstance() {
        if (instance == null) {
            instance = new SeasonFactory();
        }
        return instance;
    }

    private SeasonFactory() {
    }

    ArrayList<Season> getSeasonListFrom(DataSnapshot seriesSnapshot) {
        ArrayList<Season> seasons = new ArrayList<>();
        for (DataSnapshot seasonSnapshot : seriesSnapshot.getChildren()) {
            String seasonName = seasonSnapshot.getKey();
            List<Question> questions = QuestionFactory.getInstance().getQuestionsListFrom(seasonSnapshot);
            Season season = new Season(seasonName, 1, questions);
            seasons.add(season);
        }
        return seasons;
    }
}

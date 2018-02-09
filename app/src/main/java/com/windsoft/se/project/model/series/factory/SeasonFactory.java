package com.windsoft.se.project.model.series.factory;

import com.google.firebase.database.DataSnapshot;
import com.windsoft.se.project.model.quiz.Question;
import com.windsoft.se.project.model.series.season.Season;

import java.util.ArrayList;
import java.util.List;

import static com.windsoft.se.project.util.Constant.SEASON;

/**
 * Created by GersonSales on 2/9/2018.
 */

class SeasonFactory {
    private static SeasonFactory instance;
    private DataSnapshot mSeasonSnapshot;

    static SeasonFactory getInstance(DataSnapshot seriesSnapshot) {
        if (instance == null) {
            instance = new SeasonFactory(seriesSnapshot);
        }
        return instance;
    }

    private SeasonFactory(DataSnapshot seriesSnapshot) {
        if (seriesSnapshot != null && seriesSnapshot.hasChild(SEASON)) {
            mSeasonSnapshot = seriesSnapshot.child(SEASON);
        }
    }

    ArrayList<Season> getSeasonList() {
        ArrayList<Season> seasons = new ArrayList<>();
        for (DataSnapshot seasonSnapshot : mSeasonSnapshot.getChildren()) {
            String seasonName = seasonSnapshot.getKey();
            List<Question> questions = QuestionFactory.getInstance(mSeasonSnapshot).getQuestionsListOf();
            Season season = new Season(seasonName, 1, questions);
        }
        return seasons;
    }
}

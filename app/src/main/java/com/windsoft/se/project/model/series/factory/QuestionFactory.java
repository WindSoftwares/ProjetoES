package com.windsoft.se.project.model.series.factory;

import com.google.firebase.database.DataSnapshot;
import com.windsoft.se.project.model.quiz.Question;
import com.windsoft.se.project.model.series.Answer;

import java.util.ArrayList;
import java.util.List;

import static com.windsoft.se.project.util.Constant.ANSWER;
import static com.windsoft.se.project.util.Constant.DIFFICULTY;

/**
 * Created by GersonSales on 2/9/2018.
 */

class QuestionFactory {
    public static QuestionFactory instance;

    static QuestionFactory getInstance() {
        if (instance == null) {
            instance = new QuestionFactory();;
        }
        return instance;
    }

    private QuestionFactory() {
    }

    List<Question> getQuestionsListFrom(DataSnapshot seasonSnapshot) {
        List<Question> result = new ArrayList<>();
        for (DataSnapshot questionSnapshot : seasonSnapshot.getChildren()) {
            String questionDescription = questionSnapshot.getKey();
            List<Answer> answers = AnswerFactory.getInstance().getAnswerListFrom(questionSnapshot.child(ANSWER));
            Difficulty difficulty = Difficulty.valueOf(questionSnapshot.child(DIFFICULTY).getValue().toString());
            Question question = new Question(questionDescription, difficulty, answers);
        }


        return result;
    }
}

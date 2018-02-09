package com.windsoft.se.project.model.series.factory;

import com.google.firebase.database.DataSnapshot;
import com.windsoft.se.project.model.quiz.Question;
import com.windsoft.se.project.model.series.Answer;
import com.windsoft.se.project.util.Constant;

import java.util.ArrayList;
import java.util.List;

import static com.windsoft.se.project.util.Constant.QUESTION;

/**
 * Created by GersonSales on 2/9/2018.
 */

class QuestionFactory {
    public static QuestionFactory instance;
    private DataSnapshot mQuestionSnapshot;

    public static QuestionFactory getInstance(DataSnapshot seasonSnapshot) {
        if (instance == null) {
            instance = new QuestionFactory(seasonSnapshot);;
        }
        return instance;
    }

    private QuestionFactory(DataSnapshot seasonSnapshot) {
        if (seasonSnapshot != null && seasonSnapshot.hasChild(QUESTION)) {
            mQuestionSnapshot = seasonSnapshot.child(QUESTION);
        }


    }

    public List<Question> getQuestionsListOf() {
        List<Question> result = new ArrayList<>();
        for (DataSnapshot questionSnapshot : mQuestionSnapshot.getChildren()) {
            String questionDescription = questionSnapshot.getKey();
            List<Answer> answers = AnswerFactory.getInstance(mQuestionSnapshot).getAnswerList();
            Question question = new Question(questionDescription, null, answers);
        }


        return result;
    }
}

package com.windsoft.se.project.model.series.factory;

import android.view.inputmethod.CorrectionInfo;

import com.google.firebase.database.DataSnapshot;
import com.windsoft.se.project.model.series.Answer;
import com.windsoft.se.project.model.series.Correctness;
import com.windsoft.se.project.util.Constant;

import java.util.ArrayList;
import java.util.List;

import static com.windsoft.se.project.util.Constant.ANSWER;

/**
 * Created by GersonSales on 2/9/2018.
 */

class AnswerFactory {
    private static AnswerFactory instance;

    private AnswerFactory() {
    }

    static AnswerFactory getInstance() {
        if (instance == null) {
            instance = new AnswerFactory();
        }
        return instance;
    }

    List<Answer> getAnswerListFrom(DataSnapshot questionSnapshot) {
        List<Answer> answers = new ArrayList<>();
        for (DataSnapshot answerSnapshot : questionSnapshot.getChildren()) {
            String answerText = answerSnapshot.getKey();
            Correctness correctness = Correctness.valueOf(answerSnapshot.getValue().toString());
            Answer answer = new Answer(answerText, correctness);
            answers.add(answer);
        }
        return answers;
    }

}

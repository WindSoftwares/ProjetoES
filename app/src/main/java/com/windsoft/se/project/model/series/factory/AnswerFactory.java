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
    private final DataSnapshot mAnswerSnapshot;

    private AnswerFactory(DataSnapshot questionSnapshot) {
        mAnswerSnapshot = questionSnapshot.child(ANSWER);
    }

    static AnswerFactory getInstance(DataSnapshot questionSnapshot) {
        if (instance == null) {
            instance = new AnswerFactory(questionSnapshot);
        }
        return instance;
    }

    List<Answer> getAnswerList() {
        List<Answer> answers = new ArrayList<>();
        for (DataSnapshot answerSnapshot : mAnswerSnapshot.getChildren()) {
            String answerText = answerSnapshot.getKey();
            Correctness correctness = Correctness.valueOf((""));
            Answer answer = new Answer(answerText, correctness);
            answers.add(answer);
        }
        return answers;
    }

}

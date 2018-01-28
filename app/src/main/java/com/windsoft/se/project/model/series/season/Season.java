package com.windsoft.se.project.model.series.season;

import com.windsoft.se.project.model.quiz.QuestioMock;
import com.windsoft.se.project.model.quiz.Question;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by GersonSales on 1/27/2018.
 */

public class Season {
    private String mName;
    private Integer mNumber;
    private List<Question> mQuestions;

    Season(String name, Integer number) {
        mName = name;
        mNumber = number;
        mQuestions = new ArrayList<>();

        mQuestions.add(QuestioMock.getNext());
        mQuestions.add(QuestioMock.getNext());
        mQuestions.add(QuestioMock.getNext());
        mQuestions.add(QuestioMock.getNext());
    }


    public Question getNextQuestion() {
        Question result = null;
        if (!mQuestions.isEmpty()) {
            result = mQuestions.get(0);
            mQuestions.remove(result);
            mQuestions.add(result);
        }
        return result;
    }


    public String getName() {
        return mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public Integer getNumber() {
        return mNumber;
    }

    public void setNumber(Integer number) {
        this.mNumber = number;
    }
}

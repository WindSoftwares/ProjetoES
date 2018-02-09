package com.windsoft.se.project.model.series;

/**
 * Created by GersonSales on 2/9/2018.
 */

public class Answer {

    private String mText;
    private Correctness mCorrectness;

    public Answer(String text, Correctness correctness) {
        mText = text;
        mCorrectness = correctness;
    }


    public boolean isCorrect() {
        return mCorrectness == Correctness.CORRECT;
    }
}

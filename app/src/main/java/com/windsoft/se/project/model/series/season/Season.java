package com.windsoft.se.project.model.series.season;

import android.support.annotation.NonNull;

import com.windsoft.se.project.model.quiz.Question;

import java.util.Iterator;
import java.util.List;

/**
 * Created by GersonSales on 1/27/2018.
 */

public class Season implements Iterable<Question>{
    private String mName;
    private Integer mNumber;

    private List<Question> mQuestions;

    public Season(String name, Integer number, List<Question> questions) {
        mName = name;
        mNumber = number;
        mQuestions = questions;
    }


    public Question getNextQuestion() {
        Question result = mQuestions.iterator().next();
        mQuestions.remove(result);
        return result;
//
//
//        Question result = null;
//        if (!mQuestions.isEmpty()) {
////            result = mQuestions.get(0);
//            mQuestions.remove(result);
//            mQuestions.add(result);
//        }
//        return result;
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


    public boolean hasNext() {
        return mQuestions.iterator().hasNext();
    }

    @NonNull
    @Override
    public Iterator<Question> iterator() {
        return mQuestions.iterator();
    }

    public int size() {
        return mQuestions.size();
    }

    @Override
    public String toString() {
        return "Season{" +
                "mName='" + mName + '\'' +
                ", mNumber=" + mNumber +
                ", mQuestions=" + mQuestions +
                '}';
    }

    public List<Question> getQuestions() {
        return mQuestions;
    }

    public int getAnsweredCount() {
        final int[] count = {0};
        mQuestions.forEach(question -> count[0] = question.isCorrectAnswered() ? count[0] + 1 : count[0]);
        return count[0];
    }

    public int getQuestionsCount() {
        return mQuestions.size();
    }
}

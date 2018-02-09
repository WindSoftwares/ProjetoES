package com.windsoft.se.project.model.series.season;

import android.support.annotation.NonNull;

import com.windsoft.se.project.model.quiz.QuestioMock;
import com.windsoft.se.project.model.quiz.Question;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by GersonSales on 1/27/2018.
 */

public class Season implements Iterable<Question>{
    private String mName;
    private Integer mNumber;

    private List<Question> mQuestions;
    private List<Question> mBackup;

    public Season(String name, Integer number, List<Question> questions) {
        mName = name;
        mNumber = number;
        mQuestions = new ArrayList<>();
        mBackup = new ArrayList<>();

        mQuestions.add(QuestioMock.getNext());


        mBackup.addAll(mQuestions);
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
        return mBackup.size();
    }
}

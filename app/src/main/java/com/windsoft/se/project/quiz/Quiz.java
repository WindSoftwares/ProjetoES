package com.windsoft.se.project.quiz;


import com.windsoft.se.project.model.quiz.Question;
import com.windsoft.se.project.model.series.factory.Difficulty;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * Created by lucas on 11/12/2017.
 */

public class Quiz {

    private  Difficulty mDifficulty;
    private List<Question> mQuestions;
    private Stack<Question> mQuestionsStack;


    public Quiz(Difficulty difficulty, ArrayList<Question> questions ){
        mDifficulty = difficulty;
        mQuestions  = questions;
        mQuestionsStack = new Stack<>();
        mQuestionsStack.addAll(mQuestions);

    }


    /**
     * Modifica a ordens das questões no quiz.
     */
    public void shuffleQuiz(){
        Collections.shuffle(mQuestions);
    }

    /**
     * Retorna a próxima questão do quiz.
     * @return Question
     */
    public Question getNextQuestion(){
        return mQuestionsStack.pop();

    }

    public boolean hasNext() {
        return !mQuestionsStack.isEmpty();
    }

    public List<Question> getQuestions(){
        return this.mQuestions;
    }

    public int getTopScore() {
        return 100;//TODO
    }
}

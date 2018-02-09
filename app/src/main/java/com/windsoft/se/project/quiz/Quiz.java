package com.windsoft.se.project.quiz;


import com.windsoft.se.project.model.quiz.LEVEL;
import com.windsoft.se.project.model.quiz.Question;
import com.windsoft.se.project.model.series.factory.Difficulty;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Created by lucas on 11/12/2017.
 */

public class Quiz {

    private  Difficulty mDifficulty;
    private List<Question> quiz;
    Iterator<Question> iterator;

    public Quiz(Difficulty difficulty){

        this.quiz  = new ArrayList<Question>();
        mDifficulty = difficulty;
        this.iterator = quiz.iterator();

    }

    public Quiz(Difficulty difficulty, ArrayList<Question> quiz ){

        mDifficulty = difficulty;
        this.quiz  = quiz;
        this.iterator = quiz.iterator();

    }


    /**
     * Modifica a ordens das questões no quiz.
     */
    public void shuffleQuiz(){
        Collections.shuffle(quiz);
    }

    /**
     * Retorna a próxima questão do quiz.
     * @return Question
     */
    public Question getNextQuestion(){
        if(iterator.hasNext()){
            return iterator.next();
        }
        return null;
    }

    public List<Question> getQuestions(){
        return this.quiz;
    }

}

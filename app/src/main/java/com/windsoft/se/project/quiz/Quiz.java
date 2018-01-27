package com.windsoft.se.project.quiz;


import com.windsoft.se.project.model.LEVEL;
import com.windsoft.se.project.model.Question;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Created by lucas on 11/12/2017.
 */

public class Quiz {

    private LEVEL level;
    private List<Question> quiz;
    Iterator<Question> iterator;

    public Quiz(LEVEL level){

        this.quiz  = new ArrayList<Question>();
        this.level = level;
        this.iterator = quiz.iterator();

    }

    public Quiz(LEVEL level, ArrayList<Question> quiz ){

        this.quiz  = quiz;
        this.level = level;
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

package com.windsoft.se.project.quiz;

import com.windsoft.se.project.questao.LEVEL;
import com.windsoft.se.project.questao.Question;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

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

    public void shufflequiz(){
        Random random = new Random();

        for (int i = 0; i < quiz.size(); i++) {
            int n = random.nextInt(quiz.size());
            Question questionMoment = quiz.get(n);
            quiz.remove(questionMoment);
            quiz.add(questionMoment);
        }
    }

    public Question getNextQuestion(){
        if(iterator.hasNext()){
            return iterator.next();
        }
        return null;
    }

}

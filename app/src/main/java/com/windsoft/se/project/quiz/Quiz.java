package com.windsoft.se.project.quiz;

import com.windsoft.se.project.questao.LEVEL;
import com.windsoft.se.project.questao.Question;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by lucas on 11/12/2017.
 */

public class Quiz {

    private LEVEL level;
    private Set<Question> quiz;

    public Quiz(LEVEL level){

        quiz  = new HashSet<Question>();
        this.level = level;

    }

    public void shufflequiz(){
        
    }

    public Question getNextQuestion(){
        return null;
    }


}

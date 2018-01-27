package com.windsoft.se.project.model;

/**
 * Created by João Lucas on 10/12/2017.
 */

public class GameQuestion {

    private int score;

    public GameQuestion(){
        this.score = 0;
    }


    public void ehCerta(String resposta,Question question){
        if(question.getCorrectAnswer().equals(resposta)){
            score = score + 1;
        }
    }

    public int getScore(){
        return score;
    }
}

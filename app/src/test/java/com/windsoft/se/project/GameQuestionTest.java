package com.windsoft.se.project;

/**
 * Created by João Lucas on 10/12/2017.
 */

import com.windsoft.se.project.model.quiz.GameQuestion;
import com.windsoft.se.project.model.quiz.LEVEL;
import com.windsoft.se.project.model.quiz.Question;

import org.junit.Test;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

import static org.junit.Assert.*;

public class GameQuestionTest {

    Queue<String> escolhas = new LinkedList<>();

    Queue<String> respostasErradas = new LinkedList<>();

    private GameQuestion gameQuestion = new GameQuestion() ;

    @Test
    public void testeUm() throws  Exception{
        escolhas.add("1");
        escolhas.add("2");
        escolhas.add("3");
        escolhas.add("4");

        Question question1 = new Question("Quanto eh 1 + 2?",LEVEL.HARD,escolhas,"3");

        gameQuestion.ehCerta("3",question1);


        assertEquals(1,gameQuestion.getScore());

    }

}

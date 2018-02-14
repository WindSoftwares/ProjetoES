package com.windsoft.se.project;

/**
 * Created by João Lucas on 10/12/2017.
 */

import com.windsoft.se.project.model.quiz.GameQuestion;
import com.windsoft.se.project.model.quiz.Question;
import com.windsoft.se.project.model.series.Answer;
import com.windsoft.se.project.model.series.Correctness;
import com.windsoft.se.project.model.series.factory.Difficulty;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;

import static org.junit.Assert.*;

public class GameQuestionTest {

    List<Answer> escolhas = new ArrayList<>();

    HashSet<String> respostasErradas = new HashSet<String>();

    private Question question1 = new Question("Quanto eh 1 + 2?", Difficulty.HARD,escolhas,false);

    private GameQuestion gameQuestion = new GameQuestion() ;

    @Test
    public void testeUm() throws  Exception{
        escolhas.add(new Answer("1", Correctness.INCORRECT));
        escolhas.add(new Answer("3", Correctness.CORRECT));


        gameQuestion.ehCerta("3",question1);


        assertEquals(1,gameQuestion.getScore());

    }

}

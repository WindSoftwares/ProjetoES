package com.windsoft.se.project;

/**
 * Created by João Lucas on 25/11/2017.
 */

import com.windsoft.se.project.model.quiz.LEVEL;
import com.windsoft.se.project.model.quiz.Question;
import com.windsoft.se.project.model.series.Answer;
import com.windsoft.se.project.model.series.Correctness;
import com.windsoft.se.project.model.series.factory.Difficulty;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.*;

public class QuestionTest {
    List<Answer> escolhas = new ArrayList<>();

    List<Answer> respostasErradas = new ArrayList<>();

    private Question question1 = new Question("Quanto eh 1 + 2?", Difficulty.HARD,escolhas, false);
//    private Question questaonull = new Question(null,null,null,false);

    @Test
    public void Testeinicial(){
       assertEquals(question1.getDifficulty(),Difficulty.HARD);

       Answer correctAnswer = new Answer("3", Correctness.CORRECT);
       Answer incorrectAnswer = new Answer("1", Correctness.INCORRECT);

       escolhas.add(incorrectAnswer);
       escolhas.add(correctAnswer);

       respostasErradas.add(incorrectAnswer);

       assertEquals(question1.getCorrectAnswer(),correctAnswer);
       assertEquals(question1.getWrongAnswer(),respostasErradas);
    }

}

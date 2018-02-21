package com.windsoft.se.project;

/**
 * Created by João Lucas on 25/11/2017.
 */

import com.windsoft.se.project.model.quiz.LEVEL;
import com.windsoft.se.project.model.quiz.Question;
import com.windsoft.se.project.util.factory.Difficulty;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;

import static org.junit.Assert.*;

public class QuestionTest {
    HashSet<String> escolhas = new HashSet<String>();

    HashSet<String> respostasErradas = new HashSet<String>();


    private Question question1 = new Question("", Difficulty.EASY, new ArrayList<>());
    private Question questaonull = new Question("", Difficulty.EASY, new ArrayList<>());

//    @Test
    public void Testeinicial(){
       assertEquals(question1.getDifficulty(),LEVEL.HARD);

       escolhas.add("3");
       escolhas.add("2");
       escolhas.add("1");

       respostasErradas.add("2");
       respostasErradas.add("1");

       assertEquals(question1.getCorrectAnswer(),"3");
       assertEquals(question1.getWrongAnswer(),respostasErradas);
    }

//    @Test
    public void TesteNull(){
        assertEquals(questaonull.getCorrectAnswer(),null);
        assertEquals(questaonull.getDifficulty(),null);
    }


}

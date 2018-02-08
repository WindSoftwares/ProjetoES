package com.windsoft.se.project;

/**
 * Created by João Lucas on 25/11/2017.
 */

import com.windsoft.se.project.model.quiz.LEVEL;
import com.windsoft.se.project.model.quiz.Question;

import org.junit.Test;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

import static org.junit.Assert.*;

public class QuestionTest {
    private Queue<String> escolhas = new LinkedList<>();
    private Queue<String> respostasErradas = new LinkedList<>();

    @Test
    public void Testeinicial(){

       escolhas.add("3");
       escolhas.add("2");
       escolhas.add("1");

       respostasErradas.add("2");
       respostasErradas.add("1");

       Question question1 = new Question("Quanto eh 1 + 2?",LEVEL.HARD,escolhas,"3");
       assertEquals(question1.getLevel(),LEVEL.HARD);

       assertEquals(question1.getCorrectAnswer(),"3");
       assertEquals(question1.getWrongAnswer(),respostasErradas);
    }

    @Test(expected = NullPointerException.class)
    public void TesteNull(){

        Question nullQuestion = new Question(null,null,null,null);
    }


}

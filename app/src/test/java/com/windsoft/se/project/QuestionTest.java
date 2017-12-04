package com.windsoft.se.project;

/**
 * Created by João Lucas on 25/11/2017.
 */

import com.windsoft.se.project.questao.LEVEL;
import com.windsoft.se.project.questao.Question;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;
import static org.junit.Assert.*;

public class QuestionTest {
    HashSet<String> escolhas = new HashSet<String>();

    HashSet<String> respostasErradas = new HashSet<String>();

    private Question question1 = new Question("Quanto eh 1 + 2?",LEVEL.HARD,escolhas,"3");
    private Question questaonull = new Question(null,null,null,null);

    @Test
    public void Testeinicial(){
       assertEquals(question1.getLevel(),LEVEL.HARD);

       escolhas.add("3");
       escolhas.add("2");
       escolhas.add("1");

       respostasErradas.add("2");
       respostasErradas.add("1");

       assertEquals(question1.getCorrectAnswer(),"3");
       assertEquals(question1.getWrongAnswer(),respostasErradas);
    }

    @Test
    public void TesteNull(){
        assertEquals(questaonull.getCorrectAnswer(),null);
        assertEquals(questaonull.getLevel(),null);
    }


}

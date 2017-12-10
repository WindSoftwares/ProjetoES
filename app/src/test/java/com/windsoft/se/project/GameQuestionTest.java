package com.windsoft.se.project;

/**
 * Created by João Lucas on 10/12/2017.
 */

import com.windsoft.se.project.questao.LEVEL;
import com.windsoft.se.project.questao.Question;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;
import static org.junit.Assert.*;

public class GameQuestionTest {

    HashSet<String> escolhas = new HashSet<String>();

    HashSet<String> respostasErradas = new HashSet<String>();

    private Question question1 = new Question("Quanto eh 1 + 2?",LEVEL.HARD,escolhas,"3");

    private GameQuestion gameQuestion = new GameQuestion() ;

    @Test
    public void testeUm() throws  Exception{
        escolhas.add("1");
        escolhas.add("2");
        escolhas.add("3");
        escolhas.add("4");


        gameQuestion.ehCerta("3",question1);


        assertEquals(1,gameQuestion.getScore());

    }

}

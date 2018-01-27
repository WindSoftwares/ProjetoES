package com.windsoft.se.project;

/**
 * Created by maiana brito on 13/12/2017.
 */

import com.windsoft.se.project.questao.LEVEL;
import com.windsoft.se.project.questao.Question;
import com.windsoft.se.project.quiz.*;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.*;

public class FactoryQuizTest {

    public static HashSet<String> options = new HashSet<String>();

    public static Question questionE1 = new Question("description",LEVEL.EASY, options,"3");
    public static Question questionE2 = new Question("description",LEVEL.EASY, options,"3");
    public static Question questionE3 = new Question("description",LEVEL.EASY, options,"3");
    public static Question questionE4 = new Question("description",LEVEL.EASY, options,"3");
    public static Question questionH1 = new Question("description",LEVEL.HARD, options,"3");
    public static Question questionH2 = new Question("description",LEVEL.HARD, options,"3");
    public static Question questionH3 = new Question("description",LEVEL.HARD, options,"3");
    public static Question questionH4 = new Question("description",LEVEL.HARD, options,"3");

    public static ArrayList<Question> allQuestions = new ArrayList<>();
    public static FactoryQuiz factoryQuiz = new FactoryQuiz();

    @BeforeClass
    public static void createObjects(){

        allQuestions.add(questionE1);
        allQuestions.add(questionE2);
        allQuestions.add(questionE3);
        allQuestions.add(questionE4);
        allQuestions.add(questionH1);
        allQuestions.add(questionH2);
        allQuestions.add(questionH3);
        allQuestions.add(questionH4);

        factoryQuiz.setAllQuestions(allQuestions);
        factoryQuiz.setNumberQuestion(4);
    }

    @Test
    public void createQuizEasy(){
        Quiz quiz = factoryQuiz.createQuiz("Name", 1, LEVEL.EASY);
        List<Question> questions = quiz.getQuestions();

        assertEquals(questions.size(),4);
        assertTrue(questions.contains(questionE1));
        assertTrue(questions.contains(questionE2));
        assertTrue(questions.contains(questionE3));
        assertTrue(questions.contains(questionE4));
    }

    @Test
    public void createQuizHard(){
        Quiz quiz = factoryQuiz.createQuiz("Name", 1, LEVEL.HARD);
        List<Question> questions = quiz.getQuestions();

        assertEquals(questions.size(),4);
        assertTrue(questions.contains(questionH1));
        assertTrue(questions.contains(questionH2));
        assertTrue(questions.contains(questionH3));
        assertTrue(questions.contains(questionH4));
    }
}

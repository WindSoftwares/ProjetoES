package com.windsoft.se.project;

/**
 * Created by maiana brito on 13/12/2017.
 */

import com.windsoft.se.project.model.quiz.LEVEL;
import com.windsoft.se.project.model.quiz.Question;
import com.windsoft.se.project.util.factory.Difficulty;
import com.windsoft.se.project.util.factory.QuizFactory;
import com.windsoft.se.project.quiz.Quiz;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class QuizFactoryTest {

    public static HashSet<String> options = new HashSet<String>();

    public static Question questionE1 = new Question("", Difficulty.EASY, new ArrayList<>());
    public static Question questionE2 = new Question("", Difficulty.EASY, new ArrayList<>());
    public static Question questionE3 = new Question("", Difficulty.EASY, new ArrayList<>());
    public static Question questionE4 = new Question("", Difficulty.EASY, new ArrayList<>());
    public static Question questionH1 = new Question("", Difficulty.EASY, new ArrayList<>());
    public static Question questionH2 = new Question("", Difficulty.EASY, new ArrayList<>());
    public static Question questionH3 = new Question("", Difficulty.EASY, new ArrayList<>());
    public static Question questionH4 = new Question("", Difficulty.EASY, new ArrayList<>());

    public static ArrayList<Question> allQuestions = new ArrayList<>();
    public static QuizFactory quizFactory = new QuizFactory();

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

//        quizFactory.setAllQuestions(allQuestions);
        quizFactory.setNumberQuestion(4);
    }

//    @Test
    public void createQuizEasy(){
        Quiz quiz = quizFactory.createQuiz();
        List<Question> questions = quiz.getQuestions();

        assertEquals(questions.size(),4);
        assertTrue(questions.contains(questionE1));
        assertTrue(questions.contains(questionE2));
        assertTrue(questions.contains(questionE3));
        assertTrue(questions.contains(questionE4));
    }

//    @Test
    public void createQuizHard(){
        Quiz quiz = quizFactory.createQuiz();
        List<Question> questions = quiz.getQuestions();

        assertEquals(questions.size(),4);
        assertTrue(questions.contains(questionH1));
        assertTrue(questions.contains(questionH2));
        assertTrue(questions.contains(questionH3));
        assertTrue(questions.contains(questionH4));
    }
}

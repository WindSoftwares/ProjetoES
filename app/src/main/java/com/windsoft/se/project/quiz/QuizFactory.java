package com.windsoft.se.project.quiz;

import com.windsoft.se.project.questao.LEVEL;
import com.windsoft.se.project.questao.Question;
import com.windsoft.se.project.quiz.Quiz;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by maiana on 12/12/17.
 */

public class QuizFactory {

    /** This method create the quiz.
     *
     * @param name - Series name
     * @param season - Season of the series, when it is 0, the quiz is about all seasons
     * @return Quiz
     */
    public static Quiz createQuiz(String name, Integer season){
        // TODO: Get questions from bd
        // Set<Question> allQuestions = bd.getQuestions(name, season);
        List<Question> allQuestions = new ArrayList<Question>();
        List<Question> questionsSelected = selectQuestions(allQuestions);

        Quiz quiz = new Quiz(questionsSelected);
        quiz.shufflequiz();

        return quiz;
    }

    /** This method select 10 questions that 5 are hard and 5 are easy.
     *
     * @param allQuestions that will be select 10
     * @return The selected questions.
     */
    private static List<Question> selectQuestions(List<Question> allQuestions){
        ArrayList<Question> easy = new ArrayList<>();
        ArrayList<Question> hard = new ArrayList<>();
        List<Question> selects = new ArrayList<>();
        ArrayList<Integer> indices = new ArrayList(5);
        Collections.shuffle(indices);

        for ( Question question : allQuestions) {
            if (question.getLevel() == LEVEL.EASY){
                easy.add(question);
            } else {
                hard.add(question);
            }
        }

        for(Integer indice : indices){
            selects.add(easy.get(indice));
            selects.add(hard.get(indice));
        }

        return selects;
    };
}

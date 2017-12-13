package com.windsoft.se.project.quiz;

import com.windsoft.se.project.questao.LEVEL;
import com.windsoft.se.project.questao.Question;
import com.windsoft.se.project.quiz.Quiz;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by maiana on 12/12/17.
 */

public class FactoryQuiz {

    private int NUMBER_QUESTION = 10;
    /**TODO: This attribute should be remove when conect with BD
     * by: Maiana Brito
     */
    private ArrayList<Question> allQuestions = new ArrayList<Question>();


    /** This method create the quiz.
     *
     * @param name - Series name
     * @param season - Season of the series, when it is 0, the quiz is about all seasons
     * @return Quiz
     */
    public Quiz createQuiz(String name, Integer season, LEVEL level){
        /** TODO: Get questions from bd
         *  by: Maiana
         */
        // Set<Question> allQuestions = bd.getQuestions(name, season);
        ArrayList<Question> questionsSelected = selectQuestions(this.allQuestions, level);

        Quiz quiz = new Quiz(level, questionsSelected);
        quiz.shuffleQuiz();

        return quiz;
    }

    /** This method select n questions according level.
     *
     * @param allQuestions All question where that will be select.
     * @return The selected questions.
     */
    private ArrayList<Question> selectQuestions(ArrayList<Question> allQuestions, LEVEL level){
        ArrayList<Question> selects = new ArrayList<>();
        ArrayList<Question> quiz = new ArrayList<>();

        ArrayList<Integer> indices = new ArrayList<>();
        for (int i = 0; i < NUMBER_QUESTION ; i++) {
            indices.add(i);
        }
        Collections.shuffle(indices);

        for ( Question question : allQuestions) {
            if (question.getLevel() == level) {
                selects.add(question);
            }
        }

        for(Integer indice : indices){
            quiz.add(selects.get(indice));
        }

        return selects;
    };

    public ArrayList<Question> getAllQuestions(){
        return allQuestions;
    }

    public void setAllQuestions(ArrayList<Question> list) {
        this.allQuestions = list;
    }

    public int getNumberQuestion(){
        return NUMBER_QUESTION;
    }

    public void setNumberQuestion(Integer number){
        this.NUMBER_QUESTION = number;
    }

}

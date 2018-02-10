package com.windsoft.se.project.model.series.factory;


import com.windsoft.se.project.model.quiz.LEVEL;
import com.windsoft.se.project.model.quiz.Question;
import com.windsoft.se.project.quiz.Quiz;
import com.windsoft.se.project.util.StaticFlow;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by maiana on 12/12/17.
 */

public class QuizFactory {
    private static QuizFactory instance;

    public static QuizFactory getInstance() {
        if (instance == null) {
            instance = new QuizFactory();
        }
        return instance;
    }




    private int NUMBER_QUESTION = 10;
    /**TODO: This attribute should be remove when conect with BD
     * by: Maiana Brito
     */

    /** This method create the quiz.
     *
//     * @param name - Series name
//     * @param season - Season of the mSeries, when it is 0, the quiz is about all seasons
     * @return Quiz
     */
    public Quiz createQuiz(Difficulty difficulty){

        /** TODO: Get questions from bd
         *  by: Maiana
         */
        // Set<Question> allQuestions = bd.getQuestions(name, season);
        List<Question> allQuestions = StaticFlow.getActualSeason().getQuestions();
        ArrayList<Question> questionsSelected = selectQuestions(allQuestions, difficulty);

        Quiz quiz = new Quiz(difficulty, questionsSelected);
        quiz.shuffleQuiz();

        return quiz;
    }

    /** This method select n questions according level.
     *
     * @param questions All question where that will be select.
     * @return The selected questions.
     */
    private ArrayList<Question> selectQuestions(List<Question> questions, Difficulty difficulty){
        ArrayList<Question> selects = new ArrayList<>();

        for ( Question question : questions) {
            if (question.getDifficulty() == difficulty) {
                selects.add(question);
            }
        }
        Collections.shuffle(selects);

        return selects;//new ArrayList<Question>(selects.subList(0, NUMBER_QUESTION));
    };

//    public ArrayList<Question> getAllQuestions(){
//        return allQuestions;
//    }

//    public void setAllQuestions(ArrayList<Question> list) {
//        this.allQuestions = list;
//    }

    public int getNumberQuestion(){
        return NUMBER_QUESTION;
    }

    public void setNumberQuestion(Integer number){
        this.NUMBER_QUESTION = number;
    }

    public static Quiz createQuiz(List<Question> questions) {
        return null;
    }
}

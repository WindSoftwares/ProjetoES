package com.windsoft.se.project.model.quiz;

import com.windsoft.se.project.model.series.Answer;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by João Lucas on 24/11/2017.
 */


public class Question {
    private String description;
    private LEVEL level;
    private Queue<String> choices = new LinkedList<>();
    private String correctAnswer;
    //private SeasonQuestion seasonQuestion = new SeasonQuestion

    public Question(String description, LEVEL level, List<Answer> answers){
        this.description = description;
        this.level = level;
    }

   public Question(String description,LEVEL level,Queue<String> choices,String correctAnswer){
        if (! choices.contains(correctAnswer)) {
            throw new IllegalArgumentException("Choices must contains the correctAnswer!");
        }

        this.description = description;
        this.level = level;
        this.choices = choices;
        this.correctAnswer = correctAnswer;
    }

    /**
     * Medoto que retorna a questão correta daquela questão
     * @return correctAnswer
     */
    public String getCorrectAnswer() {
        return correctAnswer;
    }

    /**
     * Metodo que vai apresentar um Set das questões erradas
     * @return set<String> wrongAnswer
     */
    public Queue<String> getWrongAnswer(){

       Queue<String> wrongAnswer = getChoices();
       wrongAnswer.remove(getCorrectAnswer());
       return wrongAnswer;

    }

    /**
     * Retorna o enum LEVEL da questão
     * @return leve
     */
    public LEVEL getLevel() {
        return level;
    }

    /**
     * Da uma descrição, que no caso é uma pergunta
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Vai retornar um Set de alternativas da questão
     * @return set<String> choices
     */
    public Queue<String> getChoices() {
        return choices;
    }

    public String pickAnswer() {
        String result = choices.poll();
        choices.add(result);
        return result;
    }

    public void shuffle() {
        Collections.shuffle((List<?>) choices);
    }
}

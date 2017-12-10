package com.windsoft.se.project.questao;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by João Lucas on 24/11/2017.
 */


public class Question {
    private String description;
    private LEVEL level;
    private Set<String> choices = new HashSet<String>();
    private String correctAnswer;
    //private SeasonQuestion seasonQuestion = new SeasonQuestion

    public Question(String description,LEVEL level){
        this.description = description;
        this.level = level;
    }

   public Question(String description,LEVEL level,Set<String> choices,String correctAnswer){
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

    public Set<String> getWrongAnswer(){

       Set<String> wrongAnswer = getChoices();
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
    public Set<String> getChoices() {
        return choices;
    }
}

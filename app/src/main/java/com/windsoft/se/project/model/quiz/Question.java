package com.windsoft.se.project.model.quiz;

import com.windsoft.se.project.model.series.Answer;
import com.windsoft.se.project.model.series.factory.Difficulty;

import java.util.Collections;
import java.util.List;
import java.util.Queue;

/**
 * Created by João Lucas on 24/11/2017.
 */


public class Question {
    private String mDescription;
    private Difficulty mDifficulty;
    private String correctAnswer;
    private List<Answer> mAnswers;
    //private SeasonQuestion seasonQuestion = new SeasonQuestion

    public Question(String description, Difficulty difficulty, List<Answer> answers){
        mDescription = description;
        mDifficulty = difficulty;
        mAnswers = answers;
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
    public List<Answer> getWrongAnswer(){
        return getChoices();//TODO

    }

    /**
     * Retorna o enum LEVEL da questão
     * @return leve
     */
    public Difficulty getDifficulty() {
        return mDifficulty;
    }

    /**
     * Da uma descrição, que no caso é uma pergunta
     * @return mDescription
     */
    public String getDescription() {
        return mDescription;
    }

    /**
     * Vai retornar um Set de alternativas da questão
     * @return set<String> choices
     */
    public List<Answer> getChoices() {
        return mAnswers;
    }

    public String pickAnswer() {
        return mAnswers.get(0).getText();//TODO
    }

    public void shuffle() {
        Collections.shuffle(mAnswers);
    }

    @Override
    public String toString() {
        return "Question{" +
                "mDescription='" + mDescription + '\'' +
                ", mDifficulty=" + mDifficulty +
                ", choices=" + mAnswers +
                ", correctAnswer='" + correctAnswer + '\'' +
                '}';
    }
}

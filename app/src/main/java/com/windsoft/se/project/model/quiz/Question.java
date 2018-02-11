package com.windsoft.se.project.model.quiz;

import com.windsoft.se.project.model.series.Answer;
import com.windsoft.se.project.model.series.factory.Difficulty;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 * Created by João Lucas on 24/11/2017.
 */


public class Question {
    private String mDescription;
    private Difficulty mDifficulty;
    private String correctAnswer;
    private List<Answer> mAnswers;
    private Stack<Answer> mAnswersStack;
    //private SeasonQuestion seasonQuestion = new SeasonQuestion

    public Question(String description, Difficulty difficulty, List<Answer> answers){
        mDescription = description;
        mDifficulty = difficulty;
        mAnswers = answers;
        mAnswersStack = new Stack<>();
        mAnswersStack.addAll(mAnswers);
    }
    /**
     * Medoto que retorna a questão correta daquela questão
     * @return correctAnswer
     */
    public Answer getCorrectAnswer() {
        for (Answer answer : mAnswers) {
            if (answer.isCorrect()) {
                return answer;
            }
        }

        return mAnswers.get(0);//mAnswers.forEach(return);
    }

    /**
     * Metodo que vai apresentar um Set das questões erradas
     * @return set<String> wrongAnswer
     */
    public List<Answer> getWrongAnswer(){
        List<Answer> result = new ArrayList<>();
        mAnswers.forEach(answer -> {
            if (!answer.isCorrect()) {
                result.add(answer);
            }
        });
        return result;

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
        if (mAnswersStack.isEmpty()) {
            mAnswersStack.addAll(mAnswers);
        }
        return mAnswersStack.pop().getText();
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

package com.windsoft.se.project;

import com.windsoft.se.project.model.quiz.GameQuestion;
import com.windsoft.se.project.model.quiz.LEVEL;
import com.windsoft.se.project.model.quiz.Question;
import com.windsoft.se.project.model.series.Answer;
import com.windsoft.se.project.model.series.Correctness;
import com.windsoft.se.project.model.series.Series;
import com.windsoft.se.project.model.series.factory.Difficulty;
import com.windsoft.se.project.model.series.season.Season;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by Ígor Brasileiro on 14/02/2018.
 */

public class SerieUnitTest {
    private List<Answer> answers;
    private List<Question> questions;
    private List<Season> seasons = new ArrayList<>();

    @Before
    public void initiate() {
        answers = new ArrayList<>();
        fullfilAnswers();

        questions = new ArrayList<>();
        fullfilQuestions();

        seasons = new ArrayList<>();
        fullfilSeason();
    }

    @Test
    public void testDefaultConstructor() {
        Series newSerie = new Series("Minha Série de Teste", null, seasons);

        assertEquals("Minha Série de Teste", newSerie.getName());
        assertEquals(false,newSerie.isFavorite());

        newSerie.setAsFavorite();
        assertEquals(true,newSerie.isFavorite());
    }

    @Test
    public void testOtherConstructor() {
        Series newSerie = new Series("Minha Série de Teste", null, seasons, true);
        assertEquals(true,newSerie.isFavorite());
    }

    public void fullfilAnswers() {
        Answer answer1 = new Answer("1", Correctness.INCORRECT);
        Answer answer2 = new Answer("3", Correctness.CORRECT);
        answers.add(answer1);
        answers.add(answer2);
    }

    public void fullfilQuestions() {
        Question question1 = new Question("Quanto eh 1 + 2?", Difficulty.HARD,answers,false);
        Question question2 = new Question("Quanto é 2 + 1?", Difficulty.EASY,answers,false);
        questions.add(question1);
        questions.add(question2);
    }

    public void fullfilSeason() {
        Season season1 = new Season("Temporada 1", 1, questions);
        seasons.add(season1);
    }
}

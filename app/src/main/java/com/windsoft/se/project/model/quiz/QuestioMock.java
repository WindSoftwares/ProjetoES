package com.windsoft.se.project.model.quiz;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by GersonSales on 1/27/2018.
 */

public class QuestioMock {
    private static Question netx;

    public static boolean hasNext() {
        return true;
    }

    public static Question getNext() {
        Queue<String> choices = new LinkedList<>();
        choices.add("Alternative(1)");
        choices.add("Alternative(2)");
        choices.add("Alternative(3)");
        choices.add("Alternative(4)");

        return new Question("Description", LEVEL.EASY, choices , "Alternative(1)");
//        return netx;
    }
}

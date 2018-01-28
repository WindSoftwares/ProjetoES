package com.windsoft.se.project.model.series.season;

/**
 * Created by GersonSales on 1/27/2018.
 */

public class Season {
    private String mName;
    private Integer mNumber;

    Season(String name, Integer number) {
        this.mName = name;
        this.mNumber = number;
    }


    public String getName() {
        return mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public Integer getNumber() {
        return mNumber;
    }

    public void setNumber(Integer number) {
        this.mNumber = number;
    }
}

package com.windsoft.se.project.series;

import android.graphics.Bitmap;

import java.util.Set;
import java.util.TreeSet;

/**
 * Created by gersonsales on 07/11/17.
 */

public class Series {

    private String mName;
    private Bitmap mThumbnail;
    private Set<Season> mSeasons;
    private long mId;


    public Series(String name, Bitmap thumbnail, TreeSet<Season> seasons) {
        this.mName = name;
        this.mThumbnail = thumbnail;
        this.mSeasons = seasons;
    }

    public String getName() {
        return mName;
    }


    public void setName(String name) {
        this.mName = name;
    }

    public long getId() {
        return mId;
    }

    @Override
    public String toString() {
        return "Series{" +
                "mName='" + mName + '\'' +
                '}';
    }
}

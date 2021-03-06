package com.windsoft.se.project.model.series;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.provider.MediaStore;

import com.windsoft.se.project.model.series.season.Season;
import com.windsoft.se.project.util.MediaUtil;

import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by gersonsales on 07/11/17.
 */

public class Series {

    private String mName;
    private Bitmap mThumbnail;
    private List<Season> mSeasons;
    private boolean mFavorite;

    public Series(String name, Bitmap thumbnail, List<Season> seasons) {
        mName = name;
        mThumbnail = thumbnail;
        mSeasons = seasons;
        mFavorite = false;
    }

    public Series(String name, Bitmap thumbnail, List<Season> seasons, boolean isFavorite) {
        this(name, thumbnail, seasons);
        mFavorite = isFavorite;
    }

    public String getName() {
        return mName;
    }


    public void setName(String name) {
        this.mName = name;
    }

    public Bitmap getThumbnail() {//TODO temp method to simulate the series thumbnail
        return mThumbnail;//MediaUtil.getBitmapFromURL("https://lh4.googleusercontent.com/SxVOcR70jJ6kbqIlqI5xrW-tOsM8U7nkmw6ohQMPvw-4C0Z4SNVfPMZ6e6mbfA-DdU3l1qCtBIfyp5aJBv_S=w1920-h987");
    }

    @Override
    public String toString() {
        return "Series{" +
                "mName='" + mName + '\'' +
                ", mThumbnail=" + mThumbnail +
                ", mSeasons=" + mSeasons +
                '}';
    }

    public Season getSeasonByPosition(int position) {
        return mSeasons.get(position);
    }

    public int getSeasonCount() {
        return mSeasons.size();
    }

    public boolean isFavorite() {
        return mFavorite;
    }

    public void setAsFavorite(boolean isFavorite) {
        mFavorite = isFavorite;
    }
}

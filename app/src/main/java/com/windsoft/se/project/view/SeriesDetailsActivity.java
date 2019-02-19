package com.windsoft.se.project.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.ImageView;

import com.windsoft.se.project.R;
import com.windsoft.se.project.model.series.FavoriteSeriesMock;
import com.windsoft.se.project.model.series.Series;
import com.windsoft.se.project.util.StaticFlow;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;

public class SeriesDetailsActivity extends AppCompatActivity {

    @BindView(R.id.seriesDetails_imageView)
    ImageView mSeriesThumbnail;

    @BindView(R.id.seriesDetails_favorite_checkBox)
    CheckBox mIsFavorite;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_series_deatails);
        ButterKnife.bind(this);

        mSeriesThumbnail.setImageBitmap(StaticFlow.getActualSeries().getThumbnail());
        mIsFavorite.setChecked(StaticFlow.getActualSeries().isFavorite());
        setTitle(StaticFlow.getActualSeries().getName());
    }

    @OnCheckedChanged(R.id.seriesDetails_favorite_checkBox)
    void OnCheckedChanged(boolean checked) {
        Series series = StaticFlow.getActualSeries();
        series.setAsFavorite(checked);
        if (checked) {
            FavoriteSeriesMock.getInstance().addSeries(series);
        }else {
            FavoriteSeriesMock.getInstance().removeSeries(series);
        }
    }

}

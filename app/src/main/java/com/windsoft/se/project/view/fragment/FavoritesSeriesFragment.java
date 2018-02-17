package com.windsoft.se.project.view.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.windsoft.se.project.R;
import com.windsoft.se.project.adapter.FavoriteSeriesViewAdapter;
import com.windsoft.se.project.adapter.SeriesViewAdapter;
import com.windsoft.se.project.model.series.FavoriteSeriesMock;
import com.windsoft.se.project.model.series.SeriesMock;


/**
 * Created by GersonSales on 1/27/2018.
 */

public class FavoritesSeriesFragment extends SeriesScreenFragment{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        if (!(savedInstanceState == null)) return getView();

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @NonNull
    @Override
    SeriesViewAdapter getNewAdapter() {
        return new FavoriteSeriesViewAdapter();
    }


    @Override
    void filterSeriesByName(String newText) {
        FavoriteSeriesMock.getInstance().filterByName(newText);
    }


    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        menu.removeItem(R.id.action_favorites);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_home:
                goToSeriesScreen();
                return true;

            default: return super.onOptionsItemSelected(item);
        }
    }

    @SuppressLint("ResourceType")
    private void goToSeriesScreen() {
        getFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left)
                .replace(R.id.mainFragment, new SeriesScreenFragment())
                .commit();
    }
}

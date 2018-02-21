package com.windsoft.se.project.view;


import android.annotation.SuppressLint;
import android.app.FragmentTransaction;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.Toast;

import com.windsoft.se.project.R;
import com.windsoft.se.project.model.series.SeriesMock;
import com.windsoft.se.project.view.fragment.FavoritesSeriesFragment;
import com.windsoft.se.project.view.fragment.ScoreFragment;
import com.windsoft.se.project.view.fragment.SeasonScreenFragment;
import com.windsoft.se.project.view.fragment.SeriesScreenFragment;

/**
 * Created by GersonSales on 1/18/2018.
 */

public class MainActivity extends AppCompatActivity {


    private SearchView mSeriesSearch;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            setSupportActionBar(findViewById(R.id.app_toolbar));
        if (savedInstanceState == null) {
            bindFragment();
        }
    }



    private void bindFragment() {
        getFragmentManager().beginTransaction()
                .replace(R.id.mainFragment, new SeriesScreenFragment())
                .commitAllowingStateLoss();
    }



    @Override
    public boolean onPrepareOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_home:
                goToSeriesScreen();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    public void onBackPressed() {
        if(SeasonScreenFragment.backOrNot){
            goToSeriesScreen(R.anim.enter_from_bottom, R.anim.exit_to_top);
        }else {
            super.onBackPressed();
        }
    }

    
    @SuppressLint("ResourceType")
    private void goToFavoriteSeriesScreen() {
        getFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.enter_from_top, R.anim.exit_to_bottom)
                .replace(R.id.mainFragment, new FavoritesSeriesFragment())
                .addToBackStack("favoriteScreen")
                .commit();
        mOnHomeScreen = false;
        mOnFavoritesScreen = true;
    }




    @SuppressLint("ResourceType")
    private void goToSeriesScreen() {
        goToSeriesScreen(R.anim.enter_from_right, R.anim.exit_to_left);
    }

    private void goToSeriesScreen(int enterAnimation, int exitAnimation) {
        getFragmentManager().beginTransaction()
                .setCustomAnimations(enterAnimation, exitAnimation)
                .replace(R.id.mainFragment, new SeriesScreenFragment())
                .commit();
    }
}

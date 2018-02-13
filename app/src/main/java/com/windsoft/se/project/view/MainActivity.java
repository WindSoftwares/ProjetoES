package com.windsoft.se.project.view;


import android.annotation.SuppressLint;
import android.app.FragmentTransaction;
import android.content.ClipData;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.windsoft.se.project.R;
import com.windsoft.se.project.view.fragment.FavoritesSeriesFragment;
import com.windsoft.se.project.view.fragment.SeriesScreenFragment;

/**
 * Created by GersonSales on 1/18/2018.
 */

public class MainActivity extends AppCompatActivity {


    private boolean mOnFavoritesScreen = false;
    private boolean mOnHomeScreen = true;
    MenuItem favoriteIcon;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSupportActionBar(findViewById(R.id.app_toolbar));

        bindFragment();
    }

    private void bindFragment() {
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.mainFragment, new SeriesScreenFragment());
        fragmentTransaction.commit();
    }

    @Override
    public boolean onPrepareOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        favoriteIcon = menu.findItem(R.id.action_favorites);

        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_home:
                goToSeriesScreen();
                return true;

            case R.id.action_favorites:
                if (mOnFavoritesScreen) {
                    item.setIcon(R.drawable.ic_star_border_black_24dp);
                    goToSeriesScreen(R.anim.enter_from_bottom, R.anim.exit_to_top);
                } else {
                    item.setIcon(R.drawable.ic_star_black_24dp);
                    goToFavoriteSeriesScreen();
                }
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);
        }
    }

    @SuppressLint("ResourceType")
    private void goToFavoriteSeriesScreen() {
        getFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.enter_from_top, R.anim.exit_to_bottom)
                .replace(R.id.mainFragment, new FavoritesSeriesFragment())
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

        mOnHomeScreen = true;
        mOnFavoritesScreen = false;
        favoriteIcon.setIcon(R.drawable.ic_star_border_black_24dp);
    }
}

package com.windsoft.se.project.view.fragment;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.support.v7.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.windsoft.se.project.R;
import com.windsoft.se.project.adapter.SeriesViewAdapter;
import com.windsoft.se.project.model.series.Series;
import com.windsoft.se.project.model.series.SeriesMock;
import com.windsoft.se.project.util.StaticFlow;
import com.windsoft.se.project.view.SeriesDetailsActivity;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;
import butterknife.OnItemLongClick;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class SeriesScreenFragment extends Fragment {

    @BindView(R.id.series_gridView)
    GridView mSeriesGridView;

    @BindView(R.id.noSeriesFound_tectView)
    TextView mNoSeriesFound;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.view_serie_screen, container, false);
        ButterKnife.bind(this, view);
        setHasOptionsMenu(true);
        getActivity().setTitle(R.string.app_label);

        mSeriesGridView.setAdapter(getNewAdapter());


        updateNoSeriesFound();
        return view;
    }

    private void updateNoSeriesFound() {
        mNoSeriesFound.setVisibility(mSeriesGridView.getAdapter().isEmpty() ? VISIBLE : GONE);
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        menu.removeItem(R.id.action_home);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_favorites:
                goToFavoriteSeriesScreen();
                return true;

                default: return super.onOptionsItemSelected(item);
        }


    }

    @NonNull
    SeriesViewAdapter getNewAdapter() {
        return new SeriesViewAdapter();
    }


    @OnItemLongClick(R.id.series_gridView)
    boolean onItemLongClick(int position) {
        StaticFlow.setActualSeries((Series) mSeriesGridView.getAdapter().getItem(position));
        Intent intent = new Intent(getActivity(), SeriesDetailsActivity.class);
        getActivity().startActivity(intent);

//        new AlertDialog.Builder(getActivity())
//                .setTitle("title")
//                .setMessage("message")
//                .setPositiveButton("positive", ((dialog, which) -> {}))
//                .setNegativeButton("negative", ((dialog, which) -> {}))
//                .show();
        return true; //if return false, the onItemClick() will be invoked when touch up
    }

    @SuppressLint("ResourceType")
    @OnItemClick(R.id.series_gridView)
    void onItemClick(int position) {
        Series series = (Series) mSeriesGridView.getAdapter().getItem(position);
        StaticFlow.setActualSeries(series);
        getActivity()
                .getFragmentManager()
                .beginTransaction()
                .setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right)
                .replace(R.id.mainFragment, new SeasonScreenFragment())
                .commit();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu__main_search, menu);

        MenuItem menuItem = menu.findItem(R.id.action_search);

        SearchView searchView = (SearchView) menuItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterSeriesByName(newText);
                updateNoSeriesFound();
                return false;
            }
        });

    }

    void filterSeriesByName(String newText) {
        SeriesMock.getInstance().filterByName(newText);
    }

    @SuppressLint("ResourceType")
    private void goToFavoriteSeriesScreen() {
        getFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right)
                .replace(R.id.mainFragment, new FavoritesSeriesFragment())
                .addToBackStack("favoriteScreen")
                .commit();
    }

}

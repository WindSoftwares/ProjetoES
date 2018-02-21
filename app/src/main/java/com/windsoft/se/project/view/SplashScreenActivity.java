package com.windsoft.se.project.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.windsoft.se.project.R;
import com.windsoft.se.project.model.series.Series;
import com.windsoft.se.project.model.series.SeriesMock;
import com.windsoft.se.project.util.FileUtil;
import com.windsoft.se.project.util.factory.SeriesFactory;

import java.util.HashSet;
import java.util.Set;

import static android.support.v4.content.PermissionChecker.checkSelfPermission;
import static com.windsoft.se.project.util.Constant.SERIES;
import static com.windsoft.se.project.util.EnvironmentUtil.isNetworkAvailable;
import static com.windsoft.se.project.util.EnvironmentUtil.toastShortMessage;
import static java.lang.Thread.sleep;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FileUtil.setExternalFileDir(getExternalFilesDir(Environment.DIRECTORY_PICTURES));

        setContentView(R.layout.activity_splash_screen);


        toGrantNetwork();
        toGrantPermission();


        Thread splashScreenDelay  = new Thread(() ->{
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference reference = database.getReference();
            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    SeriesFactory.getInstance().setSeriesSnapshot(dataSnapshot.child(SERIES));
                    Intent quizScreenIntent = new Intent(SplashScreenActivity.this, MainActivity.class);
                    startActivity(quizScreenIntent);
                    database.goOffline();
                    finish();
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        });


        splashScreenDelay.start();
    }

    private void toGrantPermission() {
        if (!isPermissionGranted()) {
            requestPermission();
        }

        if(!isPermissionGranted()) {
            toastShortMessage(this, getString(R.string.necessary_storage_permissio));
            finish();
        }
    }


    private boolean isPermissionGranted() {
        return PackageManager.PERMISSION_GRANTED == checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                && PackageManager.PERMISSION_GRANTED == checkSelfPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE);
    }

    private void requestPermission() {
        String[] permissions = {android.Manifest.permission.WRITE_EXTERNAL_STORAGE, android.Manifest.permission.READ_EXTERNAL_STORAGE};
        ActivityCompat.requestPermissions(this, permissions, 0);
    }

    private void toGrantNetwork() {
        if (!isNetworkAvailable(this)){
            toastShortMessage(this, getString(R.string.no_network_access));
            finish();
            return;
        }
    }
}

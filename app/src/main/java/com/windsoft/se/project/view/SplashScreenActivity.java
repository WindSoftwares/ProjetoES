package com.windsoft.se.project.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.windsoft.se.project.R;
import com.windsoft.se.project.model.series.Series;
import com.windsoft.se.project.util.FileUtil;
import com.windsoft.se.project.util.factory.SeriesFactory;

import java.util.HashSet;
import java.util.Set;

import static com.windsoft.se.project.util.Constant.SERIES;
import static com.windsoft.se.project.util.EnvironmentUtil.isNetworkAvailable;
import static com.windsoft.se.project.util.EnvironmentUtil.toastShortMessage;
import static java.lang.Thread.sleep;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FileUtil.setSharedPreferences(getSharedPreferences("Files", 0));
        FileUtil.setContext(this);
        FileUtil.setActivity(this);


        setContentView(R.layout.activity_splash_screen);

        if (!isNetworkAvailable(this)){
            toastShortMessage(this, "No network access.");
            finish();
            return;
        }

        Thread splashScreenDelay  = new Thread(() ->{
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference reference = database.getReference();
            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    SeriesFactory.getInstance().setSeriesSnapshot(dataSnapshot.child(SERIES));
                    Intent quizScreenIntent = new Intent(SplashScreenActivity.this, MainActivity.class);
                    startActivity(quizScreenIntent);
                    finish();
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        });


        splashScreenDelay.start();
    }
}

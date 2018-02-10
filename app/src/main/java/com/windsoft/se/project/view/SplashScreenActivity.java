package com.windsoft.se.project.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.windsoft.se.project.R;
import com.windsoft.se.project.model.series.factory.SeriesFactory;

import static com.windsoft.se.project.util.Constant.ONE_SECOND;
import static com.windsoft.se.project.util.Constant.SERIES;
import static com.windsoft.se.project.util.Constant.TWO_SECONDS;
import static java.lang.Thread.sleep;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Thread splashScreenDelay  = new Thread(() ->{
            try {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference reference = database.getReference();
                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        SeriesFactory.getInstance().setSeriesSnapshot(dataSnapshot.child(SERIES));
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

                sleep(TWO_SECONDS);
            }catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                Intent quizScreenIntent = new Intent(this, MainActivity.class);
                startActivity(quizScreenIntent);
                finish();
            }
        });


        splashScreenDelay.start();
    }
}

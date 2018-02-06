package com.windsoft.se.project.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;


import com.windsoft.se.project.R;

import static com.windsoft.se.project.util.Constant.ONE_SECOND;
import static java.lang.Thread.sleep;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Thread splashScreenDelay  = new Thread(() ->{
            try {
                sleep(ONE_SECOND);
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

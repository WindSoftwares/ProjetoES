package com.windsoft.se.project;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.mattheusbrito.projetoes.R;
import com.windsoft.se.project.view.MainActivity;

import static java.lang.Thread.sleep;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Thread splashScreenDelay  = new Thread(() ->{
            try {
                sleep(3000);
            }catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                Intent mainActivityIntent = new Intent(this, MainActivity.class);
                startActivity(mainActivityIntent);
                finish();
            }
        });


        splashScreenDelay.start();
    }
}

package com.example.pc.android_advance_lesson3_le_thanh_dat_oe10;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashActivity extends Activity {

    final long delay = 2000L; // 1000ms = 1s
    final Runnable action = new Runnable() {
        public void run() {
            Intent goToMain = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(goToMain);
            finish();
        }
    };

    public void onCreate (Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_splash);

        Handler handler = new Handler();
        handler.postDelayed(action, delay);
    }

}

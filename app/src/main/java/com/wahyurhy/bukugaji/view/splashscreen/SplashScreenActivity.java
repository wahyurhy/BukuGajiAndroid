package com.wahyurhy.bukugaji.view.splashscreen;

import static com.wahyurhy.bukugaji.utils.Utils.setSystemBarColor;
import static com.wahyurhy.bukugaji.utils.Utils.setSystemBarLight;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.wahyurhy.bukugaji.R;
import com.wahyurhy.bukugaji.view.main.MainActivity;

@SuppressLint("CustomSplashScreen")
public class SplashScreenActivity extends AppCompatActivity {

    SplashScreenActivity mThis = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        initialize();
        splashScreenStart();
    }

    private void splashScreenStart() {
        new Handler().postDelayed(() -> {
            Intent intentMainActivity = new Intent(mThis, MainActivity.class);
            intentMainActivity.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intentMainActivity);
        }, 2000);
    }

    private void initialize() {
        setSystemBarLight(this);
        setSystemBarColor(this, R.color.white);
    }
}
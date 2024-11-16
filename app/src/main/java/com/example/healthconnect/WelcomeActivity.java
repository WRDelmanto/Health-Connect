package com.example.healthconnect;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.healthconnect.home.HomeActivity;
import com.google.android.material.progressindicator.CircularProgressIndicator;

public class WelcomeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_welcome);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        final int COUNTDOWN_TIMER_IN_SECONDS = 3;

        CircularProgressIndicator progressIndicator = findViewById(R.id.welcome_activity_progress);

        progressIndicator.setMax(100);
        progressIndicator.setProgress(0);

        new CountDownTimer(COUNTDOWN_TIMER_IN_SECONDS * 1000, 1) {
            @Override
            public void onTick(long millisUntilFinished) {
                long elapsedMillis = (COUNTDOWN_TIMER_IN_SECONDS * 1000) - millisUntilFinished;
                int percentage = (int) ((elapsedMillis / (float) (COUNTDOWN_TIMER_IN_SECONDS * 1000)) * 100);
                progressIndicator.setProgress(percentage);
            }

            @Override
            public void onFinish() {
                progressIndicator.setProgress(100);
                startActivity(new Intent(WelcomeActivity.this, HomeActivity.class));
                finish();
            }
        }.start();
    }
}
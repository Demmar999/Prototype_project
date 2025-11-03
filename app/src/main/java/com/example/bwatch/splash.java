package com.example.bwatch;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

public class splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        ProgressBar progressBar = findViewById(R.id.progressBar);
        LinearLayout centerContent = findViewById(R.id.centerContent);

        // Fade in center content
        centerContent.setAlpha(0f);
        centerContent.animate()
                .alpha(1f)
                .setDuration(800)
                .start();

        // Animate progress bar from 0 to 100
        ObjectAnimator progressAnimator = ObjectAnimator.ofInt(progressBar, "progress", 0, 100);
        progressAnimator.setDuration(3000); // 3 seconds
        progressAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        progressAnimator.setStartDelay(500); // Start after fade-in
        progressAnimator.start();

        // Navigate to login after 3.5 seconds
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(splash.this, ui_login.class);
                startActivity(intent);
                finish();

                // Smooth transition animation
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        }, 3500);
    }
}
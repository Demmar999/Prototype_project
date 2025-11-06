package com.example.bwatch;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseApp;
import com.google.firebase.analytics.FirebaseAnalytics;

public class splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        ProgressBar progressBar = findViewById(R.id.progressBar);
        LinearLayout centerContent = findViewById(R.id.centerContent);

        FirebaseApp.initializeApp(this);


        FirebaseAnalytics analytics = FirebaseAnalytics.getInstance(this);
        if (analytics != null) {
            Log.d("FirebaseTest", "✅ Firebase Analytics initialized successfully");


            Bundle testEvent = new Bundle();
            testEvent.putString("status", "connected");
            FirebaseAnalytics.getInstance(this).logEvent("test_event", testEvent);
            Log.d("FirebaseTest", "🚀 Test event sent to Firebase Analytics");


        } else {
            Log.e("FirebaseTest", "❌ Firebase initialization failed!");
        }


        centerContent.setAlpha(0f);
        centerContent.animate()
                .alpha(1f)
                .setDuration(800)
                .start();


        ObjectAnimator progressAnimator = ObjectAnimator.ofInt(progressBar, "progress", 0, 100);
        progressAnimator.setDuration(3000);
        progressAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        progressAnimator.setStartDelay(500);
        progressAnimator.start();


        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            Intent intent = new Intent(splash.this, ui_login.class);
            startActivity(intent);
            finish();
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        }, 3500);
    }
}

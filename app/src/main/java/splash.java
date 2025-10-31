package com.example.bwatch;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;

public class splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash); // Connect to your splash.xml layout

        // Delay for 3 seconds before going to login screen
        new Handler().postDelayed(() -> {
            Intent intent = new Intent(splash.this, com.example.bwatch.ui_login.class);
            startActivity(intent);
            finish(); // Finish splash so user can’t go back to it
        }, 3000); // 3000ms = 3 seconds
    }
}

package com.example.bwatch;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;

public class ReportActivity extends AppCompatActivity {

    private LinearLayout reportButtonLayout;
    private LinearLayout myReportsButtonLayout;
    private LinearLayout homeButtonLayout;
    private LinearLayout accountButtonLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.report);


        myReportsButtonLayout = findViewById(R.id.myReportsButtonLayout);
        accountButtonLayout = findViewById(R.id.accountButtonLayout);
        homeButtonLayout = findViewById(R.id.homeButtonLayout);


        myReportsButtonLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToMyReport();
            }
        });



        homeButtonLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToMyHome();
            }
        });


        accountButtonLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToAccount();
            }
        });
    }

    private void navigateToMyReport() {
        Intent intent = new Intent(ReportActivity.this, MyReportActivity.class);
        startActivity(intent);
    }

    private void navigateToMyHome() {
        Intent intent = new Intent(ReportActivity.this, HomeActivity.class);
        startActivity(intent);
    }

    private void navigateToAccount() {
        Intent intent = new Intent(ReportActivity.this, AccountActivity.class);
        startActivity(intent);
    }
}
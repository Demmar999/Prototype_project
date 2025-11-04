package com.example.bwatch;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;

public class AccountActivity extends AppCompatActivity {

    private LinearLayout reportButtonLayout;
    private LinearLayout myReportsButtonLayout;
    private LinearLayout homeButtonLayout;
    private LinearLayout accountButtonLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);


        reportButtonLayout = findViewById(R.id.reportButtonLayout);
        myReportsButtonLayout = findViewById(R.id.myReportsButtonLayout);
        homeButtonLayout = findViewById(R.id.homeButtonLayout);


        reportButtonLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToReport();
            }
        });



        homeButtonLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToMyHome();
            }
        });


        myReportsButtonLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToMyReports();
            }
        });
    }

    private void navigateToReport() {
        Intent intent = new Intent(AccountActivity.this, ReportActivity.class);
        startActivity(intent);
    }

    private void navigateToMyHome() {
        Intent intent = new Intent(AccountActivity.this, HomeActivity.class);
        startActivity(intent);
    }

    private void navigateToMyReports() {
        Intent intent = new Intent(AccountActivity.this, MyReportActivity.class);
        startActivity(intent);
    }
}
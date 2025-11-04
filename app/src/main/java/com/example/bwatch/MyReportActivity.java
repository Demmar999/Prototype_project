package com.example.bwatch;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;

public class MyReportActivity extends AppCompatActivity {

    private LinearLayout reportButtonLayout;
    private LinearLayout myReportsButtonLayout;
    private LinearLayout homeButtonLayout;
    private LinearLayout accountButtonLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_reports);


        reportButtonLayout = findViewById(R.id.reportButtonLayout);
        accountButtonLayout = findViewById(R.id.accountButtonLayout);
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


        accountButtonLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToAccount();
            }
        });
    }

    private void navigateToReport() {
        Intent intent = new Intent(MyReportActivity.this, ReportActivity.class);
        startActivity(intent);
    }

    private void navigateToMyHome() {
        Intent intent = new Intent(MyReportActivity.this, HomeActivity.class);
        startActivity(intent);
    }

    private void navigateToAccount() {
        Intent intent = new Intent(MyReportActivity.this, AccountActivity.class);
        startActivity(intent);
    }
}
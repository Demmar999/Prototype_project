package com.example.bwatch;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    private LinearLayout reportButtonLayout;
    private LinearLayout homeButtonLayout;
    private LinearLayout myReportsButtonLayout;
    private LinearLayout accountButtonLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);  // Your home XML file

        // Find all navigation buttons
        reportButtonLayout = findViewById(R.id.reportButtonLayout);
        myReportsButtonLayout = findViewById(R.id.myReportsButtonLayout);
        accountButtonLayout = findViewById(R.id.accountButtonLayout);

        // Report button - navigates to ReportActivity (report.xml)
        reportButtonLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToReport();
            }
        });


        // My Reports button - navigates to MyReportsActivity
        myReportsButtonLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToMyReports();
            }
        });

        // Account button - navigates to AccountActivity
        accountButtonLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToAccount();
            }
        });
    }

    private void navigateToReport() {
        Intent intent = new Intent(HomeActivity.this, ReportActivity.class);
        startActivity(intent);
    }

    private void navigateToMyReports() {
        Intent intent = new Intent(HomeActivity.this, MyReportActivity.class);
        startActivity(intent);
    }

    private void navigateToAccount() {
        Intent intent = new Intent(HomeActivity.this, AccountActivity.class);
        startActivity(intent);
    }
}
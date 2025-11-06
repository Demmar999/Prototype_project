package com.example.bwatch;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    private LinearLayout reportButtonLayout;
    private LinearLayout homeButtonLayout;
    private LinearLayout myReportsButtonLayout;
    private LinearLayout accountButtonLayout;
    private ImageView announcementImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);


        reportButtonLayout = findViewById(R.id.reportButtonLayout);
        myReportsButtonLayout = findViewById(R.id.myReportsButtonLayout);
        accountButtonLayout = findViewById(R.id.accountButtonLayout);
        announcementImage = findViewById(R.id.announcementImage);

        if (announcementImage != null) {
            announcementImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(HomeActivity.this, AnnouncementActivity.class);
                    startActivity(intent);
                }
            });
        }


        reportButtonLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToReport();
            }
        });


        myReportsButtonLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToMyReports();
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

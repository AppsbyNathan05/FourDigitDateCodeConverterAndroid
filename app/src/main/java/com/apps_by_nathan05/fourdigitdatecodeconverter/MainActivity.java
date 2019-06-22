package com.apps_by_nathan05.fourdigitdatecodeconverter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class MainActivity extends AppCompatActivity {

    // Declarations
    Button btnDCToDate;
    Button btnDateToDC;
    private AdView avMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // MobileAds.initialize(this, "YOUR_ADMOB_APP_ID");
        MobileAds.initialize(this, "ca-app-pub-6270945777512054~7544682133");

        avMain = findViewById(R.id.avMain);
        AdRequest mainAdRequest = new AdRequest.Builder().build();
        avMain.loadAd(mainAdRequest);

        // Connect to view
        btnDCToDate = findViewById(R.id.btnDCToDate);
        btnDateToDC = findViewById(R.id.btnDateToDC);

        // Button Date Code to Date listener
        btnDCToDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, com.apps_by_nathan05.fourdigitdatecodeconverter.DateCodeToDate.class);
                startActivity(intent);

            }
        }); // End Button Date Code to Date listener

        // Button Date to Date Code listener
        btnDateToDC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, com.apps_by_nathan05.fourdigitdatecodeconverter.DateToDateCode.class);
                startActivity(intent);

            }
        }); // End Button Date to Date Code listener

    } // End on Create
}

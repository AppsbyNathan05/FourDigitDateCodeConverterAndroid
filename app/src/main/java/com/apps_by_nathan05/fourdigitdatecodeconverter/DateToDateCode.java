package com.apps_by_nathan05.fourdigitdatecodeconverter;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.util.Calendar;

public class DateToDateCode extends Activity {
    // Declarations
    Button btnDCToDate;
    Button btnSelectDate;
    DatePickerDialog.OnDateSetListener dpDateSetListner;
    TextView tvResultDC;
    RadioGroup rgDCFormatDDC;
    RadioButton rbYYWWDDC;
    RadioButton rbWWYYDDC;
    private AdView avDTDC;

    // Global Variables
    int globalYear;
    int globalMonth;
    int globalDay;
    Boolean selectedDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_to_date_code);

        avDTDC = findViewById(R.id.avDTDC);
        AdRequest dtdcAdRequest = new AdRequest.Builder().build();
        avDTDC.loadAd(dtdcAdRequest);

        // Connect to view
        btnDCToDate = findViewById(R.id.btnDCToDate);
        btnSelectDate = findViewById(R.id.btnSelectDate);

        // Button Date Code To Date listener
        btnDCToDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(DateToDateCode.this, com.apps_by_nathan05.fourdigitdatecodeconverter.DateCodeToDate.class);
                startActivity(intent);

            }
        }); // End Button Date Code To Date listener

        // Button Select Date listener
        btnSelectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        DateToDateCode.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        dpDateSetListner,
                        year, month, day);

                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();

            }
        }); // End Button Select Date listener

        // Connect to view
        tvResultDC =findViewById(R.id.tvResultDC);
        rgDCFormatDDC = findViewById(R.id.rgDCFormatDDC);
        rbYYWWDDC = findViewById(R.id.rbYYWWDDC);
        rbWWYYDDC = findViewById(R.id.rbWWYYDDC);

        // Set Variable
        selectedDate = false;

        // Instantiate Conversion Calculations
        final ConversionCalculations call = new ConversionCalculations();

        // Date Picker Date Set Listener
        dpDateSetListner = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int dpYear, int dpMonth, int dpDay) {

                // Set Variables
                selectedDate = true;
                globalYear = dpYear;
                globalDay = dpDay;
                globalMonth = dpMonth;

                // Add initial date to string
                String sDateResults = globalMonth + 1 + "/" + globalDay + "/" + globalYear + " " + getString(R.string.converts_to);

                // Set initial Calendar date
                Calendar cal = Calendar.getInstance();
                cal.set(globalYear, globalMonth, globalDay);

                int slectedID = rgDCFormatDDC.getCheckedRadioButtonId();

                // Check format
                if (slectedID == rbWWYYDDC.getId()) {
                    // Find WWYY Date Code and add to results string
                    sDateResults = sDateResults + " " + call.getDateCode(cal, true);
                } else {
                    // Find YYWW Date Code and add to results string
                    sDateResults = sDateResults + " " + call.getDateCode(cal, false);
                } // end if
                // Display results
                tvResultDC.setText(sDateResults);

            } // end onDateSet
        }; // End Date Picker Date Set Listener

        // Radio Group Date Code Format Date to Date Code
        rgDCFormatDDC.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedID) {

                // Check if date selected
                if (selectedDate == Boolean.TRUE) {

                    // Add initial date to string
                    String sDateResults = globalMonth + 1 + "/" + globalDay + "/" + globalYear + " " + getString(R.string.converts_to);

                    // Set initial Calendar date
                    Calendar cal = Calendar.getInstance();
                    cal.set(globalYear, globalMonth, globalDay);

                    // Check format
                    if (checkedID == R.id.rbWWYYDDC) {
                        // Find WWYY Date Code and add to results string
                        sDateResults = sDateResults + " " + call.getDateCode(cal, true);
                    } else {
                        // Find YYWW Date Code and add to results string
                        sDateResults = sDateResults + " " + call.getDateCode(cal, false);
                    } // end if
                    // Display results
                    tvResultDC.setText(sDateResults);

                } // end if

            } // end onCheckedChanged
        }); // End Radio Group Date Code Format Date to Date Code

    } // End on Create

}

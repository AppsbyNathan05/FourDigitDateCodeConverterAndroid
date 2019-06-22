package com.apps_by_nathan05.fourdigitdatecodeconverter;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.Calendar;

public class DateCodeToDate extends Activity {

    // Declarations
    Button btnDateToDC;
    Button btnConvertDC;
    RadioGroup rgDCFormatDCD;
    EditText etDateCode;
    RadioButton rbWWYYDCD;
    TextView tvResultDate;
    private AdView avDCTD;

    /*Date Code website:
    http://www.cpu-world.com/cgi-bin/members/calendar.pl?DATECODE=1500&FULLYEAR=1&PROCESS=Show
    :*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_code_to_date);

        avDCTD = findViewById(R.id.avDCTD);
        AdRequest dctdAdRequest = new AdRequest.Builder().build();
        avDCTD.loadAd(dctdAdRequest);

        // Connect to view
        btnDateToDC = findViewById(R.id.btnDateToDC);
        btnConvertDC = findViewById(R.id.btnConvertDC);
        rgDCFormatDCD = findViewById(R.id.rgDCFormatDCD);
        rbWWYYDCD = findViewById(R.id.rbWWYYDCD);
        etDateCode = findViewById(R.id.etDateCode);
        tvResultDate = findViewById(R.id.tvResultDate);

        // Instantiate Conversion Calculations
        final ConversionCalculations call = new ConversionCalculations();

        // Set message guiding user to enter a D/C
        tvResultDate.setText(getString(R.string.please_enter_a_dc));

        // Button Date to Date Code listener
        btnDateToDC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Go to Date to D/C activity
                Intent intent = new Intent(DateCodeToDate.this, com.apps_by_nathan05.fourdigitdatecodeconverter.DateToDateCode.class);
                startActivity(intent);

            } // end onClick
        }); // End Button Date to Date Code listener

        // Radio Group Date Code Format listener
        rgDCFormatDCD.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedID) {

                // Check if D/C entered
                if (etDateCode.getText().toString().isEmpty()) {

                    // Remind user to enter a D/C
                    Toast.makeText(DateCodeToDate.this, getString(R.string.please_enter_a_dc), Toast.LENGTH_SHORT).show();
                    // Reset message guiding user to enter a D/C
                    tvResultDate.setText(getString(R.string.please_enter_a_dc));

                    // Set Hint
                    if (checkedID == R.id.rbWWYYDCD) {
                        etDateCode.setHint(getString(R.string.dc_wwyy_format));
                    } else {
                        etDateCode.setHint(getString(R.string.dc_yyww_format));
                    } // end if


                } else if (Integer.parseInt(etDateCode.getText().toString().trim()) > 9999) {

                    // Remind user to enter a valid D/C
                    Toast.makeText(DateCodeToDate.this, getString(R.string.please_enter_a_valid_dc), Toast.LENGTH_SHORT).show();
                    // Reset message guiding user to enter a D/C
                    tvResultDate.setText(getString(R.string.please_enter_a_dc));

                } else {

                    // Local Variables
                    int slectedID = rgDCFormatDCD.getCheckedRadioButtonId();
                    Calendar cal = Calendar.getInstance();
                    String strDateCode = etDateCode.getText().toString().trim();
                    int numDateCode = Integer.parseInt(strDateCode) % 10000;
                    String resultDate;
                    Boolean validDateCode = false;

                    // Check D/C format
                    if (slectedID == rbWWYYDCD.getId()) {
                        validDateCode = numDateCode / 100 < 54;
                        // no need to calculate invalid Date Code
                        if (validDateCode) {
                            // Calculate the first day of the week for the D/C WWYY
                            cal.setTime(call.getFirstDay(numDateCode / 100, numDateCode % 100).getTime());
                            // Check if returned date valid
                            validDateCode = Integer.parseInt(call.getDateCode(cal, true).trim()) == numDateCode;
                        } // end if
                    } else {
                        validDateCode = numDateCode % 100 < 54;
                        // no need to calculate invalid Date Code
                        if (validDateCode) {
                            // Calculate the first day of the week for the D/C YYWW
                            cal.setTime(call.getFirstDay(numDateCode % 100, numDateCode / 100).getTime());
                            // Check if returned date valid
                            validDateCode = Integer.parseInt(call.getDateCode(cal, false).trim()) == numDateCode;
                        } // end if
                    } // end if

                    if (validDateCode) {

                        // Add first day of week to results
                        resultDate = getString(R.string.date_is_between) +
                                " " + (cal.get(Calendar.MONTH) + 1) + "/" + cal.get(Calendar.DAY_OF_MONTH) + "/" + cal.get(Calendar.YEAR) + " " + getString(R.string.and);
                        // Calculate last day of week
                        cal.add(Calendar.DATE, 6);
                        // Add last day of week to results
                        resultDate = resultDate + " " + (cal.get(Calendar.MONTH) + 1) + "/" + cal.get(Calendar.DAY_OF_MONTH) + "/" + cal.get(Calendar.YEAR);
                        // Display results
                        tvResultDate.setText(resultDate);

                    } else {

                        // Remind user to enter a valid D/C
                        Toast.makeText(DateCodeToDate.this, getString(R.string.please_enter_a_valid_dc), Toast.LENGTH_SHORT).show();
                        // Reset message guiding user to enter a D/C
                        tvResultDate.setText(getString(R.string.please_enter_a_dc));

                    } // end if

                } // end if

            } // end onCheckedChanged
        }); // End Radio Group Date Code Format listener

        // Button Convert Date Code listener
        btnConvertDC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Check if D/C entered
                if (etDateCode.getText().toString().isEmpty()) {

                    // Remind user to enter a D/C
                    Toast.makeText(DateCodeToDate.this, getString(R.string.please_enter_a_dc), Toast.LENGTH_SHORT).show();
                    // Reset message guiding user to enter a D/C
                    tvResultDate.setText(getString(R.string.please_enter_a_dc));

                } else if (Integer.parseInt(etDateCode.getText().toString().trim()) > 9999) {

                    // Remind user to enter a valid D/C
                    Toast.makeText(DateCodeToDate.this, getString(R.string.please_enter_a_valid_dc), Toast.LENGTH_SHORT).show();
                    // Reset message guiding user to enter a D/C
                    tvResultDate.setText(getString(R.string.please_enter_a_dc));

                }else{

                    // Local Variables
                    int slectedID = rgDCFormatDCD.getCheckedRadioButtonId();
                    Calendar cal = Calendar.getInstance();
                    String strDateCode = etDateCode.getText().toString().trim();
                    int numDateCode = Integer.parseInt(strDateCode) % 10000;
                    String resultDate;
                    Boolean validDateCode = false;

                    // Check D/C format
                    if (slectedID == rbWWYYDCD.getId()) {
                        // Calculate the first day of the week for the D/C WWYY
                        cal.setTime(call.getFirstDay(numDateCode / 100, numDateCode % 100).getTime());

                        validDateCode = Integer.parseInt(call.getDateCode(cal, true).trim()) == numDateCode;

                    } else {
                        // Calculate the first day of the week for the D/C YYWW
                        cal.setTime(call.getFirstDay(numDateCode % 100, numDateCode / 100).getTime());

                        validDateCode = Integer.parseInt(call.getDateCode(cal, false).trim()) == numDateCode;

                    } // end if

                    if (validDateCode) {

                        // Add first day of week to results
                        resultDate = getString(R.string.date_is_between) +
                                " " + (cal.get(Calendar.MONTH) + 1) + "/" + cal.get(Calendar.DAY_OF_MONTH) + "/" + cal.get(Calendar.YEAR) + " " + getString(R.string.and);
                        // Calculate last day of week
                        cal.add(Calendar.DATE, 6);
                        // Add last day of week to results
                        resultDate = resultDate + " " + (cal.get(Calendar.MONTH) + 1) + "/" + cal.get(Calendar.DAY_OF_MONTH) + "/" + cal.get(Calendar.YEAR);
                        // Display results
                        tvResultDate.setText(resultDate);

                    } else {

                        // Remind user to enter a valid D/C
                        Toast.makeText(DateCodeToDate.this, getString(R.string.please_enter_a_valid_dc), Toast.LENGTH_SHORT).show();
                        // Reset message guiding user to enter a D/C
                        tvResultDate.setText(getString(R.string.please_enter_a_dc));

                    } // end if

                } // end if

            } // end onClick
        }); // End Button Convert Date Code listener

    } // End on Create

}

package com.example.kontr;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import com.example.kontr.R;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private final int DATE_INPUT_REQUEST_CODE = 1;
    private int mYear, mMonth, mDay;
    private TextView resultTextView;
    private Handler handler;

    private Calendar selectedDate;
    private Calendar currentDate;
    private boolean distanceShowed;
    private long daysBetween;
    private String addText;
    private String mainText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultTextView = (TextView) findViewById(R.id.resultTextView);
        this.currentDate = Calendar.getInstance();
        distanceShowed = false;
        daysBetween = 0;
        addText = "";
        mainText = "";
        handler = new Handler();
    }

    Runnable runnable = new Runnable() {
        @RequiresApi(api = Build.VERSION_CODES.O)
        @Override
        public void run() {
            try {
                if(!distanceShowed) {
                    showDistance();
                } else {
                    generateRand();
                }
            } finally {
                handler.postDelayed(runnable, 1000);
            }
        }
    };

    public void onOpenDateClick(View v) {
        // Get Current Date
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @RequiresApi(api = Build.VERSION_CODES.O)
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        onDateSelected(dayOfMonth, monthOfYear, year);
                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == DATE_INPUT_REQUEST_CODE) {

        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void onDateSelected(int day, int month, int year) {
         mainText = "Dabartine data: {curDate}\nPasirinkta data: {date}\n ";

        this.selectedDate = Calendar.getInstance();
        this.selectedDate.set(year, month, day);

        mainText = mainText.replace("{curDate}", this.currentDate.getTime().toString()).replace("{date}", this.selectedDate.getTime().toString());
        resultTextView.setText(mainText);

        showDistance();
        handler.postDelayed(runnable, 5000);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void showDistance(){
        daysBetween = ChronoUnit.DAYS.between(this.currentDate.toInstant(), this.selectedDate.toInstant());

        addText = ("\nSkirtumas : {d} dienu").replace("{d}", Long.toString(daysBetween));
        this.resultTextView.setText(mainText + addText);

        distanceShowed = true;
    }

    private void generateRand() {
        int max;
        int min;

        if(daysBetween > 0) {
            max = (int)daysBetween;
            min = 0;
        } else {
            max = 0;
            min = (int)daysBetween;
        }

        Random rand = new Random();
        int random = rand.nextInt((max - min) + 1) + min;
        this.addText = ("\nRandom number in between = " + random);
        this.resultTextView.setText(mainText + addText);
    }

    public void stopGen(View v) {
        handler.removeCallbacks(runnable);
    }

    public void resGen(View v) {
        handler.postDelayed(runnable, 1000);
    }

    public void exit(View v) {
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
    }
}
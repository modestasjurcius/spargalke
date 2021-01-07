package com.example.lab3;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Rating;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private AutoCompleteTextView autoCompleteTextView;
    private TextView nameTextView;
    private RatingBar complexityRatingBar;
    private Spinner daySpinner;
    private TimePicker timePicker;
    private Switch registerSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.autoCompleteTextView = findViewById(R.id.facultyAutoCompleteTextView);
        this.nameTextView = findViewById(R.id.nameEditText);
        this.complexityRatingBar = findViewById(R.id.complexityRatingBar);
        this.daySpinner = findViewById(R.id.dayDropdown);
        this.timePicker = findViewById(R.id.timePicker);
        this.registerSwitch = findViewById(R.id.registerSwitch);

        String[] faculties = getResources().getStringArray(R.array.fakultetai);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.select_dialog_item, faculties);
        this.autoCompleteTextView.setThreshold(1);
        this.autoCompleteTextView.setAdapter(adapter);
    }

    public void onSave(View v) {
        float complexityCount = this.complexityRatingBar.getRating();
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        String timeText = this.timePicker.getHour() + ":" + this.timePicker.getMinute();

        String resultText = "Pavadinimas: {name}\nFakultetas: {faculty}\nSudėtingumas: {complexity}\nDiena: {day}\nLaikas: {time}\nRegistruotis: {register}";
        resultText = resultText.replace("{name}", this.nameTextView.getText().toString());
        resultText = resultText.replace("{faculty}", this.autoCompleteTextView.getText().toString());
        resultText = resultText.replace("{complexity}", df.format(complexityCount));
        resultText = resultText.replace("{day}", this.daySpinner.getSelectedItem().toString());
        resultText = resultText.replace("{time}", timeText);
        resultText = resultText.replace("{register}", this.registerSwitch.isChecked() ? "Taip" : "Ne");

        Toast.makeText(this, resultText, Toast.LENGTH_LONG).show();
    }
}
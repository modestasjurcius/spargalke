package com.example.lab6;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> listItems;
    private ArrayAdapter<String> adapter;
    private ListView listView;
    private TextView resultTextView;

    private int firstTextIndex;
    private int textOption1Index;
    private int textOption2Index;
    private int textDisplayInterval;

    private String textToDisplay;
    private int displayTextCharIndex;

    private Runnable textDisplayRunnable;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setupListViewWithContextMenu();
        setupRunnable();
    }

    private void setupListViewWithContextMenu() {
        resultTextView = findViewById(R.id.textView4);
        listView = findViewById(R.id.listView);

        firstTextIndex = 0;
        textOption1Index = 1;
        textOption2Index = 2;

        listItems = new ArrayList<String>();
        listItems.add(firstTextIndex, getString(R.string.defaultText).toString());
        listItems.add(textOption1Index, getString(R.string.textOption1).toString());
        listItems.add(textOption2Index, getString(R.string.textOption2).toString());

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listItems);
        listView.setAdapter(adapter);
        registerForContextMenu(listView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.endWorkMenuItem:
                finish();
                break;
            case R.id.timeInput:
                return showTimePicker();
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    public boolean showTimePicker() {
        Log.d("[showTimePicker]", "showTimePicker");
        final TimePicker picker = new TimePicker(this);
        new AlertDialog.Builder(this)
                .setView(picker)
                .setNegativeButton("Cancel", null)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        showTimeDifferenceDialog(picker.getHour(), picker.getMinute());
                    }
                })
                .show();

        return true;
    }

    public void showTimeDifferenceDialog(int hour, int minute) {
        int diff = getTimeDifferenceInMinutes(hour, minute);
        String text = "Time difference between now and time selected is " + diff + " minutes";

        listItems.set(firstTextIndex, text);
        adapter.notifyDataSetChanged();

        new AlertDialog.Builder(this)
                .setTitle("Time difference")
                .setMessage(text)
                .setIcon(android.R.drawable.ic_dialog_info)
                .show();
    }

    private int getTimeDifferenceInMinutes(int h, int m) {
        int curH = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        int curM = Calendar.getInstance().get(Calendar.MINUTE);

        int curTime = curH * 60 + curM;
        int time = h * 60 + m;

        int diff = curTime - time;
        if(diff < 0)
            diff *= -1;

        return diff;
    }

    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_menu, menu);
    }

    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        TextView textViewPressed = (TextView) info.targetView;
        String selectedText = textViewPressed.getText().toString();

        switch (item.getItemId()) {
            case R.id.optionShowSymbolCount:
                return showSymbolCount(selectedText);
            case R.id.optionDisplaySymbolsPerSecond:
                this.textToDisplay = selectedText;
                this.displayTextCharIndex = 0;
                this.handler.removeCallbacks(textDisplayRunnable);
                this.textDisplayRunnable.run();
                return true;
            default:
                return false;
        }
    }

    private boolean showSymbolCount(String text) {
        int symbolCount = text.trim().length();
        String finalText = "Selected text symbol count = " + symbolCount;

        listItems.set(textOption1Index, finalText);
        adapter.notifyDataSetChanged();

        new AlertDialog.Builder(this)
                .setTitle("Symbol count")
                .setMessage(finalText)
                .setIcon(android.R.drawable.ic_dialog_info)
                .show();

        return true;
    }

    private void setupRunnable() {
        this.handler = new Handler();
        this.textDisplayInterval = 1000;
        this.displayTextCharIndex = 0;
        this.textToDisplay = "";
        textDisplayRunnable = new Runnable() {
            @Override
            public void run() {
                try {
                    displayText();
                } finally {
                    handler.postDelayed(textDisplayRunnable, textDisplayInterval);
                }
            }
        };
    }

    private void displayText() {
        if(!textToDisplay.isEmpty() && textToDisplay != "")
        {
            if(displayTextCharIndex >= textToDisplay.length())
                displayTextCharIndex = 0;

            String text = String.valueOf(textToDisplay.charAt(displayTextCharIndex));
            this.resultTextView.setText(text);
            displayTextCharIndex++;
        }
    }
}
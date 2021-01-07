package com.example.lab5;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NoACharFragment extends Fragment {

    private String finalText;
    private TextView textView;

    public NoACharFragment() {
    }

    public static NoACharFragment newInstance(String param1, String param2) {
        NoACharFragment fragment = new NoACharFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            String selectedText = getArguments().getString("bundleTextKey");
            this.finalText = computeFinalText(selectedText);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_no_a_char, container, false);
        this.textView = view.findViewById(R.id.textView);
        this.textView.setText(this.finalText);
        return view;
    }

    private String computeFinalText(String text) {
        String rText = "Tekstas:\n{text}\nTeksto ilgos: {length}\nBalsių skaičius: {bCount}\nPriebalsių skaičius: {pCount}\nDidžiųjų raidžių: {uCount}\nMažųjų raidžių: {lCount}";

        int bCount = getCharCount(text, "[AaĄąEeĘęĖėIiĮįYyOoUuŲųŪū]");
        int pCount = getCharCount(text, "[BbCcČčDdFfGgHhJjKkLlMmNnPpRrSsŠšTtVvZzŽž]");
        int uCount = getCharCount(text, "[A-Z]");
        int lCount = getCharCount(text, "[a-z]");

        rText = rText.replace("{text}", text);
        rText = rText.replace("{length}", String.valueOf(text.length()));
        rText = rText.replace("{bCount}", String.valueOf(bCount));
        rText = rText.replace("{pCount}", String.valueOf(pCount));
        rText = rText.replace("{uCount}", String.valueOf(uCount));
        rText = rText.replace("{lCount}", String.valueOf(lCount));

        return rText;
    }

    private int getCharCount(String text, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        int charCount = 0;

        while(matcher.find())
            charCount++;

        return charCount;
    }
}
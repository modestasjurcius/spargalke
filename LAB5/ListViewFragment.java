package com.example.lab5;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentResultListener;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ListViewFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private ListView listView;

    public ListViewFragment() {
    }

    public static ListViewFragment newInstance(String param1, String param2) {
        ListViewFragment fragment = new ListViewFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_listview, container, false);

        listView = (ListView) view.findViewById(R.id.listView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String selectedText = listView.getItemAtPosition(i).toString();
                openFragmentBySelectedText(selectedText);
                Log.d("ListViewFragment", "Selected text: " + selectedText);
            }
        });

        return view;
    }

    private void openFragmentBySelectedText(String text) {
        Fragment fragment = null;
        int count = GetACharCount(text);

        if(count > 0)
            fragment = new ACharFragment();
        else
            fragment = new NoACharFragment();

        Bundle bundle = new Bundle();
        bundle.putString("bundleTextKey", text);
        bundle.putInt("bundleCountKey", count);
        fragment.setArguments(bundle);
        getParentFragmentManager().setFragmentResult("requestKey", bundle);

        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.secondaryFragment, fragment);
        ft.addToBackStack("back");
        ft.commit();
    }

    private int GetACharCount(String text) {
        Pattern pattern = Pattern.compile("[aA]");
        Matcher matcher = pattern.matcher(text);
        int aCharCount = 0;

        while(matcher.find())
            aCharCount++;

        return aCharCount;
    }
}
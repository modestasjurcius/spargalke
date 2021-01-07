package com.example.kontrolinis2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


public class NavigationFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private Button btn1, btn2, btn3;
    private String url1, url2, url3;

    public NavigationFragment() {
    }

    public static NavigationFragment newInstance(String param1, String param2) {
        NavigationFragment fragment = new NavigationFragment();
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
            url1 = getArguments().getString("text1");
            url2 = getArguments().getString("text2");
            url3 = getArguments().getString("text3");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_navigation, container, false);

        btn1 = view.findViewById(R.id.btnAddress1);
        btn1.setText(url1);

        btn2 = view.findViewById(R.id.btnAddress2);
        btn2.setText(url2);

        btn3 = view.findViewById(R.id.btnAddress3);
        btn3.setText(url3);

        registerBtnClicks();

        return view;
    }

    private void registerBtnClicks() {
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBtnClick(view);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBtnClick(view);
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBtnClick(view);
            }
        });
    }

    private void onBtnClick(View v) {
        Button btn = (Button) v;
        String text = btn.getText().toString();

        FragmentManager fm = getFragmentManager();

        Fragment fragment = new WebViewFragment();
        Bundle bundle = new Bundle();
        bundle.putString("url", text);
        fragment.setArguments(bundle);
        //getFragmentManager().setFragmentResult("requestKey", bundle);


        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.webFragment, fragment);
        ft.addToBackStack("back");
        ft.commit();
    }
}
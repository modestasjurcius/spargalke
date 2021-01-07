package com.example.lab5;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ACharFragment extends Fragment {

    private String finalText;
    private TextView textView;

    public ACharFragment() {
    }

    public static ACharFragment newInstance() {
        ACharFragment fragment = new ACharFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getParentFragmentManager().setFragmentResultListener("requestKey", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String key, @NonNull Bundle bundle) {
                /*
                String text = bundle.getString("bundleTextKey");
                int count = bundle.getInt("bundleCountKey");
                String finalText = "Pasirinktas tekstas:\n{text}\nRaidžių A skaičius: {count}";
                finalText = finalText.replace("{text}", text);
                finalText = finalText.replace("{count", String.valueOf(count));

                TextView textview = (TextView) getActivity().findViewById(R.id.textView);
                textview.setText(finalText);
                 */
            }
        });

        if (getArguments() != null) {
            this.finalText = "Pasirinktas tekstas:\n{text}\nRaidžių A skaičius: {count}";
            String text = getArguments().getString("bundleTextKey");
            int count = getArguments().getInt("bundleCountKey");
            this.finalText = this.finalText.replace("{text}", text);
            this.finalText = this.finalText.replace("{count}", String.valueOf(count));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_a_char, container, false);
        this.textView = (TextView) view.findViewById(R.id.textView);
        this.textView.setText(this.finalText);
        return view;
    }
}
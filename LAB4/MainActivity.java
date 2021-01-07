package com.example.lab4;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private WebView webView;
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.webView = findViewById(R.id.webView);
        this.textView = findViewById(R.id.linkTextView);

        this.webView.setWebViewClient(new WebViewClient() {
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    view.loadUrl(url);
                return false;
            }
        });
        this.webView.getSettings().setJavaScriptEnabled(true);
    }

    public void onGo(View v) {
        String url = this.textView.getText().toString();
        if(url.isEmpty() || !url.contains("http")) {
            Toast.makeText(this, "Blogas adresas!", Toast.LENGTH_LONG).show();
            return;
        }

        this.webView.loadUrl(url);
    }
}
package vgtu.iip.lab2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class CalculateTextLengthActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("CalcTextLengthActivity", "onCreate");
        setContentView(R.layout.activity_trecia);

        TextView outputTextView = (TextView) findViewById(R.id.rezultatas);
        String text = getIntent().getStringExtra(Intent.EXTRA_TEXT);
        int count = text.split(" ").length;

        String output = getString(R.string.ivestasTestas);
        output = output.replace("{text}", text);
        output = output.replace("{count}", Integer.toString(count));

        outputTextView.setText(output);
    }

    protected  void onStart() {
        super.onStart();
        Log.i("CalcTextLengthActivity", "onStart");
    }

    protected  void onPause() {
        super.onPause();
        Log.i("CalcTextLengthActivity", "onPause");
    }

    protected  void onResume() {
        super.onResume();
        Log.i("CalcTextLengthActivity", "onResume");
    }

    protected  void onRestart() {
        super.onRestart();
        Log.i("CalcTextLengthActivity", "onRestart");
    }

    protected  void onStop() {
        super.onStop();
        Log.i("CalcTextLengthActivity", "onStop");
    }

    protected  void onDestroy() {
        super.onDestroy();
        Log.i("CalcTextLengthActivity", "onDestroy");
    }
}

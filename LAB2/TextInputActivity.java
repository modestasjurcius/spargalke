package vgtu.iip.lab2;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class TextInputActivity extends AppCompatActivity {

    private EditText inputField;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("TextInputActivity", "onCreate");
        setContentView(R.layout.activity_antra);
        inputField = (EditText) findViewById(R.id.ivedimui);
    }

    public void onSaveInputResult(View w){
        String text = inputField.getText().toString();
        Intent resultIntent = new Intent();
        resultIntent.putExtra(Intent.EXTRA_TEXT, text);
        setResult(Activity.RESULT_OK, resultIntent);
        finish();
    }

    protected  void onStart() {
        super.onStart();
        Log.i("TextInputActivity", "onStart");
    }

    protected  void onPause() {
        super.onPause();
        Log.i("TextInputActivity", "onPause");
    }

    protected  void onResume() {
        super.onResume();
        Log.i("TextInputActivity", "onResume");
    }

    protected  void onRestart() {
        super.onRestart();
        Log.i("TextInputActivity", "onRestart");
    }

    protected  void onStop() {
        super.onStop();
        Log.i("TextInputActivity", "onStop");
    }

    protected  void onDestroy() {
        super.onDestroy();
        Log.i("TextInputActivity", "onDestroy");
    }
}

package vgtu.iip.lab2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    final static int TEXT_INPUT_REQUEST_CODE = 1;
    private TextView textOutputField;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("MainActivity", "onCreate");
        setContentView(R.layout.activity_pirma);
        textOutputField = (TextView) findViewById(R.id.tekstas);
    }

    public void onIputTextBtnClick(View w) {
        Intent i = new Intent(getApplicationContext(), TextInputActivity.class);
        startActivityForResult(i, TEXT_INPUT_REQUEST_CODE);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == TEXT_INPUT_REQUEST_CODE) {
            Log.i("[MainActivity]", "onActivityResult");
            textOutputField.setText(data.getStringExtra(Intent.EXTRA_TEXT));
        }
    }

    public void onInputInformation(View w){
        String text = textOutputField.getText().toString();
        Intent intent = new Intent(getBaseContext(), CalculateTextLengthActivity.class);
        intent.putExtra(Intent.EXTRA_TEXT, text);
        startActivity(intent);
    }

    public void onSendInformation(View w){
        String textToSend = textOutputField.getText().toString();

        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, textToSend);
        sendIntent.setType("text/plain");

        Intent shareIntent = Intent.createChooser(sendIntent, null);
        startActivity(shareIntent);
    }
}

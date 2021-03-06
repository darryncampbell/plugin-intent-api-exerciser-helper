package com.darryncampbell.pluginintentapiexerciserhelper;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button btnStartActivityForResult = (Button)findViewById(R.id.btnStartActivityForResult);
        btnStartActivityForResult.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent startPluginIntentAPIExerciserForResult = new Intent();
                startPluginIntentAPIExerciserForResult.setClassName("com.darryncampbell.cordova.plugin.intent.api.exerciser", "com.darryncampbell.cordova.plugin.intent.api.exerciser.MainActivity");
                try {
                    startActivityForResult(startPluginIntentAPIExerciserForResult, REQUEST_CODE);
                }
                catch (ActivityNotFoundException e)
                {
                    Toast.makeText(getApplicationContext(), "Intent API Exerciser app is not installed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE)
        {
            TextView txtOutput = (TextView)findViewById(R.id.txtOutput);
            //  We have received the result successfully
            if (data != null && data.hasExtra("Test Intent"))
            {
                String output = "";
                if (data.hasExtra("Test Intent"))
                    output += "String: " + data.getStringExtra("Test Intent");
                if (data.hasExtra("Test Intent int"))
                    output += ", Int: " + data.getIntExtra("Test Intent int", 0);
                if (data.hasExtra("Test Intent bool"))
                    output += ", Bool: " + data.getBooleanExtra("Test Intent bool", false);
                if (data.hasExtra("Test Intent double"))
                    output += ", Float: " + data.getDoubleExtra("Test Intent double", 0.0f);
                txtOutput.setText("Received Result from StartActivity.  Content: " + output);
            }
            else
            {
                txtOutput.setText("Received Result from StartActivity: No data in intent");
            }
        }
    }
}

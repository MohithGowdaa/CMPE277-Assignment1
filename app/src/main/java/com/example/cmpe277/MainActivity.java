package com.example.cmpe277;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String PREFS_NAME = "MyPrefs";
    private static final String KEY_COUNTER = "counter";

    private int counter = 0;
    private TextView counterTextView;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        counterTextView = findViewById(R.id.text_counter);
        sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);

        // Retrieve the counter value from SharedPreferences
        counter = sharedPreferences.getInt(KEY_COUNTER, 0);
        updateCounterText();

        Button buttonB = findViewById(R.id.button_activity_b);
        Button buttonC = findViewById(R.id.button_activity_c);
        Button buttonDialog = findViewById(R.id.button_dialog);
        Button buttonCloseApp = findViewById(R.id.button_close_app);

        buttonB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter += 5;
                updateCounterText();
                startActivity(new Intent(MainActivity.this, ActivityB.class));
            }
        });

        buttonC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter += 10;
                updateCounterText();
                startActivity(new Intent(MainActivity.this, ActivityC.class));
            }
        });

        buttonDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Display a simple dialog
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Simple Dialog")
                        .setMessage("This is a simple dialog box.")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // You can perform actions here when "OK" is clicked
                            }
                        });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        buttonCloseApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Close the app
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Save the counter value to SharedPreferences
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(KEY_COUNTER, counter);
        editor.apply();
    }

    private void updateCounterText() {
        counterTextView.setText("Counter: " + counter);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        // You can capture the counter here
        updateCounterText();
    }
}

package com.example.cs2340a_team23.viewmodels;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.content.Intent;

import com.example.cs2340a_team23.R;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button startButton = findViewById(R.id.startButton);
        Button quitButton = findViewById(R.id.quitButton);

        startButton.setOnClickListener(view -> {
            Intent gameConfig = new Intent(MainActivity.this, GameConfigurationActivity.class);
            startActivity(gameConfig);
            finish();
        });
        quitButton.setOnClickListener(view -> {
            finish();
            System.exit(0);
        });
    }
}
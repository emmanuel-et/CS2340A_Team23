package com.example.cs2340a_team23.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.cs2340a_team23.R;

public class EndActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);

        Button restartButton = findViewById(R.id.restartButton);
        restartButton.setOnClickListener(view -> {
//            TODO: Call player reset method
            Intent gameConfig = new Intent(EndActivity.this, GameConfigurationActivity.class);
            startActivity(gameConfig);
            finish();
        });
    }
}
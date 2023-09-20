package com.example.cs2340a_team23;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class GameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Button endButton = findViewById(R.id.endButton);

        endButton.setOnClickListener(view -> {
            Intent endScreen = new Intent(GameActivity.this, EndActivity.class);
            startActivity(gameConfig);
            finish();
        });
    }
}

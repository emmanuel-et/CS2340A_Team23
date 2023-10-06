package com.example.cs2340a_team23.viewmodels;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cs2340a_team23.R;

public class GameActivityRoom2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_2);

        Button prevButton = findViewById(R.id.prevbutton);
        Button nextButton = findViewById(R.id.nextbutton2);

        prevButton.setOnClickListener(view -> {
            Intent room1Screen = new Intent(GameActivityRoom2.this, GameActivityRoom1.class);
            startActivity(room1Screen);
            finish();
        });

        nextButton.setOnClickListener(view -> {
            Intent room3Screen = new Intent(GameActivityRoom2.this, GameActivityRoom3.class);
            startActivity(room3Screen);
            finish();
        });
    }
}

package com.example.cs2340a_team23.viewmodels;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cs2340a_team23.R;

public class GameActivityRoom2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_2);

        Button prevButton = findViewById(R.id.prevbutton);

        prevButton.setOnClickListener(view -> {
            Intent endScreen = new Intent(GameActivityRoom2.this, GameActivityRoom1.class);
            setContentView(R.layout.activity_room_2);
            startActivity(endScreen);
            finish();
        });
    }
}

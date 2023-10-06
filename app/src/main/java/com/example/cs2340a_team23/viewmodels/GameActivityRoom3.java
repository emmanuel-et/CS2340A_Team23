package com.example.cs2340a_team23.viewmodels;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cs2340a_team23.R;

public class GameActivityRoom3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_3);

        TextView playerName = findViewById(R.id.playerName);
        playerName.setText(getIntent().getStringExtra("playerName"));

        TextView playerHealth = findViewById(R.id.playerHealth);
        playerHealth.setText("Health: " + getIntent().getStringExtra("health"));

        TextView gameDifficulty = findViewById(R.id.gameDifficulty);
        gameDifficulty.setText(getIntent().getStringExtra("difficulty"));

        ImageView playerSprite = findViewById(R.id.playerSprite);
        String spriteName = getIntent().getStringExtra("sprite");
        int resID = getResources().getIdentifier(spriteName, "drawable", getPackageName());
        playerSprite.setImageResource(resID);

        Button endButton = findViewById(R.id.endButton);
        Button prevButton = findViewById(R.id.prevbutton2);

        prevButton.setOnClickListener(view -> {
            Intent room2Screen = new Intent(GameActivityRoom3.this, GameActivityRoom2.class);
            startActivity(room2Screen);
            finish();
        });

        endButton.setOnClickListener(view -> {
            Intent endScreen = new Intent(GameActivityRoom3.this, EndActivity.class);
            startActivity(endScreen);
            finish();
        });
    }
}

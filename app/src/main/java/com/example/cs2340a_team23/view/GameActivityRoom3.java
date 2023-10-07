package com.example.cs2340a_team23.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cs2340a_team23.R;
import com.example.cs2340a_team23.model.Player;
import com.example.cs2340a_team23.viewModel.PlayerViewModel;

public class GameActivityRoom3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_3);

        Player player = Player.getPlayer();


        TextView playerName = findViewById(R.id.playerName);
        playerName.setText(player.getPlayerName());

        TextView playerHealth = findViewById(R.id.playerHealth);
        playerHealth.setText("Health: " + Integer.toString(player.getHealth()));

        TextView gameDifficulty = findViewById(R.id.gameDifficulty);
        gameDifficulty.setText(getIntent().getStringExtra("difficulty"));

        ImageView playerSprite = findViewById(R.id.playerSprite);
        String spriteName = player.getSprite();

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

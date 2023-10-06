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

public class GameActivity extends AppCompatActivity {
    private PlayerViewModel playerViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Player player = Player.getPlayer();


        TextView playerName = findViewById(R.id.playerName);
        playerName.setText(player.getPlayerName());

        TextView playerHealth = findViewById(R.id.playerHealth);
        playerHealth.setText(player.getHealth());

        TextView gameDifficulty = findViewById(R.id.gameDifficulty);
        gameDifficulty.setText(getIntent().getStringExtra("difficulty"));

        ImageView playerSprite = findViewById(R.id.playerSprite);
        String spriteName = getIntent().getStringExtra("sprite");
        int resID = getResources().getIdentifier(spriteName, "drawable", getPackageName());
        playerSprite.setImageResource(resID);
        Button endButton = findViewById(R.id.endButton);
        playerViewModel.initializePlayer(player.getPlayerName(), player.getHealth(),
                player.getSprite());
        endButton.setOnClickListener(view -> {
            Intent endScreen = new Intent(GameActivity.this, EndActivity.class);
            startActivity(endScreen);
            finish();
        });
    }
}

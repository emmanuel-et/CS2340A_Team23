package com.example.cs2340a_team23.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.lifecycle.ViewModelProvider;

import com.example.cs2340a_team23.R;
import com.example.cs2340a_team23.model.Player;
import com.example.cs2340a_team23.viewModel.PlayerViewModel;

public class GameActivity extends AppCompatActivity {
    private PlayerViewModel playerViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        //playerViewModel = new ViewModelProvider(this).get(PlayerViewModel.class);


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
        playerViewModel.initializePlayer(Player.getPlayer().getPlayerName(), Player.getPlayer().getHealth(), Player.getPlayer().getSprite());
        //Player player = Player.getPlayer();


        endButton.setOnClickListener(view -> {
            Intent endScreen = new Intent(GameActivity.this, EndActivity.class);
            startActivity(endScreen);
            finish();
        });
    }
}

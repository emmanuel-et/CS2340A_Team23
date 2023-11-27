package com.example.cs2340a_team23.view;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.content.Intent;
import android.widget.EditText;
import android.widget.RadioGroup;
import androidx.lifecycle.ViewModelProvider;
import com.example.cs2340a_team23.R;
import com.example.cs2340a_team23.model.GameState;
import com.example.cs2340a_team23.viewModel.PlayerViewModel;
import com.example.cs2340a_team23.model.Player;

import android.view.KeyEvent;
import android.view.View;

import java.time.LocalTime;

public class GameConfigurationActivity extends AppCompatActivity {
    private PlayerViewModel playerViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_configuration);
        playerViewModel = new ViewModelProvider(this).get(PlayerViewModel.class);
        Button playButton = findViewById(R.id.playButton);
        EditText nameInput = findViewById(R.id.nameInput);
        RadioGroup difficultyRadioGroup = findViewById(R.id.difficultyRadioGroup);
        RadioGroup spriteRadioGroup = findViewById(R.id.spriteRadioGroup);

        playButton.setOnClickListener(view -> {
            String playerName = nameInput.getText().toString().trim();
            if (!playerName.isEmpty()) {

                int selectedDifficulty = difficultyRadioGroup.getCheckedRadioButtonId();
                int selectedSprite = spriteRadioGroup.getCheckedRadioButtonId();
                String difficulty;
                int health = 300;
                if (selectedDifficulty == R.id.easyRadioButton) {

                    difficulty = "Easy";

                } else if (selectedDifficulty == R.id.mediumRadioButton) {
                    difficulty = "Medium";

                } else if (selectedDifficulty == R.id.hardRadioButton) {
                    difficulty = "hard";

                } else {
                    difficulty = "superHard";

                }


                Intent gamePlay = new Intent(GameConfigurationActivity.this,
                        GameActivityRoom1.class);


                int selectedSpriteId = spriteRadioGroup.getCheckedRadioButtonId();
                String sprite = "";
                switch (selectedSpriteId) {
                case R.id.megamanRadioButton:
                    sprite = "megaman";
                    break;
                case R.id.marioRadioButton:
                    sprite = "mario";
                    break;
                case R.id.sonicRadioButton:
                    sprite = "sonic";
                    break;
                default:
                    sprite = "";
                    break;
                }
                Player player = Player.getPlayer();
                player.initializePlayer(playerName, health, sprite);
                GameState gameState = GameState.getGameState();
                gameState.setDifficulty(difficulty);
                gameState.setTimeStart(LocalTime.now());
                startActivity(gamePlay);
                finish();
            } else {
                nameInput.setError("Player name is invalid, cannot be"
                        + " null, empty, or white space!");
            }

        });
    }

}

package com.example.cs2340a_team23.view;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.content.Intent;
import android.widget.EditText;
import android.widget.RadioGroup;
import androidx.lifecycle.ViewModelProvider;
import com.example.cs2340a_team23.R;
import com.example.cs2340a_team23.viewModel.PlayerViewModel;
import com.example.cs2340a_team23.model.Player;

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
                int health;
                if (selectedDifficulty == R.id.easyRadioButton) {

                    difficulty = "Easy";
                    health = 300;

                } else if (selectedDifficulty == R.id.mediumRadioButton) {
                    health = 200;
                    difficulty = "Medium";

                } else if (selectedDifficulty == R.id.hardRadioButton) {
                    health = 100;
                    difficulty = "hard";

                } else {
                    health = 75;
                    difficulty = "superHard";

                }
                Intent gamePlay = new Intent(GameConfigurationActivity.this, GameActivity.class);


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
                }
                playerViewModel.initializePlayer(playerName, health, sprite);
                Player player = Player.getPlayer();
                startActivity(gamePlay);
                finish();
            } else {
                nameInput.setError("Player name is invalid, cannot be"
                        + " null, empty, or white space!");
            }

        });
    }
}

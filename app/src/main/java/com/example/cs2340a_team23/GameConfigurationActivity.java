package com.example.cs2340a_team23;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.content.Intent;
import android.widget.EditText;
import android.widget.RadioGroup;

public class GameConfigurationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_configuration);

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

                gamePlay.putExtra("health", String.valueOf(health));
                gamePlay.putExtra("playerName", playerName);
                if (selectedSprite == R.id.megamanRadioButton) {
                    gamePlay.putExtra("sprite", "megaman");
                } else if (selectedSprite == R.id.marioRadioButton) {
                    gamePlay.putExtra("sprite", "mario");
                } else {
                    gamePlay.putExtra("sprite", "sonic");
                }
                gamePlay.putExtra("difficulty", difficulty);
                startActivity(gamePlay);
                finish();
            } else {
                nameInput.setError("Player name is invalid, cannot be"
                        + " null, empty, or white space!");
            }

        });
    }
}

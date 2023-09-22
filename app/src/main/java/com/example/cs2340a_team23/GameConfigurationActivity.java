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
        // Here I have an if statement to see if the name is valid if not throw an error
        // Then there are many if else for the difficulty and how the data will be changed
        playButton.setOnClickListener(view -> {
            String playerName = nameInput.getText().toString().trim();
            if (!playerName.isEmpty()) {
                // here a sprite name is needed still for the code
                int selectedDifficulty = difficultyRadioGroup.getCheckedRadioButtonId();
                String difficulty;

                if(selectedDifficulty == R.id.easyRadioButton) {
                    //do we need to keep track of the difficulty?? easy to delete if need be
                    difficulty = "Easy";
                    int easyHealth = 300;
                    Player player = new Player(easyHealth, playerName, "sprite");

                } else if(selectedDifficulty == R.id.mediumRadioButton) {
                    int mediumHealth = 200;
                    difficulty = "Medium";
                    Player player = new Player(mediumHealth, playerName, "sprite");

                } else if(selectedDifficulty == R.id.hardRadioButton) {
                    int hardHealth = 100;
                    difficulty = "hard";
                    Player player = new Player(hardHealth, playerName, "sprite");

                } else {
                    int superHardHealth = 75;
                    difficulty = "superHard";
                    Player player = new Player(superHardHealth, playerName, "sprite");

                }
                Intent gamePlay = new Intent(GameConfigurationActivity.this, GameActivity.class);
                gamePlay.putExtra("Player Name", playerName);
                // Once again is the difficulty in need of being tracked??
                gamePlay.putExtra("Difficulty", difficulty);
                startActivity(gamePlay);
                finish();
            } else {
                // error message for invalid name
                nameInput.setError("Player name is invalid, cannot be null, empty, or white space!");
            }

        });
    }
}

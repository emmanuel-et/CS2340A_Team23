package com.example.cs2340a_team23;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.content.Intent;
import android.widget.EditText;

public class GameConfigurationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_configuration);

        Button playButton = findViewById(R.id.playButton);
        EditText nameInput = findViewById(R.id.nameInput);


        playButton.setOnClickListener(view -> {
            String playerName = nameInput.getText().toString().trim();
            if (!playerName.isEmpty()) {
                Intent gamePlay = new Intent(GameConfigurationActivity.this, GameActivity.class);
                gamePlay.putExtra("Player Name", playerName);
                startActivity(gamePlay);
                finish();
            } else {
                // error message for invalid name
                nameInput.setError("Player name is invalid, cannot be null, empty, or white space!");
            }

        });
    }
}

package com.example.cs2340a_team23;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.content.Intent;
public class GameConfigurationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_configuration);

        Button playButton = findViewById(R.id.playButton);

        playButton.setOnClickListener(view -> {
            Intent gamePlay = new Intent(GameConfigurationActivity.this, GameActivity.class);
            startActivity(gamePlay);
            finish();
        });
    }
}

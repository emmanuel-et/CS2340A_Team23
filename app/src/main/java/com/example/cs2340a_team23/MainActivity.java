package com.example.cs2340a_team23;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.content.Intent;
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button startButton = findViewById(R.id.startButton);
        Button quitButton = findViewById(R.id.quitButton);

        startButton.setOnClickListener(view -> {
            // Create an Intent to start the GameActivity
            Intent game_config = new Intent(MainActivity.this, GameConfigurationActivity.class);//replace with nme of class

            // Optionally, you can add extra data to the intent
            // intent.putExtra("key", "value");

            // Start the GameActivity
            startActivity(game_config);
            finish();
        });

        quitButton.setOnClickListener(view -> {
            // Finish the current activity to simulate quitting the game
            finish();
            System.exit(0);
        });


    }


}
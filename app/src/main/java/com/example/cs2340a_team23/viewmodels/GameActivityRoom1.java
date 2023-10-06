package com.example.cs2340a_team23.viewmodels;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cs2340a_team23.R;

public class GameActivityRoom1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_1);

        Button nextButton = findViewById(R.id.nextbutton);

        nextButton.setOnClickListener(view -> {
            Intent room2Screen = new Intent(GameActivityRoom1.this, GameActivityRoom2.class);
            startActivity(room2Screen);
            finish();
        });
    }
}

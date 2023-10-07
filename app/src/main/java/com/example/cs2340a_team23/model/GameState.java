package com.example.cs2340a_team23.model;

import java.util.Timer;
import java.util.TimerTask;
import java.time.Instant;
import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;

public class GameState {

    private Timer timerToUpdateScore;
    private Instant gameStartTime;
    private AtomicInteger playerScore;

    private static GameState gameState;

    private GameState() {
        playerScore = new AtomicInteger(0);

        timerToUpdateScore = new Timer(true);

    }

    public static GameState getGameState() {
        if (gameState == null) {
            gameState = new GameState();
        }
        return gameState;
    }

    private void startScoreTimer() {
        timerToUpdateScore.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                calculateScore();
            }
        }, 0,1000); //updates every second
    }

    private void stopScoreTimer() {
        timerToUpdateScore.cancel();

    }

    private void calculateScore() {
        if (gameStartTime != null) {
            Instant presentTime = Instant.now();

            Duration duration = Duration.between(gameStartTime, presentTime);

            long seconds = duration.getSeconds();

            int gameScore = (int) (seconds * 5); // 5 points per second we can change later
            playerScore.set(gameScore);
        }


    }

    public void startGame() {
        gameStartTime = Instant.now();

        startScoreTimer();
    }

    public void endGame() {
        stopScoreTimer();
        calculateScore();

    }

    public int getPlayerScore() {

        return playerScore.get();
    }
}



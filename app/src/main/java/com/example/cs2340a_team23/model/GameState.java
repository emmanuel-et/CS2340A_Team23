package com.example.cs2340a_team23.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Timer;
import java.util.TimerTask;

public class GameState {
    private static GameState gameState;
    private int score;
    private LocalTime timeStart;
    private LocalTime timeEnd;
    private LocalDate date;
    private Timer scoreTimer;
    private String difficulty;



    private GameState() {
        this.score = 1000;
        this.timeStart = null;
        this.timeEnd = null;
        this.scoreTimer = null;
        this.difficulty = null;
    }
    public static GameState getGameState() {
        if (gameState == null) {
            gameState = new GameState();
        }
        return gameState;
    }
    public static void resetGameState() {
        gameState = null;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public LocalTime getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(LocalTime timeStart) {
        this.timeStart = timeStart;
    }

    public LocalTime getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(LocalTime timeEnd) {
        this.timeEnd = timeEnd;
    }

    public LocalDate getDate() { return date; }

    public void setDate(LocalDate date) { this.date = date; }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public void startScoreTimer() {
        this.scoreTimer = new Timer();
        scoreTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                score -= 5;
            }
        }, 0, 5000);
    }

    public void stopScoreTimer() {
        if (this.scoreTimer != null) {
            scoreTimer.cancel();
            scoreTimer = null;
        }
    }
}

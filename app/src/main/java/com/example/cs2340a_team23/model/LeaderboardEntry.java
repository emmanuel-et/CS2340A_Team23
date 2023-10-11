package com.example.cs2340a_team23.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class LeaderboardEntry {

    private String name;
    private int score;

    private LocalDate date;
    private LocalTime endTime;

    public LeaderboardEntry(String name, LocalDate date, LocalTime endTime, int score) {
        this.name = name;
        this.date = date;
        this.endTime = endTime;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getEndTime() {
        return endTime;
    }


}

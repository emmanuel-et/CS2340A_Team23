package com.example.cs2340a_team23.model;

public interface MoveBehavior {
    abstract float[] move(float xPos, float yPos, String direction, int screenWidth, int screenHeight,
                       int spriteWidth, int spriteHeight);
}

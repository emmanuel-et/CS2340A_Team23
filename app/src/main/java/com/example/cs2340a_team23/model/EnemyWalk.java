package com.example.cs2340a_team23.model;

public class EnemyWalk implements MoveBehavior {
    private int moveDistance = 0;

    public EnemyWalk(int speed) {
        moveDistance = speed; }
    @Override
    public float[] move(float xPos, float yPos, String direction, int screenWidth, int screenHeight,
                     int spriteWidth, int spriteHeight) {
        if (direction == "left") {
            if (xPos - moveDistance >= 0) {
                xPos -= moveDistance;
            }
        } else if (direction == "right") {
            if (xPos + moveDistance + spriteWidth <= screenWidth) {
                xPos += moveDistance;
            }
        } else if (direction == "up") {
            if (yPos - moveDistance >= 0) {
                yPos -= moveDistance;
            }
        } else {
            if (yPos + moveDistance + spriteHeight <= screenHeight) {
                yPos += moveDistance;
            }
        }
        float[] newPos = {xPos, yPos};
        return newPos;
    }
}

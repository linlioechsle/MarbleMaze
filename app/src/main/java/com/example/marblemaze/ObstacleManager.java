package com.example.marblemaze;

import android.graphics.Canvas;

import java.util.ArrayList;

public class ObstacleManager {
    // higher index and higher y value means lower on the screen
    private ArrayList<Obstacle> obstacles;
    private int playerGap;
    private int obstacleGap;
    private int obstacleHeight;
    private int color;
    private long startTime;


    public ObstacleManager(int playerGap, int obstacleGap, int obstacleHeight, int color) {
        this.playerGap = playerGap;
        this.obstacleGap = obstacleGap;
        this.obstacleHeight = obstacleHeight;
        this.color = color;

        startTime = System.currentTimeMillis();

        obstacles = new ArrayList<>();

        populateObstacles();
    }

    public boolean playerCollision(MarblePlayer player) {
        for (Obstacle ob : obstacles) {
            if (ob.playerCollision(player))
                return true;
        }
        return false;
    }

    private void populateObstacles() {
        int currY = -5*Constants.SCREEN_HEIGHT/4;

        while(currY < 0) {
            int xOrigin = (int)(Math.random()*(Constants.SCREEN_WIDTH - playerGap));
            obstacles.add(new Obstacle(obstacleHeight, color, xOrigin, currY, playerGap));
            currY += obstacleHeight + obstacleGap;

        }
    }

    public void update() {
        int elapsedTime = (int)(System.currentTimeMillis() - startTime);
        startTime = System.currentTimeMillis();
        float speed = Constants.SCREEN_HEIGHT/10000.0f;

        for(Obstacle ob: obstacles) {
            ob.incrementY(speed * elapsedTime);
        }

        if(obstacles.get(obstacles.size() - 1).getRectangle().top >= Constants.SCREEN_HEIGHT) {
            int xOrigin = (int)(Math.random()*(Constants.SCREEN_WIDTH - playerGap));
            obstacles.add(0, new Obstacle(obstacleHeight, color, xOrigin, obstacles.get(0).getRectangle().top - obstacleHeight + obstacleGap, playerGap));
            obstacles.remove(obstacles.size() - 1);

        }
    }

    public void draw(Canvas canvas) {
        for(Obstacle ob : obstacles) {
            ob.draw(canvas);
        }
    }
}

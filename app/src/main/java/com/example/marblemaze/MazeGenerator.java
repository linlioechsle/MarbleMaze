package com.example.marblemaze;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import java.util.ArrayList;

public class MazeGenerator {
    // higher index and higher y value means lower on the screen
    private ArrayList<Obstacle> obstacles;
    private int playerGap;
    private int obstacleGap;
    private int obstacleHeight;
    private int color;
    private long startTime;

    private int rows;
    private int columns;

    // maze stuff
    private Paint line;



    public MazeGenerator(int playerGap, int obstacleGap, int obstacleHeight, int color) {
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
        // currY = -5*Constants.SCREEN_HEIGHT/4;


        obstacles.add(new Obstacle(280,0, 287, 300));
        obstacles.add(new Obstacle(150, 300, 287, 307));
        obstacles.add(new Obstacle(0, 150, 100, 157));
        obstacles.add(new Obstacle(150, 300, 157, 500));
        obstacles.add(new Obstacle(150, 500, 400, 507));
        obstacles.add(new Obstacle(0, 700, 800,707)); // long horiz
        obstacles.add(new Obstacle(800,250,807, 707));
        obstacles.add(new Obstacle(500, 250, 807, 257));
        //obstacles.add(new Obstacle());


    }

    public void generateMaze() {

    }

    public void update() {
        if (startTime < Constants.INIT_TIME)
            startTime = Constants.INIT_TIME;

   /*     int elapsedTime = (int)(System.currentTimeMillis() - startTime);
        startTime = System.currentTimeMillis();
        float speed = Constants.SCREEN_HEIGHT/10000.0f;

        for(Obstacle ob: obstacles) {
            ob.incrementY(speed * elapsedTime);
        }*/

       // if(obstacles.get(obstacles.size() - 1).getRectangle().top >= Constants.SCREEN_HEIGHT) {
        //    int xOrigin = (int)(Math.random()*(Constants.SCREEN_WIDTH - playerGap));
            //obstacles.add(0, new Obstacle(obstacleHeight, color, Constants.SCREEN_HEIGHT/2, obstacles.get(0).getRectangle().top - obstacleHeight + obstacleGap, playerGap));
        //    obstacles.remove(obstacles.size() - 1);

        //}
    }


    public void draw(Canvas canvas) {
        for(Obstacle ob : obstacles) {
            ob.draw(canvas);
        }
    }
}

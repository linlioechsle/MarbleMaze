package com.example.marblemaze;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import java.util.ArrayList;

public class MazeGenerator {
    // higher index and higher y value means lower on the screen
    private ArrayList<Obstacle> obstacles;
    private int color;
    private long startTime;

    private int rows;
    private int columns;

    // maze stuff
    private Paint line;



    public MazeGenerator(int color) {
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
        obstacles.add(new Obstacle(280,0, 287, 300));
        obstacles.add(new Obstacle(150, 300, 287, 307));
        obstacles.add(new Obstacle(0, 150, 100, 157));
        obstacles.add(new Obstacle(150, 300, 157, 500));
        obstacles.add(new Obstacle(150, 500, 400, 507));
        obstacles.add(new Obstacle(0, 700, 800,707)); // long horiz
        obstacles.add(new Obstacle(250, 700, 257, 1000));
        obstacles.add(new Obstacle(650, 700, 657,950));
        obstacles.add(new Obstacle(800,250,807, 707));
        obstacles.add(new Obstacle(500, 250, 807, 257));
        obstacles.add(new Obstacle(Constants.SCREEN_WIDTH-50, 350, Constants.SCREEN_WIDTH-1, 357));
        obstacles.add(new Obstacle(Constants.SCREEN_WIDTH-100, 800, Constants.SCREEN_WIDTH-1, 807));
        obstacles.add(new Obstacle(Constants.SCREEN_WIDTH-100, 800, Constants.SCREEN_WIDTH-107, 850));
        obstacles.add(new Obstacle(400, 1000, 407, 1700)); // long vert
        obstacles.add(new Obstacle(250, 1700, 407, 1707));
        obstacles.add(new Obstacle(900, 1800, 907, Constants.SCREEN_HEIGHT-1));
        obstacles.add(new Obstacle(Constants.SCREEN_WIDTH-250, 1100, Constants.SCREEN_HEIGHT-1, 1107));
        obstacles.add(new Obstacle(Constants.SCREEN_WIDTH-600, 1400, Constants.SCREEN_HEIGHT-1, 1407));
        obstacles.add(new Obstacle(300, Constants.SCREEN_HEIGHT-100, 307, Constants.SCREEN_HEIGHT-1));
        obstacles.add(new Obstacle(100, Constants.SCREEN_HEIGHT-100, 307, Constants.SCREEN_HEIGHT-107));
        obstacles.add(new Obstacle(100, Constants.SCREEN_HEIGHT-400, 107, Constants.SCREEN_HEIGHT-100));
        obstacles.add(new Obstacle(150, Constants.SCREEN_HEIGHT-600,250, Constants.SCREEN_HEIGHT-607));
        obstacles.add(new Obstacle(Constants.SCREEN_WIDTH-300, 1600, Constants.SCREEN_WIDTH-1, 1607));

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

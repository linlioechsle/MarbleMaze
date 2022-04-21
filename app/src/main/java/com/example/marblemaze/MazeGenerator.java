package com.example.marblemaze;

import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.ArrayList;

public class MazeGenerator {
    // higher index and higher y value means lower on the screen
    private ArrayList<MazeWall> walls;
    private int color;
    private long startTime;

    private int rows;
    private int columns;

    // maze stuff
    private Paint line;



    public MazeGenerator(int color) {
        this.color = color;

        startTime = System.currentTimeMillis();

        walls = new ArrayList<>();

        populateObstacles();
    }

    public boolean playerCollision(MarblePlayer player) {
        for (MazeWall wall : walls) {
            if (wall.playerCollision(player))
                return true;
        }
        return false;
    }


    private void populateObstacles() {
        walls.add(new MazeWall(280,0, 287, 300));
        walls.add(new MazeWall(150, 300, 287, 307));
        walls.add(new MazeWall(0, 150, 100, 157));
        walls.add(new MazeWall(150, 300, 157, 500));
        walls.add(new MazeWall(150, 500, 400, 507));
        walls.add(new MazeWall(0, 700, 800,707)); // long horiz
        walls.add(new MazeWall(250, 700, 257, 1000));
        walls.add(new MazeWall(650, 700, 657,950));
        walls.add(new MazeWall(800,250,807, 707));
        walls.add(new MazeWall(500, 250, 807, 257));
        walls.add(new MazeWall(Constants.SCREEN_WIDTH-50, 350, Constants.SCREEN_WIDTH-1, 357));
        walls.add(new MazeWall(Constants.SCREEN_WIDTH-100, 800, Constants.SCREEN_WIDTH-1, 807));
        walls.add(new MazeWall(Constants.SCREEN_WIDTH-100, 800, Constants.SCREEN_WIDTH-107, 850));
        walls.add(new MazeWall(400, 1000, 407, 1700)); // long vert
        walls.add(new MazeWall(250, 1700, 407, 1707));
        walls.add(new MazeWall(900, 1800, 907, Constants.SCREEN_HEIGHT-1));
        walls.add(new MazeWall(Constants.SCREEN_WIDTH-250, 1100, Constants.SCREEN_HEIGHT-1, 1107));
        walls.add(new MazeWall(Constants.SCREEN_WIDTH-600, 1400, Constants.SCREEN_HEIGHT-1, 1407));
        walls.add(new MazeWall(300, Constants.SCREEN_HEIGHT-100, 307, Constants.SCREEN_HEIGHT-1));
        walls.add(new MazeWall(100, Constants.SCREEN_HEIGHT-100, 307, Constants.SCREEN_HEIGHT-107));
        walls.add(new MazeWall(100, Constants.SCREEN_HEIGHT-400, 107, Constants.SCREEN_HEIGHT-100));
        walls.add(new MazeWall(150, Constants.SCREEN_HEIGHT-600,250, Constants.SCREEN_HEIGHT-607));
        walls.add(new MazeWall(Constants.SCREEN_WIDTH-300, 1600, Constants.SCREEN_WIDTH-1, 1607));

    }

    public void update() {
        if (startTime < Constants.INIT_TIME)
            startTime = Constants.INIT_TIME;
    }


    public void draw(Canvas canvas) {
        for(MazeWall wall : walls) {
            wall.draw(canvas);
        }
    }
}

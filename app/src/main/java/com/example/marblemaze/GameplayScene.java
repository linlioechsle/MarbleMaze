package com.example.marblemaze;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.MotionEvent;

public class GameplayScene implements Scene {
    private MarblePlayer player;
    private Point playerPoint;
    private MazeGenerator generator;

    private boolean movingPlayer = false;

    private boolean gameOver = false;
    private long gameOverTime;

    private SensorData sensorData;
    private long frameTime;

    public GameplayScene() {
        sensorData = new SensorData();
        sensorData.register();
        player = new MarblePlayer(new Rect(50, 50, 100, 100));
        playerPoint = new Point(Constants.SCREEN_WIDTH/2, 3*Constants.SCREEN_HEIGHT/4);
        player.update(playerPoint);
        player.update();

        generator = new MazeGenerator(200, 350, 10, Color.BLACK);
        frameTime = System.currentTimeMillis();


    }

    public void reset() {
        playerPoint = new Point(Constants.SCREEN_WIDTH/2, 3*Constants.SCREEN_HEIGHT/4);
        player.update(playerPoint);
        generator = new MazeGenerator(200, 350, 10, Color.BLACK);
        movingPlayer = false;
    }

    @Override
    public void terminate() {
        SceneManager.ACTIVE_SCENE = 1;
    }

    @Override
    public void receiveTouch(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (!gameOver && player.getMarble().contains((int)event.getX(), (int)event.getY()))
                    movingPlayer = true;
                if(gameOver && System.currentTimeMillis() - gameOverTime >= 2000) {
                    reset();
                    gameOver = false;
                    sensorData.newGame();
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if(!gameOver && movingPlayer)
                    playerPoint.set((int)event.getX(), (int)event.getY());
                break;
            case MotionEvent.ACTION_UP:
                movingPlayer = false;
                break;
        }
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawColor(Constants.BACKGROUND_COLOR);

        player.draw(canvas);
        generator.draw(canvas);

        if(gameOver) {
            Paint paint = new Paint();
            paint.setTextSize(100);
            paint.setColor(Color.MAGENTA);
            paint.setTextAlign(Paint.Align.CENTER);
            canvas.drawText("GAME OVER!", Constants.SCREEN_WIDTH/2, Constants.SCREEN_HEIGHT/2, paint);
        }
    }

    @Override
    public void update() {
        if(!gameOver) {
            if (frameTime < Constants.INIT_TIME)
                frameTime = Constants.INIT_TIME;

            int elapsedTime = (int) (System.currentTimeMillis() - frameTime);
            frameTime = System.currentTimeMillis();

            if (sensorData.getOrientation() != null && sensorData.getStartOrientation() != null) {
                float deltaPitch = sensorData.getOrientation()[1] - sensorData.getStartOrientation()[1]; // Y axis
                float deltaRoll = sensorData.getOrientation()[2] - sensorData.getStartOrientation()[2]; // X axis

                float xSpeed = 2 * deltaRoll * Constants.SCREEN_WIDTH / 1000f;
                float ySpeed = deltaPitch * Constants.SCREEN_HEIGHT / 1000f;

                playerPoint.x += Math.abs(xSpeed*elapsedTime) > 5 ? xSpeed*elapsedTime : 0;
                playerPoint.y -= Math.abs(ySpeed*elapsedTime) > 5 ? ySpeed*elapsedTime : 0;
            }

            if (playerPoint.x < 0) {
                playerPoint.x = 0;
            } else if (playerPoint.x > Constants.SCREEN_WIDTH) {
                playerPoint.x = Constants.SCREEN_WIDTH;
            }

            if (playerPoint.y < 0) {
                playerPoint.y = 0;
            } else if (playerPoint.y > Constants.SCREEN_HEIGHT) {
                playerPoint.y = Constants.SCREEN_HEIGHT;
            }

            player.update(playerPoint);
            generator.update();
            if(generator.playerCollision(player)) {
                gameOver = true;
                gameOverTime = System.currentTimeMillis();
                //playerPoint.x = playerPoint.x-10;
                //playerPoint.y = playerPoint.y-10;
            }
        }
    }
}

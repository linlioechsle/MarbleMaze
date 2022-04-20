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
    private Rect winBox;

    private boolean movingPlayer = false;

    private boolean gameOver = false;
    private boolean winner = false;
    private long gameOverTime;
    private long winTime;

    private SensorData sensorData;
    private long frameTime;

    private Rect portal1, portal2;

    public GameplayScene() {
        sensorData = new SensorData();
        sensorData.register();
        player = new MarblePlayer(new Rect(50, 50, 100, 100));
        playerPoint = new Point(50, 50);
        player.update(playerPoint);
        player.update();

        generator = new MazeGenerator(Color.BLACK);
        winBox = new Rect(Constants.SCREEN_WIDTH - 90, Constants.SCREEN_HEIGHT - 100, Constants.SCREEN_WIDTH- 40, Constants.SCREEN_HEIGHT- 50);
        portal1 = new Rect(280, Constants.SCREEN_HEIGHT-650, 350, Constants.SCREEN_HEIGHT-575);
        portal2 = new Rect(700, 25, 775, 100);

        frameTime = System.currentTimeMillis();
    }

    public void reset() {
        playerPoint = new Point(50, 50);
        player.update(playerPoint);
        generator = new MazeGenerator(Color.BLACK);
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
                if(winner && System.currentTimeMillis() - winTime >= 2000) {
                    reset();
                    winner = false;
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
        Paint paint = new Paint();
        player.draw(canvas);
        generator.draw(canvas);
        paint.setColor(Color.MAGENTA);
        canvas.drawRect(winBox, paint);

        paint.setColor(Color.YELLOW);
        canvas.drawRect(portal1, paint);
        canvas.drawRect(portal2, paint);

        if(gameOver) {
            Paint paint1 = new Paint();
            paint1.setTextSize(100);
            paint1.setColor(Color.RED);
            paint1.setTextAlign(Paint.Align.CENTER);
            canvas.drawText("GAME OVER!", Constants.SCREEN_WIDTH/2, Constants.SCREEN_HEIGHT/2, paint1);
        }

        if(winner) {
            Paint paint2 = new Paint();
            paint2.setTextSize(100);
            paint2.setColor(Color.GREEN);
            paint2.setTextAlign(Paint.Align.CENTER);
            canvas.drawText("WINNER!", Constants.SCREEN_WIDTH/2, Constants.SCREEN_HEIGHT/2, paint2);
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

            if (Rect.intersects(portal1, player.getMarble()) || Rect.intersects(portal2, player.getMarble())) {
                playerPoint.x = 50;
                playerPoint.y = 50;
            }

            if (Rect.intersects(winBox, player.getMarble())) {
                winner = true;
                playerPoint.x = 50;
                playerPoint.y = 50;
                winTime = System.currentTimeMillis();
            }
        }
    }
}

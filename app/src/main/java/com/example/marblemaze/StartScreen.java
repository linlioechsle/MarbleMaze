package com.example.marblemaze;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;

public class StartScreen implements Scene {

    @Override
    public void update() {

    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawColor(Color.WHITE);
        Paint paint = new Paint();
        paint.setTextSize(50);
        paint.setColor(Color.MAGENTA);
        paint.setTextAlign(Paint.Align.CENTER);
        canvas.drawText("Welcome to MarbleMaze!\n", Constants.SCREEN_WIDTH/2, Constants.SCREEN_HEIGHT/8, paint);
        canvas.drawText("Tap the screen to start playing!", Constants.SCREEN_WIDTH/2, Constants.SCREEN_HEIGHT/4, paint);
    }

    @Override
    public void terminate() {

    }

    @Override
    public void receiveTouch(MotionEvent event) {
        SceneManager.ACTIVE_SCENE = 1;
    }
}

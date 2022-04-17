package com.example.marblemaze;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

public class Obstacle implements GameObject {
    private Rect rectangle;
    private int color;

    int left, top, right, bottom;


    public Rect getRectangle() {
        return rectangle;
    }

    public void incrementY(float y) {
        rectangle.top += y;
        rectangle.bottom += y;

    }

    public Obstacle(int left, int top, int right, int bottom) {
        rectangle = new Rect(left, top, right, bottom);

    }

    public boolean playerCollision(MarblePlayer player) {
        return Rect.intersects(rectangle, player.getMarble());
    }

    @Override
    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Constants.OBSTACLE_COLOR);
        canvas.drawRect(rectangle, paint);
    }

    @Override
    public void update() {

    }
}

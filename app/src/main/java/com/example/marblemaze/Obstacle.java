package com.example.marblemaze;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

public class Obstacle implements GameObject {
    private Rect rectangle;
    private Rect rectangle2;
    private int color;


    public Rect getRectangle() {
        return rectangle;
    }

    public void incrementY(float y) {
        rectangle.top += y;
        rectangle.bottom += y;
        rectangle2.top += y;
        rectangle2.bottom += y;
    }

    public Obstacle(int rectHeight, int color, int originX, int originY, int playerGap) {
        this.color = color;
        rectangle = new Rect(0, originY, originX, originY + rectHeight);
        rectangle2 = new Rect(originX + playerGap, originY, Constants.SCREEN_WIDTH, originY + rectHeight);


    }

    public boolean playerCollision(MarblePlayer player) {
        return Rect.intersects(rectangle, player.getMarble()) || Rect.intersects(rectangle2, player.getMarble());
    }

    @Override
    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(color);
        canvas.drawRect(rectangle, paint);
        canvas.drawRect(rectangle2, paint);
    }

    @Override
    public void update() {

    }
}

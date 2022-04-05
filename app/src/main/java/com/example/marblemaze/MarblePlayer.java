package com.example.marblemaze;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

public class MarblePlayer implements GameObject {
    private Rect marble;
    private int color;

    public Rect getMarble() {
        return marble;
    }

    public MarblePlayer(Rect marble, int color) {
        this.marble = marble;
        this.color = color;
        System.out.println("CREATED NEW PLAYER");
    }

    @Override
    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(color);
        canvas.drawRect(marble, paint);
    }

    @Override
    public void update() {

    }

    public void update(Point point) { // left, top, right, bottom
        marble.set(point.x - marble.width()/2, point.y - marble.height()/2, point.x + marble.width()/2, point.y + marble.height()/2);
    }
}

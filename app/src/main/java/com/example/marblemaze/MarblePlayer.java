package com.example.marblemaze;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

public class MarblePlayer implements GameObject {
    private Rect marble;

    public Rect getMarble() {
        return marble;
    }

    public MarblePlayer(Rect marble) {
        this.marble = marble;
        System.out.println("CREATED NEW PLAYER");
    }

    @Override
    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Constants.MARBLE_COLOR);
        canvas.drawRect(marble, paint);
    }

    @Override
    public void update() {
    }

    public void update(Point point) { // left, top, right, bottom
        marble.set(point.x - marble.width()/2, point.y - marble.height()/2, point.x + marble.width()/2, point.y + marble.height()/2);
    }
}

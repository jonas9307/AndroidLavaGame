package com.example.jonas.androidlavagame;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

/**
 * Created by Alexander on 2017-07-21.
 */

public class RectPlayer implements GameObject{

    private Rect rect;
    private int color;
    private int id;

    public RectPlayer(Rect rect, int color) {
        this.rect = rect;
        this.color = color;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(color);
        canvas.drawRect(rect, paint);
    }

    @Override
    public void update() {
    }

    public void update(Point position) {
        rect.set(
            position.x - rect.width()  / 2,
            position.y - rect.height() / 2,
            position.x + rect.width()  / 2,
            position.y + rect.height() / 2
        );
    }

    public int getPositionX() {
        return rect.centerX();
    }


    public int getPositionY() {
        return rect.centerY();
    }
}

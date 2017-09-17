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
    private double vx, vy;
    private double x, y;
    private static final double MAX_SPEED = 22.0; // pixels per update

    public RectPlayer(Rect rect, int color) {
        this.rect = rect;
        this.color = color;
    }

    @Override
    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(color);
        canvas.drawRect(rect, paint);
    }

    public void setVelocity(Joystick joystick) {
        vx = joystick.getVelocityX()/(2*joystick.getOuterCircleRadius());
        vy = joystick.getVelocityY()/(2*joystick.getOuterCircleRadius());
    }

    @Override
    public void update() {
        x = (double) (x + vx*MAX_SPEED);
        y = (double) (y + vy*MAX_SPEED);
        updateRect();
    }
    public void updateRect() {
        rect.set(
            (int) (x - (double)rect.width()  / 2.0),
            (int) (y - (double)rect.height() / 2.0),
            (int) (x + (double)rect.width()  / 2.0),
            (int) (y + (double)rect.height() / 2.0)
        );
    }

    /*
    public void update(Point position) {
        rect.set(
            position.x - rect.width()  / 2,
            position.y - rect.height() / 2,
            position.x + rect.width()  / 2,
            position.y + rect.height() / 2
        );

    }
    */
}

package com.example.jonas.androidlavagame;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

/**
 * Created by Alexander on 2017-07-21.
 */

public class RectPlayer implements GameObject{

    private int color;
    private int cx;
    private int cy;
    private double vx, vy;
    private double x, y;
    private static final double MAX_SPEED = 22.0; // pixels per update


    public RectPlayer(int color, int screenWidth, int screenHeight, int positionX, int positionY) {
        this.color = color;
        this.cx = screenWidth/2;
        this.cy = screenHeight/2;
        this.x = positionX - screenWidth/2;
        this.y = positionY - screenHeight/2;
    }

    @Override
    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(color);
        //canvas.drawRect(rect, paint);
        canvas.drawCircle(cx, cy, 50, paint);
    }

    public void setVelocity(Joystick joystick) {
        vx = joystick.getVelocityX()/(2*joystick.getOuterCircleRadius());
        vy = joystick.getVelocityY()/(2*joystick.getOuterCircleRadius());
    }

    @Override
    public void update() {
        x = (double) (x + vx*MAX_SPEED);
        y = (double) (y + vy*MAX_SPEED);
    }

    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }
    public double getCX() {
        return cx;
    }
    public double getCY() {
        return cy;
    }
}

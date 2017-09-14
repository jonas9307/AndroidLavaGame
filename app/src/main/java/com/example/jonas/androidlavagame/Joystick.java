package com.example.jonas.androidlavagame;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;

/**
 * Created by Alexander on 2017-09-14.
 */

public class Joystick implements GameObject {

    private int cx, cy, r, r2;
    private double vx, vy;
    private Paint paint;
    private Paint paint2;

    public Joystick(int cx, int cy, int r) {
        this.cx = cx;
        this.cy = cy;
        vx = 0.0;
        vy = 0.0;
        this.r = r;
        r2 = (int)(0.3*r);

        paint = new Paint();
        paint.setColor(Color.GRAY);
        paint.setStyle(Style.FILL);

        paint2 = new Paint();
        paint2.setColor(Color.RED);
        paint2.setStyle(Style.FILL);
    }

    @Override
    public void draw(Canvas canvas) {

        canvas.drawCircle(cx, cy, r, paint);
        canvas.drawCircle((int)(cx + vx*r), (int)(cy + vy*r), 5, paint2);
    }

    @Override
    public void update() {

    }

    public boolean isPressed(int x, int y) {
        double d = Math.sqrt( Math.pow(cx - x, 2) + Math.pow(cy - y, 2)); // distance to center
        return (d < r) ? true : false; // true if inside circle

    }

    public double getVelocityX(int x) {
        vx = (x - cx)/r;
        if (vx > 1.0) {
            vx = 1.0;
        } else if (vx < -1.0) {
            vx = -1.0;
        }
        return vx;
    }

    public double getVelocityY(int y) {
        vy = (y - cy)/r;
        if (vy > 1.0) {
            vy = 1.0;
        } else if (vy < -1.0) {
            vy = -1.0;
        }
        return vy;
    }
}

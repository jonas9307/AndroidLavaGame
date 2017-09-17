package com.example.jonas.androidlavagame;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;

import static android.R.attr.x;

/**
 * Created by Alexander on 2017-09-14.
 */

public class Joystick implements GameObject {

    private int outerCircleCenterPositionX,
                outerCircleCenterPositionY,
                innerCircleCenterPositionX,
                innerCircleCenterPositionY,
                outerCircleRadius,
                innerCircleRadius;

    private double velocityX,
                   velocityY;

    private Paint outerCirclePaint,
                  innerCirclePaint;

    public Joystick(int centerPositionX, int centerPositionY, int outerCricleRadius, int innerCircleRadius) {
        outerCircleCenterPositionX = centerPositionX;
        outerCircleCenterPositionY = centerPositionY;
        innerCircleCenterPositionX = centerPositionX;
        innerCircleCenterPositionY = centerPositionY;
        this.outerCircleRadius = outerCricleRadius;
        this.innerCircleRadius = innerCircleRadius;

        velocityX = 0.0;
        velocityY = 0.0;

        outerCirclePaint = new Paint();
        outerCirclePaint.setColor(Color.GRAY);
        outerCirclePaint.setStyle(Style.FILL_AND_STROKE);

        innerCirclePaint = new Paint();
        innerCirclePaint.setColor(Color.BLUE);
        innerCirclePaint.setStyle(Style.FILL_AND_STROKE);
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawCircle(outerCircleCenterPositionX, outerCircleCenterPositionY, outerCircleRadius, outerCirclePaint);
        canvas.drawCircle(innerCircleCenterPositionX, innerCircleCenterPositionY, innerCircleRadius, innerCirclePaint);
    }

    @Override
    public void update() {
    }

    public boolean isPressed(int touchPositionX, int touchPositionY) {
        double joystickCenterToTouchPositionDistance =
            Math.sqrt(Math.pow(outerCircleCenterPositionX - touchPositionX, 2) +
                      Math.pow(outerCircleCenterPositionY - touchPositionY, 2)
            );
        return (joystickCenterToTouchPositionDistance < outerCircleRadius) ? true : false; // true if inside circle
    }

    public double getVelocityX() {
        return velocityX;
    }

    public double getVelocityY() {
        return velocityY;
    }

    public void setVelocity(int touchPositionX, int touchPositionY) {
        velocityX = (touchPositionX - outerCircleCenterPositionX);
        velocityY = (touchPositionY - outerCircleCenterPositionY);
        double joystickCenterToTouchPositionDistance = Math.sqrt(Math.pow(velocityX, 2) + Math.pow(velocityY, 2));
        if (joystickCenterToTouchPositionDistance > 2*outerCircleRadius) {
            double velocityScaleFactor = 2*outerCircleRadius/joystickCenterToTouchPositionDistance;
            velocityX *= velocityScaleFactor;
            velocityY *= velocityScaleFactor;
        }
    }

    public void setVelocityZero() {
        velocityX = 0.0;
        velocityY = 0.0;
    }

    public void updateInnerCircleCenterPosition() {
        innerCircleCenterPositionX = (int) (outerCircleCenterPositionX + velocityX);
        innerCircleCenterPositionY = (int) (outerCircleCenterPositionY + velocityY);
    }

    public int getOuterCircleRadius() {
        return outerCircleRadius;
    }
}

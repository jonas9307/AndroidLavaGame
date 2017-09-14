package com.example.jonas.androidlavagame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
/**
 * Created by Alexander on 2017-07-12.
 */

public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {
    private MainThread thread;

    private Joystick joystick;
    private RectPlayer player;
    private Point playerPosition;

    private boolean joystickPressed = false;

    public GamePanel(Context context) {
        super(context);
        getHolder().addCallback(this);

        thread = new MainThread(getHolder(), this);

        joystick = new Joystick(200, 200, 100);
        player = new RectPlayer(new Rect(400, 400, 200, 200), Color.rgb(255, 0, 0));
        //playerPosition = new Point(150, 150);

        setFocusable(true);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        thread = new MainThread(getHolder(), this);
        thread.setRunning(true);
        thread.start();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        while(true) {
            try {
                thread.setRunning(false);
                thread.join();
            } catch(Exception e) {e.printStackTrace();}
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        System.out.println(event.getAction());
        switch(event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                joystickPressed = joystick.isPressed((int)event.getX(), (int)event.getY());
                return true;
            case MotionEvent.ACTION_MOVE:
                if(joystickPressed) {
                    player.setVelocity(joystick.getVelocityX((int)event.getX()), joystick.getVelocityY((int)event.getY()));
                    //System.out.println("JoystickPressed = true");
                }
                return true;
               // playerPosition.set((int)event.getX(), (int)event.getY());
            case MotionEvent.ACTION_UP:
                joystickPressed = false;
                player.setVelocity(0.0, 0.0);
                System.out.println("AcTION_UP: " + MotionEvent.ACTION_UP + ", joystickPressed = " + joystickPressed);
                return true;
        }
        return true;
        //return super.onTouchEvent(event);
    }

    public void update() {
        player.update();
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        canvas.drawColor(Color.WHITE);

        player.draw(canvas);
        joystick.draw(canvas);
    }
}

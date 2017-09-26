package com.example.jonas.androidlavagame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.view.Display;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.WindowManager;

/**
 * Created by Alexander on 2017-07-12.
 */

public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {
    private MainThread thread;
    private Joystick joystick;
    private RectPlayer player;
    private Level level;
    private Map map;
    private Tile[][] tileMap;
    private Screen screen;
    private int width;
    private int height;
    private boolean joystickPressed = false;

    public GamePanel(Context context) {
        super(context);
        getHolder().addCallback(this);
        thread = new MainThread(getHolder(), this);
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        width = size.x;
        height = size.y;
        joystick = new Joystick(300, 300, 100, 50);
        player = new RectPlayer(Color.rgb(255, 0, 0), width, height);
        level = new Level("testLevel");
        map = new Map(context, level);
        tileMap = map.getTileMap();
        screen = new Screen(width, height, tileMap);
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
        //System.out.println(event.getAction());
        switch(event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                joystickPressed = joystick.isPressed((int)event.getX(), (int)event.getY());
                return true;
            case MotionEvent.ACTION_MOVE:
                if(joystickPressed) {
                    joystick.setVelocity((int)event.getX(), (int)event.getY());
                    joystick.updateInnerCircleCenterPosition();
                    player.setVelocity(joystick);
                    //System.out.println("JoystickPressed = true");
                }
                return true;
               // playerPosition.set((int)event.getX(), (int)event.getY());
            case MotionEvent.ACTION_UP:
                joystickPressed = false;
                joystick.setVelocityZero();
                joystick.updateInnerCircleCenterPosition();
                player.setVelocity(joystick);
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
        screen.draw(canvas, player);
        player.draw(canvas);
        joystick.draw(canvas);
    }
}

package com.example.jonas.androidlavagame;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.List;

/**
 * Created by Alexander on 2017-09-29.
 */

class Enemy implements GameObject {

    private int cx, cy;
    private double vx, vy;
    private RectPlayer player;
    private static int spawnsPerMinute = 10;
    private static int timeToSpawn = 0;
    private int color;

    public static final int MIN_SPAWN_DISTANCE = 500; 	// pixels from player
    public static final int MAX_SPAWN_DISTANCE = 3000; 	// pixels from player
    public static final int MIN_SPEED = 1; 			// pixels per update
    public static final int MAX_SPEED = 10; 			// pixels per update

    public final int speed;

    public Enemy(RectPlayer player) {
        this.player = player;
        cx = (int) (MIN_SPAWN_DISTANCE + Math.random()*(MAX_SPAWN_DISTANCE - MIN_SPAWN_DISTANCE));
        cy = (int) (MIN_SPAWN_DISTANCE + Math.random()*(MAX_SPAWN_DISTANCE - MIN_SPAWN_DISTANCE));
        speed = (int) (MIN_SPEED + Math.random()*(MAX_SPEED - MIN_SPEED));
        color = Color.rgb(0, 15, 255);

    }


    @Override
    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(color);
        canvas.drawCircle((int) (player.getCX() + player.getX() - cx), (int) (player.getCY() + player.getY() - cy), 50, paint);
    }

    @Override
    public void update() {
        double d = Math.sqrt(Math.pow(player.getX() - cx, 2) + Math.pow(player.getY() - cy, 2));
        vx = (player.getX() - cx)/d;
        vy = (player.getY() - cy)/d;
        cx = (int) (cx + vx*MAX_SPEED);
        cy = (int) (cx + vy*MAX_SPEED);
    }

    public static boolean readyToSpawn() {
        if(timeToSpawn <= 0) {
            timeToSpawn += spawnsPerMinute*60;
            return true;
        } else {
            timeToSpawn -= 1;
            return false;
        }
    }
}

package com.example.jonas.androidlavagame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexander on 2017-07-12.
 */

public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {
    private MainThread thread;

    private List<RectPlayer> enemyPlayers = new ArrayList<RectPlayer>();
    private RectPlayer player;
    private Point playerPosition;

    public GamePanel(Context context) {
        super(context);

        getHolder().addCallback(this);

        thread = new MainThread(getHolder(), this);

        player = new RectPlayer(new Rect(100, 100, 200, 200), Color.rgb(255, 0, 0));
        playerPosition = new Point(150, 150);

        ClientConnection clientConnection = new ClientConnection(player);
        clientConnection.execute();

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
        switch(event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                playerPosition.set((int)event.getX(), (int)event.getY());
                //new QuoteClient().execute();

        }



        return true;
        //return super.onTouchEvent(event);
    }

    public void update() {
        player.update(playerPosition);
        new ClientUpdate(player, enemyPlayers).execute();
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        canvas.drawColor(Color.WHITE);

        player.draw(canvas);
        for(RectPlayer enemyPlayer : enemyPlayers) {
            enemyPlayer.draw(canvas);
        }
    }
}

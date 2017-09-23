package com.example.jonas.androidlavagame;

import android.content.Context;
import android.graphics.Canvas;

/**
 * Created by Alexander on 2017-09-23.
 */


public class Map {

    private int width;
    private int height;
    private SpriteSheet spriteSheet;
    private Tile groundTile;
    private Tile lavaTile;

    private Tile[][] tileList;

    public Map(Context context, int width, int height) {
        this.width = width;
        this.height = height;
        spriteSheet = new SpriteSheet(context, R.raw.spritesheet);
        groundTile = new Tile(spriteSheet.getSprite(0, 0), false);
        lavaTile = new Tile(spriteSheet.getSprite(0, 1), false);
        initializeTileList();
    }

    private void initializeTileList() {
        tileList = new Tile[height][width];
        for (int i = 0; i < height; i++) {
            for(int j = 0; j < width; j++) {
                tileList[i][j] = lavaTile;
            }
        }

        for (int i = height/4; i < 3*height/4; i++) {
            for(int j = height/4; j < 3*width/4; j++) {
                tileList[i][j] = groundTile;
            }
        }

    }

    public void draw(Canvas canvas) {
        for (int i = 0; i < height; i++) {
            for(int j = 0; j < width; j++) {
                canvas.drawBitmap(tileList[i][j].getImage(), j*128, i*128, null);
            }
        }
    }
}

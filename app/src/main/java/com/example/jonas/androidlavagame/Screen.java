package com.example.jonas.androidlavagame;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * Created by Alexander on 2017-09-25.
 */

public class Screen {

    private int width;
    private int height;
    private int top = 0;
    private int left = 0;
    private Tile[][] tileMap;

    public Screen(int width, int height, Tile[][] tileMap){
        this.width = width;
        this.height = height;
        this.tileMap = tileMap;
    }

    public void draw(Canvas canvas, RectPlayer player) {
        /*
        1. Check if tile is left of screen
            1.1 if left of screen, check if on screen
        */
        top = (int)player.getX();
        int bot = top + height - 1;
        left = (int)player.getY();
        int right = left + width - 1;
        int nRows = tileMap.length;
        int nCols = tileMap[0].length;

        int xPixels = 0;
        int yPixels = 0;
        
        for (int i = 0; i < nRows; i++) {

            int tileTop = i*128;
            int tileBot = tileTop + 128 - 1;

            if(tileBot > top && tileTop < bot){
                for(int j = 0; j < nCols; j++) {

                    int tileLeft = j*128;
                    int tileRight = tileLeft + 128 - 1;


                        if(tileRight > left && tileLeft < right){

                            // what part of the tile to draw?
                            int newTileLeft, newTileTop, newTileWidth, newTileHeight;

                            if(tileLeft < left) {
                                newTileWidth = left - tileLeft;
                                newTileLeft = 128 - newTileWidth;
                            } else if(tileRight > right) {
                                newTileWidth = right - tileLeft;
                                newTileLeft = 0;
                            } else {
                                newTileLeft = 0;
                                newTileWidth = 128;
                            }

                            if(tileTop < top) {
                                newTileHeight = top - tileTop;
                                newTileTop = 128 - newTileHeight;
                            } else if(tileBot > bot) {
                                newTileHeight = bot - tileTop;
                                newTileTop = 0;
                            } else {
                                newTileTop = 0;
                                newTileHeight = 128;
                            }

                            Bitmap tilePart = Bitmap.createBitmap(tileMap[i][j].getImage(), newTileLeft, newTileTop, newTileWidth, newTileHeight);
                            canvas.drawBitmap(tilePart, tileLeft - left + newTileLeft, tileTop - top + newTileTop, null);
                        }
                    }
                //canvas.drawBitmap(tileMap[i][j].getImage(), j*128, i*128, null);
            }
        }
    }

    public void setTop(int top) {
        this.top = top;
    }

    public void setLeft(int left) {
        this.left = left;
    }
}

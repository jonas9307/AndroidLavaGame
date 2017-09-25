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
        left = (int)player.getY();
        int nRows = tileMap.length;
        int nCols = tileMap[0].length;

        for (int i = 0; i < 5; i++) {
            for(int j = 0; j < 5; j++) {
                if((i+1)*128 > top && i*128 < top + height){
                    if((j+1)*128 > left && j*128 < left + width){
                        // what part of the tile to draw?
                        int xTile, yTile, widthTile, heightTile ;

                        if(j*128 < left) {
                            widthTile = left - j*128;
                            xTile = 128 - widthTile;
                        } else if((j+1)*128 > left + width) {
                            widthTile = left + width - j*128;
                            xTile = left + width - widthTile;
                        } else {
                            xTile = 0;
                            widthTile = 128;
                        }

                        if(i*128 < top) {
                            heightTile = top - i*128;
                            yTile = 128 - heightTile;
                        } else if((i+1)*128 > top + height) {
                            heightTile = top + height - i*128;
                            yTile = top + height - heightTile;
                        } else {
                            yTile = 0;
                            heightTile = 128;
                        }

                        Bitmap tilePart = Bitmap.createBitmap(tileMap[i][j].getImage(), yTile, xTile, heightTile, widthTile);
                        canvas.drawBitmap(tilePart, left - j*128 + 128 - widthTile, top - i*128 + 128 - heightTile, null);
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

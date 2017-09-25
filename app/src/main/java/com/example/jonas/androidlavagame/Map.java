package com.example.jonas.androidlavagame;

import android.content.Context;
import android.graphics.Canvas;

/**
 * Created by Alexander on 2017-09-23.
 */


public class Map {


    private SpriteSheet spriteSheet;
    private Tile groundTile;
    private Tile lavaTile;
    private Tile[] tileList;
    private Tile[][] tileMap;
    private Level level;

    public Map(Context context, Level level) {
        this.level = level;
        spriteSheet = new SpriteSheet(context, R.raw.spritesheet);
        groundTile = new Tile(spriteSheet.getSprite(0, 0), false);
        lavaTile = new Tile(spriteSheet.getSprite(0, 1), false);
        tileList = new Tile[] {groundTile, lavaTile};
        initializeTileMap();
    }

    private void initializeTileMap() {
        int [][] layout = level.getLayout();
        int nRows = layout.length;
        int nCols = layout[0].length;
        tileMap = new Tile[nRows][nCols];
        for(int i = 0; i < nRows; i++){
            for(int j = 0; j < nCols; j++){
                tileMap[i][j] = tileList[layout[i][j]];
            }
        }
        /*tileMap = new Tile[height][width];
        for (int i = 0; i < height; i++) {
            for(int j = 0; j < width; j++) {
                tileMap[i][j] = lavaTile;
            }
        }

        for (int i = height/4; i < 3*height/4; i++) {
            for(int j = height/4; j < 3*width/4; j++) {
                tileMap[i][j] = groundTile;
            }
        }*/
    }

    public Tile[][] getTileMap() {
        return tileMap;
    }
}

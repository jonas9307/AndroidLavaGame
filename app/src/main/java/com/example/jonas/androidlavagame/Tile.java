package com.example.jonas.androidlavagame;

import android.graphics.Bitmap;

/**
 * Created by Alexander on 2017-09-23.
 */

public class Tile {

    private Bitmap image;
    private boolean solid;

    public Tile(Bitmap image, boolean solid) {
        this.image = image;
        this.solid = solid;
    }

    public Bitmap getImage() {
        return image;
    }
}

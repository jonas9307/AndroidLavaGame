package com.example.jonas.androidlavagame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by Alexander on 2017-09-23.
 */

public class SpriteSheet {
    private Bitmap spriteSheet;

    public SpriteSheet(Context context, int id) {
        spriteSheet = BitmapFactory.decodeResource(context.getResources(), id);
    }

    public Bitmap getSprite(int i, int j) {
        Bitmap sprite = Bitmap.createBitmap(spriteSheet, j*128, i*128, 128, 128);
        return sprite;
    }

}

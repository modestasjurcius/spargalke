package com.example.lab8;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class Line extends Drawable {
    private final Paint paint;
    private Utilities utils;

    private float width, height;

    public Line(Utilities utils, float width, float height) {
        this.paint = utils.getPaint();

        this.utils = utils;
        this.paint.setStrokeWidth(this.utils.getRandom(5, 40));

        this.width = width;
        this.height = height;
    }

    @Override
    public void draw(@NonNull Canvas canvas) {

        float startPosX = utils.getRandom(1, (int)width);
        float startPosY = utils.getRandom(1, (int)height);

        float stopPosX = utils.getRandom(1, (int)width);
        float stopPosY = utils.getRandom(1, (int)height);

        canvas.drawLine(startPosX, startPosY, stopPosX, stopPosY, paint);
    }

    @Override
    public void setAlpha(int i) {

    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {

    }

    @Override
    public int getOpacity() {
        return PixelFormat.OPAQUE;
    }
}

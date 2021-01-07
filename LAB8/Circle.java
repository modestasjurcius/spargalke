package com.example.lab8;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class Circle extends Drawable {
    private final Paint paint;
    private Utilities utils;

    private int divider;
    private float maxWidth, maxHeight;

    public Circle(Utilities utils, float width, float height) {
        this.paint = utils.getPaint();

        this.utils = utils;
        this.divider = this.utils.getRandomDivider();

        this.maxWidth = width;
        this.maxHeight = height;
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        int width = getBounds().width() / divider;
        int height = getBounds().height() / divider;
        float radius = Math.min(width, height) / 2;

        float posX = utils.getRandomCirclePos(radius, maxWidth, true);
        float posY = utils.getRandomCirclePos(radius, maxHeight, false);

        canvas.drawCircle(posX, posY, radius, paint);
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

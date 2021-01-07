package com.example.lab8;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PixelFormat;
import android.graphics.Point;
import android.graphics.drawable.Drawable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class Triangle extends Drawable {
    private Paint paint;
    private Utilities utils;

    private float width, height;
    private Point startPoint, midPoint, endPoint;

    public Triangle(Utilities utils, float width, float height) {
        this.paint = utils.getPaint();

        this.utils = utils;

        this.width = width;
        this.height = height;
        calculatePositions();
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        Path path = new Path();

        path.moveTo(startPoint.x, startPoint.y);
        path.lineTo(midPoint.x, midPoint.y);
        path.lineTo(endPoint.x, endPoint.y);
        path.close();

        canvas.drawPath(path, paint);
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

    private void calculatePositions() {
        float startX = utils.getRandom(1, (int)width);
        float startY = utils.getRandom(1, (int)height);
        startPoint = new Point();
        startPoint.set((int) startX, (int)startY);

        float midX = utils.getRandom(1, (int)width);
        float midY = utils.getRandom(1, (int)height);
        midPoint = new Point();
        midPoint.set((int) midX, (int)midY);

        float endX = utils.getRandom(1, (int)width);
        float endY = utils.getRandom(1, (int)height);
        endPoint = new Point();
        endPoint.set((int) endX, (int)endY);
    }
}

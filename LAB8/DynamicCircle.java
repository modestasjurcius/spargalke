package com.example.lab8;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class DynamicCircle extends Drawable {
    private Paint paint;

    private float centerX, centerY, radius;

    public DynamicCircle(Utilities utils, MotionEvent event) {
        this.paint = utils.getPaint();

        calcCenter(event);
        calcRadius(centerX, centerY);
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        canvas.drawCircle(centerX, centerY, radius, paint);
    }

    public void calcRadius(float pointX, float pointY) {
        this.radius = (float)Math.hypot((centerX-pointX), (centerY-pointY));
    }

    private void calcCenter(MotionEvent event) {
        int pId = getPointerIndex(event, 0);
        float ax = event.getX(pId);
        float ay = event.getY(pId);

        pId = getPointerIndex(event, 1);
        float bx = event.getX(pId);
        float by = event.getY(pId);

        centerX = (ax + bx) / 2;
        centerY = (ay + by) / 2;
    }

    private int getPointerIndex(MotionEvent event, int id) {
        int pointerId = event.getPointerId(id);
        return event.findPointerIndex(pointerId);
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

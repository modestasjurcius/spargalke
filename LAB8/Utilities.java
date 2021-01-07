package com.example.lab8;

import android.graphics.Paint;

import java.util.Random;

public class Utilities {
    private final int DIVIDER_MIN = 1;
    private final int DIVIDER_MAX = 15;

    private Paint paint;
    private Random randomGenerator;

    public Utilities() {
        this.randomGenerator = new Random();

        this.paint = new Paint();
        this.paint.setARGB(255, 57, 68, 205);
    }

    public float getRandomCirclePos(float radius, float max, boolean getX) {
        float value = getRandom(1, Math.round(max));
        if(value <= radius)
            value = radius;
        else if (value > (max - radius))
            value = max - radius;

        return value;
    }

    public int getRandomDivider() {
        return getRandom(DIVIDER_MIN, DIVIDER_MAX);
    }

    public int getRandom(int min, int max) {
        return randomGenerator.nextInt(max) + min;
    }

    public Paint getPaint() {
        return paint;
    }
}

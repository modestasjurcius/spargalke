package com.example.lab8;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private ImageView imageView;

    private boolean isPainted, wasSwiped;
    private float touchDownX, touchDownY,touchMoveX;
    static final int MIN_DISTANCE = 170;

    private Utilities utils;
    private DynamicCircle dynamicCircle;
    private Drawables nextDrawable;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.imageView = findViewById(R.id.imageView);

        this.utils = new Utilities();

        this.isPainted = false;
        this.wasSwiped = false;
        this.nextDrawable = Drawables.Circle;

        final ViewTreeObserver observer = findViewById(R.id.layout).getViewTreeObserver();
        observer.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                draw();
            }
        });

        imageView.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return onTouchEvent(view, motionEvent);
            }
        });
    }

    private boolean onTouchEvent(View v, MotionEvent e) {
        switch (e.getActionMasked()) {
            case MotionEvent.ACTION_DOWN: {
                onActionDown(e);
                break;
            }
            case MotionEvent.ACTION_MOVE: {
                onActionMove(e);
                break;
            }
            case MotionEvent.ACTION_UP: {
                onActionUp();
            }
            default:
                break;
        }
        return true;
    }

    private void onActionDown(MotionEvent e) {
        touchDownX = e.getX();
        touchDownY = e.getY();
        isPainted = false;
        wasSwiped = false;
    }

    private void onActionMove(MotionEvent e) {
        switch(e.getPointerCount()) {
            case 2: {
                drawDynamicCircle(e);
                break;
            }
            case 1:
                if (!isPainted) {
                    touchMoveX = e.getX();
                    float deltaX = touchDownX - touchMoveX;
                    if(Math.abs(deltaX) > MIN_DISTANCE) {
                        draw();
                        wasSwiped = true;
                    }
                }
            default: {
                break;
            }
        }
    }

    private void drawDynamicCircle(MotionEvent e) {
        if(dynamicCircle == null) {
            dynamicCircle = new DynamicCircle(utils, e);
            isPainted = true;
            wasSwiped = true;
        } else if (isPainted) {
            dynamicCircle.calcRadius(e.getX(), e.getY());
            dynamicCircle.invalidateSelf();
        }
        imageView.setImageDrawable(dynamicCircle);
    }

    private void draw() {
        switch(this.nextDrawable) {
            case Circle: {
                drawCircle();
                this.nextDrawable = Drawables.Line;
                break;
            }
            case Line: {
                drawLine();
                this.nextDrawable = Drawables.Triangle;
                break;
            }
            case Triangle: {
                drawTriangle();
                this.nextDrawable = Drawables.Circle;
                break;
            }
            default:
                break;
        }

        isPainted = true;
    }

    private void drawCircle() {
        Circle circle = new Circle(utils, imageView.getWidth(), imageView.getHeight());
        imageView.setImageDrawable(circle);
    }

    private void drawLine() {
        Line line = new Line(utils, imageView.getWidth(), imageView.getHeight());
        imageView.setImageDrawable(line);
    }

    private void drawTriangle() {
        Triangle triangle = new Triangle(utils, imageView.getWidth(), imageView.getHeight());
        imageView.setImageDrawable(triangle);
    }

    private void onActionUp() {
        Log.d("[ON ACTION UP]", "up, wasswiped = " + wasSwiped);
        this.dynamicCircle = null;
        isPainted = true;

        if(!wasSwiped)
            imageView.setImageDrawable(null);
    }
}
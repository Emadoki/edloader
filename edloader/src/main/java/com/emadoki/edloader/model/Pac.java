package com.emadoki.edloader.model;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

import com.emadoki.edloader.Vector2;

public class Pac
{
    public Vector2 position;
    private Paint paint;
    public float radius;
    public RectF rect;

    public Pac(float x, float y, float radius)
    {
        this.radius = radius;

        float left = x - radius;
        float top = y - radius;
        float right = x + radius;
        float bottom = y + radius;
        rect = new RectF(left, top, right, bottom);

        position = new Vector2(rect.centerX(), rect.centerY());

        paint = new Paint();
        paint.setColor(Color.WHITE);
    }

    public void render(Canvas canvas, float progress)
    {
        float startangle = 45 - (progress / 2);
        float sweepangle = 270 + progress;

        canvas.drawArc(rect, startangle, sweepangle, true, paint);
    }

    public void move(float dx, float dy)
    {
        position.set(dx, dy);
        rect.set(position.x - radius, position.y - radius, position.x + radius, position.y + radius);
    }

    public void moveX(float dx)
    {
        move(dx, position.y);
    }

    public void moveY(float dy)
    {
        move(position.x, dy);
    }

    public void setColor(int color)
    {
        this.paint.setColor(color);
    }
}

package com.emadoki.edloader;

public class Vector2
{
    public float x;
    public float y;

    public Vector2()
    {
        this.x = 0;
        this.y = 0;
    }

    public Vector2(float x, float y)
    {
        this.x = x;
        this.y = y;
    }

    public void set(float x, float y)
    {
        this.x = x;
        this.y = y;
    }

    /**
     * Add value into x and y
     * @param value add into x and y
     */
    public void add(float value)
    {
        this.x += value;
        this.y += value;
    }

    /**
     * Add dx into x and dy into y
     * @param dx add into x
     * @param dy add into y
     */
    public void add(float dx, float dy)
    {
        this.x += dx;
        this.y += dy;
    }

    /**
     * Subtract value from x and y
     * @param value subtract from x and y
     */
    public void subtract(float value)
    {
        this.x -= value;
        this.y -= value;
    }

    /**
     * Subtract dx from x and dy from y
     * @param dx subtract from x
     * @param dy subtract from y
     */
    public void subtract(float dx, float dy)
    {
        this.x -= dx;
        this.y -= dy;
    }

    /**
     * Multiply value to x and y
     * @param value multiply x and y
     */
    public void multiply(float value)
    {
        this.x *= value;
        this.y *= value;
    }

    /**
     * Multiply dx into x and dy into y
     * @param dx multiply x
     * @param dy multiply y
     */
    public void multiply(float dx, float dy)
    {
        this.x *= dx;
        this.y *= dy;
    }
}

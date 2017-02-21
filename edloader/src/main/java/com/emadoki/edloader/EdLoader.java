package com.emadoki.edloader;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.emadoki.edloader.type.BaseType;
import com.emadoki.edloader.type.Classic;
import com.emadoki.edloader.type.Grow;
import com.emadoki.edloader.type.Jump;
import com.emadoki.edloader.type.Pacman;
import com.emadoki.edloader.type.Swap;

public class EdLoader extends View
{
    private BaseType model;

    private LoaderType DEFAULT_TYPE;
    private float DEFAULT_RADIUS;
    private int DEFAULT_AMOUNT;
    private int DEFAULT_COLOR;

    private LoaderType type;
    public float radius;
    public int amount;
    public int color;

    public EdLoader(Context context)
    {
        super(context);
        init(context, null);
    }

    public EdLoader(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        init(context, attrs);
    }

    public EdLoader(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs)
    {
        DEFAULT_TYPE = LoaderType.CLASSIC;
        DEFAULT_RADIUS = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 6, context.getResources().getDisplayMetrics());
        DEFAULT_AMOUNT = 3;
        DEFAULT_COLOR = Color.WHITE;

        if (attrs != null)
        {
            TypedArray set = context.getTheme()
                    .obtainStyledAttributes(attrs, R.styleable.EdLoader, 0, 0);

            if (set != null)
            {
                type = LoaderType.fromId(set.getInt(R.styleable.EdLoader_type, 0));
                radius = set.getDimension(R.styleable.EdLoader_radius, DEFAULT_RADIUS);
                amount = set.getInteger(R.styleable.EdLoader_amount, DEFAULT_AMOUNT);
                amount = amount < 3 ? 3 : amount;
                color = set.getColor(R.styleable.EdLoader_color, DEFAULT_COLOR);
            }
        }
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom)
    {
        super.onLayout(changed, left, top, right, bottom);
        build();
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        if (model != null)
            model.render(canvas);
    }

    private void build()
    {
        switch (type){
            case CLASSIC:
                model = new Classic();
                break;
            case GROW:
                model = new Grow();
                break;
            case SWAP:
                model = new Swap();
                break;
            case JUMP:
                model = new Jump();
                break;
            case PACMAN:
                model = new Pacman();
                break;
        }

        model.setup(this);
        model.setListener(new InvalidateListener());
    }

    /**
     * Listening for animator event callback from listener,
     * to invalidate the canvas
     */
    private class InvalidateListener implements OnInvalidateListener
    {
        @Override
        public void onInvalidate()
        {
            invalidate();
        }
    }
}

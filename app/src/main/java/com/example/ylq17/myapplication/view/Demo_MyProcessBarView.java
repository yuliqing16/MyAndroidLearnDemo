package com.example.ylq17.myapplication.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.example.ylq17.myapplication.R;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.EView;

/**
 * Created by fyq on 16/5/15.
 */
@EView
public class Demo_MyProcessBarView extends View {
    private int mFirstColor;
    private int mSecondColor;
    private int mCircleWidth;
    private Paint mPaint;
    private int mProgress;
    private int mSpeed;
    private boolean isNext = false;

    public Demo_MyProcessBarView(Context context) {
        this(context, null);
    }

    public Demo_MyProcessBarView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Demo_MyProcessBarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.Demo_MyProcessBarView, defStyleAttr, 0);
        int n = a.getIndexCount();
        for (int i = 0; i < n; i++) {
            int attr = a.getIndex(i);
            switch (attr) {
                case R.styleable.Demo_MyProcessBarView_firstColor:
                    mFirstColor = a.getColor(attr, Color.GREEN);
                    break;
                case R.styleable.Demo_MyProcessBarView_secondColor:
                    mSecondColor = a.getColor(attr, Color.RED);
                    break;
                case R.styleable.Demo_MyProcessBarView_speed:
                    mSpeed = a.getInt(attr, 20);
                    break;
                case R.styleable.Demo_MyProcessBarView_circulWidth:
                    mCircleWidth = a.getDimensionPixelSize(attr, (int) TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_PX, 20, getResources().getDisplayMetrics()
                    ));
                    break;
            }
        }

        a.recycle();
        mPaint = new Paint();
        mProgress = 0;
        progress();

    }

    @Background
    void progress() {
        while (true) {
            mProgress++;
            if (mProgress == 360) {
                mProgress = 0;
                isNext = !isNext;
            }
            postInvalidate();
            try {
                Thread.sleep(mSpeed);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int center = getWidth() / 2;
        int radius = center - mCircleWidth / 2;
        mPaint.setStrokeWidth(mCircleWidth);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);

        RectF oval = new RectF(center - radius, center - radius, center + radius, center + radius);

        if (!isNext) {
            mPaint.setColor(mFirstColor);
            canvas.drawCircle(center,center, radius, mPaint);
            mPaint.setColor(mSecondColor);
            canvas.drawArc(oval, -90, mProgress, false, mPaint);
        } else {
            mPaint.setColor(mSecondColor);
            canvas.drawCircle(center,center, radius, mPaint);
            mPaint.setColor(mFirstColor);
            canvas.drawArc(oval, -90, mProgress, false, mPaint);
        }
    }
}











package com.example.ylq17.myapplication.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.EView;

/**
 * Created by fyq on 16/5/15.
 */
@EView
public class Demo_MyProcessBarView extends View {
    private Paint mPaint;
    private String mTitle;
    public Demo_MyProcessBarView(Context context) {
        this(context, null);
    }

    public Demo_MyProcessBarView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Demo_MyProcessBarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setTextSize(40);
        //mPaint.setStyle(Paint.Style.FILL);
        //mPaint.setStrokeWidth(5);
        mTitle = "Hello Wordl!!!";

        //play();

    }
/*
    private int positionY = 10;
    @Override
    protected void onDraw(Canvas canvas) {
        //super.onDraw(canvas);
        canvas.drawText(mTitle, 0, mTitle.length(), 0, positionY, mPaint);
        canvas.drawCircle(300,300, positionY, mPaint);
    }

    @Background
    void play(){
        while (true) {
            positionY++;
            if (positionY >= 300) {
                positionY = 10;
            }
            postInvalidate();
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    */

}

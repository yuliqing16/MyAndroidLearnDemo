package com.example.ylq17.myapplication.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.StrictMode;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EView;

/**
 * Created by fyq on 16/5/16.
 */
@EView
public class Demo_CanvasView extends View{
    private Paint mPaint;
    public Demo_CanvasView(Context context) {
        this(context, null);
    }

    public Demo_CanvasView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Demo_CanvasView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //changeXY();
        rotateStart();
    }

    float X;
    float Y;
    int rotate = 0;
    @Background
    void rotateStart(){
        while (true) {
            rotate = (rotate + 6 ) % 360;
            postInvalidate();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Background
    void changeXY() {
        int calcX = 1;
        int calcY  = 1;
        int r = 250;
        int count = 0;
        while (true) {

            for (int i = 0; i <= 90; i += 3) {
                double y = Math.sin(i * Math.PI / 180.0f) * r;
                double x = Math.sqrt(r * r - y * y);
                X = (float)(500.0f + calcX * x);
                Y = (float)(300.0f + calcY * y);
                postInvalidate();
                try {
                    Thread.sleep(30);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            count++;
            if (count< 0) count = 0;
            switch (count % 4) {
                case 0:
                    calcX = -1;
                    calcY = 1;
                    break;
                case 1:
                    calcX = -1;
                    calcY = -1;
                    break;
                case 2:
                    calcX = 1;
                    calcY = -1;
                    break;
                case 3:
                    calcX = 1;
                    calcY = 1;
                    break;
            }



        }

    }
    @Override
    protected void onDraw(Canvas canvas) {
        //super.onDraw(canvas);
        mPaint = new Paint();
        mPaint.setTextSize(30);
        mPaint.setColor(Color.BLACK);

        canvas.drawText("画圆", 10, 80, mPaint);
        canvas.drawCircle(200,80,40,mPaint);
        mPaint.setAntiAlias(true);
        canvas.drawCircle(380,80,80, mPaint);

        canvas.drawText("画线",10, 200, mPaint);
        canvas.drawLine(80,170,130,240, mPaint);
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawArc(new RectF(200,200, 250, 260), 180, 180, false, mPaint);
        canvas.drawArc(new RectF(300,200, 350, 260), 180, 180, false, mPaint);
        canvas.drawArc(new RectF(225,240, 325, 290), 0, 180, false, mPaint);

        int r = 300;
        for (int i = 0; i <= 90; i += 6) {
            double y = Math.sin(i * Math.PI / 180) * r;
            double x = Math.sqrt(r * r - y * y);
            canvas.drawLine(X, Y, (float)(getWidth() / 2 + x), (float)(getHeight() / 2 + y), mPaint);
            canvas.drawLine(X, Y, (float)(getWidth() / 2 - x), (float)(getHeight() / 2 + y), mPaint);
            canvas.drawLine(X, Y, (float)(getWidth() / 2 + x), (float)(getHeight() / 2 - y), mPaint);
            canvas.drawLine(X, Y, (float)(getWidth() / 2 - x), (float)(getHeight() / 2 - y), mPaint);
        }

        canvas.drawLine(500,800, 500, 300, mPaint);
        canvas.save();
        canvas.rotate(rotate, 500, 800);
        canvas.drawLine(500,800, 500, 300, mPaint);
        canvas.restore();
        mPaint = null;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                X = event.getX();
                Y = event.getY();
                postInvalidate();
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return true;
    }

}

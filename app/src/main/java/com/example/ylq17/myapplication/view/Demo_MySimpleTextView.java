package com.example.ylq17.myapplication.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.example.ylq17.myapplication.R;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Created by ylq17 on 2016/5/14.
 */
public class Demo_MySimpleTextView extends View {

    private String mTitleText;
    private int mTileTextColor;
    private int mTitleTextSize;

    private Rect mBound;
    private Paint mPaint;
    public Demo_MySimpleTextView(Context context) {
        this(context, null);
    }

    public Demo_MySimpleTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Demo_MySimpleTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.Demo_MySimpleTextView, defStyleAttr, 0);

        int size = a.getIndexCount();
        for (int i = 0; i < size; i++) {
            int attr = a.getIndex(i);
            switch (attr) {
                case R.styleable.Demo_MySimpleTextView_titleText:
                    mTitleText = a.getString(attr);
                    break;
                case R.styleable.Demo_MySimpleTextView_titleTextCLR:
                    mTileTextColor = a.getColor(attr, Color.BLACK);
                    break;
                case R.styleable.Demo_MySimpleTextView_titleTextSize:
                    mTitleTextSize = a.getDimensionPixelSize(attr, (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 16, getResources().getDisplayMetrics()));
                    break;
            }
        }

        a.recycle();

        mPaint = new Paint();
        mPaint.setTextSize(mTitleTextSize);
        mPaint.setColor(mTileTextColor);
        mBound = new Rect();
        mPaint.getTextBounds(mTitleText, 0, mTitleText.length(), mBound);

        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mTitleText = randomText();
                mPaint.getTextBounds(mTitleText, 0, mTitleText.length(), mBound);
                postInvalidate();
            }
        });
    }

    private String randomText() {
        Random random = new Random();
        Set<Integer> set = new HashSet<>();
        while (set.size() < 3) {
            int randomInt = random.nextInt(10);
            set.add(randomInt);
        }

        StringBuffer sb = new StringBuffer();
        for(Integer i : set) {
            sb.append("" + i);
        }

        return sb.toString();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int width;
        int height;
        if (widthMode == MeasureSpec.EXACTLY) {
            width = widthSize;
        } else {
            mPaint.setTextSize(mTitleTextSize);
            mPaint.getTextBounds(mTitleText, 0, mTitleText.length(), mBound);
            float textWidth = mBound.width();
            int desired = (int) (getPaddingLeft() + textWidth + getPaddingRight());
            width = desired;
        }

        if (heightMode == MeasureSpec.EXACTLY) {
            height = heightSize;
        } else {
            mPaint.setTextSize(mTitleTextSize);
            mPaint.getTextBounds(mTitleText, 0, mTitleText.length(), mBound);
            float textWidth = mBound.height();
            int desired = (int) (getPaddingTop() + textWidth + getPaddingBottom());
            height = desired;
        }

        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mPaint.setColor(Color.YELLOW);
        canvas.drawRect(0,0,getMeasuredWidth(), getMeasuredHeight(), mPaint);

        mPaint.setColor(mTileTextColor);
        canvas.drawText(mTitleText, getWidth() / 2 - mBound.width() / 2, getHeight() / 2 + mBound.height() / 2, mPaint);
    }
}

package com.example.ylq17.myapplication.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;

import com.example.ylq17.myapplication.R;

/**
 * Created by fyq on 16/5/15.
 */
public class Demo_MySimpleImageView extends View{

    public static final int IMAGE_SCALE_FITXY = 0;
    public static final int IMAGE_SCALE_SCALE = 1;
    private String mTitleText;
    private int mTileTextColor;
    private int mTitleTextSize;
    private Bitmap mBitmap;
    private int mImageScale;

    private Rect mImageBound;
    private Rect mTextBound;
    private Paint mPaint;

    private int mWidth;
    private int mHeight;

    public Demo_MySimpleImageView(Context context) {
        this(context, null);
    }

    public Demo_MySimpleImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Demo_MySimpleImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.Demo_MySimpleImageView,defStyleAttr, 0);
        int size = a.getIndexCount();
        for (int i = 0; i < size; i++) {
            int attr = a.getIndex(i);
            switch (attr) {
                case R.styleable.Demo_MySimpleImageView_image:
                    mBitmap = BitmapFactory.decodeResource(getResources(), a.getResourceId(attr, 0));
                    break;
                case R.styleable.Demo_MySimpleImageView_imageScaleType:
                    mImageScale = a.getInt(attr, 0);
                    break;
                case R.styleable.Demo_MySimpleImageView_titleText:
                    mTitleText = a.getString(attr);
                    break;
                case R.styleable.Demo_MySimpleImageView_titleTextCLR:
                    mTileTextColor = a.getColor(attr, Color.BLACK);
                    break;
                case R.styleable.Demo_MySimpleImageView_titleTextSize:
                    mTitleTextSize = a.getDimensionPixelSize(
                           attr, (int) TypedValue.applyDimension(
                                    TypedValue.COMPLEX_UNIT_SP,16,getResources().getDisplayMetrics()
                            )
                    );
                    break;
            }
        }

        a.recycle();
        mImageBound = new Rect();
        mPaint = new Paint();
        mTextBound = new Rect();

        mPaint.setTextSize(mTitleTextSize);
        mPaint.getTextBounds(mTitleText, 0 ,mTitleText.length(), mTextBound);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int specMode = MeasureSpec.getMode(widthMeasureSpec);
        int specSize = MeasureSpec.getSize(widthMeasureSpec);

        if (specMode == MeasureSpec.EXACTLY) {
            Log.e("onMeasure", "EXACTLY " + specSize);
            mWidth = specSize;
        } else {
            int desireByImage = getPaddingLeft() + getPaddingRight() + mBitmap.getWidth();
            int desireByTitle = getPaddingLeft() + getPaddingRight() + mTextBound.width();

            if (specMode == MeasureSpec.AT_MOST) {
                // wrap_content
                int desire = Math.max(desireByImage, desireByTitle);
                mWidth = Math.min(desire, specSize);
                Log.e("xxx", "AT_MOST mWidth:desire=" + desire + " specSize=" + specSize);
            }
        }

        specMode = MeasureSpec.getMode(heightMeasureSpec);
        specSize = MeasureSpec.getSize(heightMeasureSpec);

        if (specMode == MeasureSpec.EXACTLY) {
            Log.e("onMeasure", "EXACTLY " + specSize);
            mHeight = specSize;
        } else {
            int desire = getPaddingTop() + getPaddingBottom() + mBitmap.getHeight() + mTextBound.height();
            if (specMode == MeasureSpec.AT_MOST) {
                // wrap_content
                mHeight = Math.min(desire, specSize);
                Log.e("xxx", "AT_MOST mHeight:desire=" + desire + " specSize=" + specSize);
            }
        }
        setMeasuredDimension(mWidth, mHeight);
        Log.e("xxx", "mHeight = " + mWidth + " ,mHeight=" + mHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mPaint.setStrokeWidth(4);
        mPaint.setColor(Color.CYAN);
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawRect(0,0, getMeasuredWidth(), getMeasuredHeight(), mPaint);

        mImageBound.left = getPaddingLeft();
        mImageBound.right = mWidth - getPaddingRight();
        mImageBound.top = getPaddingTop();
        mImageBound.bottom = mHeight - getPaddingBottom();

        mPaint.setColor(mTileTextColor);
        mPaint.setStyle(Paint.Style.FILL);

        if (mTextBound.width() > mWidth) {
            TextPaint paint = new TextPaint(mPaint);
            String msg = TextUtils.ellipsize(mTitleText, paint,(float) mWidth - getPaddingLeft() - getPaddingRight()
                    ,TextUtils.TruncateAt.END).toString();
            canvas.drawText(msg, getPaddingLeft(),mHeight - getPaddingBottom(), mPaint);
        } else {
            canvas.drawText(mTitleText, mWidth / 2 - mTextBound.width() * 1.0f / 2, mHeight - getPaddingBottom(), mPaint);
        }

        mImageBound.bottom -= mTextBound.height();

        if (mImageScale == IMAGE_SCALE_FITXY) {

            canvas.drawBitmap(mBitmap, null, mImageBound, mPaint);
        } else {
            mImageBound.left = mWidth / 2 - mBitmap.getWidth() / 2;
            mImageBound.right = mWidth / 2 + mBitmap.getWidth() / 2;

            mImageBound.top = (mHeight - mTextBound.height()) / 2 - mBitmap.getHeight() / 2;
            mImageBound.bottom = (mHeight - mTextBound.height()) / 2 + mBitmap.getHeight() / 2;

            canvas.drawBitmap(mBitmap, null, mImageBound, mPaint);
        }
    }
}










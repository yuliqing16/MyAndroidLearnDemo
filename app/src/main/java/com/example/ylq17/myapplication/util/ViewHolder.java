package com.example.ylq17.myapplication.util;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by ylq16 on 2015/6/3.
 */
public class ViewHolder {
    private SparseArray<View> mViews;
    private int mPosition;
    private View mConvertView;
    public ViewHolder(Context context,
                      ViewGroup parent,
                      int layoutId,
                      int position){
        this.mPosition = position;
        this.mViews = new SparseArray<View>();

        mConvertView = LayoutInflater.from(context).inflate(layoutId, parent, false);
        mConvertView.setTag(this);

    }

    public  static ViewHolder get(Context context,
                                  View convertView,
                                  ViewGroup parent,
                                  int layoutId,
                                  int position){
        if (convertView == null){
            return new ViewHolder(context, parent,layoutId,position);
        }
        else {
            ViewHolder holder = (ViewHolder)convertView.getTag();
            holder.mPosition = position;
            return holder;
        }

    }

    public View getConvertView() {
        return mConvertView;
    }

    @SuppressWarnings("unchecked")
	public <T extends View> T getView(int viewId){
        View view = mViews.get(viewId);

        if (view == null){
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }

        return (T)view;
    }

    public ViewHolder setText(int viewId, String text)
    {
        TextView tv = getView(viewId);
        tv.setText(text);
        return  this;
    }

    public ViewHolder setImageResource(int viewId, int resId)
    {
        ImageView tv = getView(viewId);
        tv.setImageResource(resId);
        return  this;
    }

    public ViewHolder setViewBackground(int viewId, int color) {
        View tv = getView(viewId);
        tv.setBackgroundColor(color);
        return  this;
    }
}

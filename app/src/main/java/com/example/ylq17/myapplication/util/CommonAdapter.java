package com.example.ylq17.myapplication.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by ylq16 on 2015/6/3.
 */
public abstract class CommonAdapter<T> extends BaseAdapter{

    protected Context mContext;
    protected List<T> mdatas;
    protected LayoutInflater mInflater;
    protected int mlayoutId;

    public CommonAdapter(Context context, List<T> datas, int layoutId)
    {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(context);
        this.mdatas = datas;
        this.mlayoutId = layoutId;
    }
    @Override
    public int getCount() {
        return mdatas.size();
    }

    @Override
    public T getItem(int position) {
        return mdatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        ViewHolder viewHolder = ViewHolder.get(mContext, convertView, parent
                , this.mlayoutId, position);

        convert(viewHolder, getItem(position));

        return viewHolder.getConvertView();
    }

    public abstract void convert(ViewHolder holder, T t);
}

package com.meten.ifuture.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.tencent.bugly.proguard.T;

import java.util.List;

/**
 * Created by Administrator on 2016/5/11 0011.
 */
public abstract class BasicAdapter<T> extends BaseAdapter {
    public List<T> data;
    public Context context;
    public LayoutInflater inflater;

    public BasicAdapter(List<T> data, Context context) {
        this.data = data;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return createView(position, convertView, parent);
    }

    public abstract View createView(int position, View convertView, ViewGroup parent);
}

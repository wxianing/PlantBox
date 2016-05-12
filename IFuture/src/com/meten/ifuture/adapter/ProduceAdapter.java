package com.meten.ifuture.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.meten.ifuture.R;
import com.meten.ifuture.bean.produce.DataListBean;

import java.util.List;

/**
 * Created by Administrator on 2016/5/12 0012.
 */
public class ProduceAdapter extends BasicAdapter<DataListBean> {
    public ProduceAdapter(List<DataListBean> data, Context context) {
        super(data, context);
    }

    @Override
    public View createView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            inflater.inflate(R.layout.item_listview_latest, parent, false);
        }
        return convertView;
    }

    private static class ViewHolder{

    }
}

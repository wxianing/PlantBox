package com.sinoinnovo.plantbox.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.sinoinnovo.plantbox.R;
import com.sinoinnovo.plantbox.MainApplication;
import com.sinoinnovo.plantbox.utils.ToastUtils;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/5/13 0013.
 */
public class ProduceGvAdapter extends BasicAdapter<String> {
    public ProduceGvAdapter(List<String> data, Context context) {
        super(data, context);
    }

    @Override
    public View createView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_progv_layout, parent, false);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        MainApplication.imageLoader.displayImage(data.get(position), vh.imageView, MainApplication.optionsRounds);

        return convertView;
    }

    protected class ViewHolder {
        @Bind(R.id.pro_gv_img)
        protected ImageView imageView;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}

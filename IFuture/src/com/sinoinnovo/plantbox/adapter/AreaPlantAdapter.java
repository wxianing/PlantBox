package com.sinoinnovo.plantbox.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.sinoinnovo.plantbox.MainApplication;
import com.sinoinnovo.plantbox.R;
import com.sinoinnovo.plantbox.bean.areabean.MyAreaBean;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/5/24 0024.
 */
public class AreaPlantAdapter extends BasicAdapter<MyAreaBean.DataListBean> {
    public AreaPlantAdapter(List<MyAreaBean.DataListBean> data, Context context) {
        super(data, context);
    }

    @Override
    public View createView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_myarea_layout, parent, false);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }

        vh.cnName.setText(data.get(position).getProductName());
        ImageLoader.getInstance().displayImage(data.get(position).getThumbImg(), vh.mImageView, MainApplication.optionsRounds);

        return convertView;
    }

    protected class ViewHolder {
        @Bind(R.id.cnName)
        protected TextView cnName;
        @Bind(R.id.imageView)
        protected ImageView mImageView;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}

package com.sinoinnovo.plantbox.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sinoinnovo.plantbox.MainApplication;
import com.sinoinnovo.plantbox.R;
import com.sinoinnovo.plantbox.bean.baike.PlantBaiKe;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/5/25 0025.
 */
public class MonitoringAdapter extends BasicAdapter<PlantBaiKe.DataListBean> {
    public MonitoringAdapter(List<PlantBaiKe.DataListBean> data, Context context) {
        super(data, context);
    }

    @Override
    public View createView(int position, View convertView, ViewGroup parent) {
        PlantBaiKe.DataListBean bean = data.get(position);
        ViewHolder vh = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_monitoring_layout, parent, false);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        MainApplication.imageLoader.displayImage(bean.getImgPath(), vh.imageView, MainApplication.optionsRounds);

        vh.content.setText(bean.getSummary());
        vh.nameTv.setText(bean.getTitle());

        return convertView;
    }

    protected class ViewHolder {

        @Bind(R.id.name_tv)
        protected TextView nameTv;
        @Bind(R.id.imageView)
        protected ImageView imageView;

        @Bind(R.id.content_tv)
        protected TextView content;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}

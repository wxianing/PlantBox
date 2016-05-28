package com.sinoinnovo.plantbox.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.sinoinnovo.plantbox.MainApplication;
import com.sinoinnovo.plantbox.R;
import com.sinoinnovo.plantbox.bean.tutorial.ReferUserTutorial;
import com.sinoinnovo.plantbox.utils.ImageUtils;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/5/25 0025.
 */
public class ReferToUserTutorialAdapter extends BasicAdapter<ReferUserTutorial.DataListBean> {
    public ReferToUserTutorialAdapter(List<ReferUserTutorial.DataListBean> data, Context context) {
        super(data, context);
    }

    @Override
    public View createView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_user_tutorial, parent, false);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        ImageLoader.getInstance().displayImage(data.get(position).getPhoto(), vh.headerImg, MainApplication.optionsCircle);
        vh.titleName.setText(data.get(position).getHeadTitle());
        vh.timeTv.setText(data.get(position).getCreateTime());

        return convertView;
    }

    protected class ViewHolder {

        @Bind(R.id.header_img)
        protected ImageView headerImg;

        @Bind(R.id.title_name)
        protected TextView titleName;
        @Bind(R.id.time_tv)
        protected TextView timeTv;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}

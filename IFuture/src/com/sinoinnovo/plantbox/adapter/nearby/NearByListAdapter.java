package com.sinoinnovo.plantbox.adapter.nearby;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.sinoinnovo.plantbox.MainApplication;
import com.sinoinnovo.plantbox.R;
import com.sinoinnovo.plantbox.adapter.BasicAdapter;
import com.sinoinnovo.plantbox.bean.nearby.NearByDataList;
import com.sinoinnovo.plantbox.constant.URL;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/5/18 0018.
 */
public class NearByListAdapter extends BasicAdapter<NearByDataList> {
    public NearByListAdapter(List<NearByDataList> data, Context context) {
        super(data, context);
    }

    @Override
    public View createView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh = null;
        NearByDataList bean = data.get(position);
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_nearby_list, parent, false);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);

        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        MainApplication.imageLoader.displayImage(URL.BASE_URL + bean.getThumbImg(), vh.img);
        vh.cnname.setText(bean.getCnName());
        vh.distincts.setText("距离" + ((int) bean.getDistincts()) + "km");

        vh.likeCount.setText("粉丝数：" + bean.getPraiseCount());

        return convertView;
    }

    protected class ViewHolder {
        @Bind(R.id.header_img)
        protected ImageView img;
        @Bind(R.id.user_name)
        protected TextView cnname;
        @Bind(R.id.distance)
        protected TextView distincts;
        @Bind(R.id.like_count)
        protected TextView likeCount;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}

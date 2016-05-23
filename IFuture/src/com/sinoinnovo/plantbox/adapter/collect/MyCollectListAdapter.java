package com.sinoinnovo.plantbox.adapter.collect;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sinoinnovo.plantbox.R;
import com.sinoinnovo.plantbox.MainApplication;
import com.sinoinnovo.plantbox.adapter.BasicAdapter;
import com.sinoinnovo.plantbox.bean.collect.CollectBean;
import com.sinoinnovo.plantbox.constant.URL;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/5/19 0019.
 */
public class MyCollectListAdapter extends BasicAdapter<CollectBean.DataBean.DataListBean> {
    public MyCollectListAdapter(List<CollectBean.DataBean.DataListBean> data, Context context) {
        super(data, context);
    }

    @Override
    public View createView(int position, View convertView, ViewGroup parent) {
        CollectBean.DataBean.DataListBean bean = data.get(position);
        ViewHolder vh = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_mycollect_layout, parent, false);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        Log.e("bean", bean.getThumbImg());
        MainApplication.imageLoader.displayImage(URL.BASE_URL + bean.getThumbImg(), vh.imageView);
        vh.title.setText(bean.getTitle());
        vh.createTime.setText(bean.getCreateTime());
        return convertView;
    }

    protected class ViewHolder {
        @Bind(R.id.imageView)
        protected ImageView imageView;
        @Bind(R.id.title)
        protected TextView title;
        @Bind(R.id.create_time)
        protected TextView createTime;


        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}

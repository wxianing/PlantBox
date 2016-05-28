package com.sinoinnovo.plantbox.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.sinoinnovo.plantbox.MainApplication;
import com.sinoinnovo.plantbox.R;
import com.sinoinnovo.plantbox.bean.tutorial.Tusercourse;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/5/27 0027.
 */
public class UserTutoDetAdapter extends BasicAdapter<Tusercourse.RecordsBean> {
    private ProduceGvAdapter mAdapter;

    public UserTutoDetAdapter(List<Tusercourse.RecordsBean> data, Context context) {
        super(data, context);
    }

    @Override
    public View createView(int position, View convertView, ViewGroup parent) {
        Tusercourse.RecordsBean bean = data.get(position);
        List<String> imageUrls = bean.getImages();
        ViewHolder vh = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_user_tutorial_details, parent, false);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        mAdapter = new ProduceGvAdapter(imageUrls, context);
        vh.mGridView.setAdapter(mAdapter);
        vh.contentTv.setText(data.get(position).getContext());
        for (int i = 0; i < imageUrls.size(); i++) {
            ImageView imageView = new ImageView(context);
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            imageView.setLayoutParams(params);
            ImageLoader.getInstance().displayImage(imageUrls.get(i), imageView, MainApplication.options);
            vh.parentImg.addView(imageView);
        }

        return convertView;
    }

    protected class ViewHolder {
        @Bind(R.id.content_tv)
        protected TextView contentTv;
        @Bind(R.id.parent_img)
        protected LinearLayout parentImg;
        @Bind(R.id.hlistView)
        protected GridView mGridView;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}

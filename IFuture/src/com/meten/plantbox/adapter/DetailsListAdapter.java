package com.meten.plantbox.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.meten.plantbox.MainApplication;
import com.meten.plantbox.R;
import com.meten.plantbox.utils.ToastUtils;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/5/13 0013.
 */
public class DetailsListAdapter extends BasicAdapter<String> {
    public DetailsListAdapter(List<String> data, Context context) {
        super(data, context);
    }

    @Override
    public View createView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_details_layout, parent, false);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        MainApplication.imageLoader.displayImage(data.get(position), vh.imageView);
        vh.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.show(context,"点击图片");
            }
        });
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

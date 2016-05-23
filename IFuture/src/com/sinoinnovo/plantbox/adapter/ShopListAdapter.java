package com.sinoinnovo.plantbox.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sinoinnovo.plantbox.R;
import com.sinoinnovo.plantbox.MainApplication;
import com.sinoinnovo.plantbox.bean.shoplist.SHopDataList;
import com.sinoinnovo.plantbox.constant.URL;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/5/16 0016.
 */
public class ShopListAdapter extends BasicAdapter<SHopDataList> {

    public ShopListAdapter(List<SHopDataList> data, Context context) {
        super(data, context);
    }

    @Override
    public View createView(int position, View convertView, ViewGroup parent) {
        SHopDataList list = data.get(position);
        ViewHolder vh = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_shop_list, parent, false);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }

        MainApplication.imageLoader.displayImage(URL.BASE_URL+list.getThumbImg(),vh.imageView);
        vh.notice.setText(list.getNotice());
//        vh.price.setText(list.getMinSalePrice());

        return convertView;
    }

    protected class ViewHolder {
        @Bind(R.id.notice_introduce)
        protected TextView notice;
        @Bind(R.id.imageView)
        protected ImageView imageView;
        @Bind(R.id.price_tv)
        protected TextView price;
        @Bind(R.id.already_buy)
        protected TextView alreadyBuy;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}

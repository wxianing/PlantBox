package com.sinoinnovo.plantbox.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sinoinnovo.plantbox.R;
import com.sinoinnovo.plantbox.bean.order.MyOrderBean;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/5/27 0027.
 */
public class MyOrderListAdapter extends BasicAdapter<MyOrderBean.DataListBean> {
    public MyOrderListAdapter(List<MyOrderBean.DataListBean> data, Context context) {
        super(data, context);
    }

    @Override
    public View createView(int position, View convertView, ViewGroup parent) {
        MyOrderBean.DataListBean bean = data.get(position);
        ViewHolder vh = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_myorder_list, parent, false);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        vh.orderNumber.setText("订单编号：" + bean.getOrderNo());
        vh.orderTime.setText("下单时间：" + bean.getCreateTime());
        vh.orderPerson.setText("收  货  人：" + bean.getCustomName());
        vh.orderAddress.setText("收货地址：" + bean.getAddress());
        return convertView;
    }

    protected class ViewHolder {
        @Bind(R.id.order_number)
        protected TextView orderNumber;
        @Bind(R.id.order_time)
        protected TextView orderTime;
        @Bind(R.id.order_person)
        protected TextView orderPerson;
        @Bind(R.id.order_address)
        protected TextView orderAddress;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

}

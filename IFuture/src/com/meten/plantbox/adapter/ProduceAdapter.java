package com.meten.plantbox.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.meten.plantbox.R;
import com.meten.plantbox.ShopDetailActivity;
import com.meten.plantbox.bean.produce.DataListBean;
import com.meten.plantbox.utils.ToastUtils;
import com.meten.plantbox.widget.HListView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/5/12 0012.
 */
public class ProduceAdapter extends BasicAdapter<DataListBean> implements View.OnClickListener {
    private ProduceGvAdapter mAdapter;
    private int userId;
    private int goodId;
    private int count;


    public ProduceAdapter(List<DataListBean> data, Context context) {
        super(data, context);

    }

    @Override
    public View createView(int position, View convertView, ViewGroup parent) {
        DataListBean bean = data.get(position);
        List<String> imageUrls = bean.getProduct().getPictures();
        ViewHolder vh = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_listview_latest, parent, false);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        if (bean != null) {

            vh.cnName.setText(bean.getUser().getCnName());
            vh.notice.setText("        " + bean.getProduct().getNotice());

            mAdapter = new ProduceGvAdapter(imageUrls, context);
            vh.mGridView.setAdapter(mAdapter);

            count = position;

            vh.notice.setOnClickListener(this);

            vh.commentCount.setOnClickListener(this);
        }
        return convertView;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.notice_tv:
                userId = data.get(count).getUser().getUserId();
                goodId = data.get(count).getProduct().getId();
                Log.e("ProduceAdapter", "userId:" + userId + "goodId:" + goodId);
                Intent intent = new Intent(context, ShopDetailActivity.class);
                intent.putExtra("userId", userId);
                intent.putExtra("goodId", goodId);
                context.startActivity(intent);
                break;
            case R.id.commot_tv:
                ToastUtils.show(context, "点击了评论");
                break;
        }
    }


    protected class ViewHolder {
        @Bind(R.id.cnname)
        protected TextView cnName;
        @Bind(R.id.hlistView)
        protected HListView mGridView;
        @Bind(R.id.notice_tv)
        protected TextView notice;
        //
//        @Bind(R.id.transpond_tv)
//        protected TextView transpondCount;//转发
//        @Bind(R.id.like_tv)
//        protected TextView likeCount;//点赞
        @Bind(R.id.commot_tv)
        protected TextView commentCount;//评论


        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}

package com.sinoinnovo.plantbox.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.lidroid.xutils.http.RequestParams;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.sinoinnovo.plantbox.MainApplication;
import com.sinoinnovo.plantbox.R;
import com.sinoinnovo.plantbox.activity.CommentActivity;
import com.sinoinnovo.plantbox.activity.MyBaseAreaActivity;
import com.sinoinnovo.plantbox.activity.ShopDetailActivity;
import com.sinoinnovo.plantbox.bean.produce.DataListBean;
import com.sinoinnovo.plantbox.constant.URL;
import com.sinoinnovo.plantbox.http.HttpRequestCallBack;
import com.sinoinnovo.plantbox.http.HttpRequestUtils;
import com.sinoinnovo.plantbox.http.LikeCallBack;
import com.sinoinnovo.plantbox.http.RequestParamsUtils;
import com.sinoinnovo.plantbox.model.ResultInfo;
import com.sinoinnovo.plantbox.utils.ShareUtils;
import com.sinoinnovo.plantbox.utils.ToastUtils;
import com.sinoinnovo.plantbox.view.CircularImage;
import com.sinoinnovo.plantbox.widget.HListView;

import org.json.JSONException;
import org.json.JSONObject;

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

    private int oid;
    private LikeCallBack callBack;

    public ProduceAdapter(List<DataListBean> data, Context context, LikeCallBack callBack) {
        super(data, context);
        this.callBack = callBack;
    }

    @Override
    public View createView(int position, View convertView, ViewGroup parent) {
        final DataListBean bean = data.get(position);
        List<String> imageUrls = bean.getPictures();
        ViewHolder vh = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_listview_latest, parent, false);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        vh.cnName.setText(bean.getCnName());
        vh.notice.setText("        " + bean.getContext());
        vh.likeCount.setText(bean.getPraiseCount() + "赞");
        vh.commentCount.setText(bean.getTotalComment() + "评论");
        vh.timeTv.setText(bean.getTimeStr());
        vh.cityName.setText(bean.getCity());
        ImageLoader.getInstance().displayImage(bean.getHeadPhoto(), vh.headerImg, MainApplication.optionsCircle);

        for (int i = 3; i < imageUrls.size(); i++) {
            if (imageUrls.size() > 2) {
                imageUrls.remove(i);
            }
        }
        mAdapter = new ProduceGvAdapter(imageUrls, context);
        vh.mGridView.setAdapter(mAdapter);
        oid = data.get(position).getId();
        final int count = position;
        this.position = position;
        vh.notice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userId = data.get(count).getUserId();
                goodId = data.get(count).getId();
                Intent intent = new Intent(context, ShopDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("listBean", bean);
                intent.putExtra("userId", userId);
                intent.putExtra("goodId", goodId);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
        vh.commentCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userId = data.get(count).getUserId();
                goodId = data.get(count).getId();
                Intent intent = new Intent(context, ShopDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("listBean", bean);
                intent.putExtra("userId", userId);
                intent.putExtra("goodId", goodId);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
        vh.likeCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                callBack.likeClick(count);
                userId = data.get(count).getUserId();
                goodId = data.get(count).getId();
                Intent intent = new Intent(context, ShopDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("listBean", bean);
                intent.putExtra("userId", userId);
                intent.putExtra("goodId", goodId);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
        vh.headerImg.setOnClickListener(this);
        vh.cnName.setOnClickListener(this);
        vh.transpondCount.setOnClickListener(this);

        return convertView;
    }

    int position = 1;

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.home_header_img:
                intent = new Intent(context, MyBaseAreaActivity.class);
                intent.putExtra("cnName", data.get(position).getCnName());
                context.startActivity(intent);
                break;
            case R.id.cnname:
                intent = new Intent(context, MyBaseAreaActivity.class);
                intent.putExtra("cnName", data.get(position).getCnName());
                context.startActivity(intent);
                break;
            case R.id.transpond_tv:
                ShareUtils.showShare(context);
                break;
        }
    }

    protected class ViewHolder {
        @Bind(R.id.cnname)
        protected TextView cnName;
        @Bind(R.id.hlistView)
        protected GridView mGridView;
        @Bind(R.id.notice_tv)
        protected TextView notice;
        @Bind(R.id.transpond_tv)
        protected TextView transpondCount;//转发
        @Bind(R.id.like_tv)
        protected TextView likeCount;//点赞
        @Bind(R.id.commot_tv)
        protected TextView commentCount;//评论
        @Bind(R.id.home_header_img)
        protected CircularImage headerImg;
        @Bind(R.id.time_tv)
        protected TextView timeTv;
        @Bind(R.id.city_name)
        protected TextView cityName;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}

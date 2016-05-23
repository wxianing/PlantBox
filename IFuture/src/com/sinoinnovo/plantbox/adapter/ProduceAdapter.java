package com.sinoinnovo.plantbox.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lidroid.xutils.http.RequestParams;
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
import com.sinoinnovo.plantbox.utils.ToastUtils;
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
    private int count;
    private int oid;
    private LikeCallBack callBack;
    private CommentClick commentClick;

    public interface CommentClick {
        public void myOnClik(View view, int oid);
    }

    public ProduceAdapter(List<DataListBean> data, Context context, LikeCallBack callBack) {
        super(data, context);
        this.callBack = callBack;
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
            vh.likeCount.setText(bean.getProduct().getHits() + "赞");
            mAdapter = new ProduceGvAdapter(imageUrls, context);
            vh.mGridView.setAdapter(mAdapter);
            count = position;
            vh.notice.setOnClickListener(this);
            vh.commentCount.setOnClickListener(this);
            vh.likeCount.setOnClickListener(this);
            vh.headerImg.setOnClickListener(this);
            vh.cnName.setOnClickListener(this);
        }
        return convertView;
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        oid = data.get(count).getProduct().getId();
        DataListBean listBean = data.get(count);
        switch (v.getId()) {
            case R.id.like_tv://点赞

                RequestParams params = RequestParamsUtils.getLikeParams(oid, "");
                HttpRequestUtils.create(context).send(URL.DIAN_ZAN_URL, params, new HttpRequestCallBack<ResultInfo>() {
                    @Override
                    public void onSuccess(ResultInfo resultInfo, int requestCode) {
                    }

                    @Override
                    public void onReponse(String result) {
                        super.onReponse(result);
                        Log.e("dianzan", result);
                        try {
                            JSONObject obj = new JSONObject(result);
                            int enumcode = obj.getInt("enumcode");
                            if (enumcode == 0) {
                                callBack.likeClick(enumcode);
                                ToastUtils.show(context, "成功点赞");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
                break;
            case R.id.notice_tv:

                userId = data.get(count).getUser().getUserId();
                goodId = data.get(count).getProduct().getId();
                Log.e("ProduceAdapter", "userId:" + userId + "goodId:" + goodId);
                intent = new Intent(context, ShopDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("listBean", listBean);
                intent.putExtra("userId", userId);
                intent.putExtra("goodId", goodId);
                intent.putExtras(bundle);
                context.startActivity(intent);
                break;
            case R.id.commot_tv:
                intent = new Intent(context, CommentActivity.class);
                intent.putExtra("oid", oid);
                context.startActivity(intent);
//                commentClick.myOnClik(v, oid);
                break;
            case R.id.home_header_img:
                intent = new Intent(context, MyBaseAreaActivity.class);
                intent.putExtra("cnName",data.get(count).getUser().getCnName());
                context.startActivity(intent);
                break;
            case R.id.cnname:
                intent = new Intent(context, MyBaseAreaActivity.class);
                intent.putExtra("cnName",data.get(count).getUser().getCnName());
                context.startActivity(intent);
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

        //        @Bind(R.id.transpond_tv)
//        protected TextView transpondCount;//转发
        @Bind(R.id.like_tv)
        protected TextView likeCount;//点赞
        @Bind(R.id.commot_tv)
        protected TextView commentCount;//评论
        @Bind(R.id.home_header_img)
        protected ImageView headerImg;


        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}

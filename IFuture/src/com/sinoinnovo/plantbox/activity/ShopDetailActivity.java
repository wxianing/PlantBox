package com.sinoinnovo.plantbox.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.lidroid.xutils.http.RequestParams;
import com.sinoinnovo.plantbox.R;
import com.sinoinnovo.plantbox.activity.base.BaseActivity;
import com.sinoinnovo.plantbox.adapter.CommentListAdapter;
import com.sinoinnovo.plantbox.adapter.ProduceAdapter;
import com.sinoinnovo.plantbox.adapter.ProduceGvAdapter;
import com.sinoinnovo.plantbox.bean.comment.Comments;
import com.sinoinnovo.plantbox.bean.produce.DataListBean;
import com.sinoinnovo.plantbox.constant.URL;
import com.sinoinnovo.plantbox.http.HttpRequestCallBack;
import com.sinoinnovo.plantbox.http.HttpRequestUtils;
import com.sinoinnovo.plantbox.http.RequestParamsUtils;
import com.sinoinnovo.plantbox.model.ResultInfo;
import com.sinoinnovo.plantbox.utils.JsonParse;
import com.sinoinnovo.plantbox.utils.ToastUtils;
import com.sinoinnovo.plantbox.view.MyListView;
import com.sinoinnovo.plantbox.widget.HListView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ShopDetailActivity extends BaseActivity implements View.OnClickListener {

    private DataListBean data;
    @Bind(R.id.cnname)
    protected TextView cnName;
    @Bind(R.id.hlistView)
    protected HListView mGridView;
    @Bind(R.id.notice_tv)
    protected TextView notice;

    @Bind(R.id.like_tv)
    protected TextView likeCount;//点赞
    @Bind(R.id.commot_tv)
    protected TextView commentCount;//评论

    private ProduceGvAdapter mAdapter;
    @Bind(R.id.title_tv)
    protected TextView title;
    @Bind(R.id.back_arrows)
    protected ImageView backImg;
    @Bind(R.id.listview)
    protected MyListView mListView;
    private List<Comments.DataListBean> mDatas;
    private CommentListAdapter mCommentAdapter;
    private int oid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_detail);
        ButterKnife.bind(this);

        data = (DataListBean) getIntent().getSerializableExtra("listBean");
        oid = data.getId();
        initView();
        initData();
        initEvent();
    }

    private void initEvent() {
        notice.setOnClickListener(this);
        commentCount.setOnClickListener(this);
        likeCount.setOnClickListener(this);
        backImg.setOnClickListener(this);
    }

    private void initData() {
        Log.e("oid", ">>>>>" + oid);
        RequestParams params = RequestParamsUtils.getCommentList(String.valueOf(oid), "3", "1", "10");
        HttpRequestUtils.create(this).send(URL.COMMENT_LIST_URL, params, new HttpRequestCallBack<ResultInfo>() {
            @Override
            public void onSuccess(ResultInfo resultInfo, int requestCode) {
                Comments comments = JsonParse.parseToObject(resultInfo, Comments.class);
                if (comments != null) {
                    mDatas.addAll(comments.getDataList());
                    mCommentAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    private void initView() {
        title.setText("详情");
        List<String> imageUrls = data.getPictures();
        cnName.setText(data.getCnName());
        notice.setText("        " + data.getContext());
        likeCount.setText(data.getPraiseCount() + "赞");
        mAdapter = new ProduceGvAdapter(imageUrls, this);
        mGridView.setAdapter(mAdapter);
        mDatas = new ArrayList<>();
        mCommentAdapter = new CommentListAdapter(mDatas, this);
        mListView.setAdapter(mCommentAdapter);
    }

    @Override
    public void onClick(View v) {

        Intent intent;
        switch (v.getId()) {
            case R.id.like_tv://点赞
                RequestParams params = RequestParamsUtils.getLikeParams(oid, 1, "");
                HttpRequestUtils.create(this).send(URL.DIAN_ZAN_URL, params, new HttpRequestCallBack<ResultInfo>() {
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
                                ToastUtils.show(ShopDetailActivity.this, "成功点赞");
                                //刷新数据
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
                break;
            case R.id.commot_tv:
                intent = new Intent(this, CommentActivity.class);
                intent.putExtra("oid", oid);
                startActivity(intent);

                break;
            case R.id.back_arrows:
                finish();
                break;
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}

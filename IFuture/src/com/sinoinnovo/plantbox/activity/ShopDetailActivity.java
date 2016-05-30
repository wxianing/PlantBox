package com.sinoinnovo.plantbox.activity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lidroid.xutils.http.RequestParams;
import com.sinoinnovo.plantbox.R;
import com.sinoinnovo.plantbox.activity.base.BaseActivity;
import com.sinoinnovo.plantbox.adapter.CommentListAdapter;
import com.sinoinnovo.plantbox.adapter.ProduceGvAdapter;
import com.sinoinnovo.plantbox.bean.comment.Comments;
import com.sinoinnovo.plantbox.bean.produce.DataListBean;
import com.sinoinnovo.plantbox.constant.URL;
import com.sinoinnovo.plantbox.http.HttpRequestCallBack;
import com.sinoinnovo.plantbox.http.HttpRequestUtils;
import com.sinoinnovo.plantbox.http.RequestParamsUtils;
import com.sinoinnovo.plantbox.model.ResultInfo;
import com.sinoinnovo.plantbox.utils.JsonParse;
import com.sinoinnovo.plantbox.utils.ShareUtils;
import com.sinoinnovo.plantbox.utils.ToastUtils;
import com.sinoinnovo.plantbox.view.MyListView;
import com.sinoinnovo.plantbox.widget.HListView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 成果详情
 */
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
    @Bind(R.id.tv_content)
    protected EditText editText;
    @Bind(R.id.btn_send)
    protected Button sendBtn;
    @Bind(R.id.controll)
    protected LinearLayout controll;
    @Bind(R.id.time_tv)
    protected TextView time_tv;
    @Bind(R.id.address_tv)
    protected TextView address;
    @Bind(R.id.transpond_tv)
    protected TextView transpond;

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
        sendBtn.setOnClickListener(this);
        transpond.setOnClickListener(this);
    }

    private void initData() {
        RequestParams params = RequestParamsUtils.getCommentList(String.valueOf(data.getId()), "3", "1", "10");
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
        time_tv.setText(data.getTimeStr());
        address.setText(data.getCity());
        mAdapter = new ProduceGvAdapter(imageUrls, this);
        mGridView.setAdapter(mAdapter);
        mDatas = new ArrayList<>();
        mCommentAdapter = new CommentListAdapter(mDatas, this);
        mListView.setAdapter(mCommentAdapter);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.transpond_tv:
                ShareUtils.showShare(this);
                break;
            case R.id.btn_send:
                editText.setFocusable(true);
                InputMethodManager imm = (InputMethodManager) ShopDetailActivity.this.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);

                String comment = editText.getText().toString().trim();
                RequestParams params1 = RequestParamsUtils.getCommentParams(data.getId(), 3, comment);

                HttpRequestUtils.create(this).send(URL.COMMENT_URL, params1, new HttpRequestCallBack<ResultInfo>() {
                    @Override
                    public void onSuccess(ResultInfo resultInfo, int requestCode) {
                    }

                    @Override
                    public void onReponse(String result) {
                        super.onReponse(result);
                        Log.e("评论：", result);
                        try {
                            JSONObject obj = new JSONObject(result);
                            int enumcode = obj.getInt("enumcode");
                            if (enumcode == 0) {
                                ToastUtils.show(ShopDetailActivity.this, "评论成功");
                                controll.setVisibility(View.GONE);
                                mDatas.clear();
                                initData();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
                break;
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
                                likeCount.setText((data.getPraiseCount() + 1) + "赞");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
                break;
            case R.id.commot_tv:
                controll.setVisibility(View.VISIBLE);
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

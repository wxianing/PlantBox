package com.sinoinnovo.plantbox.activity;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.lidroid.xutils.http.RequestParams;
import com.sinoinnovo.plantbox.R;
import com.sinoinnovo.plantbox.activity.base.BaseActivity;
import com.sinoinnovo.plantbox.adapter.UserTutoDetAdapter;
import com.sinoinnovo.plantbox.bean.tutorial.ReferUserTutorial;
import com.sinoinnovo.plantbox.bean.tutorial.Tusercourse;
import com.sinoinnovo.plantbox.constant.URL;
import com.sinoinnovo.plantbox.http.HttpRequestCallBack;
import com.sinoinnovo.plantbox.http.HttpRequestUtils;
import com.sinoinnovo.plantbox.http.RequestParamsUtils;
import com.sinoinnovo.plantbox.model.ResultInfo;
import com.sinoinnovo.plantbox.utils.JsonParse;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class UserTutorialDetailsActivity extends BaseActivity implements View.OnClickListener {
    @Bind(R.id.back_arrows)
    protected ImageView backImg;
    @Bind(R.id.title_tv)
    protected TextView title;
    @Bind(R.id.listview)
    protected ListView mListView;
    @Bind(R.id.title_name)
    protected TextView titleName;
    private String headTitle;
    private int oid;
    private List<Tusercourse.RecordsBean> mDatas;
    private UserTutoDetAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_tutorial_details);
        ButterKnife.bind(this);
        oid = getIntent().getIntExtra("oid", 0);
        initView();
        initEvent();
        initData(oid);
    }

    private void initData(int oid) {
        RequestParams params = RequestParamsUtils.createRequestParams();
        params.addBodyParameter("Id", "" + oid);
        HttpRequestUtils.create(this).send(URL.USER_COUSER_DETAILS_URL, params, new HttpRequestCallBack<ResultInfo>() {
            @Override
            public void onSuccess(ResultInfo resultInfo, int requestCode) {
                Tusercourse course = JsonParse.parseToObject(resultInfo, Tusercourse.class);
                if (course != null) {
                    mDatas.addAll(course.getRecords());
                    mAdapter.notifyDataSetChanged();
                    titleName.setText(course.getHeadTitle());
                }
            }
        });
    }

    private void initEvent() {
        backImg.setOnClickListener(this);
    }

    private void initView() {
        title.setText("用户教程详情");
        titleName.setText(headTitle);
        mDatas = new ArrayList<>();
        mAdapter = new UserTutoDetAdapter(mDatas, this);
        mListView.setAdapter(mAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_arrows:
                finish();
                break;
        }
    }
}

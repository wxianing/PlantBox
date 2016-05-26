package com.sinoinnovo.plantbox.activity;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.lidroid.xutils.http.RequestParams;
import com.sinoinnovo.plantbox.R;
import com.sinoinnovo.plantbox.activity.base.BaseActivity;
import com.sinoinnovo.plantbox.adapter.ReferToUserTutorialAdapter;
import com.sinoinnovo.plantbox.bean.tutorial.ReferUserTutorial;
import com.sinoinnovo.plantbox.constant.URL;
import com.sinoinnovo.plantbox.http.HttpRequestCallBack;
import com.sinoinnovo.plantbox.http.HttpRequestUtils;
import com.sinoinnovo.plantbox.http.RequestParamsUtils;
import com.sinoinnovo.plantbox.model.ResultInfo;
import com.sinoinnovo.plantbox.utils.JsonParse;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PublishTutorialListsActivity extends BaseActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    @Bind(R.id.title_tv)
    protected TextView title;
    @Bind(R.id.back_arrows)
    protected ImageView backImg;
    @Bind(R.id.right_tv)
    protected TextView titleRight;

    private List<ReferUserTutorial.DataListBean> mDatas;
    @Bind(R.id.listView)
    protected ListView mListView;
    private ReferToUserTutorialAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish_tutorial_lists);
        ButterKnife.bind(this);
        initView();
        initEvent();
        initData();
    }

    private void initData() {
        RequestParams params = RequestParamsUtils.createRequestParams();
        params.addBodyParameter("sType", "2");
        params.addBodyParameter("PageIndex", "1");
        params.addBodyParameter("PageSize", "10");
        HttpRequestUtils.create(this).send(URL.MY_PUBLISH_TUTORIAL_URL, params, new HttpRequestCallBack<ResultInfo>() {
            @Override
            public void onSuccess(ResultInfo resultInfo, int requestCode) {
                ReferUserTutorial tutorial = JsonParse.parseToObject(resultInfo, ReferUserTutorial.class);
                if (tutorial != null) {
                    mDatas.addAll(tutorial.getDataList());
                    mAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    private void initEvent() {
        backImg.setOnClickListener(this);
        titleRight.setOnClickListener(this);
    }

    private void initView() {
        title.setText("我的教程");
        titleRight.setText("添加");
        mDatas = new ArrayList<>();
        mAdapter = new ReferToUserTutorialAdapter(mDatas, this);
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.right_tv:
                startActivity(new Intent(this, PublishTutorialActivity.class));
                break;
            case R.id.back_arrows:
                finish();
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ReferUserTutorial.DataListBean bean = mDatas.get(position);

        Intent intent = new Intent(this, PublishTutorialActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("bean", (Serializable) bean);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}

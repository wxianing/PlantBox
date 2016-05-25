package com.sinoinnovo.plantbox.activity;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

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

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ReferToUserTutorialActivity extends BaseActivity implements View.OnClickListener {
    @Bind(R.id.back_arrows)
    protected ImageView backImg;
    @Bind(R.id.search_edittext)
    protected EditText editText;
    @Bind(R.id.search_img)
    protected ImageView searchImg;
    private List<ReferUserTutorial.DataListBean> mDatas;
    @Bind(R.id.listview)
    protected ListView mListView;

    private ReferToUserTutorialAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refer_to_user_tutorial);
        ButterKnife.bind(this);
        initView();
        initData("");
        initEvent();
    }

    private void initData(String keyWord) {
        RequestParams params = RequestParamsUtils.createRequestParams();
        params.addBodyParameter("Keyword", keyWord);
        params.addBodyParameter("sType", "2");
        params.addBodyParameter("PageIndex", "1");
        params.addBodyParameter("PageSize", "10");
        HttpRequestUtils.create(this).send(URL.REFER_USER_TUTORIAL_URL, params, new HttpRequestCallBack<ResultInfo>() {
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

    private void initView() {
        mDatas = new ArrayList<>();
        mAdapter = new ReferToUserTutorialAdapter(mDatas, this);
//        mListView.setAdapter(mAdapter);
    }

    private void initEvent() {
        backImg.setOnClickListener(this);
        searchImg.setOnClickListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.search_img:
                String keyWord = editText.getText().toString().trim();
                initData(keyWord);
                break;
            case R.id.back_arrows:
                finish();
                break;
        }
    }
}

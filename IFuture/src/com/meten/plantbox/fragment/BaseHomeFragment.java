package com.meten.plantbox.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.lidroid.xutils.http.RequestParams;
import com.meten.plantbox.R;
import com.meten.plantbox.adapter.ProduceAdapter;
import com.meten.plantbox.bean.produce.DataListBean;
import com.meten.plantbox.bean.produce.Produce;
import com.meten.plantbox.constant.URL;
import com.meten.plantbox.http.HttpRequestCallBack;
import com.meten.plantbox.http.HttpRequestUtils;
import com.meten.plantbox.http.RequestParamsUtils;
import com.meten.plantbox.model.ResultInfo;
import com.meten.plantbox.utils.JsonParse;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * 基地主页
 */
public class BaseHomeFragment extends Fragment implements View.OnClickListener {

    @Bind(R.id.show_listview)
    protected ListView mListView;
    private ProduceAdapter mAdapter;
    private List<DataListBean> mDatas;
    private CallBack callBack;

    @Bind(R.id.more_information)
    protected TextView moreInformation;//更多资料
    @Bind(R.id.nickname_linear)
    protected LinearLayout nickNameLinear;//昵称
    @Bind(R.id.sex_linear)
    protected LinearLayout sexLinear;//性别
    @Bind(R.id.introduction_linear)
    protected LinearLayout introduction;//简介

    public BaseHomeFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_base_home, container, false);
        ButterKnife.bind(this, view);

        initView();
        initData();
        initEvent();
        return view;
    }

    private void initEvent() {
        moreInformation.setOnClickListener(this);
    }

    private void initView() {
        mDatas = new ArrayList<>();
        mAdapter = new ProduceAdapter(mDatas, getActivity());
        mListView.setAdapter(mAdapter);
    }

    private void initData() {
        callBack = new CallBack();
        RequestParams params = RequestParamsUtils.getProductList("3", "1", "3");
        HttpRequestUtils.create(getActivity()).send(URL.HOME_PRODUCTLIST_URL, params, callBack);
    }


    public void setViewVisable() {
        nickNameLinear.setVisibility(View.VISIBLE);
        sexLinear.setVisibility(View.VISIBLE);
        introduction.setVisibility(View.VISIBLE);
    }

    public void setViewInVisiable() {
        nickNameLinear.setVisibility(View.GONE);
        sexLinear.setVisibility(View.GONE);
        introduction.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.more_information:
                String flag = moreInformation.getText().toString().trim();
                if (flag.equals("更多资料>>")) {
                    setViewVisable();
                    moreInformation.setText("收起");
                } else {
                    moreInformation.setText("更多资料>>");
                    setViewInVisiable();
                }
                break;
            default:
                break;
        }
    }

    class CallBack extends HttpRequestCallBack<ResultInfo> {

        @Override
        public void onSuccess(ResultInfo info, int requestCode) {

            Produce produce = JsonParse.parseToObject(info, Produce.class);
            if (produce != null) {
                mDatas.addAll(produce.getDataList());
                mAdapter.notifyDataSetChanged();
            }
        }

        @Override
        public void onFailure(Context context, ResultInfo info, int requestCode) {
            super.onFailure(context, info, requestCode);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}

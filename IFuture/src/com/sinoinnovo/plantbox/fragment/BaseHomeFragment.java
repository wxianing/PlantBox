package com.sinoinnovo.plantbox.fragment;


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

import com.sinoinnovo.plantbox.R;
import com.sinoinnovo.plantbox.adapter.ProduceAdapter;
import com.sinoinnovo.plantbox.bean.produce.DataListBean;
import com.sinoinnovo.plantbox.bean.produce.Produce;
import com.sinoinnovo.plantbox.constant.URL;
import com.sinoinnovo.plantbox.http.HttpRequestCallBack;
import com.sinoinnovo.plantbox.http.HttpRequestUtils;
import com.sinoinnovo.plantbox.http.LikeCallBack;
import com.sinoinnovo.plantbox.http.RequestParamsUtils;
import com.sinoinnovo.plantbox.model.ResultInfo;
import com.sinoinnovo.plantbox.utils.JsonParse;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * 基地主页
 */
public class BaseHomeFragment extends Fragment implements View.OnClickListener, LikeCallBack {

    @Bind(R.id.show_listview)
    protected ListView mListView;
    private ProduceAdapter mAdapter;
    private List<DataListBean> mDatas;
    private CallBack callBack;


    protected TextView moreInformation;//更多资料

    protected LinearLayout nickNameLinear;//昵称
    protected LinearLayout sexLinear;//性别
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
        View headerView = LayoutInflater.from(getActivity()).inflate(R.layout.myarea_home_list_header, null);
        moreInformation = (TextView) headerView.findViewById(R.id.more_information);
        nickNameLinear = (LinearLayout) headerView.findViewById(R.id.nickname_linear);
        sexLinear = (LinearLayout) headerView.findViewById(R.id.sex_linear);
        introduction = (LinearLayout) headerView.findViewById(R.id.introduction_linear);

        mDatas = new ArrayList<>();
        mAdapter = new ProduceAdapter(mDatas, getActivity(), this);
        mListView.setAdapter(mAdapter);
        mListView.addHeaderView(headerView);
    }

    private void initData() {
        callBack = new CallBack();
        RequestParams params = RequestParamsUtils.getProductList("", "1", "1", "10");
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

    @Override
    public void likeClick(int enumcode) {
        initData();
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

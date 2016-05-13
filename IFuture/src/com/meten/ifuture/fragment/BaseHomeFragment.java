package com.meten.ifuture.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.lidroid.xutils.http.RequestParams;
import com.meten.ifuture.R;
import com.meten.ifuture.adapter.ProduceAdapter;
import com.meten.ifuture.bean.produce.DataListBean;
import com.meten.ifuture.bean.produce.Produce;
import com.meten.ifuture.constant.URL;
import com.meten.ifuture.http.HttpRequestCallBack;
import com.meten.ifuture.http.HttpRequestUtils;
import com.meten.ifuture.http.RequestParamsUtils;
import com.meten.ifuture.model.ResultInfo;
import com.meten.ifuture.utils.JsonParse;
import com.meten.ifuture.utils.SharedPreferencesUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * 基地主页
 */
public class BaseHomeFragment extends Fragment {

    @Bind(R.id.show_listview)
    protected ListView mListView;
    private ProduceAdapter mAdapter;
    private List<DataListBean> mDatas;
    private CallBack callBack;

    public BaseHomeFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_base_home, container, false);
        ButterKnife.bind(this, view);

        initView();
        initData();
        return view;
    }

    private void initView() {
        mDatas = new ArrayList<>();
        mAdapter = new ProduceAdapter(mDatas, getActivity());
        mListView.setAdapter(mAdapter);
    }

    private void initData() {
        callBack = new CallBack();
        String code = SharedPreferencesUtils.getStringData(getActivity(), "code", null);
        RequestParams params = RequestParamsUtils.getProductList("3", "1", "3", code);
        HttpRequestUtils.create(getActivity()).send(URL.HOME_PRODUCTLIST_URL, params, callBack);
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

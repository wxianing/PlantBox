package com.meten.ifuture.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lidroid.xutils.http.RequestParams;
import com.meten.ifuture.R;
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

import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CommFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CommFragment extends Fragment {

    private static final String ARG_PARAM1 = "sType";
    private static final String ARG_PARAM2 = "sType";

    private String mParam1;
    private String mParam2;

    private CallBack callback;
    List<DataListBean> dataLists;

    public CommFragment() {
    }

    public static CommFragment newInstance(String param1, String param2) {
        CommFragment fragment = new CommFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
            Log.e("mParam", mParam1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_comm, container, false);
        ButterKnife.bind(this, view);
        initView();
        initData();
        return view;
    }

    private void initData() {
        String code = SharedPreferencesUtils.getStringData(getActivity(), "code", null);
        RequestParams params = RequestParamsUtils.getProductList(mParam1, "1", "3", code);
        HttpRequestUtils.create(getActivity()).send(URL.HOME_PRODUCTLIST_URL, params, callback);
    }

    private void initView() {
        callback = new CallBack();
        dataLists = new ArrayList<>();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    class CallBack extends HttpRequestCallBack<ResultInfo> {

        @Override
        public void onSuccess(ResultInfo info, int requestCode) {

            Produce produce = JsonParse.parseToObject(info, Produce.class);
            if (produce != null) {
                dataLists.addAll(produce.getDataList());

            }
        }

        @Override
        public void onFailure(Context context, ResultInfo info, int requestCode) {
            super.onFailure(context, info, requestCode);

        }
    }
}

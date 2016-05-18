package com.meten.plantbox.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lidroid.xutils.http.RequestParams;
import com.meten.plantbox.R;
import com.meten.plantbox.adapter.ProduceAdapter;
import com.meten.plantbox.bean.produce.DataListBean;
import com.meten.plantbox.bean.produce.Produce;
import com.meten.plantbox.constant.URL;
import com.meten.plantbox.http.HttpRequestCallBack;
import com.meten.plantbox.http.HttpRequestListener;
import com.meten.plantbox.http.HttpRequestUtils;
import com.meten.plantbox.http.RequestParamsUtils;
import com.meten.plantbox.model.ResultInfo;
import com.meten.plantbox.utils.JsonParse;
import com.meten.plantbox.view.MyListView;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
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

    private ProduceAdapter mAdapter;
    @Bind(R.id.listview)
    protected MyListView mListView;

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

        RequestParams params = RequestParamsUtils.getProductList(mParam1, "1", "3");
        HttpRequestUtils.create(getActivity()).send(URL.HOME_PRODUCTLIST_URL, params, callback);
    }

    private void initView() {
        callback = new CallBack();
        dataLists = new ArrayList<>();
        mAdapter = new ProduceAdapter(dataLists, getActivity());
        mListView.setAdapter(mAdapter);

    }

    class CallBack extends HttpRequestCallBack<ResultInfo> {

        @Override
        public void onSuccess(ResultInfo info, int requestCode) {

            Produce produce = JsonParse.parseToObject(info, Produce.class);
            if (produce != null) {
                dataLists.addAll(produce.getDataList());
                mAdapter.notifyDataSetChanged();
            }
        }

        @Override
        public void onFailure(Context context, ResultInfo info, int requestCode) {
            super.onFailure(context, info, requestCode);

        }
    }
}
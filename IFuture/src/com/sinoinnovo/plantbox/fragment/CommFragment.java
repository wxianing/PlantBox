package com.sinoinnovo.plantbox.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.lidroid.xutils.http.RequestParams;

import com.sinoinnovo.plantbox.R;
import com.sinoinnovo.plantbox.activity.base.BaseFragment;
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
import com.sinoinnovo.plantbox.utils.ToastUtils;
import com.sinoinnovo.plantbox.view.MyListView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CommFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CommFragment extends BaseFragment implements LikeCallBack {

    private static final String ARG_PARAM1 = "sType";
    private static final String ARG_PARAM2 = "sType";

    private String mParam1;
    private String mParam2;

    private CallBack callback;
    List<DataListBean> dataLists;

    private ProduceAdapter mAdapter;
    @Bind(R.id.listview)
    protected MyListView mListView;
    @Bind(R.id.add_nore)
    protected TextView addMore;
    int pageIndex = 1;

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
        if (mParam1.equals("2")) {
            initData(pageIndex);
        }
        initEvent();
        return view;
    }

    private void initEvent() {
        addMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pageIndex++;
                initData(pageIndex);
            }
        });
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ToastUtils.show(getActivity(), "d" + position);
            }
        });
    }


    private void initData(int pageIndex) {
        RequestParams params = RequestParamsUtils.getProductList("", mParam1, "" + pageIndex, "8");
        HttpRequestUtils.create(getActivity()).send(URL.HOME_PRODUCTLIST_URL, params, callback);
    }

    private void initView() {
        callback = new CallBack();
        dataLists = new ArrayList<>();
        mAdapter = new ProduceAdapter(dataLists, getActivity(), this);
        mListView.setAdapter(mAdapter);
    }

    @Override
    public void likeClick(int position) {
        int oid = dataLists.get(position).getId();
        RequestParams params = RequestParamsUtils.getLikeParams(oid, 1, "");
        HttpRequestUtils.create(getActivity()).send(URL.DIAN_ZAN_URL, params, new HttpRequestCallBack<ResultInfo>() {
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
                        initData(pageIndex);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    protected void lazyLoad() {
        initData(pageIndex);
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

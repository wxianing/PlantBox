package com.sinoinnovo.plantbox.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lidroid.xutils.http.RequestParams;
import com.meten.imanager.pulltorefresh.library.PullToRefreshBase;
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
import com.sinoinnovo.plantbox.utils.ToastUtils;
import com.sinoinnovo.plantbox.view.MyListView;

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
    protected MyListView mListView;
    private ProduceAdapter mAdapter;
    private List<DataListBean> mDatas;
    private CallBack callBack;


    protected TextView moreInformation;//更多资料

    protected LinearLayout nickNameLinear;//昵称
    protected LinearLayout sexLinear;//性别
    protected LinearLayout introduction;//简介

    protected TextView locationTtv;//所在地
    protected TextView nickname;//昵称
    protected TextView sexTv;//性别
    protected TextView introductionTv;//简介

    private String keyWord = "王显宁";

    private static final String ARG_PARAM1 = "cnName";
    private String mParam;
    private EditText editText;
    private LinearLayout editTextLinear;
    private RelativeLayout rootView;
    private Button sendBtn;

    public BaseHomeFragment() {
    }

    public static BaseHomeFragment newInstance(String param) {
        BaseHomeFragment fragment = new BaseHomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param);

        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam = getArguments().getString(ARG_PARAM1);
            Log.e("mParam", mParam);
            if (mParam != null && !"".equals(mParam)) {
                keyWord = mParam;
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_base_home, container, false);
        ButterKnife.bind(this, view);

        initView(view);
        initData();
        initEvent();
        return view;
    }

    private int position;

    private void initEvent() {
        moreInformation.setOnClickListener(this);
        locationTtv.setOnClickListener(this);
        nickname.setOnClickListener(this);
        sexTv.setOnClickListener(this);
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextLinear.setVisibility(View.GONE);
                switch (position) {
                    case R.id.location_tv:
                        sendMsg(locationTtv);
                        break;
                    case R.id.nickname:
                        sendMsg(nickname);
                        break;
                    case R.id.sex_tv:
                        sendMsg(sexTv);
                        break;
                    case R.id.introduction_tv:
                        sendMsg(introductionTv);
                        break;
                }

            }
        });
    }

    private void initView(View view) {
        rootView = (RelativeLayout) view.findViewById(R.id.root_layout);

        View headerView = LayoutInflater.from(getActivity()).inflate(R.layout.myarea_home_list_header, null);
        moreInformation = (TextView) headerView.findViewById(R.id.more_information);
        nickNameLinear = (LinearLayout) headerView.findViewById(R.id.nickname_linear);
        sexLinear = (LinearLayout) headerView.findViewById(R.id.sex_linear);
        introduction = (LinearLayout) headerView.findViewById(R.id.introduction_linear);
        locationTtv = (TextView) headerView.findViewById(R.id.location_tv);

        nickname = (TextView) headerView.findViewById(R.id.nickname);
        editTextLinear = (LinearLayout) headerView.findViewById(R.id.editText_linear);
        editText = (EditText) headerView.findViewById(R.id.editText);
        sexTv = (TextView) headerView.findViewById(R.id.sex_tv);
        introductionTv = (TextView) headerView.findViewById(R.id.introduction_tv);
        sendBtn = (Button) headerView.findViewById(R.id.send_btn);
        mDatas = new ArrayList<>();
        mListView.addHeaderView(headerView);
        mAdapter = new ProduceAdapter(mDatas, getActivity(), this);
        mListView.setAdapter(mAdapter);
    }

    private void initData() {
        callBack = new CallBack();
        RequestParams params = RequestParamsUtils.getProductList(keyWord, "1", "1", "10");
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
        InputMethodManager imm = (InputMethodManager) editText.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        switch (v.getId()) {
            case R.id.location_tv://所在地
                editTextLinear.setVisibility(View.VISIBLE);
                editText.requestFocus();
                imm.toggleSoftInput(0, InputMethodManager.SHOW_FORCED);

                position = R.id.location_tv;
                break;
            case R.id.nickname:
                editTextLinear.setVisibility(View.VISIBLE);
                editText.requestFocus();
                imm.toggleSoftInput(0, InputMethodManager.SHOW_FORCED);

                position = R.id.nickname;
                break;
            case R.id.sex_tv:
                editTextLinear.setVisibility(View.VISIBLE);
                editText.requestFocus();
                imm.toggleSoftInput(0, InputMethodManager.SHOW_FORCED);

                position = R.id.sex_tv;
                break;
            case R.id.introduction_tv:
                editTextLinear.setVisibility(View.VISIBLE);
                editText.requestFocus();
                imm.toggleSoftInput(0, InputMethodManager.SHOW_FORCED);

                position = R.id.introduction_tv;
                break;
            case R.id.more_information:
                String flag = moreInformation.getText().toString().trim();
                if (flag.equals("展开")) {
                    setViewVisable();
                    moreInformation.setText("收起");
                } else {
                    moreInformation.setText("展开");
                    setViewInVisiable();
                }
                break;
            default:
                break;
        }
    }

    private void sendMsg(TextView tv) {
        String content = editText.getText().toString().trim();
        if (content != null && !"".equals(content)) {
            tv.setText(content);
            content = "";
            editText.setText("");
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
            } else {
                ToastUtils.show(getActivity(), "还没有发表成果！");
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

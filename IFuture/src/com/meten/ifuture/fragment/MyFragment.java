package com.meten.ifuture.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.meten.ifuture.PersonCenterAdapter;
import com.meten.ifuture.R;
import com.meten.ifuture.activity.MyBaseAreaActivity;
import com.meten.ifuture.bean.PersonCenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */

public class MyFragment extends Fragment implements AdapterView.OnItemClickListener {
    @Bind(R.id.gridView)
    protected GridView mGridView;
    private List<PersonCenter> mDatas;
    private PersonCenterAdapter mAdapter;

    public MyFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my, container, false);
        ButterKnife.bind(this, view);
        initData();
        initView(view);
        return view;
    }

    private void initView(View view) {
        mAdapter = new PersonCenterAdapter(mDatas, getActivity());
        mGridView.setAdapter(mAdapter);
        mGridView.setOnItemClickListener(this);
    }

    private void initData() {
        mDatas = new ArrayList<>();

        mDatas.add(new PersonCenter(R.drawable.person_mybase_icon, "我的基地"));
        mDatas.add(new PersonCenter(R.drawable.person_activity_icon, "我的活动"));
        mDatas.add(new PersonCenter(R.drawable.person_collect_icon, "我的收藏"));
        mDatas.add(new PersonCenter(R.drawable.person_order_icon, "我的订单"));
        mDatas.add(new PersonCenter(R.drawable.person_activity_area_icon, "活动专区"));
        mDatas.add(new PersonCenter(R.drawable.person_myshare_icon, "我的分享"));
        mDatas.add(new PersonCenter(R.drawable.person_connect_server, "联系客服"));
        mDatas.add(new PersonCenter(R.drawable.person_mywallet_icon, "我的钱包"));
        mDatas.add(new PersonCenter(R.drawable.person_setting_icon, "设置"));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:
                Intent intent = new Intent(getActivity(), MyBaseAreaActivity.class);
                startActivity(intent);
                break;
        }
    }
}

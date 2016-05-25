package com.sinoinnovo.plantbox.fragment;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;


import com.sinoinnovo.plantbox.R;
import com.sinoinnovo.plantbox.activity.ActiveActivity;
import com.sinoinnovo.plantbox.activity.MyBaseAreaActivity;
import com.sinoinnovo.plantbox.activity.MyCollectActivity;
import com.sinoinnovo.plantbox.activity.MyOrderActivity;
import com.sinoinnovo.plantbox.activity.MyShareActivity;
import com.sinoinnovo.plantbox.activity.PersonCenterAdapter;
import com.sinoinnovo.plantbox.activity.SettingActivity;
import com.sinoinnovo.plantbox.bean.PersonCenter;
import com.sinoinnovo.plantbox.utils.SharedPreferencesUtils;
import com.sinoinnovo.plantbox.view.DActionSheetDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */

public class MyFragment extends Fragment implements AdapterView.OnItemClickListener, View.OnClickListener {
    @Bind(R.id.gridView)
    protected GridView mGridView;
    private List<PersonCenter> mDatas;
    private PersonCenterAdapter mAdapter;
    @Bind(R.id.user_name)
    protected TextView userName;
    private String userNames;
    @Bind(R.id.header_img)
    protected ImageView headerImg;


    public MyFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my, container, false);
        ButterKnife.bind(this, view);
        initData();
        initView(view);
        initEvent();
        return view;
    }

    private void initEvent() {
        headerImg.setOnClickListener(this);
    }

    private void initView(View view) {

        mAdapter = new PersonCenterAdapter(mDatas, getActivity());
        mGridView.setAdapter(mAdapter);
        mGridView.setOnItemClickListener(this);
        userNames = SharedPreferencesUtils.getStringData(getActivity(), "userName", null);
        userName.setText(userNames);
    }

    private void initData() {
        mDatas = new ArrayList<>();

        mDatas.add(new PersonCenter(R.drawable.person_mybase_icon, "我的基地"));
//        mDatas.add(new PersonCenter(R.drawable.person_activity_icon, "我的活动"));
        mDatas.add(new PersonCenter(R.drawable.person_collect_icon, "我的收藏"));
        mDatas.add(new PersonCenter(R.drawable.person_order_icon, "我的订单"));
        mDatas.add(new PersonCenter(R.drawable.person_activity_area_icon, "活动专区"));
        mDatas.add(new PersonCenter(R.drawable.person_myshare_icon, "我的分享"));
        mDatas.add(new PersonCenter(R.drawable.person_connect_server, "联系客服"));
//        mDatas.add(new PersonCenter(R.drawable.person_mywallet_icon, "我的钱包"));
        mDatas.add(new PersonCenter(R.drawable.person_setting_icon, "设置"));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent;
        switch (position) {
            case 0:
                intent = new Intent(getActivity(), MyBaseAreaActivity.class);
                startActivity(intent);
                break;
            case 1:
                intent = new Intent(getActivity(), MyCollectActivity.class);
                startActivity(intent);
                break;
            case 2:
                intent = new Intent(getActivity(), MyOrderActivity.class);
                startActivity(intent);
                break;
            case 3:
                intent = new Intent(getActivity(), ActiveActivity.class);
                startActivity(intent);
                break;
            case 4:
                startActivity(new Intent(getActivity(), MyShareActivity.class));
                break;
            case 5:
                selectIcon();
                break;
            case 6:
                intent = new Intent(getActivity(), SettingActivity.class);
                startActivity(intent);
                break;
        }
    }

    private void selectIcon() {
        new DActionSheetDialog(getActivity()).builder()
                .setTitle("联系客服")
                .setCancelable(false)
                .setCanceledOnTouchOutside(false)
                .addSheetItem("400-000-0000",
                        DActionSheetDialog.SheetItemColor.Blue,
                        new DActionSheetDialog.OnSheetItemClickListener() {
                            @Override
                            public void onClick(int which) {
                                setCellPhone("400-000-0000");
                            }
                        }).show();

    }

    public void setCellPhone(String phone) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + phone));
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.header_img:
                photoSelectIcon();
                break;
        }
    }

    private void photoSelectIcon() {


    }

}

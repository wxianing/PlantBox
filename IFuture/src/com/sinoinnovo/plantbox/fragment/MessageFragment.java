package com.sinoinnovo.plantbox.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;


import com.sinoinnovo.plantbox.R;
import com.sinoinnovo.plantbox.activity.DimensionCodeActivity;
import com.sinoinnovo.plantbox.adapter.ExpandableAdapter;
import com.sinoinnovo.plantbox.bean.message.Child;
import com.sinoinnovo.plantbox.bean.message.Group;
import com.sinoinnovo.plantbox.view.CustomDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */

public class MessageFragment extends Fragment implements View.OnClickListener, ExpandableListView.OnChildClickListener {
    // 声明PopupWindow对象的引用
    private PopupWindow popupWindow;

    @Bind(R.id.title_tv)
    protected TextView title;
    @Bind(R.id.back_arrows)
    protected ImageView backImg;
    @Bind(R.id.title_right_img)
    protected ImageView addImg;

    @Bind(R.id.expListView)
    protected ExpandableListView expListView;

    private List<Group> list = new ArrayList<Group>();
    private CustomDialog customDialog;


    public MessageFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_message, container, false);
        ButterKnife.bind(this, view);
        initData();
        initView();
        initEvent();
        return view;
    }

    private void initView() {
        title.setText("消息");
        backImg.setVisibility(View.GONE);
        addImg.setImageResource(R.drawable.add_message_icon);
        addImg.setVisibility(View.VISIBLE);
    }

    private void initData() {
        Group g = new Group();
        g.setTitle("我的好友");
        ArrayList<Child> childList = new ArrayList<Child>();
        childList.add(new Child(R.drawable.ic_launcher, "张三"));
        childList.add(new Child(R.drawable.ic_launcher, "李四"));
        childList.add(new Child(R.drawable.ic_launcher, "王五"));
        childList.add(new Child(R.drawable.ic_launcher, "赵六"));
        childList.add(new Child(R.drawable.ic_launcher, "钱七"));
        g.setChildList(childList);

        Group g1 = new Group();
        g1.setTitle("最近联系人");
        ArrayList<Child> childList1 = new ArrayList<Child>();
        childList1.add(new Child(R.drawable.ic_launcher, "张三"));
        childList1.add(new Child(R.drawable.ic_launcher, "王五"));
        childList1.add(new Child(R.drawable.ic_launcher, "李四"));

        g1.setChildList(childList1);

        list.add(g);
        list.add(g1);

        expListView.setOnChildClickListener(this);

        expListView.setAdapter(new ExpandableAdapter(list, getActivity()));
    }

    private void initEvent() {
        backImg.setOnClickListener(this);
        addImg.setOnClickListener(this);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_right_img:
                selectDialog();
                break;
            case R.id.scan_layout:
                startActivity(new Intent(getActivity(), DimensionCodeActivity.class));
                customDialog.dismiss();
                break;
            case R.id.search_layout:
                customDialog.dismiss();
                break;
        }
    }

    private void selectDialog() {
        View view = LayoutInflater.from(getActivity()).inflate(
                R.layout.customdialog, null);
        customDialog = new CustomDialog(getActivity(), view);
        view.findViewById(R.id.scan_layout).setOnClickListener(this);
        view.findViewById(R.id.search_layout).setOnClickListener(this);
        Window localWindow = customDialog.getWindow();
        WindowManager.LayoutParams localLayoutParams = new WindowManager.LayoutParams();
        localLayoutParams.gravity = 53;
        localLayoutParams.y = 80;
        localLayoutParams.x = 0;
        localWindow.setAttributes(localLayoutParams);
        customDialog.setCanceledOnTouchOutside(true);
        customDialog.show();
    }

    @Override
    public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
        Toast.makeText(getActivity(), "点击了: " + list.get(groupPosition).getChildList().get(childPosition).getName(), Toast.LENGTH_SHORT).show();


        return true;
    }
}

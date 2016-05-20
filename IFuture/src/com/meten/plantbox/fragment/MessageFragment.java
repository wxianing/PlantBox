package com.meten.plantbox.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.meten.plantbox.R;
import com.meten.plantbox.adapter.ExpandableAdapter;
import com.meten.plantbox.bean.message.Child;
import com.meten.plantbox.bean.message.Group;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */

public class MessageFragment extends Fragment implements View.OnClickListener, ExpandableListView.OnChildClickListener {

    @Bind(R.id.title_tv)
    protected TextView title;
    @Bind(R.id.back_arrows)
    protected ImageView backImg;
    @Bind(R.id.title_right_img)
    protected ImageView addImg;

    @Bind(R.id.expListView)
    protected ExpandableListView expListView;

    private List<Group> list = new ArrayList<Group>();


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
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_arrows:
                Toast.makeText(getActivity(), "dianji", Toast.LENGTH_SHORT).show();
                getChildFragmentManager().popBackStack();
                break;
        }
    }

    @Override
    public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
        Toast.makeText(getActivity(), "点击了: " + list.get(groupPosition).getChildList().get(childPosition).getName(), Toast.LENGTH_SHORT).show();


        return true;
    }
}

package com.sinoinnovo.plantbox.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.sinoinnovo.plantbox.R;
import com.sinoinnovo.plantbox.activity.AddFriendActivity;
import com.sinoinnovo.plantbox.activity.MyLikeActivity;
import com.sinoinnovo.plantbox.activity.PlantShopActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * 成果
 */
public class AchievementFragment extends Fragment implements View.OnClickListener {

    @Bind(R.id.my_like)
    protected TextView myLike;

    @Bind(R.id.my_comment)
    protected TextView myComment;
    @Bind(R.id.my_share)
    protected TextView myShare;
    @Bind(R.id.immediately_plant)
    protected TextView immediatelyPlant;

    public AchievementFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_achievement, container, false);
        ButterKnife.bind(this, view);
        initView();
        initEvent();
        return view;
    }


    private void initView() {
    }

    private void initEvent() {
        myLike.setOnClickListener(this);
        myComment.setOnClickListener(this);
        myShare.setOnClickListener(this);
        immediatelyPlant.setOnClickListener(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {

            case R.id.my_like:
                intent = new Intent(getActivity(), MyLikeActivity.class);
                intent.putExtra("Tag", 1);
                startActivity(intent);
                break;
            case R.id.my_comment:
                intent = new Intent(getActivity(), MyLikeActivity.class);
                intent.putExtra("Tag", 2);
                startActivity(intent);
                break;
            case R.id.my_share:
                intent = new Intent(getActivity(), MyLikeActivity.class);
                intent.putExtra("Tag", 3);
                startActivity(intent);
                break;
            case R.id.immediately_plant:
                intent = new Intent(getActivity(), PlantShopActivity.class);
                startActivity(intent);
                break;

        }
    }
}

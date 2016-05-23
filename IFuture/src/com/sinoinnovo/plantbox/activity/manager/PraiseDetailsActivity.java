package com.sinoinnovo.plantbox.activity.manager;

import android.content.Context;
import android.os.Bundle;
import android.widget.ListView;

import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.http.RequestParams;
import com.meten.imanager.pulltorefresh.library.PullToRefreshBase;
import com.sinoinnovo.plantbox.activity.base.BaseListActivity;
import com.sinoinnovo.plantbox.adapter.manager.PraiseDetailsAdapter;
import com.sinoinnovo.plantbox.constant.Constant;
import com.sinoinnovo.plantbox.constant.URL;
import com.sinoinnovo.plantbox.http.HttpRequestCallBack;
import com.sinoinnovo.plantbox.http.HttpRequestUtils;
import com.sinoinnovo.plantbox.http.RequestParamsUtils;
import com.sinoinnovo.plantbox.model.ResultInfo;
import com.sinoinnovo.plantbox.model.student.Complain;
import com.sinoinnovo.plantbox.utils.JsonParse;

import java.util.List;

/**
 * Created by Cmad on 2015/3/21.
 */
public class PraiseDetailsActivity extends BaseListActivity implements PullToRefreshBase.OnRefreshListener2<ListView> {
    private int teacherUserId;
    private String teacherName;
    private PraiseDetailsAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        teacherUserId = getIntent().getIntExtra(Constant.USER_ID,-1);
        teacherName = getIntent().getStringExtra(Constant.TEACHER_KEY);

        initView();
    }

    private void initView() {
        setTitle(teacherName);

        hiddenRightImageView();

        adapter = new PraiseDetailsAdapter(this);
        setAdapter(adapter);

//        getPullToRefreshListView().getRefreshableView().setDivider(new ColorDrawable(getResources().getColor(R.color.divider_bg_color)));
//        getPullToRefreshListView().getRefreshableView().setDividerHeight(getResources().getDimensionPixelSize(R.dimen.divider));
        setOnRefreshListener(this);
        setMode(PullToRefreshBase.Mode.BOTH);

        RequestParams params = RequestParamsUtils.getPraiseDetails(URL.GET_TEACHER_PRAISE_DETAILS, teacherUserId,false);
        HttpRequestUtils.create(this).send(URL.GET_TEACHER_PRAISE_DETAILS,params,Constant.REFRESH,callBack);

    }

    HttpRequestCallBack<ResultInfo> callBack = new HttpRequestCallBack<ResultInfo>() {

        @Override
        public void onSuccess(ResultInfo info, int requestCode) {
            List<Complain> data = JsonParse.parseDataOfPageToData(info,new TypeToken<List<Complain>>(){}.getType());
            if(requestCode == Constant.REFRESH){
                adapter.setListData(data);
            }else if(requestCode == Constant.LOADMORE){
                adapter.addData(data);
            }
            onRefreshComplete();
        }

        @Override
        public void onFailure(Context context, ResultInfo info, int requestCode) {
            super.onFailure(context, info, requestCode);
            onRefreshComplete();
        }
    };

    @Override
    public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
        RequestParams params = RequestParamsUtils.getPraiseDetails(URL.GET_TEACHER_PRAISE_DETAILS, teacherUserId,false);
        HttpRequestUtils.create(this).send(URL.GET_TEACHER_PRAISE_DETAILS,params,Constant.REFRESH,callBack);
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
        RequestParams params = RequestParamsUtils.getPraiseDetails(URL.GET_TEACHER_PRAISE_DETAILS, teacherUserId,true);
        HttpRequestUtils.create(this).send(URL.GET_TEACHER_PRAISE_DETAILS,params,Constant.LOADMORE,callBack);
    }
}

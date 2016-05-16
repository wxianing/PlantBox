package com.meten.plantbox.activity.student;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.google.gson.reflect.TypeToken;
import com.meten.plantbox.R;
import com.meten.plantbox.activity.base.BaseListActivity;
import com.meten.plantbox.adapter.student.ChooseComplainObjectAdapter;
import com.meten.plantbox.constant.Constant;
import com.meten.plantbox.constant.URL;
import com.meten.plantbox.http.HttpRequestCallBack;
import com.meten.plantbox.http.HttpRequestUtils;
import com.meten.plantbox.http.RequestParamsUtils;
import com.meten.plantbox.model.ResultInfo;
import com.meten.plantbox.model.student.MyTeacher;
import com.meten.plantbox.utils.JsonParse;

import java.util.List;

/**
 * Created by Cmad on 2015/3/6.
 */
public class ChooseComplainObjectActivity extends BaseListActivity implements AdapterView.OnItemClickListener {

    private ChooseComplainObjectAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle(getString(R.string.choose_complain_teacher));
        hiddenRightImageView();

        int userId = getIntent().getIntExtra(Constant.USER_ID,-1);

        adapter = new ChooseComplainObjectAdapter(this);
        adapter.setSelectUserId(userId);
        setAdapter(adapter);

        getListView().setSelector(R.color.light_white);

        setOnItemClickListener(this);

        HttpRequestUtils.create(this).send(URL.GET_MY_TEACHER, RequestParamsUtils.createRequestParams(), callback);
    }

    HttpRequestCallBack<ResultInfo> callback = new HttpRequestCallBack<ResultInfo>() {

        @Override
        public void onSuccess(ResultInfo info, int requestCode) {

            List<MyTeacher> data = JsonParse.parseToObject(info, new TypeToken<List<MyTeacher>>() {
            }.getType());
            adapter.setListData(data);
        }


    };

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        MyTeacher tea = adapter.getListData().get(position-1);
        adapter.setSelectUserId(tea.getUserId());
        Intent intent = new Intent();
        intent.putExtra(Constant.COMPLAIN_OBJECT,tea);
        setResult(100,intent);
        finish();
    }
}
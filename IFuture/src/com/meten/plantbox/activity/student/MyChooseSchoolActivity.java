package com.meten.plantbox.activity.student;

import java.util.List;

import android.os.Bundle;

import com.google.gson.reflect.TypeToken;
import com.meten.plantbox.R;
import com.meten.plantbox.activity.base.BaseListActivity;
import com.meten.plantbox.adapter.student.MyChooseSchoolAdapter;
import com.meten.plantbox.constant.URL;
import com.meten.plantbox.http.HttpRequestCallBack;
import com.meten.plantbox.http.HttpRequestUtils;
import com.meten.plantbox.http.RequestParamsUtils;
import com.meten.plantbox.model.ResultInfo;
import com.meten.plantbox.model.student.School;
import com.meten.plantbox.utils.JsonParse;

public class MyChooseSchoolActivity extends BaseListActivity {
	private MyChooseSchoolAdapter adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setTitle(getString(R.string.my_choose_school));
		hiddenRightImageView();
		
		adapter = new MyChooseSchoolAdapter(this);
		setAdapter(adapter);
		
		HttpRequestUtils.create(this).send(URL.GET_MY_SCHOOL, RequestParamsUtils.createRequestParams(), callback);
	}
	
	HttpRequestCallBack<ResultInfo> callback = new HttpRequestCallBack<ResultInfo>() {

		@Override
		public void onSuccess(ResultInfo info, int requestCode) {
			List<School> data = JsonParse.parseToObject(info, new TypeToken<List<School>>(){}.getType());
			adapter.setListData(data);
		}
	};
	
}

package com.sinoinnovo.plantbox.activity.student;

import android.os.Bundle;

import com.google.gson.reflect.TypeToken;
import com.sinoinnovo.plantbox.R;
import com.sinoinnovo.plantbox.activity.base.BaseListActivity;
import com.sinoinnovo.plantbox.adapter.student.MyChooseSchoolAdapter;
import com.sinoinnovo.plantbox.constant.URL;
import com.sinoinnovo.plantbox.http.HttpRequestCallBack;
import com.sinoinnovo.plantbox.http.HttpRequestUtils;
import com.sinoinnovo.plantbox.http.RequestParamsUtils;
import com.sinoinnovo.plantbox.model.ResultInfo;
import com.sinoinnovo.plantbox.model.student.School;
import com.sinoinnovo.plantbox.utils.JsonParse;

import java.util.List;

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

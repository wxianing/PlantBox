package com.meten.plantbox.activity.student;

import android.os.Bundle;
import android.widget.TextView;

import com.lidroid.xutils.http.RequestParams;
import com.meten.plantbox.R;
import com.meten.plantbox.activity.base.BaseListActivity;
import com.meten.plantbox.adapter.student.ComplainReplyAdapter;
import com.meten.plantbox.constant.Constant;
import com.meten.plantbox.constant.URL;
import com.meten.plantbox.http.HttpRequestCallBack;
import com.meten.plantbox.http.HttpRequestUtils;
import com.meten.plantbox.http.RequestParamsUtils;
import com.meten.plantbox.model.ResultInfo;
import com.meten.plantbox.model.User;
import com.meten.plantbox.model.student.Complain;
import com.meten.plantbox.utils.JsonParse;
import com.meten.plantbox.utils.SharedPreferencesUtils;

/**
 * Created by Cmad on 2015/3/6.
 */
public class ComplainDetailsActivity extends BaseListActivity {
    private TextView tvComplainObject;
    private TextView tvComplainDate;
    private TextView tvComplainContent;

    private ComplainReplyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addTopView(R.layout.complain_details_top);


        initView();
    }

    private void initView() {
        tvComplainObject = (TextView) findViewById(R.id.complain_object_tv);
        tvComplainDate = (TextView) findViewById(R.id.complain_date_tv);
        tvComplainContent = (TextView) findViewById(R.id.complain_content_tv);
        tvComplainContent.setSingleLine(false);

        hiddenRightImageView();
        setTitle(getString(R.string.complain_details));

        Complain complain = (Complain) getIntent().getSerializableExtra(Constant.COMPLAIN);
        int complainId = getIntent().getIntExtra(Constant.COMPLAIN_ID, -1);

        adapter = new ComplainReplyAdapter(this);
        setAdapter(adapter);

        if (complain != null) {
            setDataInView(complain);
        } else if (complainId > 0) {
            RequestParams params = RequestParamsUtils.getComplainDetails(complainId);
            HttpRequestUtils.create(this).send(URL.GET_COMPLAIN_DETAILS, params, callback);
        }

    }

    private void setDataInView(Complain complain) {
        User user = SharedPreferencesUtils.getInstance(this).getUser();
        TextView tv = (TextView) findViewById(R.id.complain_hint_tv);
        if (user.getUserType() == Constant.MANAGER) {
            tv.setText(getString(R.string.complain_student));
            tvComplainObject.setText(complain.getFromCnName());
        } else {
            tvComplainObject.setText(complain.getToCnName());
        }
        tvComplainDate.setText(complain.getCreateTime());
        tvComplainContent.setText(complain.getContent());

//        adapter.setListData(complain.getListReply());
    }

    HttpRequestCallBack<ResultInfo> callback = new HttpRequestCallBack<ResultInfo>() {


        @Override
        public void onSuccess(ResultInfo info, int requestCode) {
            Complain complain = JsonParse.parseToObject(info, Complain.class);
            setDataInView(complain);
        }
    };
}

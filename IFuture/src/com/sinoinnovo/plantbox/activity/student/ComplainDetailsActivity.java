package com.sinoinnovo.plantbox.activity.student;

import android.os.Bundle;
import android.widget.TextView;

import com.lidroid.xutils.http.RequestParams;
import com.sinoinnovo.plantbox.R;
import com.sinoinnovo.plantbox.activity.base.BaseListActivity;
import com.sinoinnovo.plantbox.adapter.student.ComplainReplyAdapter;
import com.sinoinnovo.plantbox.constant.Constant;
import com.sinoinnovo.plantbox.constant.URL;
import com.sinoinnovo.plantbox.http.HttpRequestCallBack;
import com.sinoinnovo.plantbox.http.HttpRequestUtils;
import com.sinoinnovo.plantbox.http.RequestParamsUtils;
import com.sinoinnovo.plantbox.model.ResultInfo;
import com.sinoinnovo.plantbox.model.User;
import com.sinoinnovo.plantbox.model.student.Complain;
import com.sinoinnovo.plantbox.utils.JsonParse;
import com.sinoinnovo.plantbox.utils.SharedPreferencesUtils;

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

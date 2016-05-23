package com.sinoinnovo.plantbox.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.lidroid.xutils.http.RequestParams;
import com.sinoinnovo.plantbox.R;
import com.sinoinnovo.plantbox.activity.base.BaseActivity;
import com.sinoinnovo.plantbox.adapter.ProduceAdapter;
import com.sinoinnovo.plantbox.adapter.SearchAutoAdapter;
import com.sinoinnovo.plantbox.bean.SearchAutoData;
import com.sinoinnovo.plantbox.bean.produce.DataListBean;
import com.sinoinnovo.plantbox.bean.produce.Produce;
import com.sinoinnovo.plantbox.constant.URL;
import com.sinoinnovo.plantbox.http.HttpRequestCallBack;
import com.sinoinnovo.plantbox.http.HttpRequestUtils;
import com.sinoinnovo.plantbox.http.LikeCallBack;
import com.sinoinnovo.plantbox.http.RequestParamsUtils;
import com.sinoinnovo.plantbox.model.ResultInfo;
import com.sinoinnovo.plantbox.utils.JsonParse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SearchActivity extends BaseActivity implements View.OnClickListener, LikeCallBack {

    public static final String SEARCH_HISTORY = "search_history";
    private ListView mAutoListView;
    private ImageView mSearchButtoon;
    private EditText mAutoEdit;
    private SearchAutoAdapter mSearchAutoAdapter;
    private String keyWord;
    @Bind(R.id.listview)
    protected ListView mListView;
    private List<DataListBean> dataLists;
    private ProduceAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        initView();
        initEvent();
    }

    private void initData(String keyWord) {

        RequestParams params = RequestParamsUtils.getProductList(keyWord, "" + 1, "" + 1, "" + 10);
        HttpRequestUtils.create(this).send(URL.HOME_PRODUCTLIST_URL, params, new HttpRequestCallBack<ResultInfo>() {
            @Override
            public void onSuccess(ResultInfo resultInfo, int requestCode) {
                Produce produce = JsonParse.parseToObject(resultInfo, Produce.class);
                if (produce != null) {
                    dataLists.clear();
                    dataLists.addAll(produce.getDataList());
                    mAdapter.notifyDataSetChanged();
                }
            }
        });

    }


    private void initView() {
        dataLists = new ArrayList<>();
        mAdapter = new ProduceAdapter(dataLists, this, this);
        mListView.setAdapter(mAdapter);

        mSearchAutoAdapter = new SearchAutoAdapter(this, -1, this);
        mAutoListView = (ListView) findViewById(R.id.auto_listview);
        mAutoListView.setAdapter(mSearchAutoAdapter);
        mAutoListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View view, int position,
                                    long arg3) {
                SearchAutoData data = (SearchAutoData) mSearchAutoAdapter.getItem(position);
                mAutoEdit.setText(data.getContent());
                mSearchButtoon.performClick();
            }
        });

        mSearchButtoon = (ImageView) findViewById(R.id.search_button);
        mSearchButtoon.setOnClickListener(this);
        mAutoEdit = (EditText) findViewById(R.id.auto_edit);
        mAutoEdit.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                mSearchAutoAdapter.performFiltering(s);
                keyWord = mAutoEdit.getText().toString().trim();
                initData(keyWord);
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    /*
     * 保存搜索记录
	 */
    private void saveSearchHistory() {
        String text = mAutoEdit.getText().toString().trim();
        if (text.length() < 1) {
            return;
        }
        SharedPreferences sp = getSharedPreferences(SEARCH_HISTORY, 0);
        String longhistory = sp.getString(SEARCH_HISTORY, "");
        String[] tmpHistory = longhistory.split(",");
        ArrayList<String> history = new ArrayList<String>(
                Arrays.asList(tmpHistory));
        if (history.size() > 0) {
            int i;
            for (i = 0; i < history.size(); i++) {
                if (text.equals(history.get(i))) {
                    history.remove(i);
                    break;
                }
            }
            history.add(0, text);
        }
        if (history.size() > 0) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < history.size(); i++) {
                sb.append(history.get(i) + ",");
            }
            sp.edit().putString(SEARCH_HISTORY, sb.toString()).commit();
        } else {
            sp.edit().putString(SEARCH_HISTORY, text + ",").commit();
        }
    }

    private void initEvent() {

    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.search_button) {//搜索按钮
            keyWord = mAutoEdit.getText().toString().trim();
            initData(keyWord);
            saveSearchHistory();
            mSearchAutoAdapter.initSearchHistory();
        } else {//"+"号按钮
            SearchAutoData data = (SearchAutoData) v.getTag();
            mAutoEdit.setText(data.getContent());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @Override
    public void likeClick(int enumcode) {
        if (enumcode == 0) {
//            initData();
        }
    }
}

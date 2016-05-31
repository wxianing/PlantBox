package com.sinoinnovo.plantbox.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lidroid.xutils.http.RequestParams;
import com.sinoinnovo.plantbox.MainApplication;
import com.sinoinnovo.plantbox.R;
import com.sinoinnovo.plantbox.activity.base.BaseActivity;
import com.sinoinnovo.plantbox.adapter.DetailsListAdapter;
import com.sinoinnovo.plantbox.bean.collect.Collect;
import com.sinoinnovo.plantbox.bean.produce.ProduceDetails;
import com.sinoinnovo.plantbox.constant.URL;
import com.sinoinnovo.plantbox.http.HttpRequestCallBack;
import com.sinoinnovo.plantbox.http.HttpRequestUtils;
import com.sinoinnovo.plantbox.http.RequestParamsUtils;
import com.sinoinnovo.plantbox.model.ResultInfo;
import com.sinoinnovo.plantbox.utils.JsonParse;
import com.sinoinnovo.plantbox.utils.ShareUtils;
import com.sinoinnovo.plantbox.utils.ToastUtils;
import com.sinoinnovo.plantbox.view.DActionSheetDialog;
import com.sinoinnovo.plantbox.view.MyListView;
import com.sinoinnovo.plantbox.widget.AutoAdjustHeightImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.sharesdk.framework.ShareSDK;

/**
 * 商品详情
 */
public class ProductDetailsActivity extends BaseActivity implements View.OnClickListener {
    @Bind(R.id.title_tv)
    protected TextView title;//标题
    @Bind(R.id.back_arrows)
    protected ImageView backImg;

    @Bind(R.id.details_listview)
    protected MyListView mListView;
    private List<String> mDatas;
    protected DetailsListAdapter mAdapter;


    private int count = 0;
    @Bind(R.id.buy_now_buttons)
    protected Button buyNow;//立即购买
    private int oid;
    protected AutoAdjustHeightImageView bannerImg;
    @Bind(R.id.share_img)
    protected TextView shareImg;
    @Bind(R.id.customer_service)
    protected TextView customerService;//客服

    protected TextView produceName;//产品名称
    protected ImageView reduceImg;//减少图标
    protected ImageView addImg;//添加
    @Bind(R.id.collect)
    protected TextView collectTv;
    @Bind(R.id.collect_img)
    protected ImageView collectImg;
    protected TextView total;

    private Gson gson;
    private int isCollect;

    protected ProduceDetails produce;
    protected TextView price;
    private TextView introduce;
    private double producePrice;
    private TextView lookBaike;
    private int classId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        ButterKnife.bind(this);
        initView();
        initData();
        initEvent();
        ShareSDK.initSDK(this);
    }


    private void initView() {
        View headerView = LayoutInflater.from(this).inflate(R.layout.produce_details_header, null);
        mListView.addHeaderView(headerView);
        produceName = (TextView) headerView.findViewById(R.id.produce_name);
        reduceImg = (ImageView) headerView.findViewById(R.id.reduce_img);
        addImg = (ImageView) headerView.findViewById(R.id.add_img);
        bannerImg = (AutoAdjustHeightImageView) headerView.findViewById(R.id.banner_img);
        total = (TextView) headerView.findViewById(R.id.total_count);
        introduce = (TextView) headerView.findViewById(R.id.produce_introduce);
        price = (TextView) headerView.findViewById(R.id.price_tv);
        lookBaike = (TextView) headerView.findViewById(R.id.look_baike);
        gson = new Gson();
        title.setText("商品详情");

        oid = getIntent().getIntExtra("oid", 0);

        producePrice = getIntent().getDoubleExtra("price", 0);
        price.setText("优惠价:￥" + producePrice);
        mDatas = new ArrayList<>();
        if (mDatas.size() > 0)
            mDatas.remove(0);
        mAdapter = new DetailsListAdapter(mDatas, this);
        mListView.setAdapter(mAdapter);
    }

    private void initData() {
        Log.e("oid", "oid>>>>" + oid);
        RequestParams params = RequestParamsUtils.getProduceDetails(oid, this);
        HttpRequestUtils.create(this).send(URL.PRODUCE_DETAILS_URL, params, new HttpRequestCallBack<ResultInfo>() {
            @Override
            public void onSuccess(ResultInfo resultInfo, int requestCode) {
                produce = JsonParse.parseToObject(resultInfo, ProduceDetails.class);
                if (produce != null) {
                    introduce.setText(produce.getNotice());
                    produceName.setText(produce.getProductName());
                    classId = produce.getClassId();
                    mDatas.addAll(produce.getPictures());
                    mAdapter.notifyDataSetChanged();
                    if (mDatas != null && !mDatas.isEmpty())
                        MainApplication.imageLoader.displayImage(mDatas.get(0), bannerImg, MainApplication.options);
                    setDataToView(produce);
                }
            }
        });
    }


    private void setDataToView(ProduceDetails produce) {
//        price.setText("优惠价:￥" + produce.getMinSalePrice());
        int isCollect = produce.getIsCollect();
        if (isCollect == 1) {
            collectImg.setImageResource(R.drawable.cellect_checked);
            collectTv.setText("已收藏");
        } else {
            collectImg.setImageResource(R.drawable.collect_img);
            collectTv.setText("收藏");
        }
    }


    private void initEvent() {
        backImg.setOnClickListener(this);
        reduceImg.setOnClickListener(this);
        addImg.setOnClickListener(this);
        buyNow.setOnClickListener(this);
        shareImg.setOnClickListener(this);
        collectTv.setOnClickListener(this);
        customerService.setOnClickListener(this);
        lookBaike.setOnClickListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.look_baike:
                intent = new Intent(this, PlantBaikeActivity.class);
                intent.putExtra("ClassId", classId);
                startActivity(intent);
                break;
            case R.id.collect:
                String flag = collectTv.getText().toString().trim();
                if (flag.equals("收藏")) {
                    isCollect = 1;
                } else {
                    isCollect = 0;
                }
                RequestParams params = RequestParamsUtils.collectParams(oid, 1, isCollect);
                HttpRequestUtils.create(this).send(URL.COLLENT_URL, params, new HttpRequestCallBack<ResultInfo>() {
                    @Override
                    public void onSuccess(ResultInfo resultInfo, int requestCode) {

                    }

                    @Override
                    public void onReponse(String result) {
                        super.onReponse(result);
                        Collect collect = gson.fromJson(result, Collect.class);
                        if (!TextUtils.isEmpty(collect.getMsg()) && collect.getMsg().equals("您已经收藏啦！")) {
                            collectImg.setImageResource(R.drawable.cellect_checked);
                            collectTv.setText("已收藏");
                        } else {
                            collectImg.setImageResource(R.drawable.collect_img);
                            collectTv.setText("收藏");
                        }
                    }
                });

                break;
            case R.id.share_img://分享
                ShareUtils.showShare(this);
                break;
            case R.id.buy_now_buttons://立即购买
                String totalCount = total.getText().toString().trim();
                intent = new Intent(this, OrderActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("produce", produce);
                intent.putExtras(bundle);
                intent.putExtra("oid", oid);
                intent.putExtra("price", producePrice);
                intent.putExtra("totalCount", totalCount);
                startActivity(intent);

                break;
            case R.id.back_arrows://返回箭头
                finish();
                break;
            case R.id.reduce_img://减号图标
                if (count > 0) {
                    count--;
                    total.setText("" + count);
                }
                break;
            case R.id.add_img://加号图标
                count++;
                total.setText("" + count);
                break;
            case R.id.customer_service:
                selectIcon();
                break;
        }
    }

    private void selectIcon() {
        new DActionSheetDialog(this).builder()
                .setTitle("联系客服")
                .setCancelable(false)
                .setCanceledOnTouchOutside(false)
                .addSheetItem("0769-82669988",
                        DActionSheetDialog.SheetItemColor.Blue,
                        new DActionSheetDialog.OnSheetItemClickListener() {
                            @Override
                            public void onClick(int which) {
                                setCellPhone("076982669988");
                            }
                        }).show();
    }

    public void setCellPhone(String phone) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + phone));
        startActivity(intent);
    }
}

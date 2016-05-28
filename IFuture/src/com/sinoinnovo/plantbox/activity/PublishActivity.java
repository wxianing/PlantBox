package com.sinoinnovo.plantbox.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.sinoinnovo.plantbox.R;
import com.sinoinnovo.plantbox.activity.base.BaseActivity;
import com.sinoinnovo.plantbox.bean.tutorial.TutorialBean;
import com.sinoinnovo.plantbox.constant.Constant;
import com.sinoinnovo.plantbox.constant.URL;
import com.sinoinnovo.plantbox.http.HttpRequestListener;
import com.sinoinnovo.plantbox.http.HttpRequestUtils;
import com.sinoinnovo.plantbox.utils.ImageUtils;
import com.sinoinnovo.plantbox.utils.ToastUtils;
import com.sinoinnovo.plantbox.view.DActionSheetDialog;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PublishActivity extends BaseActivity implements View.OnClickListener {

    @Bind(R.id.title_tv)
    protected TextView title;
    @Bind(R.id.right_tv)
    protected TextView rightTv;
    @Bind(R.id.back_arrows)
    protected ImageView backImg;
    @Bind(R.id.add_photo)
    protected GridView addImg;
    private SimpleAdapter simpleAdapter;//适配器
    private ArrayList<HashMap<String, Object>> imageItem;
    private Bitmap bmp;
    private File tempFile;
    private Bitmap bitmap;
    private String pathImage;
    private List<String> imageUrls = new ArrayList<>();//存放相片的集合
    @Bind(R.id.content_tv)
    protected EditText contentEt;
    private Gson gson;
    private int oid;
    @Bind(R.id.title_text)
    protected TextView titleText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish);
        ButterKnife.bind(this);
        oid = getIntent().getIntExtra("oid", 0);
        initView();
        initEvent();
    }

    private void initView() {
        gson = new Gson();
        title.setText("发表");
        rightTv.setText("发送");
        bmp = BitmapFactory.decodeResource(getResources(), R.drawable.gridview_addpic); //加号
        imageItem = new ArrayList<>();
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("itemImage", bmp);
        map.put("pathImage", "add_pic");
        imageItem.add(map);
        simpleAdapter = new SimpleAdapter(this,
                imageItem, R.layout.griditem_addpic,
                new String[]{"itemImage"}, new int[]{R.id.imageView1});
        simpleAdapter.setViewBinder(new SimpleAdapter.ViewBinder() {
            @Override
            public boolean setViewValue(View view, Object data,
                                        String textRepresentation) {
                if (view instanceof ImageView && data instanceof Bitmap) {
                    ImageView i = (ImageView) view;
                    i.setImageBitmap((Bitmap) data);
                    return true;
                }
                return false;
            }
        });
        addImg.setAdapter(simpleAdapter);
    }

    private void initEvent() {
        rightTv.setOnClickListener(this);
        backImg.setOnClickListener(this);
        addImg.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (imageItem.size() == 4) { //第一张为默认图片
                    Toast.makeText(PublishActivity.this, "图片数3张已满", Toast.LENGTH_SHORT).show();
                } else if (position == 0) { //点击图片位置为+ 0对应0张图片
                    selectIconPhoto();
                } else {
                    DeleteDialog(position);
                }
            }
        });
    }

    /*
         * Dialog对话框提示用户删除操作
         * position为删除图片位置
         */
    protected void DeleteDialog(final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(PublishActivity.this);
        builder.setMessage("确认移除已添加图片吗？");
        builder.setTitle("提示");
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                imageItem.remove(position);
                simpleAdapter.notifyDataSetChanged();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }


    private void selectIconPhoto() {
        new DActionSheetDialog(PublishActivity.this).builder()
                .setTitle("请选择方式")
                .setCancelable(false)
                .setCanceledOnTouchOutside(false)
                .addSheetItem("打开相册",
                        DActionSheetDialog.SheetItemColor.Blue,
                        new DActionSheetDialog.OnSheetItemClickListener() {
                            @Override
                            public void onClick(int which) {
                                ImageUtils.gallery(PublishActivity.this);
                            }
                        })
                .addSheetItem("拍照",
                        DActionSheetDialog.SheetItemColor.Blue,
                        new DActionSheetDialog.OnSheetItemClickListener() {
                            @Override
                            public void onClick(int which) {
                                ImageUtils.camera(PublishActivity.this);
                            }
                        }).show();

    }


    //获取图片路径 响应startActivityForResult
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Constant.LOGIN_RESULT || requestCode == Constant.USER_SET) {//登录或设置账号
//            CreateUI();
        } else if (requestCode == Constant.PHOTO_REQUEST_GALLERY) {
            if (data != null) {
                // 得到图片的全路径
                Uri uri = data.getData();
                ImageUtils.crop(uri, PublishActivity.this);
            }

        } else if (requestCode == Constant.PHOTO_REQUEST_CAMERA) {
            if (ImageUtils.hasSdcard()) {
                tempFile = new File(Environment.getExternalStorageDirectory(),
                        Constant.PHOTO_FILE_NAME);
                ImageUtils.crop(Uri.fromFile(tempFile), PublishActivity.this);
            } else {
                ToastUtils.show(this, "未找到存储卡，无法存储照片！");
            }

        } else if (requestCode == Constant.PHOTO_REQUEST_CUT) {
            try {
                bitmap = data.getParcelableExtra("data");

                HashMap<String, Object> map = new HashMap<String, Object>();
                map.put("itemImage", bitmap);
                map.put("pathImage", pathImage);
                imageItem.add(map);
                simpleAdapter = new SimpleAdapter(this,
                        imageItem, R.layout.griditem_addpic,
                        new String[]{"itemImage"}, new int[]{R.id.imageView1});
                //接口载入图片
                simpleAdapter.setViewBinder(new SimpleAdapter.ViewBinder() {
                    @Override
                    public boolean setViewValue(View view, Object data,
                                                String textRepresentation) {
                        if (view instanceof ImageView && data instanceof Bitmap) {
                            ImageView i = (ImageView) view;
                            i.setImageBitmap((Bitmap) data);
                            return true;
                        }
                        return false;
                    }
                });
                addImg.setAdapter(simpleAdapter);
                simpleAdapter.notifyDataSetChanged();

                String photo = ImageUtils.bitmaptoString(bitmap);
                imageUrls.add(photo);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.right_tv:
                ToastUtils.show(this, "正在发表");
                String content = contentEt.getText().toString().trim();
                String titleStr = titleText.getText().toString().trim();
                sendMsg(titleStr, content, oid, imageUrls);
                break;
            case R.id.back_arrows:
                finish();
                break;
        }
    }


    private void sendMsg(String headTitle, String contentStr, int userGoodsId, List<String> imageUrl) {
        HashMap hashMap = new HashMap();
        hashMap.put("Context", contentStr);
        hashMap.put("HeadTitle", headTitle);
        hashMap.put("MadeDate", "");
        hashMap.put("ImgData", imageUrl);
        hashMap.put("UserGoodsId", "" + userGoodsId);
        hashMap.put("sType", 1);
        hashMap.put("RecordHeadId", 0);
        HttpRequestUtils.getmInstance().send(URL.PUBLISH_TUTORIAL_URL, hashMap, new HttpRequestListener() {
            @Override
            public void onSuccess(JSONObject jsonObject) {
                Log.e("发布成果", jsonObject.toString());
                try {
                    JSONObject obj = new JSONObject(jsonObject.toString());
                    int enumcode = obj.getInt("enumcode");
                    if (enumcode == 0) {
                        ToastUtils.show(PublishActivity.this, "发布成功！");
                        Log.e("发布教程", jsonObject.toString());
                        imageUrls.clear();
                        finish();
                    } else {
                        ToastUtils.show(PublishActivity.this, "发表失败！");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
//
            }
        });
    }
}

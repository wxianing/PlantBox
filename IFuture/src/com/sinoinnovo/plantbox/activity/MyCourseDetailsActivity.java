package com.sinoinnovo.plantbox.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lidroid.xutils.http.RequestParams;
import com.sinoinnovo.plantbox.R;
import com.sinoinnovo.plantbox.activity.base.BaseActivity;
import com.sinoinnovo.plantbox.adapter.UserTutoDetAdapter;
import com.sinoinnovo.plantbox.bean.tutorial.Tusercourse;
import com.sinoinnovo.plantbox.bean.tutorial.TutorialBean;
import com.sinoinnovo.plantbox.constant.URL;
import com.sinoinnovo.plantbox.http.HttpRequestCallBack;
import com.sinoinnovo.plantbox.http.HttpRequestListener;
import com.sinoinnovo.plantbox.http.HttpRequestUtils;
import com.sinoinnovo.plantbox.http.RequestParamsUtils;
import com.sinoinnovo.plantbox.model.ResultInfo;
import com.sinoinnovo.plantbox.utils.ImageUtils;
import com.sinoinnovo.plantbox.utils.JsonParse;
import com.sinoinnovo.plantbox.utils.ToastUtils;
import com.sinoinnovo.plantbox.view.DActionSheetDialog;
import com.sinoinnovo.plantbox.view.MyListView;

import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MyCourseDetailsActivity extends BaseActivity implements View.OnClickListener {
    @Bind(R.id.back_arrows)
    protected ImageView backImg;
    @Bind(R.id.title_tv)
    protected TextView title;
    @Bind(R.id.listview)
    protected MyListView mListView;
    @Bind(R.id.title_name)
    protected TextView titleName;
    private String headTitle;
    private int oid;
    private List<Tusercourse.RecordsBean> mDatas;
    private UserTutoDetAdapter mAdapter;
    @Bind(R.id.right_tv)
    protected TextView rightTv;

    @Bind(R.id.add_photo)
    protected GridView addImg;
    //适配器
    private SimpleAdapter simpleAdapter;
    private Bitmap bmp;
    //存储Bmp图像
    private ArrayList<HashMap<String, Object>> imageItem;

    private static final int PHOTO_REQUEST_CAMERA = 1;// 拍照
    private static final int PHOTO_REQUEST_GALLERY = 2;// 从相册中选择
    private static final int PHOTO_REQUEST_CUT = 3;// 结果
    private static final String PHOTO_FILE_NAME = "temp_photo.jpg";

    private static final int LOGIN_RESULT = 100;
    private static final int USER_SET = 101;
    private File tempFile;
    private Bitmap bitmap;
    private String pathImage;
    private List<String> imageUrls = new ArrayList<>();

    private Gson gson;
    @Bind(R.id.time_tv)
    protected EditText timeText;
    @Bind(R.id.content_tv)
    protected EditText contentText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_course_details);
        ButterKnife.bind(this);
        oid = getIntent().getIntExtra("oid", 0);
        initView();
        initEvent();
        initData(oid);
    }

    private void initData(int oid) {
        RequestParams params = RequestParamsUtils.createRequestParams();
        params.addBodyParameter("Id", "" + oid);
        HttpRequestUtils.create(this).send(URL.USER_COUSER_DETAILS_URL, params, new HttpRequestCallBack<ResultInfo>() {
            @Override
            public void onSuccess(ResultInfo resultInfo, int requestCode) {
                Tusercourse course = JsonParse.parseToObject(resultInfo, Tusercourse.class);
                if (course != null) {
                    mDatas.addAll(course.getRecords());
                    mAdapter.notifyDataSetChanged();
                    titleName.setText(course.getHeadTitle());
                }
            }
        });
    }

    private void initEvent() {
        backImg.setOnClickListener(this);
        rightTv.setOnClickListener(this);
        addImg.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (imageItem.size() == 4) { //第一张为默认图片
                    Toast.makeText(MyCourseDetailsActivity.this, "图片数3张已满", Toast.LENGTH_SHORT).show();
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
        AlertDialog.Builder builder = new AlertDialog.Builder(MyCourseDetailsActivity.this);
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

    private void selectIconPhoto() {
        new DActionSheetDialog(MyCourseDetailsActivity.this).builder()
                .setTitle("请选择方式")
                .setCancelable(false)
                .setCanceledOnTouchOutside(false)
                .addSheetItem("打开相册",
                        DActionSheetDialog.SheetItemColor.Blue,
                        new DActionSheetDialog.OnSheetItemClickListener() {
                            @Override
                            public void onClick(int which) {
                                gallery();
                            }
                        })
                .addSheetItem("拍照",
                        DActionSheetDialog.SheetItemColor.Blue,
                        new DActionSheetDialog.OnSheetItemClickListener() {
                            @Override
                            public void onClick(int which) {
                                camera();
                            }
                        }).show();

    }

    /*
    * 从相册获取
    */
    public void gallery() {
        // 激活系统图库，选择一张图片
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, PHOTO_REQUEST_GALLERY);
    }

    /*
 * 从相机获取
 */
    public void camera() {
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        // 判断存储卡是否可以用，可用进行存储
        if (hasSdcard()) {
            intent.putExtra(MediaStore.EXTRA_OUTPUT,
                    Uri.fromFile(new File(Environment
                            .getExternalStorageDirectory(), PHOTO_FILE_NAME)));
        }
        startActivityForResult(intent, PHOTO_REQUEST_CAMERA);
    }

    /**
     * 检查SD卡
     *
     * @return
     */
    private boolean hasSdcard() {
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            return true;
        } else {
            return false;
        }
    }

    private void initView() {
        gson = new Gson();
        title.setText("我的教程详情");
        rightTv.setText("发布");
        titleName.setText(headTitle);
        mDatas = new ArrayList<>();
        mAdapter = new UserTutoDetAdapter(mDatas, this);
        mListView.setAdapter(mAdapter);

        bmp = BitmapFactory.decodeResource(getResources(), R.drawable.gridview_addpic); //加号
        imageItem = new ArrayList<HashMap<String, Object>>();
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
                // TODO Auto-generated method stub
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_arrows:
                finish();
                break;
            case R.id.right_tv:
                String timeStr = timeText.getText().toString().trim();
                String contentStr = contentText.getText().toString().trim();
                sendMsg(timeStr, contentStr, imageUrls, oid);
                timeStr = "";
                contentStr = "";
                imageUrls.clear();
                break;
        }
    }

    private void sendMsg(String timeStr, String contentStr, List<String> imageUrls, int recordHeadId) {
        HashMap hashMap = new HashMap();
        hashMap.put("HeadTitle", "");
        hashMap.put("MadeDate", timeStr);
        hashMap.put("Context", contentStr);
        hashMap.put("ImgData", imageUrls);
        hashMap.put("UserGoodsId", 0);
        hashMap.put("sType", 2);
        hashMap.put("RecordHeadId", recordHeadId);
        HttpRequestUtils.getmInstance().send(URL.PUBLISH_TUTORIAL_URL, hashMap, new HttpRequestListener() {
            @Override
            public void onSuccess(JSONObject jsonObject) {
                TutorialBean bean = gson.fromJson(jsonObject.toString(), TutorialBean.class);
                if (bean != null) {
                    int enumcode = bean.getEnumcode();
                    if (enumcode == 0) {
                        ToastUtils.show(MyCourseDetailsActivity.this, "发布成功！");
                        Log.e("发布教程", jsonObject.toString());
                        initData(oid);
                    }
                }
            }
        });
    }

    private void crop(Uri uri) {
        // 裁剪图片意图
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        // 裁剪框的比例，1：1
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // 裁剪后输出图片的尺寸大小
        intent.putExtra("outputX", 250);
        intent.putExtra("outputY", 250);
        // 图片格式
        intent.putExtra("outputFormat", "JPEG");
        intent.putExtra("noFaceDetection", true);// 取消人脸识别
        intent.putExtra("return-data", true);// true:不返回uri，false：返回uri
        startActivityForResult(intent, PHOTO_REQUEST_CUT);
    }

    //获取图片路径 响应startActivityForResult
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == LOGIN_RESULT || requestCode == USER_SET) {//登录或设置账号
//            CreateUI();
        } else if (requestCode == PHOTO_REQUEST_GALLERY) {
            if (data != null) {
                // 得到图片的全路径
                Uri uri = data.getData();
                crop(uri);
            }

        } else if (requestCode == PHOTO_REQUEST_CAMERA) {
            if (hasSdcard()) {
                tempFile = new File(Environment.getExternalStorageDirectory(),
                        PHOTO_FILE_NAME);
                crop(Uri.fromFile(tempFile));
            } else {
                ToastUtils.show(this, "未找到存储卡，无法存储照片！");
            }

        } else if (requestCode == PHOTO_REQUEST_CUT) {
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
}

package com.sinoinnovo.plantbox.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
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
import com.sinoinnovo.plantbox.bean.tutorial.ReferUserTutorial;
import com.sinoinnovo.plantbox.bean.tutorial.TutorialBean;
import com.sinoinnovo.plantbox.constant.URL;

import com.sinoinnovo.plantbox.http.HttpRequestListener;
import com.sinoinnovo.plantbox.http.HttpRequestUtils;
import com.sinoinnovo.plantbox.utils.ImageUtils;
import com.sinoinnovo.plantbox.utils.ToastUtils;
import com.sinoinnovo.plantbox.view.DActionSheetDialog;
import com.sinoinnovo.plantbox.widget.ProcessActivity;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PublishTutorialActivity extends BaseActivity implements View.OnClickListener {
    @Bind(R.id.title_tv)
    protected TextView title;
    @Bind(R.id.back_arrows)
    protected ImageView backImg;
    @Bind(R.id.right_tv)
    protected TextView rightTv;
    @Bind(R.id.add_photo)
    protected GridView addImg;
    //存储Bmp图像
    private ArrayList<HashMap<String, Object>> imageItem;
    //适配器
    private SimpleAdapter simpleAdapter;
    private Bitmap bmp;                             //导入临时图片
    @Bind(R.id.title_text)
    protected EditText titleText;
    @Bind(R.id.time_tv)
    protected EditText timeText;
    @Bind(R.id.content_tv)
    protected EditText contentText;
    protected Gson gson;
    private Activity mActivity;
    private Bitmap myBitmap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish_tutorial);
        ButterKnife.bind(this);
        mActivity = this;
        initView();
        initData();
        initEvent();
    }


    private void initEvent() {
        rightTv.setOnClickListener(this);
    }

    private void initView() {
        title.setText("添加教程");
        rightTv.setText("发布");
        gson = new Gson();

        ReferUserTutorial.DataListBean bean = (ReferUserTutorial.DataListBean) getIntent().getSerializableExtra("bean");
        if (bean != null) {
            titleText.setText(bean.getHeadTitle());
            timeText.setText(bean.getCreateTime());
        }


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


    private void initData() {
        backImg.setOnClickListener(this);
        rightTv.setOnClickListener(this);
        addImg.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (imageItem.size() == 10) { //第一张为默认图片
                    Toast.makeText(PublishTutorialActivity.this, "图片数9张已满", Toast.LENGTH_SHORT).show();
                } else if (position == 0) { //点击图片位置为+ 0对应0张图片
                    selectIcon();
                } else {
                    DeleteDialog(position);
                }
            }
        });

    }

    private String pathImage;                     //选择图片路径

    private void selectIcon() {
        new DActionSheetDialog(PublishTutorialActivity.this).builder()
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

    private static final int PHOTO_REQUEST_CAMERA = 1;// 拍照
    private static final int PHOTO_REQUEST_GALLERY = 2;// 从相册中选择
    private static final int PHOTO_REQUEST_CUT = 3;// 结果
    private static final String PHOTO_FILE_NAME = "temp_photo.jpg";

    private static final int LOGIN_RESULT = 100;
    private static final int USER_SET = 101;
    private File tempFile;

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


    /*
         * Dialog对话框提示用户删除操作
         * position为删除图片位置
         */
    protected void DeleteDialog(final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(PublishTutorialActivity.this);
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

    public static byte[] readStream(InputStream inStream) throws Exception {
        byte[] buffer = new byte[1024];
        int len = -1;
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        while ((len = inStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, len);
        }
        byte[] data = outStream.toByteArray();
        outStream.close();
        inStream.close();
        return data;

    }

    public static Bitmap getPicFromBytes(byte[] bytes, BitmapFactory.Options opts) {
        if (bytes != null)
            if (opts != null)
                return BitmapFactory.decodeByteArray(bytes, 0, bytes.length, opts);
            else
                return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        return null;
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

    private Bitmap bitmap;


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
//                imageItem.add(0,map);
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

    //刷新图片
    @Override
    protected void onResume() {
        super.onResume();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    private List<String> imageUrls = new ArrayList<>();

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.right_tv:
                String titleStr = titleText.getText().toString().trim();
                String timeStr = timeText.getText().toString().trim();
                String contentStr = contentText.getText().toString().trim();
                sendMsg(titleStr, timeStr, contentStr, imageUrls);
                break;
            case R.id.back_arrows:
                finish();
                break;
        }
    }

    private void sendMsg(String titleStr, String timeStr, String contentStr, List<String> imageUrls) {
        HashMap hashMap = new HashMap();
        hashMap.put("HeadTitle", titleStr);
        hashMap.put("MadeDate", timeStr);
        hashMap.put("Context", contentStr);
        hashMap.put("ImgData", imageUrls);
        hashMap.put("UserGoodsId", 0);
        hashMap.put("sType", 2);
        hashMap.put("RecordHeadId", 0);
        HttpRequestUtils.getmInstance().send(URL.PUBLISH_TUTORIAL_URL, hashMap, new HttpRequestListener() {
            @Override
            public void onSuccess(JSONObject jsonObject) {
                TutorialBean bean = gson.fromJson(jsonObject.toString(), TutorialBean.class);
                if (bean != null) {
                    int enumcode = bean.getEnumcode();
                    if (enumcode == 0) {
                        ToastUtils.show(PublishTutorialActivity.this, "发布成功！");
                        Log.e("发布教程", jsonObject.toString());

                        finish();
                    }
                }
            }
        });
    }
}

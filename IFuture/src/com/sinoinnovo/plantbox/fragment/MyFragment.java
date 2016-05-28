package com.sinoinnovo.plantbox.fragment;


import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.sinoinnovo.plantbox.R;
import com.sinoinnovo.plantbox.activity.ActiveActivity;
import com.sinoinnovo.plantbox.activity.MyBaseAreaActivity;
import com.sinoinnovo.plantbox.activity.MyCollectActivity;
import com.sinoinnovo.plantbox.activity.MyOrderActivity;
import com.sinoinnovo.plantbox.activity.PersonCenterAdapter;
import com.sinoinnovo.plantbox.activity.SettingActivity;
import com.sinoinnovo.plantbox.bean.PersonCenter;
import com.sinoinnovo.plantbox.utils.ShareUtils;
import com.sinoinnovo.plantbox.utils.SharedPreferencesUtils;
import com.sinoinnovo.plantbox.utils.ToastUtils;
import com.sinoinnovo.plantbox.view.CircularImage;
import com.sinoinnovo.plantbox.view.DActionSheetDialog;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */

public class MyFragment extends Fragment implements AdapterView.OnItemClickListener, View.OnClickListener {
    @Bind(R.id.gridView)
    protected GridView mGridView;
    private List<PersonCenter> mDatas;
    private PersonCenterAdapter mAdapter;
    @Bind(R.id.user_name)
    protected TextView userName;
    private String userNames;
    @Bind(R.id.header_img)
    protected CircularImage headerImg;


    public MyFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my, container, false);
        ButterKnife.bind(this, view);
        initData();
        initView(view);
        initEvent();
        return view;
    }

    private void initEvent() {
        headerImg.setOnClickListener(this);
    }

    private void initView(View view) {

        mAdapter = new PersonCenterAdapter(mDatas, getActivity());
        mGridView.setAdapter(mAdapter);
        mGridView.setOnItemClickListener(this);
        userNames = SharedPreferencesUtils.getStringData(getActivity(), "userName", null);
        userName.setText(userNames);
    }

    private void initData() {
        mDatas = new ArrayList<>();

        mDatas.add(new PersonCenter(R.drawable.person_mybase_icon, "我的基地"));
//        mDatas.add(new PersonCenter(R.drawable.person_activity_icon, "我的活动"));
        mDatas.add(new PersonCenter(R.drawable.person_collect_icon, "我的收藏"));
        mDatas.add(new PersonCenter(R.drawable.person_order_icon, "我的订单"));
        mDatas.add(new PersonCenter(R.drawable.person_activity_area_icon, "活动专区"));
        mDatas.add(new PersonCenter(R.drawable.person_myshare_icon, "我的分享"));
        mDatas.add(new PersonCenter(R.drawable.person_connect_server, "联系客服"));
//        mDatas.add(new PersonCenter(R.drawable.person_mywallet_icon, "我的钱包"));
        mDatas.add(new PersonCenter(R.drawable.person_setting_icon, "设置"));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent;
        switch (position) {
            case 0:
                intent = new Intent(getActivity(), MyBaseAreaActivity.class);
                startActivity(intent);
                break;
            case 1:
                intent = new Intent(getActivity(), MyCollectActivity.class);
                startActivity(intent);
                break;
            case 2:
                intent = new Intent(getActivity(), MyOrderActivity.class);
                startActivity(intent);
                break;
            case 3:
                intent = new Intent(getActivity(), ActiveActivity.class);
                startActivity(intent);
                break;
            case 4:
//                startActivity(new Intent(getActivity(), MyShareActivity.class));
                ShareUtils.showShare(getActivity());
                break;
            case 5:
                selectIcon();
                break;
            case 6:
                intent = new Intent(getActivity(), SettingActivity.class);
                startActivity(intent);
                break;
        }
    }

    private void selectIcon() {
        new DActionSheetDialog(getActivity()).builder()
                .setTitle("联系客服")
                .setCancelable(false)
                .setCanceledOnTouchOutside(false)
                .addSheetItem("076982669988",
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

    private void selectIconPhoto() {
        new DActionSheetDialog(getActivity()).builder()
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

    private Bitmap bitmap;

    /*
  * 从相册获取
  */
    public void gallery() {
        // 激活系统图库，选择一张图片
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, PHOTO_REQUEST_GALLERY);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.header_img:
                selectIconPhoto();
                break;
        }
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

    private File tempFile;

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PHOTO_REQUEST_GALLERY) {
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
                ToastUtils.show(getActivity(), "未找到存储卡，无法存储照片！");
            }

        } else if (requestCode == PHOTO_REQUEST_CUT) {
            try {
                bitmap = data.getParcelableExtra("data");
                this.headerImg.setImageBitmap(bitmap);
                if (tempFile != null) {
                    boolean delete = tempFile.delete();
                }
                upload(bitmap);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    /*
     * 上传图片
	 */
    public void upload(Bitmap bitmap) {

    }
}

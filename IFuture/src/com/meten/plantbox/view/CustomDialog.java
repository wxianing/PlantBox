package com.meten.plantbox.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.meten.plantbox.R;

/**
 * Package com.meten.plantbox.view
 * 创 建 人：wxianing
 * 作 用：
 * 时 间：2016/5/22
 * 修 改 人：
 * 时 间：
 */
public class CustomDialog extends Dialog {
    private View layoutRes;// 布局文件
    private Context context;

    public CustomDialog(Context context) {
        super(context);
    }

    /**
     * 自定义主题及布局的构造方法
     *
     * @param context
     * @param resLayout
     */
    public CustomDialog(Context context, View resLayout) {
        super(context, R.style.customDialog);
        this.context = context;
        this.layoutRes = resLayout;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(layoutRes);
    }

}

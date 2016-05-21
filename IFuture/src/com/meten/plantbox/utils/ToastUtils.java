package com.meten.plantbox.utils;


import com.meten.plantbox.BuildConfig;
import com.meten.plantbox.R;
import com.meten.plantbox.model.ResultInfo;

import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ToastUtils {

    private static ToastUtils toastCommom;

    private static Toast toast;

    private ToastUtils() {
    }

    public static ToastUtils createToastConfig() {
        if (toastCommom == null) {
            toastCommom = new ToastUtils();
        }
        return toastCommom;
    }

    /**
     * 显示Toast
     *
     * @param context
     * @param msg
     */

    public static void show(Context context, String msg) {
        View layout = LayoutInflater.from(context).inflate(R.layout.toast_xml, null);
        TextView text = (TextView) layout.findViewById(R.id.text);
        text.setText(msg);
        toast = new Toast(context);
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();
    }

//    public static void show(Context context, String msg) {
//        if (!TextUtils.isEmpty(msg)) {
//            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
//        }
//    }

    public static void showMsg(Context context, ResultInfo info) {
        if (info != null && !TextUtils.isEmpty(info.getMsg())) {
            show(context, info.getMsg());
        }
    }

    public static void showd(Context context, String msg) {
        if (BuildConfig.DEBUG) {
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
        }
    }
}

package com.meten.ifuture.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.meten.ifuture.R;
import com.meten.ifuture.activity.base.BaseFragmentActivity;
import com.meten.ifuture.fragment.HomeFragment;
import com.meten.ifuture.fragment.MessageFragment;
import com.meten.ifuture.fragment.MyFragment;
import com.meten.ifuture.fragment.NearbyFragment;
import com.meten.ifuture.view.SystemBarTintManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/5/9 0009.
 */
public class MainActivity extends BaseFragmentActivity implements RadioGroup.OnCheckedChangeListener {

    private RadioGroup mRadioGroup;
    private List<Fragment> mFragments;

    private FragmentManager manager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);

        init();
    }

//    private void setTranslucentStatus(boolean on) {
//        Window win = getWindow();
//        WindowManager.LayoutParams winParams = win.getAttributes();
//        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
//        if (on) {
//            winParams.flags |= bits;
//        } else {
//            winParams.flags &= ~bits;
//        }
//        win.setAttributes(winParams);
//    }


    private void init() {
        mRadioGroup = (RadioGroup) findViewById(R.id.main_bottom_rg);
        mFragments = new ArrayList<>();
        mFragments.add(new HomeFragment());
        mFragments.add(new NearbyFragment());
        mFragments.add(new MessageFragment());
        mFragments.add(new MyFragment());

        ((RadioButton) mRadioGroup.getChildAt(0)).setChecked(true);
        manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.main_fragments_contents, mFragments.get(0));
        transaction.commit();
        mRadioGroup.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        for (int i = 0; i < group.getChildCount(); i++) {
            Fragment fragment = mFragments.get(i);
            if (checkedId == group.getChildAt(i).getId()) {
                addFragment(fragment);
            } else {
                FragmentTransaction t = manager.beginTransaction();
                t.hide(fragment);
                t.commit();
            }
        }
    }


    private void addFragment(Fragment fragment) {
        FragmentTransaction t = manager.beginTransaction();
        if (!fragment.isAdded()) {
            t.add(R.id.main_fragments_contents, fragment);
        }
        t.show(fragment);
        t.commit();
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exitApplicatiopn();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }


    private long exitTime = 0;

    private void exitApplicatiopn() {

        if ((System.currentTimeMillis() - exitTime) > 2000) {
            Toast.makeText(getApplicationContext(), "再按一次返回键退出程序",
                    Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            finish();
            System.exit(0);
        }
    }
}

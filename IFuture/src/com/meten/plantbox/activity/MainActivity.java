package com.meten.plantbox.activity;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.meten.plantbox.R;
import com.meten.plantbox.activity.base.BackHandledFragment;
import com.meten.plantbox.activity.base.BackHandledInterface;
import com.meten.plantbox.activity.base.BaseFragmentActivity;
import com.meten.plantbox.fragment.HomeFragment;
import com.meten.plantbox.fragment.MessageFragment;
import com.meten.plantbox.fragment.MyFragment;
import com.meten.plantbox.fragment.NearbyFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/5/9 0009.
 */
public class MainActivity extends BaseFragmentActivity implements RadioGroup.OnCheckedChangeListener, BackHandledInterface {

    private RadioGroup mRadioGroup;
    private List<Fragment> mFragments;
    private BackHandledFragment mBackHandedFragment;
    private FragmentManager manager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

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
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        transaction.addToBackStack("tag");
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
//            t.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
//            t.addToBackStack("tag");
        }
        t.show(fragment);
        t.commit();
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exitSystem();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }


    private long exitTime = 0;

    private void exitSystem() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            Toast.makeText(getApplicationContext(), "再按一次返回键退出程序",
                    Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            finish();
            System.exit(0);
        }
    }

    @Override
    public void setSelectedFragment(BackHandledFragment selectedFragment) {
        this.mBackHandedFragment = selectedFragment;
    }
}

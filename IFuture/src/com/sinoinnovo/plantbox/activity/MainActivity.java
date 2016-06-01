package com.sinoinnovo.plantbox.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.sinoinnovo.plantbox.R;
import com.sinoinnovo.plantbox.activity.base.BackHandledFragment;
import com.sinoinnovo.plantbox.activity.base.BackHandledInterface;
import com.sinoinnovo.plantbox.activity.base.BaseFragmentActivity;
import com.sinoinnovo.plantbox.fragment.HomeFragment;
import com.sinoinnovo.plantbox.fragment.MessageFragment;
import com.sinoinnovo.plantbox.fragment.MyFragment;
import com.sinoinnovo.plantbox.fragment.NearbyFragment;

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
    public static MainActivity mainActivity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainActivity = this;
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

package com.wxb.wanshu;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.Window;
import android.widget.FrameLayout;

import com.wxb.wanshu.base.BaseFragment;
import com.wxb.wanshu.ui.activity.MeActivity;
import com.wxb.wanshu.ui.fragment.BookselfFragment;
import com.wxb.wanshu.view.TabBarView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by qiming on 2017/12/12.
 */

public class WanshuActivity extends FragmentActivity implements TabBarView.TabChangeListener {

    Fragment currentFragment;
    @BindView(R.id.areaFragments)
    FrameLayout areaFragments;
    @BindView(R.id.tabBar)
    TabBarView tabBar;
    private BookselfFragment bookselfFragment;
    private MeActivity meActivity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main_frag);
        ButterKnife.bind(this);

        initFragment();
        tabBar.setOnTabChangeListener(this);
    }

    private void initFragment() {
        bookselfFragment = new BookselfFragment();
        meActivity = new MeActivity();

        FragmentTransaction trans = getSupportFragmentManager().beginTransaction();

        trans.add(R.id.areaFragments, bookselfFragment);
        trans.commit();
        currentFragment = bookselfFragment;
    }

    private void switchFragmentTo(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        if (null == getSupportFragmentManager().getFragments() || !getSupportFragmentManager().getFragments().contains(fragment)) {
            transaction.add(R.id.areaFragments, fragment);
        }

        transaction.hide(currentFragment);
        transaction.show(fragment);
        transaction.commit();
        currentFragment = fragment;
    }

    @Override
    public void onTabChange(int index) {
        switch (index){
            case 1:
                switchFragmentTo(bookselfFragment);
                break;
            case 2:
                switchFragmentTo(bookselfFragment);
                break;
            case 3:
                switchFragmentTo(bookselfFragment);
                break;
            case 4:
//                switchFragmentTo(meActivity);
                break;
        }
    }
}

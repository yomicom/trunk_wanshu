package com.wxb.wanshu.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.wxb.wanshu.MainActivity;
import com.wxb.wanshu.R;
import com.wxb.wanshu.base.BaseActivity;
import com.wxb.wanshu.base.ChapterRead;
import com.wxb.wanshu.base.Constant;
import com.wxb.wanshu.component.AppComponent;
import com.wxb.wanshu.ui.fragment.ReadRecommendFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ReadOtherStatusActivity extends BaseActivity {

    @BindView(R.id.iv_wn)
    ImageView ivWn;
    @BindView(R.id.tv_toast)
    TextView tvToast;
    @BindView(R.id.back)
    TextView back;
    @BindView(R.id.recommend)
    FrameLayout recommend;
    @BindView(R.id.common_toolbar)
    Toolbar commonToolbar;

    public static boolean startActivity(Context context,int is_onsale) {
        if (Constant.BOOK_IS_NOT_ONSALE == is_onsale) {
            context.startActivity(new Intent(context, ReadOtherStatusActivity.class)
                    .putExtra("code", Constant.READ_DOWN_CODE));
            return true;
        }
        return false;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_other_status_read;
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {

    }

    @Override
    public void initToolBar() {
        mCommonToolbar.setTitle(getIntent().getStringExtra("title"));
        mCommonToolbar.setNavigationIcon(R.mipmap.ab_back);
    }

    @Override
    public void initDatas() {
        int code = getIntent().getIntExtra("code", 0);
        switch (code) {
            case Constant.READ_DOWN_CODE:
                gone(commonToolbar);
                ivWn.setImageResource(R.mipmap.load_lock);
                tvToast.setText("为响应先进文化“净网行动”号召，维持绿色的网络环境，部分书籍已下架。");
                visible(back);
                break;
            case Constant.READ_ING_CODE:
                showRecomend();
                break;
            case Constant.READ_FINISH_CODE:
                ivWn.setImageResource(R.mipmap.read_finish);
                tvToast.setText("全书完，快去寻找你的下一本书吧！");
                showRecomend();
                break;
        }
    }

    private void showRecomend() {
        if (getIntent().hasExtra("data")) {
            ChapterRead.DataBean data = (ChapterRead.DataBean) getIntent().getSerializableExtra("data");
            if (data.recommend_list.size() > 0) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();

                ReadRecommendFragment fragment = ReadRecommendFragment.newInstance(data);
                transaction.replace(R.id.recommend, fragment);
                transaction.commit();
                visible(recommend);
            }
        }
    }

    @Override
    public void configViews() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        overridePendingTransition(R.anim.push_left_in, R.anim.push_right_out);
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.back)
    public void onViewClicked() {
        finish();
        startActivity(new Intent(mContext, MainActivity.class));
    }
}

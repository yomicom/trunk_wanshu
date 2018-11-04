package com.wxb.wanshu.view;

import android.content.Context;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wxb.wanshu.R;
import com.wxb.wanshu.bean.HomeData;
import com.wxb.wanshu.ui.activity.BookDetailsActivity;
import com.wxb.wanshu.utils.ImageUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qiming on 2017/11/29.
 */

public class CustomerBanner extends FrameLayout {
    private static final int DELAY = 3000;
    private Context mContext;
    //存放图片的ImageView
    private List<View> mPagesIV;
    //存放圆点的ImageView
    private List<ImageView> mDotsIV;
    //待显示图片的资源ID
//    private int[] mDrawableIds = {R.drawable.red, R.drawable.green, R.drawable.yellow,
//            R.drawable.orange, R.drawable.blue};
    private ViewPager mVP;
    private boolean isAutoPlay;
    private int currentItem;

    private Handler mHandler = new Handler();

    public CustomerBanner(Context context) {
        this(context, null);
    }

    public CustomerBanner(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomerBanner(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initView();
    }

    private void initView() {
        mPagesIV = new ArrayList<>();
        mDotsIV = new ArrayList<>();
    }

    private void initContent(int len) {
        View view = LayoutInflater.from(mContext).inflate(
                R.layout.carousel_layout, this, true);
        mVP = (ViewPager) view.findViewById(R.id.view_pager);
        LinearLayout mDotsLayout = (LinearLayout) view.findViewById(R.id.dots);

        for (int i = 0; i < len; i++) {
            ImageView dotIV = new ImageView(mContext);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            params.leftMargin = params.rightMargin = 4;
            mDotsLayout.addView(dotIV, params);
            mDotsIV.add(dotIV);
        }
    }

    private void startCarousel() {
        isAutoPlay = true;
        mHandler.postDelayed(task, DELAY);
    }

    public void setData(HomeData.DataBeanX data) {
        List<HomeData.DataBeanX.DataBean> list = data.getData();
        if (list != null && list.size() > 0) {
            initContent(list.size());
            for (int i = 0; i < list.size(); i++) {
                View item = View.inflate(mContext, R.layout.item_vp_banner, null);
                ImageView iv = item.findViewById(R.id.iv_banner);
                TextView tvTitle = item.findViewById(R.id.tv_banner_title);
                TextView tvIntro = item.findViewById(R.id.tv_banner_intro);

                HomeData.DataBeanX.DataBean bean = list.get(i);
                ImageUtils.displayImage(mContext, iv, bean.getCover(), 0, 0);
                tvTitle.setText(bean.getName());
                tvIntro.setText(bean.getDescription());

                item.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        BookDetailsActivity.startActivity(mContext, bean.getNovel_id());
                    }
                });

                mPagesIV.add(item);
            }


            mVP.setAdapter(new TopPagerAdapter());
            mVP.setFocusable(true);
            mVP.setCurrentItem(currentItem);
            mVP.addOnPageChangeListener(new TopOnPageChangeListener());
            startCarousel();
        }
    }

    private final Runnable task = new Runnable() {
        @Override
        public void run() {
            if (isAutoPlay) {
                currentItem = (currentItem + 1) % (mPagesIV.size());
                mVP.setCurrentItem(currentItem);
                mHandler.postDelayed(task, DELAY);
            } else {
                mHandler.postDelayed(task, DELAY);
            }
        }
    };

    class TopPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return mPagesIV.size();
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(mPagesIV.get(position));
            return mPagesIV.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

    }

    class TopOnPageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            for (int i = 0; i < mDotsIV.size(); i++) {
                if (i == position) {
                    mDotsIV.get(i).setImageResource(R.drawable.dot_focus);
                } else {
                    mDotsIV.get(i).setImageResource(R.drawable.dot_blur);
                }
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {
            switch (state) {
                //SCROLL_STATE_DRAGGING
                case 1:
                    isAutoPlay = false;
                    break;
                //SCROLL_STATE_SETTLING
                case 2:
                    isAutoPlay = true;
                    break;
                default:
                    break;
            }
        }
    }


}

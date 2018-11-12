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
import com.wxb.wanshu.ui.activity.WebViewActivity;
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
    private List<ImageView> mPagesIV;
    //存放圆点的ImageView
    private List<ImageView> mDotsIV;
    //待显示图片的资源ID
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
                View item = View.inflate(mContext, R.layout.image_banner, null);
                ImageView iv = item.findViewById(R.id.iv_banner);

                HomeData.DataBeanX.DataBean bean = list.get(i);
                ImageUtils.displayImage(mContext, iv, bean.getCover(), 0, 0);

                item.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if ("page".equals(bean.type)) {
                            WebViewActivity.startActivity(mContext, "", bean.url);
                        } else {
                            BookDetailsActivity.startActivity(mContext, bean.getId());
                        }
                    }
                });

                mPagesIV.add(iv);
            }


            mVP.setAdapter(new TopPagerAdapter());
            mVP.setFocusable(true);
            int item = Integer.MAX_VALUE / 2 - (Integer.MAX_VALUE / 2 % mPagesIV.size());
            mVP.setCurrentItem(item);
//            mVP.setCurrentItem(currentItem);
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
            return Integer.MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            //拿着position位置 % 集合.size
            int newposition = position % mPagesIV.size();
            //获取到条目要显示的内容imageview
            ImageView iv = mPagesIV.get(newposition);
            //要把 iv加入到 container 中

            if (iv.getParent() != null)
                ((ViewGroup) iv.getParent()).removeView(iv);
            container.addView(iv);
            return iv;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((ImageView) object);
        }

    }

    class TopOnPageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//            if (positionOffset == 0.0) {
//                if (position == 0) {
//                    //当滑到第一张图时显示最后一张图并将postion跳至"D"位置
//                    mVP.setCurrentItem(mPagesIV.size() - 2, false);
//                }//当滑到最后一张图时显示第一张图并将position跳至"A"位置
//                else if (position == mPagesIV.size() - 1) {
//                    mVP.setCurrentItem(1, false);
//                }
//            }
        }

        @Override
        public void onPageSelected(int position) {
            for (int i = 0; i < mDotsIV.size(); i++) {
                if (i == position) {
                    mDotsIV.get(i).setImageResource(R.mipmap.dot_focus);
                } else {
                    mDotsIV.get(i).setImageResource(R.mipmap.dot_blur);
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

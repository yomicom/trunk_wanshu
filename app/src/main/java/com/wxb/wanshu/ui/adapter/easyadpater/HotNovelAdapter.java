package com.wxb.wanshu.ui.adapter.easyadpater;

import android.content.Context;
import android.view.View;

import com.wxb.wanshu.R;
import com.wxb.wanshu.bean.HomeData;
import com.wxb.wanshu.bean.HotNovelList;
import com.wxb.wanshu.common.OnRvItemClickListener;
import com.wxb.wanshu.ui.activity.SearchActivity;
import com.wxb.wanshu.ui.adapter.base.EasyRVAdapter;
import com.wxb.wanshu.ui.adapter.base.EasyRVHolder;

import java.util.List;

/**
 * Created by qiming on 2017/12/13.
 */

public class HotNovelAdapter extends EasyRVAdapter<HomeData.DataBeanX.DataBean> {
    private OnRvItemClickListener itemClickListener;

    public HotNovelAdapter(Context context, List<HomeData.DataBeanX.DataBean> list, OnRvItemClickListener listener) {
        super(context, list, R.layout.item_search_hot);
        this.itemClickListener = listener;
    }


    @Override
    protected void onBindData(EasyRVHolder viewHolder, int position, HomeData.DataBeanX.DataBean item) {
        viewHolder.setText(R.id.article_title, item.getName());
//                .setText(R.id.tv_sort, "" + (position + 1));

//        switch (position + 1) {
//            case 1:
//                viewHolder.setBackgroundColorRes(R.id.tv_sort, R.color.search_color_1);
//                break;
//            case 2:
//                viewHolder.setBackgroundColorRes(R.id.tv_sort, R.color.search_color_2);
//                break;
//            case 3:
//                viewHolder.setBackgroundColorRes(R.id.tv_sort, R.color.search_color_2);
//                break;
//            default:
//                viewHolder.setBackgroundColorRes(R.id.tv_sort, R.color.search_color_3);
//                break;
//        }

        viewHolder.setOnItemViewClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemClickListener.onItemClick(view, position, item);
            }
        });
    }
}
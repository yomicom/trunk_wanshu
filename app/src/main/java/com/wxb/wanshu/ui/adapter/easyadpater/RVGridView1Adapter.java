package com.wxb.wanshu.ui.adapter.easyadpater;

import android.content.Context;
import android.view.View;

import com.wxb.wanshu.R;
import com.wxb.wanshu.bean.HomeData;
import com.wxb.wanshu.common.OnRvItemClickListener;
import com.wxb.wanshu.ui.adapter.base.EasyRVAdapter;
import com.wxb.wanshu.ui.adapter.base.EasyRVHolder;

import java.util.List;

/**
 * Created by qiming on 2017/11/30.
 */

public class RVGridView1Adapter extends EasyRVAdapter<HomeData.DataBeanX.DataBean> {
    private OnRvItemClickListener itemClickListener;

    public RVGridView1Adapter(Context context, List<HomeData.DataBeanX.DataBean> list, OnRvItemClickListener listener) {
        super(context, list, R.layout.include_item_easy_book);
        this.itemClickListener = listener;
    }

    @Override
    protected void onBindData(EasyRVHolder viewHolder, int position, HomeData.DataBeanX.DataBean item) {
        viewHolder.setImageUrl(R.id.iv, item.getCover())
                .setText(R.id.tv_title, item.getName());

//        switch (position + 1) {
//            case 1:
//                viewHolder.setImageResource(R.id.iv_sort, R.mipmap.top_1);
//                break;
//            case 2:
//                viewHolder.setImageResource(R.id.iv_sort, R.mipmap.top_2);
//                break;
//            case 3:
//                viewHolder.setImageResource(R.id.iv_sort, R.mipmap.top_3);
//                break;
//            case 4:
//                viewHolder.setImageResource(R.id.iv_sort, R.mipmap.top_4);
//                break;
//            case 5:
//                viewHolder.setImageResource(R.id.iv_sort, R.mipmap.top_5);
//                break;
//            case 6:
//                viewHolder.setImageResource(R.id.iv_sort, R.mipmap.top_6);
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

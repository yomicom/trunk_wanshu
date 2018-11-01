package com.wxb.wanshu.ui.adapter.easyadpater;

import android.content.Context;
import android.view.View;
import android.widget.ListAdapter;

import com.wxb.wanshu.R;
import com.wxb.wanshu.bean.NovelCategory;
import com.wxb.wanshu.common.OnRvItemClickListener;
import com.wxb.wanshu.ui.adapter.base.EasyLVAdapter;
import com.wxb.wanshu.ui.adapter.base.EasyLVHolder;
import com.wxb.wanshu.ui.adapter.base.EasyRVAdapter;
import com.wxb.wanshu.ui.adapter.base.EasyRVHolder;

import java.util.List;

import static android.R.id.list;

/**
 * Created by qiming on 2017/12/14.
 */

public class NovelCategoryAdapter extends EasyLVAdapter<NovelCategory.DataBean> {

    public NovelCategoryAdapter(Context context, List<NovelCategory.DataBean> list) {
        super(context, list, R.layout.item_search_history);
    }

    @Override
    public void convert(EasyLVHolder viewHolder, int position, NovelCategory.DataBean item) {
        viewHolder.setText(R.id.tv, item.getName());
        if (item.isSelected) {
            viewHolder.setTextColorRes(R.id.tv, R.color.gobal_color);
        } else {
            viewHolder.setTextColorRes(R.id.tv, R.color.text_color_2);
        }
    }

    public void selectNoAll() {
        for (NovelCategory.DataBean dataBean : mList) {
            dataBean.isSelected = false;
        }
        notifyDataSetChanged();
    }
}

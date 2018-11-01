/**
 * Copyright 2016 JustWayward Team
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.wxb.wanshu.ui.adapter.easyadpater;

import android.content.Context;
import android.text.Html;

import com.wxb.wanshu.R;
import com.wxb.wanshu.bean.BookMenu;
import com.wxb.wanshu.bean.RechargeAmount;
import com.wxb.wanshu.manager.CacheManager;
import com.wxb.wanshu.ui.adapter.base.EasyLVAdapter;
import com.wxb.wanshu.ui.adapter.base.EasyLVHolder;

import java.util.List;


/**
 * @author yuyh.
 * @date 2016/9/23.
 */
public class BookMenuAdapter extends EasyLVAdapter<BookMenu.DataBean.ChaptersBean> {

    int selectPos;
    int novel_id;

    public BookMenuAdapter(Context context, List<BookMenu.DataBean.ChaptersBean> list, int novel_id, int selectPos) {
        super(context, list, R.layout.item_book_menu);
        this.selectPos = selectPos;
        this.novel_id = novel_id;
    }

    @Override
    public void convert(EasyLVHolder holder, int position, BookMenu.DataBean.ChaptersBean data) {
        holder.setText(R.id.tv_chapter_title, data.getChapter_name());

        //付费维度各状态优先级：免费＞会员免费＞已购买＞限时免费＞未购买（含单章购买/整本购买标识）
        if (data.is_free == 1) {//免费
            holder.setText(R.id.tv_chapter_status, "").setVisible(R.id.iv_locked_chapter, false);
        } else if (data.is_vip == 1) {//会员免费
            holder.setText(R.id.tv_chapter_status, "会员免费").setVisible(R.id.iv_locked_chapter, false);
        } else if (data.is_buy == 1) {//已购买
            holder.setText(R.id.tv_chapter_status, "已购买").setVisible(R.id.iv_locked_chapter, false);
        } else if (data.free_time != 0) {//限时免费
            holder.setText(R.id.tv_chapter_status, "限时免费").setVisible(R.id.iv_locked_chapter, false);
        } else if (data.is_whole == 1) {//需整本购买标识
            holder.setText(R.id.tv_chapter_status, "").setVisible(R.id.iv_locked_chapter, true);
        } else {
            holder.setText(R.id.tv_chapter_status, data.fee + "书币").setVisible(R.id.iv_locked_chapter, false);
        }

        //若该章节已缓存，则高亮显示
        if (CacheManager.getInstance().getChapterFile(novel_id + "", data.getChapter_id()) != null) {
            holder.setTextColorRes(R.id.tv_chapter_title, R.color.text_color_1);
        }else {
            holder.setTextColorRes(R.id.tv_chapter_title, R.color.text_color_2);
        }

        if (position == selectPos) {
            holder.setTextColorRes(R.id.tv_chapter_title, R.color.gobal_color);
        }

    }

}

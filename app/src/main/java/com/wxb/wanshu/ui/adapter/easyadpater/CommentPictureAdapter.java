package com.wxb.wanshu.ui.adapter.easyadpater;

import android.content.Context;
import android.view.View;

import com.wxb.wanshu.R;
import com.wxb.wanshu.bean.NovelCategory;
import com.wxb.wanshu.ui.adapter.base.EasyLVAdapter;
import com.wxb.wanshu.ui.adapter.base.EasyLVHolder;

import java.util.List;

/**
 * Created by qiming on 2017/12/14.
 */

public class CommentPictureAdapter extends EasyLVAdapter<String> {

    public CommentPictureAdapter(Context context, List<String> list) {
        super(context, list, R.layout.item_comment_img);
    }

    @Override
    public void convert(EasyLVHolder viewHolder, int position, String item) {
        if ("".equals(item)) {
            viewHolder.setImageResource(R.id.iv, R.mipmap.ic_add_picture);
            viewHolder.setVisible(R.id.del,false);
        } else {
            viewHolder.setImageUrl(R.id.iv, item);
            viewHolder.setVisible(R.id.del,true);
        }

        viewHolder.getView(R.id.del).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!"".equals(item)){
                    remove(position);
                }
            }
        });
    }
}

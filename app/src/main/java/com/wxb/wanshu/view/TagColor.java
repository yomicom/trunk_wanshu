package com.wxb.wanshu.view;

import android.graphics.Color;

import com.wxb.wanshu.base.Constant;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qiming on 2017/12/14.
 */

public class TagColor {

    public int borderColor = Color.WHITE;
    public int backgroundColor = Color.WHITE;
    public int textColor = Color.parseColor("#1C1C1C");

    public static List<TagColor> getRandomColors(int size){

        List<TagColor> list = new ArrayList<>();
        for(int i=0; i< size; i++){
            TagColor color = new TagColor();
            color.borderColor = color.backgroundColor = Constant.tagColors[i % Constant.tagColors.length];
            list.add(color);
        }
        return list;
    }
}

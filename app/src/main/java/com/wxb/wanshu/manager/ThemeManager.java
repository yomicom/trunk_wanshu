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
package com.wxb.wanshu.manager;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.content.ContextCompat;
import android.view.View;

import com.wxb.wanshu.R;
import com.wxb.wanshu.bean.ReadTheme;
import com.wxb.wanshu.utils.AppUtils;
import com.wxb.wanshu.utils.ScreenUtils;

import java.util.ArrayList;
import java.util.List;
/**
 * @author yuyh.
 * @date 2016/9/23.
 */
public class ThemeManager {

    public static final int NORMAL = 0;
    public static final int GRAY = 1;
    public static final int LEATHER = 2;
    public static final int GREEN = 3;
    public static final int BLUE = 4;
    public static final int BROWN = 5;
    public static final int NIGHT = 6;

    public static int[] THEME_CONTENT_COLOR = {R.color.read_content_white, R.color.read_content_gray, R.color.read_content_leather,
            R.color.read_content_green, R.color.read_content_blue, R.color.read_content_brown, R.color.read_content_night};

    public static int[] THEME_TITLE_COLOR = {R.color.read_title_white, R.color.read_title_gray, R.color.read_title_leather,
            R.color.read_title_green, R.color.read_title_blue, R.color.read_title_brown, R.color.read_title_night};

    /**
     * 显示缩略图
     * @param theme
     * @param view
     */
    public static void setReaderThemeDemo(int theme, View view) {
        switch (theme) {
            case NORMAL:
                view.setBackgroundResource(R.drawable.theme_white_bg);
                break;
            case GRAY:
                view.setBackgroundResource(R.drawable.theme_gray_small);
                break;
            case LEATHER:
                view.setBackgroundResource(R.drawable.theme_leather_small);
                break;
            case GREEN:
                view.setBackgroundResource(R.drawable.theme_green_bg);
                break;
            case BLUE:
                view.setBackgroundResource(R.drawable.theme_blue_bg);
                break;
            case BROWN:
                view.setBackgroundResource(R.drawable.theme_brown_bg);
                break;
            case NIGHT:
                view.setBackgroundResource(R.drawable.theme_night_bg);
                break;
            default:
                break;
        }
    }
    public static void setReaderTheme(int theme, View view) {
        switch (theme) {
            case NORMAL:
                view.setBackgroundResource(R.drawable.theme_white_bg);
                break;
            case GRAY:
                view.setBackgroundResource(R.drawable.theme_gray_bg);
                break;
            case LEATHER:
                view.setBackgroundResource(R.drawable.theme_leather_bg);
                break;
            case GREEN:
                view.setBackgroundResource(R.drawable.theme_green_bg);
                break;
            case BLUE:
                view.setBackgroundResource(R.drawable.theme_blue_bg);
                break;
            case BROWN:
                view.setBackgroundResource(R.drawable.theme_brown_bg);
                break;
            case NIGHT:
                view.setBackgroundResource(R.drawable.theme_night_bg);
                break;
            default:
                break;
        }
    }

    public static Bitmap getThemeDrawable(int theme) {
        Bitmap bmp = Bitmap.createBitmap(ScreenUtils.getScreenWidth(), ScreenUtils.getScreenHeight(), Bitmap.Config.ARGB_8888);
        switch (theme) {
            case NORMAL:
                bmp.eraseColor(ContextCompat.getColor(AppUtils.getAppContext(), R.color.read_theme_white));
                break;
            case GRAY:
                bmp = BitmapFactory.decodeResource(AppUtils.getAppContext().getResources(), R.drawable.theme_gray_bg);
                break;
            case LEATHER:
                bmp = BitmapFactory.decodeResource(AppUtils.getAppContext().getResources(), R.drawable.theme_leather_bg);
                break;
            case GREEN:
                bmp.eraseColor(ContextCompat.getColor(AppUtils.getAppContext(), R.color.read_theme_green));
                break;
            case BLUE:
                bmp.eraseColor(ContextCompat.getColor(AppUtils.getAppContext(), R.color.read_theme_blue));
                break;
            case BROWN:
                bmp.eraseColor(ContextCompat.getColor(AppUtils.getAppContext(), R.color.read_theme_brown));
                break;
            case NIGHT:
                bmp.eraseColor(ContextCompat.getColor(AppUtils.getAppContext(), R.color.read_theme_night));
                break;
            default:
                break;
        }
        return bmp;
    }

//    public static int getContentColor(Context mContext, int theme) {
//        switch (theme) {
//            case NORMAL:
//                ContextCompat.getColor(mContext,R.color.read_content_white);
//                break;
//            case GRAY:
//                bmp = BitmapFactory.decodeResource(AppUtils.getAppContext().getResources(), R.drawable.theme_gray_bg);
//                break;
//            case LEATHER:
//                bmp = BitmapFactory.decodeResource(AppUtils.getAppContext().getResources(), R.drawable.theme_leather_bg);
//                break;
//            case GREEN:
//                bmp.eraseColor(ContextCompat.getColor(AppUtils.getAppContext(), R.color.read_theme_green));
//                break;
//            case BLUE:
//                bmp.eraseColor(ContextCompat.getColor(AppUtils.getAppContext(), R.color.read_theme_blue));
//                break;
//            case BROWN:
//                bmp.eraseColor(ContextCompat.getColor(AppUtils.getAppContext(), R.color.read_theme_brown));
//                break;
//            case NIGHT:
//                bmp.eraseColor(ContextCompat.getColor(AppUtils.getAppContext(), R.color.read_theme_night));
//                break;
//            default:
//                break;
//        }
//        return bmp;
//    }

    public static List<ReadTheme> getReaderThemeData() {
        int[] themes = {NORMAL, GRAY, LEATHER, GREEN, BLUE, BROWN};
        List<ReadTheme> list = new ArrayList<>();
        ReadTheme theme;
        for (int i = 0; i < themes.length; i++) {
            theme = new ReadTheme();
            theme.theme = themes[i];
            list.add(theme);
        }
        return list;
    }

}

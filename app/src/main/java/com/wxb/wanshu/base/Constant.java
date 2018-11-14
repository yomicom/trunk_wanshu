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
package com.wxb.wanshu.base;

import android.graphics.Color;
import android.support.annotation.StringDef;

import com.wxb.wanshu.utils.AppUtils;
import com.wxb.wanshu.utils.FileUtils;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yuyh.
 * @date 16/8/5.
 */
public class Constant {

    public static final String APP_ID = "1";
    public static final String APP_SECRET = "6f2e7db1743fc59dd127bef6032e98bd";

    public static final String API_BASE_URL = "http://app-dev.wanshu.com";

    public static String PATH_DATA = FileUtils.createRootPath(AppUtils.getAppContext()) + "/cache";

    public static String PATH_COLLECT = FileUtils.createRootPath(AppUtils.getAppContext()) + "/collect";

    public static String PATH_TXT = PATH_DATA + "/book/";

    public static String PATH_EPUB = PATH_DATA + "/epub";

    public static String PATH_CHM = PATH_DATA + "/chm";

    public static String BASE_PATH = AppUtils.getAppContext().getCacheDir().getPath();

    public static final String ISNIGHT = "isNight";

    public static final String ISBYUPDATESORT = "isByUpdateSort";
    public static final String FLIP_STYLE = "flipStyle";

    /**
     * 微信
     */
    public static final String WEXIN_APPID = "wx3d6ecaec7ac28525";
    public static final String WEXIN_APPSECRECT = "dba933d7a70fc2898a4ab46e040a13b5";
    /**
     * QQ
     */
    public static final String QQ_APPID = "1106438795";
    public static final String QQ_APPSECRECT = "MGaMid3vly2Vmau2";

    public static final int SLIDABLE_DISABLE = 0;
    public static final int SLIDABLE_EDGE = 1;

    /**
     * 阅读书籍状态码
     */
    public static final int READ_DOWN_CODE = 410;//小说已下架
    public static final int READ_ING_CODE = 420;//小说未完待续
    public static final int READ_FINISH_CODE = 430;//小说完结

    public static final int BOOK_IS_NOT_ONSALE = 0;//小说下架

    /**
     * 支付宝支付业务：入参app_id
     */
    public static final String ALIPAY_APPID = "2015121100959749";
    // 商户PID
    public static final String ALIPAY_PARTNER = "2088911308261825";
    // 商户收款账号
    public static final String ALIPAY_SELLER = "2088911308261825";

    public static final String SUFFIX_TXT = ".txt";
    public static final String SUFFIX_PDF = ".pdf";
    public static final String SUFFIX_EPUB = ".epub";
    public static final String SUFFIX_ZIP = ".zip";
    public static final String SUFFIX_CHM = ".chm";

    public static final int[] tagColors = new int[]{
            Color.parseColor("#90C5F0"),
            Color.parseColor("#91CED5"),
            Color.parseColor("#F88F55"),
            Color.parseColor("#C0AFD0"),
            Color.parseColor("#E78F8F"),
            Color.parseColor("#67CCB7"),
            Color.parseColor("#F6BC7E")
    };

    @StringDef({
            Gender.MALE,
            Gender.FEMALE
    })
    @Retention(RetentionPolicy.SOURCE)
    public @interface Gender {
        String MALE = "male";

        String FEMALE = "female";
    }

    @StringDef({
            CateType.HOT,
            CateType.NEW,
            CateType.REPUTATION,
            CateType.OVER
    })
    @Retention(RetentionPolicy.SOURCE)
    public @interface CateType {
        String HOT = "hot";

        String NEW = "new";

        String REPUTATION = "reputation";

        String OVER = "over";
    }

    @StringDef({
            Distillate.ALL,
            Distillate.DISTILLATE
    })
    @Retention(RetentionPolicy.SOURCE)
    public @interface Distillate {
        String ALL = "";

        String DISTILLATE = "true";
    }

    @StringDef({
            SortType.DEFAULT,
            SortType.COMMENT_COUNT,
            SortType.CREATED,
            SortType.HELPFUL
    })
    @Retention(RetentionPolicy.SOURCE)
    public @interface SortType {
        String DEFAULT = "updated";

        String CREATED = "created";

        String HELPFUL = "helpful";

        String COMMENT_COUNT = "comment-count";
    }

    public static List<String> sortTypeList = new ArrayList<String>() {{
        add(SortType.DEFAULT);
        add(SortType.CREATED);
        add(SortType.COMMENT_COUNT);
        add(SortType.HELPFUL);
    }};

    @StringDef({
            BookType.ALL,
            BookType.Banner,
            BookType.EDITOR,
            BookType.FRESH,
            BookType.POPULAR,
            BookType.PUBLISHING,
            BookType.FINISH,
            BookType.MID_BANNER,
            BookType.SEARCH_HOT,
            BookType.Boutique_All,
            BookType.Boutique_Publishing,
            BookType.Boutique_Finished,
            BookType.Short_ModernRomance,
            BookType.Short_AncientRomance,
            BookType.Short_All,
            BookType.Finished_All,
            BookType.Finished_Popular,
            BookType.Finished_Latest
    })
    @Retention(RetentionPolicy.SOURCE)
    public @interface BookType {
        String ALL = "all";

        String Banner = "homepage:big_cover";//Banner

        String EDITOR = "homepage:editor";//编辑力荐

        String FRESH = "homepage:fresh";//新书速递

        String POPULAR = "homepage:popular";//人气佳作

        String PUBLISHING = "homepage:publishing";//火爆连载

        String MID_BANNER = "homepage:mid_banner";//首页图片

        String FINISH = "homepage:complete";//完本精选

        String SEARCH_HOT = "search:hot";//搜索推荐

        String Boutique_All = "boutique:all";//畅读精品

        String Boutique_Publishing = "boutique:publishing";//连载好文

        String Boutique_Finished = "boutique:finished";//完结精选

        String Short_ModernRomance = "short:modernRomance";//现代言情

        String Short_AncientRomance = "short:ancientRomance";//古代言情

        String Short_All = "short:all";//精选短篇

        String Finished_All = "finished:all";//完结精选

        String Finished_Popular = "finished:popular";//人气红文

        String Finished_Latest = "finished:latest";//最新完结
    }

    public static List<String> bookTypeList = new ArrayList<String>() {{
        add(BookType.ALL);
        add(BookType.Banner);
        add(BookType.EDITOR);
        add(BookType.FRESH);
        add(BookType.POPULAR);
        add(BookType.PUBLISHING);
        add(BookType.FINISH);
        add(BookType.MID_BANNER);
        add(BookType.SEARCH_HOT);
        add(BookType.Boutique_All);
        add(BookType.Boutique_Publishing);
        add(BookType.Boutique_Finished);
        add(BookType.Short_ModernRomance);
        add(BookType.Short_AncientRomance);
        add(BookType.Short_All);
        add(BookType.Finished_All);
        add(BookType.Finished_Popular);
        add(BookType.Finished_Latest);
    }};
//
//    public static Map<String, String> bookType = new HashMap<String, String>() {{
//        put("qt", "其他");
//        put(BookType.EDITOR, "玄幻奇幻");
//        put(BookType.FRESH, "武侠仙侠");
//        put(BookType.POPULAR, "都市异能");
//        put(BookType.PUBLISHING, "历史军事");
//        put(BookType.FINISH, "游戏竞技");
//        put(BookType.HOT, "科幻灵异");
//        put(BookType.CYJK, "穿越架空");
//        put(BookType.HMZC, "豪门总裁");
//        put(BookType.XDYQ, "现代言情");
//        put(BookType.GDYQ, "古代言情");
//        put(BookType.HXYQ, "幻想言情");
//        put(BookType.DMTR, "耽美同人");
//    }};
}

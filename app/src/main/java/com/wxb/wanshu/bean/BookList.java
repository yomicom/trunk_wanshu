package com.wxb.wanshu.bean;

import java.util.List;

/**
 * Created by qiming on 2017/11/30.
 */

public class BookList extends Base {

    /**
     * data : [{"novel_id":11,"title":"极品相师美人恩","summary":"\u201c断天下不平之事，品芸芸众生之相。\u201d 叶枫无意间获得了大明开国军师刘伯温的《青田遗书》，凭借着书中的风水、命理、医药、奇术，纵横都市。","cover":"http://cdn-novel.weituibao.com/0-temp-201708-30-1504070887233.jpg","complete_status":0,"category":"都市言情","read":1024},{"novel_id":12,"title":"硬汉保镖要上天","summary":"叶凌天，神秘部队退伍军人，为了给妹妹凑集五十万的治疗费用不得不给三元集团的千金小姐李雨欣当贴身保镖。且看经历过太多生死的铮铮硬汉叶凌天如何在这个繁华都市里走出属于自己的一条不平凡的路来。","cover":"http://cdn-novel.weituibao.com/0-temp-201709-26-1506409633341.jpg","complete_status":1,"category":"穿越架空","read":1024},{"novel_id":13,"title":"极品相师美人恩","summary":"\u201c断天下不平之事，品芸芸众生之相。\u201d 叶枫无意间获得了大明开国军师刘伯温的《青田遗书》，凭借着书中的风水、命理、医药、奇术，纵横都市。","cover":"http://cdn-novel.weituibao.com/0-temp-201708-30-1504070887233.jpg","complete_status":0,"category":"都市言情","read":1024},{"novel_id":14,"title":"硬汉保镖要上天","summary":"叶凌天，神秘部队退伍军人，为了给妹妹凑集五十万的治疗费用不得不给三元集团的千金小姐李雨欣当贴身保镖。且看经历过太多生死的铮铮硬汉叶凌天如何在这个繁华都市里走出属于自己的一条不平凡的路来。","cover":"http://cdn-novel.weituibao.com/0-temp-201709-26-1506409633341.jpg","complete_status":1,"category":"穿越架空","read":1024}]
     * totalCount : 4
     */

    private List<DataBean> data;
    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean extends Book{
    }
}

package com.wxb.wanshu.bean;

import java.util.List;

/**
 * Created by qiming on 2017/12/13.
 */

public class HotNovelList extends Base {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * novel_id : 2109
         * name : 我的极品美女上司
         * link : http://xs.yiwei.com/book?account_id=84&novel_id=2109
         */

        private int novel_id;
        private String name;
        private String link;

        public int getNovel_id() {
            return novel_id;
        }

        public void setNovel_id(int novel_id) {
            this.novel_id = novel_id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }
    }
}

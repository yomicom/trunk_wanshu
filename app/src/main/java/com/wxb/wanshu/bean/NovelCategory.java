package com.wxb.wanshu.bean;

import java.util.List;

/**
 * Created by qiming on 2017/12/14.
 */

public class NovelCategory extends Base {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * category_id : 1
         * cover : http://s.weituibao.com/static/1498129510527/DUSHI.png
         * name : 都市言情
         * tag : ["都市情感","婚姻","情仇爱恨","美女"]
         */

        private int category_id;
        private String cover;
        private String name;
        private List<String> tag;
        public boolean isSelected = false;

        public int getCategory_id() {
            return category_id;
        }

        public void setCategory_id(int category_id) {
            this.category_id = category_id;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<String> getTag() {
            return tag;
        }

        public void setTag(List<String> tag) {
            this.tag = tag;
        }
    }
}

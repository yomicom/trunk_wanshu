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
         * id : 2
         * name : 时空穿越
         * cover : http://s.weituibao.com/static/1498129510527/CHUANYUE.png
         */

        private String id;
        private String name;
        private String cover;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }
    }
}

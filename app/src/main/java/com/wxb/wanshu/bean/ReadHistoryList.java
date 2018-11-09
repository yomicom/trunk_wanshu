package com.wxb.wanshu.bean;

import java.util.List;

/**
 * Created by qiming on 2017/12/14.
 */

public class ReadHistoryList extends Base {

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
         * name : 千金归来：老公，请走开
         * cover : http://wenxin-novel.oss-cn-hangzhou.aliyuncs.com/novel-cover-a19d1ddedd8d32f32f97f71f9d4d9bca.jpg
         * description : 深爱之人毫无预兆地变脸：出轨，设计她父亲跳楼，逼她离婚，甚至还想要她的命！被伤到体无完肤的凌萱，落荒而逃。两年后，再相逢。“凌萱？”“陆董，我叫凌若，您是不是认错人了？”复仇的火焰在凌萱心底熊熊燃烧：陆庭轩，你的戏已落幕，该轮到我登场了……
         * word_num : 33345
         * author : 栀想
         * category_name :
         * is_complete : false
         */

        private String id;
        private String name;
        private String cover;
        private String description;
        private String word_num;
        private String author;
        private String category_name;
        private boolean is_complete;

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

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getWord_num() {
            return word_num;
        }

        public void setWord_num(String word_num) {
            this.word_num = word_num;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getCategory_name() {
            return category_name;
        }

        public void setCategory_name(String category_name) {
            this.category_name = category_name;
        }

        public boolean isIs_complete() {
            return is_complete;
        }

        public void setIs_complete(boolean is_complete) {
            this.is_complete = is_complete;
        }
    }
}

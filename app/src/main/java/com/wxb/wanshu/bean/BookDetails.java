package com.wxb.wanshu.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by qiming on 2017/12/1.
 */

public class BookDetails extends Base {

    /**
     * data : {"id":"2","name":"千金归来：老公，请走开","owner_id":"13","author":"栀想","cover":"novel-cover-a19d1ddedd8d32f32f97f71f9d4d9bca.jpg","description":"深爱之人毫无预兆地变脸：出轨，设计她父亲跳楼，逼她离婚，甚至还想要她的命！被伤到体无完肤的凌萱，落荒而逃。两年后，再相逢。\u201c凌萱？\u201d\u201c陆董，我叫凌若，您是不是认错人了？\u201d复仇的火焰在凌萱心底熊熊燃烧：陆庭轩，你的戏已落幕，该轮到我登场了\u2026\u2026","complete_status":"0","complete_time":null,"category_id":"3","sex_type":"20","word_num":"33345","sort":"0","chapter_num":"5","free_chapter_num":"0","create_time":"0","update_time":"0","length_type":"1","view_count":"0","is_onsale":"1","category_name":"总裁豪门","first_chapter":{"id":"1","name":"第1章 三不画","publish_time":"2018-11-06"},"latest_chapter":{"id":"3","name":"第3章 魂再现","publish_time":"2018-11-06"},"recommend":[{"id":"3","name":"千金归来：老公，请走开","cover":"http://wenxin-novel.oss-cn-hangzhou.aliyuncs.com/novel-cover-a19d1ddedd8d32f32f97f71f9d4d9bca.jpg","description":"深爱之人毫无预兆地变脸：出轨，设计她父亲跳楼，逼她离婚，甚至还想要她的命！被伤到体无完肤的凌萱，落荒而逃。两年后，再相逢。\u201c凌萱？\u201d\u201c陆董，我叫凌若，您是不是认错人了？\u201d复仇的火焰在凌萱心底熊熊燃烧：陆庭轩，你的戏已落幕，该轮到我登场了\u2026\u2026","word_num":"33345","author":"栀想","category_name":"","is_complete":false},{"id":"4","name":"千金归来：老公，请走开","cover":"http://wenxin-novel.oss-cn-hangzhou.aliyuncs.com/novel-cover-a19d1ddedd8d32f32f97f71f9d4d9bca.jpg","description":"深爱之人毫无预兆地变脸：出轨，设计她父亲跳楼，逼她离婚，甚至还想要她的命！被伤到体无完肤的凌萱，落荒而逃。两年后，再相逢。\u201c凌萱？\u201d\u201c陆董，我叫凌若，您是不是认错人了？\u201d复仇的火焰在凌萱心底熊熊燃烧：陆庭轩，你的戏已落幕，该轮到我登场了\u2026\u2026","word_num":"33345","author":"栀想","category_name":"","is_complete":false},{"id":"5","name":"千金归来：老公，请走开","cover":"http://wenxin-novel.oss-cn-hangzhou.aliyuncs.com/novel-cover-a19d1ddedd8d32f32f97f71f9d4d9bca.jpg","description":"深爱之人毫无预兆地变脸：出轨，设计她父亲跳楼，逼她离婚，甚至还想要她的命！被伤到体无完肤的凌萱，落荒而逃。两年后，再相逢。\u201c凌萱？\u201d\u201c陆董，我叫凌若，您是不是认错人了？\u201d复仇的火焰在凌萱心底熊熊燃烧：陆庭轩，你的戏已落幕，该轮到我登场了\u2026\u2026","word_num":"33345","author":"栀想","category_name":"","is_complete":false}]}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean implements Serializable {
        /**
         * id : 2
         * name : 千金归来：老公，请走开
         * owner_id : 13
         * author : 栀想
         * cover : novel-cover-a19d1ddedd8d32f32f97f71f9d4d9bca.jpg
         * description : 深爱之人毫无预兆地变脸：出轨，设计她父亲跳楼，逼她离婚，甚至还想要她的命！被伤到体无完肤的凌萱，落荒而逃。两年后，再相逢。“凌萱？”“陆董，我叫凌若，您是不是认错人了？”复仇的火焰在凌萱心底熊熊燃烧：陆庭轩，你的戏已落幕，该轮到我登场了……
         * complete_status : 0
         * complete_time : null
         * category_id : 3
         * sex_type : 20
         * word_num : 33345
         * sort : 0
         * chapter_num : 5
         * free_chapter_num : 0
         * create_time : 0
         * update_time : 0
         * length_type : 1
         * view_count : 0
         * is_onsale : 1
         * category_name : 总裁豪门
         * first_chapter : {"id":"1","name":"第1章 三不画","publish_time":"2018-11-06"}
         * latest_chapter : {"id":"3","name":"第3章 魂再现","publish_time":"2018-11-06"}
         * recommend : [{"id":"3","name":"千金归来：老公，请走开","cover":"http://wenxin-novel.oss-cn-hangzhou.aliyuncs.com/novel-cover-a19d1ddedd8d32f32f97f71f9d4d9bca.jpg","description":"深爱之人毫无预兆地变脸：出轨，设计她父亲跳楼，逼她离婚，甚至还想要她的命！被伤到体无完肤的凌萱，落荒而逃。两年后，再相逢。\u201c凌萱？\u201d\u201c陆董，我叫凌若，您是不是认错人了？\u201d复仇的火焰在凌萱心底熊熊燃烧：陆庭轩，你的戏已落幕，该轮到我登场了\u2026\u2026","word_num":"33345","author":"栀想","category_name":"","is_complete":false},{"id":"4","name":"千金归来：老公，请走开","cover":"http://wenxin-novel.oss-cn-hangzhou.aliyuncs.com/novel-cover-a19d1ddedd8d32f32f97f71f9d4d9bca.jpg","description":"深爱之人毫无预兆地变脸：出轨，设计她父亲跳楼，逼她离婚，甚至还想要她的命！被伤到体无完肤的凌萱，落荒而逃。两年后，再相逢。\u201c凌萱？\u201d\u201c陆董，我叫凌若，您是不是认错人了？\u201d复仇的火焰在凌萱心底熊熊燃烧：陆庭轩，你的戏已落幕，该轮到我登场了\u2026\u2026","word_num":"33345","author":"栀想","category_name":"","is_complete":false},{"id":"5","name":"千金归来：老公，请走开","cover":"http://wenxin-novel.oss-cn-hangzhou.aliyuncs.com/novel-cover-a19d1ddedd8d32f32f97f71f9d4d9bca.jpg","description":"深爱之人毫无预兆地变脸：出轨，设计她父亲跳楼，逼她离婚，甚至还想要她的命！被伤到体无完肤的凌萱，落荒而逃。两年后，再相逢。\u201c凌萱？\u201d\u201c陆董，我叫凌若，您是不是认错人了？\u201d复仇的火焰在凌萱心底熊熊燃烧：陆庭轩，你的戏已落幕，该轮到我登场了\u2026\u2026","word_num":"33345","author":"栀想","category_name":"","is_complete":false}]
         */

        public String id;
        public String name;
        public String owner_id;
        public String author;
        public String cover;
        public String description;
        public String complete_status;
        public Object complete_time;
        public String category_id;
        public String sex_type;
        public int word_num;
        public int sort;
        public String chapter_num;
        public String free_chapter_num;
        public String create_time;
        public String update_time;
        public String length_type;
        public String view_count;
        public int is_onsale = 1;
        public String category_name;
        public boolean on_shelf;
        public FirstChapterBean first_chapter;
        public LatestChapterBean latest_chapter;
        public List<RecommendBean> recommend;

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

        public String getOwner_id() {
            return owner_id;
        }

        public void setOwner_id(String owner_id) {
            this.owner_id = owner_id;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
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

        public String getComplete_status() {
            return complete_status;
        }

        public void setComplete_status(String complete_status) {
            this.complete_status = complete_status;
        }

        public Object getComplete_time() {
            return complete_time;
        }

        public void setComplete_time(Object complete_time) {
            this.complete_time = complete_time;
        }

        public String getCategory_id() {
            return category_id;
        }

        public void setCategory_id(String category_id) {
            this.category_id = category_id;
        }

        public String getSex_type() {
            return sex_type;
        }

        public void setSex_type(String sex_type) {
            this.sex_type = sex_type;
        }

        public int getWord_num() {
            return word_num;
        }

        public void setWord_num(int word_num) {
            this.word_num = word_num;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }

        public String getChapter_num() {
            return chapter_num;
        }

        public void setChapter_num(String chapter_num) {
            this.chapter_num = chapter_num;
        }

        public String getFree_chapter_num() {
            return free_chapter_num;
        }

        public void setFree_chapter_num(String free_chapter_num) {
            this.free_chapter_num = free_chapter_num;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getUpdate_time() {
            return update_time;
        }

        public void setUpdate_time(String update_time) {
            this.update_time = update_time;
        }

        public String getLength_type() {
            return length_type;
        }

        public void setLength_type(String length_type) {
            this.length_type = length_type;
        }

        public String getView_count() {
            return view_count;
        }

        public void setView_count(String view_count) {
            this.view_count = view_count;
        }

        public int getIs_onsale() {
            return is_onsale;
        }

        public void setIs_onsale(int is_onsale) {
            this.is_onsale = is_onsale;
        }

        public String getCategory_name() {
            return category_name;
        }

        public void setCategory_name(String category_name) {
            this.category_name = category_name;
        }

        public FirstChapterBean getFirst_chapter() {
            return first_chapter;
        }

        public void setFirst_chapter(FirstChapterBean first_chapter) {
            this.first_chapter = first_chapter;
        }

        public LatestChapterBean getLatest_chapter() {
            return latest_chapter;
        }

        public void setLatest_chapter(LatestChapterBean latest_chapter) {
            this.latest_chapter = latest_chapter;
        }

        public List<RecommendBean> getRecommend() {
            return recommend;
        }

        public void setRecommend(List<RecommendBean> recommend) {
            this.recommend = recommend;
        }

        public static class FirstChapterBean implements Serializable{
            /**
             * id : 1
             * name : 第1章 三不画
             * publish_time : 2018-11-06
             */

            public String id;
            public String name;
            public int sort;
            public String publish_time;

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

            public String getPublish_time() {
                return publish_time;
            }

            public void setPublish_time(String publish_time) {
                this.publish_time = publish_time;
            }
        }

        public static class LatestChapterBean implements Serializable{
            /**
             * id : 3
             * name : 第3章 魂再现
             * publish_time : 2018-11-06
             */

            public String id;
            public String name;
            public int sort;
            public String publish_time;

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

            public String getPublish_time() {
                return publish_time;
            }

            public void setPublish_time(String publish_time) {
                this.publish_time = publish_time;
            }
        }

        public static class RecommendBean extends Book{
        }
    }
}

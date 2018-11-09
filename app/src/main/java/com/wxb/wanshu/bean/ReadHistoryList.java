package com.wxb.wanshu.bean;

import java.util.List;

/**
 * Created by qiming on 2017/12/14.
 */

public class ReadHistoryList extends Base {

    public List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * novel : {"id":"33","name":"你的爱情说了谎","cover":"http://wenxin-novel.oss-cn-hangzhou.aliyuncs.com/novel-cover-eaf226fec1f878563ce7cd3c67d65dd5.jpg","description":"南泽见不得江蓝沁好，夺走了她的第一次不说，用的还是情-趣玩具。\n他不屑碰她，却能用各种手段让她身心俱疲。\n人生有两件事逃不过，一是病一是死，而江蓝沁逃不过的始终是个南泽罢了\u2026\u2026","word_num":"54397","author":"抹茶奈奈","category_name":"","is_complete":true}
         * chapter : {"id":"3076","novel_id":"33","volume_id":"69","name":"第4章 病历","sort":"4","word_num":"1123","create_time":"1537432117","update_time":"1541734503","publish_time":"1537432117","view_count":"0"}
         * last_read_time : 2018-11-09 17:55:44
         * on_shelf : false
         */

        public NovelBean novel;
        public ChapterBean chapter;
        public String last_read_time;
        public boolean on_shelf;

        public NovelBean getNovel() {
            return novel;
        }

        public void setNovel(NovelBean novel) {
            this.novel = novel;
        }

        public ChapterBean getChapter() {
            return chapter;
        }

        public void setChapter(ChapterBean chapter) {
            this.chapter = chapter;
        }

        public String getLast_read_time() {
            return last_read_time;
        }

        public void setLast_read_time(String last_read_time) {
            this.last_read_time = last_read_time;
        }

        public boolean isOn_shelf() {
            return on_shelf;
        }

        public void setOn_shelf(boolean on_shelf) {
            this.on_shelf = on_shelf;
        }

        public static class NovelBean {
            /**
             * id : 33
             * name : 你的爱情说了谎
             * cover : http://wenxin-novel.oss-cn-hangzhou.aliyuncs.com/novel-cover-eaf226fec1f878563ce7cd3c67d65dd5.jpg
             * description : 南泽见不得江蓝沁好，夺走了她的第一次不说，用的还是情-趣玩具。
             他不屑碰她，却能用各种手段让她身心俱疲。
             人生有两件事逃不过，一是病一是死，而江蓝沁逃不过的始终是个南泽罢了……
             * word_num : 54397
             * author : 抹茶奈奈
             * category_name : 
             * is_complete : true
             */

            public String id;
            public String name;
            public String cover;
            public String description;
            public String word_num;
            public String author;
            public String category_name;
            public boolean is_complete;

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

        public static class ChapterBean {
            /**
             * id : 3076
             * novel_id : 33
             * volume_id : 69
             * name : 第4章 病历
             * sort : 4
             * word_num : 1123
             * create_time : 1537432117
             * update_time : 1541734503
             * publish_time : 1537432117
             * view_count : 0
             */

            public String id;
            public String novel_id;
            public String volume_id;
            public String name;
            public String sort;
            public String word_num;
            public String create_time;
            public String update_time;
            public String publish_time;
            public String view_count;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getNovel_id() {
                return novel_id;
            }

            public void setNovel_id(String novel_id) {
                this.novel_id = novel_id;
            }

            public String getVolume_id() {
                return volume_id;
            }

            public void setVolume_id(String volume_id) {
                this.volume_id = volume_id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getSort() {
                return sort;
            }

            public void setSort(String sort) {
                this.sort = sort;
            }

            public String getWord_num() {
                return word_num;
            }

            public void setWord_num(String word_num) {
                this.word_num = word_num;
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

            public String getPublish_time() {
                return publish_time;
            }

            public void setPublish_time(String publish_time) {
                this.publish_time = publish_time;
            }

            public String getView_count() {
                return view_count;
            }

            public void setView_count(String view_count) {
                this.view_count = view_count;
            }
        }
    }
}

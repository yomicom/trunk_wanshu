package com.wxb.wanshu.bean;

import java.util.List;

/**
 * Created by qiming on 2017/12/18.
 */

public class NovelRank extends Base {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * novel_id : 1955
         * author : 小小海洋
         * category : 穿越架空
         * is_free : 0
         * free_time : 0
         * read : 4
         * title : 随身英雄系统
         * level : 80
         * cover : http://lily.sunshe.com/c/742/1067742_issqed0gdz_o.jpg
         * summary : 典韦吕布赵子龙！千军万马之中，如入无人之境！ 一剑西来，天外飞仙！还有独孤求败，以魔剑行走天下！ 自从带着英雄系统穿越之后，陆离的生活就开始大不一样了，可是这个小娃娃你是谁？ 竟然还可以口吐三昧真火！ 你说你是那个个翻天覆地的大圣？谁信啊！ 大圣，别打脸…… …… 从此之后，历史武将，武侠人物，神话传说就这样乱入进了陆离的身边！
         * link : http://xs.yiwei.com/book?account_id=84&novel_id=1955
         * complete_status : 1
         */

        private int novel_id;
        private String author;
        private String category;
        private int is_free;
        private int free_time;
        private int read;
        private String title;
        private int level;
        private String cover;
        private String summary;
        private String link;
        private int complete_status;

        public int getNovel_id() {
            return novel_id;
        }

        public void setNovel_id(int novel_id) {
            this.novel_id = novel_id;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public int getIs_free() {
            return is_free;
        }

        public void setIs_free(int is_free) {
            this.is_free = is_free;
        }

        public int getFree_time() {
            return free_time;
        }

        public void setFree_time(int free_time) {
            this.free_time = free_time;
        }

        public int getRead() {
            return read;
        }

        public void setRead(int read) {
            this.read = read;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public String getSummary() {
            return summary;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public int getComplete_status() {
            return complete_status;
        }

        public void setComplete_status(int complete_status) {
            this.complete_status = complete_status;
        }
    }
}

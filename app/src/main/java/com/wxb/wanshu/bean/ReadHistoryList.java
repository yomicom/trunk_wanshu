package com.wxb.wanshu.bean;

import java.util.List;

/**
 * Created by qiming on 2017/12/14.
 */

public class ReadHistoryList extends Base {

    /**
     * data : [{"novel_id":1943,"title":"都市无敌仙尊","author":"挥墨客","cover":"http://lily.sunshe.com/c/002/1068002_bkcjxioui0_o.jpg","summary":"莫云，曾经仙界最强的雷云战尊，叱咤仙界，所向无敌。 未曾想，在一次修炼时被人偷袭，重生到一个胆小怕事，性格懦弱的私生子身上。 \u201c骚年，就让本尊纠正你错误的人生吧！\u201d 无敌仙尊驰骋都市，重启牛逼闪闪的人生！ 财富手中握，美人怀里揉，敌人脚下踩！ \u201c待本尊重回仙界，搅你个天翻地覆！\u201d","chapter":2,"chapter_name":"第二章 活该被雷劈","latest_chapter":10,"link":"http://xs.yiwei.com/read/index?account_id=84&novel_id=1943&chapter=2","is_collect":1,"complete_status":0,"category":"玄幻仙侠","read":1943,"update_time":"2017-10-21 10:46:12"}]
     * pager : {"page":1,"perpage":20,"numRecords":1,"numPages":1,"firstRecord":0,"lastRecord":0}
     */

    private PagerBean pager;
    private List<DataBean> data;

    public PagerBean getPager() {
        return pager;
    }

    public void setPager(PagerBean pager) {
        this.pager = pager;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class PagerBean {
        /**
         * page : 1
         * perpage : 20
         * numRecords : 1
         * numPages : 1
         * firstRecord : 0
         * lastRecord : 0
         */

        private int page;
        private int perpage;
        private int numRecords;
        private int numPages;
        private int firstRecord;
        private int lastRecord;

        public int getPage() {
            return page;
        }

        public void setPage(int page) {
            this.page = page;
        }

        public int getPerpage() {
            return perpage;
        }

        public void setPerpage(int perpage) {
            this.perpage = perpage;
        }

        public int getNumRecords() {
            return numRecords;
        }

        public void setNumRecords(int numRecords) {
            this.numRecords = numRecords;
        }

        public int getNumPages() {
            return numPages;
        }

        public void setNumPages(int numPages) {
            this.numPages = numPages;
        }

        public int getFirstRecord() {
            return firstRecord;
        }

        public void setFirstRecord(int firstRecord) {
            this.firstRecord = firstRecord;
        }

        public int getLastRecord() {
            return lastRecord;
        }

        public void setLastRecord(int lastRecord) {
            this.lastRecord = lastRecord;
        }
    }

    public static class DataBean {
        /**
         * novel_id : 1943
         * title : 都市无敌仙尊
         * author : 挥墨客
         * cover : http://lily.sunshe.com/c/002/1068002_bkcjxioui0_o.jpg
         * summary : 莫云，曾经仙界最强的雷云战尊，叱咤仙界，所向无敌。 未曾想，在一次修炼时被人偷袭，重生到一个胆小怕事，性格懦弱的私生子身上。 “骚年，就让本尊纠正你错误的人生吧！” 无敌仙尊驰骋都市，重启牛逼闪闪的人生！ 财富手中握，美人怀里揉，敌人脚下踩！ “待本尊重回仙界，搅你个天翻地覆！”
         * chapter : 2
         * chapter_name : 第二章 活该被雷劈
         * latest_chapter : 10
         * link : http://xs.yiwei.com/read/index?account_id=84&novel_id=1943&chapter=2
         * is_collect : 1
         * complete_status : 0
         * category : 玄幻仙侠
         * read : 1943
         * update_time : 2017-10-21 10:46:12
         */

        private int novel_id;
        private String title;
        private String author;
        private String cover;
        private String summary;
        private int chapter;
        private String chapter_name;
        private int latest_chapter;
        private String link;
        private int is_collect;
        private int complete_status;
        private String category;
        private int read;
        private String update_time;

        public int getNovel_id() {
            return novel_id;
        }

        public void setNovel_id(int novel_id) {
            this.novel_id = novel_id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
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

        public String getSummary() {
            return summary;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }

        public int getChapter() {
            return chapter;
        }

        public void setChapter(int chapter) {
            this.chapter = chapter;
        }

        public String getChapter_name() {
            return chapter_name;
        }

        public void setChapter_name(String chapter_name) {
            this.chapter_name = chapter_name;
        }

        public int getLatest_chapter() {
            return latest_chapter;
        }

        public void setLatest_chapter(int latest_chapter) {
            this.latest_chapter = latest_chapter;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public int getIs_collect() {
            return is_collect;
        }

        public void setIs_collect(int is_collect) {
            this.is_collect = is_collect;
        }

        public int getComplete_status() {
            return complete_status;
        }

        public void setComplete_status(int complete_status) {
            this.complete_status = complete_status;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public int getRead() {
            return read;
        }

        public void setRead(int read) {
            this.read = read;
        }

        public String getUpdate_time() {
            return update_time;
        }

        public void setUpdate_time(String update_time) {
            this.update_time = update_time;
        }
    }
}

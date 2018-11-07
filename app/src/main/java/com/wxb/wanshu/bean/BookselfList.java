package com.wxb.wanshu.bean;

import java.util.List;

/**
 * Created by qiming on 2017/12/11.
 */

public class BookselfList extends Base{

    /**
     * data : [{"novel_id":1943,"cover":"http://lily.sunshe.com/c/002/1068002_bkcjxioui0_o.jpg","title":"都市无敌仙尊","link":"http://xs.yiwei.com/read/index?account_id=84&novel_id=1943","update_time":"2017-08-18 09:59:48","is_updated":0,"is_free":0,"free_time":0,"complete_status":1},{"novel_id":922,"cover":"http://lily.sunshe.com/c/002/1068002_bkcjxioui0_o.jpg","title":"仙尊无敌","link":"http://xs.yiwei.com/read/index?account_id=84&novel_id=1943","update_time":"2017-12-10 09:59:48","is_updated":1,"is_free":0,"free_time":0,"complete_status":0}]
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
         * cover : http://lily.sunshe.com/c/002/1068002_bkcjxioui0_o.jpg
         * title : 都市无敌仙尊
         * link : http://xs.yiwei.com/read/index?account_id=84&novel_id=1943
         * update_time : 2017-08-18 09:59:48
         * is_updated : 0
         * is_free : 0
         * free_time : 0
         * complete_status : 1
         */

        private String novel_id;
        private String cover;
        private String title;
        private String link;
        private String update_time;
        private int is_updated;
        private int is_free;
        private int free_time;
        private int complete_status;

        public boolean showCheckBox = false;//显示多选框
        public boolean isSeleted = false;//被选中

        public String getNovel_id() {
            return novel_id;
        }

        public void setNovel_id(String novel_id) {
            this.novel_id = novel_id;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getUpdate_time() {
            return update_time;
        }

        public void setUpdate_time(String update_time) {
            this.update_time = update_time;
        }

        public int getIs_updated() {
            return is_updated;
        }

        public void setIs_updated(int is_updated) {
            this.is_updated = is_updated;
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

        public int getComplete_status() {
            return complete_status;
        }

        public void setComplete_status(int complete_status) {
            this.complete_status = complete_status;
        }
    }
}

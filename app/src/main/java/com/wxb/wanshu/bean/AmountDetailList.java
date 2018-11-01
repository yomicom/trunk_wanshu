package com.wxb.wanshu.bean;

import java.util.List;

/**
 * Created by qiming on 2017/12/12.
 */

public class AmountDetailList extends Base {

    /**
     * data : [{"id":"11544","fans_id":"70000179","amount":50,"type":"4","union_id":"0","memo":"签到赠送","create_time":"2017-12-01 10:48:30","type_txt":"签到","chapter_name":"","novel_name":""}]
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
         * id : 11544
         * fans_id : 70000179
         * amount : 50
         * type : 4
         * union_id : 0
         * memo : 签到赠送
         * create_time : 2017-12-01 10:48:30
         * type_txt : 签到
         * chapter_name :
         * novel_name :
         */

        private String id;
        private String fans_id;
        private int amount;
        private String type;
        private String union_id;
        private String memo;
        private String create_time;
        private String type_txt;
        private String chapter_name;
        private String novel_name;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getFans_id() {
            return fans_id;
        }

        public void setFans_id(String fans_id) {
            this.fans_id = fans_id;
        }

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUnion_id() {
            return union_id;
        }

        public void setUnion_id(String union_id) {
            this.union_id = union_id;
        }

        public String getMemo() {
            return memo;
        }

        public void setMemo(String memo) {
            this.memo = memo;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getType_txt() {
            return type_txt;
        }

        public void setType_txt(String type_txt) {
            this.type_txt = type_txt;
        }

        public String getChapter_name() {
            return chapter_name;
        }

        public void setChapter_name(String chapter_name) {
            this.chapter_name = chapter_name;
        }

        public String getNovel_name() {
            return novel_name;
        }

        public void setNovel_name(String novel_name) {
            this.novel_name = novel_name;
        }
    }
}

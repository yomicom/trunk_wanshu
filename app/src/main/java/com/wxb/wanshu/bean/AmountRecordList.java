package com.wxb.wanshu.bean;

import java.util.List;

/**
 * Created by qiming on 2017/12/14.
 */

public class AmountRecordList extends Base {

    /**
     * data : [{"id":115441,"amount":50,"type":10,"type_name":"签到赠送","memo":"签到赠送","ctime":1512114172,"chapter_name":"","title":""}]
     * pager : {"page":1,"perpage":20,"numRecords":1,"numPages":1,"firstRecord":0,"lastRecord":0}
     * type_name : [{"type":10,"value":"签到赠送"},{"type":20,"value":"在线充值"},{"type":30,"value":"打赏消费"},{"type":40,"value":"阅读消费"}]
     */

    private PagerBean pager;
    private List<DataBean> data;
    private List<TypeNameBean> type_name;

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

    public List<TypeNameBean> getType_name() {
        return type_name;
    }

    public void setType_name(List<TypeNameBean> type_name) {
        this.type_name = type_name;
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
         * id : 115441
         * amount : 50
         * type : 10
         * type_name : 签到赠送
         * memo : 签到赠送
         * ctime : 1512114172
         * chapter_name :
         * title :
         */

        private int id;
        private int amount;
        private int type;
        private String type_name;
        private String memo;
        private int ctime;
        private String chapter_name;
        private String title;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getType_name() {
            return type_name;
        }

        public void setType_name(String type_name) {
            this.type_name = type_name;
        }

        public String getMemo() {
            return memo;
        }

        public void setMemo(String memo) {
            this.memo = memo;
        }

        public int getCtime() {
            return ctime;
        }

        public void setCtime(int ctime) {
            this.ctime = ctime;
        }

        public String getChapter_name() {
            return chapter_name;
        }

        public void setChapter_name(String chapter_name) {
            this.chapter_name = chapter_name;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }

    public static class TypeNameBean {
        /**
         * type : 10
         * value : 签到赠送
         */

        private int type;
        public boolean isSelected;
        private String value;

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}

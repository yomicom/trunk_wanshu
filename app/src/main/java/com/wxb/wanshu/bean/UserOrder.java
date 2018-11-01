package com.wxb.wanshu.bean;

import java.util.List;

/**
 * 充值记录
 * Created by qiming on 2017/12/8.
 */

public class UserOrder extends Base {

    /**
     * data : [{"id":"350027","sn":"AB21512021678205","novel_id":"0","popularize_id":"3625","type":"0","union_type":"0","union_id":"0","bank":"weixin","amount":"30.00","user_id":"21","auth_accounts_id":"84","fans_id":"4755701","status":"1","serialno":null,"info":null,"create_time":"2017-11-21 16:00:02","pay_time":"2017-11-21 16:00:09","channel_checkout_status":"0","channel_checkout_id":"0","channel_checkout_time":null,"channel_checkout_rate":"90","channel_checkout_amount":"27.00","owner_checkout_status":"0","owner_checkout_id":"0","owner_checkout_time":null,"owner_checkout_rate":null,"owner_checkout_amount":null,"wxb_amount":"3.00","cash_account_appid":"20171115173837668854"},{"id":"349851","sn":"AB21320786798545","novel_id":"0","popularize_id":"3625","type":"1","union_type":"0","union_id":"0","bank":"weixin","amount":"365.00","user_id":"21","auth_accounts_id":"84","fans_id":"4755701","status":"1","serialno":null,"info":null,"create_time":"2017-11-21 10:41:18","pay_time":"2017-11-21 10:41:26","channel_checkout_status":"0","channel_checkout_id":"0","channel_checkout_time":null,"channel_checkout_rate":"90","channel_checkout_amount":"328.50","owner_checkout_status":"0","owner_checkout_id":"0","owner_checkout_time":null,"owner_checkout_rate":null,"owner_checkout_amount":null,"wxb_amount":"36.50","cash_account_appid":"20171115173837668854"},{"id":"349451","sn":"AB07360746351227","novel_id":"0","popularize_id":"0","type":"0","union_type":"1","union_id":"41","bank":"weixin","amount":"11.00","user_id":"21","auth_accounts_id":"84","fans_id":"4755701","status":"1","serialno":null,"info":null,"create_time":"2017-11-07 14:27:54","pay_time":"2017-11-07 14:28:01","channel_checkout_status":"0","channel_checkout_id":"0","channel_checkout_time":null,"channel_checkout_rate":"90","channel_checkout_amount":"9.90","owner_checkout_status":"0","owner_checkout_id":"0","owner_checkout_time":null,"owner_checkout_rate":null,"owner_checkout_amount":null,"wxb_amount":"1.10","cash_account_appid":"wxf256f2b299853154"},{"id":"349448","sn":"AB07351701741399","novel_id":"0","popularize_id":"0","type":"0","union_type":"1","union_id":"41","bank":"weixin","amount":"11.00","user_id":"21","auth_accounts_id":"84","fans_id":"4755701","status":"1","serialno":null,"info":null,"create_time":"2017-11-07 14:12:50","pay_time":"2017-11-07 14:12:57","channel_checkout_status":"0","channel_checkout_id":"0","channel_checkout_time":null,"channel_checkout_rate":"90","channel_checkout_amount":"9.90","owner_checkout_status":"0","owner_checkout_id":"0","owner_checkout_time":null,"owner_checkout_rate":null,"owner_checkout_amount":null,"wxb_amount":"1.10","cash_account_appid":"wxf256f2b299853154"}]
     * pager : {"page":1,"perpage":20,"numRecords":4,"numPages":1,"firstRecord":0,"lastRecord":3}
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
         * numRecords : 4
         * numPages : 1
         * firstRecord : 0
         * lastRecord : 3
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
         * id : 350027
         * sn : AB21512021678205
         * novel_id : 0
         * popularize_id : 3625
         * type : 0
         * union_type : 0
         * union_id : 0
         * bank : weixin
         * amount : 30.00
         * user_id : 21
         * auth_accounts_id : 84
         * fans_id : 4755701
         * status : 1
         * serialno : null
         * info : null
         * create_time : 2017-11-21 16:00:02
         * pay_time : 2017-11-21 16:00:09
         * channel_checkout_status : 0
         * channel_checkout_id : 0
         * channel_checkout_time : null
         * channel_checkout_rate : 90
         * channel_checkout_amount : 27.00
         * owner_checkout_status : 0
         * owner_checkout_id : 0
         * owner_checkout_time : null
         * owner_checkout_rate : null
         * owner_checkout_amount : null
         * wxb_amount : 3.00
         * cash_account_appid : 20171115173837668854
         */

        private String id;
        private String sn;
        private String novel_id;
        private String popularize_id;
        private String type;
        private String union_type;
        private String union_id;
        private String bank;
        private String amount;
        private String user_id;
        private String auth_accounts_id;
        private String fans_id;
        private String status;
        private Object serialno;
        private Object info;
        private String create_time;
        private String pay_time;
        private String channel_checkout_status;
        private String channel_checkout_id;
        private Object channel_checkout_time;
        private String channel_checkout_rate;
        private String channel_checkout_amount;
        private String owner_checkout_status;
        private String owner_checkout_id;
        private Object owner_checkout_time;
        private Object owner_checkout_rate;
        private Object owner_checkout_amount;
        private String money;
        private String cash_account_appid;

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getSn() {
            return sn;
        }

        public void setSn(String sn) {
            this.sn = sn;
        }

        public String getNovel_id() {
            return novel_id;
        }

        public void setNovel_id(String novel_id) {
            this.novel_id = novel_id;
        }

        public String getPopularize_id() {
            return popularize_id;
        }

        public void setPopularize_id(String popularize_id) {
            this.popularize_id = popularize_id;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUnion_type() {
            return union_type;
        }

        public void setUnion_type(String union_type) {
            this.union_type = union_type;
        }

        public String getUnion_id() {
            return union_id;
        }

        public void setUnion_id(String union_id) {
            this.union_id = union_id;
        }

        public String getBank() {
            return bank;
        }

        public void setBank(String bank) {
            this.bank = bank;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getAuth_accounts_id() {
            return auth_accounts_id;
        }

        public void setAuth_accounts_id(String auth_accounts_id) {
            this.auth_accounts_id = auth_accounts_id;
        }

        public String getFans_id() {
            return fans_id;
        }

        public void setFans_id(String fans_id) {
            this.fans_id = fans_id;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public Object getSerialno() {
            return serialno;
        }

        public void setSerialno(Object serialno) {
            this.serialno = serialno;
        }

        public Object getInfo() {
            return info;
        }

        public void setInfo(Object info) {
            this.info = info;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getPay_time() {
            return pay_time;
        }

        public void setPay_time(String pay_time) {
            this.pay_time = pay_time;
        }

        public String getChannel_checkout_status() {
            return channel_checkout_status;
        }

        public void setChannel_checkout_status(String channel_checkout_status) {
            this.channel_checkout_status = channel_checkout_status;
        }

        public String getChannel_checkout_id() {
            return channel_checkout_id;
        }

        public void setChannel_checkout_id(String channel_checkout_id) {
            this.channel_checkout_id = channel_checkout_id;
        }

        public Object getChannel_checkout_time() {
            return channel_checkout_time;
        }

        public void setChannel_checkout_time(Object channel_checkout_time) {
            this.channel_checkout_time = channel_checkout_time;
        }

        public String getChannel_checkout_rate() {
            return channel_checkout_rate;
        }

        public void setChannel_checkout_rate(String channel_checkout_rate) {
            this.channel_checkout_rate = channel_checkout_rate;
        }

        public String getChannel_checkout_amount() {
            return channel_checkout_amount;
        }

        public void setChannel_checkout_amount(String channel_checkout_amount) {
            this.channel_checkout_amount = channel_checkout_amount;
        }

        public String getOwner_checkout_status() {
            return owner_checkout_status;
        }

        public void setOwner_checkout_status(String owner_checkout_status) {
            this.owner_checkout_status = owner_checkout_status;
        }

        public String getOwner_checkout_id() {
            return owner_checkout_id;
        }

        public void setOwner_checkout_id(String owner_checkout_id) {
            this.owner_checkout_id = owner_checkout_id;
        }

        public Object getOwner_checkout_time() {
            return owner_checkout_time;
        }

        public void setOwner_checkout_time(Object owner_checkout_time) {
            this.owner_checkout_time = owner_checkout_time;
        }

        public Object getOwner_checkout_rate() {
            return owner_checkout_rate;
        }

        public void setOwner_checkout_rate(Object owner_checkout_rate) {
            this.owner_checkout_rate = owner_checkout_rate;
        }

        public Object getOwner_checkout_amount() {
            return owner_checkout_amount;
        }

        public void setOwner_checkout_amount(Object owner_checkout_amount) {
            this.owner_checkout_amount = owner_checkout_amount;
        }

        public String getCash_account_appid() {
            return cash_account_appid;
        }

        public void setCash_account_appid(String cash_account_appid) {
            this.cash_account_appid = cash_account_appid;
        }
    }
}

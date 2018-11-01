package com.wxb.wanshu.bean;

import java.util.List;

/**
 * Created by qiming on 2017/12/14.
 */

public class NotificationList extends Base {

    /**
     * data : [{"id":"1491299","user_id":"23","smid":"0","type":"2","title":"定时群发成功","content":"您的公众号(精选原创集)定时群发已成功","is_read":"1","to_client":"0","to_module":"0","ctime":"1501324139"},{"id":"1491299","user_id":"23","smid":"0","type":"2","title":"定时群发成功","content":"您的公众号(精选原创集)定时群发已成功","is_read":"1","to_client":"0","to_module":"0","ctime":"1501324139"}]
     * totalCount : 2
     */

    private int totalCount;
    private List<DataBean> data;

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 1491299
         * user_id : 23
         * smid : 0
         * type : 2
         * title : 定时群发成功
         * content : 您的公众号(精选原创集)定时群发已成功
         * is_read : 1
         * to_client : 0
         * to_module : 0
         * ctime : 1501324139
         */

        private String id;
        private String user_id;
        private String smid;
        private String type;
        private String title;
        private String content;
        private String is_read;
        private String to_client;
        private String to_module;
        private String ctime;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getSmid() {
            return smid;
        }

        public void setSmid(String smid) {
            this.smid = smid;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getIs_read() {
            return is_read;
        }

        public void setIs_read(String is_read) {
            this.is_read = is_read;
        }

        public String getTo_client() {
            return to_client;
        }

        public void setTo_client(String to_client) {
            this.to_client = to_client;
        }

        public String getTo_module() {
            return to_module;
        }

        public void setTo_module(String to_module) {
            this.to_module = to_module;
        }

        public String getCtime() {
            return ctime;
        }

        public void setCtime(String ctime) {
            this.ctime = ctime;
        }
    }
}

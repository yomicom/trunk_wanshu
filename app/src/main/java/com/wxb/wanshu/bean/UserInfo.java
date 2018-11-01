package com.wxb.wanshu.bean;

/**
 * Created by qiming on 2017/12/21.
 */

public class UserInfo extends Base {

    /**
     * data : {"amount":1000,"avatar":"http://wx.qlogo.cn/mmopen/ajNVdqHZLLC94vQhTEmHvHZDsLt5rKsYjT3Yic6C7EIqLhlujWnqzQF5ay3BPsVUxcGB08cjHRtKMLqlibtx1xrQ/0","id":84,"is_signin":0,"is_vip":0,"vip_end_time":0,"nickname":"Eric"}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * amount : 1000
         * avatar : http://wx.qlogo.cn/mmopen/ajNVdqHZLLC94vQhTEmHvHZDsLt5rKsYjT3Yic6C7EIqLhlujWnqzQF5ay3BPsVUxcGB08cjHRtKMLqlibtx1xrQ/0
         * id : 84
         * is_signin : 0
         * is_vip : 0
         * vip_end_time : 0
         * nickname : Eric
         */

        private int amount;
        private String avatar;
        private int id;
        private int is_signin;
        private int is_vip;
        private int vip_end_time;
        private String nickname;

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getIs_signin() {
            return is_signin;
        }

        public void setIs_signin(int is_signin) {
            this.is_signin = is_signin;
        }

        public int getIs_vip() {
            return is_vip;
        }

        public void setIs_vip(int is_vip) {
            this.is_vip = is_vip;
        }

        public int getVip_end_time() {
            return vip_end_time;
        }

        public void setVip_end_time(int vip_end_time) {
            this.vip_end_time = vip_end_time;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }
    }
}

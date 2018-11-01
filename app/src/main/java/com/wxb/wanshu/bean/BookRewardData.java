package com.wxb.wanshu.bean;

import java.util.List;

/**
 * Created by qiming on 2017/12/1.
 */

public class BookRewardData extends Base {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * nick_name : 竺颖
         * avatar : http://wx.qlogo.cn/mmopen/PiajxSqBRaEJ9iaPmwGFgTaibyH18qkasYgkYFd1REvNCL0iclwB2sVibOVLZIyZsh48TYUCDJqQbLp8iaFAAFkxnoUg/0
         * gift : kiss
         * gift_num : 3
         * amount : 300
         * ctime : 1512113172
         */

        private String nick_name;
        private String avatar;
        private String gift;
        private String icon;
        private int gift_num;
        private int amount;
        private int ctime;

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getNick_name() {
            return nick_name;
        }

        public void setNick_name(String nick_name) {
            this.nick_name = nick_name;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getGift() {
            return gift;
        }

        public void setGift(String gift) {
            this.gift = gift;
        }

        public int getGift_num() {
            return gift_num;
        }

        public void setGift_num(int gift_num) {
            this.gift_num = gift_num;
        }

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        public int getCtime() {
            return ctime;
        }

        public void setCtime(int ctime) {
            this.ctime = ctime;
        }
    }
}

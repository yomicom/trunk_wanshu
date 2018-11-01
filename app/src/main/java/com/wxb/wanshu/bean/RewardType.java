package com.wxb.wanshu.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by qiming on 2017/12/6.
 */

public class RewardType extends Base {

    public List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Serializable{
        /**
         * type : handclap
         * amount : 100
         * icon : http://7xkq88.com1.z0.glb.clouddn.com/23-advert-201712-06-1512548187862.png
         */

        private String type;
        private int amount;
        private String icon;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }
    }
}

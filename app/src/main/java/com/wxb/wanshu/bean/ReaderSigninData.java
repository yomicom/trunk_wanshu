package com.wxb.wanshu.bean;

/**
 * Created by qiming on 2017/12/13.
 */

public class ReaderSigninData extends Base {

    /**
     * data : {"is_sign":0,"give_amount":50}
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
         * is_sign : 0
         * give_amount : 50
         */

        private int is_sign;
        private int give_amount;

        public int getIs_sign() {
            return is_sign;
        }

        public void setIs_sign(int is_sign) {
            this.is_sign = is_sign;
        }

        public int getGive_amount() {
            return give_amount;
        }

        public void setGive_amount(int give_amount) {
            this.give_amount = give_amount;
        }
    }
}

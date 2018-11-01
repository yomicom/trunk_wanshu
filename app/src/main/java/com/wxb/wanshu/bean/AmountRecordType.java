package com.wxb.wanshu.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by qiming on 2017/12/14.
 */

public class AmountRecordType extends Base {

    /**
     * type_name : {"10":"签到赠送","20":"在线充值","30":"打赏消费","40":"阅读消费"}
     */

    private TypeNameBean type_name;

    public TypeNameBean getType_name() {
        return type_name;
    }

    public void setType_name(TypeNameBean type_name) {
        this.type_name = type_name;
    }

    public static class TypeNameBean {
        /**
         * 10 : 签到赠送
         * 20 : 在线充值
         * 30 : 打赏消费
         * 40 : 阅读消费
         */

        @SerializedName("10")
        private String _$10;
        @SerializedName("20")
        private String _$20;
        @SerializedName("30")
        private String _$30;
        @SerializedName("40")
        private String _$40;

        public String get_$10() {
            return _$10;
        }

        public void set_$10(String _$10) {
            this._$10 = _$10;
        }

        public String get_$20() {
            return _$20;
        }

        public void set_$20(String _$20) {
            this._$20 = _$20;
        }

        public String get_$30() {
            return _$30;
        }

        public void set_$30(String _$30) {
            this._$30 = _$30;
        }

        public String get_$40() {
            return _$40;
        }

        public void set_$40(String _$40) {
            this._$40 = _$40;
        }
    }
}

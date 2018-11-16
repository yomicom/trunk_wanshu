package com.wxb.wanshu.bean;

public class AppVersion extends Base {

    /**
     * data : {"version":"1.0","version_code":"1","package_path":"","msg":""}
     */

    public DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * version : 1.0
         * version_code : 1
         * package_path :
         * msg :
         */

        public String version;
        public int version_code;
        public String package_path;
        public String msg;

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public int getVersion_code() {
            return version_code;
        }

        public void setVersion_code(int version_code) {
            this.version_code = version_code;
        }

        public String getPackage_path() {
            return package_path;
        }

        public void setPackage_path(String package_path) {
            this.package_path = package_path;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }
    }
}

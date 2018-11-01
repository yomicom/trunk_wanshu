package com.wxb.wanshu.bean;

/**
 * Created by qiming on 2017/12/19.
 */

public class UploadPictureBean extends Base {

    /**
     * data : {"message":"[OK]","url":"http://7xkq88.com1.z0.glb.clouddn.com/23-content-201712-18-1513580286506.jpg"}
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
         * message : [OK]
         * url : http://7xkq88.com1.z0.glb.clouddn.com/23-content-201712-18-1513580286506.jpg
         */

        private String message;
        private String url;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}

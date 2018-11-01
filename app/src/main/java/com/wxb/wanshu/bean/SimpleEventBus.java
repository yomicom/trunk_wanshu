package com.wxb.wanshu.bean;

/**
 * Created by qiming on 2017/12/21.
 */

public class SimpleEventBus {
    boolean success;
    int status;

    public SimpleEventBus(boolean success, int status) {
        this.success = success;
        this.status = status;
    }

    public SimpleEventBus(int status) {
        this.status = status;
    }

    public SimpleEventBus(boolean success) {
        this.success = success;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}

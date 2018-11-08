package com.wxb.wanshu.bean;

import java.util.List;

/**
 * Created by qiming on 2017/12/11.
 */

public class BookselfList extends Base{

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean extends Book{
        public boolean isSeleted;
        public boolean showCheckBox;
    }
}

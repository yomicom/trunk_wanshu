package com.wxb.wanshu.base;

/**
 * Created by qiming on 2017/11/23.
 */

public class BaseContract {

    public interface BasePresenter<T> {

        void attachView(T view);

        void detachView();
    }

    public interface BaseView {

        void showError();

        void complete();

    }
}

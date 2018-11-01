package com.wxb.wanshu.ui.contract;

import com.wxb.wanshu.base.BaseContract;
import com.wxb.wanshu.bean.Base;
import com.wxb.wanshu.bean.UploadPictureBean;

import java.io.File;

import okhttp3.RequestBody;

/**
 * Created by qiming on 2017/11/30.
 */

public interface CommentContract {

    interface View extends BaseContract.BaseView {
        void showUploadSinglePicture(UploadPictureBean data);

        void getCommentResult(Base result);
    }

    interface Presenter<T> extends BaseContract.BasePresenter<T> {
        void getUploadSinglePicture(File file);

        void sendComment(String data);
    }
}

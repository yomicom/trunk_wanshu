package com.wxb.wanshu.ui.contract;

import com.wxb.wanshu.base.BaseContract;
import com.wxb.wanshu.bean.Base;
import com.wxb.wanshu.bean.BookDetails;
import com.wxb.wanshu.bean.BookRewardData;
import com.wxb.wanshu.bean.RewardType;

/**
 * Created by qiming on 2017/12/1.
 */

public interface BookDetailsContract {
    interface View extends BaseContract.BaseView {
        void showBookDetails(BookDetails data);

        void showBookReward(BookRewardData data);

        void showRewardType(RewardType data);

        void addBookResult(Base result);

//         void getRewardGiftResult(Base result);
    }

    interface Presenter<T> extends BaseContract.BasePresenter<T> {
        void getBookDetails(String novel_id, int client_id, int user_id);

        void getBookReward(String novel_id, int page);

        void getRewardType();

        void addBookShelf(String novel_id);

//         void rewardGift(String type, int number, int novel_id, String chapter_id);
    }
}

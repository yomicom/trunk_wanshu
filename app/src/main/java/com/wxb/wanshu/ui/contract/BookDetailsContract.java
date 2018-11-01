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
     interface View extends BaseContract.BaseView{
         void showBookDetails(BookDetails.DataBean data);

         void showBookReward(BookRewardData data);

         void showRewardType(RewardType data);

         void addBookResult(Base result);

//         void getRewardGiftResult(Base result);
     }

     interface Presenter<T> extends BaseContract.BasePresenter<T>{
         void getBookDetails(int novel_id);

         void getBookReward(int novel_id,int page);

         void getRewardType();

         void addBookShelf(int novel_id);

//         void rewardGift(String type, int number, int novel_id, String chapter_id);
     }
}

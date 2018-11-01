package com.wxb.wanshu.component;

import com.wxb.wanshu.ui.activity.CommentActivity;
import com.wxb.wanshu.ui.activity.ListActivity.AmountRecordActivity;
import com.wxb.wanshu.ui.activity.ListActivity.MyNotificationActivity;
import com.wxb.wanshu.ui.activity.ListActivity.OrderListActivity;
import com.wxb.wanshu.ui.activity.ListActivity.ReadHistoryActivity;
import com.wxb.wanshu.ui.activity.MeActivity;
import com.wxb.wanshu.ui.activity.RechargeAmountActivity;

import dagger.Component;

/**
 * Created by qiming on 2017/12/8.
 */

@Component(dependencies = AppComponent.class)
public interface AccountComponent {
    OrderListActivity inject(OrderListActivity activity);

    MyNotificationActivity inject(MyNotificationActivity activity);

    AmountRecordActivity inject(AmountRecordActivity activity);

    ReadHistoryActivity inject(ReadHistoryActivity activity);

    RechargeAmountActivity inject(RechargeAmountActivity activity);

    CommentActivity inject(CommentActivity activity);

    MeActivity inject(MeActivity activity);
}

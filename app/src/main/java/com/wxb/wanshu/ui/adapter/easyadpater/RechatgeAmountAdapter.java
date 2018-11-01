/**
 * Copyright 2016 JustWayward Team
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.wxb.wanshu.ui.adapter.easyadpater;

import android.content.Context;
import android.text.Html;

import com.wxb.wanshu.R;
import com.wxb.wanshu.bean.ReadTheme;
import com.wxb.wanshu.bean.RechargeAmount;
import com.wxb.wanshu.manager.ThemeManager;
import com.wxb.wanshu.ui.adapter.base.EasyLVAdapter;
import com.wxb.wanshu.ui.adapter.base.EasyLVHolder;
import com.wxb.wanshu.utils.LogUtils;

import java.util.List;

import static com.alipay.b.a.a.b.b.t;


/**
 * @author yuyh.
 * @date 2016/9/23.
 */
public class RechatgeAmountAdapter extends EasyLVAdapter<RechargeAmount.DataBean> {

    public RechatgeAmountAdapter(Context context, List<RechargeAmount.DataBean> list) {
        super(context, list, R.layout.item_recharge_amount);
    }

    @Override
    public void convert(EasyLVHolder holder, int position, RechargeAmount.DataBean data) {
        holder.setText(R.id.tv_money, data.getAmount() + "元");
        if (data.getGive() == 0) {
            holder.setText(R.id.tv_amount, data.getTitle() + "书币");
        } else {
            holder.setText(R.id.tv_amount, Html.fromHtml((data.getAmount() * data.getRecharge_rate()) + "<font color=#E8554D>+" + data.getGive() + "</font>书币"));
        }
    }

}

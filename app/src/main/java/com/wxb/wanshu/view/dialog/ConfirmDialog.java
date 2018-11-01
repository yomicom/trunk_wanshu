package com.wxb.wanshu.view.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import com.wxb.wanshu.R;

import java.io.IOException;

/**
 * Created by qiming on 2017/12/26.
 */

public class ConfirmDialog {

    public static void showNotice(Context context, String title, String message, final Callback callback) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View noticeMessage = View.inflate(context,R.layout.dialog_notice, null);
        TextView msgView = (TextView) noticeMessage.findViewById(R.id.notice_msg);
        msgView.setText(Html.fromHtml(message));
        builder.setTitle(title);
        builder.setView(noticeMessage).setPositiveButton("登 录", null);
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                try {
                    callback.exec();
                    dialog.dismiss();
                } catch (Exception e) {

                }
            }
        });

        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public static interface Callback {
        void exec() throws IOException;
    }

    public static void showNotice(Context context, String title, String message, String sureTip, String cancleTip,
                                  final SureCallback sureCallback, final CancleCallback cancleCallback) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View noticeMessage = View.inflate(context, R.layout.dialog_notice, null);
        TextView msgView = (TextView) noticeMessage.findViewById(R.id.notice_msg);
        msgView.setText(Html.fromHtml(message));
        builder.setTitle(title);
        builder.setView(noticeMessage).setPositiveButton("登 录", null);
        builder.setPositiveButton(sureTip, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                try {
                    sureCallback.exec();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                dialog.dismiss();
            }
        });
        builder.setNegativeButton(cancleTip, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                try {
                    cancleCallback.exec();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                dialog.dismiss();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.setCancelable(false);
        alertDialog.show();
    }

    public static interface SureCallback {
        void exec() throws Exception;
    }

    public static interface CancleCallback {
        void exec() throws Exception;
    }
}

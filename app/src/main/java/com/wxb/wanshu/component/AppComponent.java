package com.wxb.wanshu.component;

import android.content.Context;

import com.wxb.wanshu.api.BookApi;
import com.wxb.wanshu.module.AppModule;
import com.wxb.wanshu.module.BookApiModule;

import dagger.Component;

/**
 * Created by qiming on 2017/11/24.
 */

@Component(modules = {AppModule.class, BookApiModule.class})
public interface AppComponent {

    Context getContext();

    BookApi getReaderApi();

}
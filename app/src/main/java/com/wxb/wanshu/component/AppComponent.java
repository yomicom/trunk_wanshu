package com.wxb.wanshu.component;

import android.content.Context;

import com.wxb.wanshu.api.Api;
import com.wxb.wanshu.module.ApiModule;
import com.wxb.wanshu.module.AppModule;

import dagger.Component;

/**
 * Created by qiming on 2017/11/24.
 */

@Component(modules = {AppModule.class, ApiModule.class})
public interface AppComponent {

    Context getContext();

    Api getReaderApi();

}
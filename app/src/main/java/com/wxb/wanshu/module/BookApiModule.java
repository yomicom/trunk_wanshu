package com.wxb.wanshu.module;

import com.wxb.wanshu.api.BookApi;
import com.wxb.wanshu.api.HeaderInterceptor;
import com.wxb.wanshu.api.LoggingInterceptor;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;

/**
 * Created by qiming on 2017/11/24.
 */

@Module
public class BookApiModule {

    @Provides
    public OkHttpClient provideOkHttpClient() {

        LoggingInterceptor logging = new LoggingInterceptor(new LoggingInterceptor.Logger() {
            @Override
            public void log(String message) {

            }
        });
        logging.setLevel(LoggingInterceptor.Level.BODY);

        OkHttpClient.Builder builder = new OkHttpClient.Builder().connectTimeout(10, TimeUnit.SECONDS)
                .connectTimeout(20 * 1000, TimeUnit.MILLISECONDS)
                .readTimeout(20 * 1000, TimeUnit.MILLISECONDS)
                .retryOnConnectionFailure(true) // 失败重发
                .addInterceptor(new HeaderInterceptor())
                .addInterceptor(logging);
        return builder.build();
    }

    @Provides
    protected BookApi provideBookService(OkHttpClient okHttpClient) {
        return BookApi.getInstance(okHttpClient);
    }
}

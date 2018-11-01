package com.wxb.wanshu.utils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by qiming on 2017/11/29.
 */

public class OkhttpUtils {
    private static OkHttpClient mOkHttpClient = new OkHttpClient();

    static {
//        mOkHttpClient.setConnectTimeout(30, TimeUnit.SECONDS);
//        mOkHttpClient.setReadTimeout(30, TimeUnit.SECONDS);
//        mOkHttpClient.setWriteTimeout(30, TimeUnit.SECONDS);

        //javax.net.ssl.SSLHandshakeException: javax.net.ssl.SSLProtocolException: SSL handshake
//        try {//方法一：
//            mOkHttpClient.setSslSocketFactory(new TLSSocketFactory());
//        } catch (KeyManagementException e) {
//            e.printStackTrace();
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        }
//        try {//方法二：
//            // 自定义一个信任所有证书的TrustManager，添加SSLSocketFactory的时候要用到
//            final X509TrustManager trustAllCert =
//                    new X509TrustManager() {
//                        @Override
//                        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
//                        }
//
//                        @Override
//                        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
//                        }
//
//                        @Override
//                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
//                            return new java.security.cert.X509Certificate[]{};
//                        }
//                    };
//            final SSLSocketFactory sslSocketFactory = new SSLSocketFactoryCompat(trustAllCert);
//            mOkHttpClient.setSslSocketFactory(sslSocketFactory);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
    }

    public static Response execute(Request request) throws IOException {
        return mOkHttpClient.newCall(request).execute();
    }

    /**
     * 开启异步线程访问网络
     *
     * @param request
     * @param responseCallback
     */
    public static void enqueue(Request request, Callback responseCallback) {
        mOkHttpClient.newCall(request).enqueue(responseCallback);
    }


    /**
     * 开启异步线程访问网络, 且不在意返回结果（实现空callback）
     *
     * @param request
     */
    public static void enqueue(Request request) {
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

            }
        });
    }
}

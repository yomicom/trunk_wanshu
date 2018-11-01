package com.wxb.wanshu.api;

import android.content.pm.PackageManager;

import com.wxb.wanshu.utils.OkhttpUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

import okhttp3.Headers;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by qiming on 2017/11/29.
 */

public class WanshuComponent {

    public static final String apiUri = "http://api-dev.wanshu.com";

    private static volatile WanshuComponent instance;
    private static Headers headers;

    static {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
        map.put("Pragma", "no-cache");
        map.put("X-Requested-With", "XMLHttpRequest");
        headers = Headers.of(map);
    }

    public WanshuComponent() {
    }

    public static WanshuComponent getInstance() {
        if (instance == null) {
            Class var0 = WanshuComponent.class;
            synchronized (WanshuComponent.class) {
                if (instance == null) {
                    instance = new WanshuComponent();
                }
            }
        }
        return instance;
    }

    /**
     * 同步请求
     *
     * @param requestUrl
     * @return
     * @throws IOException
     * @throws NullPointerException
     */
    public static Response request(String requestUrl) throws IOException, NullPointerException {
        Request request = buildRequest(requestUrl);
        Response response = OkhttpUtils.execute(request);
        if (!response.isSuccessful()) {
            throw new IOException("Unexpected code " + response);
        }
        return response;
    }

    /**
     * 同步请求
     *
     * @param requestUrl
     * @param postData
     * @param fileData
     * @return
     */
    public static Response request(String requestUrl, HashMap<String, String> postData, HashMap<String, String> fileData) throws IOException, NullPointerException {
        Request request = buildRequest(requestUrl);
        Response response = OkhttpUtils.execute(request);
        if (!response.isSuccessful()) {
            throw new IOException("Unexpected code " + response);
        }
        return response;
    }

    /**
     * 构建请求
     *
     * @param requestUrl
     * @return
     */
    private static Request buildRequest(String requestUrl) {
        Request.Builder rBuilder = new Request.Builder().url(requestUrl).headers(headers);
        return rBuilder.build();
    }

    public JSONObject getData(String url) throws Exception {
        Response response = request(apiUri + url);
        String res = response.body().string();
        JSONObject jsonObject = new JSONObject(res);
        return jsonObject;
    }


    /**
     * 上传封面图片
     *
     * @return
     * @throws IOException
     * @throws JSONException
     */
    public JSONObject uploadPic(String filePath) throws Exception {
        String requestUrl = apiUri + "/index/upload";
        HashMap<String, String> map = new HashMap<String, String>();
        HashMap<String, String> filemap = new HashMap<String, String>();
        filemap.put("files", filePath);
        Response response = request(requestUrl, map, filemap);
        return new JSONObject(response.body().string());
    }
}

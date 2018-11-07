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
package com.wxb.wanshu.api;

import com.wxb.wanshu.base.Constant;
import com.wxb.wanshu.utils.MD5;
import com.wxb.wanshu.utils.Utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Retrofit2 Header拦截器。用于保存和设置Cookies
 *
 * @author yuyh.
 * @date 16/8/6.
 */
public final class CustomSignInterceptor extends BaseDynamicInterceptor<CustomSignInterceptor> {

    @Override
    public TreeMap<String, String> dynamic(TreeMap<String, String> dynamicMap) {
        //dynamicMap:是原有的全局参数+局部参数
        dynamicMap.put("app_id", Constant.APP_ID);
        dynamicMap.put("timestamp", String.valueOf(System.currentTimeMillis() / 1000));
        if (isAccessToken()) {//是否添加token
            dynamicMap.put("token", "");
        }
        //device_id：设备唯一标示
        //device_type：设备类型，1android，2ios
        //version：app版本
        // os：操作系统信息
        dynamicMap.put("device_id", Utils.getUUID());
        dynamicMap.put("device_type", "1");
        dynamicMap.put("version", Utils.getVersionName());
        dynamicMap.put("os", Utils.getOsMessage());

        if (isSign()) {//是否签名,因为你的字段key可能不是sign，这种动态的自己处理
            dynamicMap.put("sign", sign(dynamicMap));
        }
        //HttpLog.i("dynamicMap:" + dynamicMap.toString());
        return dynamicMap;//dynamicMap:是原有的全局参数+局部参数+新增的动态参数
    }

    //签名规则：url+参数的拼装+secret
    private String sign(TreeMap<String, String> dynamicMap) {
        StringBuilder sb = new StringBuilder();
//        String url = getHttpUrl().url().toString();
//        sb.append(url);
        List<Map.Entry<String, String>> infoIds = new ArrayList<Map.Entry<String, String>>(dynamicMap.entrySet());
        Collections.sort(infoIds, new Comparator<Map.Entry<String, String>>() {
            public int compare(Map.Entry<String, String> o1, Map.Entry<String, String> o2) {
                return (o1.getKey()).compareTo(o2.getKey());
            }
        });

        for (Map.Entry<String, String> entry : dynamicMap.entrySet()) {
            try {
                sb.append(URLEncoder.encode(entry.getKey(), "utf8")).append("=").append(URLEncoder.encode(entry.getValue(), "utf8")).append("&");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }

        String str = sb.toString();
        if (str.length() > 0) {
            str = str.substring(0, str.length() - 1);
        }
        return MD5.encode(str + Constant.APP_SECRET);
    }
}

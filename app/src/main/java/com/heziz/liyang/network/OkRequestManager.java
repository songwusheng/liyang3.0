package com.heziz.liyang.network;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;
import java.util.Set;

import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by sws on 2019-04-25.
 * from:
 * describe:
 */

public class OkRequestManager {
    private static OkRequestManager mOkGoClient;
    public static synchronized OkRequestManager getInstance() {
        if (null == mOkGoClient) {
            // 单例,持有应用级别上下文，可以防止持有activity的上下文导致内存泄露
            mOkGoClient = new OkRequestManager();
        }
        return mOkGoClient;
    }
    public void getData(String url,Map<String,String> params, Callback callback){
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
//                .post(requestBody)
                .get()
                .build();
        okHttpClient.newCall(request).enqueue(callback);
    }
    public void postData(String url,Map<String,String> params, Callback callback){
        JSONObject object=new JSONObject();
        JSONObject object1=new JSONObject();

        try {
            object.put("entity",object1);
            // 获取所有键值对对象的集合
            Set<Map.Entry<String, String>> set = params.entrySet();
            // 遍历键值对对象的集合，得到每一个键值对对象
            for (Map.Entry<String, String> me : set) {
                // 根据键值对对象获取键和值
                String key = me.getKey();
                String value = me.getValue();
                object1.put(key,value);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody requestBody = RequestBody.create(JSON,object.toString());
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
//                .get()
                .build();
        okHttpClient.newCall(request).enqueue(callback);
    }
}

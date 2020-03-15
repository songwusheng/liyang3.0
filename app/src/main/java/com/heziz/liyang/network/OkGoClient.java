package com.heziz.liyang.network;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;
import java.util.Set;

@SuppressWarnings("unchecked")
/**
 * Created by hasee on 2017/home_bg_icon3/20.
 */

public class OkGoClient {
    private Context mContext;
    private static OkGoClient mOkGoClient;

    private OkGoClient(){

    }
    public static synchronized OkGoClient getInstance() {
        if (null == mOkGoClient) {
            // 单例,持有应用级别上下文，可以防止持有activity的上下文导致内存泄露
            mOkGoClient = new OkGoClient();
        }
        return mOkGoClient;
    }
    public void getJsonData(String url, Map<String, String> params, JsonCallBack callBack) {
        OkGo.getInstance()
                .setRetryCount(1)
                .get(url)
                .params(params)
                .execute(callBack);
    }
    public void getJsonData1(String url, Map<String, String> params, JsonCallBack1 callBack) {
        OkGo.getInstance()
                .setRetryCount(1)
                .get(url)
                .params(params)
                .execute(callBack);
    }

    public void getJsonData0(String url, Map<String, String> params, JsonCallBack2 callBack) {
        OkGo.getInstance()
                .setRetryCount(1)
                .get(url)
                .params(params)
                .execute(callBack);
    }
    public void getJsonData2(String url, Map<String, String> params, JsonCallBack0 callBack) {
        OkGo.getInstance()
                .setRetryCount(1)
                .get(url)
                .params(params)
                .execute(callBack);
    }
    public void postJsonData0(String url, Map<String, String> params, JsonCallBack1 callBack) {
        JSONObject object=new JSONObject();
//        JSONObject object1=new JSONObject();

        try {
//            object.put("entity",object1);
            // 获取所有键值对对象的集合
            Set<Map.Entry<String, String>> set = params.entrySet();
            // 遍历键值对对象的集合，得到每一个键值对对象
            for (Map.Entry<String, String> me : set) {
                // 根据键值对对象获取键和值
                String key = me.getKey();
                String value = me.getValue();
                object.put(key,value);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.w("main","请求参数："+object.toString());
        OkGo.getInstance()
                .setRetryCount(1)
                .post(url)
                .upJson(object.toString().replace("\\",""))
//                .params(map)
                .execute(callBack);
    }
    public void postJsonData(String url, Map<String, String> params, JsonCallBack1 callBack) {
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
        Log.w("main","请求参数："+object.toString());
        OkGo.getInstance()
                .setRetryCount(1)
                .post(url)
                .upJson(object.toString().replace("\\",""))
//                .params(map)
                .execute(callBack);
    }
    public void postJsonData1(String url, Map<String, String> params,String keyStr, JsonCallBack1 callBack) {
        JSONObject object=new JSONObject();
        JSONObject object1=new JSONObject();
        JSONObject object2=new JSONObject();

        try {
            object1.put(keyStr,object2);
            object.put("entity",object1);
            // 获取所有键值对对象的集合
            Set<Map.Entry<String, String>> set = params.entrySet();
            // 遍历键值对对象的集合，得到每一个键值对对象
            for (Map.Entry<String, String> me : set) {
                // 根据键值对对象获取键和值
                String key = me.getKey();
                String value = me.getValue();
                object2.put(key,value);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.w("main","请求参数："+object.toString());
        OkGo.getInstance()
                .setRetryCount(1)
                .post(url)
                .upJson(object.toString().replace("\\",""))
//                .params(map)
                .execute(callBack);
    }

    public void postJsonData2(String url, Map<String, String> params, Map<String, String> params1, JsonCallBack1 callBack) {
        JSONObject object=new JSONObject();
        JSONObject object1=new JSONObject();
        JSONObject object2=new JSONObject();

        try {
            object.put("entity",object1);
            object.put("paging",object2);
            // 获取所有键值对对象的集合
            Set<Map.Entry<String, String>> set = params.entrySet();
            Set<Map.Entry<String, String>> set1 = params1.entrySet();
            // 遍历键值对对象的集合，得到每一个键值对对象
            for (Map.Entry<String, String> me : set) {
                // 根据键值对对象获取键和值
                String key = me.getKey();
                String value = me.getValue();
                object1.put(key,value);
            }
            for (Map.Entry<String, String> me : set1) {
                // 根据键值对对象获取键和值
                String key = me.getKey();
                String value = me.getValue();
                object2.put(key,value);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.w("main","请求参数："+object.toString());
        OkGo.getInstance()
                .setRetryCount(1)
                .post(url)
                .upJson(object.toString().replace("\\",""))
//                .params(map)
                .execute(callBack);
    }

    public void postJsonData3(String url, Map<String, String> params, JsonCallBack1 callBack) {
        JSONObject object=new JSONObject();
        JSONObject object1=new JSONObject();
//        JSONObject object2=new JSONObject();

        try {
            object.put("entity",object1);
            object.put("groupColumn","project_id");
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
        Log.w("main","请求参数："+object.toString());
        OkGo.getInstance()
                .setRetryCount(1)
                .post(url)
                .upJson(object.toString().replace("\\",""))
//                .params(map)
                .execute(callBack);
    }


    /*----我的 五达标一公示-----*/
    public void postJsonData4(String url, Map<String, String> params, Map<String, String> params1,String keyName,String valueName, JsonCallBack1 callBack) {
        JSONObject object=new JSONObject();
        JSONObject object1=new JSONObject();
        JSONObject object2=new JSONObject();

        try {
            object.put("entity",object1);
            object.put("paging",object2);
            object.put(keyName,valueName);
            // 获取所有键值对对象的集合
            Set<Map.Entry<String, String>> set = params.entrySet();
            Set<Map.Entry<String, String>> set1 = params1.entrySet();
            // 遍历键值对对象的集合，得到每一个键值对对象
            for (Map.Entry<String, String> me : set) {
                // 根据键值对对象获取键和值
                String key = me.getKey();
                String value = me.getValue();
                object1.put(key,value);
            }
            for (Map.Entry<String, String> me : set1) {
                // 根据键值对对象获取键和值
                String key = me.getKey();
                String value = me.getValue();
                object2.put(key,value);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.w("main","请求参数："+object.toString());
        OkGo.getInstance()
                .setRetryCount(1)
                .post(url)
                .upJson(object.toString().replace("\\",""))
//                .params(map)
                .execute(callBack);
    }

    /*----我的 五达标一公示-----*/
    public void postJsonData5(String url, Map<String, String> params, String keyName,String valueName, JsonCallBack1 callBack) {
        JSONObject object=new JSONObject();
        JSONObject object1=new JSONObject();
        JSONObject object2=new JSONObject();

        try {
            object.put("entity",object1);
//            object.put("paging",object2);
            object.put(keyName,valueName);
            // 获取所有键值对对象的集合
            Set<Map.Entry<String, String>> set = params.entrySet();
//            Set<Map.Entry<String, String>> set1 = params1.entrySet();
            // 遍历键值对对象的集合，得到每一个键值对对象
            for (Map.Entry<String, String> me : set) {
                // 根据键值对对象获取键和值
                String key = me.getKey();
                String value = me.getValue();
                object1.put(key,value);
            }
//            for (Map.Entry<String, String> me : set1) {
//                // 根据键值对对象获取键和值
//                String key = me.getKey();
//                String value = me.getValue();
//                object2.put(key,value);
//            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.w("main","请求参数："+object.toString());
        OkGo.getInstance()
                .setRetryCount(1)
                .post(url)
                .upJson(object.toString().replace("\\",""))
//                .params(map)
                .execute(callBack);
    }

    /*----我的 五达标一公示-----*/
    public void postJsonData6(String url, Map<String, String> params, Map<String, String> params1,String keyName,String valueName, JsonCallBack1 callBack) {
        JSONObject object=new JSONObject();
        JSONObject object1=new JSONObject();
        JSONObject object2=new JSONObject();
        JSONObject object3=new JSONObject();

        try {
            object3.put("entity",object1);
            object.put("paging",object2);
            object3.put(keyName,valueName);
            object.put("",object3);
            // 获取所有键值对对象的集合
            Set<Map.Entry<String, String>> set = params.entrySet();
            Set<Map.Entry<String, String>> set1 = params1.entrySet();
            // 遍历键值对对象的集合，得到每一个键值对对象
            for (Map.Entry<String, String> me : set) {
                // 根据键值对对象获取键和值
                String key = me.getKey();
                String value = me.getValue();
                object1.put(key,value);
            }
            for (Map.Entry<String, String> me : set1) {
                // 根据键值对对象获取键和值
                String key = me.getKey();
                String value = me.getValue();
                object2.put(key,value);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.w("main","请求参数："+object.toString());
        OkGo.getInstance()
                .setRetryCount(1)
                .post(url)
                .upJson(object.toString().replace("\\",""))
//                .params(map)
                .execute(callBack);
    }

    public void postJsonData9(String url, Object params, JsonCallBack1 callBack) {
        Gson gson=new Gson();
        String s=gson.toJson(params);
        OkGo.getInstance()
                .setRetryCount(1)
                .post(url)
                .upJson(s)
//                .params(map)
                .execute(callBack);
    }
    public void postJsonData10(String url, Map<String, String> params, JsonCallBack1 callBack) {
        JSONObject object=new JSONObject(params);
        OkGo.getInstance()
                .setRetryCount(1)
                .post(url)
                .upJson(object.toString())
//                .params(map)
                .execute(callBack);
    }
    public void postJsonData11(String url, Map<String, Object> params, JsonCallBack0 callBack) {
        JSONObject object=new JSONObject(params);
        OkGo.getInstance()
                .setRetryCount(1)
                .post(url)
                .upJson(object.toString())
//                .params(map)
                .execute(callBack);
    }
}

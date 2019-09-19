package com.heziz.liyang.ui.rcjc;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.heziz.liyang.R;
import com.heziz.liyang.base.BaseActivity;
import com.heziz.liyang.utils.LogUtils;
import com.heziz.liyang.view.ProgressWebView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WebviewActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.webView)
    ProgressWebView web;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.rlBack)
    RelativeLayout rlBack;
    private String mWebUrl;
    private String title;
    private int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        ButterKnife.bind(this);
        initViews();
        initDatas();
        initListeners();
    }

    private void initViews() {
//        showProgressDialog();
        mWebUrl=getIntent().getStringExtra("mWebUrl");
        LogUtils.show(mWebUrl);
        id=getIntent().getIntExtra("id",0);
        switch (id){
            case 1:
                title="日常检查";
                break;
            case 2:
                title="专项检查";
                break;
            case 3:
                title="日常巡检整改";
                break;
            case 4:
                title="专项检查整改";
                break;
            case 5:
                title="政策文件";
                break;
            case 6:
                title="安全检查";
                break;
            case 7:
                title="质量检查";
                break;
        }

        tvTitle.setText(title);
        web.getSettings().setDefaultTextEncodingName("UTF-8");
        web.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        web.getSettings().setBuiltInZoomControls(true);

        web.getSettings().setDomStorageEnabled(true);
        web.getSettings().setSupportMultipleWindows(true);
//        web.addJavascriptInterface(new Android(),"android");
        web.getSettings().setJavaScriptEnabled(true);
        web.getSettings().setDisplayZoomControls(false);

        web.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return super.shouldOverrideUrlLoading(view, url);
            }
        });
//        web.setWebChromeClient(new WebChromeClient(){
//            @Override
//            public void onProgressChanged(WebView view, int newProgress) {
//                super.onProgressChanged(view, newProgress);
//                if (newProgress==100){
//                    dissmissProgressDialog();
//                }
//            }
//        });

//        web.loadUrl("javascript:change('" + MyApplication.getInstance().getUserInfor().uuid + "')");
        web.loadUrl(mWebUrl);
    }

    private void initDatas() {

//        String url = "https://www.heziz.com/apis/urmp/wechatMiniProgram/getOpenId?code=023Wy2MY1iYJpT01GsOY1SCKLY1Wy2Mj";
//        Map<String,String> params=new HashMap<>();
////        params.put("siteId","1551258680345413");
//        JsonCallBack1<SRequstBean<List<StreetBean>>> jsonCallBack = new JsonCallBack1<SRequstBean<List<StreetBean>>>() {
//            @Override
//            public void onSuccess(com.lzy.okgo.model.Response<SRequstBean<List<StreetBean>>> response) {
//
//            }
//
//            @Override
//            public void onError(com.lzy.okgo.model.Response<SRequstBean<List<StreetBean>>> response) {
//                super.onError(response);
//                dissmissProgressDialog();
//            }
//
//        };
//        OkGoClient.getInstance()
//                .getJsonData1(url, params, jsonCallBack);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d("main", "requestCode=" + requestCode);
        if (resultCode == RESULT_OK) {
            if (requestCode == ProgressWebView.TYPE_CAMERA) { // 相册选择
//                if (data!=null){
//                    Uri uri = data.getData();
//                    Log.d("main", "uri=" + uri);
//                    if (uri != null) {
                web.onActivityCallBack(true, null,1);
//                    }
//                }

            } else if (requestCode == ProgressWebView.TYPE_GALLERY) {// 相册选择
                if (data != null) {
                    Uri uri = data.getData();
                    Log.d("main", "uri=" + uri);
                    if (uri != null) {
                        web.onActivityCallBack(false, uri,1);
                    } else {
                        Toast.makeText(WebviewActivity.this, "获取数据为空", Toast.LENGTH_LONG).show();
                    }
                }else{
                    web.onActivityCallBack(false, null,2);
                }
            }

        }else{
            web.onActivityCallBack(false, null,2);
        }
    }

    // 权限回调
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        if (requestCode == ProgressWebView.TYPE_REQUEST_PERMISSION) {
////            web.toCamera();// 到相机
//            web.showOptions();
//        }
    }


    private void initListeners() {
        rlBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rlBack:
                finish();
                break;
        }
    }
}

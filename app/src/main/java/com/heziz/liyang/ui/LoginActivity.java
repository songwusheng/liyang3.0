package com.heziz.liyang.ui;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.heziz.liyang.R;
import com.heziz.liyang.app.MyApplication;
import com.heziz.liyang.base.BaseActivity;
import com.heziz.liyang.bean.UserInfor;
import com.heziz.liyang.network.API;
import com.heziz.liyang.network.HezhiResponse;
import com.heziz.liyang.network.JsonCallBack;
import com.heziz.liyang.network.OkGoClient;
import com.heziz.liyang.utils.ToastUtil;
import com.heziz.liyang.view.ClearEditText;
import com.pgyersdk.update.DownloadFileListener;
import com.pgyersdk.update.PgyUpdateManager;
import com.pgyersdk.update.UpdateManagerListener;
import com.pgyersdk.update.javabean.AppBean;

import java.util.HashMap;
import java.util.Map;

import butterknife.ButterKnife;

import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    private ClearEditText etUsername;
    private ClearEditText etPassword;
    private CheckBox cb;
    private Button btnLogin;
    private LinearLayout llSave;
    Map<String, String> params;
    private AppBean appBean1;
    private Dialog dialog;
    private TextView tvProgress;
    private ProgressBar progressBar;
    private TextView tvTitle1;
    private TextView tvTitle2;
    private TextView tvTitle3;
    LinearLayout llBtn;
    LinearLayout llProgress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        ButterKnife.bind(this);
        requestPermission();

        initViews();

        initListeners();

        getLoginInfo();

        checkUpdata();
    }

    private void checkUpdata() {
        new PgyUpdateManager.Builder()
                .setForced(false)//设置是否强制提示更新,非自定义回调更新接口此方法有用
                .setUserCanRetry(false)         //失败后是否提示重新下载，非自定义下载 apk 回调此方法有用
                .setDeleteHistroyApk(false)     // 检查更新前是否删除本地历史 Apk， 默认为true
                .setUpdateManagerListener(new UpdateManagerListener() {
                    @Override
                    public void onNoUpdateAvailable() {
                        //没有更新是回调此方法
                        ToastUtil.showToast("已经是最新版本");
                    }
                    @Override
                    public void onUpdateAvailable(AppBean appBean) {
                        appBean1=appBean;
                        //有更新回调此方法
                        Log.d("pgyer", "there is new version can update"
                                + "new versionCode is " + appBean.getVersionCode());
                        //调用以下方法，DownloadFileListener 才有效；
                        //如果完全使用自己的下载方法，不需要设置DownloadFileListener
                        showDialog(appBean);

                    }

                    @Override
                    public void checkUpdateFailed(Exception e) {
                        //更新检测失败回调
                        //更新拒绝（应用被下架，过期，不在安装有效期，下载次数用尽）以及无网络情况会调用此接口
//                        Log.e("pgyer", "check update failed ", e);
                    }
                })
                //注意 ：
                //下载方法调用 PgyUpdateManager.downLoadApk(appBean.getDownloadURL()); 此回调才有效
                //此方法是方便用户自己实现下载进度和状态的 UI 提供的回调
                //想要使用蒲公英的默认下载进度的UI则不设置此方法
                .setDownloadFileListener(new DownloadFileListener() {
                    @Override
                    public void downloadFailed() {
                        //下载失败
                        ToastUtil.showToast("下载失败");
                        Log.e("pgyer", "download apk failed");
                    }

                    @Override
                    public void downloadSuccessful(Uri uri) {
                        Log.e("pgyer", "download apk failed");
                        // 使用蒲公英提供的安装方法提示用户 安装apk
                        dialog.dismiss();
                        PgyUpdateManager.installApk(uri);
                    }

                    @Override
                    public void onProgressUpdate(Integer... integers) {
                        Log.e("pgyer", "update download apk progress" + integers);
                        progressBar.setProgress(integers.clone()[0]);
                        tvProgress.setText(integers.clone()[0]+"%");
                    }})
                .register();
    }

    private void showDialog(final AppBean appBean) {
        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this,R.style.no_border_dialog);
        LayoutInflater inflater = LayoutInflater.from(LoginActivity.this);
        View v = inflater.inflate(R.layout.upload_view, null);
        Button btnUp= (Button) v.findViewById(R.id.btnUp);
        Button btnCancel= (Button) v.findViewById(R.id.btnCacel);
        tvTitle1= (TextView) v.findViewById(R.id.tvTitle1);
        tvTitle2= (TextView) v.findViewById(R.id.tvTitle2);
        tvTitle3= (TextView) v.findViewById(R.id.tvTitle3);
        tvTitle2.setText("版本介绍："+appBean.getVersionName());
        tvTitle3.setText(appBean.getReleaseNote());
        LinearLayout llDialog= (LinearLayout) v.findViewById(R.id.llDialog);
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) llDialog.getLayoutParams();
        lp.width = getScreenWidth();
        llDialog.setLayoutParams(lp);
        llBtn=(LinearLayout) v.findViewById(R.id.llBtn);
        llProgress=(LinearLayout) v.findViewById(R.id.llProgress);
        progressBar=(ProgressBar) v.findViewById(R.id.progressBar);
        tvProgress=(TextView) v.findViewById(R.id.tvProgress);
        dialog = builder.create();
        dialog.show();
        dialog.setCancelable(false);
        dialog.getWindow().setContentView(v);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        btnUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                llBtn.setVisibility(View.GONE);
                llProgress.setVisibility(View.VISIBLE);
                tvTitle2.setVisibility(View.GONE);
                tvTitle3.setVisibility(View.GONE);
                tvTitle1.setText("正在下载");
                if (ContextCompat.checkSelfPermission(LoginActivity.this, WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    //申请WRITE_EXTERNAL_STORAGE权限
                    ActivityCompat.requestPermissions(LoginActivity.this, new String[]{WRITE_EXTERNAL_STORAGE},
                            101);
                }else{
                    PgyUpdateManager.downLoadApk(appBean.getDownloadURL());
                }

            }

        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 101:
                // 授权被允许
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    PgyUpdateManager.downLoadApk(appBean1.getDownloadURL());

                } else {
                    toast("缺少权限");
                }
                break;
        }
    }
    private void getLoginInfo() {
        SharedPreferences sp = getSharedPreferences("login", Context.MODE_PRIVATE);
        String name = sp.getString("account", "");
        String pass = sp.getString("password", "");
        if("".equals(name)||"".equals(pass)){
            return;
        }else{
            etUsername.setText(name);
            etPassword.setText(pass);
        }
    }

    private void initViews() {
        etUsername=(ClearEditText) findViewById(R.id.etusername);
        etPassword=(ClearEditText) findViewById(R.id.etpassword);
        cb=(CheckBox) findViewById(R.id.cb);
        btnLogin=(Button) findViewById(R.id.btnLogin);
        llSave= (LinearLayout) findViewById(R.id.llSave);
    }

    private void initListeners() {
        btnLogin.setOnClickListener(this);
        llSave.setOnClickListener(this);
    }
    private void requestPermission() {
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_PHONE_STATE, Manifest.permission.CAMERA,Manifest.permission.ACCESS_FINE_LOCATION},
                0);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnLogin:
                String account=etUsername.getText().toString();
                String password=etPassword.getText().toString();
                if ("".equals(account) || "".equals(password)) {
                    Toast.makeText(LoginActivity.this,"账号或密码不能为空",Toast.LENGTH_LONG).show();
                    return;
                } else {
                    params = new HashMap<>();
                    params.put("account", account);
                    params.put("password", password);
                    showProgressDialog();
                    if(cb.isChecked()){
                        SharedPreferences sp = getSharedPreferences("login", Context.MODE_PRIVATE);
                        sp.edit().putString("account", account).putString("password", password).commit();
                    }else{
                        SharedPreferences sp = getSharedPreferences("login", Context.MODE_PRIVATE);
                        sp.edit().putString("account", "").putString("password", "").commit();
                    }
                    login(params);
                }
                break;
            case R.id.llSave:
                if(cb.isChecked()){
                    cb.setChecked(false);
                }else{
                    cb.setChecked(true);
                }
                break;
        }
    }

    private void login(Map<String, String> params) {
        String url = API.LOGIN;
        JsonCallBack<HezhiResponse<UserInfor>> jsonCallBack = new JsonCallBack<HezhiResponse<UserInfor>>() {
            @Override
            public void onSuccess(com.lzy.okgo.model.Response<HezhiResponse<UserInfor>> response) {
                //保存uuid
                MyApplication.getInstance().setUserInfor(response.body().data);
                dissmissProgressDialog();
                startMyActivity(NewHomeActivity.class);
                finish();
            }

            @Override
            public void onError(com.lzy.okgo.model.Response<HezhiResponse<UserInfor>> response) {
                super.onError(response);
                dissmissProgressDialog();
            }

        };
        OkGoClient.getInstance()
                .getJsonData(url, params, jsonCallBack);
    }

    private int getScreenWidth(){
        DisplayMetrics metric = new DisplayMetrics();
        getWindowManager().getDefaultDisplay()
                .getMetrics(metric);
        int mScreenWidth = metric.widthPixels; // 屏幕宽度（像素）
        int mScreenHeight = metric.heightPixels; // 屏幕高度（像素）
//        mScreenHeight = mScreenWidth * 3 / 4;
        return mScreenWidth * 3 / 4;
    }
}

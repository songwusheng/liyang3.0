package com.heziz.liyang.ui;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.heziz.liyang.R;
import com.heziz.liyang.app.MyApplication;
import com.heziz.liyang.base.BaseActivity;
import com.heziz.liyang.bean.DataBean;
import com.heziz.liyang.bean.KqzlBean;
import com.heziz.liyang.bean.UserInfor;
import com.heziz.liyang.network.API;
import com.heziz.liyang.network.JsonCallBack1;
import com.heziz.liyang.network.OkGoClient;
import com.heziz.liyang.network.SRequstBean;
import com.heziz.liyang.ui.zhihui.sjj.SjjStreetDataListActivity;
import com.heziz.liyang.ui.zhihui.zcwj.XWZXListActivity;
import com.heziz.liyang.ui.zhihui.bjxx.BaojingxxActivity;
import com.heziz.liyang.ui.newjm.CommActivity;
import com.heziz.liyang.ui.newjm.TdSmzAqZlActivity;
import com.heziz.liyang.ui.zhihui.clwcx.ClcxStreetProjectActivity;
import com.heziz.liyang.ui.zhihui.clwcx.ClwcxStreetDeviceActivity;
import com.heziz.liyang.ui.zhihui.fdl.FDLProjectListActivity;
import com.heziz.liyang.ui.zhihui.fdl.FdlStreetDeviceListActivity;
import com.heziz.liyang.ui.zhihui.smz.SmzProjectListActivity;
import com.heziz.liyang.ui.zhihui.smz.SmzStreetInfoActivity;
import com.heziz.liyang.ui.zhihui.sp.SpStreetDeviceListActivity;
import com.heziz.liyang.ui.zhihui.sp.SpStreetProjectActivity;
import com.heziz.liyang.ui.zhihui.td.TdProjectListActivity;
import com.heziz.liyang.ui.zhihui.td.TdStreetInfoActivity;
import com.heziz.liyang.ui.zhihui.yc.StreetYcDeviceListActivity;
import com.heziz.liyang.ui.zhihui.yc.YcStreetProjectListActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewHomeActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.ivXtsz)
    ImageView ivXtsz;
    @BindView(R.id.tvSp)
    TextView tvSp;
    @BindView(R.id.tvYc)
    TextView tvYc;
    @BindView(R.id.tvBj)
    TextView tvBj;
    @BindView(R.id.tvCl)
    TextView tvCl;
    @BindView(R.id.tvXm)
    TextView tvXm;
    @BindView(R.id.tvDt)
    TextView tvDt;
    @BindView(R.id.tvRc)
    TextView tvRc;
    @BindView(R.id.tvFd)
    TextView tvFd;
    @BindView(R.id.llJszc)
    LinearLayout llJszc;

    @BindView(R.id.tvSjj)
    TextView tvSjj;
    @BindView(R.id.tvTd)
    TextView tvTd;
    @BindView(R.id.tvSmz)
    TextView tvSmz;
    @BindView(R.id.tvAq)
    TextView tvAq;
    @BindView(R.id.tvZl)
    TextView tvZl;
    @BindView(R.id.tvZcwj)
    TextView tvZcwj;

    @BindView(R.id.tvWeather)
    TextView tvWeather;
    @BindView(R.id.tvFl)
    TextView tvFl;
    @BindView(R.id.tvKq)
    TextView tvKq;

    @BindView(R.id.ivKf)
    ImageView ivKf;
    private UserInfor userInfor;
    private String position;
    private NewHomeActivity mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_home);
        ButterKnife.bind(this);
        initViews();
        initDatas();
        initListeners();
    }

    private void initDatas() {
        Map<String,String> params1=new HashMap<>();
        String url1 = API.HOME_WHEATHER + "?access_token=" + userInfor.getUuid();
        params1.put("citynameId","1347");
        JsonCallBack1<SRequstBean<DataBean>> jsonCallBack = new JsonCallBack1<SRequstBean<DataBean>>() {
            @Override
            public void onSuccess(com.lzy.okgo.model.Response<SRequstBean<DataBean>> response) {
                DataBean dataBean=response.body().getData();
                String resulte=dataBean.getResult().replace("\\","");
                try {
                    JSONObject object=new JSONObject(resulte);
                    JSONObject today=object.getJSONObject("today");
                    String weather=today.getString("weather");
                    String tmp=today.getString("temperature");
                    tvWeather.setText(weather+" "+tmp);

                    JSONObject sk=object.getJSONObject("sk");
                    String fl=today.getString("wind");
                    String fj=sk.getString("wind_strength");
                    tvFl.setText(fl+" "+fj);

                    //tvKq.setText("良 61");

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onError(com.lzy.okgo.model.Response<SRequstBean<DataBean>> response) {
                super.onError(response);
                dissmissProgressDialog();
            }

        };
        OkGoClient.getInstance()
                .postJsonData0(url1, params1, jsonCallBack);

        Map<String,String> params2=new HashMap<>();
        String url2 = API.HOME_WHEATHER1 + "?access_token=" + userInfor.getUuid();
        params2.put("cityNamePinyin","nanjing");
        JsonCallBack1<SRequstBean<KqzlBean>> jsonCallBack1 = new JsonCallBack1<SRequstBean<KqzlBean>>() {
            @Override
            public void onSuccess(com.lzy.okgo.model.Response<SRequstBean<KqzlBean>> response) {

                KqzlBean dataBean=response.body().getData();
                String resulte=dataBean.getResult().replace("\\","");
                try {
                    JSONArray arr=new JSONArray(resulte);
                   JSONObject object=arr.getJSONObject(0);
                   JSONObject object1=object.getJSONObject("citynow");

                    tvKq.setText(object1.getString("quality")+" "+object1.getString("AQI"));

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onError(com.lzy.okgo.model.Response<SRequstBean<KqzlBean>> response) {
                super.onError(response);
                dissmissProgressDialog();
            }

        };
        OkGoClient.getInstance()
                .postJsonData0(url2, params2, jsonCallBack1);
    }

    private void initViews() {
        mContext=this;
        userInfor= MyApplication.getInstance().getUserInfor();
        position=userInfor.getPosition();
        //tvVertion.setText(getVersion());
    }

    private void initListeners() {
        ivXtsz.setOnClickListener(this);
        llJszc.setOnClickListener(this);
        tvSp.setOnClickListener(this);
        tvYc.setOnClickListener(this);
        tvBj.setOnClickListener(this);
        tvCl.setOnClickListener(this);
        tvXm.setOnClickListener(this);
        tvDt.setOnClickListener(this);
        tvRc.setOnClickListener(this);
        tvFd.setOnClickListener(this);
        tvTd.setOnClickListener(this);
        tvSjj.setOnClickListener(this);
        tvSmz.setOnClickListener(this);
        tvAq.setOnClickListener(this);
        tvZl.setOnClickListener(this);
        tvZcwj.setOnClickListener(this);
        llJszc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callPhone("4001165850");
            }
        });
    }

    @Override
    public void onClick(View v) {
        Intent intent=new Intent();
        switch (v.getId()){
            case R.id.ivXtsz:
                intent.setClass(mContext,CommActivity.class);
                intent.putExtra("type",3);
                intent.putExtra("title","我的管理");
                break;
            case R.id.tvSp:
                if(userInfor.getPosition().equals("3")){
                    intent.setClass(mContext,SpStreetProjectActivity.class);
                    intent.putExtra("id","");
                    intent.putExtra("name","");
                }else {
                    intent.setClass(mContext,SpStreetDeviceListActivity.class);
                }
                break;
            case R.id.tvSjj:
                if(userInfor.getPosition().equals("3")){
                    //intent.setClass(mContext,SpStreetProjectActivity.class);
                    //intent.putExtra("id","");
                    //intent.putExtra("name","");
                }else {
                    intent.setClass(mContext,SjjStreetDataListActivity.class);
                }
                break;

            case R.id.tvYc:
                if(userInfor.getPosition().equals("3")){
                    intent=new Intent(mContext, YcStreetProjectListActivity.class);
                    intent.putExtra("id","");
                    intent.putExtra("name","");
                }else {
                    intent.setClass(mContext,StreetYcDeviceListActivity.class);
                    intent.putExtra("type","yc");
                }
                break;
            case R.id.tvBj:
                intent.setClass(mContext,BaojingxxActivity.class);
                break;
            case R.id.tvCl:
                if(userInfor.getPosition().equals("3")){
                    intent=new Intent(mContext, ClcxStreetProjectActivity.class);
                    intent.putExtra("id","");
                    intent.putExtra("name","");
                }else {
                    intent.setClass(mContext,ClwcxStreetDeviceActivity.class);
                }
                break;
            case R.id.tvXm:
                intent.setClass(mContext,CommActivity.class);
                intent.putExtra("type",0);
                intent.putExtra("title","项目总览");
                break;
            case R.id.tvDt:
                intent.setClass(mContext,CommActivity.class);
                intent.putExtra("type",1);
                intent.putExtra("title","地图");
                break;
            case R.id.tvRc:
                intent.setClass(mContext,CommActivity.class);
                intent.putExtra("type",2);
                intent.putExtra("title","项目检查");
                break;
            case R.id.tvFd:
                if(userInfor.getPosition().equals("3")){
                    intent.setClass(mContext,FDLProjectListActivity.class);
                    intent.putExtra("managerId","");
                }else{
                    intent.setClass(mContext,FdlStreetDeviceListActivity.class);
                }
                break;
            case R.id.tvTd:
                if(userInfor.getPosition().equals("3")){
                    intent.setClass(mContext,TdProjectListActivity.class);
                }else{
                    intent.setClass(mContext,TdStreetInfoActivity.class);
                }
                break;
            case R.id.tvSmz:
                if(userInfor.getPosition().equals("3")){
                    intent.setClass(mContext,SmzProjectListActivity.class);
                }else{
                    intent.setClass(mContext,SmzStreetInfoActivity.class);
                }
                break;
            case R.id.tvAq:
                intent.setClass(mContext, TdSmzAqZlActivity.class);
                intent.putExtra("title","安全管理");
                intent.putExtra("type",102);
                break;
            case R.id.tvZl:
                intent.setClass(mContext, TdSmzAqZlActivity.class);
                intent.putExtra("title","质量管理");
                intent.putExtra("type",103);
                break;
            case R.id.tvZcwj:
                intent.setClass(mContext, XWZXListActivity.class);
                break;
        }
        startActivity(intent);
    }
    public void callPhone(String phoneNum) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        Uri data = Uri.parse("tel:" + phoneNum);
        intent.setData(data);
        startActivity(intent);
    }
    private String getVersion(){
        PackageInfo pkg;
        String versionName="";
        try {
            pkg = getPackageManager().getPackageInfo(getApplication().getPackageName(), 0);
            String appName = pkg.applicationInfo.loadLabel(getPackageManager()).toString();
            versionName = pkg.versionName;
            System.out.println("appName:" + appName);
            System.out.println("versionName:" + versionName);

        } catch (PackageManager.NameNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return  "v "+versionName;
    }
}

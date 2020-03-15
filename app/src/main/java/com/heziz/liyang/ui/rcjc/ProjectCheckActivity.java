package com.heziz.liyang.ui.rcjc;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.heziz.liyang.R;
import com.heziz.liyang.app.MyApplication;
import com.heziz.liyang.base.BaseActivity;
import com.heziz.liyang.bean.JPushCommBean;
import com.heziz.liyang.bean.UserInfor;
import com.heziz.liyang.bean.rcjc.RcjcBean;
import com.heziz.liyang.network.API;
import com.heziz.liyang.network.JsonCallBack0;
import com.heziz.liyang.network.OkGoClient;
import com.heziz.liyang.ui.rcjc.jgfrcjc.DayCheckActivity;
import com.heziz.liyang.ui.rcjc.jgfzxjc.ZXCheckActivity;
import com.heziz.liyang.ui.rcjc.xmfrcjc.XMRCCheckDZGActivity;
import com.heziz.liyang.ui.rcjc.xmfzc.XMZCCheckActivity;
import com.heziz.liyang.ui.rcjc.xmfzxjc.XMZXCheckDZGActivity;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProjectCheckActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.rlBack)
    RelativeLayout rlBack;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.llZXJC)
    LinearLayout llZXJC;
    @BindView(R.id.llRCJC)
    LinearLayout llRCJC;
    @BindView(R.id.llZXJCDZG)
    LinearLayout llZXJCDZG;
    @BindView(R.id.llWGRYJCQK)
    LinearLayout llWGRYJCQK;
    @BindView(R.id.llXMZCQK)
    LinearLayout llXMZCQK;

    @BindView(R.id.tvDayCheckTitle1)
    TextView tvDayCheckTitle1;
    @BindView(R.id.tvDayCheckTitle2)
    TextView tvDayCheckTitle2;
    @BindView(R.id.tvDayCheckTitle3)
    TextView tvDayCheckTitle3;
    @BindView(R.id.tvZXCheckTitle1)
    TextView tvZXCheckTitle1;
    @BindView(R.id.tvZXCheckTitle2)
    TextView tvZXCheckTitle2;
    @BindView(R.id.tvZXCheckTitle3)
    TextView tvZXCheckTitle3;

    @BindView(R.id.tvNum1)
    TextView tvNum1;
    @BindView(R.id.tvNum2)
    TextView tvNum2;
    @BindView(R.id.tvNum3)
    TextView tvNum3;


    @BindView(R.id.tvWgyc)
    TextView tvWgyc;
    @BindView(R.id.tvWgsc)
    TextView tvWgsc;
    @BindView(R.id.tvWgwc)
    TextView tvWgwc;
    @BindView(R.id.tvWgHgl)
    TextView tvWgHgl;
    @BindView(R.id.tvWgHg)
    TextView tvWgHg;
    @BindView(R.id.tvWgWhg)
    TextView tvWgWhg;

    @BindView(R.id.tvXmYc)
    TextView tvXmYc;
    @BindView(R.id.tvXmsc)
    TextView tvXmsc;
    @BindView(R.id.tvXmwc)
    TextView tvXmwc;
    @BindView(R.id.tvXmHgl)
    TextView tvXmHgl;
    @BindView(R.id.tvXmHg)
    TextView tvXmHg;
    @BindView(R.id.tvXmWhg)
    TextView tvXmWhg;

    private UserInfor userInfor;

    private ZDYJPushReceiver zdyjPushReceiver;
    private JPushCommBean jPushCommBean;

    private int wgYC;
    private int wgWC;
    private int xmYC;
    private int xmWC;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_check);
        ButterKnife.bind(this);
        initViews();
        initListeners();

//        initDatas1();
    }

    private void initListeners() {
        rlBack.setOnClickListener(this);
        llZXJC.setOnClickListener(this);
        llRCJC.setOnClickListener(this);
        llZXJCDZG.setOnClickListener(this);
//        llWGRYJCQK.setOnClickListener(this);
//        llXMZCQK.setOnClickListener(this);
    }

    private void initViews() {
        userInfor= MyApplication.getInstance().getUserInfor();
        jPushCommBean=MyApplication.getInstance().getjPushCommBean();
        if(userInfor.getPosition().equals("3")){
            tvDayCheckTitle1.setText("项目自查");
            tvDayCheckTitle2.setText("《日常检查 八达标一公示》");
            tvDayCheckTitle3.setText("“道路保洁、裸露地面覆盖、工地围挡、道路硬化、出入口冲洗、油品管理、工程机械、渣土运输管理、责任公示牌”");
            tvZXCheckTitle1.setText("日常检查待整改");
            tvZXCheckTitle2.setText("");
            tvZXCheckTitle3.setText("“八达标一公示相关项目待整改”");
            llZXJCDZG.setVisibility(View.VISIBLE);
//            llXMZCQK.setVisibility(View.GONE);
//            llWGRYJCQK.setVisibility(View.GONE);

        }else{
//            initDatas();
            llZXJCDZG.setVisibility(View.GONE);
//            llXMZCQK.setVisibility(View.VISIBLE);
//            llWGRYJCQK.setVisibility(View.VISIBLE);
        }
        tvTitle.setText("日常检查");

       refreshJPUSH(jPushCommBean);
        zdyjPushReceiver=new ZDYJPushReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("jpush_refresh");
        registerReceiver(zdyjPushReceiver,intentFilter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rlBack:
                finish();
                break;
            case R.id.llRCJC:
                Intent intent=new Intent();
                if(userInfor.getPosition().equals("3")){
                    intent.setClass(mContext,XMZCCheckActivity.class);
                    intent.putExtra("title","项目自查");
                }else{
                    intent.setClass(mContext,DayCheckActivity.class);
                    intent.putExtra("title","日常检查");
                }
                startActivity(intent);

                break;
            case R.id.llZXJC:
                Intent intent1=new Intent();
                if(userInfor.getPosition().equals("3")){
                    intent1.setClass(mContext,XMRCCheckDZGActivity.class);
                    intent1.putExtra("title","日常检查整改");
                }else{
                    intent1.setClass(mContext,ZXCheckActivity.class);
                }
                startActivity(intent1);
                break;
            case R.id.llZXJCDZG:
                startMyActivity(XMZXCheckDZGActivity.class);
                break;
//            case R.id.llWGRYJCQK:
//                Intent intent2=new Intent();
//                intent2.setClass(mContext,ProjectCheckNumDetailsActivity.class);
//                intent2.putExtra("type",1);
//                intent2.putExtra("YC",wgYC);
//                intent2.putExtra("WC",wgWC);
//                startActivity(intent2);
//                break;
//            case R.id.llXMZCQK:
//                Intent intent3=new Intent();
//                intent3.setClass(mContext,ProjectCheckNumDetailsActivity.class);
//                intent3.putExtra("type",2);
//                intent3.putExtra("YC",xmYC);
//                intent3.putExtra("WC",xmWC);
//                startActivity(intent3);
//                break;
        }
    }

    public class ZDYJPushReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            //{"badge":"0","zcdfc":"0","zg":"0","zgdsh":"0","zxdfc":"0","zxdzg":"0"}
           jPushCommBean=(JPushCommBean) intent.getSerializableExtra("jPushCommBean");

           refreshJPUSH(jPushCommBean);
        }
    }

    private void refreshJPUSH(JPushCommBean jPushCommBean){
        if(userInfor.getPosition().equals("3")){
            if(Integer.valueOf(jPushCommBean.getZcdfc())<=0){
                tvNum1.setVisibility(View.GONE);
            }else{
                tvNum1.setText(jPushCommBean.getZcdfc());
                tvNum1.setVisibility(View.VISIBLE);
            }
            if(Integer.valueOf(jPushCommBean.getZg())<=0){
                tvNum2.setVisibility(View.GONE);
            }else{
                tvNum2.setText(jPushCommBean.getZg());
                tvNum2.setVisibility(View.VISIBLE);
            }
            if(Integer.valueOf(jPushCommBean.getZxdzg())<=0){
                tvNum3.setVisibility(View.GONE);
            }else{
                tvNum3.setText(jPushCommBean.getZxdzg());
                tvNum3.setVisibility(View.VISIBLE);
            }

        }else{
            if(Integer.valueOf(jPushCommBean.getZgdsh())<=0){
                tvNum1.setVisibility(View.GONE);
            }else{
                tvNum1.setText(jPushCommBean.getZgdsh());
                tvNum1.setVisibility(View.VISIBLE);
            }
            if(Integer.valueOf(jPushCommBean.getZxdfc())<=0){
                tvNum2.setVisibility(View.GONE);
            }else{
                tvNum2.setText(jPushCommBean.getZxdfc());
                tvNum2.setVisibility(View.VISIBLE);
            }
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(zdyjPushReceiver);
    }

//    private void initDatas() {
//        showProgressDialog();
//        String url = API.RCRW_WDBYGS;
//        Map<String,String> params=new HashMap<>();
//        params.put("access_token", userInfor.getUuid());
//        if("1".equals(userInfor.getPosition())){
//            params.put("station",userInfor.getStation()+"");
//        }else if("2".equals(userInfor.getPosition())){
//            params.put("managerId",userInfor.getManagerId()+"");
//        }else if("3".equals(userInfor.getPosition())){
//            params.put("createAccount",userInfor.getAccount()+"");
//        }
//
//        JsonCallBack0<String> jsonCallBack = new JsonCallBack0<String>() {
//            @Override
//            public void onSuccess(com.lzy.okgo.model.Response<String> response) {
//                String s=response.body();
//                Gson gson=new Gson();
//                RcjcBean bean=gson.fromJson(s,RcjcBean.class);
//                xmYC=bean.getFinished();
//                xmWC=bean.getUnFinished();
//                tvXmYc.setText(bean.getTotal()+"个");
//                tvXmsc.setText(bean.getFinished()+"个");
//                tvXmwc.setText(bean.getUnFinished()+"个");
//                DecimalFormat df = new DecimalFormat("0.0");
//                tvXmHgl.setText(df.format(bean.getPassRate()*100)+"%");
//                tvXmHg.setText(bean.getPass()+"个");
//                tvXmWhg.setText(bean.getUnPass()+"个");
//
//            }
//
//            @Override
//            public void onError(com.lzy.okgo.model.Response<String> response) {
//                super.onError(response);
//                dissmissProgressDialog();
//            }
//
//        };
//        OkGoClient.getInstance()
//                .getJsonData2(url, params, jsonCallBack);
//    }
//
//    private void initDatas1() {
//        String url = API.RCRW_WLRY;
//        Map<String,String> params=new HashMap<>();
//        params.put("access_token", userInfor.getUuid());
//        if("1".equals(userInfor.getPosition())){
//            params.put("station",userInfor.getStation()+"");
//        }else if("2".equals(userInfor.getPosition())){
//            params.put("managerId",userInfor.getManagerId()+"");
//        }else if("3".equals(userInfor.getPosition())){
//            params.put("createAccount",userInfor.getAccount()+"");
//        }
//        JsonCallBack0<String> jsonCallBack = new JsonCallBack0<String>() {
//            @Override
//            public void onSuccess(com.lzy.okgo.model.Response<String> response) {
//                dissmissProgressDialog();
//                String s=response.body();
//                Gson gson=new Gson();
//                RcjcBean bean=gson.fromJson(s,RcjcBean.class);
//                wgYC=bean.getFinished();
//                wgWC=bean.getUnFinished();
//                tvWgyc.setText(bean.getTotal()+"个");
//                tvWgsc.setText(bean.getFinished()+"个");
//                tvWgwc.setText(bean.getUnFinished()+"个");
//                DecimalFormat df = new DecimalFormat("0.0");
//                tvWgHgl.setText(df.format(bean.getPassRate()*100)+"%");
//                tvWgHg.setText(bean.getPass()+"个");
//                tvWgWhg.setText(bean.getUnPass()+"个");
//
//            }
//
//            @Override
//            public void onError(com.lzy.okgo.model.Response<String> response) {
//                super.onError(response);
//                dissmissProgressDialog();
//            }
//
//        };
//        OkGoClient.getInstance()
//                .getJsonData2(url, params, jsonCallBack);
//    }

}

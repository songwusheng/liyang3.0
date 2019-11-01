package com.heziz.liyang.ui.zhihui.sjj;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.heziz.liyang.R;
import com.heziz.liyang.base.BaseActivity;
import com.heziz.liyang.bean.sjj.SjjEDBean;
import com.heziz.liyang.network.API;
import com.heziz.liyang.network.JsonCallBack1;
import com.heziz.liyang.network.OkGoClient;
import com.heziz.liyang.network.SRequstBean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SjjDetailsActivity1 extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.rlBack)
    RelativeLayout rlBack;
    @BindView(R.id.tvTitle)
    TextView tvTitle;


    @BindView(R.id.tvsbh)
    TextView tvsbh;
    @BindView(R.id.tvsfsb)
    TextView tvsfsb;
    @BindView(R.id.ivPhoto)
    ImageView ivPhoto;

    private String deviceid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sjj_details1);
        ButterKnife.bind(this);
        initViews();
        initDatas();
        initListeners();
    }

    private void initViews() {
        deviceid=getIntent().getStringExtra("deviceid");
        tvsbh.setText(deviceid);
        //tvName.setText(name);
        tvTitle.setText("sgsjj_bg");

    }

    private void initDatas() {
        showProgressDialog();
        //额定起升重量，额定高度，允许倾斜度初始化
        Map<String,String> params1=new HashMap<>();
        params1.put("devNum",deviceid);
        String url1 = API.SJJ_DETAILS_ED;

        JsonCallBack1<SRequstBean<List<SjjEDBean>>> jsonCallBack1 = new JsonCallBack1<SRequstBean<List<SjjEDBean>>>() {
            @Override
            public void onSuccess(com.lzy.okgo.model.Response<SRequstBean<List<SjjEDBean>>> response) {

                dissmissProgressDialog();
                SjjEDBean sjjEDBean=response.body().getData().get(0);

                Glide.with(mContext).load(sjjEDBean.getDriverPhoto()).into(ivPhoto);
                //tvqsxg.setText(sjjEDBean.getLimitHeight()+"m");
                //tvedzz.setText(sjjEDBean.getLimitWeight()+"t");
                //tvyxqxd.setText(sjjEDBean.getLimitBatter()+"°");
            }

            @Override
            public void onError(com.lzy.okgo.model.Response<SRequstBean<List<SjjEDBean>>> response) {
                super.onError(response);
                dissmissProgressDialog();
            }

        };
        OkGoClient.getInstance()
                .postJsonData(url1, params1, jsonCallBack1);
        ////身份识别初始化
        String urlnum = API.SJJ_DETAILS_SFSB;
        Map<String, String> paramsnum = new HashMap<>();
        paramsnum.put("devNum",deviceid);
        JsonCallBack1<SRequstBean<String>> jsonCallBacknum = new JsonCallBack1<SRequstBean<String>>() {
            @Override
            public void onSuccess(com.lzy.okgo.model.Response<SRequstBean<String>> response) {

                String res=response.body().getData();

                tvsfsb.setText(res);
            }

            @Override
            public void onError(com.lzy.okgo.model.Response<SRequstBean<String>> response) {
                super.onError(response);
                dissmissProgressDialog();
            }

        };
        OkGoClient.getInstance()
                .getJsonData1(urlnum, paramsnum, jsonCallBacknum);

    }


    private void initListeners() {
        rlBack.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //case R.id.cb:
            //    Intent intent=new Intent(SjjDetailsActivity.this, ProjectGKActivity.class);
            //    intent.putExtra("id",id);
            //    startActivity(intent);
            //    break;
            case R.id.rlBack:
                finish();
                break;
        }
    }

}

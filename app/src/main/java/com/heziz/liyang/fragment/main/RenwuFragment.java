package com.heziz.liyang.fragment.main;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.heziz.liyang.R;
import com.heziz.liyang.app.MyApplication;
import com.heziz.liyang.base.BaseFragment;
import com.heziz.liyang.bean.ProjectBean;
import com.heziz.liyang.bean.UserInfor;
import com.heziz.liyang.bean.rcjc.RcjcBean;
import com.heziz.liyang.network.API;
import com.heziz.liyang.network.JsonCallBack0;
import com.heziz.liyang.network.JsonCallBack1;
import com.heziz.liyang.network.OkGoClient;
import com.heziz.liyang.network.SRequstBean;
import com.heziz.liyang.ui.rcjc.HGLActivity;
import com.heziz.liyang.ui.rcjc.WDBGDZCActivity;
import com.heziz.liyang.ui.rcjc.WebviewActivity;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * new_clcx_d_cph simple {@link Fragment} subclass.
 */
public class RenwuFragment extends BaseFragment implements View.OnClickListener {

    private UserInfor userInfor;
    @BindView(R.id.tvGdzs)
    TextView tvGdzs;
    @BindView(R.id.tvGdsc)
    TextView tvGdsc;
    @BindView(R.id.tvGdwc)
    TextView tvGdwc;
    @BindView(R.id.tvHgl)
    TextView tvHgl;
    @BindView(R.id.tvHg)
    TextView tvHg;
    @BindView(R.id.tvWhg)
    TextView tvWhg;

    @BindView(R.id.tvrcxczg)
    TextView tvrcxczg;
    @BindView(R.id.tvzxjczg)
    TextView tvzxjczg;

    @BindView(R.id.tvWlzs)
    TextView tvWlzs;
    @BindView(R.id.tvWlsc)
    TextView tvWlsc;
    @BindView(R.id.tvWlwc)
    TextView tvWlwc;
    @BindView(R.id.tvWlHgl)
    TextView tvWlHgl;
    @BindView(R.id.tvWlHg)
    TextView tvWlHg;
    @BindView(R.id.tvWlWhg)
    TextView tvWlWhg;
    @BindView(R.id.llgdzc)
    LinearLayout llgdzc;
    @BindView(R.id.llwlry)
    LinearLayout llwlry;
    @BindView(R.id.llGDHgl)
    LinearLayout llGDHgl;
    @BindView(R.id.llWlhgl)
    LinearLayout llWlhgl;
    //质量安全模块
    @BindView(R.id.llZLAQJC)
    LinearLayout llZLAQJC;

    @BindView(R.id.btnWDBYGS)
    Button btnWDBYGS;
    @BindView(R.id.btnZXJC)
    Button btnZXJC;
    @BindView(R.id.btnAQJC)
    Button btnAQJC;
    @BindView(R.id.btnZLJC)
    Button btnZLJC;

    public RenwuFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_rcjc, container, false);
        ButterKnife.bind(this,view);
        initViews();
        initDatas();
        initDatas1();
        initListeners();
        return view;
    }

    private void initListeners() {
        llgdzc.setOnClickListener(this);
        llwlry.setOnClickListener(this);
        llWlhgl.setOnClickListener(this);
        llGDHgl.setOnClickListener(this);
        btnWDBYGS.setOnClickListener(this);
        btnZXJC.setOnClickListener(this);
        tvzxjczg.setOnClickListener(this);
        tvrcxczg.setOnClickListener(this);
        btnAQJC.setOnClickListener(this);
        btnZLJC.setOnClickListener(this);
    }

    private void initViews() {
        userInfor=MyApplication.getInstance().getUserInfor();

        if("3".equals(userInfor.getPosition())){
            tvzxjczg.setVisibility(View.VISIBLE);
            tvrcxczg.setVisibility(View.VISIBLE);
            llZLAQJC.setVisibility(View.GONE);
        }else if("1".equals(userInfor.getPosition())){
            llZLAQJC.setVisibility(View.VISIBLE);
        }else{
            llZLAQJC.setVisibility(View.GONE);
        }
    }

    private void initDatas() {
        showProgressDialog();
        String url = API.RCRW_WDBYGS;
        Map<String,String> params=new HashMap<>();
        params.put("access_token", userInfor.getUuid());
        if("1".equals(userInfor.getPosition())){
            params.put("station",userInfor.getStation()+"");
        }else if("2".equals(userInfor.getPosition())){
            params.put("managerId",userInfor.getManagerId()+"");
        }else if("3".equals(userInfor.getPosition())){
            params.put("createAccount",userInfor.getAccount()+"");
        }

        JsonCallBack0<String> jsonCallBack = new JsonCallBack0<String>() {
            @Override
            public void onSuccess(com.lzy.okgo.model.Response<String> response) {
                String s=response.body();
                Gson gson=new Gson();
                RcjcBean bean=gson.fromJson(s,RcjcBean.class);
                tvGdzs.setText(bean.getTotal()+"个");
                tvGdsc.setText(bean.getFinished()+"个");
                tvGdwc.setText(bean.getUnFinished()+"个");
                DecimalFormat df = new DecimalFormat("0.00");
                tvHgl.setText(df.format(bean.getPassRate()*100)+"%");
                tvHg.setText(bean.getPass()+"个");
                tvWhg.setText(bean.getUnPass()+"个");

            }

            @Override
            public void onError(com.lzy.okgo.model.Response<String> response) {
                super.onError(response);
                dissmissProgressDialog();
            }

        };
        OkGoClient.getInstance()
                .getJsonData0(url, params, jsonCallBack);
    }
    private void initDatas1() {
        String url = API.RCRW_WLRY;
        Map<String,String> params=new HashMap<>();
        params.put("access_token", userInfor.getUuid());
        if("1".equals(userInfor.getPosition())){
            params.put("station",userInfor.getStation()+"");
        }else if("2".equals(userInfor.getPosition())){
            params.put("managerId",userInfor.getManagerId()+"");
        }else if("3".equals(userInfor.getPosition())){
            params.put("createAccount",userInfor.getAccount()+"");
        }
        JsonCallBack0<String> jsonCallBack = new JsonCallBack0<String>() {
            @Override
            public void onSuccess(com.lzy.okgo.model.Response<String> response) {
                dissmissProgressDialog();
                String s=response.body();
                Gson gson=new Gson();
                RcjcBean bean=gson.fromJson(s,RcjcBean.class);
                tvWlzs.setText(bean.getTotal()+"个");
                tvWlsc.setText(bean.getFinished()+"个");
                tvWlwc.setText(bean.getUnFinished()+"个");
                DecimalFormat df = new DecimalFormat("0.00");
                tvWlHgl.setText(df.format(bean.getPassRate()*100)+"%");
                tvWlHg.setText(bean.getPass()+"个");
                tvWlWhg.setText(bean.getUnPass()+"个");

            }

            @Override
            public void onError(com.lzy.okgo.model.Response<String> response) {
                super.onError(response);
                dissmissProgressDialog();
            }

        };
        OkGoClient.getInstance()
                .getJsonData0(url, params, jsonCallBack);
    }

    @Override
    public void onClick(View v) {
        Intent intent=new Intent();
        switch (v.getId()){
            case R.id.llgdzc:
                intent.setClass(getActivity(),WDBGDZCActivity.class);
                intent.putExtra("type","1");
                startActivity(intent);
                break;
            case R.id.llwlry:
                intent.setClass(getActivity(),WDBGDZCActivity.class);
                intent.putExtra("type","2");
                startActivity(intent);
                break;
            case R.id.llGDHgl:
                intent.setClass(getActivity(),HGLActivity.class);
                intent.putExtra("type","1");
                startActivity(intent);
                break;
            case R.id.llWlhgl:
                intent.setClass(getActivity(),HGLActivity.class);
                intent.putExtra("type","2");
                startActivity(intent);
                break;
            case R.id.btnWDBYGS:
                if(userInfor.getPosition().equals("1")){
                   startA(1);
                }else if(userInfor.getPosition().equals("2")){
                   startB(1);
                }else if(userInfor.getPosition().equals("3")){
                    getIDS(1);
                }
                break;
            case R.id.btnZXJC:
                if(userInfor.getPosition().equals("1")){
                    startA(2);
                }else if(userInfor.getPosition().equals("2")){
                    startB(2);
                }else if(userInfor.getPosition().equals("3")){
                    getIDS(2);
                }
                break;
            case R.id.tvrcxczg:
//                if(userInfor.getPosition().equals("1")){
//                   startA(3);
//                }else if(userInfor.getPosition().equals("2")){
//                   startB(3);
//                }else
                    if(userInfor.getPosition().equals("3")){
                    getIDS(3);
                }
                break;
            case R.id.tvzxjczg:
//                if(userInfor.getPosition().equals("1")){
//                   startA(4);
//                }else if(userInfor.getPosition().equals("2")){
//                    startB(4);
//                }else
                    if(userInfor.getPosition().equals("3")){
                    getIDS(4);
                }
                break;
            case R.id.btnAQJC:
                startC(6,3);
                break;
            case R.id.btnZLJC:
                startC(7,4);
                break;
        }

    }

    private void startA(int id){
        Intent intent=new Intent();
        intent.putExtra("id",id);
        intent.setClass(getActivity(),WebviewActivity.class);
        intent.putExtra("mWebUrl",API.WEB_URL1+"?uuid="+userInfor.getUuid()+"&mold="+id+"&siteId="+userInfor.getStation());
        startActivity(intent);
    }
    private void startB(int id){
        Intent intent=new Intent();
        intent.putExtra("id",id);
        intent.setClass(getActivity(),WebviewActivity.class);
        intent.putExtra("mWebUrl",API.WEB_URL1+"?uuid="+userInfor.getUuid()+"&mold="+id+"&managerId="+userInfor.getManagerId());
        startActivity(intent);
    }
    private void startC(int id,int mold){
        Intent intent=new Intent();
        intent.putExtra("id",id);
        intent.setClass(getActivity(),WebviewActivity.class);
        intent.putExtra("mWebUrl",API.WEB_URL1+"?uuid="+userInfor.getUuid()+"&mold="+mold+"&siteId="+API.STATION);
        startActivity(intent);
    }
    private void getIDS(int id){
        String url1 = API.PROJECT_LIST+"?access_token="+ MyApplication.getInstance().getUserInfor().getUuid();
        Map<String,String> params=new HashMap<>();
        params.put("station",API.STATION);
        params.put("createName",userInfor.getAccount());
        JsonCallBack1<SRequstBean<List<ProjectBean>>> jsonCallBack1 = new JsonCallBack1<SRequstBean<List<ProjectBean>>>() {
            @Override
            public void onSuccess(com.lzy.okgo.model.Response<SRequstBean<List<ProjectBean>>> response) {

                List<ProjectBean> list=response.body().getData();
                if(list.size()!=0){
                    Intent intent=new Intent();
                    intent.putExtra("id",id);
                    intent.setClass(getActivity(),WebviewActivity.class);
                    intent.putExtra("mWebUrl","https://xm.heziz.com/loginProject?uuid="+userInfor.getUuid()+"&mold="+id+"&proId="+list.get(0).getId());
                    startActivity(intent);
                }


            }

            @Override
            public void onError(com.lzy.okgo.model.Response<SRequstBean<List<ProjectBean>>> response) {
                super.onError(response);
                dissmissProgressDialog();
            }

        };
        OkGoClient.getInstance()
                .postJsonData(url1, params, jsonCallBack1);
    }


}
//https://app.heziz.com/loginApp?token=ea7c6c85-6460-4769-beff-7a65eb54d712&mold=1
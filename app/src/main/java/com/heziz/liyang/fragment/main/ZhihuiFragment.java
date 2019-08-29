package com.heziz.liyang.fragment.main;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.heziz.liyang.R;
import com.heziz.liyang.app.MyApplication;
import com.heziz.liyang.base.BaseFragment;
import com.heziz.liyang.bean.ProjectBean;
import com.heziz.liyang.bean.UserInfor;
import com.heziz.liyang.bean.car.ClcxZlxBean;
import com.heziz.liyang.bean.sp.SpDevicezlxNumBean;
import com.heziz.liyang.bean.yc.YcDevicezlxNumBean;
import com.heziz.liyang.bean.zh.FDLBean;
import com.heziz.liyang.network.API;
import com.heziz.liyang.network.JsonCallBack1;
import com.heziz.liyang.network.OkGoClient;
import com.heziz.liyang.network.SRequstBean;
import com.heziz.liyang.ui.zhihui.clwcx.ClcxStreetProjectActivity;
import com.heziz.liyang.ui.zhihui.clwcx.ClwcxStreetDeviceActivity;
import com.heziz.liyang.ui.zhihui.fdl.FDLProjectListActivity;
import com.heziz.liyang.ui.zhihui.fdl.FdlStreetDeviceListActivity;
import com.heziz.liyang.ui.zhihui.sp.SpStreetDeviceListActivity;
import com.heziz.liyang.ui.zhihui.sp.SpStreetProjectActivity;
import com.heziz.liyang.ui.zhihui.yc.StreetYcDeviceListActivity;
import com.heziz.liyang.ui.zhihui.yc.YcStreetProjectListActivity;
import com.heziz.liyang.utils.LogUtils;
import com.heziz.liyang.utils.NumberUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * new_clcx_d_cph simple {@link Fragment} subclass.
 */
public class ZhihuiFragment extends BaseFragment implements View.OnClickListener {

    @BindView(R.id.llYc)
    LinearLayout llYc;
    @BindView(R.id.yc_total)
    TextView yc_total;
    @BindView(R.id.yc_onLine)
    TextView yc_onLine;
    @BindView(R.id.yc_offline)
    TextView yc_offline;

    @BindView(R.id.llSp)
    LinearLayout llSp;
    @BindView(R.id.sp_total)
    TextView sp_total;
    @BindView(R.id.sp_onLine)
    TextView sp_onLine;
    @BindView(R.id.sp_offline)
    TextView sp_offline;

    @BindView(R.id.llCar)
    LinearLayout llCar;
    @BindView(R.id.car_total)
    TextView car_total;
    @BindView(R.id.car_onLine)
    TextView car_onLine;
    @BindView(R.id.car_offline)
    TextView car_offline;

    @BindView(R.id.llDl)
    LinearLayout llDl;
    @BindView(R.id.dl_yt)
    TextView dl_yt;
    @BindView(R.id.dl_pc)
    TextView dl_pc;
    @BindView(R.id.dl_yl)
    TextView dl_yl;

    private UserInfor userInfor;
    private String position;
    private StringBuffer projectIds=new StringBuffer();

    Map<String, String> paramsclnum = new HashMap<>();
    public ZhihuiFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_zhihui, container, false);
        ButterKnife.bind(this,view);
        userInfor= MyApplication.getInstance().getUserInfor();
        position=userInfor.getPosition();
        initDatas();
        initListeners();
        return view;
    }

    private void initDatas() {
        showProgressDialog();
        if(userInfor.getPosition().equals("1")){
            paramsclnum.put("cardsProjectEndpoint",userInfor.getStation()+"");
            getClNum();
        }else if(userInfor.getPosition().equals("2")){
            paramsclnum.put("managerRoleIds",userInfor.getManagerId()+"");
            getClNum();
        }else if(userInfor.getPosition().equals("3")){
            getIDS(2);
//            paramsclnum.put("createName",userInfor.getAccount());
//            getClNum();
        }
        getYcNum();
        getSpNum();

//         /*车辆未冲洗*/
//        String url1 = API.ZH_DATA1+"?access_token="+MyApplication.getInstance().getUserInfor().getUuid();
//        Map<String,String> params1=new HashMap<>();
//        if(position.equals("1")){
//            params1.put("station",userInfor.getStation()+"");
//        }else if(position.equals("2")){
//            params1.put("managerRoleIds",userInfor.getManagerId()+"");
//        }else if (position.equals("3")){
//            params1.put("createName",userInfor.getAccount());
//        }
//
//        JsonCallBack1<SRequstBean<String>> jsonCallBack1 = new JsonCallBack1<SRequstBean<String>>() {
//            @Override
//            public void onSuccess(com.lzy.okgo.model.Response<SRequstBean<String>> response) {
//
//                String s=response.body().getData().replace("\\","");
//                LogUtils.show(s);
//                Gson gson=new Gson();
//                ZHDataBean bean=gson.fromJson(s, ZHDataBean.class);
//                    yc_offline.setText(bean.getWeatherOfflineCount());
//                    yc_onLine.setText(bean.getWeatherOnlineCount());
//                    yc_total.setText(Integer.valueOf(bean.getWeatherOnlineCount())+Integer.valueOf(bean.getWeatherOfflineCount())+"");
//                    car_total.setText(bean.getFtpDeviceCount());
//                    sp_total.setText(Integer.valueOf(bean.getVideoOfflineCount())+Integer.valueOf(bean.getVideoOnlineCount())+Integer.valueOf(bean.getVideoUnknowCount())+"");
//                    sp_onLine.setText(bean.getVideoOnlineCount());
//                    sp_offline.setText(bean.getVideoOfflineCount());
//
//            }
//
//            @Override
//            public void onError(com.lzy.okgo.model.Response<SRequstBean<String>> response) {
//                super.onError(response);
//                dissmissProgressDialog();
//            }
//
//        };
//        OkGoClient.getInstance()
//                .postJsonData(url1, params1, jsonCallBack1);

        Map<String,String> params2=new HashMap<>();
        if(position.equals("1")){
            params2.put("siteId",userInfor.getStation()+"");
            getDLData(params2);
        }else if(position.equals("2")){
            params2.put("managerId",userInfor.getManagerId()+"");
            getDLData(params2);
        }else if(position.equals("3")){
            getIDS(1);
        }

    }

    private void getClNum() {
        String urlnum = API.CL_STREET_NUM+"?access_token="+MyApplication.getInstance().getUserInfor().getUuid();

        JsonCallBack1<SRequstBean<String>> jsonCallBacknum = new JsonCallBack1<SRequstBean<String>>() {
            @Override
            public void onSuccess(com.lzy.okgo.model.Response<SRequstBean<String>> response) {

                if (response.body().getData()!=null){
                    String res=response.body().getData().replace("\\","");
                    LogUtils.show(res);
                    Gson gson=new Gson();
                    ClcxZlxBean bean=gson.fromJson(res, ClcxZlxBean.class);
                    car_onLine.setText(bean.getOnlinecount());
                    car_offline.setText(bean.getNotonlinecount());
                    int total=Integer.valueOf(bean.getNotonlinecount())+Integer.valueOf(bean.getOnlinecount())+Integer.valueOf(bean.getUnknowcount());
                    car_total.setText(total+"");
                }


            }

            @Override
            public void onError(com.lzy.okgo.model.Response<SRequstBean<String>> response) {
                super.onError(response);
                dissmissProgressDialog();
            }

        };
        OkGoClient.getInstance()
                .postJsonData(urlnum, paramsclnum, jsonCallBacknum);
    }

    private void getSpNum() {
        String urlnum = API.SP_STREET_NUM+"?access_token="+MyApplication.getInstance().getUserInfor().getUuid();
        Map<String, String> paramsnum = new HashMap<>();
        if(userInfor.getPosition().equals("1")){
            paramsnum.put("station",userInfor.getStation()+"");
        }else if(userInfor.getPosition().equals("2")){
            paramsnum.put("managerRoleIds",userInfor.getManagerId()+"");
        }else if(userInfor.getPosition().equals("3")){
            paramsnum.put("createName",userInfor.getAccount()+"");
        }
        JsonCallBack1<SRequstBean<String>> jsonCallBacknum = new JsonCallBack1<SRequstBean<String>>() {
            @Override
            public void onSuccess(com.lzy.okgo.model.Response<SRequstBean<String>> response) {
                if(response.body().getData()!=null){
                    String res=response.body().getData().replace("\\","");
                    LogUtils.show(res);
                    Gson gson=new Gson();
                    SpDevicezlxNumBean bean=gson.fromJson(res, SpDevicezlxNumBean.class);
                    sp_onLine.setText(bean.getOnlinedevice());
                    sp_offline.setText(bean.getNotonlinedevice());
                    int total=Integer.valueOf(bean.getNotonlinedevice())+Integer.valueOf(bean.getOnlinedevice())+Integer.valueOf(bean.getUnknowdevicecount());
                    sp_total.setText(total+"");
                }

            }

            @Override
            public void onError(com.lzy.okgo.model.Response<SRequstBean<String>> response) {
                super.onError(response);
                dissmissProgressDialog();
            }

        };
        OkGoClient.getInstance()
                .postJsonData(urlnum, paramsnum, jsonCallBacknum);
    }

    private void getYcNum() {
        String urlnum = API.YC_STREET_NUM;
        Map<String, String> paramsnum = new HashMap<>();
        if(userInfor.getPosition().equals("1")){
            paramsnum.put("station",userInfor.getStation()+"");
        }else if(userInfor.getPosition().equals("2")){
            paramsnum.put("managerRoleIds",userInfor.getManagerId()+"");
        }else if(userInfor.getPosition().equals("3")){
            paramsnum.put("createName",userInfor.getAccount()+"");
        }
        JsonCallBack1<SRequstBean<String>> jsonCallBacknum = new JsonCallBack1<SRequstBean<String>>() {
            @Override
            public void onSuccess(com.lzy.okgo.model.Response<SRequstBean<String>> response) {

                if(response.body().getData()!=null){
                    String res=response.body().getData().replace("\\","");
                    LogUtils.show(res);
                    Gson gson=new Gson();
                    YcDevicezlxNumBean bean=gson.fromJson(res, YcDevicezlxNumBean.class);
                    yc_onLine.setText(bean.getOnlinecount());
                    yc_offline.setText(bean.getNotonlinecount());
                    int total=Integer.valueOf(bean.getNotonlinecount())+Integer.valueOf(bean.getOnlinecount())+Integer.valueOf(bean.getUnknowcount());
                    yc_total.setText(total+"");
                }

            }

            @Override
            public void onError(com.lzy.okgo.model.Response<SRequstBean<String>> response) {
                super.onError(response);
                dissmissProgressDialog();
            }

        };
        OkGoClient.getInstance()
                .postJsonData(urlnum, paramsnum, jsonCallBacknum);
    }

    private void getDLData(Map<String, String> params2) {
        String url1 = API.ZH_DATA2+"?access_token="+ MyApplication.getInstance().getUserInfor().getUuid();
        JsonCallBack1<SRequstBean<FDLBean>> jsonCallBack1 = new JsonCallBack1<SRequstBean<FDLBean>>() {
            @Override
            public void onSuccess(com.lzy.okgo.model.Response<SRequstBean<FDLBean>> response) {
                dissmissProgressDialog();
                if(response.body().getData()!=null){
                    FDLBean fdlBean=response.body().getData();
                    if(fdlBean.getTotalRecord()==0){
                        dl_yt.setText("0%");
                    }else{
                        dl_yt.setText(NumberUtils.getTwoDecimal(fdlBean.getQualifiedNumber()*100.0/fdlBean.getTotalRecord())+"%");
                    }

                    dl_pc.setText(fdlBean.getTotalRecord()+"");
                    dl_yl.setText(fdlBean.getTotalAmount()+"");
                }else{
                    dl_yt.setText("0");
                    dl_pc.setText("0");
                    dl_yl.setText("0");
                }
            }

            @Override
            public void onError(com.lzy.okgo.model.Response<SRequstBean<FDLBean>> response) {
                super.onError(response);
                dissmissProgressDialog();
            }

        };
        OkGoClient.getInstance()
                .getJsonData1(url1, params2, jsonCallBack1);
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
                for(int i=0;i<list.size();i++){
                    projectIds.append(list.get(i).getId()+"");
                    if(i!=list.size()-1){
                        projectIds.append(",");
                    }
                }
                if(id==1){
                    Map<String,String> params2=new HashMap<>();
                    params2.put("projectIds",projectIds.toString());

                    getDLData(params2);
                }else{
                    paramsclnum.put("projectIds",projectIds.toString());
                    getClNum();
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

    private void initListeners() {
        llCar.setOnClickListener(this);
        llYc.setOnClickListener(this);
        llSp.setOnClickListener(this);
        llDl.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent=new Intent();
        switch (v.getId()){
            case R.id.llYc:
                if(userInfor.getPosition().equals("3")){
                    intent=new Intent(getActivity(), YcStreetProjectListActivity.class);
                    intent.putExtra("id","");
                    intent.putExtra("name","");
//                    intent.putExtra("projectIds",projectIds.toString());
//                intent.putExtra("object",projectBeanList.get(position).getStreet());
                }else {
                    intent.setClass(getActivity(),StreetYcDeviceListActivity.class);
                    intent.putExtra("type","yc");
                }

                break;
            case R.id.llCar:
                if(userInfor.getPosition().equals("3")){
                    intent=new Intent(getActivity(), ClcxStreetProjectActivity.class);
                    intent.putExtra("id","");
                    intent.putExtra("name","");
//                    intent.putExtra("projectIds",projectIds.toString());
//                intent.putExtra("object",projectBeanList.get(position).getStreet());
                }else {
                    intent.setClass(getActivity(),ClwcxStreetDeviceActivity.class);
                }
                break;
            case R.id.llSp:
                if(userInfor.getPosition().equals("3")){
                    intent.setClass(getActivity(),SpStreetProjectActivity.class);
                    intent.putExtra("id","");
                    intent.putExtra("name","");
                }else {
                    intent.setClass(getActivity(),SpStreetDeviceListActivity.class);
                }

//                intent.putExtra("type","sp");
                break;
            case R.id.llDl:
                if(userInfor.getPosition().equals("3")){
                    intent.setClass(getActivity(),FDLProjectListActivity.class);
                    intent.putExtra("managerId","");
//                intent.putExtra("object",projectBeanList.get(position).getManagerRoleId());
                }else{
                    intent.setClass(getActivity(),FdlStreetDeviceListActivity.class);
                }

//                intent.putExtra("type","dl");
                break;
        }
        startActivity(intent);
    }
}

package com.heziz.liyang.ui.zhihui.bjxx.clcx;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.heziz.liyang.R;
import com.heziz.liyang.adaper.car.MineCLCXGJListAdapter;
import com.heziz.liyang.app.MyApplication;
import com.heziz.liyang.base.BaseActivity;
import com.heziz.liyang.bean.ProjectBean;
import com.heziz.liyang.bean.StreetBean;
import com.heziz.liyang.bean.UserInfor;
import com.heziz.liyang.bean.mine.clcx.MineCLCXBean;
import com.heziz.liyang.network.API;
import com.heziz.liyang.network.JsonCallBack1;
import com.heziz.liyang.network.OkGoClient;
import com.heziz.liyang.network.SRequstBean;
import com.heziz.liyang.view.SpinnerPopuwindow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MineCLCXListActivity extends BaseActivity implements View.OnClickListener  {
    @BindView(R.id.rlBack)
    RelativeLayout rlBack;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    //    @BindView(R.id.llList)
//    LinearLayout llList;
    @BindView(R.id.recycleView)
    RecyclerView recyclerView;
    @BindView(R.id.btnSearch)
    Button btnSearch;
    @BindView(R.id.etName)
    EditText etName;
    @BindView(R.id.llSX)
    LinearLayout llSX;
    @BindView(R.id.llgx)
    LinearLayout llgx;
    @BindView(R.id.tvgx)
    TextView tvgx;
    @BindView(R.id.lljd)
    LinearLayout lljd;
    @BindView(R.id.tvjd)
    TextView tvjd;
    @BindView(R.id.lllx)
    LinearLayout lllx;
    @BindView(R.id.tvlx)
    TextView tvlx;
    @BindView(R.id.llsx)
    LinearLayout llsx;
    @BindView(R.id.llcb)
    LinearLayout llcb;
    @BindView(R.id.llaz)
    LinearLayout llaz;
    @BindView(R.id.tvsx)
    TextView tvsx;
    /** 管辖级别数据*/
    private List<String> gxData;
    /** 街道数据*/
    private List<StreetBean> streetBeanList=new ArrayList<>();
    private List<String> jdData;
    /** 项目类型数据*/
    private List<String> lxData;
    /** 项目属性数据*/
    private List<String> sxData;
    private String type_gx;
    private String type_jd;
    private String type_lx;
    private String type_sx;
    MineCLCXGJListAdapter adapter;
    private List<MineCLCXBean> projectBeanList=new ArrayList<>();
    private SpinnerPopuwindow gxSpinnerPopuwindow;
    private SpinnerPopuwindow jdSpinnerPopuwindow;
    private SpinnerPopuwindow lxSpinnerPopuwindow;
    private SpinnerPopuwindow sxSpinnerPopuwindow;
    Map<String,String> params1=new HashMap<>();
    private UserInfor userInfor;
    private Activity mContext;
    private StringBuffer projectIds=new StringBuffer();
    private int pageNow=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_yc_list);
        ButterKnife.bind(this);
        initViews();
        initDatas();
        initListeners();
    }
    private void initListeners() {
        btnSearch.setOnClickListener(this);
        llgx.setOnClickListener(this);
        if(userInfor.getPosition().equals("1")){
            lljd.setOnClickListener(this);
        }
        lllx.setOnClickListener(this);
        llsx.setOnClickListener(this);
        rlBack.setOnClickListener(this);

//        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
//            @Override
//            public void onLoadMoreRequested() {
//                pageNow++;
//                if(userInfor.getPosition().equals("3")){
//                    initProjectData();
//                }else{
//                    getIDS();
//                }
//            }
//        });
    }

    private void initDatas() {

        showProgressDialog();
        String url = API.STREET_LIST;
        Map<String,String> params=new HashMap<>();
        JsonCallBack1<SRequstBean<List<StreetBean>>> jsonCallBack = new JsonCallBack1<SRequstBean<List<StreetBean>>>() {
            @Override
            public void onSuccess(com.lzy.okgo.model.Response<SRequstBean<List<StreetBean>>> response) {
                List<StreetBean> list=response.body().getData();
                if(userInfor.getPosition().equals("2")){
                    for(int i=0;i<list.size();i++){
                        if(String.valueOf(list.get(i).getId()).equals(userInfor.getManagerId())){
                            streetBeanList.add(list.get(i));
                            if(userInfor.getPosition().equals("2")){
                                for(int j=0;j<list.size();j++){
                                    if(userInfor.getManagerId().equals(list.get(j).getId()+"")){
                                        tvjd.setText(list.get(j).getName());
                                    }
                                }
                            }
                        }
                    }
                }else{
                    streetBeanList.addAll(response.body().getData());
                }
                StreetData();
            }

            @Override
            public void onError(com.lzy.okgo.model.Response<SRequstBean<List<StreetBean>>> response) {
                super.onError(response);
                dissmissProgressDialog();
            }

        };
        OkGoClient.getInstance()
                .getJsonData1(url, params, jsonCallBack);


        params1.put("name","");
        params1.put("vasa","");
        params1.put("managerRoleIds","");
        params1.put("pType","");
        params1.put("diff","");

        params1.put("licensePlateColor","黄");
        params1.put("licenseType","-1");

        if(userInfor.getPosition().equals("3")){
//            getIDS();
            params1.put("createName",userInfor.getAccount()+"");
            initProjectData();
        }else if(userInfor.getPosition().equals("2")){
            params1.put("managerRoleIds","["+userInfor.getManagerId()+"]");
            initProjectData();
        }else if(userInfor.getPosition().equals("1")){
            params1.put("station",userInfor.getStation()+"");
            initProjectData();
        }


    }
    private void getIDS(){
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
                params1.put("projectIds",projectIds.toString());
                initProjectData();

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

    private void initProjectData() {
        String url1 = API.MINE_CLCX_LIST+"/"+pageNow+"/10"+"?access_token="+ MyApplication.getInstance().getUserInfor().getUuid();

        JsonCallBack1<SRequstBean<List<MineCLCXBean>>> jsonCallBack1 = new JsonCallBack1<SRequstBean<List<MineCLCXBean>>>() {
            @Override
            public void onSuccess(com.lzy.okgo.model.Response<SRequstBean<List<MineCLCXBean>>> response) {
                projectBeanList.clear();
                projectBeanList.addAll(response.body().getData());
                adapter.notifyDataSetChanged();
                dissmissProgressDialog();
            }

            @Override
            public void onError(com.lzy.okgo.model.Response<SRequstBean<List<MineCLCXBean>>> response) {
                super.onError(response);
                dissmissProgressDialog();
            }

        };
        OkGoClient.getInstance()
                .postJsonData(url1, params1, jsonCallBack1);
    }

    private void initViews() {
        mContext=this;
        tvTitle.setText("车辆冲洗告警信息");
        llcb.setVisibility(View.GONE);
        llaz.setVisibility(View.GONE);
//        llList.setVisibility(View.VISIBLE);
        userInfor=MyApplication.getInstance().getUserInfor();

        adapter=new MineCLCXGJListAdapter(mContext,projectBeanList);

        LinearLayoutManager manager=new LinearLayoutManager(mContext.getApplicationContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        adapter.bindToRecyclerView(recyclerView);
        adapter.setEmptyView(R.layout.empty_view);
//        adapter.bindToRecyclerView(recyclerView);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent=new Intent(mContext, MineCLCXProjectXXActivity.class);
                intent.putExtra("mineYcListBean",projectBeanList.get(position));
                startActivity(intent);
            }
        });

        GXData();
        LXData();
        SXData();
    }


    private void go() {

        Window window;
        window = getWindow();
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(window.getDecorView().getWindowToken(), 0);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.llgx:
                type_gx = tvgx.getText().toString();
                gxSpinnerPopuwindow = new SpinnerPopuwindow(mContext,type_gx,gxData,gxitemsOnClick);
                gxSpinnerPopuwindow.showPopupWindow(llSX);
                gxSpinnerPopuwindow.setTitleText("管辖级别");//给下拉列表设置标题
                break;
            case R.id.lljd:
                type_jd = tvjd.getText().toString();
                jdSpinnerPopuwindow = new SpinnerPopuwindow(mContext,type_jd,jdData,jditemsOnClick);
                jdSpinnerPopuwindow.showPopupWindow(llSX);
                jdSpinnerPopuwindow.setTitleText("街道");//给下拉列表设置标题
                break;
            case R.id.lllx:
                type_lx = tvlx.getText().toString();
                lxSpinnerPopuwindow = new SpinnerPopuwindow(mContext,type_lx,lxData,lxitemsOnClick);
                lxSpinnerPopuwindow.showPopupWindow(llSX);
                lxSpinnerPopuwindow.setTitleText("项目类型");//给下拉列表设置标题
                break;
            case R.id.llsx:
                type_sx = tvsx.getText().toString();
                sxSpinnerPopuwindow = new SpinnerPopuwindow(mContext,type_sx,sxData,sxitemsOnClick);
                sxSpinnerPopuwindow.showPopupWindow(llSX);
                sxSpinnerPopuwindow.setTitleText("项目属性");//给下拉列表设置标题
                break;
            case R.id.btnSearch:
                go();
                String s=etName.getText().toString();
                params1.put("name",s);
                refresh();
                break;
            case R.id.rlBack:
                finish();
                break;
        }
    }
    private void refresh(){
//        projectBeanList.clear();
//        pageNow=1;
        showProgressDialog();
        initProjectData();
    }
    private AdapterView.OnItemClickListener gxitemsOnClick = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            String value = gxData.get(gxSpinnerPopuwindow.getText());
            tvgx.setText(value);
            gxSpinnerPopuwindow.dismissPopupWindow();
            if(position==0){
                params1.put("vasa","");
            }else{
                params1.put("vasa",position+"");
            }
            refresh();
        }
    };
    private AdapterView.OnItemClickListener jditemsOnClick = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            String value = jdData.get(jdSpinnerPopuwindow.getText());
            tvjd.setText(value);
            jdSpinnerPopuwindow.dismissPopupWindow();
            if(userInfor.getPosition().equals("2")){

            }else{
                if(position==0){
                    params1.put("managerRoleIds","");
                }else{
                    params1.put("managerRoleIds","["+streetBeanList.get(position-1).getId()+"]");
                }
            }

            refresh();
        }
    };
    private AdapterView.OnItemClickListener lxitemsOnClick = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            String value = lxData.get(lxSpinnerPopuwindow.getText());
            tvlx.setText(value);
            lxSpinnerPopuwindow.dismissPopupWindow();
            if(position==0){
                params1.put("pType","");
            }else{
                params1.put("pType",position+"");
            }
            refresh();
        }
    };
    private AdapterView.OnItemClickListener sxitemsOnClick = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            String value = sxData.get(sxSpinnerPopuwindow.getText());
            tvsx.setText(value);
            sxSpinnerPopuwindow.dismissPopupWindow();
            if(position==0){
                params1.put("diff","");
                tvsx.setText("项目属性");
            }else if(position==1){
                params1.put("diff",position+"");
                tvsx.setText(value);
            }else if(position==2){
                params1.put("diff",position+"");
                tvsx.setText(value);
            }else if(position==3){
                params1.put("diff","1,2");
                tvsx.setText(value);
            }else if(position==3){
                params1.put("diff","0");
                tvsx.setText(value);
            }
            refresh();
        }
    };
    /**
     * 管辖级别数据
     */
    private void GXData() {
        gxData = new ArrayList<>();
        gxData.add("全部");
        gxData.add("市直管");
        gxData.add("区直管");
    }
    /**
     * 街道数据
     */
    private void StreetData() {
        jdData = new ArrayList<>();
        jdData.add("全部");
        for(int i=0;i<streetBeanList.size();i++){
            jdData.add(streetBeanList.get(i).getName());
        }

    }
    /**
     * 街道数据
     */
    private void LXData() {
        lxData = new ArrayList<>();
        lxData.add("全部");
        lxData.add("房建");
        lxData.add("市政");
        lxData.add("交通");
        lxData.add("轨道");
        lxData.add("水务");
        lxData.add("园林");
        lxData.add("其他");
    }
    /**
     * 项目属性数据
     */
    private void SXData() {
        sxData = new ArrayList<>();
        sxData.add("全部");
         sxData.add("信息化工地");
  sxData.add("智慧工地");
   sxData.add("智慧&信息化工地");
 sxData.add("未申报智慧工地");
    }
}


package com.heziz.liyang.ui.rcjc;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.heziz.liyang.R;
import com.heziz.liyang.adaper.xmjc.rcjc.WGXMListAdapter;
import com.heziz.liyang.adaper.xmjc.rcjc.WGXMWCListAdapter;
import com.heziz.liyang.app.MyApplication;
import com.heziz.liyang.base.BaseActivity;
import com.heziz.liyang.bean.StreetBean;
import com.heziz.liyang.bean.UserInfor;
import com.heziz.liyang.bean.rcjc.WgxmListBean;
import com.heziz.liyang.network.API;
import com.heziz.liyang.network.JsonCallBack1;
import com.heziz.liyang.network.OkGoClient;
import com.heziz.liyang.network.RequestBean;
import com.heziz.liyang.network.SRequstBean;
import com.heziz.liyang.view.SpinnerPopuwindow1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProjectCheckNumDetailsActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.rlBack)
    RelativeLayout rlBack;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.recycleView)
    RecyclerView recycleView;

    @BindView(R.id.etName)
    EditText etName;
    @BindView(R.id.ivSearch)
    ImageView ivSearch;
    @BindView(R.id.tb)
    TabLayout tb;

    //辖区按钮
    @BindView(R.id.tvFl)
    TextView tvFl;
    @BindView(R.id.llFL)
    LinearLayout llFL;
    /**分类数据*/
    /** 街道数据*/
    private List<StreetBean> streetBeanList=new ArrayList<>();
    private List<String> flData;
    private String type_fl;
    private SpinnerPopuwindow1 flSpinnerPopuwindow;

    private int type;
    private UserInfor userInfor;

    Map<String,String> params1=new HashMap<>();
    Map<String,String> params2=new HashMap<>();
    private int pageNow=1;
    private int tabPositon;

    private WGXMListAdapter adapter;
    private WGXMWCListAdapter adapterWC;
    private List<WgxmListBean> wgxmListBeanList=new ArrayList<>();
    private int yc;
    private int wc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_check_num_details);

        ButterKnife.bind(this);
        initViews();

        initDatas();

        initListeners();
    }

    private void initViews() {
        userInfor= MyApplication.getInstance().getUserInfor();
        type=getIntent().getIntExtra("type",0);
        yc=getIntent().getIntExtra("YC",0);
        wc=getIntent().getIntExtra("WC",0);
        if(type==1){
            tvTitle.setText("网络人员检查情况");
        }else{
            tvTitle.setText("项目方自查情况");
        }
        tb.addTab(tb.newTab().setText("已查项目  "+yc));
        tb.addTab(tb.newTab().setText("未查项目  "+wc));

        adapter=new WGXMListAdapter(this,wgxmListBeanList,type);

        adapterWC=new WGXMWCListAdapter(this,wgxmListBeanList,type);

        LinearLayoutManager manager=new LinearLayoutManager(getApplicationContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recycleView.setLayoutManager(manager);

        adapter.bindToRecyclerView(recycleView);
        adapter.setEmptyView(R.layout.empty_view);

        adapterWC.bindToRecyclerView(recycleView);
        adapterWC.setEmptyView(R.layout.empty_view);

        recycleView.setAdapter(adapter);


    }

    private void initDatas() {
        getStreetData();
        getList();
    }

    private void refresh(){
        wgxmListBeanList.clear();
        pageNow=1;
        showProgressDialog();
        getList();
    }

    private void getList(){
        String url1 = API.WG_XM_LIST+"?access_token="+ MyApplication.getInstance().getUserInfor().getUuid();
        if(userInfor.getPosition().equals("1")){
            params1.put("siteId",API.STATION);
        }else {
            params1.put("managerRoleIds",userInfor.getManagerId()+"");
        }
       if(type==1){
           if(tabPositon==0){
               params1.put("alreadyCheck","1");
               params1.put("checkStatus","1");
               params1.remove("off");
               params1.put("latest","2");
               params1.put("xoff","1");
           }else{
               params1.put("alreadyCheck","1");
               params1.put("checkStatus","2");
               params1.remove("off");
               params1.put("latest","2");
               params1.put("xoff","1");
           }
       }else{
           if(tabPositon==0){
               params1.put("alreadyCheck","1");
               params1.put("checkStatus","1");
               params1.put("off","1");
               params1.put("latest","1");
               params1.remove("xoff");
           }else{
               params1.put("alreadyCheck","1");
               params1.put("checkStatus","2");
               params1.put("off","1");
               params1.put("latest","1");
               params1.remove("xoff");
           }
       }
        params2.put("pageNow",pageNow+"");
        params2.put("pageSize","20");
        JsonCallBack1<SRequstBean<RequestBean<List<WgxmListBean>>>> jsonCallBack1 = new JsonCallBack1<SRequstBean<RequestBean<List<WgxmListBean>>>>() {
            @Override
            public void onSuccess(com.lzy.okgo.model.Response<SRequstBean<RequestBean<List<WgxmListBean>>>> response) {
                dissmissProgressDialog();
                if (response.body().getData()!=null){

                    if(response.body().getData().getList().size()!=0){
                        wgxmListBeanList.addAll(response.body().getData().getList());
                        if(wgxmListBeanList.size()<20){
                            if(tabPositon==0){
                                adapter.loadMoreEnd();
                            }else{
                                adapterWC.loadMoreEnd();
                            }

                        }else{
                            if(tabPositon==0){
                                adapter.loadMoreComplete();
                            }else{
                                adapterWC.loadMoreComplete();
                            }

                        }
//
                    }else{
                        if(tabPositon==0){
                            adapter.loadMoreEnd();
                        }else{
                            adapterWC.loadMoreEnd();
                        }
                    }
                }else{
                    if(tabPositon==0){
                        adapter.loadMoreFail();
                    }else{
                        adapterWC.loadMoreFail();
                    }
                }
//
                if(tabPositon==0){
                    recycleView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }else{
                    recycleView.setAdapter(adapterWC);
                    adapterWC.notifyDataSetChanged();
                }

            }

            @Override
            public void onError(com.lzy.okgo.model.Response<SRequstBean<RequestBean<List<WgxmListBean>>>> response) {
                super.onError(response);
                if(tabPositon==0){
                    adapter.loadMoreFail();
                }else{
                    adapterWC.loadMoreFail();
                }
                //adapter.loadMoreFail();
                dissmissProgressDialog();
            }

        };
        OkGoClient.getInstance()
                .postJsonData2(url1, params1,params2,jsonCallBack1);
    }

    private void initListeners() {

        rlBack.setOnClickListener(this);
        llFL.setOnClickListener(this);
        ivSearch.setOnClickListener(this);
        tb.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                tabPositon=tab.getPosition();
                refresh();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                tabPositon=tab.getPosition();
                refresh();
            }
        });
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                pageNow++;
                getList();
            }
        });
        adapterWC.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                pageNow++;
                getList();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rlBack:
                finish();
                break;
            case R.id.llFL:
                type_fl = tvFl.getText().toString();
                flSpinnerPopuwindow = new SpinnerPopuwindow1(ProjectCheckNumDetailsActivity.this,type_fl,flData,flitemsOnClick);
                flSpinnerPopuwindow.showPopupWindow(llFL);
                flSpinnerPopuwindow.setTitleText("分类");//给下拉列表设置标题
                break;
            case R.id.ivSearch:
                params1.put("projectName",etName.getText().toString());
                refresh();
                break;
        }
    }
    private AdapterView.OnItemClickListener flitemsOnClick = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            String value = flData.get(flSpinnerPopuwindow.getText());
            tvFl.setText(value);
            flSpinnerPopuwindow.dismissPopupWindow();
            if(position==0){
                params1.remove("managerRoleIds");
            }else{
                params1.put("managerRoleIds",streetBeanList.get(position-1).getId()+"");
            }
            refresh();
        }
    };
    private void getStreetData(){
        String url = API.STREET_LIST;
        Map<String,String> params1=new HashMap<>();
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
                                        tvFl.setText(list.get(j).getName());
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
                .getJsonData1(url, params1, jsonCallBack);
    }
    /**
     * 街道数据
     */
    private void StreetData() {
        flData = new ArrayList<>();
        flData.add("全部");
        for(int i=0;i<streetBeanList.size();i++){
            flData.add(streetBeanList.get(i).getName());
        }
    }

}

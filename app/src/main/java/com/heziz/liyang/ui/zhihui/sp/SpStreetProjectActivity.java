package com.heziz.liyang.ui.zhihui.sp;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.heziz.liyang.R;
import com.heziz.liyang.adaper.sp.MySpExtendableListViewAdapter;
import com.heziz.liyang.app.MyApplication;
import com.heziz.liyang.base.BaseActivity;
import com.heziz.liyang.bean.StreetBean;
import com.heziz.liyang.bean.UserInfor;
import com.heziz.liyang.bean.sp.ProjectVideoBean;
import com.heziz.liyang.bean.sp.SpDevicezlxNumBean;
import com.heziz.liyang.bean.sp.SpProjectBean;
import com.heziz.liyang.bean.sp.VideoProjectBean;
import com.heziz.liyang.network.API;
import com.heziz.liyang.network.JsonCallBack1;
import com.heziz.liyang.network.OkGoClient;
import com.heziz.liyang.network.RequestBean;
import com.heziz.liyang.network.SRequstBean;
import com.heziz.liyang.utils.LogUtils;
import com.heziz.liyang.view.SpinnerPopuwindow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SpStreetProjectActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.ivIcon)
    ImageView ivIcon;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.tvTotal)
    TextView tvTotal;
    @BindView(R.id.tvOnLine)
    TextView tvOnLine;
    @BindView(R.id.tvOffline)
    TextView tvOffline;
    @BindView(R.id.rlBack)
    RelativeLayout rlBack;

    @BindView(R.id.expanded_menu)
    ExpandableListView expanded_menu;

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

    @BindView(R.id.tvJdTitle)
    TextView tvJdTitle;
    @BindView(R.id.llsx)
    LinearLayout llsx;
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
    private SpinnerPopuwindow gxSpinnerPopuwindow;
    private SpinnerPopuwindow jdSpinnerPopuwindow;
    private SpinnerPopuwindow lxSpinnerPopuwindow;
    private SpinnerPopuwindow sxSpinnerPopuwindow;
    Map<String,String> params1=new HashMap<>();
    private UserInfor userInfor;
    private Activity mContext;
    private String managerRoleIds;
    private String name;
    MySpExtendableListViewAdapter adapter;
    List<SpProjectBean> list=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sp_street_project);
        ButterKnife.bind(this);

        initViews();
        initDatas();
        initListeners();
    }
    private void initViews() {
        mContext=this;
        userInfor= MyApplication.getInstance().getUserInfor();
        tvTitle.setText("视频监控");
        tvJdTitle.setText("项目名称");
        ivIcon.setImageResource(R.drawable.zh_sp_icon1);
        managerRoleIds=getIntent().getStringExtra("id");
        if(!userInfor.getPosition().equals("3")){
            name=getIntent().getStringExtra("name");
            tvjd.setText(name);
        }
        adapter=new MySpExtendableListViewAdapter(SpStreetProjectActivity.this,list);
        expanded_menu.setGroupIndicator(null);
        expanded_menu.setAdapter(adapter);
        GXData();
        LXData();
        SXData();
    }

    private void initDatas() {
        String urlnum = API.SP_STREET_NUM+"?access_token="+MyApplication.getInstance().getUserInfor().getUuid();
        Map<String, String> paramsnum = new HashMap<>();
        if(userInfor.getPosition().equals("1")){
            paramsnum.put("managerRoleIds",managerRoleIds);
        }else if(userInfor.getPosition().equals("2")){
            paramsnum.put("managerRoleIds",managerRoleIds);
        }else if(userInfor.getPosition().equals("3")){
            paramsnum.put("createName",userInfor.getAccount()+"");
        }
        JsonCallBack1<SRequstBean<String>> jsonCallBacknum = new JsonCallBack1<SRequstBean<String>>() {
            @Override
            public void onSuccess(com.lzy.okgo.model.Response<SRequstBean<String>> response) {

                String res=response.body().getData().replace("\\","");
                LogUtils.show(res);
                Gson gson=new Gson();
                SpDevicezlxNumBean bean=gson.fromJson(res, SpDevicezlxNumBean.class);
                tvOnLine.setText(bean.getOnlinedevice());
                tvOffline.setText(bean.getNotonlinedevice());
                int total=Integer.valueOf(bean.getNotonlinedevice())+Integer.valueOf(bean.getOnlinedevice())+Integer.valueOf(bean.getUnknowdevicecount());
                tvTotal.setText(total+"");
            }

            @Override
            public void onError(com.lzy.okgo.model.Response<SRequstBean<String>> response) {
                super.onError(response);
                dissmissProgressDialog();
            }

        };
        OkGoClient.getInstance()
                .postJsonData(urlnum, paramsnum, jsonCallBacknum);

//        获取街道信息
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
        if(userInfor.getPosition().equals("3")){
            params1.put("createName",userInfor.getAccount());
        }else {
            params1.put("managerRoleIds",managerRoleIds);
        }
        params1.put("pType","");
        params1.put("diff","");

        initProjectData();
    }

    private void initProjectData() {
        showProgressDialog();
        String url1 = API.SP_PROJECT_LIST+"/1/500?access_token="+ MyApplication.getInstance().getUserInfor().getUuid();

        JsonCallBack1<SRequstBean<RequestBean<List<SpProjectBean>>>> jsonCallBack1 = new JsonCallBack1<SRequstBean<RequestBean<List<SpProjectBean>>>>() {
            @Override
            public void onSuccess(com.lzy.okgo.model.Response<SRequstBean<RequestBean<List<SpProjectBean>>>> response) {
                list.clear();
                if(response.body().getData()!=null){
                    if(response.body().getData().getList()!=null){
                        list.addAll(response.body().getData().getList());
                        for(int i=0;i<list.size();i++){
                            List<ProjectVideoBean<VideoProjectBean>> dBeanList=new ArrayList<>();
//                    dBeanList.add(new YcProjectDBean<YcProjectDeviceBean>());
                            list.get(i).setDavstring(dBeanList);
                        }
                    }
                }
                adapter.notifyDataSetChanged();
//                projectBeanList.clear();
//                projectBeanList.addAll(response.body().getData());
//                adapter.notifyDataSetChanged();
//                showMark(projectBeanList);
                dissmissProgressDialog();
            }

            @Override
            public void onError(com.lzy.okgo.model.Response<SRequstBean<RequestBean<List<SpProjectBean>>>> response) {
                super.onError(response);
                dissmissProgressDialog();
            }

        };
        OkGoClient.getInstance()
                .postJsonData(url1, params1, jsonCallBack1);
    }

    private void initListeners() {
        rlBack.setOnClickListener(this);

        btnSearch.setOnClickListener(this);
        llgx.setOnClickListener(this);
        if(userInfor.getPosition().equals("3")){
            lljd.setOnClickListener(this);
        }
        lllx.setOnClickListener(this);
        llsx.setOnClickListener(this);

        expanded_menu.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                int count = adapter.getGroupCount();
                for(int i = 0;i < count;i++){
                    if (i!=groupPosition){
                        expanded_menu.collapseGroup(i);
                    }
                }
            }
        });
        //设置分组的监听
        expanded_menu.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
//                Toast.makeText(getApplicationContext(), groupString[groupPosition], Toast.LENGTH_SHORT).show();
                if(!parent.isGroupExpanded(groupPosition)){
                    if(list.get(groupPosition).getDavstring().size()==0){
                        getYcDevice(groupPosition,list.get(groupPosition).getProjectId());
                    }

                }

                return false;
            }
        });
        //设置子项布局监听
        expanded_menu.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
//                Toast.makeText(getApplicationContext(), childString[groupPosition][childPosition], Toast.LENGTH_SHORT).show();
                return true;

            }
        });
    }

    private void getYcDevice(int position,String id){
        showProgressDialog();
        String url = API.SP_LIST;
        Map<String,String> params=new HashMap<>();
        params.put("projectId",id+"");
        JsonCallBack1<SRequstBean<RequestBean<List<ProjectVideoBean<VideoProjectBean>>>>> jsonCallBack = new JsonCallBack1<SRequstBean<RequestBean<List<ProjectVideoBean<VideoProjectBean>>>>>() {
            @Override
            public void onSuccess(com.lzy.okgo.model.Response<SRequstBean<RequestBean<List<ProjectVideoBean<VideoProjectBean>>>>> response) {

                dissmissProgressDialog();
//                list=response.body().getData().getList();
//                Log.w("main","扬尘设备："+response.body().getData().size());
                if (list.get(position).getDavstring().size()==0){
                    list.get(position).getDavstring().addAll(response.body().getData().getList());
                    adapter.notifyDataSetChanged();
                }


            }

            @Override
            public void onError(com.lzy.okgo.model.Response<SRequstBean<RequestBean<List<ProjectVideoBean<VideoProjectBean>>>>> response) {
                super.onError(response);
                dissmissProgressDialog();
            }

        };
        OkGoClient.getInstance()
                .postJsonData1(url, params, "mVideoProjectDevice", jsonCallBack);
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
                initProjectData();
                break;
            case R.id.rlBack:
                finish();
                break;
        }
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
            initProjectData();
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

            initProjectData();
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
            initProjectData();
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
            }else if(position==4){
                params1.put("diff","0");
                tvsx.setText(value);
            }
            initProjectData();
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

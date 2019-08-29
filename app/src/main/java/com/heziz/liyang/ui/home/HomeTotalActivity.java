package com.heziz.liyang.ui.home;

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
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.TextureMapView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.heziz.liyang.R;
import com.heziz.liyang.adaper.project.ProjectListAdapter;
import com.heziz.liyang.app.MyApplication;
import com.heziz.liyang.base.BaseActivity;
import com.heziz.liyang.bean.ProjectBean;
import com.heziz.liyang.bean.StreetBean;
import com.heziz.liyang.bean.UserInfor;
import com.heziz.liyang.network.API;
import com.heziz.liyang.network.JsonCallBack1;
import com.heziz.liyang.network.OkGoClient;
import com.heziz.liyang.network.RequestBean;
import com.heziz.liyang.network.SRequstBean;
import com.heziz.liyang.ui.project.ProjectDetailsActivity;
import com.heziz.liyang.view.SpinnerPopuwindow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeTotalActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.rlBack)
    RelativeLayout rlBack;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.mapview)
    TextureMapView mapView;
    @BindView(R.id.cb)
    CheckBox checkBox;
    private BaiduMap baiduMap;
    @BindView(R.id.llList)
    LinearLayout llList;
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
    @BindView(R.id.tvsx)
    TextView tvsx;
    /**
     * 管辖级别数据
     */
    private List<String> gxData;
    /**
     * 街道数据
     */
    private List<StreetBean> streetBeanList = new ArrayList<>();
    private List<String> jdData;
    /**
     * 项目类型数据
     */
    private List<String> lxData;
    /**
     * 项目属性数据
     */
    private List<String> sxData;
    private String type_gx;
    private String type_jd;
    private String type_lx;
    private String type_sx;
    ProjectListAdapter adapter;
    private List<ProjectBean> projectBeanList = new ArrayList<>();

    private SpinnerPopuwindow gxSpinnerPopuwindow;
    private SpinnerPopuwindow jdSpinnerPopuwindow;
    private SpinnerPopuwindow lxSpinnerPopuwindow;
    private SpinnerPopuwindow sxSpinnerPopuwindow;

    @BindView(R.id.llaz)
    LinearLayout llaz;
    @BindView(R.id.tvaz)
    TextView tvaz;
    /**
     * 设备安装数据
     */
    private List<String> azData;
    private String type_az;
    private SpinnerPopuwindow azSpinnerPopuwindow;

    Map<String, String> params1 = new HashMap<>();
    Map<String, String> params2 = new HashMap<>();

    private UserInfor userInfor;

    private int pageNow = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_total);
        ButterKnife.bind(this);
        initViews();
        initDatas();
        initListeners();
    }

    private void initListeners() {
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    checkBox.setBackgroundResource(R.drawable.project_ditu);
                    mapView.setVisibility(View.GONE);
                    llList.setVisibility(View.VISIBLE);
                } else {
                    checkBox.setBackgroundResource(R.drawable.project_liebiao);
                    mapView.setVisibility(View.VISIBLE);
                    llList.setVisibility(View.GONE);
                }
            }
        });
        btnSearch.setOnClickListener(this);
        llgx.setOnClickListener(this);
        lljd.setOnClickListener(this);
        lllx.setOnClickListener(this);
        llsx.setOnClickListener(this);
        llaz.setOnClickListener(this);
        rlBack.setOnClickListener(this);

        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                pageNow++;
                initProjectData();
            }
        });
    }

    private void initDatas() {

        showProgressDialog();
        String url = API.STREET_LIST;
        Map<String, String> params = new HashMap<>();
        JsonCallBack1<SRequstBean<List<StreetBean>>> jsonCallBack = new JsonCallBack1<SRequstBean<List<StreetBean>>>() {
            @Override
            public void onSuccess(com.lzy.okgo.model.Response<SRequstBean<List<StreetBean>>> response) {
                List<StreetBean> list = response.body().getData();
                if (userInfor.getPosition().equals("2")) {
                    for (int i = 0; i < list.size(); i++) {
                        if (String.valueOf(list.get(i).getId()).equals(userInfor.getManagerId())) {
                            streetBeanList.add(list.get(i));
                        }
                    }
                } else {
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


        params2.put("pageSize", "10");


        params1.put("name", "");
        params1.put("vasa", "");
        params1.put("managerRoleIds", "");
        params1.put("pType", "");
        params1.put("diff", "");
        params1.put("station", API.STATION);
        if (userInfor.getPosition().equals("3")) {
            params1.put("createName", userInfor.getAccount());
        } else if (userInfor.getPosition().equals("2")) {
            params1.put("managerRoleIds", "[" + userInfor.getManagerId() + "]");
        }
        initProjectData();

    }

    private void initProjectData() {
        String url1 = API.HOME_TOTAL_PROJECT_LIST + "?access_token=" + MyApplication.getInstance().getUserInfor().getUuid();
        params2.put("pageNow", pageNow + "");
        JsonCallBack1<SRequstBean<RequestBean<List<ProjectBean>>>> jsonCallBack1 = new JsonCallBack1<SRequstBean<RequestBean<List<ProjectBean>>>>() {
            @Override
            public void onSuccess(com.lzy.okgo.model.Response<SRequstBean<RequestBean<List<ProjectBean>>>> response) {
//                projectBeanList.clear();
//                projectBeanList.addAll(response.body().getData());
//

                if (response.body().getData().getList().size() != 0) {
                    projectBeanList.addAll(response.body().getData().getList());
                    adapter.loadMoreComplete();
                } else {
                    adapter.loadMoreEnd();
                }
                adapter.notifyDataSetChanged();
                dissmissProgressDialog();
            }

            @Override
            public void onError(com.lzy.okgo.model.Response<SRequstBean<RequestBean<List<ProjectBean>>>> response) {
                super.onError(response);
                dissmissProgressDialog();
                adapter.loadMoreFail();
            }

        };
        OkGoClient.getInstance()
                .postJsonData2(url1, params1, params2, jsonCallBack1);
    }

    private void refresh() {
        projectBeanList.clear();
        pageNow = 1;
        showProgressDialog();
        initProjectData();
    }

    private void initViews() {
        tvTitle.setText("全区总项目");
        llcb.setVisibility(View.GONE);
        mapView.setVisibility(View.GONE);
        llList.setVisibility(View.VISIBLE);
        userInfor = MyApplication.getInstance().getUserInfor();
        baiduMap = mapView.getMap();
        baiduMap.setMapType(BaiduMap.MAP_TYPE_SATELLITE);
        adapter = new ProjectListAdapter(HomeTotalActivity.this, projectBeanList);
        LinearLayoutManager manager = new LinearLayoutManager(HomeTotalActivity.this.getApplicationContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        adapter.bindToRecyclerView(recyclerView);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(HomeTotalActivity.this, ProjectDetailsActivity.class);
                intent.putExtra("id", projectBeanList.get(position).getId());
                startActivity(intent);
            }
        });

        GXData();
        LXData();
        SXData();
        AZData();
    }


    private void go() {

        Window window;
        window = getWindow();
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(window.getDecorView().getWindowToken(), 0);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.llgx:
                type_gx = tvgx.getText().toString();
                gxSpinnerPopuwindow = new SpinnerPopuwindow(HomeTotalActivity.this, type_gx, gxData, gxitemsOnClick);
                gxSpinnerPopuwindow.showPopupWindow(llSX);
                gxSpinnerPopuwindow.setTitleText("管辖级别");//给下拉列表设置标题
                break;
            case R.id.lljd:
                type_jd = tvjd.getText().toString();
                jdSpinnerPopuwindow = new SpinnerPopuwindow(HomeTotalActivity.this, type_jd, jdData, jditemsOnClick);
                jdSpinnerPopuwindow.showPopupWindow(llSX);
                jdSpinnerPopuwindow.setTitleText("街道");//给下拉列表设置标题
                break;
            case R.id.lllx:
                type_lx = tvlx.getText().toString();
                lxSpinnerPopuwindow = new SpinnerPopuwindow(HomeTotalActivity.this, type_lx, lxData, lxitemsOnClick);
                lxSpinnerPopuwindow.showPopupWindow(llSX);
                lxSpinnerPopuwindow.setTitleText("项目类型");//给下拉列表设置标题
                break;
            case R.id.llsx:
                type_sx = tvsx.getText().toString();
                sxSpinnerPopuwindow = new SpinnerPopuwindow(HomeTotalActivity.this, type_sx, sxData, sxitemsOnClick);
                sxSpinnerPopuwindow.showPopupWindow(llSX);
                sxSpinnerPopuwindow.setTitleText("项目属性");//给下拉列表设置标题
                break;
            case R.id.llaz:
                type_az = tvaz.getText().toString();
                azSpinnerPopuwindow = new SpinnerPopuwindow(HomeTotalActivity.this, type_az, azData, azitemsOnClick);
                azSpinnerPopuwindow.showPopupWindow(llSX);
                azSpinnerPopuwindow.setTitleText("项目属性");//给下拉列表设置标题
                break;
            case R.id.btnSearch:
                go();
                String s = etName.getText().toString();
                params1.put("name", s);
                refresh();
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

            gxSpinnerPopuwindow.dismissPopupWindow();
            if (position == 0) {
                params1.put("vasa", "");
                tvgx.setText("管辖级别");
            } else {
                params1.put("vasa", position + "");
                tvgx.setText(value);
            }
            refresh();
        }
    };
    private AdapterView.OnItemClickListener jditemsOnClick = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            String value = jdData.get(jdSpinnerPopuwindow.getText());

            jdSpinnerPopuwindow.dismissPopupWindow();
            if (userInfor.getPosition().equals("2")) {

            } else {
                if (position == 0) {
                    params1.put("managerRoleIds", "");
                    tvjd.setText("街道");
                } else {
                    params1.put("managerRoleIds", "[" + streetBeanList.get(position - 1).getId() + "]");
                    tvjd.setText(value);
                }
            }

            refresh();
        }
    };
    private AdapterView.OnItemClickListener lxitemsOnClick = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            String value = lxData.get(lxSpinnerPopuwindow.getText());

            lxSpinnerPopuwindow.dismissPopupWindow();
            if (position == 0) {
                params1.put("pType", "");
                tvlx.setText("项目类型");
            } else {
                params1.put("pType", position + "");
                tvlx.setText(value);
            }
            refresh();
        }
    };
    private AdapterView.OnItemClickListener sxitemsOnClick = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            String value = sxData.get(sxSpinnerPopuwindow.getText());

            sxSpinnerPopuwindow.dismissPopupWindow();
            if (position == 0) {
                params1.put("diff", "");
                tvsx.setText("项目属性");
            } else if (position == 1) {
                params1.put("diff", position + "");
                tvsx.setText(value);
            } else if (position == 2) {
                params1.put("diff", position + "");
                tvsx.setText(value);
            } else if (position == 3) {
                params1.put("diff", "1,2");
                tvsx.setText(value);
            } else if (position == 4) {
                params1.put("diff", "0");
                tvsx.setText(value);
            }
            refresh();
        }
    };
    private AdapterView.OnItemClickListener azitemsOnClick = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            String value = azData.get(azSpinnerPopuwindow.getText());

            azSpinnerPopuwindow.dismissPopupWindow();
            if (position == 0) {
                params1.put("relationType", "");
                tvaz.setText("设备安装");
            } else {
                params1.put("relationType", position + "");
                tvaz.setText(value);
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
        for (int i = 0; i < streetBeanList.size(); i++) {
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

    /**
     * 设备安装数据  全部安装；2：车辆未冲洗未安装；3：扬尘设备未安装；4：视频未安装；5：均未安装
     */
    private void AZData() {
        azData = new ArrayList<>();
        azData.add("全部");
        azData.add("全部安装");
        azData.add("车辆未冲洗未安装");
        azData.add("扬尘设备未安装");
        azData.add("视频未安装");
        azData.add("均未安装");
    }
}

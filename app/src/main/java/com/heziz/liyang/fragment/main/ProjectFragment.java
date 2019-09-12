package com.heziz.liyang.fragment.main;


import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.TextureMapView;
import com.baidu.mapapi.model.LatLng;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.heziz.liyang.R;
import com.heziz.liyang.adaper.project.ProjectListAdapter;
import com.heziz.liyang.app.MyApplication;
import com.heziz.liyang.base.BaseFragment;
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

/**
 * new_clcx_d_cph simple {@link Fragment} subclass.
 */
public class ProjectFragment extends BaseFragment implements View.OnClickListener {

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
    @BindView(R.id.tvsx)
    TextView tvsx;
    @BindView(R.id.llaz)
    LinearLayout llaz;
    @BindView(R.id.tvaz)
    TextView tvaz;
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
    /**
     * 设备安装数据
     */
    private List<String> azData;
    private String type_gx;
    private String type_jd;
    private String type_lx;
    private String type_sx;
    private String type_az;
    ProjectListAdapter adapter;
    private List<ProjectBean> projectBeanList = new ArrayList<>();
    private List<ProjectBean> projectBeanList1 = new ArrayList<>();

    private SpinnerPopuwindow gxSpinnerPopuwindow;
    private SpinnerPopuwindow jdSpinnerPopuwindow;
    private SpinnerPopuwindow lxSpinnerPopuwindow;
    private SpinnerPopuwindow sxSpinnerPopuwindow;
    private SpinnerPopuwindow azSpinnerPopuwindow;

    Map<String, String> params1 = new HashMap<>();
    Map<String, String> params2 = new HashMap<>();

    private int pageNow = 1;
    private UserInfor userInfor;

    private ProjectBean projectBean;

    private boolean flag;

    public ProjectFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_project, container, false);
        ButterKnife.bind(this, view);
        initViews();
        initDatas();
        initListeners();
        return view;
    }


    private void initListeners() {
        baiduMap.setOnMarkerClickListener(new BaiduMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                baiduMap.hideInfoWindow();
                projectBean = ((ProjectBean) marker.getExtraInfo().getSerializable("project"));
                String code = projectBean.getName();
//
////                ToastUtil.showToast(code);
//                //定义用于显示该InfoWindow的坐标点
                LatLng pt = new LatLng(Double.valueOf(projectBean.getLatitude()), Double.valueOf(projectBean.getLongitude()));
                View view = LayoutInflater.from(getActivity()).inflate(R.layout.project_map_marker_info_view, null);
//                //创建InfoWindow , 传入 view， 地理坐标， y 轴偏移量
                final InfoWindow mInfoWindow = new InfoWindow(view, pt, -65);
//                //显示InfoWindow
                baiduMap.showInfoWindow(mInfoWindow);
                TextView tvName = (TextView) view.findViewById(R.id.tvName);
                tvName.setText(code);
                tvName.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getActivity(), ProjectDetailsActivity.class);
                        intent.putExtra("id", projectBean.getId());
                        startActivity(intent);
                    }
                });
                return false;
            }
        });

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    checkBox.setBackgroundResource(R.drawable.project_ditu);
                    mapView.setVisibility(View.GONE);
                    llList.setVisibility(View.VISIBLE);
                    flag = true;
                    pageNow = 1;
                    refresh();

                } else {
                    checkBox.setBackgroundResource(R.drawable.project_liebiao);
                    mapView.setVisibility(View.VISIBLE);
                    llList.setVisibility(View.GONE);
                    flag = false;
                    initProjectData();
                }
            }
        });
        btnSearch.setOnClickListener(this);
        llgx.setOnClickListener(this);
        lljd.setOnClickListener(this);
        lllx.setOnClickListener(this);
        llsx.setOnClickListener(this);
        llaz.setOnClickListener(this);
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                pageNow++;
                initProjectDataList();
            }
        });
    }

    private void initDatas() {


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


        params1.put("name", "");
        params1.put("vasa", "");
        params1.put("managerRoleIds", "");
        params1.put("pType", "");
        params1.put("diff", "");
        params1.put("relationType", "");
        params1.put("station", API.STATION);
        if (userInfor.getPosition().equals("3")) {
            params1.put("createName", userInfor.getAccount());
        } else if (userInfor.getPosition().equals("2")) {
            params1.put("managerRoleIds", "[" + userInfor.getManagerId() + "]");
        }
        initProjectData();

    }

    private void initProjectData() {
        showProgressDialog();
        Log.w("main", "请求参数：" + params1.toString());
        String url1 = API.PROJECT_LIST1 + "?access_token=" + MyApplication.getInstance().getUserInfor().getUuid();

        JsonCallBack1<SRequstBean<List<ProjectBean>>> jsonCallBack1 = new JsonCallBack1<SRequstBean<List<ProjectBean>>>() {
            @Override
            public void onSuccess(com.lzy.okgo.model.Response<SRequstBean<List<ProjectBean>>> response) {
                projectBeanList.clear();
//                baiduMap.clear();
                projectBeanList.addAll(response.body().getData());
                adapter.notifyDataSetChanged();
                showMark(projectBeanList);
                dissmissProgressDialog();
            }

            @Override
            public void onError(com.lzy.okgo.model.Response<SRequstBean<List<ProjectBean>>> response) {
                super.onError(response);
                dissmissProgressDialog();
            }

        };
        OkGoClient.getInstance()
                .postJsonData(url1, params1, jsonCallBack1);
    }

    private void initProjectDataList() {
        params2.put("pageNow", pageNow + "");
        params2.put("pageSize", "10");
        String url1 = API.HOME_TOTAL_PROJECT_LIST + "?access_token=" + MyApplication.getInstance().getUserInfor().getUuid();

        JsonCallBack1<SRequstBean<RequestBean<List<ProjectBean>>>> jsonCallBack1 = new JsonCallBack1<SRequstBean<RequestBean<List<ProjectBean>>>>() {
            @Override
            public void onSuccess(com.lzy.okgo.model.Response<SRequstBean<RequestBean<List<ProjectBean>>>> response) {
//                projectBeanList.clear();
//                projectBeanList.addAll(response.body().getData());
//

                if (response.body().getData().getList().size() != 0) {
                    projectBeanList1.addAll(response.body().getData().getList());
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
        projectBeanList1.clear();
        pageNow = 1;
        showProgressDialog();
        initProjectDataList();
    }

    private void initViews() {
        userInfor = MyApplication.getInstance().getUserInfor();
        baiduMap = mapView.getMap();
        baiduMap.setMapType(BaiduMap.MAP_TYPE_SATELLITE);
        setBaidiCenter();
        adapter = new ProjectListAdapter(getActivity(), projectBeanList1);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity().getApplicationContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        adapter.bindToRecyclerView(recyclerView);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(getActivity(), ProjectDetailsActivity.class);
                intent.putExtra("id", projectBeanList.get(position).getId());
                startActivity(intent);
            }
        });

        GXData();
        LXData();
        SXData();
        AZData();
    }

    private void setBaidiCenter() {
        //118.916498,32.103823
        LatLng point = new LatLng(31.422165,119.49215);
        MapStatus mMapStatus = new MapStatus.Builder()
                //要移动的点
                .target(point)
                //放大地图到20倍
                .zoom(13)
                .build();

        //定义MapStatusUpdate对象，以便描述地图状态将要发生的变化
        MapStatusUpdate mMapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mMapStatus);

        //改变地图状态
        baiduMap.setMapStatus(mMapStatusUpdate);
    }

    private void showMark(List<ProjectBean> list) {
        baiduMap.clear();
        //构建Marker图标
        BitmapDescriptor bitmap = BitmapDescriptorFactory
                .fromResource(R.drawable.project_location);
        BitmapDescriptor bitmap1 = BitmapDescriptorFactory
                .fromResource(R.drawable.location_offline);
        for (int i = 0; i < list.size(); i++) {
            //定义Maker坐标点
            if (list.get(i).getLatitude() != null && list.get(i).getLongitude() != null) {
                LatLng point = new LatLng(Double.valueOf(list.get(i).getLatitude()), Double.valueOf(list.get(i).getLongitude()));
                //构建MarkerOption，用于在地图上添加Marker
                Bundle bundle = new Bundle();
                bundle.putSerializable("project", list.get(i));
//                if(list.get(i).getIsonline().equals("0")){
//                    OverlayOptions option = new MarkerOptions()
//                            .position(point)
//                            .extraInfo(bundle)
//                            .icon(bitmap1);
//                    //在地图上添加Marker，并显示
//                    baiduMap.addOverlay(option);
//                }else{
                OverlayOptions option = new MarkerOptions()
                        .position(point)
                        .extraInfo(bundle)
                        .icon(bitmap);
                //在地图上添加Marker，并显示
                baiduMap.addOverlay(option);
//                }

            }
        }
    }

    private void go() {

        Window window;
        window = getActivity().getWindow();
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(window.getDecorView().getWindowToken(), 0);
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.llgx:
                type_gx = tvgx.getText().toString();
                gxSpinnerPopuwindow = new SpinnerPopuwindow(getActivity(), type_gx, gxData, gxitemsOnClick);
                gxSpinnerPopuwindow.showPopupWindow(llSX);
                gxSpinnerPopuwindow.setTitleText("管辖级别");//给下拉列表设置标题
                break;
            case R.id.lljd:
                type_jd = tvjd.getText().toString();
                jdSpinnerPopuwindow = new SpinnerPopuwindow(getActivity(), type_jd, jdData, jditemsOnClick);
                jdSpinnerPopuwindow.showPopupWindow(llSX);
                jdSpinnerPopuwindow.setTitleText("街道");//给下拉列表设置标题
                break;
            case R.id.lllx:
                type_lx = tvlx.getText().toString();
                lxSpinnerPopuwindow = new SpinnerPopuwindow(getActivity(), type_lx, lxData, lxitemsOnClick);
                lxSpinnerPopuwindow.showPopupWindow(llSX);
                lxSpinnerPopuwindow.setTitleText("项目类型");//给下拉列表设置标题
                break;
            case R.id.llsx:
                type_sx = tvsx.getText().toString();
                sxSpinnerPopuwindow = new SpinnerPopuwindow(getActivity(), type_sx, sxData, sxitemsOnClick);
                sxSpinnerPopuwindow.showPopupWindow(llSX);
                sxSpinnerPopuwindow.setTitleText("项目属性");//给下拉列表设置标题
                break;
            case R.id.llaz:
                type_az = tvaz.getText().toString();
                azSpinnerPopuwindow = new SpinnerPopuwindow(getActivity(), type_az, azData, azitemsOnClick);
                azSpinnerPopuwindow.showPopupWindow(llSX);
                azSpinnerPopuwindow.setTitleText("项目属性");//给下拉列表设置标题
                break;
            case R.id.btnSearch:
                go();
                String s = etName.getText().toString();
                params1.put("name", s);
                getRefresh();
                break;
        }
    }

    private AdapterView.OnItemClickListener gxitemsOnClick = new AdapterView.OnItemClickListener() {
        @RequiresApi(api = Build.VERSION_CODES.N)
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
            getRefresh();
        }
    };

    private void getRefresh() {
        if (flag) {
            refresh();
        } else {
            initProjectData();
        }
    }

    private AdapterView.OnItemClickListener jditemsOnClick = new AdapterView.OnItemClickListener() {
        @RequiresApi(api = Build.VERSION_CODES.N)
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

            getRefresh();
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
            getRefresh();
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
            //} else if (position == 3) {
            //    params1.put("diff", "1,2");
            //    tvsx.setText(value);
            //} else if (position == 4) {
            //    params1.put("diff", "0");
            //    tvsx.setText(value);
            }
            getRefresh();
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
            getRefresh();
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
        //sxData.add("智慧&信息化工地");
        //sxData.add("未申报智慧工地");
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

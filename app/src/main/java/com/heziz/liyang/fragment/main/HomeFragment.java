package com.heziz.liyang.fragment.main;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.heziz.liyang.R;
import com.heziz.liyang.adaper.HomeListAdapter;
import com.heziz.liyang.app.MyApplication;
import com.heziz.liyang.base.BaseFragment;
import com.heziz.liyang.bean.HomeBean;
import com.heziz.liyang.bean.HomeListBean;
import com.heziz.liyang.bean.ProjectBean;
import com.heziz.liyang.bean.UserInfor;
import com.heziz.liyang.network.API;
import com.heziz.liyang.network.JsonCallBack1;
import com.heziz.liyang.network.OkGoClient;
import com.heziz.liyang.network.SRequstBean;
import com.heziz.liyang.ui.home.HomeProjectListActivity;
import com.heziz.liyang.ui.home.HomeTotalActivity;
import com.heziz.liyang.utils.ListDataSave;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * new_clcx_d_cph simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseFragment implements View.OnClickListener {


    @BindView(R.id.all)
    TextView all;
    @BindView(R.id.tvZC)
    TextView tvZC;
    @BindView(R.id.zhnumber)
    TextView zhnumber;
    @BindView(R.id.zhcbnumber)
    TextView zhcbnumber;
    @BindView(R.id.wsbnumber)
    TextView wsbnumber;
    @BindView(R.id.cbnumber)
    TextView cbnumber;
    @BindView(R.id.szgnumber)
    TextView szgnumber;
    @BindView(R.id.qznumber)
    TextView qznumber;
    @BindView(R.id.fjnumber)
    TextView fjnumber;
    @BindView(R.id.sznumber)
    TextView sznumber;
    @BindView(R.id.ylnumber)
    TextView ylnumber;
    @BindView(R.id.jtnumber)
    TextView jtnumber;
    @BindView(R.id.slnumber)
    TextView slnumber;
    @BindView(R.id.dtnumber)
    TextView dtnumber;
    @BindView(R.id.qtnumber)
    TextView qtnumber;
    @BindView(R.id.recycleView)
    RecyclerView recycleView;

    @BindView(R.id.llTotal)
    LinearLayout llTotal;
    @BindView(R.id.llZH)
    LinearLayout llZH;
    @BindView(R.id.llCB)
    LinearLayout llCB;
    @BindView(R.id.llZHCB)
    LinearLayout llZHCB;
    @BindView(R.id.llWSB)
    LinearLayout llWSB;
    @BindView(R.id.llFJ)
    LinearLayout llFJ;
    @BindView(R.id.llSZ)
    LinearLayout llSZ;
    @BindView(R.id.llQZG)
    LinearLayout llQZG;
    @BindView(R.id.llSZG)
    LinearLayout llSZG;
    @BindView(R.id.llYL)
    LinearLayout llYL;
    @BindView(R.id.llJT)
    LinearLayout llJT;
    @BindView(R.id.llSL)
    LinearLayout llSL;
    @BindView(R.id.llDT)
    LinearLayout llDT;
    @BindView(R.id.llQT)
    LinearLayout llQT;


    HomeListAdapter adapter;
    private List<HomeListBean<List<ProjectBean>>> homeListBeans = new ArrayList<>();

    private UserInfor userInfor;

    ListDataSave dataSave;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home1, container, false);
        ButterKnife.bind(this, view);
        initViews();
        initDatas();
        initListeners();
        return view;
    }

    private void initListeners() {
        if (!userInfor.getPosition().equals("3")) {
            llTotal.setOnClickListener(this);
            llZH.setOnClickListener(this);
            llCB.setOnClickListener(this);
            llSZG.setOnClickListener(this);
            llQZG.setOnClickListener(this);
            llFJ.setOnClickListener(this);
            llSZ.setOnClickListener(this);
            llYL.setOnClickListener(this);
            llJT.setOnClickListener(this);
            llSL.setOnClickListener(this);
            llDT.setOnClickListener(this);
            llQT.setOnClickListener(this);
            llZHCB.setOnClickListener(this);
            llWSB.setOnClickListener(this);
        }

        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (!userInfor.getPosition().equals("3")) {
                    Intent intent = new Intent(getActivity(), HomeProjectListActivity.class);
                    intent.putExtra("type", 0);
                    intent.putExtra("name", homeListBeans.get(position).getPopedomName());
                    intent.putExtra("id", homeListBeans.get(position).getPopedom());
                    startActivity(intent);
                }


            }
        });
    }

    private void initViews() {

        dataSave = new ListDataSave(getActivity().getApplicationContext(), "project_list");
        userInfor = MyApplication.getInstance().getUserInfor();
        adapter = new HomeListAdapter(getActivity(), homeListBeans);
//        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), home_bg_icon4);
//        gridLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity().getApplicationContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recycleView.setLayoutManager(manager);
        recycleView.setAdapter(adapter);
    }

    private void initDatas() {


        showProgressDialog();
        String url1 = API.HOME_DATA1 + "?access_token=" + userInfor.getUuid();
        Map<String, String> params1 = new HashMap<>();
        if (userInfor.getPosition().equals("1")) {
            params1.put("station", userInfor.getStation() + "");
        } else if (userInfor.getPosition().equals("2")) {
            params1.put("managerRoleIds", userInfor.getManagerId() + "");
        } else if (userInfor.getPosition().equals("3")) {
            params1.put("createName", userInfor.getAccount());
        }
        JsonCallBack1<SRequstBean<HomeBean>> jsonCallBack = new JsonCallBack1<SRequstBean<HomeBean>>() {
            @Override
            public void onSuccess(com.lzy.okgo.model.Response<SRequstBean<HomeBean>> response) {
                HomeBean bean = response.body().getData();
                all.setText(bean.getTotal() + "");
                //total.setText(NumberUtils.getTwoDecimal(bean.getCost() / 10000.0) + "亿元");
                zhnumber.setText(bean.getZhgd() + "");
                cbnumber.setText(bean.getDiff() + "");
                szgnumber.setText(bean.getQzg() + "");
                //qznumber.setText(bean.getQzg() + "");
                fjnumber.setText(bean.getFj() + "");
                sznumber.setText(bean.getSz() + "");
                ylnumber.setText(bean.getYl() + "");
                jtnumber.setText(bean.getJt() + "");
                slnumber.setText(bean.getSl() + "");
                dtnumber.setText(bean.getDt() + "");
                qtnumber.setText(bean.getNotBuild() + "");
                zhcbnumber.setText(bean.getQtDiff()+"");
                wsbnumber.setText(bean.getWsbZh()+"");
            }

            @Override
            public void onError(com.lzy.okgo.model.Response<SRequstBean<HomeBean>> response) {
                super.onError(response);
                dissmissProgressDialog();
            }

        };
        OkGoClient.getInstance()
                .postJsonData(url1, params1, jsonCallBack);


        String url = API.HOME_DATA2 + "?access_token=" + userInfor.getUuid();
        Map<String, String> params = new HashMap<>();
        params.put("station", API.STATION);
        if (userInfor.getPosition().equals("3")) {
            params.put("createName", userInfor.getAccount());
        }
        JsonCallBack1<SRequstBean<List<HomeListBean<List<ProjectBean>>>>> jsonCallBack1 = new JsonCallBack1<SRequstBean<List<HomeListBean<List<ProjectBean>>>>>() {
            @Override
            public void onSuccess(com.lzy.okgo.model.Response<SRequstBean<List<HomeListBean<List<ProjectBean>>>>> response) {
                List<HomeListBean<List<ProjectBean>>> list = response.body().getData();
                dataSave.setDataList("project", list);
                if (userInfor.getPosition().equals("2")) {
                    for (int i = 0; i < list.size(); i++) {
                        if (list.get(i).getPopedom().equals(userInfor.getManagerId())) {
                            homeListBeans.add(list.get(i));
                        }
                    }
                } else if (userInfor.getPosition().equals("3")) {
                    for (int i = 0; i < list.size(); i++) {
                        if (list.get(i).getListSize() != 0) {
                            homeListBeans.add(list.get(i));
                        }
                    }
                } else {
                    homeListBeans.addAll(response.body().getData());
                }
                adapter.notifyDataSetChanged();
                dissmissProgressDialog();
            }

            @Override
            public void onError(com.lzy.okgo.model.Response<SRequstBean<List<HomeListBean<List<ProjectBean>>>>> response) {
                super.onError(response);
                dissmissProgressDialog();
            }

        };
        OkGoClient.getInstance()
                .postJsonData(url, params, jsonCallBack1);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getActivity(), HomeProjectListActivity.class);
        switch (v.getId()) {
            case R.id.llTotal:
                startActivity(new Intent(getActivity(), HomeTotalActivity.class));
                break;
            //case R.id.llCost:
            //    Intent intent1 = new Intent(getActivity(), HomeTypeActivity.class);
            //    intent1.putExtra("type", 1);
            //    startActivity(intent1);
            //    break;
            case R.id.llZH:
                intent.putExtra("type", 2);
                intent.putExtra("name", "智慧工地");
                startActivity(intent);
                break;
            case R.id.llCB:
                intent.putExtra("type", 3);
                intent.putExtra("name", "信息化工地");
                startActivity(intent);
                break;
            case R.id.llZHCB:
                intent.putExtra("type", 13);
                intent.putExtra("name", "其他工地项目");
                startActivity(intent);
                break;
            case R.id.llWSB:
                intent.putExtra("type", 14);
                intent.putExtra("name", "未申报智慧工地项目");
                startActivity(intent);
                break;
            case R.id.llSZG:
                intent.putExtra("type", 5);
                intent.putExtra("name", "市直管项目");
                startActivity(intent);
                break;
            case R.id.llQZG:
                intent.putExtra("type", 5);
                intent.putExtra("name", "区直管项目");
                startActivity(intent);
                break;
            case R.id.llFJ:
                intent.putExtra("type", 6);
                intent.putExtra("name", "房建项目");
                startActivity(intent);
                break;
            case R.id.llSZ:
                intent.putExtra("type", 7);
                intent.putExtra("name", "市政项目");
                startActivity(intent);
                break;
            case R.id.llYL:
                intent.putExtra("type", 8);
                intent.putExtra("name", "园林项目");
                startActivity(intent);
                break;
            case R.id.llJT:
                intent.putExtra("type", 9);
                intent.putExtra("name", "交通项目");
                startActivity(intent);
                break;
            case R.id.llSL:
                intent.putExtra("type", 10);
                intent.putExtra("name", "水利项目");
                startActivity(intent);
                break;
            case R.id.llDT:
                intent.putExtra("type", 11);
                intent.putExtra("name", "地铁项目");
                startActivity(intent);
                break;
            case R.id.llQT:
                intent.putExtra("type", 12);
                intent.putExtra("name", "其他项目");
                startActivity(intent);
                break;
        }
    }
}

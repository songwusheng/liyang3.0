package com.heziz.liyang.ui.zhihui.smz;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baidu.mapapi.map.TextureMapView;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.heziz.liyang.R;
import com.heziz.liyang.adaper.smz.SmzDayListAdapter;
import com.heziz.liyang.app.MyApplication;
import com.heziz.liyang.base.BaseFragment;
import com.heziz.liyang.bean.smz.SmzDayBean;
import com.heziz.liyang.bean.smz.SmzHMCBean;
import com.heziz.liyang.network.API;
import com.heziz.liyang.network.JsonCallBack1;
import com.heziz.liyang.network.OkGoClient;
import com.heziz.liyang.network.RequestBean;
import com.heziz.liyang.network.SRequstBean;
import com.heziz.liyang.ui.zhihui.clwcx.CarDetailsActivity;
import com.heziz.liyang.utils.TimeUtils;
import com.heziz.liyang.utils.ToastUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.

 */
public class SmzD3Fragment extends BaseFragment implements View.OnClickListener {
    private static final String ARG_PARAM1 = "param1";

    private String mParam1;
    private SmzDayListAdapter adapter;
    private List<SmzDayBean> list=new ArrayList<>();
    Map<String, String> params1 = new HashMap<>();
    Map<String, String> params2 = new HashMap<>();
    private int page = 1;
    private int totalPage;
    //@BindView(R.id.rls)
    //ImageView rls;
    @BindView(R.id.recycleView)
    RecyclerView recycleView;
    @BindView(R.id.llPage)
    LinearLayout llPage;
    @BindView(R.id.btnUp)
    Button btnUp;
    @BindView(R.id.btnDow)
    Button btnDow;
    @BindView(R.id.etName)
    EditText etName;
    @BindView(R.id.tvStartTime)
    TextView tvStartTime;
    @BindView(R.id.tvEndTime)
    TextView tvEndTime;
    @BindView(R.id.rls)
    RelativeLayout rls;

    private String startTime;
    private String endTime;

    public static SmzD3Fragment newInstance(String param1) {
        SmzD3Fragment fragment = new SmzD3Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_smz_d3, container, false);
        ButterKnife.bind(this,view);
        initViews();
        initDatas();
        initListeners();
        return view;
    }
    private void initListeners() {
        rls.setOnClickListener(this);
        btnDow.setOnClickListener(this);
        btnUp.setOnClickListener(this);
        tvStartTime.setOnClickListener(this);
        tvEndTime.setOnClickListener(this);
        startTime=TimeUtils.getWeekTime();
        endTime=TimeUtils.getCurrentTime();
    }


    private void initViews() {
        adapter=new SmzDayListAdapter(getActivity(),list);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity().getApplicationContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recycleView.setLayoutManager(manager);
        recycleView.setAdapter(adapter);
        adapter.bindToRecyclerView(recycleView);
        adapter.setEmptyView(R.layout.empty_view);
    }

    private void initDatas() {
        showProgressDialog();
        String url2 = API.SMZ_DAY_HISTORY_LIST + "?access_token=" + MyApplication.getInstance().getUserInfor().getUuid();
        params1.put("deviceNo", "" + mParam1);
        params2.put("pageSize", "5");
        params2.put("pageNow", page + "");
        params1.put("startTime", startTime);
        params1.put("endTime", endTime);
        JsonCallBack1<SRequstBean<RequestBean<List<SmzHMCBean>>>> jsonCallBack2 = new JsonCallBack1<SRequstBean<RequestBean<List<SmzHMCBean>>>>() {
            @Override
            public void onSuccess(com.lzy.okgo.model.Response<SRequstBean<RequestBean<List<SmzHMCBean>>>> response) {
                if (response.body().getData() != null) {
////                            carDDetailsBeanList.addAll(response.body().getData().getList());
                    totalPage = response.body().getData().getPage().getTotalPageCount();
                    //if (response.body().getData().getList().size() != 0) {
                    //    list.addAll(response.body().getData().getList());
                    //}
                }
                dissmissProgressDialog();
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onError(com.lzy.okgo.model.Response<SRequstBean<RequestBean<List<SmzHMCBean>>>> response) {
                super.onError(response);
                dissmissProgressDialog();
            }

        };
        OkGoClient.getInstance()
                .postJsonData2(url2, params1, params2, jsonCallBack2);
    }
    private void refresh() {
        page = 1;
        list.clear();
        initDatas();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rls:
                String name = etName.getText().toString();
                String startTime=tvStartTime.getText().toString();
                String endTime=tvEndTime.getText().toString();
                params1.put("name", name);
                params1.put("startTime", startTime);
                params1.put("endTime", endTime);
                //
                refresh();
                break;
            case R.id.btnUp:
                if (page==1){
                    ToastUtil.showToast("已经是第一页");
                    return;
                }else{
                    page--;
                    list.clear();
                    initDatas();
                }
                break;
            case R.id.btnDow:
                if(totalPage==0){
                    return;
                }else{
                    if (page < totalPage) {
                        list.clear();
                        page++;
                        initDatas();
                    }else{
                        ToastUtil.showToast("已经是最后一页");
                    }
                }

                break;
            case R.id.tvStartTime:
                //时间选择器
                TimePickerView pvTime = new TimePickerBuilder(getActivity(), new OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {

                        //date.setHours(23);
                        //date.setMinutes(59);
                        //date.setSeconds(59);
                        //endTime= TimeUtils.setSTime(date);
                        date.setHours(0);
                        date.setMinutes(0);
                        date.setSeconds(0);
                        tvStartTime.setText(TimeUtils.setSTime(date));
                    }
                })
                        .build();
                pvTime.setTitleText("请选择开始时间");
                pvTime.show();
                break;
            case R.id.tvEndTime:
                //时间选择器
                TimePickerView pvTime1 = new TimePickerBuilder(getActivity(), new OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {

                        date.setHours(23);
                        date.setMinutes(59);
                        date.setSeconds(59);
                        tvEndTime.setText(TimeUtils.setSTime(date));
                        //date.setHours(0);
                        //date.setMinutes(0);
                        //date.setSeconds(0);
                        //startTime=TimeUtils.setSTime(date);
                    }
                })
                        .build();
                pvTime1.setTitleText("请选择结束时间");
                pvTime1.show();
                break;
        }
    }


}

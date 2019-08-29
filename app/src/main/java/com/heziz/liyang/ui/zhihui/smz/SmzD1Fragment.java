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
import com.heziz.liyang.utils.ToastUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.

 */
public class SmzD1Fragment extends BaseFragment implements View.OnClickListener {
    private static final String ARG_PARAM1 = "param1";
    private String mParam1;

    @BindView(R.id.etName)
    EditText etName;
    @BindView(R.id.rls)
    ImageView rls;
    @BindView(R.id.recycleView)
    RecyclerView recycleView;
    @BindView(R.id.llPage)
    LinearLayout llPage;
    @BindView(R.id.btnUp)
    Button btnUp;
    @BindView(R.id.btnDow)
    Button btnDow;

    private SmzDayListAdapter adapter;
    private List<SmzDayBean> list=new ArrayList<>();
    Map<String, String> params1 = new HashMap<>();
    Map<String, String> params2 = new HashMap<>();
    private int page = 1;
    private int totalPage;

    public static SmzD1Fragment newInstance(String param1) {
        SmzD1Fragment fragment = new SmzD1Fragment();
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
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_smz_d1, container, false);
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
                params1.put("name", name);

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
        }
    }
}

package com.heziz.liyang.fragment.main;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.heziz.liyang.R;
import com.heziz.liyang.app.MyApplication;
import com.heziz.liyang.base.BaseFragment;
import com.heziz.liyang.bean.UserInfor;
import com.heziz.liyang.ui.LoginActivity;
import com.heziz.liyang.ui.mine.mineinfo.MineXGMMActivity;
import com.heziz.liyang.ui.mine.fdl.MineFdlListActivity;
import com.heziz.liyang.ui.mine.wdbygs.MineWDBYGSListActivity;
import com.heziz.liyang.ui.mine.zxjc.MineZXJCListActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * new_clcx_d_cph simple {@link Fragment} subclass.
 */
public class MineFragment extends BaseFragment implements View.OnClickListener {

    @BindView(R.id.btn)
    Button btn;
    @BindView(R.id.tvPhone)
    TextView tvPhone;
    @BindView(R.id.tvWDBYGS)
    TextView tvWDBYGS;
    @BindView(R.id.tvZXJC)
    TextView tvZXJC;
    @BindView(R.id.tvFDLJXGL)
    TextView tvFDLJXGL;
    @BindView(R.id.tvXGMM)
    TextView tvXGMM;

    private UserInfor userInfor;
    public MineFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_mine, container, false);
        ButterKnife.bind(this,view);
        initViews();
        initDatas();
        initeListeners();
        return view;
    }

    private void initViews() {

        userInfor=MyApplication.getInstance().getUserInfor();
            tvPhone.setText(userInfor.getName());

    }

    private void initDatas() {

    }

    private void initeListeners() {
        btn.setOnClickListener(this);
        tvWDBYGS.setOnClickListener(this);
        tvZXJC.setOnClickListener(this);
        tvFDLJXGL.setOnClickListener(this);
        tvXGMM.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent=new Intent();
        switch (v.getId()){
            case R.id.btn:
                intent.setClass(getActivity(), LoginActivity.class);
                getActivity().finish();
                break;
            case R.id.tvWDBYGS:
                intent.setClass(getActivity(), MineWDBYGSListActivity.class);
                break;
            case R.id.tvZXJC:
                intent.setClass(getActivity(), MineZXJCListActivity.class);
                break;
            case R.id.tvFDLJXGL:
                intent.setClass(getActivity(), MineFdlListActivity.class);
                break;
            case R.id.tvXGMM:
                intent.setClass(getActivity(), MineXGMMActivity.class);
                break;


        }
        startActivity(intent);
    }
}

package com.heziz.liyang.ui.mine.mineinfo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.heziz.liyang.R;
import com.heziz.liyang.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MineInfoActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.rlBack)
    RelativeLayout rlBack;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.tvXGMM)
    TextView tvXGMM;
    MyBroadcastReceiver mBroadcastReceiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_info);
        ButterKnife.bind(this);

        initViews();
        initDatas();
        initListeners();
    }

    private void initViews() {
        tvTitle.setText("个人信息");
        mBroadcastReceiver = new MyBroadcastReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("xg_password_success");
        registerReceiver(mBroadcastReceiver, intentFilter);
    }

    private void initDatas() {
    }

    private void initListeners() {
        rlBack.setOnClickListener(this);
        tvXGMM.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.rlBack:
                finish();
                break;
            case R.id.tvXGMM:
                intent.setClass(MineInfoActivity.this, MineXGMMActivity.class);
                startActivity(intent);
                break;
        }

    }
    class MyBroadcastReceiver extends BroadcastReceiver {
        public static final String TAG = "MyBroadcastReceiver";

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("xg_password_success")) {
                finish();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mBroadcastReceiver);
    }
}

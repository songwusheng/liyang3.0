package com.heziz.liyang.ui.newjm;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.heziz.liyang.R;
import com.heziz.liyang.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SanjiActivity extends BaseActivity {

    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.rlBack)
    RelativeLayout rlBack;
    @BindView(R.id.ivPicBg)
    ImageView iv;
    private int type;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sanji);

        ButterKnife.bind(this);

        type=getIntent().getIntExtra("type",0);

        switch (type){
            case 100:
                tvTitle.setText("塔吊监控");
                //iv.setImageResource(R.drawable.td_bg);
                Glide.with(this).load(R.drawable.td_bg1).into(iv);
                break;
            case 101:
                tvTitle.setText("实名制考勤");
                Glide.with(this).load(R.drawable.kq_bg1).into(iv);
                break;
            case 102:
                tvTitle.setText("安全管理");
                Glide.with(this).load(R.drawable.aq_bg1).into(iv);
                break;
            case 103:
                tvTitle.setText("质量管理");
                Glide.with(this).load(R.drawable.zl_bg1).into(iv);
                break;
        }



        rlBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}

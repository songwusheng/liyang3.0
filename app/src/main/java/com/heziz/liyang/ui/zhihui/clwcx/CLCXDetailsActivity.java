package com.heziz.liyang.ui.zhihui.clwcx;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.heziz.liyang.R;
import com.heziz.liyang.base.BaseActivity;
import com.heziz.liyang.bean.car.CarDetailsBean1;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.ittiger.player.VideoPlayerView;

public class CLCXDetailsActivity extends BaseActivity {
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.rlBack)
    RelativeLayout rlBack;
    @BindView(R.id.ivPic)
    ImageView ivPic;
    @BindView(R.id.tvCph)
    TextView tvCph;
    @BindView(R.id.tvZpsj)
    TextView tvZpsj;
    @BindView(R.id.tvCplx)
    TextView tvCplx;
    @BindView(R.id.btnA)
    CheckBox btn;
    @BindView(R.id.video_player_view)
    VideoPlayerView video_player_view;
    private CarDetailsBean1 carDetailsBean1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clcxdetails);
        ButterKnife.bind(this);
        initViews();
        initDatas();
        initListeners();
    }

    private void initDatas() {

    }

    private void initListeners() {
        rlBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    video_player_view.startPlayVideo();
                    video_player_view.setVisibility(View.VISIBLE);
                    ivPic.setVisibility(View.GONE);
                    btn.setText("停止回放");
                }else{
                    video_player_view.onChangeUIPauseState();
                    video_player_view.setVisibility(View.GONE);
                    ivPic.setVisibility(View.VISIBLE);
                    btn.setText("回放现场视频");
                }
            }
        });
    }

    private void initViews() {
        tvTitle.setText("车辆未冲洗详情");
        carDetailsBean1=(CarDetailsBean1)getIntent().getSerializableExtra("CarDetailsBean1");
        Glide.with(this).load(carDetailsBean1.getDistantview()).into(ivPic);
        tvCph.setText(carDetailsBean1.getLicensePlate());
        tvZpsj.setText(carDetailsBean1.getDateTimeString());
        tvCplx.setText(carDetailsBean1.getLicensePlateColor()+"牌");

        //video_player_view.bind(carDetailsBean1.getVideo(), "车辆未冲洗抓拍回放");
        video_player_view.bind("https://www.heziz.com/afile/qhxtjbgq2/2/20190819015521666.mp4", "车辆未冲洗抓拍回放");

    }
}

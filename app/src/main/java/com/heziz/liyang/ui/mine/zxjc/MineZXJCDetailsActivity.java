package com.heziz.liyang.ui.mine.zxjc;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.heziz.liyang.R;
import com.heziz.liyang.adaper.wdbygs.WDBYGSImageListAdapter;
import com.heziz.liyang.base.BaseActivity;
import com.heziz.liyang.bean.mine.zxjc.MineZXJCBean;
import com.heziz.liyang.utils.StringUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MineZXJCDetailsActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.rlBack)
    RelativeLayout rlBack;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.tvTitle1)
    TextView tvTitle1;
    @BindView(R.id.tvSx1)
    TextView tvSx1;
    @BindView(R.id.tvMs1)
    TextView tvMs1;
    @BindView(R.id.tvJg1)
    TextView tvJg1;
    @BindView(R.id.recycleView1)
    RecyclerView recycleView1;
    List<String> list1=new ArrayList<>();
    private WDBYGSImageListAdapter adapter1;
    private MineZXJCBean mineWDBYGSListBean;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_zxjcdetails);
        ButterKnife.bind(this);

        initViews();
        initDatas();
        initListeners();
    }
    private void initListeners() {
        rlBack.setOnClickListener(this);

    }

    private void initViews() {
        mineWDBYGSListBean=(MineZXJCBean) getIntent().getSerializableExtra("mineWDBYGSListBean");
        tvTitle.setText("专项检查");
        tvTitle1.setText(mineWDBYGSListBean.getProjectName());

        if(!(StringUtil.isNull(mineWDBYGSListBean.getCustomEventImage()))){
            String[] images1=mineWDBYGSListBean.getCustomEventImage().split(",");
            list1= Arrays.asList(images1);
        }


        adapter1=new WDBYGSImageListAdapter(this,list1);

        LinearLayoutManager manager=new LinearLayoutManager(this.getApplicationContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recycleView1.setLayoutManager(manager);

        recycleView1.setAdapter(adapter1);

        tvSx1.setText(StringUtil.isNullOrEmpty1(mineWDBYGSListBean.getCustomEventName()));
        tvMs1.setText(StringUtil.isNullOrEmpty1(mineWDBYGSListBean.getCustomEventCommonts()));

        int num1=mineWDBYGSListBean.getEndStatus();
        if(num1==2){
            tvJg1.setText("合格");
        }else if(num1==3||num1==4){
            tvJg1.setText("不合格");
        }else if(num1==5){
            tvJg1.setText("");
        }


    }


    private void initDatas() {

//        adapter1.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//            }
//        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rlBack:
                finish();
                break;

        }
    }
}


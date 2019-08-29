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

public class MineZXJCZGDetailsActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.rlBack)
    RelativeLayout rlBack;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.tvTitle1)
    TextView tvTitle1;
    @BindView(R.id.tvSx1)
    TextView tvSx1;
//    @BindView(R.id.tvJg1)
//    TextView tvJg1;
    @BindView(R.id.recycleView1)
    RecyclerView recycleView1;
    @BindView(R.id.recycleView12)
    RecyclerView recycleView12;

    List<String> list1=new ArrayList<>();
    List<String> list12=new ArrayList<>();
    private WDBYGSImageListAdapter adapter1;
    private WDBYGSImageListAdapter adapter12;
    private MineZXJCBean mineWDBYGSListBean;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_zxjczgdetails);
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
        tvSx1.setText(StringUtil.isNullOrEmpty1(mineWDBYGSListBean.getCustomEventName()));
        String[] images1=mineWDBYGSListBean.getCustomEventImage().split(",");
        list1= Arrays.asList(images1);

        String[] images12=mineWDBYGSListBean.getChangeEventImage().split(",");
        list12= Arrays.asList(images12);

        adapter1=new WDBYGSImageListAdapter(this,list1);

        adapter12=new WDBYGSImageListAdapter(this,list12);

        LinearLayoutManager manager=new LinearLayoutManager(this.getApplicationContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recycleView1.setLayoutManager(manager);
        LinearLayoutManager manager1=new LinearLayoutManager(this.getApplicationContext());
        manager1.setOrientation(LinearLayoutManager.VERTICAL);
        recycleView12.setLayoutManager(manager1);

        recycleView1.setAdapter(adapter1);

        recycleView12.setAdapter(adapter12);


    }


    private void initDatas() {
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


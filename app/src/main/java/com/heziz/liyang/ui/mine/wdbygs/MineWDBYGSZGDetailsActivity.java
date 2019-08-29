package com.heziz.liyang.ui.mine.wdbygs;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.heziz.liyang.R;
import com.heziz.liyang.adaper.wdbygs.WDBYGSImageListAdapter;
import com.heziz.liyang.base.BaseActivity;
import com.heziz.liyang.bean.mine.wdbygs.MineWDBYGSListBean;
import com.heziz.liyang.utils.StringUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MineWDBYGSZGDetailsActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.rlBack)
    RelativeLayout rlBack;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.tvTitle1)
    TextView tvTitle1;
    @BindView(R.id.tvSx1)
    TextView tvSx1;
    @BindView(R.id.tvJg1)
    TextView tvJg1;
    @BindView(R.id.recycleView1)
    RecyclerView recycleView1;
    @BindView(R.id.recycleView12)
    RecyclerView recycleView12;

    @BindView(R.id.tvSx2)
    TextView tvSx2;
    @BindView(R.id.tvJg2)
    TextView tvJg2;
    @BindView(R.id.recycleView2)
    RecyclerView recycleView2;
    @BindView(R.id.recycleView22)
    RecyclerView recycleView22;
    @BindView(R.id.tvSx3)
    TextView tvSx3;
    @BindView(R.id.tvJg3)
    TextView tvJg3;
    @BindView(R.id.recycleView3)
    RecyclerView recycleView3;
    @BindView(R.id.recycleView32)
    RecyclerView recycleView32;
    @BindView(R.id.tvSx4)
    TextView tvSx4;
    @BindView(R.id.tvJg4)
    TextView tvJg4;
    @BindView(R.id.recycleView4)
    RecyclerView recycleView4;
    @BindView(R.id.recycleView42)
    RecyclerView recycleView42;
    @BindView(R.id.tvSx5)
    TextView tvSx5;
    @BindView(R.id.tvJg5)
    TextView tvJg5;
    @BindView(R.id.recycleView5)
    RecyclerView recycleView5;
    @BindView(R.id.recycleView52)
    RecyclerView recycleView52;
    @BindView(R.id.tvSx6)
    TextView tvSx6;
    @BindView(R.id.tvJg6)
    TextView tvJg6;
    @BindView(R.id.recycleView6)
    RecyclerView recycleView6;
    @BindView(R.id.recycleView62)
    RecyclerView recycleView62;
    List<String> list1=new ArrayList<>();
    List<String> list2=new ArrayList<>();
    List<String> list3=new ArrayList<>();
    List<String> list4=new ArrayList<>();
    List<String> list5=new ArrayList<>();
    List<String> list6=new ArrayList<>();

    List<String> list12=new ArrayList<>();
    List<String> list22=new ArrayList<>();
    List<String> list32=new ArrayList<>();
    List<String> list42=new ArrayList<>();
    List<String> list52=new ArrayList<>();
    List<String> list62=new ArrayList<>();

    private WDBYGSImageListAdapter adapter1;
    private WDBYGSImageListAdapter adapter2;
    private WDBYGSImageListAdapter adapter3;
    private WDBYGSImageListAdapter adapter4;
    private WDBYGSImageListAdapter adapter5;
    private WDBYGSImageListAdapter adapter6;
    private WDBYGSImageListAdapter adapter12;
    private WDBYGSImageListAdapter adapter22;
    private WDBYGSImageListAdapter adapter32;
    private WDBYGSImageListAdapter adapter42;
    private WDBYGSImageListAdapter adapter52;
    private WDBYGSImageListAdapter adapter62;
    private MineWDBYGSListBean mineWDBYGSListBean;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_wdbygszgdetails);
        ButterKnife.bind(this);

        initViews();
        initDatas();
        initListeners();
    }

    private void initListeners() {
        rlBack.setOnClickListener(this);

    }

    private void initViews() {
        mineWDBYGSListBean=(MineWDBYGSListBean) getIntent().getSerializableExtra("mineWDBYGSListBean");
        tvTitle.setText("五达标一公示");
        tvTitle1.setText(mineWDBYGSListBean.getProjectName());

        String[] images1=mineWDBYGSListBean.getCleaningImage().split(",");
        list1= Arrays.asList(images1);
        String[] images2=mineWDBYGSListBean.getCoverageImage().split(",");
        list2= Arrays.asList(images2);
        String[] images3=mineWDBYGSListBean.getEnclosureImage().split(",");
        list3= Arrays.asList(images3);
        String[] images4=mineWDBYGSListBean.getHardeningImage().split(",");
        list4= Arrays.asList(images4);
        String[] images5=mineWDBYGSListBean.getInoutImage().split(",");
        list5= Arrays.asList(images5);
        String[] images6=mineWDBYGSListBean.getPublicityImage().split(",");
        list6= Arrays.asList(images6);

        String[] images12= StringUtil.isNullOrEmpty2(mineWDBYGSListBean.getChangeCleaningImage()).split(",");
        list12= Arrays.asList(images12);
        String[] images22=StringUtil.isNullOrEmpty2(mineWDBYGSListBean.getChangeCoverageImage()).split(",");
        list22= Arrays.asList(images22);
        String[] images32=StringUtil.isNullOrEmpty2(mineWDBYGSListBean.getChangeEnclosureImage()).split(",");
        list32= Arrays.asList(images32);
        String[] images42=StringUtil.isNullOrEmpty2(mineWDBYGSListBean.getChangeHardeningImage()).split(",");
        list42= Arrays.asList(images42);
        String[] images52=StringUtil.isNullOrEmpty2(mineWDBYGSListBean.getChangeInoutImage()).split(",");
        list52= Arrays.asList(images52);
        String[] images62=StringUtil.isNullOrEmpty2(mineWDBYGSListBean.getChangePublicityImage()).split(",");
        list62= Arrays.asList(images62);

        adapter1=new WDBYGSImageListAdapter(this,list1);
        adapter2=new WDBYGSImageListAdapter(this,list2);
        adapter3=new WDBYGSImageListAdapter(this,list3);
        adapter4=new WDBYGSImageListAdapter(this,list4);
        adapter5=new WDBYGSImageListAdapter(this,list5);
        adapter6=new WDBYGSImageListAdapter(this,list6);

        adapter12=new WDBYGSImageListAdapter(this,list12);
        adapter22=new WDBYGSImageListAdapter(this,list22);
        adapter32=new WDBYGSImageListAdapter(this,list32);
        adapter42=new WDBYGSImageListAdapter(this,list42);
        adapter52=new WDBYGSImageListAdapter(this,list52);
        adapter62=new WDBYGSImageListAdapter(this,list62);

        LinearLayoutManager manager=new LinearLayoutManager(this.getApplicationContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recycleView1.setLayoutManager(manager);
        LinearLayoutManager manager2=new LinearLayoutManager(this.getApplicationContext());
        manager2.setOrientation(LinearLayoutManager.VERTICAL);
        recycleView2.setLayoutManager(manager2);
        LinearLayoutManager manager3=new LinearLayoutManager(this.getApplicationContext());
        manager3.setOrientation(LinearLayoutManager.VERTICAL);
        recycleView3.setLayoutManager(manager3);
        LinearLayoutManager manager4=new LinearLayoutManager(this.getApplicationContext());
        manager4.setOrientation(LinearLayoutManager.VERTICAL);
        recycleView4.setLayoutManager(manager4);
        LinearLayoutManager manager5=new LinearLayoutManager(this.getApplicationContext());
        manager5.setOrientation(LinearLayoutManager.VERTICAL);
        recycleView5.setLayoutManager(manager5);
        LinearLayoutManager manager6=new LinearLayoutManager(this.getApplicationContext());
        manager6.setOrientation(LinearLayoutManager.VERTICAL);
        recycleView6.setLayoutManager(manager6);
        LinearLayoutManager manager1=new LinearLayoutManager(this.getApplicationContext());
        manager1.setOrientation(LinearLayoutManager.VERTICAL);
        recycleView12.setLayoutManager(manager1);
        LinearLayoutManager manager22=new LinearLayoutManager(this.getApplicationContext());
        manager22.setOrientation(LinearLayoutManager.VERTICAL);
        recycleView22.setLayoutManager(manager22);
        LinearLayoutManager manager32=new LinearLayoutManager(this.getApplicationContext());
        manager32.setOrientation(LinearLayoutManager.VERTICAL);
        recycleView32.setLayoutManager(manager32);
        LinearLayoutManager manager42=new LinearLayoutManager(this.getApplicationContext());
        manager42.setOrientation(LinearLayoutManager.VERTICAL);
        recycleView42.setLayoutManager(manager42);
        LinearLayoutManager manager52=new LinearLayoutManager(this.getApplicationContext());
        manager52.setOrientation(LinearLayoutManager.VERTICAL);
        recycleView52.setLayoutManager(manager52);
        LinearLayoutManager manager62=new LinearLayoutManager(this.getApplicationContext());
        manager62.setOrientation(LinearLayoutManager.VERTICAL);
        recycleView62.setLayoutManager(manager62);

        recycleView1.setAdapter(adapter1);
        recycleView2.setAdapter(adapter2);
        recycleView3.setAdapter(adapter3);
        recycleView4.setAdapter(adapter4);
        recycleView5.setAdapter(adapter5);
        recycleView6.setAdapter(adapter6);

        recycleView12.setAdapter(adapter12);
        recycleView22.setAdapter(adapter22);
        recycleView32.setAdapter(adapter32);
        recycleView42.setAdapter(adapter42);
        recycleView52.setAdapter(adapter52);
        recycleView62.setAdapter(adapter62);

        int num1=mineWDBYGSListBean.getCleaningStatus();
        if(num1==0){
            tvJg1.setText("审核中");
        }else if(num1==1){
            tvJg1.setText("通过");
        }else if(num1==2){
            tvJg1.setText("不通过");
        }

        int num2=mineWDBYGSListBean.getCoverageStatus();
        if(num2==0){
            tvJg2.setText("审核中");
        }else if(num2==1){
            tvJg2.setText("通过");
        }else if(num2==2){
            tvJg2.setText("不通过");
        }

        int num3=mineWDBYGSListBean.getEnclosureStatus();
        if(num3==0){
            tvJg3.setText("审核中");
        }else if(num3==1){
            tvJg3.setText("通过");
        }else if(num3==2){
            tvJg3.setText("不通过");
        }

        int num4=mineWDBYGSListBean.getHardeningStatus();
        if(num4==0){
            tvJg4.setText("审核中");
        }else if(num4==1){
            tvJg4.setText("通过");
        }else if(num4==2){
            tvJg4.setText("不通过");
        }

        int num5=mineWDBYGSListBean.getInoutStatus();
        if(num5==0){
            tvJg5.setText("审核中");
        }else if(num5==1){
            tvJg5.setText("通过");
        }else if(num5==2){
            tvJg5.setText("不通过");
        }


        int num6=mineWDBYGSListBean.getPublicityStatus();
        if(num6==0){
            tvJg6.setText("审核中");
        }else if(num6==1){
            tvJg6.setText("通过");
        }else if(num6==2){
            tvJg6.setText("不通过");
        }



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


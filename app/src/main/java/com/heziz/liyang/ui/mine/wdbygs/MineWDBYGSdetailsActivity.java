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

public class MineWDBYGSdetailsActivity extends BaseActivity implements View.OnClickListener {
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

    @BindView(R.id.tvSx2)
    TextView tvSx2;
    @BindView(R.id.tvMs2)
    TextView tvMs2;
    @BindView(R.id.tvJg2)
    TextView tvJg2;
    @BindView(R.id.recycleView2)
    RecyclerView recycleView2;

    @BindView(R.id.tvSx3)
    TextView tvSx3;
    @BindView(R.id.tvMs3)
    TextView tvMs3;
    @BindView(R.id.tvJg3)
    TextView tvJg3;
    @BindView(R.id.recycleView3)
    RecyclerView recycleView3;

    @BindView(R.id.tvSx4)
    TextView tvSx4;
    @BindView(R.id.tvMs4)
    TextView tvMs4;
    @BindView(R.id.tvJg4)
    TextView tvJg4;
    @BindView(R.id.recycleView4)
    RecyclerView recycleView4;

    @BindView(R.id.tvSx5)
    TextView tvSx5;
    @BindView(R.id.tvMs5)
    TextView tvMs5;
    @BindView(R.id.tvJg5)
    TextView tvJg5;
    @BindView(R.id.recycleView5)
    RecyclerView recycleView5;

    @BindView(R.id.tvSx6)
    TextView tvSx6;
    @BindView(R.id.tvMs6)
    TextView tvMs6;
    @BindView(R.id.tvJg6)
    TextView tvJg6;
    @BindView(R.id.recycleView6)
    RecyclerView recycleView6;

    List<String> list1=new ArrayList<>();
    List<String> list2=new ArrayList<>();
    List<String> list3=new ArrayList<>();
    List<String> list4=new ArrayList<>();
    List<String> list5=new ArrayList<>();
    List<String> list6=new ArrayList<>();

    private WDBYGSImageListAdapter adapter1;
    private WDBYGSImageListAdapter adapter2;
    private WDBYGSImageListAdapter adapter3;
    private WDBYGSImageListAdapter adapter4;
    private WDBYGSImageListAdapter adapter5;
    private WDBYGSImageListAdapter adapter6;
    private MineWDBYGSListBean mineWDBYGSListBean;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_wdbygsdetails);
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

        if(!(StringUtil.isNull(mineWDBYGSListBean.getCleaningImage()))){
            String[] images1=mineWDBYGSListBean.getCleaningImage().split(",");
            list1= Arrays.asList(images1);
        }
        if(!(StringUtil.isNull(mineWDBYGSListBean.getCoverageImage()))){
            String[] images2=mineWDBYGSListBean.getCoverageImage().split(",");
            list2= Arrays.asList(images2);
        }
        if(!(StringUtil.isNull(mineWDBYGSListBean.getEnclosureImage()))){
            String[] images3=mineWDBYGSListBean.getEnclosureImage().split(",");
            list3= Arrays.asList(images3);
        }
        if(!(StringUtil.isNull(mineWDBYGSListBean.getHardeningImage()))){
            String[] images4=mineWDBYGSListBean.getHardeningImage().split(",");
            list4= Arrays.asList(images4);
        }
        if(!(StringUtil.isNull(mineWDBYGSListBean.getInoutImage()))){
            String[] images5=mineWDBYGSListBean.getInoutImage().split(",");
            list5= Arrays.asList(images5);
        }
        if(!(StringUtil.isNull(mineWDBYGSListBean.getPublicityImage()))){
            String[] images6=mineWDBYGSListBean.getPublicityImage().split(",");
            list6= Arrays.asList(images6);
        }

            adapter1=new WDBYGSImageListAdapter(this,list1);
            adapter2=new WDBYGSImageListAdapter(this,list2);
            adapter3=new WDBYGSImageListAdapter(this,list3);
            adapter4=new WDBYGSImageListAdapter(this,list4);
            adapter5=new WDBYGSImageListAdapter(this,list5);
            adapter6=new WDBYGSImageListAdapter(this,list6);

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

        recycleView1.setAdapter(adapter1);
        recycleView2.setAdapter(adapter2);
        recycleView3.setAdapter(adapter3);
        recycleView4.setAdapter(adapter4);
        recycleView5.setAdapter(adapter5);
        recycleView6.setAdapter(adapter6);


        tvMs1.setText( StringUtil.isNullOrEmpty1(mineWDBYGSListBean.getCleaningCommonts()));
        tvMs2.setText(StringUtil.isNullOrEmpty1(mineWDBYGSListBean.getCoverageCommonts()));
        tvMs3.setText(StringUtil.isNullOrEmpty1(mineWDBYGSListBean.getEnclosureCommonts()));
        tvMs4.setText(StringUtil.isNullOrEmpty1(mineWDBYGSListBean.getHardeningCommonts()));
        tvMs5.setText(StringUtil.isNullOrEmpty1(mineWDBYGSListBean.getInoutCommonts()));
        tvMs6.setText(StringUtil.isNullOrEmpty1(mineWDBYGSListBean.getPublicityCommonts()));

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

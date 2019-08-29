package com.heziz.liyang.ui.mine.wdbygs;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.heziz.liyang.R;
import com.heziz.liyang.adaper.wdbygs.MineWDBYGSListAdapter;
import com.heziz.liyang.app.MyApplication;
import com.heziz.liyang.base.BaseActivity;
import com.heziz.liyang.bean.UserInfor;
import com.heziz.liyang.bean.mine.wdbygs.MineWDBYGSListBean;
import com.heziz.liyang.network.API;
import com.heziz.liyang.network.JsonCallBack1;
import com.heziz.liyang.network.OkGoClient;
import com.heziz.liyang.network.RequestBean;
import com.heziz.liyang.network.SRequstBean;
import com.heziz.liyang.view.SpinnerPopuwindow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MineWDBYGSListActivity extends BaseActivity implements View.OnClickListener {

    private int page = 1;
    private UserInfor userInfor;
    @BindView(R.id.rlBack)
    RelativeLayout rlBack;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.recycleView)
    RecyclerView recycleView;
    @BindView(R.id.btnSearch)
    Button btnSearch;
    @BindView(R.id.etName)
    EditText etName;
    @BindView(R.id.ll)
    LinearLayout ll;
    @BindView(R.id.tvSx)
    TextView tvSx;
    MineWDBYGSListAdapter adapter;
    private List<MineWDBYGSListBean> list=new ArrayList<>();

    private String type_sx;
    private SpinnerPopuwindow sxSpinnerPopuwindow;
    private List<String> sxData;

    Map<String, String> params1 = new HashMap<>();
    Map<String, String> params2 = new HashMap<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_wdbygslist);
        ButterKnife.bind(this);

        initViews();
        initDatas1();
        initListeners();
    }

    private void initListeners() {
        rlBack.setOnClickListener(this);
        btnSearch.setOnClickListener(this);
        tvSx.setOnClickListener(this);

        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                page++;
                initDatas();
            }
        });
    }

    private void initViews() {
        tvTitle.setText("五达标一公示");
        userInfor = MyApplication.getInstance().getUserInfor();
        SXData();
        adapter=new MineWDBYGSListAdapter(this,list);
        LinearLayoutManager manager=new LinearLayoutManager(this.getApplicationContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recycleView.setLayoutManager(manager);
        recycleView.setAdapter(adapter);
        adapter.bindToRecyclerView(recycleView);

        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if(list.get(position).getEndStatus()==5){
                    Intent intent = new Intent(mContext, MineWDBYGSZGDetailsActivity.class);
                    intent.putExtra("mineWDBYGSListBean", list.get(position));
                    startActivity(intent);
                }else if(list.get(position).getEndStatus()!=1&&list.get(position).getEndStatus()!=0){
                    Intent intent = new Intent(mContext, MineWDBYGSdetailsActivity.class);
                    intent.putExtra("mineWDBYGSListBean", list.get(position));
                    startActivity(intent);
                }

            }
        });
    }

    private void initDatas1(){
        params1.put("endStatus","");
        params1.put("projectName","");
        params2.put("pageSize", "10");
        if (userInfor.getPosition().equals("1")) {
            params1.put("startPopedomAccount", userInfor.getName() + "");
        } else if (userInfor.getPosition().equals("2")) {
            params1.put("startPopedomAccount", userInfor.getName() + "");
        } else if (userInfor.getPosition().equals("3")) {
            params1.put("startProjectAccount", userInfor.getAccount() + "");
        }
        showProgressDialog();
        initDatas();
    }
    private void initDatas() {
        String url2 = API.MINE_WDBYGS_LIST + "?access_token=" + MyApplication.getInstance().getUserInfor().getUuid();
        params2.put("pageNow", page + "");
        JsonCallBack1<SRequstBean<RequestBean<List<MineWDBYGSListBean>>>> jsonCallBack2 = new JsonCallBack1<SRequstBean<RequestBean<List<MineWDBYGSListBean>>>>() {
            @Override
            public void onSuccess(com.lzy.okgo.model.Response<SRequstBean<RequestBean<List<MineWDBYGSListBean>>>> response) {
                if (response.body().getData()!=null){
//                            carDDetailsBeanList.addAll(response.body().getData().getList());
                    if(response.body().getData().getList().size()!=0){
                        list.addAll(response.body().getData().getList());
                        adapter.loadMoreComplete();
                    }else{
                        if(page==1){
                            adapter.setEmptyView(R.layout.empty_view);
                        }else{
                            adapter.loadMoreEnd();
                        }
                    }
                }else{
                    adapter.loadMoreFail();
                }
                dissmissProgressDialog();
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onError(com.lzy.okgo.model.Response<SRequstBean<RequestBean<List<MineWDBYGSListBean>>>> response) {
                super.onError(response);
                dissmissProgressDialog();
            }

        };
        OkGoClient.getInstance()
                .postJsonData4(url2, params1, params2, "orderColumn", "create_time", jsonCallBack2);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rlBack:
                finish();
                break;
            case R.id.btnSearch:
                go();
                String s=etName.getText().toString();
//                if(s.equals("")){
//                    ToastUtil.showToast("请输入项目名称");
//                }else{
                    params1.put("projectName",s);
                    refresh();
//                }
                break;
            case R.id.tvSx:
                type_sx = tvSx.getText().toString();
                sxSpinnerPopuwindow = new SpinnerPopuwindow(MineWDBYGSListActivity.this,type_sx,sxData,jditemsOnClick);
                sxSpinnerPopuwindow.showPopupWindow(ll);
                sxSpinnerPopuwindow.setTitleText("状态");//给下拉列表设置标题
                break;
        }
    }

    private AdapterView.OnItemClickListener jditemsOnClick = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            String value = sxData.get(sxSpinnerPopuwindow.getText());
            tvSx.setText(value);
            sxSpinnerPopuwindow.dismissPopupWindow();
//            if(userInfor.getPosition().equals("2")){
//
//            }else{
                if(position==0){
                    params1.put("endStatus","");
                }else{
                    if(userInfor.getPosition().equals("3")){
                        params1.put("endStatus",position+"");
                    }else{
                        switch (position){
                            case 1:
                                params1.put("endStatus","1");
                                break;
                            case 2:
                                params1.put("endStatus","2");
                                break;
                            case 3:
                                params1.put("endStatus","4");
                                break;
                            case 4:
                                params1.put("endStatus","5");
                                break;
                        }
                    }

                }
//            }
            refresh();
        }
    };

    private void refresh(){
        list.clear();
        page=1;
        showProgressDialog();
        initDatas();
    }
    /**
     * 筛选数据
     */
    private void SXData() {
//        1未上传图片，2检查结束，状态正常，3不正常，4不正常，已通知项目方整改，5项目方整改完毕，检查结束
        sxData = new ArrayList<>();
        if(userInfor.getPosition().equals("1")||userInfor.getPosition().equals("2")){
            sxData.add("全部");
            sxData.add("未检查");
            sxData.add("合格");
//            sxData.add("不合格");
            sxData.add("通知整改");
            sxData.add("整改完毕");

//            sxData.add("通知整改");
//            sxData.add("整改完毕");
        }else{

            sxData.add("全部");
            sxData.add("未检查");//1
            sxData.add("合格");//2
            sxData.add("不合格");//3
        }

    }

    private void go() {

        Window window;
        window = getWindow();
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(window.getDecorView().getWindowToken(), 0);
    }
}

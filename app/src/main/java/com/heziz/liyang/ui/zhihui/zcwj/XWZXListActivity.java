package com.heziz.liyang.ui.zhihui.zcwj;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.heziz.liyang.R;
import com.heziz.liyang.adaper.XWZXListAdapter;
import com.heziz.liyang.app.MyApplication;
import com.heziz.liyang.base.BaseActivity;
import com.heziz.liyang.bean.UserInfor;
import com.heziz.liyang.bean.mine.XWZXBean;
import com.heziz.liyang.network.API;
import com.heziz.liyang.network.JsonCallBack1;
import com.heziz.liyang.network.OkGoClient;
import com.heziz.liyang.network.SRequstBean;
import com.heziz.liyang.utils.FileUtil;
import com.heziz.liyang.utils.ScreenUtils;
import com.heziz.liyang.utils.StringUtil;
import com.heziz.liyang.utils.ToastUtil;
import com.heziz.liyang.view.SlideRecyclerView;
import com.heziz.liyang.view.SpinnerPopuwindowBottom;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class XWZXListActivity extends BaseActivity implements View.OnClickListener{

    @BindView(R.id.rlBack)
    RelativeLayout rlBack;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.recycleView)
    SlideRecyclerView recycleView;
    @BindView(R.id.btnSearch)
    Button btnSearch;
    @BindView(R.id.etName)
    EditText etName;
    @BindView(R.id.tvSave)
    TextView tvSave;
    EditText etTitle;
    TextView tvName;
    private Dialog dialog;
    private UserInfor userInfor;
    Map<String, String> params = new HashMap<>();
    Map<String, String> params1 = new HashMap<>();
    private File file;
    private List<XWZXBean> list=new ArrayList<>();
    private XWZXListAdapter adapter;

    private int pageNow=1;
    private SpinnerPopuwindowBottom xgSpinnerPopuwindow;
    /**修改信息*/
    private List<String> xgData;

    @BindView(R.id.ll)
    LinearLayout ll;

    private int currentPosition;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xwzxlist);
        ButterKnife.bind(this);

        initViews();
        initDatas();
        initListeners();
    }

    private void initViews() {
        GXData();
        userInfor=MyApplication.getInstance().getUserInfor();
        if(userInfor.getPosition().equals("1")){
            tvSave.setVisibility(View.VISIBLE);
        }

        adapter=new XWZXListAdapter(this,list);
        LinearLayoutManager manager=new LinearLayoutManager(this.getApplicationContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recycleView.setLayoutManager(manager);
        recycleView.setAdapter(adapter);
        adapter.bindToRecyclerView(recycleView);
//
//        ItemDragAndSwipeCallback itemDragAndSwipeCallback = new ItemDragAndSwipeCallback(adapter);
//        itemDragAndSwipeCallback.setSwipeMoveFlags(ItemTouchHelper.START);
//        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(itemDragAndSwipeCallback);
//        itemTouchHelper.attachToRecyclerView(recycleView);
//
//// 开启拖拽
////        mAdapter.enableDragItem(itemTouchHelper, R.id.textView, true);
////        mAdapter.setOnItemDragListener(onItemDragListener);
//
//        OnItemSwipeListener onItemSwipeListener = new OnItemSwipeListener() {
//            @Override
//            public void onItemSwipeStart(RecyclerView.ViewHolder viewHolder, int pos) {
//                LogUtils.show("开始左滑");
//            }
//            @Override
//            public void clearView(RecyclerView.ViewHolder viewHolder, int pos) {
//                LogUtils.show("清除这一条");
//            }
//            @Override
//            public void onItemSwiped(RecyclerView.ViewHolder viewHolder, int pos) {
//                LogUtils.show("--------------");
//            }
//
//            @Override
//            public void onItemSwipeMoving(Canvas canvas, RecyclerView.ViewHolder viewHolder, float dX, float dY, boolean isCurrentlyActive) {
//                LogUtils.show("------"+dX+"--------"+dY);
//            }
//        };
//// 开启滑动删除
//        adapter.enableSwipeItem();
//        adapter.setOnItemSwipeListener(onItemSwipeListener);

        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

                String url=list.get(position).getUploadPath();
                if(url.endsWith("pdf")||url.endsWith("pdf")
                        ||url.endsWith("txt")
                        ||url.endsWith("doc")
                        ||url.endsWith("docx")
                        ||url.endsWith("jpg")
                        ||url.endsWith("jpeg")
                        ||url.endsWith("bmp")
                        ||url.endsWith("ppt")
                        ||url.endsWith("xls")
                        ||url.endsWith("ppt")
                        ||url.endsWith("pptx")
                        ||url.endsWith("xlsx")
                        ||url.endsWith("png")
                        ||url.endsWith("gif")){
//                    showProgressDialog();
//                    downLoad(url);
                    Intent intent=new Intent();
                intent.setClass(XWZXListActivity.this,WebActivity.class);
                intent.putExtra("url",list.get(position).getUploadPath());
                startActivity(intent);
                }else{
                    ToastUtil.showToast("文件格式不支持");
                }


            }
        });
        if(userInfor.getPosition().equals("1")){
            adapter.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
                    currentPosition=position;
                    xgSpinnerPopuwindow = new SpinnerPopuwindowBottom(XWZXListActivity.this,"",xgData,gxitemsOnClick);
                    xgSpinnerPopuwindow.showPopupWindowBottom(ll);
//                xgSpinnerPopuwindow.setTitleText("管辖级别");//给下拉列表设置标题
                    return false;
                }
            });
        }

    }

    private AdapterView.OnItemClickListener gxitemsOnClick = new AdapterView.OnItemClickListener() {
        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            String value = xgData.get(xgSpinnerPopuwindow.getText());
//            tvgx.setText(value);
            xgSpinnerPopuwindow.dismissPopupWindow();

            if (position==0){
//                ToastUtil.showToast("修改"+list.get(currentPosition).getId()+"号文件");
                showEditDialog();
            }else{
//                ToastUtil.showToast("删除"+list.get(currentPosition).getId()+"号文件");
                showDelDialog();
            }

        }
    };

    /**
     * 管辖级别数据
     */
    private void GXData() {
        xgData = new ArrayList<>();
        xgData.add("修改文件名");
        xgData.add("删除文件");
    }
    private void initDatas() {
        String url2 = API.MINE_XWZX_LIST;
        params.put("siteId",API.STATION);
        params1.put("pageNow",pageNow+"");
        params1.put("pageSize",10+"");
        JsonCallBack1<SRequstBean<List<XWZXBean>>> jsonCallBack2 = new JsonCallBack1<SRequstBean<List<XWZXBean>>>() {
            @Override
            public void onSuccess(com.lzy.okgo.model.Response<SRequstBean<List<XWZXBean>>> response) {
dissmissProgressDialog();


                if (response.body().getData()!=null){
//                            carDDetailsBeanList.addAll(response.body().getData().getList());
                    if(response.body().getData().size()!=0){
                        list.addAll(response.body().getData());
                        adapter.loadMoreComplete();
                    }else{
                        if(pageNow==1){
                            adapter.setEmptyView(R.layout.empty_view);
                        }else{
                            adapter.loadMoreEnd();
                        }
                    }
                }else{
                    adapter.loadMoreFail();
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onError(com.lzy.okgo.model.Response<SRequstBean<List<XWZXBean>>> response) {
                super.onError(response);
                dissmissProgressDialog();
            }

        };
        OkGoClient.getInstance()
                .postJsonData2(url2, params,params1, jsonCallBack2);
    }

    private void initListeners() {
        rlBack.setOnClickListener(this);
        tvSave.setOnClickListener(this);
        btnSearch.setOnClickListener(this);

        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                pageNow++;
                initDatas();
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.rlBack:
                finish();
                break;
            case R.id.tvSave:

                showDialog();
                break;
            case R.id.btnSearch:
                go();
                String name=etName.getText().toString();
                params.put("originalName", name);
                pageNow=1;
                list.clear();
                initDatas();
                break;

        }
    }

    private void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(XWZXListActivity.this,R.style.no_border_dialog);
        LayoutInflater inflater = LayoutInflater.from(XWZXListActivity.this);
        View v = inflater.inflate(R.layout.select_file_dialog_view, null);
        Button btnOk= (Button) v.findViewById(R.id.btnOk);
        Button btnCancle= (Button) v.findViewById(R.id.btnCancle);
        TextView tvSelect= (TextView) v.findViewById(R.id.tvSelect);
        tvName= (TextView) v.findViewById(R.id.tvName);
        etTitle= (EditText) v.findViewById(R.id.etFileName);
        LinearLayout llDialog= (LinearLayout) v.findViewById(R.id.llDialog);
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) llDialog.getLayoutParams();
        lp.width = ScreenUtils.getScreenWidth(XWZXListActivity.this);
        llDialog.setLayoutParams(lp);

        dialog = builder.create();
        dialog.show();
        dialog.setCancelable(false);
        dialog.getWindow().setContentView(v);
        dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        tvSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("*/*");
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                startActivityForResult(Intent.createChooser(intent, "Select a File to Upload"), 1);
            }
        });
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                String s=etTitle.getText().toString();

                if(file!=null){
                    if(StringUtil.isEmpty(s)){
                        ToastUtil.showToast("请输入文件名！");
                        return;
                    }
                    go();
                    uploadFile(s);
                    showProgressDialog();
                }else{
                    ToastUtil.showToast("请选择有效文件");
                }

            }

        });
        btnCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }

    private void go() {

        Window window;
        window = getWindow();
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(window.getDecorView().getWindowToken(), 0);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (data != null) {
                final Uri uri = data.getData();
                String path = FileUtil.getPath(this, uri);
//                String path = FileUtil.getRealFilePath(this, uri);
            /*这里要调用这个getPath方法来能过uri获取路径不能直接使用uri.getPath。
            因为如果选的图片的话直接使用得到的path不是图片的本身路径*/
            String aa=uri.getEncodedPath();
                Log.w("main",aa+"========"+path);
            if(path!=null){
                file = new File(path);
             /* 取得扩展名 */
                String fileName = file.getName();
//                Log.w("main","选择的文件路径;"+path+"-------文件名"+fileName);
                etTitle.setText(fileName);
                tvName.setText(fileName);
            }


            }
        }

    }



    private void uploadFile(String name){
        /**
         * 保存数据到服务器
         */
            //上传单个文件
            String url = API.FILE+name+"&siteId="+API.STATION;
//    File  file = new File(file.getAbsolutePath(), file.getName());
        List<File> list1=new ArrayList<>();
        list1.add(file);
            OkGo.<String>post(url)
                    .tag(this)
                    .addFileParams("file", list1)
                    .isMultipart(true)
                    .execute(new StringCallback() {
                        @Override
                        public void onSuccess(Response<String> response) {
//                            Log.e("main","上传成功" + response.body());
                            ToastUtil.showToast("上传成功");
                            dialog.dismiss();
                            etTitle.setText("");
                            params.put("originalName", "");
                            pageNow=1;
                            list.clear();
                            initDatas();
//                            dissmissProgressDialog();

                        }

                        @Override
                        public void onError(Response<String> response) {
//                            Log.e("main","上传失败" + response.body());
                            ToastUtil.showToast("上传失败");
                            dissmissProgressDialog();
                        }
                    });
    }


    private void showDelDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(XWZXListActivity.this,R.style.no_border_dialog);
        LayoutInflater inflater = LayoutInflater.from(XWZXListActivity.this);
        View v = inflater.inflate(R.layout.alter_dialog_delet_view, null);
        Button btnUp= (Button) v.findViewById(R.id.btnOk);
        Button btnCancel= (Button) v.findViewById(R.id.btnCancle);
        TextView tvTitle2= (TextView) v.findViewById(R.id.tvTitle2);
        tvTitle2.setText("确定要删除文件名为:"+list.get(currentPosition).getOriginalName()+"的文件吗?");
        LinearLayout llDialog= (LinearLayout) v.findViewById(R.id.llDialog);
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) llDialog.getLayoutParams();
        lp.width = ScreenUtils.getScreenWidth(XWZXListActivity.this);
        llDialog.setLayoutParams(lp);
        dialog = builder.create();
        dialog.show();
        dialog.setCancelable(false);
        dialog.getWindow().setContentView(v);

        btnUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                dialog.dismiss();
                delete();

            }

        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }

    private void delete() {
        showProgressDialog();
        String url2 = API.MINE_XWZX_LIST_DELETE ;
        Map<String, String> params = new HashMap<>();
//        params.put("access_token",MyApplication.getInstance().getUserInfor().getUuid());
        params.put("ids",list.get(currentPosition).getId()+"");
        JsonCallBack1<SRequstBean<XWZXBean>> jsonCallBack2 = new JsonCallBack1<SRequstBean<XWZXBean>>() {
            @Override
            public void onSuccess(com.lzy.okgo.model.Response<SRequstBean<XWZXBean>> response) {
                dissmissProgressDialog();
                ToastUtil.showToast("删除成功");
                list.remove(currentPosition);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onError(com.lzy.okgo.model.Response<SRequstBean<XWZXBean>> response) {
                super.onError(response);
                ToastUtil.showToast("删除失败");
                dissmissProgressDialog();
            }

        };
        OkGoClient.getInstance()
                .getJsonData1(url2, params, jsonCallBack2);
    }

    private void showEditDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(XWZXListActivity.this,R.style.no_border_dialog);
        LayoutInflater inflater = LayoutInflater.from(XWZXListActivity.this);
        View v = inflater.inflate(R.layout.edit_dialog_view, null);
        Button btnOk= (Button) v.findViewById(R.id.btnOk);
        Button btnCancle= (Button) v.findViewById(R.id.btnCancle);
        TextView tvSelect= (TextView) v.findViewById(R.id.tvSelect);
        etTitle= (EditText) v.findViewById(R.id.etFileName);
        LinearLayout llDialog= (LinearLayout) v.findViewById(R.id.llDialog);
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) llDialog.getLayoutParams();
        lp.width = ScreenUtils.getScreenWidth(XWZXListActivity.this);
        llDialog.setLayoutParams(lp);

        dialog = builder.create();
        dialog.show();
        dialog.setCancelable(false);
        dialog.getWindow().setContentView(v);
        dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        tvSelect.setText(list.get(currentPosition).getOriginalName());
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){

                String name=etTitle.getText().toString();
                if(name.equals("")){
                    ToastUtil.showToast("请输入新文件名!");
                }else{
                    dialog.dismiss();
                    edit(name);
                }


            }

        });
        btnCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }


    private void edit(String name) {
        showProgressDialog();
        String url2 = API.MINE_XWZX_LIST_EDITE ;
        Map<String, String> params = new HashMap<>();
//        params.put("access_token",MyApplication.getInstance().getUserInfor().getUuid());
        params.put("id",list.get(currentPosition).getId()+"");
        params.put("fileName",name);
        JsonCallBack1<SRequstBean<XWZXBean>> jsonCallBack2 = new JsonCallBack1<SRequstBean<XWZXBean>>() {
            @Override
            public void onSuccess(com.lzy.okgo.model.Response<SRequstBean<XWZXBean>> response) {
                dissmissProgressDialog();
                ToastUtil.showToast("修改成功");
                list.get(currentPosition).setOriginalName(name);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onError(com.lzy.okgo.model.Response<SRequstBean<XWZXBean>> response) {
                super.onError(response);
                ToastUtil.showToast("修改失败");
                dissmissProgressDialog();
            }

        };
        OkGoClient.getInstance()
                .getJsonData1(url2, params, jsonCallBack2);
    }
}

package com.heziz.liyang.ui.zhihui.sp;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.business.adapter.DataAdapteeImpl;
import com.android.business.adapter.DataAdapterInterface;
import com.android.business.adapter.DeviceWithChannelList;
import com.android.business.adapter.DeviceWithChannelListBean;
import com.android.business.entity.ChannelInfo;
import com.android.business.entity.DataInfo;
import com.android.business.entity.DeviceInfo;
import com.android.business.entity.GroupInfo;
import com.android.business.entity.LogicalInfo;
import com.android.business.exception.BusinessException;
import com.android.dahua.playmanager.IMediaPlayListener;
import com.android.dahua.playmanager.IPTZListener;
import com.android.dahua.playmanager.PlayManager;
import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.heziz.liyang.R;
import com.heziz.liyang.adaper.video.ChannelInfoAdapter;
import com.heziz.liyang.app.MyApplication;
import com.heziz.liyang.base.BaseActivity;
import com.heziz.liyang.bean.ProjectBean;
import com.heziz.liyang.bean.VProjectBean;
import com.heziz.liyang.network.API;
import com.heziz.liyang.network.JsonCallBack1;
import com.heziz.liyang.network.OkGoClient;
import com.heziz.liyang.network.SRequstBean;
import com.heziz.liyang.utils.OritationUtil;
import com.heziz.liyang.utils.ProjectUtils;
import com.heziz.liyang.utils.TimeUtils;
import com.heziz.liyang.utils.ToastUtil;
import com.mm.Api.Camera;
import com.mm.Api.DPSRTCamera;
import com.mm.Api.DPSRTCameraParam;
import com.mm.Api.Err;
import com.mm.uc.PlayWindow;

import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;

public class VXiangqingActivity extends BaseActivity implements View.OnClickListener,View.OnTouchListener {
    public static final int Stream_Main_Type = 1;		//主码流
    public static final int Stream_Assist_Type = 2;		//辅码流
    public static final int Stream_Third_Type = 3;		//三码流

    public static final int KEY_Handler_Stream_Played = 10;
    public static final int KEY_Handler_First_Frame = 20;
    public static final int KEY_Handler_Net_Error = 30;
    public static final int KEY_Handler_Play_Failed = 40;
    public static final int KEY_Handler_TIme_Out = 50;
    public static final int KEY_Handler_Talk_Success = 70;
    public static final int KEY_Handler_Talk_failed = 80;
    private RelativeLayout rlBack;
    private RelativeLayout llTitle;
    private RelativeLayout rlMain;
    private LinearLayout llV;
    private TextView tvTitle;
    private TextView tvName;
    private ImageView ivCamera;
    private ImageView ivJia;
    private ImageView ivJian;

    private LinearLayout llContorle;
    private LinearLayout rlUp;
    private LinearLayout rlLeft;
    private LinearLayout rlRight;
    private LinearLayout rlDown;
    private ImageView ivBig;
    private String name;
    private RecyclerView recycleView;
    private ChannelInfoAdapter adapter;
    private CheckBox cbPause;
    private VProjectBean vProjectBean;

    private PlayWindow mPlayWin;
    protected PlayManager mPlayManager;

    private Map<String, List<String>> devIdMap = new HashMap<>();
    private Map<String, List<String>> chnIdMap = new HashMap<>();
    private List<DataInfo> channelInfos = new ArrayList<>();
    private DataAdapterInterface dataAdapterInterface;
    private List<ChannelInfo> channels=new ArrayList<>();

    private List<GroupInfo> aaaa=new ArrayList<>();
    private int winIndex;
    private int currentNum;
    List<String> devIdList=new ArrayList<>();
    List<String> chanalList=new ArrayList<>();

    @BindView(R.id.cb)
    CheckBox cb;
    @BindView(R.id.llgcgk)
    LinearLayout llgcgk;
    @BindView(R.id.tvName)
    TextView tvName1;
    @BindView(R.id.tvAddress)
    TextView tvAddress;
    @BindView(R.id.tvFzr)
    TextView tvFzr;
    @BindView(R.id.tvPhone)
    TextView tvPhone;
    @BindView(R.id.tvStreet)
    TextView tvStreet;
    @BindView(R.id.tvJdFzr)
    TextView tvJdFzr;
    @BindView(R.id.tvJdPhone)
    TextView tvJdPhone;
    @BindView(R.id.tvGxjb)
    TextView tvGxjb;
    @BindView(R.id.tvType)
    TextView tvType;
    @BindView(R.id.tvSx)
    TextView tvSx;
    @BindView(R.id.tvXk)
    TextView tvXk;
    @BindView(R.id.tvkg)
    TextView tvkg;
    @BindView(R.id.tvjg)
    TextView tvjg;
    @BindView(R.id.ivDh)
    ImageView ivDh;
    @BindView(R.id.tvXmxj)
    TextView tvXmxj;
    @BindView(R.id.tvWlxj)
    TextView tvWlxj;
    @BindView(R.id.ivfzrcall)
    ImageView ivfzrcall;
    @BindView(R.id.ivfzrxx)
    ImageView ivfzrxx;
    @BindView(R.id.ivjdcall)
    ImageView ivjdcall;
    @BindView(R.id.ivjdxx)
    ImageView ivjdxx;
    ProjectBean bean;

    /*定位参数*/
    public LocationClient mLocationClient = null;
    private MyLocationListener myListener = new MyLocationListener();
    private BDLocation location1;
    private Long id;
    protected Handler mDeviceHander = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    dissmissProgressDialog();
                        for(int i=0;i<channelInfos.size();i++){
                            DataInfo dataInfo= channelInfos.get(i);
                            ChannelInfo channelInfo=null;
                            if(dataInfo instanceof ChannelInfo) {
                                channelInfo = (ChannelInfo) dataInfo;
                            } else if(dataInfo instanceof LogicalInfo) {
                                channelInfo = (ChannelInfo) ((LogicalInfo) dataInfo).getDataInfo();
                            }
                            channels.add(channelInfo);
                        }
                        adapter.notifyDataSetChanged();
                    if(channels.size()!=0){
                        for (int k=0;k<channels.size();k++){
                            if(channels.get(k).getState()== ChannelInfo.ChannelState.Online){
                                startPlay(channels.get(k));
                                return;
                            }
                        }
                        ToastUtil.showToast("设备离线");

                    }

//                    }
                    break;
                case 2:
                    GroupInfo info = (GroupInfo) msg.obj;
                    for(int i=0;i<aaaa.size();i++){
                        if (vProjectBean.getMedia_port().contains(aaaa.get(i).getDevList().get(0))){
                            getGroupDeviceList(aaaa.get(i));
                        }
//
                    }
                    break;
                case KEY_Handler_Stream_Played:
                    Log.w("main","当前播放winIndex=="+msg.obj);
//                    dissmissProgressDialog();
                    winIndex=(int)msg.obj;
                    break;
                case KEY_Handler_Play_Failed:
                    ToastUtil.showToast("播放失败");
                    mPlayManager.stop((int)msg.obj);
                break;
                case KEY_Handler_TIme_Out:
                    ToastUtil.showToast("网络超时");
                    mPlayManager.stop((int)msg.obj);
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vxiangqing);
        ButterKnife.bind(this);
        vProjectBean=(VProjectBean) getIntent().getSerializableExtra("project");
        name=vProjectBean.getName();
        initViews();
        initLocation();
        initDatas();

        initListeners();

        initvideoPlay();

    }

    private void initvideoPlay() {
        mPlayManager = new PlayManager();
        //初始化窗口数量，默认显示4个窗口，最多16窗口，若设置单窗口均设置为1
        mPlayManager.init(this, 1, 1, mPlayWin);
        //设置播放监听
        mPlayManager.setOnMediaPlayListener(iMediaPlayListener);
        //设置云台监听
        mPlayManager.setOnPTZListener(iptzListener);
        initCommonWindow();

    }

    /**
     * 批量播放
     */
    private void playBatch(ChannelInfo channelInfo){
    mPlayManager.playBatch(getCameras(channelInfo));
    }

    private List<Camera> getCameras(ChannelInfo channelInfo){
        List<Camera> cameras = new ArrayList<>();
//        for(ChannelInfo channelInfo : channels){
            cameras.add(getCamera(channelInfo));
//        }
        return cameras;
    }

    /**
     * 设置播放camera的参数
     * @param channelInfo
     * @return
     */
    private DPSRTCamera getCamera(ChannelInfo channelInfo){
        //创建播放Camera参数
        DPSRTCameraParam dpsrtCameraParam=new DPSRTCameraParam();
        //设置窗口要播放的通道ID
        dpsrtCameraParam.setCameraID(channelInfo.getChnSncode());
        try {
            dpsrtCameraParam.setDpHandle(String.valueOf(dataAdapterInterface.getDPSDKEntityHandle()));
        } catch (BusinessException e) {
            e.printStackTrace();
        }
        //获取码流类型
        int mStreamType = ChannelInfo.ChannelStreamType.getValue(channelInfo.getStreamType());
        if(mStreamType > Stream_Assist_Type) mStreamType = Stream_Assist_Type;
        dpsrtCameraParam.setStreamType(Stream_Main_Type);
        dpsrtCameraParam.setMediaType(3);
        dpsrtCameraParam.setCheckPermission(true);
        dpsrtCameraParam.setStartChannleIndex(-1);
        dpsrtCameraParam.setSeparateNum(0);
        dpsrtCameraParam.setTrackID("601");//h265视频播放

        DPSRTCamera dpsrtCamera=new DPSRTCamera(dpsrtCameraParam);
        return dpsrtCamera;
    }

    private IPTZListener iptzListener = new IPTZListener() {
        @Override
        public void onPTZControl(int winIndex, PtzOperation oprType, boolean isStop, boolean isLongPress) {
            sendPTZOperation(getPtzOperation(oprType), isStop);
            Log.w("main","onPTZControl");
        }

        @Override
        public void onPTZZooming(int winIndex, float scale, PtzOperation oprType, PtzZoomState state) {
            Log.w("main","onPTZZooming");
        }

    };

    private void sendPTZOperation(final ChannelInfo.PtzOperation operation, final boolean isStop){
        if(operation == null) return;
        mDeviceHander.post(new Runnable() {
            @Override
            public void run() {
                try {
                    dataAdapterInterface.operatePTZ(operation, channels.get(currentNum).getUuid(), 4, isStop);
                } catch (BusinessException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    public static ChannelInfo.PtzOperation getPtzZoomAddOperation(){
        ChannelInfo.PtzOperation operation = ChannelInfo.PtzOperation.zoomAdd;
//            operation = ChannelInfo.PtzOperation.zoomAdd;
        return operation;
    }
    public static ChannelInfo.PtzOperation getPtzZoomROperation(){
        ChannelInfo.PtzOperation operation  = ChannelInfo.PtzOperation.zoomReduce;
//        operation = ChannelInfo.PtzOperation.zoomReduce;
        return operation;
    }
    public static ChannelInfo.PtzOperation getPtzJiaoAddOperation(){
        ChannelInfo.PtzOperation operation = ChannelInfo.PtzOperation.focusAdd;
//        operation = ChannelInfo.PtzOperation.focusAdd;
        return operation;
    }
    public static ChannelInfo.PtzOperation getPtzJiaoROperation(){
        ChannelInfo.PtzOperation operation  = ChannelInfo.PtzOperation.focusReduce;
//        operation = ChannelInfo.PtzOperation.focusReduce;
        return operation;
    }
    public static ChannelInfo.PtzOperation getPtzGAddOperation(){
        ChannelInfo.PtzOperation operation = ChannelInfo.PtzOperation.apertureAdd;
//        operation = ChannelInfo.PtzOperation.apertureAdd;
        return operation;
    }
    public static ChannelInfo.PtzOperation getPtzGROperation(){
        ChannelInfo.PtzOperation operation  = ChannelInfo.PtzOperation.apertureReduce;
//        operation = ChannelInfo.PtzOperation.apertureReduce;
        return operation;
    }
    public static ChannelInfo.PtzOperation getPtzUpOperation(){
        ChannelInfo.PtzOperation operation = ChannelInfo.PtzOperation.up;
        return operation;
    }
    public static ChannelInfo.PtzOperation getPtzDownOperation(){
        ChannelInfo.PtzOperation operation = ChannelInfo.PtzOperation.down;
        return operation;
    }
    public static ChannelInfo.PtzOperation getPtzLeftOperation(){
        ChannelInfo.PtzOperation operation = ChannelInfo.PtzOperation.left;
        return operation;
    }
    public static ChannelInfo.PtzOperation getPtzRightOperation(){
        ChannelInfo.PtzOperation operation = ChannelInfo.PtzOperation.right;
        return operation;
    }
    public static ChannelInfo.PtzOperation getPtzOperation(IPTZListener.PtzOperation oprType){
        ChannelInfo.PtzOperation operation              = ChannelInfo.PtzOperation.stop;
        if(oprType == IPTZListener.PtzOperation.up)        operation = ChannelInfo.PtzOperation.up;
        if(oprType == IPTZListener.PtzOperation.down)      operation = ChannelInfo.PtzOperation.down;
        if(oprType == IPTZListener.PtzOperation.left)      operation = ChannelInfo.PtzOperation.left;
        if(oprType == IPTZListener.PtzOperation.right)     operation = ChannelInfo.PtzOperation.right;
        if(oprType == IPTZListener.PtzOperation.leftUp)    operation = ChannelInfo.PtzOperation.leftUp;
        if(oprType == IPTZListener.PtzOperation.leftDown)  operation = ChannelInfo.PtzOperation.leftDown;
        if(oprType == IPTZListener.PtzOperation.rightUp)   operation = ChannelInfo.PtzOperation.rightUp;
        if(oprType == IPTZListener.PtzOperation.rightDown) operation = ChannelInfo.PtzOperation.rightDown;

        return operation;
    }
    /**
     * 初始化视频窗口
     *
     */
    public void initCommonWindow() {
        DisplayMetrics metric = new DisplayMetrics();
        getWindowManager().getDefaultDisplay()
                .getMetrics(metric);
        int mScreenWidth = metric.widthPixels; // 屏幕宽度（像素）
        int mScreenHeight = metric.heightPixels; // 屏幕高度（像素）
        mScreenHeight = mScreenWidth * 3 / 4;
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) rlMain.getLayoutParams();
        lp.width = mScreenWidth;
        lp.height = mScreenHeight;
        rlMain.setLayoutParams(lp);
        RelativeLayout.LayoutParams lp1 = (RelativeLayout.LayoutParams) mPlayWin.getLayoutParams();
        lp1.width = mScreenWidth;
        lp1.height = mScreenHeight;
        mPlayWin.setLayoutParams(lp1);
        mPlayWin.forceLayout(mScreenWidth, mScreenHeight);
    }

    private void initViews() {
        id=getIntent().getLongExtra("id",0);
        llgcgk.setVisibility(View.GONE);
        dataAdapterInterface = DataAdapteeImpl.getInstance();
        rlBack= (RelativeLayout) findViewById(R.id.rlBack);
        ivCamera= (ImageView) findViewById(R.id.ivCamera);
        ivBig= (ImageView) findViewById(R.id.ivBig);
        ivJia= (ImageView) findViewById(R.id.ivJia);
        ivJian= (ImageView) findViewById(R.id.ivJian);

        llContorle= (LinearLayout) findViewById(R.id.llControle);

        rlUp= (LinearLayout) findViewById(R.id.rlUp);
        rlLeft= (LinearLayout) findViewById(R.id.rlLeft);
        rlRight= (LinearLayout) findViewById(R.id.rlRight);
        rlDown= (LinearLayout) findViewById(R.id.rlDown);


        tvTitle= (TextView) findViewById(R.id.tvTitle);
        tvTitle.setText("视频列表");
        recycleView= (RecyclerView) findViewById(R.id.recycleView);
        mPlayWin= (PlayWindow) findViewById(R.id.play_window);

        llTitle= (RelativeLayout) findViewById(R.id.llTitle);
        rlMain= (RelativeLayout) findViewById(R.id.rlMain);
        llV= (LinearLayout) findViewById(R.id.llV);

        cbPause= (CheckBox) findViewById(R.id.cbPause);
        cbPause.setButtonDrawable(R.drawable.sp_play);

    }
    private void initDatas() {
        getProjectDetails();
        adapter=new ChannelInfoAdapter(VXiangqingActivity.this,channels);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 3);
        gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        //LinearLayoutManager manager=new LinearLayoutManager(getApplicationContext());
        //manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recycleView.setLayoutManager(gridLayoutManager);
        recycleView.setAdapter(adapter);
        showProgressDialog();
        getGroupList();


    }

    private void getProjectDetails(){
         /*工程详情*/
        String url = API.PROJECT_DETAILS;
        Map<String,String> params=new HashMap<>();
        params.put("access_token", MyApplication.getInstance().getUserInfor().getUuid());
        params.put("id",id+"");
        JsonCallBack1<SRequstBean<ProjectBean>> jsonCallBack = new JsonCallBack1<SRequstBean<ProjectBean>>() {
            @Override
            public void onSuccess(com.lzy.okgo.model.Response<SRequstBean<ProjectBean>> response) {

                bean=response.body().getData();
                if(bean!=null){
                    tvName1.setText(bean.getName());
                    tvAddress.setText(bean.getAddress());
                    tvFzr.setText(ProjectUtils.getValue(bean.getXmLeader()));
                    tvPhone.setText(ProjectUtils.getValue(bean.getCreatePhone()));
                    tvStreet.setText(ProjectUtils.getValue(bean.getJdName()));
                    tvJdFzr.setText(ProjectUtils.getValue(bean.getWg()));
                    tvJdPhone.setText(ProjectUtils.getValue(bean.getJdPhone()+""));
                    tvGxjb.setText(ProjectUtils.getGXJB(bean.getGxjb()));
                    tvType.setText(ProjectUtils.getType(bean.getPType()));
                    tvXk.setText(ProjectUtils.getValue(bean.getNightConsNum()));
                    tvXmxj.setText(ProjectUtils.getValue(bean.getZjResult()));
                    tvWlxj.setText(ProjectUtils.getValue(bean.getWgResult()));
                    if(bean.getContractStartDate()!=null){
                        tvkg.setText(TimeUtils.getTime(bean.getContractStartDate()));
                    }else{
                        tvkg.setText("-");
                    }
                    if(bean.getContractEndDate()!=null){
                        tvjg.setText(ProjectUtils.getValue(TimeUtils.getTime(bean.getContractEndDate())));
                    }else{
                        tvjg.setText("-");
                    }

                    tvSx.setText(ProjectUtils.getDiff(bean.getDiff()));
                }


            }

            @Override
            public void onError(com.lzy.okgo.model.Response<SRequstBean<ProjectBean>> response) {
                super.onError(response);
                dissmissProgressDialog();
            }

        };
        OkGoClient.getInstance()
                .getJsonData1(url, params, jsonCallBack);
    }

    private void initListeners() {
        rlBack.setOnClickListener(this);
        ivCamera.setOnClickListener(this);
        ivBig.setOnClickListener(this);

        ivfzrcall.setOnClickListener(this);
        ivfzrxx.setOnClickListener(this);
        ivjdcall.setOnClickListener(this);
        ivjdxx.setOnClickListener(this);
        ivDh.setOnClickListener(this);

        ivJia.setOnClickListener(this);
        ivJian.setOnClickListener(this);

        ivJia.setOnTouchListener(this);
        ivJian.setOnTouchListener(this);


        rlUp.setOnTouchListener(this);
        rlDown.setOnTouchListener(this);
        rlLeft.setOnTouchListener(this);
        rlRight.setOnTouchListener(this);

        cbPause.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    mPlayManager.play(winIndex);
                }else{
                    mPlayManager.pause(winIndex);
                }
            }
        });

        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                currentNum=position;
                mPlayManager.stop(winIndex);
                //mPlayManager.removeStop(winIndex);
                startPlay(channels.get(position));

            }
        });

        cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    llgcgk.setVisibility(View.VISIBLE);
                }else{
                    llgcgk.setVisibility(View.GONE);
                }
            }
        });

    }
    private void getGroupList(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    GroupInfo groupInfo = dataAdapterInterface.queryGroupInfo(null);
                    List<GroupInfo> groupInfos = dataAdapterInterface.queryGroup(groupInfo.getGroupId());
                    for(GroupInfo info : groupInfos) {

                        if (info.getDevList() != null) {
                            aaaa.add(info);
                            Log.w("main","========="+vProjectBean.getMedia_port()+"======="+info.getDevList().get(0));
                            if(vProjectBean.getMedia_port().contains(info.getDevList().get(0))){
                                devIdList=info.getDevList();
                                getGroupDeviceList(info);
                                continue;
                            }
                            //此处需要注意，每个组织下可能还有子组织，按照自身的平台（每个平台组织不一样）进行递归查询各个子组织，获取所有组织下对应的设备通道
                            devIdMap.put(info.getGroupId(),info.getDevList());
                        }

                        if(info.getChannelList() != null) {
                            Log.w("main","====1111====="+vProjectBean.getMedia_port()+"======="+info.getChannelList().toString());
                            chnIdMap.put(info.getGroupId(), info.getChannelList());
                            for(int i=0;i<info.getChannelList().size();i++){
                                if(info.getChannelList().get(i).contains(vProjectBean.getMedia_port())){
                                    chanalList=info.getChannelList();
                                    getGroupDeviceList(info);
                                    break;
                                }
                            }


                        }
                    }
//                    Message msg = new Message();
//                    msg.what = home_bg_icon2;
//                    msg.obj = groupInfo;
//                    mDeviceHander.sendMessage(msg);
                } catch (BusinessException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void getGroupDeviceList(final GroupInfo groupInfo){
        new Thread(new Runnable() {
            @Override
            public void run() {
                DeviceWithChannelList deviceWithChannelList = null;
//                List<String> devIdList = groupInfo.getDevList();
                if(devIdList.size()!=0) {
                    try {
                        deviceWithChannelList = dataAdapterInterface.getDeviceList(devIdList);
                    } catch (BusinessException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        deviceWithChannelList = dataAdapterInterface.getDeviceListByChannelList(chanalList);
                    } catch (BusinessException e) {
                        e.printStackTrace();
                    }
                }
                if(deviceWithChannelList != null) {
                    if(deviceWithChannelList.getDevWithChannelList() != null) {
                        for(DeviceWithChannelListBean deviceWithChannelListBean : deviceWithChannelList.getDevWithChannelList()){
                            DeviceInfo deviceInfo = deviceWithChannelListBean.getDeviceInfo();
                            for(DataInfo dataInfo : deviceWithChannelListBean.getChannelList()) {
                                ChannelInfo channelInfo = (ChannelInfo) dataInfo;
                                channelInfo.setDeviceUuid(deviceInfo.getSnCode());
                                //此处获取视频通道，接口直接返回所有类型的通道，如报警通道，会产生重复数据，所以需要按需取通道，一般都是获取视频通道
                                if(channelInfo.getCategory() == ChannelInfo.ChannelCategory.videoInputChannel) {
                                    channelInfos.add(channelInfo);
                                }
                            }
                        }
                    }
                }
                mDeviceHander.sendEmptyMessage(1);
            }
        }).start();
    }
    private void startPlay(ChannelInfo channelInfo) {
//        tvChannle.setText("通道名："+channelInfo.getName());
        getCamera(channelInfo);
        if(channelInfo.getState()== ChannelInfo.ChannelState.Online){
            playBatch(channelInfo);
            mPlayManager.replay();
        }else{
            toast("此设备离线");
//            dissmissProgressDialog();
        }
    }
    private void captureBitmap() {
        if(!mPlayManager.isPlayed(mPlayManager.getSelectedWindowIndex())) return;
        int currentWindowIndex = mPlayManager.getSelectedWindowIndex();
        String path = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DCIM).getPath() + "/Pictures/" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + ".jpg";
        int ret = mPlayManager.snapShot(currentWindowIndex, path, false);
        if (ret == Err.OK) {
            toast("抓图成功");
            MediaScannerConnection.scanFile(this, new String[]{path}, null, null);
        } else {
            toast("抓图失败");
        }
    }

    private IMediaPlayListener iMediaPlayListener = new IMediaPlayListener(){
        @Override
        public void onPlayeStatusCallback(int winIndex, PlayStatusType type) {
            super.onPlayeStatusCallback(winIndex, type);
            Message msg = Message.obtain();
            msg.obj = winIndex;
            if(type == PlayStatusType.eStreamPlayed){
                msg.what = KEY_Handler_Stream_Played;
                if(mDeviceHander != null) mDeviceHander.sendMessage(msg);
            //}
//            else if(type == PlayStatusType.ePlayFirstFrame){
//                msg.what = KEY_Handler_First_Frame;
//                if(mPlayOnlineHander != null) mPlayOnlineHander.sendMessage(msg);
//            }else if(type == PlayStatusType.eNetworkaAbort){
//                msg.what = KEY_Handler_Net_Error;
//                if(mPlayOnlineHander != null) mPlayOnlineHander.sendMessage(msg);
            }else if(type == PlayStatusType.ePlayFailed){
                msg.what = KEY_Handler_Play_Failed;
                if(mDeviceHander != null) mDeviceHander.sendMessage(msg);
            }else if(type== PlayStatusType.eStatusTimeOut){
                msg.what = KEY_Handler_TIme_Out;
                if(mDeviceHander != null) mDeviceHander.sendMessage(msg);
            }
        }
    };
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.rlBack:

               exit();
                break;
            case R.id.ivCamera:
                if (ContextCompat.checkSelfPermission(this, READ_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    //申请WRITE_EXTERNAL_STORAGE权限
                    ActivityCompat.requestPermissions(this, new String[]{READ_EXTERNAL_STORAGE},
                            100);
                }else{

                    captureBitmap();
                }
                break;

            case R.id.ivBig:
                if(OritationUtil.getOritation(this)){
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);// 横屏
                }else{
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);// 竖屏
                }
                break;
            case R.id.ivfzrcall:
                callPhone(tvPhone.getText().toString());
                break;
            case R.id.ivjdcall:
                callPhone(tvJdPhone.getText().toString());
                break;
            case R.id.ivfzrxx:
                sendSmsWithBody(tvPhone.getText().toString());
                break;
            case R.id.ivjdxx:
                sendSmsWithBody(tvJdPhone.getText().toString());
                break;
            case R.id.ivDh:
                if (isAvilible(this, "com.autonavi.minimap")||isAvilible(this, "com.baidu.BaiduMap")){
                    if(isAvilible(this, "com.baidu.BaiduMap")){
                        setUpBaiduAPPByLoca();
                    }else{
                        startNaviGao();
                    }
                }else{
                    toast("请先安装百度或高德地图客户端");
                }

                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 100:
                // 授权被允许
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    captureBitmap();
                } else {
                    toast("缺少权限");
                }
                break;
        }
    }
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        switch (view.getId()){
            case R.id.ivJia:
                if(motionEvent.getAction()==MotionEvent.ACTION_DOWN){
//                    Log.w("main","++++++++++++++");
                    sendPTZOperation(getPtzZoomAddOperation(),false);
                }else if(motionEvent.getAction()==MotionEvent.ACTION_UP){
//                    Log.w("main","-----------------");
                    sendPTZOperation(getPtzZoomAddOperation(),true);
                }
                break;
            case R.id.ivJian:
                if(motionEvent.getAction()==MotionEvent.ACTION_DOWN){
                    sendPTZOperation(getPtzZoomROperation(),false);
                }else if(motionEvent.getAction()==MotionEvent.ACTION_UP){
                    sendPTZOperation(getPtzZoomROperation(),true);
                }
                break;

            case R.id.rlUp:
                if(motionEvent.getAction()==MotionEvent.ACTION_DOWN){
                    sendPTZOperation(getPtzUpOperation(),false);
                }else if(motionEvent.getAction()==MotionEvent.ACTION_UP){
                    sendPTZOperation(getPtzUpOperation(),true);
                }
                break;
            case R.id.rlLeft:
                if(motionEvent.getAction()==MotionEvent.ACTION_DOWN){
                    sendPTZOperation(getPtzLeftOperation(),false);
                }else if(motionEvent.getAction()==MotionEvent.ACTION_UP){
                    sendPTZOperation(getPtzLeftOperation(),true);
                }
                break;
            case R.id.rlRight:
                if(motionEvent.getAction()==MotionEvent.ACTION_DOWN){
                    sendPTZOperation(getPtzRightOperation(),false);
                }else if(motionEvent.getAction()==MotionEvent.ACTION_UP){
                    sendPTZOperation(getPtzRightOperation(),true);
                }
                break;
            case R.id.rlDown:
                if(motionEvent.getAction()==MotionEvent.ACTION_DOWN){
                    sendPTZOperation(getPtzDownOperation(),false);
                }else if(motionEvent.getAction()==MotionEvent.ACTION_UP){
                    sendPTZOperation(getPtzDownOperation(),true);
                }
                break;
        }
        return false;
    }
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            llContorle.setVisibility(View.VISIBLE);
            llTitle.setVisibility(View.GONE);
            initCommonWindow1();
            llV.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
            ivJia.setImageResource(R.drawable.sp_fd);
            ivJian.setImageResource(R.drawable.sp_sx);

            ivCamera.setImageResource(R.drawable.sp_cut);
            ivBig.setImageResource(R.drawable.sp_full);
//            rlTitle.setVisibility(View.VISIBLE);

        } else {
            llContorle.setVisibility(View.GONE);
            llTitle.setVisibility(View.VISIBLE);
            initCommonWindow();
            llV.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
//            text_screen.append("\n 当前屏幕为竖屏");
            ivJia.setImageResource(R.drawable.sp_fd);
            ivJian.setImageResource(R.drawable.sp_sx);

            ivCamera.setImageResource(R.drawable.sp_cut);
            ivBig.setImageResource(R.drawable.sp_full);
//            rlTitle.setVisibility(View.GONE );
        }
        super.onConfigurationChanged(newConfig);
    }


    /**
     * 初始化视频窗口
     *
     */
    public void initCommonWindow1() {
        DisplayMetrics metric = new DisplayMetrics();
        getWindowManager().getDefaultDisplay()
                .getMetrics(metric);
        int mScreenWidth = metric.widthPixels; // 屏幕宽度（像素）
        int mScreenHeight = metric.heightPixels; // 屏幕高度（像素）
//        mScreenHeight = mScreenWidth * home_bg_icon3 / home_bg_icon4;
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) rlMain.getLayoutParams();
        lp.width = mScreenWidth;
        lp.height = mScreenHeight;
        rlMain.setLayoutParams(lp);
        RelativeLayout.LayoutParams lp1 = (RelativeLayout.LayoutParams) mPlayWin.getLayoutParams();
        lp1.width = mScreenWidth;
        lp1.height = mScreenHeight;
        mPlayWin.forceLayout(mScreenWidth, mScreenHeight);
    }

    private void exit() {
//        showProgressDialog();
        try {
            boolean a=dataAdapterInterface.logout();
//            dissmissProgressDialog();
            if (a){
                finish();
            }else{
                Toast.makeText(this, "退出失败", Toast.LENGTH_SHORT).show();
            }

        } catch (BusinessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onBackPressed() {
        exit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPlayManager != null) {
            mPlayManager.uninit();
            mPlayManager = null;
        }
    }

    public void callPhone(String phoneNum) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        Uri data = Uri.parse("tel:" + phoneNum);
        intent.setData(data);
        startActivity(intent);
    }
    public void sendSmsWithBody(String number) {
        Intent sendIntent = new Intent(Intent.ACTION_SENDTO);
        sendIntent.setData(Uri.parse("smsto:" + number));
//        sendIntent.putExtra("sms_body", body);
        startActivity(sendIntent);
    }

    public void setUpBaiduAPPByLoca(){
        try {
            Intent intent = Intent.getIntent("intent://map/direction?origin=latlng:"+location1.getLatitude()+","+location1.getLongitude()+"|name:我的位置&destination=latlng:"+bean.getLatitude()+","+bean.getLongitude()+"|name:"+bean.getAddress()+"&mode=driving&src=yourCompanyName|yourAppName#Intent;scheme=bdapp;package=com.baidu.BaiduMap;end");
            startActivity(intent);

        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
    public void startNaviGao() {
        try {
            //sourceApplication
            Intent intent = Intent.getIntent("androidamap://route?sourceApplication=softname&slat="+location1.getLatitude()+"&slon="+location1.getLongitude()+"&sname="+"我的位置"+"&dlat="+bean.getLatitude()+"&dlon="+bean.getLongitude()+"&dname="+bean.getAddress()+"&dev=0&m=0&t=1");
            startActivity(intent);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
    //验证各种导航地图是否安装
    public static boolean isAvilible(Context context, String packageName) {
        //获取packagemanager
        final PackageManager packageManager = context.getPackageManager();
        //获取所有已安装程序的包信息
        List<PackageInfo> packageInfos = packageManager.getInstalledPackages(0);
        //用于存储所有已安装程序的包名
        List<String> packageNames = new ArrayList<String>();
        //从pinfo中将包名字逐一取出，压入pName list中
        if (packageInfos != null) {
            for (int i = 0; i < packageInfos.size(); i++) {
                String packName = packageInfos.get(i).packageName;
                packageNames.add(packName);
            }
        }
        //判断packageNames中是否有目标程序的包名，有TRUE，没有FALSE
        return packageNames.contains(packageName);
    }

    public class MyLocationListener extends BDAbstractLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location){
            location1=location;
        }
    }

    private void initLocation(){
        mLocationClient = new LocationClient(this);
        //声明LocationClient类
        mLocationClient.registerLocationListener(myListener);
        LocationClientOption option = new LocationClientOption();
        option.setIsNeedAddress(true);
        //可选，是否需要地址信息，默认为不需要，即参数为false
        //如果开发者需要获得当前点的地址信息，此处必须为true
        mLocationClient.setLocOption(option);
        mLocationClient.start();
    }
}

package com.heziz.liyang.network;

/**
 * Created by Administrator on 2016/9/21.
 *
 */

public class API {



 public static String base_url = "https://apis.heziz.com/";
 public static String base_url1 = "https://apis.heziz.com/hzxm/web/";

 public static String base_url2 = "http://120.27.0.207:31007/";
 public static String base_url3 = "http://120.27.3.172:31003/";
 public static String base_url4 = "https://jg.heziz.com/";

 //login_btn
 public static String LOGIN=base_url+"urmp/getToken/getTokenByUser1";
 //修改密码
 public static String PASSWORD_XG=base_url+"/urmp/user/alterPassword";

 //溧阳站点id
 public static String STATION="1541992240945678";

 //首页
 public static String HOME_DATA1=base_url1+"getQxAppProjectVo";

 //首页天气预报（新）
 public static String HOME_WHEATHER=base_url+"hzjg/weatherForecast/getRequestWeatherForecastDataTask";
 //首页空气质量（新）
 public static String HOME_WHEATHER1=base_url+"hzjg/airQuality/getRequestKqzlCityListDataTask";

 //首页列表
 public static String HOME_DATA2=base_url1+"queryEveryPopedom";
 //首页--二级页面--全部项目列表
 public static String HOME_TOTAL_PROJECT_LIST=base_url1+"queryForPageAndStreet";
 //工程列表 home_map
 public static String PROJECT_LIST=base_url1+"queryByCondition";
 public static String PROJECT_LIST1=base_url1+"queryForsubList";
// public static String PROJECT_LIST1=base_url1+"queryForsubList";
 //街道列表
 public static String STREET_LIST=base_url+"urmp/pRole/queryAreaIdList?siteId="+STATION;
 //项目详情
 public static String PROJECT_DETAILS=base_url1+"getProjectUnits";
 //通过项目id获取车辆未冲洗name
 public static String CAR_COUNT=base_url+"hzjg/qxNewDemand/getAllInfoByProject";
 //通过项目id获取扬尘设备列表
 public static String YC_LIST=base_url+"hzjg/weather/getWeatherDeviceForPage";
 //通过项目id车辆未冲洗列表
 public static String CAR_PROJECT_LIST=base_url+"hzjg/ftpCarsInfoManager/getFtpEsInfoByProject";
 //通过项目id车辆未冲洗本日本周本月违章数量
 public static String CAR_WZ_NUM=base_url+"hzjg/ftpCarsInfoManager/getFtpEsInfoByProjectByDate";
 //通过设备id车辆未冲洗列表
 public static String CAR_DEVICE_LIST=base_url+"hzjg/qxNewDemand/getFtpAlarmByDivId";
 //websocket地址
public static String WEBSOCKET_URL="ws://skt.heziz.com:28020/websocket";
 //根据设备id查看扬尘折线图
 public static String YC_HISTORY_URL=base_url+"hzjg/newDemandForDust/getAWeekOrOnDayAvgOfFugitiveDustByDeviceId/1"; //根据项目id获取扬尘报警数据
 public static String YC_HISTORY_URL1=base_url+"hzjg/newDemandForDust/getAWeekOrOnDayAvgOfFugitiveDustByDeviceId1/"; //根据项目id获取扬尘报警数据
 public static String YC_BJ_URL=base_url+"hzjg/elastic/getyangchencountByDate";
 //根据项目id获取扬尘报警数据列表
 public static String YC_BJ_LIST=base_url+"hzjg/elastic/getWeatherEsInfoByProject";

 //智慧工地扬尘、视频、车辆数据
 public static String ZH_DATA1=base_url+"hzjg/qxNewDemand/getAllDeviceInfoByProject";
 //智慧工地非道路机械数据
 public static String ZH_DATA2=base_url+"hzjg/projectDieselOilOpt/statisticsNowDayRecord";
 //智慧工地塔吊数据
 public static String ZH_DATA3=base_url+"hzjg/tower/queryDeviceCountVo";
 //智慧工地实名制考勤数据
 public static String ZH_DATA4=base_url+"hzjg/employeeproject/queryDeviceCountVo";
 //根据项目id查看视频设备数
 public static String SP_LIST=base_url+"hzjg/video/getVideoDeviceForPage";

 //扬尘界面获取各设备总数，在离线数（统计信息）
 public static String YC_STREET_NUM=base_url+"hzjg/elastic/getAllYangchenDeviceIsOnlineByStation";
 //扬尘界面获取各街道在离线数列表
 public static String YC_STREET_LIST=base_url+"hzjg/qxNewDemand/getYangchenIsOnlineByManagerRole";
 //扬尘界面获取各街道项目在离线列表
 public static String YC_STREET_PROJECT_LIST=base_url+"hzjg/qxNewDemand/getYangchenIsOnlineavgByRole";
 //通过项目id获取扬尘设备列表(在离线情况)
 public static String YC_LIST_DEVICE=base_url+"hzjg/qxNewDemand/getDavidIDByProject";

 //视频界面获取各设备总数，在离线数（统计信息）
 public static String SP_STREET_NUM=base_url+"hzjg/qxNewDemand/getvideoonline";
 //视频界面获取各街道在离线数列表
 public static String SP_STREET_LIST=base_url+"hzjg/qxNewDemand/viedoOnlineByManagerRole/1/10";
 //根据街道id或者项目id获取视频工地列表
 public static String SP_PROJECT_LIST=base_url+"hzjg/qxNewDemand/getviedoIsOnlineByRole";

 //升降机界面获取各设备总数，在离线数（统计信息）
 public static String SJJ_STREET_NUM=base_url3+"projQTZ/getOffline";
 //升降机界面获取各街道设备总数，在离线数（统计信息）
 public static String SJJ_STREET_LIST=base_url3+"projQTZ/queryEveryPopedom";
 //升降机界面获取各街道设备总数，在离线数（统计信息）
 public static String SJJ_PROJECT_LIST=base_url+"hzjg/towerCrane/queryPage";
 //升降机界面获取各街道设备总数，在离线数（统计信息）
 public static String SJJ_DETAILS_ED=base_url3+"projQTZ/queryList";
 //升降机界面身份识别
 public static String SJJ_DETAILS_SFSB=base_url3+"projQTZ/getAuthenticationInfo";
 //升降机界面识别记录列表
 public static String SJJ_DETAILS_SBJL=base_url4+"linshi/projQTZ/getAuthenticationDataPage";
 //升降机界面报警列表
 public static String SJJ_BJ_LIST=base_url3+"projQTZ/searchLifterData";
 //升降机界面报警数量
 public static String SJJ_BJ_NUM=base_url3+"projQTZ/getLifterAlarmCountDWM";

 //车辆冲洗界面获取各设备总数，在离线数（统计信息）
 public static String CL_STREET_NUM=base_url+"hzjg/qxNewDemand/geturmpAllFTPdeviceisonline";
 //车辆冲洗界面获取各街道在离线数列表
 public static String CL_STREET_LIST=base_url+"hzjg/qxNewDemand/getFtpIsOnlineByManagerRole";
 //根据街道id或者项目id获取车辆冲洗工地列表
 public static String CL_PROJECT_LIST=base_url+"hzjg/qxNewDemand/getFtpIsOnlineavgByRole";
 //根据项目id查看车辆设备数
 public static String CL_LIST=base_url+"hzjg/qxNewDemand/getFTPbyproject";

 //根据设备id查看车辆未冲洗列表
 public static String CL_NEW_LIST=base_url+"hzjg/ftpCarsInfoManager/getRequesEsCarsInfo";


 //塔吊街道设备在离线list数据
 public static String TD_STREET_LIST=base_url+"hzjg/tower/queryDeviceCountListVo";
 //塔吊项目list数据
 public static String TD_PROJECT_LIST=base_url+"hzjg/tower/queryProjectTowersPage";
 //根据项目id获取塔吊报警数量
 public static String TD_BJ_URL=base_url+"hzjg/tower/statistics";
 //根据项目id获取塔吊报警数据列表
 public static String TD_BJ_LIST=base_url+"hzjg/tower/queryAlarm";

 //实名制街道设备在离线list数据
 public static String SMZ_STREET_LIST=base_url+"hzjg/employeeproject/queryDeviceCountListVo";
 //实名制项目list数据
 public static String SMZ_PROJECT_LIST=base_url+"hzjg/employeeproject/queryProjectEmploysPage";
 //实名制项目花名册
 public static String SMZ_HMC_LIST=base_url+"hzjg/employeeproject/roster";
 //实名制当天历史考勤
 public static String SMZ_DAY_HISTORY_LIST=base_url+"hzjg/employeeproject/attendanceData";



 //获取非道路机械的各街道的数据list
 public static String FDL_STREET_LIST=base_url+"hzjg/projectDieselOilOpt/statisticsNowDayRecordBy";
 //获取非道路机械的项目数据list
 public static String FDL_PROJECT_LIST=base_url+"hzjg/projectDieselOilOpt/statisticsNowDayRecordByCondition";
 //获取非道路机械的项目下具体购油数据list
 public static String FDL_PROJECT_DETAILS_LIST=base_url+"hzjg/projectDieselOilOpt/queryTDieselOilList";

 //日常任务--五达标一公示
// public static String RCRW_WDBYGS=base_url+"hzxm/daliy/queryZCCountVo";
 //五达标一公示--已查项目列表
 public static String WDBYGS_YC_LIST=base_url+"hzxm/daliy/searchPage";
 //五达标一公示--未查项目列表
 public static String WDBYGS_WC_LIST=base_url+"hzxm/daliy/queryNotCheck";
 //五达标一公示--合格列表
 public static String HG_LIST=base_url+"hzxm/daliy/searchList";
 //五达标一公示--不合格列表
 public static String BHG_LIST=base_url+"hzxm/daliy/searchList";

 //日常任务--五达标一公示
// public static String RCRW_WDBYGS=base_url+"hzxm/daliy/queryZCCountVo";
// //日常任务--网络人员
// public static String RCRW_WLRY=base_url+"hzxm/daliy/queryWCCountVo";
 public static String WEB_URL1="https://oa.heziz.com/loginApp";
 //日常任务--安全检查统计数据
 public static String RCRW_AQJC=base_url+"hzxm/managerSafe/statistics";
 //日常任务--质量检查统计数据
 public static String RCRW_ZLJC=base_url+"hzxm/managerQuality/statistics";
 //安全检查--已查未查项目列表
 public static String RCRW_AQJC_CHECK_LIST=base_url+"hzxm/managerSafe/queryChecked";
 //质量检查--已查未查项目列表
 public static String RCRW_ZLJC_CHECK_LIST=base_url+"hzxm/managerQuality/queryChecked";
 //安全检查--合格项目列表
 public static String RCRW_AQJC_HG_LIST=base_url+"hzxm/managerSafe/queryPassed";
 //质量检查--合格项目列表
 public static String RCRW_ZLJC_HG_LIST=base_url+"hzxm/managerQuality/queryPassed";



 //日常任务--五达标一公示
 public static String RCRW_WDBYGS=base_url+"hzxm/daliy/queryZCCountVo";
 //日常任务--网络人员
 public static String RCRW_WLRY=base_url+"hzxm/daliy/queryWCCountVo";
 //网格人员检查情况和项目方自查情况详情列表
 public static String WG_XM_LIST=base_url+"hzxm/daliy/queryPage";
 //日常检查
 public static String RCJC_LIST=base_url+"hzxm/daliy/searchPage";
 //专项检查
 public static String ZXJC_LIST=base_url+"hzxm/customDaliy/getSimplePage";
 //新增专项检查推送
 public static String ZXJC_NOTICE=base_url+"urmp/appPush/push";
 public static String ZXJC_NOTICE_NO=base_url+"urmp/appPush/notpush";
 //新增专项检查推送获取人名
 public static String RM=base_url+"urmp/user/getUsernameByAccount";
 //新增专项检查
 public static String NEW_ZXCHECK=base_url+"hzxm/customDaliy/selfAdd";
 //专项检查详情
 public static String ZXCHECK_DETAILS=base_url+"hzxm/customDaliy/getDetail";
 //专项审核
 public static String ZXCHECK_SH=base_url+"hzxm/customDaliy/selfModify";
 //专项检查--删除列表
 public static String ZXCHECK_DELETE=base_url+"hzxm/customDaliy/deleteByIdAndEndStatus";

 //日常检查--新增--选择项目列表
 public static String RCJC_PROJECT_LIST=base_url1+"getProjectInfoList";
 //日常检查--删除--选择项目列表
 public static String RCJC_DELETE_CHECK=base_url+"hzxm/daliy/delete";

 //日常检查--新增检查
 //public static String RCJC_ADD_CHECK=base_url+"hzxm/daliy/add";
 public static String RCJC_ADD_CHECK=base_url+"hzxm/daliy/jgAdd";
 //日常检查--修改整改状态
 public static String CHANGE_STATUS_CHECK=base_url+"hzxm/daliy/jgModify";

 //项目方自查
 public static String XMF_ZC_CHECK_LIST=base_url+"hzxm/daliy/searchSimplePage";
 //项目方自查--新增
 public static String XMF_ZC_CHECK_NEW=base_url+"hzxm/daliy/selfAdd";
 //项目方自查--详情
 public static String XMF_CHECK_DETAILS=base_url+"hzxm/daliy/getDetail";
 //项目方自查--复查
 public static String XMF_CHECK_FC=base_url+"hzxm/daliy/selfModify";





 //我的界面
 public static String YC_CL_NUM=base_url+"hzjg/qxNewDemand/getProjectAlarmByprojectId";
 //我的--扬尘报警列表
 public static String MINE_YC_LIST=base_url+"hzjg/qxNewDemand/getWeatherAlarmByProject";
 //我的--扬尘报警列表--工地详情列表
 public static String MINE_YC_DETAILS_LIST=base_url+"hzjg/qxNewDemand/getWeatherAlarmByprojectId";
 //我的--扬尘报警列表--工地详情列表
 public static String MINE_YC_DETAILS_LIST1=base_url+"hzjg/qxNewDemand/getWeatherAlarmByprojectId1";
 //我的--车辆报警列表
 public static String MINE_CLCX_LIST=base_url+"hzjg/qxNewDemand/getFtpAlarmByProject";
 //我的--车辆报警列表--工地详情列表
 public static String MINE_CLCX_DETAILS_LIST=base_url+"hzjg/qxNewDemand/getFtpAlarmByprojectId";
 //我的--车辆报警列表--工地详情列表
 public static String MINE_CLCX_DETAILS_LIST1=base_url+"hzjg/qxNewDemand/getFtpAlarmByprojectId1";
 //我的--五达标一公示list
 public static String MINE_WDBYGS_LIST=base_url+"hzxm/daliy/searchAPPPage";
 //我的--专项检查list
 public static String MINE_ZXJC_LIST=base_url+"hzxm/customDaliy/searchPage";

 //我的--非道路机械管理list
 public static String MINE_FDLJX_LIST=base_url+"hzjg/projectDieselOilOpt/queryForPage";
 //我的--非道路机械管理改变状态
 public static String MINE_FDLJX_ZT_LIST=base_url+"hzjg/projectDieselOilOpt/updateTDieselOil";
//我的--新闻资讯列表
 public static String MINE_XWZX_LIST=base_url2+"policyDoc/queryList";

 //我的--新闻资讯列表--删除
 public static String MINE_XWZX_LIST_DELETE=base_url2+"policyDoc/delete";

 //我的--新闻资讯列表--编辑
 public static String MINE_XWZX_LIST_EDITE=base_url2+"policyDoc/modify";

 //视频项目列表
 public static String VIDEO_LIST=base_url+"app/clock/getVedioList";

 public static String FILE=base_url2+"policyDoc/add?fileName=";

 //文件上传
 public static String IMAGE_FILE_UPLOAD="https://appaj.heziz.com/upload/file/uploadSftpTest";
}

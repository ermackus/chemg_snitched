package com.kingagroot.kingdraw.config;

public class NetConfig
{
    public static int SUCCESS_CODE = 200;
    public static String aesKey = "zbchala12312{{78";
    public static String jsAccountUrl;
    public static String jsElnWelcome;
    public static String jsGroupCreate;
    public static String jsGroupManage;
    public static String jsMessageUrl;
    public static String jsWorkUrl;
    public static String mqttUrl;
    public static String pediaUrl;
    public static String slideCnUrl;
    public static String slideEnUrl;
    public static String templateCnUrl;
    public static String templateEnUrl;
    public static String url;
    
    static {
        if (AppConfig.RELEASE == Release.STANDARD) {
            NetConfig.url = "https://c-api.kingdraw.com";
            NetConfig.jsAccountUrl = "https://client.spa.kingdraw.com/account/";
            NetConfig.jsMessageUrl = "https://client.spa.kingdraw.com/message/";
            NetConfig.jsWorkUrl = "https://client.spa.kingdraw.com/work/";
            NetConfig.jsGroupCreate = "https://client.spa.kingdraw.com/register-group-mobile/";
            NetConfig.pediaUrl = "https://baike.kingdraw.com/Scripts/mobile/index.html";
            NetConfig.templateCnUrl = "https://chem.kingdraw.com/?source=5&tab=2";
            NetConfig.templateEnUrl = "https://chem.kingdraw.com/chemists?source=5&tab=2";
            NetConfig.jsGroupManage = "https://spa.kingdraw.com/go-pc-download/?from=kd-workbench&scence=kd-manage";
            NetConfig.slideCnUrl = "https://client.spa.kingdraw.com/nobot/awsc_zh.html";
            NetConfig.slideEnUrl = "https://client.spa.kingdraw.com/nobot/awsc_en.html";
            NetConfig.mqttUrl = "tcp://139.196.103.38:1883";
        }
        else if (AppConfig.RELEASE == Release.EARLY) {
            NetConfig.url = "http://newapitest.kingdraw.com";
            NetConfig.jsAccountUrl = "http://172.16.70.28:7000/account-new/";
            NetConfig.jsMessageUrl = "http://172.16.70.28:7000/message-new/";
            NetConfig.jsWorkUrl = "http://172.16.70.28:7000/work-new/";
            NetConfig.jsGroupCreate = "http://172.16.70.28:7000/register-group-mobile-new/";
            NetConfig.pediaUrl = "http://47.105.182.188:11111/Scripts/mobile/index.html";
            NetConfig.mqttUrl = "tcp://139.196.103.38:1883";
        }
        else if (AppConfig.RELEASE == Release.HOME) {
            NetConfig.url = "http://221.0.191.18:18000";
            NetConfig.jsAccountUrl = "http://221.0.191.18:7000/account/";
            NetConfig.jsMessageUrl = "http://221.0.191.18:7000/message/";
            NetConfig.jsWorkUrl = "http://221.0.191.18:7000/work/";
            NetConfig.jsGroupCreate = "http://221.0.191.18:7000/register-group-mobile/";
            NetConfig.pediaUrl = "http://47.105.182.188:11111/Scripts/mobile/index.html";
            NetConfig.mqttUrl = "tcp://221.0.191.18:1883";
        }
        else {
            NetConfig.url = "http://10.120.10.15:8000";
            NetConfig.jsAccountUrl = "http://172.16.70.28:7000/account/";
            NetConfig.jsMessageUrl = "http://172.16.70.28:7000/message/";
            NetConfig.jsWorkUrl = "http://172.16.70.28:7000/work/";
            NetConfig.jsGroupCreate = "http://172.16.70.28:7000/register-group-mobile/";
            NetConfig.pediaUrl = "http://47.105.182.188:11111/Scripts/mobile/index.html";
            NetConfig.mqttUrl = "tcp://172.16.70.28:1883";
        }
    }
    
    public static class Account
    {
        public static String getLoginUrl() {
            final StringBuilder sb = new StringBuilder();
            sb.append(NetConfig.url);
            sb.append("/api/User/UserLogin");
            return sb.toString();
        }
        
        public static String getLogoutUrl() {
            final StringBuilder sb = new StringBuilder();
            sb.append(NetConfig.url);
            sb.append("/api/User/Logout");
            return sb.toString();
        }
        
        public static String getMsgCode() {
            final StringBuilder sb = new StringBuilder();
            sb.append(NetConfig.url);
            sb.append("/api/Code/SendCode");
            return sb.toString();
        }
        
        public static String getNewMsgCode() {
            final StringBuilder sb = new StringBuilder();
            sb.append(NetConfig.url);
            sb.append("/api/Code/SendCodeNew");
            return sb.toString();
        }
        
        public static String getNextCode() {
            final StringBuilder sb = new StringBuilder();
            sb.append(NetConfig.url);
            sb.append("/api/Code/ResultCodeNext");
            return sb.toString();
        }
        
        public static String getPassword() {
            final StringBuilder sb = new StringBuilder();
            sb.append(NetConfig.url);
            sb.append("/api/User/ForgetPassword");
            return sb.toString();
        }
        
        public static String getRegisterUrl() {
            final StringBuilder sb = new StringBuilder();
            sb.append(NetConfig.url);
            sb.append("/api/User/NewRegister");
            return sb.toString();
        }
        
        public static String getUserInfo() {
            final StringBuilder sb = new StringBuilder();
            sb.append(NetConfig.url);
            sb.append("/api/User/GetUserInfo");
            return sb.toString();
        }
        
        public static String uploadHeadImg() {
            final StringBuilder sb = new StringBuilder();
            sb.append(NetConfig.url);
            sb.append("/api/Upload/UploadHeadImg");
            return sb.toString();
        }
        
        public static String userInfoUpdate() {
            final StringBuilder sb = new StringBuilder();
            sb.append(NetConfig.url);
            sb.append("/api/User/UserInfoUpdate");
            return sb.toString();
        }
    }
    
    public static class BaseData
    {
        public static String agreement() {
            final StringBuilder sb = new StringBuilder();
            sb.append(NetConfig.url);
            sb.append("/ClientService/WebContent/WebView");
            return sb.toString();
        }
        
        public static String appUpdate() {
            final StringBuilder sb = new StringBuilder();
            sb.append(NetConfig.url);
            sb.append("/api/Update/Index");
            return sb.toString();
        }
        
        public static String errLog() {
            final StringBuilder sb = new StringBuilder();
            sb.append(NetConfig.url);
            sb.append("/ClientService/ErrorLog/Index");
            return sb.toString();
        }
        
        public static String fileDownLoad() {
            final StringBuilder sb = new StringBuilder();
            sb.append(NetConfig.url);
            sb.append("/api/CloudFile/download");
            return sb.toString();
        }
        
        public static String getAiUpLoadImage() {
            final StringBuilder sb = new StringBuilder();
            sb.append(NetConfig.url);
            sb.append("/api/upload/UploadAppUnrecognisedImage");
            return sb.toString();
        }
        
        public static String getApplyData() {
            final StringBuilder sb = new StringBuilder();
            sb.append(NetConfig.url);
            sb.append("/api/Settings/GetApplyData");
            return sb.toString();
        }
        
        public static String getCheckVersionIsNotAvailable() {
            final StringBuilder sb = new StringBuilder();
            sb.append(NetConfig.url);
            sb.append("/api/Update/CheckVersionIsNotAvailable");
            return sb.toString();
        }
        
        public static String getCountryCode() {
            final StringBuilder sb = new StringBuilder();
            sb.append(NetConfig.url);
            sb.append("/api/Settings/GetCountryCode");
            return sb.toString();
        }
        
        public static String getCountryCodeByIp() {
            final StringBuilder sb = new StringBuilder();
            sb.append(NetConfig.url);
            sb.append("/api/User/GetCountryCodeByClientIP");
            return sb.toString();
        }
        
        public static String getLimitConfig() {
            final StringBuilder sb = new StringBuilder();
            sb.append(NetConfig.url);
            sb.append("/api/ApplyBackgroundSetting/GetApplySettingByUdid");
            return sb.toString();
        }
        
        public static String getRadicalsList() {
            final StringBuilder sb = new StringBuilder();
            sb.append(NetConfig.url);
            sb.append("/api/GSGroup/List");
            return sb.toString();
        }
        
        public static String getRecordAppStartUrl() {
            final StringBuilder sb = new StringBuilder();
            sb.append(NetConfig.url);
            sb.append("/api/Device/RecordAppStartWithConfig");
            return sb.toString();
        }
        
        public static String getUdid() {
            final StringBuilder sb = new StringBuilder();
            sb.append(NetConfig.url);
            sb.append("/api/UDID");
            return sb.toString();
        }
        
        public static String getVipLimitConfig() {
            final StringBuilder sb = new StringBuilder();
            sb.append(NetConfig.url);
            sb.append("/api/ApplyBackgroundSetting/GetApplySettingMember");
            return sb.toString();
        }
        
        public static String getWhiteList() {
            final StringBuilder sb = new StringBuilder();
            sb.append(NetConfig.url);
            sb.append("/api/Settings/GetWhiteList");
            return sb.toString();
        }
        
        public static String picDownLoad() {
            final StringBuilder sb = new StringBuilder();
            sb.append(NetConfig.url);
            sb.append("/api/Util/ViewFile");
            return sb.toString();
        }
        
        public static String share() {
            final StringBuilder sb = new StringBuilder();
            sb.append(NetConfig.url);
            sb.append("/api/UserShare/Share");
            return sb.toString();
        }
        
        public static String vipCenterCn() {
            return "https://usspa.kingdraw.com/vip/membershipPrivileges_App.html";
        }
        
        public static String vipCenterEn() {
            return "https://usspa.kingdraw.com/vip/membershipPrivileges_App_en.html";
        }
    }
    
    public static class Feedback
    {
        public static String getFeedbackList() {
            final StringBuilder sb = new StringBuilder();
            sb.append(NetConfig.url);
            sb.append("/ClientService/Feedback/FeedbackList");
            return sb.toString();
        }
        
        public static String getOptionDetailById() {
            final StringBuilder sb = new StringBuilder();
            sb.append(NetConfig.url);
            sb.append("/ClientService/Feedback/GetOptionDetailByID");
            return sb.toString();
        }
        
        public static String submitFeedback() {
            final StringBuilder sb = new StringBuilder();
            sb.append(NetConfig.url);
            sb.append("/ClientService/Feedback/Index");
            return sb.toString();
        }
    }
    
    public static class File
    {
        public static String deleteFileCloud() {
            final StringBuilder sb = new StringBuilder();
            sb.append(NetConfig.url);
            sb.append("/api/CloudFile/Delete");
            return sb.toString();
        }
        
        public static String deleteFileMulti() {
            final StringBuilder sb = new StringBuilder();
            sb.append(NetConfig.url);
            sb.append("/api/CloudFile/DeleteMulti");
            return sb.toString();
        }
        
        public static String fileReName() {
            final StringBuilder sb = new StringBuilder();
            sb.append(NetConfig.url);
            sb.append("/api/CloudFile/ReName");
            return sb.toString();
        }
        
        public static String getFileByName() {
            final StringBuilder sb = new StringBuilder();
            sb.append(NetConfig.url);
            sb.append("/api/CloudFile/GetOnlyByFileName");
            return sb.toString();
        }
        
        public static String getFileList() {
            final StringBuilder sb = new StringBuilder();
            sb.append(NetConfig.url);
            sb.append("/api/CloudFile/List");
            return sb.toString();
        }
        
        public static String getFileListWithout() {
            final StringBuilder sb = new StringBuilder();
            sb.append(NetConfig.url);
            sb.append("/api/CloudFile/GetFileWithoutCheckedWithTime");
            return sb.toString();
        }
        
        public static String upLoadFileAutoReName() {
            final StringBuilder sb = new StringBuilder();
            sb.append(NetConfig.url);
            sb.append("/api/CloudFile/AutoReNameAdd");
            return sb.toString();
        }
        
        public static String upLoadFileCloud() {
            final StringBuilder sb = new StringBuilder();
            sb.append(NetConfig.url);
            sb.append("/api/CloudFile/AddOrEdit");
            return sb.toString();
        }
    }
    
    public static class Group
    {
        public static String getUserGroupsList() {
            final StringBuilder sb = new StringBuilder();
            sb.append(NetConfig.url);
            sb.append("/api/User/GetUserGroupsList");
            return sb.toString();
        }
    }
    
    public static class Message
    {
        public static String getDialogMsgInfoById() {
            final StringBuilder sb = new StringBuilder();
            sb.append(NetConfig.url);
            sb.append("/api/Message/GetMessageNotificationInfoForPopWindow");
            return sb.toString();
        }
        
        public static String getDialogMsgList() {
            final StringBuilder sb = new StringBuilder();
            sb.append(NetConfig.url);
            sb.append("/api/Message/GetUnnormalSystemMessageList");
            return sb.toString();
        }
        
        public static String getMessageTypeAndMessageUnreadCount() {
            final StringBuilder sb = new StringBuilder();
            sb.append(NetConfig.url);
            sb.append("/api/Message/GetMessageTypeAndMessageUnreadCount");
            return sb.toString();
        }
    }
    
    public static class UserOrder
    {
        private static String payUrl = "https://dev.pay.kingdraw.com";
        
        public static String createOrder() {
            final StringBuilder sb = new StringBuilder();
            sb.append(UserOrder.payUrl);
            sb.append("/order/create");
            return sb.toString();
        }
        
        public static String createVipOrder() {
            final StringBuilder sb = new StringBuilder();
            sb.append(NetConfig.url);
            sb.append("/api/Pay/CreatedPayOrder");
            return sb.toString();
        }
        
        public static String getPayInfo() {
            final StringBuilder sb = new StringBuilder();
            sb.append(NetConfig.url);
            sb.append("/api/Settings/GetPayPackage");
            return sb.toString();
        }
        
        public static String googlePay() {
            final StringBuilder sb = new StringBuilder();
            sb.append(UserOrder.payUrl);
            sb.append("/pay/google");
            return sb.toString();
        }
        
        public static String orderByUser() {
            final StringBuilder sb = new StringBuilder();
            sb.append(UserOrder.payUrl);
            sb.append("/order/my");
            return sb.toString();
        }
        
        public static String orderCheck() {
            final StringBuilder sb = new StringBuilder();
            sb.append(NetConfig.url);
            sb.append("/api/Pay/PayCallBack");
            return sb.toString();
        }
        
        public static String orderInfo() {
            final StringBuilder sb = new StringBuilder();
            sb.append(UserOrder.payUrl);
            sb.append("/order/info");
            return sb.toString();
        }
        
        public static String sku() {
            final StringBuilder sb = new StringBuilder();
            sb.append(UserOrder.payUrl);
            sb.append("/vip/sku");
            return sb.toString();
        }
        
        public static String spu() {
            final StringBuilder sb = new StringBuilder();
            sb.append(UserOrder.payUrl);
            sb.append("/vip/spu");
            return sb.toString();
        }
        
        public static String spu_sku() {
            final StringBuilder sb = new StringBuilder();
            sb.append(UserOrder.payUrl);
            sb.append("/vip/spu-sku");
            return sb.toString();
        }
    }
}

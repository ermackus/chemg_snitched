package com.kingagroot.kingdraw.mqtt;

import android.os.IBinder;
import android.content.Intent;
import com.kingagroot.kingdraw.dialog.MsgMqttPop;
import com.goodsrc.ui.library.widget.AppManager;
import android.util.Log;
import android.content.Context;
import com.kingagroot.kingdraw.config.NetConfig;
import org.xutils.http.RequestParams;
import com.goodsrc.library.http.RequestCallBack;
import com.kingagroot.kingdraw.config.NetConfig$Message;
import org.xutils.http.HttpMethod;
import com.kingagroot.kingdraw.http.NewHttpManager;
import org.eclipse.paho.client.mqttv3.MqttException;
import com.kingagroot.kingdraw.base.MApplication;
import com.kingagroot.kingdraw.model.MsgDialogModel;
import com.kingagroot.kingdraw.http.HttpHeadUtils;
import com.kingagroot.kingdraw.config.Release;
import com.kingagroot.kingdraw.config.AppConfig;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.android.service.MqttAndroidClient;
import android.app.Service;

public class MsgMqttService extends Service
{
    public static final String TAG = "MsgMqttService";
    private static MqttAndroidClient mqttAndroidClient;
    public static String msgTopic;
    public static String passWord;
    public static String userName;
    public String clientId;
    private MqttConnectOptions connOpts;
    private final IMqttActionListener iMqttActionListener;
    private final MqttCallback mqttCallback;
    
    static {
        if (AppConfig.RELEASE == Release.DEBUG) {
            MsgMqttService.userName = "kingdraw";
            MsgMqttService.passWord = "kingdraw1234";
        }
        else {
            MsgMqttService.userName = "kingdraw";
            MsgMqttService.passWord = "ZdK0RG5dvAVln1WL";
        }
    }
    
    public MsgMqttService() {
        this.mqttCallback = (MqttCallback)new MsgMqttService$1(this);
        this.clientId = HttpHeadUtils.getUserDeviceId();
        this.iMqttActionListener = (IMqttActionListener)new MsgMqttService$2(this);
    }
    
    private MqttConnectOptions createConnectOptions(final String userName, final String s) {
        final MqttConnectOptions mqttConnectOptions = new MqttConnectOptions();
        mqttConnectOptions.setCleanSession(false);
        mqttConnectOptions.setUserName(userName);
        mqttConnectOptions.setPassword(s.toCharArray());
        mqttConnectOptions.setAutomaticReconnect(false);
        mqttConnectOptions.setConnectionTimeout(10);
        mqttConnectOptions.setKeepAliveInterval(20);
        return mqttConnectOptions;
    }
    
    private void doClientConnection() {
        if (!MsgMqttService.mqttAndroidClient.isConnected()) {
            try {
                MsgMqttService.mqttAndroidClient.connect(this.connOpts, (Object)MApplication.getInstance(), this.iMqttActionListener);
                return;
            }
            catch (final RuntimeException ex) {}
            catch (final MqttException ex2) {}
            final RuntimeException ex;
            ((Exception)ex).printStackTrace();
        }
    }
    
    private void getDialogList() {
        final NewHttpManager.Builder builder = new NewHttpManager.Builder();
        builder.setHttpMethod(HttpMethod.GET);
        final NewHttpManager build = builder.build();
        build.request(build.params(NetConfig$Message.getDialogMsgList()), (com.goodsrc.library.http.RequestCallBack<Object>)new MsgMqttService$3(this));
    }
    
    private void getMsgInfo(final String s) {
        final NewHttpManager.Builder builder = new NewHttpManager.Builder();
        builder.setHttpMethod(HttpMethod.GET);
        final NewHttpManager build = builder.build();
        final RequestParams params = build.params(NetConfig$Message.getDialogMsgInfoById());
        params.addBodyParameter("id", s);
        build.request(params, (com.goodsrc.library.http.RequestCallBack<Object>)new MsgMqttService$4(this));
    }
    
    private void init() {
        MsgMqttService.mqttAndroidClient = new MqttAndroidClient((Context)this, NetConfig.mqttUrl, this.clientId);
        Log.e("MsgMqttService", this.clientId);
        MsgMqttService.msgTopic = "specialmessage3";
        MsgMqttService.mqttAndroidClient.setCallback(this.mqttCallback);
        this.connOpts = this.createConnectOptions(MsgMqttService.userName, MsgMqttService.passWord);
        this.doClientConnection();
    }
    
    private void showDialogCenter(final MsgDialogModel msgDialogModel) {
        final MsgMqttPop msgMqttPop = new MsgMqttPop((Context)AppManager.getInstance().getLastActivity(), msgDialogModel);
        msgMqttPop.setMsgPopClickListener((MsgMqttPop.OnMsgPopClick)new MsgMqttService$5(this, msgMqttPop));
        msgMqttPop.show();
    }
    
    public static void startService(final Context context) {
        context.startService(new Intent(context, (Class)MsgMqttService.class));
    }
    
    public static void stopService(final Context context) {
        context.stopService(new Intent(context, (Class)MsgMqttService.class));
    }
    
    public IBinder onBind(final Intent intent) {
        return null;
    }
    
    public void onDestroy() {
        try {
            if (MsgMqttService.mqttAndroidClient != null && MsgMqttService.mqttAndroidClient.isConnected()) {
                MsgMqttService.mqttAndroidClient.unregisterResources();
                MsgMqttService.mqttAndroidClient.disconnect();
            }
        }
        catch (final MqttException ex) {
            ex.printStackTrace();
        }
        super.onDestroy();
    }
    
    public int onStartCommand(final Intent intent, final int n, final int n2) {
        this.init();
        return super.onStartCommand(intent, n, n2);
    }
}

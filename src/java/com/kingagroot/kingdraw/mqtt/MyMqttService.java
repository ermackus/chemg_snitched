package com.kingagroot.kingdraw.mqtt;

import android.os.IBinder;
import android.content.Intent;
import android.util.Log;
import android.content.Context;
import com.kingagroot.kingdraw.config.NetConfig;
import org.eclipse.paho.client.mqttv3.MqttException;
import com.kingagroot.kingdraw.base.MApplication;
import com.kingagroot.kingdraw.http.HttpHeadUtils;
import com.kingagroot.kingdraw.config.Release;
import com.kingagroot.kingdraw.config.AppConfig;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.android.service.MqttAndroidClient;
import android.app.Service;

public class MyMqttService extends Service
{
    public static String logoutTopic;
    private static MqttAndroidClient mqttAndroidClient;
    public static String passWord;
    public static String userName;
    public final String TAG;
    public String clientId;
    private MqttConnectOptions connOpts;
    private final IMqttActionListener iMqttActionListener;
    private final MqttCallback mqttCallback;
    
    static {
        if (AppConfig.RELEASE == Release.DEBUG) {
            MyMqttService.userName = "kingdraw";
            MyMqttService.passWord = "kingdraw1234";
        }
        else {
            MyMqttService.userName = "kingdraw";
            MyMqttService.passWord = "ZdK0RG5dvAVln1WL";
        }
    }
    
    public MyMqttService() {
        this.TAG = MyMqttService.class.getSimpleName();
        this.clientId = HttpHeadUtils.getUserDeviceId();
        this.mqttCallback = (MqttCallback)new MyMqttService$1(this);
        this.iMqttActionListener = (IMqttActionListener)new MyMqttService$2(this);
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
        if (!MyMqttService.mqttAndroidClient.isConnected()) {
            try {
                MyMqttService.mqttAndroidClient.connect(this.connOpts, (Object)MApplication.getInstance(), this.iMqttActionListener);
                return;
            }
            catch (final RuntimeException ex) {}
            catch (final MqttException ex2) {}
            final RuntimeException ex;
            ((Exception)ex).printStackTrace();
        }
    }
    
    private MqttMsgModel getMqttMessage() {
        final MqttMsgModel mqttMsgModel = new MqttMsgModel();
        mqttMsgModel.setType(0);
        final LoginDataModel loginDataModel = new LoginDataModel();
        loginDataModel.setClientId(HttpHeadUtils.getUserDeviceId());
        loginDataModel.setPlatform("APP");
        mqttMsgModel.setLoginDataModel(loginDataModel);
        return mqttMsgModel;
    }
    
    private void init() {
        MyMqttService.mqttAndroidClient = new MqttAndroidClient((Context)this, NetConfig.mqttUrl, this.clientId);
        Log.e(this.TAG, this.clientId);
        if (MApplication.getInstance().getAccountUserModel() != null) {
            MyMqttService.logoutTopic = MApplication.getInstance().getAccountUserModel().getUuid();
            MyMqttService.mqttAndroidClient.setCallback(this.mqttCallback);
            this.connOpts = this.createConnectOptions(MyMqttService.userName, MyMqttService.passWord);
            this.doClientConnection();
        }
    }
    
    public static void publish(final String s) {
        try {
            MyMqttService.mqttAndroidClient.publish(MyMqttService.logoutTopic, s.getBytes(), 0, false);
        }
        catch (final MqttException ex) {
            ex.printStackTrace();
        }
    }
    
    public static void startService(final Context context) {
        context.startService(new Intent(context, (Class)MyMqttService.class));
    }
    
    public static void stopService(final Context context) {
        context.stopService(new Intent(context, (Class)MyMqttService.class));
    }
    
    public IBinder onBind(final Intent intent) {
        return null;
    }
    
    public void onDestroy() {
        this.stopSelf();
        try {
            if (MyMqttService.mqttAndroidClient != null && MyMqttService.mqttAndroidClient.isConnected()) {
                MyMqttService.mqttAndroidClient.unregisterResources();
                MyMqttService.mqttAndroidClient.disconnect();
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

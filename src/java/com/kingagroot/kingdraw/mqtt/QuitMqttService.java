package com.kingagroot.kingdraw.mqtt;

import android.os.IBinder;
import androidx.appcompat.app.AlertDialog;
import java.util.List;
import android.graphics.Color;
import android.content.DialogInterface$OnClickListener;
import androidx.appcompat.app.AlertDialog$Builder;
import com.goodsrc.ui.library.widget.AppManager;
import com.kingagroot.kingdraw.interfaces.impl.GroupListDBIMpl;
import android.content.Intent;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.goodsrc.library.core.LibraryApplication;
import com.kingagroot.kingdraw.config.ShareData;
import com.goodsrc.library.utils.SPUtil;
import android.content.DialogInterface;
import com.kingagroot.kingdraw.model.GroupModel;
import com.kingagroot.kingdraw.interfaces.GroupListDBI;
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

public class QuitMqttService extends Service
{
    private static MqttAndroidClient mqttAndroidClient;
    public static String passWord;
    public static String quitTopic;
    public static String userName;
    private final String TAG;
    public String clientId;
    private MqttConnectOptions connOpts;
    private final IMqttActionListener iMqttActionListener;
    private final MqttCallback mqttCallback;
    
    static {
        if (AppConfig.RELEASE == Release.DEBUG) {
            QuitMqttService.userName = "kingdraw";
            QuitMqttService.passWord = "kingdraw1234";
        }
        else {
            QuitMqttService.userName = "kingdraw";
            QuitMqttService.passWord = "ZdK0RG5dvAVln1WL";
        }
    }
    
    public QuitMqttService() {
        this.TAG = QuitMqttService.class.getSimpleName();
        this.mqttCallback = (MqttCallback)new QuitMqttService$1(this);
        this.clientId = HttpHeadUtils.getUserDeviceId();
        this.iMqttActionListener = (IMqttActionListener)new QuitMqttService$2(this);
    }
    
    private MqttConnectOptions createConnectOptions(final String userName, final String s) {
        final MqttConnectOptions mqttConnectOptions = new MqttConnectOptions();
        mqttConnectOptions.setCleanSession(true);
        mqttConnectOptions.setUserName(userName);
        mqttConnectOptions.setPassword(s.toCharArray());
        mqttConnectOptions.setAutomaticReconnect(false);
        mqttConnectOptions.setConnectionTimeout(10);
        mqttConnectOptions.setKeepAliveInterval(20);
        return mqttConnectOptions;
    }
    
    private void doClientConnection() {
        if (!QuitMqttService.mqttAndroidClient.isConnected()) {
            try {
                QuitMqttService.mqttAndroidClient.connect(this.connOpts, (Object)MApplication.getInstance(), this.iMqttActionListener);
                return;
            }
            catch (final RuntimeException ex) {}
            catch (final MqttException ex2) {}
            final RuntimeException ex;
            ((Exception)ex).printStackTrace();
        }
    }
    
    private void init() {
        QuitMqttService.mqttAndroidClient = new MqttAndroidClient((Context)this, NetConfig.mqttUrl, this.clientId);
        Log.e(this.TAG, this.clientId);
        if (MApplication.getInstance().getAccountUserModel() != null) {
            QuitMqttService.quitTopic = "bussctrldata";
        }
        QuitMqttService.mqttAndroidClient.setCallback(this.mqttCallback);
        this.connOpts = this.createConnectOptions(QuitMqttService.userName, QuitMqttService.passWord);
        this.doClientConnection();
    }
    
    private void quitGroup(final QuitDataModel quitDataModel) {
        final GroupListDBIMpl groupListDBIMpl = new GroupListDBIMpl();
        final int groupDefault = ShareData.getGroupDefault();
        final List groupList = ((GroupListDBI)groupListDBIMpl).getGroupList();
        if (groupList != null && groupList.size() > 0) {
            final GroupModel defaultGroup = ((GroupListDBI)groupListDBIMpl).getDefaultGroup(groupDefault);
            if (defaultGroup != null) {
                final String companyName = defaultGroup.getCompanyName();
                if (quitDataModel.getGroupOpenID().equals((Object)defaultGroup.getGroupOpenID())) {
                    final AlertDialog$Builder alertDialog$Builder = new AlertDialog$Builder((Context)AppManager.getInstance().getLastActivity());
                    alertDialog$Builder.setTitle(2131821524).setMessage((CharSequence)String.format(this.getString(2131821290), new Object[] { companyName })).setPositiveButton(2131820731, (DialogInterface$OnClickListener)new _$$Lambda$QuitMqttService$KRWsBC7v7qxOrP6v5HwDPrMpm_I((GroupListDBI)groupListDBIMpl, defaultGroup));
                    final AlertDialog create = alertDialog$Builder.create();
                    create.setCanceledOnTouchOutside(false);
                    create.setCancelable(false);
                    create.show();
                    create.getButton(-1).setTextColor(Color.parseColor("#e13e3f"));
                }
                else {
                    ((GroupListDBI)groupListDBIMpl).deleteGroup(defaultGroup.getGroupOpenID());
                }
            }
        }
    }
    
    public static void startService(final Context context) {
        context.startService(new Intent(context, (Class)QuitMqttService.class));
    }
    
    public static void stopService(final Context context) {
        context.stopService(new Intent(context, (Class)QuitMqttService.class));
    }
    
    public boolean isAlreadyConnected() {
        final MqttAndroidClient mqttAndroidClient = QuitMqttService.mqttAndroidClient;
        if (mqttAndroidClient != null) {
            try {
                return mqttAndroidClient.isConnected();
            }
            catch (final Exception ex) {
                ex.printStackTrace();
            }
        }
        return false;
    }
    
    public IBinder onBind(final Intent intent) {
        return null;
    }
    
    public void onDestroy() {
        try {
            if (this.isAlreadyConnected()) {
                QuitMqttService.mqttAndroidClient.unregisterResources();
                QuitMqttService.mqttAndroidClient.disconnect();
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
    
    public class QuitDataModel
    {
        private String GroupOpenID;
        private String GroupUserID;
        private String UUID;
        final QuitMqttService this$0;
        
        public QuitDataModel(final QuitMqttService this$0) {
            this.this$0 = this$0;
        }
        
        public String getGroupOpenID() {
            return this.GroupOpenID;
        }
        
        public String getGroupUserID() {
            return this.GroupUserID;
        }
        
        public String getUUID() {
            return this.UUID;
        }
        
        public void setGroupOpenID(final String groupOpenID) {
            this.GroupOpenID = groupOpenID;
        }
        
        public void setGroupUserID(final String groupUserID) {
            this.GroupUserID = groupUserID;
        }
        
        public void setUUID(final String uuid) {
            this.UUID = uuid;
        }
    }
    
    public class QuitMqttModel
    {
        private QuitDataModel data;
        final QuitMqttService this$0;
        private int type;
        
        public QuitMqttModel(final QuitMqttService this$0) {
            this.this$0 = this$0;
        }
        
        public QuitDataModel getData() {
            return this.data;
        }
        
        public int getType() {
            return this.type;
        }
        
        public void setData(final QuitDataModel data) {
            this.data = data;
        }
        
        public void setType(final int type) {
            this.type = type;
        }
    }
}

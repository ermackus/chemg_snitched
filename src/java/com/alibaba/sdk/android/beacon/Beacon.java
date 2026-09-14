package com.alibaba.sdk.android.beacon;

import java.util.HashMap;
import android.util.Log;
import android.os.Looper;
import java.util.Iterator;
import android.os.Build$VERSION;
import android.os.Message;
import android.content.Context;
import java.util.ArrayList;
import android.os.Handler;
import java.util.Map;
import java.util.List;
import android.os.HandlerThread;

public final class Beacon
{
    private int a;
    private final HandlerThread a;
    private final b a;
    private final List<OnUpdateListener> a;
    private final List<OnServiceErrListener> b;
    private final String mAppKey;
    private final String mAppSecret;
    private final Map<String, String> mExtras;
    private Handler mHandler;
    private long mLoopInterval;
    
    private Beacon(final Builder builder) {
        this.a = (List<OnUpdateListener>)new ArrayList();
        this.b = (List<OnServiceErrListener>)new ArrayList();
        this.a = 255;
        this.mAppKey = builder.mAppKey;
        this.mAppSecret = builder.mAppSecret;
        this.mExtras = builder.mExtras;
        this.mLoopInterval = builder.mLoopInterval;
        this.a = new b(this);
        (this.a = new HandlerThread("Beacon Daemon")).start();
        this.a();
    }
    
    private void a() {
        this.mHandler = new BeaconHandler(this.a.getLooper());
    }
    
    private void a(final Context obj) {
        final Message obtain = Message.obtain();
        obtain.what = 1;
        obtain.obj = obj;
        this.mHandler.sendMessage(obtain);
    }
    
    private void a(final OnServiceErrListener onServiceErrListener) {
        this.b.add((Object)onServiceErrListener);
    }
    
    private void a(final OnUpdateListener onUpdateListener) {
        this.a.add((Object)onUpdateListener);
    }
    
    private void b() {
        if (Build$VERSION.SDK_INT >= 18) {
            this.mHandler.getLooper().quitSafely();
        }
        else {
            this.mHandler.getLooper().quit();
        }
        this.a();
    }
    
    private void b(final Context obj) {
        final Message obtain = Message.obtain();
        obtain.what = 2;
        obtain.obj = obj;
        this.mHandler.sendMessage(obtain);
    }
    
    private void b(final Error error) {
        final Iterator iterator = this.b.iterator();
        while (iterator.hasNext()) {
            ((OnServiceErrListener)iterator.next()).onErr(error);
        }
    }
    
    private void b(final OnUpdateListener onUpdateListener) {
        this.a.remove((Object)onUpdateListener);
    }
    
    private void c(final Context context) {
        this.b(context);
        this.a = 1;
    }
    
    private void d(final Context context) {
        this.a.a(context, this.mAppKey, this.mAppSecret, this.mExtras);
        final List<Config> a = this.a.a();
        final Iterator iterator = this.a.iterator();
        while (iterator.hasNext()) {
            ((OnUpdateListener)iterator.next()).onUpdate(a);
        }
    }
    
    private void e(final Context context) {
        if (this.mHandler.hasMessages(2)) {
            this.mHandler.removeMessages(2);
        }
        this.a(context);
        this.mHandler.sendEmptyMessageDelayed(2, this.mLoopInterval);
    }
    
    private boolean isStarted() {
        final int a = this.a;
        boolean b = true;
        if (a != 1) {
            b = false;
        }
        return b;
    }
    
    public static final void setPrepare(final boolean a) {
        a.a = a;
    }
    
    void a(final Error obj) {
        final Message obtain = Message.obtain();
        obtain.what = 7;
        obtain.obj = obj;
        this.mHandler.sendMessage(obtain);
    }
    
    public void addServiceErrListener(final OnServiceErrListener obj) {
        final Message obtain = Message.obtain();
        obtain.what = 6;
        obtain.obj = obj;
        this.mHandler.sendMessage(obtain);
    }
    
    public void addUpdateListener(final OnUpdateListener obj) {
        final Message obtain = Message.obtain();
        obtain.what = 4;
        obtain.obj = obj;
        this.mHandler.sendMessage(obtain);
    }
    
    public List<Config> getConfigs() {
        return this.a.a();
    }
    
    public void start(final Context obj) {
        if (!this.isStarted()) {
            final Message obtain = Message.obtain();
            obtain.what = 0;
            obtain.obj = obj;
            this.mHandler.sendMessage(obtain);
        }
    }
    
    public void stop() {
        if (this.isStarted()) {
            final Message obtain = Message.obtain();
            obtain.what = 3;
            this.mHandler.sendMessage(obtain);
        }
    }
    
    private final class BeaconHandler extends Handler
    {
        static final int MSG_ADD_ERR_LISTENER = 6;
        static final int MSG_ADD_UPDATE_LISTENER = 4;
        static final int MSG_ERR_CALLBACK = 7;
        static final int MSG_REMOVE_UPDATE_LISTENER = 5;
        static final int MSG_START = 0;
        static final int MSG_START_POLLING = 2;
        static final int MSG_STOP_POLLING = 3;
        static final int MSG_UPDATE = 1;
        final Beacon this$0;
        
        BeaconHandler(final Beacon this$0, final Looper looper) {
            this.this$0 = this$0;
            super(looper);
        }
        
        public void handleMessage(final Message message) {
            super.handleMessage(message);
            try {
                switch (message.what) {
                    case 7: {
                        this.this$0.b((Error)message.obj);
                        break;
                    }
                    case 6: {
                        this.this$0.a((OnServiceErrListener)message.obj);
                        break;
                    }
                    case 5: {
                        this.this$0.b((OnUpdateListener)message.obj);
                        break;
                    }
                    case 4: {
                        this.this$0.a((OnUpdateListener)message.obj);
                        break;
                    }
                    case 3: {
                        this.this$0.b();
                        break;
                    }
                    case 2: {
                        this.this$0.e((Context)message.obj);
                        break;
                    }
                    case 1: {
                        this.this$0.d((Context)message.obj);
                        break;
                    }
                    case 0: {
                        this.this$0.c((Context)message.obj);
                        break;
                    }
                }
            }
            catch (final Exception ex) {
                Log.i("beacon", ex.getMessage(), (Throwable)ex);
            }
        }
    }
    
    public static final class Builder
    {
        String mAppKey;
        String mAppSecret;
        Map<String, String> mExtras;
        long mLoopInterval;
        
        public Builder() {
            this.mExtras = (Map<String, String>)new HashMap();
            this.mLoopInterval = 300000L;
        }
        
        public Builder appKey(final String s) {
            this.mAppKey = s.trim();
            return this;
        }
        
        public Builder appSecret(final String s) {
            this.mAppSecret = s.trim();
            return this;
        }
        
        public Beacon build() {
            return new Beacon(this, null);
        }
        
        public Builder extras(final Map<String, String> map) {
            this.mExtras.putAll((Map)map);
            return this;
        }
        
        public Builder loopInterval(final long mLoopInterval) {
            if (mLoopInterval < 60000L) {
                this.mLoopInterval = 60000L;
            }
            else {
                this.mLoopInterval = mLoopInterval;
            }
            return this;
        }
    }
    
    public static final class Config
    {
        public final String key;
        public final String value;
        
        public Config(final String key, final String value) {
            this.key = key;
            this.value = value;
        }
    }
    
    public static final class Error
    {
        public final String errCode;
        public final String errMsg;
        
        Error(final String errCode, final String errMsg) {
            this.errCode = errCode;
            this.errMsg = errMsg;
        }
    }
    
    public interface OnServiceErrListener
    {
        void onErr(final Error p0);
    }
    
    public interface OnUpdateListener
    {
        void onUpdate(final List<Config> p0);
    }
}

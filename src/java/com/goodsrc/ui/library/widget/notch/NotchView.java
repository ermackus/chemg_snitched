package com.goodsrc.ui.library.widget.notch;

import android.content.Intent;
import android.content.Context;
import android.view.ViewGroup$LayoutParams;
import com.goodsrc.library.utils.L;
import android.view.ViewGroup$MarginLayoutParams;
import android.view.WindowManager;
import android.content.res.Configuration;
import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.os.Message;
import android.view.View;
import android.os.Handler;

public class NotchView
{
    private static final int MSG_ORIENTATION_CHANGE = 1200;
    int backgroundColor;
    private boolean isRegisterReceiver;
    private final Handler mHandler;
    private NotchGravity notchGravity;
    private int notchHeight;
    private OrientationChangedReceive orientationChangedReceive;
    private final View rootView;
    
    public NotchView(final View rootView) {
        this.backgroundColor = -16777216;
        this.notchHeight = 0;
        this.notchGravity = NotchGravity.none;
        this.isRegisterReceiver = false;
        this.mHandler = new Handler() {
            final NotchView this$0;
            
            public void handleMessage(final Message message) {
                super.handleMessage(message);
                if (message.what == 1200) {
                    this.this$0.onConfigurationChanged(this.this$0.rootView.getContext().getResources().getConfiguration());
                }
            }
        };
        this.rootView = rootView;
    }
    
    private void registerReceiver() {
        if (this.rootView != null) {
            if (!this.isRegisterReceiver) {
                this.orientationChangedReceive = new OrientationChangedReceive();
                final IntentFilter intentFilter = new IntentFilter();
                intentFilter.addAction("android.intent.action.CONFIGURATION_CHANGED");
                this.rootView.getContext().registerReceiver((BroadcastReceiver)this.orientationChangedReceive, intentFilter);
                this.isRegisterReceiver = true;
            }
        }
    }
    
    public void onConfigurationChanged(final Configuration configuration) {
        final View rootView = this.rootView;
        if (rootView != null) {
            if (this.notchHeight != 0) {
                final int rotation = ((WindowManager)rootView.getContext().getSystemService("window")).getDefaultDisplay().getRotation();
                final ViewGroup$MarginLayoutParams layoutParams = (ViewGroup$MarginLayoutParams)this.rootView.getLayoutParams();
                final int n = NotchView$2.$SwitchMap$com$goodsrc$ui$library$widget$notch$NotchView$NotchGravity[this.notchGravity.ordinal()];
                if (n != 1) {
                    if (n != 2) {
                        if (n != 3) {
                            if (n == 4) {
                                layoutParams.bottomMargin -= this.notchHeight;
                            }
                        }
                        else {
                            layoutParams.rightMargin -= this.notchHeight;
                        }
                    }
                    else {
                        layoutParams.topMargin -= this.notchHeight;
                    }
                }
                else {
                    layoutParams.leftMargin -= this.notchHeight;
                }
                final StringBuilder sb = new StringBuilder();
                sb.append(rotation);
                sb.append("");
                L.i(sb.toString());
                if (rotation != 0) {
                    if (rotation != 1) {
                        if (rotation != 2) {
                            if (rotation == 3) {
                                this.notchGravity = NotchGravity.right;
                                layoutParams.rightMargin += this.notchHeight;
                            }
                        }
                        else {
                            this.notchGravity = NotchGravity.bottom;
                            layoutParams.bottomMargin += this.notchHeight;
                        }
                    }
                    else {
                        this.notchGravity = NotchGravity.left;
                        layoutParams.leftMargin += this.notchHeight;
                    }
                }
                else {
                    this.notchGravity = NotchGravity.top;
                    layoutParams.topMargin += this.notchHeight;
                }
                this.rootView.setLayoutParams((ViewGroup$LayoutParams)layoutParams);
            }
        }
    }
    
    public void onDestroy() {
        this.mHandler.removeMessages(1200);
        this.unregisterReceiver();
    }
    
    public void onResume() {
        this.registerReceiver();
    }
    
    public void setNotchColor(final int backgroundColor) {
        final View rootView = this.rootView;
        if (rootView == null) {
            return;
        }
        final View view = (View)rootView.getParent();
        if (view == null) {
            return;
        }
        view.setBackgroundColor(backgroundColor);
    }
    
    public void setNotchHeight(final int notchHeight) {
        this.notchHeight = notchHeight;
    }
    
    public void unregisterReceiver() {
        final View rootView = this.rootView;
        if (rootView != null) {
            if (this.isRegisterReceiver) {
                if (this.orientationChangedReceive != null) {
                    rootView.getContext().unregisterReceiver((BroadcastReceiver)this.orientationChangedReceive);
                    this.isRegisterReceiver = false;
                }
            }
        }
    }
    
    private enum NotchGravity
    {
        private static final NotchGravity[] $VALUES;
        
        bottom, 
        left, 
        none, 
        right, 
        top;
    }
    
    private class OrientationChangedReceive extends BroadcastReceiver
    {
        final NotchView this$0;
        
        private OrientationChangedReceive(final NotchView this$0) {
            this.this$0 = this$0;
        }
        
        public void onReceive(final Context context, final Intent intent) {
            this.this$0.mHandler.removeMessages(1200);
            this.this$0.mHandler.sendEmptyMessage(1200);
        }
    }
}

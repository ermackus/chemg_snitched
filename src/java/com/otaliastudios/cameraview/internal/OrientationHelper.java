package com.otaliastudios.cameraview.internal;

import android.hardware.display.DisplayManager;
import android.view.WindowManager;
import android.os.Build$VERSION;
import android.os.Looper;
import android.os.Handler;
import android.hardware.display.DisplayManager$DisplayListener;
import android.view.OrientationEventListener;
import android.content.Context;

public class OrientationHelper
{
    private final Callback mCallback;
    private final Context mContext;
    private int mDeviceOrientation;
    final OrientationEventListener mDeviceOrientationListener;
    private int mDisplayOffset;
    final DisplayManager$DisplayListener mDisplayOffsetListener;
    private boolean mEnabled;
    private final Handler mHandler;
    
    public OrientationHelper(final Context mContext, final Callback mCallback) {
        this.mHandler = new Handler(Looper.getMainLooper());
        this.mDeviceOrientation = -1;
        this.mDisplayOffset = -1;
        this.mContext = mContext;
        this.mCallback = mCallback;
        this.mDeviceOrientationListener = new OrientationEventListener(this, mContext.getApplicationContext(), 3) {
            final OrientationHelper this$0;
            
            public void onOrientationChanged(final int n) {
                final int n2 = 0;
                int access$000;
                if (n == -1) {
                    access$000 = n2;
                    if (this.this$0.mDeviceOrientation != -1) {
                        access$000 = this.this$0.mDeviceOrientation;
                    }
                }
                else {
                    access$000 = n2;
                    if (n < 315) {
                        if (n < 45) {
                            access$000 = n2;
                        }
                        else if (n >= 45 && n < 135) {
                            access$000 = 90;
                        }
                        else if (n >= 135 && n < 225) {
                            access$000 = 180;
                        }
                        else {
                            access$000 = n2;
                            if (n >= 225) {
                                access$000 = n2;
                                if (n < 315) {
                                    access$000 = 270;
                                }
                            }
                        }
                    }
                }
                if (access$000 != this.this$0.mDeviceOrientation) {
                    this.this$0.mDeviceOrientation = access$000;
                    this.this$0.mCallback.onDeviceOrientationChanged(this.this$0.mDeviceOrientation);
                }
            }
        };
        if (Build$VERSION.SDK_INT >= 17) {
            this.mDisplayOffsetListener = (DisplayManager$DisplayListener)new DisplayManager$DisplayListener(this) {
                final OrientationHelper this$0;
                
                public void onDisplayAdded(final int n) {
                }
                
                public void onDisplayChanged(int access$200) {
                    access$200 = this.this$0.mDisplayOffset;
                    final int access$201 = this.this$0.findDisplayOffset();
                    if (access$201 != access$200) {
                        this.this$0.mDisplayOffset = access$201;
                        this.this$0.mCallback.onDisplayOffsetChanged();
                    }
                }
                
                public void onDisplayRemoved(final int n) {
                }
            };
        }
        else {
            this.mDisplayOffsetListener = null;
        }
    }
    
    private int findDisplayOffset() {
        final int rotation = ((WindowManager)this.mContext.getSystemService("window")).getDefaultDisplay().getRotation();
        if (rotation == 1) {
            return 90;
        }
        if (rotation == 2) {
            return 180;
        }
        if (rotation != 3) {
            return 0;
        }
        return 270;
    }
    
    public void disable() {
        if (!this.mEnabled) {
            return;
        }
        this.mEnabled = false;
        this.mDeviceOrientationListener.disable();
        if (Build$VERSION.SDK_INT >= 17) {
            ((DisplayManager)this.mContext.getSystemService("display")).unregisterDisplayListener(this.mDisplayOffsetListener);
        }
        this.mDisplayOffset = -1;
        this.mDeviceOrientation = -1;
    }
    
    public void enable() {
        if (this.mEnabled) {
            return;
        }
        this.mEnabled = true;
        this.mDisplayOffset = this.findDisplayOffset();
        if (Build$VERSION.SDK_INT >= 17) {
            ((DisplayManager)this.mContext.getSystemService("display")).registerDisplayListener(this.mDisplayOffsetListener, this.mHandler);
        }
        this.mDeviceOrientationListener.enable();
    }
    
    public int getLastDeviceOrientation() {
        return this.mDeviceOrientation;
    }
    
    public int getLastDisplayOffset() {
        return this.mDisplayOffset;
    }
    
    public interface Callback
    {
        void onDeviceOrientationChanged(final int p0);
        
        void onDisplayOffsetChanged();
    }
}

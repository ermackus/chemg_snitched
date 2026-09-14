package com.kingagroot.kingdraw.core.data;

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;

public class IUPACConverter
{
    static {
        System.loadLibrary("kingdrawCore-data");
    }
    
    private static native ConvertResult IUPACNameToJson(final String[] p0, final float p1, final float p2);
    
    public static Builer builder() {
        return new Builer();
    }
    
    private static native ConvertResult dynamicJsonToIUPACName(final String p0);
    
    private static native ConvertResult jsonToIUPACName(final String p0);
    
    public static class Builer
    {
        private float bondLength;
        private IUPACConverterListener listener;
        private float radian;
        
        public void DynamicKdJsonToIUPACName(final String s) {
            new Thread((Runnable)new Runnable(this, s) {
                final Builer this$0;
                final String val$kdjson;
                
                public void run() {
                    if (!TextUtils.isEmpty((CharSequence)this.val$kdjson)) {
                        new Handler(Looper.getMainLooper()).post((Runnable)new Runnable(this, dynamicJsonToIUPACName(this.val$kdjson)) {
                            final IUPACConverter$Builer$2 this$1;
                            final ConvertResult val$result;
                            
                            public void run() {
                                if (this.this$1.this$0.listener != null) {
                                    if (this.val$result.success) {
                                        this.this$1.this$0.listener.onSucess(this.val$result.result);
                                    }
                                    else {
                                        this.this$1.this$0.listener.onError(this.val$result.info);
                                    }
                                }
                            }
                        });
                    }
                    else {
                        new Handler(Looper.getMainLooper()).post((Runnable)new Runnable(this) {
                            final IUPACConverter$Builer$2 this$1;
                            
                            public void run() {
                                if (this.this$1.this$0.listener != null) {
                                    this.this$1.this$0.listener.onError("");
                                }
                            }
                        });
                    }
                }
            }).start();
        }
        
        public void IUPACNameToKdJson(final String[] array) {
            new Thread((Runnable)new Runnable(this, array) {
                final Builer this$0;
                final String[] val$name;
                
                public void run() {
                    new Handler(Looper.getMainLooper()).post((Runnable)new Runnable(this, IUPACNameToJson(this.val$name, this.this$0.bondLength, this.this$0.radian)) {
                        final IUPACConverter$Builer$3 this$1;
                        final ConvertResult val$result;
                        
                        public void run() {
                            if (this.this$1.this$0.listener != null) {
                                if (this.val$result.success) {
                                    this.this$1.this$0.listener.onSucess(this.val$result.result);
                                }
                                else {
                                    this.this$1.this$0.listener.onError(this.val$result.info);
                                }
                            }
                        }
                    });
                }
            }).start();
        }
        
        public void KdJsonToIUPACName(final String s) {
            new Thread((Runnable)new Runnable(this, s) {
                final Builer this$0;
                final String val$kdjson;
                
                public void run() {
                    if (!TextUtils.isEmpty((CharSequence)this.val$kdjson)) {
                        new Handler(Looper.getMainLooper()).post((Runnable)new Runnable(this, jsonToIUPACName(this.val$kdjson)) {
                            final IUPACConverter$Builer$1 this$1;
                            final ConvertResult val$result;
                            
                            public void run() {
                                if (this.this$1.this$0.listener != null) {
                                    if (this.val$result.success) {
                                        this.this$1.this$0.listener.onSucess(this.val$result.result);
                                    }
                                    else {
                                        this.this$1.this$0.listener.onError(this.val$result.info);
                                    }
                                }
                            }
                        });
                    }
                    else {
                        new Handler(Looper.getMainLooper()).post((Runnable)new Runnable(this) {
                            final IUPACConverter$Builer$1 this$1;
                            
                            public void run() {
                                if (this.this$1.this$0.listener != null) {
                                    this.this$1.this$0.listener.onError("");
                                }
                            }
                        });
                    }
                }
            }).start();
        }
        
        public Builer setBondLength(final float bondLength) {
            this.bondLength = bondLength;
            return this;
        }
        
        public Builer setConvertListener(final IUPACConverterListener listener) {
            this.listener = listener;
            return this;
        }
        
        public Builer setRadian(final float radian) {
            this.radian = radian;
            return this;
        }
    }
    
    public interface IUPACConverterListener
    {
        void onError(final String p0);
        
        void onSucess(final String p0);
    }
}

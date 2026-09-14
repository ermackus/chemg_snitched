package com.alipay.sdk.m.x;

import android.view.Window;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.text.TextUtils;
import android.widget.TextView;
import android.view.animation.Animation;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView$ScaleType;
import android.widget.LinearLayout$LayoutParams;
import android.widget.ImageView;
import android.graphics.drawable.GradientDrawable;
import android.view.ViewGroup$LayoutParams;
import android.widget.FrameLayout$LayoutParams;
import android.widget.LinearLayout;
import android.view.View;
import android.graphics.drawable.Drawable;
import android.content.res.Resources;
import android.app.AlertDialog;
import android.content.Context;
import com.alipay.sdk.m.u.e;
import android.os.Message;
import android.os.Looper;
import android.os.Handler;
import android.app.Activity;

public class a
{
    public static final String i = "\u6b63\u5728\u52a0\u8f7d";
    public static final String j = "\u53bb\u652f\u4ed8\u5b9d\u4ed8\u6b3e";
    public static final String k = "\u53bb\u652f\u4ed8\u5b9d\u6388\u6743";
    public static String l = "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";
    public d a;
    public Activity b;
    public String c;
    public long d;
    public final int e;
    public final long f;
    public boolean g;
    public Handler h;
    
    public a(final Activity b) {
        this.d = -1L;
        this.e = 1;
        this.f = 10000L;
        this.g = false;
        this.h = new Handler(this, Looper.getMainLooper()) {
            public final a a;
            
            public void dispatchMessage(final Message message) {
                this.a.a();
            }
        };
        this.b = b;
    }
    
    public a(final Activity b, final String c) {
        this.d = -1L;
        this.e = 1;
        this.f = 10000L;
        this.g = false;
        this.h = new Handler(this, Looper.getMainLooper()) {
            public final a a;
            
            public void dispatchMessage(final Message message) {
                this.a.a();
            }
        };
        this.b = b;
        this.c = c;
    }
    
    public static /* synthetic */ Activity a(final a a) {
        return a.b;
    }
    
    public static /* synthetic */ d a(final a a, final d a2) {
        return a.a = a2;
    }
    
    public static /* synthetic */ String b(final a a) {
        return a.c;
    }
    
    public static /* synthetic */ d c(final a a) {
        return a.a;
    }
    
    public static /* synthetic */ boolean d(final a a) {
        return a.g;
    }
    
    public static /* synthetic */ Handler e(final a a) {
        return a.h;
    }
    
    public void a() {
        final Activity b = this.b;
        if (b != null) {
            b.runOnUiThread((Runnable)new Runnable(this) {
                public final a a;
                
                public void run() {
                    if (com.alipay.sdk.m.x.a.c(this.a) != null && com.alipay.sdk.m.x.a.c(this.a).isShowing()) {
                        try {
                            com.alipay.sdk.m.x.a.e(this.a).removeMessages(1);
                            com.alipay.sdk.m.x.a.c(this.a).dismiss();
                        }
                        catch (final Exception ex) {
                            com.alipay.sdk.m.u.e.a((Throwable)ex);
                        }
                    }
                }
            });
        }
    }
    
    public void a(final String c) {
        this.c = c;
    }
    
    public void a(final boolean g) {
        this.g = g;
    }
    
    public void b() {
        this.b = null;
        this.a = null;
    }
    
    public String c() {
        return this.c;
    }
    
    public void d() {
        final Activity b = this.b;
        if (b != null) {
            b.runOnUiThread((Runnable)new Runnable(this) {
                public final a a;
                
                public void run() {
                    if (com.alipay.sdk.m.x.a.c(this.a) == null) {
                        final a a = this.a;
                        final a a2 = this.a;
                        com.alipay.sdk.m.x.a.a(a, a2.new d((Context)com.alipay.sdk.m.x.a.a(a2)));
                        com.alipay.sdk.m.x.a.c(this.a).setCancelable(com.alipay.sdk.m.x.a.d(this.a));
                    }
                    try {
                        if (!com.alipay.sdk.m.x.a.c(this.a).isShowing()) {
                            com.alipay.sdk.m.x.a.c(this.a).show();
                            com.alipay.sdk.m.x.a.e(this.a).sendEmptyMessageDelayed(1, 10000L);
                        }
                    }
                    catch (final Exception ex) {
                        com.alipay.sdk.m.u.e.a((Throwable)ex);
                    }
                }
            });
        }
    }
    
    public class d extends AlertDialog
    {
        public final a a;
        
        public d(final a a, final Context context) {
            this.a = a;
            super(context);
        }
        
        private int a(final Context context, final float n) {
            Resources resources;
            if (context == null) {
                resources = Resources.getSystem();
            }
            else {
                resources = context.getResources();
            }
            return (int)(n * resources.getDisplayMetrics().density);
        }
        
        private Drawable a(final Context p0, final String p1, final int p2) {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     1: astore          5
            //     3: new             Ljava/io/ByteArrayInputStream;
            //     6: astore          6
            //     8: aload           6
            //    10: aload_2        
            //    11: invokestatic    com/alipay/sdk/m/n/a.a:(Ljava/lang/String;)[B
            //    14: invokespecial   java/io/ByteArrayInputStream.<init>:([B)V
            //    17: new             Landroid/graphics/BitmapFactory$Options;
            //    20: astore_2       
            //    21: aload_2        
            //    22: invokespecial   android/graphics/BitmapFactory$Options.<init>:()V
            //    25: iload_3        
            //    26: istore          4
            //    28: iload_3        
            //    29: ifgt            37
            //    32: sipush          240
            //    35: istore          4
            //    37: aload_2        
            //    38: iload           4
            //    40: putfield        android/graphics/BitmapFactory$Options.inDensity:I
            //    43: aload_2        
            //    44: aload_1        
            //    45: invokevirtual   android/content/Context.getResources:()Landroid/content/res/Resources;
            //    48: invokevirtual   android/content/res/Resources.getDisplayMetrics:()Landroid/util/DisplayMetrics;
            //    51: getfield        android/util/DisplayMetrics.densityDpi:I
            //    54: putfield        android/graphics/BitmapFactory$Options.inTargetDensity:I
            //    57: aload           6
            //    59: aconst_null    
            //    60: aload_2        
            //    61: invokestatic    android/graphics/BitmapFactory.decodeStream:(Ljava/io/InputStream;Landroid/graphics/Rect;Landroid/graphics/BitmapFactory$Options;)Landroid/graphics/Bitmap;
            //    64: astore          7
            //    66: new             Landroid/graphics/drawable/BitmapDrawable;
            //    69: astore_2       
            //    70: aload_2        
            //    71: aload_1        
            //    72: invokevirtual   android/content/Context.getResources:()Landroid/content/res/Resources;
            //    75: aload           7
            //    77: invokespecial   android/graphics/drawable/BitmapDrawable.<init>:(Landroid/content/res/Resources;Landroid/graphics/Bitmap;)V
            //    80: aload           6
            //    82: invokevirtual   java/io/InputStream.close:()V
            //    85: goto            116
            //    88: astore_2       
            //    89: aload           6
            //    91: astore_1       
            //    92: goto            98
            //    95: astore_2       
            //    96: aconst_null    
            //    97: astore_1       
            //    98: aload_2        
            //    99: invokestatic    com/alipay/sdk/m/u/e.a:(Ljava/lang/Throwable;)V
            //   102: aload           5
            //   104: astore_2       
            //   105: aload_1        
            //   106: ifnull          116
            //   109: aload_1        
            //   110: invokevirtual   java/io/InputStream.close:()V
            //   113: aload           5
            //   115: astore_2       
            //   116: aload_2        
            //   117: areturn        
            //   118: astore_2       
            //   119: aload_1        
            //   120: ifnull          127
            //   123: aload_1        
            //   124: invokevirtual   java/io/InputStream.close:()V
            //   127: aload_2        
            //   128: athrow         
            //   129: astore_1       
            //   130: goto            85
            //   133: astore_1       
            //   134: aload           5
            //   136: astore_2       
            //   137: goto            116
            //   140: astore_1       
            //   141: goto            127
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type                 
            //  -----  -----  -----  -----  ---------------------
            //  3      17     95     98     Any
            //  17     25     88     95     Any
            //  37     80     88     95     Any
            //  80     85     129    133    Ljava/lang/Exception;
            //  98     102    118    129    Any
            //  109    113    133    140    Ljava/lang/Exception;
            //  123    127    140    144    Ljava/lang/Exception;
            // 
            // The error that occurred was:
            // 
            // java.lang.IndexOutOfBoundsException: Index: 76, Size: 76
            //     at java.util.ArrayList.get(ArrayList.java:437)
            //     at q5.g.d(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:31)
            //     at q5.g.b(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:2125)
            //     at u5.m.d(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:21)
            //     at u5.i.g(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:23)
            //     at u5.i.f(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:159)
            //     at u5.i.j(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:619)
            //     at u5.i.k(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:13)
            //     at u5.i.j(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:799)
            //     at u5.i.k(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:13)
            //     at u5.i.i(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:29)
            //     at s5.b.a(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:90)
            //     at com.thesourceofcode.jadec.decompilers.JavaExtractionWorker.decompileWithProcyon(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:367)
            //     at com.thesourceofcode.jadec.decompilers.JavaExtractionWorker.doWork(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:162)
            //     at com.thesourceofcode.jadec.decompilers.BaseDecompiler.withAttempt(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:3)
            //     at z6.a.run(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:31)
            //     at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1167)
            //     at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:641)
            //     at java.lang.Thread.run(Thread.java:920)
            // 
            throw new IllegalStateException("An error occurred while decompiling this method.");
        }
        
        private View a(final Context context) {
            final LinearLayout linearLayout = new LinearLayout(context);
            final FrameLayout$LayoutParams layoutParams = new FrameLayout$LayoutParams(-2, this.a(context, 50.0f));
            layoutParams.gravity = 17;
            linearLayout.setOrientation(0);
            linearLayout.setLayoutParams((ViewGroup$LayoutParams)layoutParams);
            final GradientDrawable backgroundDrawable = new GradientDrawable();
            backgroundDrawable.setColor(-450944201);
            backgroundDrawable.setCornerRadius((float)this.a(context, 5.0f));
            linearLayout.setBackgroundDrawable((Drawable)backgroundDrawable);
            final ImageView imageView = new ImageView(context);
            final LinearLayout$LayoutParams layoutParams2 = new LinearLayout$LayoutParams(this.a(context, 20.0f), this.a(context, 20.0f));
            layoutParams2.gravity = 16;
            layoutParams2.setMargins(this.a((Context)com.alipay.sdk.m.x.a.a(this.a), 17.0f), this.a((Context)com.alipay.sdk.m.x.a.a(this.a), 10.0f), this.a((Context)com.alipay.sdk.m.x.a.a(this.a), 8.0f), this.a((Context)com.alipay.sdk.m.x.a.a(this.a), 10.0f));
            imageView.setLayoutParams((ViewGroup$LayoutParams)layoutParams2);
            imageView.setScaleType(ImageView$ScaleType.FIT_CENTER);
            imageView.setImageDrawable(this.a(context, com.alipay.sdk.m.x.a.l, 480));
            final RotateAnimation rotateAnimation = new RotateAnimation(0.0f, 359.0f, 1, 0.5f, 1, 0.5f);
            ((Animation)rotateAnimation).setRepeatCount(-1);
            ((Animation)rotateAnimation).setDuration(900L);
            ((Animation)rotateAnimation).setInterpolator((Interpolator)new LinearInterpolator());
            imageView.startAnimation((Animation)rotateAnimation);
            final TextView textView = new TextView(context);
            String b;
            if (TextUtils.isEmpty((CharSequence)com.alipay.sdk.m.x.a.b(this.a))) {
                b = "\u6b63\u5728\u52a0\u8f7d";
            }
            else {
                b = com.alipay.sdk.m.x.a.b(this.a);
            }
            textView.setText((CharSequence)b);
            textView.setTextSize(16.0f);
            textView.setTextColor(-1);
            final LinearLayout$LayoutParams layoutParams3 = new LinearLayout$LayoutParams(-2, -2);
            layoutParams3.gravity = 16;
            layoutParams3.setMargins(0, 0, this.a(context, 17.0f), 0);
            textView.setLayoutParams((ViewGroup$LayoutParams)layoutParams3);
            linearLayout.addView((View)imageView);
            linearLayout.addView((View)textView);
            final FrameLayout frameLayout = new FrameLayout(context);
            frameLayout.setLayoutParams((ViewGroup$LayoutParams)new FrameLayout$LayoutParams(-2, -2, 17));
            frameLayout.addView((View)linearLayout);
            return (View)frameLayout;
        }
        
        public void onCreate(final Bundle bundle) {
            super.onCreate(bundle);
            this.setContentView(this.a(this.getContext()));
            final Window window = this.getWindow();
            if (window != null) {
                window.setBackgroundDrawable((Drawable)new ColorDrawable(0));
            }
        }
    }
}

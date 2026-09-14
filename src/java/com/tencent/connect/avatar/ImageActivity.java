package com.tencent.connect.avatar;

import android.graphics.drawable.StateListDrawable;
import android.os.Bundle;
import com.tencent.connect.UserInfo;
import android.graphics.Matrix;
import android.widget.Toast;
import android.view.ViewTreeObserver$OnGlobalLayoutListener;
import com.tencent.open.utils.k;
import java.util.Map;
import com.tencent.open.b.h;
import java.util.HashMap;
import android.content.Intent;
import android.text.TextUtils$TruncateAt;
import android.widget.LinearLayout$LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout$LayoutParams;
import android.widget.ImageView$ScaleType;
import android.content.Context;
import android.view.ViewGroup$LayoutParams;
import android.graphics.drawable.Drawable;
import java.io.IOException;
import java.io.InputStream;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.graphics.BitmapFactory$Options;
import android.graphics.Color;
import android.view.View;
import com.tencent.tauth.IUiListener;
import android.view.View$OnClickListener;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Button;
import android.os.Handler;
import com.tencent.connect.auth.QQToken;
import android.widget.RelativeLayout;
import android.app.Activity;

public class ImageActivity extends Activity
{
    RelativeLayout a;
    private QQToken b;
    private String c;
    private Handler d;
    private c e;
    private Button f;
    private Button g;
    private b h;
    private TextView i;
    private ProgressBar j;
    private int k;
    private boolean l;
    private long m;
    private int n;
    private final int o;
    private final int p;
    private Rect q;
    private String r;
    private Bitmap s;
    private final View$OnClickListener t;
    private final View$OnClickListener u;
    private final IUiListener v;
    private final IUiListener w;
    
    public ImageActivity() {
        this.k = 0;
        this.l = false;
        this.m = 0L;
        this.n = 0;
        this.o = 640;
        this.p = 640;
        this.q = new Rect();
        this.t = (View$OnClickListener)new View$OnClickListener() {
            final ImageActivity a;
            
            public void onClick(final View view) {
                this.a.j.setVisibility(0);
                this.a.g.setEnabled(false);
                this.a.g.setTextColor(Color.rgb(21, 21, 21));
                this.a.f.setEnabled(false);
                this.a.f.setTextColor(Color.rgb(36, 94, 134));
                new Thread((Runnable)new Runnable(this) {
                    final ImageActivity$2 a;
                    
                    public void run() {
                        this.a.a.c();
                    }
                }).start();
                if (this.a.l) {
                    this.a.a("10657", 0L);
                }
                else {
                    this.a.a("10655", System.currentTimeMillis() - this.a.m);
                    if (this.a.e.b) {
                        this.a.a("10654", 0L);
                    }
                }
            }
        };
        this.u = (View$OnClickListener)new View$OnClickListener() {
            final ImageActivity a;
            
            public void onClick(final View view) {
                this.a.a("10656", System.currentTimeMillis() - this.a.m);
                this.a.setResult(0);
                this.a.d();
            }
        };
        this.v = (IUiListener)new ImageActivity$5(this);
        this.w = (IUiListener)new ImageActivity$6(this);
    }
    
    private Bitmap a(String decodeStream) throws IOException {
        final BitmapFactory$Options bitmapFactory$Options = new BitmapFactory$Options();
        int inSampleSize = 1;
        bitmapFactory$Options.inJustDecodeBounds = true;
        final Uri parse = Uri.parse(decodeStream);
        final InputStream openInputStream = this.getContentResolver().openInputStream(parse);
        decodeStream = null;
        if (openInputStream == null) {
            return null;
        }
        try {
            BitmapFactory.decodeStream(openInputStream, (Rect)null, bitmapFactory$Options);
        }
        catch (final OutOfMemoryError outOfMemoryError) {
            outOfMemoryError.printStackTrace();
        }
        openInputStream.close();
        for (int outWidth = bitmapFactory$Options.outWidth, outHeight = bitmapFactory$Options.outHeight; outWidth * outHeight > 4194304; outWidth /= 2, outHeight /= 2, inSampleSize *= 2) {}
        bitmapFactory$Options.inJustDecodeBounds = false;
        bitmapFactory$Options.inSampleSize = inSampleSize;
        final InputStream openInputStream2 = this.getContentResolver().openInputStream(parse);
        try {
            decodeStream = (String)BitmapFactory.decodeStream(openInputStream2, (Rect)null, bitmapFactory$Options);
        }
        catch (final OutOfMemoryError outOfMemoryError2) {
            outOfMemoryError2.printStackTrace();
        }
        return (Bitmap)decodeStream;
    }
    
    private View a() {
        final ViewGroup$LayoutParams layoutParams = new ViewGroup$LayoutParams(-1, -1);
        final ViewGroup$LayoutParams layoutParams2 = new ViewGroup$LayoutParams(-1, -1);
        final ViewGroup$LayoutParams layoutParams3 = new ViewGroup$LayoutParams(-2, -2);
        (this.a = new RelativeLayout((Context)this)).setLayoutParams(layoutParams);
        this.a.setBackgroundColor(-16777216);
        final RelativeLayout relativeLayout = new RelativeLayout((Context)this);
        relativeLayout.setLayoutParams(layoutParams3);
        this.a.addView((View)relativeLayout);
        (this.e = new c((Context)this)).setLayoutParams(layoutParams2);
        this.e.setScaleType(ImageView$ScaleType.MATRIX);
        relativeLayout.addView((View)this.e);
        this.h = new b((Context)this);
        final RelativeLayout$LayoutParams layoutParams4 = new RelativeLayout$LayoutParams(layoutParams2);
        layoutParams4.addRule(14, -1);
        layoutParams4.addRule(15, -1);
        this.h.setLayoutParams((ViewGroup$LayoutParams)layoutParams4);
        relativeLayout.addView((View)this.h);
        final LinearLayout linearLayout = new LinearLayout((Context)this);
        final RelativeLayout$LayoutParams layoutParams5 = new RelativeLayout$LayoutParams(-2, com.tencent.connect.avatar.a.a((Context)this, 80.0f));
        layoutParams5.addRule(14, -1);
        linearLayout.setLayoutParams((ViewGroup$LayoutParams)layoutParams5);
        linearLayout.setOrientation(0);
        linearLayout.setGravity(17);
        this.a.addView((View)linearLayout);
        final ImageView imageView = new ImageView((Context)this);
        imageView.setLayoutParams((ViewGroup$LayoutParams)new LinearLayout$LayoutParams(com.tencent.connect.avatar.a.a((Context)this, 24.0f), com.tencent.connect.avatar.a.a((Context)this, 24.0f)));
        imageView.setImageDrawable(this.b("com.tencent.plus.logo.png"));
        linearLayout.addView((View)imageView);
        this.i = new TextView((Context)this);
        final LinearLayout$LayoutParams layoutParams6 = new LinearLayout$LayoutParams(layoutParams3);
        layoutParams6.leftMargin = com.tencent.connect.avatar.a.a((Context)this, 7.0f);
        this.i.setLayoutParams((ViewGroup$LayoutParams)layoutParams6);
        this.i.setEllipsize(TextUtils$TruncateAt.END);
        this.i.setSingleLine();
        this.i.setTextColor(-1);
        this.i.setTextSize(24.0f);
        this.i.setVisibility(8);
        linearLayout.addView((View)this.i);
        final RelativeLayout relativeLayout2 = new RelativeLayout((Context)this);
        final RelativeLayout$LayoutParams layoutParams7 = new RelativeLayout$LayoutParams(-1, com.tencent.connect.avatar.a.a((Context)this, 60.0f));
        layoutParams7.addRule(12, -1);
        layoutParams7.addRule(9, -1);
        relativeLayout2.setLayoutParams((ViewGroup$LayoutParams)layoutParams7);
        relativeLayout2.setBackgroundDrawable(this.b("com.tencent.plus.bar.png"));
        final int a = com.tencent.connect.avatar.a.a((Context)this, 10.0f);
        relativeLayout2.setPadding(a, a, a, 0);
        this.a.addView((View)relativeLayout2);
        final a a2 = new a((Context)this);
        final int a3 = com.tencent.connect.avatar.a.a((Context)this, 14.0f);
        final int a4 = com.tencent.connect.avatar.a.a((Context)this, 7.0f);
        (this.g = new Button((Context)this)).setLayoutParams((ViewGroup$LayoutParams)new RelativeLayout$LayoutParams(com.tencent.connect.avatar.a.a((Context)this, 78.0f), com.tencent.connect.avatar.a.a((Context)this, 45.0f)));
        this.g.setText((CharSequence)"\u53d6\u6d88");
        this.g.setTextColor(-1);
        this.g.setTextSize(18.0f);
        this.g.setPadding(a3, a4, a3, a4);
        a2.b(this.g);
        relativeLayout2.addView((View)this.g);
        this.f = new Button((Context)this);
        final RelativeLayout$LayoutParams layoutParams8 = new RelativeLayout$LayoutParams(com.tencent.connect.avatar.a.a((Context)this, 78.0f), com.tencent.connect.avatar.a.a((Context)this, 45.0f));
        layoutParams8.addRule(11, -1);
        this.f.setLayoutParams((ViewGroup$LayoutParams)layoutParams8);
        this.f.setTextColor(-1);
        this.f.setTextSize(18.0f);
        this.f.setPadding(a3, a4, a3, a4);
        this.f.setText((CharSequence)"\u9009\u53d6");
        a2.a(this.f);
        relativeLayout2.addView((View)this.f);
        final TextView textView = new TextView((Context)this);
        final RelativeLayout$LayoutParams layoutParams9 = new RelativeLayout$LayoutParams(layoutParams3);
        layoutParams9.addRule(13, -1);
        textView.setLayoutParams((ViewGroup$LayoutParams)layoutParams9);
        textView.setText((CharSequence)"\u79fb\u52a8\u548c\u7f29\u653e");
        textView.setPadding(0, com.tencent.connect.avatar.a.a((Context)this, 3.0f), 0, 0);
        textView.setTextSize(18.0f);
        textView.setTextColor(-1);
        relativeLayout2.addView((View)textView);
        this.j = new ProgressBar((Context)this);
        final RelativeLayout$LayoutParams layoutParams10 = new RelativeLayout$LayoutParams(layoutParams3);
        layoutParams10.addRule(14, -1);
        layoutParams10.addRule(15, -1);
        this.j.setLayoutParams((ViewGroup$LayoutParams)layoutParams10);
        this.j.setVisibility(8);
        this.a.addView((View)this.j);
        return (View)this.a;
    }
    
    private void a(final int n, final String s, final String s2, final String s3) {
        final Intent intent = new Intent();
        intent.putExtra("key_error_code", n);
        intent.putExtra("key_error_msg", s2);
        intent.putExtra("key_error_detail", s3);
        intent.putExtra("key_response", s);
        this.setResult(-1, intent);
    }
    
    private void a(final Bitmap bitmap) {
        new ImageActivity.ImageActivity$QQAvatarImp(this, this.b).setAvator(bitmap, this.v);
    }
    
    private void a(final String s, final int n) {
        this.d.post((Runnable)new Runnable(this, s, n) {
            final String a;
            final int b;
            final ImageActivity c;
            
            public void run() {
                this.c.b(this.a, this.b);
            }
        });
    }
    
    public static void a(final String s, final long n, final String s2) {
        final HashMap hashMap = new HashMap();
        ((Map)hashMap).put((Object)"strValue", (Object)s2);
        ((Map)hashMap).put((Object)"nValue", (Object)s);
        ((Map)hashMap).put((Object)"qver", (Object)"3.5.4.lite");
        if (n != 0L) {
            ((Map)hashMap).put((Object)"elt", (Object)String.valueOf(n));
        }
        h.a().a("https://cgi.qplus.com/report/report", (Map)hashMap);
    }
    
    private Drawable b(final String s) {
        return com.tencent.open.utils.k.a(s, (Context)this);
    }
    
    private void b() {
        try {
            final Bitmap a = this.a(this.r);
            this.s = a;
            if (a == null) {
                final StringBuilder sb = new StringBuilder();
                sb.append("cannot read picture: '");
                sb.append(this.r);
                sb.append("'!");
                throw new IOException(sb.toString());
            }
            this.e.setImageBitmap(a);
        }
        catch (final IOException ex) {
            ex.printStackTrace();
            this.a("\u56fe\u7247\u8bfb\u53d6\u5931\u8d25\uff0c\u8bf7\u68c0\u67e5\u8be5\u56fe\u7247\u662f\u5426\u6709\u6548", 1);
            this.a(-5, null, "\u56fe\u7247\u8bfb\u53d6\u5931\u8d25\uff0c\u8bf7\u68c0\u67e5\u8be5\u56fe\u7247\u662f\u5426\u6709\u6548", ex.getMessage());
            this.d();
        }
        this.f.setOnClickListener(this.t);
        this.g.setOnClickListener(this.u);
        this.a.getViewTreeObserver().addOnGlobalLayoutListener((ViewTreeObserver$OnGlobalLayoutListener)new ViewTreeObserver$OnGlobalLayoutListener(this) {
            final ImageActivity a;
            
            public void onGlobalLayout() {
                this.a.a.getViewTreeObserver().removeGlobalOnLayoutListener((ViewTreeObserver$OnGlobalLayoutListener)this);
                final ImageActivity a = this.a;
                a.q = a.h.a();
                this.a.e.a(this.a.q);
            }
        });
    }
    
    private void b(final String s, final int n) {
        final Toast text = Toast.makeText((Context)this, (CharSequence)s, 1);
        final LinearLayout view = (LinearLayout)text.getView();
        ((TextView)view.getChildAt(0)).setPadding(8, 0, 0, 0);
        final ImageView imageView = new ImageView((Context)this);
        imageView.setLayoutParams((ViewGroup$LayoutParams)new LinearLayout$LayoutParams(com.tencent.connect.avatar.a.a((Context)this, 16.0f), com.tencent.connect.avatar.a.a((Context)this, 16.0f)));
        if (n == 0) {
            imageView.setImageDrawable(this.b("com.tencent.plus.ic_success.png"));
        }
        else {
            imageView.setImageDrawable(this.b("com.tencent.plus.ic_error.png"));
        }
        view.addView((View)imageView, 0);
        view.setOrientation(0);
        view.setGravity(17);
        text.setView((View)view);
        text.setGravity(17, 0, 0);
        text.show();
    }
    
    private void c() {
        final float n = (float)this.q.width();
        final Matrix imageMatrix = this.e.getImageMatrix();
        final float[] array = new float[9];
        imageMatrix.getValues(array);
        final float n2 = array[2];
        final float n3 = array[5];
        final float n4 = array[0];
        final float n5 = 640.0f / n;
        int n6 = (int)((this.q.left - n2) / n4);
        if (n6 < 0) {
            n6 = 0;
        }
        int n7 = (int)((this.q.top - n3) / n4);
        if (n7 < 0) {
            n7 = 0;
        }
        final Matrix matrix = new Matrix();
        matrix.set(imageMatrix);
        matrix.postScale(n5, n5);
        final int n8 = (int)(650.0f / n4);
        final int min = Math.min(this.s.getWidth() - n6, n8);
        final int min2 = Math.min(this.s.getHeight() - n7, n8);
        try {
            final Bitmap bitmap = Bitmap.createBitmap(this.s, n6, n7, min, min2, matrix, true);
            final Bitmap bitmap2 = Bitmap.createBitmap(bitmap, 0, 0, 640, 640);
            bitmap.recycle();
            this.a(bitmap2);
        }
        catch (final IllegalArgumentException ex) {
            ex.printStackTrace();
            this.a("\u56fe\u7247\u8bfb\u53d6\u5931\u8d25\uff0c\u8bf7\u68c0\u67e5\u8be5\u56fe\u7247\u662f\u5426\u6709\u6548", 1);
            this.a(-5, null, "\u56fe\u7247\u8bfb\u53d6\u5931\u8d25\uff0c\u8bf7\u68c0\u67e5\u8be5\u56fe\u7247\u662f\u5426\u6709\u6548", ex.getMessage());
            this.d();
        }
    }
    
    private void c(String d) {
        d = this.d(d);
        if (!"".equals((Object)d)) {
            this.i.setText((CharSequence)d);
            this.i.setVisibility(0);
        }
    }
    
    private String d(final String s) {
        return s.replaceAll("&gt;", ">").replaceAll("&lt;", "<").replaceAll("&quot;", "\"").replaceAll("&#39;", "'").replaceAll("&amp;", "&");
    }
    
    private void d() {
        this.finish();
        final int n = this.n;
        if (n != 0) {
            this.overridePendingTransition(0, n);
        }
    }
    
    private void e() {
        ++this.k;
        new UserInfo((Context)this, this.b).getUserInfo(this.w);
    }
    
    public void a(final String s, final long n) {
        a(s, n, this.b.getAppId());
    }
    
    public void onBackPressed() {
        this.setResult(0);
        this.d();
    }
    
    public void onCreate(final Bundle bundle) {
        this.requestWindowFeature(1);
        super.onCreate(bundle);
        this.setRequestedOrientation(1);
        this.setContentView(this.a());
        this.d = new Handler();
        final Bundle bundleExtra = this.getIntent().getBundleExtra("key_params");
        this.r = bundleExtra.getString("picture");
        this.c = bundleExtra.getString("return_activity");
        final String string = bundleExtra.getString("appid");
        final String string2 = bundleExtra.getString("access_token");
        final long long1 = bundleExtra.getLong("expires_in");
        final String string3 = bundleExtra.getString("openid");
        this.n = bundleExtra.getInt("exitAnim");
        final QQToken b = new QQToken(string);
        this.b = b;
        final StringBuilder sb = new StringBuilder();
        sb.append((long1 - System.currentTimeMillis()) / 1000L);
        sb.append("");
        b.setAccessToken(string2, sb.toString());
        this.b.setOpenId(string3);
        this.b();
        this.e();
        this.m = System.currentTimeMillis();
        this.a("10653", 0L);
    }
    
    protected void onDestroy() {
        super.onDestroy();
        this.e.setImageBitmap(null);
        final Bitmap s = this.s;
        if (s != null && !s.isRecycled()) {
            this.s.recycle();
        }
    }
    
    class a extends View
    {
        final ImageActivity a;
        
        public a(final ImageActivity a, final Context context) {
            this.a = a;
            super(context);
        }
        
        public void a(final Button button) {
            final StateListDrawable backgroundDrawable = new StateListDrawable();
            final Drawable a = this.a.b("com.tencent.plus.blue_normal.png");
            final Drawable a2 = this.a.b("com.tencent.plus.blue_down.png");
            final Drawable a3 = this.a.b("com.tencent.plus.blue_disable.png");
            backgroundDrawable.addState(View.PRESSED_ENABLED_STATE_SET, a2);
            backgroundDrawable.addState(View.ENABLED_FOCUSED_STATE_SET, a);
            backgroundDrawable.addState(View.ENABLED_STATE_SET, a);
            backgroundDrawable.addState(View.FOCUSED_STATE_SET, a);
            backgroundDrawable.addState(View.EMPTY_STATE_SET, a3);
            button.setBackgroundDrawable((Drawable)backgroundDrawable);
        }
        
        public void b(final Button button) {
            final StateListDrawable backgroundDrawable = new StateListDrawable();
            final Drawable a = this.a.b("com.tencent.plus.gray_normal.png");
            final Drawable a2 = this.a.b("com.tencent.plus.gray_down.png");
            final Drawable a3 = this.a.b("com.tencent.plus.gray_disable.png");
            backgroundDrawable.addState(View.PRESSED_ENABLED_STATE_SET, a2);
            backgroundDrawable.addState(View.ENABLED_FOCUSED_STATE_SET, a);
            backgroundDrawable.addState(View.ENABLED_STATE_SET, a);
            backgroundDrawable.addState(View.FOCUSED_STATE_SET, a);
            backgroundDrawable.addState(View.EMPTY_STATE_SET, a3);
            button.setBackgroundDrawable((Drawable)backgroundDrawable);
        }
    }
}

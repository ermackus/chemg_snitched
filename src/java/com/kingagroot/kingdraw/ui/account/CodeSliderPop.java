package com.kingagroot.kingdraw.ui.account;

import android.webkit.JavascriptInterface;
import com.goodsrc.library.utils.GsonUtil;
import com.kingagroot.kingdraw.model.SlideModel;
import android.text.TextUtils;
import android.util.Log;
import android.view.WindowManager$LayoutParams;
import android.view.Window;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.os.Bundle;
import android.view.View;
import com.kingagroot.kingdraw.config.NetConfig;
import com.goodsrc.library.utils.LanguageTool;
import android.webkit.ValueCallback;
import android.webkit.WebViewClient;
import android.webkit.WebView;
import android.widget.ImageButton;
import android.content.Context;
import android.view.View$OnClickListener;
import android.app.Dialog;

public class CodeSliderPop extends Dialog implements View$OnClickListener
{
    static final boolean $assertionsDisabled = false;
    public static final String TAG = "CodeSliderPop";
    private Context context;
    private ImageButton ibtSlideClose;
    private ImageButton ibtSlideRefresh;
    private int mWidth;
    private OnSliderInfoListener onSliderInfoListener;
    private WebView webCodeSlider;
    
    public CodeSliderPop(final Context context) {
        super(context, 2131886849);
        this.context = context;
    }
    
    private void initWeb() {
        this.webCodeSlider.getSettings().setUseWideViewPort(true);
        this.webCodeSlider.getSettings().setLoadWithOverviewMode(true);
        this.webCodeSlider.getSettings().setCacheMode(2);
        this.webCodeSlider.setWebViewClient((WebViewClient)new WebViewClient(this) {
            final CodeSliderPop this$0;
            
            public void onPageFinished(final WebView webView, final String s) {
                super.onPageFinished(webView, s);
                this.this$0.webCodeSlider.post((Runnable)new _$$Lambda$CodeSliderPop$1$ELgidaA5gXj6dLrav1PGgkhWHgY(this));
            }
            
            public boolean shouldOverrideUrlLoading(final WebView webView, final String s) {
                webView.loadUrl(s);
                return true;
            }
        });
        this.webCodeSlider.getSettings().setJavaScriptEnabled(true);
        this.webCodeSlider.addJavascriptInterface((Object)new CodeJsInterface(), "NOBOT");
        String s;
        if (LanguageTool.getLanguageType(this.context).equals((Object)LanguageTool.SER_ZH)) {
            s = NetConfig.slideCnUrl;
        }
        else {
            s = NetConfig.slideEnUrl;
        }
        this.webCodeSlider.loadUrl(s);
        this.webCodeSlider.setScrollContainer(false);
        this.webCodeSlider.setVerticalScrollBarEnabled(false);
        this.webCodeSlider.setHorizontalScrollBarEnabled(false);
    }
    
    public void onClick(final View view) {
        if (view == this.ibtSlideClose) {
            this.dismiss();
        }
        else if (view == this.ibtSlideRefresh) {
            this.webCodeSlider.reload();
        }
    }
    
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.setContentView(2131493143);
        this.setCanceledOnTouchOutside(false);
        final WindowManager windowManager = (WindowManager)this.context.getSystemService("window");
        final DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        this.mWidth = (int)(displayMetrics.widthPixels * 0.8);
        final Window window = this.getWindow();
        window.setGravity(17);
        final WindowManager$LayoutParams attributes = window.getAttributes();
        attributes.width = this.mWidth;
        attributes.height = -2;
        window.setAttributes(attributes);
        this.webCodeSlider = (WebView)this.findViewById(2131297753);
        this.ibtSlideClose = (ImageButton)this.findViewById(2131296827);
        this.ibtSlideRefresh = (ImageButton)this.findViewById(2131296828);
        this.ibtSlideClose.setOnClickListener((View$OnClickListener)this);
        this.ibtSlideRefresh.setOnClickListener((View$OnClickListener)this);
        this.initWeb();
    }
    
    public void setOnSliderInfoListener(final OnSliderInfoListener onSliderInfoListener) {
        this.onSliderInfoListener = onSliderInfoListener;
    }
    
    public class CodeJsInterface
    {
        final CodeSliderPop this$0;
        
        public CodeJsInterface(final CodeSliderPop this$0) {
            this.this$0 = this$0;
        }
        
        @JavascriptInterface
        public void onSlideData(final String s) {
            Log.e("CodeSliderPop", s);
            if (!TextUtils.isEmpty((CharSequence)s)) {
                final SlideModel slideModel = (SlideModel)GsonUtil.fromJson(s, (Class)SlideModel.class);
                if (this.this$0.onSliderInfoListener != null) {
                    this.this$0.onSliderInfoListener.onCallData(slideModel);
                }
            }
        }
    }
    
    public interface OnSliderInfoListener
    {
        void onCallData(final SlideModel p0);
    }
}

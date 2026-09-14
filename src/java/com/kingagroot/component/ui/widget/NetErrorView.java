package com.kingagroot.component.ui.widget;

import android.content.Intent;
import android.text.TextUtils;
import com.goodsrc.library.utils.SystemInfoUtil;
import android.view.View$OnClickListener;
import android.view.ViewGroup;
import android.view.View;
import com.kingagroot.component.ui.R;
import android.util.AttributeSet;
import android.content.Context;
import android.widget.TextView;
import android.widget.LinearLayout;

public class NetErrorView extends LinearLayout
{
    private final TextView tvNetSet;
    
    public NetErrorView(final Context context) {
        this(context, null);
    }
    
    public NetErrorView(final Context context, final AttributeSet set) {
        super(context, set);
        (this.tvNetSet = (TextView)View.inflate(context, R.layout.component_net_error_view, (ViewGroup)this).findViewById(R.id.tv_net_set)).setOnClickListener((View$OnClickListener)new View$OnClickListener(this) {
            final NetErrorView this$0;
            
            public void onClick(final View view) {
                if (TextUtils.equals((CharSequence)SystemInfoUtil.getSystemInfo().getOs(), (CharSequence)"sys_miui")) {
                    this.this$0.getContext().startActivity(new Intent("android.settings.SETTINGS"));
                    this.this$0.setVisibility(8);
                }
                else {
                    this.this$0.getContext().startActivity(new Intent("android.settings.WIRELESS_SETTINGS"));
                    this.this$0.setVisibility(8);
                }
            }
        });
    }
    
    public void setVisibility(final int visibility) {
        super.setVisibility(visibility);
    }
}

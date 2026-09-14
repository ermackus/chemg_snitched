package com.kingagroot.kingdraw.widget;

import android.text.Spanned;
import android.view.ViewGroup;
import android.view.View;
import android.util.AttributeSet;
import android.content.Context;
import android.widget.TextView;
import android.widget.LinearLayout;

public class LocalSearchEmptyView extends LinearLayout
{
    private TextView tvHint;
    
    public LocalSearchEmptyView(final Context context) {
        this(context, null);
    }
    
    public LocalSearchEmptyView(final Context context, final AttributeSet set) {
        this(context, set, 0);
    }
    
    public LocalSearchEmptyView(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        View.inflate(context, 2131493089, (ViewGroup)this);
        this.initView();
    }
    
    private void initView() {
        this.tvHint = (TextView)this.findViewById(2131297609);
    }
    
    public void setTvHint(final Spanned text) {
        this.tvHint.setText((CharSequence)text);
    }
}

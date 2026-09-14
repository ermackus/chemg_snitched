package com.kingagroot.kingdraw.widget;

import android.widget.TextView;
import android.widget.ImageView;
import android.content.Context;
import androidx.appcompat.app.AppCompatDelegate;
import android.widget.LinearLayout;

public abstract class GTabView extends LinearLayout
{
    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }
    
    public GTabView(final Context context) {
        super(context);
    }
    
    public abstract ImageView getImageView();
    
    public abstract TextView getTabText();
    
    public abstract void setShowIcon(final boolean p0);
    
    public abstract void setTabText(final int p0);
    
    public abstract void startAnim();
    
    public abstract void stopAnim();
}

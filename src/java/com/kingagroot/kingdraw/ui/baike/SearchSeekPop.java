package com.kingagroot.kingdraw.ui.baike;

import android.content.res.Resources$Theme;
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ColorDrawable;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.content.Context;
import com.kingagroot.kingdraw.ui.baike.seekbar.GVerticalSeekBar;
import android.widget.LinearLayout;
import com.kingagroot.kingdraw.ui.baike.seekbar.OnProgressChangeListener;
import android.widget.TextView;
import android.view.View;
import android.widget.PopupWindow;

public class SearchSeekPop extends PopupWindow
{
    public static final int MAX = 99;
    public static final int MIN = 1;
    View conentView;
    private final TextView indicatorview;
    private OnProgressChangeListener listener;
    private final LinearLayout rlseek;
    private final GVerticalSeekBar seekbar;
    
    public SearchSeekPop(final Context context) {
        super(context);
        this.setContentView(this.conentView = ((LayoutInflater)context.getSystemService("layout_inflater")).inflate(2131493151, (ViewGroup)null));
        this.setWidth(-1);
        this.setHeight(-1);
        this.setFocusable(true);
        this.setOutsideTouchable(false);
        this.update();
        this.setBackgroundDrawable((Drawable)new ColorDrawable(-2013265920));
        (this.indicatorview = (TextView)this.conentView.findViewById(2131296898)).setBackground((Drawable)VectorDrawableCompat.create(context.getResources(), 2131231521, (Resources$Theme)null));
        this.seekbar = (GVerticalSeekBar)this.conentView.findViewById(2131297363);
        this.rlseek = (LinearLayout)this.conentView.findViewById(2131297028);
        this.seekbar.setOnProgressChangeListener((OnProgressChangeListener)new SearchSeekPop$1(this));
        this.seekbar.setMax(99);
        this.seekbar.setMin(1);
    }
    
    public int getProgress() {
        return this.seekbar.getProgress();
    }
    
    public void setListener(final OnProgressChangeListener listener) {
        this.listener = listener;
    }
    
    public void setProgress(final int progress) {
        this.seekbar.setProgress(progress);
    }
    
    public void showAtLocation(final View view) {
        super.showAtLocation(view, 0, 0, 0);
    }
}

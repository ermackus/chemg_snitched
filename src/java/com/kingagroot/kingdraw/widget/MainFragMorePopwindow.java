package com.kingagroot.kingdraw.widget;

import android.view.View$OnClickListener;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ColorDrawable;
import com.goodsrc.library.utils.DisplayUtil;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.content.Context;
import android.widget.TextView;
import android.widget.LinearLayout;
import android.widget.ImageView;
import android.view.View;
import android.widget.PopupWindow;

public class MainFragMorePopwindow extends PopupWindow
{
    private static String MODE_LIST;
    private static String MODE_PREVIEW;
    OnMainFragMorePopListener OnMainFragMorePopListener;
    View conentView;
    ImageView imgMode;
    private final LinearLayout llBatchOperate;
    private final LinearLayout llListMode;
    TextView tvMode;
    
    public MainFragMorePopwindow(final Context context) {
        super(context);
        this.setContentView(this.conentView = ((LayoutInflater)context.getSystemService("layout_inflater")).inflate(2131493155, (ViewGroup)null));
        this.setWidth(DisplayUtil.dip2px(context, 150.0f));
        this.setHeight(-2);
        this.setFocusable(true);
        this.setOutsideTouchable(true);
        this.update();
        this.setBackgroundDrawable((Drawable)new ColorDrawable(0));
        this.tvMode = (TextView)this.conentView.findViewById(2131297616);
        this.imgMode = (ImageView)this.conentView.findViewById(2131296879);
        this.llListMode = (LinearLayout)this.conentView.findViewById(2131297006);
        this.llBatchOperate = (LinearLayout)this.conentView.findViewById(2131296985);
        this.llListMode.setOnClickListener((View$OnClickListener)new View$OnClickListener(this) {
            final MainFragMorePopwindow this$0;
            
            public void onClick(final View view) {
                if (this.this$0.tvMode.getText().toString().equals((Object)MainFragMorePopwindow.MODE_LIST)) {
                    this.this$0.OnMainFragMorePopListener.onListMode();
                }
                else {
                    this.this$0.OnMainFragMorePopListener.onPreviewMode();
                }
                this.this$0.dismiss();
            }
        });
        this.llBatchOperate.setOnClickListener((View$OnClickListener)new View$OnClickListener(this) {
            final MainFragMorePopwindow this$0;
            
            public void onClick(final View view) {
                this.this$0.OnMainFragMorePopListener.onBatchOperate();
                this.this$0.dismiss();
            }
        });
        MainFragMorePopwindow.MODE_LIST = context.getString(2131820987);
        MainFragMorePopwindow.MODE_PREVIEW = context.getString(2131821195);
    }
    
    public void hideListMode() {
        this.llListMode.setVisibility(8);
    }
    
    public void setOnMainFragMorePopListener(final OnMainFragMorePopListener onMainFragMorePopListener) {
        this.OnMainFragMorePopListener = onMainFragMorePopListener;
    }
    
    public void setShowBatchOperate(final boolean b) {
        if (b) {
            this.llBatchOperate.setVisibility(0);
        }
        else {
            this.llBatchOperate.setVisibility(8);
        }
    }
    
    public void toListMode() {
        this.tvMode.setText((CharSequence)MainFragMorePopwindow.MODE_LIST);
        this.imgMode.setImageResource(2131231335);
    }
    
    public void toPreviewMode() {
        this.tvMode.setText((CharSequence)MainFragMorePopwindow.MODE_PREVIEW);
        this.imgMode.setImageResource(2131231338);
    }
    
    public interface OnMainFragMorePopListener
    {
        void onBatchOperate();
        
        void onListMode();
        
        void onPreviewMode();
    }
}

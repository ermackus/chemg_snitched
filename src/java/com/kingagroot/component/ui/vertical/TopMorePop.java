package com.kingagroot.component.ui.vertical;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.ColorDrawable;
import android.view.ViewGroup;
import com.kingagroot.component.ui.R;
import android.view.LayoutInflater;
import android.content.Context;
import android.widget.LinearLayout;
import android.view.View;
import android.view.View$OnClickListener;
import android.widget.PopupWindow;

public class TopMorePop extends PopupWindow implements View$OnClickListener
{
    private final View contentView;
    private LinearLayout llPopTopHelp;
    private LinearLayout llPopTopPaletteSetting;
    private LinearLayout llPopTopSave;
    private LinearLayout llPopTopSaveAs;
    private LinearLayout llPopTopShare;
    private OnTopMoreMenuClickListener onClickListener;
    
    public TopMorePop(final Context context) {
        super(context);
        this.setContentView(this.contentView = ((LayoutInflater)context.getSystemService("layout_inflater")).inflate(R.layout.pop_top_more_tool, (ViewGroup)null));
        this.setWidth(-2);
        this.setHeight(-2);
        this.setFocusable(true);
        this.setOutsideTouchable(true);
        this.update();
        this.setBackgroundDrawable((Drawable)new ColorDrawable(0));
        this.initView();
    }
    
    private void initView() {
        this.llPopTopShare = (LinearLayout)this.contentView.findViewById(R.id.ll_pop_top_share);
        this.llPopTopSave = (LinearLayout)this.contentView.findViewById(R.id.ll_pop_top_save);
        this.llPopTopSaveAs = (LinearLayout)this.contentView.findViewById(R.id.ll_pop_top_save_as);
        this.llPopTopHelp = (LinearLayout)this.contentView.findViewById(R.id.ll_pop_top_help);
        this.llPopTopPaletteSetting = (LinearLayout)this.contentView.findViewById(R.id.ll_pop_top_palette_setting);
        this.llPopTopShare.setOnClickListener((View$OnClickListener)this);
        this.llPopTopSave.setOnClickListener((View$OnClickListener)this);
        this.llPopTopSaveAs.setOnClickListener((View$OnClickListener)this);
        this.llPopTopHelp.setOnClickListener((View$OnClickListener)this);
        this.llPopTopPaletteSetting.setOnClickListener((View$OnClickListener)this);
    }
    
    public void enableSaveAs(final boolean enabled) {
        this.llPopTopSaveAs.setEnabled(enabled);
    }
    
    public void onClick(final View view) {
        if (view == this.llPopTopShare) {
            this.onClickListener.onShareClick();
        }
        else if (view == this.llPopTopSave) {
            this.onClickListener.onSaveClick();
        }
        else if (view == this.llPopTopSaveAs) {
            this.onClickListener.onSaveAsClick();
        }
        else if (view == this.llPopTopHelp) {
            this.onClickListener.onHelpClick();
        }
        else if (view == this.llPopTopPaletteSetting) {
            this.onClickListener.onPaletteSettingClick();
        }
        this.dismiss();
    }
    
    public void setOnTopMenuClickListener(final OnTopMoreMenuClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }
    
    public interface OnTopMoreMenuClickListener
    {
        void onHelpClick();
        
        void onPaletteSettingClick();
        
        void onSaveAsClick();
        
        void onSaveClick();
        
        void onShareClick();
    }
}

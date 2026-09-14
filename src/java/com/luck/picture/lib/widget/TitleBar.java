package com.luck.picture.lib.widget;

import com.luck.picture.lib.style.TitleBarStyle;
import com.luck.picture.lib.utils.StyleUtils;
import com.luck.picture.lib.utils.DensityUtil;
import com.luck.picture.lib.config.SelectMimeType;
import android.text.TextUtils;
import androidx.core.content.ContextCompat;
import com.luck.picture.lib.config.SelectorProviders;
import android.view.ViewGroup;
import com.luck.picture.lib.R;
import android.view.LayoutInflater;
import android.util.AttributeSet;
import android.content.Context;
import android.widget.TextView;
import android.view.View;
import android.widget.ImageView;
import com.luck.picture.lib.config.SelectorConfig;
import android.view.View$OnClickListener;
import android.widget.RelativeLayout;

public class TitleBar extends RelativeLayout implements View$OnClickListener
{
    protected SelectorConfig config;
    protected ImageView ivArrow;
    protected ImageView ivDelete;
    protected ImageView ivLeftBack;
    protected RelativeLayout rlAlbumBg;
    protected RelativeLayout titleBarLayout;
    protected View titleBarLine;
    protected OnTitleBarListener titleBarListener;
    protected TextView tvCancel;
    protected MarqueeTextView tvTitle;
    protected View viewAlbumClickArea;
    protected View viewTopStatusBar;
    
    public TitleBar(final Context context) {
        super(context);
        this.init();
    }
    
    public TitleBar(final Context context, final AttributeSet set) {
        super(context, set);
        this.init();
    }
    
    public TitleBar(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        this.init();
    }
    
    public ImageView getImageArrow() {
        return this.ivArrow;
    }
    
    public ImageView getImageDelete() {
        return this.ivDelete;
    }
    
    public View getTitleBarLine() {
        return this.titleBarLine;
    }
    
    public TextView getTitleCancelView() {
        return this.tvCancel;
    }
    
    public String getTitleText() {
        return this.tvTitle.getText().toString();
    }
    
    protected void handleLayoutUI() {
    }
    
    protected void inflateLayout() {
        LayoutInflater.from(this.getContext()).inflate(R.layout.ps_title_bar, (ViewGroup)this);
    }
    
    protected void init() {
        this.inflateLayout();
        this.setClickable(true);
        this.setFocusable(true);
        this.config = SelectorProviders.getInstance().getSelectorConfig();
        this.viewTopStatusBar = this.findViewById(R.id.top_status_bar);
        this.titleBarLayout = (RelativeLayout)this.findViewById(R.id.rl_title_bar);
        this.ivLeftBack = (ImageView)this.findViewById(R.id.ps_iv_left_back);
        this.rlAlbumBg = (RelativeLayout)this.findViewById(R.id.ps_rl_album_bg);
        this.ivDelete = (ImageView)this.findViewById(R.id.ps_iv_delete);
        this.viewAlbumClickArea = this.findViewById(R.id.ps_rl_album_click);
        this.tvTitle = (MarqueeTextView)this.findViewById(R.id.ps_tv_title);
        this.ivArrow = (ImageView)this.findViewById(R.id.ps_iv_arrow);
        this.tvCancel = (TextView)this.findViewById(R.id.ps_tv_cancel);
        this.titleBarLine = this.findViewById(R.id.title_bar_line);
        this.ivLeftBack.setOnClickListener((View$OnClickListener)this);
        this.tvCancel.setOnClickListener((View$OnClickListener)this);
        this.rlAlbumBg.setOnClickListener((View$OnClickListener)this);
        this.titleBarLayout.setOnClickListener((View$OnClickListener)this);
        this.viewAlbumClickArea.setOnClickListener((View$OnClickListener)this);
        this.setBackgroundColor(ContextCompat.getColor(this.getContext(), R.color.ps_color_grey));
        this.handleLayoutUI();
        if (TextUtils.isEmpty((CharSequence)this.config.defaultAlbumName)) {
            Context context;
            int n;
            if (this.config.chooseMode == SelectMimeType.ofAudio()) {
                context = this.getContext();
                n = R.string.ps_all_audio;
            }
            else {
                context = this.getContext();
                n = R.string.ps_camera_roll;
            }
            this.setTitle(context.getString(n));
        }
        else {
            this.setTitle(this.config.defaultAlbumName);
        }
    }
    
    public void onClick(final View view) {
        final int id = view.getId();
        if (id != R.id.ps_iv_left_back && id != R.id.ps_tv_cancel) {
            if (id != R.id.ps_rl_album_bg && id != R.id.ps_rl_album_click) {
                if (id == R.id.rl_title_bar) {
                    final OnTitleBarListener titleBarListener = this.titleBarListener;
                    if (titleBarListener != null) {
                        titleBarListener.onTitleDoubleClick();
                    }
                }
            }
            else {
                final OnTitleBarListener titleBarListener2 = this.titleBarListener;
                if (titleBarListener2 != null) {
                    titleBarListener2.onShowAlbumPopWindow((View)this);
                }
            }
        }
        else {
            final OnTitleBarListener titleBarListener3 = this.titleBarListener;
            if (titleBarListener3 != null) {
                titleBarListener3.onBackPressed();
            }
        }
    }
    
    public void setOnTitleBarListener(final OnTitleBarListener titleBarListener) {
        this.titleBarListener = titleBarListener;
    }
    
    public void setTitle(final String text) {
        this.tvTitle.setText((CharSequence)text);
    }
    
    public void setTitleBarStyle() {
        if (this.config.isPreviewFullScreenMode) {
            this.viewTopStatusBar.getLayoutParams().height = DensityUtil.getStatusBarHeight(this.getContext());
        }
        final TitleBarStyle titleBarStyle = this.config.selectorStyle.getTitleBarStyle();
        final int titleBarHeight = titleBarStyle.getTitleBarHeight();
        if (StyleUtils.checkSizeValidity(titleBarHeight)) {
            this.titleBarLayout.getLayoutParams().height = titleBarHeight;
        }
        else {
            this.titleBarLayout.getLayoutParams().height = DensityUtil.dip2px(this.getContext(), 48.0f);
        }
        if (this.titleBarLine != null) {
            if (titleBarStyle.isDisplayTitleBarLine()) {
                this.titleBarLine.setVisibility(0);
                if (StyleUtils.checkStyleValidity(titleBarStyle.getTitleBarLineColor())) {
                    this.titleBarLine.setBackgroundColor(titleBarStyle.getTitleBarLineColor());
                }
            }
            else {
                this.titleBarLine.setVisibility(8);
            }
        }
        final int titleBackgroundColor = titleBarStyle.getTitleBackgroundColor();
        if (StyleUtils.checkStyleValidity(titleBackgroundColor)) {
            this.setBackgroundColor(titleBackgroundColor);
        }
        final int titleLeftBackResource = titleBarStyle.getTitleLeftBackResource();
        if (StyleUtils.checkStyleValidity(titleLeftBackResource)) {
            this.ivLeftBack.setImageResource(titleLeftBackResource);
        }
        String text;
        if (StyleUtils.checkStyleValidity(titleBarStyle.getTitleDefaultTextResId())) {
            text = this.getContext().getString(titleBarStyle.getTitleDefaultTextResId());
        }
        else {
            text = titleBarStyle.getTitleDefaultText();
        }
        if (StyleUtils.checkTextValidity(text)) {
            this.tvTitle.setText((CharSequence)text);
        }
        final int titleTextSize = titleBarStyle.getTitleTextSize();
        if (StyleUtils.checkSizeValidity(titleTextSize)) {
            this.tvTitle.setTextSize((float)titleTextSize);
        }
        final int titleTextColor = titleBarStyle.getTitleTextColor();
        if (StyleUtils.checkStyleValidity(titleTextColor)) {
            this.tvTitle.setTextColor(titleTextColor);
        }
        if (this.config.isOnlySandboxDir) {
            this.ivArrow.setImageResource(R.drawable.ps_ic_trans_1px);
        }
        else {
            final int titleDrawableRightResource = titleBarStyle.getTitleDrawableRightResource();
            if (StyleUtils.checkStyleValidity(titleDrawableRightResource)) {
                this.ivArrow.setImageResource(titleDrawableRightResource);
            }
        }
        final int titleAlbumBackgroundResource = titleBarStyle.getTitleAlbumBackgroundResource();
        if (StyleUtils.checkStyleValidity(titleAlbumBackgroundResource)) {
            this.rlAlbumBg.setBackgroundResource(titleAlbumBackgroundResource);
        }
        if (titleBarStyle.isHideCancelButton()) {
            this.tvCancel.setVisibility(8);
        }
        else {
            this.tvCancel.setVisibility(0);
            final int titleCancelBackgroundResource = titleBarStyle.getTitleCancelBackgroundResource();
            if (StyleUtils.checkStyleValidity(titleCancelBackgroundResource)) {
                this.tvCancel.setBackgroundResource(titleCancelBackgroundResource);
            }
            String text2;
            if (StyleUtils.checkStyleValidity(titleBarStyle.getTitleCancelTextResId())) {
                text2 = this.getContext().getString(titleBarStyle.getTitleCancelTextResId());
            }
            else {
                text2 = titleBarStyle.getTitleCancelText();
            }
            if (StyleUtils.checkTextValidity(text2)) {
                this.tvCancel.setText((CharSequence)text2);
            }
            final int titleCancelTextColor = titleBarStyle.getTitleCancelTextColor();
            if (StyleUtils.checkStyleValidity(titleCancelTextColor)) {
                this.tvCancel.setTextColor(titleCancelTextColor);
            }
            final int titleCancelTextSize = titleBarStyle.getTitleCancelTextSize();
            if (StyleUtils.checkSizeValidity(titleCancelTextSize)) {
                this.tvCancel.setTextSize((float)titleCancelTextSize);
            }
        }
        final int previewDeleteBackgroundResource = titleBarStyle.getPreviewDeleteBackgroundResource();
        if (StyleUtils.checkStyleValidity(previewDeleteBackgroundResource)) {
            this.ivDelete.setBackgroundResource(previewDeleteBackgroundResource);
        }
        else {
            this.ivDelete.setBackgroundResource(R.drawable.ps_ic_delete);
        }
    }
    
    public static class OnTitleBarListener
    {
        public void onBackPressed() {
        }
        
        public void onShowAlbumPopWindow(final View view) {
        }
        
        public void onTitleDoubleClick() {
        }
    }
}

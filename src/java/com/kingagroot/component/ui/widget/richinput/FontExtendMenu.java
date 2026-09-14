package com.kingagroot.component.ui.widget.richinput;

import com.kingagroot.component.ui.widget.richinput.view.GFontSizeButton$OnFontSizeChangeListner;
import com.kingagroot.component.ui.widget.richinput.view.GFontFamilyButton$OnFamilyChangeListner;
import com.kingagroot.component.ui.R$id;
import androidx.core.content.ContextCompat;
import com.kingagroot.component.ui.R$drawable;
import android.widget.CompoundButton$OnCheckedChangeListener;
import android.widget.TextView;
import com.kingagroot.component.ui.R$color;
import android.view.ViewGroup$LayoutParams;
import android.widget.LinearLayout$LayoutParams;
import androidx.appcompat.view.ContextThemeWrapper;
import com.kingagroot.component.ui.R$style;
import com.kingagroot.kingdraw.core.utils.GDensityUtil;
import android.view.ViewGroup;
import com.kingagroot.component.ui.R$layout;
import android.util.AttributeSet;
import android.content.Context;
import com.kingagroot.component.ui.widget.richinput.view.GFontSizeButton;
import com.kingagroot.component.ui.widget.richinput.view.GFontFamilyButton;
import android.widget.HorizontalScrollView;
import android.widget.SeekBar;
import com.kingagroot.component.ui.widget.richinput.view.RichEditor;
import android.widget.SeekBar$OnSeekBarChangeListener;
import android.view.View$OnClickListener;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;

public class FontExtendMenu extends LinearLayout implements BaseExtendMenu
{
    private static final int[] FONTSIZES;
    private CheckBox cbFontSizeType;
    View contentView;
    private final View$OnClickListener fontFamilyClick;
    private final View$OnClickListener fontSizeClick;
    private LinearLayout llFontFamily;
    private LinearLayout llFontSize;
    private final SeekBar$OnSeekBarChangeListener onSeekBarChangeListener;
    private RichEditor richEditor;
    private SeekBar sbFontSize;
    private HorizontalScrollView scrollView;
    private GFontFamilyButton tvFontFamily;
    private GFontSizeButton tvFontSize;
    
    static {
        FONTSIZES = new int[] { 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28 };
    }
    
    public FontExtendMenu(final Context context) {
        this(context, null);
    }
    
    public FontExtendMenu(final Context context, final AttributeSet set) {
        super(context, set);
        this.fontFamilyClick = (View$OnClickListener)new FontExtendMenu$4(this);
        this.onSeekBarChangeListener = (SeekBar$OnSeekBarChangeListener)new FontExtendMenu$5(this);
        this.fontSizeClick = (View$OnClickListener)new FontExtendMenu$6(this);
        this.contentView = View.inflate(context, R$layout.component_rich_font_menu, (ViewGroup)this);
        this.initView();
        this.initData();
    }
    
    private void initData() {
        for (final String family : RichConfig.getFontFamily()) {
            final int dp2px = GDensityUtil.dp2px(8.0f);
            final GFontFamilyButton gFontFamilyButton = new GFontFamilyButton((Context)new ContextThemeWrapper(this.getContext(), R$style.Widget_AppCompat_ActionButton));
            gFontFamilyButton.setLayoutParams((ViewGroup$LayoutParams)new LinearLayout$LayoutParams(-2, -1));
            gFontFamilyButton.setGravity(17);
            gFontFamilyButton.setTextSize(2, 16.0f);
            gFontFamilyButton.setTextColor(this.getContext().getResources().getColorStateList(R$color.rich_btn_color_selector));
            gFontFamilyButton.setFamily(family);
            gFontFamilyButton.setPadding(0, 0, dp2px * 2, 0);
            gFontFamilyButton.setOnClickListener(this.fontFamilyClick);
            this.llFontFamily.addView((View)gFontFamilyButton);
        }
        for (final int n : FontExtendMenu.FONTSIZES) {
            final int dp2px2 = GDensityUtil.dp2px(8.0f);
            final TextView textView = new TextView((Context)new ContextThemeWrapper(this.getContext(), R$style.Widget_AppCompat_ActionButton));
            final LinearLayout$LayoutParams layoutParams = new LinearLayout$LayoutParams(-2, -1);
            layoutParams.gravity = 17;
            textView.setLayoutParams((ViewGroup$LayoutParams)layoutParams);
            textView.setGravity(17);
            textView.setTextSize(2, 16.0f);
            textView.setTextColor(this.getContext().getResources().getColorStateList(R$color.rich_btn_color_selector));
            textView.setText((CharSequence)String.valueOf(n));
            textView.setPadding(dp2px2, 0, dp2px2 * 2, 0);
            textView.setOnClickListener(this.fontSizeClick);
            this.llFontSize.addView((View)textView);
        }
        this.cbFontSizeType.setOnCheckedChangeListener((CompoundButton$OnCheckedChangeListener)new FontExtendMenu$3(this));
        this.sbFontSize.setMax(RichConfig.getFontSizeMax() - RichConfig.getFontSizeMin());
        this.sbFontSize.setOnSeekBarChangeListener(this.onSeekBarChangeListener);
        this.sbFontSize.setThumb(ContextCompat.getDrawable(this.getContext(), R$drawable.ic_rich_seek_thumb));
    }
    
    private void initView() {
        this.tvFontFamily = (GFontFamilyButton)this.findViewById(R$id.tv_font_family);
        this.llFontFamily = (LinearLayout)this.findViewById(R$id.ll_font_family);
        this.tvFontSize = (GFontSizeButton)this.findViewById(R$id.tv_font_size);
        this.llFontSize = (LinearLayout)this.findViewById(R$id.ll_font_size);
        this.sbFontSize = (SeekBar)this.findViewById(R$id.sb_font_size);
        this.cbFontSizeType = (CheckBox)this.findViewById(R$id.cb_font_size_type);
        this.scrollView = (HorizontalScrollView)this.findViewById(R$id.scroll_view);
        this.tvFontFamily.setOnFamilyChangeListner((GFontFamilyButton$OnFamilyChangeListner)new GFontFamilyButton$OnFamilyChangeListner(this) {
            final FontExtendMenu this$0;
            
            public void onChange(final String s) {
                for (int childCount = this.this$0.llFontFamily.getChildCount(), i = 0; i < childCount; ++i) {
                    final View child = this.this$0.llFontFamily.getChildAt(i);
                    if (child instanceof TextView) {
                        final TextView textView = (TextView)child;
                        textView.setTextColor(this.this$0.getContext().getResources().getColorStateList(R$color.rich_btn_color_selector));
                        if (textView.getText().toString().equals((Object)s)) {
                            textView.setTextColor(this.this$0.getResources().getColor(R$color.rich_blue));
                        }
                    }
                }
            }
        });
        this.tvFontSize.setOnFontSizeChangeListner((GFontSizeButton$OnFontSizeChangeListner)new GFontSizeButton$OnFontSizeChangeListner(this) {
            final FontExtendMenu this$0;
            
            public void onChange(final int n) {
                for (int childCount = this.this$0.llFontSize.getChildCount(), i = 0; i < childCount; ++i) {
                    final View child = this.this$0.llFontSize.getChildAt(i);
                    if (child instanceof TextView) {
                        final TextView textView = (TextView)child;
                        textView.setTextColor(this.this$0.getContext().getResources().getColorStateList(R$color.rich_btn_color_selector));
                        final String string = textView.getText().toString();
                        final StringBuilder sb = new StringBuilder();
                        sb.append("");
                        sb.append(n);
                        if (string.equals((Object)sb.toString())) {
                            textView.setTextColor(this.this$0.getResources().getColor(R$color.rich_blue));
                        }
                    }
                }
                if (this.this$0.sbFontSize.getVisibility() == 0) {
                    this.this$0.sbFontSize.setOnSeekBarChangeListener((SeekBar$OnSeekBarChangeListener)null);
                    this.this$0.setSbFontSize(n);
                    this.this$0.sbFontSize.setOnSeekBarChangeListener(this.this$0.onSeekBarChangeListener);
                }
            }
        });
    }
    
    private void setSbFontSize(final int n) {
        this.sbFontSize.setProgress(n - RichConfig.getFontSizeMin());
    }
    
    private void showSeekView(final boolean b) {
        if (b) {
            this.sbFontSize.setVisibility(0);
            this.scrollView.setVisibility(8);
            this.sbFontSize.setOnSeekBarChangeListener((SeekBar$OnSeekBarChangeListener)null);
            this.setSbFontSize(this.tvFontSize.getSize());
            this.sbFontSize.setOnSeekBarChangeListener(this.onSeekBarChangeListener);
        }
        else {
            this.sbFontSize.setVisibility(8);
            this.scrollView.setVisibility(0);
        }
    }
    
    private void upSeletionController() {
        this.richEditor.upSeletionController();
    }
    
    public GFontFamilyButton getFontFamilyView() {
        return this.tvFontFamily;
    }
    
    public GFontSizeButton getFontSizeView() {
        return this.tvFontSize;
    }
    
    public void setRichEditor(final RichEditor richEditor) {
        this.richEditor = richEditor;
    }
}

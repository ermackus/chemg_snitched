package com.kingagroot.component.ui.menu;

import com.kingagroot.component.ui.widget.richinput.SpanUtil;
import com.kingagroot.component.ui.OnToolHintListener;
import com.kingagroot.component.ui.OnToolChangeListener;
import com.kingagroot.component.ui.utils.CheckDoubleClick;
import com.goodsrc.library.utils.ToastUtil;
import android.text.TextUtils;
import com.goodsrc.library.utils.StringUtils;
import com.kingagroot.component.ui.widget.EditPop$OnEditPopListener;
import com.kingagroot.component.ui.vertical.ToolDataManage;
import com.kingagroot.component.ui.menu.menuitem.ToolBaseItemView;
import java.util.List;
import android.animation.ObjectAnimator;
import com.kingagroot.kingdraw.core.view.KingDrawView;
import androidx.core.content.ContextCompat;
import com.kingagroot.component.ui.R$color;
import com.kingagroot.component.ui.view.GCompoundButton;
import android.widget.CompoundButton$OnCheckedChangeListener;
import android.widget.CheckBox;
import com.kingagroot.component.ui.R$id;
import com.kingagroot.component.ui.R$string;
import com.kingagroot.component.ui.widget.EditPop$Build;
import android.view.ViewGroup;
import com.kingagroot.component.ui.R$layout;
import android.util.AttributeSet;
import android.content.Context;
import android.widget.TextView;
import com.kingagroot.component.ui.PaletteTopInterface;
import com.kingagroot.component.ui.view.GImageButton;
import android.widget.ImageButton;
import android.view.View;
import android.view.View$OnClickListener;
import android.widget.LinearLayout;

public class MainPaletteTopMenu extends LinearLayout implements BaseToolMenu, View$OnClickListener
{
    private final View contentView;
    private ImageButton ibtClose;
    private ImageButton ibtSave;
    private GImageButton ibtSaveAs;
    private LinearLayout llContent;
    private TopMenu menu;
    private String nameHtml;
    private int paletteType;
    private PaletteTopInterface topListener;
    private TextView tvFilename;
    
    public MainPaletteTopMenu(final Context context, final AttributeSet set) {
        super(context, set);
        this.contentView = View.inflate(context, R$layout.component_layout_menu_main_top, (ViewGroup)this);
        this.initView();
    }
    
    private EditPop$Build createEditPop() {
        final EditPop$Build editPop$Build = new EditPop$Build(this.getContext());
        editPop$Build.setHint(this.getContext().getString(R$string.palette_input_filename)).setMaxLines(1);
        return editPop$Build;
    }
    
    private void initView() {
        this.llContent = (LinearLayout)this.findViewById(R$id.ll_content);
        this.ibtClose = (ImageButton)this.findViewById(R$id.ibt_close);
        this.tvFilename = (TextView)this.findViewById(R$id.tv_filename);
        this.ibtSave = (ImageButton)this.findViewById(R$id.ibt_save);
        this.ibtSaveAs = (GImageButton)this.findViewById(R$id.ibt_save_as);
        this.menu = (TopMenu)this.findViewById(R$id.menu);
        ((CheckBox)this.findViewById(R$id.cb_full_screen)).setOnCheckedChangeListener((CompoundButton$OnCheckedChangeListener)new MainPaletteTopMenu$1(this));
        this.ibtClose.setOnClickListener((View$OnClickListener)this);
        this.ibtSave.setOnClickListener((View$OnClickListener)this);
        this.ibtSaveAs.setOnClickListener((View$OnClickListener)this);
        this.tvFilename.setOnClickListener((View$OnClickListener)this);
    }
    
    private void setViewEnable(final GCompoundButton gCompoundButton, final boolean clickEnable) {
        gCompoundButton.setClickEnable(clickEnable);
        if (clickEnable) {
            gCompoundButton.setButtonDrawableColor(ContextCompat.getColor(this.getContext(), R$color.elementColor));
        }
        else {
            gCompoundButton.setButtonDrawableColor(ContextCompat.getColor(this.getContext(), R$color.line));
        }
    }
    
    public void clearCheck() {
        this.menu.clearCheck();
    }
    
    public void contextKingDrawView(final KingDrawView kingDrawView) {
        this.menu.contextKingDrawView(kingDrawView);
    }
    
    public void dismiss() {
        final LinearLayout llContent = this.llContent;
        final ObjectAnimator ofFloat = ObjectAnimator.ofFloat((Object)llContent, "translationY", new float[] { llContent.getTranslationY(), (float)(-this.getMeasuredHeight()) });
        ofFloat.setDuration(300L);
        ofFloat.start();
    }
    
    public String getFileName() {
        return this.tvFilename.getText().toString();
    }
    
    public String getSupName() {
        return this.nameHtml;
    }
    
    public void initData(final List<ToolBaseItemView> list) {
        this.menu.addMenuItems((List)list);
        this.menu.layoutMenu();
    }
    
    public void inputFileName() {
        if (this.paletteType == ToolDataManage.TYPE_SUP) {
            final PaletteTopInterface topListener = this.topListener;
            if (topListener != null) {
                topListener.onInputSupName(this.nameHtml);
            }
        }
        else {
            final EditPop$Build editPop = this.createEditPop();
            editPop.setContent(this.tvFilename.getText().toString().trim());
            editPop.setMaxLength(50);
            editPop.setOnEditPopListener((EditPop$OnEditPopListener)new EditPop$OnEditPopListener(this) {
                final MainPaletteTopMenu this$0;
                
                public void onText(String reFileName) {
                    reFileName = StringUtils.reFileName(reFileName);
                    if (!TextUtils.isEmpty((CharSequence)reFileName)) {
                        this.this$0.tvFilename.setText((CharSequence)reFileName);
                        if (this.this$0.topListener != null) {
                            this.this$0.topListener.onInputFileName(reFileName);
                        }
                    }
                    else {
                        ToastUtil.showShort((CharSequence)this.this$0.getContext().getString(R$string.palette_file_name_hint));
                    }
                }
            });
            editPop.create().show();
        }
    }
    
    public void onClick(final View view) {
        if (CheckDoubleClick.isFastDoubleClick(view)) {
            return;
        }
        if (view == this.tvFilename) {
            this.inputFileName();
        }
        else if (view == this.ibtClose) {
            if (CheckDoubleClick.isFastDoubleClick()) {
                return;
            }
            final PaletteTopInterface topListener = this.topListener;
            if (topListener != null) {
                topListener.onClose();
            }
        }
        else if (view == this.ibtSave) {
            if (CheckDoubleClick.isFastDoubleClick()) {
                return;
            }
            final PaletteTopInterface topListener2 = this.topListener;
            if (topListener2 != null) {
                topListener2.onSaveFile();
            }
        }
        else if (view == this.ibtSaveAs) {
            if (CheckDoubleClick.isFastDoubleClick()) {
                return;
            }
            final PaletteTopInterface topListener3 = this.topListener;
            if (topListener3 != null) {
                topListener3.onSaveAsFile();
            }
        }
    }
    
    public void setFileName(final String text) {
        this.tvFilename.setText((CharSequence)text);
    }
    
    public void setMainPaletteView(final PaletteTopInterface topListener) {
        this.topListener = topListener;
    }
    
    public void setNotch(final boolean b) {
    }
    
    public void setOnToolChangeListener(final OnToolChangeListener onToolChangeListener) {
        this.menu.setOnToolChangeListener(onToolChangeListener);
    }
    
    public void setOnToolHintListener(final OnToolHintListener onToolHintListener) {
        this.menu.setOnToolHintListener(onToolHintListener);
    }
    
    public void setSaveAsEnable(final boolean b) {
        this.setViewEnable((GCompoundButton)this.ibtSaveAs, b);
    }
    
    public void setSupName(final String nameHtml) {
        this.nameHtml = nameHtml;
        this.tvFilename.setText((CharSequence)SpanUtil.htmlToSpan(nameHtml));
    }
    
    public void setTopViewType(final int paletteType) {
        this.paletteType = paletteType;
    }
    
    public void show() {
        final LinearLayout llContent = this.llContent;
        final ObjectAnimator ofFloat = ObjectAnimator.ofFloat((Object)llContent, "translationY", new float[] { llContent.getTranslationY(), 0.0f });
        ofFloat.setDuration(300L);
        ofFloat.start();
    }
}

package com.kingagroot.component.ui.menu;

import com.kingagroot.component.ui.widget.toolpop.PaletteBasePop$OnToolsPopListener;
import android.widget.PopupWindow$OnDismissListener;
import android.view.View$OnTouchListener;
import com.kingagroot.component.ui.model.ToolModel;
import java.util.List;
import com.kingagroot.component.ui.R$drawable;
import com.kingagroot.component.ui.widget.toolpop.BottomToolsPop;
import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.AnimatorSet;
import com.kingagroot.component.ui.ToolEnum;
import android.widget.CompoundButton$OnCheckedChangeListener;
import android.widget.RadioGroup$OnCheckedChangeListener;
import android.widget.CheckBox;
import com.kingagroot.component.ui.R$id;
import android.view.ViewGroup;
import com.kingagroot.component.ui.R$layout;
import android.util.AttributeSet;
import android.widget.HorizontalScrollView;
import android.widget.RadioGroup;
import com.kingagroot.component.ui.view.GRadioButton;
import com.kingagroot.component.ui.OnToolHintListener;
import com.kingagroot.component.ui.OnToolChangeListener;
import android.content.Context;
import android.view.View;
import android.view.View$OnLongClickListener;
import android.widget.LinearLayout;

public class SearchPaletteBottomView extends LinearLayout implements BaseToolMenu, View$OnLongClickListener
{
    private final View contentView;
    private final Context context;
    private LinearLayout llDirection;
    private LinearLayout llTools;
    protected OnToolChangeListener onToolChangeListener;
    protected OnToolHintListener onToolHintListener;
    private GRadioButton rbtBondDouble;
    private GRadioButton rbtCharge;
    private RadioGroup rgElementTool;
    private HorizontalScrollView scrollView;
    
    public SearchPaletteBottomView(final Context context, final AttributeSet set) {
        super(context, set);
        this.context = context;
        this.contentView = View.inflate(context, R$layout.component_view_compound_search, (ViewGroup)this);
        this.initView();
        this.intData();
    }
    
    private void initView() {
        this.llTools = (LinearLayout)this.findViewById(R$id.ll_tools);
        this.scrollView = (HorizontalScrollView)this.findViewById(R$id.scroll_view);
        this.rgElementTool = (RadioGroup)this.findViewById(R$id.rg_element_tool);
        this.rbtBondDouble = (GRadioButton)this.findViewById(R$id.bond_double);
        this.rbtCharge = (GRadioButton)this.findViewById(R$id.rbt_charge);
        this.llDirection = (LinearLayout)this.findViewById(R$id.ll_direction);
        final CheckBox checkBox = (CheckBox)this.findViewById(R$id.cb_direction);
        this.rgElementTool.setOnCheckedChangeListener((RadioGroup$OnCheckedChangeListener)new SearchPaletteBottomView$1(this));
        this.rbtBondDouble.setOnLongClickListener((View$OnLongClickListener)this);
        this.rbtCharge.setOnLongClickListener((View$OnLongClickListener)this);
        checkBox.setOnCheckedChangeListener((CompoundButton$OnCheckedChangeListener)new SearchPaletteBottomView$2(this));
    }
    
    private void intData() {
        this.rbtBondDouble.setTag((Object)ToolEnum.GBOND_DOUBLE_TOOL.id);
        this.rbtCharge.setTag((Object)ToolEnum.GCHARGE_POSITIVE_TOOL.id);
    }
    
    private void toRightIn() {
        final AnimatorSet set = new AnimatorSet();
        final LinearLayout llTools = this.llTools;
        final ObjectAnimator ofFloat = ObjectAnimator.ofFloat((Object)llTools, "translationX", new float[] { llTools.getTranslationX(), 0.0f });
        ofFloat.setDuration(600L);
        final LinearLayout llDirection = this.llDirection;
        final ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat((Object)llDirection, "alpha", new float[] { llDirection.getAlpha(), 1.0f });
        ofFloat2.setDuration(300L);
        set.play((Animator)ofFloat).with((Animator)ofFloat2);
        set.start();
    }
    
    private void toRightOut() {
        final AnimatorSet set = new AnimatorSet();
        final LinearLayout llTools = this.llTools;
        final ObjectAnimator ofFloat = ObjectAnimator.ofFloat((Object)llTools, "translationX", new float[] { llTools.getTranslationX(), (float)this.llTools.getMeasuredWidth() });
        ofFloat.setDuration(600L);
        final LinearLayout llDirection = this.llDirection;
        final ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat((Object)llDirection, "alpha", new float[] { llDirection.getAlpha(), 0.5f });
        ofFloat2.setDuration(300L);
        set.play((Animator)ofFloat).with((Animator)ofFloat2);
        set.start();
    }
    
    public void clearCheck() {
        this.rgElementTool.clearCheck();
    }
    
    public void dismiss() {
        final ObjectAnimator ofFloat = ObjectAnimator.ofFloat((Object)this, "translationY", new float[] { this.getTranslationY(), (float)this.getMeasuredHeight() });
        ofFloat.setDuration(300L);
        ofFloat.start();
    }
    
    public boolean onLongClick(final View view) {
        final GRadioButton gRadioButton = (GRadioButton)view;
        if (gRadioButton.hasMore()) {
            final BottomToolsPop bottomToolsPop = new BottomToolsPop(this.context, this.contentView);
            gRadioButton.setBackgroundResource(R$drawable.bg_bottom_select);
            gRadioButton.setVisibleCorner(false);
            if (gRadioButton == this.rbtBondDouble) {
                bottomToolsPop.setToolModels((List<ToolModel>)PaletteToolsData.getBondTools());
            }
            else if (gRadioButton == this.rbtCharge) {
                bottomToolsPop.setToolModels((List<ToolModel>)PaletteToolsData.getChargeTools());
            }
            bottomToolsPop.setOldCheck(ToolEnum.getValueById((int)gRadioButton.getTag()));
            bottomToolsPop.show((View)gRadioButton);
            this.scrollView.setOnTouchListener((View$OnTouchListener)new SearchPaletteBottomView$3(this));
            bottomToolsPop.setOnDismissListener((PopupWindow$OnDismissListener)new SearchPaletteBottomView$4(this, gRadioButton));
            bottomToolsPop.setOnToolsPopListener((PaletteBasePop$OnToolsPopListener)new PaletteBasePop$OnToolsPopListener(this, gRadioButton) {
                final SearchPaletteBottomView this$0;
                final GRadioButton val$radioButton;
                
                public void onChoice(final ToolModel toolModel) {
                    final ToolEnum toolEnum = toolModel.getToolEnum();
                    if (toolEnum == null) {
                        return;
                    }
                    this.val$radioButton.setButtonDrawable(toolModel.getResId());
                    this.val$radioButton.setTag((Object)toolEnum.id);
                    this.this$0.rgElementTool.clearCheck();
                    this.val$radioButton.setChecked(true);
                    if (this.this$0.onToolHintListener != null) {
                        this.this$0.onToolHintListener.onShowToolName(this.this$0.getContext().getString(toolEnum.hintNameId));
                    }
                    if (this.this$0.onToolChangeListener != null) {
                        this.this$0.onToolChangeListener.onChange(toolEnum);
                    }
                }
            });
            return true;
        }
        return false;
    }
    
    public void setNotch(final boolean b) {
    }
    
    public void setOnToolChangeListener(final OnToolChangeListener onToolChangeListener) {
        this.onToolChangeListener = onToolChangeListener;
    }
    
    public void setOnToolHintListener(final OnToolHintListener onToolHintListener) {
        this.onToolHintListener = onToolHintListener;
    }
    
    public void show() {
        final ObjectAnimator ofFloat = ObjectAnimator.ofFloat((Object)this, "translationY", new float[] { this.getTranslationY(), 0.0f });
        ofFloat.setDuration(300L);
        ofFloat.start();
    }
}

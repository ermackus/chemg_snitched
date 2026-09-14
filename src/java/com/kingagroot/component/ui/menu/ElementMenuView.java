package com.kingagroot.component.ui.menu;

import com.kingagroot.component.ui.R$anim;
import android.app.Activity;
import com.kingagroot.kingdraw.core.tool.GestureTool;
import com.kingagroot.kingdraw.core.tool.ToolNameEnum;
import android.content.Intent;
import java.util.Iterator;
import java.util.List;
import com.kingagroot.component.ui.R$drawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ColorDrawable;
import com.kingagroot.component.ui.R$color;
import android.view.ViewGroup$LayoutParams;
import android.widget.RadioGroup$LayoutParams;
import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.AnimatorSet;
import com.kingagroot.component.ui.ToolEnum;
import android.widget.RadioButton;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver$OnGlobalLayoutListener;
import android.view.View$OnClickListener;
import android.widget.CompoundButton$OnCheckedChangeListener;
import com.kingagroot.component.ui.R$id;
import com.kingagroot.component.ui.db.impl.PeriodicTableDBImpl;
import android.view.View$MeasureSpec;
import com.goodsrc.library.utils.DisplayUtil;
import android.view.ViewGroup;
import com.kingagroot.component.ui.R$layout;
import android.util.AttributeSet;
import com.kingagroot.component.ui.ElementTableActivity;
import android.widget.ScrollView;
import android.widget.RadioGroup;
import com.kingagroot.component.ui.view.GRadioButton;
import com.kingagroot.component.ui.db.PeriodicTableDBI;
import com.kingagroot.component.ui.OnToolHintListener;
import com.kingagroot.component.ui.OnToolChangeListener;
import android.widget.RadioGroup$OnCheckedChangeListener;
import com.kingagroot.kingdraw.core.view.KingDrawView;
import android.content.Context;
import android.view.View;
import com.kingagroot.component.ui.model.GAtom;
import android.widget.CheckBox;
import android.widget.LinearLayout;

public class ElementMenuView extends LinearLayout implements BaseToolMenu
{
    private static final int REQUEST_CODE_ELEMENT_TABLE = 1000;
    private int Max_Gatom;
    private int RadioWidth;
    private CheckBox cbDirection;
    private GAtom checkAtom;
    private final View contentView;
    private final Context context;
    private boolean isGestureOpen;
    protected boolean isNotch;
    private KingDrawView kingDrawView;
    private LinearLayout llContent;
    private int mWidth;
    private RadioGroup$OnCheckedChangeListener onCheckedChangeListener;
    protected OnToolChangeListener onToolChangeListener;
    protected OnToolHintListener onToolHintListener;
    private PeriodicTableDBI periodicTableDBI;
    private GRadioButton rbtElementTable;
    private RadioGroup rgElement;
    private ScrollView scrollView;
    int scrollY;
    private Class<? extends ElementTableActivity> tableActivity;
    private int translationX;
    
    public ElementMenuView(final Context context, final AttributeSet set) {
        super(context, set);
        this.checkAtom = null;
        this.isGestureOpen = false;
        this.tableActivity = ElementTableActivity.class;
        this.onCheckedChangeListener = (RadioGroup$OnCheckedChangeListener)new ElementMenuView$5(this);
        this.context = context;
        this.contentView = View.inflate(context, R$layout.component_layout_menu_element, (ViewGroup)this);
        this.initView();
        this.initData();
    }
    
    private void initData() {
        this.RadioWidth = DisplayUtil.dip2px(this.context, 45.0f);
        final int measureSpec = View$MeasureSpec.makeMeasureSpec(0, 0);
        this.measure(measureSpec, measureSpec);
        this.cbDirection.setAlpha(0.5f);
        this.mWidth = this.getMeasuredWidth();
        this.cbDirection.setChecked(false);
        this.periodicTableDBI = (PeriodicTableDBI)new PeriodicTableDBImpl();
        this.initCollectPeriodic();
    }
    
    private void initView() {
        this.llContent = (LinearLayout)this.findViewById(R$id.ll_content);
        this.rgElement = (RadioGroup)this.findViewById(R$id.rg_element);
        this.rbtElementTable = (GRadioButton)this.findViewById(R$id.rbt_element_table);
        this.cbDirection = (CheckBox)this.findViewById(R$id.cb_direction);
        this.scrollView = (ScrollView)this.findViewById(R$id.scrollview);
        this.cbDirection.setOnCheckedChangeListener((CompoundButton$OnCheckedChangeListener)new ElementMenuView$1(this));
        this.rgElement.setOnCheckedChangeListener(this.onCheckedChangeListener);
        this.rbtElementTable.setOnClickListener((View$OnClickListener)new ElementMenuView$2(this));
        final ViewTreeObserver viewTreeObserver = this.llContent.getViewTreeObserver();
        viewTreeObserver.addOnGlobalLayoutListener((ViewTreeObserver$OnGlobalLayoutListener)new ElementMenuView$3(this, viewTreeObserver));
    }
    
    private boolean setCheck(final GAtom checkAtom) {
        this.checkAtom = checkAtom;
        while (true) {
            for (int childCount = this.rgElement.getChildCount(), i = 0; i < childCount; ++i) {
                final View child = this.rgElement.getChildAt(i);
                if (child instanceof RadioButton) {
                    final GAtom gAtom = (GAtom)child.getTag();
                    if (gAtom != null && gAtom.name.equals((Object)checkAtom.name)) {
                        this.rgElement.setOnCheckedChangeListener((RadioGroup$OnCheckedChangeListener)null);
                        ((RadioButton)child).setChecked(true);
                        this.rgElement.setOnCheckedChangeListener(this.onCheckedChangeListener);
                        final boolean b = true;
                        this.scrollY = 0;
                        final int max_Gatom = this.Max_Gatom;
                        if (i >= max_Gatom) {
                            this.scrollY = (i + 1 - max_Gatom) * this.RadioWidth;
                        }
                        this.scrollView.postDelayed((Runnable)new ElementMenuView$6(this), 300L);
                        if (this.onToolChangeListener != null) {
                            final ToolEnum gatom_TOOL = ToolEnum.GATOM_TOOL;
                            gatom_TOOL.toolParm = checkAtom.name;
                            this.onToolChangeListener.onChange(gatom_TOOL);
                        }
                        final OnToolHintListener onToolHintListener = this.onToolHintListener;
                        if (onToolHintListener != null) {
                            onToolHintListener.onShowToolName(checkAtom.getNameEn());
                        }
                        return b;
                    }
                }
            }
            final boolean b = false;
            int i = 0;
            continue;
        }
    }
    
    private void toLeftIn() {
        this.translationX = 0;
        final AnimatorSet set = new AnimatorSet();
        final ObjectAnimator ofFloat = ObjectAnimator.ofFloat((Object)this, "translationX", new float[] { this.getTranslationX(), (float)this.translationX });
        ofFloat.setDuration(300L);
        final CheckBox cbDirection = this.cbDirection;
        final ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat((Object)cbDirection, "alpha", new float[] { cbDirection.getAlpha(), 1.0f });
        ofFloat2.setDuration(300L);
        if (this.isNotch) {
            final ObjectAnimator ofFloat3 = ObjectAnimator.ofFloat((Object)this.llContent, "scaleY", new float[] { 0.0f, 1.0f });
            final ObjectAnimator ofFloat4 = ObjectAnimator.ofFloat((Object)this.llContent, "scaleX", new float[] { 0.0f, 1.0f });
            ofFloat3.setDuration(300L);
            set.play((Animator)ofFloat).with((Animator)ofFloat2).with((Animator)ofFloat3).with((Animator)ofFloat4);
        }
        else {
            set.play((Animator)ofFloat).with((Animator)ofFloat2);
        }
        set.start();
    }
    
    private void toLeftOut() {
        this.translationX = this.rgElement.getMeasuredWidth();
        final AnimatorSet set = new AnimatorSet();
        final ObjectAnimator ofFloat = ObjectAnimator.ofFloat((Object)this, "translationX", new float[] { this.getTranslationX(), (float)(-this.translationX) });
        ofFloat.setDuration(300L);
        final CheckBox cbDirection = this.cbDirection;
        final ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat((Object)cbDirection, "alpha", new float[] { cbDirection.getAlpha(), 0.5f });
        if (this.isNotch) {
            final ObjectAnimator ofFloat3 = ObjectAnimator.ofFloat((Object)this.llContent, "scaleY", new float[] { 1.0f, 0.0f });
            final ObjectAnimator ofFloat4 = ObjectAnimator.ofFloat((Object)this.llContent, "scaleX", new float[] { 1.0f, 0.0f });
            ofFloat3.setDuration(300L);
            set.play((Animator)ofFloat).with((Animator)ofFloat2).with((Animator)ofFloat3).with((Animator)ofFloat4);
        }
        else {
            set.play((Animator)ofFloat).with((Animator)ofFloat2);
        }
        set.start();
    }
    
    public void clearCheck() {
        this.checkAtom = null;
        this.rgElement.clearCheck();
        this.rbtElementTable.setChecked(false);
    }
    
    public void contextKingDrawView(final KingDrawView kingDrawView) {
        this.kingDrawView = kingDrawView;
    }
    
    public void dismiss() {
        final ObjectAnimator ofFloat = ObjectAnimator.ofFloat((Object)this, "translationX", new float[] { this.getTranslationX(), (float)(-this.mWidth) });
        ofFloat.setDuration(300L);
        ofFloat.start();
    }
    
    public void initCollectPeriodic() {
        this.rgElement.removeAllViews();
        final List collectPeriodicTable = this.periodicTableDBI.getCollectPeriodicTable();
        if (collectPeriodicTable != null) {
            for (final GAtom tag : collectPeriodicTable) {
                final int radioWidth = this.RadioWidth;
                final RadioGroup$LayoutParams layoutParams = new RadioGroup$LayoutParams(radioWidth, radioWidth);
                layoutParams.gravity = 17;
                final GRadioButton gRadioButton = new GRadioButton(this.context);
                gRadioButton.setLayoutParams((ViewGroup$LayoutParams)layoutParams);
                gRadioButton.setText((CharSequence)tag.getName());
                gRadioButton.setGravity(17);
                gRadioButton.setTextColor(this.context.getResources().getColor(R$color.elementColor));
                gRadioButton.setTextSize(1, 14.0f);
                gRadioButton.setTag((Object)tag);
                gRadioButton.setButtonDrawable((Drawable)new ColorDrawable(0));
                gRadioButton.setBackgroundResource(R$drawable.radiobutton_background);
                this.rgElement.addView((View)gRadioButton);
                if (this.checkAtom != null && tag.getIndex() == this.checkAtom.getIndex()) {
                    this.rgElement.setOnCheckedChangeListener((RadioGroup$OnCheckedChangeListener)null);
                    gRadioButton.setChecked(true);
                    this.rgElement.setOnCheckedChangeListener(this.onCheckedChangeListener);
                }
                gRadioButton.setOnClickListener((View$OnClickListener)new ElementMenuView$4(this));
            }
        }
    }
    
    public void onActivityResult(int n, final int n2, final Intent intent) {
        if (n == 1000) {
            this.initCollectPeriodic();
            final KingDrawView kingDrawView = this.kingDrawView;
            GestureTool gestureTool2 = null;
            Label_0065: {
                if (kingDrawView != null && kingDrawView.getToolNameEnum() == ToolNameEnum.GGESTURE_TOOL) {
                    final GestureTool gestureTool = this.kingDrawView.getGestureTool();
                    if ((gestureTool2 = gestureTool) != null) {
                        n = 1;
                        gestureTool2 = gestureTool;
                        break Label_0065;
                    }
                }
                else {
                    gestureTool2 = null;
                }
                n = 0;
            }
            if (n != 0 && this.isGestureOpen) {
                if (n2 == -1 && intent != null) {
                    gestureTool2.SetAtomName(((GAtom)intent.getSerializableExtra("data")).name);
                }
                this.rbtElementTable.setChecked(false);
            }
            else if (n2 == -1 && intent != null) {
                final GAtom check = (GAtom)intent.getSerializableExtra("data");
                this.rgElement.setOnCheckedChangeListener((RadioGroup$OnCheckedChangeListener)null);
                this.rgElement.clearCheck();
                this.rgElement.setOnCheckedChangeListener(this.onCheckedChangeListener);
                if (this.setCheck(check)) {
                    this.rbtElementTable.setChecked(false);
                }
            }
            else {
                this.rbtElementTable.setChecked(false);
            }
        }
    }
    
    protected void onMeasure(final int n, final int n2) {
        super.onMeasure(n, n2);
    }
    
    public void openPeriodicTable(final boolean isGestureOpen) {
        this.isGestureOpen = isGestureOpen;
        if (!ElementTableActivity.isShow) {
            ElementTableActivity.isShow = true;
            final Activity activity = (Activity)this.context;
            activity.startActivityForResult(new Intent(this.context, (Class)this.tableActivity), 1000);
            activity.overridePendingTransition(R$anim.push_bottom_in, R$anim.activity_stay);
        }
    }
    
    public void setElementTableActivity(final Class<? extends ElementTableActivity> tableActivity) {
        this.tableActivity = tableActivity;
    }
    
    public void setNotch(final boolean isNotch) {
        this.isNotch = isNotch;
    }
    
    public void setOnToolChangeListener(final OnToolChangeListener onToolChangeListener) {
        this.onToolChangeListener = onToolChangeListener;
    }
    
    public void setOnToolHintListener(final OnToolHintListener onToolHintListener) {
        this.onToolHintListener = onToolHintListener;
    }
    
    public void show() {
        final ObjectAnimator ofFloat = ObjectAnimator.ofFloat((Object)this, "translationX", new float[] { this.getTranslationX(), (float)(-this.translationX) });
        ofFloat.setDuration(300L);
        ofFloat.start();
    }
}

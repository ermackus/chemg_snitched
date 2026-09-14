package com.kingagroot.component.ui.menu;

import android.text.SpannableStringBuilder;
import android.view.ViewTreeObserver;
import com.kingagroot.component.ui.widget.richinput.SpanUtil;
import android.view.ViewTreeObserver$OnPreDrawListener;
import android.text.TextUtils;
import com.kingagroot.component.ui.model.GSGroupModel;
import com.kingagroot.component.ui.utils.CheckDoubleClick;
import android.animation.ObjectAnimator;
import com.kingagroot.component.ui.R$id;
import com.kingagroot.component.ui.R$drawable;
import android.view.ViewGroup;
import android.view.View;
import com.kingagroot.component.ui.R$layout;
import android.util.AttributeSet;
import android.content.Context;
import android.widget.TextView;
import com.kingagroot.component.ui.widget.sup.SupCollectView;
import com.kingagroot.component.ui.widget.PaletteFingerView;
import android.widget.ImageView;
import android.widget.ImageButton;
import android.view.View$OnLongClickListener;
import android.view.View$OnClickListener;
import android.widget.FrameLayout;

public class PaletteOtherView extends FrameLayout implements View$OnClickListener, BaseToolMenu, View$OnLongClickListener
{
    private ImageButton cleanup;
    private FrameLayout flScreen;
    private FrameLayout flSup;
    private ImageButton ibtEncyclopedia;
    private ImageButton ibtGesture;
    private ImageButton ibtR;
    private ImageButton ibtScreenT;
    private ImageButton ibtSup;
    private ImageView ivRedDot;
    private PaletteOtherView.PaletteOtherView$OnMenuClickListener onMenuClickListener;
    private PaletteFingerView paletteMove;
    private SupCollectView supCollectView;
    private TextView tvSup;
    
    public PaletteOtherView(final Context context, final AttributeSet set) {
        super(context, set);
        this.initView(View.inflate(context, R$layout.component_view_usual_base, (ViewGroup)this));
    }
    
    private void checkGestureTool(final boolean b) {
        this.ibtGesture.setTag((Object)b);
        if (b) {
            this.ibtGesture.setBackgroundResource(R$drawable.drag_bg_check);
            this.ibtGesture.setImageResource(R$drawable.ic_draw_ic_gesture_sel);
        }
        else {
            this.ibtGesture.setBackgroundResource(R$drawable.drag_bg_nor);
            this.ibtGesture.setImageResource(R$drawable.ic_draw_ic_gesture_nor);
        }
    }
    
    private void initView(final View view) {
        this.ibtEncyclopedia = (ImageButton)view.findViewById(R$id.ibt_encyclopedia);
        this.ibtGesture = (ImageButton)view.findViewById(R$id.ibt_gesture);
        this.ibtR = (ImageButton)view.findViewById(R$id.ibt_r);
        this.flSup = (FrameLayout)view.findViewById(R$id.fl_sup);
        this.ibtSup = (ImageButton)view.findViewById(R$id.ibt_sup);
        this.tvSup = (TextView)view.findViewById(R$id.tv_sup);
        this.cleanup = (ImageButton)view.findViewById(R$id.cleanup);
        this.ibtScreenT = (ImageButton)view.findViewById(R$id.ibt_screen_t);
        this.paletteMove = (PaletteFingerView)view.findViewById(R$id.palette_move);
        this.ivRedDot = (ImageView)view.findViewById(R$id.iv_red_dot);
        this.flScreen = (FrameLayout)view.findViewById(R$id.fl_screen);
        this.ibtEncyclopedia.setOnClickListener((View$OnClickListener)this);
        this.ibtGesture.setOnClickListener((View$OnClickListener)this);
        this.ibtR.setOnClickListener((View$OnClickListener)this);
        this.ibtSup.setOnClickListener((View$OnClickListener)this);
        this.tvSup.setOnClickListener((View$OnClickListener)this);
        this.cleanup.setOnClickListener((View$OnClickListener)this);
        this.paletteMove.setOnClickListener((View$OnClickListener)this);
        this.ibtGesture.setOnLongClickListener((View$OnLongClickListener)this);
        this.ibtScreenT.setOnClickListener((View$OnClickListener)this);
    }
    
    private void restSup() {
        this.ibtSup.setVisibility(0);
        this.tvSup.setText((CharSequence)"");
        this.tvSup.setVisibility(8);
    }
    
    public void checkRTool(final boolean b) {
        this.ibtR.setTag((Object)b);
        if (b) {
            this.ibtR.setBackgroundResource(R$drawable.drag_bg_check);
            this.ibtR.setImageResource(R$drawable.ic_r_check);
        }
        else {
            this.ibtR.setBackgroundResource(R$drawable.drag_bg_nor);
            this.ibtR.setImageResource(R$drawable.ic_r);
        }
    }
    
    public void clearCheck() {
        this.checkRTool(false);
        this.checkGestureTool(false);
        this.paletteMove.setSelect(false);
        this.restSup();
        final SupCollectView supCollectView = this.supCollectView;
        if (supCollectView != null) {
            supCollectView.restSup();
        }
    }
    
    public void dismiss() {
        final ObjectAnimator ofFloat = ObjectAnimator.ofFloat((Object)this, "translationX", new float[] { this.getTranslationX(), (float)this.getMeasuredWidth() });
        ofFloat.setDuration(300L);
        ofFloat.start();
    }
    
    public PaletteFingerView getPaletteMove() {
        return this.paletteMove;
    }
    
    public void onClick(final View view) {
        if (CheckDoubleClick.isFastDoubleClick(view)) {
            return;
        }
        if (view == this.ibtSup) {
            final PaletteOtherView.PaletteOtherView$OnMenuClickListener onMenuClickListener = this.onMenuClickListener;
            if (onMenuClickListener != null) {
                onMenuClickListener.onSupButtOnClick();
            }
        }
        else if (view == this.tvSup) {
            final PaletteOtherView.PaletteOtherView$OnMenuClickListener onMenuClickListener2 = this.onMenuClickListener;
            if (onMenuClickListener2 != null) {
                onMenuClickListener2.onSupTextOnClick();
            }
        }
        else if (view == this.cleanup) {
            final PaletteOtherView.PaletteOtherView$OnMenuClickListener onMenuClickListener3 = this.onMenuClickListener;
            if (onMenuClickListener3 != null) {
                onMenuClickListener3.onCleanUpOnClick();
            }
        }
        else if (view == this.ibtEncyclopedia) {
            final PaletteOtherView.PaletteOtherView$OnMenuClickListener onMenuClickListener4 = this.onMenuClickListener;
            if (onMenuClickListener4 != null) {
                onMenuClickListener4.onEncyOnClick();
            }
        }
        else if (view == this.ibtGesture) {
            if (this.onMenuClickListener != null) {
                this.checkGestureTool(true);
                this.restSup();
                this.paletteMove.setSelect(false);
                this.onMenuClickListener.onGestureOnClick();
            }
            final SupCollectView supCollectView = this.supCollectView;
            if (supCollectView != null) {
                supCollectView.restSup();
            }
            this.checkRTool(false);
        }
        else if (view == this.paletteMove) {
            if (this.onMenuClickListener != null) {
                this.checkGestureTool(false);
                this.restSup();
                this.paletteMove.setSelect(true);
                this.onMenuClickListener.onMoveOnClick();
            }
            final SupCollectView supCollectView2 = this.supCollectView;
            if (supCollectView2 != null) {
                supCollectView2.restSup();
            }
            this.checkRTool(false);
        }
        else if (view == this.ibtR) {
            if (this.onMenuClickListener != null) {
                this.checkGestureTool(false);
                this.restSup();
                this.paletteMove.setSelect(false);
                this.checkRTool(true);
                this.onMenuClickListener.onSupR();
            }
            final SupCollectView supCollectView3 = this.supCollectView;
            if (supCollectView3 != null) {
                supCollectView3.restSup();
            }
        }
        else if (view == this.ibtScreenT && this.onMenuClickListener != null) {
            this.checkGestureTool(false);
            this.restSup();
            this.paletteMove.setSelect(false);
            this.checkRTool(true);
            this.onMenuClickListener.onScreenToVertical();
            this.ivRedDot.setVisibility(8);
        }
    }
    
    public boolean onLongClick(final View view) {
        if (view == this.ibtGesture) {
            final PaletteOtherView.PaletteOtherView$OnMenuClickListener onMenuClickListener = this.onMenuClickListener;
            if (onMenuClickListener != null) {
                onMenuClickListener.onGestureOnLongClick();
            }
        }
        return true;
    }
    
    public void setChooseSupState(final GSGroupModel gsGroupModel) {
        this.ibtSup.setVisibility(8);
        this.tvSup.setVisibility(0);
        this.tvSup.setTextSize(14.0f);
        if (TextUtils.isEmpty((CharSequence)gsGroupModel.getNameHtml())) {
            this.tvSup.setText((CharSequence)gsGroupModel.getName());
            final ViewTreeObserver viewTreeObserver = this.tvSup.getViewTreeObserver();
            viewTreeObserver.addOnPreDrawListener((ViewTreeObserver$OnPreDrawListener)new PaletteOtherView$1(this, viewTreeObserver));
        }
        else {
            final SpannableStringBuilder htmlToSpan = SpanUtil.htmlToSpan(gsGroupModel.getNameHtml());
            SpanUtil.setSpanFontSize(htmlToSpan, 20);
            this.tvSup.setText((CharSequence)htmlToSpan);
            final ViewTreeObserver viewTreeObserver2 = this.tvSup.getViewTreeObserver();
            viewTreeObserver2.addOnPreDrawListener((ViewTreeObserver$OnPreDrawListener)new PaletteOtherView$2(this, htmlToSpan, viewTreeObserver2));
        }
    }
    
    public void setDotShow(final boolean b) {
        if (b) {
            this.ivRedDot.setVisibility(0);
        }
        else {
            this.ivRedDot.setVisibility(8);
        }
    }
    
    public void setEncyclopediaShow(final boolean b) {
        if (b) {
            this.ibtEncyclopedia.setVisibility(0);
        }
        else {
            this.ibtEncyclopedia.setVisibility(8);
        }
    }
    
    public void setNotch(final boolean b) {
    }
    
    public void setOnMenuClickListener(final PaletteOtherView.PaletteOtherView$OnMenuClickListener onMenuClickListener) {
        this.onMenuClickListener = onMenuClickListener;
    }
    
    public void setScreenShow(final boolean b) {
        if (b) {
            this.flScreen.setVisibility(8);
        }
        else {
            this.flScreen.setVisibility(0);
        }
    }
    
    public void setSupShow(final boolean b) {
        if (b) {
            this.flSup.setVisibility(8);
            this.ibtR.setVisibility(0);
            this.flScreen.setVisibility(8);
        }
        else {
            this.flSup.setVisibility(0);
            this.ibtR.setVisibility(8);
            this.flScreen.setVisibility(0);
        }
    }
    
    public void setSupView(final SupCollectView supCollectView) {
        this.supCollectView = supCollectView;
    }
    
    public void show() {
        final ObjectAnimator ofFloat = ObjectAnimator.ofFloat((Object)this, "translationX", new float[] { this.getTranslationX(), 0.0f });
        ofFloat.setDuration(300L);
        ofFloat.start();
    }
}

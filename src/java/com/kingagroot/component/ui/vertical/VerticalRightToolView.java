package com.kingagroot.component.ui.vertical;

import com.kingagroot.component.ui.OnToolHintListener;
import com.kingagroot.component.ui.OnToolChangeListener;
import android.text.SpannableStringBuilder;
import com.kingagroot.component.ui.widget.richinput.SpanUtil;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver$OnPreDrawListener;
import android.text.TextUtils;
import com.kingagroot.component.ui.model.GSGroupModel;
import com.kingagroot.component.ui.utils.CheckDoubleClick;
import android.view.ViewGroup$LayoutParams;
import com.kingagroot.component.ui.menu.MoreMenuPop;
import android.widget.LinearLayout$LayoutParams;
import com.kingagroot.kingdraw.core.tool.ClipboardData;
import com.kingagroot.component.ui.menu.menuitem.CopyItemView;
import java.util.Iterator;
import android.view.ViewGroup;
import com.kingagroot.component.ui.R;
import java.util.ArrayList;
import android.util.AttributeSet;
import android.widget.TextView;
import com.kingagroot.component.ui.widget.sup.SupCollectView;
import com.kingagroot.component.ui.menu.OnMenuClickListener;
import com.kingagroot.component.ui.menu.menuitem.ToolBaseItemView;
import java.util.List;
import android.widget.LinearLayout;
import com.kingagroot.kingdraw.core.view.KingDrawView;
import android.widget.ImageButton;
import android.content.Context;
import android.view.View;
import android.view.View$OnLongClickListener;
import android.view.View$OnClickListener;
import android.widget.FrameLayout;

public class VerticalRightToolView extends FrameLayout implements View$OnClickListener, View$OnLongClickListener
{
    private View contentView;
    private Context context;
    private FrameLayout flSup;
    private ImageButton ibtAi;
    private ImageButton ibtDragMove;
    private ImageButton ibtEncyclopedia;
    private ImageButton ibtGesture;
    private ImageButton ibtR;
    private ImageButton ibtScreenV;
    private ImageButton ibtSup;
    private boolean isOnDrag;
    private KingDrawView kingDrawView;
    private LinearLayout llRightTool;
    private List<ToolBaseItemView> menuItems;
    OnMenuClickListener onMenuClickListener;
    private PaletteRightInterface onRightMenuClickListener;
    private VerticalSelectItemView selectItem;
    private SupCollectView supCollectView;
    private TextView tvSup;
    
    public VerticalRightToolView(final Context context, final AttributeSet set) {
        super(context, set);
        this.menuItems = (List<ToolBaseItemView>)new ArrayList();
        this.onMenuClickListener = (OnMenuClickListener)new VerticalRightToolView$1(this);
        this.context = context;
        this.contentView = View.inflate(context, R.layout.view_vertical_other_tool, (ViewGroup)this);
        this.initView();
    }
    
    private void addMenuItem(final ToolBaseItemView toolBaseItemView) {
        toolBaseItemView.setOnMenuClickListener(this.onMenuClickListener);
        this.menuItems.add((Object)toolBaseItemView);
        this.layoutMenu();
    }
    
    private void addMenuItems(final List<ToolBaseItemView> list) {
        final Iterator iterator = list.iterator();
        while (iterator.hasNext()) {
            this.addMenuItem((ToolBaseItemView)iterator.next());
        }
    }
    
    private void checkGestureTool(final boolean b) {
        this.ibtGesture.setTag((Object)b);
        if (b) {
            this.ibtGesture.setBackgroundResource(R.drawable.drag_bg_check);
            this.ibtGesture.setImageResource(R.drawable.ic_draw_ic_gesture_sel);
        }
        else {
            this.ibtGesture.setBackgroundResource(R.drawable.drag_bg_nor);
            this.ibtGesture.setImageResource(R.drawable.ic_draw_ic_gesture_nor);
        }
    }
    
    private void initView() {
        this.ibtEncyclopedia = (ImageButton)this.contentView.findViewById(R.id.ibt_encyclopedia);
        this.ibtGesture = (ImageButton)this.contentView.findViewById(R.id.ibt_gesture);
        this.ibtDragMove = (ImageButton)this.contentView.findViewById(R.id.ibt_drag_move);
        this.ibtR = (ImageButton)this.contentView.findViewById(R.id.ibt_r);
        this.flSup = (FrameLayout)this.contentView.findViewById(R.id.fl_sup);
        this.ibtSup = (ImageButton)this.contentView.findViewById(R.id.ibt_sup);
        this.tvSup = (TextView)this.contentView.findViewById(R.id.tv_sup);
        this.ibtAi = (ImageButton)this.contentView.findViewById(R.id.ibt_ai);
        this.ibtScreenV = (ImageButton)this.contentView.findViewById(R.id.ibt_screen_v);
        this.llRightTool = (LinearLayout)this.contentView.findViewById(R.id.ll_right_tool);
        this.ibtEncyclopedia.setOnClickListener((View$OnClickListener)this);
        this.ibtGesture.setOnClickListener((View$OnClickListener)this);
        this.ibtDragMove.setOnClickListener((View$OnClickListener)this);
        this.ibtR.setOnClickListener((View$OnClickListener)this);
        this.ibtSup.setOnClickListener((View$OnClickListener)this);
        this.tvSup.setOnClickListener((View$OnClickListener)this);
        this.ibtGesture.setOnLongClickListener((View$OnLongClickListener)this);
        this.ibtAi.setOnClickListener((View$OnClickListener)this);
        this.ibtScreenV.setOnClickListener((View$OnClickListener)this);
    }
    
    private void restSup() {
        this.ibtSup.setVisibility(0);
        this.tvSup.setText((CharSequence)"");
        this.tvSup.setVisibility(8);
    }
    
    public void SetCanPaste() {
        for (int i = 0; i < this.menuItems.size(); ++i) {
            final ToolBaseItemView toolBaseItemView = (ToolBaseItemView)this.menuItems.get(i);
            if (toolBaseItemView instanceof CopyItemView) {
                final CopyItemView copyItemView = (CopyItemView)toolBaseItemView;
                copyItemView.setEnable(true);
                copyItemView.SetCanPaste();
            }
        }
    }
    
    public void checkRTool(final boolean b) {
        if (this.ibtR.getVisibility() == 0) {
            this.ibtR.setTag((Object)b);
            if (b) {
                this.ibtR.setBackgroundResource(R.drawable.drag_bg_check);
                this.ibtR.setImageResource(R.drawable.ic_r_check);
            }
            else {
                this.ibtR.setBackgroundResource(R.drawable.drag_bg_nor);
                this.ibtR.setImageResource(R.drawable.ic_r);
            }
        }
    }
    
    public void checkSelect() {
        this.selectItem.onSelected();
    }
    
    public void clearCheck() {
        this.checkGestureTool(false);
        this.setSelect(false);
        this.checkRTool(false);
        this.restSup();
        this.supCollectView.restSup();
    }
    
    protected List<ToolBaseItemView> initRightMenu() {
        final ArrayList list = new ArrayList();
        this.selectItem = new VerticalSelectItemView(this.context);
        final VerticalClearItemView verticalClearItemView = new VerticalClearItemView(this.context);
        ((ToolBaseItemView)verticalClearItemView).setKingDrawView(this.kingDrawView);
        final CopyItemView copyItemView = new CopyItemView(this.context);
        copyItemView.setKingDrawView(this.kingDrawView);
        if (ClipboardData.getInstance().hasClipData()) {
            copyItemView.enablePaste();
        }
        ((List)list).add((Object)this.selectItem);
        ((List)list).add((Object)verticalClearItemView);
        ((List)list).add((Object)copyItemView);
        return (List<ToolBaseItemView>)list;
    }
    
    public void layoutMenu() {
        this.llRightTool.removeAllViews();
        final float dimension = this.getContext().getResources().getDimension(R.dimen.home_tool_height);
        for (int i = 0; i < this.menuItems.size(); ++i) {
            final ToolBaseItemView toolBaseItemView = (ToolBaseItemView)this.menuItems.get(i);
            final int n = (int)dimension;
            final LinearLayout$LayoutParams linearLayout$LayoutParams = new LinearLayout$LayoutParams(n, n);
            final ViewGroup viewGroup = (ViewGroup)toolBaseItemView.getParent();
            if (viewGroup != null) {
                viewGroup.removeView((View)toolBaseItemView);
            }
            toolBaseItemView.contactPopupWindow((MoreMenuPop)null);
            this.llRightTool.addView((View)toolBaseItemView, (ViewGroup$LayoutParams)linearLayout$LayoutParams);
        }
    }
    
    public void onClick(final View view) {
        if (CheckDoubleClick.isFastDoubleClick(view)) {
            return;
        }
        if (view == this.ibtEncyclopedia) {
            final PaletteRightInterface onRightMenuClickListener = this.onRightMenuClickListener;
            if (onRightMenuClickListener != null) {
                onRightMenuClickListener.onEncyOnClick();
            }
            this.setOtherToolSelect();
            this.supCollectView.restSup();
        }
        else if (view == this.ibtGesture) {
            final PaletteRightInterface onRightMenuClickListener2 = this.onRightMenuClickListener;
            if (onRightMenuClickListener2 != null) {
                onRightMenuClickListener2.onGestureOnClick();
                this.checkGestureTool(true);
                this.setSelect(false);
                this.setOtherToolSelect();
                this.supCollectView.restSup();
                this.restSup();
            }
            this.checkRTool(false);
        }
        else if (view == this.ibtDragMove) {
            final PaletteRightInterface onRightMenuClickListener3 = this.onRightMenuClickListener;
            if (onRightMenuClickListener3 != null) {
                onRightMenuClickListener3.onMoveOnClick();
                this.checkGestureTool(false);
                this.setSelect(true);
                this.setOtherToolSelect();
                this.supCollectView.restSup();
                this.restSup();
            }
            this.checkRTool(false);
        }
        else if (view == this.ibtSup) {
            final PaletteRightInterface onRightMenuClickListener4 = this.onRightMenuClickListener;
            if (onRightMenuClickListener4 != null) {
                onRightMenuClickListener4.onSupClick();
            }
        }
        else if (view == this.tvSup) {
            final PaletteRightInterface onRightMenuClickListener5 = this.onRightMenuClickListener;
            if (onRightMenuClickListener5 != null) {
                onRightMenuClickListener5.onTextSupClick();
            }
        }
        else if (view == this.ibtR) {
            if (this.onRightMenuClickListener != null) {
                this.checkGestureTool(false);
                this.restSup();
                this.supCollectView.restSup();
                this.setSelect(false);
                this.checkRTool(true);
                this.setOtherToolSelect();
                this.onRightMenuClickListener.onRClick();
            }
        }
        else if (view == this.ibtScreenV) {
            if (this.onRightMenuClickListener != null) {
                this.checkGestureTool(false);
                this.restSup();
                this.supCollectView.restSup();
                this.setSelect(false);
                this.checkRTool(true);
                this.setOtherToolSelect();
                this.onRightMenuClickListener.onScreenToHorizontal();
            }
        }
        else if (view == this.ibtAi) {
            final PaletteRightInterface onRightMenuClickListener6 = this.onRightMenuClickListener;
            if (onRightMenuClickListener6 != null) {
                onRightMenuClickListener6.onSelectPic();
            }
        }
    }
    
    public void onDrag(final boolean b) {
        if (this.isOnDrag && !b) {
            this.isOnDrag = false;
            this.ibtDragMove.setImageResource(R.drawable.ic_drag_check);
        }
        else if (!this.isOnDrag && b) {
            this.isOnDrag = true;
            this.ibtDragMove.setImageResource(R.drawable.ic_move_status);
        }
    }
    
    public boolean onLongClick(final View view) {
        if (view == this.ibtGesture) {
            final PaletteRightInterface onRightMenuClickListener = this.onRightMenuClickListener;
            if (onRightMenuClickListener != null) {
                onRightMenuClickListener.onGestureOnLongClick();
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
            viewTreeObserver.addOnPreDrawListener((ViewTreeObserver$OnPreDrawListener)new ViewTreeObserver$OnPreDrawListener(this, viewTreeObserver) {
                final VerticalRightToolView this$0;
                final ViewTreeObserver val$vto;
                
                public boolean onPreDraw() {
                    if (this.this$0.tvSup.getLineCount() == 1) {
                        this.this$0.tvSup.setTextSize(14.0f);
                    }
                    else if (this.this$0.tvSup.getLineCount() == 2) {
                        this.this$0.tvSup.setTextSize(12.0f);
                    }
                    else {
                        this.this$0.tvSup.setTextSize(10.0f);
                    }
                    this.this$0.tvSup.setPadding(5, 5, 5, 5);
                    if (this.val$vto.isAlive()) {
                        this.val$vto.removeOnPreDrawListener((ViewTreeObserver$OnPreDrawListener)this);
                    }
                    return true;
                }
            });
        }
        else {
            final SpannableStringBuilder htmlToSpan = SpanUtil.htmlToSpan(gsGroupModel.getNameHtml());
            SpanUtil.setSpanFontSize(htmlToSpan, 20);
            this.tvSup.setText((CharSequence)htmlToSpan);
            final ViewTreeObserver viewTreeObserver2 = this.tvSup.getViewTreeObserver();
            viewTreeObserver2.addOnPreDrawListener((ViewTreeObserver$OnPreDrawListener)new ViewTreeObserver$OnPreDrawListener(this, htmlToSpan, viewTreeObserver2) {
                final VerticalRightToolView this$0;
                final SpannableStringBuilder val$spannable;
                final ViewTreeObserver val$vto;
                
                public boolean onPreDraw() {
                    if (this.this$0.tvSup.getLineCount() == 1) {
                        SpanUtil.setSpanFontSize(this.val$spannable, 20);
                    }
                    else if (this.this$0.tvSup.getLineCount() == 2) {
                        SpanUtil.setSpanFontSize(this.val$spannable, 18);
                    }
                    else {
                        SpanUtil.setSpanFontSize(this.val$spannable, 16);
                    }
                    this.this$0.tvSup.setPadding(5, 5, 5, 5);
                    this.this$0.tvSup.setText((CharSequence)this.val$spannable);
                    if (this.val$vto.isAlive()) {
                        this.val$vto.removeOnPreDrawListener((ViewTreeObserver$OnPreDrawListener)this);
                    }
                    return true;
                }
            });
        }
    }
    
    public void setKingDrawView(final KingDrawView kingDrawView) {
        this.kingDrawView = kingDrawView;
        this.addMenuItems(this.initRightMenu());
    }
    
    public void setOnRightMenuClickListener(final PaletteRightInterface onRightMenuClickListener) {
        this.onRightMenuClickListener = onRightMenuClickListener;
    }
    
    public void setOnToolChangeListener(final OnToolChangeListener onToolChangeListener) {
        final Iterator iterator = this.menuItems.iterator();
        while (iterator.hasNext()) {
            ((ToolBaseItemView)iterator.next()).setOnToolChangeListener(onToolChangeListener);
        }
    }
    
    public void setOnToolHintListener(final OnToolHintListener onToolHintListener) {
        final Iterator iterator = this.menuItems.iterator();
        while (iterator.hasNext()) {
            ((ToolBaseItemView)iterator.next()).setOnToolHintListener(onToolHintListener);
        }
    }
    
    public void setOtherToolSelect() {
        for (final ToolBaseItemView toolBaseItemView : this.menuItems) {
            if (toolBaseItemView.isCheckOnly()) {
                toolBaseItemView.setCheck(false);
            }
        }
    }
    
    public void setPaletteType(final int n) {
        if (n == ToolDataManage.TYPE_BASE) {
            this.flSup.setVisibility(0);
            this.ibtR.setVisibility(8);
            this.ibtEncyclopedia.setVisibility(0);
            this.ibtAi.setVisibility(0);
            this.ibtScreenV.setVisibility(0);
        }
        else if (n == ToolDataManage.TYPE_SUP) {
            this.flSup.setVisibility(8);
            this.ibtR.setVisibility(0);
            this.ibtEncyclopedia.setVisibility(8);
            this.ibtScreenV.setVisibility(8);
            this.ibtAi.setVisibility(8);
        }
        else if (n == ToolDataManage.TYPE_SEARCH) {
            this.flSup.setVisibility(0);
            this.ibtR.setVisibility(8);
            this.ibtEncyclopedia.setVisibility(8);
            this.ibtScreenV.setVisibility(8);
            this.ibtAi.setVisibility(8);
        }
    }
    
    public void setPicAiShow(final boolean b) {
        if (b) {
            this.ibtAi.setVisibility(0);
        }
        else {
            this.ibtAi.setVisibility(8);
        }
    }
    
    public void setScreenShow(final boolean b) {
        if (b) {
            this.ibtScreenV.setVisibility(8);
        }
        else {
            this.ibtScreenV.setVisibility(0);
        }
    }
    
    public void setSelect(final boolean b) {
        this.ibtDragMove.setTag((Object)b);
        if (b) {
            this.ibtDragMove.setImageResource(R.drawable.ic_drag_check);
        }
        else {
            this.ibtDragMove.setImageResource(R.drawable.ic_drag_nor);
        }
    }
    
    public void setSupView(final SupCollectView supCollectView) {
        this.supCollectView = supCollectView;
    }
    
    public void unEnableCopy() {
        for (int i = 0; i < this.menuItems.size(); ++i) {
            final ToolBaseItemView toolBaseItemView = (ToolBaseItemView)this.menuItems.get(i);
            if (toolBaseItemView instanceof CopyItemView) {
                final CopyItemView copyItemView = (CopyItemView)toolBaseItemView;
                copyItemView.setEnable(false);
                copyItemView.setCheck(false);
            }
        }
    }
}

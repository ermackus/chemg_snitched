package com.kingagroot.component.ui.widget.richinput.view;

import java.util.Collection;
import com.kingagroot.component.ui.widget.richinput.span.BaseSpan;
import com.kingagroot.component.ui.R$drawable;
import com.kingagroot.kingdraw.core.utils.GDensityUtil;
import android.graphics.Canvas;
import java.util.Iterator;
import android.view.View$OnClickListener;
import java.util.ArrayList;
import android.util.AttributeSet;
import android.content.Context;
import java.util.List;
import android.graphics.drawable.Drawable;
import androidx.appcompat.widget.AppCompatImageButton;

public class GStyleButton extends AppCompatImageButton implements BaseStyleView
{
    private BaseRichEditor baseRichEditor;
    private Drawable buttonDrawable;
    private int checkResId;
    boolean isCheck;
    private final List<BaseStyleView$OnClickListener> onClickListener;
    private final List<BaseStyleView> toggleViews;
    private int unChecnkResId;
    
    public GStyleButton(final Context context) {
        this(context, null);
    }
    
    public GStyleButton(final Context context, final AttributeSet set) {
        super(context, set);
        this.onClickListener = (List<BaseStyleView$OnClickListener>)new ArrayList();
        this.toggleViews = (List<BaseStyleView>)new ArrayList();
        this.setBackgroundColor(0);
        this.setOnClickListener((View$OnClickListener)new GStyleButton$1(this));
    }
    
    private boolean toggleViewsHasCheck() {
        final Iterator iterator = this.toggleViews.iterator();
        while (iterator.hasNext()) {
            if (((BaseStyleView)iterator.next()).isCheck()) {
                return true;
            }
        }
        return false;
    }
    
    public void addTogglevView(final BaseStyleView baseStyleView) {
        if (baseStyleView != null) {
            this.toggleViews.add((Object)baseStyleView);
        }
    }
    
    public List<BaseStyleView$OnClickListener> getOnClickListener() {
        return this.onClickListener;
    }
    
    public boolean isCheck() {
        return this.isCheck;
    }
    
    protected void onDraw(final Canvas canvas) {
        super.onDraw(canvas);
        if (this.buttonDrawable != null) {
            final int dp2px = GDensityUtil.dp2px(40.0f);
            final int dp2px2 = GDensityUtil.dp2px(28.0f);
            final int n = (dp2px - dp2px2) / 2;
            final int n2 = dp2px2 + n;
            this.buttonDrawable.setBounds(n, n, n2, n2);
            this.buttonDrawable.draw(canvas);
        }
    }
    
    public void setBaseRichEditor(final BaseRichEditor baseRichEditor) {
        this.baseRichEditor = baseRichEditor;
    }
    
    public void setCheck(final boolean isCheck) {
        this.isCheck = isCheck;
        if (isCheck) {
            this.setBackgroundResource(R$drawable.tool_bg_sel);
            this.buttonDrawable = this.getContext().getResources().getDrawable(this.checkResId);
        }
        else {
            this.setBackgroundColor(0);
            this.buttonDrawable = this.getContext().getResources().getDrawable(this.unChecnkResId);
        }
    }
    
    public void setCheckResId(final int checkResId) {
        this.checkResId = checkResId;
    }
    
    public void setOnClickListener(final BaseStyleView$OnClickListener baseStyleView$OnClickListener) {
        this.onClickListener.add((Object)baseStyleView$OnClickListener);
    }
    
    public void setStyleClass(final Class<? extends BaseSpan> clazz) {
    }
    
    public void setToggleViews(final List<BaseStyleView> list) {
        if (list != null) {
            this.toggleViews.addAll((Collection)list);
        }
    }
    
    public void setUnChecnkResId(final int unChecnkResId) {
        this.unChecnkResId = unChecnkResId;
    }
}

package com.kingagroot.component.ui.widget.richinput.view;

import com.kingagroot.component.ui.R$drawable;
import com.kingagroot.kingdraw.core.utils.GDensityUtil;
import android.graphics.Canvas;
import android.view.View$OnClickListener;
import java.util.ArrayList;
import android.util.AttributeSet;
import android.content.Context;
import com.kingagroot.component.ui.widget.richinput.span.BaseSpan;
import java.util.List;
import android.graphics.drawable.Drawable;
import androidx.appcompat.widget.AppCompatImageButton;

public class GBIStyleButton extends AppCompatImageButton implements BaseStyleView
{
    private BaseRichEditor baseRichEditor;
    private Drawable buttonDrawable;
    private int checkResId;
    boolean isCheck;
    private final List<BaseStyleView$OnClickListener> onClickListener;
    private Class<? extends BaseSpan> styleClass;
    private int unChecnkResId;
    
    public GBIStyleButton(final Context context) {
        this(context, null);
    }
    
    public GBIStyleButton(final Context context, final AttributeSet set) {
        super(context, set);
        this.onClickListener = (List<BaseStyleView$OnClickListener>)new ArrayList();
        this.setBackgroundColor(0);
        this.setOnClickListener((View$OnClickListener)new GBIStyleButton$1(this));
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
    
    public void setStyleClass(final Class<? extends BaseSpan> styleClass) {
        this.styleClass = styleClass;
    }
    
    public void setUnChecnkResId(final int unChecnkResId) {
        this.unChecnkResId = unChecnkResId;
    }
}

package com.kingagroot.component.ui.widget.richinput.style;

import com.kingagroot.component.ui.widget.richinput.span.BaseSpan;
import java.lang.reflect.ParameterizedType;
import android.text.Editable;
import com.kingagroot.component.ui.widget.richinput.view.BaseStyleView$OnClickListener;
import com.kingagroot.component.ui.widget.richinput.view.BaseStyleView;

public abstract class MetricAffectingStyle<E> implements BaseStyle<E>
{
    protected BaseStyleView baseStyleView;
    protected boolean isCheck;
    private final BaseStyleView$OnClickListener onClickListener;
    protected Class<E> styleClass;
    
    public MetricAffectingStyle() {
        this.onClickListener = (BaseStyleView$OnClickListener)new BaseStyleView$OnClickListener() {
            final MetricAffectingStyle this$0;
            
            public void onClick(final Editable editable, final int n, final int n2) {
                this.this$0.applyStyle(editable, n, n2);
            }
        };
        this.styleClass = (Class)((ParameterizedType)this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }
    
    public MetricAffectingStyle(final BaseStyleView baseStyleView) {
        this.onClickListener = (BaseStyleView$OnClickListener)new BaseStyleView$OnClickListener() {
            final MetricAffectingStyle this$0;
            
            public void onClick(final Editable editable, final int n, final int n2) {
                this.this$0.applyStyle(editable, n, n2);
            }
        };
        this.baseStyleView = baseStyleView;
        this.styleClass = (Class)((ParameterizedType)this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        if (baseStyleView != null) {
            baseStyleView.setOnClickListener(this.onClickListener);
        }
    }
    
    public void addSpan(final Editable editable, final int n, final int n2) {
        if (n < n2) {
            final Object[] spans = editable.getSpans(n, n2, (Class)this.styleClass);
            for (int length = spans.length, i = 0; i < length; ++i) {
                this.removeMarginSapan(editable, n, n2, (BaseSpan)spans[i]);
            }
            this.checkAndMergeSpan(editable, n, n2, this.styleClass);
        }
    }
    
    public void applyStyle(final Editable editable, final int n, final int n2) {
        if (this.isCheck()) {
            this.addSpan(editable, n, n2);
        }
        else {
            this.removeSpan(editable, n, n2);
        }
        this.clearEmptySpan(editable, n, n2);
    }
    
    protected void checkAndMergeSpan(final Editable editable, final int n, final int n2, final Class<E> clazz) {
        final Object[] spans = editable.getSpans(n, n, (Class)clazz);
        final int length = spans.length;
        final Object o = null;
        Object o2;
        if (length > 0) {
            o2 = spans[0];
        }
        else {
            o2 = null;
        }
        final Object[] spans2 = editable.getSpans(n2, n2, (Class)clazz);
        Object o3 = o;
        if (spans2.length > 0) {
            o3 = spans2[0];
        }
        final int spanStart = editable.getSpanStart(o2);
        final int spanEnd = editable.getSpanEnd(o3);
        if (o2 != null && o3 != null) {
            final Object creatSpan = this.creatSpan();
            editable.removeSpan(o2);
            editable.removeSpan(o3);
            editable.setSpan(creatSpan, spanStart, spanEnd, 34);
        }
        else if (o2 != null && o3 == null) {
            editable.removeSpan(o2);
            editable.setSpan(this.creatSpan(), spanStart, n2, 34);
        }
        else if (o2 == null && o3 != null) {
            editable.removeSpan(o3);
            editable.setSpan(this.creatSpan(), n, spanEnd, 34);
        }
        else {
            editable.setSpan(this.creatSpan(), n, n2, 34);
        }
    }
    
    protected void clearEmptySpan(final Editable editable, int i, int length) {
        final Object[] spans = editable.getSpans(i, length, (Class)this.styleClass);
        if (spans != null && spans.length > 0) {
            Object o;
            for (length = spans.length, i = 0; i < length; ++i) {
                o = spans[i];
                if (o != null && editable.getSpanStart(o) >= editable.getSpanEnd(o)) {
                    editable.removeSpan(o);
                }
            }
        }
    }
    
    abstract E creatSpan();
    
    public BaseStyleView getView() {
        return this.baseStyleView;
    }
    
    public boolean isCheck() {
        final BaseStyleView baseStyleView = this.baseStyleView;
        if (baseStyleView != null) {
            return baseStyleView.isCheck();
        }
        return this.isCheck;
    }
    
    protected void removeMarginSapan(final Editable editable, final int n, final int n2, final BaseSpan baseSpan) {
        final int spanStart = editable.getSpanStart((Object)baseSpan);
        final int spanEnd = editable.getSpanEnd((Object)baseSpan);
        if (n > spanStart && n2 >= spanEnd) {
            editable.removeSpan((Object)baseSpan);
            editable.setSpan((Object)baseSpan.newInstance(), spanStart, n, 34);
        }
        else if (n > spanStart && n2 < spanEnd) {
            editable.removeSpan((Object)baseSpan);
            editable.setSpan((Object)baseSpan.newInstance(), spanStart, n, 34);
            editable.setSpan((Object)baseSpan.newInstance(), n2, spanEnd, 34);
        }
        else if (n <= spanStart && n2 >= spanEnd) {
            editable.removeSpan((Object)baseSpan);
        }
        else if (n == spanStart && n2 < spanEnd) {
            editable.removeSpan((Object)baseSpan);
            editable.setSpan((Object)baseSpan.newInstance(), n2, spanEnd, 34);
        }
        else if (n < spanStart && n2 < spanEnd) {
            editable.removeSpan((Object)baseSpan);
            editable.setSpan((Object)baseSpan.newInstance(), n2, spanEnd, 34);
        }
    }
    
    public void removeSpan(final Editable editable, final int n, final int n2) {
        int i = 0;
        if (n2 > n) {
            final Object[] spans = editable.getSpans(n, n2, (Class)this.styleClass);
            if (spans.length > 0) {
                while (i < spans.length) {
                    final Object o = spans[i];
                    if (o != null) {
                        final int spanStart = editable.getSpanStart(o);
                        final int spanEnd = editable.getSpanEnd(o);
                        if (n >= spanEnd) {
                            editable.removeSpan(o);
                            editable.setSpan(o, spanStart, n - 1, 34);
                        }
                        else if (n > spanStart && n2 >= spanEnd) {
                            editable.removeSpan(o);
                            editable.setSpan(this.creatSpan(), spanStart, n, 34);
                        }
                        else if (n > spanStart && n2 < spanEnd) {
                            editable.removeSpan(o);
                            editable.setSpan(this.creatSpan(), spanStart, n, 34);
                            editable.setSpan(this.creatSpan(), n2, spanEnd, 34);
                        }
                        else if (n <= spanStart && n2 >= spanEnd) {
                            editable.removeSpan(o);
                        }
                        else if (n == spanStart && n2 < spanEnd) {
                            editable.removeSpan(o);
                            editable.setSpan(this.creatSpan(), n2, spanEnd, 34);
                        }
                        else if (n < spanStart && n2 < spanEnd) {
                            editable.removeSpan(o);
                            editable.setSpan(this.creatSpan(), n2, spanEnd, 34);
                        }
                    }
                    ++i;
                }
            }
        }
        else if (n2 != n) {
            final Object[] spans2 = editable.getSpans(n, n2, (Class)this.styleClass);
            if (spans2.length > 0) {
                final Object o2 = spans2[0];
                if (o2 != null) {
                    if (editable.getSpanStart(o2) < editable.getSpanEnd(o2)) {
                        this.setCheck(true);
                    }
                }
            }
        }
    }
    
    public void setCheck(final boolean b) {
        final BaseStyleView baseStyleView = this.baseStyleView;
        if (baseStyleView != null) {
            baseStyleView.setCheck(b);
        }
        this.isCheck = b;
    }
}

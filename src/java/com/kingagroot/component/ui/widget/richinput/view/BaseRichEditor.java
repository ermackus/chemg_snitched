package com.kingagroot.component.ui.widget.richinput.view;

import com.kingagroot.component.ui.widget.richinput.GAlignEnum;
import android.text.Spanned;
import com.kingagroot.component.ui.widget.richinput.html.Html;
import android.text.Editable;
import android.util.AttributeSet;
import android.content.Context;
import com.kingagroot.component.ui.widget.richinput.StyleBaseManager;
import android.text.TextWatcher;
import androidx.appcompat.widget.AppCompatEditText;

public abstract class BaseRichEditor extends AppCompatEditText implements TextWatcher
{
    public int MaxLength;
    int end;
    protected boolean richEnable;
    int start;
    protected StyleBaseManager styleBaseManager;
    
    public BaseRichEditor(final Context context) {
        this(context, null);
    }
    
    public BaseRichEditor(final Context context, final AttributeSet set) {
        super(context, set);
        this.MaxLength = -1;
        this.styleBaseManager = this.getStyleManager();
        this.addTextChangedListener((TextWatcher)this);
        this.setInputType(524289);
        this.setRawInputType(524289);
    }
    
    public void addTextChangedListener() {
        this.addTextChangedListener((TextWatcher)this);
    }
    
    public void afterTextChanged(final Editable editable) {
        if (this.richEnable) {
            this.styleBaseManager.applyStyle(editable, this.start, this.end);
        }
    }
    
    public void beforeTextChanged(final CharSequence charSequence, final int n, final int n2, final int n3) {
    }
    
    public CharSequence fromHtml(final String s) {
        final Spanned spanned = Html.fromHtml(s).getSpanned();
        this.setText((CharSequence)spanned);
        return (CharSequence)spanned;
    }
    
    public abstract StyleBaseManager getStyleManager();
    
    protected void onSelectionChanged(final int n, final int n2) {
        super.onSelectionChanged(n, n2);
        if (this.richEnable) {
            final StyleBaseManager styleBaseManager = this.styleBaseManager;
            if (styleBaseManager != null) {
                styleBaseManager.onSelectionChanged(n, n2);
            }
        }
    }
    
    public void onTextChanged(final CharSequence charSequence, final int start, final int n, final int n2) {
        this.start = start;
        this.end = start + n2;
    }
    
    public void removeTextChangedListener() {
        this.removeTextChangedListener((TextWatcher)this);
    }
    
    protected void replaceSelectContent() {
    }
    
    public void setMaxLength(final int maxLength) {
        this.MaxLength = maxLength;
    }
    
    public void setRichEnable(final boolean richEnable) {
        this.richEnable = richEnable;
    }
    
    public String toHtml(final GAlignEnum gAlignEnum) {
        return Html.toHtml((Spanned)this.getText(), gAlignEnum);
    }
    
    public abstract void upSeletionController();
}

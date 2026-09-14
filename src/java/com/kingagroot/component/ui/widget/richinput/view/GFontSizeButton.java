package com.kingagroot.component.ui.widget.richinput.view;

import android.widget.TextView$BufferType;
import com.kingagroot.component.ui.widget.richinput.span.BaseSpan;
import java.util.Iterator;
import android.text.Editable;
import android.util.AttributeSet;
import java.util.ArrayList;
import android.content.Context;
import java.util.List;
import androidx.appcompat.widget.AppCompatTextView;

public class GFontSizeButton extends AppCompatTextView implements BaseStyleView
{
    BaseRichEditor baseRichEditor;
    boolean isCheck;
    List<BaseStyleView$OnClickListener> onClickListener;
    private GFontSizeButton.GFontSizeButton$OnFontSizeChangeListner onFontSizeChangeListner;
    int size;
    
    public GFontSizeButton(final Context context) {
        super(context);
        this.isCheck = true;
        this.onClickListener = (List<BaseStyleView$OnClickListener>)new ArrayList();
        this.size = 10;
    }
    
    public GFontSizeButton(final Context context, final AttributeSet set) {
        super(context, set);
        this.isCheck = true;
        this.onClickListener = (List<BaseStyleView$OnClickListener>)new ArrayList();
        this.setSize(this.size = 10);
    }
    
    public void applySize() {
        final Editable editableText = this.baseRichEditor.getEditableText();
        final int selectionStart = this.baseRichEditor.getSelectionStart();
        final int selectionEnd = this.baseRichEditor.getSelectionEnd();
        final Iterator iterator = this.onClickListener.iterator();
        while (iterator.hasNext()) {
            ((BaseStyleView$OnClickListener)iterator.next()).onClick(editableText, selectionStart, selectionEnd);
        }
    }
    
    public List<BaseStyleView$OnClickListener> getOnClickListener() {
        return this.onClickListener;
    }
    
    public int getSize() {
        return this.size;
    }
    
    public boolean isCheck() {
        return true;
    }
    
    public void setBaseRichEditor(final BaseRichEditor baseRichEditor) {
        this.baseRichEditor = baseRichEditor;
    }
    
    public void setCheck(final boolean isCheck) {
        this.isCheck = isCheck;
    }
    
    public void setOnClickListener(final BaseStyleView$OnClickListener baseStyleView$OnClickListener) {
        this.onClickListener.add((Object)baseStyleView$OnClickListener);
    }
    
    public void setOnFontSizeChangeListner(final GFontSizeButton.GFontSizeButton$OnFontSizeChangeListner onFontSizeChangeListner) {
        this.onFontSizeChangeListner = onFontSizeChangeListner;
    }
    
    public void setSize(final int size) {
        this.size = size;
        this.setText((CharSequence)String.valueOf(size));
    }
    
    public void setStyleClass(final Class<? extends BaseSpan> clazz) {
    }
    
    public void setText(CharSequence charSequence, final TextView$BufferType textView$BufferType) {
        try {
            final int intValue = Integer.valueOf(charSequence.toString());
            if (intValue == 0) {
                charSequence = (CharSequence)"";
            }
            this.size = intValue;
            if (this.onFontSizeChangeListner != null) {
                this.onFontSizeChangeListner.onChange(intValue);
            }
            super.setText(charSequence, textView$BufferType);
        }
        catch (final NumberFormatException ex) {
            ex.printStackTrace();
        }
    }
}

package com.kingagroot.component.ui.widget.richinput.view;

import android.widget.TextView$BufferType;
import com.kingagroot.component.ui.widget.richinput.span.BaseSpan;
import android.graphics.Typeface;
import com.goodsrc.library.utils.StringUtils;
import java.util.Iterator;
import android.text.Editable;
import java.util.ArrayList;
import android.util.AttributeSet;
import android.content.Context;
import com.kingagroot.component.ui.widget.richinput.RichConfig;
import java.util.List;
import androidx.appcompat.widget.AppCompatTextView;

public class GFontFamilyButton extends AppCompatTextView implements BaseStyleView
{
    private static final String family;
    BaseRichEditor baseRichEditor;
    boolean isCheck;
    List<BaseStyleView$OnClickListener> onClickListener;
    private GFontFamilyButton.GFontFamilyButton$OnFamilyChangeListner onFamilyChangeListner;
    private final String path;
    
    static {
        family = RichConfig.DEFAULT_FONT_FAMILY;
    }
    
    public GFontFamilyButton(final Context context) {
        this(context, null);
    }
    
    public GFontFamilyButton(final Context context, final AttributeSet set) {
        super(context, set);
        this.isCheck = true;
        this.onClickListener = (List<BaseStyleView$OnClickListener>)new ArrayList();
        this.path = "fonts/%s.ttf";
        this.setFamily(GFontFamilyButton.family);
    }
    
    public void applyFamily() {
        final Editable editableText = this.baseRichEditor.getEditableText();
        final int selectionStart = this.baseRichEditor.getSelectionStart();
        final int selectionEnd = this.baseRichEditor.getSelectionEnd();
        final Iterator iterator = this.onClickListener.iterator();
        while (iterator.hasNext()) {
            ((BaseStyleView$OnClickListener)iterator.next()).onClick(editableText, selectionStart, selectionEnd);
        }
    }
    
    public String getFamily() {
        return this.getText().toString();
    }
    
    public List<BaseStyleView$OnClickListener> getOnClickListener() {
        return this.onClickListener;
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
    
    public void setFamily(final String text) {
        try {
            this.setTypeface(Typeface.createFromAsset(this.getContext().getAssets(), StringUtils.format("fonts/%s.ttf", new Object[] { text })));
        }
        catch (final RuntimeException ex) {
            ex.printStackTrace();
        }
        this.setText((CharSequence)text);
    }
    
    public void setOnClickListener(final BaseStyleView$OnClickListener baseStyleView$OnClickListener) {
        this.onClickListener.add((Object)baseStyleView$OnClickListener);
    }
    
    public void setOnFamilyChangeListner(final GFontFamilyButton.GFontFamilyButton$OnFamilyChangeListner onFamilyChangeListner) {
        this.onFamilyChangeListner = onFamilyChangeListner;
    }
    
    public void setStyleClass(final Class<? extends BaseSpan> clazz) {
    }
    
    public void setText(final CharSequence charSequence, final TextView$BufferType textView$BufferType) {
        super.setText(charSequence, textView$BufferType);
        final GFontFamilyButton.GFontFamilyButton$OnFamilyChangeListner onFamilyChangeListner = this.onFamilyChangeListner;
        if (onFamilyChangeListner != null) {
            onFamilyChangeListner.onChange(charSequence.toString());
        }
    }
}

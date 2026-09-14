package com.kingagroot.component.ui.widget.richinput.view;

import android.view.ContextMenu;
import android.app.Activity;
import com.kingagroot.component.ui.widget.richinput.GStyleManager;
import com.kingagroot.component.ui.widget.richinput.StyleBaseManager;
import com.kingagroot.component.ui.widget.richinput.SelectableSelector.SelectionInfo;
import android.text.Editable;
import java.util.List;
import com.kingagroot.component.ui.widget.richinput.GStyleAutoManager;
import android.view.MotionEvent;
import android.view.View;
import com.kingagroot.component.ui.R$color;
import android.widget.EditText;
import com.kingagroot.component.ui.widget.richinput.SelectableSelector.SelectableTextHelper$Builder;
import com.kingagroot.component.ui.widget.richinput.SelectableSelector.SelectMenu$SelectMenuListner;
import java.lang.reflect.Field;
import android.widget.TextView;
import android.text.TextUtils;
import android.content.ClipData;
import android.os.Build$VERSION;
import android.content.ClipboardManager;
import com.kingagroot.component.ui.widget.richinput.SpanUtil;
import android.text.Spanned;
import com.kingagroot.component.ui.widget.richinput.html.Html;
import com.kingagroot.component.ui.widget.richinput.GAlignEnum;
import android.text.SpannableStringBuilder;
import java.util.Locale;
import android.util.AttributeSet;
import android.content.Context;
import com.kingagroot.component.ui.widget.richinput.SelectableSelector.SelectableTextHelper;
import com.kingagroot.component.ui.widget.richinput.SelectableSelector.SelectMenu;
import android.view.ActionMode$Callback;

public class RichEditor extends BaseRichEditor
{
    private final ActionMode$Callback callback;
    private String content;
    public SelectMenu selectMenu;
    private SelectableTextHelper selectableTextHelper;
    
    public RichEditor(final Context context) {
        super(context);
        this.callback = (ActionMode$Callback)new RichEditor$2(this);
    }
    
    public RichEditor(final Context context, final AttributeSet set) {
        super(context, set);
        this.callback = (ActionMode$Callback)new RichEditor$2(this);
        this.setTextSize(2, 14.0f);
        this.setCustomSelectionActionModeCallback(this.callback);
        this.setTextLocale(Locale.ENGLISH);
        this.init();
    }
    
    private void clipboardCopy() {
        final int selectionStart = this.getSelectionStart();
        final int selectionEnd = this.getSelectionEnd();
        final SpannableStringBuilder spannableStringBuilder = (SpannableStringBuilder)this.getEditableText().subSequence(Math.max(0, Math.min(selectionStart, selectionEnd)), Math.max(0, Math.max(selectionStart, selectionEnd)));
        final String html = Html.toHtml((Spanned)spannableStringBuilder, GAlignEnum.left);
        final String spanToString = SpanUtil.SpanToString(spannableStringBuilder);
        final ClipboardManager clipboardManager = (ClipboardManager)this.getContext().getSystemService("clipboard");
        ClipData primaryClip;
        if (Build$VERSION.SDK_INT >= 16) {
            primaryClip = ClipData.newHtmlText((CharSequence)null, (CharSequence)spanToString, html);
        }
        else {
            primaryClip = ClipData.newPlainText((CharSequence)null, (CharSequence)spanToString);
        }
        clipboardManager.setPrimaryClip(primaryClip);
    }
    
    private void clipboardPaste() {
        final ClipData primaryClip = ((ClipboardManager)this.getContext().getSystemService("clipboard")).getPrimaryClip();
        if (primaryClip == null) {
            return;
        }
        String s = primaryClip.getItemAt(0).getHtmlText();
        if (TextUtils.isEmpty((CharSequence)s) || !s.startsWith("<span") || !s.endsWith("</span>")) {
            final CharSequence text = primaryClip.getItemAt(0).getText();
            if (text == null) {
                return;
            }
            s = text.toString();
        }
        this.paste(s);
    }
    
    private void enableInsertion(final boolean b) {
        try {
            final Field declaredField = TextView.class.getDeclaredField("mEditor");
            declaredField.setAccessible(true);
            final Object value = declaredField.get((Object)this);
            final Field declaredField2 = Class.forName("android.widget.Editor").getDeclaredField("mInsertionControllerEnabled");
            declaredField2.setAccessible(true);
            declaredField2.set(value, (Object)b);
            declaredField2.setAccessible(false);
            declaredField.setAccessible(false);
        }
        catch (final Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private void init() {
        (this.selectMenu = new SelectMenu((TextView)this)).setSelectMenuListner((SelectMenu$SelectMenuListner)new RichEditor$1(this));
        this.selectableTextHelper = new SelectableTextHelper$Builder((EditText)this).setCursorHandleSizeInDp(20.0f).setCursorHandleColor(this.getResources().getColor(R$color.colorRed)).setSelectMenu(this.selectMenu).build();
    }
    
    private boolean isEditTextView(final View view, final MotionEvent motionEvent) {
        boolean b2;
        final boolean b = b2 = false;
        if (view != null) {
            b2 = b;
            if (view == this) {
                final int[] array2;
                final int[] array = array2 = new int[2];
                array2[1] = (array2[0] = 0);
                view.getLocationInWindow(array);
                final int n = array[0];
                final int n2 = array[1];
                final int height = view.getHeight();
                final int width = view.getWidth();
                b2 = b;
                if (motionEvent.getX() > n) {
                    b2 = b;
                    if (motionEvent.getX() < width + n) {
                        b2 = b;
                        if (motionEvent.getY() > n2) {
                            b2 = b;
                            if (motionEvent.getY() < height + n2) {
                                b2 = true;
                            }
                        }
                    }
                }
            }
        }
        return b2;
    }
    
    private void pasteBuilder(final SpannableStringBuilder spannableStringBuilder, final int n, final SpannableStringBuilder spannableStringBuilder2) {
        if (spannableStringBuilder2 == null) {
            return;
        }
        final GStyleAutoManager gStyleAutoManager = new GStyleAutoManager();
        for (int i = 0; i < spannableStringBuilder2.length(); ++i) {
            final List drawSpan = SpanUtil.getDrawSpan(spannableStringBuilder2, i);
            if (this.MaxLength != -1 && n + i >= this.MaxLength) {
                break;
            }
            final int n2 = n + i;
            gStyleAutoManager.applyStyle(spannableStringBuilder, n2, n2 + 1, drawSpan);
        }
    }
    
    public void SelectMenuDismiss() {
        final SelectableTextHelper selectableTextHelper = this.selectableTextHelper;
        if (selectableTextHelper != null) {
            selectableTextHelper.hideSelectView();
        }
    }
    
    @Override
    public void afterTextChanged(final Editable editable) {
        super.afterTextChanged(editable);
        if (this.content.length() > editable.toString().length()) {
            final SelectableTextHelper selectableTextHelper = this.selectableTextHelper;
            if (selectableTextHelper != null) {
                selectableTextHelper.destroy();
            }
        }
        final SelectableTextHelper selectableTextHelper2 = this.selectableTextHelper;
        if (selectableTextHelper2 != null) {
            selectableTextHelper2.cancelInsertCursor();
            this.selectableTextHelper.cancelSelectMenu();
        }
    }
    
    @Override
    public void beforeTextChanged(final CharSequence charSequence, final int n, final int n2, final int n3) {
        final SelectableTextHelper selectableTextHelper = this.selectableTextHelper;
        if (selectableTextHelper != null) {
            final SelectionInfo selectionInfo = selectableTextHelper.getSelectionInfo();
            if (n2 != 0 && n3 != 0 && selectionInfo.mEnd != selectionInfo.mStart) {
                if (this.richEnable && this.styleBaseManager != null) {
                    this.styleBaseManager.onSelectionChanged(n, n);
                }
                this.replaceSelectContent();
            }
        }
        this.content = charSequence.toString();
    }
    
    @Override
    public StyleBaseManager getStyleManager() {
        if (this.styleBaseManager == null) {
            this.styleBaseManager = (StyleBaseManager)new GStyleManager((BaseRichEditor)this);
        }
        return this.styleBaseManager;
    }
    
    public void keyBoardEvent(final MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0 && this.isEditTextView(((Activity)this.getContext()).getCurrentFocus(), motionEvent)) {
            this.enableInsertion(false);
        }
    }
    
    protected void onCreateContextMenu(final ContextMenu contextMenu) {
    }
    
    public void paste(final String s) {
        final boolean b = !TextUtils.isEmpty((CharSequence)s) && s.startsWith("<span") && s.endsWith("</span>");
        final int selectionStart = this.getSelectionStart();
        final int selectionEnd = this.getSelectionEnd();
        final int max = Math.max(0, Math.min(selectionStart, selectionEnd));
        final int max2 = Math.max(0, Math.max(selectionStart, selectionEnd));
        if (b) {
            final SpannableStringBuilder spannableStringBuilder = (SpannableStringBuilder)Html.fromHtml(s).getSpanned();
            this.setRichEnable(false);
            Editable text;
            if (max == max2) {
                text = this.getText().insert(max, (CharSequence)spannableStringBuilder);
            }
            else {
                text = this.getText().replace(max, max2, (CharSequence)spannableStringBuilder);
            }
            int maxLength;
            final int n = maxLength = spannableStringBuilder.length() + max;
            if (this.MaxLength != -1 && (maxLength = n) > this.MaxLength) {
                maxLength = this.MaxLength;
            }
            this.pasteBuilder((SpannableStringBuilder)text, max, spannableStringBuilder);
            this.setText((CharSequence)text);
            this.setSelection(maxLength);
            if (this.styleBaseManager != null) {
                this.styleBaseManager.onSelectionChanged(maxLength, maxLength);
            }
            this.setRichEnable(true);
        }
        else {
            final Editable replace = this.getText().replace(max, max2, (CharSequence)s);
            int maxLength2;
            final int n2 = maxLength2 = s.length() + max;
            if (this.MaxLength != -1 && (maxLength2 = n2) > this.MaxLength) {
                maxLength2 = this.MaxLength;
            }
            this.styleBaseManager.applyStyle(replace, max, maxLength2);
            this.setSelection(maxLength2);
        }
    }
    
    @Override
    protected void replaceSelectContent() {
        final SelectableTextHelper selectableTextHelper = this.selectableTextHelper;
        if (selectableTextHelper != null) {
            selectableTextHelper.destroy();
        }
    }
    
    @Override
    public void upSeletionController() {
        final SelectableTextHelper selectableTextHelper = this.selectableTextHelper;
        if (selectableTextHelper != null) {
            selectableTextHelper.updateCursorHandle();
        }
    }
}

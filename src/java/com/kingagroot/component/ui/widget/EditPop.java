package com.kingagroot.component.ui.widget;

import android.graphics.Rect;
import android.app.Activity;
import android.view.WindowManager$LayoutParams;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ColorDrawable;
import android.view.Window;
import com.kingagroot.component.ui.R$layout;
import android.os.Bundle;
import android.view.View;
import android.text.InputFilter$LengthFilter;
import android.text.InputFilter;
import java.util.Objects;
import android.text.Editable;
import android.text.TextWatcher;
import com.kingagroot.component.ui.R$id;
import android.os.Handler;
import android.widget.TextView;
import com.goodsrc.ui.library.widget.ClearEditText;
import android.view.View$OnClickListener;
import androidx.appcompat.app.AlertDialog;

public class EditPop extends AlertDialog implements View$OnClickListener
{
    private final EditPop.EditPop$Build build;
    private ClearEditText etText;
    private final EditPop.EditPop$OnEditPopListener onEditPopListener;
    private TextView tvCancel;
    private TextView tvCount;
    private TextView tvSure;
    
    private EditPop(final EditPop.EditPop$Build build) {
        super(build.context);
        this.build = build;
        this.onEditPopListener = build.onEditPopListener;
    }
    
    private void hidInput() {
        new Handler().postDelayed((Runnable)new EditPop$3(this), 300L);
    }
    
    private void initData() {
        this.setMaxLength(this.build.maxLength);
        this.setMaxLines(this.build.maxLines);
        this.setHint(this.build.hint);
        this.setShowCount(this.build.showCount);
        this.setContent((CharSequence)this.build.content);
    }
    
    private void initView() {
        this.etText = (ClearEditText)this.findViewById(R$id.et_text);
        this.tvCount = (TextView)this.findViewById(R$id.tv_count);
        this.tvCancel = (TextView)this.findViewById(R$id.tv_cancel);
        this.tvSure = (TextView)this.findViewById(R$id.tv_sure);
        this.etText.addTextChangedListener((TextWatcher)new EditPop$1(this));
        this.tvCancel.setOnClickListener((View$OnClickListener)this);
        this.tvSure.setOnClickListener((View$OnClickListener)this);
    }
    
    private void setContent(final CharSequence text) {
        this.etText.setText(text);
        final Editable text2 = this.etText.getText();
        final ClearEditText etText = this.etText;
        int length;
        if (text2 != null) {
            length = text2.length();
        }
        else {
            length = 0;
        }
        etText.setSelection(length);
    }
    
    private void setCount() {
        final int length = Objects.requireNonNull((Object)this.etText.getText()).toString().trim().length();
        final StringBuilder sb = new StringBuilder();
        sb.append(length);
        sb.append("/");
        sb.append(this.build.maxLength);
        this.tvCount.setText((CharSequence)sb.toString());
    }
    
    private void setHint(final String hint) {
        this.etText.setHint((CharSequence)hint);
    }
    
    private void setMaxLength(final int n) {
        this.etText.setFilters(new InputFilter[] { (InputFilter)new InputFilter$LengthFilter(n) });
        this.setCount();
    }
    
    private void setMaxLines(final int maxLines) {
        this.etText.setMaxLines(maxLines);
    }
    
    private void setShowCount(final boolean b) {
        if (b) {
            this.tvCount.setVisibility(0);
        }
        else {
            this.tvCount.setVisibility(8);
        }
    }
    
    private void showInput() {
        new Handler().postDelayed((Runnable)new EditPop$2(this), 300L);
    }
    
    public void dismiss() {
        super.dismiss();
        this.hidInput();
    }
    
    public void onClick(final View view) {
        if (view == this.tvCancel) {
            this.dismiss();
        }
        else if (view == this.tvSure) {
            if (this.onEditPopListener != null) {
                this.onEditPopListener.onText(Objects.requireNonNull((Object)this.etText.getText()).toString().trim().replace((CharSequence)"\n", (CharSequence)"").replace((CharSequence)" ", (CharSequence)""));
            }
            this.dismiss();
        }
    }
    
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.setContentView(R$layout.component_popuwindow_edit);
        this.etText = (ClearEditText)this.findViewById(R$id.et_text);
        final WindowManager$LayoutParams attributes = ((Window)Objects.requireNonNull((Object)this.getWindow())).getAttributes();
        final Window window = this.getWindow();
        attributes.dimAmount = 0.1f;
        attributes.width = this.build.width;
        attributes.height = -2;
        window.setBackgroundDrawable((Drawable)new ColorDrawable(0));
        window.clearFlags(131080);
        window.setSoftInputMode(50);
        window.setAttributes(attributes);
        window.setGravity(48);
        this.initView();
        this.initData();
    }
    
    public void show() {
        super.show();
        this.etText.setFocusableInTouchMode(true);
        this.etText.requestFocus();
        this.showInput();
    }
    
    public void show(final View view) {
        final Activity activity = (Activity)view.getContext();
        final Rect rect = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
        final int[] array = new int[2];
        view.getLocationOnScreen(array);
        final WindowManager$LayoutParams attributes = ((Window)Objects.requireNonNull((Object)this.getWindow())).getAttributes();
        final Window window = this.getWindow();
        int y;
        if ((y = array[1] - rect.top) < 0) {
            y = 0;
        }
        attributes.y = y;
        window.setAttributes(attributes);
        super.show();
        this.etText.setFocusableInTouchMode(true);
        this.etText.requestFocus();
        this.showInput();
    }
}

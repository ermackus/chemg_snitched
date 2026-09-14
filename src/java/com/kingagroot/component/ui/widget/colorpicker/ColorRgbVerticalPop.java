package com.kingagroot.component.ui.widget.colorpicker;

import android.graphics.Color;
import android.text.TextUtils;
import com.kingagroot.component.ui.utils.IntegerInputFliter;
import android.text.InputFilter;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ColorDrawable;
import java.util.Objects;
import android.view.Window;
import android.view.ViewGroup;
import com.kingagroot.component.ui.R;
import android.view.LayoutInflater;
import android.text.Editable;
import android.content.Context;
import android.widget.TextView;
import android.text.TextWatcher;
import android.widget.EditText;
import android.view.View;
import android.app.Dialog;

public class ColorRgbVerticalPop extends Dialog
{
    private View contentView;
    private EditText etColorB;
    private EditText etColorG;
    private EditText etColorR;
    private ColorPicker.OnColorChangedListener onColorChangedListener;
    private ColorPicker picker;
    private String strB;
    private String strG;
    private String strR;
    private TextWatcher textWatcher;
    private TextView tvColor;
    
    public ColorRgbVerticalPop(final Context context) {
        super(context);
        this.textWatcher = (TextWatcher)new TextWatcher() {
            final ColorRgbVerticalPop this$0;
            
            public void afterTextChanged(final Editable editable) {
                this.this$0.setChange();
            }
            
            public void beforeTextChanged(final CharSequence charSequence, final int n, final int n2, final int n3) {
            }
            
            public void onTextChanged(final CharSequence charSequence, final int n, final int n2, final int n3) {
            }
        };
        this.onColorChangedListener = (ColorPicker.OnColorChangedListener)new ColorRgbVerticalPop$2(this);
        this.requestWindowFeature(1);
        final LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService("layout_inflater");
        View inflate = null;
        if (layoutInflater != null) {
            inflate = layoutInflater.inflate(R.layout.component_pop_color_rgb_vertical, (ViewGroup)null);
        }
        if ((this.contentView = inflate) != null) {
            this.setContentView(inflate);
        }
        ((Window)Objects.requireNonNull((Object)this.getWindow())).setBackgroundDrawable((Drawable)new ColorDrawable(0));
        this.setCanceledOnTouchOutside(true);
        this.init();
        this.initEvent();
    }
    
    private void addColorPickerListener() {
        this.picker.setOnColorChangedListener(this.onColorChangedListener);
    }
    
    private void addTextWatchListener() {
        this.etColorR.addTextChangedListener(this.textWatcher);
        this.etColorG.addTextChangedListener(this.textWatcher);
        this.etColorB.addTextChangedListener(this.textWatcher);
    }
    
    private void getColorText() {
        this.strR = this.etColorR.getText().toString();
        this.strG = this.etColorG.getText().toString();
        this.strB = this.etColorB.getText().toString();
    }
    
    private void init() {
        this.picker = (ColorPicker)this.contentView.findViewById(R.id.picker_color);
        final SVBar svBar = (SVBar)this.contentView.findViewById(R.id.sv_bar);
        this.etColorR = (EditText)this.contentView.findViewById(R.id.et_color_r);
        this.etColorG = (EditText)this.contentView.findViewById(R.id.et_color_g);
        this.etColorB = (EditText)this.contentView.findViewById(R.id.et_color_b);
        this.tvColor = (TextView)this.contentView.findViewById(R.id.tv_color);
        this.picker.setShowOldCenterColor(false);
        this.picker.addSVBar(svBar);
        this.addColorPickerListener();
    }
    
    private void initEvent() {
        final InputFilter[] filters = { (InputFilter)new IntegerInputFliter() };
        this.etColorR.setFilters(filters);
        this.etColorG.setFilters(filters);
        this.etColorB.setFilters(filters);
        this.addTextWatchListener();
    }
    
    private boolean isnull() {
        this.getColorText();
        final boolean empty = TextUtils.isEmpty((CharSequence)this.strR);
        boolean b = false;
        if (!empty) {
            if (!TextUtils.isEmpty((CharSequence)this.strG)) {
                if (!TextUtils.isEmpty((CharSequence)this.strB)) {
                    b = true;
                }
            }
        }
        return b;
    }
    
    private void removeColorPickerListener() {
        this.picker.setOnColorChangedListener(null);
    }
    
    private void removeTextWatchListener() {
        this.etColorR.removeTextChangedListener(this.textWatcher);
        this.etColorG.removeTextChangedListener(this.textWatcher);
        this.etColorB.removeTextChangedListener(this.textWatcher);
    }
    
    private void setChange() {
        if (this.isnull()) {
            this.removeColorPickerListener();
            final int int1 = Integer.parseInt(this.strR);
            final int int2 = Integer.parseInt(this.strG);
            final int int3 = Integer.parseInt(this.strB);
            this.toHex(int1, int2, int3);
            this.setPickerColor(Color.rgb(int1, int2, int3));
            this.addColorPickerListener();
        }
    }
    
    private void toHex(final int n, final int n2, final int n3) {
        final String hexString = Integer.toHexString(n);
        final String hexString2 = Integer.toHexString(n2);
        final String hexString3 = Integer.toHexString(n3);
        String string = hexString;
        if (hexString.length() == 1) {
            final StringBuilder sb = new StringBuilder();
            sb.append("0");
            sb.append(hexString);
            string = sb.toString();
        }
        String string2 = hexString2;
        if (hexString2.length() == 1) {
            final StringBuilder sb2 = new StringBuilder();
            sb2.append("0");
            sb2.append(hexString2);
            string2 = sb2.toString();
        }
        String string3 = hexString3;
        if (hexString3.length() == 1) {
            final StringBuilder sb3 = new StringBuilder();
            sb3.append("0");
            sb3.append(hexString3);
            string3 = sb3.toString();
        }
        final TextView tvColor = this.tvColor;
        final StringBuilder sb4 = new StringBuilder();
        sb4.append("#");
        sb4.append(string);
        sb4.append(string2);
        sb4.append(string3);
        tvColor.setText((CharSequence)sb4.toString());
    }
    
    public int getColor() {
        return this.picker.getColor();
    }
    
    public void setPickerColor(final int color) {
        this.picker.setColor(color);
    }
    
    public void torgb(int n) {
        this.removeTextWatchListener();
        final int n2 = (0xFF0000 & n) >> 16;
        final int n3 = (0xFF00 & n) >> 8;
        n &= 0xFF;
        final EditText etColorR = this.etColorR;
        final StringBuilder sb = new StringBuilder();
        sb.append("");
        sb.append(n2);
        etColorR.setText((CharSequence)sb.toString());
        final EditText etColorG = this.etColorG;
        final StringBuilder sb2 = new StringBuilder();
        sb2.append("");
        sb2.append(n3);
        etColorG.setText((CharSequence)sb2.toString());
        final EditText etColorB = this.etColorB;
        final StringBuilder sb3 = new StringBuilder();
        sb3.append("");
        sb3.append(n);
        etColorB.setText((CharSequence)sb3.toString());
        final EditText etColorR2 = this.etColorR;
        etColorR2.setSelection(etColorR2.getText().toString().length());
        final EditText etColorG2 = this.etColorG;
        etColorG2.setSelection(etColorG2.getText().toString().length());
        final EditText etColorB2 = this.etColorB;
        etColorB2.setSelection(etColorB2.getText().toString().length());
        this.toHex(n2, n3, n);
        this.addTextWatchListener();
    }
}

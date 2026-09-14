package com.kingagroot.component.ui.account;

import android.view.View$OnClickListener;
import android.widget.TextView;
import android.widget.ImageButton;
import android.view.View;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ColorDrawable;
import android.view.ViewGroup;
import com.kingagroot.component.ui.R;
import android.view.LayoutInflater;
import android.content.Context;
import android.widget.PopupWindow;

public class CodeExplainPop extends PopupWindow
{
    static final boolean $assertionsDisabled = false;
    private final Context context;
    private final int type;
    
    public CodeExplainPop(final Context context, final int type) {
        super(context);
        this.context = context;
        this.type = type;
        this.setWidth(-1);
        this.setHeight(-2);
        final View inflate = ((LayoutInflater)context.getSystemService("layout_inflater")).inflate(R.layout.pop_code_explain, (ViewGroup)null);
        inflate.measure(0, 0);
        this.setContentView(inflate);
        this.setFocusable(true);
        this.setOutsideTouchable(true);
        this.update();
        this.setBackgroundDrawable((Drawable)new ColorDrawable(0));
        this.setAnimationStyle(R.style.pop_anim_style);
        this.initView(inflate);
    }
    
    private void initView(final View view) {
        final ImageButton imageButton = (ImageButton)view.findViewById(R.id.ibt_close_pop);
        final TextView textView = (TextView)view.findViewById(R.id.tv_code);
        final int type = this.type;
        if (type == 0) {
            textView.setText((CharSequence)this.context.getString(R.string.code_explain_phone));
        }
        else if (type == 1) {
            textView.setText((CharSequence)this.context.getString(R.string.code_explain_email));
        }
        imageButton.setOnClickListener((View$OnClickListener)new View$OnClickListener(this) {
            final CodeExplainPop this$0;
            
            public void onClick(final View view) {
                this.this$0.dismiss();
            }
        });
    }
}

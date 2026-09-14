package com.kingagroot.component.ui.widget;

import com.kingagroot.component.ui.R$drawable;
import android.util.AttributeSet;
import android.content.Context;
import androidx.appcompat.widget.AppCompatImageButton;

public class PaletteFingerView extends AppCompatImageButton
{
    private boolean isDraging;
    
    public PaletteFingerView(final Context context) {
        super(context);
    }
    
    public PaletteFingerView(final Context context, final AttributeSet set) {
        super(context, set);
    }
    
    public void onDrag(final boolean b) {
        if (this.isDraging && !b) {
            this.isDraging = b;
            this.setBackgroundResource(R$drawable.drag_bg_check);
            this.setImageResource(R$drawable.ic_drag_check);
        }
        else if (!this.isDraging && b) {
            this.isDraging = b;
            this.setBackgroundResource(R$drawable.drag_bg_check);
            this.setImageResource(R$drawable.ic_move_status);
        }
    }
    
    public void setSelect(final boolean b) {
        this.setTag((Object)b);
        if (b) {
            this.setBackgroundResource(R$drawable.drag_bg_check);
            this.setImageResource(R$drawable.ic_drag_check);
        }
        else {
            this.setBackgroundResource(R$drawable.drag_bg_nor);
            this.setImageResource(R$drawable.ic_drag_nor);
        }
    }
}

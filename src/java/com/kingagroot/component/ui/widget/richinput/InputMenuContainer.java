package com.kingagroot.component.ui.widget.richinput;

import com.kingagroot.kingdraw.core.model.FormatValue;
import android.view.MotionEvent;
import com.kingagroot.component.ui.R$id;
import android.view.ViewGroup;
import com.kingagroot.component.ui.R$layout;
import android.util.AttributeSet;
import android.content.Context;
import android.view.View;

public class InputMenuContainer extends InputBaseMenu
{
    private View contentView;
    private InputBaseMenu currentInputMenu;
    private LandscapeInputMenu landscapeInputMenu;
    private InputMenuContainer$OnInputListner onInputListner;
    private PortraitInputMenu portraitInputMenu;
    
    public InputMenuContainer(final Context context) {
        super(context);
    }
    
    public InputMenuContainer(final Context context, final AttributeSet set) {
        super(context, set);
        this.contentView = View.inflate(context, R$layout.component_rich_input_menu_container, (ViewGroup)this);
        this.initView();
    }
    
    private void initView() {
        this.landscapeInputMenu = (LandscapeInputMenu)this.contentView.findViewById(R$id.input_menu_landscape);
        final PortraitInputMenu portraitInputMenu = (PortraitInputMenu)this.contentView.findViewById(R$id.input_menu_portrait);
        this.portraitInputMenu = portraitInputMenu;
        this.currentInputMenu = portraitInputMenu;
    }
    
    public void isInputSupName(final boolean b) {
        this.landscapeInputMenu.setFontIsShow(b);
        this.portraitInputMenu.setFontIsShow(b);
    }
    
    public void isPortrait(final boolean b) {
        if (b) {
            this.currentInputMenu = this.portraitInputMenu;
            this.landscapeInputMenu.setVisibility(8);
        }
        else {
            this.currentInputMenu = this.landscapeInputMenu;
            this.portraitInputMenu.setVisibility(8);
        }
    }
    
    public boolean keyBoardEvent(final MotionEvent motionEvent) {
        return this.currentInputMenu.keyBoardEvent(motionEvent);
    }
    
    public void onPause() {
        this.currentInputMenu.onPause();
    }
    
    public void onResume() {
        this.currentInputMenu.onResume();
    }
    
    public void setContent(final boolean b, final String s, final FormatValue formatValue) {
        this.currentInputMenu.setContent(b, s, formatValue);
    }
    
    public void setOnInputListner(final InputMenuContainer$OnInputListner onInputListner) {
        this.onInputListner = onInputListner;
        final InputMenuContainer$OnInputListner inputMenuContainer$OnInputListner = (InputMenuContainer$OnInputListner)new InputMenuContainer$OnInputListner(this) {
            final InputMenuContainer this$0;
            
            public void onCancel() {
                this.this$0.setVisibility(8);
                this.this$0.onInputListner.onCancel();
            }
            
            public void onResult(final String s) {
                this.this$0.setVisibility(8);
                this.this$0.onInputListner.onResult(s);
            }
        };
        this.landscapeInputMenu.setOnInputListner((InputMenuContainer$OnInputListner)inputMenuContainer$OnInputListner);
        this.portraitInputMenu.setOnInputListner((InputMenuContainer$OnInputListner)inputMenuContainer$OnInputListner);
    }
    
    public void setVisibility(final int n) {
        super.setVisibility(n);
        this.currentInputMenu.setVisibility(n);
    }
}

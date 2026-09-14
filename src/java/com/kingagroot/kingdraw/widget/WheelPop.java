package com.kingagroot.kingdraw.widget;

import com.kingagroot.kingdraw.utils.fontutil.OnItemSelectedListener;
import android.view.View$OnClickListener;
import java.util.Arrays;
import com.kingagroot.component.ui.model.GFormatValue;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ColorDrawable;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import java.util.ArrayList;
import com.kingagroot.kingdraw.utils.fontutil.LoopView;
import java.util.List;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.PopupWindow;

public class WheelPop extends PopupWindow
{
    private Button btnFontSure;
    private View conentView;
    Context context;
    List<String> fontList;
    int fontPostion;
    private LoopView lvFont;
    private LoopView lvSize;
    private LoopView lvStyle;
    private OnClickButtonSureListener onClickButtonSureListener;
    List<String> sizeList;
    int sizePostion;
    private String strFont;
    private String strSize;
    private String strStyle;
    List<String> styleList;
    int stylePostion;
    
    public WheelPop(final Context context, final String strFont, final String strStyle, final String strSize) {
        super(context);
        this.fontList = (List<String>)new ArrayList();
        this.styleList = (List<String>)new ArrayList();
        this.sizeList = (List<String>)new ArrayList();
        this.fontPostion = 0;
        this.stylePostion = 0;
        this.sizePostion = 0;
        this.context = context;
        this.strFont = strFont;
        this.strStyle = strStyle;
        this.strSize = strSize;
        final LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService("layout_inflater");
        if (layoutInflater != null) {
            this.conentView = layoutInflater.inflate(2131493153, (ViewGroup)null);
        }
        this.setContentView(this.conentView);
        this.setWidth(-1);
        this.setHeight(-2);
        this.setFocusable(true);
        this.setOutsideTouchable(true);
        this.update();
        this.setBackgroundDrawable((Drawable)new ColorDrawable(-16777216));
        this.init();
        this.setData();
    }
    
    private void getFont() {
        final String[] allFontName = GFormatValue.getAllFontName();
        final String[] allFontStyle = GFormatValue.getAllFontStyle();
        final int[] allFontSize = GFormatValue.getAllFontSize();
        this.fontList = (List<String>)Arrays.asList((Object[])allFontName);
        this.styleList = (List<String>)Arrays.asList((Object[])allFontStyle);
        for (int i = 0; i < allFontSize.length; ++i) {
            final List<String> sizeList = this.sizeList;
            final StringBuilder sb = new StringBuilder();
            sb.append(allFontSize[i]);
            sb.append("");
            sizeList.add((Object)sb.toString());
        }
    }
    
    private void init() {
        this.lvFont = (LoopView)this.conentView.findViewById(2131297042);
        this.lvStyle = (LoopView)this.conentView.findViewById(2131297045);
        this.lvSize = (LoopView)this.conentView.findViewById(2131297044);
        (this.btnFontSure = (Button)this.conentView.findViewById(2131296429)).setOnClickListener((View$OnClickListener)new View$OnClickListener(this) {
            final WheelPop this$0;
            
            public void onClick(final View view) {
                if (this.this$0.onClickButtonSureListener != null) {
                    this.this$0.onClickButtonSureListener.onSure(this.this$0.strFont, this.this$0.strStyle, this.this$0.strSize);
                    this.this$0.dismiss();
                }
            }
        });
    }
    
    private void setData() {
        this.getFont();
        this.fontPostion = this.fontList.indexOf((Object)this.strFont);
        this.stylePostion = this.styleList.indexOf((Object)this.strStyle);
        this.sizePostion = this.sizeList.indexOf((Object)this.strSize);
        this.lvFont.setNotLoop();
        this.lvFont.setItems((List)this.fontList);
        this.lvFont.setInitPosition(this.fontPostion);
        this.lvFont.setListener((OnItemSelectedListener)new WheelPop$2(this));
        this.lvStyle.setNotLoop();
        this.lvStyle.setItems((List)this.styleList);
        this.lvStyle.setInitPosition(this.stylePostion);
        this.lvStyle.setListener((OnItemSelectedListener)new WheelPop$3(this));
        this.lvSize.setNotLoop();
        this.lvSize.setItems((List)this.sizeList);
        this.lvSize.setInitPosition(this.sizePostion);
        this.lvSize.setListener((OnItemSelectedListener)new WheelPop$4(this));
    }
    
    public void dismiss() {
        super.dismiss();
    }
    
    public void setSure(final OnClickButtonSureListener onClickButtonSureListener) {
        this.onClickButtonSureListener = onClickButtonSureListener;
    }
    
    public interface OnClickButtonSureListener
    {
        void onSure(final String p0, final String p1, final String p2);
    }
}

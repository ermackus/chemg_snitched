package com.kingagroot.kingdraw.widget.ViewSearch;

import android.view.ViewGroup;
import android.view.View;
import android.util.AttributeSet;
import android.widget.TextView;
import android.widget.RelativeLayout;
import android.widget.ImageView;
import android.content.Context;
import android.widget.Button;
import android.widget.LinearLayout;

public class DrawSearchView extends LinearLayout
{
    Button btn_draw;
    Context context;
    ImageView img_clean;
    ImageView img_editor;
    RelativeLayout rl_draw_data;
    TextView tv_draw_name;
    TextView tv_draw_smiles;
    
    public DrawSearchView(final Context context) {
        super(context);
    }
    
    public DrawSearchView(final Context context, final AttributeSet set) {
        super(context, set);
    }
    
    public DrawSearchView(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        this.context = context;
        this.init(View.inflate(context, 2131493196, (ViewGroup)this));
    }
    
    private void init(final View view) {
        this.btn_draw = (Button)view.findViewById(2131296425);
        this.rl_draw_data = (RelativeLayout)view.findViewById(2131297290);
        this.img_editor = (ImageView)view.findViewById(2131296875);
        this.img_clean = (ImageView)view.findViewById(2131296873);
        this.tv_draw_name = (TextView)view.findViewById(2131297565);
        this.tv_draw_smiles = (TextView)view.findViewById(2131297566);
    }
}

package com.kingagroot.kingdraw.widget;

import android.widget.ImageView;
import com.kingagroot.kingdraw.utils.ImageLoader;
import org.xutils.image.ImageOptions$Builder;
import android.view.View$OnClickListener;
import com.goodsrc.library.utils.DisplayUtil;
import com.goodsrc.library.utils.SystemUtils;
import android.view.ViewGroup;
import android.view.View;
import android.util.AttributeSet;
import android.content.Context;
import android.widget.TextView;
import com.google.android.material.imageview.ShapeableImageView;
import android.widget.LinearLayout;

public class WorkStationToolBar extends LinearLayout
{
    public ShapeableImageView imgIcon;
    private LinearLayout llToolbarCompany;
    private OnToolBarClickTypeListener onToolBarClickTypeListener;
    private TextView toolbarRightText;
    public TextView tvTitle;
    
    public WorkStationToolBar(final Context context) {
        super(context);
    }
    
    public WorkStationToolBar(final Context context, final AttributeSet set) {
        super(context, set);
        this.initView(View.inflate(context, 2131493094, (ViewGroup)this));
    }
    
    public WorkStationToolBar(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        this.initView(View.inflate(context, 2131493094, (ViewGroup)this));
    }
    
    private void initView(final View view) {
        this.tvTitle = (TextView)view.findViewById(2131297508);
        this.imgIcon = (ShapeableImageView)view.findViewById(2131297505);
        this.toolbarRightText = (TextView)view.findViewById(2131297506);
        this.llToolbarCompany = (LinearLayout)view.findViewById(2131297035);
        this.tvTitle.setMaxWidth(SystemUtils.getScreenWidth(this.getContext()) - DisplayUtil.dip2px(this.getContext(), 80.0f));
        this.llToolbarCompany.setOnClickListener((View$OnClickListener)new _$$Lambda$WorkStationToolBar$Vu1qcIsWCG8DD9m6NKEBGjc4geQ(this));
        this.toolbarRightText.setOnClickListener((View$OnClickListener)new _$$Lambda$WorkStationToolBar$xB4u_gMdyT5Wi_oSh3kO4FQfI3o(this));
    }
    
    public void setLogoIcon(final String s) {
        ImageLoader.bind((ImageView)this.imgIcon, s, new ImageOptions$Builder().setFailureDrawableId(2131231328).build());
    }
    
    public void setToolBarClickListener(final OnToolBarClickTypeListener onToolBarClickTypeListener) {
        this.onToolBarClickTypeListener = onToolBarClickTypeListener;
    }
    
    public void setToolbarRightTextVisible(final int n) {
        if (n == 2) {
            this.toolbarRightText.setVisibility(8);
        }
        else {
            this.toolbarRightText.setVisibility(0);
        }
    }
    
    public void setTvTitle(final String text) {
        this.tvTitle.setText((CharSequence)text);
    }
    
    public interface OnToolBarClickTypeListener
    {
        void onChangeGroupListener();
        
        void onManagerListener();
    }
}

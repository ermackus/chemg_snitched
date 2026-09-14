package com.kingagroot.kingdraw.widget.guide;

import java.util.Collection;
import android.widget.ListAdapter;
import com.kingagroot.component.ui.utils.GBitmapUtils;
import com.goodsrc.ui.library.widget.fastAdapter.ViewHolder;
import com.goodsrc.ui.library.widget.fastAdapter.CommonAdapter;
import android.view.View$OnClickListener;
import android.view.ViewGroup;
import com.kingagroot.kingdraw.interfaces.impl.GestureDbiMpl;
import java.util.ArrayList;
import android.util.AttributeSet;
import android.content.Context;
import com.goodsrc.ui.library.widget.HorizontalListView;
import android.widget.ImageButton;
import com.kingagroot.kingdraw.model.GestureGroupModel;
import java.util.List;
import com.kingagroot.kingdraw.interfaces.GestureDbi;
import android.view.View;
import android.widget.RelativeLayout;

public class GuideGestureChartView extends RelativeLayout implements GuideBaseView
{
    View contentView;
    private final GestureDbi gestureDbi;
    List<GestureGroupModel> gestureGroupModels;
    GuideBaseView guideBaseView;
    ImageButton imgbtnClose;
    HorizontalListView listView;
    
    public GuideGestureChartView(final Context context) {
        this(context, null);
    }
    
    public GuideGestureChartView(final Context context, final AttributeSet set) {
        super(context, set);
        this.gestureGroupModels = (List<GestureGroupModel>)new ArrayList();
        this.gestureDbi = (GestureDbi)new GestureDbiMpl();
        final View inflate = View.inflate(context, 2131493069, (ViewGroup)this);
        this.contentView = inflate;
        this.imgbtnClose = (ImageButton)inflate.findViewById(2131296887);
        this.listView = (HorizontalListView)this.contentView.findViewById(2131296981);
        this.imgbtnClose.setOnClickListener((View$OnClickListener)new _$$Lambda$GuideGestureChartView$daqdmyeLC5ZQEGg8K4qIRbbSktc(this));
        this.initData();
        this.listView.setAdapter((ListAdapter)new CommonAdapter<GestureGroupModel>(this, context, this.gestureGroupModels, 2131493078) {
            final GuideGestureChartView this$0;
            
            public void convert(final ViewHolder viewHolder, final GestureGroupModel gestureGroupModel) {
                viewHolder.setImageResource(2131296944, GBitmapUtils.getResId(this.this$0.getContext(), gestureGroupModel.getBondRes()));
                viewHolder.setImageResource(2131296920, GBitmapUtils.getResId(this.this$0.getContext(), gestureGroupModel.getGestureRes()));
            }
        });
    }
    
    private void initData() {
        this.gestureGroupModels.clear();
        final List dataByKey = this.gestureDbi.getDataByKey(true);
        if (dataByKey != null) {
            this.gestureGroupModels.addAll((Collection)dataByKey);
        }
    }
    
    public void addObserver(final GuideBaseView guideBaseView) {
        this.guideBaseView = guideBaseView;
    }
    
    public void dismiss() {
        this.setVisibility(8);
    }
    
    public void next() {
        final GuideBaseView guideBaseView = this.guideBaseView;
        if (guideBaseView != null) {
            guideBaseView.next();
        }
    }
    
    public void setBackground(final int background) {
        final GuideBaseView guideBaseView = this.guideBaseView;
        if (guideBaseView != null) {
            guideBaseView.setBackground(background);
        }
    }
    
    public void setBackgroundAlpha(final float backgroundAlpha) {
        final GuideBaseView guideBaseView = this.guideBaseView;
        if (guideBaseView != null) {
            guideBaseView.setBackgroundAlpha(backgroundAlpha);
        }
    }
    
    public void show() {
        this.setBackground(0);
        this.setVisibility(0);
    }
}

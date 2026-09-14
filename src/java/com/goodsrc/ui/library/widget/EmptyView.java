package com.goodsrc.ui.library.widget;

import android.view.ViewGroup$LayoutParams;
import com.goodsrc.library.utils.ScreenUtils;
import android.view.View$MeasureSpec;
import android.widget.FrameLayout$LayoutParams;
import android.view.ViewGroup$MarginLayoutParams;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout$OnRefreshListener;
import android.graphics.Color;
import android.view.ViewGroup;
import com.goodsrc.ui.library.R;
import android.util.AttributeSet;
import android.widget.TextView;
import android.widget.ImageView;
import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;

public class EmptyView extends LinearLayout
{
    private final View contentView;
    private final Context context;
    private EmptyViewOnRefreshListener emptyViewOnRefreshListener;
    private final ImageView imagEmptyIcon;
    private final RefreshLayout refresh;
    private final TextView tvEmptyHint;
    
    public EmptyView(final Context context) {
        this(context, null);
    }
    
    public EmptyView(final Context context, final AttributeSet set) {
        super(context, set);
        this.context = context;
        final View inflate = View.inflate(context, R.layout.empty_view, (ViewGroup)this);
        this.contentView = inflate;
        this.refresh = (RefreshLayout)inflate.findViewById(R.id.refresh);
        this.imagEmptyIcon = (ImageView)this.contentView.findViewById(R.id.imag_empty_icon);
        (this.tvEmptyHint = (TextView)this.contentView.findViewById(R.id.tv_empty_hint)).setTextColor(Color.parseColor("#9F9F9F"));
        this.refresh.setOnRefreshListener((SwipeRefreshLayout$OnRefreshListener)new SwipeRefreshLayout$OnRefreshListener(this) {
            final EmptyView this$0;
            
            public void onRefresh() {
                if (this.this$0.emptyViewOnRefreshListener != null) {
                    this.this$0.emptyViewOnRefreshListener.onRefresh();
                }
                this.this$0.refresh.setRefreshing(false);
            }
        });
        this.setEnableRefresh(true);
    }
    
    public void setEmptyView(final int imageResource, final String text) {
        this.imagEmptyIcon.setImageResource(imageResource);
        this.tvEmptyHint.setText((CharSequence)text);
    }
    
    public void setEmptyView(final String text) {
        this.tvEmptyHint.setText((CharSequence)text);
    }
    
    public void setEmptyViewOnRefreshListener(final EmptyViewOnRefreshListener emptyViewOnRefreshListener) {
        this.emptyViewOnRefreshListener = emptyViewOnRefreshListener;
    }
    
    public void setEnableRefresh(final boolean refresh) {
        this.refresh.setRefresh(refresh);
    }
    
    public void showLocatuion(final View view) {
        view.post((Runnable)new Runnable(this, view) {
            final EmptyView this$0;
            final View val$refArea;
            
            public void run() {
                final int[] array = new int[2];
                this.val$refArea.getLocationOnScreen(array);
                final View view = (View)this.this$0.getParent();
                final int[] array2 = new int[2];
                view.getLocationOnScreen(array2);
                final ViewGroup$MarginLayoutParams viewGroup$MarginLayoutParams = (ViewGroup$MarginLayoutParams)this.val$refArea.getLayoutParams();
                final FrameLayout$LayoutParams layoutParams = new FrameLayout$LayoutParams(-1, -1);
                this.val$refArea.measure(View$MeasureSpec.makeMeasureSpec(1073741823, Integer.MIN_VALUE), View$MeasureSpec.makeMeasureSpec(1073741823, Integer.MIN_VALUE));
                final int screenWidth = ScreenUtils.getScreenWidth(this.this$0.getContext());
                final int measuredHeight = this.val$refArea.getMeasuredHeight();
                if (screenWidth != 0 && measuredHeight != 0) {
                    layoutParams.width = screenWidth + viewGroup$MarginLayoutParams.leftMargin + viewGroup$MarginLayoutParams.rightMargin;
                    layoutParams.height = measuredHeight + viewGroup$MarginLayoutParams.topMargin + viewGroup$MarginLayoutParams.bottomMargin;
                }
                layoutParams.setMargins(0, array[1] - array2[1] - viewGroup$MarginLayoutParams.topMargin, 0, 0);
                this.this$0.contentView.setLayoutParams((ViewGroup$LayoutParams)layoutParams);
            }
        });
    }
    
    public interface EmptyViewOnRefreshListener
    {
        void onRefresh();
    }
}

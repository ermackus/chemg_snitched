package com.kingagroot.component.ui.widget.richinput.SelectableSelector;

import android.text.TextUtils;
import android.text.Layout;
import android.view.View;
import android.view.View$OnClickListener;
import com.kingagroot.kingdraw.core.utils.GDensityUtil;
import android.view.View$MeasureSpec;
import android.view.ViewGroup;
import com.kingagroot.component.ui.R;
import android.view.LayoutInflater;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.LinearLayout;

public class SelectMenu
{
    private final LinearLayout llCopy;
    private final LinearLayout llSelectAll;
    private int mCursorHandleSize;
    private final int mHeight;
    private final TextView mTextView;
    private final int mWidth;
    private final PopupWindow mWindow;
    private SelectMenuListner selectMenuListner;
    
    public SelectMenu(final TextView mTextView) {
        final View inflate = LayoutInflater.from(mTextView.getContext()).inflate(R.layout.component_rich_layout_operate_windows, (ViewGroup)null);
        inflate.measure(View$MeasureSpec.makeMeasureSpec(0, 0), View$MeasureSpec.makeMeasureSpec(0, 0));
        this.mTextView = mTextView;
        this.mWidth = inflate.getMeasuredWidth();
        this.mHeight = inflate.getMeasuredHeight();
        (this.mWindow = new PopupWindow(inflate, -2, GDensityUtil.dp2px(45.0f), false)).setClippingEnabled(false);
        this.mWindow.setBackgroundDrawable(mTextView.getContext().getResources().getDrawable(R.drawable.tool_bg_sel));
        this.llSelectAll = (LinearLayout)inflate.findViewById(R.id.ll_select_all);
        this.llCopy = (LinearLayout)inflate.findViewById(R.id.ll_copy);
        inflate.findViewById(R.id.tv_select_all).setOnClickListener((View$OnClickListener)new View$OnClickListener(this) {
            final SelectMenu this$0;
            
            public void onClick(final View view) {
                if (this.this$0.selectMenuListner != null) {
                    this.this$0.selectMenuListner.onSelectAll();
                }
            }
        });
        inflate.findViewById(R.id.tv_copy).setOnClickListener((View$OnClickListener)new View$OnClickListener(this) {
            final SelectMenu this$0;
            
            public void onClick(final View view) {
                if (this.this$0.selectMenuListner != null) {
                    this.this$0.selectMenuListner.onCopy();
                }
            }
        });
        inflate.findViewById(R.id.tv_paste).setOnClickListener((View$OnClickListener)new View$OnClickListener(this) {
            final SelectMenu this$0;
            
            public void onClick(final View view) {
                if (this.this$0.selectMenuListner != null) {
                    this.this$0.selectMenuListner.onPaste();
                }
            }
        });
    }
    
    private int[] getShowPosition(final SelectionInfo selectionInfo) throws IndexOutOfBoundsException {
        final Layout layout = this.mTextView.getLayout();
        final int lineForOffset = layout.getLineForOffset(selectionInfo.mStart);
        final int lineForOffset2 = layout.getLineForOffset(selectionInfo.mEnd);
        float primaryHorizontal = layout.getPrimaryHorizontal(selectionInfo.mStart);
        final float primaryHorizontal2 = layout.getPrimaryHorizontal(selectionInfo.mEnd);
        final float n = (float)layout.getLineTop(lineForOffset);
        final float n2 = (float)layout.getLineBottom(lineForOffset2);
        final int n3 = this.mTextView.getMeasuredWidth() - this.mTextView.getPaddingLeft() - this.mTextView.getPaddingRight();
        final int n4 = this.mTextView.getMeasuredHeight() - this.mTextView.getPaddingTop() - this.mTextView.getPaddingBottom();
        final int mCursorHandleSize = this.mCursorHandleSize;
        final float n5 = (float)(mCursorHandleSize * 2);
        final float n6 = (float)n3;
        final float n7 = (float)(mCursorHandleSize * 2);
        final float n8 = (float)n4;
        final float n9 = (float)mCursorHandleSize;
        final int mHeight = this.mHeight;
        final float n10 = (float)mHeight;
        int n11 = 8;
        int n12 = 0;
        Label_0283: {
            if (n <= n10) {
                if (n8 - n2 - n9 > mHeight) {
                    n12 = (int)primaryHorizontal;
                    n11 = n4 - mHeight;
                    break Label_0283;
                }
                final int mWidth = this.mWidth;
                if (n6 - primaryHorizontal2 - n7 > mWidth) {
                    n12 = (int)primaryHorizontal2 + mCursorHandleSize * 2;
                    break Label_0283;
                }
                if (primaryHorizontal - n5 <= mWidth) {
                    n12 = (n3 - mWidth) / 2;
                    break Label_0283;
                }
                primaryHorizontal = primaryHorizontal - mWidth - mCursorHandleSize * 2;
            }
            n12 = (int)primaryHorizontal;
        }
        final int[] array = new int[2];
        this.mTextView.getLocationOnScreen(array);
        return new int[] { n12 + array[0], n11 + array[1] };
    }
    
    public void dismiss() {
        this.mWindow.dismiss();
        this.enableCopy(true);
        this.enableSelectAll(false);
    }
    
    public void enableCopy(final boolean b) {
        if (b) {
            this.llCopy.setVisibility(0);
        }
        else {
            this.llCopy.setVisibility(8);
        }
    }
    
    public void enableSelectAll(final boolean b) {
        if (b && !TextUtils.isEmpty(this.mTextView.getText())) {
            this.llSelectAll.setVisibility(0);
        }
        else {
            this.llSelectAll.setVisibility(8);
        }
    }
    
    public boolean isShowing() {
        return this.mWindow.isShowing();
    }
    
    public void setCursorHandleSize(final int mCursorHandleSize) {
        this.mCursorHandleSize = mCursorHandleSize;
    }
    
    public void setSelectMenuListner(final SelectMenuListner selectMenuListner) {
        this.selectMenuListner = selectMenuListner;
    }
    
    public void show(final SelectionInfo selectionInfo) {
        try {
            final int[] showPosition = this.getShowPosition(selectionInfo);
            this.mWindow.showAtLocation((View)this.mTextView, 0, showPosition[0], showPosition[1]);
        }
        catch (final IndexOutOfBoundsException ex) {
            ex.printStackTrace();
        }
    }
    
    public void updata(final SelectionInfo selectionInfo) {
        try {
            final int[] showPosition = this.getShowPosition(selectionInfo);
            this.mWindow.update(showPosition[0], showPosition[1], -1, -1);
        }
        catch (final IndexOutOfBoundsException ex) {
            ex.printStackTrace();
        }
    }
    
    public interface SelectMenuListner
    {
        void onCopy();
        
        void onPaste();
        
        void onSelectAll();
    }
}

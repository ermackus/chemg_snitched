package com.kingagroot.kingdraw.ui.baike.view;

import android.view.View$MeasureSpec;
import android.util.Log;
import android.view.ViewGroup$MarginLayoutParams;
import android.view.ViewGroup$LayoutParams;
import android.util.AttributeSet;
import java.util.ArrayList;
import android.content.Context;
import android.view.View;
import java.util.List;
import android.view.ViewGroup;

public class FlowLayout extends ViewGroup
{
    private final String TAG;
    private final List<List<View>> mAllViews;
    private final List<Integer> mLineHeight;
    
    public FlowLayout(final Context context) {
        super(context);
        this.mAllViews = (List<List<View>>)new ArrayList();
        this.mLineHeight = (List<Integer>)new ArrayList();
        this.TAG = "TAG";
    }
    
    public FlowLayout(final Context context, final AttributeSet set) {
        super(context, set);
        this.mAllViews = (List<List<View>>)new ArrayList();
        this.mLineHeight = (List<Integer>)new ArrayList();
        this.TAG = "TAG";
    }
    
    public FlowLayout(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        this.mAllViews = (List<List<View>>)new ArrayList();
        this.mLineHeight = (List<Integer>)new ArrayList();
        this.TAG = "TAG";
    }
    
    public ViewGroup$LayoutParams generateLayoutParams(final AttributeSet set) {
        return (ViewGroup$LayoutParams)new ViewGroup$MarginLayoutParams(this.getContext(), set);
    }
    
    protected void onLayout(final boolean b, int n, int i, int j, int n2) {
        new ArrayList();
        final int size = this.mAllViews.size();
        i = 0;
        n = 0;
        while (i < size) {
            final List list = (List)this.mAllViews.get(i);
            final int intValue = (int)this.mLineHeight.get(i);
            final StringBuilder sb = new StringBuilder();
            sb.append("\u7b2c");
            sb.append(i);
            sb.append("\u884c \uff1a");
            sb.append(list.size());
            sb.append("-------lineHeight");
            sb.append(intValue);
            Log.e("onLayout", sb.toString());
            j = 0;
            n2 = 0;
            while (j < list.size()) {
                final View view = (View)list.get(j);
                if (view.getVisibility() != 8) {
                    final ViewGroup$MarginLayoutParams viewGroup$MarginLayoutParams = (ViewGroup$MarginLayoutParams)view.getLayoutParams();
                    final int n3 = viewGroup$MarginLayoutParams.leftMargin + n2;
                    final int n4 = viewGroup$MarginLayoutParams.topMargin + n;
                    view.layout(n3, n4, view.getMeasuredWidth() + n3, view.getMeasuredHeight() + n4);
                    n2 += view.getMeasuredWidth() + viewGroup$MarginLayoutParams.rightMargin + viewGroup$MarginLayoutParams.leftMargin;
                }
                ++j;
            }
            n += intValue;
            ++i;
        }
        final StringBuilder sb2 = new StringBuilder();
        sb2.append("onLayout   mAllViews.size() -- > ");
        sb2.append(this.mAllViews.size());
        sb2.append("   mLineHeight.size() -- > ");
        sb2.append(this.mLineHeight.size());
        Log.v("onLayout", sb2.toString());
    }
    
    protected void onMeasure(int n, int n2) {
        this.mAllViews.clear();
        this.mLineHeight.clear();
        final int mode = View$MeasureSpec.getMode(n);
        final int mode2 = View$MeasureSpec.getMode(n2);
        final int size = View$MeasureSpec.getSize(n);
        final int size2 = View$MeasureSpec.getSize(n2);
        final int childCount = this.getChildCount();
        ArrayList list = new ArrayList();
        int i = 0;
        int n3 = 0;
        int n4 = 0;
        int n5 = 0;
        int n6 = 0;
        while (i < childCount) {
            final View child = this.getChildAt(i);
            this.measureChild(child, n, n2);
            final ViewGroup$MarginLayoutParams viewGroup$MarginLayoutParams = (ViewGroup$MarginLayoutParams)child.getLayoutParams();
            final int n7 = child.getMeasuredWidth() + viewGroup$MarginLayoutParams.leftMargin + viewGroup$MarginLayoutParams.rightMargin;
            n3 = child.getMeasuredHeight() + viewGroup$MarginLayoutParams.topMargin + viewGroup$MarginLayoutParams.bottomMargin;
            final int n8 = n4 + n7;
            int n9;
            int n10;
            int n11;
            if (n8 > size) {
                n9 = Math.max(n4, n7);
                this.mAllViews.add((Object)list);
                this.mLineHeight.add((Object)n3);
                list = new ArrayList();
                ((List)list).add((Object)child);
                n10 = n6 + n3;
                final StringBuilder sb = new StringBuilder();
                sb.append("hight--");
                sb.append(n10);
                Log.e("\u9700\u8981\u6362\u884c", sb.toString());
                final StringBuilder sb2 = new StringBuilder();
                sb2.append("AllViews.size()  --  > ");
                sb2.append(this.mAllViews.size());
                Log.e("onMeasure", sb2.toString());
                n11 = n7;
            }
            else {
                final StringBuilder sb3 = new StringBuilder();
                sb3.append("hight--");
                sb3.append(n6);
                Log.e("\u4e0d\u9700\u8981\u6362\u884c", sb3.toString());
                ((List)list).add((Object)child);
                n9 = n5;
                n10 = n6;
                n11 = n8;
            }
            int n12 = n10;
            if (i == childCount - 1) {
                n9 = Math.max(n11, n7);
                n12 = n10 + n3;
                final StringBuilder sb4 = new StringBuilder();
                sb4.append("hight--");
                sb4.append(n12);
                Log.e("\u6700\u540e\u4e00\u4e2aview", sb4.toString());
            }
            ++i;
            n5 = n9;
            n4 = n11;
            n6 = n12;
        }
        this.mLineHeight.add((Object)n3);
        this.mAllViews.add((Object)list);
        if (mode == 1073741824) {
            n = size;
        }
        else {
            n = n5;
        }
        if (mode2 == 1073741824) {
            n2 = size2;
        }
        else {
            n2 = n6;
        }
        this.setMeasuredDimension(n, n2);
        final StringBuilder sb5 = new StringBuilder();
        sb5.append("mAllViews.size() -- > ");
        sb5.append(this.mAllViews.size());
        sb5.append("   mLineHeight.size() -- > ");
        sb5.append(this.mLineHeight.size());
        sb5.append("Height -- > ");
        sb5.append(n6);
        Log.e("onMeasure", sb5.toString());
    }
}

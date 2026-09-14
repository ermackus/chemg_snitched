package com.kingagroot.kingdraw.widget.FileTag;

import android.view.View$MeasureSpec;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import android.view.View;
import android.view.View$OnClickListener;
import com.kingagroot.kingdraw.core.utils.GDensityUtil;
import android.util.AttributeSet;
import android.content.Context;
import android.view.ViewGroup;

public class FileTagGroup extends ViewGroup
{
    private final AddTagView addTagView;
    private final Context context;
    private boolean hasAddTag;
    private final int horizontalSpacing;
    private OnAddTagClickListener onAddTagClickListener;
    private OnTagClickListener onTagClickListener;
    private final int verticalSpacing;
    
    public FileTagGroup(final Context context) {
        this(context, null);
    }
    
    public FileTagGroup(final Context context, final AttributeSet set) {
        this(context, set, 0);
    }
    
    public FileTagGroup(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        this.horizontalSpacing = GDensityUtil.dp2px(12.0f);
        this.verticalSpacing = GDensityUtil.dp2px(12.0f);
        this.context = context;
        this.addTagView = new AddTagView(context);
    }
    
    public void appendAddTag(final OnAddTagClickListener onAddTagClickListener) {
        this.hasAddTag = true;
        this.onAddTagClickListener = onAddTagClickListener;
        this.addTagView.setOnClickListener((View$OnClickListener)new View$OnClickListener(this) {
            final FileTagGroup this$0;
            
            public void onClick(final View view) {
                if (this.this$0.onAddTagClickListener != null) {
                    this.this$0.onAddTagClickListener.onAdd(view);
                }
            }
        });
        this.addView((View)this.addTagView);
        this.requestLayout();
    }
    
    public void appendTag(final CharSequence text, final FileTagType tag) {
        final FileTagView fileTagView = new FileTagView(this.context);
        fileTagView.setText(text);
        fileTagView.setTag(tag);
        fileTagView.setOnClickListener((View$OnClickListener)new View$OnClickListener(this, fileTagView) {
            final FileTagGroup this$0;
            final FileTagView val$fileTagView;
            
            public void onClick(final View view) {
                if (this.this$0.onTagClickListener != null) {
                    this.this$0.onTagClickListener.onClick(view, this.val$fileTagView.getText().toString());
                }
            }
        });
        if (this.hasAddTag) {
            this.removeViewAt(this.getChildCount() - 1);
            this.addView((View)fileTagView);
            this.appendAddTag(this.onAddTagClickListener);
        }
        else {
            this.addView((View)fileTagView);
        }
        this.requestLayout();
    }
    
    public void appendTagWithRepeatCheck(final CharSequence charSequence, final FileTagType fileTagType) {
        final int n = 0;
        int n2 = 0;
        int n3;
        while (true) {
            n3 = n;
            if (n2 >= this.getChildCount()) {
                break;
            }
            final View child = this.getChildAt(n2);
            if (child instanceof FileTagView && charSequence.equals(((FileTagView)child).getText().toString())) {
                n3 = 1;
                break;
            }
            ++n2;
        }
        if (n3 == 0) {
            this.appendTag(charSequence, fileTagType);
        }
    }
    
    public void appendTags(final List<String> list, final FileTagType tag) {
        if (list == null) {
            return;
        }
        if (this.hasAddTag) {
            this.removeViewAt(this.getChildCount() - 1);
        }
        for (final String text : list) {
            final FileTagView fileTagView = new FileTagView(this.context);
            fileTagView.setText((CharSequence)text);
            fileTagView.setTag(tag);
            fileTagView.setOnClickListener((View$OnClickListener)new View$OnClickListener(this, fileTagView) {
                final FileTagGroup this$0;
                final FileTagView val$fileTagView;
                
                public void onClick(final View view) {
                    if (this.this$0.onTagClickListener != null) {
                        this.this$0.onTagClickListener.onClick(view, this.val$fileTagView.getText().toString());
                    }
                }
            });
            this.addView((View)fileTagView);
        }
        if (this.hasAddTag) {
            this.appendAddTag(this.onAddTagClickListener);
        }
        this.requestLayout();
    }
    
    public void clearTags() {
        this.removeAllViews();
        if (this.hasAddTag) {
            this.appendAddTag(this.onAddTagClickListener);
        }
    }
    
    public List<String> getTags() {
        final ArrayList list = new ArrayList();
        for (int i = 0; i < this.getChildCount(); ++i) {
            final View child = this.getChildAt(i);
            if (child instanceof FileTagView) {
                ((List)list).add((Object)((FileTagView)child).getText().toString());
            }
        }
        return (List<String>)list;
    }
    
    protected void onLayout(final boolean b, final int n, int n2, final int n3, int paddingTop) {
        final int paddingLeft = this.getPaddingLeft();
        final int paddingRight = this.getPaddingRight();
        paddingTop = this.getPaddingTop();
        this.getPaddingBottom();
        final int childCount = this.getChildCount();
        int i = 0;
        n2 = paddingLeft;
        int n4 = 0;
        while (i < childCount) {
            final View child = this.getChildAt(i);
            final int measuredWidth = child.getMeasuredWidth();
            final int measuredHeight = child.getMeasuredHeight();
            int n5 = n4;
            int n6 = n2;
            int n7 = paddingTop;
            if (child.getVisibility() != 8) {
                int n8;
                if (n2 + measuredWidth > n3 - n - paddingRight) {
                    paddingTop += n4 + this.verticalSpacing;
                    n8 = paddingLeft;
                    n2 = measuredHeight;
                }
                else {
                    final int max = Math.max(n4, measuredHeight);
                    n8 = n2;
                    n2 = max;
                }
                child.layout(n8, paddingTop, n8 + measuredWidth, measuredHeight + paddingTop);
                n6 = n8 + (measuredWidth + this.horizontalSpacing);
                n7 = paddingTop;
                n5 = n2;
            }
            ++i;
            n4 = n5;
            n2 = n6;
            paddingTop = n7;
        }
    }
    
    protected void onMeasure(int n, int n2) {
        final int mode = View$MeasureSpec.getMode(n);
        final int mode2 = View$MeasureSpec.getMode(n2);
        int size = View$MeasureSpec.getSize(n);
        final int size2 = View$MeasureSpec.getSize(n2);
        this.measureChildren(n, n2);
        final int childCount = this.getChildCount();
        int i = 0;
        n = 0;
        int n3 = 0;
        n2 = 0;
        int n4 = 0;
        while (i < childCount) {
            final View child = this.getChildAt(i);
            final int measuredWidth = child.getMeasuredWidth();
            final int measuredHeight = child.getMeasuredHeight();
            int n5 = n;
            int n6 = n3;
            int n7 = n2;
            int n8 = n4;
            if (child.getVisibility() != 8) {
                int n9 = n4 + measuredWidth;
                int max;
                if (n9 >= size - this.getPaddingLeft() - this.getPaddingRight()) {
                    n += n3 + this.verticalSpacing;
                    ++n2;
                    n9 = measuredWidth;
                    max = measuredHeight;
                }
                else {
                    max = Math.max(n3, measuredHeight);
                }
                n8 = n9 + this.horizontalSpacing;
                n7 = n2;
                n6 = max;
                n5 = n;
            }
            ++i;
            n = n5;
            n3 = n6;
            n2 = n7;
            n4 = n8;
        }
        final int paddingTop = this.getPaddingTop();
        final int paddingBottom = this.getPaddingBottom();
        if (n2 == 0) {
            n2 = n4 + (this.getPaddingLeft() + this.getPaddingRight());
        }
        else {
            n2 = size;
        }
        if (mode != 1073741824) {
            size = n2;
        }
        if (mode2 == 1073741824) {
            n = size2;
        }
        else {
            n = n + n3 + (paddingTop + paddingBottom);
        }
        this.setMeasuredDimension(size, n);
    }
    
    public void removeTag(final String s) {
        for (int i = 0; i < this.getChildCount(); ++i) {
            final View child = this.getChildAt(i);
            if (child instanceof FileTagView) {
                final FileTagView fileTagView = (FileTagView)child;
                if (fileTagView.getText().toString().equals((Object)s)) {
                    this.removeView((View)fileTagView);
                    break;
                }
            }
        }
    }
    
    public void setAddTagViewRes(final int n, final int n2, final int n3, final String s) {
        this.addTagView.setViewBackground(n, n2, n3, s);
    }
    
    public void setOnTagClickListener(final OnTagClickListener onTagClickListener) {
        this.onTagClickListener = onTagClickListener;
    }
}

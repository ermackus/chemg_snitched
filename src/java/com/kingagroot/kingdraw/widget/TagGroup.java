package com.kingagroot.kingdraw.widget;

import android.content.res.TypedArray;
import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.view.View$BaseSavedState;
import java.util.List;
import android.os.Parcelable;
import android.view.View$MeasureSpec;
import java.util.ArrayList;
import android.view.ViewGroup$LayoutParams;
import android.util.TypedValue;
import android.view.View;
import android.view.View$OnClickListener;
import com.kingagroot.kingdraw.R$styleable;
import android.graphics.Color;
import android.util.AttributeSet;
import android.content.Context;
import android.view.ViewGroup;

public class TagGroup extends ViewGroup
{
    private final int backgroundColor;
    private final int borderColor;
    private final float borderStrokeWidth;
    private final int checkedBackgroundColor;
    private final int checkedBorderColor;
    private final int checkedMarkerColor;
    private final int checkedTextColor;
    private final int dashBorderColor;
    private final int default_background_color;
    private final int default_border_color;
    private final float default_border_stroke_width;
    private final int default_checked_background_color;
    private final int default_checked_border_color;
    private final int default_checked_marker_color;
    private final int default_checked_text_color;
    private final int default_dash_border_color;
    private final float default_horizontal_padding;
    private final float default_horizontal_spacing;
    private final int default_input_hint_color;
    private final int default_input_text_color;
    private final int default_pressed_background_color;
    private final int default_text_color;
    private final float default_text_size;
    private final float default_vertical_padding;
    private final float default_vertical_spacing;
    private final int horizontalPadding;
    private final int horizontalSpacing;
    private final CharSequence inputHint;
    private final int inputHintColor;
    private final int inputTextColor;
    private final boolean isAppendMode;
    private final InternalTagClickListener mInternalTagClickListener;
    private OnTagChangeListener mOnTagChangeListener;
    private OnTagClickListener mOnTagClickListener;
    private final int pressedBackgroundColor;
    private final int textColor;
    private final float textSize;
    private final int verticalPadding;
    private final int verticalSpacing;
    
    public TagGroup(final Context context) {
        this(context, null);
    }
    
    public TagGroup(final Context context, final AttributeSet set) {
        this(context, set, 2130969630);
    }
    
    public TagGroup(final Context context, AttributeSet obtainStyledAttributes, final int n) {
        super(context, obtainStyledAttributes, n);
        this.default_border_color = Color.rgb(73, 193, 32);
        this.default_text_color = Color.rgb(73, 193, 32);
        this.default_background_color = -1;
        this.default_dash_border_color = Color.rgb(170, 170, 170);
        this.default_input_hint_color = Color.argb(128, 0, 0, 0);
        this.default_input_text_color = Color.argb(222, 0, 0, 0);
        this.default_checked_border_color = Color.rgb(73, 193, 32);
        this.default_checked_text_color = -1;
        this.default_checked_marker_color = -1;
        this.default_checked_background_color = Color.rgb(73, 193, 32);
        this.default_pressed_background_color = Color.rgb(237, 237, 237);
        this.mInternalTagClickListener = new InternalTagClickListener();
        this.default_border_stroke_width = this.dp2px(0.5f);
        this.default_text_size = this.sp2px(13.0f);
        this.default_horizontal_spacing = this.dp2px(8.0f);
        this.default_vertical_spacing = this.dp2px(4.0f);
        this.default_horizontal_padding = this.dp2px(10.0f);
        this.default_vertical_padding = this.dp2px(3.0f);
        obtainStyledAttributes = (AttributeSet)context.obtainStyledAttributes(obtainStyledAttributes, R$styleable.TagGroup, n, 2131886439);
        try {
            this.isAppendMode = ((TypedArray)obtainStyledAttributes).getBoolean(13, false);
            this.inputHint = ((TypedArray)obtainStyledAttributes).getText(10);
            this.borderColor = ((TypedArray)obtainStyledAttributes).getColor(1, this.default_border_color);
            this.textColor = ((TypedArray)obtainStyledAttributes).getColor(15, this.default_text_color);
            this.backgroundColor = ((TypedArray)obtainStyledAttributes).getColor(0, -1);
            this.dashBorderColor = ((TypedArray)obtainStyledAttributes).getColor(7, this.default_dash_border_color);
            this.inputHintColor = ((TypedArray)obtainStyledAttributes).getColor(11, this.default_input_hint_color);
            this.inputTextColor = ((TypedArray)obtainStyledAttributes).getColor(12, this.default_input_text_color);
            this.checkedBorderColor = ((TypedArray)obtainStyledAttributes).getColor(4, this.default_checked_border_color);
            this.checkedTextColor = ((TypedArray)obtainStyledAttributes).getColor(6, -1);
            this.checkedMarkerColor = ((TypedArray)obtainStyledAttributes).getColor(5, -1);
            this.checkedBackgroundColor = ((TypedArray)obtainStyledAttributes).getColor(3, this.default_checked_background_color);
            this.pressedBackgroundColor = ((TypedArray)obtainStyledAttributes).getColor(14, this.default_pressed_background_color);
            this.borderStrokeWidth = ((TypedArray)obtainStyledAttributes).getDimension(2, this.default_border_stroke_width);
            this.textSize = ((TypedArray)obtainStyledAttributes).getDimension(16, this.default_text_size);
            this.horizontalSpacing = (int)((TypedArray)obtainStyledAttributes).getDimension(9, this.default_horizontal_spacing);
            this.verticalSpacing = (int)((TypedArray)obtainStyledAttributes).getDimension(18, this.default_vertical_spacing);
            this.horizontalPadding = (int)((TypedArray)obtainStyledAttributes).getDimension(8, this.default_horizontal_padding);
            this.verticalPadding = (int)((TypedArray)obtainStyledAttributes).getDimension(17, this.default_vertical_padding);
            ((TypedArray)obtainStyledAttributes).recycle();
            if (this.isAppendMode) {
                this.appendInputTag();
                this.setOnClickListener((View$OnClickListener)new View$OnClickListener(this) {
                    final TagGroup this$0;
                    
                    public void onClick(final View view) {
                        this.this$0.submitTag();
                    }
                });
            }
        }
        finally {
            ((TypedArray)obtainStyledAttributes).recycle();
        }
    }
    
    protected void appendInputTag() {
        this.appendInputTag(null);
    }
    
    protected void appendInputTag(final String s) {
        if (this.getInputTag() == null) {
            final TagGroup$TagView tagGroup$TagView = new TagGroup$TagView(this, this.getContext(), 2, (CharSequence)s);
            tagGroup$TagView.setOnClickListener((View$OnClickListener)this.mInternalTagClickListener);
            this.addView((View)tagGroup$TagView);
            return;
        }
        throw new IllegalStateException("Already has a INPUT tag in group.");
    }
    
    protected void appendTag(final CharSequence charSequence) {
        final TagGroup$TagView tagGroup$TagView = new TagGroup$TagView(this, this.getContext(), 1, charSequence);
        tagGroup$TagView.setOnClickListener((View$OnClickListener)this.mInternalTagClickListener);
        this.addView((View)tagGroup$TagView);
    }
    
    protected void deleteTag(final TagGroup$TagView tagGroup$TagView) {
        this.removeView((View)tagGroup$TagView);
        final OnTagChangeListener mOnTagChangeListener = this.mOnTagChangeListener;
        if (mOnTagChangeListener != null) {
            mOnTagChangeListener.onDelete(this, tagGroup$TagView.getText().toString());
        }
    }
    
    public float dp2px(final float n) {
        return TypedValue.applyDimension(1, n, this.getResources().getDisplayMetrics());
    }
    
    public ViewGroup$LayoutParams generateLayoutParams(final AttributeSet set) {
        return new LayoutParams(this.getContext(), set);
    }
    
    protected TagGroup$TagView getCheckedTag() {
        final int checkedTagIndex = this.getCheckedTagIndex();
        if (checkedTagIndex != -1) {
            return this.getTagAt(checkedTagIndex);
        }
        return null;
    }
    
    protected int getCheckedTagIndex() {
        for (int childCount = this.getChildCount(), i = 0; i < childCount; ++i) {
            if (TagGroup$TagView.access$100(this.getTagAt(i))) {
                return i;
            }
        }
        return -1;
    }
    
    protected TagGroup$TagView getInputTag() {
        if (this.isAppendMode) {
            final TagGroup$TagView tag = this.getTagAt(this.getChildCount() - 1);
            if (tag != null && TagGroup$TagView.access$000(tag) == 2) {
                return tag;
            }
        }
        return null;
    }
    
    public String getInputTagText() {
        final TagGroup$TagView inputTag = this.getInputTag();
        if (inputTag != null) {
            return inputTag.getText().toString();
        }
        return null;
    }
    
    protected TagGroup$TagView getLastNormalTagView() {
        int n;
        if (this.isAppendMode) {
            n = this.getChildCount() - 2;
        }
        else {
            n = this.getChildCount() - 1;
        }
        return this.getTagAt(n);
    }
    
    protected TagGroup$TagView getTagAt(final int n) {
        return (TagGroup$TagView)this.getChildAt(n);
    }
    
    public String[] getTags() {
        final int childCount = this.getChildCount();
        final ArrayList list = new ArrayList();
        for (int i = 0; i < childCount; ++i) {
            final TagGroup$TagView tag = this.getTagAt(i);
            if (TagGroup$TagView.access$000(tag) == 1) {
                ((List)list).add((Object)tag.getText().toString());
            }
        }
        return (String[])((List)list).toArray((Object[])new String[((List)list).size()]);
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
                if (n9 >= size) {
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
    
    public void onRestoreInstanceState(final Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        final SavedState savedState = (SavedState)parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        this.setTags(savedState.tags);
        final TagGroup$TagView tag = this.getTagAt(savedState.checkedPosition);
        if (tag != null) {
            tag.setChecked(true);
        }
        if (this.getInputTag() != null) {
            this.getInputTag().setText((CharSequence)savedState.input);
        }
    }
    
    public Parcelable onSaveInstanceState() {
        final SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.tags = this.getTags();
        savedState.checkedPosition = this.getCheckedTagIndex();
        if (this.getInputTag() != null) {
            savedState.input = this.getInputTag().getText().toString();
        }
        return (Parcelable)savedState;
    }
    
    public void setOnTagChangeListener(final OnTagChangeListener mOnTagChangeListener) {
        this.mOnTagChangeListener = mOnTagChangeListener;
    }
    
    public void setOnTagClickListener(final OnTagClickListener mOnTagClickListener) {
        this.mOnTagClickListener = mOnTagClickListener;
    }
    
    public void setTags(final List<String> list) {
        this.setTags((String[])list.toArray((Object[])new String[list.size()]));
    }
    
    public void setTags(final String... array) {
        this.removeAllViews();
        for (int length = array.length, i = 0; i < length; ++i) {
            this.appendTag((CharSequence)array[i]);
        }
        if (this.isAppendMode) {
            this.appendInputTag();
        }
    }
    
    public float sp2px(final float n) {
        return TypedValue.applyDimension(2, n, this.getResources().getDisplayMetrics());
    }
    
    public void submitTag() {
        final TagGroup$TagView inputTag = this.getInputTag();
        if (inputTag != null && inputTag.isInputAvailable()) {
            inputTag.endInput();
            final OnTagChangeListener mOnTagChangeListener = this.mOnTagChangeListener;
            if (mOnTagChangeListener != null) {
                mOnTagChangeListener.onAppend(this, inputTag.getText().toString());
            }
            this.appendInputTag();
        }
    }
    
    class InternalTagClickListener implements View$OnClickListener
    {
        final TagGroup this$0;
        
        InternalTagClickListener(final TagGroup this$0) {
            this.this$0 = this$0;
        }
        
        public void onClick(final View view) {
            final TagGroup$TagView tagGroup$TagView = (TagGroup$TagView)view;
            if (this.this$0.isAppendMode) {
                if (TagGroup$TagView.access$000(tagGroup$TagView) == 2) {
                    final TagGroup$TagView checkedTag = this.this$0.getCheckedTag();
                    if (checkedTag != null) {
                        checkedTag.setChecked(false);
                    }
                }
                else if (TagGroup$TagView.access$100(tagGroup$TagView)) {
                    this.this$0.deleteTag(tagGroup$TagView);
                }
                else {
                    final TagGroup$TagView checkedTag2 = this.this$0.getCheckedTag();
                    if (checkedTag2 != null) {
                        checkedTag2.setChecked(false);
                    }
                    tagGroup$TagView.setChecked(true);
                }
            }
            else if (this.this$0.mOnTagClickListener != null) {
                this.this$0.mOnTagClickListener.onTagClick(tagGroup$TagView.getText().toString());
            }
        }
    }
    
    public static class LayoutParams extends ViewGroup$LayoutParams
    {
        public LayoutParams(final int n, final int n2) {
            super(n, n2);
        }
        
        public LayoutParams(final Context context, final AttributeSet set) {
            super(context, set);
        }
    }
    
    public interface OnTagChangeListener
    {
        void onAppend(final TagGroup p0, final String p1);
        
        void onDelete(final TagGroup p0, final String p1);
    }
    
    public interface OnTagClickListener
    {
        void onTagClick(final String p0);
    }
    
    static class SavedState extends View$BaseSavedState
    {
        public static final Parcelable$Creator<SavedState> CREATOR;
        int checkedPosition;
        String input;
        int tagCount;
        String[] tags;
        
        static {
            CREATOR = (Parcelable$Creator)new Parcelable$Creator<SavedState>() {
                public SavedState createFromParcel(final Parcel parcel) {
                    return new SavedState(parcel);
                }
                
                public SavedState[] newArray(final int n) {
                    return new SavedState[n];
                }
            };
        }
        
        public SavedState(final Parcel parcel) {
            super(parcel);
            final int int1 = parcel.readInt();
            this.tagCount = int1;
            parcel.readStringArray(this.tags = new String[int1]);
            this.checkedPosition = parcel.readInt();
            this.input = parcel.readString();
        }
        
        public SavedState(final Parcelable parcelable) {
            super(parcelable);
        }
        
        public void writeToParcel(final Parcel parcel, int length) {
            super.writeToParcel(parcel, length);
            length = this.tags.length;
            parcel.writeInt(this.tagCount = length);
            parcel.writeStringArray(this.tags);
            parcel.writeInt(this.checkedPosition);
            parcel.writeString(this.input);
        }
    }
}

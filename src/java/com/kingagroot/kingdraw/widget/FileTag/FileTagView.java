package com.kingagroot.kingdraw.widget.FileTag;

import android.graphics.Canvas;
import com.kingagroot.kingdraw.core.utils.GDensityUtil;
import android.util.AttributeSet;
import android.graphics.drawable.Drawable;
import android.content.Context;
import androidx.appcompat.widget.AppCompatTextView;

public class FileTagView extends AppCompatTextView
{
    private final Context context;
    private Drawable cornerDrawable;
    int height;
    private FileTagType tagType;
    private int width;
    
    public FileTagView(final Context context) {
        this(context, null);
    }
    
    public FileTagView(final Context context, final AttributeSet set) {
        this(context, set, 0);
    }
    
    public FileTagView(final Context context, final AttributeSet set, int height) {
        super(context, set, height);
        this.context = context;
        this.setTextColor(-16777216);
        this.setTextSize(2, 14.0f);
        final int dp2px = GDensityUtil.dp2px(10.0f);
        height = GDensityUtil.dp2px(4.0f);
        this.setPadding(dp2px, height, dp2px, height);
        this.setSingleLine();
        height = GDensityUtil.dp2px(28.0f);
        this.setMinWidth(this.height = height);
        this.setHeight(this.height);
    }
    
    public void draw(final Canvas canvas) {
        super.draw(canvas);
        if (this.cornerDrawable != null) {
            final int dp2px = GDensityUtil.dp2px(18.0f);
            final int dp2px2 = GDensityUtil.dp2px(18.0f);
            final int n = this.width - dp2px;
            this.cornerDrawable.setBounds(n, 0, dp2px + n, dp2px2 + 0);
            this.cornerDrawable.draw(canvas);
        }
    }
    
    public FileTagType getTagType() {
        return this.tagType;
    }
    
    protected void onSizeChanged(final int width, final int n, final int n2, final int n3) {
        super.onSizeChanged(width, n, n2, n3);
        this.width = width;
    }
    
    public void setTag(final FileTagType tagType) {
        this.tagType = tagType;
        if (tagType == FileTagType.delete) {
            this.setBackgroundResource(2131230918);
            this.cornerDrawable = this.context.getResources().getDrawable(2131231231);
        }
        else if (tagType == FileTagType.add) {
            this.setBackgroundResource(2131230915);
            this.cornerDrawable = this.context.getResources().getDrawable(2131231081);
        }
        else {
            this.setBackgroundResource(2131230918);
        }
    }
}

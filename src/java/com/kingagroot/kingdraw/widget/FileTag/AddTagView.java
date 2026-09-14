package com.kingagroot.kingdraw.widget.FileTag;

import android.text.TextUtils;
import com.kingagroot.kingdraw.core.utils.GDensityUtil;
import android.view.View$MeasureSpec;
import android.graphics.Color;
import android.util.AttributeSet;
import android.content.Context;
import androidx.appcompat.widget.AppCompatTextView;

public class AddTagView extends AppCompatTextView
{
    int height;
    int width;
    
    public AddTagView(final Context context) {
        this(context, null);
    }
    
    public AddTagView(final Context context, final AttributeSet set) {
        this(context, set, 0);
    }
    
    public AddTagView(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
    }
    
    private void setAddTagText(final String text) {
        this.setText((CharSequence)text);
        this.setTextSize(12.0f);
        this.setGravity(17);
        this.setTextColor(Color.parseColor("#FF178BFF"));
    }
    
    protected void onMeasure(final int n, final int n2) {
        super.onMeasure(View$MeasureSpec.makeMeasureSpec(this.width, 1073741824), View$MeasureSpec.makeMeasureSpec(this.height, 1073741824));
    }
    
    public void setViewBackground(final int backgroundResource, final int n, final int n2, final String addTagText) {
        this.setBackgroundResource(backgroundResource);
        this.width = GDensityUtil.dp2px((float)n);
        this.height = GDensityUtil.dp2px((float)n2);
        if (!TextUtils.isEmpty((CharSequence)addTagText)) {
            this.setAddTagText(addTagText);
        }
    }
}

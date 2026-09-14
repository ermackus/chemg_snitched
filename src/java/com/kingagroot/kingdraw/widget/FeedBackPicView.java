package com.kingagroot.kingdraw.widget;

import android.view.View$MeasureSpec;
import com.kingagroot.kingdraw.utils.ImageLoader;
import android.view.View;
import android.view.View$OnClickListener;
import android.view.ViewGroup$LayoutParams;
import android.widget.ImageView$ScaleType;
import android.text.TextUtils;
import android.widget.ImageView;
import android.util.AttributeSet;
import com.kingagroot.kingdraw.core.utils.GDensityUtil;
import android.content.Context;
import android.widget.LinearLayout$LayoutParams;
import java.util.List;
import android.widget.LinearLayout;

public class FeedBackPicView extends LinearLayout
{
    public static int MAX_WIDTH;
    private final int MAX_PER_ROW_COUNT;
    private List<String> imagesList;
    private OnItemClickListener mOnItemClickListener;
    private LinearLayout$LayoutParams morePara;
    private LinearLayout$LayoutParams moreParaColumnFirst;
    private final int pxImagePadding;
    private int pxMoreWandH;
    private LinearLayout$LayoutParams rowPara;
    
    public FeedBackPicView(final Context context) {
        super(context);
        this.pxMoreWandH = 0;
        this.pxImagePadding = GDensityUtil.dp2px(3.0f);
        this.MAX_PER_ROW_COUNT = 6;
    }
    
    public FeedBackPicView(final Context context, final AttributeSet set) {
        super(context, set);
        this.pxMoreWandH = 0;
        this.pxImagePadding = GDensityUtil.dp2px(3.0f);
        this.MAX_PER_ROW_COUNT = 6;
    }
    
    private ImageView createImageView(final int n) {
        String s;
        if (!TextUtils.isEmpty((CharSequence)this.imagesList.get(n))) {
            s = (String)this.imagesList.get(n);
        }
        else {
            s = "";
        }
        final ImageView imageView = new ImageView(this.getContext());
        imageView.setScaleType(ImageView$ScaleType.CENTER_CROP);
        LinearLayout$LayoutParams layoutParams;
        if (n % 6 == 0) {
            layoutParams = this.moreParaColumnFirst;
        }
        else {
            layoutParams = this.morePara;
        }
        imageView.setLayoutParams((ViewGroup$LayoutParams)layoutParams);
        imageView.setId(s.hashCode());
        imageView.setOnClickListener((View$OnClickListener)new View$OnClickListener(this, n) {
            final FeedBackPicView this$0;
            final int val$position;
            
            public void onClick(final View view) {
                if (this.this$0.mOnItemClickListener != null) {
                    this.this$0.mOnItemClickListener.onItemClick(view, this.val$position);
                }
            }
        });
        ImageLoader.bind(imageView, s, true);
        return imageView;
    }
    
    private void initImageLayoutParams() {
        final int pxMoreWandH = this.pxMoreWandH;
        this.moreParaColumnFirst = new LinearLayout$LayoutParams(pxMoreWandH, pxMoreWandH);
        final int pxMoreWandH2 = this.pxMoreWandH;
        (this.morePara = new LinearLayout$LayoutParams(pxMoreWandH2, pxMoreWandH2)).setMargins(this.pxImagePadding, 0, 0, 0);
        this.rowPara = new LinearLayout$LayoutParams(-1, -2);
    }
    
    private void initView() {
        this.setOrientation(1);
        this.removeAllViews();
        if (FeedBackPicView.MAX_WIDTH == 0) {
            this.addView(new View(this.getContext()));
            return;
        }
        final List<String> imagesList = this.imagesList;
        if (imagesList != null) {
            if (imagesList.size() != 0) {
                for (int i = 0; i < 1; ++i) {
                    final LinearLayout linearLayout = new LinearLayout(this.getContext());
                    linearLayout.setOrientation(0);
                    linearLayout.setLayoutParams((ViewGroup$LayoutParams)this.rowPara);
                    if (i != 0) {
                        linearLayout.setPadding(0, this.pxImagePadding, 0, 0);
                    }
                    int size = this.imagesList.size();
                    if (i != 0) {
                        size = 6;
                    }
                    this.addView((View)linearLayout);
                    for (int j = 0; j < size; ++j) {
                        linearLayout.addView((View)this.createImageView(j + i * 6));
                    }
                }
            }
        }
    }
    
    private int measureWidth(int n) {
        final int mode = View$MeasureSpec.getMode(n);
        n = View$MeasureSpec.getSize(n);
        if (mode != 1073741824) {
            if (mode == Integer.MIN_VALUE) {
                n = Math.min(0, n);
            }
            else {
                n = 0;
            }
        }
        return n;
    }
    
    protected void onMeasure(final int n, final int n2) {
        if (FeedBackPicView.MAX_WIDTH == 0) {
            final int measureWidth = this.measureWidth(n);
            if (measureWidth > 0) {
                FeedBackPicView.MAX_WIDTH = measureWidth - this.getPaddingLeft() - this.getPaddingRight();
                final List<String> imagesList = this.imagesList;
                if (imagesList != null && imagesList.size() > 0) {
                    this.setList(this.imagesList);
                }
            }
        }
        super.onMeasure(n, n2);
    }
    
    public void setList(final List<String> imagesList) throws IllegalArgumentException {
        if (imagesList != null) {
            this.imagesList = imagesList;
            final int max_WIDTH = FeedBackPicView.MAX_WIDTH;
            if (max_WIDTH > 0) {
                this.pxMoreWandH = (max_WIDTH - this.pxImagePadding * 2) / 6;
                this.initImageLayoutParams();
            }
            this.initView();
            return;
        }
        throw new IllegalArgumentException("imageList is null...");
    }
    
    public void setOnItemClickListener(final OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }
    
    public interface OnItemClickListener
    {
        void onItemClick(final View p0, final int p1);
    }
}

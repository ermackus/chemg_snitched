package com.goodsrc.ui.library.widget.fastAdapter;

import android.graphics.Typeface;
import android.widget.RatingBar;
import android.view.View$OnTouchListener;
import android.view.View$OnLongClickListener;
import android.view.View$OnClickListener;
import android.widget.ProgressBar;
import android.widget.ImageView;
import android.graphics.Bitmap;
import android.widget.TextView$BufferType;
import android.text.Html;
import android.graphics.drawable.Drawable;
import android.widget.Checkable;
import android.view.animation.Animation;
import android.view.animation.AlphaAnimation;
import android.os.Build$VERSION;
import android.text.util.Linkify;
import android.widget.TextView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.util.SparseArray;
import android.view.View;
import android.content.Context;

public class ViewHolder
{
    private final Context mContext;
    private final View mConvertView;
    private final int mLayoutId;
    private int mPosition;
    private final SparseArray<View> mViews;
    
    public ViewHolder(final Context mContext, final ViewGroup viewGroup, final int mLayoutId, final int mPosition) {
        this.mContext = mContext;
        this.mLayoutId = mLayoutId;
        this.mPosition = mPosition;
        this.mViews = (SparseArray<View>)new SparseArray();
        (this.mConvertView = LayoutInflater.from(mContext).inflate(mLayoutId, viewGroup, false)).setTag((Object)this);
    }
    
    public static ViewHolder get(final Context context, final View view, final ViewGroup viewGroup, final int n, final int mPosition) {
        if (view == null) {
            return new ViewHolder(context, viewGroup, n, mPosition);
        }
        final ViewHolder viewHolder = (ViewHolder)view.getTag();
        viewHolder.mPosition = mPosition;
        return viewHolder;
    }
    
    public View getConvertView() {
        return this.mConvertView;
    }
    
    public int getLayoutId() {
        return this.mLayoutId;
    }
    
    public int getPosition() {
        return this.mPosition;
    }
    
    public <T extends View> T getView(final int n) {
        View viewById;
        if ((viewById = (View)this.mViews.get(n)) == null) {
            viewById = this.mConvertView.findViewById(n);
            this.mViews.put(n, (Object)viewById);
        }
        return (T)viewById;
    }
    
    public ViewHolder linkify(final int n) {
        Linkify.addLinks((TextView)this.getView(n), 15);
        return this;
    }
    
    public ViewHolder setAlpha(final int n, final float alpha) {
        if (Build$VERSION.SDK_INT >= 11) {
            this.getView(n).setAlpha(alpha);
        }
        else {
            final AlphaAnimation alphaAnimation = new AlphaAnimation(alpha, alpha);
            alphaAnimation.setDuration(0L);
            alphaAnimation.setFillAfter(true);
            this.getView(n).startAnimation((Animation)alphaAnimation);
        }
        return this;
    }
    
    public ViewHolder setBackgroundColor(final int n, final int backgroundColor) {
        this.getView(n).setBackgroundColor(backgroundColor);
        return this;
    }
    
    public ViewHolder setBackgroundRes(final int n, final int backgroundResource) {
        this.getView(n).setBackgroundResource(backgroundResource);
        return this;
    }
    
    public ViewHolder setChecked(final int n, final boolean checked) {
        this.getView(n).setChecked(checked);
        return this;
    }
    
    public ViewHolder setCompoundDrawables(final int n, final Drawable drawable) {
        this.getView(n).setCompoundDrawables(drawable, (Drawable)null, (Drawable)null, (Drawable)null);
        return this;
    }
    
    public ViewHolder setHtmlText(final int n, final String s) {
        this.getView(n).setText((CharSequence)Html.fromHtml(s), TextView$BufferType.SPANNABLE);
        return this;
    }
    
    public ViewHolder setImageBitmap(final int n, final Bitmap imageBitmap) {
        this.getView(n).setImageBitmap(imageBitmap);
        return this;
    }
    
    public ViewHolder setImageDrawable(final int n, final Drawable imageDrawable) {
        this.getView(n).setImageDrawable(imageDrawable);
        return this;
    }
    
    public ViewHolder setImageResource(final int n, final int imageResource) {
        this.getView(n).setImageResource(imageResource);
        return this;
    }
    
    public ViewHolder setMax(final int n, final int max) {
        this.getView(n).setMax(max);
        return this;
    }
    
    public ViewHolder setOnClickListener(final int n, final View$OnClickListener onClickListener) {
        this.getView(n).setOnClickListener(onClickListener);
        return this;
    }
    
    public ViewHolder setOnLongClickListener(final int n, final View$OnLongClickListener onLongClickListener) {
        this.getView(n).setOnLongClickListener(onLongClickListener);
        return this;
    }
    
    public ViewHolder setOnTouchListener(final int n, final View$OnTouchListener onTouchListener) {
        this.getView(n).setOnTouchListener(onTouchListener);
        return this;
    }
    
    public ViewHolder setProgress(final int n, final int progress) {
        this.getView(n).setProgress(progress);
        return this;
    }
    
    public ViewHolder setProgress(final int n, final int progress, final int max) {
        final ProgressBar progressBar = this.getView(n);
        progressBar.setMax(max);
        progressBar.setProgress(progress);
        return this;
    }
    
    public ViewHolder setRating(final int n, final float rating) {
        this.getView(n).setRating(rating);
        return this;
    }
    
    public ViewHolder setRating(final int n, final float rating, final int max) {
        final RatingBar ratingBar = this.getView(n);
        ratingBar.setMax(max);
        ratingBar.setRating(rating);
        return this;
    }
    
    public ViewHolder setTag(final int n, final int n2, final Object o) {
        this.getView(n).setTag(n2, o);
        return this;
    }
    
    public ViewHolder setTag(final int n, final Object tag) {
        this.getView(n).setTag(tag);
        return this;
    }
    
    public ViewHolder setText(final int n, final String text) {
        this.getView(n).setText((CharSequence)text);
        return this;
    }
    
    public ViewHolder setTextColor(final int n, final int textColor) {
        this.getView(n).setTextColor(textColor);
        return this;
    }
    
    public ViewHolder setTextColorRes(final int n, final int n2) {
        this.getView(n).setTextColor(this.mContext.getResources().getColor(n2));
        return this;
    }
    
    public ViewHolder setTextDrawable(final int n, final Drawable drawable) {
        final TextView textView = this.getView(n);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        textView.setCompoundDrawables((Drawable)null, (Drawable)null, drawable, (Drawable)null);
        return this;
    }
    
    public ViewHolder setTypeface(final Typeface typeface, final int... array) {
        for (int length = array.length, i = 0; i < length; ++i) {
            final TextView textView = this.getView(array[i]);
            textView.setTypeface(typeface);
            textView.setPaintFlags(textView.getPaintFlags() | 0x80);
        }
        return this;
    }
    
    public ViewHolder setVisible(int visibility, final boolean b) {
        final View view = this.getView(visibility);
        if (b) {
            visibility = 0;
        }
        else {
            visibility = 8;
        }
        view.setVisibility(visibility);
        return this;
    }
}

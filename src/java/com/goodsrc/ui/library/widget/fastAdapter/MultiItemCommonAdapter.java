package com.goodsrc.ui.library.widget.fastAdapter;

import android.view.ViewGroup;
import android.view.View;
import java.util.List;
import android.content.Context;

public abstract class MultiItemCommonAdapter<T> extends CommonAdapter<T>
{
    protected MultiItemTypeSupport<T> mMultiItemTypeSupport;
    
    public MultiItemCommonAdapter(final Context context, final List<T> list, final MultiItemTypeSupport<T> mMultiItemTypeSupport) {
        super(context, list, -1);
        this.mMultiItemTypeSupport = mMultiItemTypeSupport;
    }
    
    public int getItemViewType(final int n) {
        final MultiItemTypeSupport<T> mMultiItemTypeSupport = this.mMultiItemTypeSupport;
        if (mMultiItemTypeSupport != null) {
            return mMultiItemTypeSupport.getItemViewType(n, (T)this.mDatas.get(n));
        }
        return super.getItemViewType(n);
    }
    
    @Override
    public View getView(final int n, final View view, final ViewGroup viewGroup) {
        final MultiItemTypeSupport<T> mMultiItemTypeSupport = this.mMultiItemTypeSupport;
        if (mMultiItemTypeSupport == null) {
            return super.getView(n, view, viewGroup);
        }
        final ViewHolder value = ViewHolder.get(this.mContext, view, viewGroup, mMultiItemTypeSupport.getLayoutId(n, this.getItem(n)), n);
        this.convert(value, this.getItem(n));
        return value.getConvertView();
    }
    
    public int getViewTypeCount() {
        final MultiItemTypeSupport<T> mMultiItemTypeSupport = this.mMultiItemTypeSupport;
        if (mMultiItemTypeSupport != null) {
            return mMultiItemTypeSupport.getViewTypeCount();
        }
        return super.getViewTypeCount();
    }
}

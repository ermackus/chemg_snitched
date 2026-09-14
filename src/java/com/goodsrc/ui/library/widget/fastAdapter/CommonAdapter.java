package com.goodsrc.ui.library.widget.fastAdapter;

import android.view.ViewGroup;
import android.view.View;
import android.view.LayoutInflater;
import java.util.List;
import android.content.Context;
import android.widget.BaseAdapter;

public abstract class CommonAdapter<T> extends BaseAdapter
{
    private final int layoutId;
    protected Context mContext;
    protected List<T> mDatas;
    protected LayoutInflater mInflater;
    
    public CommonAdapter(final Context mContext, final List<T> mDatas, final int layoutId) {
        this.mContext = mContext;
        this.mInflater = LayoutInflater.from(mContext);
        this.mDatas = mDatas;
        this.layoutId = layoutId;
    }
    
    public abstract void convert(final ViewHolder p0, final T p1);
    
    public int getCount() {
        final List<T> mDatas = this.mDatas;
        if (mDatas == null) {
            return 0;
        }
        return mDatas.size();
    }
    
    public T getItem(final int n) {
        return (T)this.mDatas.get(n);
    }
    
    public long getItemId(final int n) {
        return n;
    }
    
    public View getView(final int n, final View view, final ViewGroup viewGroup) {
        final ViewHolder value = ViewHolder.get(this.mContext, view, viewGroup, this.layoutId, n);
        this.convert(value, this.getItem(n));
        return value.getConvertView();
    }
}

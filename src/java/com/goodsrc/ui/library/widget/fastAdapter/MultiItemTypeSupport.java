package com.goodsrc.ui.library.widget.fastAdapter;

public interface MultiItemTypeSupport<T>
{
    int getItemViewType(final int p0, final T p1);
    
    int getLayoutId(final int p0, final T p1);
    
    int getViewTypeCount();
}

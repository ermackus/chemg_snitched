package com.lzf.easyfloat.interfaces;

import com.lzf.easyfloat.widget.BaseSwitchView;
import kotlin.Metadata;

@Metadata(bv = { 1, 0, 3 }, d1 = { "\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&J\b\u0010\b\u001a\u00020\u0003H&¨\u0006\t" }, d2 = { "Lcom/lzf/easyfloat/interfaces/OnTouchRangeListener;", "", "touchInRange", "", "inRange", "", "view", "Lcom/lzf/easyfloat/widget/BaseSwitchView;", "touchUpInRange", "easyfloat_release" }, k = 1, mv = { 1, 4, 1 })
public interface OnTouchRangeListener
{
    void touchInRange(final boolean p0, final BaseSwitchView p1);
    
    void touchUpInRange();
}

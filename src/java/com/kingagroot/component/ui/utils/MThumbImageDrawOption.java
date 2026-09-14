package com.kingagroot.component.ui.utils;

import android.content.Context;
import com.goodsrc.library.utils.SystemUtils;
import com.kingagroot.component.ui.UIComponentHelper;
import com.kingagroot.kingdraw.core.image.ThumbImageDrawOption;

public class MThumbImageDrawOption extends ThumbImageDrawOption
{
    public MThumbImageDrawOption() {
        final int n = SystemUtils.getScreenDefaultWidth((Context)UIComponentHelper.getInstance()) / 5;
        final int n2 = n / 2;
        this.setWidth((float)n);
        this.setHeight((float)n2);
    }
}

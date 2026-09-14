package com.luck.picture.lib.interfaces;

import com.luck.picture.lib.config.SelectorConfig;
import com.luck.picture.lib.entity.LocalMedia;
import android.content.Context;

public interface OnSelectLimitTipsListener
{
    boolean onSelectLimitTips(final Context p0, final LocalMedia p1, final SelectorConfig p2, final int p3);
}

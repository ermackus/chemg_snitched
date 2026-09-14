package com.luck.picture.lib.basic;

import android.os.Bundle;
import android.view.View;
import androidx.fragment.app.Fragment;

public interface IBridgeViewLifecycle
{
    void onDestroy(final Fragment p0);
    
    void onViewCreated(final Fragment p0, final View p1, final Bundle p2);
}

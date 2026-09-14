package org.xutils;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;

public interface ViewInjector
{
    View inject(final Object p0, final LayoutInflater p1, final ViewGroup p2);
    
    void inject(final Activity p0);
    
    void inject(final View p0);
    
    void inject(final Object p0, final View p1);
}

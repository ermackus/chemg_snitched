package org.xutils.view;

import android.view.View;
import android.app.Activity;

final class ViewFinder
{
    private Activity activity;
    private View view;
    
    public ViewFinder(final Activity activity) {
        this.activity = activity;
    }
    
    public ViewFinder(final View view) {
        this.view = view;
    }
    
    public View findViewById(final int n) {
        final View view = this.view;
        if (view != null) {
            return view.findViewById(n);
        }
        final Activity activity = this.activity;
        if (activity != null) {
            return activity.findViewById(n);
        }
        return null;
    }
    
    public View findViewById(final int n, final int n2) {
        View viewById;
        if (n2 > 0) {
            viewById = this.findViewById(n2);
        }
        else {
            viewById = null;
        }
        View view;
        if (viewById != null) {
            view = viewById.findViewById(n);
        }
        else {
            view = this.findViewById(n);
        }
        return view;
    }
    
    public View findViewByInfo(final ViewInfo viewInfo) {
        return this.findViewById(viewInfo.value, viewInfo.parentId);
    }
}

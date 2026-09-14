package com.tencent.open.c;

import android.app.Activity;
import android.view.View$MeasureSpec;
import android.content.Context;
import android.graphics.Rect;
import android.widget.RelativeLayout;

public class a extends RelativeLayout
{
    private static final String a;
    private Rect b;
    private boolean c;
    private a d;
    
    static {
        a = a.class.getName();
    }
    
    public a(final Context context) {
        super(context);
        this.b = null;
        this.c = false;
        this.d = null;
        if (!false) {
            this.b = new Rect();
        }
    }
    
    public void a(final a d) {
        this.d = d;
    }
    
    protected void onMeasure(final int n, final int n2) {
        final int size = View$MeasureSpec.getSize(n2);
        final Activity activity = (Activity)this.getContext();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(this.b);
        final int top = this.b.top;
        final int height = activity.getWindowManager().getDefaultDisplay().getHeight();
        final a d = this.d;
        if (d != null && size != 0) {
            if (height - top - size > 100) {
                d.a(Math.abs(this.b.height()) - this.getPaddingBottom() - this.getPaddingTop());
            }
            else {
                d.a();
            }
        }
        super.onMeasure(n, n2);
    }
    
    public interface a
    {
        void a();
        
        void a(final int p0);
    }
}

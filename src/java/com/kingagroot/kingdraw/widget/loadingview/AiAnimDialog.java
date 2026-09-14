package com.kingagroot.kingdraw.widget.loadingview;

import android.os.Bundle;
import android.content.Context;
import android.app.Dialog;

public class AiAnimDialog extends Dialog
{
    AiLoadingView aiLoadingView;
    
    public AiAnimDialog(final Context context) {
        super(context, 2131886849);
    }
    
    public void dismiss() {
        this.aiLoadingView.stop();
        super.dismiss();
    }
    
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.requestWindowFeature(1);
        this.setContentView(2131493016);
        (this.aiLoadingView = (AiLoadingView)this.findViewById(2131296355)).playAnimotion();
        this.setCanceledOnTouchOutside(false);
    }
}

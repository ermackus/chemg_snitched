package com.kingagroot.kingdraw.ui;

import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.CompoundButton$OnCheckedChangeListener;
import com.kingagroot.kingdraw.config.ShareData;
import androidx.appcompat.widget.SwitchCompat;
import com.goodsrc.ui.library.ToolBarActivity;

public class LabActivity extends ToolBarActivity
{
    private void initView() {
        final SwitchCompat switchCompat = (SwitchCompat)this.findViewById(2131297439);
        switchCompat.setChecked(ShareData.getPicAiStatus());
        switchCompat.setOnCheckedChangeListener((CompoundButton$OnCheckedChangeListener)_$$Lambda$LabActivity$Kl3FeGF1FZexVcvC_SLFNV47BnI.INSTANCE);
    }
    
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.setContentView(2131492916);
        this.setTitle(2131820963);
        this.initView();
    }
}

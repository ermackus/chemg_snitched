package com.kingagroot.kingdraw.ui;

import java.io.Serializable;
import android.content.Context;
import android.content.Intent;
import com.kingagroot.component.ui.model.GSGroupModel;
import android.content.res.Configuration;
import android.os.Bundle;
import com.kingagroot.component.ui.BaseSupActivity;

public class SUPTableActivity extends BaseSupActivity
{
    @Override
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        if (this.getResources().getConfiguration().orientation == 2) {
            this.gridSup.setNumColumns(7);
        }
        else {
            this.gridSup.setNumColumns(4);
        }
        final Configuration configuration = this.getResources().getConfiguration();
        if (configuration.orientation == 2) {
            this.setRequestedOrientation(11);
        }
        else if (configuration.orientation == 1) {
            this.setRequestedOrientation(1);
        }
    }
    
    @Override
    protected void onSupPaletteEdit(final GSGroupModel gsGroupModel) {
        final Intent intent = new Intent((Context)this, (Class)SupPaletteActivity.class);
        if (gsGroupModel != null) {
            intent.putExtra(BaseSupActivity.KEY_SUP_FILE, (Serializable)gsGroupModel);
        }
        this.startActivity(intent);
    }
}

package com.kingagroot.kingdraw.ui;

import android.os.Bundle;
import com.kingagroot.kingdraw.config.APIConfig;
import com.goodsrc.library.utils.LanguageTool;
import com.goodsrc.library.core.LibraryApplication;
import android.content.Context;
import android.content.Intent;
import com.kingagroot.component.ui.utils.CheckDoubleClick;
import android.view.View;
import android.widget.RelativeLayout;
import android.view.View$OnClickListener;
import com.goodsrc.ui.library.ToolBarActivity;

public class HelpActivity extends ToolBarActivity implements View$OnClickListener
{
    private RelativeLayout rlInstructions;
    private RelativeLayout rlNewFunction;
    
    public void onClick(final View view) {
        if (view == this.rlNewFunction) {
            if (CheckDoubleClick.isFastDoubleClick()) {
                return;
            }
            final Intent intent = new Intent((Context)this, (Class)WebViewActivity.class);
            intent.putExtra("title_key", this.getString(2131821070));
            if (LibraryApplication.getLanguage().equals((Object)LanguageTool.SER_ZH)) {
                intent.putExtra("url_key", APIConfig.NEW_VERSION_ZH);
            }
            else {
                intent.putExtra("url_key", APIConfig.NEW_VERSION_EN);
            }
            this.startActivity(intent);
        }
        else if (view == this.rlInstructions) {
            if (CheckDoubleClick.isFastDoubleClick()) {
                return;
            }
            final Intent intent2 = new Intent((Context)this, (Class)WebViewActivity.class);
            intent2.putExtra("title_key", this.getString(2131820947));
            if (LibraryApplication.getLanguage().equals((Object)LanguageTool.SER_ZH)) {
                intent2.putExtra("url_key", APIConfig.HELP_ZH);
            }
            else {
                intent2.putExtra("url_key", APIConfig.HELP_EN);
            }
            this.startActivity(intent2);
        }
    }
    
    public void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.setContentView(2131493048);
        this.setTitle(2131820933);
        this.rlNewFunction = (RelativeLayout)this.findViewById(2131297300);
        this.rlInstructions = (RelativeLayout)this.findViewById(2131297294);
        this.rlNewFunction.setOnClickListener((View$OnClickListener)this);
        this.rlInstructions.setOnClickListener((View$OnClickListener)this);
    }
}

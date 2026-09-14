package com.kingagroot.kingdraw.widget.paletteMenu;

import com.kingagroot.kingdraw.config.APIConfig;
import com.goodsrc.library.utils.LanguageTool;
import com.goodsrc.library.core.LibraryApplication;
import android.content.Intent;
import com.kingagroot.kingdraw.ui.WebViewActivity;
import android.view.View;
import android.view.View$OnClickListener;
import android.content.Context;
import com.kingagroot.component.ui.menu.menuitem.HelpItemView;

public class KdHelpItemView extends HelpItemView
{
    public KdHelpItemView(final Context context) {
        super(context);
        this.setOnClickListener((View$OnClickListener)this);
    }
    
    public void onClick(final View view) {
        super.onClick(view);
        final Intent intent = new Intent(this.context, (Class)WebViewActivity.class);
        intent.putExtra("title_key", this.context.getString(2131820947));
        if (LibraryApplication.getLanguage().equals((Object)LanguageTool.SER_ZH)) {
            intent.putExtra("url_key", APIConfig.HELP_ZH);
        }
        else {
            intent.putExtra("url_key", APIConfig.HELP_EN);
        }
        this.context.startActivity(intent);
    }
}

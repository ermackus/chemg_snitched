package com.kingagroot.kingdraw.widget.paletteMenu;

import android.app.Activity;
import java.io.Serializable;
import com.kingagroot.component.ui.model.GFormatValue;
import android.content.Intent;
import com.kingagroot.kingdraw.ui.OtherSettingActivity;
import android.view.View;
import android.view.View$OnClickListener;
import android.content.Context;
import com.kingagroot.component.ui.menu.menuitem.SettingItemView;

public class KdSettingItemView extends SettingItemView
{
    public static int REQUEST_CODE_SET_FORMAT = 10001;
    public static final String RESULT_FORMATVALUE_KEY = "result_formatvalue_key";
    
    public KdSettingItemView(final Context context) {
        super(context);
        this.setOnClickListener((View$OnClickListener)this);
    }
    
    public void onClick(final View view) {
        super.onClick(view);
        final Intent intent = new Intent(this.context, (Class)OtherSettingActivity.class);
        intent.putExtra("intent_key_format", (Serializable)GFormatValue.formatValue(this.kingDrawView.getFormatValue()));
        intent.putExtra("intent_key_color", this.kingDrawView.getColorAtom());
        ((Activity)this.context).startActivityForResult(intent, KdSettingItemView.REQUEST_CODE_SET_FORMAT);
    }
}

package com.kingagroot.kingdraw.widget.paletteMenu;

import com.kingagroot.component.ui.model.GDocumentTypeEnum;
import android.view.View;
import com.kingagroot.component.ui.db.impl.GFormatValueDBImpl;
import android.content.Context;
import com.kingagroot.component.ui.db.GFormatValueDBI;
import com.kingagroot.component.ui.model.GFormatValue;
import com.kingagroot.component.ui.menu.menuitem.FormatItemView;

public class KdFormatItemView extends FormatItemView
{
    private KdFormatItemView.KdFormatItemView$OnFormatMenuListner formatMenuListner;
    private GFormatValue formatValue;
    private final GFormatValueDBI gFormatValueDBI;
    
    public KdFormatItemView(final Context context) {
        super(context);
        this.gFormatValueDBI = (GFormatValueDBI)new GFormatValueDBImpl();
    }
    
    public void onClick(final View view) {
        super.onClick(view);
        GFormatValue gFormatValue;
        if (this.isCheck) {
            gFormatValue = this.gFormatValueDBI.readerFormatForType(GDocumentTypeEnum.ACS96\u683c\u5f0f);
        }
        else {
            if (this.formatValue == null) {
                this.formatValue = this.gFormatValueDBI.readerFormatForType(GDocumentTypeEnum.KingDraw\u683c\u5f0f);
            }
            gFormatValue = this.formatValue;
        }
        final KdFormatItemView.KdFormatItemView$OnFormatMenuListner formatMenuListner = this.formatMenuListner;
        if (formatMenuListner != null) {
            formatMenuListner.applyFormatValue(gFormatValue, this.isCheck);
        }
    }
    
    public void setFormatMenuListner(final KdFormatItemView.KdFormatItemView$OnFormatMenuListner formatMenuListner) {
        this.formatMenuListner = formatMenuListner;
    }
    
    public void setFormatValue(final GFormatValue formatValue) {
        if (formatValue.getDocumentType() != GDocumentTypeEnum.ACS96\u683c\u5f0f.getCode()) {
            this.formatValue = formatValue;
            this.setCheck(false);
        }
        else {
            this.setCheck(true);
        }
    }
}

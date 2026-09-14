package com.kingagroot.component.ui.menu.menuitem;

import com.goodsrc.library.utils.ToastUtil;
import com.kingagroot.component.ui.R$string;
import com.kingagroot.component.ui.widget.AnalysisPop;
import com.goodsrc.library.utils.GsonUtil;
import com.kingagroot.kingdraw.core.tool.ChemisAnalysisTool;
import com.kingagroot.component.ui.model.ChemicalAnalysisModel;
import android.view.View;
import android.view.View$OnClickListener;
import com.kingagroot.component.ui.R$drawable;
import android.content.Context;

public class ChemInfoItemView extends ToolBaseItemView
{
    public ChemInfoItemView(final Context context) {
        super(context);
        this.init();
    }
    
    private void init() {
        this.setButtonDrawable(R$drawable.ic_chem_attr);
        this.setBackgroundResource(R$drawable.palete_menu_nor_bg);
        this.setOnClickListener((View$OnClickListener)this);
    }
    
    @Override
    public void onClick(final View view) {
        super.onClick(view);
        if (this.kingDrawView != null) {
            final ChemicalAnalysisModel chemicalAnalysisModel = (ChemicalAnalysisModel)GsonUtil.fromJson(new ChemisAnalysisTool(this.kingDrawView.getPaletteId()).chemisAnalysisWithSelectElements(), (Class)ChemicalAnalysisModel.class);
            if (chemicalAnalysisModel != null) {
                new AnalysisPop(this.context, this.kingDrawView, chemicalAnalysisModel).show((View)this.kingDrawView);
            }
            else {
                ToastUtil.showShort((CharSequence)this.getContext().getString(R$string.palette_no_chem));
            }
        }
    }
}

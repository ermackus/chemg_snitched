package com.kingagroot.kingdraw.ui;

import com.kingagroot.component.ui.BaseSupActivity;
import com.kingagroot.component.ui.model.GSGroupModel;
import android.view.View;
import com.kingagroot.kingdraw.core.GestureRecognizeResultCallBack;
import com.kingagroot.kingdraw.core.GestureCallBack;
import com.kingagroot.kingdraw.ui.workstation.PediasActivity;
import com.goodsrc.library.utils.GsonUtil;
import com.kingagroot.kingdraw.core.data.ProtocolConverter;
import com.kingagroot.kingdraw.model.BaiKeModel;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.TextUtils;
import com.kingagroot.kingdraw.core.model.FormatValue;
import android.content.DialogInterface;
import com.goodsrc.library.utils.FileUtil;
import java.io.IOException;
import com.kingagroot.kingdraw.core.FileReader;
import com.kingagroot.kingdraw.model.GestureGroupModel;
import com.kingagroot.kingdraw.core.view3d.DataBuilder;
import android.os.Handler;
import android.os.Looper;
import android.content.Intent;
import com.kingagroot.kingdraw.core.view3d.DataElements;
import com.kingagroot.kingdraw.core.tool.ToolNameEnum;
import com.kingagroot.kingdraw.config.ShareData;
import com.kingagroot.kingdraw.widget.paletteMenu.KdHelpItemView;
import com.kingagroot.component.ui.menu.menuitem.GridItemView;
import com.kingagroot.component.ui.menu.menuitem.CopyItemView;
import com.kingagroot.component.ui.menu.menuitem.ChemInfoItemView;
import com.kingagroot.component.ui.menu.menuitem.SelectItemView;
import com.kingagroot.component.ui.menu.menuitem.AlignItemView;
import com.kingagroot.component.ui.menu.menuitem.ColorItemView;
import com.kingagroot.component.ui.menu.menuitem.ClearItemView;
import java.util.ArrayList;
import com.kingagroot.component.ui.menu.menuitem.ToolBaseItemView;
import java.util.List;
import androidx.appcompat.app.AlertDialog;
import android.content.DialogInterface$OnClickListener;
import androidx.appcompat.app.AlertDialog$Builder;
import com.goodsrc.library.utils.ToastUtil;
import com.goodsrc.library.utils.StringUtils;
import com.kingagroot.component.ui.model.GFormatValue;
import com.kingagroot.kingdraw.core.model.PaletteConfigModel;
import com.kingagroot.component.ui.model.GDocumentTypeEnum;
import com.kingagroot.component.ui.db.impl.GFormatValueDBImpl;
import com.kingagroot.kingdraw.interfaces.impl.GestureDbiMpl;
import android.content.Context;
import com.kingagroot.kingdraw.widget.guide.GuideManager;
import com.kingagroot.kingdraw.interfaces.GestureDbi;
import com.kingagroot.component.ui.NewBaseSupPaletteActivity;

public class SupPaletteActivity extends NewBaseSupPaletteActivity
{
    GestureDbi gestureDbi;
    protected GuideManager guideManager;
    
    public SupPaletteActivity() {
        this.guideManager = new GuideManager((Context)this);
        this.gestureDbi = (GestureDbi)new GestureDbiMpl();
    }
    
    private void initFinish() {
        this.runOnUiThread((Runnable)new _$$Lambda$SupPaletteActivity$HihqfHfKB1bIGAbvWtcnXO4IfHw(this));
    }
    
    private void initPalette() {
        this.kingDraw.createPalette(new GFormatValueDBImpl().readerFormatForType(GDocumentTypeEnum.KingDraw\u683c\u5f0f).formatValue(), new PaletteConfigModel());
    }
    
    private void readFail() {
        this.runOnUiThread((Runnable)new _$$Lambda$SupPaletteActivity$JJk1qjAS_3oY0HGe5Rstpeq_x8o(this));
    }
    
    private void showFormatHint(final GFormatValue gFormatValue) {
        ToastUtil.showFormatInfo((CharSequence)StringUtils.format(this.getString(2131820779), new Object[] { GDocumentTypeEnum.valueOfCode(gFormatValue.getDocumentType()).getFullName() }));
    }
    
    private void showMultiBtnDialog(final GFormatValue gFormatValue) {
        final AlertDialog$Builder alertDialog$Builder = new AlertDialog$Builder((Context)this);
        alertDialog$Builder.setTitle(2131821524);
        alertDialog$Builder.setMessage(2131821023);
        alertDialog$Builder.setPositiveButton(2131821022, (DialogInterface$OnClickListener)new _$$Lambda$SupPaletteActivity$FMIbHyZSlAN7lsz_SsqjadopN3c(this, gFormatValue));
        alertDialog$Builder.setNeutralButton(2131820661, (DialogInterface$OnClickListener)new _$$Lambda$SupPaletteActivity$QlaG1NC0U3Wu4gw25TIP94vi7pY(this));
        alertDialog$Builder.setNegativeButton(2131820778, (DialogInterface$OnClickListener)new _$$Lambda$SupPaletteActivity$tKrdvHt3IPxhubKp6DieVYAtR_o(this, gFormatValue));
        final AlertDialog create = alertDialog$Builder.create();
        create.setCanceledOnTouchOutside(false);
        create.setCancelable(false);
        create.show();
    }
    
    public void applyFormatValue(final GFormatValue formatValue) {
        this.setFormatValue(formatValue);
        this.showLoading(this.getString(2131821523), 1000L);
        this.kingDraw.applyFormatValue();
        this.showFormatHint(formatValue);
        this.dismissLoading();
    }
    
    @Override
    protected List<ToolBaseItemView> initTopMenu() {
        final ArrayList list = new ArrayList();
        final ClearItemView clearItemView = new ClearItemView((Context)this);
        ((ToolBaseItemView)clearItemView).setKingDrawView(this.kingDraw);
        final ColorItemView colorItemView = new ColorItemView((Context)this);
        ((ToolBaseItemView)colorItemView).setKingDrawView(this.kingDraw);
        ((ToolBaseItemView)colorItemView).setEnable(false);
        final AlignItemView alignItemView = new AlignItemView((Context)this);
        ((ToolBaseItemView)alignItemView).setKingDrawView(this.kingDraw);
        final SelectItemView selectItemView = new SelectItemView((Context)this);
        final ChemInfoItemView chemInfoItemView = new ChemInfoItemView((Context)this);
        ((ToolBaseItemView)chemInfoItemView).setKingDrawView(this.kingDraw);
        final CopyItemView copyItemView = new CopyItemView((Context)this);
        ((ToolBaseItemView)copyItemView).setEnable(false);
        ((ToolBaseItemView)copyItemView).setKingDrawView(this.kingDraw);
        final GridItemView gridItemView = new GridItemView((Context)this);
        ((ToolBaseItemView)gridItemView).setKingDrawView(this.kingDraw);
        final KdHelpItemView kdHelpItemView = new KdHelpItemView((Context)this);
        ((List)list).add((Object)clearItemView);
        ((List)list).add((Object)colorItemView);
        ((List)list).add((Object)alignItemView);
        ((List)list).add((Object)selectItemView);
        ((List)list).add((Object)chemInfoItemView);
        ((List)list).add((Object)copyItemView);
        ((List)list).add((Object)gridItemView);
        ((List)list).add((Object)kdHelpItemView);
        return (List<ToolBaseItemView>)list;
    }
    
    @Override
    protected void initView() {
        super.initView();
        if (ShareData.getZoomerStatus()) {
            this.kingDraw.contactMagnifierView(this.magnifier);
        }
        this.kingDraw.setOnKingDrawViewListener(this.onKingDrawViewListener);
    }
    
    @Override
    public void on3dViewClick() {
        super.on3dViewClick();
        final String selectedMoleculeElementsJson = this.kingDraw.getSelectedMoleculeElementsJson();
        final String allElementsJson = this.kingDraw.getAllElementsJson();
        String s = selectedMoleculeElementsJson;
        if (TextUtils.isEmpty((CharSequence)selectedMoleculeElementsJson)) {
            s = allElementsJson;
        }
        DataElements.cleanElements();
        if (!TextUtils.isEmpty((CharSequence)s)) {
            this.showLoading(this.getString(2131820953));
            new Thread((Runnable)new _$$Lambda$SupPaletteActivity$kpc4bUfLjoyDZYby_9SoQifDXzU(this, s)).start();
        }
        else {
            ToastUtil.showShort(2131821134);
        }
    }
    
    @Override
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        final Configuration configuration = this.getResources().getConfiguration();
        final int orientation = configuration.orientation;
        boolean elementViewSize = true;
        if (orientation == 2) {
            this.setRequestedOrientation(11);
        }
        else if (configuration.orientation == 1) {
            this.setRequestedOrientation(1);
        }
        if (this.getResources().getConfiguration().orientation != 1) {
            elementViewSize = false;
        }
        this.setElementViewSize(elementViewSize);
    }
    
    @Override
    public void onEncyOnClick() {
        super.onEncyOnClick();
        if (this.checkNetwork()) {
            final String selectedElementsJson = this.kingDraw.getSelectedElementsJson();
            String smiles = null;
            Label_0056: {
                if (!TextUtils.isEmpty((CharSequence)selectedElementsJson)) {
                    final BaiKeModel baiKeModel = (BaiKeModel)GsonUtil.fromJson(ProtocolConverter.jsonToSmiles(selectedElementsJson), (Class)BaiKeModel.class);
                    if (baiKeModel != null) {
                        smiles = baiKeModel.getSmiles();
                        break Label_0056;
                    }
                }
                smiles = "";
            }
            if (!TextUtils.isEmpty((CharSequence)smiles) && !smiles.equals((Object)"error")) {
                final Intent intent = new Intent((Context)this, (Class)PediasActivity.class);
                intent.putExtra(PediasActivity.PALETTE_SMILES, smiles);
                this.startActivity(intent);
            }
            else {
                ToastUtil.showShort((CharSequence)this.getString(2131820622));
            }
        }
    }
    
    @Override
    public void onFormatChangeClick(final GFormatValue gFormatValue) {
        super.onFormatChangeClick(gFormatValue);
        this.showMultiBtnDialog(gFormatValue);
    }
    
    @Override
    public void onGestureOnClick() {
        super.onGestureOnClick();
        GestureCallBack.setResultCallBack((GestureRecognizeResultCallBack)new _$$Lambda$SupPaletteActivity$1dNRxyFOsOczr4dViFerp9I4Xmg(this));
    }
    
    @Override
    public void onGestureOnLongClick() {
        final List dataByKey = this.gestureDbi.getDataByKey(true);
        if (dataByKey != null && dataByKey.size() > 0) {
            this.guideManager.showGestureChartGuide((View)this.kingDraw);
        }
    }
    
    @Override
    public void onJumpToPredictionClick() {
        super.onJumpToPredictionClick();
        if (this.checkNetwork()) {
            final String selectedElementsJson = this.kingDraw.getSelectedElementsJson();
            String smiles = null;
            Label_0056: {
                if (!TextUtils.isEmpty((CharSequence)selectedElementsJson)) {
                    final BaiKeModel baiKeModel = (BaiKeModel)GsonUtil.fromJson(ProtocolConverter.jsonToSmiles(selectedElementsJson), (Class)BaiKeModel.class);
                    if (baiKeModel != null) {
                        smiles = baiKeModel.getSmiles();
                        break Label_0056;
                    }
                }
                smiles = "";
            }
            if (!TextUtils.isEmpty((CharSequence)smiles) && !smiles.equals((Object)"error")) {
                final Intent intent = new Intent((Context)this, (Class)WebViewActivity.class);
                final StringBuilder sb = new StringBuilder();
                sb.append("https://askcos.mit.edu/retro/network/?target=");
                sb.append(smiles);
                intent.putExtra("url_key", sb.toString());
                this.startActivity(intent);
            }
            else {
                ToastUtil.showShort((CharSequence)this.getString(2131820622));
            }
        }
    }
    
    @Override
    public void onKingDrawViewAvailable() {
        final GFormatValue readerFormatForType = new GFormatValueDBImpl().readerFormatForType(GDocumentTypeEnum.KingDraw\u683c\u5f0f);
        this.gsGroupModel = (GSGroupModel)this.getIntent().getSerializableExtra(BaseSupActivity.KEY_SUP_FILE);
        final GSGroupModel gsGroupModel = this.gsGroupModel;
        int n = 0;
        final int n2 = 0;
        if (gsGroupModel != null) {
            Label_0139: {
                if (TextUtils.isEmpty((CharSequence)this.gsGroupModel.getContent())) {
                    final String kdxFile = this.gsGroupModelDBI.getKdxFile(this.gsGroupModel.getRowid(), this.gsGroupModel.isCustomize());
                    n = n2;
                    if (TextUtils.isEmpty((CharSequence)kdxFile)) {
                        break Label_0139;
                    }
                    new Thread((Runnable)new _$$Lambda$SupPaletteActivity$iPt2w_c72Dqv40x1D6l0eHSkWZU(this, readerFormatForType, kdxFile)).start();
                }
                else {
                    new Thread((Runnable)new _$$Lambda$SupPaletteActivity$CXcyrGHr_sUEcAVw05jzX8jaVuI(this, readerFormatForType)).start();
                }
                n = 1;
            }
            String s;
            if (TextUtils.isEmpty((CharSequence)this.gsGroupModel.getNameHtml())) {
                s = this.gsGroupModel.getName();
            }
            else {
                s = this.gsGroupModel.getNameHtml();
            }
            if (this.isVertical) {
                this.verticalTopTool.setSupName(s);
            }
            else {
                this.landTopTool.setSupName(s);
            }
        }
        if (n == 0) {
            this.initPalette();
            this.initFinish();
        }
    }
    
    protected void setFormatValue(final GFormatValue defaultFormat) {
        this.verticalBottomTool.setDefaultFormat(defaultFormat);
    }
}

package com.kingagroot.kingdraw.ui;

import com.kingagroot.component.ui.view.OperationState;
import java.io.Serializable;
import com.kingagroot.component.ui.widget.EditPop$OnEditPopListener;
import com.kingagroot.component.ui.widget.EditPop$Build;
import com.kingagroot.kingdraw.model.GFileSearchModel;
import android.view.KeyEvent;
import java.util.List;
import com.kingagroot.kingdraw.core.GestureRecognizeResultCallBack;
import com.kingagroot.kingdraw.core.GestureCallBack;
import com.kingagroot.component.ui.menu.SildeFlingGestureAction$OnSildeFlingGestureActionListener;
import com.kingagroot.kingdraw.core.tool.gesture.BaseGestureAction;
import com.kingagroot.component.ui.menu.SildeFlingGestureAction;
import android.content.Intent;
import com.kingagroot.kingdraw.ui.workstation.PediasActivity;
import com.kingagroot.kingdraw.core.data.ProtocolConverter;
import com.kingagroot.kingdraw.model.BaiKeModel;
import com.kingagroot.kingdraw.interfaces.impl.DrawSearchHisDBImpl;
import android.os.Bundle;
import android.view.View;
import com.kingagroot.component.ui.widget.AnalysisPop;
import com.goodsrc.library.utils.GsonUtil;
import com.kingagroot.kingdraw.core.tool.ChemisAnalysisTool;
import com.kingagroot.component.ui.model.ChemicalAnalysisModel;
import com.kingagroot.kingdraw.core.view3d.DataElements;
import android.text.TextUtils;
import android.content.DialogInterface;
import com.kingagroot.kingdraw.core.model.FormatValue;
import com.kingagroot.kingdraw.core.StructConvertListener;
import com.kingagroot.kingdraw.core.tool.Struct2NameTool;
import com.goodsrc.library.utils.FileUtil;
import java.io.IOException;
import com.kingagroot.kingdraw.core.FileReader;
import com.kingagroot.kingdraw.model.GestureGroupModel;
import com.kingagroot.kingdraw.core.tool.CleanUpTool;
import com.kingagroot.kingdraw.core.tool.ClipboardData;
import com.kingagroot.kingdraw.core.tool.ToolNameEnum;
import com.kingagroot.component.ui.vertical.ToolDataManage;
import com.kingagroot.kingdraw.config.ShareData;
import androidx.appcompat.app.AlertDialog;
import android.content.DialogInterface$OnClickListener;
import androidx.appcompat.app.AlertDialog$Builder;
import com.goodsrc.library.utils.ToastUtil;
import com.goodsrc.library.utils.StringUtils;
import com.kingagroot.component.ui.model.GFormatValue;
import com.kingagroot.kingdraw.core.model.PaletteConfigModel;
import com.kingagroot.component.ui.model.GDocumentTypeEnum;
import com.kingagroot.component.ui.db.impl.GFormatValueDBImpl;
import com.kingagroot.component.ui.menu.ElementMenuView;
import com.kingagroot.kingdraw.core.view.KingDrawView;
import com.kingagroot.kingdraw.interfaces.impl.GestureDbiMpl;
import android.content.Context;
import com.kingagroot.kingdraw.widget.guide.GuideManager;
import com.kingagroot.kingdraw.interfaces.GestureDbi;
import com.kingagroot.kingdraw.interfaces.DrawSearchHisDBI;
import com.kingagroot.component.ui.vertical.PaletteRightInterface;
import com.kingagroot.component.ui.PaletteTopInterface;
import com.kingagroot.component.ui.vertical.PaletteBottomInterface;
import com.kingagroot.component.ui.vertical.NewPaletteActivity;

public class NewSearchPaletteActivity extends NewPaletteActivity implements PaletteBottomInterface, PaletteTopInterface, PaletteRightInterface
{
    public static final String INTENT_KEY_GFILESEARCHMODEL = "intent_key_gfilesearchmodel";
    public static final String RESLULT_KEY_GSEARCHMODEL = "reslult_key_GSearchModel";
    private DrawSearchHisDBI drawSearchHisDBI;
    GestureDbi gestureDbi;
    protected GuideManager guideManager;
    
    public NewSearchPaletteActivity() {
        this.guideManager = new GuideManager((Context)this);
        this.gestureDbi = (GestureDbi)new GestureDbiMpl();
    }
    
    private void createPalette() {
        this.kingDraw.createPalette(new GFormatValueDBImpl().readerFormatForType(GDocumentTypeEnum.KingDraw\u683c\u5f0f).formatValue(), new PaletteConfigModel());
    }
    
    private void initFinish() {
        this.runOnUiThread((Runnable)new _$$Lambda$NewSearchPaletteActivity$RYY2gv9q16pj5_Leax1iKLPkOko(this));
    }
    
    private void readFail() {
        this.runOnUiThread((Runnable)new _$$Lambda$NewSearchPaletteActivity$YLj7OhBFnyUW8R4t44tq3rKmPVY(this));
    }
    
    private void showFormatHint(final GFormatValue gFormatValue) {
        ToastUtil.showFormatInfo((CharSequence)StringUtils.format(this.getString(2131820779), new Object[] { GDocumentTypeEnum.valueOfCode(gFormatValue.getDocumentType()).getFullName() }));
    }
    
    private void showMultiBtnDialog(final GFormatValue gFormatValue) {
        final AlertDialog$Builder alertDialog$Builder = new AlertDialog$Builder((Context)this);
        alertDialog$Builder.setTitle(2131821524);
        alertDialog$Builder.setMessage(2131821023);
        alertDialog$Builder.setPositiveButton(2131821022, (DialogInterface$OnClickListener)new _$$Lambda$NewSearchPaletteActivity$jeo9_PTpfqfBWB2u4WVmp6Xbvr0(this, gFormatValue));
        alertDialog$Builder.setNeutralButton(2131820661, (DialogInterface$OnClickListener)new _$$Lambda$NewSearchPaletteActivity$2T__Nv_5Bdcbvqkvs7l9CYE34UU(this));
        alertDialog$Builder.setNegativeButton(2131820778, (DialogInterface$OnClickListener)new _$$Lambda$NewSearchPaletteActivity$dsxXNYM46WduvPGBGLkvPkpnfj8(this, gFormatValue));
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
    protected void initView() {
        super.initView();
        if (ShareData.getZoomerStatus()) {
            this.kingDraw.contactMagnifierView(this.magnifier);
        }
        this.setIsPad(false);
        this.isPortrait(true, true);
        this.verticalRightTool.setPaletteType(ToolDataManage.TYPE_SEARCH);
        this.verticalTopTool.setTopViewType(ToolDataManage.TYPE_SEARCH);
        this.verticalBottomTool.setPaletteType(ToolDataManage.TYPE_SEARCH);
        this.verticalBottomTool.setBottomViewClickListener((PaletteBottomInterface)this);
        this.verticalTopTool.setOnTopToolClick((PaletteTopInterface)this);
        this.verticalRightTool.setOnRightMenuClickListener((PaletteRightInterface)this);
    }
    
    public void on3dViewClick() {
        final String selectedMoleculeElementsJson = this.kingDraw.getSelectedMoleculeElementsJson();
        final String allElementsJson = this.kingDraw.getAllElementsJson();
        String s = selectedMoleculeElementsJson;
        if (TextUtils.isEmpty((CharSequence)selectedMoleculeElementsJson)) {
            s = allElementsJson;
        }
        DataElements.cleanElements();
        if (!TextUtils.isEmpty((CharSequence)s)) {
            this.showLoading(this.getString(2131820953));
            new Thread((Runnable)new NewSearchPaletteActivity$1(this, s)).start();
        }
        else {
            ToastUtil.showShort(2131821134);
        }
    }
    
    public void onChemInfoClick() {
        if (this.kingDraw != null) {
            final ChemicalAnalysisModel chemicalAnalysisModel = (ChemicalAnalysisModel)GsonUtil.fromJson(new ChemisAnalysisTool(this.kingDraw.getPaletteId()).chemisAnalysisWithSelectElements(), (Class)ChemicalAnalysisModel.class);
            if (chemicalAnalysisModel != null) {
                new AnalysisPop((Context)this, this.kingDraw, chemicalAnalysisModel).show((View)this.kingDraw);
            }
            else {
                ToastUtil.showShort((CharSequence)this.getString(2131821142));
            }
        }
    }
    
    public void onChiralClick(final boolean b) {
    }
    
    public void onCleanUpClick() {
        this.showLoading(this.getString(2131821523), 1000L);
        new Thread((Runnable)new _$$Lambda$NewSearchPaletteActivity$1TmfThyzIvIVteLmyA7lOGFhPo0(this)).start();
    }
    
    public void onClose() {
        this.finish();
    }
    
    @Override
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.showLoading(this.getString(2131821523), 1000L);
        this.drawSearchHisDBI = (DrawSearchHisDBI)new DrawSearchHisDBImpl();
    }
    
    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.kingDraw.onDestroy();
    }
    
    public void onEncyOnClick() {
        if (this.checkNetwork()) {
            final String selectedElementsJson = this.kingDraw.getSelectedElementsJson();
            String smiles = null;
            Label_0052: {
                if (!TextUtils.isEmpty((CharSequence)selectedElementsJson)) {
                    final BaiKeModel baiKeModel = (BaiKeModel)GsonUtil.fromJson(ProtocolConverter.jsonToSmiles(selectedElementsJson), (Class)BaiKeModel.class);
                    if (baiKeModel != null) {
                        smiles = baiKeModel.getSmiles();
                        break Label_0052;
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
    
    public void onFirstToolViewClick() {
        this.verticalRightTool.clearCheck();
        this.verticalRightTool.setOtherToolSelect();
        this.paletteMenuElement.clearCheck();
    }
    
    public void onFormatChangeClick(final GFormatValue gFormatValue) {
        this.showMultiBtnDialog(gFormatValue);
    }
    
    public void onFormatSetting() {
    }
    
    public void onFullScreen(final boolean b) {
    }
    
    public void onGestureOnClick() {
        if (this.sildeFlingGestureAction == null) {
            this.sildeFlingGestureAction = new SildeFlingGestureAction((Context)this, (float)this.kingDraw.getWidth());
            this.kingDraw.addGestureAction((BaseGestureAction)this.sildeFlingGestureAction);
            this.sildeFlingGestureAction.setOnSildeFlingGestureActionListener((SildeFlingGestureAction$OnSildeFlingGestureActionListener)new NewSearchPaletteActivity$4(this));
        }
        this.kingDraw.setTool(ToolNameEnum.GGESTURE_TOOL, (String)null);
        GestureCallBack.setResultCallBack((GestureRecognizeResultCallBack)new _$$Lambda$NewSearchPaletteActivity$BVtN1EnOBqZCsdwk2iWVxiS8888(this));
        this.verticalBottomTool.clearCheck();
        this.paletteMenuElement.clearCheck();
    }
    
    public void onGestureOnLongClick() {
        final List dataByKey = this.gestureDbi.getDataByKey(true);
        if (dataByKey != null && dataByKey.size() > 0) {
            this.guideManager.showGestureChartGuide((View)this.kingDraw);
        }
    }
    
    public void onHelp() {
    }
    
    public void onInputFileName(final String s) {
    }
    
    public void onInputSupName(final String s) {
    }
    
    public void onJumpToPredictionClick() {
        if (this.checkNetwork()) {
            final String selectedElementsJson = this.kingDraw.getSelectedElementsJson();
            String smiles = null;
            Label_0052: {
                if (!TextUtils.isEmpty((CharSequence)selectedElementsJson)) {
                    final BaiKeModel baiKeModel = (BaiKeModel)GsonUtil.fromJson(ProtocolConverter.jsonToSmiles(selectedElementsJson), (Class)BaiKeModel.class);
                    if (baiKeModel != null) {
                        smiles = baiKeModel.getSmiles();
                        break Label_0052;
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
    
    public boolean onKeyDown(final int n, final KeyEvent keyEvent) {
        if (n == 4) {
            if (keyEvent.getAction() == 0) {
                if (this.inputMenu.getVisibility() == 0) {
                    this.inputMenu.setVisibility(8);
                }
                else {
                    this.onClose();
                }
            }
            return true;
        }
        return super.onKeyDown(n, keyEvent);
    }
    
    @Override
    public void onKingDrawViewAvailable() {
        final Bundle extras = this.getIntent().getExtras();
        final GFormatValue readerFormatForType = new GFormatValueDBImpl().readerFormatForType(GDocumentTypeEnum.KingDraw\u683c\u5f0f);
        boolean b = true;
        Label_0126: {
            if (extras != null) {
                final GFileSearchModel gFileSearchModel = (GFileSearchModel)extras.getSerializable("intent_key_gfilesearchmodel");
                if (gFileSearchModel != null) {
                    final String kingContent = gFileSearchModel.getKingContent();
                    if (!TextUtils.isEmpty((CharSequence)kingContent)) {
                        new Thread((Runnable)new _$$Lambda$NewSearchPaletteActivity$2ITTub5B_bP0UCcMgis44hwV1v0(this, readerFormatForType, kingContent)).start();
                        break Label_0126;
                    }
                    final String searchFile = this.drawSearchHisDBI.getSearchFile(gFileSearchModel.getId());
                    if (!TextUtils.isEmpty((CharSequence)searchFile)) {
                        new Thread((Runnable)new _$$Lambda$NewSearchPaletteActivity$hCwG4Xwxei3xzDdGS2SvVWtUTMM(this, readerFormatForType, searchFile)).start();
                        break Label_0126;
                    }
                }
            }
            b = false;
        }
        if (!b) {
            this.createPalette();
            this.initFinish();
        }
    }
    
    public void onMoveOnClick() {
        this.kingDraw.setTool(ToolNameEnum.G_DRAG_TOOL, (String)null);
        this.verticalBottomTool.clearCheck();
        this.paletteMenuElement.clearCheck();
    }
    
    public void onNameToStructClick() {
        new EditPop$Build((Context)this).setContent("").setHint(this.getString(2131821141)).setMaxLength(200).setShowCount(false).setOnEditPopListener((EditPop$OnEditPopListener)new _$$Lambda$NewSearchPaletteActivity$4_OnsJRdegsdq_d5LbqHfCDyMTw(this)).create().show((View)this.verticalTopTool);
    }
    
    public void onRClick() {
    }
    
    @Override
    protected void onResume() {
        super.onResume();
    }
    
    public void onSaveAsFile() {
    }
    
    public void onSaveFile() {
    }
    
    public void onScreenToHorizontal() {
    }
    
    public void onSearchDone() {
        if (!this.kingDraw.isEmpty()) {
            if (this.isSaving()) {
                return;
            }
            this.startSave();
            final OperationState save = this.drawSearchHisDBI.save(this.kingDraw);
            this.endSave();
            if (save.isSuccess) {
                final GFileSearchModel gFileSearchModel = (GFileSearchModel)save.data;
                final String smiles = gFileSearchModel.getSmiles();
                if (!TextUtils.isEmpty((CharSequence)smiles) && !smiles.equals((Object)"error")) {
                    final Intent intent = new Intent();
                    intent.putExtra("reslult_key_GSearchModel", (Serializable)gFileSearchModel);
                    this.setResult(-1, intent);
                    this.finish();
                }
                else {
                    ToastUtil.showShort((CharSequence)this.getString(2131821134));
                }
            }
        }
        else {
            ToastUtil.showShort((CharSequence)this.getString(2131821134));
        }
    }
    
    public void onSecondToolViewClick() {
        this.verticalRightTool.clearCheck();
        this.verticalRightTool.setOtherToolSelect();
        this.paletteMenuElement.clearCheck();
    }
    
    public void onSelectPic() {
    }
    
    public void onShare() {
    }
    
    public void onStructToNameClick() {
        this.showLoading(this.getString(2131820950));
        new Struct2NameTool(this.kingDraw.getPaletteId()).structToName((StructConvertListener)new NewSearchPaletteActivity$3(this));
    }
    
    public void onSupClick() {
        this.dlPalette.openDrawer(5);
    }
    
    public void onSupSave() {
    }
    
    @Override
    protected void onSupTable(final boolean b) {
        super.onSupTable(b);
        this.startActivityForResult(new Intent((Context)this, (Class)SUPTableActivity.class), 1100);
    }
    
    public void onTextSupClick() {
        this.dlPalette.openDrawer(5);
    }
    
    public void onToolDefaultSelectClick() {
        this.verticalRightTool.checkSelect();
    }
    
    public void onToolNameShow(final String s) {
        this.tvHint.show(s);
    }
    
    protected void setFormatValue(final GFormatValue defaultFormat) {
        this.verticalBottomTool.setDefaultFormat(defaultFormat);
    }
}

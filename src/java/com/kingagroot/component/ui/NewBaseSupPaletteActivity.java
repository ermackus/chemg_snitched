package com.kingagroot.component.ui;

import com.kingagroot.kingdraw.core.StructConvertListener;
import com.kingagroot.kingdraw.core.tool.Struct2NameTool;
import com.kingagroot.component.ui.widget.EditPop;
import com.kingagroot.component.ui.widget.EditPop$OnEditPopListener;
import com.kingagroot.component.ui.widget.EditPop$Build;
import android.view.KeyEvent;
import com.kingagroot.kingdraw.core.tool.ToolNameEnum;
import com.kingagroot.component.ui.menu.SildeFlingGestureAction$OnSildeFlingGestureActionListener;
import com.kingagroot.kingdraw.core.tool.gesture.BaseGestureAction;
import com.kingagroot.component.ui.menu.SildeFlingGestureAction;
import com.kingagroot.component.ui.model.GFormatValue;
import com.kingagroot.component.ui.widget.richinput.InputMenuContainer$OnInputListner;
import android.util.Log;
import com.kingagroot.component.ui.view.OperationState;
import android.content.Intent;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.kingagroot.component.ui.widget.richinput.SpanUtil;
import android.content.DialogInterface$OnClickListener;
import androidx.appcompat.app.AlertDialog$Builder;
import android.text.TextUtils;
import com.goodsrc.library.utils.ToastUtil;
import com.kingagroot.component.ui.widget.AnalysisPop;
import com.goodsrc.library.utils.GsonUtil;
import com.kingagroot.kingdraw.core.tool.ChemisAnalysisTool;
import com.kingagroot.component.ui.model.ChemicalAnalysisModel;
import android.view.MotionEvent;
import com.kingagroot.component.ui.vertical.ToolDataManage;
import android.view.View;
import com.kingagroot.component.ui.db.impl.GSGroupModelDBImpl;
import com.kingagroot.component.ui.menu.menuitem.HelpItemView;
import com.kingagroot.component.ui.menu.menuitem.GridItemView;
import com.kingagroot.component.ui.menu.menuitem.CopyItemView;
import com.kingagroot.component.ui.menu.menuitem.ChemInfoItemView;
import com.kingagroot.component.ui.menu.menuitem.SelectItemView;
import com.kingagroot.component.ui.menu.menuitem.AlignItemView;
import com.kingagroot.component.ui.menu.menuitem.ColorItemView;
import android.content.Context;
import com.kingagroot.component.ui.menu.menuitem.ClearItemView;
import java.util.ArrayList;
import com.kingagroot.component.ui.menu.menuitem.ToolBaseItemView;
import java.util.List;
import com.kingagroot.component.ui.widget.richinput.InputMenuContainer;
import com.kingagroot.component.ui.menu.MainPaletteTopMenu;
import com.kingagroot.component.ui.menu.ElementMenuView;
import com.kingagroot.kingdraw.core.view.KingDrawView;
import com.kingagroot.component.ui.vertical.VerticalTopToolView;
import com.kingagroot.component.ui.db.GSGroupModelDBI;
import com.kingagroot.component.ui.model.GSGroupModel;
import com.kingagroot.component.ui.menu.PaletteOtherView$OnMenuClickListener;
import com.kingagroot.component.ui.vertical.PaletteRightInterface;
import com.kingagroot.component.ui.vertical.PaletteBottomInterface;
import com.kingagroot.component.ui.vertical.NewPaletteActivity;

public class NewBaseSupPaletteActivity extends NewPaletteActivity implements PaletteTopInterface, PaletteBottomInterface, PaletteRightInterface, PaletteOtherView$OnMenuClickListener
{
    protected GSGroupModel gsGroupModel;
    protected GSGroupModelDBI gsGroupModelDBI;
    
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
        ((ToolBaseItemView)copyItemView).setKingDrawView(this.kingDraw);
        final GridItemView gridItemView = new GridItemView((Context)this);
        ((ToolBaseItemView)gridItemView).setKingDrawView(this.kingDraw);
        final HelpItemView helpItemView = new HelpItemView((Context)this);
        ((List)list).add((Object)clearItemView);
        ((List)list).add((Object)colorItemView);
        ((List)list).add((Object)alignItemView);
        ((List)list).add((Object)selectItemView);
        ((List)list).add((Object)chemInfoItemView);
        ((List)list).add((Object)copyItemView);
        ((List)list).add((Object)gridItemView);
        ((List)list).add((Object)helpItemView);
        return (List<ToolBaseItemView>)list;
    }
    
    @Override
    protected void initView() {
        super.initView();
        this.gsGroupModelDBI = (GSGroupModelDBI)new GSGroupModelDBImpl();
        this.landTopTool.setSaveAsEnable(false);
        this.landRightTool.setSupShow(true);
        this.landRightTool.checkRTool(false);
        this.dlPalette.setDrawerLockMode(1);
        this.dlPalette.removeView((View)this.supCollect);
        this.showLoading(this.getString(R$string.wait_hint), 1000L);
        this.landRightTool.setEncyclopediaShow(false);
        this.kingDraw.setOnKingDrawViewListener(this.onKingDrawViewListener);
        this.verticalRightTool.setScreenShow(false);
        this.verticalRightTool.setPaletteType(ToolDataManage.TYPE_SUP);
        this.verticalTopTool.setTopViewType(ToolDataManage.TYPE_SUP);
        this.verticalBottomTool.setPaletteType(ToolDataManage.TYPE_SUP);
        this.landTopTool.setTopViewType(ToolDataManage.TYPE_SUP);
        this.verticalBottomTool.setBottomViewClickListener((PaletteBottomInterface)this);
        this.verticalTopTool.setOnTopToolClick((PaletteTopInterface)this);
        this.verticalRightTool.setOnRightMenuClickListener((PaletteRightInterface)this);
        this.verticalRightTool.unEnableCopy();
        this.landRightTool.setOnMenuClickListener((PaletteOtherView$OnMenuClickListener)this);
        this.landTopTool.setMainPaletteView((PaletteTopInterface)this);
        if (this.getResources().getConfiguration().orientation == 2) {
            this.flLandscape.setVisibility(0);
            this.flPortrait.setVisibility(8);
            this.isVertical = false;
        }
        else {
            this.flLandscape.setVisibility(8);
            this.flPortrait.setVisibility(0);
            this.isVertical = true;
        }
        this.inputMenu.isPortrait(this.isVertical);
    }
    
    @Override
    protected boolean keyBoardEvent(final MotionEvent motionEvent) {
        return (this.inputMenu == null || !this.inputMenu.keyBoardEvent(motionEvent)) && super.keyBoardEvent(motionEvent);
    }
    
    public void on3dViewClick() {
    }
    
    public void onChemInfoClick() {
        if (this.kingDraw != null) {
            final ChemicalAnalysisModel chemicalAnalysisModel = (ChemicalAnalysisModel)GsonUtil.fromJson(new ChemisAnalysisTool(this.kingDraw.getPaletteId()).chemisAnalysisWithSelectElements(), (Class)ChemicalAnalysisModel.class);
            if (chemicalAnalysisModel != null) {
                new AnalysisPop((Context)this, this.kingDraw, chemicalAnalysisModel).show((View)this.kingDraw);
            }
            else {
                ToastUtil.showShort((CharSequence)this.getString(R$string.palette_no_chem));
            }
        }
    }
    
    public void onChiralClick(final boolean b) {
    }
    
    public void onCleanUpClick() {
        this.showLoading(this.getString(R$string.wait_hint), 1000L);
        new Thread((Runnable)new NewBaseSupPaletteActivity$4(this)).start();
    }
    
    public void onCleanUpOnClick() {
    }
    
    public void onClose() {
        String s;
        if (this.isVertical) {
            s = this.verticalTopTool.getSupName();
        }
        else {
            s = this.landTopTool.getSupName();
        }
        final int haveR = this.kingDraw.haveR();
        if (!this.kingDraw.isEmpty() && haveR != 2 && this.gsGroupModel == null && TextUtils.isEmpty((CharSequence)s)) {
            new AlertDialog$Builder((Context)this).setTitle((CharSequence)this.getString(R$string.warm_hint)).setMessage((CharSequence)this.getString(R$string.sup_alert_msg_sup_save)).setPositiveButton((CharSequence)this.getString(R$string.quit), (DialogInterface$OnClickListener)new NewBaseSupPaletteActivity$2(this)).setNegativeButton((CharSequence)this.getString(R$string.sup_go_save), (DialogInterface$OnClickListener)new NewBaseSupPaletteActivity$1(this)).setNeutralButton((CharSequence)this.getString(R$string.cancel), (DialogInterface$OnClickListener)null).show();
        }
        else {
            this.finish();
        }
    }
    
    @Override
    protected void onDestroy() {
        String s;
        if (this.isVertical) {
            s = this.verticalTopTool.getSupName();
        }
        else {
            s = this.landTopTool.getSupName();
        }
        final String spanToString = SpanUtil.SpanToString(SpanUtil.htmlToSpan(s));
        final GSGroupModel gsGroupModel = this.gsGroupModel;
        OperationState operationState;
        if (gsGroupModel == null) {
            operationState = this.gsGroupModelDBI.saveFile(spanToString, s, this.kingDraw);
        }
        else {
            operationState = this.gsGroupModelDBI.updateFile(gsGroupModel.getRowid(), this.gsGroupModel.isCustomize(), this.kingDraw);
        }
        if (operationState.isSuccess) {
            LocalBroadcastManager.getInstance((Context)this).sendBroadcast(new Intent("gsgroup_data_changed"));
        }
        this.kingDraw.onDestroy();
        super.onDestroy();
    }
    
    public void onEncyOnClick() {
    }
    
    public void onFileNameInput(final String s) {
        if (this.inputMenu != null) {
            this.inputMenu.setVisibility(0);
            this.inputMenu.isInputSupName(true);
            this.verticalBottomTool.setPopDismiss();
            final StringBuilder sb = new StringBuilder();
            sb.append(s);
            sb.append("");
            Log.e("faceHtml", sb.toString());
            this.inputMenu.setContent(false, s, this.kingDraw.getFormatValue());
            this.inputMenu.setOnInputListner((InputMenuContainer$OnInputListner)new NewBaseSupPaletteActivity$3(this));
        }
    }
    
    public void onFirstToolViewClick() {
        this.verticalRightTool.clearCheck();
        this.verticalRightTool.setOtherToolSelect();
        this.paletteMenuElement.clearCheck();
    }
    
    public void onFormatChangeClick(final GFormatValue gFormatValue) {
    }
    
    public void onFormatSetting() {
    }
    
    public void onFullScreen(final boolean b) {
        if (b) {
            this.paletteMenuElement.dismiss();
            this.landTopTool.dismiss();
            this.landBottomTool.dismiss();
            this.landRightTool.dismiss();
        }
        else {
            this.paletteMenuElement.show();
            this.landTopTool.show();
            this.landBottomTool.show();
            this.landRightTool.show();
        }
    }
    
    public void onGestureOnClick() {
        if (this.sildeFlingGestureAction == null) {
            this.sildeFlingGestureAction = new SildeFlingGestureAction((Context)this, (float)this.kingDraw.getWidth());
            this.kingDraw.addGestureAction((BaseGestureAction)this.sildeFlingGestureAction);
            this.sildeFlingGestureAction.setOnSildeFlingGestureActionListener((SildeFlingGestureAction$OnSildeFlingGestureActionListener)new NewBaseSupPaletteActivity$7(this));
        }
        this.kingDraw.setTool(ToolNameEnum.GGESTURE_TOOL, (String)null);
        this.paletteMenuElement.clearCheck();
        if (this.isVertical) {
            this.verticalBottomTool.clearCheck();
        }
        else {
            this.landBottomTool.clearCheck();
            this.landTopTool.clearCheck();
        }
    }
    
    public void onGestureOnLongClick() {
    }
    
    public void onHelp() {
    }
    
    public void onInputFileName(final String s) {
    }
    
    public void onInputSupName(final String s) {
        this.onFileNameInput(s);
    }
    
    public void onJumpToPredictionClick() {
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
    
    public void onMoveOnClick() {
        this.kingDraw.setTool(ToolNameEnum.G_DRAG_TOOL, (String)null);
        this.paletteMenuElement.clearCheck();
        if (this.isVertical) {
            this.verticalBottomTool.clearCheck();
        }
        else {
            this.landBottomTool.clearCheck();
            this.landTopTool.clearCheck();
        }
    }
    
    public void onNameToStructClick() {
        final EditPop create = new EditPop$Build((Context)this).setContent("").setHint(this.getString(R$string.palette_input_iupac_name)).setMaxLength(200).setShowCount(false).setOnEditPopListener((EditPop$OnEditPopListener)new NewBaseSupPaletteActivity$5(this)).create();
        if (this.isVertical) {
            create.show((View)this.verticalTopTool);
        }
        else {
            create.show();
        }
    }
    
    public void onRClick() {
        this.kingDraw.setTool(ToolNameEnum.GR_TOOL, (String)null);
        this.verticalBottomTool.clearCheck();
        this.paletteMenuElement.clearCheck();
    }
    
    public void onSaveAsFile() {
    }
    
    public void onSaveFile() {
        this.onSupSave();
    }
    
    public void onScreenToHorizontal() {
    }
    
    public void onScreenToVertical() {
    }
    
    public void onSearchDone() {
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
        this.showLoading(this.getString(R$string.iupac_conversion));
        new Struct2NameTool(this.kingDraw.getPaletteId()).structToName((StructConvertListener)new NewBaseSupPaletteActivity$6(this));
    }
    
    public void onSupButtOnClick() {
    }
    
    public void onSupClick() {
    }
    
    public void onSupR() {
        this.kingDraw.setTool(ToolNameEnum.GR_TOOL, (String)null);
        this.landBottomTool.clearCheck();
        this.paletteMenuElement.clearCheck();
        this.landTopTool.clearCheck();
    }
    
    public void onSupSave() {
        String s;
        if (this.isVertical) {
            s = this.verticalTopTool.getSupName();
        }
        else {
            s = this.landTopTool.getSupName();
        }
        final String spanToString = SpanUtil.SpanToString(SpanUtil.htmlToSpan(s));
        final GSGroupModel gsGroupModel = this.gsGroupModel;
        OperationState operationState;
        if (gsGroupModel == null) {
            operationState = this.gsGroupModelDBI.saveFile(spanToString, s, this.kingDraw);
        }
        else {
            operationState = this.gsGroupModelDBI.updateFile(gsGroupModel.getRowid(), this.gsGroupModel.isCustomize(), this.kingDraw);
        }
        if (operationState.getData() != null) {
            final GSGroupModel gsGroupModel2 = (GSGroupModel)operationState.getData();
            this.gsGroupModel = gsGroupModel2;
            if (gsGroupModel2.getNameHtml().isEmpty()) {
                if (this.isVertical) {
                    this.verticalTopTool.setSupName(this.gsGroupModel.getName());
                }
                else {
                    this.landTopTool.setSupName(this.gsGroupModel.getName());
                }
            }
            else if (this.isVertical) {
                this.verticalTopTool.setSupName(this.gsGroupModel.getNameHtml());
            }
            else {
                this.landTopTool.setSupName(this.gsGroupModel.getNameHtml());
            }
        }
        ToastUtil.showShort((CharSequence)operationState.info);
    }
    
    public void onSupTextOnClick() {
    }
    
    public void onTextSupClick() {
    }
    
    public void onToolDefaultSelectClick() {
        this.verticalRightTool.checkSelect();
    }
    
    public void onToolNameShow(final String s) {
        this.tvHint.show(s);
    }
}

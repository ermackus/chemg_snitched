package com.kingagroot.component.ui.vertical;

import com.kingagroot.component.ui.ToolEnum;
import com.goodsrc.library.utils.ScreenUtils;
import com.kingagroot.component.ui.widget.richinput.InputMenuContainer$OnInputListner;
import com.kingagroot.kingdraw.core.graphics.KDColor;
import com.kingagroot.kingdraw.core.utils.GNumberUtil;
import com.kingagroot.kingdraw.core.model.FormatValue;
import com.kingagroot.component.ui.R$layout;
import android.os.Bundle;
import android.content.res.Configuration;
import com.kingagroot.kingdraw.core.model.PaletteConfigModel;
import com.kingagroot.kingdraw.core.tool.GestureTool;
import com.kingagroot.kingdraw.core.tool.ToolNameEnum;
import com.kingagroot.component.ui.BaseSupActivity;
import com.kingagroot.component.ui.model.GSGroupModel;
import android.content.Intent;
import android.view.MotionEvent;
import com.kingagroot.component.ui.R$id;
import com.kingagroot.component.ui.menu.menuitem.GridItemView;
import com.kingagroot.component.ui.menu.menuitem.SyntheticPredictionItemView;
import com.kingagroot.component.ui.menu.menuitem.To3dItemView;
import com.kingagroot.component.ui.menu.menuitem.ChiralItemView;
import com.kingagroot.component.ui.menu.menuitem.StructToNameItemView;
import com.kingagroot.component.ui.menu.menuitem.NameToStructItemView;
import com.kingagroot.component.ui.menu.menuitem.FormatItemView;
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
import com.kingagroot.component.ui.widget.sup.OnSupSelectListener;
import com.kingagroot.component.ui.widget.sup.SupCollectView$OnSupMenuListener;
import android.view.ViewGroup$LayoutParams;
import android.util.TypedValue;
import android.widget.FrameLayout$LayoutParams;
import com.kingagroot.component.ui.view.HintView;
import com.kingagroot.component.ui.widget.sup.SupCollectView;
import com.kingagroot.component.ui.menu.SildeFlingGestureAction;
import com.kingagroot.component.ui.menu.ElementMenuView;
import com.kingagroot.component.ui.OnToolHintListener;
import com.kingagroot.kingdraw.core.OnKingDrawViewListener;
import com.kingagroot.kingdraw.core.OnCoreAvailableListener;
import com.kingagroot.component.ui.OnToolChangeListener;
import com.goodsrc.ui.library.widget.notch.NotchView;
import com.goodsrc.ui.library.widget.notch.NotchContext;
import com.kingagroot.kingdraw.core.view.MagnifierView;
import com.kingagroot.component.ui.menu.MainPaletteTopMenu;
import com.kingagroot.component.ui.menu.PaletteOtherView;
import com.kingagroot.component.ui.menu.PaletteBottomView;
import com.kingagroot.kingdraw.core.view.KingDrawView;
import com.kingagroot.component.ui.widget.richinput.InputMenuContainer;
import android.widget.FrameLayout;
import androidx.drawerlayout.widget.DrawerLayout;
import com.kingagroot.component.ui.ComponentBaseActivity;

public class NewPaletteActivity extends ComponentBaseActivity
{
    protected static final int REQUEST_CODE_SUP_TABLE = 1100;
    private boolean dataChange;
    protected DrawerLayout dlPalette;
    protected FrameLayout flLandscape;
    protected FrameLayout flPortrait;
    protected InputMenuContainer inputMenu;
    private boolean isGestureOpen;
    private boolean isInitRequestedOrientation;
    private boolean isPad;
    private boolean isSaving;
    protected boolean isVertical;
    protected KingDrawView kingDraw;
    protected PaletteBottomView landBottomTool;
    protected PaletteOtherView landRightTool;
    protected MainPaletteTopMenu landTopTool;
    protected MagnifierView magnifier;
    private boolean needUpdateFile;
    NotchContext notchContext;
    NotchView notchView;
    private OnToolChangeListener onAtomToolChangeListener;
    private OnToolChangeListener onBottomToolChangeListener;
    private OnCoreAvailableListener onCoreAvailableListener;
    protected OnKingDrawViewListener onKingDrawViewListener;
    private OnToolHintListener onToolHintListener;
    private OnToolChangeListener onTopToolChangeListener;
    protected ElementMenuView paletteMenuElement;
    private NewPaletteActivity.NewPaletteActivity$RequestedOrientationListener requestedOrientationListener;
    protected SildeFlingGestureAction sildeFlingGestureAction;
    protected SupCollectView supCollect;
    protected HintView tvHint;
    protected VerticalBottomToolView verticalBottomTool;
    protected VerticalRightToolView verticalRightTool;
    protected VerticalTopToolView verticalTopTool;
    
    public NewPaletteActivity() {
        this.isVertical = true;
        this.isSaving = false;
        this.isInitRequestedOrientation = false;
        this.onBottomToolChangeListener = (OnToolChangeListener)new NewPaletteActivity$1(this);
        this.onAtomToolChangeListener = (OnToolChangeListener)new NewPaletteActivity$2(this);
        this.onTopToolChangeListener = (OnToolChangeListener)new NewPaletteActivity$3(this);
        this.onToolHintListener = (OnToolHintListener)new NewPaletteActivity$4(this);
        this.onKingDrawViewListener = (OnKingDrawViewListener)new NewPaletteActivity$7(this);
        this.onCoreAvailableListener = (OnCoreAvailableListener)new NewPaletteActivity$8(this);
    }
    
    private void clearVerticalTools() {
        this.verticalBottomTool.clearCheck();
        this.verticalRightTool.clearCheck();
        this.verticalRightTool.setOtherToolSelect();
        this.paletteMenuElement.clearCheck();
    }
    
    private void setBottomViewSize(final boolean b) {
        final FrameLayout$LayoutParams layoutParams = (FrameLayout$LayoutParams)this.verticalBottomTool.getLayoutParams();
        if (b) {
            layoutParams.width = (int)TypedValue.applyDimension(1, 300.0f, this.getResources().getDisplayMetrics());
        }
        else {
            layoutParams.width = -1;
        }
        layoutParams.gravity = 81;
        this.verticalBottomTool.setLayoutParams((ViewGroup$LayoutParams)layoutParams);
    }
    
    private void setSupClick() {
        this.supCollect.setOnSupMenuListener((SupCollectView$OnSupMenuListener)new NewPaletteActivity$5(this));
        this.supCollect.setOnSupSelectListener((OnSupSelectListener)new NewPaletteActivity$6(this));
    }
    
    protected void OnOrientationListenerCallBack(final boolean b) {
        final NewPaletteActivity.NewPaletteActivity$RequestedOrientationListener requestedOrientationListener = this.requestedOrientationListener;
        if (requestedOrientationListener != null) {
            requestedOrientationListener.onOrientationChange(b);
        }
    }
    
    protected void addRequestedOrientationListener(final NewPaletteActivity.NewPaletteActivity$RequestedOrientationListener requestedOrientationListener) {
        if (requestedOrientationListener != null) {
            this.requestedOrientationListener = requestedOrientationListener;
        }
    }
    
    protected void clearLandTools() {
        this.landTopTool.clearCheck();
        this.paletteMenuElement.clearCheck();
        this.landBottomTool.clearCheck();
        this.landRightTool.clearCheck();
    }
    
    protected void endSave() {
        this.isSaving = false;
    }
    
    protected List<ToolBaseItemView> initTopMenu() {
        final ArrayList list = new ArrayList();
        final ClearItemView clearItemView = new ClearItemView((Context)this);
        ((ToolBaseItemView)clearItemView).setKingDrawView(this.kingDraw);
        final ColorItemView colorItemView = new ColorItemView((Context)this);
        ((ToolBaseItemView)colorItemView).setKingDrawView(this.kingDraw);
        final AlignItemView alignItemView = new AlignItemView((Context)this);
        ((ToolBaseItemView)alignItemView).setKingDrawView(this.kingDraw);
        final SelectItemView selectItemView = new SelectItemView((Context)this);
        final ChemInfoItemView chemInfoItemView = new ChemInfoItemView((Context)this);
        ((ToolBaseItemView)chemInfoItemView).setKingDrawView(this.kingDraw);
        final CopyItemView copyItemView = new CopyItemView((Context)this);
        ((ToolBaseItemView)copyItemView).setKingDrawView(this.kingDraw);
        final FormatItemView formatItemView = new FormatItemView((Context)this);
        ((ToolBaseItemView)formatItemView).setKingDrawView(this.kingDraw);
        final NameToStructItemView nameToStructItemView = new NameToStructItemView((Context)this);
        ((ToolBaseItemView)nameToStructItemView).setKingDrawView(this.kingDraw);
        final StructToNameItemView structToNameItemView = new StructToNameItemView((Context)this);
        ((ToolBaseItemView)structToNameItemView).setKingDrawView(this.kingDraw);
        final ChiralItemView chiralItemView = new ChiralItemView((Context)this);
        ((ToolBaseItemView)chiralItemView).setKingDrawView(this.kingDraw);
        final To3dItemView to3dItemView = new To3dItemView((Context)this);
        ((ToolBaseItemView)to3dItemView).setKingDrawView(this.kingDraw);
        final SyntheticPredictionItemView syntheticPredictionItemView = new SyntheticPredictionItemView((Context)this);
        ((ToolBaseItemView)syntheticPredictionItemView).setKingDrawView(this.kingDraw);
        final GridItemView gridItemView = new GridItemView((Context)this);
        ((ToolBaseItemView)gridItemView).setKingDrawView(this.kingDraw);
        ((List)list).add((Object)clearItemView);
        ((List)list).add((Object)colorItemView);
        ((List)list).add((Object)alignItemView);
        ((List)list).add((Object)selectItemView);
        ((List)list).add((Object)chemInfoItemView);
        ((List)list).add((Object)copyItemView);
        ((List)list).add((Object)formatItemView);
        ((List)list).add((Object)nameToStructItemView);
        ((List)list).add((Object)structToNameItemView);
        ((List)list).add((Object)chiralItemView);
        ((List)list).add((Object)to3dItemView);
        ((List)list).add((Object)syntheticPredictionItemView);
        ((List)list).add((Object)gridItemView);
        return (List<ToolBaseItemView>)list;
    }
    
    protected void initView() {
        this.dlPalette = (DrawerLayout)this.findViewById(R$id.dl_palette);
        this.kingDraw = (KingDrawView)this.findViewById(R$id.king_draw);
        this.paletteMenuElement = (ElementMenuView)this.findViewById(R$id.palette_menu_element);
        this.magnifier = (MagnifierView)this.findViewById(R$id.magnifier);
        this.inputMenu = (InputMenuContainer)this.findViewById(R$id.input_menu);
        this.tvHint = (HintView)this.findViewById(R$id.tv_hint);
        this.flPortrait = (FrameLayout)this.findViewById(R$id.fl_portrait);
        this.verticalRightTool = (VerticalRightToolView)this.findViewById(R$id.vertical_right_tool);
        this.verticalBottomTool = (VerticalBottomToolView)this.findViewById(R$id.vertical_bottom_tool);
        this.verticalTopTool = (VerticalTopToolView)this.findViewById(R$id.vertical_top_tool);
        this.flLandscape = (FrameLayout)this.findViewById(R$id.fl_landscape);
        this.landRightTool = (PaletteOtherView)this.findViewById(R$id.land_right_tool);
        this.landBottomTool = (PaletteBottomView)this.findViewById(R$id.land_bottom_tool);
        this.landTopTool = (MainPaletteTopMenu)this.findViewById(R$id.land_top_tool);
        this.supCollect = (SupCollectView)this.findViewById(R$id.sup_collect);
        this.dlPalette.setScrimColor(0);
        this.kingDraw.setOnKingDrawViewListener(this.onKingDrawViewListener);
        this.kingDraw.addCoreAvailableListener(this.onCoreAvailableListener);
        this.paletteMenuElement.setOnToolChangeListener(this.onAtomToolChangeListener);
        this.paletteMenuElement.setOnToolHintListener(this.onToolHintListener);
        this.verticalBottomTool.setKingDrawView(this.kingDraw);
        this.verticalRightTool.setKingDrawView(this.kingDraw);
        this.verticalRightTool.setSupView(this.supCollect);
        this.verticalRightTool.setOnToolChangeListener(this.onTopToolChangeListener);
        this.verticalRightTool.setOnToolHintListener(this.onToolHintListener);
        this.verticalTopTool.contextKingDrawView(this.kingDraw);
        this.verticalTopTool.setOnToolHintListener(this.onToolHintListener);
        this.landRightTool.setSupView(this.supCollect);
        this.landTopTool.initData((List)this.initTopMenu());
        this.landTopTool.contextKingDrawView(this.kingDraw);
        this.landTopTool.setOnToolChangeListener(this.onTopToolChangeListener);
        this.landTopTool.setOnToolHintListener(this.onToolHintListener);
        this.landBottomTool.setOnToolChangeListener(this.onBottomToolChangeListener);
        this.landBottomTool.setOnToolHintListener(this.onToolHintListener);
        this.paletteMenuElement.contextKingDrawView(this.kingDraw);
        this.landRightTool.setSupShow(false);
        this.setSupClick();
    }
    
    public boolean isDataChange() {
        return this.dataChange;
    }
    
    public boolean isNeedUpdateFile() {
        return this.needUpdateFile;
    }
    
    public void isPortrait(final boolean b, final NewPaletteActivity.NewPaletteActivity$RequestedOrientationListener newPaletteActivity$RequestedOrientationListener) {
        this.addRequestedOrientationListener(newPaletteActivity$RequestedOrientationListener);
        final int requestedOrientation = this.getRequestedOrientation();
        if (b) {
            if (requestedOrientation != 1) {
                this.setRequestedOrientation(1);
            }
            if (this.getResources().getConfiguration().orientation == 1) {
                this.clearLandTools();
                this.OnOrientationListenerCallBack(true);
            }
        }
        else {
            if (requestedOrientation != 11) {
                this.setRequestedOrientation(11);
            }
            if (this.getResources().getConfiguration().orientation == 2) {
                this.clearVerticalTools();
                this.flPortrait.setVisibility(8);
                this.flLandscape.setVisibility(0);
                this.landRightTool.getPaletteMove().setSelect(true);
                this.OnOrientationListenerCallBack(false);
            }
        }
    }
    
    public void isPortrait(final boolean b, final boolean b2) {
        this.isInitRequestedOrientation = false;
        this.setElementViewSize(b);
        this.paletteMenuElement.invalidate();
        final int requestedOrientation = this.getRequestedOrientation();
        if (b) {
            if (requestedOrientation != 1) {
                this.setRequestedOrientation(1);
            }
            if (this.getResources().getConfiguration().orientation == 1) {
                this.clearLandTools();
            }
            else if (b2) {
                this.isInitRequestedOrientation = true;
            }
        }
        else {
            if (requestedOrientation != 11) {
                this.setRequestedOrientation(11);
            }
            if (this.getResources().getConfiguration().orientation == 2) {
                this.clearVerticalTools();
                this.flPortrait.setVisibility(8);
                this.flLandscape.setVisibility(0);
                this.landRightTool.getPaletteMove().setSelect(true);
            }
            else if (b2) {
                this.isInitRequestedOrientation = true;
            }
        }
        this.isVertical = b;
        this.inputMenu.isPortrait(b);
    }
    
    protected boolean isSaving() {
        return this.isSaving;
    }
    
    protected boolean keyBoardEvent(final MotionEvent motionEvent) {
        final InputMenuContainer inputMenu = this.inputMenu;
        return (inputMenu == null || !inputMenu.keyBoardEvent(motionEvent)) && super.keyBoardEvent(motionEvent);
    }
    
    public void longPressBlankSpace(final float n, final float n2) {
    }
    
    public void longPressSelectSpace() {
    }
    
    public void notifyDataChange() {
        this.dataChange = true;
    }
    
    protected void onActivityResult(int n, int n2, final Intent intent) {
        super.onActivityResult(n, n2, intent);
        this.paletteMenuElement.onActivityResult(n, n2, intent);
        if (n == 1100 && intent != null) {
            final GSGroupModel gSupTool = (GSGroupModel)intent.getSerializableExtra(BaseSupActivity.KEY_SUP_FILE);
            n2 = 0;
            GestureTool gestureTool = null;
            n = n2;
            if (this.kingDraw.getToolNameEnum() == ToolNameEnum.GGESTURE_TOOL) {
                final GestureTool gestureTool2 = this.kingDraw.getGestureTool();
                n = n2;
                if ((gestureTool = gestureTool2) != null) {
                    n = 1;
                    gestureTool = gestureTool2;
                }
            }
            if (n != 0 && this.isGestureOpen) {
                gestureTool.SetSupName(gSupTool.getName());
            }
            else {
                this.setGSupTool(gSupTool);
            }
        }
    }
    
    public void onConfigChange(final PaletteConfigModel paletteConfigModel) {
    }
    
    public void onConfigurationChanged(final Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (!this.isPad) {
            this.setOrientationConfig(configuration.orientation);
        }
    }
    
    @Override
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.setContentView(R$layout.activity_palette_new);
        this.initView();
    }
    
    protected void onDestroy() {
        super.onDestroy();
        this.verticalBottomTool.onDestroy();
    }
    
    public void onDrag(final boolean b) {
        this.verticalRightTool.onDrag(b);
    }
    
    public void onElementChanged(final boolean b) {
    }
    
    public void onFormatChanged(final FormatValue formatValue) {
    }
    
    public void onKingDrawViewAvailable() {
    }
    
    protected void onPause() {
        super.onPause();
        this.inputMenu.onPause();
    }
    
    protected void onResume() {
        super.onResume();
        this.inputMenu.onResume();
        this.kingDraw.onResume();
        this.verticalBottomTool.onResume();
        this.supCollect.notifyData();
    }
    
    public void onScaleChanged(final float n) {
        this.tvHint.show(GNumberUtil.format("0.0", (double)n));
    }
    
    public void onSelectEleChanged(final boolean b) {
        if (b) {
            this.verticalBottomTool.setPopCustomerColor(KDColor.parseColor(this.kingDraw.getSelectedColor()));
        }
    }
    
    public void onStartInput(final boolean b, final String s, final float n, final float n2) {
        final InputMenuContainer inputMenu = this.inputMenu;
        if (inputMenu != null) {
            inputMenu.setVisibility(0);
            this.inputMenu.isInputSupName(false);
            this.verticalBottomTool.setPopDismiss();
            this.inputMenu.setContent(b, s, this.kingDraw.getFormatValue());
            this.inputMenu.setOnInputListner((InputMenuContainer$OnInputListner)new NewPaletteActivity$9(this));
        }
    }
    
    protected void onSupTable(final boolean isGestureOpen) {
        this.isGestureOpen = isGestureOpen;
    }
    
    public void paletteChangedTool(final String s, final String s2) {
    }
    
    protected void removeRequestedOrientationListener() {
        this.requestedOrientationListener = null;
    }
    
    public void setAiShow(final boolean picAiShow) {
        this.verticalRightTool.setPicAiShow(picAiShow);
    }
    
    public void setElementViewSize(final boolean b) {
        final FrameLayout$LayoutParams layoutParams = (FrameLayout$LayoutParams)this.paletteMenuElement.getLayoutParams();
        if (b) {
            layoutParams.height = Math.max(ScreenUtils.getScreenHeight((Context)this), ScreenUtils.getScreenWidth((Context)this)) / 2;
        }
        else {
            layoutParams.height = -1;
        }
        layoutParams.gravity = 19;
        this.paletteMenuElement.setLayoutParams((ViewGroup$LayoutParams)layoutParams);
    }
    
    public void setFileName(final String s) {
        this.verticalTopTool.setFileName(s);
        this.landTopTool.setFileName(s);
    }
    
    protected void setGSupTool(final GSGroupModel choiceSup) {
        if (this.isVertical) {
            this.clearVerticalTools();
            this.verticalRightTool.setChooseSupState(choiceSup);
        }
        else {
            this.clearLandTools();
            this.landRightTool.setChooseSupState(choiceSup);
        }
        this.supCollect.setChoiceSup(choiceSup);
        this.kingDraw.setTool(ToolEnum.GSUP_TOOL.nameEnum, choiceSup.getName());
    }
    
    public void setIsPad(final boolean isPad) {
        if (isPad) {
            this.flLandscape.setVisibility(8);
        }
        this.landRightTool.setScreenShow(isPad);
        this.verticalRightTool.setScreenShow(isPad);
        this.setBottomViewSize(this.isPad = isPad);
    }
    
    public void setNeedUpdateFile(final boolean needUpdateFile) {
        this.needUpdateFile = needUpdateFile;
    }
    
    protected void setOrientationConfig(final int n) {
        if (this.isInitRequestedOrientation) {
            if (n == 2) {
                this.flPortrait.setVisibility(8);
                this.flLandscape.setVisibility(0);
                this.verticalBottomTool.setPopDismiss();
                this.clearVerticalTools();
                this.landRightTool.getPaletteMove().setSelect(true);
            }
            else if (n == 1) {
                this.flPortrait.setVisibility(0);
                this.flLandscape.setVisibility(8);
                this.clearLandTools();
                this.verticalRightTool.setSelect(true);
            }
            this.isInitRequestedOrientation = false;
        }
        else {
            if (n == 2) {
                this.flPortrait.setVisibility(8);
                this.flLandscape.setVisibility(0);
                this.verticalBottomTool.setPopDismiss();
                this.clearVerticalTools();
                this.landRightTool.getPaletteMove().setSelect(true);
            }
            else if (n == 1) {
                this.flPortrait.setVisibility(0);
                this.flLandscape.setVisibility(8);
                this.clearLandTools();
                this.verticalRightTool.setSelect(true);
            }
            this.kingDraw.setTool(ToolNameEnum.G_DRAG_TOOL, (String)null);
        }
        if (n == 2) {
            this.OnOrientationListenerCallBack(false);
        }
        else if (n == 1) {
            this.OnOrientationListenerCallBack(true);
        }
    }
    
    protected void startSave() {
        this.isSaving = true;
    }
}

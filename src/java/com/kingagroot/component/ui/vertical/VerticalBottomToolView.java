package com.kingagroot.component.ui.vertical;

import com.kingagroot.component.ui.model.GDocumentTypeEnum;
import com.kingagroot.kingdraw.core.model.ChirlityAvliableEnum;
import com.kingagroot.kingdraw.core.tool.ChirlityTool;
import android.widget.AdapterView;
import com.kingagroot.kingdraw.core.tool.ToolNameEnum;
import com.kingagroot.kingdraw.core.tool.AlignTypeEnum;
import com.kingagroot.kingdraw.core.model.NodeDirectEnum;
import java.util.Collection;
import com.kingagroot.kingdraw.core.graphics.KDColor;
import com.kingagroot.component.ui.db.impl.GFormatValueDBImpl;
import android.widget.ListAdapter;
import java.util.Iterator;
import android.view.ViewGroup;
import com.kingagroot.component.ui.R;
import java.util.ArrayList;
import android.util.AttributeSet;
import java.util.List;
import com.kingagroot.component.ui.vertical.model.ToolBaseModel;
import com.kingagroot.kingdraw.core.view.KingDrawView;
import android.widget.GridView;
import com.kingagroot.component.ui.db.GFormatValueDBI;
import com.kingagroot.component.ui.model.GFormatValue;
import android.content.Context;
import android.view.View;
import android.widget.AdapterView$OnItemClickListener;
import android.widget.LinearLayout;

public class VerticalBottomToolView extends LinearLayout implements AdapterView$OnItemClickListener
{
    private BottomToolAdapter adapter;
    public BottomToolPop bottomToolPop;
    private boolean chiralEnable;
    private final View contentView;
    private final Context context;
    private GFormatValue formatValue;
    private GFormatValueDBI gformatvaluedbi;
    private GridView gvBottomTool;
    private boolean isChiralCheck;
    private boolean isFormatCheck;
    private KingDrawView kingDrawView;
    private PaletteBottomInterface paletteBottomInterface;
    private int paletteType;
    private boolean struct2NameAvailable;
    private ToolBaseModel toolBaseModel;
    private final List<ToolBaseModel> toolBaseModels;
    
    public VerticalBottomToolView(final Context context, final AttributeSet set) {
        super(context, set);
        this.toolBaseModels = (List<ToolBaseModel>)new ArrayList();
        this.context = context;
        this.contentView = View.inflate(context, R.layout.view_vertical_bottom_tool, (ViewGroup)this);
        this.initView();
    }
    
    private List<ToolBaseModel> initBottomToolData() {
        final ArrayList list = new ArrayList();
        final ToolDataManage toolDataManage = new ToolDataManage(this.context);
        for (final Integer n : toolDataManage.getParentList()) {
            for (final ToolBaseModel toolBaseModel : toolDataManage.getAllBaseModel()) {
                if (toolBaseModel.getToolId() == n) {
                    toolBaseModel.setSubChemList(toolDataManage.getChildList(n));
                    ((List)list).add((Object)toolBaseModel);
                    break;
                }
            }
        }
        return (List<ToolBaseModel>)list;
    }
    
    private void initView() {
        (this.gvBottomTool = (GridView)this.contentView.findViewById(R.id.gv_bottom_tool)).setOnItemClickListener((AdapterView$OnItemClickListener)this);
        final BottomToolAdapter bottomToolAdapter = new BottomToolAdapter(this.context, this.toolBaseModels);
        this.adapter = bottomToolAdapter;
        this.gvBottomTool.setAdapter((ListAdapter)bottomToolAdapter);
        this.setModelDates(this.initBottomToolData());
        this.gformatvaluedbi = (GFormatValueDBI)new GFormatValueDBImpl();
    }
    
    private void multiMenu(final ToolBaseModel toolBaseModel) {
        final BottomToolPop bottomToolPop = this.bottomToolPop;
        if (bottomToolPop != null && bottomToolPop.isShowing()) {
            this.bottomToolPop.setToolModels((List)toolBaseModel.getSubChemList());
        }
        else {
            (this.bottomToolPop = new BottomToolPop(this.context, (View)this)).setEnable(this.struct2NameAvailable);
            this.bottomToolPop.setChiralEnable(this.chiralEnable, this.isChiralCheck);
            this.bottomToolPop.setToolModels((List)toolBaseModel.getSubChemList());
            this.bottomToolPop.show((View)this);
        }
        if (!"ic_bottom_more_tools".equals((Object)toolBaseModel.getResourceName()) && !"ic_align_left".equals((Object)toolBaseModel.getResourceName())) {
            final PaletteBottomInterface paletteBottomInterface = this.paletteBottomInterface;
            if (paletteBottomInterface != null) {
                paletteBottomInterface.onFirstToolViewClick();
            }
            for (final ToolBaseModel toolBaseModel2 : toolBaseModel.getSubChemList()) {
                if (toolBaseModel2.getResourceName().equals((Object)toolBaseModel.getResourceName())) {
                    this.bottomToolPop.setCheckTool(toolBaseModel2);
                    this.setToolUse(toolBaseModel2);
                    final PaletteBottomInterface paletteBottomInterface2 = this.paletteBottomInterface;
                    if (paletteBottomInterface2 == null) {
                        continue;
                    }
                    paletteBottomInterface2.onToolNameShow(toolBaseModel2.getShowName());
                }
            }
        }
        else {
            final PaletteBottomInterface paletteBottomInterface3 = this.paletteBottomInterface;
            if (paletteBottomInterface3 != null) {
                paletteBottomInterface3.onToolDefaultSelectClick();
            }
            if ("ic_bottom_more_tools".equals((Object)toolBaseModel.getResourceName())) {
                this.bottomToolPop.setFormatSelect(this.isFormatCheck);
            }
        }
        this.bottomToolPop.setOnClickToolListener((BottomToolPop.OnClickToolListener)new VerticalBottomToolView$1(this));
    }
    
    private void setColorItemClick() {
        final PaletteBottomInterface paletteBottomInterface = this.paletteBottomInterface;
        if (paletteBottomInterface != null) {
            paletteBottomInterface.onFirstToolViewClick();
        }
        final PaletteBottomInterface paletteBottomInterface2 = this.paletteBottomInterface;
        if (paletteBottomInterface2 != null) {
            paletteBottomInterface2.onToolDefaultSelectClick();
        }
        final int color = KDColor.parseColor(this.kingDrawView.getSelectedColor());
        final BottomToolPop bottomToolPop = this.bottomToolPop;
        if (bottomToolPop != null && bottomToolPop.isShowing()) {
            this.bottomToolPop.setViewContent(0);
        }
        else {
            (this.bottomToolPop = new BottomToolPop(this.context, (View)this)).setEnable(this.struct2NameAvailable);
            this.bottomToolPop.setChiralEnable(this.chiralEnable, this.isChiralCheck);
            this.bottomToolPop.setViewContent(0);
            this.bottomToolPop.show((View)this);
        }
        this.bottomToolPop.setCustomColor(color);
        this.bottomToolPop.setOnClickColorListener((BottomToolPop.OnClickColorListener)new VerticalBottomToolView$2(this));
    }
    
    private void setModelDates(final List<ToolBaseModel> list) {
        this.toolBaseModels.clear();
        this.toolBaseModels.addAll((Collection)list);
        this.adapter.notifyDataSetChanged();
    }
    
    private void setToolUse(final ToolBaseModel toolBaseModel) {
        if ("GAlignmentTool".equals((Object)toolBaseModel.getObjectName())) {
            if ("ic_align_text_auto".equals((Object)toolBaseModel.getResourceName())) {
                this.kingDrawView.setNodeDirect(NodeDirectEnum.AUTO);
            }
            else if ("ic_align_text_right".equals((Object)toolBaseModel.getResourceName())) {
                this.kingDrawView.setNodeDirect(NodeDirectEnum.USER_RIGHT);
            }
            else {
                this.kingDrawView.setSelectElementAlignType(AlignTypeEnum.valueOfTypee(String.valueOf(Integer.parseInt(toolBaseModel.getAttribute().getAlignmentType()) - 1)));
            }
        }
        else {
            final ToolNameEnum toolEnum = ToolNameEnum.getToolEnum(toolBaseModel.getObjectName());
            if (toolEnum != null) {
                String value;
                if (toolBaseModel.getAttribute() != null && toolBaseModel.getAttribute().getShapeStyle() != -1) {
                    value = String.valueOf(toolBaseModel.getAttribute().getShapeStyle());
                }
                else {
                    value = "";
                }
                this.kingDrawView.setTool(toolEnum, value);
            }
        }
    }
    
    public void clearCheck() {
        final BottomToolPop bottomToolPop = this.bottomToolPop;
        if (bottomToolPop != null && bottomToolPop.isShowing()) {
            this.bottomToolPop.clearCheck();
        }
        if (this.toolBaseModel != null && ToolDataManage.PIC_TEXT.equals((Object)this.toolBaseModel.getResourceName())) {
            this.gvBottomTool.clearChoices();
            this.gvBottomTool.clearFocus();
        }
        this.adapter.clickTemp = -1;
        this.adapter.notifyDataSetChanged();
    }
    
    public void onDestroy() {
        final BottomToolPop bottomToolPop = this.bottomToolPop;
        if (bottomToolPop != null && bottomToolPop.isShowing()) {
            this.bottomToolPop.dismiss();
        }
    }
    
    public void onItemClick(final AdapterView<?> adapterView, final View view, final int selection, final long n) {
        final ToolBaseModel item = this.adapter.getItem(selection);
        this.toolBaseModel = item;
        if (item.getSubChemList() != null && item.getSubChemList().size() > 0) {
            this.multiMenu(item);
        }
        else if (ToolDataManage.PIC_COLOR.equals((Object)item.getResourceName())) {
            this.setColorItemClick();
        }
        else if (ToolDataManage.PIC_3D.equals((Object)item.getResourceName())) {
            final PaletteBottomInterface paletteBottomInterface = this.paletteBottomInterface;
            if (paletteBottomInterface != null) {
                paletteBottomInterface.on3dViewClick();
            }
        }
        else if (ToolDataManage.PIC_CLEAN_UP.equals((Object)item.getResourceName())) {
            final PaletteBottomInterface paletteBottomInterface2 = this.paletteBottomInterface;
            if (paletteBottomInterface2 != null) {
                paletteBottomInterface2.onCleanUpClick();
            }
        }
        else if (ToolDataManage.PIC_CHEM_INFO.equals((Object)item.getResourceName())) {
            final PaletteBottomInterface paletteBottomInterface3 = this.paletteBottomInterface;
            if (paletteBottomInterface3 != null) {
                paletteBottomInterface3.onChemInfoClick();
            }
        }
        else {
            final PaletteBottomInterface paletteBottomInterface4 = this.paletteBottomInterface;
            if (paletteBottomInterface4 != null) {
                paletteBottomInterface4.onFirstToolViewClick();
            }
            if (ToolDataManage.PIC_TEXT.equals((Object)item.getResourceName())) {
                final PaletteBottomInterface paletteBottomInterface5 = this.paletteBottomInterface;
                if (paletteBottomInterface5 != null) {
                    paletteBottomInterface5.onToolNameShow(item.getShowName());
                }
                this.setPopDismiss();
            }
            this.setToolUse(item);
        }
        if (!ToolDataManage.PIC_3D.equals((Object)item.getResourceName()) && !ToolDataManage.PIC_CLEAN_UP.equals((Object)item.getResourceName()) && !ToolDataManage.PIC_CHEM_INFO.equals((Object)item.getResourceName())) {
            this.adapter.setSelection(selection);
        }
        this.adapter.notifyDataSetChanged();
    }
    
    public void onResume() {
        final BottomToolPop bottomToolPop = this.bottomToolPop;
        if (bottomToolPop != null && bottomToolPop.isShowing()) {
            this.bottomToolPop.onResume();
        }
    }
    
    public void setBottomViewClickListener(final PaletteBottomInterface paletteBottomInterface) {
        this.paletteBottomInterface = paletteBottomInterface;
    }
    
    public void setChiralClickState() {
        final boolean isChiralCheck = this.isChiralCheck ^ true;
        this.isChiralCheck = isChiralCheck;
        this.bottomToolPop.setChiralEnable(true, isChiralCheck);
    }
    
    public void setChiralState(final boolean chiralEnable) {
        if (this.paletteType != ToolDataManage.TYPE_BASE) {
            final BottomToolPop bottomToolPop = this.bottomToolPop;
            if (bottomToolPop != null && bottomToolPop.isShowing()) {
                this.bottomToolPop.setChiralEnable(false, this.isChiralCheck);
            }
        }
        else {
            this.chiralEnable = chiralEnable;
            if (chiralEnable) {
                final ChirlityAvliableEnum chirlityAvailable = ChirlityTool.ChirlityAvailable(this.kingDrawView.getPaletteId());
                if (chirlityAvailable == ChirlityAvliableEnum.Disable) {
                    this.chiralEnable = false;
                }
                else if (chirlityAvailable == ChirlityAvliableEnum.Close) {
                    this.chiralEnable = true;
                    this.isChiralCheck = true;
                }
                else if (chirlityAvailable == ChirlityAvliableEnum.Open) {
                    this.chiralEnable = true;
                    this.isChiralCheck = false;
                }
            }
            final BottomToolPop bottomToolPop2 = this.bottomToolPop;
            if (bottomToolPop2 != null && bottomToolPop2.isShowing()) {
                this.bottomToolPop.setChiralEnable(this.chiralEnable, this.isChiralCheck);
            }
        }
    }
    
    public void setDefaultFormat(final GFormatValue formatValue) {
        if (formatValue.getDocumentType() != GDocumentTypeEnum.ACS96\u683c\u5f0f.getCode()) {
            this.formatValue = formatValue;
            this.isFormatCheck = false;
        }
        else {
            this.isFormatCheck = true;
        }
        final BottomToolPop bottomToolPop = this.bottomToolPop;
        if (bottomToolPop != null && bottomToolPop.isShowing()) {
            this.bottomToolPop.setFormatSelect(this.isFormatCheck);
        }
    }
    
    public void setKingDrawView(final KingDrawView kingDrawView) {
        this.kingDrawView = kingDrawView;
    }
    
    public void setPaletteType(final int n) {
        this.paletteType = n;
        this.adapter.setPaletteType(n);
    }
    
    public void setPopCustomerColor(final int customColor) {
        final BottomToolPop bottomToolPop = this.bottomToolPop;
        if (bottomToolPop != null && bottomToolPop.isShowing()) {
            this.bottomToolPop.setCustomColor(customColor);
        }
    }
    
    public void setPopDismiss() {
        final BottomToolPop bottomToolPop = this.bottomToolPop;
        if (bottomToolPop != null && bottomToolPop.isShowing()) {
            this.bottomToolPop.dismiss();
        }
    }
    
    public void setToolToNameEnable(final boolean b) {
        this.struct2NameAvailable = b;
        final BottomToolPop bottomToolPop = this.bottomToolPop;
        if (bottomToolPop != null && bottomToolPop.isShowing()) {
            this.bottomToolPop.setEnable(b);
        }
    }
}

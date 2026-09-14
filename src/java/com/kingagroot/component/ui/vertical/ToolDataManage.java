package com.kingagroot.component.ui.vertical;

import java.util.Iterator;
import java.util.ArrayList;
import com.goodsrc.library.utils.GsonUtil;
import com.google.gson.Gson;
import android.content.Context;
import com.kingagroot.component.ui.vertical.model.VerticalDataModel;
import com.kingagroot.component.ui.vertical.model.ToolBaseModel;
import com.kingagroot.component.ui.vertical.model.VerticalChildDataModel;
import java.util.List;

public class ToolDataManage
{
    public static String PIC_3D = "ic_more_3d";
    public static String PIC_CHEM_INFO = "ic_chem_attr";
    public static String PIC_CHIRAL_NAME = "ic_chiral_disable";
    public static String PIC_CLEAN_UP = "ic_clean_up";
    public static String PIC_COLOR = "ic_tool_colors";
    public static String PIC_FORMAT = "ic_draw_96_nor";
    public static String PIC_GRID_LINE = "ic_draw_grids_nor";
    public static String PIC_NAME_STRUCT = "ic_name_iupac";
    public static String PIC_PREDICTION = "ic_forecast_disabled";
    public static String PIC_STRUCT_NAME = "ic_iupac_name";
    public static String PIC_TEXT = "ic_text";
    public static int TYPE_BASE = 0;
    public static int TYPE_SEARCH = 2;
    public static int TYPE_SUP = 1;
    private final List<VerticalChildDataModel> list;
    private final List<ToolBaseModel> toolBaseModelList;
    private final VerticalDataModel verticalDataModel;
    
    public ToolDataManage(final Context context) {
        this.verticalDataModel = (VerticalDataModel)new Gson().fromJson(GsonUtil.getJson(context, "verticalTool.json"), (Class)VerticalDataModel.class);
        this.toolBaseModelList = (List<ToolBaseModel>)new Gson().fromJson(GsonUtil.getJson(context, "chemToolList.json"), new ToolDataManage$1(this).getType());
        this.list = this.verticalDataModel.getList();
    }
    
    private List<ToolBaseModel> getModelList(final List<Integer> list) {
        final ArrayList list2 = new ArrayList();
        for (final Integer n : list) {
            for (final ToolBaseModel toolBaseModel : this.toolBaseModelList) {
                if (toolBaseModel.getToolId() == n) {
                    ((List)list2).add((Object)toolBaseModel);
                }
            }
        }
        return (List<ToolBaseModel>)list2;
    }
    
    public List<ToolBaseModel> getAllBaseModel() {
        return this.toolBaseModelList;
    }
    
    public List<ToolBaseModel> getChildList(final int n) {
        for (int i = 0; i < this.list.size(); ++i) {
            if (((VerticalChildDataModel)this.list.get(i)).getParentId() == n) {
                return this.getModelList(((VerticalChildDataModel)this.list.get(i)).getChildList());
            }
        }
        return null;
    }
    
    public List<Integer> getParentList() {
        return this.verticalDataModel.getParentIds();
    }
}

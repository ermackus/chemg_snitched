package com.kingagroot.component.ui.vertical;

import android.view.View$MeasureSpec;
import java.util.Collection;
import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.view.View$OnClickListener;
import androidx.recyclerview.widget.RecyclerView$Adapter;
import androidx.recyclerview.widget.RecyclerView$LayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.kingagroot.component.ui.R$id;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ColorDrawable;
import android.view.ViewGroup;
import com.kingagroot.component.ui.R$layout;
import android.view.LayoutInflater;
import java.util.ArrayList;
import androidx.recyclerview.widget.RecyclerView;
import com.kingagroot.component.ui.vertical.model.ToolBaseModel;
import java.util.List;
import android.widget.RelativeLayout;
import android.os.Handler;
import android.widget.FrameLayout;
import com.kingagroot.component.ui.view.RotateTextView;
import android.content.Context;
import android.view.View;
import com.kingagroot.component.ui.widget.toolpop.PaletteBasePop;

public class BottomToolPop extends PaletteBasePop
{
    private static final int MSG_TYPE_UPDATE = 1001;
    private ColorAdapter colorAdapter;
    private View contentView;
    private final Context context;
    private RotateTextView custom;
    private int customerColor;
    private FrameLayout flCommonTool;
    Handler handler;
    boolean isRegisterReceiver;
    private BottomToolPop.BottomToolPop$OnClickColorListener onClickColorListener;
    private BottomToolPop.BottomToolPop$OnClickToolListener onClickToolListener;
    private BottomToolPop.BottomToolPop$OrientationChangedReceive orientationChangedReceive;
    private final View parentView;
    private RelativeLayout rlMoreColor;
    private ToolBottomAdapter toolAdapter;
    private final List<ToolBaseModel> toolModels;
    private RecyclerView viewTools;
    
    public BottomToolPop(final Context context, final View parentView) {
        this.toolModels = (List<ToolBaseModel>)new ArrayList();
        this.isRegisterReceiver = false;
        this.handler = (Handler)new BottomToolPop$1(this);
        this.customerColor = -16777216;
        this.context = context;
        this.parentView = parentView;
        final LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService("layout_inflater");
        if (layoutInflater != null) {
            this.contentView = layoutInflater.inflate(R$layout.pop_bottom_tool, (ViewGroup)null);
        }
        this.setContentView(this.contentView);
        this.setWidth(parentView.getWidth());
        this.setHeight(-2);
        this.setFocusable(false);
        this.setOutsideTouchable(false);
        this.update();
        this.setBackgroundDrawable((Drawable)new ColorDrawable(0));
        this.initView();
    }
    
    private List<Integer> getNormalColor() {
        final ArrayList list = new ArrayList();
        ((List)list).add((Object)Color.parseColor("#FF0000"));
        ((List)list).add((Object)Color.parseColor("#FFFF00"));
        ((List)list).add((Object)Color.parseColor("#0000FF"));
        ((List)list).add((Object)Color.parseColor("#008000"));
        ((List)list).add((Object)Color.parseColor("#000000"));
        ((List)list).add((Object)Color.parseColor("#00FFFF"));
        ((List)list).add((Object)Color.parseColor("#800080"));
        return (List<Integer>)list;
    }
    
    private void initColorView() {
        this.rlMoreColor = (RelativeLayout)this.contentView.findViewById(R$id.rl_more_color);
        final RecyclerView recyclerView = (RecyclerView)this.contentView.findViewById(R$id.list_color);
        this.custom = (RotateTextView)this.contentView.findViewById(R$id.custom);
        final FrameLayout frameLayout = (FrameLayout)this.contentView.findViewById(R$id.fl_custom);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this.context);
        layoutManager.setOrientation(0);
        recyclerView.setLayoutManager((RecyclerView$LayoutManager)layoutManager);
        recyclerView.setAdapter((RecyclerView$Adapter)(this.colorAdapter = new ColorAdapter(this.context, this.getNormalColor())));
        this.colorAdapter.setOnItemClickListener((ColorAdapter.ColorAdapter$OnColorItemClickListener)new ColorAdapter$OnColorItemClickListener(this) {
            final BottomToolPop this$0;
            
            public void onClick(int itemValue) {
                itemValue = this.this$0.colorAdapter.getItemValue(itemValue);
                if (this.this$0.onClickColorListener != null) {
                    this.this$0.onClickColorListener.onClickItemColor(itemValue);
                }
            }
        });
        frameLayout.setOnClickListener((View$OnClickListener)new BottomToolPop$4(this));
    }
    
    private void initUsualView() {
        this.flCommonTool = (FrameLayout)this.contentView.findViewById(R$id.fl_common_tool);
        this.viewTools = (RecyclerView)this.contentView.findViewById(R$id.view_tools);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this.context);
        layoutManager.setOrientation(0);
        this.viewTools.setLayoutManager((RecyclerView$LayoutManager)layoutManager);
        final ToolBottomAdapter toolBottomAdapter = new ToolBottomAdapter(this.context, this.toolModels);
        this.toolAdapter = toolBottomAdapter;
        this.viewTools.setAdapter((RecyclerView$Adapter)toolBottomAdapter);
        this.toolAdapter.setOnItemClickListener((ToolBottomAdapter.ToolBottomAdapter$OnListItemClickListener)new ToolBottomAdapter$OnListItemClickListener(this) {
            final BottomToolPop this$0;
            
            public void onClick(final int n) {
                final ToolBaseModel checkTool = (ToolBaseModel)this.this$0.toolModels.get(n);
                if (this.this$0.onClickToolListener != null) {
                    this.this$0.onClickToolListener.onClickTool(checkTool);
                }
                if (checkTool.getToolId() != 124 && !"GAlignmentTool".equals((Object)checkTool.getObjectName()) && !"ic_name_iupac".equals((Object)checkTool.getResourceName()) && !"ic_iupac_name".equals((Object)checkTool.getResourceName()) && !ToolDataManage.PIC_PREDICTION.equals((Object)checkTool.getResourceName()) && !ToolDataManage.PIC_FORMAT.equals((Object)checkTool.getResourceName()) && !ToolDataManage.PIC_GRID_LINE.equals((Object)checkTool.getResourceName())) {
                    this.this$0.setCheckTool(checkTool);
                }
            }
        });
    }
    
    private void initView() {
        this.initUsualView();
        this.initColorView();
    }
    
    private void registerReceiver() {
        if (this.isRegisterReceiver) {
            return;
        }
        this.orientationChangedReceive = new BottomToolPop.BottomToolPop$OrientationChangedReceive(this, (BottomToolPop$1)null);
        final IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.CONFIGURATION_CHANGED");
        this.context.registerReceiver((BroadcastReceiver)this.orientationChangedReceive, intentFilter);
        this.isRegisterReceiver = true;
    }
    
    private void sendUpdateMsg() {
        this.handler.removeMessages(1001);
        this.handler.sendEmptyMessageDelayed(1001, 200L);
    }
    
    private void unregisterReceiver() {
        final BottomToolPop.BottomToolPop$OrientationChangedReceive orientationChangedReceive = this.orientationChangedReceive;
        if (orientationChangedReceive != null && this.isRegisterReceiver) {
            this.context.unregisterReceiver((BroadcastReceiver)orientationChangedReceive);
            this.isRegisterReceiver = false;
        }
    }
    
    public void clearCheck() {
        this.viewTools.clearFocus();
        this.toolAdapter.setCheckTool(null);
        this.toolAdapter.notifyDataSetChanged();
    }
    
    public void dismiss() {
        super.dismiss();
        this.unregisterReceiver();
        this.handler.removeMessages(1001);
    }
    
    public View getIndicatorView() {
        return null;
    }
    
    public boolean isNeedMove() {
        return false;
    }
    
    public void onResume() {
        this.sendUpdateMsg();
    }
    
    public void setCheckTool(final ToolBaseModel toolBaseModel) {
        this.toolAdapter.setCheckTool(toolBaseModel.getResourceName());
        this.toolAdapter.notifyDataSetChanged();
    }
    
    public void setChiralEnable(final boolean b, final boolean b2) {
        this.toolAdapter.setChiralEnable(b, b2);
        this.toolAdapter.notifyDataSetChanged();
    }
    
    public void setCustomColor(final int n) {
        this.customerColor = n;
        this.custom.setRotateColor(n);
    }
    
    public void setEnable(final boolean nameEnable) {
        this.toolAdapter.setNameEnable(nameEnable);
        this.toolAdapter.notifyDataSetChanged();
    }
    
    public void setFormatSelect(final boolean formatCheck) {
        this.toolAdapter.setFormatCheck(formatCheck);
        this.toolAdapter.notifyDataSetChanged();
    }
    
    public void setLineSelect(final boolean lineCheck) {
        this.toolAdapter.setLineCheck(lineCheck);
        this.toolAdapter.notifyDataSetChanged();
    }
    
    public void setOnClickColorListener(final BottomToolPop.BottomToolPop$OnClickColorListener onClickColorListener) {
        this.onClickColorListener = onClickColorListener;
    }
    
    public void setOnClickToolListener(final BottomToolPop.BottomToolPop$OnClickToolListener onClickToolListener) {
        this.onClickToolListener = onClickToolListener;
    }
    
    public void setToolModels(final List<ToolBaseModel> list) {
        this.toolModels.clear();
        if (list != null) {
            this.toolModels.addAll((Collection)list);
        }
        this.setViewContent(1);
        this.toolAdapter.notifyDataSetChanged();
    }
    
    public void setViewContent(final int n) {
        if (n == 0) {
            this.flCommonTool.setVisibility(8);
            this.rlMoreColor.setVisibility(0);
        }
        else if (n == 1) {
            this.flCommonTool.setVisibility(0);
            this.rlMoreColor.setVisibility(8);
        }
    }
    
    public void show(final View view) {
        final int[] array = new int[2];
        view.getLocationInWindow(array);
        this.contentView.measure(View$MeasureSpec.makeMeasureSpec(1073741823, Integer.MIN_VALUE), View$MeasureSpec.makeMeasureSpec(1073741823, Integer.MIN_VALUE));
        this.showAtLocation(view, 0, array[0], array[1] - this.contentView.getMeasuredHeight());
        this.registerReceiver();
    }
}

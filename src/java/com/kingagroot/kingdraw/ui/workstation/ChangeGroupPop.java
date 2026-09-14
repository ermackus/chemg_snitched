package com.kingagroot.kingdraw.ui.workstation;

import com.kingagroot.kingdraw.interfaces.GroupListDBI;
import android.widget.AdapterView;
import android.view.View$OnClickListener;
import com.kingagroot.kingdraw.widget.DrawLeftCenterAppCompatButton;
import android.widget.AdapterView$OnItemClickListener;
import android.widget.ListAdapter;
import java.util.Collection;
import com.kingagroot.kingdraw.interfaces.impl.GroupListDBIMpl;
import android.widget.ListView;
import android.view.View;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ColorDrawable;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import java.util.ArrayList;
import com.kingagroot.kingdraw.model.GroupModel;
import java.util.List;
import android.content.Context;
import android.widget.PopupWindow;

public class ChangeGroupPop extends PopupWindow
{
    static final boolean $assertionsDisabled = false;
    private GroupAdapter adapter;
    private final int chooseGroupId;
    private final Context context;
    private final List<GroupModel> groupModels;
    private OnChooseItemClickListener onChooseItemClickListener;
    
    public ChangeGroupPop(final Context context, final int chooseGroupId) {
        super(context);
        this.groupModels = (List<GroupModel>)new ArrayList();
        this.context = context;
        this.setWidth(-1);
        this.setHeight(-2);
        final View inflate = ((LayoutInflater)context.getSystemService("layout_inflater")).inflate(2131493141, (ViewGroup)null);
        inflate.measure(0, 0);
        this.setContentView(inflate);
        this.setFocusable(true);
        this.setOutsideTouchable(true);
        this.update();
        this.setBackgroundDrawable((Drawable)new ColorDrawable(0));
        this.chooseGroupId = chooseGroupId;
        this.initView(inflate);
    }
    
    private void initView(final View view) {
        final ListView listView = (ListView)view.findViewById(2131297043);
        final List groupList = ((GroupListDBI)new GroupListDBIMpl()).getGroupList();
        if (groupList != null && groupList.size() > 0) {
            this.groupModels.clear();
            this.groupModels.addAll((Collection)groupList);
        }
        listView.setAdapter((ListAdapter)(this.adapter = new GroupAdapter(this.context, this.groupModels)));
        listView.addFooterView(LayoutInflater.from(this.context).inflate(2131493081, (ViewGroup)null));
        listView.setOnItemClickListener((AdapterView$OnItemClickListener)new _$$Lambda$ChangeGroupPop$3oqt2dMtjKTXqtHjvOegpWITHJU(this));
        ((DrawLeftCenterAppCompatButton)view.findViewById(2131296440)).setOnClickListener((View$OnClickListener)new _$$Lambda$ChangeGroupPop$gcAHMyejvaUfNTtbQcmu9rL6j1w(this));
        this.adapter.setChooseGroupId(this.chooseGroupId);
    }
    
    public void setOnChooseItemClickListener(final OnChooseItemClickListener onChooseItemClickListener) {
        this.onChooseItemClickListener = onChooseItemClickListener;
    }
    
    public void show(final View view) {
        this.showAtLocation(view, 48, 0, 0);
    }
    
    public interface OnChooseItemClickListener
    {
        void onAddGroupListener();
        
        void onChooseListener(final GroupModel p0);
    }
}

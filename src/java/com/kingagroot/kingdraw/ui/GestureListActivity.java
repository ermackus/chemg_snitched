package com.kingagroot.kingdraw.ui;

import java.util.Collection;
import android.view.MenuItem;
import android.os.Bundle;
import java.io.Serializable;
import android.content.Intent;
import android.widget.AdapterView;
import android.content.DialogInterface$OnClickListener;
import android.app.AlertDialog$Builder;
import com.goodsrc.library.utils.DisplayUtil;
import com.goodsrc.library.utils.SystemUtils;
import android.content.res.Configuration;
import android.content.DialogInterface;
import com.kingagroot.kingdraw.adapter.GestureAdapter$UnboundItemOnClickListener;
import android.widget.AdapterView$OnItemClickListener;
import android.widget.ListAdapter;
import android.content.Context;
import com.kingagroot.kingdraw.interfaces.impl.GestureDbiMpl;
import android.view.View;
import java.util.ArrayList;
import android.view.Menu;
import android.widget.GridView;
import com.kingagroot.kingdraw.model.GestureGroupModel;
import java.util.List;
import com.kingagroot.kingdraw.interfaces.GestureDbi;
import com.kingagroot.kingdraw.adapter.GestureAdapter;
import com.goodsrc.ui.library.ToolBarActivity;

public class GestureListActivity extends ToolBarActivity
{
    private GestureAdapter adapter;
    private GestureDbi gestureDbi;
    private final List<GestureGroupModel> gestureGroupModels;
    private GridView gvGesture;
    private Menu menuAdd;
    
    public GestureListActivity() {
        this.gestureGroupModels = (List<GestureGroupModel>)new ArrayList();
    }
    
    private void checkMenu() {
        if (this.menuAdd != null) {
            if (this.gestureGroupModels.size() < 13) {
                this.menuAdd.getItem(0).setEnabled(true);
                this.menuAdd.getItem(0).setIcon(2131231078);
            }
            else {
                this.menuAdd.getItem(0).setEnabled(false);
                this.menuAdd.getItem(0).setIcon(2131231080);
            }
        }
    }
    
    private void checkShowEmpty() {
        if (this.gestureGroupModels.size() > 0) {
            this.hidEmptyView();
        }
        else {
            this.showEmptyView((View)this.gvGesture);
            this.setEmptyView(2131821079);
        }
    }
    
    private void initView() {
        this.gvGesture = (GridView)this.findViewById(2131296774);
        this.gestureDbi = (GestureDbi)new GestureDbiMpl();
        final GestureAdapter gestureAdapter = new GestureAdapter((Context)this, (List)this.gestureGroupModels);
        this.adapter = gestureAdapter;
        this.gvGesture.setAdapter((ListAdapter)gestureAdapter);
        this.gvGesture.setOnItemClickListener((AdapterView$OnItemClickListener)new _$$Lambda$GestureListActivity$34CwVoEzFG5Mu8ImU5T_TQ58A_E(this));
        this.adapter.setUnboundItemClickListener((GestureAdapter$UnboundItemOnClickListener)new _$$Lambda$GestureListActivity$5n9778j4vA_c4w9T0mLQyEX7r1A(this));
    }
    
    private void onOrientationChanged(final Configuration configuration) {
        final int orientation = configuration.orientation;
        if (orientation == 2) {
            this.gvGesture.setNumColumns(SystemUtils.getScreenDefaultHeight((Context)this) / DisplayUtil.dip2px((Context)this, 180.0f));
        }
        else if (orientation == 1) {
            this.gvGesture.setNumColumns(SystemUtils.getScreenDefaultWidth((Context)this) / DisplayUtil.dip2px((Context)this, 180.0f));
        }
    }
    
    private void showDialog(final GestureGroupModel gestureGroupModel) {
        new AlertDialog$Builder((Context)this).setTitle(2131821524).setMessage(2131820900).setPositiveButton(2131820731, (DialogInterface$OnClickListener)new _$$Lambda$GestureListActivity$v6omQrnCVWJti908l_C9WKGHo4w(this, gestureGroupModel)).setNegativeButton(2131820661, (DialogInterface$OnClickListener)_$$Lambda$GestureListActivity$i7iYp6BLR00_iyKuWI084O50jj0.INSTANCE).show();
    }
    
    public void onConfigurationChanged(final Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.onOrientationChanged(configuration);
    }
    
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.setContentView(2131492910);
        this.setTitle(2131820901);
        this.initView();
        this.onOrientationChanged(this.getResources().getConfiguration());
    }
    
    public boolean onCreateOptionsMenu(final Menu menu) {
        final MenuItem add = menu.add(0, 0, 0, (CharSequence)this.getString(2131820585));
        add.setIcon(2131231078);
        add.setShowAsAction(2);
        super.onCreateOptionsMenu(menu);
        return true;
    }
    
    public boolean onOptionsItemSelected(final MenuItem menuItem) {
        if (menuItem.getItemId() == 0) {
            this.startActivity(new Intent((Context)this, (Class)GestureEditActivity.class));
        }
        return super.onOptionsItemSelected(menuItem);
    }
    
    public boolean onPrepareOptionsMenu(final Menu menuAdd) {
        this.menuAdd = menuAdd;
        this.checkMenu();
        return super.onPrepareOptionsMenu(menuAdd);
    }
    
    protected void onResume() {
        super.onResume();
        this.gestureGroupModels.clear();
        final List dataByKey = this.gestureDbi.getDataByKey(true);
        if (dataByKey != null) {
            this.gestureGroupModels.addAll((Collection)dataByKey);
        }
        this.checkShowEmpty();
        this.checkMenu();
        this.adapter.notifyDataSetChanged();
    }
}

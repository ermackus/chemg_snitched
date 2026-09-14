package com.kingagroot.kingdraw.floatwindow;

import com.kingagroot.kingdraw.interfaces.WorkWindowDbi;
import android.app.Activity;
import android.content.DialogInterface$OnDismissListener;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.BitmapDrawable;
import com.goodsrc.ui.library.ActivityStack;
import com.lzf.easyfloat.interfaces.OnTouchRangeListener;
import com.lzf.easyfloat.enums.ShowPattern;
import com.lzf.easyfloat.utils.DragUtils;
import android.view.MotionEvent;
import android.content.IntentFilter;
import android.content.Context;
import android.os.Bundle;
import com.kingagroot.kingdraw.ui.workstation.WebStationActivity;
import com.kingagroot.kingdraw.ui.workstation.TemplateActivity;
import android.os.Build$VERSION;
import com.kingagroot.kingdraw.ui.workstation.PediasActivity;
import com.goodsrc.library.utils.SPUtil;
import com.lzf.easyfloat.EasyFloat;
import android.content.DialogInterface;
import java.util.Iterator;
import java.util.List;
import com.kingagroot.kingdraw.ui.workstation.WorkStationModel;
import com.kingagroot.kingdraw.interfaces.impl.WorkWindowDbiMpl;
import com.kingagroot.kingdraw.base.MApplication;
import android.content.BroadcastReceiver;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.kingagroot.component.ui.ComponentBaseActivity;

public class FloatBaseActivity extends ComponentBaseActivity
{
    public static final String FLOAT_PEDIA = "FLOAT_PEDIA";
    public static final String FLOAT_TEMPLATE = "FLOAT_TEMPLATE";
    public static final String FLOAT_WEB_STATION = "FLOAT_WEB_STATION";
    private LocalBroadcastManager broadcastManager;
    private FloatViewDialog dialog;
    BroadcastReceiver receiver;
    
    public FloatBaseActivity() {
        this.receiver = (BroadcastReceiver)new FloatBaseActivity$1(this);
    }
    
    private void closeFloatWindowNeedLogin() {
        if (!MApplication.getInstance().isLogin()) {
            final WorkWindowDbiMpl workWindowDbiMpl = new WorkWindowDbiMpl();
            final List allWorkItems = ((WorkWindowDbi)workWindowDbiMpl).getAllWorkItems();
            if (allWorkItems != null && allWorkItems.size() > 0) {
                final Iterator iterator = allWorkItems.iterator();
                while (iterator.hasNext()) {
                    final WorkStationModel workStationModel = (WorkStationModel)iterator.next();
                    if (workStationModel.getNeedLogin() == 1) {
                        iterator.remove();
                        ((WorkWindowDbi)workWindowDbiMpl).deleteWorkItem(workStationModel);
                    }
                }
                if (allWorkItems.size() < 1) {
                    this.closeFloatWindow();
                }
            }
        }
    }
    
    public void closeFloatWindow() {
        EasyFloat.dismiss();
        SPUtil.setBooleanDefault("FLOAT_PEDIA", false);
        SPUtil.setBooleanDefault("FLOAT_TEMPLATE", false);
        SPUtil.setBooleanDefault("FLOAT_WEB_STATION", false);
        if (PediasActivity.getIntense() != null && Build$VERSION.SDK_INT >= 21) {
            PediasActivity.getIntense().finishAndRemoveTask();
        }
        if (TemplateActivity.getIntense() != null && Build$VERSION.SDK_INT >= 21) {
            TemplateActivity.getIntense().finishAndRemoveTask();
        }
        if (WebStationActivity.getIntense() != null && Build$VERSION.SDK_INT >= 21) {
            WebStationActivity.getIntense().closeActivity();
        }
        ((WorkWindowDbi)new WorkWindowDbiMpl()).cleanAllWorkItems();
    }
    
    @Override
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.broadcastManager = LocalBroadcastManager.getInstance((Context)this);
        final IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("login_data_changed");
        this.broadcastManager.registerReceiver(this.receiver, intentFilter);
    }
    
    protected void onDestroy() {
        super.onDestroy();
        final LocalBroadcastManager broadcastManager = this.broadcastManager;
        if (broadcastManager != null) {
            final BroadcastReceiver receiver = this.receiver;
            if (receiver != null) {
                broadcastManager.unregisterReceiver(receiver);
            }
        }
    }
    
    public void registerDragClose(final MotionEvent motionEvent) {
        DragUtils.INSTANCE.registerDragClose(motionEvent, (OnTouchRangeListener)new FloatBaseActivity$2(this), 2131493093, ShowPattern.FOREGROUND);
    }
    
    public void setDialogDismiss() {
        final FloatViewDialog dialog = this.dialog;
        if (dialog != null && dialog.isShowing()) {
            this.dialog.dismiss();
        }
    }
    
    public void showContractFloat() {
        final Activity topActivity = ActivityStack.getInstance().getTopActivity();
        if (topActivity != null) {
            final BitmapDrawable contentBackground = new BitmapDrawable(this.getResources(), takeScreenShot(topActivity));
            (this.dialog = new FloatViewDialog((Context)topActivity)).show();
            this.dialog.setContentBackground((Drawable)contentBackground);
            EasyFloat.hide();
            this.dialog.setOnDismissListener((DialogInterface$OnDismissListener)_$$Lambda$FloatBaseActivity$Nt5xZAhMYy_ju86XBLWeVGTno68.INSTANCE);
        }
    }
}

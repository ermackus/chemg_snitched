package com.kingagroot.kingdraw.widget.guide;

import android.widget.PopupWindow$OnDismissListener;
import android.app.Activity;
import com.kingagroot.kingdraw.core.view.KingDrawView;
import java.util.List;
import java.util.ArrayList;
import com.kingagroot.kingdraw.utils.PreferencesUtils;
import android.view.View;
import com.kingagroot.kingdraw.base.MApplication;
import android.content.SharedPreferences;
import android.content.Context;

public class GuideManager
{
    private static final String SP_KEY_GUIDE_3D = "sp_key_guide_3d";
    private static final String SP_KEY_GUIDE_GESUTRE = "sp_key_guide_gesutre";
    private static final String SP_KEY_GUIDE_GIF = "isFirst_2.3.0";
    private static final String SP_KEY_SEARCHPALETTE_HIS = "isFirst_SEARCH";
    private final Context context;
    private GuideChangeListner guideChangeListner;
    private GuideContentPopupWindow guideContentPopupWindow;
    private boolean isInit;
    private boolean isShowing;
    
    public GuideManager(final Context context) {
        this.context = context;
    }
    
    private void creatGuideContentView() {
        if (this.guideContentPopupWindow == null) {
            this.guideContentPopupWindow = new GuideContentPopupWindow(this.context);
        }
    }
    
    private static SharedPreferences getDefaultShare() {
        return MApplication.getInstance().getSharedPreferences("palettConfigXML", 0);
    }
    
    public void dismiss() {
        this.isShowing = false;
        final GuideChangeListner guideChangeListner = this.guideChangeListner;
        if (guideChangeListner != null) {
            guideChangeListner.onFinish();
        }
    }
    
    public boolean isInit() {
        return this.isInit;
    }
    
    public boolean isShowing() {
        return this.isShowing;
    }
    
    public void show3DGuide(final View view) {
        this.creatGuideContentView();
        if (PreferencesUtils.getData(getDefaultShare(), "sp_key_guide_3d", (Object)true)) {
            this.guideContentPopupWindow.addGuideView((GuideBaseView)new Guide3DView(this.context));
            PreferencesUtils.saveData(getDefaultShare(), "sp_key_guide_3d", (Object)false);
            this.guideContentPopupWindow.next();
            this.guideContentPopupWindow.showAtLocation(view, 17, 0, 0);
        }
        this.isInit = true;
    }
    
    public void showFormatBondsGuide(final View view) {
        this.creatGuideContentView();
        this.guideContentPopupWindow.addGuideView((GuideBaseView)new GuideFormatBondsView(this.context));
        this.guideContentPopupWindow.next();
        this.guideContentPopupWindow.showAtLocation(view, 17, 0, 0);
    }
    
    public void showFormatChainsGuide(final View view) {
        this.creatGuideContentView();
        this.guideContentPopupWindow.addGuideView((GuideBaseView)new GuideFormatChainsView(this.context));
        this.guideContentPopupWindow.next();
        this.guideContentPopupWindow.showAtLocation(view, 17, 0, 0);
    }
    
    public void showGestureChartGuide(final View view) {
        this.creatGuideContentView();
        final ArrayList guideViews = new ArrayList();
        ((List)guideViews).add((Object)new GuideGestureChartView(this.context));
        if (!((List)guideViews).isEmpty()) {
            this.guideContentPopupWindow.setGuideViews((List)guideViews);
            this.guideContentPopupWindow.next();
            this.guideContentPopupWindow.showAtLocation(view, 17, 0, 0);
        }
    }
    
    public void showPaletteGuide(final KingDrawView kingDrawView) {
        if (!((Activity)this.context).isFinishing()) {
            this.isShowing = true;
            this.creatGuideContentView();
            final ArrayList guideViews = new ArrayList();
            if (PreferencesUtils.getData(getDefaultShare(), "isFirst_2.3.0", (Object)true)) {
                ((List)guideViews).add((Object)new GuideGife(this.context));
                PreferencesUtils.saveData(getDefaultShare(), "isFirst_2.3.0", (Object)false);
            }
            if (!((List)guideViews).isEmpty()) {
                this.isShowing = true;
                this.guideContentPopupWindow.setGuideViews((List)guideViews);
                this.guideContentPopupWindow.next();
                this.guideContentPopupWindow.showAtLocation((View)kingDrawView, 17, 0, 0);
                this.guideContentPopupWindow.setOnDismissListener((PopupWindow$OnDismissListener)new PopupWindow$OnDismissListener(this) {
                    final GuideManager this$0;
                    
                    public void onDismiss() {
                        this.this$0.dismiss();
                    }
                });
            }
            else {
                this.dismiss();
            }
            this.isInit = true;
        }
    }
    
    public void showSearchPaletteGuide(final View view) {
        this.creatGuideContentView();
        if (PreferencesUtils.getData(getDefaultShare(), "isFirst_SEARCH", (Object)true)) {
            this.guideContentPopupWindow.addGuideView((GuideBaseView)new GuideSearchHis(this.context));
            PreferencesUtils.saveData(getDefaultShare(), "isFirst_SEARCH", (Object)false);
            this.guideContentPopupWindow.next();
            this.guideContentPopupWindow.showAtLocation(view, 17, 0, 0);
        }
        this.isInit = true;
    }
}

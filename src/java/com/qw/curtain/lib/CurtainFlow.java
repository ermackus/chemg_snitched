package com.qw.curtain.lib;

import com.qw.curtain.lib.debug.CurtainDebug;
import android.view.View;
import android.util.SparseArray;
import com.qw.curtain.lib.flow.CurtainFlowInterface;

public class CurtainFlow implements CurtainFlowInterface
{
    private SparseArray<Curtain> allCurtains;
    private CurtainFlow.CurtainFlow$CallBack callBack;
    private int currentCurtainId;
    private GuideDialogFragment guider;
    
    public CurtainFlow() {
        this.currentCurtainId = -1;
        this.allCurtains = (SparseArray<Curtain>)new SparseArray();
    }
    
    private void doWhenCurtainUpdated(final Curtain curtain, int key) {
        this.updateCurtainInfo(curtain);
        this.guider.updateContent();
        key = this.allCurtains.keyAt(key);
        this.currentCurtainId = key;
        final CurtainFlow.CurtainFlow$CallBack callBack = this.callBack;
        if (callBack != null) {
            callBack.onProcess(key, (CurtainFlowInterface)this);
        }
    }
    
    private Curtain getNodeInFlow(final SparseArray<Curtain> sparseArray, final int n) {
        try {
            return (Curtain)sparseArray.valueAt(n);
        }
        catch (final Exception ex) {
            return null;
        }
    }
    
    private void updateCurtainInfo(final Curtain curtain) {
        final Curtain$Param buildParams = curtain.buildParams;
        final GuideView guideView = new GuideView(buildParams.activity);
        guideView.setCurtainColor(buildParams.curtainColor);
        guideView.setHollowInfo(buildParams.hollows);
        this.guider.setGuideView(guideView);
        this.guider.setCancelable(buildParams.cancelBackPressed);
        this.guider.setTopViewRes(buildParams.topLayoutRes);
        this.guider.setParam(buildParams);
    }
    
    public void addCurtain(final int n, final Curtain curtain) {
        this.allCurtains.append(n, (Object)curtain);
    }
    
    public <T extends View> T findViewInCurrentCurtain(final int n) {
        final GuideDialogFragment guider = this.guider;
        if (guider != null) {
            return (T)guider.findViewByIdInTopView(n);
        }
        return null;
    }
    
    public void finish() {
        final GuideDialogFragment guider = this.guider;
        if (guider != null) {
            guider.dismissGuide();
        }
        final CurtainFlow.CurtainFlow$CallBack callBack = this.callBack;
        if (callBack != null) {
            callBack.onFinish();
        }
    }
    
    public void pop() {
        final int n = this.allCurtains.indexOfKey(this.currentCurtainId) - 1;
        if (n < 0) {
            return;
        }
        final Curtain nodeInFlow = this.getNodeInFlow(this.allCurtains, n);
        if (nodeInFlow != null) {
            this.doWhenCurtainUpdated(nodeInFlow, n);
        }
    }
    
    public void push() {
        final int n = this.allCurtains.indexOfKey(this.currentCurtainId) + 1;
        final Curtain nodeInFlow = this.getNodeInFlow(this.allCurtains, n);
        if (nodeInFlow != null) {
            this.doWhenCurtainUpdated(nodeInFlow, n);
        }
        else {
            this.finish();
        }
    }
    
    public void start() {
        this.start(null);
    }
    
    public void start(final CurtainFlow.CurtainFlow$CallBack callBack) {
        this.callBack = callBack;
        if (this.allCurtains.size() == 0) {
            return;
        }
        final Curtain curtain = (Curtain)this.allCurtains.valueAt(0);
        this.currentCurtainId = this.allCurtains.keyAt(0);
        if (curtain.buildParams.hollows.size() == 0) {
            CurtainDebug.w("Curtain", "with out any views");
            return;
        }
        final View targetView = ((HollowInfo)curtain.buildParams.hollows.valueAt(0)).targetView;
        if (targetView.getWidth() == 0) {
            targetView.post((Runnable)new CurtainFlow$1(this, callBack));
            return;
        }
        this.guider = new GuideDialogFragment();
        this.updateCurtainInfo(curtain);
        this.guider.show();
        if (callBack != null) {
            callBack.onProcess(this.currentCurtainId, (CurtainFlowInterface)this);
        }
    }
    
    public void toCurtainById(int indexOfKey) {
        indexOfKey = this.allCurtains.indexOfKey(indexOfKey);
        final Curtain curtain = (Curtain)this.allCurtains.valueAt(indexOfKey);
        if (curtain != null) {
            this.doWhenCurtainUpdated(curtain, indexOfKey);
        }
    }
}

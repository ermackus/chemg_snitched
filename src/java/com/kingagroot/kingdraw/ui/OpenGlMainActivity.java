package com.kingagroot.kingdraw.ui;

import com.goodsrc.ui.library.widget.notch.NotchCallBack;
import android.app.Activity;
import android.widget.ImageButton;
import android.os.Bundle;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff$Mode;
import androidx.core.content.ContextCompat;
import android.content.Context;
import com.kingagroot.kingdraw.core.view3d.DataElements;
import android.view.View;
import android.widget.RadioButton;
import com.goodsrc.ui.library.widget.notch.NotchView;
import com.goodsrc.ui.library.widget.notch.NotchContext;
import com.kingagroot.kingdraw.widget.guide.GuideManager;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.kingagroot.kingdraw.core.view3d.Chem3DView;
import android.view.View$OnClickListener;
import com.goodsrc.ui.library.BaseActivity;

public class OpenGlMainActivity extends BaseActivity implements View$OnClickListener
{
    private Chem3DView chem3Dview;
    private FloatingActionButton fabMore;
    protected GuideManager guideManager;
    private NotchContext notchContext;
    private NotchView notchView;
    private int openGlType;
    private MoreOperationPop pop;
    private RadioButton rbtModelClub;
    private RadioButton rbtModelSphere;
    private RadioButton rbtModelStick;
    private boolean showPop;
    
    public OpenGlMainActivity() {
        this.showPop = false;
    }
    
    public void onClick(final View view) {
        final RadioButton rbtModelSphere = this.rbtModelSphere;
        if (view == rbtModelSphere) {
            rbtModelSphere.setChecked(true);
            this.rbtModelStick.setChecked(false);
            this.rbtModelClub.setChecked(false);
            this.openGlType = 0;
        }
        else if (view == this.rbtModelStick) {
            rbtModelSphere.setChecked(false);
            this.rbtModelStick.setChecked(true);
            this.rbtModelClub.setChecked(false);
            this.openGlType = 1;
        }
        else if (view == this.rbtModelClub) {
            rbtModelSphere.setChecked(false);
            this.rbtModelStick.setChecked(false);
            this.rbtModelClub.setChecked(true);
            this.openGlType = 2;
        }
        this.chem3Dview.setModel(this.openGlType);
        this.onResume();
    }
    
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.requestWindowFeature(1);
        this.getWindow().setFlags(1024, 1024);
        this.setContentView(2131492920);
        this.chem3Dview = (Chem3DView)this.findViewById(2131296516);
        this.rbtModelSphere = (RadioButton)this.findViewById(2131297247);
        this.rbtModelStick = (RadioButton)this.findViewById(2131297248);
        this.rbtModelClub = (RadioButton)this.findViewById(2131297246);
        this.fabMore = (FloatingActionButton)this.findViewById(2131296665);
        ((ImageButton)this.findViewById(2131296416)).setOnClickListener((View$OnClickListener)new _$$Lambda$OpenGlMainActivity$0m1e__90nMeXcO3T7k151_jPXSI(this));
        this.rbtModelClub.setChecked(true);
        this.openGlType = 2;
        this.rbtModelSphere.setOnClickListener((View$OnClickListener)this);
        this.rbtModelStick.setOnClickListener((View$OnClickListener)this);
        this.rbtModelClub.setOnClickListener((View$OnClickListener)this);
        this.notchView = new NotchView(this.findViewById(2131296751));
        (this.notchContext = new NotchContext((Activity)this, (View)this.chem3Dview)).checkNotchInScreen((NotchCallBack)new _$$Lambda$OpenGlMainActivity$ZUrSsf5cPOvHTI1rqWpW0Vk15MI(this));
        this.fabMore.setOnClickListener((View$OnClickListener)new _$$Lambda$OpenGlMainActivity$yqbT_nFD3LTyxMdDhSJnaRbhrD0(this));
    }
    
    public void onPause() {
        super.onPause();
        this.chem3Dview.onPause();
    }
    
    public void onResume() {
        super.onResume();
        this.chem3Dview.onResume();
    }
    
    public void onWindowFocusChanged(final boolean b) {
        super.onWindowFocusChanged(b);
        if (this.guideManager == null) {
            this.guideManager = new GuideManager((Context)this);
        }
        if (!this.guideManager.isInit()) {
            this.guideManager.show3DGuide((View)this.chem3Dview);
        }
    }
}

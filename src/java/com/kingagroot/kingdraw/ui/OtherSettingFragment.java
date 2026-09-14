package com.kingagroot.kingdraw.ui;

import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.os.Bundle;
import android.content.DialogInterface;
import android.content.Intent;
import android.app.AlertDialog;
import android.content.DialogInterface$OnClickListener;
import android.app.AlertDialog$Builder;
import com.kingagroot.kingdraw.core.KingDrawConfig;
import android.widget.CompoundButton;
import android.view.View$OnClickListener;
import android.widget.CompoundButton$OnCheckedChangeListener;
import com.kingagroot.kingdraw.config.ShareData;
import android.view.View;
import androidx.appcompat.widget.SwitchCompat;
import android.widget.RelativeLayout;
import android.widget.RadioButton;
import android.content.Context;

public class OtherSettingFragment extends BaseFragment
{
    static final boolean $assertionsDisabled = false;
    private int carbonType;
    Context mContext;
    private OtherSettingFragment.OtherSettingFragment$OnConfigChangeListener onConfigChangeListener;
    private RadioButton rbtEnd;
    private RadioButton rbtHide;
    private RadioButton rbtShow;
    private RelativeLayout rlGesture;
    private SwitchCompat switchGuides;
    
    private void initView(final View view) {
        final SwitchCompat switchCompat = (SwitchCompat)view.findViewById(2131297440);
        final SwitchCompat switchCompat2 = (SwitchCompat)view.findViewById(2131297444);
        final SwitchCompat switchCompat3 = (SwitchCompat)view.findViewById(2131297441);
        this.switchGuides = (SwitchCompat)view.findViewById(2131297443);
        this.rbtShow = (RadioButton)view.findViewById(2131297251);
        this.rbtHide = (RadioButton)view.findViewById(2131297243);
        this.rbtEnd = (RadioButton)view.findViewById(2131297240);
        this.rlGesture = (RelativeLayout)view.findViewById(2131297292);
        switchCompat.setChecked(ShareData.getProofStatus());
        switchCompat2.setChecked(ShareData.getZoomerStatus());
        this.switchGuides.setChecked(ShareData.getGuidesState());
        switchCompat3.setChecked(this.getArguments().getBoolean("intent_key_color"));
        this.setCarbonChoose(this.carbonType = ShareData.getCarbonState());
        switchCompat.setOnCheckedChangeListener((CompoundButton$OnCheckedChangeListener)_$$Lambda$OtherSettingFragment$UEHTA9s4ev5Wcq2_gZm7ZMBs7ts.INSTANCE);
        switchCompat2.setOnCheckedChangeListener((CompoundButton$OnCheckedChangeListener)_$$Lambda$OtherSettingFragment$VAnM_MUncdEx_niRiVc7wKOsOPk.INSTANCE);
        this.switchGuides.setOnCheckedChangeListener((CompoundButton$OnCheckedChangeListener)_$$Lambda$OtherSettingFragment$lBBU_uPwqvATywMvd_4M_Tdaj50.INSTANCE);
        switchCompat3.setOnCheckedChangeListener((CompoundButton$OnCheckedChangeListener)new _$$Lambda$OtherSettingFragment$qfJgQX0zogQ5s9Xcch2F9chrab8(this));
        this.rbtShow.setOnClickListener((View$OnClickListener)new _$$Lambda$OtherSettingFragment$HyZP9WYsIGOjvU9A4SG0XJDRnsw(this));
        this.rbtHide.setOnClickListener((View$OnClickListener)new _$$Lambda$OtherSettingFragment$8D5BGkHl_Pu9I4pTJv_f1_HZj_I(this));
        this.rbtEnd.setOnClickListener((View$OnClickListener)new _$$Lambda$OtherSettingFragment$5ZYT6K1ovwtp3dybfZwKDnHLQJw(this));
        this.rlGesture.setOnClickListener((View$OnClickListener)new _$$Lambda$OtherSettingFragment$C9NVBnkjnepmixcXtuQU0kyfEE4(this));
    }
    
    private void setCarbonChoose(final int n) {
        if (n == 0) {
            this.rbtHide.setChecked(true);
        }
        else if (n == 1) {
            this.rbtShow.setChecked(true);
        }
        else if (n == 2) {
            this.rbtEnd.setChecked(true);
        }
    }
    
    private void showMultiBtnDialog(final int n) {
        final AlertDialog$Builder alertDialog$Builder = new AlertDialog$Builder(this.mContext);
        alertDialog$Builder.setTitle(2131821524).setMessage(2131821023).setPositiveButton(2131821022, (DialogInterface$OnClickListener)new _$$Lambda$OtherSettingFragment$uR2qtplcoWTpPR4gpEYpoW3zDXE(this, n)).setNeutralButton(2131820661, (DialogInterface$OnClickListener)new _$$Lambda$OtherSettingFragment$4S2thS_QNE45gb84ECZSD3ZiACA(this)).setNegativeButton(2131820778, (DialogInterface$OnClickListener)new _$$Lambda$OtherSettingFragment$EJwd_AaRpYvGwST55WAkZYwaJhc(this, n));
        final AlertDialog create = alertDialog$Builder.create();
        create.setCanceledOnTouchOutside(false);
        create.setCancelable(false);
        create.show();
    }
    
    public void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.mContext = (Context)this.getActivity();
    }
    
    public View onCreateView(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final Bundle bundle) {
        final ViewGroup viewGroup2 = (ViewGroup)layoutInflater.inflate(2131493058, viewGroup, false);
        this.initView((View)viewGroup2);
        return (View)viewGroup2;
    }
    
    public void setConfigChangeListener(final OtherSettingFragment.OtherSettingFragment$OnConfigChangeListener onConfigChangeListener) {
        this.onConfigChangeListener = onConfigChangeListener;
    }
}

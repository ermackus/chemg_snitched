package com.kingagroot.kingdraw.ui;

import android.content.IntentFilter;
import com.kingagroot.kingdraw.config.ShareData;
import android.view.LayoutInflater;
import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import com.kingagroot.kingdraw.utils.ShareFile;
import com.hjq.permissions.OnPermissionCallback;
import android.os.Build$VERSION;
import com.hjq.permissions.XXPermissions;
import java.util.Objects;
import com.kingagroot.kingdraw.dialog.LogInHintDialog;
import com.kingagroot.kingdraw.ui.synchornize.SynchronizeActivity;
import com.kingagroot.component.ui.utils.CheckDoubleClick;
import com.kingagroot.kingdraw.ui.user.UserInfoEditActivity;
import android.content.DialogInterface$OnClickListener;
import androidx.appcompat.app.AlertDialog$Builder;
import com.goodsrc.ui.library.widget.AppManager;
import com.kingagroot.kingdraw.ui.account.LoginMainActivity;
import com.goodsrc.library.utils.NetworkUtil;
import android.content.Intent;
import java.util.List;
import com.goodsrc.ui.library.BaseActivity;
import com.qw.curtain.lib.Curtain$CallBack;
import com.qw.curtain.lib.shape.Shape;
import com.qw.curtain.lib.shape.RoundShape;
import com.qw.curtain.lib.Padding;
import com.qw.curtain.lib.Curtain;
import android.graphics.Color;
import android.text.TextUtils;
import android.graphics.drawable.Drawable;
import androidx.core.content.ContextCompat;
import android.content.DialogInterface;
import com.kingagroot.kingdraw.ui.account.model.AccountUserModel;
import android.widget.ImageView;
import com.kingagroot.kingdraw.utils.ImageLoader;
import org.xutils.image.ImageOptions$Builder;
import com.kingagroot.kingdraw.base.MApplication;
import com.goodsrc.library.utils.ToastUtil;
import com.goodsrc.library.utils.AppUtil;
import android.view.ViewGroup$LayoutParams;
import com.goodsrc.library.utils.ScreenUtils;
import android.widget.TextView;
import android.content.BroadcastReceiver;
import android.view.ViewGroup;
import android.view.View;
import android.content.Context;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.goodsrc.ui.library.widget.RoundAndCircleImageView;
import android.widget.ImageButton;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import android.view.View$OnClickListener;

public class MeFragment extends BaseFragment implements View$OnClickListener
{
    private LocalBroadcastManager broadcastManager;
    private ImageButton ibtSetting;
    private RoundAndCircleImageView ivUserLogin;
    private RelativeLayout llNoLogin;
    private LinearLayout llUserInfo;
    private LinearLayout llUserTop;
    Context mContext;
    protected View mStatusBarView;
    private ViewGroup mView;
    private String picHead;
    BroadcastReceiver receiver;
    private RelativeLayout rlAbout;
    private RelativeLayout rlHelp;
    private RelativeLayout rlLogin;
    private RelativeLayout rlOpinion;
    private RelativeLayout rlScan;
    private RelativeLayout rlScore;
    private RelativeLayout rlShare;
    private RelativeLayout rlSynList;
    private RelativeLayout rlVipCenter;
    private TextView tvUserPhone;
    private TextView tvUserType;
    private TextView tvVipCenter;
    private String vipDueTime;
    
    public MeFragment() {
        this.receiver = (BroadcastReceiver)new MeFragment$1(this);
    }
    
    private void addStatusBar() {
        if (this.mStatusBarView == null) {
            (this.mStatusBarView = new View(this.getContext())).setLayoutParams(new ViewGroup$LayoutParams(this.getResources().getDisplayMetrics().widthPixels, ScreenUtils.getStatusBarHeight((Context)this.requireActivity())));
            this.mStatusBarView.requestLayout();
            final ViewGroup mView = this.mView;
            if (mView != null) {
                mView.addView(this.mStatusBarView, 0);
            }
        }
    }
    
    private void gotoRate() {
        if (!AppUtil.openAppMarket(this.mContext)) {
            ToastUtil.showShort((CharSequence)this.getString(2131821090));
        }
    }
    
    private void initData() {
        if (MApplication.getInstance().isLogin()) {
            final AccountUserModel accountUserModel = MApplication.getInstance().getAccountUserModel();
            if (accountUserModel != null) {
                this.rlLogin.setVisibility(0);
                this.llNoLogin.setVisibility(8);
                this.tvUserPhone.setText((CharSequence)accountUserModel.getNickName());
                this.picHead = accountUserModel.getHeadImgs();
                ImageLoader.bind((ImageView)this.ivUserLogin, this.picHead, new ImageOptions$Builder().setFailureDrawableId(2131231517).build());
                this.rlVipCenter.setVisibility(0);
                this.vipDueTime = accountUserModel.getVipExpiryTime();
                this.setUserAreaType(accountUserModel);
            }
            else {
                this.rlLogin.setVisibility(8);
                this.llNoLogin.setVisibility(0);
                this.rlVipCenter.setVisibility(8);
                this.llUserTop.setBackgroundResource(2131099966);
                this.rlScan.setVisibility(8);
            }
        }
        else {
            this.rlLogin.setVisibility(8);
            this.llNoLogin.setVisibility(0);
            this.rlVipCenter.setVisibility(8);
            this.llUserTop.setBackgroundResource(2131099966);
            this.rlScan.setVisibility(8);
        }
    }
    
    private void initView(final View view) {
        this.ibtSetting = (ImageButton)view.findViewById(2131296826);
        this.llNoLogin = (RelativeLayout)view.findViewById(2131297011);
        this.rlLogin = (RelativeLayout)view.findViewById(2131297297);
        this.llUserInfo = (LinearLayout)view.findViewById(2131297038);
        this.tvUserPhone = (TextView)view.findViewById(2131297683);
        this.rlSynList = (RelativeLayout)view.findViewById(2131297322);
        this.rlScan = (RelativeLayout)view.findViewById(2131297308);
        this.rlShare = (RelativeLayout)view.findViewById(2131297320);
        this.rlOpinion = (RelativeLayout)view.findViewById(2131297305);
        this.rlHelp = (RelativeLayout)view.findViewById(2131297293);
        this.rlAbout = (RelativeLayout)view.findViewById(2131297282);
        final TextView textView = (TextView)view.findViewById(2131297687);
        this.rlScore = (RelativeLayout)view.findViewById(2131297309);
        this.ivUserLogin = (RoundAndCircleImageView)view.findViewById(2131296941);
        this.tvUserType = (TextView)view.findViewById(2131297685);
        this.llUserTop = (LinearLayout)view.findViewById(2131297039);
        this.rlVipCenter = (RelativeLayout)view.findViewById(2131297327);
        this.tvVipCenter = (TextView)view.findViewById(2131297692);
        this.llNoLogin.setOnClickListener((View$OnClickListener)this);
        this.llUserInfo.setOnClickListener((View$OnClickListener)this);
        this.ibtSetting.setOnClickListener((View$OnClickListener)this);
        this.rlSynList.setOnClickListener((View$OnClickListener)this);
        this.rlScan.setOnClickListener((View$OnClickListener)this);
        this.rlShare.setOnClickListener((View$OnClickListener)this);
        this.rlOpinion.setOnClickListener((View$OnClickListener)this);
        this.rlHelp.setOnClickListener((View$OnClickListener)this);
        this.rlAbout.setOnClickListener((View$OnClickListener)this);
        this.rlScore.setOnClickListener((View$OnClickListener)this);
        this.ivUserLogin.setOnClickListener((View$OnClickListener)this);
        this.tvVipCenter.setOnClickListener((View$OnClickListener)this);
        textView.setText((CharSequence)String.format("v%s", new Object[] { "3.6.1" }));
    }
    
    private void setUserAreaType(final AccountUserModel accountUserModel) {
        final String countryArea = accountUserModel.getCountryArea();
        if (!"CN".equals((Object)countryArea) && !"MO".equals((Object)countryArea) && !"HK".equals((Object)countryArea) && !"TW".equals((Object)countryArea)) {
            this.rlVipCenter.setVisibility(0);
            this.rlScan.setVisibility(0);
            this.tvUserType.setVisibility(0);
            final Drawable drawable = ContextCompat.getDrawable((Context)this.requireActivity(), 2131231330);
            if (drawable != null) {
                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            }
            this.tvUserPhone.setCompoundDrawables((Drawable)null, (Drawable)null, drawable, (Drawable)null);
            this.setUserVipInfo(accountUserModel.getVipState());
        }
        else {
            this.rlVipCenter.setVisibility(8);
            this.rlScan.setVisibility(8);
            this.tvUserType.setVisibility(8);
            this.tvUserPhone.setCompoundDrawables((Drawable)null, (Drawable)null, (Drawable)null, (Drawable)null);
            this.llUserTop.setBackgroundResource(2131099966);
        }
    }
    
    private void setUserVipInfo(final int n) {
        if (n == 1) {
            this.llUserTop.setBackgroundResource(2131230890);
            this.rlVipCenter.setBackgroundResource(2131231522);
            this.tvVipCenter.setBackgroundResource(2131230900);
            this.tvVipCenter.setTextColor(ContextCompat.getColor((Context)this.requireActivity(), 2131099706));
            this.tvVipCenter.setText(2131821513);
            final Drawable drawable = ContextCompat.getDrawable((Context)this.requireActivity(), 2131231329);
            if (drawable != null) {
                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            }
            this.tvUserPhone.setCompoundDrawables((Drawable)null, (Drawable)null, drawable, (Drawable)null);
            if (!TextUtils.isEmpty((CharSequence)this.vipDueTime)) {
                this.tvUserType.setText((CharSequence)String.format(this.getString(2131821514), new Object[] { this.vipDueTime }));
            }
            this.tvUserType.setTextColor(Color.parseColor("#F27270"));
        }
        else if (n == 0) {
            this.llUserTop.setBackgroundResource(2131099966);
            this.rlVipCenter.setBackgroundResource(2131231171);
            this.tvVipCenter.setBackgroundResource(2131230933);
            this.tvVipCenter.setTextColor(ContextCompat.getColor((Context)this.requireActivity(), 2131099705));
            this.tvVipCenter.setText(2131821121);
            this.tvUserPhone.setCompoundDrawables((Drawable)null, (Drawable)null, (Drawable)null, (Drawable)null);
            this.tvUserType.setText(2131821502);
            this.tvUserType.setTextColor(Color.parseColor("#777777"));
        }
        else if (n == 2) {
            this.llUserTop.setBackgroundResource(2131099966);
            this.rlVipCenter.setBackgroundResource(2131231524);
            this.tvVipCenter.setBackgroundResource(2131230933);
            this.tvVipCenter.setTextColor(ContextCompat.getColor((Context)this.requireActivity(), 2131099705));
            this.tvVipCenter.setText(2131821315);
            final Drawable drawable2 = ContextCompat.getDrawable((Context)this.requireActivity(), 2131231330);
            if (drawable2 != null) {
                drawable2.setBounds(0, 0, drawable2.getMinimumWidth(), drawable2.getMinimumHeight());
            }
            this.tvUserPhone.setCompoundDrawables((Drawable)null, (Drawable)null, drawable2, (Drawable)null);
            this.tvUserType.setText(2131821502);
            this.tvUserType.setTextColor(Color.parseColor("#777777"));
        }
    }
    
    private void showInitGuide() {
        new Curtain(this.requireActivity()).with((View)this.ibtSetting).withPadding((View)this.ibtSetting, Padding.all(24)).withShape((View)this.ibtSetting, (Shape)new RoundShape(12.0f)).setTopView(2131493229).setCallBack((Curtain$CallBack)new MeFragment$2(this)).show();
    }
    
    public void onClick(final View view) {
        if (view == this.llNoLogin) {
            if (NetworkUtil.isNetworkConnected((Context)this.getActivity())) {
                this.startActivity(new Intent(this.mContext, (Class)LoginMainActivity.class));
            }
            else {
                final AlertDialog$Builder alertDialog$Builder = new AlertDialog$Builder((Context)AppManager.getInstance().getLastActivity());
                alertDialog$Builder.setTitle(2131821524).setMessage((CharSequence)this.getString(2131821066)).setPositiveButton(2131820731, (DialogInterface$OnClickListener)_$$Lambda$MeFragment$hduYX6qQ_pVL3tN1J_zZquPOATI.INSTANCE);
                final AlertDialog create = alertDialog$Builder.create();
                create.setCanceledOnTouchOutside(false);
                create.setCancelable(false);
                create.show();
                create.getButton(-1).setTextColor(Color.parseColor("#e13e3f"));
            }
        }
        else if (view == this.llUserInfo) {
            final Intent intent = new Intent(this.mContext, (Class)UserInfoEditActivity.class);
            intent.putExtra("REG", "CHANGE");
            this.startActivity(intent);
        }
        else if (view == this.ibtSetting) {
            this.startActivity(new Intent(this.mContext, (Class)AppSettingActivity.class));
        }
        else if (view == this.rlSynList) {
            if (CheckDoubleClick.isFastDoubleClick()) {
                return;
            }
            if (MApplication.getInstance().isLogin()) {
                this.startActivity(new Intent(this.mContext, (Class)SynchronizeActivity.class));
            }
            else {
                new LogInHintDialog(this.mContext).show();
            }
        }
        else if (view == this.rlScan) {
            if (CheckDoubleClick.isFastDoubleClick()) {
                return;
            }
            if (MApplication.getInstance().isLogin()) {
                final BaseActivity baseActivity = (BaseActivity)this.getActivity();
                final XXPermissions with = XXPermissions.with((Context)Objects.requireNonNull((Object)baseActivity));
                if (Build$VERSION.SDK_INT >= 33) {
                    with.permission(new String[] { "android.permission.READ_MEDIA_IMAGES" });
                }
                else {
                    with.permission(new String[] { "android.permission.WRITE_EXTERNAL_STORAGE" });
                }
                with.request((OnPermissionCallback)new _$$Lambda$MeFragment$sWg3A_crjMCyGFOKoM__rIHjqYs(this, baseActivity));
            }
            else {
                new LogInHintDialog(this.mContext).show();
            }
        }
        else if (view == this.rlShare) {
            if (CheckDoubleClick.isFastDoubleClick()) {
                return;
            }
            new ShareFile(this.mContext).shareApp();
        }
        else if (view == this.rlOpinion) {
            this.startActivity(new Intent(this.mContext, (Class)FeedBackListActivity.class));
        }
        else if (view == this.rlHelp) {
            this.startActivity(new Intent(this.mContext, (Class)HelpActivity.class));
        }
        else if (view == this.rlAbout) {
            this.startActivity(new Intent(this.mContext, (Class)AboutActivity.class));
        }
        else if (view == this.rlScore) {
            this.gotoRate();
        }
        else if (view == this.ivUserLogin) {
            final Intent intent2 = new Intent((Context)this.getActivity(), (Class)ImageDetailActivity.class);
            intent2.putExtra("intent_path_url", this.picHead);
            intent2.putExtra("PATH_URL_TYPE", 0);
            this.startActivity(intent2);
        }
        else if (view == this.tvVipCenter) {
            this.startActivity(new Intent((Context)this.getActivity(), (Class)VipPayActivity.class));
        }
    }
    
    public void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.mContext = (Context)this.getActivity();
        this.addStatusBar();
    }
    
    public View onCreateView(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final Bundle bundle) {
        this.initView((View)(this.mView = (ViewGroup)layoutInflater.inflate(2131493055, viewGroup, false)));
        if (ShareData.getMeGuide()) {
            this.showInitGuide();
        }
        this.broadcastManager = LocalBroadcastManager.getInstance((Context)this.requireActivity());
        final IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("login_data_changed");
        intentFilter.addAction("USER_VIP_STATE_CHANGE");
        this.broadcastManager.registerReceiver(this.receiver, intentFilter);
        return (View)this.mView;
    }
    
    public void onDestroy() {
        super.onDestroy();
        final LocalBroadcastManager broadcastManager = this.broadcastManager;
        if (broadcastManager != null) {
            final BroadcastReceiver receiver = this.receiver;
            if (receiver != null) {
                broadcastManager.unregisterReceiver(receiver);
            }
        }
    }
    
    public void onResume() {
        super.onResume();
        this.initData();
    }
}

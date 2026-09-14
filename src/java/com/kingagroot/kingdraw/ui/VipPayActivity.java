package com.kingagroot.kingdraw.ui;

import androidx.recyclerview.widget.LinearLayoutManager;
import android.app.Activity;
import com.kingagroot.kingdraw.utils.GoogleCheckUtil;
import android.os.Bundle;
import com.kingagroot.kingdraw.config.NetConfig$BaseData;
import com.goodsrc.library.utils.LanguageTool;
import com.goodsrc.library.core.LibraryApplication;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import org.xutils.http.RequestParams;
import org.json.JSONException;
import org.json.JSONObject;
import android.graphics.Color;
import android.text.TextUtils;
import android.graphics.drawable.Drawable;
import androidx.core.content.ContextCompat;
import com.kingagroot.kingdraw.ui.account.model.AccountUserModel;
import android.widget.ImageView;
import com.kingagroot.kingdraw.utils.ImageLoader;
import org.xutils.image.ImageOptions;
import com.kingagroot.kingdraw.base.MApplication;
import java.util.Collection;
import android.widget.AdapterView$OnItemClickListener;
import com.kingagroot.kingdraw.ui.pay.VipPayPlanAdapter$OnItemClickListener;
import androidx.recyclerview.widget.RecyclerView$Adapter;
import androidx.recyclerview.widget.RecyclerView$LayoutManager;
import android.widget.ListAdapter;
import android.content.Context;
import android.text.method.LinkMovementMethod;
import android.text.SpannableString;
import com.goodsrc.ui.library.BaseActivity;
import com.kingagroot.kingdraw.http.NewHttpManager;
import com.goodsrc.library.http.RequestCallBack;
import com.kingagroot.kingdraw.config.NetConfig$UserOrder;
import org.xutils.http.HttpMethod;
import com.kingagroot.kingdraw.http.NewHttpManager$Builder;
import com.kingagroot.kingdraw.model.PayInfoModel;
import android.os.Looper;
import java.util.ArrayList;
import com.kingagroot.kingdraw.ui.pay.VipPayWayAdapter;
import com.kingagroot.kingdraw.ui.pay.VipPayPlanAdapter;
import com.kingagroot.kingdraw.ui.pay.VipCenterAdapter;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.kingagroot.component.ui.widget.LoadingDialog;
import com.kingagroot.kingdraw.model.PayInfoModel$PayType;
import com.kingagroot.kingdraw.model.PayInfoModel$PayPackage;
import com.kingagroot.kingdraw.model.PayInfoModel$PayPackageImages;
import java.util.List;
import com.goodsrc.ui.library.widget.RoundAndCircleImageView;
import android.os.Handler;
import android.widget.GridView;
import android.widget.Button;
import android.view.View$OnClickListener;
import com.goodsrc.ui.library.ToolBarActivity;

public class VipPayActivity extends ToolBarActivity implements View$OnClickListener
{
    public static final String TAG = "VipPayActivity";
    private Button btnPay;
    private GridView gvPayWay;
    private GridView gvVipCenter;
    Handler handler;
    private RoundAndCircleImageView ivUserPic;
    private int orderPayType;
    private int orderStoreId;
    private final List<PayInfoModel$PayPackageImages> payPackageImagesList;
    private final List<PayInfoModel$PayPackage> payPackageList;
    private final List<PayInfoModel$PayType> payTypeList;
    private int payWayPosition;
    private int planPosition;
    private int productType;
    private LoadingDialog progressDialog;
    private RecyclerView rvVipPlan;
    private TextView tvMoney;
    private TextView tvMoneyInfo;
    private TextView tvMoneyType;
    private TextView tvUserName;
    private TextView tvUserType;
    private TextView tvVipAgreement;
    private TextView tvVipCheckAll;
    private VipCenterAdapter vipCenterAdapter;
    private String vipDueTime;
    private VipPayPlanAdapter vipPayPlanAdapter;
    private VipPayWayAdapter vipPayWayAdapter;
    
    public VipPayActivity() {
        this.payPackageImagesList = (List<PayInfoModel$PayPackageImages>)new ArrayList();
        this.payPackageList = (List<PayInfoModel$PayPackage>)new ArrayList();
        this.payTypeList = (List<PayInfoModel$PayType>)new ArrayList();
        this.handler = new Handler(Looper.getMainLooper());
        this.planPosition = 0;
        this.payWayPosition = 0;
    }
    
    private void getPayInfo() {
        final NewHttpManager$Builder newHttpManager$Builder = new NewHttpManager$Builder();
        newHttpManager$Builder.setHttpMethod(HttpMethod.GET);
        final NewHttpManager build = newHttpManager$Builder.build();
        build.request(build.params(NetConfig$UserOrder.getPayInfo()), (RequestCallBack)new VipPayActivity$4(this));
    }
    
    private void init() {
        this.ivUserPic = (RoundAndCircleImageView)this.findViewById(2131296943);
        this.tvUserName = (TextView)this.findViewById(2131297681);
        this.tvUserType = (TextView)this.findViewById(2131297685);
        this.tvVipCheckAll = (TextView)this.findViewById(2131297694);
        this.gvVipCenter = (GridView)this.findViewById(2131296778);
        this.btnPay = (Button)this.findViewById(2131296441);
        this.tvMoneyType = (TextView)this.findViewById(2131297621);
        this.tvMoney = (TextView)this.findViewById(2131297619);
        this.tvMoneyInfo = (TextView)this.findViewById(2131297620);
        this.rvVipPlan = (RecyclerView)this.findViewById(2131297336);
        this.tvVipAgreement = (TextView)this.findViewById(2131297691);
        this.gvPayWay = (GridView)this.findViewById(2131296775);
        (this.progressDialog = new LoadingDialog((BaseActivity)this)).setTextMessage(this.getString(2131821517));
        this.tvVipCheckAll.setOnClickListener((View$OnClickListener)this);
        this.btnPay.setOnClickListener((View$OnClickListener)this);
    }
    
    private void initAgreement() {
        final String string = this.getString(2131821521);
        final String string2 = this.getString(2131821520);
        final SpannableString spannableString = new SpannableString((CharSequence)string);
        spannableString.setSpan((Object)new VipPayActivity$1(this), 0, spannableString.length(), 33);
        this.tvVipAgreement.append((CharSequence)" ");
        this.tvVipAgreement.append((CharSequence)spannableString);
        this.tvVipAgreement.append((CharSequence)" ");
        this.tvVipAgreement.append((CharSequence)this.getString(2131821512));
        this.tvVipAgreement.append((CharSequence)" ");
        final SpannableString spannableString2 = new SpannableString((CharSequence)string2);
        spannableString2.setSpan((Object)new VipPayActivity$2(this), 0, spannableString2.length(), 33);
        this.tvVipAgreement.setMovementMethod(LinkMovementMethod.getInstance());
        this.tvVipAgreement.setHighlightColor(0);
        this.tvVipAgreement.append((CharSequence)spannableString2);
    }
    
    private void setAdapterData() {
        this.vipCenterAdapter = new VipCenterAdapter((Context)this, (List)this.payPackageImagesList);
        this.vipPayPlanAdapter = new VipPayPlanAdapter((Context)this, (List)this.payPackageList);
        this.vipPayWayAdapter = new VipPayWayAdapter((Context)this, (List)this.payTypeList);
        this.gvVipCenter.setAdapter((ListAdapter)this.vipCenterAdapter);
        final VipPayActivity$3 layoutManager = new VipPayActivity$3(this, (Context)this);
        this.rvVipPlan.setHasFixedSize(true);
        this.rvVipPlan.setNestedScrollingEnabled(false);
        ((LinearLayoutManager)layoutManager).setOrientation(1);
        this.rvVipPlan.setLayoutManager((RecyclerView$LayoutManager)layoutManager);
        this.rvVipPlan.setAdapter((RecyclerView$Adapter)this.vipPayPlanAdapter);
        this.gvPayWay.setAdapter((ListAdapter)this.vipPayWayAdapter);
        this.vipPayPlanAdapter.setItemChecked(this.planPosition);
        this.vipPayPlanAdapter.setOnItemClickListener((VipPayPlanAdapter$OnItemClickListener)new _$$Lambda$VipPayActivity$1BIW6FC9aV_Ur1ZclcivXQWo99k(this));
        this.vipPayWayAdapter.setSelection(this.payWayPosition);
        this.gvPayWay.setOnItemClickListener((AdapterView$OnItemClickListener)new _$$Lambda$VipPayActivity$9LSO9NbqqaZpvRyfo6YRBaAwWqE(this));
    }
    
    private void setPayInfo(final int n) {
        final PayInfoModel$PayPackage itemData = this.vipPayPlanAdapter.getItemData(n);
        this.tvMoneyType.setText((CharSequence)itemData.getSymbol());
        this.tvMoney.setText((CharSequence)String.valueOf(itemData.getMoney()));
        this.tvMoneyInfo.setText((CharSequence)itemData.getTitleName());
        this.orderStoreId = itemData.getStoreId();
        this.productType = itemData.getPackageType();
    }
    
    private void setPayInfo(final PayInfoModel payInfoModel) {
        this.payPackageImagesList.clear();
        this.payPackageList.clear();
        this.payTypeList.clear();
        this.payPackageImagesList.addAll((Collection)payInfoModel.getPayPackageImages());
        this.payPackageList.addAll((Collection)payInfoModel.getPayPackage());
        this.payTypeList.addAll((Collection)payInfoModel.getPayType());
        this.vipCenterAdapter.notifyDataSetChanged();
        this.vipPayPlanAdapter.notifyDataSetChanged();
        this.vipPayWayAdapter.notifyDataSetChanged();
        this.setPayInfo(this.planPosition);
        this.setPayType(this.payWayPosition);
    }
    
    private void setUserInfo() {
        final AccountUserModel accountUserModel = MApplication.getInstance().getAccountUserModel();
        ImageLoader.bind((ImageView)this.ivUserPic, accountUserModel.getHeadImgs(), new ImageOptions.Builder().setFailureDrawableId(2131231517).build());
        this.tvUserName.setText((CharSequence)accountUserModel.getNickName());
        this.vipDueTime = accountUserModel.getVipExpiryTime();
        this.setUserVipInfo(accountUserModel.getVipState());
    }
    
    private void setUserVipInfo(final int n) {
        if (n == 1) {
            final Drawable drawable = ContextCompat.getDrawable((Context)this, 2131231329);
            if (drawable != null) {
                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            }
            this.tvUserName.setCompoundDrawables((Drawable)null, (Drawable)null, drawable, (Drawable)null);
            if (!TextUtils.isEmpty((CharSequence)this.vipDueTime)) {
                this.tvUserType.setText((CharSequence)String.format(this.getString(2131821514), new Object[] { this.vipDueTime }));
            }
            this.tvUserType.setTextColor(Color.parseColor("#F7DBC2"));
        }
        else if (n == 0) {
            this.tvUserName.setCompoundDrawables((Drawable)null, (Drawable)null, (Drawable)null, (Drawable)null);
            this.tvUserType.setText(2131821502);
            this.tvUserType.setTextColor(Color.parseColor("#AAAAAA"));
        }
        else if (n == 2) {
            final Drawable drawable2 = ContextCompat.getDrawable((Context)this, 2131231330);
            if (drawable2 != null) {
                drawable2.setBounds(0, 0, drawable2.getMinimumWidth(), drawable2.getMinimumHeight());
            }
            this.tvUserName.setCompoundDrawables((Drawable)null, (Drawable)null, drawable2, (Drawable)null);
            this.tvUserType.setText(2131821502);
            this.tvUserType.setTextColor(Color.parseColor("#AAAAAA"));
        }
    }
    
    public void createOrder(final int n, final int n2, final int n3) {
        final NewHttpManager build = new NewHttpManager$Builder().build();
        final RequestParams params = build.params(NetConfig$UserOrder.createVipOrder());
        final JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("orderType", n);
            jsonObject.put("payType", n2);
            jsonObject.put("storeId", n3);
        }
        catch (final JSONException ex) {
            ex.printStackTrace();
        }
        params.addBodyParameter("", jsonObject.toString());
        build.request(params, (RequestCallBack)new VipPayActivity$5(this, n2));
    }
    
    public void onClick(final View view) {
        if (view == this.tvVipCheckAll) {
            final Intent intent = new Intent((Context)this, (Class)WebViewActivity.class);
            intent.putExtra("title_key", this.getString(2131821513));
            if (LibraryApplication.getLanguage().equals((Object)LanguageTool.SER_ZH)) {
                intent.putExtra("url_key", NetConfig$BaseData.vipCenterCn());
            }
            else {
                intent.putExtra("url_key", NetConfig$BaseData.vipCenterEn());
            }
            this.startActivity(intent);
        }
        else if (view == this.btnPay) {
            this.createOrder(0, this.orderPayType, this.orderStoreId);
        }
    }
    
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.setContentView(2131492938);
        this.init();
        this.initAgreement();
        this.setUserInfo();
        this.setAdapterData();
        this.getPayInfo();
    }
    
    protected void onResume() {
        super.onResume();
        GoogleCheckUtil.onCheckGooglePlayServices((Activity)this);
    }
    
    public void orderCheck(final int n, final String s, final int n2) {
        this.handler.post((Runnable)new VipPayActivity.VipPayActivity$MyThread(this));
        final NewHttpManager build = new NewHttpManager$Builder().build();
        final RequestParams params = build.params(NetConfig$UserOrder.orderCheck());
        final JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("storeId", n);
            jsonObject.put("token", (Object)s);
            jsonObject.put("orderId", n2);
        }
        catch (final JSONException ex) {
            ex.printStackTrace();
        }
        params.addBodyParameter("", jsonObject.toString());
        build.request(params, (RequestCallBack)new VipPayActivity$6(this));
    }
    
    public void setPayType(final int n) {
        this.orderPayType = this.vipPayWayAdapter.getItem(n).getId();
    }
}

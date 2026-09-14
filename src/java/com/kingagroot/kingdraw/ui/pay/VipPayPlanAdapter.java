package com.kingagroot.kingdraw.ui.pay;

import android.widget.TextView;
import android.widget.LinearLayout;
import android.view.ViewGroup;
import android.graphics.Color;
import android.view.View$OnClickListener;
import androidx.recyclerview.widget.RecyclerView$ViewHolder;
import android.view.View;
import android.content.Context;
import com.kingagroot.kingdraw.model.PayInfoModel$PayPackage;
import java.util.List;
import android.view.LayoutInflater;
import android.util.SparseBooleanArray;
import androidx.recyclerview.widget.RecyclerView$Adapter;

public class VipPayPlanAdapter extends RecyclerView$Adapter<ViewHolder>
{
    public static final String TAG = "VipPayPlanAdapter";
    private final SparseBooleanArray mBooleanArray;
    private final LayoutInflater mInflater;
    private int mLastCheckedPosition;
    private VipPayPlanAdapter.VipPayPlanAdapter$OnItemClickListener onItemClickListener;
    private final List<PayInfoModel$PayPackage> packages;
    
    public VipPayPlanAdapter(final Context context, final List<PayInfoModel$PayPackage> packages) {
        this.mLastCheckedPosition = -1;
        this.mInflater = LayoutInflater.from(context);
        this.packages = packages;
        this.mBooleanArray = new SparseBooleanArray(this.packages.size());
    }
    
    public int getItemCount() {
        return this.packages.size();
    }
    
    public PayInfoModel$PayPackage getItemData(final int n) {
        return (PayInfoModel$PayPackage)this.packages.get(n);
    }
    
    public void onBindViewHolder(final ViewHolder viewHolder, final int n) {
        final PayInfoModel$PayPackage payInfoModel$PayPackage = (PayInfoModel$PayPackage)this.packages.get(n);
        viewHolder.tvVipPlanName.setText((CharSequence)payInfoModel$PayPackage.getTitleName());
        viewHolder.tvVipPlanMoneyType.setText((CharSequence)payInfoModel$PayPackage.getSymbol());
        viewHolder.tvVipPlanMoney.setText((CharSequence)String.valueOf(payInfoModel$PayPackage.getMoney()));
        viewHolder.tvVipPlanInfo.setText((CharSequence)String.valueOf((Object)payInfoModel$PayPackage.getPackageDesc()));
        viewHolder.tvVipPlanRemark.setText((CharSequence)payInfoModel$PayPackage.getRemarks());
        viewHolder.llItemPlan.setOnClickListener((View$OnClickListener)new _$$Lambda$VipPayPlanAdapter$Z4aAp7WHzvzPA6CjUhB6duE7rNI(this, n));
        if (!this.mBooleanArray.get(n)) {
            viewHolder.llItemPlan.setBackgroundResource(2131230934);
            viewHolder.tvVipPlanName.setTextColor(Color.parseColor("#ff333333"));
            viewHolder.tvVipPlanMoneyType.setTextColor(Color.parseColor("#333333"));
            viewHolder.tvVipPlanMoney.setTextColor(Color.parseColor("#333333"));
            viewHolder.tvVipPlanInfo.setBackgroundColor(Color.parseColor("#F3F3F3"));
            viewHolder.tvVipPlanInfo.setTextColor(Color.parseColor("#ffaaaaaa"));
            viewHolder.tvVipPlanRemark.setTextColor(Color.parseColor("#ffaaaaaa"));
        }
        else {
            viewHolder.llItemPlan.setBackgroundResource(2131230891);
            viewHolder.tvVipPlanName.setTextColor(Color.parseColor("#FFAE8A"));
            viewHolder.tvVipPlanMoneyType.setTextColor(Color.parseColor("#703B2B"));
            viewHolder.tvVipPlanMoney.setTextColor(Color.parseColor("#703B2B"));
            viewHolder.tvVipPlanInfo.setBackgroundColor(Color.parseColor("#F9DBCF"));
            viewHolder.tvVipPlanInfo.setTextColor(Color.parseColor("#F46F54"));
            viewHolder.tvVipPlanRemark.setTextColor(Color.parseColor("#F46F54"));
        }
    }
    
    public ViewHolder onCreateViewHolder(final ViewGroup viewGroup, final int n) {
        final View inflate = this.mInflater.inflate(2131493083, viewGroup, false);
        final ViewHolder viewHolder = new ViewHolder(inflate);
        viewHolder.llItemPlan = (LinearLayout)inflate.findViewById(2131297004);
        viewHolder.tvVipPlanName = (TextView)inflate.findViewById(2131297699);
        viewHolder.tvVipPlanMoneyType = (TextView)inflate.findViewById(2131297698);
        viewHolder.tvVipPlanMoney = (TextView)inflate.findViewById(2131297697);
        viewHolder.tvVipPlanInfo = (TextView)inflate.findViewById(2131297696);
        viewHolder.tvVipPlanRemark = (TextView)inflate.findViewById(2131297700);
        return viewHolder;
    }
    
    public void setItemChecked(final int mLastCheckedPosition) {
        this.mBooleanArray.put(mLastCheckedPosition, true);
        final int mLastCheckedPosition2 = this.mLastCheckedPosition;
        if (mLastCheckedPosition2 > -1) {
            this.mBooleanArray.put(mLastCheckedPosition2, false);
            this.notifyItemChanged(this.mLastCheckedPosition);
        }
        this.notifyDataSetChanged();
        this.mLastCheckedPosition = mLastCheckedPosition;
    }
    
    public void setOnItemClickListener(final VipPayPlanAdapter.VipPayPlanAdapter$OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
    
    protected static class ViewHolder extends RecyclerView$ViewHolder
    {
        private LinearLayout llItemPlan;
        private TextView tvVipPlanInfo;
        private TextView tvVipPlanMoney;
        private TextView tvVipPlanMoneyType;
        private TextView tvVipPlanName;
        private TextView tvVipPlanRemark;
        
        public ViewHolder(final View view) {
            super(view);
        }
    }
}

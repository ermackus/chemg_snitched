package com.kingagroot.kingdraw.ui.pay;

import android.widget.TextView;
import android.widget.RelativeLayout;
import android.widget.ImageView;
import android.graphics.Color;
import com.bumptech.glide.Glide;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;
import com.kingagroot.kingdraw.model.PayInfoModel$PayType;
import java.util.List;
import android.content.Context;
import android.widget.BaseAdapter;

public class VipPayWayAdapter extends BaseAdapter
{
    public static final String TAG = "VipPayWayAdapter";
    private final Context context;
    private int mLastCheckedPosition;
    private final List<PayInfoModel$PayType> payTypeList;
    
    public VipPayWayAdapter(final Context context, final List<PayInfoModel$PayType> payTypeList) {
        this.mLastCheckedPosition = -1;
        this.context = context;
        this.payTypeList = payTypeList;
    }
    
    public int getCount() {
        return this.payTypeList.size();
    }
    
    public PayInfoModel$PayType getItem(final int n) {
        return (PayInfoModel$PayType)this.payTypeList.get(n);
    }
    
    public long getItemId(final int n) {
        return 0L;
    }
    
    public View getView(final int n, View inflate, final ViewGroup viewGroup) {
        final LayoutInflater from = LayoutInflater.from(this.context);
        ViewHolder tag;
        if (inflate == null) {
            inflate = from.inflate(2131493084, (ViewGroup)null);
            tag = new ViewHolder(inflate);
            inflate.setTag((Object)tag);
        }
        else {
            tag = (ViewHolder)inflate.getTag();
        }
        final PayInfoModel$PayType item = this.getItem(n);
        Glide.with(this.context).load(item.getImageUrl()).into(tag.ivVipPayWayPic);
        tag.tvVipPayWayName.setText((CharSequence)item.getTitleName());
        if (this.mLastCheckedPosition == n) {
            tag.rlItemPayWay.setBackgroundResource(2131230891);
            tag.tvVipPayWayName.setTextColor(Color.parseColor("#9A3407"));
        }
        else {
            tag.rlItemPayWay.setBackgroundResource(2131230934);
            tag.tvVipPayWayName.setTextColor(Color.parseColor("#333333"));
        }
        return inflate;
    }
    
    public void setSelection(final int mLastCheckedPosition) {
        this.mLastCheckedPosition = mLastCheckedPosition;
        this.notifyDataSetChanged();
    }
    
    protected static class ViewHolder
    {
        private final ImageView ivVipPayWayPic;
        private final RelativeLayout rlItemPayWay;
        private final TextView tvVipPayWayName;
        
        public ViewHolder(final View view) {
            this.rlItemPayWay = (RelativeLayout)view.findViewById(2131297295);
            this.ivVipPayWayPic = (ImageView)view.findViewById(2131296946);
            this.tvVipPayWayName = (TextView)view.findViewById(2131297695);
        }
    }
}

package com.kingagroot.kingdraw.ui.pay;

import android.widget.TextView;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;
import com.kingagroot.kingdraw.model.PayInfoModel$PayPackageImages;
import java.util.List;
import android.content.Context;
import android.widget.BaseAdapter;

public class VipCenterAdapter extends BaseAdapter
{
    public static final String TAG = "VipCenterAdapter";
    private final Context context;
    private final List<PayInfoModel$PayPackageImages> payPackageImages;
    
    public VipCenterAdapter(final Context context, final List<PayInfoModel$PayPackageImages> payPackageImages) {
        this.context = context;
        this.payPackageImages = payPackageImages;
    }
    
    public int getCount() {
        return this.payPackageImages.size();
    }
    
    public PayInfoModel$PayPackageImages getItem(final int n) {
        return (PayInfoModel$PayPackageImages)this.payPackageImages.get(n);
    }
    
    public long getItemId(final int n) {
        return 0L;
    }
    
    public View getView(final int n, View inflate, final ViewGroup viewGroup) {
        final LayoutInflater from = LayoutInflater.from(this.context);
        ViewHolder tag;
        if (inflate == null) {
            inflate = from.inflate(2131493082, (ViewGroup)null);
            tag = new ViewHolder(inflate);
            inflate.setTag((Object)tag);
        }
        else {
            tag = (ViewHolder)inflate.getTag();
        }
        final PayInfoModel$PayPackageImages item = this.getItem(n);
        Glide.with(this.context).load(item.getImageUrl()).into(tag.ivVipCenterPic);
        tag.tvVipCenterName.setText((CharSequence)item.getTitleName());
        return inflate;
    }
    
    protected static class ViewHolder
    {
        private final ImageView ivVipCenterPic;
        private final TextView tvVipCenterName;
        
        public ViewHolder(final View view) {
            this.ivVipCenterPic = (ImageView)view.findViewById(2131296945);
            this.tvVipCenterName = (TextView)view.findViewById(2131297693);
        }
    }
}

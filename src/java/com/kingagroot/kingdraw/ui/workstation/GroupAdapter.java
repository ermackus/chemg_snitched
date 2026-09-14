package com.kingagroot.kingdraw.ui.workstation;

import android.widget.TextView;
import android.widget.LinearLayout;
import com.google.android.material.imageview.ShapeableImageView;
import android.graphics.Color;
import android.widget.ImageView;
import com.kingagroot.kingdraw.utils.ImageLoader;
import org.xutils.image.ImageOptions$Builder;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;
import com.kingagroot.kingdraw.model.GroupModel;
import java.util.List;
import android.content.Context;
import android.widget.BaseAdapter;

public class GroupAdapter extends BaseAdapter
{
    private int chooseGroupId;
    private final Context context;
    private final List<GroupModel> groupModels;
    
    public GroupAdapter(final Context context, final List<GroupModel> groupModels) {
        this.context = context;
        this.groupModels = groupModels;
    }
    
    public int getCount() {
        return this.groupModels.size();
    }
    
    public GroupModel getItem(final int n) {
        return (GroupModel)this.groupModels.get(n);
    }
    
    public long getItemId(final int n) {
        return n;
    }
    
    public View getView(final int n, View inflate, final ViewGroup viewGroup) {
        final LayoutInflater from = LayoutInflater.from(this.context);
        ViewHolder tag;
        if (inflate == null) {
            inflate = from.inflate(2131493080, (ViewGroup)null);
            tag = new ViewHolder(inflate);
            inflate.setTag((Object)tag);
        }
        else {
            tag = (ViewHolder)inflate.getTag();
        }
        final GroupModel groupModel = (GroupModel)this.groupModels.get(n);
        ImageLoader.bind((ImageView)tag.imgLogo, groupModel.getLogo(), new ImageOptions$Builder().setFailureDrawableId(2131231328).build());
        tag.tvGroupName.setText((CharSequence)groupModel.getCompanyName());
        if (groupModel.getGroupID() == this.chooseGroupId) {
            tag.llGroupItem.setBackgroundColor(Color.parseColor("#F3F3F3"));
        }
        else {
            tag.llGroupItem.setBackgroundColor(0);
        }
        return inflate;
    }
    
    public void setChooseGroupId(final int chooseGroupId) {
        this.chooseGroupId = chooseGroupId;
    }
    
    static class ViewHolder
    {
        private final ShapeableImageView imgLogo;
        private final LinearLayout llGroupItem;
        private final TextView tvGroupName;
        
        public ViewHolder(final View view) {
            this.llGroupItem = (LinearLayout)view.findViewById(2131297003);
            this.imgLogo = (ShapeableImageView)view.findViewById(2131296878);
            this.tvGroupName = (TextView)view.findViewById(2131297608);
        }
    }
}

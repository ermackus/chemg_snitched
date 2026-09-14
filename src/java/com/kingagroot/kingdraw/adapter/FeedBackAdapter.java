package com.kingagroot.kingdraw.adapter;

import android.widget.TextView;
import com.goodsrc.ui.library.widget.RoundAndCircleImageView;
import com.kingagroot.kingdraw.widget.FeedBackPicView;
import android.content.Intent;
import com.kingagroot.kingdraw.ui.ImageDetailActivity;
import android.view.ViewGroup;
import android.view.View;
import java.util.Iterator;
import com.kingagroot.kingdraw.widget.FeedBackPicView$OnItemClickListener;
import com.kingagroot.kingdraw.model.FeedBackModel$PictureListModel;
import java.util.ArrayList;
import android.widget.ImageView;
import com.kingagroot.kingdraw.utils.ImageLoader;
import org.xutils.image.ImageOptions$Builder;
import android.text.TextUtils;
import com.kingagroot.kingdraw.base.MApplication;
import android.view.LayoutInflater;
import com.kingagroot.kingdraw.model.FeedBackModel;
import java.util.List;
import android.content.Context;
import android.widget.BaseAdapter;

public class FeedBackAdapter extends BaseAdapter
{
    private final Context context;
    private final List<FeedBackModel> feedBackModelList;
    private final LayoutInflater layoutInflater;
    
    public FeedBackAdapter(final Context context, final List<FeedBackModel> feedBackModelList) {
        this.context = context;
        this.feedBackModelList = feedBackModelList;
        this.layoutInflater = LayoutInflater.from(context);
    }
    
    private void initializeViews(final FeedBackModel feedBackModel, final ViewHolder viewHolder) {
        if (MApplication.getInstance().isLogin()) {
            if (!TextUtils.isEmpty((CharSequence)feedBackModel.getNickName())) {
                viewHolder.tvFeedbackUser.setText((CharSequence)feedBackModel.getNickName());
            }
            else {
                viewHolder.tvFeedbackUser.setText((CharSequence)feedBackModel.getDevice());
            }
            ImageLoader.bind((ImageView)viewHolder.ivUserHead, feedBackModel.getHeadImage(), new ImageOptions$Builder().setFailureDrawableId(2131231024).build());
        }
        else {
            viewHolder.ivUserHead.setImageDrawable(this.context.getDrawable(2131231024));
            viewHolder.tvFeedbackUser.setText((CharSequence)feedBackModel.getDevice());
        }
        viewHolder.tvFeedbackTime.setText((CharSequence)feedBackModel.getCreateTime());
        if (feedBackModel.getContent() == null) {
            viewHolder.tvFeedbackContent.setVisibility(8);
        }
        else {
            viewHolder.tvFeedbackContent.setText((CharSequence)feedBackModel.getContent().trim());
            viewHolder.tvFeedbackContent.setVisibility(0);
        }
        final ArrayList list = new ArrayList();
        if (feedBackModel.getPictureList() != null && feedBackModel.getPictureList().size() > 0) {
            final Iterator iterator = feedBackModel.getPictureList().iterator();
            while (iterator.hasNext()) {
                list.add((Object)((FeedBackModel$PictureListModel)iterator.next()).getPicturePath());
            }
        }
        viewHolder.fpvImg.setList((List)list);
        if (feedBackModel.getIsRead() == 1) {
            viewHolder.imgNews.setVisibility(8);
        }
        else {
            viewHolder.imgNews.setVisibility(0);
        }
        viewHolder.fpvImg.setOnItemClickListener((FeedBackPicView$OnItemClickListener)new _$$Lambda$FeedBackAdapter$lGDytJRytws7_YOoyTJNHTIs5SQ(this, list));
    }
    
    public int getCount() {
        return this.feedBackModelList.size();
    }
    
    public FeedBackModel getItem(final int n) {
        return (FeedBackModel)this.feedBackModelList.get(n);
    }
    
    public long getItemId(final int n) {
        return n;
    }
    
    public View getView(final int n, final View view, final ViewGroup viewGroup) {
        View inflate = view;
        if (view == null) {
            inflate = this.layoutInflater.inflate(2131492946, (ViewGroup)null);
            inflate.setTag((Object)new ViewHolder(inflate));
        }
        this.initializeViews(this.getItem(n), (ViewHolder)inflate.getTag());
        return inflate;
    }
    
    protected static class ViewHolder
    {
        private final FeedBackPicView fpvImg;
        private final ImageView imgNews;
        private final RoundAndCircleImageView ivUserHead;
        private final TextView tvFeedbackContent;
        private final TextView tvFeedbackTime;
        private final TextView tvFeedbackUser;
        
        public ViewHolder(final View view) {
            this.ivUserHead = (RoundAndCircleImageView)view.findViewById(2131296940);
            this.tvFeedbackUser = (TextView)view.findViewById(2131297586);
            this.tvFeedbackTime = (TextView)view.findViewById(2131297585);
            this.tvFeedbackContent = (TextView)view.findViewById(2131297581);
            this.fpvImg = (FeedBackPicView)view.findViewById(2131296747);
            this.imgNews = (ImageView)view.findViewById(2131296880);
        }
    }
}

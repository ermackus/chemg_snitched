package com.kingagroot.kingdraw.adapter;

import android.widget.TextView;
import com.kingagroot.kingdraw.widget.FeedBackPicView;
import java.io.Serializable;
import com.kingagroot.kingdraw.ui.ImageDetailActivity;
import android.view.ViewGroup;
import android.graphics.Color;
import android.text.TextPaint;
import android.content.Intent;
import com.kingagroot.kingdraw.ui.WebViewActivity;
import android.view.View;
import android.text.style.ClickableSpan;
import java.util.Iterator;
import com.kingagroot.kingdraw.widget.FeedBackPicView$OnItemClickListener;
import com.kingagroot.kingdraw.model.FeedBackModel$PictureListModel;
import java.util.ArrayList;
import android.text.method.LinkMovementMethod;
import android.text.Spanned;
import android.text.style.URLSpan;
import android.text.SpannableStringBuilder;
import android.text.Html;
import com.kingagroot.kingdraw.model.FeedBackModel$FeedbackReplyModel;
import java.util.List;
import android.view.LayoutInflater;
import android.content.Context;
import android.widget.BaseAdapter;

public class FeedBackReplyAdapter extends BaseAdapter
{
    Context context;
    private final LayoutInflater layoutInflater;
    private final List<FeedBackModel$FeedbackReplyModel> replyModels;
    
    public FeedBackReplyAdapter(final Context context, final List<FeedBackModel$FeedbackReplyModel> replyModels) {
        this.context = context;
        this.replyModels = replyModels;
        this.layoutInflater = LayoutInflater.from(context);
    }
    
    private CharSequence getClickableHtml(final String s) {
        final Spanned fromHtml = Html.fromHtml(s);
        final SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder((CharSequence)fromHtml);
        final int length = fromHtml.length();
        int i = 0;
        for (URLSpan[] array = (URLSpan[])spannableStringBuilder.getSpans(0, length, (Class)URLSpan.class); i < array.length; ++i) {
            this.setLinkClickable(spannableStringBuilder, array[i]);
        }
        return (CharSequence)spannableStringBuilder;
    }
    
    private void initializeViews(final FeedBackModel$FeedbackReplyModel feedBackModel$FeedbackReplyModel, final ViewHolder viewHolder) {
        viewHolder.tvFeedbackUser.setText((CharSequence)feedBackModel$FeedbackReplyModel.getAdminName());
        viewHolder.tvFeedbackTime.setText((CharSequence)feedBackModel$FeedbackReplyModel.getCreateTime());
        if (feedBackModel$FeedbackReplyModel.getContent() != null && !feedBackModel$FeedbackReplyModel.getContent().isEmpty()) {
            viewHolder.tvFeedbackContent.setVisibility(0);
            viewHolder.tvFeedbackContent.setText(this.getClickableHtml(feedBackModel$FeedbackReplyModel.getContent()));
            viewHolder.tvFeedbackContent.setMovementMethod(LinkMovementMethod.getInstance());
        }
        else {
            viewHolder.tvFeedbackContent.setVisibility(8);
        }
        final ArrayList list = new ArrayList();
        if (feedBackModel$FeedbackReplyModel.getPictureList() != null && feedBackModel$FeedbackReplyModel.getPictureList().size() > 0) {
            final Iterator iterator = feedBackModel$FeedbackReplyModel.getPictureList().iterator();
            while (iterator.hasNext()) {
                list.add((Object)((FeedBackModel$PictureListModel)iterator.next()).getPicturePath());
            }
            viewHolder.fpvImg.setVisibility(0);
        }
        else {
            viewHolder.fpvImg.setVisibility(8);
        }
        viewHolder.fpvImg.setList((List)list);
        viewHolder.fpvImg.setOnItemClickListener((FeedBackPicView$OnItemClickListener)new _$$Lambda$FeedBackReplyAdapter$8l5V7GWZt7DRCaqZEp1X_6OxX6I(this, list));
    }
    
    private void setLinkClickable(final SpannableStringBuilder spannableStringBuilder, final URLSpan urlSpan) {
        final int spanStart = spannableStringBuilder.getSpanStart((Object)urlSpan);
        final int spanEnd = spannableStringBuilder.getSpanEnd((Object)urlSpan);
        final int spanFlags = spannableStringBuilder.getSpanFlags((Object)urlSpan);
        final ClickableSpan clickableSpan = new ClickableSpan(this, urlSpan) {
            final FeedBackReplyAdapter this$0;
            final URLSpan val$urlSpan;
            
            public void onClick(final View view) {
                final String url = this.val$urlSpan.getURL();
                final Intent intent = new Intent(this.this$0.context, (Class)WebViewActivity.class);
                intent.putExtra("url_key", url);
                this.this$0.context.startActivity(intent);
            }
            
            public void updateDrawState(final TextPaint textPaint) {
                textPaint.setColor(Color.parseColor("#0058FF"));
                textPaint.setUnderlineText(true);
            }
        };
        spannableStringBuilder.removeSpan((Object)urlSpan);
        spannableStringBuilder.setSpan((Object)clickableSpan, spanStart, spanEnd, spanFlags);
    }
    
    public int getCount() {
        return this.replyModels.size();
    }
    
    public FeedBackModel$FeedbackReplyModel getItem(final int n) {
        return (FeedBackModel$FeedbackReplyModel)this.replyModels.get(n);
    }
    
    public long getItemId(final int n) {
        return n;
    }
    
    public View getView(final int n, final View view, final ViewGroup viewGroup) {
        View inflate = view;
        if (view == null) {
            inflate = this.layoutInflater.inflate(2131492947, (ViewGroup)null);
            inflate.setTag((Object)new ViewHolder(inflate));
        }
        this.initializeViews(this.getItem(n), (ViewHolder)inflate.getTag());
        return inflate;
    }
    
    protected static class ViewHolder
    {
        private final FeedBackPicView fpvImg;
        private final TextView tvFeedbackContent;
        private final TextView tvFeedbackTime;
        private final TextView tvFeedbackUser;
        
        public ViewHolder(final View view) {
            this.tvFeedbackUser = (TextView)view.findViewById(2131297586);
            this.tvFeedbackTime = (TextView)view.findViewById(2131297585);
            this.tvFeedbackContent = (TextView)view.findViewById(2131297581);
            this.fpvImg = (FeedBackPicView)view.findViewById(2131296747);
        }
    }
}

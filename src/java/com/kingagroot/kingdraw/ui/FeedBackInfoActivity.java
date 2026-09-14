package com.kingagroot.kingdraw.ui;

import android.app.Activity;
import com.goodsrc.ui.library.widget.AppManager;
import android.os.Bundle;
import java.io.Serializable;
import android.content.Intent;
import android.widget.ListAdapter;
import android.content.Context;
import com.kingagroot.kingdraw.adapter.FeedBackReplyAdapter;
import java.util.Iterator;
import android.os.Handler;
import android.view.View;
import com.kingagroot.kingdraw.widget.FeedBackPicView$OnItemClickListener;
import com.kingagroot.kingdraw.model.FeedBackModel$PictureListModel;
import android.widget.ImageView;
import com.kingagroot.kingdraw.utils.ImageLoader;
import org.xutils.image.ImageOptions;
import android.text.TextUtils;
import com.kingagroot.kingdraw.base.MApplication;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout$OnRefreshListener;
import org.xutils.http.RequestParams;
import com.kingagroot.kingdraw.http.NewHttpManager;
import com.goodsrc.library.http.RequestCallBack;
import org.json.JSONException;
import org.json.JSONObject;
import com.kingagroot.kingdraw.config.NetConfig$Feedback;
import com.kingagroot.kingdraw.http.NewHttpManager$Builder;
import com.kingagroot.kingdraw.model.FeedBackModel;
import java.util.ArrayList;
import android.widget.TextView;
import com.kingagroot.kingdraw.widget.GSwipeRefreshLayout;
import android.widget.ScrollView;
import com.kingagroot.kingdraw.model.FeedBackModel$FeedbackReplyModel;
import java.util.List;
import com.goodsrc.ui.library.widget.NoScrollListView;
import com.goodsrc.ui.library.widget.RoundAndCircleImageView;
import com.kingagroot.kingdraw.widget.FeedBackPicView;
import com.kingagroot.kingdraw.ui.jpushbase.JpushToolBarBaseActivity;

public class FeedBackInfoActivity extends JpushToolBarBaseActivity
{
    public static final String DATA_ID = "DATA";
    private FeedBackPicView fpvInfoImg;
    private int id;
    private RoundAndCircleImageView ivInfoHead;
    private NoScrollListView listReply;
    List<FeedBackModel$FeedbackReplyModel> optionReplyModels;
    private ScrollView scrollView;
    private GSwipeRefreshLayout swiperefreshlayout;
    private TextView tvFeedbackInfoContent;
    private TextView tvFeedbackInfoTime;
    private TextView tvFeedbackInfoUser;
    
    public FeedBackInfoActivity() {
        this.optionReplyModels = (List<FeedBackModel$FeedbackReplyModel>)new ArrayList();
        this.id = 0;
    }
    
    private void getInfoFeedBack(final int n) {
        final NewHttpManager build = new NewHttpManager$Builder().build();
        final RequestParams params = build.params(NetConfig$Feedback.getOptionDetailById());
        final JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("feedbackID", n);
            params.addParameter("", jsonObject.toString());
        }
        catch (final JSONException ex) {
            ex.printStackTrace();
        }
        build.request(params, (RequestCallBack)new FeedBackInfoActivity$1(this));
    }
    
    private void init() {
        this.ivInfoHead = (RoundAndCircleImageView)this.findViewById(2131296922);
        this.tvFeedbackInfoUser = (TextView)this.findViewById(2131297584);
        this.tvFeedbackInfoTime = (TextView)this.findViewById(2131297583);
        this.tvFeedbackInfoContent = (TextView)this.findViewById(2131297582);
        this.fpvInfoImg = (FeedBackPicView)this.findViewById(2131296748);
        this.listReply = (NoScrollListView)this.findViewById(2131296979);
        this.scrollView = (ScrollView)this.findViewById(2131297351);
        (this.swiperefreshlayout = (GSwipeRefreshLayout)this.findViewById(2131297437)).setOnRefreshListener((SwipeRefreshLayout$OnRefreshListener)new _$$Lambda$FeedBackInfoActivity$j1UCXe_0vP79kfSI7NFVPXFIkQI(this));
    }
    
    private void setData(final FeedBackModel feedBackModel) {
        if (MApplication.getInstance().isLogin()) {
            if (!TextUtils.isEmpty((CharSequence)feedBackModel.getNickName())) {
                this.tvFeedbackInfoUser.setText((CharSequence)feedBackModel.getNickName());
            }
            else {
                this.tvFeedbackInfoUser.setText((CharSequence)feedBackModel.getDevice());
            }
            ImageLoader.bind((ImageView)this.ivInfoHead, feedBackModel.getHeadImage(), new ImageOptions.Builder().setFailureDrawableId(2131231024).build());
        }
        else {
            this.tvFeedbackInfoUser.setText((CharSequence)feedBackModel.getDevice());
        }
        this.tvFeedbackInfoTime.setText((CharSequence)feedBackModel.getCreateTime());
        if (feedBackModel.getContent() == null) {
            this.tvFeedbackInfoContent.setVisibility(8);
        }
        else {
            this.tvFeedbackInfoContent.setVisibility(0);
            this.tvFeedbackInfoContent.setText((CharSequence)feedBackModel.getContent());
        }
        final ArrayList list = new ArrayList();
        if (feedBackModel.getPictureList() != null && feedBackModel.getPictureList().size() > 0) {
            final Iterator iterator = feedBackModel.getPictureList().iterator();
            while (iterator.hasNext()) {
                list.add((Object)((FeedBackModel$PictureListModel)iterator.next()).getPicturePath());
            }
        }
        this.fpvInfoImg.setList((List)list);
        this.fpvInfoImg.setOnItemClickListener((FeedBackPicView$OnItemClickListener)new _$$Lambda$FeedBackInfoActivity$gCTQtiLJ8FBVWSkr23OLPtCGA2U(this, list));
        final List feedbackReplyList = feedBackModel.getFeedbackReplyList();
        if ((this.optionReplyModels = (List<FeedBackModel$FeedbackReplyModel>)feedbackReplyList) != null && feedbackReplyList.size() > 0) {
            this.setListAdapter(this.optionReplyModels);
            this.hidEmptyView();
        }
        else {
            this.showEmptyView((View)this.listReply);
        }
        this.setEnableEmptyRefresh(false);
        this.setEmptyView(2131231022, 2131821093);
        new Handler().postDelayed((Runnable)new _$$Lambda$FeedBackInfoActivity$QHoxbrc01KgQWHVkGoSsMRm4LRk(this), 50L);
    }
    
    private void setListAdapter(final List<FeedBackModel$FeedbackReplyModel> list) {
        this.listReply.setAdapter((ListAdapter)new FeedBackReplyAdapter((Context)this, (List)list));
    }
    
    public int getFeedBackInfoId() {
        return this.id;
    }
    
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.setTitle(2131820832);
        this.setContentView(2131492905);
        this.init();
        this.id = this.getIntent().getIntExtra("DATA", 0);
        this.setRefreshing(true, false);
        final Activity lastActivity = AppManager.getInstance().getLastActivity();
        if (lastActivity instanceof FeedBackInfoActivity && ((FeedBackInfoActivity)lastActivity).getFeedBackInfoId() == this.id) {
            this.finish();
            return;
        }
        AppManager.getInstance().addActivity((Activity)this);
    }
    
    protected void onDestroy() {
        super.onDestroy();
        AppManager.getInstance().removeActivity((Activity)this);
    }
    
    @Override
    protected void onResume() {
        super.onResume();
        this.getInfoFeedBack(this.id);
    }
}

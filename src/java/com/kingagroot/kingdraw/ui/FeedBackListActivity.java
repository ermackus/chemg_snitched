package com.kingagroot.kingdraw.ui;

import android.os.Handler;
import android.view.MenuItem;
import android.text.style.ForegroundColorSpan;
import android.graphics.Color;
import android.text.SpannableString;
import android.view.Menu;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.content.Context;
import com.kingagroot.kingdraw.widget.MListView$OnMListViewlistener;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout$OnRefreshListener;
import android.widget.AdapterView$OnItemClickListener;
import org.xutils.http.RequestParams;
import com.kingagroot.kingdraw.http.NewHttpManager;
import com.goodsrc.library.http.RequestCallBack;
import org.json.JSONException;
import org.json.JSONObject;
import com.kingagroot.kingdraw.config.NetConfig$Feedback;
import com.kingagroot.kingdraw.http.NewHttpManager$Builder;
import java.util.ArrayList;
import com.kingagroot.kingdraw.widget.GSwipeRefreshLayout;
import android.widget.RelativeLayout;
import com.kingagroot.kingdraw.model.FeedBackModel;
import java.util.List;
import com.kingagroot.kingdraw.widget.MListView;
import com.kingagroot.kingdraw.adapter.FeedBackAdapter;
import com.kingagroot.kingdraw.ui.jpushbase.JpushToolBarBaseActivity;

public class FeedBackListActivity extends JpushToolBarBaseActivity
{
    private FeedBackAdapter adapter;
    boolean add;
    String lastNum;
    private MListView listFeedback;
    List<FeedBackModel> models;
    private RelativeLayout rlNoMsg;
    private GSwipeRefreshLayout swiperefreshlayout;
    
    public FeedBackListActivity() {
        this.models = (List<FeedBackModel>)new ArrayList();
        this.lastNum = "";
    }
    
    private void getListFeedBack(final String s) {
        final NewHttpManager build = new NewHttpManager$Builder().build();
        final RequestParams params = build.params(NetConfig$Feedback.getFeedbackList());
        final JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("num", (Object)s);
            params.addParameter("", jsonObject.toString());
        }
        catch (final JSONException ex) {
            ex.printStackTrace();
        }
        build.request(params, (RequestCallBack)new FeedBackListActivity$1(this));
    }
    
    private void init() {
        this.swiperefreshlayout = (GSwipeRefreshLayout)this.findViewById(2131297437);
        this.listFeedback = (MListView)this.findViewById(2131296975);
        this.rlNoMsg = (RelativeLayout)this.findViewById(2131297303);
        this.listFeedback.setOnItemClickListener((AdapterView$OnItemClickListener)new _$$Lambda$FeedBackListActivity$sb66sohx_hrRHrw4eXkFdTkZJVA(this));
        this.swiperefreshlayout.setOnRefreshListener((SwipeRefreshLayout$OnRefreshListener)new _$$Lambda$JXgRsgc_MWpOPUyyTIkcwOf4AK8(this));
        this.listFeedback.setOnMListViewlistener((MListView$OnMListViewlistener)new _$$Lambda$j9fdfQGpIYuM5Lxd3gaAp4Rg9dU(this));
    }
    
    private void setListAdapter() {
        final FeedBackAdapter feedBackAdapter = new FeedBackAdapter((Context)this, (List)this.models);
        this.adapter = feedBackAdapter;
        this.listFeedback.setAdapter((ListAdapter)feedBackAdapter);
    }
    
    protected void getMore() {
        this.add = true;
        this.getListFeedBack(this.lastNum);
    }
    
    public void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.setContentView(2131493050);
        this.setTitle(2131820828);
        this.init();
        this.setListAdapter();
    }
    
    public boolean onCreateOptionsMenu(final Menu menu) {
        final MenuItem add = menu.add(0, 0, 0, (CharSequence)this.getString(2131820833));
        final SpannableString title = new SpannableString(add.getTitle());
        title.setSpan((Object)new ForegroundColorSpan(Color.parseColor("#E13E3F")), 0, title.length(), 0);
        add.setTitle((CharSequence)title);
        add.setShowAsAction(2);
        super.onCreateOptionsMenu(menu);
        return true;
    }
    
    @Override
    protected void onNewJpushMsg(final Intent intent) {
    }
    
    public boolean onOptionsItemSelected(final MenuItem menuItem) {
        if (menuItem.getItemId() == 0) {
            this.startActivity(new Intent((Context)this, (Class)FeedbackActivity.class));
        }
        return super.onOptionsItemSelected(menuItem);
    }
    
    public void onResume() {
        super.onResume();
        this.swiperefreshlayout.setRefreshing(true);
        this.refreshData();
    }
    
    protected void refreshData() {
        this.add = false;
        this.getListFeedBack(this.lastNum = "");
        new Handler().postDelayed((Runnable)new _$$Lambda$FeedBackListActivity$wXl6Rciq7HTCgDZlyW_kks3m7eI(this), 500L);
    }
}

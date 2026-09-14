package com.kingagroot.kingdraw.ui.synchornize;

import com.kingagroot.kingdraw.ui.SynService;
import android.content.Intent;
import android.content.Context;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import android.os.Bundle;
import androidx.viewpager.widget.PagerAdapter;
import java.util.ArrayList;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.tabs.TabLayout;
import androidx.fragment.app.Fragment;
import java.util.List;
import com.kingagroot.kingdraw.adapter.FragmentAdapter;
import com.goodsrc.ui.library.ToolBarActivity;

public class SynchronizeActivity extends ToolBarActivity
{
    public static final String INTENT_DATA_PAGEINDEX = "intent_data_pageindex";
    private FragmentAdapter fragmentAdapter;
    private final List<Fragment> fragments;
    private final String[] tabTitles;
    private TabLayout tablayout;
    private ViewPager viewpager;
    
    public SynchronizeActivity() {
        this.fragments = (List<Fragment>)new ArrayList();
        this.tabTitles = new String[2];
    }
    
    private void initData() {
        this.tabTitles[0] = this.getString(2131821495);
        this.tabTitles[1] = this.getString(2131820785);
        this.fragments.add((Object)new UploadFragment());
        this.fragments.add((Object)new DownLoadFragment());
        final FragmentAdapter fragmentAdapter = new FragmentAdapter(this.getSupportFragmentManager(), this.tabTitles, (List)this.fragments);
        this.fragmentAdapter = fragmentAdapter;
        this.viewpager.setAdapter((PagerAdapter)fragmentAdapter);
        this.tablayout.setupWithViewPager(this.viewpager);
        final Bundle extras = this.getIntent().getExtras();
        if (extras != null) {
            this.viewpager.setCurrentItem(extras.getInt("intent_data_pageindex", 0));
        }
    }
    
    private void initView() {
        this.tablayout = (TabLayout)this.findViewById(2131297449);
        this.viewpager = (ViewPager)this.findViewById(2131297746);
    }
    
    private void sendDownloadNextSyn() {
        final LocalBroadcastManager instance = LocalBroadcastManager.getInstance((Context)this);
        final Intent intent = new Intent();
        intent.setAction("syn_download_next");
        instance.sendBroadcast(intent);
    }
    
    private void sendUploadNextSyn() {
        final LocalBroadcastManager instance = LocalBroadcastManager.getInstance((Context)this);
        final Intent intent = new Intent();
        intent.setAction("syn_upload_next");
        instance.sendBroadcast(intent);
    }
    
    private void setAutoSyn(final boolean b) {
        final LocalBroadcastManager instance = LocalBroadcastManager.getInstance((Context)this);
        final Intent intent = new Intent();
        intent.setAction("syn_auto");
        intent.putExtra("intent_data_syn_auto", b);
        instance.sendBroadcast(intent);
    }
    
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.setTitle(2131821444);
        this.setContentView(2131492934);
        this.startService(new Intent((Context)this, (Class)SynService.class));
        this.initView();
        this.initData();
    }
    
    protected void onPause() {
        this.setAutoSyn(true);
        this.sendUploadNextSyn();
        this.sendDownloadNextSyn();
        super.onPause();
    }
    
    protected void onResume() {
        super.onResume();
        this.setAutoSyn(false);
    }
}

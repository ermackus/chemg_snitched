package com.goodsrc.ui.library;

import android.view.View;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.ViewGroup;
import com.goodsrc.library.utils.StatusBarUtil;
import android.os.Bundle;
import android.view.inputmethod.InputMethodManager;
import androidx.appcompat.widget.Toolbar;
import com.goodsrc.ui.library.widget.MaterialProgress.ProgressView;
import android.widget.FrameLayout;
import com.goodsrc.ui.library.widget.EmptyView;
import android.widget.LinearLayout;
import com.google.android.material.appbar.AppBarLayout;

public class ToolBarActivity extends BaseActivity
{
    protected AppBarLayout appbarLayout;
    protected LinearLayout barLayout;
    private LinearLayout contentView;
    private EmptyView emptyView;
    private FrameLayout progressFram;
    private ProgressView progressView;
    private LinearLayout rootView;
    protected Toolbar toolbar;
    
    public void hidEmptyView() {
        this.emptyView.setVisibility(8);
    }
    
    public void hidInput() {
        try {
            ((InputMethodManager)this.getSystemService("input_method")).hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), 2);
        }
        catch (final Exception ex) {}
    }
    
    public void hideBar() {
        this.barLayout.setVisibility(8);
    }
    
    @Override
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        StatusBarUtil.MIUISetStatusBarLightMode(this.getWindow(), true);
        final LinearLayout rootView = (LinearLayout)this.getLayoutInflater().inflate(R.layout.activity_toolbar, (ViewGroup)null);
        this.rootView = rootView;
        this.contentView = (LinearLayout)rootView.findViewById(R.id.content_view);
        this.toolbar = (Toolbar)this.rootView.findViewById(R.id.toolbar);
        this.barLayout = (LinearLayout)this.rootView.findViewById(R.id.bar_layout);
        this.progressFram = (FrameLayout)this.rootView.findViewById(R.id.progress_fram);
        this.progressView = (ProgressView)this.rootView.findViewById(R.id.progressView);
        this.emptyView = (EmptyView)this.rootView.findViewById(R.id.empty_view);
        this.appbarLayout = (AppBarLayout)this.rootView.findViewById(R.id.appbar_layout);
        this.setSupportActionBar(this.toolbar);
        this.getSupportActionBar().setHomeButtonEnabled(true);
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.emptyView.setEmptyViewOnRefreshListener((EmptyView.EmptyViewOnRefreshListener)new EmptyView.EmptyViewOnRefreshListener(this) {
            final ToolBarActivity this$0;
            
            @Override
            public void onRefresh() {
                this.this$0.onEmptyRefresh();
            }
        });
    }
    
    protected void onEmptyRefresh() {
    }
    
    public boolean onOptionsItemSelected(final MenuItem menuItem) {
        if (menuItem.getItemId() == 16908332) {
            this.onBackPressed();
        }
        return super.onOptionsItemSelected(menuItem);
    }
    
    @Override
    protected void onResume() {
        super.onResume();
        this.hidInput();
    }
    
    public void setContentView(final int n) {
        this.contentView.addView(LayoutInflater.from((Context)this).inflate(n, (ViewGroup)null), -1, -1);
        super.setContentView((View)this.rootView);
    }
    
    public void setContentView(final View view) {
        this.contentView.addView(view);
        super.setContentView((View)this.rootView);
    }
    
    public void setEmptyView(final int n) {
        this.emptyView.setEmptyView(this.getString(n));
    }
    
    public void setEmptyView(final int n, final int n2) {
        this.emptyView.setEmptyView(n, this.getString(n2));
    }
    
    public void setEnableEmptyRefresh(final boolean enableRefresh) {
        this.emptyView.setEnableRefresh(enableRefresh);
    }
    
    public void setRefreshing(final boolean refreshing, final boolean b) {
        final FrameLayout progressFram = this.progressFram;
        int visibility;
        if (refreshing) {
            visibility = 0;
        }
        else {
            visibility = 8;
        }
        progressFram.setVisibility(visibility);
        if (refreshing) {
            this.progressFram.setClickable(b ^ true);
        }
        this.progressView.setRefreshing(refreshing);
    }
    
    public void showBar() {
        this.barLayout.setVisibility(0);
    }
    
    public void showEmptyView(final View view) {
        final EmptyView emptyView = this.emptyView;
        Object contentView = view;
        if (view == null) {
            contentView = this.contentView;
        }
        emptyView.showLocatuion((View)contentView);
        this.emptyView.setVisibility(0);
    }
}

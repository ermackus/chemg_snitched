package com.kingagroot.kingdraw.ui;

import android.content.IntentFilter;
import android.view.MenuInflater;
import android.view.Menu;
import android.os.Bundle;
import com.kingagroot.kingdraw.dialog.LogInHintDialog;
import android.view.MotionEvent;
import com.kingagroot.kingdraw.config.ShareData;
import com.kingagroot.kingdraw.widget.GTabViewCloud;
import com.kingagroot.kingdraw.widget.GTabViewLocal;
import com.kingagroot.kingdraw.base.MApplication;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ColorDrawable;
import android.widget.PopupWindow;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import androidx.core.content.ContextCompat;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.Objects;
import com.kingagroot.kingdraw.limit.LimitPalette$OnJumpPalette;
import com.kingagroot.kingdraw.limit.LimitPalette;
import com.kingagroot.component.ui.utils.CheckDoubleClick;
import com.goodsrc.library.utils.DisplayUtil;
import android.content.Context;
import android.content.Intent;
import android.view.View$OnTouchListener;
import androidx.viewpager.widget.ViewPager$OnPageChangeListener;
import com.google.android.material.tabs.TabLayout$Tab;
import com.google.android.material.tabs.TabLayout$OnTabSelectedListener;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager.widget.PagerAdapter;
import java.util.ArrayList;
import com.kingagroot.kingdraw.widget.GViewPager;
import com.google.android.material.tabs.TabLayout;
import android.view.View;
import android.content.BroadcastReceiver;
import androidx.fragment.app.Fragment;
import java.util.List;
import com.kingagroot.kingdraw.adapter.FragmentAdapter;
import com.kingagroot.kingdraw.widget.GTabView;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import android.view.MenuItem;

public class MainFragment extends BaseFragment implements LocalDiskFragment$OnLocalFileCountChangeListener, CloudDiskFragment$OnCloudFileCountChangeListener
{
    static final boolean $assertionsDisabled = false;
    private long LocalTabDoubleTapUpTime;
    MenuItem addItem;
    private LocalBroadcastManager broadcastManager;
    private CloudDiskFragment cloudDiskFragment;
    MenuItem cloudHintItem;
    public GTabView cloudTab;
    MenuItem doneItem;
    private FragmentAdapter fragmentAdapter;
    private final List<Fragment> fragments;
    private boolean isDataInit;
    private LocalDiskFragment localDiskFragment;
    public GTabView localTab;
    MenuItem moreItem;
    BroadcastReceiver receiver;
    private View rootView;
    MenuItem searchItem;
    private final String[] tabTitles;
    private TabLayout tablayout;
    public GViewPager viewpager;
    
    public MainFragment() {
        this.fragments = (List<Fragment>)new ArrayList();
        this.tabTitles = new String[2];
        this.receiver = (BroadcastReceiver)new MainFragment$1(this);
    }
    
    private void initView(View view) {
        this.tablayout = (TabLayout)view.findViewById(2131297449);
        (this.viewpager = (GViewPager)view.findViewById(2131297746)).setAdapter((PagerAdapter)this.fragmentAdapter);
        this.tablayout.setupWithViewPager((ViewPager)this.viewpager);
        this.tablayout.addOnTabSelectedListener((TabLayout$OnTabSelectedListener)new TabLayout$OnTabSelectedListener(this) {
            final MainFragment this$0;
            
            public void onTabReselected(final TabLayout$Tab tabLayout$Tab) {
            }
            
            public void onTabSelected(final TabLayout$Tab tabLayout$Tab) {
            }
            
            public void onTabUnselected(final TabLayout$Tab tabLayout$Tab) {
            }
        });
        this.viewpager.addOnPageChangeListener((ViewPager$OnPageChangeListener)new MainFragment$3(this));
        for (int i = 0; i < this.tablayout.getTabCount(); ++i) {
            final TabLayout$Tab tab = this.tablayout.getTabAt(i);
            if (tab != null) {
                tab.setCustomView(this.getTabView(i));
                if (tab.getCustomView() != null) {
                    view = (View)tab.getCustomView().getParent();
                    view.setTag((Object)i);
                    view.setOnTouchListener((View$OnTouchListener)new _$$Lambda$MainFragment$pejHFcjHbvmNJ3wHLTeVhzkIWtk(this));
                }
            }
        }
        this.viewpager.setCurrentItem(0);
        this.setTabSelect(0);
    }
    
    private void login() {
        this.viewpager.setScrollable(true);
        this.setCloudTabStatus(true);
    }
    
    private void onCloudFragmentMenu(final MenuItem menuItem) {
        final int itemId = menuItem.getItemId();
        if (itemId != 1) {
            if (itemId != 3) {
                if (itemId != 4) {
                    if (itemId == 5) {
                        this.showTips((View)this.tablayout);
                    }
                }
                else {
                    this.doneBatchOperate();
                }
            }
            else {
                this.startActivity(new Intent((Context)this.getActivity(), (Class)CloudSearchActivity.class));
            }
        }
        else {
            this.cloudDiskFragment.showAtLocation((View)this.tablayout, 53, 0, DisplayUtil.dip2px(this.requireContext(), 65.0f));
        }
    }
    
    private void onEditFragmentMenu(final MenuItem menuItem) {
        final int itemId = menuItem.getItemId();
        if (itemId != 1) {
            if (itemId != 2) {
                if (itemId != 3) {
                    if (itemId == 4) {
                        if (CheckDoubleClick.isFastDoubleClick()) {
                            return;
                        }
                        this.doneBatchOperate();
                    }
                }
                else {
                    if (CheckDoubleClick.isFastDoubleClick()) {
                        return;
                    }
                    this.startActivity(new Intent((Context)this.getActivity(), (Class)SearchActivity.class));
                }
            }
            else {
                if (CheckDoubleClick.isFastDoubleClick()) {
                    return;
                }
                new LimitPalette((LimitPalette$OnJumpPalette)new MainFragment$4(this)).jumpPaletteCheck((Context)this.getActivity());
            }
        }
        else {
            if (CheckDoubleClick.isFastDoubleClick()) {
                return;
            }
            this.localDiskFragment.showAtLocation((View)this.tablayout, 53, 0, DisplayUtil.dip2px(this.requireContext(), 65.0f));
        }
    }
    
    private void outLogin() {
        this.viewpager.setCurrentItem(0);
        this.viewpager.setScrollable(false);
        this.setCloudTabStatus(false);
        this.cloudTab.setTabText(0);
    }
    
    private void setCloudTabStatus(final boolean b) {
        final TextView tabText = ((GTabView)Objects.requireNonNull((Object)((TabLayout$Tab)Objects.requireNonNull((Object)this.tablayout.getTabAt(1))).getCustomView())).getTabText();
        if (b) {
            tabText.setTextColor(-16777216);
        }
        else {
            tabText.setTextColor(-4408132);
        }
    }
    
    private void setTabImageViewSelect(final ImageView imageView, final boolean b) {
        if (b) {
            imageView.setImageResource(2131231205);
        }
        else {
            imageView.setImageResource(2131231202);
        }
    }
    
    private void setTabSelect(final int n) {
        for (int i = 0; i < this.tablayout.getTabCount(); ++i) {
            final GTabView gTabView = (GTabView)((TabLayout$Tab)Objects.requireNonNull((Object)this.tablayout.getTabAt(i))).getCustomView();
            final TextView tabText = gTabView.getTabText();
            final ImageView imageView = gTabView.getImageView();
            if (i == n) {
                this.setTabTextSelect(tabText, true);
                this.setTabImageViewSelect(imageView, true);
            }
            else {
                this.setTabTextSelect(tabText, false);
                this.setTabImageViewSelect(imageView, false);
            }
        }
    }
    
    private void setTabTextSelect(final TextView textView, final boolean b) {
        if (b) {
            textView.setTextColor(ContextCompat.getColor(this.requireContext(), 2131099773));
        }
        else {
            textView.setTextColor(-16777216);
        }
    }
    
    private void showTips(final View view) {
        final PopupWindow popupWindow = new PopupWindow(LayoutInflater.from(this.getContext()).inflate(2131493156, (ViewGroup)null, false), -2, -2, true);
        popupWindow.setBackgroundDrawable((Drawable)new ColorDrawable(0));
        popupWindow.setOutsideTouchable(true);
        popupWindow.setTouchable(true);
        final MenuItem moreItem = this.moreItem;
        int n;
        if (moreItem != null && moreItem.isVisible()) {
            n = DisplayUtil.dip2px(this.requireContext(), 62.0f);
        }
        else {
            n = DisplayUtil.dip2px(this.requireContext(), 18.0f);
        }
        popupWindow.showAtLocation(view, 53, n, DisplayUtil.dip2px(this.requireContext(), 62.0f));
    }
    
    public boolean checkLogin() {
        final boolean login = MApplication.getInstance().isLogin();
        if (login) {
            this.login();
        }
        else {
            this.outLogin();
        }
        return login;
    }
    
    public void doneBatchOperate() {
        final MenuItem doneItem = this.doneItem;
        if (doneItem != null) {
            doneItem.setVisible(false);
        }
        final MenuItem searchItem = this.searchItem;
        if (searchItem != null) {
            searchItem.setVisible(true);
        }
        final MenuItem moreItem = this.moreItem;
        if (moreItem != null) {
            moreItem.setVisible(true);
        }
        if (this.viewpager.getCurrentItem() == 0) {
            this.localDiskFragment.toBatchOperate(false);
            final MenuItem addItem = this.addItem;
            if (addItem != null) {
                addItem.setVisible(true);
            }
            final MenuItem cloudHintItem = this.cloudHintItem;
            if (cloudHintItem != null) {
                cloudHintItem.setVisible(false);
            }
        }
        else {
            this.cloudDiskFragment.toBatchOperate(false);
            final MenuItem addItem2 = this.addItem;
            if (addItem2 != null) {
                addItem2.setVisible(false);
            }
            final MenuItem cloudHintItem2 = this.cloudHintItem;
            if (cloudHintItem2 != null) {
                cloudHintItem2.setVisible(true);
            }
        }
        this.viewpager.setScrollable(true);
    }
    
    public View getTabView(final int n) {
        GTabView gTabView;
        if (n != 1) {
            if (this.localTab == null) {
                (this.localTab = (GTabView)new GTabViewLocal(this.getContext(), (ViewPager)this.viewpager)).setShowIcon(false);
                this.localTab.setTabText(0);
            }
            gTabView = this.localTab;
        }
        else {
            if (this.cloudTab == null) {
                (this.cloudTab = (GTabView)new GTabViewCloud(this.getContext(), (ViewPager)this.viewpager)).setShowIcon(false);
                this.cloudTab.setTabText(0);
            }
            gTabView = this.cloudTab;
        }
        gTabView.setGravity(17);
        return (View)gTabView;
    }
    
    public void onCloudCountChange(final int tabText) {
        this.cloudTab.setTabText(tabText);
    }
    
    public void onCountChange(final int tabText) {
        this.localTab.setTabText(tabText);
    }
    
    public void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        if (!this.isDataInit) {
            this.tabTitles[0] = this.getString(2131820988);
            this.tabTitles[1] = this.getString(2131820701);
            this.localDiskFragment = new LocalDiskFragment();
            this.cloudDiskFragment = new CloudDiskFragment();
            this.fragments.add((Object)this.localDiskFragment);
            this.fragments.add((Object)this.cloudDiskFragment);
            this.isDataInit = true;
            this.localDiskFragment.setOnLocalFileCountChangeListener((LocalDiskFragment$OnLocalFileCountChangeListener)this);
            this.cloudDiskFragment.setOnCloudFileCountChangeListener((CloudDiskFragment$OnCloudFileCountChangeListener)this);
        }
        this.setHasOptionsMenu(true);
        this.fragmentAdapter = new FragmentAdapter(this.getChildFragmentManager(), this.tabTitles, this.fragments);
    }
    
    public void onCreateOptionsMenu(final Menu menu, final MenuInflater menuInflater) {
        (this.moreItem = menu.add(0, 1, 5, 2131821024)).setIcon(2131231344);
        this.moreItem.setShowAsAction(2);
        (this.doneItem = menu.add(0, 4, 3, 2131820781)).setIcon(2131231359);
        this.doneItem.setShowAsAction(2);
        (this.addItem = menu.add(0, 2, 2, 2131821071)).setShowAsAction(2);
        (this.searchItem = menu.add(0, 3, 1, 2131821344)).setIcon(2131231450);
        this.searchItem.setShowAsAction(2);
        (this.cloudHintItem = menu.add(0, 5, 4, (CharSequence)"")).setIcon(2131231360);
        this.cloudHintItem.setShowAsAction(2);
        super.onCreateOptionsMenu(menu, menuInflater);
    }
    
    public View onCreateView(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final Bundle bundle) {
        final View rootView = this.rootView;
        if (rootView != null) {
            final ViewGroup viewGroup2 = (ViewGroup)rootView.getParent();
            if (viewGroup2 != null) {
                viewGroup2.removeView(this.rootView);
            }
        }
        else {
            this.initView(this.rootView = layoutInflater.inflate(2131493054, (ViewGroup)null));
        }
        this.broadcastManager = LocalBroadcastManager.getInstance((Context)this.requireActivity());
        final IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("login_data_changed");
        intentFilter.addAction("syn_upload_start");
        intentFilter.addAction("syn_upload_finish");
        intentFilter.addAction("syn_download_start");
        intentFilter.addAction("syn_download_finish");
        this.broadcastManager.registerReceiver(this.receiver, intentFilter);
        return this.rootView;
    }
    
    public void onDestroy() {
        super.onDestroy();
        final LocalBroadcastManager broadcastManager = this.broadcastManager;
        if (broadcastManager != null) {
            final BroadcastReceiver receiver = this.receiver;
            if (receiver != null) {
                broadcastManager.unregisterReceiver(receiver);
            }
        }
    }
    
    public boolean onKeyDown() {
        boolean b;
        if (!this.localDiskFragment.isBatchMode && !this.cloudDiskFragment.isBatchMode) {
            b = false;
        }
        else {
            this.doneBatchOperate();
            b = true;
        }
        if (this.localDiskFragment.isBatchMode) {
            this.localDiskFragment.toBatchOperate(false);
        }
        if (this.cloudDiskFragment.isBatchMode) {
            this.cloudDiskFragment.toBatchOperate(false);
        }
        return b;
    }
    
    public boolean onOptionsItemSelected(final MenuItem menuItem) {
        if (this.viewpager.getCurrentItem() == 0) {
            this.onEditFragmentMenu(menuItem);
        }
        else {
            this.onCloudFragmentMenu(menuItem);
        }
        return super.onOptionsItemSelected(menuItem);
    }
    
    public void onPrepareOptionsMenu(final Menu menu) {
        super.onPrepareOptionsMenu(menu);
        this.doneItem.setVisible(false);
        this.cloudHintItem.setVisible(false);
        if (this.viewpager.getCurrentItem() == 1) {
            this.addItem.setIcon(ContextCompat.getDrawable(this.requireContext(), 2131231078));
            this.addItem.setEnabled(false);
            if (this.cloudDiskFragment.isBatchMode) {
                this.toBatchOperate();
            }
            this.cloudDiskFragment.showMenuItem();
        }
        else {
            this.addItem.setIcon(ContextCompat.getDrawable(this.requireContext(), 2131231542));
            this.addItem.setEnabled(true);
            if (this.localDiskFragment.isBatchMode) {
                this.toBatchOperate();
            }
            this.localDiskFragment.showMenuItem();
        }
    }
    
    public void onResume() {
        super.onResume();
        this.checkLogin();
    }
    
    public void toBatchOperate() {
        final MenuItem doneItem = this.doneItem;
        if (doneItem != null) {
            doneItem.setVisible(true);
        }
        final MenuItem searchItem = this.searchItem;
        if (searchItem != null) {
            searchItem.setVisible(false);
        }
        final MenuItem moreItem = this.moreItem;
        if (moreItem != null) {
            moreItem.setVisible(false);
        }
        final MenuItem addItem = this.addItem;
        if (addItem != null) {
            addItem.setVisible(false);
        }
        final MenuItem cloudHintItem = this.cloudHintItem;
        if (cloudHintItem != null) {
            cloudHintItem.setVisible(false);
        }
        if (this.viewpager.getCurrentItem() == 0) {
            this.localDiskFragment.toBatchOperate(true);
        }
        else {
            this.cloudDiskFragment.toBatchOperate(true);
        }
        this.viewpager.setScrollable(false);
    }
}

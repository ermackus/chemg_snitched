package com.kingagroot.kingdraw.ui;

import com.kingagroot.kingdraw.widget.TagGroup$OnTagClickListener;
import android.widget.SearchView$OnQueryTextListener;
import android.view.View$OnClickListener;
import com.kingagroot.kingdraw.interfaces.impl.SearchHisDBImpl;
import android.os.Bundle;
import android.content.DialogInterface;
import android.content.DialogInterface$OnClickListener;
import android.content.Context;
import androidx.appcompat.app.AlertDialog$Builder;
import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.Fragment;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import java.util.List;
import com.kingagroot.kingdraw.model.SearchHisModel;
import java.util.ArrayList;
import com.kingagroot.kingdraw.model.SearchTypeEnum;
import com.kingagroot.kingdraw.widget.TagGroup;
import com.kingagroot.kingdraw.interfaces.SearchHisDBI;
import com.kingagroot.kingdraw.widget.GSearchView;
import android.widget.LinearLayout;
import android.widget.ImageButton;
import com.goodsrc.ui.library.BaseActivity;

public class CloudSearchActivity extends BaseActivity
{
    private CloudSearchFragment cloudSearchFragment;
    private ImageButton imbtnBack;
    private ImageButton imgbtnClearHis;
    private LinearLayout llSearchHis;
    private GSearchView searchView;
    SearchHisDBI searchhisdbi;
    private TagGroup tagView;
    
    private void getSearchHisData() {
        final List list = this.searchhisdbi.getList(SearchTypeEnum.\u4e91\u7aef\u753b\u5e03\u641c\u7d22);
        final ArrayList tags = new ArrayList();
        boolean enabled = false;
        if (list != null) {
            for (int i = 0; i < list.size(); ++i) {
                ((List)tags).add((Object)((SearchHisModel)list.get(i)).getData());
            }
        }
        final ImageButton imgbtnClearHis = this.imgbtnClearHis;
        if (((List)tags).size() > 0) {
            enabled = true;
        }
        imgbtnClearHis.setEnabled(enabled);
        this.tagView.setTags((List)tags);
    }
    
    private void initView() {
        this.imbtnBack = (ImageButton)this.findViewById(2131296862);
        this.searchView = (GSearchView)this.findViewById(2131297361);
        this.tagView = (TagGroup)this.findViewById(2131297464);
        this.llSearchHis = (LinearLayout)this.findViewById(2131297027);
        this.imgbtnClearHis = (ImageButton)this.findViewById(2131296886);
        this.searchView.setQueryHint(this.getString(2131821347));
    }
    
    private void onSearch(final String s) {
        this.searchhisdbi.addHis(s, SearchTypeEnum.\u4e91\u7aef\u753b\u5e03\u641c\u7d22);
        this.llSearchHis.setVisibility(8);
        this.cloudSearchFragment.onSearchKey(s);
        final InputMethodManager inputMethodManager = (InputMethodManager)this.getSystemService("input_method");
        inputMethodManager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), 2);
        inputMethodManager.showSoftInput((View)this.searchView, 2);
    }
    
    private void setDefaultFragment() {
        final FragmentTransaction beginTransaction = this.getSupportFragmentManager().beginTransaction();
        beginTransaction.replace(2131296754, (Fragment)(this.cloudSearchFragment = new CloudSearchFragment()));
        beginTransaction.commit();
    }
    
    private void showClearDialog() {
        new AlertDialog$Builder((Context)this).setTitle(2131821524).setMessage(2131820697).setPositiveButton((CharSequence)this.getString(2131820588), (DialogInterface$OnClickListener)new _$$Lambda$CloudSearchActivity$UNkqKtfD4gbgKaNVxEuhOiCREaw(this)).setNegativeButton((CharSequence)this.getString(2131820661), (DialogInterface$OnClickListener)null).show();
    }
    
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.setContentView(2131492899);
        this.initView();
        this.searchhisdbi = (SearchHisDBI)new SearchHisDBImpl();
        this.imbtnBack.setOnClickListener((View$OnClickListener)new _$$Lambda$CloudSearchActivity$DmYgEEXTG80phhqkeLqmu3AL200(this));
        this.imgbtnClearHis.setOnClickListener((View$OnClickListener)new _$$Lambda$CloudSearchActivity$YVgRktIeExH_94jDjTLqBnc3btY(this));
        this.searchView.onSearch(true);
        this.searchView.setOnQueryTextListener((SearchView$OnQueryTextListener)new CloudSearchActivity$1(this));
        this.tagView.setOnTagClickListener((TagGroup$OnTagClickListener)new _$$Lambda$CloudSearchActivity$MeVyGyhkBTum6zxgbaUaMnqjZmM(this));
        this.setDefaultFragment();
        this.getSearchHisData();
    }
}

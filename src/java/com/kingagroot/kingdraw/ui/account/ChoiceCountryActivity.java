package com.kingagroot.kingdraw.ui.account;

import android.os.Bundle;
import java.io.Serializable;
import android.content.Intent;
import android.widget.AdapterView;
import java.util.Comparator;
import java.util.Collections;
import java.util.LinkedHashSet;
import com.kingagroot.kingdraw.utils.link.CountryLink$OnCountryFinishLister;
import com.kingagroot.kingdraw.utils.link.CountryLink;
import com.kingagroot.component.ui.db.impl.CountryDbiMpl;
import android.widget.ListAdapter;
import android.content.Context;
import com.kingagroot.kingdraw.widget.WaveSideBarView$OnTouchLetterChangeListener;
import android.widget.AdapterView$OnItemClickListener;
import android.widget.SearchView$OnQueryTextListener;
import android.view.View$OnClickListener;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import java.util.Collection;
import java.util.ArrayList;
import com.kingagroot.kingdraw.widget.WaveSideBarView;
import com.kingagroot.kingdraw.widget.GSearchView;
import android.widget.ListView;
import android.widget.ImageButton;
import com.kingagroot.component.ui.model.CountryModel;
import java.util.List;
import com.kingagroot.component.ui.db.CountryDbi;
import com.kingagroot.kingdraw.adapter.ChoiceCountryAdapter;
import com.goodsrc.ui.library.BaseActivity;

public class ChoiceCountryActivity extends BaseActivity
{
    private ChoiceCountryAdapter adapter;
    private CountryDbi countryDbi;
    private final List<CountryModel> countryModels;
    private ImageButton ibtBack;
    private ListView listArea;
    private GSearchView searchView;
    private WaveSideBarView sidebar;
    
    public ChoiceCountryActivity() {
        this.countryModels = (List<CountryModel>)new ArrayList();
    }
    
    private void getAreaData() {
        if (this.countryDbi != null) {
            this.countryModels.clear();
            if (this.countryDbi.getAllData() != null) {
                this.countryModels.addAll((Collection)this.countryDbi.getAllData());
            }
        }
        this.adapter.notifyDataSetChanged();
        this.sortData(this.countryModels);
    }
    
    private void getSearchData(final String s) {
        if (this.countryDbi != null) {
            this.countryModels.clear();
            this.countryModels.addAll((Collection)this.countryDbi.getCountryModels(s));
        }
        this.adapter.notifyDataSetChanged();
        this.sortData(this.countryModels);
        if (this.adapter.isEmpty()) {
            this.sidebar.setVisibility(8);
        }
        else {
            this.sidebar.setVisibility(0);
        }
    }
    
    private void hidKeyBoard() {
        final InputMethodManager inputMethodManager = (InputMethodManager)this.getSystemService("input_method");
        if (inputMethodManager != null) {
            inputMethodManager.hideSoftInputFromWindow(this.searchView.getWindowToken(), 2);
            inputMethodManager.showSoftInput((View)this.searchView, 2);
        }
    }
    
    private void initData() {
        this.searchView.setTitle(this.getString(2131821358));
        this.searchView.setQueryHint(this.getString(2131821359));
        this.ibtBack.setOnClickListener((View$OnClickListener)new _$$Lambda$ChoiceCountryActivity$1v04sicmnRbUVjgBWRA0xlm_KAQ(this));
        this.searchView.setOnQueryTextListener((SearchView$OnQueryTextListener)new ChoiceCountryActivity$2(this));
        this.listArea.setOnItemClickListener((AdapterView$OnItemClickListener)new _$$Lambda$ChoiceCountryActivity$aaoONOpYPO_1owSeq6XQVyfuVRw(this));
    }
    
    private void initView() {
        this.ibtBack = (ImageButton)this.findViewById(2131296800);
        this.searchView = (GSearchView)this.findViewById(2131297361);
        this.listArea = (ListView)this.findViewById(2131296972);
        (this.sidebar = (WaveSideBarView)this.findViewById(2131297375)).setOnTouchLetterChangeListener((WaveSideBarView$OnTouchLetterChangeListener)new _$$Lambda$ChoiceCountryActivity$ps4W5kJy7yMHilsfxwB3_RGkwkA(this));
        final ChoiceCountryAdapter choiceCountryAdapter = new ChoiceCountryAdapter((Context)this, (List)this.countryModels);
        this.adapter = choiceCountryAdapter;
        this.listArea.setAdapter((ListAdapter)choiceCountryAdapter);
        final CountryDbiMpl countryDbi = new CountryDbiMpl();
        this.countryDbi = (CountryDbi)countryDbi;
        if (((CountryDbi)countryDbi).CountryCount() == 0L) {
            new CountryLink((CountryLink$OnCountryFinishLister)new ChoiceCountryActivity$1(this)).getCountryList();
        }
        else {
            this.getAreaData();
        }
    }
    
    private void setSideBarLetter(final List<CountryModel> list) {
        final ArrayList letters = new ArrayList();
        if (list != null && !list.isEmpty()) {
            for (int i = 0; i < list.size(); ++i) {
                ((List)letters).add((Object)((CountryModel)list.get(i)).getPinYinFirstCode());
            }
        }
        final LinkedHashSet set = new LinkedHashSet((Collection)letters);
        ((List)letters).clear();
        ((List)letters).addAll((Collection)set);
        Collections.sort((List)letters, (Comparator)_$$Lambda$ChoiceCountryActivity$hK0kC0dO7RnVg8va5m4KCMNZ0zs.INSTANCE);
        this.sidebar.setLetters((List)letters);
    }
    
    private void sortData(final List<CountryModel> sideBarLetter) {
        Collections.sort((List)sideBarLetter, (Comparator)_$$Lambda$ChoiceCountryActivity$wmZ8DSqK1qbXb3UUIsSw6GmUJZ8.INSTANCE);
        this.setSideBarLetter(sideBarLetter);
    }
    
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.setContentView(2131492898);
        this.initView();
        this.initData();
    }
}

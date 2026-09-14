package com.kingagroot.kingdraw.ui.baike;

import android.view.ViewGroup$LayoutParams;
import android.content.res.ColorStateList;
import android.graphics.Color;
import com.google.android.material.chip.Chip;
import android.os.Bundle;
import android.content.DialogInterface$OnClickListener;
import android.content.Context;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import android.view.View;
import android.content.DialogInterface;
import android.widget.SearchView$OnQueryTextListener;
import android.text.TextUtils;
import com.kingagroot.kingdraw.ui.baike.dbi.SearchDbiMpl;
import com.kingagroot.kingdraw.ui.baike.model.SearchHistoryModel;
import java.util.List;
import java.util.Collections;
import android.widget.SearchView;
import com.kingagroot.kingdraw.ui.baike.dbi.SearchDbi;
import android.widget.ImageButton;
import com.google.android.material.chip.ChipGroup;
import android.widget.Button;
import android.view.View$OnClickListener;
import com.goodsrc.ui.library.BaseActivity;

public class SearchPediaActivity extends BaseActivity implements View$OnClickListener
{
    private Button btnCancelSearch;
    private ChipGroup chipGroupHistory;
    private ImageButton ibtCleanHistory;
    private SearchDbi searchDbi;
    private SearchView svPedia;
    
    private void initHistoryData() {
        final List allList = this.searchDbi.getAllList();
        if (allList != null && allList.size() > 1) {
            Collections.reverse(allList);
        }
        this.chipGroupHistory.removeAllViews();
        if (allList != null && allList.size() > 0) {
            Collections.reverse(allList);
            this.setTextHistory((List<SearchHistoryModel>)allList);
        }
    }
    
    private void initView() {
        this.svPedia = (SearchView)this.findViewById(2131297435);
        this.btnCancelSearch = (Button)this.findViewById(2131296415);
        this.ibtCleanHistory = (ImageButton)this.findViewById(2131296802);
        this.chipGroupHistory = (ChipGroup)this.findViewById(2131296522);
        this.searchDbi = (SearchDbi)new SearchDbiMpl();
        this.initHistoryData();
        this.btnCancelSearch.setOnClickListener((View$OnClickListener)this);
        this.ibtCleanHistory.setOnClickListener((View$OnClickListener)this);
        final String stringExtra = this.getIntent().getStringExtra("searchKey");
        if (!TextUtils.isEmpty((CharSequence)stringExtra)) {
            this.svPedia.setQuery((CharSequence)stringExtra, false);
        }
        this.svPedia.requestFocus();
        this.svPedia.setOnQueryTextListener((SearchView$OnQueryTextListener)new SearchPediaActivity$1(this));
    }
    
    public void onClick(final View view) {
        if (view == this.btnCancelSearch) {
            this.finish();
        }
        else if (view == this.ibtCleanHistory) {
            new MaterialAlertDialogBuilder((Context)this, 2131886086).setTitle(2131820936).setMessage(2131820694).setPositiveButton(2131820731, (DialogInterface$OnClickListener)new _$$Lambda$SearchPediaActivity$zAMaCrR_g6VjKvUAi9u5mQxxhu4(this)).setNegativeButton(2131820661, (DialogInterface$OnClickListener)null).show();
        }
    }
    
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.setContentView(2131492930);
        this.initView();
    }
    
    public void setTextHistory(final List<SearchHistoryModel> list) {
        for (int i = 0; i < list.size(); ++i) {
            final Chip chip = new Chip((Context)this);
            chip.setText((CharSequence)((SearchHistoryModel)list.get(i)).getSearchName());
            try {
                chip.setTextAppearance(2131886329);
            }
            catch (final NoSuchMethodError noSuchMethodError) {
                noSuchMethodError.printStackTrace();
            }
            chip.setTextColor(Color.parseColor("#DE000000"));
            chip.setChipStrokeWidth(1.0f);
            chip.setChipStrokeColor(ColorStateList.valueOf(Color.parseColor("#D9D9D9")));
            chip.setChipBackgroundColor(ColorStateList.valueOf(Color.parseColor("#FEFEFE")));
            chip.setOnClickListener((View$OnClickListener)new SearchPediaActivity$2(this));
            this.chipGroupHistory.addView((View)chip, new ViewGroup$LayoutParams(-2, -2));
        }
    }
}

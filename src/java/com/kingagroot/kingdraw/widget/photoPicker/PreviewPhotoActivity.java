package com.kingagroot.kingdraw.widget.photoPicker;

import android.view.Menu;
import android.os.Bundle;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View$OnClickListener;
import androidx.viewpager.widget.PagerAdapter;
import java.util.Collection;
import androidx.viewpager.widget.ViewPager;
import java.util.ArrayList;
import android.view.MenuItem;
import androidx.appcompat.widget.AppCompatCheckBox;
import android.widget.Button;
import androidx.viewpager.widget.ViewPager$OnPageChangeListener;
import com.goodsrc.ui.library.ToolBarActivity;

public class PreviewPhotoActivity extends ToolBarActivity implements ViewPager$OnPageChangeListener
{
    public static final String PATHS_URL = "intent_paths_url";
    private Button btnEdit;
    private AppCompatCheckBox cbOriginal;
    private AppCompatCheckBox cbSelected;
    private MenuItem confirmItem;
    private final ArrayList<String> pictures;
    private int position;
    private ViewPager viewpager;
    
    public PreviewPhotoActivity() {
        this.pictures = (ArrayList<String>)new ArrayList();
        this.position = 0;
    }
    
    private void initData() {
        final ArrayList stringArrayList = this.getIntent().getExtras().getStringArrayList("intent_paths_url");
        if (stringArrayList != null) {
            this.pictures.addAll((Collection)stringArrayList);
        }
        this.viewpager.setAdapter((PagerAdapter)new ImagePagerAdapter(this.getSupportFragmentManager(), (ArrayList)this.pictures, 0));
        this.viewpager.addOnPageChangeListener((ViewPager$OnPageChangeListener)this);
        this.viewpager.setCurrentItem(this.position);
    }
    
    private void initView() {
        this.viewpager = (ViewPager)this.findViewById(2131297746);
        this.btnEdit = (Button)this.findViewById(2131296426);
        this.cbOriginal = (AppCompatCheckBox)this.findViewById(2131296487);
        this.cbSelected = (AppCompatCheckBox)this.findViewById(2131296488);
        this.btnEdit.setOnClickListener((View$OnClickListener)new _$$Lambda$PreviewPhotoActivity$B8IJlFNnVgvObu9SBKOZaK20oJE(this));
    }
    
    private void setConfirmItemText() {
        final String string = this.getString(2131821183, new Object[] { this.position + 1, this.viewpager.getAdapter().getCount() });
        final MenuItem confirmItem = this.confirmItem;
        if (confirmItem != null) {
            confirmItem.setTitle((CharSequence)string);
        }
    }
    
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.setContentView(2131492926);
        this.setTitle(2131820583);
        this.initView();
        this.initData();
    }
    
    public boolean onCreateOptionsMenu(final Menu menu) {
        (this.confirmItem = menu.add(0, 0, 0, (CharSequence)"")).setShowAsAction(2);
        this.setConfirmItemText();
        return super.onCreateOptionsMenu(menu);
    }
    
    public void onPageScrollStateChanged(final int n) {
    }
    
    public void onPageScrolled(final int n, final float n2, final int n3) {
    }
    
    public void onPageSelected(final int position) {
        this.position = position;
        this.setConfirmItemText();
    }
}

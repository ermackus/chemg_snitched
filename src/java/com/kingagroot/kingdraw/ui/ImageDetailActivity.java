package com.kingagroot.kingdraw.ui;

import android.os.Bundle;
import androidx.viewpager.widget.ViewPager$OnPageChangeListener;
import java.util.Objects;
import androidx.viewpager.widget.PagerAdapter;
import com.kingagroot.kingdraw.widget.photoPicker.ImagePagerAdapter;
import android.text.TextUtils;
import java.util.Collection;
import androidx.viewpager.widget.ViewPager;
import android.widget.TextView;
import java.util.ArrayList;
import androidx.appcompat.app.AppCompatActivity;

public class ImageDetailActivity extends AppCompatActivity
{
    public static final String PATHS_URL = "intent_paths_url";
    public static final String PATH_URL = "intent_path_url";
    public static final String PATH_URL_TYPE = "PATH_URL_TYPE";
    public static final String PICTURE_INDEX = "intent_picture_index";
    private int pagerPosition;
    private final ArrayList<String> pictures;
    private TextView tvIndicator;
    private int urlType;
    private ViewPager viewpager;
    
    public ImageDetailActivity() {
        this.pictures = (ArrayList<String>)new ArrayList();
        this.pagerPosition = 0;
        this.urlType = 0;
    }
    
    private void initData() {
        final Bundle extras = this.getIntent().getExtras();
        if (extras != null) {
            this.pagerPosition = extras.getInt("intent_picture_index", 0);
            final ArrayList stringArrayList = extras.getStringArrayList("intent_paths_url");
            final String string = extras.getString("intent_path_url");
            if (stringArrayList != null) {
                this.pictures.addAll((Collection)stringArrayList);
            }
            if (!TextUtils.isEmpty((CharSequence)string)) {
                this.pictures.add((Object)string);
            }
            this.pagerPosition = extras.getInt("position");
            this.urlType = extras.getInt("PATH_URL_TYPE");
        }
        this.viewpager.setAdapter((PagerAdapter)new ImagePagerAdapter(this.getSupportFragmentManager(), (ArrayList)this.pictures, this.urlType));
        this.tvIndicator.setText((CharSequence)this.getString(2131821182, new Object[] { 1, ((PagerAdapter)Objects.requireNonNull((Object)this.viewpager.getAdapter())).getCount() }));
        this.viewpager.addOnPageChangeListener((ViewPager$OnPageChangeListener)new ImageDetailActivity$1(this));
        this.viewpager.setCurrentItem(this.pagerPosition);
    }
    
    private void initView() {
        this.tvIndicator = (TextView)this.findViewById(2131296897);
        this.viewpager = (ViewPager)this.findViewById(2131297746);
    }
    
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.setContentView(2131492913);
        this.initView();
        this.initData();
    }
}

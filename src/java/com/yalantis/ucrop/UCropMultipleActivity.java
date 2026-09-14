package com.yalantis.ucrop;

import android.view.MenuItem;
import android.graphics.drawable.Animatable;
import android.view.Menu;
import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.ActionBar;
import android.graphics.drawable.Drawable;
import androidx.core.graphics.BlendModeColorFilterCompat;
import androidx.core.graphics.BlendModeCompat;
import androidx.appcompat.content.res.AppCompatResources;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;
import android.view.Window;
import android.os.Build$VERSION;
import androidx.recyclerview.widget.RecyclerView$Adapter;
import android.view.animation.AnimationUtils;
import androidx.recyclerview.widget.RecyclerView$ItemDecoration;
import com.yalantis.ucrop.decoration.GridSpacingItemDecoration;
import com.yalantis.ucrop.util.DensityUtil;
import androidx.recyclerview.widget.RecyclerView$LayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.Iterator;
import java.util.Map$Entry;
import org.json.JSONArray;
import android.os.Parcelable;
import android.text.TextUtils;
import com.yalantis.ucrop.statusbar.ImmersiveManager;
import androidx.core.content.ContextCompat;
import android.widget.Toast;
import android.content.Intent;
import android.os.Environment;
import java.io.File;
import android.content.Context;
import android.net.Uri;
import com.yalantis.ucrop.util.FileUtils;
import android.os.Bundle;
import java.util.Collection;
import androidx.appcompat.app.AppCompatDelegate;
import org.json.JSONObject;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.HashSet;
import com.yalantis.ucrop.model.AspectRatio;
import java.util.ArrayList;
import androidx.appcompat.app.AppCompatActivity;

public class UCropMultipleActivity extends AppCompatActivity implements UCropFragmentCallback
{
    private ArrayList<AspectRatio> aspectRatioList;
    private int currentFragmentPosition;
    private final HashSet<String> filterSet;
    private final List<UCropFragment> fragments;
    private UCropGalleryAdapter galleryAdapter;
    private boolean isForbidCropGifWebp;
    private boolean isSkipCropForbid;
    private boolean mShowLoader;
    private int mStatusBarColor;
    private int mToolbarCancelDrawable;
    private int mToolbarColor;
    private int mToolbarCropDrawable;
    private String mToolbarTitle;
    private int mToolbarTitleSize;
    private int mToolbarWidgetColor;
    private String outputCropFileName;
    private UCropFragment uCropCurrentFragment;
    private ArrayList<String> uCropNotSupportList;
    private ArrayList<String> uCropSupportList;
    private final LinkedHashMap<String, JSONObject> uCropTotalQueue;
    
    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }
    
    public UCropMultipleActivity() {
        this.fragments = (List<UCropFragment>)new ArrayList();
        this.uCropTotalQueue = (LinkedHashMap<String, JSONObject>)new LinkedHashMap();
        this.filterSet = (HashSet<String>)new HashSet();
    }
    
    private int getCropSupportPosition() {
        final Bundle extras = this.getIntent().getExtras();
        final boolean b = false;
        if (extras == null) {
            return 0;
        }
        final ArrayList stringArrayList = extras.getStringArrayList("com.yalantis.ucrop.SkipCropMimeType");
        int n = b ? 1 : 0;
        if (stringArrayList != null) {
            n = (b ? 1 : 0);
            if (stringArrayList.size() > 0) {
                this.filterSet.addAll((Collection)stringArrayList);
                int n2 = 0;
                int n3 = -1;
                int n4;
                while (true) {
                    n4 = n3;
                    if (n2 >= this.uCropSupportList.size()) {
                        break;
                    }
                    final String pathToMimeType = this.getPathToMimeType((String)this.uCropSupportList.get(n2));
                    n4 = n3 + 1;
                    if (!this.filterSet.contains((Object)pathToMimeType)) {
                        break;
                    }
                    ++n2;
                    n3 = n4;
                }
                n = (b ? 1 : 0);
                if (n4 != -1) {
                    if (n4 > this.fragments.size()) {
                        n = (b ? 1 : 0);
                    }
                    else {
                        n = n4;
                    }
                }
            }
        }
        return n;
    }
    
    private String getPathToMimeType(String s) {
        if (FileUtils.isContent(s)) {
            s = FileUtils.getMimeTypeFromMediaContentUri((Context)this, Uri.parse(s));
        }
        else {
            s = FileUtils.getMimeTypeFromMediaContentUri((Context)this, Uri.fromFile(new File(s)));
        }
        return s;
    }
    
    private String getSandboxPathDir() {
        final String stringExtra = this.getIntent().getStringExtra("com.yalantis.ucrop.CropOutputDir");
        File file;
        if (stringExtra != null && !"".equals((Object)stringExtra)) {
            file = new File(stringExtra);
        }
        else {
            file = new File(this.getExternalFilesDir(Environment.DIRECTORY_PICTURES).getAbsolutePath(), "Sandbox");
        }
        if (!file.exists()) {
            file.mkdirs();
        }
        final StringBuilder sb = new StringBuilder();
        sb.append(file.getAbsolutePath());
        sb.append(File.separator);
        return sb.toString();
    }
    
    private void handleCropError(final Intent intent) {
        final Throwable error = UCrop.getError(intent);
        if (error != null) {
            Toast.makeText((Context)this, (CharSequence)error.getMessage(), 1).show();
        }
        else {
            Toast.makeText((Context)this, (CharSequence)"Unexpected error", 0).show();
        }
    }
    
    private void immersive() {
        final Intent intent = this.getIntent();
        final boolean booleanExtra = intent.getBooleanExtra("com.yalantis.ucrop.isDarkStatusBarBlack", false);
        final int intExtra = intent.getIntExtra("com.yalantis.ucrop.StatusBarColor", ContextCompat.getColor((Context)this, R$color.ucrop_color_statusbar));
        ImmersiveManager.immersiveAboveAPI23((AppCompatActivity)this, this.mStatusBarColor = intExtra, intExtra, booleanExtra);
    }
    
    private void initCropFragments(final Intent intent) {
        int i = 0;
        this.isSkipCropForbid = intent.getBooleanExtra("com.yalantis.ucrop.ForbidSkipCrop", false);
        final ArrayList stringArrayListExtra = intent.getStringArrayListExtra("com.yalantis.ucrop.CropTotalDataSource");
        if (stringArrayListExtra == null || stringArrayListExtra.size() <= 0) {
            throw new IllegalArgumentException("Missing required parameters, count cannot be less than 1");
        }
        this.uCropSupportList = (ArrayList<String>)new ArrayList();
        this.uCropNotSupportList = (ArrayList<String>)new ArrayList();
        while (i < stringArrayListExtra.size()) {
            final String s = (String)stringArrayListExtra.get(i);
            this.uCropTotalQueue.put((Object)s, (Object)new JSONObject());
            String path;
            if (FileUtils.isContent(s)) {
                path = FileUtils.getPath((Context)this, Uri.parse(s));
            }
            else {
                path = s;
            }
            final String pathToMimeType = this.getPathToMimeType(s);
            if (!FileUtils.isUrlHasVideo(path) && !FileUtils.isHasVideo(pathToMimeType) && !FileUtils.isHasAudio(pathToMimeType)) {
                this.uCropSupportList.add((Object)s);
                final Bundle extras = intent.getExtras();
                if (extras != null) {
                    Uri uri;
                    if (!FileUtils.isContent(s) && !FileUtils.isHasHttp(s)) {
                        uri = Uri.fromFile(new File(s));
                    }
                    else {
                        uri = Uri.parse(s);
                    }
                    final String postfixDefaultJPEG = FileUtils.getPostfixDefaultJPEG((Context)this, this.isForbidCropGifWebp, uri);
                    String s2;
                    if (TextUtils.isEmpty((CharSequence)this.outputCropFileName)) {
                        final StringBuilder sb = new StringBuilder();
                        final StringBuilder sb2 = new StringBuilder();
                        sb2.append("CROP_");
                        sb2.append(i + 1);
                        sb.append(FileUtils.getCreateFileName(sb2.toString()));
                        sb.append(postfixDefaultJPEG);
                        s2 = sb.toString();
                    }
                    else {
                        final StringBuilder sb3 = new StringBuilder();
                        sb3.append(i + 1);
                        sb3.append(FileUtils.getCreateFileName());
                        sb3.append("_");
                        sb3.append(this.outputCropFileName);
                        s2 = sb3.toString();
                    }
                    final Uri fromFile = Uri.fromFile(new File(this.getSandboxPathDir(), s2));
                    extras.putParcelable("com.yalantis.ucrop.InputUri", (Parcelable)uri);
                    extras.putParcelable("com.yalantis.ucrop.OutputUri", (Parcelable)fromFile);
                    final ArrayList<AspectRatio> aspectRatioList = this.aspectRatioList;
                    AspectRatio aspectRatio;
                    if (aspectRatioList != null && aspectRatioList.size() > i) {
                        aspectRatio = (AspectRatio)this.aspectRatioList.get(i);
                    }
                    else {
                        aspectRatio = null;
                    }
                    final float n = -1.0f;
                    float aspectRatioX;
                    if (aspectRatio != null) {
                        aspectRatioX = aspectRatio.getAspectRatioX();
                    }
                    else {
                        aspectRatioX = -1.0f;
                    }
                    extras.putFloat("com.yalantis.ucrop.AspectRatioX", aspectRatioX);
                    float aspectRatioY = n;
                    if (aspectRatio != null) {
                        aspectRatioY = aspectRatio.getAspectRatioY();
                    }
                    extras.putFloat("com.yalantis.ucrop.AspectRatioY", aspectRatioY);
                    this.fragments.add((Object)UCropFragment.newInstance(extras));
                }
            }
            else {
                this.uCropNotSupportList.add((Object)s);
            }
            ++i;
        }
        if (this.uCropSupportList.size() != 0) {
            this.setGalleryAdapter();
            this.switchCropFragment((UCropFragment)this.fragments.get(this.getCropSupportPosition()), this.getCropSupportPosition());
            this.galleryAdapter.setCurrentSelectPosition(this.getCropSupportPosition());
            return;
        }
        throw new IllegalArgumentException("No clipping data sources are available");
    }
    
    private void mergeCropResult(final Intent intent) {
        try {
            final String stringExtra = intent.getStringExtra("com.yalantis.ucrop.CropInputOriginal");
            final JSONObject jsonObject = (JSONObject)this.uCropTotalQueue.get((Object)stringExtra);
            final Uri output = UCrop.getOutput(intent);
            String path;
            if (output != null) {
                path = output.getPath();
            }
            else {
                path = "";
            }
            jsonObject.put("outPutPath", (Object)path);
            jsonObject.put("imageWidth", UCrop.getOutputImageWidth(intent));
            jsonObject.put("imageHeight", UCrop.getOutputImageHeight(intent));
            jsonObject.put("offsetX", UCrop.getOutputImageOffsetX(intent));
            jsonObject.put("offsetY", UCrop.getOutputImageOffsetY(intent));
            jsonObject.put("aspectRatio", (double)UCrop.getOutputCropAspectRatio(intent));
            this.uCropTotalQueue.put((Object)stringExtra, (Object)jsonObject);
        }
        catch (final Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private void onCropCompleteFinish() {
        final JSONArray jsonArray = new JSONArray();
        final Iterator iterator = this.uCropTotalQueue.entrySet().iterator();
        while (iterator.hasNext()) {
            jsonArray.put((Object)((Map$Entry)iterator.next()).getValue());
        }
        final Intent intent = new Intent();
        intent.putExtra("output", jsonArray.toString());
        this.setResult(-1, intent);
        this.finish();
    }
    
    private void setGalleryAdapter() {
        final RecyclerView recyclerView = (RecyclerView)this.findViewById(R$id.recycler_gallery);
        final LinearLayoutManager layoutManager = new LinearLayoutManager((Context)this);
        layoutManager.setOrientation(0);
        recyclerView.setLayoutManager((RecyclerView$LayoutManager)layoutManager);
        if (recyclerView.getItemDecorationCount() == 0) {
            recyclerView.addItemDecoration((RecyclerView$ItemDecoration)new GridSpacingItemDecoration(Integer.MAX_VALUE, DensityUtil.dip2px((Context)this, 6.0f), true));
        }
        recyclerView.setLayoutAnimation(AnimationUtils.loadLayoutAnimation((Context)this, R$anim.ucrop_layout_animation_fall_down));
        recyclerView.setBackgroundResource(this.getIntent().getIntExtra("com.yalantis.ucrop.GalleryBarBackground", R$drawable.ucrop_gallery_bg));
        (this.galleryAdapter = new UCropGalleryAdapter((List)this.uCropSupportList)).setOnItemClickListener((UCropGalleryAdapter$OnItemClickListener)new UCropMultipleActivity$1(this));
        recyclerView.setAdapter((RecyclerView$Adapter)this.galleryAdapter);
    }
    
    private void setStatusBarColor(final int statusBarColor) {
        if (Build$VERSION.SDK_INT >= 21) {
            final Window window = this.getWindow();
            if (window != null) {
                window.addFlags(Integer.MIN_VALUE);
                window.setStatusBarColor(statusBarColor);
            }
        }
    }
    
    private void setupAppBar() {
        this.setStatusBarColor(this.mStatusBarColor);
        final Toolbar supportActionBar = (Toolbar)this.findViewById(R$id.toolbar);
        supportActionBar.setBackgroundColor(this.mToolbarColor);
        supportActionBar.setTitleTextColor(this.mToolbarWidgetColor);
        final TextView textView = (TextView)supportActionBar.findViewById(R$id.toolbar_title);
        textView.setTextColor(this.mToolbarWidgetColor);
        textView.setText((CharSequence)this.mToolbarTitle);
        textView.setTextSize((float)this.mToolbarTitleSize);
        final Drawable mutate = AppCompatResources.getDrawable((Context)this, this.mToolbarCancelDrawable).mutate();
        mutate.setColorFilter(BlendModeColorFilterCompat.createBlendModeColorFilterCompat(this.mToolbarWidgetColor, BlendModeCompat.SRC_ATOP));
        supportActionBar.setNavigationIcon(mutate);
        this.setSupportActionBar(supportActionBar);
        final ActionBar supportActionBar2 = this.getSupportActionBar();
        if (supportActionBar2 != null) {
            supportActionBar2.setDisplayShowTitleEnabled(false);
        }
    }
    
    private void setupViews(final Intent intent) {
        this.aspectRatioList = (ArrayList<AspectRatio>)this.getIntent().getParcelableArrayListExtra("com.yalantis.ucrop.MultipleAspectRatio");
        this.isForbidCropGifWebp = intent.getBooleanExtra("com.yalantis.ucrop.ForbidCropGifWebp", false);
        this.outputCropFileName = intent.getStringExtra("com.yalantis.ucrop.CropOutputFileName");
        this.mStatusBarColor = intent.getIntExtra("com.yalantis.ucrop.StatusBarColor", ContextCompat.getColor((Context)this, R$color.ucrop_color_statusbar));
        this.mToolbarColor = intent.getIntExtra("com.yalantis.ucrop.ToolbarColor", ContextCompat.getColor((Context)this, R$color.ucrop_color_toolbar));
        this.mToolbarWidgetColor = intent.getIntExtra("com.yalantis.ucrop.UcropToolbarWidgetColor", ContextCompat.getColor((Context)this, R$color.ucrop_color_toolbar_widget));
        this.mToolbarCancelDrawable = intent.getIntExtra("com.yalantis.ucrop.UcropToolbarCancelDrawable", R$drawable.ucrop_ic_cross);
        this.mToolbarCropDrawable = intent.getIntExtra("com.yalantis.ucrop.UcropToolbarCropDrawable", R$drawable.ucrop_ic_done);
        this.mToolbarTitle = intent.getStringExtra("com.yalantis.ucrop.UcropToolbarTitleText");
        this.mToolbarTitleSize = intent.getIntExtra("com.yalantis.ucrop.UcropToolbarTitleTextSize", 18);
        String mToolbarTitle = this.mToolbarTitle;
        if (mToolbarTitle == null) {
            mToolbarTitle = this.getResources().getString(R$string.ucrop_label_edit_photo);
        }
        this.mToolbarTitle = mToolbarTitle;
        this.setupAppBar();
    }
    
    private void switchCropFragment(final UCropFragment uCropCurrentFragment, final int currentFragmentPosition) {
        final FragmentTransaction beginTransaction = this.getSupportFragmentManager().beginTransaction();
        if (!uCropCurrentFragment.isAdded()) {
            final UCropFragment uCropCurrentFragment2 = this.uCropCurrentFragment;
            if (uCropCurrentFragment2 != null) {
                beginTransaction.hide((Fragment)uCropCurrentFragment2);
            }
            final int fragment_container = R$id.fragment_container;
            final StringBuilder sb = new StringBuilder();
            sb.append(UCropFragment.TAG);
            sb.append("-");
            sb.append(currentFragmentPosition);
            beginTransaction.add(fragment_container, (Fragment)uCropCurrentFragment, sb.toString());
        }
        else {
            beginTransaction.hide((Fragment)this.uCropCurrentFragment).show((Fragment)uCropCurrentFragment);
            uCropCurrentFragment.fragmentReVisible();
        }
        this.currentFragmentPosition = currentFragmentPosition;
        this.uCropCurrentFragment = uCropCurrentFragment;
        beginTransaction.commitAllowingStateLoss();
    }
    
    public void loadingProgress(final boolean mShowLoader) {
        this.mShowLoader = mShowLoader;
        this.supportInvalidateOptionsMenu();
    }
    
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.immersive();
        this.setContentView(R$layout.ucrop_activity_multiple);
        this.setupViews(this.getIntent());
        this.initCropFragments(this.getIntent());
    }
    
    public boolean onCreateOptionsMenu(final Menu menu) {
        this.getMenuInflater().inflate(R$menu.ucrop_menu_activity, menu);
        final MenuItem item = menu.findItem(R$id.menu_loader);
        final Drawable icon = item.getIcon();
        if (icon != null) {
            try {
                icon.mutate();
                icon.setColorFilter(BlendModeColorFilterCompat.createBlendModeColorFilterCompat(this.mToolbarWidgetColor, BlendModeCompat.SRC_ATOP));
                item.setIcon(icon);
            }
            catch (final IllegalStateException ex) {
                ex.printStackTrace();
            }
            ((Animatable)item.getIcon()).start();
        }
        final MenuItem item2 = menu.findItem(R$id.menu_crop);
        final Drawable drawable = ContextCompat.getDrawable((Context)this, this.mToolbarCropDrawable);
        if (drawable != null) {
            drawable.mutate();
            drawable.setColorFilter(BlendModeColorFilterCompat.createBlendModeColorFilterCompat(this.mToolbarWidgetColor, BlendModeCompat.SRC_ATOP));
            item2.setIcon(drawable);
        }
        return true;
    }
    
    public void onCropFinish(final UCropFragment$UCropResult uCropFragment$UCropResult) {
        final int mResultCode = uCropFragment$UCropResult.mResultCode;
        if (mResultCode != -1) {
            if (mResultCode == 96) {
                this.handleCropError(uCropFragment$UCropResult.mResultData);
            }
        }
        else {
            final int currentFragmentPosition = this.currentFragmentPosition;
            final int size = this.uCropNotSupportList.size();
            final int size2 = this.uCropNotSupportList.size();
            final int size3 = this.uCropSupportList.size();
            boolean b = true;
            final int n = size2 + size3 - 1;
            this.mergeCropResult(uCropFragment$UCropResult.mResultData);
            if (currentFragmentPosition + size != n) {
                int currentSelectPosition = this.currentFragmentPosition + 1;
                String s = this.getPathToMimeType((String)this.uCropSupportList.get(currentSelectPosition));
                while (true) {
                    while (this.filterSet.contains((Object)s)) {
                        if (currentSelectPosition == n) {
                            if (b) {
                                this.onCropCompleteFinish();
                                return;
                            }
                            this.switchCropFragment((UCropFragment)this.fragments.get(currentSelectPosition), currentSelectPosition);
                            final UCropGalleryAdapter galleryAdapter = this.galleryAdapter;
                            galleryAdapter.notifyItemChanged(galleryAdapter.getCurrentSelectPosition());
                            this.galleryAdapter.setCurrentSelectPosition(currentSelectPosition);
                            final UCropGalleryAdapter galleryAdapter2 = this.galleryAdapter;
                            galleryAdapter2.notifyItemChanged(galleryAdapter2.getCurrentSelectPosition());
                            return;
                        }
                        else {
                            ++currentSelectPosition;
                            s = this.getPathToMimeType((String)this.uCropSupportList.get(currentSelectPosition));
                        }
                    }
                    b = false;
                    continue;
                }
            }
            this.onCropCompleteFinish();
        }
    }
    
    protected void onDestroy() {
        UCropDevelopConfig.destroy();
        super.onDestroy();
    }
    
    public boolean onOptionsItemSelected(final MenuItem menuItem) {
        if (menuItem.getItemId() == R$id.menu_crop) {
            final UCropFragment uCropCurrentFragment = this.uCropCurrentFragment;
            if (uCropCurrentFragment != null && uCropCurrentFragment.isAdded()) {
                this.uCropCurrentFragment.cropAndSaveImage();
            }
        }
        else if (menuItem.getItemId() == 16908332) {
            this.onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }
    
    public boolean onPrepareOptionsMenu(final Menu menu) {
        menu.findItem(R$id.menu_crop).setVisible(this.mShowLoader ^ true);
        menu.findItem(R$id.menu_loader).setVisible(this.mShowLoader);
        return super.onPrepareOptionsMenu(menu);
    }
}

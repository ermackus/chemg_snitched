package com.luck.picture.lib.basic;

import com.luck.picture.lib.entity.LocalMedia;
import java.util.ArrayList;
import android.content.Intent;
import androidx.fragment.app.FragmentActivity;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import androidx.fragment.app.Fragment;
import android.app.Activity;
import java.lang.ref.SoftReference;

public final class PictureSelector
{
    private final SoftReference<Activity> mActivity;
    private final SoftReference<Fragment> mFragment;
    
    private PictureSelector(final Activity activity) {
        this(activity, null);
    }
    
    private PictureSelector(final Activity activity, final Fragment fragment) {
        this.mActivity = (SoftReference<Activity>)new SoftReference((Object)activity);
        this.mFragment = (SoftReference<Fragment>)new SoftReference((Object)fragment);
    }
    
    private PictureSelector(final Fragment fragment) {
        this((Activity)fragment.getActivity(), fragment);
    }
    
    public static PictureSelector create(final Context context) {
        return new PictureSelector((Activity)context);
    }
    
    public static PictureSelector create(final AppCompatActivity appCompatActivity) {
        return new PictureSelector((Activity)appCompatActivity);
    }
    
    public static PictureSelector create(final Fragment fragment) {
        return new PictureSelector(fragment);
    }
    
    public static PictureSelector create(final FragmentActivity fragmentActivity) {
        return new PictureSelector((Activity)fragmentActivity);
    }
    
    public static ArrayList<LocalMedia> obtainSelectorList(final Intent intent) {
        if (intent == null) {
            return (ArrayList<LocalMedia>)new ArrayList();
        }
        ArrayList parcelableArrayListExtra = intent.getParcelableArrayListExtra("extra_result_media");
        if (parcelableArrayListExtra == null) {
            parcelableArrayListExtra = new ArrayList();
        }
        return (ArrayList<LocalMedia>)parcelableArrayListExtra;
    }
    
    public static Intent putIntentResult(final ArrayList<LocalMedia> list) {
        return new Intent().putParcelableArrayListExtra("extra_result_media", (ArrayList)list);
    }
    
    public PictureSelectionQueryModel dataSource(final int n) {
        return new PictureSelectionQueryModel(this, n);
    }
    
    Activity getActivity() {
        return (Activity)this.mActivity.get();
    }
    
    Fragment getFragment() {
        final SoftReference<Fragment> mFragment = this.mFragment;
        Fragment fragment;
        if (mFragment != null) {
            fragment = (Fragment)mFragment.get();
        }
        else {
            fragment = null;
        }
        return fragment;
    }
    
    public PictureSelectionCameraModel openCamera(final int n) {
        return new PictureSelectionCameraModel(this, n);
    }
    
    public PictureSelectionModel openGallery(final int n) {
        return new PictureSelectionModel(this, n);
    }
    
    public PictureSelectionPreviewModel openPreview() {
        return new PictureSelectionPreviewModel(this);
    }
    
    public PictureSelectionSystemModel openSystemGallery(final int n) {
        return new PictureSelectionSystemModel(this, n);
    }
}

package com.lzf.easyfloat.permission;

import kotlin.jvm.internal.Intrinsics;
import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.content.Intent;
import com.lzf.easyfloat.utils.Logger;
import android.os.Bundle;
import android.view.View;
import kotlin.jvm.internal.DefaultConstructorMarker;
import java.util.HashMap;
import com.lzf.easyfloat.interfaces.OnPermissionResult;
import kotlin.Metadata;
import android.app.Fragment;

@Metadata(bv = { 1, 0, 3 }, d1 = { "\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u0000 \r2\u00020\u0001:\u0001\rB\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0016J\"\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\t2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0016¨\u0006\u000e" }, d2 = { "Lcom/lzf/easyfloat/permission/PermissionFragment;", "Landroid/app/Fragment;", "()V", "onActivityCreated", "", "savedInstanceState", "Landroid/os/Bundle;", "onActivityResult", "requestCode", "", "resultCode", "data", "Landroid/content/Intent;", "Companion", "easyfloat_release" }, k = 1, mv = { 1, 4, 1 })
public final class PermissionFragment extends Fragment
{
    public static final Companion Companion;
    private static OnPermissionResult onPermissionResult;
    private HashMap _$_findViewCache;
    
    static {
        Companion = new Companion(null);
    }
    
    public static final /* synthetic */ void access$setOnPermissionResult$cp(final OnPermissionResult onPermissionResult) {
        PermissionFragment.onPermissionResult = onPermissionResult;
    }
    
    public void _$_clearFindViewByIdCache() {
        final HashMap $_findViewCache = this._$_findViewCache;
        if ($_findViewCache != null) {
            $_findViewCache.clear();
        }
    }
    
    public View _$_findCachedViewById(final int n) {
        if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
        }
        View viewById;
        if ((viewById = (View)this._$_findViewCache.get((Object)n)) == null) {
            final View view = this.getView();
            if (view == null) {
                return null;
            }
            viewById = view.findViewById(n);
            this._$_findViewCache.put((Object)n, (Object)viewById);
        }
        return viewById;
    }
    
    public void onActivityCreated(final Bundle bundle) {
        super.onActivityCreated(bundle);
        PermissionUtils.INSTANCE.requestPermission$easyfloat_release(this);
        Logger.INSTANCE.i("PermissionFragment\uff1arequestPermission");
    }
    
    public void onActivityResult(final int n, final int n2, final Intent intent) {
        if (n == 199) {
            new Handler(Looper.getMainLooper()).postDelayed((Runnable)new PermissionFragment$onActivityResult.PermissionFragment$onActivityResult$1(this), 500L);
        }
    }
    
    @Metadata(bv = { 1, 0, 3 }, d1 = { "\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\u0004R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\t" }, d2 = { "Lcom/lzf/easyfloat/permission/PermissionFragment$Companion;", "", "()V", "onPermissionResult", "Lcom/lzf/easyfloat/interfaces/OnPermissionResult;", "requestPermission", "", "activity", "Landroid/app/Activity;", "easyfloat_release" }, k = 1, mv = { 1, 4, 1 })
    public static final class Companion
    {
        private Companion() {
        }
        
        public final void requestPermission(final Activity activity, final OnPermissionResult onPermissionResult) {
            Intrinsics.checkNotNullParameter((Object)activity, "activity");
            Intrinsics.checkNotNullParameter((Object)onPermissionResult, "onPermissionResult");
            PermissionFragment.access$setOnPermissionResult$cp(onPermissionResult);
            activity.getFragmentManager().beginTransaction().add((Fragment)new PermissionFragment(), activity.getLocalClassName()).commitAllowingStateLoss();
        }
    }
}

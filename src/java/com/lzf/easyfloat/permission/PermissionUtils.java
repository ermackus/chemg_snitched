package com.lzf.easyfloat.permission;

import com.lzf.easyfloat.interfaces.OnPermissionResult;
import com.lzf.easyfloat.permission.rom.QikuUtils;
import com.lzf.easyfloat.permission.rom.OppoUtils;
import com.lzf.easyfloat.permission.rom.MiuiUtils;
import com.lzf.easyfloat.permission.rom.HuaweiUtils;
import android.app.Activity;
import android.net.Uri;
import android.content.Intent;
import android.provider.Settings;
import android.util.Log;
import com.lzf.easyfloat.utils.Logger;
import com.lzf.easyfloat.permission.rom.MeizuUtils;
import android.app.Fragment;
import kotlin.jvm.JvmStatic;
import com.lzf.easyfloat.permission.rom.RomUtils;
import android.os.Build$VERSION;
import kotlin.jvm.internal.Intrinsics;
import android.content.Context;
import kotlin.Metadata;

@Metadata(bv = { 1, 0, 3 }, d1 = { "\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0007J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0002J\u0010\u0010\u000f\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0007J\u0010\u0010\u0010\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0002J\u0010\u0010\u0011\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0002J\u0010\u0010\u0012\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0002J\u0010\u0010\u0013\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0002J\u0010\u0010\u0014\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0002J\u0010\u0010\u0015\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0002J\u0018\u0010\u0016\u001a\u00020\f2\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001aH\u0007J\u0015\u0010\u0016\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0000¢\u0006\u0002\b\u001bR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0080T¢\u0006\u0002\n\u0000¨\u0006\u001c" }, d2 = { "Lcom/lzf/easyfloat/permission/PermissionUtils;", "", "()V", "TAG", "", "requestCode", "", "checkPermission", "", "context", "Landroid/content/Context;", "commonROMPermissionApply", "", "fragment", "Landroid/app/Fragment;", "commonROMPermissionApplyInternal", "commonROMPermissionCheck", "huaweiPermissionCheck", "meizuPermissionCheck", "miuiPermissionCheck", "oppoROMPermissionCheck", "qikuPermissionCheck", "requestPermission", "activity", "Landroid/app/Activity;", "onPermissionResult", "Lcom/lzf/easyfloat/interfaces/OnPermissionResult;", "requestPermission$easyfloat_release", "easyfloat_release" }, k = 1, mv = { 1, 4, 1 })
public final class PermissionUtils
{
    public static final PermissionUtils INSTANCE;
    private static final String TAG = "PermissionUtils--->";
    public static final int requestCode = 199;
    
    static {
        INSTANCE = new PermissionUtils();
    }
    
    private PermissionUtils() {
    }
    
    @JvmStatic
    public static final boolean checkPermission(final Context context) {
        Intrinsics.checkNotNullParameter((Object)context, "context");
        boolean b;
        if (Build$VERSION.SDK_INT < 23) {
            if (RomUtils.INSTANCE.checkIsHuaweiRom()) {
                b = PermissionUtils.INSTANCE.huaweiPermissionCheck(context);
            }
            else if (RomUtils.INSTANCE.checkIsMiuiRom()) {
                b = PermissionUtils.INSTANCE.miuiPermissionCheck(context);
            }
            else if (RomUtils.INSTANCE.checkIsOppoRom()) {
                b = PermissionUtils.INSTANCE.oppoROMPermissionCheck(context);
            }
            else if (RomUtils.INSTANCE.checkIsMeizuRom()) {
                b = PermissionUtils.INSTANCE.meizuPermissionCheck(context);
            }
            else {
                b = (!RomUtils.INSTANCE.checkIs360Rom() || PermissionUtils.INSTANCE.qikuPermissionCheck(context));
            }
        }
        else {
            b = PermissionUtils.INSTANCE.commonROMPermissionCheck(context);
        }
        return b;
    }
    
    private final void commonROMPermissionApply(final Fragment fragment) {
        if (RomUtils.INSTANCE.checkIsMeizuRom()) {
            MeizuUtils.applyPermission(fragment);
        }
        else if (Build$VERSION.SDK_INT >= 23) {
            try {
                commonROMPermissionApplyInternal(fragment);
            }
            catch (final Exception ex) {
                final Logger instance = Logger.INSTANCE;
                final String stackTraceString = Log.getStackTraceString((Throwable)ex);
                Intrinsics.checkNotNullExpressionValue((Object)stackTraceString, "Log.getStackTraceString(e)");
                instance.e("PermissionUtils--->", stackTraceString);
            }
        }
        else {
            Logger.INSTANCE.d("PermissionUtils--->", "user manually refuse OVERLAY_PERMISSION");
        }
    }
    
    @JvmStatic
    public static final void commonROMPermissionApplyInternal(final Fragment fragment) {
        Intrinsics.checkNotNullParameter((Object)fragment, "fragment");
        try {
            final Intent intent = new Intent(Settings.class.getDeclaredField("ACTION_MANAGE_OVERLAY_PERMISSION").get((Object)null).toString());
            final StringBuilder sb = new StringBuilder();
            sb.append("package:");
            final Activity activity = fragment.getActivity();
            Intrinsics.checkNotNullExpressionValue((Object)activity, "fragment.activity");
            sb.append(activity.getPackageName());
            intent.setData(Uri.parse(sb.toString()));
            fragment.startActivityForResult(intent, 199);
        }
        catch (final Exception ex) {
            Logger.INSTANCE.e("PermissionUtils--->", String.valueOf((Object)ex));
        }
    }
    
    private final boolean commonROMPermissionCheck(final Context context) {
        boolean b;
        if (RomUtils.INSTANCE.checkIsMeizuRom()) {
            b = this.meizuPermissionCheck(context);
        }
        else {
            if (Build$VERSION.SDK_INT >= 23) {
                try {
                    final Object invoke = Settings.class.getDeclaredMethod("canDrawOverlays", Context.class).invoke((Object)null, new Object[] { context });
                    if (invoke != null) {
                        b = (boolean)invoke;
                        return b;
                    }
                    throw new NullPointerException("null cannot be cast to non-null type kotlin.Boolean");
                }
                catch (final Exception ex) {
                    Log.e("PermissionUtils--->", Log.getStackTraceString((Throwable)ex));
                }
            }
            b = true;
        }
        return b;
    }
    
    private final boolean huaweiPermissionCheck(final Context context) {
        return HuaweiUtils.checkFloatWindowPermission(context);
    }
    
    private final boolean meizuPermissionCheck(final Context context) {
        return MeizuUtils.checkFloatWindowPermission(context);
    }
    
    private final boolean miuiPermissionCheck(final Context context) {
        return MiuiUtils.checkFloatWindowPermission(context);
    }
    
    private final boolean oppoROMPermissionCheck(final Context context) {
        return OppoUtils.checkFloatWindowPermission(context);
    }
    
    private final boolean qikuPermissionCheck(final Context context) {
        return QikuUtils.checkFloatWindowPermission(context);
    }
    
    @JvmStatic
    public static final void requestPermission(final Activity activity, final OnPermissionResult onPermissionResult) {
        Intrinsics.checkNotNullParameter((Object)activity, "activity");
        Intrinsics.checkNotNullParameter((Object)onPermissionResult, "onPermissionResult");
        PermissionFragment.Companion.requestPermission(activity, onPermissionResult);
    }
    
    public final void requestPermission$easyfloat_release(final Fragment fragment) {
        Intrinsics.checkNotNullParameter((Object)fragment, "fragment");
        if (Build$VERSION.SDK_INT < 23) {
            if (RomUtils.INSTANCE.checkIsHuaweiRom()) {
                HuaweiUtils.applyPermission(fragment);
            }
            else if (RomUtils.INSTANCE.checkIsMiuiRom()) {
                MiuiUtils.applyMiuiPermission(fragment);
            }
            else if (RomUtils.INSTANCE.checkIsOppoRom()) {
                OppoUtils.applyOppoPermission(fragment);
            }
            else if (RomUtils.INSTANCE.checkIsMeizuRom()) {
                MeizuUtils.applyPermission(fragment);
            }
            else if (RomUtils.INSTANCE.checkIs360Rom()) {
                QikuUtils.applyPermission(fragment);
            }
            else {
                Logger.INSTANCE.i("PermissionUtils--->", "\u539f\u751f Android 6.0 \u4ee5\u4e0b\u65e0\u9700\u6743\u9650\u7533\u8bf7");
            }
        }
        else {
            this.commonROMPermissionApply(fragment);
        }
    }
}

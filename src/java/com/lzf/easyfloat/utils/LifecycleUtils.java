package com.lzf.easyfloat.utils;

import android.app.Application$ActivityLifecycleCallbacks;
import kotlin.Unit;
import android.content.ComponentName;
import java.util.Set;
import com.lzf.easyfloat.data.FloatConfig;
import android.view.View;
import android.view.Window;
import android.os.IBinder;
import java.util.Iterator;
import com.lzf.easyfloat.enums.ShowPattern;
import kotlin.jvm.internal.Intrinsics;
import com.lzf.easyfloat.core.FloatingWindowHelper;
import java.util.Map$Entry;
import com.lzf.easyfloat.core.FloatingWindowManager;
import java.util.Map;
import android.app.Activity;
import java.lang.ref.WeakReference;
import android.app.Application;
import kotlin.Metadata;

@Metadata(bv = { 1, 0, 3 }, d1 = { "\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u00c0\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\rH\u0002J\u0010\u0010\u0011\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\rH\u0002J\b\u0010\u0012\u001a\u0004\u0018\u00010\rJ\u0006\u0010\u0013\u001a\u00020\u0014J\u000e\u0010\u0015\u001a\u00020\u000f2\u0006\u0010\u0005\u001a\u00020\u0006J#\u0010\u0016\u001a\u0004\u0018\u00010\u000f2\b\b\u0002\u0010\u0017\u001a\u00020\u00142\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0002¢\u0006\u0002\u0010\u001aR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u0016\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001b" }, d2 = { "Lcom/lzf/easyfloat/utils/LifecycleUtils;", "", "()V", "activityCount", "", "application", "Landroid/app/Application;", "getApplication", "()Landroid/app/Application;", "setApplication", "(Landroid/app/Application;)V", "mTopActivity", "Ljava/lang/ref/WeakReference;", "Landroid/app/Activity;", "checkHide", "", "activity", "checkShow", "getTopActivity", "isForeground", "", "setLifecycleCallbacks", "setVisible", "isShow", "tag", "", "(ZLjava/lang/String;)Lkotlin/Unit;", "easyfloat_release" }, k = 1, mv = { 1, 4, 1 })
public final class LifecycleUtils
{
    public static final LifecycleUtils INSTANCE;
    private static int activityCount;
    public static Application application;
    private static WeakReference<Activity> mTopActivity;
    
    static {
        INSTANCE = new LifecycleUtils();
    }
    
    private LifecycleUtils() {
    }
    
    private final void checkHide(final Activity activity) {
        if (!activity.isFinishing() && this.isForeground()) {
            return;
        }
        for (final Map$Entry map$Entry : ((Map)FloatingWindowManager.INSTANCE.getWindowMap()).entrySet()) {
            final String s = (String)map$Entry.getKey();
            final FloatingWindowHelper floatingWindowHelper = (FloatingWindowHelper)map$Entry.getValue();
            final boolean finishing = activity.isFinishing();
            boolean b = true;
            if (finishing) {
                final IBinder token = floatingWindowHelper.getParams().token;
                if (token != null) {
                    final Window window = activity.getWindow();
                    IBinder windowToken = null;
                    Label_0144: {
                        if (window != null) {
                            final View decorView = window.getDecorView();
                            if (decorView != null) {
                                windowToken = decorView.getWindowToken();
                                break Label_0144;
                            }
                        }
                        windowToken = null;
                    }
                    if (Intrinsics.areEqual((Object)token, (Object)windowToken)) {
                        FloatingWindowManager.INSTANCE.dismiss(s, true);
                    }
                }
            }
            final FloatConfig config = floatingWindowHelper.getConfig();
            if (!LifecycleUtils.INSTANCE.isForeground() && floatingWindowHelper.getConfig().getShowPattern() != ShowPattern.CURRENT_ACTIVITY) {
                final LifecycleUtils instance = LifecycleUtils.INSTANCE;
                if (config.getShowPattern() == ShowPattern.FOREGROUND || !config.getNeedShow$easyfloat_release()) {
                    b = false;
                }
                instance.setVisible(b, s);
            }
        }
    }
    
    private final void checkShow(final Activity activity) {
        for (final Map$Entry map$Entry : ((Map)FloatingWindowManager.INSTANCE.getWindowMap()).entrySet()) {
            final String s = (String)map$Entry.getKey();
            final FloatConfig config = ((FloatingWindowHelper)map$Entry.getValue()).getConfig();
            if (config.getShowPattern() == ShowPattern.CURRENT_ACTIVITY) {
                continue;
            }
            if (config.getShowPattern() == ShowPattern.BACKGROUND) {
                LifecycleUtils.INSTANCE.setVisible(false, s);
            }
            else {
                if (!config.getNeedShow$easyfloat_release()) {
                    continue;
                }
                final LifecycleUtils instance = LifecycleUtils.INSTANCE;
                final Set<String> filterSet = config.getFilterSet();
                final ComponentName componentName = activity.getComponentName();
                Intrinsics.checkNotNullExpressionValue((Object)componentName, "activity.componentName");
                instance.setVisible(filterSet.contains((Object)componentName.getClassName()) ^ true, s);
            }
        }
    }
    
    private final Unit setVisible(final boolean b, final String s) {
        return FloatingWindowManager.visible$default(FloatingWindowManager.INSTANCE, b, s, false, 4, null);
    }
    
    public final Application getApplication() {
        final Application application = LifecycleUtils.application;
        if (application == null) {
            Intrinsics.throwUninitializedPropertyAccessException("application");
        }
        return application;
    }
    
    public final Activity getTopActivity() {
        final WeakReference<Activity> mTopActivity = LifecycleUtils.mTopActivity;
        Activity activity;
        if (mTopActivity != null) {
            activity = (Activity)mTopActivity.get();
        }
        else {
            activity = null;
        }
        return activity;
    }
    
    public final boolean isForeground() {
        return LifecycleUtils.activityCount > 0;
    }
    
    public final void setApplication(final Application application) {
        Intrinsics.checkNotNullParameter((Object)application, "<set-?>");
        LifecycleUtils.application = application;
    }
    
    public final void setLifecycleCallbacks(final Application application) {
        Intrinsics.checkNotNullParameter((Object)application, "application");
        (LifecycleUtils.application = application).registerActivityLifecycleCallbacks((Application$ActivityLifecycleCallbacks)new LifecycleUtils$setLifecycleCallbacks.LifecycleUtils$setLifecycleCallbacks$1());
    }
}

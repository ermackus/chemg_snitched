package com.lzf.easyfloat.core;

import kotlin.jvm.functions.Function3;
import com.lzf.easyfloat.interfaces.FloatCallbacks;
import com.lzf.easyfloat.interfaces.OnFloatCallbacks;
import com.lzf.easyfloat.utils.Logger;
import android.view.View;
import android.content.Context;
import java.util.Map;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import com.lzf.easyfloat.data.FloatConfig;
import java.util.concurrent.ConcurrentHashMap;
import kotlin.Metadata;

@Metadata(bv = { 1, 0, 3 }, d1 = { "\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\b\u00c0\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0002J\u0016\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\f\u001a\u00020\rJ#\u0010\u0012\u001a\u0004\u0018\u00010\u000f2\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00042\b\b\u0002\u0010\u0014\u001a\u00020\u000b¢\u0006\u0002\u0010\u0015J\u0012\u0010\u0016\u001a\u0004\u0018\u00010\u00072\b\u0010\u0013\u001a\u0004\u0018\u00010\u0004J\u0012\u0010\u0017\u001a\u00020\u00042\b\u0010\u0013\u001a\u0004\u0018\u00010\u0004H\u0002J\u0012\u0010\u0018\u001a\u0004\u0018\u00010\u00072\b\u0010\u0019\u001a\u0004\u0018\u00010\u0004J+\u0010\u001a\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u001b\u001a\u00020\u000b2\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00042\b\b\u0002\u0010\u001c\u001a\u00020\u000b¢\u0006\u0002\u0010\u001dR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u001d\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\u001e" }, d2 = { "Lcom/lzf/easyfloat/core/FloatingWindowManager;", "", "()V", "DEFAULT_TAG", "", "windowMap", "Ljava/util/concurrent/ConcurrentHashMap;", "Lcom/lzf/easyfloat/core/FloatingWindowHelper;", "getWindowMap", "()Ljava/util/concurrent/ConcurrentHashMap;", "checkTag", "", "config", "Lcom/lzf/easyfloat/data/FloatConfig;", "create", "", "context", "Landroid/content/Context;", "dismiss", "tag", "force", "(Ljava/lang/String;Z)Lkotlin/Unit;", "getHelper", "getTag", "remove", "floatTag", "visible", "isShow", "needShow", "(ZLjava/lang/String;Z)Lkotlin/Unit;", "easyfloat_release" }, k = 1, mv = { 1, 4, 1 })
public final class FloatingWindowManager
{
    private static final String DEFAULT_TAG = "default";
    public static final FloatingWindowManager INSTANCE;
    private static final ConcurrentHashMap<String, FloatingWindowHelper> windowMap;
    
    static {
        INSTANCE = new FloatingWindowManager();
        windowMap = new ConcurrentHashMap();
    }
    
    private FloatingWindowManager() {
    }
    
    private final boolean checkTag(final FloatConfig floatConfig) {
        floatConfig.setFloatTag(this.getTag(floatConfig.getFloatTag()));
        final ConcurrentHashMap<String, FloatingWindowHelper> windowMap = FloatingWindowManager.windowMap;
        final String floatTag = floatConfig.getFloatTag();
        Intrinsics.checkNotNull((Object)floatTag);
        return windowMap.containsKey((Object)floatTag);
    }
    
    private final String getTag(String s) {
        if (s == null) {
            s = "default";
        }
        return s;
    }
    
    public final void create(final Context context, final FloatConfig floatConfig) {
        Intrinsics.checkNotNullParameter((Object)context, "context");
        Intrinsics.checkNotNullParameter((Object)floatConfig, "config");
        if (!this.checkTag(floatConfig)) {
            final FloatingWindowHelper floatingWindowHelper = new FloatingWindowHelper(context, floatConfig);
            if (floatingWindowHelper.createWindow()) {
                final Map map = (Map)FloatingWindowManager.windowMap;
                final String floatTag = floatConfig.getFloatTag();
                Intrinsics.checkNotNull((Object)floatTag);
                map.put((Object)floatTag, (Object)floatingWindowHelper);
            }
        }
        else {
            final OnFloatCallbacks callbacks = floatConfig.getCallbacks();
            if (callbacks != null) {
                callbacks.createdResult(false, "Tag exception. You need to set different EasyFloat tag.", null);
            }
            final FloatCallbacks floatCallbacks = floatConfig.getFloatCallbacks();
            if (floatCallbacks != null) {
                final FloatCallbacks.Builder builder = floatCallbacks.getBuilder();
                if (builder != null) {
                    final Function3<Boolean, String, View, Unit> createdResult$easyfloat_release = builder.getCreatedResult$easyfloat_release();
                    if (createdResult$easyfloat_release != null) {
                        final Unit unit = (Unit)createdResult$easyfloat_release.invoke((Object)false, (Object)"Tag exception. You need to set different EasyFloat tag.", (Object)null);
                    }
                }
            }
            Logger.INSTANCE.w("Tag exception. You need to set different EasyFloat tag.");
        }
    }
    
    public final Unit dismiss(final String s, final boolean b) {
        final FloatingWindowHelper helper = this.getHelper(s);
        Unit instance;
        if (helper != null) {
            if (b) {
                helper.remove(b);
            }
            else {
                helper.exitAnim();
            }
            instance = Unit.INSTANCE;
        }
        else {
            instance = null;
        }
        return instance;
    }
    
    public final FloatingWindowHelper getHelper(final String s) {
        return (FloatingWindowHelper)FloatingWindowManager.windowMap.get((Object)this.getTag(s));
    }
    
    public final ConcurrentHashMap<String, FloatingWindowHelper> getWindowMap() {
        return FloatingWindowManager.windowMap;
    }
    
    public final FloatingWindowHelper remove(final String s) {
        return (FloatingWindowHelper)FloatingWindowManager.windowMap.remove((Object)this.getTag(s));
    }
    
    public final Unit visible(final boolean b, final String s, final boolean b2) {
        final FloatingWindowHelper helper = this.getHelper(s);
        Unit instance;
        if (helper != null) {
            int n;
            if (b) {
                n = 0;
            }
            else {
                n = 8;
            }
            helper.setVisible(n, b2);
            instance = Unit.INSTANCE;
        }
        else {
            instance = null;
        }
        return instance;
    }
}

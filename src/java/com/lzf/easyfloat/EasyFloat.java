package com.lzf.easyfloat;

import com.lzf.easyfloat.utils.LifecycleUtils;
import android.content.ComponentName;
import java.util.List;
import java.util.ArrayList;
import java.util.Collection;
import kotlin.jvm.internal.Intrinsics;
import java.util.Set;
import com.lzf.easyfloat.core.FloatingWindowHelper;
import com.lzf.easyfloat.core.FloatingWindowManager;
import com.lzf.easyfloat.data.FloatConfig;
import android.content.Context;
import android.view.View;
import android.app.Activity;
import kotlin.jvm.JvmStatic;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.Metadata;

@Metadata(bv = { 1, 0, 3 }, d1 = { "\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\u0018\u0000 \u00042\u00020\u0001:\u0002\u0003\u0004B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0005" }, d2 = { "Lcom/lzf/easyfloat/EasyFloat;", "", "()V", "Builder", "Companion", "easyfloat_release" }, k = 1, mv = { 1, 4, 1 })
public final class EasyFloat
{
    public static final Companion Companion;
    
    static {
        Companion = new Companion(null);
    }
    
    @JvmStatic
    public static final Unit clearFilters() {
        return EasyFloat.Companion.clearFilters$default(EasyFloat.Companion, null, 1, null);
    }
    
    @JvmStatic
    public static final Unit clearFilters(final String s) {
        return EasyFloat.Companion.clearFilters(s);
    }
    
    @JvmStatic
    public static final Unit dismiss() {
        return EasyFloat.Companion.dismiss$default(EasyFloat.Companion, null, false, 3, null);
    }
    
    @JvmStatic
    public static final Unit dismiss(final String s) {
        return EasyFloat.Companion.dismiss$default(EasyFloat.Companion, s, false, 2, null);
    }
    
    @JvmStatic
    public static final Unit dismiss(final String s, final boolean b) {
        return EasyFloat.Companion.dismiss(s, b);
    }
    
    @JvmStatic
    public static final Unit dragEnable(final boolean b) {
        return EasyFloat.Companion.dragEnable$default(EasyFloat.Companion, b, null, 2, null);
    }
    
    @JvmStatic
    public static final Unit dragEnable(final boolean b, final String s) {
        return EasyFloat.Companion.dragEnable(b, s);
    }
    
    @JvmStatic
    public static final Boolean filterActivities(final String s, final Class<?>... array) {
        return EasyFloat.Companion.filterActivities(s, array);
    }
    
    @JvmStatic
    public static final Boolean filterActivities(final Class<?>... array) {
        return EasyFloat.Companion.filterActivities$default(EasyFloat.Companion, null, array, 1, null);
    }
    
    @JvmStatic
    public static final Boolean filterActivity(final Activity activity) {
        return EasyFloat.Companion.filterActivity$default(EasyFloat.Companion, activity, null, 2, null);
    }
    
    @JvmStatic
    public static final Boolean filterActivity(final Activity activity, final String s) {
        return EasyFloat.Companion.filterActivity(activity, s);
    }
    
    @JvmStatic
    public static final View getFloatView() {
        return EasyFloat.Companion.getFloatView$default(EasyFloat.Companion, null, 1, null);
    }
    
    @JvmStatic
    public static final View getFloatView(final String s) {
        return EasyFloat.Companion.getFloatView(s);
    }
    
    @JvmStatic
    public static final Unit hide() {
        return EasyFloat.Companion.hide$default(EasyFloat.Companion, null, 1, null);
    }
    
    @JvmStatic
    public static final Unit hide(final String s) {
        return EasyFloat.Companion.hide(s);
    }
    
    @JvmStatic
    public static final boolean isShow() {
        return EasyFloat.Companion.isShow$default(EasyFloat.Companion, null, 1, null);
    }
    
    @JvmStatic
    public static final boolean isShow(final String s) {
        return EasyFloat.Companion.isShow(s);
    }
    
    @JvmStatic
    public static final Boolean removeFilter(final Activity activity) {
        return EasyFloat.Companion.removeFilter$default(EasyFloat.Companion, activity, null, 2, null);
    }
    
    @JvmStatic
    public static final Boolean removeFilter(final Activity activity, final String s) {
        return EasyFloat.Companion.removeFilter(activity, s);
    }
    
    @JvmStatic
    public static final Boolean removeFilters(final String s, final Class<?>... array) {
        return EasyFloat.Companion.removeFilters(s, array);
    }
    
    @JvmStatic
    public static final Boolean removeFilters(final Class<?>... array) {
        return EasyFloat.Companion.removeFilters$default(EasyFloat.Companion, null, array, 1, null);
    }
    
    @JvmStatic
    public static final Unit show() {
        return EasyFloat.Companion.show$default(EasyFloat.Companion, null, 1, null);
    }
    
    @JvmStatic
    public static final Unit show(final String s) {
        return EasyFloat.Companion.show(s);
    }
    
    @JvmStatic
    public static final Unit updateFloat() {
        return EasyFloat.Companion.updateFloat$default(EasyFloat.Companion, null, 0, 0, 7, null);
    }
    
    @JvmStatic
    public static final Unit updateFloat(final String s) {
        return EasyFloat.Companion.updateFloat$default(EasyFloat.Companion, s, 0, 0, 6, null);
    }
    
    @JvmStatic
    public static final Unit updateFloat(final String s, final int n) {
        return EasyFloat.Companion.updateFloat$default(EasyFloat.Companion, s, n, 0, 4, null);
    }
    
    @JvmStatic
    public static final Unit updateFloat(final String s, final int n, final int n2) {
        return EasyFloat.Companion.updateFloat(s, n, n2);
    }
    
    @JvmStatic
    public static final EasyFloat$Builder with(final Context context) {
        return EasyFloat.Companion.with(context);
    }
    
    @Metadata(bv = { 1, 0, 3 }, d1 = { "\u0000\\\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010#\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001b\u0010\u0003\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0007¢\u0006\u0002\u0010\u0007J%\u0010\b\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\b\u0002\u0010\t\u001a\u00020\nH\u0007¢\u0006\u0002\u0010\u000bJ#\u0010\f\u001a\u0004\u0018\u00010\u00042\u0006\u0010\f\u001a\u00020\n2\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0007¢\u0006\u0002\u0010\rJ7\u0010\u000e\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u001a\u0010\u000f\u001a\u000e\u0012\n\b\u0001\u0012\u0006\u0012\u0002\b\u00030\u00110\u0010\"\u0006\u0012\u0002\b\u00030\u0011H\u0007¢\u0006\u0002\u0010\u0012J#\u0010\u0013\u001a\u0004\u0018\u00010\n2\u0006\u0010\u0014\u001a\u00020\u00152\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0007¢\u0006\u0002\u0010\u0016J\u0014\u0010\u0017\u001a\u0004\u0018\u00010\u00182\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0002J\u001a\u0010\u0019\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u001a2\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0002J\u0016\u0010\u001b\u001a\u0004\u0018\u00010\u001c2\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0007J\u001b\u0010\u001d\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0007¢\u0006\u0002\u0010\u0007J\u0014\u0010\u001e\u001a\u00020\n2\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0007J#\u0010\u001f\u001a\u0004\u0018\u00010\n2\u0006\u0010\u0014\u001a\u00020\u00152\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0007¢\u0006\u0002\u0010\u0016J7\u0010 \u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u001a\u0010\u000f\u001a\u000e\u0012\n\b\u0001\u0012\u0006\u0012\u0002\b\u00030\u00110\u0010\"\u0006\u0012\u0002\b\u00030\u0011H\u0007¢\u0006\u0002\u0010\u0012J\u001b\u0010!\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0007¢\u0006\u0002\u0010\u0007J/\u0010\"\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\b\u0002\u0010#\u001a\u00020$2\b\b\u0002\u0010%\u001a\u00020$H\u0007¢\u0006\u0002\u0010&J\u0010\u0010'\u001a\u00020(2\u0006\u0010\u0014\u001a\u00020)H\u0007¨\u0006*" }, d2 = { "Lcom/lzf/easyfloat/EasyFloat$Companion;", "", "()V", "clearFilters", "", "tag", "", "(Ljava/lang/String;)Lkotlin/Unit;", "dismiss", "force", "", "(Ljava/lang/String;Z)Lkotlin/Unit;", "dragEnable", "(ZLjava/lang/String;)Lkotlin/Unit;", "filterActivities", "clazz", "", "Ljava/lang/Class;", "(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/Boolean;", "filterActivity", "activity", "Landroid/app/Activity;", "(Landroid/app/Activity;Ljava/lang/String;)Ljava/lang/Boolean;", "getConfig", "Lcom/lzf/easyfloat/data/FloatConfig;", "getFilterSet", "", "getFloatView", "Landroid/view/View;", "hide", "isShow", "removeFilter", "removeFilters", "show", "updateFloat", "x", "", "y", "(Ljava/lang/String;II)Lkotlin/Unit;", "with", "Lcom/lzf/easyfloat/EasyFloat$Builder;", "Landroid/content/Context;", "easyfloat_release" }, k = 1, mv = { 1, 4, 1 })
    public static final class Companion
    {
        private Companion() {
        }
        
        public static /* synthetic */ Unit clearFilters$default(final Companion companion, String s, final int n, final Object o) {
            if ((n & 0x1) != 0x0) {
                s = null;
            }
            return companion.clearFilters(s);
        }
        
        public static /* synthetic */ Unit dismiss$default(final Companion companion, String s, boolean b, final int n, final Object o) {
            if ((n & 0x1) != 0x0) {
                s = null;
            }
            if ((n & 0x2) != 0x0) {
                b = false;
            }
            return companion.dismiss(s, b);
        }
        
        public static /* synthetic */ Unit dragEnable$default(final Companion companion, final boolean b, String s, final int n, final Object o) {
            if ((n & 0x2) != 0x0) {
                s = null;
            }
            return companion.dragEnable(b, s);
        }
        
        public static /* synthetic */ Boolean filterActivities$default(final Companion companion, String s, final Class[] array, final int n, final Object o) {
            if ((n & 0x1) != 0x0) {
                s = null;
            }
            return companion.filterActivities(s, (Class<?>[])array);
        }
        
        public static /* synthetic */ Boolean filterActivity$default(final Companion companion, final Activity activity, String s, final int n, final Object o) {
            if ((n & 0x2) != 0x0) {
                s = null;
            }
            return companion.filterActivity(activity, s);
        }
        
        private final FloatConfig getConfig(final String s) {
            final FloatingWindowHelper helper = FloatingWindowManager.INSTANCE.getHelper(s);
            FloatConfig config;
            if (helper != null) {
                config = helper.getConfig();
            }
            else {
                config = null;
            }
            return config;
        }
        
        private final Set<String> getFilterSet(final String s) {
            final FloatConfig config = this.getConfig(s);
            Set<String> filterSet;
            if (config != null) {
                filterSet = config.getFilterSet();
            }
            else {
                filterSet = null;
            }
            return filterSet;
        }
        
        public static /* synthetic */ View getFloatView$default(final Companion companion, String s, final int n, final Object o) {
            if ((n & 0x1) != 0x0) {
                s = null;
            }
            return companion.getFloatView(s);
        }
        
        public static /* synthetic */ Unit hide$default(final Companion companion, String s, final int n, final Object o) {
            if ((n & 0x1) != 0x0) {
                s = null;
            }
            return companion.hide(s);
        }
        
        public static /* synthetic */ boolean isShow$default(final Companion companion, String s, final int n, final Object o) {
            if ((n & 0x1) != 0x0) {
                s = null;
            }
            return companion.isShow(s);
        }
        
        public static /* synthetic */ Boolean removeFilter$default(final Companion companion, final Activity activity, String s, final int n, final Object o) {
            if ((n & 0x2) != 0x0) {
                s = null;
            }
            return companion.removeFilter(activity, s);
        }
        
        public static /* synthetic */ Boolean removeFilters$default(final Companion companion, String s, final Class[] array, final int n, final Object o) {
            if ((n & 0x1) != 0x0) {
                s = null;
            }
            return companion.removeFilters(s, (Class<?>[])array);
        }
        
        public static /* synthetic */ Unit show$default(final Companion companion, String s, final int n, final Object o) {
            if ((n & 0x1) != 0x0) {
                s = null;
            }
            return companion.show(s);
        }
        
        public static /* synthetic */ Unit updateFloat$default(final Companion companion, String s, int n, int n2, final int n3, final Object o) {
            if ((n3 & 0x1) != 0x0) {
                s = null;
            }
            if ((n3 & 0x2) != 0x0) {
                n = -1;
            }
            if ((n3 & 0x4) != 0x0) {
                n2 = -1;
            }
            return companion.updateFloat(s, n, n2);
        }
        
        @JvmStatic
        public final Unit clearFilters() {
            return clearFilters$default(this, null, 1, null);
        }
        
        @JvmStatic
        public final Unit clearFilters(final String s) {
            final Set<String> filterSet = this.getFilterSet(s);
            Unit instance;
            if (filterSet != null) {
                filterSet.clear();
                instance = Unit.INSTANCE;
            }
            else {
                instance = null;
            }
            return instance;
        }
        
        @JvmStatic
        public final Unit dismiss() {
            return dismiss$default(this, null, false, 3, null);
        }
        
        @JvmStatic
        public final Unit dismiss(final String s) {
            return dismiss$default(this, s, false, 2, null);
        }
        
        @JvmStatic
        public final Unit dismiss(final String s, final boolean b) {
            return FloatingWindowManager.INSTANCE.dismiss(s, b);
        }
        
        @JvmStatic
        public final Unit dragEnable(final boolean b) {
            return dragEnable$default(this, b, null, 2, null);
        }
        
        @JvmStatic
        public final Unit dragEnable(final boolean dragEnable, final String s) {
            final FloatConfig config = this.getConfig(s);
            Unit instance;
            if (config != null) {
                config.setDragEnable(dragEnable);
                instance = Unit.INSTANCE;
            }
            else {
                instance = null;
            }
            return instance;
        }
        
        @JvmStatic
        public final Boolean filterActivities(final String s, final Class<?>... array) {
            Intrinsics.checkNotNullParameter((Object)array, "clazz");
            final Set<String> filterSet = this.getFilterSet(s);
            Boolean value;
            if (filterSet != null) {
                final Collection collection = (Collection)new ArrayList(array.length);
                for (int length = array.length, i = 0; i < length; ++i) {
                    collection.add((Object)array[i].getName());
                }
                value = filterSet.addAll((Collection)collection);
            }
            else {
                value = null;
            }
            return value;
        }
        
        @JvmStatic
        public final Boolean filterActivities(final Class<?>... array) {
            return filterActivities$default(this, null, array, 1, null);
        }
        
        @JvmStatic
        public final Boolean filterActivity(final Activity activity) {
            return filterActivity$default(this, activity, null, 2, null);
        }
        
        @JvmStatic
        public final Boolean filterActivity(final Activity activity, final String s) {
            Intrinsics.checkNotNullParameter((Object)activity, "activity");
            final Set<String> filterSet = this.getFilterSet(s);
            Boolean value;
            if (filterSet != null) {
                final ComponentName componentName = activity.getComponentName();
                Intrinsics.checkNotNullExpressionValue((Object)componentName, "activity.componentName");
                final String className = componentName.getClassName();
                Intrinsics.checkNotNullExpressionValue((Object)className, "activity.componentName.className");
                value = filterSet.add((Object)className);
            }
            else {
                value = null;
            }
            return value;
        }
        
        @JvmStatic
        public final View getFloatView() {
            return getFloatView$default(this, null, 1, null);
        }
        
        @JvmStatic
        public final View getFloatView(final String s) {
            final FloatConfig config = this.getConfig(s);
            View layoutView;
            if (config != null) {
                layoutView = config.getLayoutView();
            }
            else {
                layoutView = null;
            }
            return layoutView;
        }
        
        @JvmStatic
        public final Unit hide() {
            return hide$default(this, null, 1, null);
        }
        
        @JvmStatic
        public final Unit hide(final String s) {
            return FloatingWindowManager.INSTANCE.visible(false, s, false);
        }
        
        @JvmStatic
        public final boolean isShow() {
            return isShow$default(this, null, 1, null);
        }
        
        @JvmStatic
        public final boolean isShow(final String s) {
            final FloatConfig config = this.getConfig(s);
            return config != null && config.isShow();
        }
        
        @JvmStatic
        public final Boolean removeFilter(final Activity activity) {
            return removeFilter$default(this, activity, null, 2, null);
        }
        
        @JvmStatic
        public final Boolean removeFilter(final Activity activity, final String s) {
            Intrinsics.checkNotNullParameter((Object)activity, "activity");
            final Set<String> filterSet = this.getFilterSet(s);
            Boolean value;
            if (filterSet != null) {
                final ComponentName componentName = activity.getComponentName();
                Intrinsics.checkNotNullExpressionValue((Object)componentName, "activity.componentName");
                value = filterSet.remove((Object)componentName.getClassName());
            }
            else {
                value = null;
            }
            return value;
        }
        
        @JvmStatic
        public final Boolean removeFilters(final String s, final Class<?>... array) {
            Intrinsics.checkNotNullParameter((Object)array, "clazz");
            final Set<String> filterSet = this.getFilterSet(s);
            Boolean value;
            if (filterSet != null) {
                final Collection collection = (Collection)new ArrayList(array.length);
                for (int length = array.length, i = 0; i < length; ++i) {
                    collection.add((Object)array[i].getName());
                }
                value = filterSet.removeAll((Collection)collection);
            }
            else {
                value = null;
            }
            return value;
        }
        
        @JvmStatic
        public final Boolean removeFilters(final Class<?>... array) {
            return removeFilters$default(this, null, array, 1, null);
        }
        
        @JvmStatic
        public final Unit show() {
            return show$default(this, null, 1, null);
        }
        
        @JvmStatic
        public final Unit show(final String s) {
            return FloatingWindowManager.INSTANCE.visible(true, s, true);
        }
        
        @JvmStatic
        public final Unit updateFloat() {
            return updateFloat$default(this, null, 0, 0, 7, null);
        }
        
        @JvmStatic
        public final Unit updateFloat(final String s) {
            return updateFloat$default(this, s, 0, 0, 6, null);
        }
        
        @JvmStatic
        public final Unit updateFloat(final String s, final int n) {
            return updateFloat$default(this, s, n, 0, 4, null);
        }
        
        @JvmStatic
        public final Unit updateFloat(final String s, final int n, final int n2) {
            final FloatingWindowHelper helper = FloatingWindowManager.INSTANCE.getHelper(s);
            Unit instance;
            if (helper != null) {
                helper.updateFloat(n, n2);
                instance = Unit.INSTANCE;
            }
            else {
                instance = null;
            }
            return instance;
        }
        
        @JvmStatic
        public final EasyFloat$Builder with(Context context) {
            Intrinsics.checkNotNullParameter((Object)context, "activity");
            EasyFloat$Builder easyFloat$Builder;
            if (context instanceof Activity) {
                easyFloat$Builder = new EasyFloat$Builder(context);
            }
            else {
                final Activity topActivity = LifecycleUtils.INSTANCE.getTopActivity();
                if (topActivity != null) {
                    context = (Context)topActivity;
                }
                easyFloat$Builder = new EasyFloat$Builder(context);
            }
            return easyFloat$Builder;
        }
    }
}

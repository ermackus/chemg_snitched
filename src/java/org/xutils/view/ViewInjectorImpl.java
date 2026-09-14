package org.xutils.view;

import org.xutils.common.util.LogUtil;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import org.xutils.x;
import org.xutils.view.annotation.ContentView;
import android.app.Fragment;
import android.app.Activity;
import java.util.HashSet;
import org.xutils.ViewInjector;

public final class ViewInjectorImpl implements ViewInjector
{
    private static final HashSet<Class<?>> IGNORED;
    private static volatile ViewInjectorImpl instance;
    private static final Object lock;
    
    static {
        (IGNORED = new HashSet()).add((Object)Object.class);
        ViewInjectorImpl.IGNORED.add((Object)Activity.class);
        ViewInjectorImpl.IGNORED.add((Object)Fragment.class);
        while (true) {
            try {
                ViewInjectorImpl.IGNORED.add((Object)Class.forName("androidx.fragment.app.Fragment"));
                ViewInjectorImpl.IGNORED.add((Object)Class.forName("androidx.fragment.app.FragmentActivity"));
                lock = new Object();
            }
            finally {
                continue;
            }
            break;
        }
    }
    
    private ViewInjectorImpl() {
    }
    
    private static ContentView findContentView(final Class<?> clazz) {
        if (clazz == null || ViewInjectorImpl.IGNORED.contains((Object)clazz)) {
            return null;
        }
        final ContentView contentView = clazz.getAnnotation(ContentView.class);
        if (contentView == null) {
            return findContentView(clazz.getSuperclass());
        }
        return contentView;
    }
    
    private static void injectObject(final Object p0, final Class<?> p1, final ViewFinder p2) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: ifnull          467
        //     4: getstatic       org/xutils/view/ViewInjectorImpl.IGNORED:Ljava/util/HashSet;
        //     7: aload_1        
        //     8: invokevirtual   java/util/HashSet.contains:(Ljava/lang/Object;)Z
        //    11: ifeq            17
        //    14: goto            467
        //    17: aload_0        
        //    18: aload_1        
        //    19: invokevirtual   java/lang/Class.getSuperclass:()Ljava/lang/Class;
        //    22: aload_2        
        //    23: invokestatic    org/xutils/view/ViewInjectorImpl.injectObject:(Ljava/lang/Object;Ljava/lang/Class;Lorg/xutils/view/ViewFinder;)V
        //    26: aload_1        
        //    27: invokevirtual   java/lang/Class.getDeclaredFields:()[Ljava/lang/reflect/Field;
        //    30: astore          8
        //    32: aload           8
        //    34: ifnull          252
        //    37: aload           8
        //    39: arraylength    
        //    40: ifle            252
        //    43: aload           8
        //    45: arraylength    
        //    46: istore          4
        //    48: iconst_0       
        //    49: istore_3       
        //    50: iload_3        
        //    51: iload           4
        //    53: if_icmpge       252
        //    56: aload           8
        //    58: iload_3        
        //    59: aaload         
        //    60: astore          9
        //    62: aload           9
        //    64: invokevirtual   java/lang/reflect/Field.getType:()Ljava/lang/Class;
        //    67: astore          10
        //    69: aload           9
        //    71: invokevirtual   java/lang/reflect/Field.getModifiers:()I
        //    74: invokestatic    java/lang/reflect/Modifier.isStatic:(I)Z
        //    77: ifne            246
        //    80: aload           9
        //    82: invokevirtual   java/lang/reflect/Field.getModifiers:()I
        //    85: invokestatic    java/lang/reflect/Modifier.isFinal:(I)Z
        //    88: ifne            246
        //    91: aload           10
        //    93: invokevirtual   java/lang/Class.isPrimitive:()Z
        //    96: ifne            246
        //    99: aload           10
        //   101: invokevirtual   java/lang/Class.isArray:()Z
        //   104: ifeq            110
        //   107: goto            246
        //   110: aload           9
        //   112: ldc             Lorg/xutils/view/annotation/ViewInject;.class
        //   114: invokevirtual   java/lang/reflect/Field.getAnnotation:(Ljava/lang/Class;)Ljava/lang/annotation/Annotation;
        //   117: checkcast       Lorg/xutils/view/annotation/ViewInject;
        //   120: astore          10
        //   122: aload           10
        //   124: ifnull          246
        //   127: aload_2        
        //   128: aload           10
        //   130: invokeinterface org/xutils/view/annotation/ViewInject.value:()I
        //   135: aload           10
        //   137: invokeinterface org/xutils/view/annotation/ViewInject.parentId:()I
        //   142: invokevirtual   org/xutils/view/ViewFinder.findViewById:(II)Landroid/view/View;
        //   145: astore          10
        //   147: aload           10
        //   149: ifnull          169
        //   152: aload           9
        //   154: iconst_1       
        //   155: invokevirtual   java/lang/reflect/Field.setAccessible:(Z)V
        //   158: aload           9
        //   160: aload_0        
        //   161: aload           10
        //   163: invokevirtual   java/lang/reflect/Field.set:(Ljava/lang/Object;Ljava/lang/Object;)V
        //   166: goto            246
        //   169: new             Ljava/lang/RuntimeException;
        //   172: astore          11
        //   174: new             Ljava/lang/StringBuilder;
        //   177: astore          10
        //   179: aload           10
        //   181: invokespecial   java/lang/StringBuilder.<init>:()V
        //   184: aload           10
        //   186: ldc             "Invalid @ViewInject for "
        //   188: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   191: pop            
        //   192: aload           10
        //   194: aload_1        
        //   195: invokevirtual   java/lang/Class.getSimpleName:()Ljava/lang/String;
        //   198: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   201: pop            
        //   202: aload           10
        //   204: ldc             "."
        //   206: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   209: pop            
        //   210: aload           10
        //   212: aload           9
        //   214: invokevirtual   java/lang/reflect/Field.getName:()Ljava/lang/String;
        //   217: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   220: pop            
        //   221: aload           11
        //   223: aload           10
        //   225: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   228: invokespecial   java/lang/RuntimeException.<init>:(Ljava/lang/String;)V
        //   231: aload           11
        //   233: athrow         
        //   234: astore          9
        //   236: aload           9
        //   238: invokevirtual   java/lang/Throwable.getMessage:()Ljava/lang/String;
        //   241: aload           9
        //   243: invokestatic    org/xutils/common/util/LogUtil.e:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //   246: iinc            3, 1
        //   249: goto            50
        //   252: aload_1        
        //   253: invokevirtual   java/lang/Class.getDeclaredMethods:()[Ljava/lang/reflect/Method;
        //   256: astore_1       
        //   257: aload_1        
        //   258: ifnull          467
        //   261: aload_1        
        //   262: arraylength    
        //   263: ifle            467
        //   266: aload_1        
        //   267: arraylength    
        //   268: istore          7
        //   270: iconst_0       
        //   271: istore_3       
        //   272: iload_3        
        //   273: iload           7
        //   275: if_icmpge       467
        //   278: aload_1        
        //   279: iload_3        
        //   280: aaload         
        //   281: astore          11
        //   283: aload           11
        //   285: invokevirtual   java/lang/reflect/Method.getModifiers:()I
        //   288: invokestatic    java/lang/reflect/Modifier.isStatic:(I)Z
        //   291: ifne            461
        //   294: aload           11
        //   296: invokevirtual   java/lang/reflect/Method.getModifiers:()I
        //   299: invokestatic    java/lang/reflect/Modifier.isPrivate:(I)Z
        //   302: ifne            308
        //   305: goto            461
        //   308: aload           11
        //   310: ldc             Lorg/xutils/view/annotation/Event;.class
        //   312: invokevirtual   java/lang/reflect/Method.getAnnotation:(Ljava/lang/Class;)Ljava/lang/annotation/Annotation;
        //   315: checkcast       Lorg/xutils/view/annotation/Event;
        //   318: astore          12
        //   320: aload           12
        //   322: ifnull          461
        //   325: aload           12
        //   327: invokeinterface org/xutils/view/annotation/Event.value:()[I
        //   332: astore          10
        //   334: aload           12
        //   336: invokeinterface org/xutils/view/annotation/Event.parentId:()[I
        //   341: astore          9
        //   343: aload           9
        //   345: ifnonnull       354
        //   348: iconst_0       
        //   349: istore          4
        //   351: goto            359
        //   354: aload           9
        //   356: arraylength    
        //   357: istore          4
        //   359: iconst_0       
        //   360: istore          5
        //   362: iload           5
        //   364: aload           10
        //   366: arraylength    
        //   367: if_icmpge       461
        //   370: aload           10
        //   372: iload           5
        //   374: iaload         
        //   375: istore          6
        //   377: iload           6
        //   379: ifle            443
        //   382: new             Lorg/xutils/view/ViewInfo;
        //   385: astore          8
        //   387: aload           8
        //   389: invokespecial   org/xutils/view/ViewInfo.<init>:()V
        //   392: aload           8
        //   394: iload           6
        //   396: putfield        org/xutils/view/ViewInfo.value:I
        //   399: iload           4
        //   401: iload           5
        //   403: if_icmple       416
        //   406: aload           9
        //   408: iload           5
        //   410: iaload         
        //   411: istore          6
        //   413: goto            419
        //   416: iconst_0       
        //   417: istore          6
        //   419: aload           8
        //   421: iload           6
        //   423: putfield        org/xutils/view/ViewInfo.parentId:I
        //   426: aload           11
        //   428: iconst_1       
        //   429: invokevirtual   java/lang/reflect/Method.setAccessible:(Z)V
        //   432: aload_2        
        //   433: aload           8
        //   435: aload           12
        //   437: aload_0        
        //   438: aload           11
        //   440: invokestatic    org/xutils/view/EventListenerManager.addEventMethod:(Lorg/xutils/view/ViewFinder;Lorg/xutils/view/ViewInfo;Lorg/xutils/view/annotation/Event;Ljava/lang/Object;Ljava/lang/reflect/Method;)V
        //   443: iinc            5, 1
        //   446: goto            362
        //   449: astore          8
        //   451: aload           8
        //   453: invokevirtual   java/lang/Throwable.getMessage:()Ljava/lang/String;
        //   456: aload           8
        //   458: invokestatic    org/xutils/common/util/LogUtil.e:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //   461: iinc            3, 1
        //   464: goto            272
        //   467: return         
        //    Signature:
        //  (Ljava/lang/Object;Ljava/lang/Class<*>;Lorg/xutils/view/ViewFinder;)V
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  127    147    234    246    Any
        //  152    166    234    246    Any
        //  169    234    234    246    Any
        //  325    343    449    461    Any
        //  354    359    449    461    Any
        //  362    370    449    461    Any
        //  382    399    449    461    Any
        //  419    443    449    461    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException: Attempt to invoke virtual method 'g5.m0 g5.d2.L()' on a null object reference
        //     at e5.d0.e(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:26)
        //     at e5.c0.s(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:1643)
        //     at q5.g.o(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:2651)
        //     at q5.g.b(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:2099)
        //     at u5.m.d(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:21)
        //     at u5.i.g(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:23)
        //     at u5.i.f(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:159)
        //     at u5.i.j(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:619)
        //     at u5.i.k(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:13)
        //     at u5.i.i(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:29)
        //     at s5.b.a(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:90)
        //     at com.thesourceofcode.jadec.decompilers.JavaExtractionWorker.decompileWithProcyon(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:367)
        //     at com.thesourceofcode.jadec.decompilers.JavaExtractionWorker.doWork(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:162)
        //     at com.thesourceofcode.jadec.decompilers.BaseDecompiler.withAttempt(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:3)
        //     at z6.a.run(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:31)
        //     at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1167)
        //     at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:641)
        //     at java.lang.Thread.run(Thread.java:920)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static void registerInstance() {
        if (ViewInjectorImpl.instance == null) {
            final Object lock = ViewInjectorImpl.lock;
            synchronized (lock) {
                if (ViewInjectorImpl.instance == null) {
                    ViewInjectorImpl.instance = new ViewInjectorImpl();
                }
            }
        }
        x.Ext.setViewInjector(ViewInjectorImpl.instance);
    }
    
    @Override
    public View inject(final Object o, final LayoutInflater layoutInflater, final ViewGroup viewGroup) {
        final Class<?> class1 = o.getClass();
        final View view = null;
        View view2 = null;
        try {
            final ContentView contentView = findContentView(class1);
            if (contentView != null) {
                final int value = contentView.value();
                if (value > 0) {
                    layoutInflater.inflate(value, viewGroup, false);
                }
            }
        }
        finally {
            final Throwable t;
            LogUtil.e(t.getMessage(), t);
            view2 = view;
        }
        injectObject(o, class1, new ViewFinder(view2));
        return view2;
    }
    
    @Override
    public void inject(final Activity activity) {
        final Class<? extends Activity> class1 = activity.getClass();
        try {
            final ContentView contentView = findContentView(class1);
            if (contentView != null) {
                final int value = contentView.value();
                if (value > 0) {
                    class1.getMethod("setContentView", Integer.TYPE).invoke((Object)activity, new Object[] { value });
                }
            }
        }
        finally {
            final Throwable t;
            LogUtil.e(t.getMessage(), t);
        }
        injectObject(activity, class1, new ViewFinder(activity));
    }
    
    @Override
    public void inject(final View view) {
        injectObject(view, view.getClass(), new ViewFinder(view));
    }
    
    @Override
    public void inject(final Object o, final View view) {
        injectObject(o, o.getClass(), new ViewFinder(view));
    }
}

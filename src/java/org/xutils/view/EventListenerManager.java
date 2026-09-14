package org.xutils.view;

import java.util.Iterator;
import java.util.Map$Entry;
import java.util.HashMap;
import java.lang.ref.WeakReference;
import android.view.View;
import org.xutils.common.util.LogUtil;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import android.text.TextUtils;
import java.lang.reflect.Method;
import org.xutils.view.annotation.Event;
import org.xutils.common.util.DoubleKeyValueMap;
import java.util.HashSet;

final class EventListenerManager
{
    private static final HashSet<String> AVOID_QUICK_EVENT_SET;
    private static final long QUICK_EVENT_TIME_SPAN = 300L;
    private static final DoubleKeyValueMap<ViewInfo, Class<?>, Object> listenerCache;
    
    static {
        (AVOID_QUICK_EVENT_SET = new HashSet(2)).add((Object)"onClick");
        EventListenerManager.AVOID_QUICK_EVENT_SET.add((Object)"onItemClick");
        listenerCache = new DoubleKeyValueMap<ViewInfo, Class<?>, Object>();
    }
    
    private EventListenerManager() {
    }
    
    public static void addEventMethod(final ViewFinder viewFinder, final ViewInfo viewInfo, final Event event, final Object o, final Method method) {
        try {
            final View viewByInfo = viewFinder.findViewByInfo(viewInfo);
            if (viewByInfo != null) {
                final Class<?> type = event.type();
                String name;
                if (TextUtils.isEmpty((CharSequence)(name = event.setter()))) {
                    final StringBuilder sb = new StringBuilder();
                    sb.append("set");
                    sb.append(type.getSimpleName());
                    name = sb.toString();
                }
                final String method2 = event.method();
                Object o2 = EventListenerManager.listenerCache.get(viewInfo, type);
                boolean equals;
                if (o2 != null) {
                    final DynamicHandler dynamicHandler = (DynamicHandler)Proxy.getInvocationHandler(o2);
                    final boolean b = equals = o.equals(dynamicHandler.getHandler());
                    if (b) {
                        dynamicHandler.addMethod(method2, method);
                        equals = b;
                    }
                }
                else {
                    equals = false;
                }
                if (!equals) {
                    final DynamicHandler dynamicHandler2 = new DynamicHandler(o);
                    dynamicHandler2.addMethod(method2, method);
                    o2 = Proxy.newProxyInstance(type.getClassLoader(), new Class[] { type }, (InvocationHandler)dynamicHandler2);
                    EventListenerManager.listenerCache.put(viewInfo, type, o2);
                }
                viewByInfo.getClass().getMethod(name, type).invoke((Object)viewByInfo, new Object[] { o2 });
            }
        }
        finally {
            final Throwable t;
            LogUtil.e(t.getMessage(), t);
        }
    }
    
    public static class DynamicHandler implements InvocationHandler
    {
        private static long lastClickTime;
        private WeakReference<Object> handlerRef;
        private final HashMap<String, Method> methodMap;
        
        public DynamicHandler(final Object o) {
            this.methodMap = (HashMap<String, Method>)new HashMap(1);
            this.handlerRef = (WeakReference<Object>)new WeakReference(o);
        }
        
        public void addMethod(final String s, final Method method) {
            this.methodMap.put((Object)s, (Object)method);
        }
        
        public Object getHandler() {
            return this.handlerRef.get();
        }
        
        public Object invoke(Object o, Method method, final Object[] array) throws Throwable {
            final Object value = this.handlerRef.get();
            if (value != null) {
                final String name = method.getName();
                if ("toString".equals((Object)name)) {
                    return DynamicHandler.class.getSimpleName();
                }
                method = (Method)this.methodMap.get((Object)name);
                if ((o = method) == null) {
                    o = method;
                    if (this.methodMap.size() == 1) {
                        final Iterator iterator = this.methodMap.entrySet().iterator();
                        o = method;
                        if (iterator.hasNext()) {
                            final Map$Entry map$Entry = (Map$Entry)iterator.next();
                            o = method;
                            if (TextUtils.isEmpty((CharSequence)map$Entry.getKey())) {
                                o = map$Entry.getValue();
                            }
                        }
                    }
                }
                if (o != null) {
                    if (EventListenerManager.AVOID_QUICK_EVENT_SET.contains((Object)name)) {
                        final long n = System.currentTimeMillis() - DynamicHandler.lastClickTime;
                        if (n < 300L) {
                            o = new StringBuilder();
                            ((StringBuilder)o).append("onClick cancelled: ");
                            ((StringBuilder)o).append(n);
                            LogUtil.d(((StringBuilder)o).toString());
                            return null;
                        }
                        DynamicHandler.lastClickTime = System.currentTimeMillis();
                    }
                    try {
                        return ((Method)o).invoke(value, array);
                    }
                    finally {
                        final StringBuilder sb = new StringBuilder();
                        sb.append("invoke method error:");
                        sb.append(value.getClass().getName());
                        sb.append("#");
                        sb.append(((Method)o).getName());
                        final Throwable t;
                        throw new RuntimeException(sb.toString(), t);
                    }
                }
                o = new StringBuilder();
                ((StringBuilder)o).append("method not impl: ");
                ((StringBuilder)o).append(name);
                ((StringBuilder)o).append("(");
                ((StringBuilder)o).append(value.getClass().getSimpleName());
                ((StringBuilder)o).append(")");
                LogUtil.w(((StringBuilder)o).toString());
            }
            return null;
        }
    }
}

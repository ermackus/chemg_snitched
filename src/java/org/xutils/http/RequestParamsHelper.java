package org.xutils.http;

import java.lang.reflect.Field;
import org.xutils.common.util.LogUtil;
import android.os.Parcelable$Creator;
import java.lang.reflect.Modifier;
import java.util.Iterator;
import org.json.JSONException;
import java.util.Map$Entry;
import org.json.JSONObject;
import java.util.Map;
import java.util.List;
import java.lang.reflect.Array;
import org.json.JSONArray;

final class RequestParamsHelper
{
    private static final ClassLoader BOOT_CL;
    
    static {
        BOOT_CL = String.class.getClassLoader();
    }
    
    private RequestParamsHelper() {
    }
    
    static Object parseJSONObject(final Object o) throws JSONException {
        if (o == null) {
            return null;
        }
        final Class<?> class1 = o.getClass();
        Object o2;
        if (class1.isArray()) {
            final JSONArray jsonArray = new JSONArray();
            final int length = Array.getLength(o);
            int n = 0;
            while (true) {
                o2 = jsonArray;
                if (n >= length) {
                    break;
                }
                jsonArray.put(parseJSONObject(Array.get(o, n)));
                ++n;
            }
        }
        else if (o instanceof List) {
            final JSONArray jsonArray2 = new JSONArray();
            final Iterator iterator = ((List)o).iterator();
            while (true) {
                o2 = jsonArray2;
                if (!iterator.hasNext()) {
                    break;
                }
                jsonArray2.put(parseJSONObject(iterator.next()));
            }
        }
        else if (o instanceof Map) {
            final JSONObject jsonObject = new JSONObject();
            final Iterator iterator2 = ((Map)o).entrySet().iterator();
            while (true) {
                o2 = jsonObject;
                if (!iterator2.hasNext()) {
                    break;
                }
                final Map$Entry map$Entry = (Map$Entry)iterator2.next();
                final Object key = map$Entry.getKey();
                final Object value = map$Entry.getValue();
                if (key == null || value == null) {
                    continue;
                }
                jsonObject.put(String.valueOf(key), parseJSONObject(value));
            }
        }
        else {
            final ClassLoader classLoader = class1.getClassLoader();
            o2 = o;
            if (classLoader != null) {
                o2 = o;
                if (classLoader != RequestParamsHelper.BOOT_CL) {
                    o2 = new JSONObject();
                    parseKV(o, class1, (ParseKVListener)new ParseKVListener(o2) {
                        final JSONObject val$jo;
                        
                        @Override
                        public void onParseKV(final String s, Object jsonObject) {
                            try {
                                jsonObject = RequestParamsHelper.parseJSONObject(jsonObject);
                                this.val$jo.put(s, jsonObject);
                            }
                            catch (final JSONException ex) {
                                throw new IllegalArgumentException("parse RequestParams to json failed", (Throwable)ex);
                            }
                        }
                    });
                }
            }
        }
        return o2;
    }
    
    static void parseKV(final Object o, final Class<?> clazz, final ParseKVListener parseKVListener) {
        if (o != null && clazz != null && clazz != RequestParams.class) {
            if (clazz != Object.class) {
                final ClassLoader classLoader = clazz.getClassLoader();
                if (classLoader != null) {
                    if (classLoader != RequestParamsHelper.BOOT_CL) {
                        final Field[] declaredFields = clazz.getDeclaredFields();
                        if (declaredFields != null && declaredFields.length > 0) {
                            for (final Field field : declaredFields) {
                                if (!Modifier.isTransient(field.getModifiers()) && field.getType() != Parcelable$Creator.class) {
                                    field.setAccessible(true);
                                    try {
                                        final String name = field.getName();
                                        final Object value = field.get(o);
                                        if (value != null) {
                                            parseKVListener.onParseKV(name, value);
                                        }
                                    }
                                    catch (final IllegalAccessException ex) {
                                        LogUtil.e(ex.getMessage(), (Throwable)ex);
                                    }
                                }
                            }
                        }
                        parseKV(o, clazz.getSuperclass(), parseKVListener);
                    }
                }
            }
        }
    }
    
    interface ParseKVListener
    {
        void onParseKV(final String p0, final Object p1);
    }
}

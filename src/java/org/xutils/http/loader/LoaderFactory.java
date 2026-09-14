package org.xutils.http.loader;

import org.xutils.http.RequestParams;
import java.io.File;
import org.json.JSONArray;
import org.json.JSONObject;
import java.lang.reflect.Type;
import java.util.HashMap;

public final class LoaderFactory
{
    private static final HashMap<Type, Loader> converterHashMap;
    
    static {
        (converterHashMap = new HashMap()).put((Object)JSONObject.class, (Object)new JSONObjectLoader());
        LoaderFactory.converterHashMap.put((Object)JSONArray.class, (Object)new JSONArrayLoader());
        LoaderFactory.converterHashMap.put((Object)String.class, (Object)new StringLoader());
        LoaderFactory.converterHashMap.put((Object)File.class, (Object)new FileLoader());
        LoaderFactory.converterHashMap.put((Object)byte[].class, (Object)new ByteArrayLoader());
        final BooleanLoader booleanLoader = new BooleanLoader();
        LoaderFactory.converterHashMap.put((Object)Boolean.TYPE, (Object)booleanLoader);
        LoaderFactory.converterHashMap.put((Object)Boolean.class, (Object)booleanLoader);
        final IntegerLoader integerLoader = new IntegerLoader();
        LoaderFactory.converterHashMap.put((Object)Integer.TYPE, (Object)integerLoader);
        LoaderFactory.converterHashMap.put((Object)Integer.class, (Object)integerLoader);
    }
    
    private LoaderFactory() {
    }
    
    public static Loader<?> getLoader(final Type type, final RequestParams params) {
        final Loader loader = (Loader)LoaderFactory.converterHashMap.get((Object)type);
        Loader instance;
        if (loader == null) {
            instance = new ObjectLoader(type);
        }
        else {
            instance = loader.newInstance();
        }
        instance.setParams(params);
        return instance;
    }
    
    public static <T> void registerLoader(final Type type, final Loader<T> loader) {
        LoaderFactory.converterHashMap.put((Object)type, (Object)loader);
    }
}

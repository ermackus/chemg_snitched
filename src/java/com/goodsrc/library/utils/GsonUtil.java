package com.goodsrc.library.utils;

import android.text.TextUtils;
import java.lang.reflect.Type;
import android.content.res.AssetManager;
import java.io.IOException;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import android.content.Context;
import com.google.gson.JsonSyntaxException;
import com.google.gson.Gson;

public class GsonUtil
{
    public static <T> T fromJson(final String s, final Class<T> clazz) {
        try {
            return (T)new Gson().fromJson(s, (Class)clazz);
        }
        catch (final JsonSyntaxException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    public static String getJson(final Context context, String line) {
        final StringBuilder sb = new StringBuilder();
        final AssetManager assets = context.getAssets();
        try {
            final BufferedReader bufferedReader = new BufferedReader((Reader)new InputStreamReader(assets.open(line), StandardCharsets.UTF_8));
            while (true) {
                line = bufferedReader.readLine();
                if (line == null) {
                    break;
                }
                sb.append(line);
            }
            bufferedReader.close();
        }
        catch (final IOException ex) {
            ex.printStackTrace();
        }
        return sb.toString();
    }
    
    public static <T> T parseJson(final String s, final Type type) {
        if (!TextUtils.isEmpty((CharSequence)s) && type != null) {
            return (T)new Gson().fromJson(s, type);
        }
        return null;
    }
    
    public static String toJson(final Object o) {
        try {
            return new Gson().toJson(o);
        }
        catch (final Exception ex) {
            ex.printStackTrace();
            return "";
        }
    }
}

package com.lzf.easyfloat;

import android.database.Cursor;
import android.content.Context;
import android.app.Application;
import com.lzf.easyfloat.utils.LifecycleUtils;
import android.content.ContentValues;
import kotlin.jvm.internal.Intrinsics;
import android.net.Uri;
import kotlin.Metadata;
import android.content.ContentProvider;

@Metadata(bv = { 1, 0, 3 }, d1 = { "\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J/\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\u000e\u0010\t\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\nH\u0016¢\u0006\u0002\u0010\u000bJ\u0012\u0010\f\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u001c\u0010\r\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0016J\b\u0010\u0010\u001a\u00020\u0011H\u0016JK\u0010\u0012\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0005\u001a\u00020\u00062\u000e\u0010\u0014\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\n2\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\u000e\u0010\t\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\n2\b\u0010\u0015\u001a\u0004\u0018\u00010\bH\u0016¢\u0006\u0002\u0010\u0016J9\u0010\u0017\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\u000e\u0010\t\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\nH\u0016¢\u0006\u0002\u0010\u0018¨\u0006\u0019" }, d2 = { "Lcom/lzf/easyfloat/EasyFloatInitializer;", "Landroid/content/ContentProvider;", "()V", "delete", "", "uri", "Landroid/net/Uri;", "selection", "", "selectionArgs", "", "(Landroid/net/Uri;Ljava/lang/String;[Ljava/lang/String;)I", "getType", "insert", "values", "Landroid/content/ContentValues;", "onCreate", "", "query", "Landroid/database/Cursor;", "projection", "sortOrder", "(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;", "update", "(Landroid/net/Uri;Landroid/content/ContentValues;Ljava/lang/String;[Ljava/lang/String;)I", "easyfloat_release" }, k = 1, mv = { 1, 4, 1 })
public final class EasyFloatInitializer extends ContentProvider
{
    public int delete(final Uri uri, final String s, final String[] array) {
        Intrinsics.checkNotNullParameter((Object)uri, "uri");
        return 0;
    }
    
    public String getType(final Uri uri) {
        Intrinsics.checkNotNullParameter((Object)uri, "uri");
        return null;
    }
    
    public Uri insert(final Uri uri, final ContentValues contentValues) {
        Intrinsics.checkNotNullParameter((Object)uri, "uri");
        return null;
    }
    
    public boolean onCreate() {
        final LifecycleUtils instance = LifecycleUtils.INSTANCE;
        final Context context = this.getContext();
        Intrinsics.checkNotNull((Object)context);
        final Context applicationContext = context.getApplicationContext();
        if (applicationContext != null) {
            instance.setLifecycleCallbacks((Application)applicationContext);
            return true;
        }
        throw new NullPointerException("null cannot be cast to non-null type android.app.Application");
    }
    
    public Cursor query(final Uri uri, final String[] array, final String s, final String[] array2, final String s2) {
        Intrinsics.checkNotNullParameter((Object)uri, "uri");
        return null;
    }
    
    public int update(final Uri uri, final ContentValues contentValues, final String s, final String[] array) {
        Intrinsics.checkNotNullParameter((Object)uri, "uri");
        return 0;
    }
}

package com.alibaba.sdk.android.man.crashreporter.d;

import java.io.FileNotFoundException;
import java.io.InputStream;
import com.alibaba.sdk.android.man.crashreporter.e.f;
import java.io.FileInputStream;
import java.io.File;
import com.alibaba.sdk.android.man.crashreporter.global.BaseDataContent;
import com.alibaba.sdk.android.man.crashreporter.c;
import android.content.Context;

public class a extends b
{
    private final String FILENAME;
    private final String TOMBSTONE_PATH;
    private final Context a;
    private c environment;
    private final String t;
    
    public a(final Context a, final c environment) {
        this.TOMBSTONE_PATH = "tombstone";
        this.FILENAME = "crashreporter";
        this.t = ".base";
        this.environment = null;
        this.a = a;
        this.environment = environment;
    }
    
    @Override
    public BaseDataContent a() {
        Object o;
        FileInputStream fileInputStream = null;
        InputStream inputStream;
        Object o2;
        Object o3;
        Object o4;
        boolean b;
        Object o5;
        FileInputStream fileInputStream2;
        File file;
        BaseDataContent baseDataContent;
        Label_0123_Outer:Block_21_Outer:
        while (true) {
            try {
                o = new File(String.format("%s/%s%s", new Object[] { this.a.getDir("tombstone", 0).getPath(), "crashreporter", ".base" }));
                if (!((File)o).exists() || !((File)o).isFile()) {
                    break Label_0123_Outer;
                }
                fileInputStream = new FileInputStream((File)o);
                try {
                    inputStream = (InputStream)fileInputStream;
                    o2 = com.alibaba.sdk.android.man.crashreporter.d.a.a.a(inputStream);
                    o = fileInputStream;
                    o3 = o2;
                    if (o3 == null) {
                        break Label_0117;
                    }
                    o = fileInputStream;
                    o4 = o2;
                    b = (o4 instanceof BaseDataContent);
                    if (b) {
                        o5 = o2;
                        o = o5;
                        fileInputStream2 = fileInputStream;
                        f.a((InputStream)fileInputStream2);
                        file = (File)o;
                        return (BaseDataContent)file;
                    }
                    break Label_0117;
                }
                catch (final Exception o) {}
                catch (final FileNotFoundException o6) {}
            }
            catch (final Exception ex) {}
            catch (final FileNotFoundException ex2) {}
            try {
                inputStream = (InputStream)fileInputStream;
                o2 = com.alibaba.sdk.android.man.crashreporter.d.a.a.a(inputStream);
                o = fileInputStream;
                o3 = o2;
                if (o3 != null) {
                    o = fileInputStream;
                    o4 = o2;
                    b = (o4 instanceof BaseDataContent);
                    if (b) {
                        o5 = o2;
                        o = o5;
                        fileInputStream2 = fileInputStream;
                        f.a((InputStream)fileInputStream2);
                        file = (File)o;
                        return (BaseDataContent)file;
                    }
                }
            Block_23:
                while (true) {
                    while (true) {
                        if (o != null) {
                            baseDataContent = (BaseDataContent)o;
                            f.a((InputStream)baseDataContent);
                        }
                        Label_0152: {
                            return null;
                        }
                        continue Block_21_Outer;
                    }
                    com.alibaba.sdk.android.man.crashreporter.b.a.h(String.format("Trying to load crash report but base data not found.", new Object[0]));
                    iftrue(Label_0179:)(fileInputStream == null);
                    break Block_23;
                    Label_0179: {
                        return null;
                    }
                    o = null;
                    continue Label_0123_Outer;
                    com.alibaba.sdk.android.man.crashreporter.b.a.d("read base data file error.", (Throwable)o);
                    iftrue(Label_0152:)(baseDataContent == null);
                    continue;
                }
                f.a((InputStream)fileInputStream);
                return null;
            }
            finally {}
            break;
        }
    }
    
    @Override
    public void a(final BaseDataContent baseDataContent) {
        if (baseDataContent == null) {
            com.alibaba.sdk.android.man.crashreporter.b.a.g("base data object is null!");
            return;
        }
        final File dir = this.a.getDir("tombstone", 0);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        if (dir.canWrite()) {
            try {
                com.alibaba.sdk.android.man.crashreporter.d.a.a.a((Object)baseDataContent, new File(String.format("%s/%s%s", new Object[] { dir, "crashreporter", ".base" })));
                com.alibaba.sdk.android.man.crashreporter.b.a.e("base data succ");
            }
            catch (final Exception ex) {
                com.alibaba.sdk.android.man.crashreporter.b.a.d("base data write error.", (Throwable)ex);
            }
        }
    }
    
    @Override
    public void b(final boolean b) {
        if (b) {
            final BaseDataContent a = this.a();
            if (a != null) {
                a.hashCode = null;
                a.path = null;
                a.times = 0;
                this.a(a);
            }
        }
    }
    
    @Override
    public String h() {
        final String s = "";
        try {
            final BaseDataContent a = this.a();
            String userNick = s;
            if (a != null) {
                userNick = s;
                if (a.userNick != null) {
                    userNick = a.userNick;
                }
            }
            return userNick;
        }
        catch (final Exception ex) {
            com.alibaba.sdk.android.man.crashreporter.b.a.d("get local user nick err!", (Throwable)ex);
            return "";
        }
    }
}

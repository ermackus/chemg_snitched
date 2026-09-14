package com.alibaba.sdk.android.man.crashreporter.d.a;

import com.alibaba.sdk.android.man.crashreporter.e.i;
import java.io.FilenameFilter;
import android.content.Context;
import java.io.OutputStream;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.File;
import com.alibaba.sdk.android.man.crashreporter.e.f;
import java.io.ObjectInputStream;
import java.io.InputStream;

public class a
{
    public static Object a(final InputStream inputStream) {
        Object o = null;
        if (inputStream == null) {
            com.alibaba.sdk.android.man.crashreporter.b.a.h("load file error:input stream is null!");
            return null;
        }
    Label_0075:
        while (true) {
            ObjectInputStream objectInputStream = null;
            Object o6 = null;
            try {
                objectInputStream = (ObjectInputStream)(o = new ObjectInputStream(inputStream));
                try {
                    final ObjectInputStream objectInputStream2 = objectInputStream;
                    final Object o2 = objectInputStream2.readObject();
                    final Object o3 = objectInputStream;
                    final Object o4 = o2;
                    if (o4 != null) {
                        final ObjectInputStream objectInputStream3 = objectInputStream;
                        f.a((InputStream)objectInputStream3);
                        return o2;
                    }
                    break Label_0068;
                }
                catch (final Exception o) {
                    final Object o3 = objectInputStream;
                    o6 = o;
                }
            }
            catch (final Exception o6) {
                final Object o3 = null;
            }
            finally {
                break Label_0075;
            }
            try {
                final ObjectInputStream objectInputStream2 = objectInputStream;
                final Object o2 = objectInputStream2.readObject();
                final Object o3 = objectInputStream;
                final Object o4 = o2;
                if (o4 != null) {
                    final ObjectInputStream objectInputStream3 = objectInputStream;
                    f.a((InputStream)objectInputStream3);
                    return o2;
                }
                f.a((InputStream)o3);
                Label_0072: {
                    return null;
                }
                o = o3;
                com.alibaba.sdk.android.man.crashreporter.b.a.d("load reports error.", (Throwable)o6);
                iftrue(Label_0072:)(o3 == null);
                continue;
            }
            finally {}
            break;
        }
        if (o != null) {
            f.a((InputStream)o);
        }
    }
    
    public static void a(Object o, File ex) {
        if (o == null || ex == null) {
            com.alibaba.sdk.android.man.crashreporter.b.a.h("store file error:object or file is null!");
            return;
        }
        final Exception ex2 = null;
        Object o2 = null;
        Object o6 = null;
        Label_0134: {
            Object o3;
            Exception ex3 = null;
            try {
                o3 = new FileOutputStream((File)ex);
                ex = ex2;
                o2 = o3;
                try {
                    final ObjectOutputStream objectOutputStream = new(java.io.ObjectOutputStream.class)();
                    final ObjectOutputStream objectOutputStream2 = objectOutputStream;
                    ex = ex2;
                    o2 = o3;
                    final Throwable t = (Throwable)objectOutputStream2;
                    final Object o4 = o3;
                    new ObjectOutputStream((OutputStream)o4);
                    try {
                        final ObjectOutputStream objectOutputStream3 = objectOutputStream2;
                        final Object o5 = o;
                        objectOutputStream3.writeObject(o5);
                        final ObjectOutputStream objectOutputStream4 = objectOutputStream2;
                        f.a((OutputStream)objectOutputStream4);
                    }
                    catch (final Exception ex) {
                        o = objectOutputStream2;
                    }
                    finally {
                        final Throwable t2;
                        ex3 = (Exception)t2;
                    }
                }
                catch (final Exception objectOutputStream2) {}
            }
            catch (final Exception objectOutputStream2) {
                o3 = null;
            }
            finally {
                ex = (Exception)o2;
                break Label_0134;
            }
            try {
                final ObjectOutputStream objectOutputStream = new(java.io.ObjectOutputStream.class)();
                final ObjectOutputStream objectOutputStream2 = objectOutputStream;
                ex = ex2;
                o2 = o3;
                final Throwable t = (Throwable)objectOutputStream2;
                final Object o4 = o3;
                new ObjectOutputStream((OutputStream)o4);
                final ObjectOutputStream objectOutputStream3 = objectOutputStream2;
                final Object o5 = o;
                objectOutputStream3.writeObject(o5);
                final ObjectOutputStream objectOutputStream4 = objectOutputStream2;
                f.a((OutputStream)objectOutputStream4);
            Block_23_Outer:
                while (true) {
                    f.a((OutputStream)o3);
                    Label_0130: {
                        return;
                    }
                Label_0122:
                    while (true) {
                        f.a((OutputStream)ex3);
                        break Label_0122;
                        ex = ex3;
                        o2 = o3;
                        com.alibaba.sdk.android.man.crashreporter.b.a.d("store file error.", (Throwable)objectOutputStream2);
                        iftrue(Label_0122:)(ex3 == null);
                        continue;
                    }
                    iftrue(Label_0130:)(o3 == null);
                    continue Block_23_Outer;
                }
            }
            finally {
                o6 = o2;
            }
        }
        if (ex != null) {
            f.a((OutputStream)ex);
        }
        if (o6 != null) {
            f.a((OutputStream)o6);
        }
    }
    
    public static String[] a(final Context context, final String s) {
        Label_0014: {
            if (context != null) {
                break Label_0014;
            }
            try {
                com.alibaba.sdk.android.man.crashreporter.b.a.h("Trying to get crash reports but MotuCrashReporter is not initialized.");
                return new String[0];
                Label_0092: {
                    return;
                }
                final File dir = context.getDir(s, 0);
                iftrue(Label_0035:)(dir != null);
                com.alibaba.sdk.android.man.crashreporter.b.a.g("Application files directory does not exist! The application may not be installed correctly. Please try reinstalling.");
                return new String[0];
                return new String[0];
                Label_0035:
                final StringBuilder sb = new StringBuilder();
                sb.append("Looking for error files in ");
                sb.append(dir.getAbsolutePath());
                com.alibaba.sdk.android.man.crashreporter.b.a.e(sb.toString());
                iftrue(Label_0092:)((list = dir.list((FilenameFilter)new FilenameFilter(context) {
                    final Context b;
                    
                    public boolean accept(final File file, final String s) {
                        final String a = com.alibaba.sdk.android.man.crashreporter.e.a.a(this.b);
                        if (a == null) {
                            return s.startsWith("FAILURE");
                        }
                        return s.startsWith(Integer.toString(i.a(i.a(a, ""))));
                    }
                })) != null);
                return new String[0];
            }
            catch (final Exception ex) {
                com.alibaba.sdk.android.man.crashreporter.b.a.d("find file error.", (Throwable)ex);
                return null;
            }
        }
    }
    
    public static String[] a(final Context context, final String s, final String s2) {
        Label_0014: {
            if (context != null) {
                break Label_0014;
            }
            try {
                com.alibaba.sdk.android.man.crashreporter.b.a.h("Trying to get crash reports but MotuCrashReporter is not initialized.");
                return new String[0];
                final File file = new File(s);
                final StringBuilder sb = new StringBuilder();
                sb.append("Looking for error files in ");
                sb.append(file.getAbsolutePath());
                com.alibaba.sdk.android.man.crashreporter.b.a.e(sb.toString());
                String[] list;
                iftrue(Label_0080:)((list = file.list((FilenameFilter)new FilenameFilter(s2) {
                    final String v;
                    
                    public boolean accept(final File file, final String s) {
                        return s.endsWith(this.v);
                    }
                })) != null);
                return new String[0];
                Label_0080: {
                    return list;
                }
                list = new String[0];
                return list;
            }
            catch (final Exception ex) {
                com.alibaba.sdk.android.man.crashreporter.b.a.d("find file error.", (Throwable)ex);
                return null;
            }
        }
    }
}

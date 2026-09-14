package com.goodsrc.library.utils;

import java.io.IOException;
import java.io.OutputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.FileInputStream;
import android.text.TextUtils;
import java.io.File;
import java.util.Locale;

public class FileUtil
{
    private FileUtil() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }
    
    public static String byte2FitMemorySize(final long n) {
        if (n < 0L) {
            return "-1";
        }
        if (n < 1024L) {
            return String.format(Locale.ENGLISH, "%.3fB", new Object[] { Double.valueOf(n) });
        }
        if (n < 1048576L) {
            return String.format(Locale.ENGLISH, "%.3fKB", new Object[] { n / 1024.0 });
        }
        if (n < 1073741824L) {
            return String.format(Locale.ENGLISH, "%.3fMB", new Object[] { n / 1048576.0 });
        }
        return String.format(Locale.ENGLISH, "%.3fGB", new Object[] { n / 1.073741824E9 });
    }
    
    public static boolean clearDir(final File file) {
        int i = 0;
        if (file != null && file.exists() && file.isDirectory()) {
            for (File[] listFiles = file.listFiles(); i < listFiles.length; ++i) {
                final File file2 = listFiles[i];
                if (file2.isDirectory()) {
                    clearDir(file2);
                    file2.delete();
                }
                else {
                    deleteFile(file2);
                }
            }
            return true;
        }
        return false;
    }
    
    public static boolean clearDir(final String s) {
        return !TextUtils.isEmpty((CharSequence)s) && clearDir(new File(s));
    }
    
    public static boolean copyDir(final File file, final File file2) {
        if (file != null) {
            if (file2 != null) {
                if (file.exists()) {
                    if (!file.isFile()) {
                        if (file2.isFile()) {
                            return false;
                        }
                        if (file2.getAbsolutePath().startsWith(file.getAbsolutePath()) && file.getName().equals((Object)file2.getName())) {
                            return false;
                        }
                        if (file2.exists() && !file2.delete()) {
                            return false;
                        }
                        if (creatOrExistDir(file2)) {
                            for (final File file3 : file.listFiles()) {
                                final StringBuilder sb = new StringBuilder();
                                sb.append((Object)file2.getAbsoluteFile());
                                sb.append(File.separator);
                                sb.append(file3.getName());
                                final File file4 = new File(sb.toString());
                                if (file3.isDirectory()) {
                                    if (creatOrExistDir(file4)) {
                                        copyDir(file3, file4);
                                    }
                                }
                                else {
                                    copyFile(file3, file4);
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
    
    public static boolean copyDir(final String s, final String s2) {
        return !TextUtils.isEmpty((CharSequence)s) && !TextUtils.isEmpty((CharSequence)s2) && copyDir(new File(s), new File(s2));
    }
    
    public static boolean copyFile(final File file, final File file2) {
        if (file != null) {
            if (file2 != null) {
                if (file.exists()) {
                    if (!file.isDirectory()) {
                        if (file2.isDirectory()) {
                            return false;
                        }
                        if (file2.exists() && !file2.delete()) {
                            return false;
                        }
                        if (creatOrExistDir(file2.getParentFile())) {
                            return writeFile(file, file2);
                        }
                    }
                }
            }
        }
        return false;
    }
    
    public static boolean copyFile(final String s, final String s2) {
        return !TextUtils.isEmpty((CharSequence)s) && !TextUtils.isEmpty((CharSequence)s2) && copyFile(new File(s), new File(s2));
    }
    
    public static boolean creatOrExistDir(final File file) {
        if (file == null) {
            return false;
        }
        if (file.exists()) {
            return file.isDirectory();
        }
        return file.mkdirs();
    }
    
    public static boolean deleteFile(final File file) {
        if (file != null && file.exists()) {
            if (file.isDirectory()) {
                clearDir(file);
            }
            return file.delete();
        }
        return false;
    }
    
    public static boolean deleteFile(final String s) {
        return !TextUtils.isEmpty((CharSequence)s) && deleteFile(new File(s));
    }
    
    public static String getFileExtension(final File file) {
        String absolutePath;
        if (file != null) {
            absolutePath = file.getAbsolutePath();
        }
        else {
            absolutePath = "";
        }
        return getFileExtension(absolutePath);
    }
    
    public static String getFileExtension(final String s) {
        if (!TextUtils.isEmpty((CharSequence)s)) {
            return "";
        }
        final int lastIndex = s.lastIndexOf(46);
        final int lastIndex2 = s.lastIndexOf(File.separator);
        if (lastIndex != -1 && lastIndex2 < lastIndex) {
            return s.substring(lastIndex + 1);
        }
        return "";
    }
    
    public static boolean moveDir(final File file, final File file2) {
        if (file != null) {
            if (file2 != null) {
                if (copyDir(file, file2)) {
                    deleteFile(file);
                    return true;
                }
            }
        }
        return false;
    }
    
    public static boolean moveDir(final String s, final String s2) {
        return !TextUtils.isEmpty((CharSequence)s) && !TextUtils.isEmpty((CharSequence)s2) && moveFile(new File(s), new File(s2));
    }
    
    public static boolean moveFile(final File file, final File file2) {
        if (file != null) {
            if (file2 != null) {
                final StringBuilder sb = new StringBuilder();
                sb.append(file2.getAbsolutePath());
                sb.append(File.separator);
                sb.append(file.getName());
                if (copyFile(file, new File(sb.toString()))) {
                    file.delete();
                    return true;
                }
            }
        }
        return false;
    }
    
    public static boolean moveFile(final String s, final String s2) {
        return !TextUtils.isEmpty((CharSequence)s) && !TextUtils.isEmpty((CharSequence)s2) && moveFile(new File(s), new File(s2));
    }
    
    private static boolean writeFile(final File file, final File file2) {
        try {
            return writeFile((InputStream)new FileInputStream(file), file2);
        }
        catch (final FileNotFoundException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    public static boolean writeFile(final InputStream ex, final File file) {
        final OutputStream outputStream = null;
        final OutputStream outputStream2 = null;
        Object o2;
        final Object o = o2 = null;
        BufferedOutputStream bufferedOutputStream = null;
        OutputStream outputStream3;
        try {
            try {
                o2 = o;
                o2 = o;
                final FileOutputStream fileOutputStream = new FileOutputStream(file);
                o2 = o;
                bufferedOutputStream = new BufferedOutputStream((OutputStream)fileOutputStream);
                try {
                    final byte[] array = new byte[8192];
                    while (true) {
                        final int read = ((InputStream)ex).read(array, 0, 8192);
                        if (read == -1) {
                            break;
                        }
                        ((OutputStream)bufferedOutputStream).write(array, 0, read);
                    }
                    Label_0091: {
                        if (ex != null) {
                            Label_0099: {
                                try {
                                    ((InputStream)ex).close();
                                }
                                catch (final IOException ex) {
                                    break Label_0099;
                                }
                                break Label_0091;
                            }
                            ex.printStackTrace();
                            return true;
                        }
                    }
                    ((OutputStream)bufferedOutputStream).close();
                    return true;
                }
                catch (final NullPointerException o2) {}
                catch (final IOException o2) {}
                finally {
                    o2 = bufferedOutputStream;
                }
            }
            finally {}
        }
        catch (final NullPointerException bufferedOutputStream) {
            outputStream3 = outputStream;
        }
        catch (final IOException bufferedOutputStream) {
            outputStream3 = outputStream2;
        }
        try {
            ((InputStream)ex).close();
            goto Label_0163;
        }
        catch (final IOException ex2) {}
        ((IOException)bufferedOutputStream).printStackTrace();
        Label_0207: {
            if (ex != null) {
                Label_0218: {
                    try {
                        ((InputStream)ex).close();
                    }
                    catch (final IOException ex3) {
                        break Label_0218;
                    }
                    break Label_0207;
                }
                final IOException ex3;
                ex3.printStackTrace();
                return false;
            }
        }
        if (outputStream3 != null) {
            outputStream3.close();
        }
        return false;
        Label_0239: {
            if (ex != null) {
                Label_0250: {
                    try {
                        ((InputStream)ex).close();
                    }
                    catch (final IOException ex4) {
                        break Label_0250;
                    }
                    break Label_0239;
                }
                final IOException ex4;
                ex4.printStackTrace();
                return;
            }
        }
        if (o2 != null) {
            ((OutputStream)o2).close();
        }
    }
}

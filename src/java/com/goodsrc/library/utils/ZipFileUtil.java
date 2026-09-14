package com.goodsrc.library.utils;

import java.util.Iterator;
import java.util.List;
import java.io.OutputStream;
import android.text.TextUtils;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.zip.ZipInputStream;
import java.io.IOException;
import java.io.FileInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import java.io.File;

public class ZipFileUtil
{
    private ZipFileUtil() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }
    
    private static boolean checkOrCreatZipFile(File parentFile) {
        if (parentFile == null) {
            return false;
        }
        final String name = parentFile.getName();
        if (!name.endsWith(".zip") && !name.endsWith(".rar")) {
            return false;
        }
        parentFile = parentFile.getParentFile();
        return parentFile.exists() || parentFile.mkdirs();
    }
    
    private static void compress(File file, final ZipOutputStream zipOutputStream, final String s, final boolean b) throws IOException {
        final boolean file2 = file.isFile();
        int i = 0;
        if (file2) {
            zipOutputStream.putNextEntry(new ZipEntry(s));
            final byte[] array = new byte[1024];
            final FileInputStream fileInputStream = new FileInputStream(file);
            while (true) {
                final int read = fileInputStream.read(array);
                if (read == -1) {
                    break;
                }
                zipOutputStream.write(array, 0, read);
            }
            zipOutputStream.closeEntry();
            fileInputStream.close();
        }
        else {
            final File[] listFiles = file.listFiles();
            if (listFiles != null && listFiles.length > 0) {
                while (i < listFiles.length) {
                    file = listFiles[i];
                    if (b) {
                        final StringBuilder sb = new StringBuilder();
                        sb.append(s);
                        sb.append("/");
                        sb.append(file.getName());
                        compress(file, zipOutputStream, sb.toString(), b);
                    }
                    else {
                        compress(file, zipOutputStream, file.getName(), b);
                    }
                    ++i;
                }
            }
            else if (b) {
                final StringBuilder sb2 = new StringBuilder();
                sb2.append(s);
                sb2.append("/");
                zipOutputStream.putNextEntry(new ZipEntry(sb2.toString()));
                zipOutputStream.closeEntry();
            }
        }
    }
    
    public static boolean unZipFile(final File file, final File file2) {
        if (file != null) {
            if (file.exists()) {
                if (file2 != null) {
                    if (!file2.isFile()) {
                        if (!file2.exists()) {
                            file2.mkdirs();
                        }
                        try {
                            final ZipInputStream zipInputStream = new ZipInputStream((InputStream)new FileInputStream(file));
                            while (true) {
                                final ZipEntry nextEntry = zipInputStream.getNextEntry();
                                if (nextEntry == null) {
                                    break;
                                }
                                final String name = nextEntry.getName();
                                final StringBuilder sb = new StringBuilder();
                                sb.append(file2.getAbsolutePath());
                                sb.append(File.separator);
                                sb.append(name);
                                final File file3 = new File(sb.toString());
                                if (nextEntry.isDirectory()) {
                                    FileUtil.creatOrExistDir(file3);
                                }
                                else {
                                    if (!file3.exists()) {
                                        file3.getParentFile().mkdirs();
                                    }
                                    final byte[] array = new byte[1024];
                                    final FileOutputStream fileOutputStream = new FileOutputStream(file3);
                                    while (true) {
                                        final int read = zipInputStream.read(array);
                                        if (read <= 0) {
                                            break;
                                        }
                                        fileOutputStream.write(array, 0, read);
                                    }
                                    fileOutputStream.close();
                                }
                            }
                            return true;
                        }
                        catch (final IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            }
        }
        return false;
    }
    
    public static boolean unZipFile(final String s, final String s2) {
        return !TextUtils.isEmpty((CharSequence)s) && !TextUtils.isEmpty((CharSequence)s2) && unZipFile(new File(s), new File(s2));
    }
    
    public static boolean zipFile(final File file, final File file2, final boolean b) {
        if (file == null || !file.exists()) {
            return false;
        }
        if (!checkOrCreatZipFile(file2)) {
            return false;
        }
        final ZipOutputStream zipOutputStream = null;
        Object o2;
        final Object o = o2 = null;
        ZipOutputStream zipOutputStream2 = null;
        ZipOutputStream zipOutputStream3;
        try {
            try {
                o2 = o;
                o2 = o;
                final FileOutputStream fileOutputStream = new FileOutputStream(file2);
                o2 = o;
                zipOutputStream2 = new ZipOutputStream((OutputStream)fileOutputStream);
                try {
                    compress(file, zipOutputStream2, "", b);
                    try {
                        zipOutputStream2.close();
                    }
                    catch (final IOException ex) {
                        ex.printStackTrace();
                    }
                    return true;
                }
                catch (final IOException o2) {}
                finally {
                    o2 = zipOutputStream2;
                }
            }
            finally {}
        }
        catch (final IOException zipOutputStream2) {
            zipOutputStream3 = zipOutputStream;
        }
        ((IOException)zipOutputStream2).printStackTrace();
        FileUtil.deleteFile(file2);
        if (zipOutputStream3 != null) {
            try {
                zipOutputStream3.close();
            }
            catch (final IOException ex2) {
                ex2.printStackTrace();
            }
        }
        return false;
        if (o2 != null) {
            try {
                ((ZipOutputStream)o2).close();
            }
            catch (final IOException ex3) {
                ex3.printStackTrace();
            }
        }
    }
    
    public static boolean zipFile(final String s, final String s2, final boolean b) {
        return !TextUtils.isEmpty((CharSequence)s) && !TextUtils.isEmpty((CharSequence)s2) && zipFile(new File(s), new File(s2), b);
    }
    
    public static boolean zipFiles(final List<File> list, String iterator, final boolean b) {
        if (TextUtils.isEmpty((CharSequence)iterator) || list == null || list.isEmpty()) {
            return false;
        }
        final File file = new File((String)iterator);
        if (!checkOrCreatZipFile(file)) {
            return false;
        }
        if (list != null) {
            final ZipOutputStream zipOutputStream = null;
            final IOException ex = iterator = null;
            Object o = null;
            ZipOutputStream zipOutputStream2;
            try {
                try {
                    iterator = ex;
                    iterator = ex;
                    final FileOutputStream fileOutputStream = new FileOutputStream(file);
                    iterator = ex;
                    o = new ZipOutputStream((OutputStream)fileOutputStream);
                    try {
                        iterator = (IOException)list.iterator();
                        while (((Iterator)iterator).hasNext()) {
                            final File file2 = (File)((Iterator)iterator).next();
                            if (file2 != null) {
                                compress(file2, (ZipOutputStream)o, file2.getName(), b);
                            }
                        }
                        try {
                            ((ZipOutputStream)o).close();
                            return true;
                        }
                        catch (final IOException ex2) {
                            ex2.printStackTrace();
                            return true;
                        }
                    }
                    catch (final IOException iterator) {}
                    finally {
                        iterator = (IOException)o;
                    }
                }
                finally {}
            }
            catch (final IOException o) {
                zipOutputStream2 = zipOutputStream;
            }
            ((IOException)o).printStackTrace();
            FileUtil.deleteFile(file);
            if (zipOutputStream2 != null) {
                try {
                    zipOutputStream2.close();
                }
                catch (final IOException ex3) {
                    ex3.printStackTrace();
                }
            }
            return false;
            if (iterator != null) {
                try {
                    ((ZipOutputStream)iterator).close();
                }
                catch (final IOException ex4) {
                    ex4.printStackTrace();
                }
            }
        }
        return true;
    }
}

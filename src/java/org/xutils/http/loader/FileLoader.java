package org.xutils.http.loader;

import org.xutils.http.RequestParams;
import org.xutils.ex.HttpException;
import org.xutils.ex.FileLockedException;
import java.util.Date;
import org.xutils.common.util.ProcessLock;
import org.xutils.common.Callback;
import java.io.OutputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.Closeable;
import java.util.Arrays;
import java.io.FileInputStream;
import org.xutils.common.util.IOUtil;
import java.io.InputStream;
import java.io.IOException;
import org.xutils.cache.LruDiskCache;
import org.xutils.cache.DiskCacheEntity;
import java.io.UnsupportedEncodingException;
import org.xutils.common.util.LogUtil;
import java.net.URLDecoder;
import org.xutils.http.request.UriRequest;
import android.text.TextUtils;
import org.xutils.cache.DiskCacheFile;
import java.io.File;

public class FileLoader extends Loader<File>
{
    private static final int CHECK_SIZE = 512;
    private long contentLength;
    private DiskCacheFile diskCacheFile;
    private boolean isAutoRename;
    private boolean isAutoResume;
    private String responseFileName;
    private String saveFilePath;
    private String tempSaveFilePath;
    
    private File autoRename(final File file) {
        if (this.isAutoRename && file.exists() && !TextUtils.isEmpty((CharSequence)this.responseFileName)) {
            File file2;
            String parent;
            StringBuilder sb;
            for (file2 = new File(file.getParent(), this.responseFileName); file2.exists(); file2 = new File(parent, sb.toString())) {
                parent = file.getParent();
                sb = new StringBuilder();
                sb.append(System.currentTimeMillis());
                sb.append(this.responseFileName);
            }
            File file3 = file;
            if (file.renameTo(file2)) {
                file3 = file2;
            }
            return file3;
        }
        File file4 = file;
        if (!this.saveFilePath.equals((Object)this.tempSaveFilePath)) {
            final File file5 = new File(this.saveFilePath);
            file4 = file;
            if (file.renameTo(file5)) {
                file4 = file5;
            }
        }
        return file4;
    }
    
    private static String getResponseFileName(final UriRequest uriRequest) {
        if (uriRequest == null) {
            return null;
        }
        final String responseHeader = uriRequest.getResponseHeader("Content-Disposition");
        if (!TextUtils.isEmpty((CharSequence)responseHeader)) {
            final int index = responseHeader.indexOf("filename=");
            if (index > 0) {
                final int n = index + 9;
                int n2;
                if ((n2 = responseHeader.indexOf(";", n)) < 0) {
                    n2 = responseHeader.length();
                }
                if (n2 > n) {
                    try {
                        String s2;
                        final String s = s2 = URLDecoder.decode(responseHeader.substring(n, n2), uriRequest.getParams().getCharset());
                        if (s.startsWith("\"")) {
                            s2 = s;
                            if (s.endsWith("\"")) {
                                s2 = s.substring(1, s.length() - 1);
                            }
                        }
                        return s2;
                    }
                    catch (final UnsupportedEncodingException ex) {
                        LogUtil.e(ex.getMessage(), (Throwable)ex);
                    }
                }
            }
        }
        return null;
    }
    
    private void initDiskCacheFile(final UriRequest uriRequest) throws Throwable {
        final DiskCacheEntity diskCacheEntity = new DiskCacheEntity();
        diskCacheEntity.setKey(uriRequest.getCacheKey());
        final DiskCacheFile diskCacheFile = LruDiskCache.getDiskCache(this.params.getCacheDirName()).createDiskCacheFile(diskCacheEntity);
        this.diskCacheFile = diskCacheFile;
        if (diskCacheFile != null) {
            final String absolutePath = diskCacheFile.getAbsolutePath();
            this.saveFilePath = absolutePath;
            this.tempSaveFilePath = absolutePath;
            this.isAutoRename = false;
            return;
        }
        final StringBuilder sb = new StringBuilder();
        sb.append("create cache file error:");
        sb.append(uriRequest.getCacheKey());
        throw new IOException(sb.toString());
    }
    
    private static boolean isSupportRange(final UriRequest uriRequest) {
        final boolean b = false;
        if (uriRequest == null) {
            return false;
        }
        final String responseHeader = uriRequest.getResponseHeader("Accept-Ranges");
        if (responseHeader != null) {
            return responseHeader.contains((CharSequence)"bytes");
        }
        final String responseHeader2 = uriRequest.getResponseHeader("Content-Range");
        boolean b2 = b;
        if (responseHeader2 != null) {
            b2 = b;
            if (responseHeader2.contains((CharSequence)"bytes")) {
                b2 = true;
            }
        }
        return b2;
    }
    
    @Override
    public File load(InputStream inputStream) throws Throwable {
        Object o = null;
        Closeable closeable;
        try {
            final File file = new File(this.tempSaveFilePath);
            if (file.isDirectory()) {
                IOUtil.deleteFileOrDir(file);
            }
            if (!file.exists()) {
                final File parentFile = file.getParentFile();
                if (!parentFile.exists()) {
                    if (!parentFile.mkdirs()) {
                        final StringBuilder sb = new StringBuilder();
                        sb.append("can not create dir: ");
                        sb.append(parentFile.getAbsolutePath());
                        throw new IOException(sb.toString());
                    }
                }
            }
            long length = file.length();
            Label_0267: {
                if (this.isAutoResume && length > 0L) {
                    final long n = length - 512L;
                    Label_0238: {
                        if (n <= 0L) {
                            break Label_0238;
                        }
                        try {
                            final FileInputStream fileInputStream = new FileInputStream(file);
                            try {
                                if (Arrays.equals(IOUtil.readBytes(inputStream, 0L, 512), IOUtil.readBytes((InputStream)fileInputStream, n, 512))) {
                                    this.contentLength -= 512L;
                                    IOUtil.closeQuietly((Closeable)fileInputStream);
                                    break Label_0267;
                                }
                                IOUtil.closeQuietly((Closeable)fileInputStream);
                                IOUtil.deleteFileOrDir(file);
                                throw new RuntimeException("need retry");
                            }
                            finally {
                                break Label_0238;
                            }
                            IOUtil.deleteFileOrDir(file);
                            throw new RuntimeException("need retry");
                        }
                        finally {
                            inputStream = null;
                        }
                    }
                    IOUtil.closeQuietly((Closeable)inputStream);
                }
            }
            FileOutputStream fileOutputStream;
            if (this.isAutoResume) {
                fileOutputStream = new FileOutputStream(file, true);
            }
            else {
                fileOutputStream = new FileOutputStream(file);
                length = 0L;
            }
            final long n2 = this.contentLength + length;
            final BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
            try {
                o = new BufferedOutputStream((OutputStream)fileOutputStream);
                try {
                    if (this.progressHandler != null && !this.progressHandler.updateProgress(n2, length, true)) {
                        throw new Callback.CancelledException("download stopped!");
                    }
                    final byte[] array = new byte[4096];
                    while (true) {
                        final int read = bufferedInputStream.read(array);
                        if (read == -1) {
                            ((BufferedOutputStream)o).flush();
                            File commit = file;
                            if (this.diskCacheFile != null) {
                                commit = this.diskCacheFile.commit();
                            }
                            if (this.progressHandler != null) {
                                this.progressHandler.updateProgress(n2, length, true);
                            }
                            IOUtil.closeQuietly((Closeable)bufferedInputStream);
                            IOUtil.closeQuietly((Closeable)o);
                            return this.autoRename(commit);
                        }
                        if (!file.getParentFile().exists()) {
                            file.getParentFile().mkdirs();
                            throw new IOException("parent be deleted!");
                        }
                        ((BufferedOutputStream)o).write(array, 0, read);
                        final long n3 = length += read;
                        if (this.progressHandler == null) {
                            continue;
                        }
                        if (!this.progressHandler.updateProgress(n2, n3, false)) {
                            ((BufferedOutputStream)o).flush();
                            throw new Callback.CancelledException("download stopped!");
                        }
                        length = n3;
                    }
                }
                finally {}
            }
            finally {}
            o = bufferedInputStream;
        }
        finally {
            closeable = null;
        }
        IOUtil.closeQuietly((Closeable)o);
        IOUtil.closeQuietly(closeable);
    }
    
    @Override
    public File load(UriRequest uriRequest) throws Throwable {
        final Closeable closeable = null;
        Object o2;
        final Object o = o2 = null;
        Object o3 = closeable;
        Label_1282: {
            try {
                final String saveFilePath = this.params.getSaveFilePath();
                o2 = o;
                o3 = closeable;
                this.saveFilePath = saveFilePath;
                o2 = o;
                o3 = closeable;
                this.diskCacheFile = null;
                o2 = o;
                o3 = closeable;
                if (TextUtils.isEmpty((CharSequence)saveFilePath)) {
                    o2 = o;
                    o3 = closeable;
                    if (this.progressHandler != null) {
                        o2 = o;
                        o3 = closeable;
                        if (!this.progressHandler.updateProgress(0L, 0L, false)) {
                            o2 = o;
                            o3 = closeable;
                            o2 = o;
                            o3 = closeable;
                            final Callback.CancelledException ex = new Callback.CancelledException("download stopped!");
                            o2 = o;
                            o3 = closeable;
                            throw ex;
                        }
                    }
                    o2 = o;
                    o3 = closeable;
                    this.initDiskCacheFile(uriRequest);
                }
                else {
                    o2 = o;
                    o3 = closeable;
                    o2 = o;
                    o3 = closeable;
                    final StringBuilder sb = new StringBuilder();
                    o2 = o;
                    o3 = closeable;
                    sb.append(this.saveFilePath);
                    o2 = o;
                    o3 = closeable;
                    sb.append(".tmp");
                    o2 = o;
                    o3 = closeable;
                    this.tempSaveFilePath = sb.toString();
                }
                o2 = o;
                o3 = closeable;
                if (this.progressHandler != null) {
                    o2 = o;
                    o3 = closeable;
                    if (!this.progressHandler.updateProgress(0L, 0L, false)) {
                        o2 = o;
                        o3 = closeable;
                        o2 = o;
                        o3 = closeable;
                        final Callback.CancelledException ex2 = new Callback.CancelledException("download stopped!");
                        o2 = o;
                        o3 = closeable;
                        throw ex2;
                    }
                }
                o2 = o;
                o3 = closeable;
                o2 = o;
                o3 = closeable;
                final StringBuilder sb2 = new StringBuilder();
                o2 = o;
                o3 = closeable;
                sb2.append(this.saveFilePath);
                o2 = o;
                o3 = closeable;
                sb2.append("_lock");
                o2 = o;
                o3 = closeable;
                final ProcessLock tryLock = ProcessLock.tryLock(sb2.toString(), true);
                if (tryLock != null) {
                    o2 = tryLock;
                    o3 = tryLock;
                    if (tryLock.isValid()) {
                        o2 = tryLock;
                        o3 = tryLock;
                        this.params = uriRequest.getParams();
                        o2 = tryLock;
                        o3 = tryLock;
                        final boolean isAutoResume = this.isAutoResume;
                        long n = 0L;
                        if (isAutoResume) {
                            o2 = tryLock;
                            o3 = tryLock;
                            o2 = tryLock;
                            o3 = tryLock;
                            final File file = new File(this.tempSaveFilePath);
                            o2 = tryLock;
                            o3 = tryLock;
                            final long length = file.length();
                            if (length <= 512L) {
                                o2 = tryLock;
                                o3 = tryLock;
                                IOUtil.deleteFileOrDir(file);
                                n = n;
                            }
                            else {
                                n = length - 512L;
                            }
                        }
                        o2 = tryLock;
                        o3 = tryLock;
                        final RequestParams params = this.params;
                        o2 = tryLock;
                        o3 = tryLock;
                        o2 = tryLock;
                        o3 = tryLock;
                        final StringBuilder sb3 = new StringBuilder();
                        o2 = tryLock;
                        o3 = tryLock;
                        sb3.append("bytes=");
                        o2 = tryLock;
                        o3 = tryLock;
                        sb3.append(n);
                        o2 = tryLock;
                        o3 = tryLock;
                        sb3.append("-");
                        o2 = tryLock;
                        o3 = tryLock;
                        params.setHeader("RANGE", sb3.toString());
                        o2 = tryLock;
                        o3 = tryLock;
                        if (this.progressHandler != null) {
                            o2 = tryLock;
                            o3 = tryLock;
                            if (!this.progressHandler.updateProgress(0L, 0L, false)) {
                                o2 = tryLock;
                                o3 = tryLock;
                                o2 = tryLock;
                                o3 = tryLock;
                                final Callback.CancelledException ex3 = new Callback.CancelledException("download stopped!");
                                o2 = tryLock;
                                o3 = tryLock;
                                throw ex3;
                            }
                        }
                        o2 = tryLock;
                        o3 = tryLock;
                        uriRequest.sendRequest();
                        o2 = tryLock;
                        o3 = tryLock;
                        this.contentLength = uriRequest.getContentLength();
                        o2 = tryLock;
                        o3 = tryLock;
                        if (this.isAutoRename) {
                            o2 = tryLock;
                            o3 = tryLock;
                            this.responseFileName = getResponseFileName(uriRequest);
                        }
                        o2 = tryLock;
                        o3 = tryLock;
                        if (this.isAutoResume) {
                            o2 = tryLock;
                            o3 = tryLock;
                            this.isAutoResume = isSupportRange(uriRequest);
                        }
                        o2 = tryLock;
                        o3 = tryLock;
                        if (this.progressHandler != null) {
                            o2 = tryLock;
                            o3 = tryLock;
                            if (!this.progressHandler.updateProgress(0L, 0L, false)) {
                                o2 = tryLock;
                                o3 = tryLock;
                                o2 = tryLock;
                                o3 = tryLock;
                                final Callback.CancelledException ex4 = new Callback.CancelledException("download stopped!");
                                o2 = tryLock;
                                o3 = tryLock;
                                throw ex4;
                            }
                        }
                        o2 = tryLock;
                        o3 = tryLock;
                        if (this.diskCacheFile != null) {
                            o2 = tryLock;
                            o3 = tryLock;
                            final DiskCacheEntity cacheEntity = this.diskCacheFile.getCacheEntity();
                            o2 = tryLock;
                            o3 = tryLock;
                            cacheEntity.setLastAccess(System.currentTimeMillis());
                            o2 = tryLock;
                            o3 = tryLock;
                            cacheEntity.setEtag(uriRequest.getETag());
                            o2 = tryLock;
                            o3 = tryLock;
                            cacheEntity.setExpires(uriRequest.getExpiration());
                            o2 = tryLock;
                            o3 = tryLock;
                            o2 = tryLock;
                            o3 = tryLock;
                            final Date lastModify = new Date(uriRequest.getLastModified());
                            o2 = tryLock;
                            o3 = tryLock;
                            cacheEntity.setLastModify(lastModify);
                        }
                        o2 = tryLock;
                        o3 = tryLock;
                        final Object load = this.load(uriRequest.getInputStream());
                        o3 = tryLock;
                        uriRequest = (UriRequest)load;
                        break Label_1282;
                    }
                }
                o2 = tryLock;
                o3 = tryLock;
                o2 = tryLock;
                o3 = tryLock;
                o2 = tryLock;
                o3 = tryLock;
                final StringBuilder sb4 = new StringBuilder();
                o2 = tryLock;
                o3 = tryLock;
                sb4.append("download exists: ");
                o2 = tryLock;
                o3 = tryLock;
                sb4.append(this.saveFilePath);
                o2 = tryLock;
                o3 = tryLock;
                final FileLockedException ex5 = new FileLockedException(sb4.toString());
                o2 = tryLock;
                o3 = tryLock;
                throw ex5;
            }
            catch (final HttpException ex6) {
                o2 = o3;
                if (ex6.getCode() == 416) {
                    o2 = o3;
                    File commit;
                    if (this.diskCacheFile != null) {
                        o2 = o3;
                        commit = this.diskCacheFile.commit();
                    }
                    else {
                        o2 = o3;
                        commit = new File(this.tempSaveFilePath);
                    }
                    if (commit != null) {
                        o2 = o3;
                        if (commit.exists()) {
                            o2 = o3;
                            if (this.isAutoRename) {
                                o2 = o3;
                                this.responseFileName = getResponseFileName(uriRequest);
                            }
                            o2 = o3;
                            final File autoRename = this.autoRename(commit);
                            IOUtil.closeQuietly((Closeable)o3);
                            IOUtil.closeQuietly((Closeable)this.diskCacheFile);
                            return autoRename;
                        }
                    }
                    o2 = o3;
                    IOUtil.deleteFileOrDir(commit);
                    o2 = o3;
                    o2 = o3;
                    o2 = o3;
                    final StringBuilder sb5 = new StringBuilder();
                    o2 = o3;
                    sb5.append("cache file not found");
                    o2 = o3;
                    sb5.append(uriRequest.getCacheKey());
                    o2 = o3;
                    final IllegalStateException ex7 = new IllegalStateException(sb5.toString());
                    o2 = o3;
                    throw ex7;
                }
                o2 = o3;
                throw ex6;
            }
        }
        IOUtil.closeQuietly((Closeable)o2);
        IOUtil.closeQuietly((Closeable)this.diskCacheFile);
    }
    
    @Override
    public File loadFromCache(final DiskCacheEntity diskCacheEntity) throws Throwable {
        return LruDiskCache.getDiskCache(this.params.getCacheDirName()).getDiskCacheFile(diskCacheEntity.getKey());
    }
    
    @Override
    public Loader<File> newInstance() {
        return new FileLoader();
    }
    
    @Override
    public void save2Cache(final UriRequest uriRequest) {
    }
    
    @Override
    public void setParams(final RequestParams params) {
        if (params != null) {
            this.params = params;
            this.isAutoResume = params.isAutoResume();
            this.isAutoRename = params.isAutoRename();
        }
    }
}

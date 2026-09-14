package org.eclipse.paho.client.mqttv3.internal;

import java.io.IOException;
import java.io.File;
import java.io.RandomAccessFile;

public class FileLock
{
    private RandomAccessFile file;
    private Object fileLock;
    private File lockFile;
    
    public FileLock(final File file, final String s) throws Exception {
        this.lockFile = new File(file, s);
        if (ExceptionHelper.isClassAvailable("java.nio.channels.FileLock")) {
            try {
                final RandomAccessFile file2 = new RandomAccessFile(this.lockFile, "rw");
                this.file = file2;
                final Object invoke = file2.getClass().getMethod("getChannel", (Class<?>[])new Class[0]).invoke((Object)this.file, new Object[0]);
                this.fileLock = invoke.getClass().getMethod("tryLock", (Class<?>[])new Class[0]).invoke(invoke, new Object[0]);
            }
            catch (final IllegalAccessException ex) {
                this.fileLock = null;
            }
            catch (final IllegalArgumentException ex2) {
                this.fileLock = null;
            }
            catch (final NoSuchMethodException ex3) {
                this.fileLock = null;
            }
            if (this.fileLock == null) {
                this.release();
                throw new Exception("Problem obtaining file lock");
            }
        }
    }
    
    public void release() {
        try {
            if (this.fileLock != null) {
                this.fileLock.getClass().getMethod("release", (Class<?>[])new Class[0]).invoke(this.fileLock, new Object[0]);
                this.fileLock = null;
            }
        }
        catch (final Exception ex) {}
        final RandomAccessFile file = this.file;
        Label_0062: {
            if (file == null) {
                break Label_0062;
            }
            while (true) {
                try {
                    file.close();
                    this.file = null;
                    final File lockFile = this.lockFile;
                    if (lockFile != null && lockFile.exists()) {
                        this.lockFile.delete();
                    }
                    this.lockFile = null;
                }
                catch (final IOException ex2) {
                    continue;
                }
                break;
            }
        }
    }
}

package org.eclipse.paho.client.mqttv3.persist;

import java.io.FileOutputStream;
import java.util.Vector;
import java.util.Enumeration;
import java.io.IOException;
import org.eclipse.paho.client.mqttv3.internal.MqttPersistentData;
import java.io.FileInputStream;
import org.eclipse.paho.client.mqttv3.MqttPersistable;
import java.io.FileFilter;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;
import org.eclipse.paho.client.mqttv3.internal.FileLock;
import java.io.File;
import java.io.FilenameFilter;
import org.eclipse.paho.client.mqttv3.MqttClientPersistence;

public class MqttDefaultFilePersistence implements MqttClientPersistence
{
    private static FilenameFilter FILENAME_FILTER;
    private static final String LOCK_FILENAME = ".lck";
    private static final String MESSAGE_BACKUP_FILE_EXTENSION = ".bup";
    private static final String MESSAGE_FILE_EXTENSION = ".msg";
    private File clientDir;
    private File dataDir;
    private FileLock fileLock;
    
    public MqttDefaultFilePersistence() {
        this(System.getProperty("user.dir"));
    }
    
    public MqttDefaultFilePersistence(final String s) {
        this.clientDir = null;
        this.fileLock = null;
        this.dataDir = new File(s);
    }
    
    private void checkIsOpen() throws MqttPersistenceException {
        if (this.clientDir != null) {
            return;
        }
        throw new MqttPersistenceException();
    }
    
    private static FilenameFilter getFilenameFilter() {
        if (MqttDefaultFilePersistence.FILENAME_FILTER == null) {
            MqttDefaultFilePersistence.FILENAME_FILTER = (FilenameFilter)new PersistanceFileNameFilter(".msg");
        }
        return MqttDefaultFilePersistence.FILENAME_FILTER;
    }
    
    private File[] getFiles() throws MqttPersistenceException {
        this.checkIsOpen();
        final File[] listFiles = this.clientDir.listFiles(getFilenameFilter());
        if (listFiles != null) {
            return listFiles;
        }
        throw new MqttPersistenceException();
    }
    
    private boolean isSafeChar(final char c) {
        return Character.isJavaIdentifierPart(c) || c == '-';
    }
    
    private void restoreBackups(final File file) throws MqttPersistenceException {
        final File[] listFiles = file.listFiles((FileFilter)new PersistanceFileFilter(".bup"));
        if (listFiles != null) {
            for (final File file2 : listFiles) {
                final File file3 = new File(file, file2.getName().substring(0, file2.getName().length() - 4));
                if (!file2.renameTo(file3)) {
                    file3.delete();
                    file2.renameTo(file3);
                }
            }
            return;
        }
        throw new MqttPersistenceException();
    }
    
    @Override
    public void clear() throws MqttPersistenceException {
        this.checkIsOpen();
        final File[] files = this.getFiles();
        for (int length = files.length, i = 0; i < length; ++i) {
            files[i].delete();
        }
        this.clientDir.delete();
    }
    
    @Override
    public void close() throws MqttPersistenceException {
        synchronized (this) {
            if (this.fileLock != null) {
                this.fileLock.release();
            }
            if (this.getFiles().length == 0) {
                this.clientDir.delete();
            }
            this.clientDir = null;
        }
    }
    
    @Override
    public boolean containsKey(final String s) throws MqttPersistenceException {
        this.checkIsOpen();
        final File clientDir = this.clientDir;
        final StringBuilder sb = new StringBuilder(String.valueOf((Object)s));
        sb.append(".msg");
        return new File(clientDir, sb.toString()).exists();
    }
    
    @Override
    public MqttPersistable get(final String s) throws MqttPersistenceException {
        this.checkIsOpen();
        try {
            final File clientDir = this.clientDir;
            final StringBuilder sb = new StringBuilder(String.valueOf((Object)s));
            sb.append(".msg");
            final FileInputStream fileInputStream = new FileInputStream(new File(clientDir, sb.toString()));
            final int available = fileInputStream.available();
            final byte[] array = new byte[available];
            for (int i = 0; i < available; i += fileInputStream.read(array, i, available - i)) {}
            fileInputStream.close();
            return new MqttPersistentData(s, array, 0, available, null, 0, 0);
        }
        catch (final IOException ex) {
            throw new MqttPersistenceException((Throwable)ex);
        }
    }
    
    @Override
    public Enumeration<String> keys() throws MqttPersistenceException {
        this.checkIsOpen();
        final File[] files = this.getFiles();
        final Vector vector = new Vector(files.length);
        for (int length = files.length, i = 0; i < length; ++i) {
            final String name = files[i].getName();
            vector.addElement((Object)name.substring(0, name.length() - 4));
        }
        return (Enumeration<String>)vector.elements();
    }
    
    @Override
    public void open(final String p0, final String p1) throws MqttPersistenceException {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: getfield        org/eclipse/paho/client/mqttv3/persist/MqttDefaultFilePersistence.dataDir:Ljava/io/File;
        //     4: invokevirtual   java/io/File.exists:()Z
        //     7: ifeq            31
        //    10: aload_0        
        //    11: getfield        org/eclipse/paho/client/mqttv3/persist/MqttDefaultFilePersistence.dataDir:Ljava/io/File;
        //    14: invokevirtual   java/io/File.isDirectory:()Z
        //    17: ifeq            23
        //    20: goto            31
        //    23: new             Lorg/eclipse/paho/client/mqttv3/MqttPersistenceException;
        //    26: dup            
        //    27: invokespecial   org/eclipse/paho/client/mqttv3/MqttPersistenceException.<init>:()V
        //    30: athrow         
        //    31: aload_0        
        //    32: getfield        org/eclipse/paho/client/mqttv3/persist/MqttDefaultFilePersistence.dataDir:Ljava/io/File;
        //    35: invokevirtual   java/io/File.exists:()Z
        //    38: ifne            62
        //    41: aload_0        
        //    42: getfield        org/eclipse/paho/client/mqttv3/persist/MqttDefaultFilePersistence.dataDir:Ljava/io/File;
        //    45: invokevirtual   java/io/File.mkdirs:()Z
        //    48: ifeq            54
        //    51: goto            62
        //    54: new             Lorg/eclipse/paho/client/mqttv3/MqttPersistenceException;
        //    57: dup            
        //    58: invokespecial   org/eclipse/paho/client/mqttv3/MqttPersistenceException.<init>:()V
        //    61: athrow         
        //    62: aload_0        
        //    63: getfield        org/eclipse/paho/client/mqttv3/persist/MqttDefaultFilePersistence.dataDir:Ljava/io/File;
        //    66: invokevirtual   java/io/File.canWrite:()Z
        //    69: ifeq            275
        //    72: new             Ljava/lang/StringBuffer;
        //    75: dup            
        //    76: invokespecial   java/lang/StringBuffer.<init>:()V
        //    79: astore          6
        //    81: iconst_0       
        //    82: istore          5
        //    84: iconst_0       
        //    85: istore          4
        //    87: iload           4
        //    89: aload_1        
        //    90: invokevirtual   java/lang/String.length:()I
        //    93: if_icmplt       247
        //    96: aload           6
        //    98: ldc             "-"
        //   100: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   103: pop            
        //   104: iload           5
        //   106: istore          4
        //   108: iload           4
        //   110: aload_2        
        //   111: invokevirtual   java/lang/String.length:()I
        //   114: if_icmplt       219
        //   117: aload_0        
        //   118: dup            
        //   119: astore          7
        //   121: monitorenter   
        //   122: aload_0        
        //   123: getfield        org/eclipse/paho/client/mqttv3/persist/MqttDefaultFilePersistence.clientDir:Ljava/io/File;
        //   126: ifnonnull       168
        //   129: aload           6
        //   131: invokevirtual   java/lang/StringBuffer.toString:()Ljava/lang/String;
        //   134: astore_2       
        //   135: new             Ljava/io/File;
        //   138: astore_1       
        //   139: aload_1        
        //   140: aload_0        
        //   141: getfield        org/eclipse/paho/client/mqttv3/persist/MqttDefaultFilePersistence.dataDir:Ljava/io/File;
        //   144: aload_2        
        //   145: invokespecial   java/io/File.<init>:(Ljava/io/File;Ljava/lang/String;)V
        //   148: aload_0        
        //   149: aload_1        
        //   150: putfield        org/eclipse/paho/client/mqttv3/persist/MqttDefaultFilePersistence.clientDir:Ljava/io/File;
        //   153: aload_1        
        //   154: invokevirtual   java/io/File.exists:()Z
        //   157: ifne            168
        //   160: aload_0        
        //   161: getfield        org/eclipse/paho/client/mqttv3/persist/MqttDefaultFilePersistence.clientDir:Ljava/io/File;
        //   164: invokevirtual   java/io/File.mkdir:()Z
        //   167: pop            
        //   168: aload_0        
        //   169: getfield        org/eclipse/paho/client/mqttv3/persist/MqttDefaultFilePersistence.fileLock:Lorg/eclipse/paho/client/mqttv3/internal/FileLock;
        //   172: ifnull          182
        //   175: aload_0        
        //   176: getfield        org/eclipse/paho/client/mqttv3/persist/MqttDefaultFilePersistence.fileLock:Lorg/eclipse/paho/client/mqttv3/internal/FileLock;
        //   179: invokevirtual   org/eclipse/paho/client/mqttv3/internal/FileLock.release:()V
        //   182: new             Lorg/eclipse/paho/client/mqttv3/internal/FileLock;
        //   185: astore_1       
        //   186: aload_1        
        //   187: aload_0        
        //   188: getfield        org/eclipse/paho/client/mqttv3/persist/MqttDefaultFilePersistence.clientDir:Ljava/io/File;
        //   191: ldc             ".lck"
        //   193: invokespecial   org/eclipse/paho/client/mqttv3/internal/FileLock.<init>:(Ljava/io/File;Ljava/lang/String;)V
        //   196: aload_0        
        //   197: aload_1        
        //   198: putfield        org/eclipse/paho/client/mqttv3/persist/MqttDefaultFilePersistence.fileLock:Lorg/eclipse/paho/client/mqttv3/internal/FileLock;
        //   201: aload_0        
        //   202: aload_0        
        //   203: getfield        org/eclipse/paho/client/mqttv3/persist/MqttDefaultFilePersistence.clientDir:Ljava/io/File;
        //   206: invokespecial   org/eclipse/paho/client/mqttv3/persist/MqttDefaultFilePersistence.restoreBackups:(Ljava/io/File;)V
        //   209: aload           7
        //   211: monitorexit    
        //   212: return         
        //   213: astore_1       
        //   214: aload           7
        //   216: monitorexit    
        //   217: aload_1        
        //   218: athrow         
        //   219: aload_2        
        //   220: iload           4
        //   222: invokevirtual   java/lang/String.charAt:(I)C
        //   225: istore_3       
        //   226: aload_0        
        //   227: iload_3        
        //   228: invokespecial   org/eclipse/paho/client/mqttv3/persist/MqttDefaultFilePersistence.isSafeChar:(C)Z
        //   231: ifeq            241
        //   234: aload           6
        //   236: iload_3        
        //   237: invokevirtual   java/lang/StringBuffer.append:(C)Ljava/lang/StringBuffer;
        //   240: pop            
        //   241: iinc            4, 1
        //   244: goto            108
        //   247: aload_1        
        //   248: iload           4
        //   250: invokevirtual   java/lang/String.charAt:(I)C
        //   253: istore_3       
        //   254: aload_0        
        //   255: iload_3        
        //   256: invokespecial   org/eclipse/paho/client/mqttv3/persist/MqttDefaultFilePersistence.isSafeChar:(C)Z
        //   259: ifeq            269
        //   262: aload           6
        //   264: iload_3        
        //   265: invokevirtual   java/lang/StringBuffer.append:(C)Ljava/lang/StringBuffer;
        //   268: pop            
        //   269: iinc            4, 1
        //   272: goto            87
        //   275: new             Lorg/eclipse/paho/client/mqttv3/MqttPersistenceException;
        //   278: dup            
        //   279: invokespecial   org/eclipse/paho/client/mqttv3/MqttPersistenceException.<init>:()V
        //   282: athrow         
        //   283: astore_1       
        //   284: goto            201
        //    Exceptions:
        //  throws org.eclipse.paho.client.mqttv3.MqttPersistenceException
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  122    168    213    219    Any
        //  168    182    283    287    Ljava/lang/Exception;
        //  168    182    213    219    Any
        //  182    201    283    287    Ljava/lang/Exception;
        //  182    201    213    219    Any
        //  201    212    213    219    Any
        //  214    217    213    219    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0168:
        //     at q5.p.i(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:150)
        //     at q5.p.k(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:470)
        //     at u5.m.d(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:30)
        //     at u5.i.g(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:23)
        //     at u5.i.f(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:159)
        //     at u5.i.j(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:619)
        //     at u5.i.k(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:13)
        //     at u5.i.i(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:29)
        //     at s5.b.a(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:90)
        //     at com.thesourceofcode.jadec.decompilers.JavaExtractionWorker.decompileWithProcyon(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:367)
        //     at com.thesourceofcode.jadec.decompilers.JavaExtractionWorker.doWork(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:162)
        //     at com.thesourceofcode.jadec.decompilers.BaseDecompiler.withAttempt(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:3)
        //     at z6.a.run(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:31)
        //     at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1167)
        //     at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:641)
        //     at java.lang.Thread.run(Thread.java:920)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @Override
    public void put(String s, final MqttPersistable mqttPersistable) throws MqttPersistenceException {
        this.checkIsOpen();
        final File clientDir = this.clientDir;
        final StringBuilder sb = new StringBuilder(String.valueOf((Object)s));
        sb.append(".msg");
        final File file = new File(clientDir, sb.toString());
        final File clientDir2 = this.clientDir;
        final StringBuilder sb2 = new StringBuilder(String.valueOf((Object)s));
        sb2.append(".msg");
        sb2.append(".bup");
        s = (String)new File(clientDir2, sb2.toString());
        Label_0115: {
            if (!file.exists() || file.renameTo((File)s)) {
                break Label_0115;
            }
            ((File)s).delete();
            file.renameTo((File)s);
            try {
                try {
                    final FileOutputStream fileOutputStream = new FileOutputStream(file);
                    fileOutputStream.write(mqttPersistable.getHeaderBytes(), mqttPersistable.getHeaderOffset(), mqttPersistable.getHeaderLength());
                    if (mqttPersistable.getPayloadBytes() != null) {
                        fileOutputStream.write(mqttPersistable.getPayloadBytes(), mqttPersistable.getPayloadOffset(), mqttPersistable.getPayloadLength());
                    }
                    fileOutputStream.getFD().sync();
                    fileOutputStream.close();
                    if (((File)s).exists()) {
                        ((File)s).delete();
                    }
                    if (((File)s).exists() && !((File)s).renameTo(file)) {
                        file.delete();
                        ((File)s).renameTo(file);
                    }
                }
                finally {
                    if (((File)s).exists() && !((File)s).renameTo(file)) {
                        file.delete();
                        ((File)s).renameTo(file);
                    }
                }
            }
            catch (final IOException ex) {}
        }
    }
    
    @Override
    public void remove(final String s) throws MqttPersistenceException {
        this.checkIsOpen();
        final File clientDir = this.clientDir;
        final StringBuilder sb = new StringBuilder(String.valueOf((Object)s));
        sb.append(".msg");
        final File file = new File(clientDir, sb.toString());
        if (file.exists()) {
            file.delete();
        }
    }
}

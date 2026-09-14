package com.otaliastudios.cameraview.internal;

import com.otaliastudios.cameraview.size.Size;
import android.media.MediaCodecList;
import java.util.Iterator;
import java.util.Collections;
import java.util.Comparator;
import java.util.ArrayList;
import java.util.List;
import android.os.Build$VERSION;
import android.media.MediaCodecInfo$VideoCapabilities;
import android.media.MediaCodecInfo;
import android.media.MediaCodecInfo$AudioCapabilities;
import com.otaliastudios.cameraview.CameraLogger;

public class DeviceEncoders
{
    static boolean ENABLED = false;
    private static final CameraLogger LOG;
    public static final int MODE_PREFER_HARDWARE = 1;
    public static final int MODE_RESPECT_ORDER = 0;
    private static final String TAG;
    private final MediaCodecInfo$AudioCapabilities mAudioCapabilities;
    private final MediaCodecInfo mAudioEncoder;
    private final MediaCodecInfo$VideoCapabilities mVideoCapabilities;
    private final MediaCodecInfo mVideoEncoder;
    
    static {
        LOG = CameraLogger.create(TAG = DeviceEncoders.class.getSimpleName());
        DeviceEncoders.ENABLED = (Build$VERSION.SDK_INT >= 21);
    }
    
    public DeviceEncoders(final int n, final String s, final String s2, final int n2, final int n3) {
        if (DeviceEncoders.ENABLED) {
            final List<MediaCodecInfo> deviceEncoders = this.getDeviceEncoders();
            final MediaCodecInfo deviceEncoder = this.findDeviceEncoder(deviceEncoders, s, n, n2);
            this.mVideoEncoder = deviceEncoder;
            DeviceEncoders.LOG.i(new Object[] { "Enabled. Found video encoder:", deviceEncoder.getName() });
            final MediaCodecInfo deviceEncoder2 = this.findDeviceEncoder(deviceEncoders, s2, n, n3);
            this.mAudioEncoder = deviceEncoder2;
            DeviceEncoders.LOG.i(new Object[] { "Enabled. Found audio encoder:", deviceEncoder2.getName() });
            this.mVideoCapabilities = this.mVideoEncoder.getCapabilitiesForType(s).getVideoCapabilities();
            this.mAudioCapabilities = this.mAudioEncoder.getCapabilitiesForType(s2).getAudioCapabilities();
        }
        else {
            this.mVideoEncoder = null;
            this.mAudioEncoder = null;
            this.mVideoCapabilities = null;
            this.mAudioCapabilities = null;
            DeviceEncoders.LOG.i(new Object[] { "Disabled." });
        }
    }
    
    MediaCodecInfo findDeviceEncoder(final List<MediaCodecInfo> list, final String s, final int n, final int n2) {
        final ArrayList list2 = new ArrayList();
        final Iterator iterator = list.iterator();
        while (true) {
            final boolean hasNext = iterator.hasNext();
            int i = 0;
            if (!hasNext) {
                break;
            }
            final MediaCodecInfo mediaCodecInfo = (MediaCodecInfo)iterator.next();
            for (String[] supportedTypes = mediaCodecInfo.getSupportedTypes(); i < supportedTypes.length; ++i) {
                if (supportedTypes[i].equalsIgnoreCase(s)) {
                    list2.add((Object)mediaCodecInfo);
                    break;
                }
            }
        }
        DeviceEncoders.LOG.i(new Object[] { "findDeviceEncoder -", "type:", s, "encoders:", list2.size() });
        if (n == 1) {
            Collections.sort((List)list2, (Comparator)new Comparator<MediaCodecInfo>(this) {
                final DeviceEncoders this$0;
                
                public int compare(final MediaCodecInfo mediaCodecInfo, final MediaCodecInfo mediaCodecInfo2) {
                    return Boolean.compare(this.this$0.isHardwareEncoder(mediaCodecInfo2.getName()), this.this$0.isHardwareEncoder(mediaCodecInfo.getName()));
                }
            });
        }
        if (list2.size() >= n2 + 1) {
            return (MediaCodecInfo)list2.get(n2);
        }
        final StringBuilder sb = new StringBuilder();
        sb.append("No encoders for type:");
        sb.append(s);
        throw new RuntimeException(sb.toString());
    }
    
    public String getAudioEncoder() {
        final MediaCodecInfo mAudioEncoder = this.mAudioEncoder;
        if (mAudioEncoder != null) {
            return mAudioEncoder.getName();
        }
        return null;
    }
    
    List<MediaCodecInfo> getDeviceEncoders() {
        final ArrayList list = new ArrayList();
        int i = 0;
        for (MediaCodecInfo[] codecInfos = new MediaCodecList(0).getCodecInfos(); i < codecInfos.length; ++i) {
            final MediaCodecInfo mediaCodecInfo = codecInfos[i];
            if (mediaCodecInfo.isEncoder()) {
                list.add((Object)mediaCodecInfo);
            }
        }
        return (List<MediaCodecInfo>)list;
    }
    
    public int getSupportedAudioBitRate(final int n) {
        if (!DeviceEncoders.ENABLED) {
            return n;
        }
        final int intValue = (int)this.mAudioCapabilities.getBitrateRange().clamp((Comparable)n);
        DeviceEncoders.LOG.i(new Object[] { "getSupportedAudioBitRate -", "inputRate:", n, "adjustedRate:", intValue });
        return intValue;
    }
    
    public int getSupportedVideoBitRate(final int n) {
        if (!DeviceEncoders.ENABLED) {
            return n;
        }
        final int intValue = (int)this.mVideoCapabilities.getBitrateRange().clamp((Comparable)n);
        DeviceEncoders.LOG.i(new Object[] { "getSupportedVideoBitRate -", "inputRate:", n, "adjustedRate:", intValue });
        return intValue;
    }
    
    public int getSupportedVideoFrameRate(final Size size, final int n) {
        if (!DeviceEncoders.ENABLED) {
            return n;
        }
        final int n2 = (int)(double)this.mVideoCapabilities.getSupportedFrameRatesFor(size.getWidth(), size.getHeight()).clamp((Comparable)(double)n);
        DeviceEncoders.LOG.i(new Object[] { "getSupportedVideoFrameRate -", "inputRate:", n, "adjustedRate:", n2 });
        return n2;
    }
    
    public Size getSupportedVideoSize(Size supportedVideoSize) {
        if (!DeviceEncoders.ENABLED) {
            return supportedVideoSize;
        }
        final int width = supportedVideoSize.getWidth();
        int height = supportedVideoSize.getHeight();
        final double n = width / (double)height;
        DeviceEncoders.LOG.i(new Object[] { "getSupportedVideoSize - started. width:", width, "height:", height });
        int intValue;
        if ((int)this.mVideoCapabilities.getSupportedWidths().getUpper() < (intValue = width)) {
            intValue = (int)this.mVideoCapabilities.getSupportedWidths().getUpper();
            height = (int)Math.round(intValue / n);
            DeviceEncoders.LOG.i(new Object[] { "getSupportedVideoSize - exceeds maxWidth! width:", intValue, "height:", height });
        }
        int intValue2;
        if ((int)this.mVideoCapabilities.getSupportedHeights().getUpper() < (intValue2 = height)) {
            intValue2 = (int)this.mVideoCapabilities.getSupportedHeights().getUpper();
            intValue = (int)Math.round(intValue2 * n);
            DeviceEncoders.LOG.i(new Object[] { "getSupportedVideoSize - exceeds maxHeight! width:", intValue, "height:", intValue2 });
        }
        int n2;
        while (true) {
            n2 = intValue2;
            if (intValue % this.mVideoCapabilities.getWidthAlignment() == 0) {
                break;
            }
            --intValue;
        }
        while (n2 % this.mVideoCapabilities.getHeightAlignment() != 0) {
            --n2;
        }
        DeviceEncoders.LOG.i(new Object[] { "getSupportedVideoSize - aligned. width:", intValue, "height:", n2 });
        if (!this.mVideoCapabilities.getSupportedWidths().contains((Comparable)intValue)) {
            final StringBuilder sb = new StringBuilder();
            sb.append("Width not supported after adjustment. Desired:");
            sb.append(intValue);
            sb.append(" Range:");
            sb.append((Object)this.mVideoCapabilities.getSupportedWidths());
            throw new VideoException(sb.toString());
        }
        if (!this.mVideoCapabilities.getSupportedHeights().contains((Comparable)n2)) {
            final StringBuilder sb2 = new StringBuilder();
            sb2.append("Height not supported after adjustment. Desired:");
            sb2.append(n2);
            sb2.append(" Range:");
            sb2.append((Object)this.mVideoCapabilities.getSupportedHeights());
            throw new VideoException(sb2.toString());
        }
        Label_0522: {
            try {
                if (!this.mVideoCapabilities.getSupportedHeightsFor(intValue).contains((Comparable)n2)) {
                    final int intValue3 = (int)this.mVideoCapabilities.getSupportedWidths().getLower();
                    final int widthAlignment = this.mVideoCapabilities.getWidthAlignment();
                    int i = intValue;
                    int n3 = 0;
                    int n4 = 0;
                    Block_13: {
                        while (i >= intValue3) {
                            for (n3 = i - 32; n3 % widthAlignment != 0; --n3) {}
                            n4 = (int)Math.round(n3 / n);
                            i = n3;
                            if (this.mVideoCapabilities.getSupportedHeightsFor(n3).contains((Comparable)n4)) {
                                break Block_13;
                            }
                        }
                        break Label_0522;
                    }
                    DeviceEncoders.LOG.w(new Object[] { "getSupportedVideoSize - restarting with smaller size." });
                    supportedVideoSize = this.getSupportedVideoSize(new Size(n3, n4));
                    return supportedVideoSize;
                }
            }
            catch (final IllegalArgumentException ex) {}
        }
        if (this.mVideoCapabilities.isSizeSupported(intValue, n2)) {
            return new Size(intValue, n2);
        }
        final StringBuilder sb3 = new StringBuilder();
        sb3.append("Size not supported for unknown reason. Might be an aspect ratio issue. Desired size:");
        sb3.append((Object)new Size(intValue, n2));
        throw new VideoException(sb3.toString());
    }
    
    public String getVideoEncoder() {
        final MediaCodecInfo mVideoEncoder = this.mVideoEncoder;
        if (mVideoEncoder != null) {
            return mVideoEncoder.getName();
        }
        return null;
    }
    
    boolean isHardwareEncoder(String lowerCase) {
        lowerCase = lowerCase.toLowerCase();
        return (lowerCase.startsWith("omx.google.") || lowerCase.startsWith("c2.android.") || (!lowerCase.startsWith("omx.") && !lowerCase.startsWith("c2."))) ^ true;
    }
    
    public void tryConfigureAudio(final String p0, final int p1, final int p2, final int p3) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: getfield        com/otaliastudios/cameraview/internal/DeviceEncoders.mAudioEncoder:Landroid/media/MediaCodecInfo;
        //     4: ifnull          203
        //     7: aconst_null    
        //     8: astore          6
        //    10: aload_1        
        //    11: iload_3        
        //    12: iload           4
        //    14: invokestatic    android/media/MediaFormat.createAudioFormat:(Ljava/lang/String;II)Landroid/media/MediaFormat;
        //    17: astore          7
        //    19: iload           4
        //    21: iconst_2       
        //    22: if_icmpne       31
        //    25: bipush          12
        //    27: istore_3       
        //    28: goto            34
        //    31: bipush          16
        //    33: istore_3       
        //    34: aload           7
        //    36: ldc_w           "channel-mask"
        //    39: iload_3        
        //    40: invokevirtual   android/media/MediaFormat.setInteger:(Ljava/lang/String;I)V
        //    43: aload           7
        //    45: ldc_w           "bitrate"
        //    48: iload_2        
        //    49: invokevirtual   android/media/MediaFormat.setInteger:(Ljava/lang/String;I)V
        //    52: aload_0        
        //    53: getfield        com/otaliastudios/cameraview/internal/DeviceEncoders.mAudioEncoder:Landroid/media/MediaCodecInfo;
        //    56: invokevirtual   android/media/MediaCodecInfo.getName:()Ljava/lang/String;
        //    59: invokestatic    android/media/MediaCodec.createByCodecName:(Ljava/lang/String;)Landroid/media/MediaCodec;
        //    62: astore          5
        //    64: aload           5
        //    66: astore_1       
        //    67: aload           5
        //    69: aload           7
        //    71: aconst_null    
        //    72: aconst_null    
        //    73: iconst_1       
        //    74: invokevirtual   android/media/MediaCodec.configure:(Landroid/media/MediaFormat;Landroid/view/Surface;Landroid/media/MediaCrypto;I)V
        //    77: aload           5
        //    79: ifnull          203
        //    82: aload           5
        //    84: invokevirtual   android/media/MediaCodec.release:()V
        //    87: goto            203
        //    90: astore          6
        //    92: goto            108
        //    95: astore_1       
        //    96: aload           6
        //    98: astore          5
        //   100: goto            191
        //   103: astore          6
        //   105: aconst_null    
        //   106: astore          5
        //   108: aload           5
        //   110: astore_1       
        //   111: new             Lcom/otaliastudios/cameraview/internal/DeviceEncoders$AudioException;
        //   114: astore          8
        //   116: aload           5
        //   118: astore_1       
        //   119: new             Ljava/lang/StringBuilder;
        //   122: astore          7
        //   124: aload           5
        //   126: astore_1       
        //   127: aload           7
        //   129: invokespecial   java/lang/StringBuilder.<init>:()V
        //   132: aload           5
        //   134: astore_1       
        //   135: aload           7
        //   137: ldc_w           "Failed to configure video audio: "
        //   140: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   143: pop            
        //   144: aload           5
        //   146: astore_1       
        //   147: aload           7
        //   149: aload           6
        //   151: invokevirtual   java/lang/Exception.getMessage:()Ljava/lang/String;
        //   154: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   157: pop            
        //   158: aload           5
        //   160: astore_1       
        //   161: aload           8
        //   163: aload_0        
        //   164: aload           7
        //   166: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   169: aconst_null    
        //   170: invokespecial   com/otaliastudios/cameraview/internal/DeviceEncoders$AudioException.<init>:(Lcom/otaliastudios/cameraview/internal/DeviceEncoders;Ljava/lang/String;Lcom/otaliastudios/cameraview/internal/DeviceEncoders$1;)V
        //   173: aload           5
        //   175: astore_1       
        //   176: aload           8
        //   178: athrow         
        //   179: astore          5
        //   181: aload_1        
        //   182: astore          6
        //   184: aload           5
        //   186: astore_1       
        //   187: aload           6
        //   189: astore          5
        //   191: aload           5
        //   193: ifnull          201
        //   196: aload           5
        //   198: invokevirtual   android/media/MediaCodec.release:()V
        //   201: aload_1        
        //   202: athrow         
        //   203: return         
        //   204: astore_1       
        //   205: goto            203
        //   208: astore          5
        //   210: goto            201
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  10     19     103    108    Ljava/lang/Exception;
        //  10     19     95     103    Any
        //  34     64     103    108    Ljava/lang/Exception;
        //  34     64     95     103    Any
        //  67     77     90     95     Ljava/lang/Exception;
        //  67     77     179    191    Any
        //  82     87     204    208    Ljava/lang/Exception;
        //  111    116    179    191    Any
        //  119    124    179    191    Any
        //  127    132    179    191    Any
        //  135    144    179    191    Any
        //  147    158    179    191    Any
        //  161    173    179    191    Any
        //  176    179    179    191    Any
        //  196    201    208    213    Ljava/lang/Exception;
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 108, Size: 108
        //     at java.util.ArrayList.get(ArrayList.java:437)
        //     at q5.g.d(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:31)
        //     at q5.g.b(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:2125)
        //     at u5.m.d(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:21)
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
    
    public void tryConfigureVideo(final String p0, final Size p1, final int p2, final int p3) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: getfield        com/otaliastudios/cameraview/internal/DeviceEncoders.mVideoEncoder:Landroid/media/MediaCodecInfo;
        //     4: ifnull          195
        //     7: aconst_null    
        //     8: astore          5
        //    10: aload_1        
        //    11: aload_2        
        //    12: invokevirtual   com/otaliastudios/cameraview/size/Size.getWidth:()I
        //    15: aload_2        
        //    16: invokevirtual   com/otaliastudios/cameraview/size/Size.getHeight:()I
        //    19: invokestatic    android/media/MediaFormat.createVideoFormat:(Ljava/lang/String;II)Landroid/media/MediaFormat;
        //    22: astore          6
        //    24: aload           6
        //    26: ldc_w           "color-format"
        //    29: ldc_w           2130708361
        //    32: invokevirtual   android/media/MediaFormat.setInteger:(Ljava/lang/String;I)V
        //    35: aload           6
        //    37: ldc_w           "bitrate"
        //    40: iload           4
        //    42: invokevirtual   android/media/MediaFormat.setInteger:(Ljava/lang/String;I)V
        //    45: aload           6
        //    47: ldc_w           "frame-rate"
        //    50: iload_3        
        //    51: invokevirtual   android/media/MediaFormat.setInteger:(Ljava/lang/String;I)V
        //    54: aload           6
        //    56: ldc_w           "i-frame-interval"
        //    59: iconst_1       
        //    60: invokevirtual   android/media/MediaFormat.setInteger:(Ljava/lang/String;I)V
        //    63: aload_0        
        //    64: getfield        com/otaliastudios/cameraview/internal/DeviceEncoders.mVideoEncoder:Landroid/media/MediaCodecInfo;
        //    67: invokevirtual   android/media/MediaCodecInfo.getName:()Ljava/lang/String;
        //    70: invokestatic    android/media/MediaCodec.createByCodecName:(Ljava/lang/String;)Landroid/media/MediaCodec;
        //    73: astore_2       
        //    74: aload_2        
        //    75: astore_1       
        //    76: aload_2        
        //    77: aload           6
        //    79: aconst_null    
        //    80: aconst_null    
        //    81: iconst_1       
        //    82: invokevirtual   android/media/MediaCodec.configure:(Landroid/media/MediaFormat;Landroid/view/Surface;Landroid/media/MediaCrypto;I)V
        //    85: aload_2        
        //    86: ifnull          195
        //    89: aload_2        
        //    90: invokevirtual   android/media/MediaCodec.release:()V
        //    93: goto            195
        //    96: astore          5
        //    98: goto            112
        //   101: astore_1       
        //   102: aload           5
        //   104: astore_2       
        //   105: goto            185
        //   108: astore          5
        //   110: aconst_null    
        //   111: astore_2       
        //   112: aload_2        
        //   113: astore_1       
        //   114: new             Lcom/otaliastudios/cameraview/internal/DeviceEncoders$VideoException;
        //   117: astore          7
        //   119: aload_2        
        //   120: astore_1       
        //   121: new             Ljava/lang/StringBuilder;
        //   124: astore          6
        //   126: aload_2        
        //   127: astore_1       
        //   128: aload           6
        //   130: invokespecial   java/lang/StringBuilder.<init>:()V
        //   133: aload_2        
        //   134: astore_1       
        //   135: aload           6
        //   137: ldc_w           "Failed to configure video codec: "
        //   140: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   143: pop            
        //   144: aload_2        
        //   145: astore_1       
        //   146: aload           6
        //   148: aload           5
        //   150: invokevirtual   java/lang/Exception.getMessage:()Ljava/lang/String;
        //   153: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   156: pop            
        //   157: aload_2        
        //   158: astore_1       
        //   159: aload           7
        //   161: aload_0        
        //   162: aload           6
        //   164: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   167: aconst_null    
        //   168: invokespecial   com/otaliastudios/cameraview/internal/DeviceEncoders$VideoException.<init>:(Lcom/otaliastudios/cameraview/internal/DeviceEncoders;Ljava/lang/String;Lcom/otaliastudios/cameraview/internal/DeviceEncoders$1;)V
        //   171: aload_2        
        //   172: astore_1       
        //   173: aload           7
        //   175: athrow         
        //   176: astore_2       
        //   177: aload_1        
        //   178: astore          5
        //   180: aload_2        
        //   181: astore_1       
        //   182: aload           5
        //   184: astore_2       
        //   185: aload_2        
        //   186: ifnull          193
        //   189: aload_2        
        //   190: invokevirtual   android/media/MediaCodec.release:()V
        //   193: aload_1        
        //   194: athrow         
        //   195: return         
        //   196: astore_1       
        //   197: goto            195
        //   200: astore_2       
        //   201: goto            193
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  10     74     108    112    Ljava/lang/Exception;
        //  10     74     101    108    Any
        //  76     85     96     101    Ljava/lang/Exception;
        //  76     85     176    185    Any
        //  89     93     196    200    Ljava/lang/Exception;
        //  114    119    176    185    Any
        //  121    126    176    185    Any
        //  128    133    176    185    Any
        //  135    144    176    185    Any
        //  146    157    176    185    Any
        //  159    171    176    185    Any
        //  173    176    176    185    Any
        //  189    193    200    204    Ljava/lang/Exception;
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 110, Size: 110
        //     at java.util.ArrayList.get(ArrayList.java:437)
        //     at q5.g.d(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:31)
        //     at q5.g.b(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:2125)
        //     at u5.m.d(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:21)
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
    
    public class AudioException extends RuntimeException
    {
        final DeviceEncoders this$0;
        
        private AudioException(final DeviceEncoders this$0, final String s) {
            this.this$0 = this$0;
            super(s);
        }
    }
    
    public class VideoException extends RuntimeException
    {
        final DeviceEncoders this$0;
        
        private VideoException(final DeviceEncoders this$0, final String s) {
            this.this$0 = this$0;
            super(s);
        }
    }
}

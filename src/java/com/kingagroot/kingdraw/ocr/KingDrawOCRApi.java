package com.kingagroot.kingdraw.ocr;

import java.io.File;
import android.content.Context;
import org.tensorflow.lite.flex.FlexDelegate;

public class KingDrawOCRApi
{
    private static String FORMULA_MODEL_NAME;
    private static String MODEL_FILES_PATH;
    private static String OCR_MODEL_NAME;
    private static FlexDelegate flexDelegate;
    
    static {
        System.loadLibrary("kingdrawOcr-lib");
        KingDrawOCRApi.MODEL_FILES_PATH = "model";
        KingDrawOCRApi.FORMULA_MODEL_NAME = "formula_detect_v1.0.0.tflite";
        KingDrawOCRApi.OCR_MODEL_NAME = "frozen_ocr.tflite";
    }
    
    public static boolean CheckAndLoadMode(final Context context) {
        return isLoadModel() || init(context);
    }
    
    public static void deleteModel() {
        nDeleteModel();
        final FlexDelegate flexDelegate = KingDrawOCRApi.flexDelegate;
        if (flexDelegate == null) {
            flexDelegate.close();
            KingDrawOCRApi.flexDelegate = null;
        }
    }
    
    public static boolean init(final Context context) {
        final File externalFilesDir = context.getExternalFilesDir((String)null);
        if (externalFilesDir != null) {
            setDocPath(externalFilesDir.getAbsolutePath());
            final StringBuilder sb = new StringBuilder();
            sb.append(externalFilesDir.getAbsolutePath());
            sb.append(KingDrawOCRApi.MODEL_FILES_PATH);
            final String string = sb.toString();
            final StringBuilder sb2 = new StringBuilder();
            sb2.append(string);
            sb2.append("/");
            sb2.append(KingDrawOCRApi.FORMULA_MODEL_NAME);
            final File file = new File(sb2.toString());
            if (!file.exists()) {
                final String formula_MODEL_NAME = KingDrawOCRApi.FORMULA_MODEL_NAME;
                AssetFileUtils.copyAssetFile(context, formula_MODEL_NAME, string, formula_MODEL_NAME);
            }
            final StringBuilder sb3 = new StringBuilder();
            sb3.append(string);
            sb3.append("/");
            sb3.append(KingDrawOCRApi.OCR_MODEL_NAME);
            final File file2 = new File(sb3.toString());
            if (!file2.exists()) {
                final String ocr_MODEL_NAME = KingDrawOCRApi.OCR_MODEL_NAME;
                AssetFileUtils.copyAssetFile(context, ocr_MODEL_NAME, string, ocr_MODEL_NAME);
            }
            if (file2.exists() && file.exists()) {
                if (KingDrawOCRApi.flexDelegate == null) {
                    KingDrawOCRApi.flexDelegate = new FlexDelegate();
                }
                return loadModel(file.getAbsolutePath(), file2.getAbsolutePath(), KingDrawOCRApi.flexDelegate.getNativeHandle());
            }
        }
        return false;
    }
    
    public static boolean isLoadModel() {
        return nIsLoadModel();
    }
    
    public static boolean loadModel(final String s, final String s2, final long n) {
        return nLoadModel(s, s2, n);
    }
    
    private static native void nDeleteModel();
    
    private static native boolean nIsLoadModel();
    
    private static native boolean nLoadModel(final String p0, final String p1, final long p2);
    
    private static native String nRecognition(final String p0);
    
    private static native void nSetDocPath(final String p0);
    
    public static String recognition(final String s) {
        return nRecognition(s);
    }
    
    public static void setDocPath(final String s) {
        nSetDocPath(s);
    }
}

package org.tensorflow.lite;

public final class TensorFlowLite
{
    private static final String LIBNAME = "tensorflowlite_jni";
    private static final Throwable LOAD_LIBRARY_EXCEPTION;
    private static volatile boolean isInit;
    
    static {
        Throwable load_LIBRARY_EXCEPTION = null;
        try {
            System.loadLibrary("tensorflowlite_jni");
            load_LIBRARY_EXCEPTION = null;
        }
        catch (final UnsatisfiedLinkError unsatisfiedLinkError) {}
        LOAD_LIBRARY_EXCEPTION = load_LIBRARY_EXCEPTION;
    }
    
    private TensorFlowLite() {
    }
    
    public static void init() {
        if (TensorFlowLite.isInit) {
            return;
        }
        try {
            nativeRuntimeVersion();
            TensorFlowLite.isInit = true;
        }
        catch (final UnsatisfiedLinkError t) {
            final Throwable load_LIBRARY_EXCEPTION = TensorFlowLite.LOAD_LIBRARY_EXCEPTION;
            if (load_LIBRARY_EXCEPTION != null) {
                t = load_LIBRARY_EXCEPTION;
            }
            final StringBuilder sb = new StringBuilder();
            sb.append("Failed to load native TensorFlow Lite methods. Check that the correct native libraries are present, and, if using a custom native library, have been properly loaded via System.loadLibrary():\n  ");
            sb.append((Object)t);
            throw new UnsatisfiedLinkError(sb.toString());
        }
    }
    
    public static native String nativeRuntimeVersion();
    
    public static native String nativeSchemaVersion();
    
    public static String runtimeVersion() {
        init();
        return nativeRuntimeVersion();
    }
    
    public static String schemaVersion() {
        init();
        return nativeSchemaVersion();
    }
    
    @Deprecated
    public static String version() {
        return schemaVersion();
    }
}

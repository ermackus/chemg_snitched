package org.tensorflow.lite.flex;

import java.io.Closeable;
import org.tensorflow.lite.Delegate;

public class FlexDelegate implements Delegate, Closeable
{
    private static final long INVALID_DELEGATE_HANDLE = 0L;
    private static final String TFLITE_FLEX_LIB = "tensorflowlite_flex_jni";
    private long delegateHandle;
    
    static {
        System.loadLibrary("tensorflowlite_flex_jni");
    }
    
    public FlexDelegate() {
        this.delegateHandle = nativeCreateDelegate();
    }
    
    public static void initTensorFlowForTesting() {
        nativeInitTensorFlow();
    }
    
    private static native long nativeCreateDelegate();
    
    private static native void nativeDeleteDelegate(final long p0);
    
    private static native long nativeInitTensorFlow();
    
    public void close() {
        final long delegateHandle = this.delegateHandle;
        if (delegateHandle != 0L) {
            nativeDeleteDelegate(delegateHandle);
            this.delegateHandle = 0L;
        }
    }
    
    @Override
    public long getNativeHandle() {
        return this.delegateHandle;
    }
}

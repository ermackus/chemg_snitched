package org.tensorflow.lite.nnapi;

import org.tensorflow.lite.TensorFlowLite;
import org.tensorflow.lite.Delegate;

public class NnApiDelegate implements Delegate, AutoCloseable
{
    private static final long INVALID_DELEGATE_HANDLE = 0L;
    private long delegateHandle;
    
    public NnApiDelegate() {
        this(new Options());
    }
    
    public NnApiDelegate(final Options options) {
        TensorFlowLite.init();
        final int access$000 = options.executionPreference;
        final String access$2 = options.acceleratorName;
        final String access$3 = options.cacheDir;
        final String access$4 = options.modelToken;
        int intValue;
        if (options.maxDelegatedPartitions != null) {
            intValue = options.maxDelegatedPartitions;
        }
        else {
            intValue = -1;
        }
        final Boolean access$5 = options.useNnapiCpu;
        boolean b = true;
        boolean booleanValue = false;
        final boolean b2 = access$5 != null;
        if (options.useNnapiCpu == null || options.useNnapiCpu) {
            b = false;
        }
        if (options.allowFp16 != null) {
            booleanValue = options.allowFp16;
        }
        this.delegateHandle = createDelegate(access$000, access$2, access$3, access$4, intValue, b2, b, booleanValue);
    }
    
    private void checkNotClosed() {
        if (this.delegateHandle != 0L) {
            return;
        }
        throw new IllegalStateException("Should not access delegate after it has been closed.");
    }
    
    private static native long createDelegate(final int p0, final String p1, final String p2, final String p3, final int p4, final boolean p5, final boolean p6, final boolean p7);
    
    private static native void deleteDelegate(final long p0);
    
    private static native int getNnapiErrno(final long p0);
    
    public void close() {
        final long delegateHandle = this.delegateHandle;
        if (delegateHandle != 0L) {
            deleteDelegate(delegateHandle);
            this.delegateHandle = 0L;
        }
    }
    
    @Override
    public long getNativeHandle() {
        return this.delegateHandle;
    }
    
    public int getNnapiErrno() {
        this.checkNotClosed();
        return getNnapiErrno(this.delegateHandle);
    }
    
    public boolean hasErrors() {
        return getNnapiErrno(this.delegateHandle) != 0;
    }
    
    public static final class Options
    {
        public static final int EXECUTION_PREFERENCE_FAST_SINGLE_ANSWER = 1;
        public static final int EXECUTION_PREFERENCE_LOW_POWER = 0;
        public static final int EXECUTION_PREFERENCE_SUSTAINED_SPEED = 2;
        public static final int EXECUTION_PREFERENCE_UNDEFINED = -1;
        private String acceleratorName;
        private Boolean allowFp16;
        private String cacheDir;
        private int executionPreference;
        private Integer maxDelegatedPartitions;
        private String modelToken;
        private Boolean useNnapiCpu;
        
        public Options() {
            this.executionPreference = -1;
            this.acceleratorName = null;
            this.cacheDir = null;
            this.modelToken = null;
            this.maxDelegatedPartitions = null;
            this.useNnapiCpu = null;
            this.allowFp16 = null;
        }
        
        public Options setAcceleratorName(final String acceleratorName) {
            this.acceleratorName = acceleratorName;
            return this;
        }
        
        public Options setAllowFp16(final boolean b) {
            this.allowFp16 = b;
            return this;
        }
        
        public Options setCacheDir(final String cacheDir) {
            this.cacheDir = cacheDir;
            return this;
        }
        
        public Options setExecutionPreference(final int executionPreference) {
            this.executionPreference = executionPreference;
            return this;
        }
        
        public Options setMaxNumberOfDelegatedPartitions(final int n) {
            this.maxDelegatedPartitions = n;
            return this;
        }
        
        public Options setModelToken(final String modelToken) {
            this.modelToken = modelToken;
            return this;
        }
        
        public Options setUseNnapiCpu(final boolean b) {
            this.useNnapiCpu = (b ^ true);
            return this;
        }
    }
}

package org.tensorflow.lite;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.nio.MappedByteBuffer;
import java.nio.ByteBuffer;
import java.io.File;

public final class Interpreter implements AutoCloseable
{
    NativeInterpreterWrapper wrapper;
    
    public Interpreter(final File file) {
        this(file, null);
    }
    
    @Deprecated
    public Interpreter(final File file, final int numThreads) {
        this(file, new Options().setNumThreads(numThreads));
    }
    
    public Interpreter(final File file, final Options options) {
        this.wrapper = new NativeInterpreterWrapper(file.getAbsolutePath(), options);
    }
    
    public Interpreter(final ByteBuffer byteBuffer) {
        this(byteBuffer, null);
    }
    
    @Deprecated
    public Interpreter(final ByteBuffer byteBuffer, final int numThreads) {
        this(byteBuffer, new Options().setNumThreads(numThreads));
    }
    
    public Interpreter(final ByteBuffer byteBuffer, final Options options) {
        this.wrapper = new NativeInterpreterWrapper(byteBuffer, options);
    }
    
    @Deprecated
    public Interpreter(final MappedByteBuffer mappedByteBuffer) {
        this((ByteBuffer)mappedByteBuffer, null);
    }
    
    private void checkNotClosed() {
        if (this.wrapper != null) {
            return;
        }
        throw new IllegalStateException("Internal error: The Interpreter has already been closed.");
    }
    
    public void allocateTensors() {
        this.checkNotClosed();
        this.wrapper.allocateTensors();
    }
    
    public void close() {
        final NativeInterpreterWrapper wrapper = this.wrapper;
        if (wrapper != null) {
            wrapper.close();
            this.wrapper = null;
        }
    }
    
    @Override
    protected void finalize() throws Throwable {
        try {
            this.close();
        }
        finally {
            super.finalize();
        }
    }
    
    int getExecutionPlanLength() {
        this.checkNotClosed();
        return this.wrapper.getExecutionPlanLength();
    }
    
    public int getInputIndex(final String s) {
        this.checkNotClosed();
        return this.wrapper.getInputIndex(s);
    }
    
    public Tensor getInputTensor(final int n) {
        this.checkNotClosed();
        return this.wrapper.getInputTensor(n);
    }
    
    public int getInputTensorCount() {
        this.checkNotClosed();
        return this.wrapper.getInputTensorCount();
    }
    
    public Long getLastNativeInferenceDurationNanoseconds() {
        this.checkNotClosed();
        return this.wrapper.getLastNativeInferenceDurationNanoseconds();
    }
    
    public int getOutputIndex(final String s) {
        this.checkNotClosed();
        return this.wrapper.getOutputIndex(s);
    }
    
    public Tensor getOutputTensor(final int n) {
        this.checkNotClosed();
        return this.wrapper.getOutputTensor(n);
    }
    
    public int getOutputTensorCount() {
        this.checkNotClosed();
        return this.wrapper.getOutputTensorCount();
    }
    
    public void modifyGraphWithDelegate(final Delegate delegate) {
        this.checkNotClosed();
        this.wrapper.modifyGraphWithDelegate(delegate);
    }
    
    public void resetVariableTensors() {
        this.checkNotClosed();
        this.wrapper.resetVariableTensors();
    }
    
    public void resizeInput(final int n, final int[] array) {
        this.checkNotClosed();
        this.wrapper.resizeInput(n, array, false);
    }
    
    public void resizeInput(final int n, final int[] array, final boolean b) {
        this.checkNotClosed();
        this.wrapper.resizeInput(n, array, b);
    }
    
    public void run(final Object o, final Object o2) {
        final HashMap hashMap = new HashMap();
        ((Map)hashMap).put((Object)0, o2);
        this.runForMultipleInputsOutputs(new Object[] { o }, (Map<Integer, Object>)hashMap);
    }
    
    public void runForMultipleInputsOutputs(final Object[] array, final Map<Integer, Object> map) {
        this.checkNotClosed();
        this.wrapper.run(array, map);
    }
    
    public void setCancelled(final boolean cancelled) {
        this.wrapper.setCancelled(cancelled);
    }
    
    @Deprecated
    public void setNumThreads(final int numThreads) {
        this.checkNotClosed();
        this.wrapper.setNumThreads(numThreads);
    }
    
    public static class Options
    {
        Boolean allowBufferHandleOutput;
        Boolean allowCancellation;
        Boolean allowFp16PrecisionForFp32;
        final List<Delegate> delegates;
        int numThreads;
        Boolean useNNAPI;
        Boolean useXNNPACK;
        
        public Options() {
            this.numThreads = -1;
            this.delegates = (List<Delegate>)new ArrayList();
        }
        
        public Options addDelegate(final Delegate delegate) {
            this.delegates.add((Object)delegate);
            return this;
        }
        
        public Options setAllowBufferHandleOutput(final boolean b) {
            this.allowBufferHandleOutput = b;
            return this;
        }
        
        @Deprecated
        public Options setAllowFp16PrecisionForFp32(final boolean b) {
            this.allowFp16PrecisionForFp32 = b;
            return this;
        }
        
        public Options setCancellable(final boolean b) {
            this.allowCancellation = b;
            return this;
        }
        
        public Options setNumThreads(final int numThreads) {
            this.numThreads = numThreads;
            return this;
        }
        
        public Options setUseNNAPI(final boolean b) {
            this.useNNAPI = b;
            return this;
        }
        
        public Options setUseXNNPACK(final boolean b) {
            this.useXNNPACK = b;
            return this;
        }
    }
}

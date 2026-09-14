package org.tensorflow.lite;

import java.util.Map$Entry;
import java.util.HashMap;
import java.io.PrintStream;
import java.util.Iterator;
import org.tensorflow.lite.nnapi.NnApiDelegate;
import java.nio.ByteOrder;
import java.nio.MappedByteBuffer;
import java.util.ArrayList;
import java.nio.ByteBuffer;
import java.util.Map;
import java.util.List;

final class NativeInterpreterWrapper implements AutoCloseable
{
    private static final int ERROR_BUFFER_SIZE = 512;
    private long cancellationFlagHandle;
    private final List<Delegate> delegates;
    private long errorHandle;
    private long inferenceDurationNanoseconds;
    private Tensor[] inputTensors;
    private Map<String, Integer> inputsIndexes;
    private long interpreterHandle;
    private boolean isMemoryAllocated;
    private ByteBuffer modelByteBuffer;
    private long modelHandle;
    private Tensor[] outputTensors;
    private Map<String, Integer> outputsIndexes;
    private final List<AutoCloseable> ownedDelegates;
    
    NativeInterpreterWrapper(final String s) {
        this(s, null);
    }
    
    NativeInterpreterWrapper(final String s, final Interpreter.Options options) {
        this.cancellationFlagHandle = 0L;
        this.inferenceDurationNanoseconds = -1L;
        this.isMemoryAllocated = false;
        this.delegates = (List<Delegate>)new ArrayList();
        this.ownedDelegates = (List<AutoCloseable>)new ArrayList();
        TensorFlowLite.init();
        final long errorReporter = createErrorReporter(512);
        this.init(errorReporter, createModel(s, errorReporter), options);
    }
    
    NativeInterpreterWrapper(final ByteBuffer byteBuffer) {
        this(byteBuffer, null);
    }
    
    NativeInterpreterWrapper(final ByteBuffer modelByteBuffer, final Interpreter.Options options) {
        this.cancellationFlagHandle = 0L;
        this.inferenceDurationNanoseconds = -1L;
        this.isMemoryAllocated = false;
        this.delegates = (List<Delegate>)new ArrayList();
        this.ownedDelegates = (List<AutoCloseable>)new ArrayList();
        TensorFlowLite.init();
        if (modelByteBuffer != null && (modelByteBuffer instanceof MappedByteBuffer || (modelByteBuffer.isDirect() && modelByteBuffer.order() == ByteOrder.nativeOrder()))) {
            this.modelByteBuffer = modelByteBuffer;
            final long errorReporter = createErrorReporter(512);
            this.init(errorReporter, createModelWithBuffer(this.modelByteBuffer, errorReporter), options);
            return;
        }
        throw new IllegalArgumentException("Model ByteBuffer should be either a MappedByteBuffer of the model file, or a direct ByteBuffer using ByteOrder.nativeOrder() which contains bytes of model content.");
    }
    
    private static native long allocateTensors(final long p0, final long p1);
    
    private static native void allowBufferHandleOutput(final long p0, final boolean p1);
    
    private static native void allowFp16PrecisionForFp32(final long p0, final boolean p1);
    
    private static native void applyDelegate(final long p0, final long p1, final long p2);
    
    private void applyDelegates(final Interpreter.Options options) {
        final boolean hasUnresolvedFlexOp = hasUnresolvedFlexOp(this.interpreterHandle);
        if (hasUnresolvedFlexOp) {
            final Delegate maybeCreateFlexDelegate = maybeCreateFlexDelegate(options.delegates);
            if (maybeCreateFlexDelegate != null) {
                this.ownedDelegates.add((Object)maybeCreateFlexDelegate);
                applyDelegate(this.interpreterHandle, this.errorHandle, maybeCreateFlexDelegate.getNativeHandle());
            }
        }
        try {
            for (final Delegate delegate : options.delegates) {
                applyDelegate(this.interpreterHandle, this.errorHandle, delegate.getNativeHandle());
                this.delegates.add((Object)delegate);
            }
            if (options.useNNAPI != null && options.useNNAPI) {
                final NnApiDelegate nnApiDelegate = new NnApiDelegate();
                this.ownedDelegates.add((Object)nnApiDelegate);
                applyDelegate(this.interpreterHandle, this.errorHandle, nnApiDelegate.getNativeHandle());
            }
        }
        catch (final IllegalArgumentException ex) {
            if (!hasUnresolvedFlexOp || hasUnresolvedFlexOp(this.interpreterHandle)) {
                throw ex;
            }
            final PrintStream err = System.err;
            final StringBuilder sb = new StringBuilder();
            sb.append("Ignoring failed delegate application: ");
            sb.append((Object)ex);
            err.println(sb.toString());
        }
    }
    
    private static native long createCancellationFlag(final long p0);
    
    private static native long createErrorReporter(final int p0);
    
    private static native long createInterpreter(final long p0, final long p1, final int p2);
    
    private static native long createModel(final String p0, final long p1);
    
    private static native long createModelWithBuffer(final ByteBuffer p0, final long p1);
    
    private static native void delete(final long p0, final long p1, final long p2);
    
    private static native long deleteCancellationFlag(final long p0);
    
    private static native int getExecutionPlanLength(final long p0);
    
    private static native int getInputCount(final long p0);
    
    private static native String[] getInputNames(final long p0);
    
    private static native int getInputTensorIndex(final long p0, final int p1);
    
    private static native int getOutputCount(final long p0);
    
    private static native int getOutputDataType(final long p0, final int p1);
    
    private static native String[] getOutputNames(final long p0);
    
    private static native int getOutputTensorIndex(final long p0, final int p1);
    
    private static native boolean hasUnresolvedFlexOp(final long p0);
    
    private void init(final long errorHandle, final long modelHandle, final Interpreter.Options options) {
        Interpreter.Options options2 = options;
        if (options == null) {
            options2 = new Interpreter.Options();
        }
        this.errorHandle = errorHandle;
        this.modelHandle = modelHandle;
        this.interpreterHandle = createInterpreter(modelHandle, errorHandle, options2.numThreads);
        if (options2.allowCancellation != null && options2.allowCancellation) {
            this.cancellationFlagHandle = createCancellationFlag(this.interpreterHandle);
        }
        this.inputTensors = new Tensor[getInputCount(this.interpreterHandle)];
        this.outputTensors = new Tensor[getOutputCount(this.interpreterHandle)];
        if (options2.allowFp16PrecisionForFp32 != null) {
            allowFp16PrecisionForFp32(this.interpreterHandle, options2.allowFp16PrecisionForFp32);
        }
        if (options2.allowBufferHandleOutput != null) {
            allowBufferHandleOutput(this.interpreterHandle, options2.allowBufferHandleOutput);
        }
        this.applyDelegates(options2);
        if (options2.useXNNPACK != null) {
            useXNNPACK(this.interpreterHandle, errorHandle, options2.useXNNPACK, options2.numThreads);
        }
        allocateTensors(this.interpreterHandle, errorHandle);
        this.isMemoryAllocated = true;
    }
    
    private static Delegate maybeCreateFlexDelegate(final List<Delegate> list) {
        try {
            final Class<?> forName = Class.forName("org.tensorflow.lite.flex.FlexDelegate");
            final Iterator iterator = list.iterator();
            while (iterator.hasNext()) {
                if (forName.isInstance(iterator.next())) {
                    return null;
                }
            }
            return (Delegate)forName.getConstructor((Class[])new Class[0]).newInstance(new Object[0]);
        }
        catch (final Exception ex) {
            return null;
        }
    }
    
    private static native void numThreads(final long p0, final int p1);
    
    private static native void resetVariableTensors(final long p0, final long p1);
    
    private static native boolean resizeInput(final long p0, final long p1, final int p2, final int[] p3, final boolean p4);
    
    private static native void run(final long p0, final long p1);
    
    private static native void setCancelled(final long p0, final long p1, final boolean p2);
    
    private static native void useXNNPACK(final long p0, final long p1, final boolean p2, final int p3);
    
    void allocateTensors() {
        if (this.isMemoryAllocated) {
            return;
        }
        this.isMemoryAllocated = true;
        allocateTensors(this.interpreterHandle, this.errorHandle);
        int n = 0;
        while (true) {
            final Tensor[] outputTensors = this.outputTensors;
            if (n >= outputTensors.length) {
                break;
            }
            if (outputTensors[n] != null) {
                outputTensors[n].refreshShape();
            }
            ++n;
        }
    }
    
    public void close() {
        int n = 0;
        while (true) {
            final Tensor[] inputTensors = this.inputTensors;
            if (n >= inputTensors.length) {
                break;
            }
            if (inputTensors[n] != null) {
                inputTensors[n].close();
                this.inputTensors[n] = null;
            }
            ++n;
        }
        int n2 = 0;
        while (true) {
            final Tensor[] outputTensors = this.outputTensors;
            if (n2 >= outputTensors.length) {
                break;
            }
            if (outputTensors[n2] != null) {
                outputTensors[n2].close();
                this.outputTensors[n2] = null;
            }
            ++n2;
        }
        delete(this.errorHandle, this.modelHandle, this.interpreterHandle);
        deleteCancellationFlag(this.cancellationFlagHandle);
        this.errorHandle = 0L;
        this.modelHandle = 0L;
        this.interpreterHandle = 0L;
        this.cancellationFlagHandle = 0L;
        this.modelByteBuffer = null;
        this.inputsIndexes = null;
        this.outputsIndexes = null;
        this.isMemoryAllocated = false;
        this.delegates.clear();
        for (final AutoCloseable autoCloseable : this.ownedDelegates) {
            try {
                autoCloseable.close();
            }
            catch (final Exception ex) {
                final PrintStream err = System.err;
                final StringBuilder sb = new StringBuilder();
                sb.append("Failed to close flex delegate: ");
                sb.append((Object)ex);
                err.println(sb.toString());
            }
        }
        this.ownedDelegates.clear();
    }
    
    int getExecutionPlanLength() {
        return getExecutionPlanLength(this.interpreterHandle);
    }
    
    int getInputIndex(final String s) {
        if (this.inputsIndexes == null) {
            final String[] inputNames = getInputNames(this.interpreterHandle);
            this.inputsIndexes = (Map<String, Integer>)new HashMap();
            if (inputNames != null) {
                for (int i = 0; i < inputNames.length; ++i) {
                    this.inputsIndexes.put((Object)inputNames[i], (Object)i);
                }
            }
        }
        if (this.inputsIndexes.containsKey((Object)s)) {
            return (int)this.inputsIndexes.get((Object)s);
        }
        throw new IllegalArgumentException(String.format("Input error: '%s' is not a valid name for any input. Names of inputs and their indexes are %s", new Object[] { s, this.inputsIndexes.toString() }));
    }
    
    Tensor getInputTensor(final int n) {
        if (n >= 0) {
            final Tensor[] inputTensors = this.inputTensors;
            if (n < inputTensors.length) {
                Tensor fromIndex;
                if ((fromIndex = inputTensors[n]) == null) {
                    final long interpreterHandle = this.interpreterHandle;
                    fromIndex = Tensor.fromIndex(interpreterHandle, getInputTensorIndex(interpreterHandle, n));
                    inputTensors[n] = fromIndex;
                }
                return fromIndex;
            }
        }
        final StringBuilder sb = new StringBuilder();
        sb.append("Invalid input Tensor index: ");
        sb.append(n);
        throw new IllegalArgumentException(sb.toString());
    }
    
    int getInputTensorCount() {
        return this.inputTensors.length;
    }
    
    Long getLastNativeInferenceDurationNanoseconds() {
        final long inferenceDurationNanoseconds = this.inferenceDurationNanoseconds;
        Long value;
        if (inferenceDurationNanoseconds < 0L) {
            value = null;
        }
        else {
            value = inferenceDurationNanoseconds;
        }
        return value;
    }
    
    int getOutputIndex(final String s) {
        if (this.outputsIndexes == null) {
            final String[] outputNames = getOutputNames(this.interpreterHandle);
            this.outputsIndexes = (Map<String, Integer>)new HashMap();
            if (outputNames != null) {
                for (int i = 0; i < outputNames.length; ++i) {
                    this.outputsIndexes.put((Object)outputNames[i], (Object)i);
                }
            }
        }
        if (this.outputsIndexes.containsKey((Object)s)) {
            return (int)this.outputsIndexes.get((Object)s);
        }
        throw new IllegalArgumentException(String.format("Input error: '%s' is not a valid name for any output. Names of outputs and their indexes are %s", new Object[] { s, this.outputsIndexes.toString() }));
    }
    
    Tensor getOutputTensor(final int n) {
        if (n >= 0) {
            final Tensor[] outputTensors = this.outputTensors;
            if (n < outputTensors.length) {
                Tensor fromIndex;
                if ((fromIndex = outputTensors[n]) == null) {
                    final long interpreterHandle = this.interpreterHandle;
                    fromIndex = Tensor.fromIndex(interpreterHandle, getOutputTensorIndex(interpreterHandle, n));
                    outputTensors[n] = fromIndex;
                }
                return fromIndex;
            }
        }
        final StringBuilder sb = new StringBuilder();
        sb.append("Invalid output Tensor index: ");
        sb.append(n);
        throw new IllegalArgumentException(sb.toString());
    }
    
    int getOutputTensorCount() {
        return this.outputTensors.length;
    }
    
    void modifyGraphWithDelegate(final Delegate delegate) {
        applyDelegate(this.interpreterHandle, this.errorHandle, delegate.getNativeHandle());
        this.delegates.add((Object)delegate);
    }
    
    void resetVariableTensors() {
        resetVariableTensors(this.interpreterHandle, this.errorHandle);
    }
    
    void resizeInput(final int n, final int[] array) {
        this.resizeInput(n, array, false);
    }
    
    void resizeInput(final int n, final int[] array, final boolean b) {
        if (resizeInput(this.interpreterHandle, this.errorHandle, n, array, b)) {
            this.isMemoryAllocated = false;
            final Tensor[] inputTensors = this.inputTensors;
            if (inputTensors[n] != null) {
                inputTensors[n].refreshShape();
            }
        }
    }
    
    void run(final Object[] array, final Map<Integer, Object> map) {
        this.inferenceDurationNanoseconds = -1L;
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("Input error: Inputs should not be null or empty.");
        }
        if (map != null && !map.isEmpty()) {
            final int n = 0;
            for (int i = 0; i < array.length; ++i) {
                final int[] inputShapeIfDifferent = this.getInputTensor(i).getInputShapeIfDifferent(array[i]);
                if (inputShapeIfDifferent != null) {
                    this.resizeInput(i, inputShapeIfDifferent);
                }
            }
            final boolean b = this.isMemoryAllocated ^ true;
            if (b) {
                allocateTensors(this.interpreterHandle, this.errorHandle);
                this.isMemoryAllocated = true;
            }
            for (int j = 0; j < array.length; ++j) {
                this.getInputTensor(j).setTo(array[j]);
            }
            final long nanoTime = System.nanoTime();
            run(this.interpreterHandle, this.errorHandle);
            final long nanoTime2 = System.nanoTime();
            if (b) {
                int n2 = n;
                while (true) {
                    final Tensor[] outputTensors = this.outputTensors;
                    if (n2 >= outputTensors.length) {
                        break;
                    }
                    if (outputTensors[n2] != null) {
                        outputTensors[n2].refreshShape();
                    }
                    ++n2;
                }
            }
            for (final Map$Entry map$Entry : map.entrySet()) {
                this.getOutputTensor((int)map$Entry.getKey()).copyTo(map$Entry.getValue());
            }
            this.inferenceDurationNanoseconds = nanoTime2 - nanoTime;
            return;
        }
        throw new IllegalArgumentException("Input error: Outputs should not be null or empty.");
    }
    
    void setCancelled(final boolean b) {
        final long cancellationFlagHandle = this.cancellationFlagHandle;
        if (cancellationFlagHandle != 0L) {
            setCancelled(this.interpreterHandle, cancellationFlagHandle, b);
            return;
        }
        throw new IllegalStateException("Cannot cancel the inference. Have you called Interpreter.Options.setCancellable?");
    }
    
    void setNumThreads(final int n) {
        numThreads(this.interpreterHandle, n);
    }
}

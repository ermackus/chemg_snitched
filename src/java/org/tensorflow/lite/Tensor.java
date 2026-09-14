package org.tensorflow.lite;

import java.util.Arrays;
import java.nio.IntBuffer;
import java.nio.LongBuffer;
import java.nio.FloatBuffer;
import java.nio.Buffer;
import java.lang.reflect.Array;
import java.nio.ByteOrder;
import java.nio.ByteBuffer;

public final class Tensor
{
    private final DataType dtype;
    private long nativeHandle;
    private final QuantizationParams quantizationParamsCopy;
    private int[] shapeCopy;
    private final int[] shapeSignatureCopy;
    
    private Tensor(final long nativeHandle) {
        this.nativeHandle = nativeHandle;
        this.dtype = DataType.fromC(dtype(nativeHandle));
        this.shapeCopy = shape(nativeHandle);
        this.shapeSignatureCopy = shapeSignature(nativeHandle);
        this.quantizationParamsCopy = new QuantizationParams(quantizationScale(nativeHandle), quantizationZeroPoint(nativeHandle));
    }
    
    private ByteBuffer buffer() {
        return buffer(this.nativeHandle).order(ByteOrder.nativeOrder());
    }
    
    private static native ByteBuffer buffer(final long p0);
    
    static int computeNumDimensions(final Object o) {
        if (o == null || !o.getClass().isArray()) {
            return 0;
        }
        if (Array.getLength(o) != 0) {
            return computeNumDimensions(Array.get(o, 0)) + 1;
        }
        throw new IllegalArgumentException("Array lengths cannot be 0.");
    }
    
    static int computeNumElements(final int[] array) {
        int n = 1;
        for (int i = 0; i < array.length; ++i) {
            n *= array[i];
        }
        return n;
    }
    
    private void copyTo(final Buffer buffer) {
        if (buffer instanceof ByteBuffer) {
            ((ByteBuffer)buffer).put(this.buffer());
        }
        else if (buffer instanceof FloatBuffer) {
            ((FloatBuffer)buffer).put(this.buffer().asFloatBuffer());
        }
        else if (buffer instanceof LongBuffer) {
            ((LongBuffer)buffer).put(this.buffer().asLongBuffer());
        }
        else {
            if (!(buffer instanceof IntBuffer)) {
                final StringBuilder sb = new StringBuilder();
                sb.append("Unexpected output buffer type: ");
                sb.append((Object)buffer);
                throw new IllegalArgumentException(sb.toString());
            }
            ((IntBuffer)buffer).put(this.buffer().asIntBuffer());
        }
    }
    
    private static native long create(final long p0, final int p1);
    
    private static native void delete(final long p0);
    
    private static native int dtype(final long p0);
    
    static void fillShape(final Object o, final int n, final int[] array) {
        if (array != null && n != array.length) {
            final int length = Array.getLength(o);
            final int n2 = array[n];
            int i = 0;
            if (n2 == 0) {
                array[n] = length;
            }
            else if (array[n] != length) {
                throw new IllegalArgumentException(String.format("Mismatched lengths (%d and %d) in dimension %d", new Object[] { array[n], length, n }));
            }
            while (i < length) {
                fillShape(Array.get(o, i), n + 1, array);
                ++i;
            }
        }
    }
    
    static Tensor fromIndex(final long n, final int n2) {
        return new Tensor(create(n, n2));
    }
    
    private static native boolean hasDelegateBufferHandle(final long p0);
    
    private static native int index(final long p0);
    
    private static boolean isBuffer(final Object o) {
        return o instanceof Buffer;
    }
    
    private static boolean isByteBuffer(final Object o) {
        return o instanceof ByteBuffer;
    }
    
    private static native String name(final long p0);
    
    private static native int numBytes(final long p0);
    
    private static native float quantizationScale(final long p0);
    
    private static native int quantizationZeroPoint(final long p0);
    
    private static native void readMultiDimensionalArray(final long p0, final Object p1);
    
    private void setTo(final Buffer buffer) {
        if (buffer instanceof ByteBuffer) {
            final ByteBuffer byteBuffer = (ByteBuffer)buffer;
            if (byteBuffer.isDirect() && byteBuffer.order() == ByteOrder.nativeOrder()) {
                writeDirectBuffer(this.nativeHandle, buffer);
            }
            else {
                this.buffer().put(byteBuffer);
            }
        }
        else if (buffer instanceof LongBuffer) {
            final LongBuffer longBuffer = (LongBuffer)buffer;
            if (longBuffer.isDirect() && longBuffer.order() == ByteOrder.nativeOrder()) {
                writeDirectBuffer(this.nativeHandle, buffer);
            }
            else {
                this.buffer().asLongBuffer().put(longBuffer);
            }
        }
        else if (buffer instanceof FloatBuffer) {
            final FloatBuffer floatBuffer = (FloatBuffer)buffer;
            if (floatBuffer.isDirect() && floatBuffer.order() == ByteOrder.nativeOrder()) {
                writeDirectBuffer(this.nativeHandle, buffer);
            }
            else {
                this.buffer().asFloatBuffer().put(floatBuffer);
            }
        }
        else {
            if (!(buffer instanceof IntBuffer)) {
                final StringBuilder sb = new StringBuilder();
                sb.append("Unexpected input buffer type: ");
                sb.append((Object)buffer);
                throw new IllegalArgumentException(sb.toString());
            }
            final IntBuffer intBuffer = (IntBuffer)buffer;
            if (intBuffer.isDirect() && intBuffer.order() == ByteOrder.nativeOrder()) {
                writeDirectBuffer(this.nativeHandle, buffer);
            }
            else {
                this.buffer().asIntBuffer().put(intBuffer);
            }
        }
    }
    
    private static native int[] shape(final long p0);
    
    private static native int[] shapeSignature(final long p0);
    
    private void throwIfDstShapeIsIncompatible(final Object o) {
        if (isBuffer(o)) {
            final Buffer buffer = (Buffer)o;
            final int numBytes = this.numBytes();
            int capacity;
            if (isByteBuffer(o)) {
                capacity = buffer.capacity();
            }
            else {
                capacity = buffer.capacity() * this.dtype.byteSize();
            }
            if (numBytes <= capacity) {
                return;
            }
            throw new IllegalArgumentException(String.format("Cannot copy from a TensorFlowLite tensor (%s) with %d bytes to a Java Buffer with %d bytes.", new Object[] { this.name(), numBytes, capacity }));
        }
        else {
            final int[] computeShape = this.computeShapeOf(o);
            if (Arrays.equals(computeShape, this.shapeCopy)) {
                return;
            }
            throw new IllegalArgumentException(String.format("Cannot copy from a TensorFlowLite tensor (%s) with shape %s to a Java object with shape %s.", new Object[] { this.name(), Arrays.toString(this.shapeCopy), Arrays.toString(computeShape) }));
        }
    }
    
    private void throwIfSrcShapeIsIncompatible(final Object o) {
        if (isBuffer(o)) {
            final Buffer buffer = (Buffer)o;
            final int numBytes = this.numBytes();
            int capacity;
            if (isByteBuffer(o)) {
                capacity = buffer.capacity();
            }
            else {
                capacity = buffer.capacity() * this.dtype.byteSize();
            }
            if (numBytes == capacity) {
                return;
            }
            throw new IllegalArgumentException(String.format("Cannot copy to a TensorFlowLite tensor (%s) with %d bytes from a Java Buffer with %d bytes.", new Object[] { this.name(), numBytes, capacity }));
        }
        else {
            final int[] computeShape = this.computeShapeOf(o);
            if (Arrays.equals(computeShape, this.shapeCopy)) {
                return;
            }
            throw new IllegalArgumentException(String.format("Cannot copy to a TensorFlowLite tensor (%s) with shape %s from a Java object with shape %s.", new Object[] { this.name(), Arrays.toString(this.shapeCopy), Arrays.toString(computeShape) }));
        }
    }
    
    private void throwIfTypeIsIncompatible(final Object o) {
        if (isByteBuffer(o)) {
            return;
        }
        final DataType dataType = this.dataTypeOf(o);
        if (dataType == this.dtype) {
            return;
        }
        if (dataType.toStringName().equals((Object)this.dtype.toStringName())) {
            return;
        }
        throw new IllegalArgumentException(String.format("Cannot convert between a TensorFlowLite tensor with type %s and a Java object of type %s (which is compatible with the TensorFlowLite type %s).", new Object[] { this.dtype, o.getClass().getName(), dataType }));
    }
    
    private static native void writeDirectBuffer(final long p0, final Buffer p1);
    
    private static native void writeMultiDimensionalArray(final long p0, final Object p1);
    
    private static native void writeScalar(final long p0, final Object p1);
    
    void close() {
        delete(this.nativeHandle);
        this.nativeHandle = 0L;
    }
    
    int[] computeShapeOf(final Object o) {
        int computeNumDimensions;
        final int n = computeNumDimensions = computeNumDimensions(o);
        if (this.dtype == DataType.STRING) {
            Class<?> obj = o.getClass();
            computeNumDimensions = n;
            if (obj.isArray()) {
                while (obj.isArray()) {
                    obj = obj.getComponentType();
                }
                computeNumDimensions = n;
                if (Byte.TYPE.equals(obj)) {
                    computeNumDimensions = n - 1;
                }
            }
        }
        final int[] array = new int[computeNumDimensions];
        fillShape(o, 0, array);
        return array;
    }
    
    Object copyTo(final Object o) {
        if (o != null) {
            this.throwIfTypeIsIncompatible(o);
            this.throwIfDstShapeIsIncompatible(o);
            if (isBuffer(o)) {
                this.copyTo((Buffer)o);
            }
            else {
                readMultiDimensionalArray(this.nativeHandle, o);
            }
            return o;
        }
        if (hasDelegateBufferHandle(this.nativeHandle)) {
            return o;
        }
        throw new IllegalArgumentException("Null outputs are allowed only if the Tensor is bound to a buffer handle.");
    }
    
    public DataType dataType() {
        return this.dtype;
    }
    
    DataType dataTypeOf(final Object o) {
        if (o != null) {
            Class<?> clazz = o.getClass();
            if (clazz.isArray()) {
                while (clazz.isArray()) {
                    clazz = clazz.getComponentType();
                }
                if (Float.TYPE.equals(clazz)) {
                    return DataType.FLOAT32;
                }
                if (Integer.TYPE.equals(clazz)) {
                    return DataType.INT32;
                }
                if (Byte.TYPE.equals(clazz)) {
                    if (this.dtype == DataType.STRING) {
                        return DataType.STRING;
                    }
                    return DataType.UINT8;
                }
                else {
                    if (Long.TYPE.equals(clazz)) {
                        return DataType.INT64;
                    }
                    if (Boolean.TYPE.equals(clazz)) {
                        return DataType.BOOL;
                    }
                    if (String.class.equals(clazz)) {
                        return DataType.STRING;
                    }
                }
            }
            else {
                if (Float.class.equals(clazz) || o instanceof FloatBuffer) {
                    return DataType.FLOAT32;
                }
                if (Integer.class.equals(clazz) || o instanceof IntBuffer) {
                    return DataType.INT32;
                }
                if (Byte.class.equals(clazz)) {
                    return DataType.UINT8;
                }
                if (Long.class.equals(clazz) || o instanceof LongBuffer) {
                    return DataType.INT64;
                }
                if (Boolean.class.equals(clazz)) {
                    return DataType.BOOL;
                }
                if (String.class.equals(clazz)) {
                    return DataType.STRING;
                }
            }
        }
        final StringBuilder sb = new StringBuilder();
        sb.append("DataType error: cannot resolve DataType of ");
        sb.append(o.getClass().getName());
        throw new IllegalArgumentException(sb.toString());
    }
    
    int[] getInputShapeIfDifferent(final Object o) {
        if (o == null) {
            return null;
        }
        if (isBuffer(o)) {
            return null;
        }
        this.throwIfTypeIsIncompatible(o);
        final int[] computeShape = this.computeShapeOf(o);
        if (Arrays.equals(this.shapeCopy, computeShape)) {
            return null;
        }
        return computeShape;
    }
    
    public int index() {
        return index(this.nativeHandle);
    }
    
    public String name() {
        return name(this.nativeHandle);
    }
    
    public int numBytes() {
        return numBytes(this.nativeHandle);
    }
    
    public int numDimensions() {
        return this.shapeCopy.length;
    }
    
    public int numElements() {
        return computeNumElements(this.shapeCopy);
    }
    
    public QuantizationParams quantizationParams() {
        return this.quantizationParamsCopy;
    }
    
    void refreshShape() {
        this.shapeCopy = shape(this.nativeHandle);
    }
    
    void setTo(final Object o) {
        if (o != null) {
            this.throwIfTypeIsIncompatible(o);
            this.throwIfSrcShapeIsIncompatible(o);
            if (isBuffer(o)) {
                this.setTo((Buffer)o);
            }
            else if (o.getClass().isArray()) {
                writeMultiDimensionalArray(this.nativeHandle, o);
            }
            else {
                writeScalar(this.nativeHandle, o);
            }
            return;
        }
        if (hasDelegateBufferHandle(this.nativeHandle)) {
            return;
        }
        throw new IllegalArgumentException("Null inputs are allowed only if the Tensor is bound to a buffer handle.");
    }
    
    public int[] shape() {
        return this.shapeCopy;
    }
    
    public int[] shapeSignature() {
        return this.shapeSignatureCopy;
    }
    
    public static class QuantizationParams
    {
        private final float scale;
        private final int zeroPoint;
        
        public QuantizationParams(final float scale, final int zeroPoint) {
            this.scale = scale;
            this.zeroPoint = zeroPoint;
        }
        
        public float getScale() {
            return this.scale;
        }
        
        public int getZeroPoint() {
            return this.zeroPoint;
        }
    }
}

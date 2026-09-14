package org.tensorflow.lite;

public enum DataType
{
    private static final DataType[] $VALUES;
    
    BOOL(6), 
    FLOAT32(1), 
    INT32(2), 
    INT64(4), 
    INT8(9), 
    STRING(5), 
    UINT8(3);
    
    private static final DataType[] values;
    private final int value;
    
    static {
        values = values();
    }
    
    private DataType(final int value) {
        this.value = value;
    }
    
    static DataType fromC(final int n) {
        for (final DataType dataType : DataType.values) {
            if (dataType.value == n) {
                return dataType;
            }
        }
        final StringBuilder sb = new StringBuilder();
        sb.append("DataType error: DataType ");
        sb.append(n);
        sb.append(" is not recognized in Java (version ");
        sb.append(TensorFlowLite.runtimeVersion());
        sb.append(")");
        throw new IllegalArgumentException(sb.toString());
    }
    
    public int byteSize() {
        switch (DataType$1.$SwitchMap$org$tensorflow$lite$DataType[this.ordinal()]) {
            default: {
                final StringBuilder sb = new StringBuilder();
                sb.append("DataType error: DataType ");
                sb.append((Object)this);
                sb.append(" is not supported yet");
                throw new IllegalArgumentException(sb.toString());
            }
            case 6:
            case 7: {
                return -1;
            }
            case 5: {
                return 8;
            }
            case 3:
            case 4: {
                return 1;
            }
            case 1:
            case 2: {
                return 4;
            }
        }
    }
    
    int c() {
        return this.value;
    }
    
    String toStringName() {
        switch (DataType$1.$SwitchMap$org$tensorflow$lite$DataType[this.ordinal()]) {
            default: {
                final StringBuilder sb = new StringBuilder();
                sb.append("DataType error: DataType ");
                sb.append((Object)this);
                sb.append(" is not supported yet");
                throw new IllegalArgumentException(sb.toString());
            }
            case 7: {
                return "string";
            }
            case 6: {
                return "bool";
            }
            case 5: {
                return "long";
            }
            case 3:
            case 4: {
                return "byte";
            }
            case 2: {
                return "int";
            }
            case 1: {
                return "float";
            }
        }
    }
}

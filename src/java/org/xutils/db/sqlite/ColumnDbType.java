package org.xutils.db.sqlite;

public enum ColumnDbType
{
    private static final ColumnDbType[] $VALUES;
    
    BLOB("BLOB"), 
    INTEGER("INTEGER"), 
    REAL("REAL"), 
    TEXT("TEXT");
    
    private String value;
    
    private ColumnDbType(final String value) {
        this.value = value;
    }
    
    public String toString() {
        return this.value;
    }
}

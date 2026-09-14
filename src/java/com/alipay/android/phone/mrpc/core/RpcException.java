package com.alipay.android.phone.mrpc.core;

public class RpcException extends RuntimeException
{
    public static final long serialVersionUID = -2875437994101380406L;
    public int mCode;
    public String mMsg;
    public String mOperationType;
    
    public RpcException(final Integer n, final String mMsg) {
        super(a(n, mMsg));
        this.mCode = n;
        this.mMsg = mMsg;
    }
    
    public RpcException(final Integer n, final String mMsg, final Throwable t) {
        super(a(n, mMsg), t);
        this.mCode = n;
        this.mMsg = mMsg;
    }
    
    public RpcException(final Integer n, final Throwable t) {
        super(t);
        this.mCode = n;
    }
    
    public RpcException(final String mMsg) {
        super(mMsg);
        this.mCode = 0;
        this.mMsg = mMsg;
    }
    
    public static String a(final Integer n, final String s) {
        final StringBuilder sb = new StringBuilder();
        sb.append("RPCException: ");
        if (n != null) {
            sb.append("[");
            sb.append((Object)n);
            sb.append("]");
        }
        sb.append(" : ");
        if (s != null) {
            sb.append(s);
        }
        return sb.toString();
    }
    
    public int getCode() {
        return this.mCode;
    }
    
    public String getMsg() {
        return this.mMsg;
    }
    
    public String getOperationType() {
        return this.mOperationType;
    }
    
    public void setOperationType(final String mOperationType) {
        this.mOperationType = mOperationType;
    }
}

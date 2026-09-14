package org.xutils.ex;

import java.io.IOException;

public class BaseException extends IOException
{
    private static final long serialVersionUID = 1L;
    
    public BaseException() {
    }
    
    public BaseException(final String s) {
        super(s);
    }
    
    public BaseException(final String s, final Throwable t) {
        super(s);
        this.initCause(t);
    }
    
    public BaseException(final Throwable t) {
        super(t.getMessage());
        this.initCause(t);
    }
}

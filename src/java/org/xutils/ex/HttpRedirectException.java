package org.xutils.ex;

public class HttpRedirectException extends HttpException
{
    private static final long serialVersionUID = 1L;
    
    public HttpRedirectException(final int n, final String s, final String result) {
        super(n, s);
        this.setResult(result);
    }
}

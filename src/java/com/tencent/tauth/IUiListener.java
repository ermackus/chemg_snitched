package com.tencent.tauth;

public interface IUiListener
{
    void onCancel();
    
    void onComplete(final Object p0);
    
    void onError(final UiError p0);
    
    void onWarning(final int p0);
}

package com.alipay.android.app;

import android.os.Bundle;
import java.util.Map;
import android.os.RemoteException;
import android.os.IInterface;

public interface IRemoteServiceCallback extends IInterface
{
    int getVersion() throws RemoteException;
    
    boolean isHideLoadingScreen() throws RemoteException;
    
    void payEnd(final boolean p0, final String p1) throws RemoteException;
    
    void r03(final String p0, final String p1, final Map p2) throws RemoteException;
    
    void startActivity(final String p0, final String p1, final int p2, final Bundle p3) throws RemoteException;
}

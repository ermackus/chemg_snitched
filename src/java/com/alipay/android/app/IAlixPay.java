package com.alipay.android.app;

import java.util.Map;
import android.os.RemoteException;
import android.os.IInterface;

public interface IAlixPay extends IInterface
{
    String Pay(final String p0) throws RemoteException;
    
    void deployFastConnect() throws RemoteException;
    
    int getVersion() throws RemoteException;
    
    boolean manager(final String p0) throws RemoteException;
    
    String pay02(final String p0, final Map p1) throws RemoteException;
    
    String prePay(final String p0) throws RemoteException;
    
    void r03(final String p0, final String p1, final Map p2) throws RemoteException;
    
    void registerCallback(final IRemoteServiceCallback p0) throws RemoteException;
    
    void registerCallback03(final IRemoteServiceCallback p0, final String p1, final Map p2) throws RemoteException;
    
    String test() throws RemoteException;
    
    void unregisterCallback(final IRemoteServiceCallback p0) throws RemoteException;
}

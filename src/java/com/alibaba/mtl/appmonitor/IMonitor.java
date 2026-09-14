package com.alibaba.mtl.appmonitor;

import com.alibaba.mtl.appmonitor.model.MeasureValueSet;
import com.alibaba.mtl.appmonitor.model.DimensionValueSet;
import com.alibaba.mtl.appmonitor.model.DimensionSet;
import com.alibaba.mtl.appmonitor.model.MeasureSet;
import java.util.Map;
import android.os.RemoteException;
import android.os.IInterface;

public interface IMonitor extends IInterface
{
    boolean alarm_checkSampled(final String p0, final String p1) throws RemoteException;
    
    void alarm_commitFail1(final String p0, final String p1, final String p2, final String p3, final Map p4) throws RemoteException;
    
    void alarm_commitFail2(final String p0, final String p1, final String p2, final String p3, final String p4, final Map p5) throws RemoteException;
    
    void alarm_commitSuccess1(final String p0, final String p1, final Map p2) throws RemoteException;
    
    void alarm_commitSuccess2(final String p0, final String p1, final String p2, final Map p3) throws RemoteException;
    
    void alarm_setSampling(final int p0) throws RemoteException;
    
    void alarm_setStatisticsInterval(final int p0) throws RemoteException;
    
    boolean counter_checkSampled(final String p0, final String p1) throws RemoteException;
    
    void counter_commit1(final String p0, final String p1, final double p2, final Map p3) throws RemoteException;
    
    void counter_commit2(final String p0, final String p1, final String p2, final double p3, final Map p4) throws RemoteException;
    
    void counter_setSampling(final int p0) throws RemoteException;
    
    void counter_setStatisticsInterval(final int p0) throws RemoteException;
    
    void destroy() throws RemoteException;
    
    void enableLog(final boolean p0) throws RemoteException;
    
    void init() throws RemoteException;
    
    boolean offlinecounter_checkSampled(final String p0, final String p1) throws RemoteException;
    
    void offlinecounter_commit(final String p0, final String p1, final double p2) throws RemoteException;
    
    void offlinecounter_setSampling(final int p0) throws RemoteException;
    
    void offlinecounter_setStatisticsInterval(final int p0) throws RemoteException;
    
    void register1(final String p0, final String p1, final MeasureSet p2) throws RemoteException;
    
    void register2(final String p0, final String p1, final MeasureSet p2, final boolean p3) throws RemoteException;
    
    void register3(final String p0, final String p1, final MeasureSet p2, final DimensionSet p3) throws RemoteException;
    
    void register4(final String p0, final String p1, final MeasureSet p2, final DimensionSet p3, final boolean p4) throws RemoteException;
    
    void setChannel(final String p0) throws RemoteException;
    
    void setRequestAuthInfo(final boolean p0, final String p1, final String p2, final String p3) throws RemoteException;
    
    void setSampling(final int p0) throws RemoteException;
    
    void setStatisticsInterval1(final int p0) throws RemoteException;
    
    void setStatisticsInterval2(final int p0, final int p1) throws RemoteException;
    
    void stat_begin(final String p0, final String p1, final String p2) throws RemoteException;
    
    boolean stat_checkSampled(final String p0, final String p1) throws RemoteException;
    
    void stat_commit1(final String p0, final String p1, final double p2, final Map p3) throws RemoteException;
    
    void stat_commit2(final String p0, final String p1, final DimensionValueSet p2, final double p3, final Map p4) throws RemoteException;
    
    void stat_commit3(final String p0, final String p1, final DimensionValueSet p2, final MeasureValueSet p3, final Map p4) throws RemoteException;
    
    void stat_end(final String p0, final String p1, final String p2) throws RemoteException;
    
    void stat_setSampling(final int p0) throws RemoteException;
    
    void stat_setStatisticsInterval(final int p0) throws RemoteException;
    
    void transaction_begin(final Transaction p0, final String p1) throws RemoteException;
    
    void transaction_end(final Transaction p0, final String p1) throws RemoteException;
    
    void triggerUpload() throws RemoteException;
    
    void turnOffRealTimeDebug() throws RemoteException;
    
    void turnOnRealTimeDebug(final Map p0) throws RemoteException;
    
    void updateMeasure(final String p0, final String p1, final String p2, final double p3, final double p4, final double p5) throws RemoteException;
}

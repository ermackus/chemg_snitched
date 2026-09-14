package com.alibaba.mtl.appmonitor;

import com.alibaba.mtl.log.d.i;
import com.alibaba.mtl.appmonitor.model.MeasureValueSet;
import com.alibaba.mtl.appmonitor.model.DimensionValueSet;
import com.alibaba.mtl.appmonitor.model.DimensionSet;
import com.alibaba.mtl.appmonitor.model.MeasureSet;
import java.util.Map;
import android.os.RemoteException;
import com.alibaba.mtl.appmonitor.a.f;
import android.app.Application;

public class Monitor extends Stub
{
    private Application b;
    
    Monitor(final Application b) {
        this.b = b;
    }
    
    private f a(final int n) {
        return f.a(n);
    }
    
    public boolean alarm_checkSampled(final String s, final String s2) throws RemoteException {
        return AppMonitorDelegate$Alarm.checkSampled(s, s2);
    }
    
    public void alarm_commitFail1(final String s, final String s2, final String s3, final String s4, final Map map) throws RemoteException {
        AppMonitorDelegate$Alarm.commitFail(s, s2, s3, s4, map);
    }
    
    public void alarm_commitFail2(final String s, final String s2, final String s3, final String s4, final String s5, final Map map) throws RemoteException {
        AppMonitorDelegate$Alarm.commitFail(s, s2, s3, s4, s5, map);
    }
    
    public void alarm_commitSuccess1(final String s, final String s2, final Map map) throws RemoteException {
        AppMonitorDelegate$Alarm.commitSuccess(s, s2, map);
    }
    
    public void alarm_commitSuccess2(final String s, final String s2, final String s3, final Map map) throws RemoteException {
        AppMonitorDelegate$Alarm.commitSuccess(s, s2, s3, map);
    }
    
    public void alarm_setSampling(final int sampling) throws RemoteException {
        AppMonitorDelegate$Alarm.setSampling(sampling);
    }
    
    public void alarm_setStatisticsInterval(final int statisticsInterval) throws RemoteException {
        AppMonitorDelegate$Alarm.setStatisticsInterval(statisticsInterval);
    }
    
    public boolean counter_checkSampled(final String s, final String s2) throws RemoteException {
        return AppMonitorDelegate$Counter.checkSampled(s, s2);
    }
    
    public void counter_commit1(final String s, final String s2, final double n, final Map map) throws RemoteException {
        AppMonitorDelegate$Counter.commit(s, s2, n, map);
    }
    
    public void counter_commit2(final String s, final String s2, final String s3, final double n, final Map map) throws RemoteException {
        AppMonitorDelegate$Counter.commit(s, s2, s3, n, map);
    }
    
    public void counter_setSampling(final int sampling) throws RemoteException {
        AppMonitorDelegate$Counter.setSampling(sampling);
    }
    
    public void counter_setStatisticsInterval(final int statisticsInterval) throws RemoteException {
        AppMonitorDelegate$Counter.setStatisticsInterval(statisticsInterval);
    }
    
    public void destroy() throws RemoteException {
        AppMonitorDelegate.destroy();
    }
    
    public void enableLog(final boolean b) throws RemoteException {
        AppMonitorDelegate.enableLog(b);
    }
    
    public void init() throws RemoteException {
        AppMonitorDelegate.init(this.b);
    }
    
    public boolean offlinecounter_checkSampled(final String s, final String s2) throws RemoteException {
        return AppMonitorDelegate$OffLineCounter.checkSampled(s, s2);
    }
    
    public void offlinecounter_commit(final String s, final String s2, final double n) throws RemoteException {
        AppMonitorDelegate$OffLineCounter.commit(s, s2, n);
    }
    
    public void offlinecounter_setSampling(final int sampling) throws RemoteException {
        AppMonitorDelegate$OffLineCounter.setSampling(sampling);
    }
    
    public void offlinecounter_setStatisticsInterval(final int statisticsInterval) throws RemoteException {
        AppMonitorDelegate$OffLineCounter.setStatisticsInterval(statisticsInterval);
    }
    
    public void register1(final String s, final String s2, final MeasureSet set) throws RemoteException {
        AppMonitorDelegate.register(s, s2, set);
    }
    
    public void register2(final String s, final String s2, final MeasureSet set, final boolean b) throws RemoteException {
        AppMonitorDelegate.register(s, s2, set, b);
    }
    
    public void register3(final String s, final String s2, final MeasureSet set, final DimensionSet set2) throws RemoteException {
        AppMonitorDelegate.register(s, s2, set, set2);
    }
    
    public void register4(final String s, final String s2, final MeasureSet set, final DimensionSet set2, final boolean b) throws RemoteException {
        AppMonitorDelegate.register(s, s2, set, set2, b);
    }
    
    public void setChannel(final String channel) throws RemoteException {
        AppMonitorDelegate.setChannel(channel);
    }
    
    public void setRequestAuthInfo(final boolean b, final String s, final String s2, final String s3) throws RemoteException {
        AppMonitorDelegate.setRequestAuthInfo(b, s, s2, s3);
    }
    
    public void setSampling(final int sampling) throws RemoteException {
        AppMonitorDelegate.setSampling(sampling);
    }
    
    public void setStatisticsInterval1(final int statisticsInterval) throws RemoteException {
        AppMonitorDelegate.setStatisticsInterval(statisticsInterval);
    }
    
    public void setStatisticsInterval2(final int n, final int n2) throws RemoteException {
        AppMonitorDelegate.setStatisticsInterval(this.a(n), n2);
    }
    
    public void stat_begin(final String s, final String s2, final String s3) throws RemoteException {
        AppMonitorDelegate$Stat.begin(s, s2, s3);
    }
    
    public boolean stat_checkSampled(final String s, final String s2) throws RemoteException {
        return AppMonitorDelegate$Stat.checkSampled(s, s2);
    }
    
    public void stat_commit1(final String s, final String s2, final double n, final Map map) throws RemoteException {
        AppMonitorDelegate$Stat.commit(s, s2, n, map);
    }
    
    public void stat_commit2(final String s, final String s2, final DimensionValueSet set, final double n, final Map map) throws RemoteException {
        AppMonitorDelegate$Stat.commit(s, s2, set, n, map);
    }
    
    public void stat_commit3(final String s, final String s2, final DimensionValueSet set, final MeasureValueSet set2, final Map map) throws RemoteException {
        i.a("Monitor", new Object[] { "[stat_commit3]" });
        AppMonitorDelegate$Stat.commit(s, s2, set, set2, map);
    }
    
    public void stat_end(final String s, final String s2, final String s3) throws RemoteException {
        AppMonitorDelegate$Stat.end(s, s2, s3);
    }
    
    public void stat_setSampling(final int sampling) throws RemoteException {
        AppMonitorDelegate$Stat.setSampling(sampling);
    }
    
    public void stat_setStatisticsInterval(final int statisticsInterval) throws RemoteException {
        AppMonitorDelegate$Stat.setStatisticsInterval(statisticsInterval);
    }
    
    public void transaction_begin(final Transaction transaction, final String s) throws RemoteException {
        TransactionDelegate.begin(transaction, s);
    }
    
    public void transaction_end(final Transaction transaction, final String s) throws RemoteException {
        TransactionDelegate.end(transaction, s);
    }
    
    public void triggerUpload() throws RemoteException {
        AppMonitorDelegate.triggerUpload();
    }
    
    public void turnOffRealTimeDebug() throws RemoteException {
        AppMonitorDelegate.turnOffRealTimeDebug();
    }
    
    public void turnOnRealTimeDebug(final Map map) throws RemoteException {
        AppMonitorDelegate.turnOnRealTimeDebug(map);
    }
    
    public void updateMeasure(final String s, final String s2, final String s3, final double n, final double n2, final double n3) throws RemoteException {
        AppMonitorDelegate.updateMeasure(s, s2, s3, n, n2, n3);
    }
}

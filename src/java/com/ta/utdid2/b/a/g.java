package com.ta.utdid2.b.a;

import java.util.Random;
import android.telephony.TelephonyManager;
import android.content.Context;

public class g
{
    public static String a(final Context context) {
        while (true) {
            if (context == null) {
                break Label_0026;
            }
            try {
                final TelephonyManager telephonyManager = (TelephonyManager)context.getSystemService("phone");
                String deviceId;
                if (telephonyManager != null) {
                    deviceId = telephonyManager.getDeviceId();
                }
                else {
                    deviceId = null;
                }
                String c = deviceId;
                if (i.a(deviceId)) {
                    c = c();
                }
                return c;
            }
            catch (final Exception ex) {
                continue;
            }
            break;
        }
    }
    
    public static String b(final Context context) {
        while (true) {
            if (context == null) {
                break Label_0026;
            }
            try {
                final TelephonyManager telephonyManager = (TelephonyManager)context.getSystemService("phone");
                String subscriberId;
                if (telephonyManager != null) {
                    subscriberId = telephonyManager.getSubscriberId();
                }
                else {
                    subscriberId = null;
                }
                String c = subscriberId;
                if (i.a(subscriberId)) {
                    c = c();
                }
                return c;
            }
            catch (final Exception ex) {
                continue;
            }
            break;
        }
    }
    
    public static final String c() {
        final int n = (int)(System.currentTimeMillis() / 1000L);
        final int n2 = (int)System.nanoTime();
        final int nextInt = new Random().nextInt();
        final int nextInt2 = new Random().nextInt();
        final byte[] bytes = e.getBytes(n);
        final byte[] bytes2 = e.getBytes(n2);
        final byte[] bytes3 = e.getBytes(nextInt);
        final byte[] bytes4 = e.getBytes(nextInt2);
        final byte[] array = new byte[16];
        System.arraycopy((Object)bytes, 0, (Object)array, 0, 4);
        System.arraycopy((Object)bytes2, 0, (Object)array, 4, 4);
        System.arraycopy((Object)bytes3, 0, (Object)array, 8, 4);
        System.arraycopy((Object)bytes4, 0, (Object)array, 12, 4);
        return b.encodeToString(array, 2);
    }
}

package com.ut.device;

import com.ta.utdid2.a.a;
import android.content.Context;

public class UTDevice
{
    public static String getAid(final String s, final String s2, final Context context) {
        return a.a(context).a(s, s2, getUtdid(context));
    }
    
    public static void getAidAsync(final String s, final String s2, final Context context, final com.ut.device.a a) {
        a.a(context).a(s, s2, getUtdid(context), a);
    }
    
    public static String getUtdid(final Context context) {
        return com.ta.utdid2.device.UTDevice.getUtdid(context);
    }
}

package com.kingagroot.kingdraw.ui.workstation;

import java.util.Iterator;
import java.util.Map$Entry;
import android.os.Bundle;
import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import java.util.HashMap;
import java.util.Map;

public class WorkWebCountView
{
    private static final Map<String, WorkStationAppFragment> fragmentHashMap;
    
    static {
        fragmentHashMap = (Map)new HashMap();
    }
    
    public static void clearViewParent(final FragmentActivity fragmentActivity, final WorkStationAppFragment workStationAppFragment) {
        final FragmentTransaction beginTransaction = fragmentActivity.getSupportFragmentManager().beginTransaction();
        beginTransaction.remove((Fragment)workStationAppFragment);
        workStationAppFragment.setStationActivity((WebStationActivity)null);
        beginTransaction.commitNowAllowingStateLoss();
    }
    
    public static WorkStationAppFragment createAppFragment(final String s) {
        return createAppFragment(s, "");
    }
    
    public static WorkStationAppFragment createAppFragment(final String s, final String s2) {
        if (WorkWebCountView.fragmentHashMap.containsKey((Object)s)) {
            return (WorkStationAppFragment)WorkWebCountView.fragmentHashMap.get((Object)s);
        }
        final WorkStationAppFragment workStationAppFragment = new WorkStationAppFragment();
        final Bundle arguments = new Bundle();
        arguments.putString("url", s);
        arguments.putString("appid", s2);
        workStationAppFragment.setArguments(arguments);
        WorkWebCountView.fragmentHashMap.put((Object)s, (Object)workStationAppFragment);
        return workStationAppFragment;
    }
    
    public static void remove(final WorkStationAppFragment workStationAppFragment) {
        if (workStationAppFragment == null) {
            return;
        }
        for (final Map$Entry map$Entry : WorkWebCountView.fragmentHashMap.entrySet()) {
            final WorkStationAppFragment workStationAppFragment2 = (WorkStationAppFragment)map$Entry.getValue();
            if (workStationAppFragment2 != null && workStationAppFragment2 == workStationAppFragment) {
                WorkWebCountView.fragmentHashMap.remove(map$Entry.getKey());
                break;
            }
        }
        workStationAppFragment.setStationActivity((WebStationActivity)null);
        workStationAppFragment.onDestroy();
    }
}

package com.hjq.permissions;

import android.app.Activity;
import android.content.Intent;
import java.util.ArrayList;
import android.content.Context;
import java.util.Iterator;
import java.util.List;

final class PermissionApi
{
    private static final PermissionDelegate DELEGATE;
    
    static {
        if (AndroidVersion.isAndroid13()) {
            DELEGATE = (PermissionDelegate)new PermissionDelegateImplV33();
        }
        else if (AndroidVersion.isAndroid12()) {
            DELEGATE = (PermissionDelegate)new PermissionDelegateImplV31();
        }
        else if (AndroidVersion.isAndroid11()) {
            DELEGATE = (PermissionDelegate)new PermissionDelegateImplV30();
        }
        else if (AndroidVersion.isAndroid10()) {
            DELEGATE = (PermissionDelegate)new PermissionDelegateImplV29();
        }
        else if (AndroidVersion.isAndroid9()) {
            DELEGATE = (PermissionDelegate)new PermissionDelegateImplV28();
        }
        else if (AndroidVersion.isAndroid8()) {
            DELEGATE = (PermissionDelegate)new PermissionDelegateImplV26();
        }
        else if (AndroidVersion.isAndroid6()) {
            DELEGATE = (PermissionDelegate)new PermissionDelegateImplV23();
        }
        else if (AndroidVersion.isAndroid5()) {
            DELEGATE = (PermissionDelegate)new PermissionDelegateImplV21();
        }
        else if (AndroidVersion.isAndroid4_4()) {
            DELEGATE = (PermissionDelegate)new PermissionDelegateImplV19();
        }
        else if (AndroidVersion.isAndroid4_3()) {
            DELEGATE = (PermissionDelegate)new PermissionDelegateImplV18();
        }
        else {
            DELEGATE = (PermissionDelegate)new PermissionDelegateImplV14();
        }
    }
    
    static boolean containsSpecialPermission(final List<String> list) {
        if (list != null) {
            if (!list.isEmpty()) {
                final Iterator iterator = list.iterator();
                while (iterator.hasNext()) {
                    if (isSpecialPermission((String)iterator.next())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    static List<String> getDeniedPermissions(final Context context, final List<String> list) {
        final ArrayList list2 = new ArrayList(list.size());
        for (final String s : list) {
            if (!isGrantedPermission(context, s)) {
                ((List)list2).add((Object)s);
            }
        }
        return (List<String>)list2;
    }
    
    static List<String> getDeniedPermissions(final List<String> list, final int[] array) {
        final ArrayList list2 = new ArrayList();
        for (int i = 0; i < array.length; ++i) {
            if (array[i] == -1) {
                ((List)list2).add(list.get(i));
            }
        }
        return (List<String>)list2;
    }
    
    static List<String> getGrantedPermissions(final Context context, final List<String> list) {
        final ArrayList list2 = new ArrayList(list.size());
        for (final String s : list) {
            if (isGrantedPermission(context, s)) {
                ((List)list2).add((Object)s);
            }
        }
        return (List<String>)list2;
    }
    
    static List<String> getGrantedPermissions(final List<String> list, final int[] array) {
        final ArrayList list2 = new ArrayList();
        for (int i = 0; i < array.length; ++i) {
            if (array[i] == 0) {
                ((List)list2).add(list.get(i));
            }
        }
        return (List<String>)list2;
    }
    
    static Intent getPermissionIntent(final Context context, final String s) {
        return PermissionApi.DELEGATE.getPermissionIntent(context, s);
    }
    
    static boolean isGrantedPermission(final Context context, final String s) {
        return PermissionApi.DELEGATE.isGrantedPermission(context, s);
    }
    
    static boolean isGrantedPermissions(final Context context, final List<String> list) {
        if (list.isEmpty()) {
            return false;
        }
        final Iterator iterator = list.iterator();
        while (iterator.hasNext()) {
            if (!isGrantedPermission(context, (String)iterator.next())) {
                return false;
            }
        }
        return true;
    }
    
    static boolean isPermissionPermanentDenied(final Activity activity, final String s) {
        return PermissionApi.DELEGATE.isPermissionPermanentDenied(activity, s);
    }
    
    static boolean isPermissionPermanentDenied(final Activity activity, final List<String> list) {
        final Iterator iterator = list.iterator();
        while (iterator.hasNext()) {
            if (isPermissionPermanentDenied(activity, (String)iterator.next())) {
                return true;
            }
        }
        return false;
    }
    
    static boolean isSpecialPermission(final String s) {
        return PermissionUtils.isSpecialPermission(s);
    }
}

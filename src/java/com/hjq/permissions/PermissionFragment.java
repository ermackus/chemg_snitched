package com.hjq.permissions;

import java.util.Iterator;
import java.util.Collection;
import android.content.Context;
import android.content.Intent;
import java.util.Random;
import android.os.Bundle;
import android.app.Activity;
import java.util.ArrayList;
import java.util.List;
import android.app.Fragment;

public final class PermissionFragment extends Fragment implements Runnable
{
    private static final String REQUEST_CODE = "request_code";
    private static final List<Integer> REQUEST_CODE_ARRAY;
    private static final String REQUEST_PERMISSIONS = "request_permissions";
    private OnPermissionCallback mCallBack;
    private boolean mDangerousRequest;
    private IPermissionInterceptor mInterceptor;
    private boolean mRequestFlag;
    private int mScreenOrientation;
    private boolean mSpecialRequest;
    
    static {
        REQUEST_CODE_ARRAY = (List)new ArrayList();
    }
    
    public static void launch(final Activity activity, final ArrayList<String> list, final IPermissionInterceptor interceptor, final OnPermissionCallback callBack) {
        final PermissionFragment permissionFragment = new PermissionFragment();
        final Bundle arguments = new Bundle();
        int nextInt;
        do {
            nextInt = new Random().nextInt((int)Math.pow(2.0, 8.0));
        } while (PermissionFragment.REQUEST_CODE_ARRAY.contains((Object)nextInt));
        PermissionFragment.REQUEST_CODE_ARRAY.add((Object)nextInt);
        arguments.putInt("request_code", nextInt);
        arguments.putStringArrayList("request_permissions", (ArrayList)list);
        permissionFragment.setArguments(arguments);
        permissionFragment.setRetainInstance(true);
        permissionFragment.setRequestFlag(true);
        permissionFragment.setCallBack(callBack);
        permissionFragment.setInterceptor(interceptor);
        permissionFragment.attachActivity(activity);
    }
    
    public void attachActivity(final Activity activity) {
        activity.getFragmentManager().beginTransaction().add((Fragment)this, this.toString()).commitAllowingStateLoss();
    }
    
    public void detachActivity(final Activity activity) {
        activity.getFragmentManager().beginTransaction().remove((Fragment)this).commitAllowingStateLoss();
    }
    
    public void onActivityResult(final int n, final int n2, final Intent intent) {
        final Activity activity = this.getActivity();
        final Bundle arguments = this.getArguments();
        if (activity != null && arguments != null && !this.mDangerousRequest) {
            if (n == arguments.getInt("request_code")) {
                final ArrayList stringArrayList = arguments.getStringArrayList("request_permissions");
                if (stringArrayList != null) {
                    if (!stringArrayList.isEmpty()) {
                        this.mDangerousRequest = true;
                        PermissionUtils.postActivityResult((List<String>)stringArrayList, (Runnable)this);
                    }
                }
            }
        }
    }
    
    public void onAttach(final Context context) {
        super.onAttach(context);
        final Activity activity = this.getActivity();
        if (activity == null) {
            return;
        }
        if ((this.mScreenOrientation = activity.getRequestedOrientation()) != -1) {
            return;
        }
        PermissionUtils.lockActivityOrientation(activity);
    }
    
    public void onDestroy() {
        super.onDestroy();
        this.mCallBack = null;
    }
    
    public void onDetach() {
        super.onDetach();
        final Activity activity = this.getActivity();
        if (activity != null && this.mScreenOrientation == -1) {
            if (activity.getRequestedOrientation() != -1) {
                activity.setRequestedOrientation(-1);
            }
        }
    }
    
    public void onRequestPermissionsResult(final int n, final String[] array, final int[] array2) {
        if (array.length != 0) {
            if (array2.length != 0) {
                final Bundle arguments = this.getArguments();
                final Activity activity = this.getActivity();
                if (activity != null && arguments != null && this.mInterceptor != null) {
                    if (n == arguments.getInt("request_code")) {
                        final OnPermissionCallback mCallBack = this.mCallBack;
                        this.mCallBack = null;
                        final IPermissionInterceptor mInterceptor = this.mInterceptor;
                        this.mInterceptor = null;
                        PermissionUtils.optimizePermissionResults(activity, array, array2);
                        final java.util.ArrayList<String> arrayList = PermissionUtils.asArrayList(array);
                        PermissionFragment.REQUEST_CODE_ARRAY.remove((Object)n);
                        this.detachActivity(activity);
                        final List<String> grantedPermissions = PermissionApi.getGrantedPermissions((List<String>)arrayList, array2);
                        if (grantedPermissions.size() == ((List)arrayList).size()) {
                            mInterceptor.grantedPermissionRequest(activity, (List<String>)arrayList, grantedPermissions, true, mCallBack);
                            mInterceptor.finishPermissionRequest(activity, (List<String>)arrayList, false, mCallBack);
                            return;
                        }
                        final List<String> deniedPermissions = PermissionApi.getDeniedPermissions((List<String>)arrayList, array2);
                        mInterceptor.deniedPermissionRequest(activity, (List<String>)arrayList, deniedPermissions, PermissionApi.isPermissionPermanentDenied(activity, deniedPermissions), mCallBack);
                        if (!grantedPermissions.isEmpty()) {
                            mInterceptor.grantedPermissionRequest(activity, (List<String>)arrayList, grantedPermissions, false, mCallBack);
                        }
                        mInterceptor.finishPermissionRequest(activity, (List<String>)arrayList, false, mCallBack);
                    }
                }
            }
        }
    }
    
    public void onResume() {
        super.onResume();
        if (!this.mRequestFlag) {
            this.detachActivity(this.getActivity());
            return;
        }
        if (this.mSpecialRequest) {
            return;
        }
        this.mSpecialRequest = true;
        this.requestSpecialPermission();
    }
    
    public void requestDangerousPermission() {
        final Activity activity = this.getActivity();
        final Bundle arguments = this.getArguments();
        if (activity != null) {
            if (arguments != null) {
                final int int1 = arguments.getInt("request_code");
                final ArrayList stringArrayList = arguments.getStringArrayList("request_permissions");
                if (stringArrayList != null) {
                    if (!stringArrayList.isEmpty()) {
                        if (!AndroidVersion.isAndroid6()) {
                            final int size = stringArrayList.size();
                            final int[] array = new int[size];
                            for (int i = 0; i < size; ++i) {
                                int n;
                                if (PermissionApi.isGrantedPermission((Context)activity, (String)stringArrayList.get(i))) {
                                    n = 0;
                                }
                                else {
                                    n = -1;
                                }
                                array[i] = n;
                            }
                            this.onRequestPermissionsResult(int1, (String[])stringArrayList.toArray((Object[])new String[0]), array);
                            return;
                        }
                        if (AndroidVersion.isAndroid13() && stringArrayList.size() >= 2 && PermissionUtils.containsPermission((Collection<String>)stringArrayList, "android.permission.BODY_SENSORS_BACKGROUND")) {
                            final ArrayList list = new ArrayList((Collection)stringArrayList);
                            list.remove((Object)"android.permission.BODY_SENSORS_BACKGROUND");
                            this.splitTwiceRequestPermission(activity, (ArrayList<String>)stringArrayList, (ArrayList<String>)list, int1);
                            return;
                        }
                        if (AndroidVersion.isAndroid10() && stringArrayList.size() >= 2 && PermissionUtils.containsPermission((Collection<String>)stringArrayList, "android.permission.ACCESS_BACKGROUND_LOCATION")) {
                            final ArrayList list2 = new ArrayList((Collection)stringArrayList);
                            list2.remove((Object)"android.permission.ACCESS_BACKGROUND_LOCATION");
                            this.splitTwiceRequestPermission(activity, (ArrayList<String>)stringArrayList, (ArrayList<String>)list2, int1);
                            return;
                        }
                        if (AndroidVersion.isAndroid10() && PermissionUtils.containsPermission((Collection<String>)stringArrayList, "android.permission.ACCESS_MEDIA_LOCATION") && PermissionUtils.containsPermission((Collection<String>)stringArrayList, "android.permission.READ_EXTERNAL_STORAGE")) {
                            final ArrayList list3 = new ArrayList((Collection)stringArrayList);
                            list3.remove((Object)"android.permission.ACCESS_MEDIA_LOCATION");
                            this.splitTwiceRequestPermission(activity, (ArrayList<String>)stringArrayList, (ArrayList<String>)list3, int1);
                            return;
                        }
                        this.requestPermissions((String[])stringArrayList.toArray((Object[])new String[stringArrayList.size() - 1]), int1);
                    }
                }
            }
        }
    }
    
    public void requestSpecialPermission() {
        final Bundle arguments = this.getArguments();
        final Activity activity = this.getActivity();
        if (arguments != null) {
            if (activity != null) {
                final Iterator iterator = ((List)arguments.getStringArrayList("request_permissions")).iterator();
                boolean b = false;
                while (iterator.hasNext()) {
                    final String s = (String)iterator.next();
                    if (!PermissionApi.isSpecialPermission(s)) {
                        continue;
                    }
                    if (PermissionApi.isGrantedPermission((Context)activity, s)) {
                        continue;
                    }
                    if (!AndroidVersion.isAndroid11() && PermissionUtils.equalsPermission(s, "android.permission.MANAGE_EXTERNAL_STORAGE")) {
                        continue;
                    }
                    StartActivityManager.startActivityForResult(this, PermissionUtils.getSmartPermissionIntent((Context)activity, (List<String>)PermissionUtils.asArrayList(s)), this.getArguments().getInt("request_code"));
                    b = true;
                }
                if (b) {
                    return;
                }
                this.requestDangerousPermission();
            }
        }
    }
    
    public void run() {
        if (!this.isAdded()) {
            return;
        }
        this.requestDangerousPermission();
    }
    
    public void setCallBack(final OnPermissionCallback mCallBack) {
        this.mCallBack = mCallBack;
    }
    
    public void setInterceptor(final IPermissionInterceptor mInterceptor) {
        this.mInterceptor = mInterceptor;
    }
    
    public void setRequestFlag(final boolean mRequestFlag) {
        this.mRequestFlag = mRequestFlag;
    }
    
    public void splitTwiceRequestPermission(final Activity activity, final ArrayList<String> list, final ArrayList<String> list2, final int n) {
        final ArrayList list3 = new ArrayList((Collection)list);
        final Iterator iterator = list2.iterator();
        while (iterator.hasNext()) {
            list3.remove((Object)iterator.next());
        }
        launch(activity, list2, (IPermissionInterceptor)new PermissionFragment$1(this), (OnPermissionCallback)new PermissionFragment$2(this, activity, list3, (ArrayList)list, n));
    }
}

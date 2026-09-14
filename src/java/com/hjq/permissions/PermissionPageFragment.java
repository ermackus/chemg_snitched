package com.hjq.permissions;

import android.content.Context;
import java.util.List;
import android.content.Intent;
import android.os.Bundle;
import java.util.ArrayList;
import android.app.Activity;
import android.app.Fragment;

public final class PermissionPageFragment extends Fragment implements Runnable
{
    private static final String REQUEST_PERMISSIONS = "request_permissions";
    private OnPermissionPageCallback mCallBack;
    private boolean mRequestFlag;
    private boolean mStartActivityFlag;
    
    public static void beginRequest(final Activity activity, final ArrayList<String> list, final OnPermissionPageCallback callBack) {
        final PermissionPageFragment permissionPageFragment = new PermissionPageFragment();
        final Bundle arguments = new Bundle();
        arguments.putStringArrayList("request_permissions", (ArrayList)list);
        permissionPageFragment.setArguments(arguments);
        permissionPageFragment.setRetainInstance(true);
        permissionPageFragment.setRequestFlag(true);
        permissionPageFragment.setCallBack(callBack);
        permissionPageFragment.attachActivity(activity);
    }
    
    public void attachActivity(final Activity activity) {
        activity.getFragmentManager().beginTransaction().add((Fragment)this, this.toString()).commitAllowingStateLoss();
    }
    
    public void detachActivity(final Activity activity) {
        activity.getFragmentManager().beginTransaction().remove((Fragment)this).commitAllowingStateLoss();
    }
    
    public void onActivityResult(final int n, final int n2, final Intent intent) {
        if (n != 1025) {
            return;
        }
        final Activity activity = this.getActivity();
        final Bundle arguments = this.getArguments();
        if (activity != null) {
            if (arguments != null) {
                final ArrayList stringArrayList = arguments.getStringArrayList("request_permissions");
                if (stringArrayList != null) {
                    if (!stringArrayList.isEmpty()) {
                        PermissionUtils.postActivityResult((List<String>)stringArrayList, (Runnable)this);
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
        if (this.mStartActivityFlag) {
            return;
        }
        this.mStartActivityFlag = true;
        final Bundle arguments = this.getArguments();
        final Activity activity = this.getActivity();
        if (arguments != null) {
            if (activity != null) {
                StartActivityManager.startActivityForResult(this, PermissionUtils.getSmartPermissionIntent((Context)this.getActivity(), (List<String>)arguments.getStringArrayList("request_permissions")), 1025);
            }
        }
    }
    
    public void run() {
        if (!this.isAdded()) {
            return;
        }
        final Activity activity = this.getActivity();
        if (activity == null) {
            return;
        }
        final OnPermissionPageCallback mCallBack = this.mCallBack;
        this.mCallBack = null;
        if (mCallBack == null) {
            this.detachActivity(activity);
            return;
        }
        final ArrayList stringArrayList = this.getArguments().getStringArrayList("request_permissions");
        if (PermissionApi.getGrantedPermissions((Context)activity, (List<String>)stringArrayList).size() == ((List)stringArrayList).size()) {
            mCallBack.onGranted();
        }
        else {
            mCallBack.onDenied();
        }
        this.detachActivity(activity);
    }
    
    public void setCallBack(final OnPermissionPageCallback mCallBack) {
        this.mCallBack = mCallBack;
    }
    
    public void setRequestFlag(final boolean mRequestFlag) {
        this.mRequestFlag = mRequestFlag;
    }
}

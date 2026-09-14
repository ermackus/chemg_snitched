package com.hjq.permissions;

import android.content.Context;
import android.app.Fragment;
import android.app.Activity;
import android.os.Parcelable;
import android.content.Intent;

final class StartActivityManager
{
    private static final String SUB_INTENT_KEY = "sub_intent_key";
    
    static Intent addSubIntentToMainIntent(final Intent intent, final Intent intent2) {
        if (intent == null && intent2 != null) {
            return intent2;
        }
        if (intent2 == null) {
            return intent;
        }
        getDeepSubIntent(intent).putExtra("sub_intent_key", (Parcelable)intent2);
        return intent;
    }
    
    static Intent getDeepSubIntent(Intent deepSubIntent) {
        final Intent subIntentInMainIntent = getSubIntentInMainIntent(deepSubIntent);
        if (subIntentInMainIntent != null) {
            deepSubIntent = getDeepSubIntent(subIntentInMainIntent);
        }
        return deepSubIntent;
    }
    
    static Intent getSubIntentInMainIntent(Intent intent) {
        if (AndroidVersion.isAndroid13()) {
            intent = (Intent)intent.getParcelableExtra("sub_intent_key", (Class)Intent.class);
        }
        else {
            intent = (Intent)intent.getParcelableExtra("sub_intent_key");
        }
        return intent;
    }
    
    static boolean startActivity(final Activity activity, final Intent intent) {
        return startActivity((IStartActivityDelegate)new StartActivityManager.StartActivityManager$StartActivityDelegateActivityImpl(activity, (StartActivityManager$1)null), intent);
    }
    
    static boolean startActivity(final Fragment fragment, final Intent intent) {
        return startActivity((IStartActivityDelegate)new StartActivityManager.StartActivityManager$StartActivityDelegateFragmentImpl(fragment, (StartActivityManager$1)null), intent);
    }
    
    static boolean startActivity(final Context context, final Intent intent) {
        return startActivity((IStartActivityDelegate)new StartActivityManager.StartActivityManager$StartActivityDelegateContextImpl(context, (StartActivityManager$1)null), intent);
    }
    
    static boolean startActivity(final androidx.fragment.app.Fragment fragment, final Intent intent) {
        return startActivity((IStartActivityDelegate)new StartActivityManager.StartActivityManager$StartActivityDelegateSupportFragmentImpl(fragment, (StartActivityManager$1)null), intent);
    }
    
    static boolean startActivity(final IStartActivityDelegate startActivityDelegate, Intent subIntentInMainIntent) {
        try {
            startActivityDelegate.startActivity(subIntentInMainIntent);
            return true;
        }
        catch (final Exception ex) {
            ex.printStackTrace();
            subIntentInMainIntent = getSubIntentInMainIntent(subIntentInMainIntent);
            return subIntentInMainIntent != null && startActivity(startActivityDelegate, subIntentInMainIntent);
        }
    }
    
    static boolean startActivityForResult(final Activity activity, final Intent intent, final int n) {
        return startActivityForResult((IStartActivityDelegate)new StartActivityManager.StartActivityManager$StartActivityDelegateActivityImpl(activity, (StartActivityManager$1)null), intent, n);
    }
    
    static boolean startActivityForResult(final Fragment fragment, final Intent intent, final int n) {
        return startActivityForResult((IStartActivityDelegate)new StartActivityManager.StartActivityManager$StartActivityDelegateFragmentImpl(fragment, (StartActivityManager$1)null), intent, n);
    }
    
    static boolean startActivityForResult(final androidx.fragment.app.Fragment fragment, final Intent intent, final int n) {
        return startActivityForResult((IStartActivityDelegate)new StartActivityManager.StartActivityManager$StartActivityDelegateSupportFragmentImpl(fragment, (StartActivityManager$1)null), intent, n);
    }
    
    static boolean startActivityForResult(final IStartActivityDelegate startActivityDelegate, Intent subIntentInMainIntent, final int n) {
        try {
            startActivityDelegate.startActivityForResult(subIntentInMainIntent, n);
            return true;
        }
        catch (final Exception ex) {
            ex.printStackTrace();
            subIntentInMainIntent = getSubIntentInMainIntent(subIntentInMainIntent);
            return subIntentInMainIntent != null && startActivityForResult(startActivityDelegate, subIntentInMainIntent, n);
        }
    }
    
    private interface IStartActivityDelegate
    {
        void startActivity(final Intent p0);
        
        void startActivityForResult(final Intent p0, final int p1);
    }
}

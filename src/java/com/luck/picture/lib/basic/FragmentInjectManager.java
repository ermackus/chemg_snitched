package com.luck.picture.lib.basic;

import androidx.fragment.app.FragmentManager;
import com.luck.picture.lib.R;
import com.luck.picture.lib.utils.ActivityCompatHelper;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

public class FragmentInjectManager
{
    public static void injectFragment(final FragmentActivity fragmentActivity, final String s, final Fragment fragment) {
        if (ActivityCompatHelper.checkFragmentNonExits(fragmentActivity, s)) {
            fragmentActivity.getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, fragment, s).addToBackStack(s).commitAllowingStateLoss();
        }
    }
    
    public static void injectSystemRoomFragment(final FragmentManager fragmentManager, final String s, final Fragment fragment) {
        fragmentManager.beginTransaction().add(16908290, fragment, s).addToBackStack(s).commitAllowingStateLoss();
    }
}

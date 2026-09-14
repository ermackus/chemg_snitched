package com.kingagroot.kingdraw.adapter;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.Fragment;
import java.util.List;
import androidx.fragment.app.FragmentPagerAdapter;

public class FragmentAdapter extends FragmentPagerAdapter
{
    private final List<Fragment> fragments;
    private final String[] tabTitles;
    
    public FragmentAdapter(final FragmentManager fragmentManager, final String[] tabTitles, final List<Fragment> fragments) {
        super(fragmentManager);
        this.tabTitles = tabTitles;
        this.fragments = fragments;
    }
    
    public int getCount() {
        final List<Fragment> fragments = this.fragments;
        if (fragments != null) {
            return fragments.size();
        }
        return 0;
    }
    
    public Fragment getItem(final int n) {
        return (Fragment)this.fragments.get(n);
    }
    
    public CharSequence getPageTitle(final int n) {
        return (CharSequence)this.tabTitles[n];
    }
}

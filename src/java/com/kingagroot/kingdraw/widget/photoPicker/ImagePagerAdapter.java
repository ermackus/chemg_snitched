package com.kingagroot.kingdraw.widget.photoPicker;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import java.util.ArrayList;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class ImagePagerAdapter extends FragmentStatePagerAdapter
{
    private final ArrayList<String> fileList;
    private final int type;
    
    public ImagePagerAdapter(final FragmentManager fragmentManager, final ArrayList<String> fileList, final int type) {
        super(fragmentManager);
        this.fileList = fileList;
        this.type = type;
    }
    
    public int getCount() {
        final ArrayList<String> fileList = this.fileList;
        int size;
        if (fileList == null) {
            size = 0;
        }
        else {
            size = fileList.size();
        }
        return size;
    }
    
    public Fragment getItem(final int n) {
        return ImageDetailFragment.newInstance((String)this.fileList.get(n), this.type);
    }
}

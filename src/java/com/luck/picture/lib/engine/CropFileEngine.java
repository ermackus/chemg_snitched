package com.luck.picture.lib.engine;

import java.util.ArrayList;
import android.net.Uri;
import androidx.fragment.app.Fragment;

public interface CropFileEngine
{
    void onStartCrop(final Fragment p0, final Uri p1, final Uri p2, final ArrayList<String> p3, final int p4);
}

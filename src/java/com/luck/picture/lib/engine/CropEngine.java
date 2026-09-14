package com.luck.picture.lib.engine;

import java.util.ArrayList;
import com.luck.picture.lib.entity.LocalMedia;
import androidx.fragment.app.Fragment;

public interface CropEngine
{
    void onStartCrop(final Fragment p0, final LocalMedia p1, final ArrayList<LocalMedia> p2, final int p3);
}

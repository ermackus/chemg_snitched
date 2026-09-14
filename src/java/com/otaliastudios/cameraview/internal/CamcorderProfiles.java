package com.otaliastudios.cameraview.internal;

import java.util.List;
import java.util.Collections;
import java.util.Comparator;
import java.util.Collection;
import java.util.ArrayList;
import android.media.CamcorderProfile;
import android.os.Build$VERSION;
import java.util.HashMap;
import com.otaliastudios.cameraview.size.Size;
import java.util.Map;
import com.otaliastudios.cameraview.CameraLogger;

public class CamcorderProfiles
{
    private static final CameraLogger LOG;
    private static final String TAG;
    private static Map<Size, Integer> sizeToProfileMap;
    
    static {
        LOG = CameraLogger.create(TAG = CamcorderProfiles.class.getSimpleName());
        (CamcorderProfiles.sizeToProfileMap = (Map<Size, Integer>)new HashMap()).put((Object)new Size(176, 144), (Object)2);
        CamcorderProfiles.sizeToProfileMap.put((Object)new Size(320, 240), (Object)7);
        CamcorderProfiles.sizeToProfileMap.put((Object)new Size(352, 288), (Object)3);
        CamcorderProfiles.sizeToProfileMap.put((Object)new Size(720, 480), (Object)4);
        CamcorderProfiles.sizeToProfileMap.put((Object)new Size(1280, 720), (Object)5);
        CamcorderProfiles.sizeToProfileMap.put((Object)new Size(1920, 1080), (Object)6);
        if (Build$VERSION.SDK_INT >= 21) {
            CamcorderProfiles.sizeToProfileMap.put((Object)new Size(3840, 2160), (Object)8);
        }
    }
    
    public static CamcorderProfile get(final int n, Size size) {
        final long n2 = size.getWidth();
        final long n3 = size.getHeight();
        final ArrayList list = new ArrayList((Collection)CamcorderProfiles.sizeToProfileMap.keySet());
        Collections.sort((List)list, (Comparator)new Comparator<Size>(n2 * n3) {
            final long val$targetArea;
            
            public int compare(final Size size, final Size size2) {
                final long n = lcmp(Math.abs(size.getWidth() * size.getHeight() - this.val$targetArea), Math.abs(size2.getWidth() * size2.getHeight() - this.val$targetArea));
                int n2;
                if (n < 0) {
                    n2 = -1;
                }
                else if (n == 0) {
                    n2 = 0;
                }
                else {
                    n2 = 1;
                }
                return n2;
            }
        });
        while (((List)list).size() > 0) {
            size = (Size)((List)list).remove(0);
            final int intValue = (int)CamcorderProfiles.sizeToProfileMap.get((Object)size);
            if (CamcorderProfile.hasProfile(n, intValue)) {
                return CamcorderProfile.get(n, intValue);
            }
        }
        return CamcorderProfile.get(n, 0);
    }
    
    public static CamcorderProfile get(final String s, final Size size) {
        try {
            return get(Integer.parseInt(s), size);
        }
        catch (final NumberFormatException ex) {
            CamcorderProfiles.LOG.w(new Object[] { "NumberFormatException for Camera2 id:", s });
            return CamcorderProfile.get(0);
        }
    }
}

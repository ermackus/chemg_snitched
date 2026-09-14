package com.otaliastudios.cameraview.internal;

import android.os.Build;
import java.util.Arrays;
import java.util.HashMap;
import android.util.Range;
import java.util.List;
import java.util.Map;
import com.otaliastudios.cameraview.CameraLogger;

public class FpsRangeValidator
{
    private static final CameraLogger LOG;
    private static final Map<String, List<Range<Integer>>> sIssues;
    
    static {
        LOG = CameraLogger.create("FpsRangeValidator");
        final Map<String, List<Range<Integer>>> map = (Map<String, List<Range<Integer>>>)(sIssues = (Map)new HashMap());
        final Integer value = 15;
        final Integer value2 = 60;
        map.put((Object)"Google Pixel 4", (Object)Arrays.asList((Object[])new Range[] { new Range((Comparable)value, (Comparable)value2) }));
        FpsRangeValidator.sIssues.put((Object)"Google Pixel 4a", (Object)Arrays.asList((Object[])new Range[] { new Range((Comparable)value, (Comparable)value2) }));
    }
    
    public static boolean validate(final Range<Integer> range) {
        FpsRangeValidator.LOG.i(new Object[] { "Build.MODEL:", Build.MODEL, "Build.BRAND:", Build.BRAND, "Build.MANUFACTURER:", Build.MANUFACTURER });
        final StringBuilder sb = new StringBuilder();
        sb.append(Build.MANUFACTURER);
        sb.append(" ");
        sb.append(Build.MODEL);
        final List list = (List)FpsRangeValidator.sIssues.get((Object)sb.toString());
        if (list != null && list.contains((Object)range)) {
            FpsRangeValidator.LOG.i(new Object[] { "Dropping range:", range });
            return false;
        }
        return true;
    }
}

package com.ut.mini.internal;

import android.text.TextUtils;
import java.util.Map;
import com.ut.mini.UTHitBuilders$UTHitBuilder;

public class UTOriginalCustomHitBuilder extends UTHitBuilders$UTHitBuilder
{
    public UTOriginalCustomHitBuilder(final String s, final int n, final String s2, final String s3, final String s4, final Map<String, String> properties) {
        if (!TextUtils.isEmpty((CharSequence)s)) {
            super.setProperty("_field_page", s);
        }
        final StringBuilder sb = new StringBuilder();
        sb.append("");
        sb.append(n);
        super.setProperty("_field_event_id", sb.toString());
        if (!TextUtils.isEmpty((CharSequence)s2)) {
            super.setProperty("_field_arg1", s2);
        }
        if (!TextUtils.isEmpty((CharSequence)s3)) {
            super.setProperty("_field_arg2", s3);
        }
        if (!TextUtils.isEmpty((CharSequence)s4)) {
            super.setProperty("_field_arg3", s4);
        }
        super.setProperties((Map)properties);
    }
}

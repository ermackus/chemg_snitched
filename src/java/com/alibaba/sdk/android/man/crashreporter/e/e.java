package com.alibaba.sdk.android.man.crashreporter.e;

import java.io.File;

public class e
{
    public static void i(final String s) {
        if (i.a((CharSequence)s)) {
            return;
        }
        final File file = new File(s);
        if (file.exists() && file.isFile()) {
            file.delete();
        }
    }
}

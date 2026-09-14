package com.alipay.sdk.m.a0;

import java.util.regex.Pattern;
import java.io.File;
import java.io.FileFilter;

public final class c implements FileFilter
{
    public final b a;
    
    public c(final b a) {
        this.a = a;
    }
    
    public final boolean accept(final File file) {
        return Pattern.matches("cpu[0-9]+", (CharSequence)file.getName());
    }
}

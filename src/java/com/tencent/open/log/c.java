package com.tencent.open.log;

import com.tencent.connect.common.Constants;
import java.io.File;

public class c
{
    public static int a = 60;
    public static int b = 60;
    public static String c = "OpenSDK.Client.File.Tracer";
    public static String d;
    public static String e;
    public static long f;
    public static int g;
    public static int h;
    public static int i;
    public static String j;
    public static String k;
    public static String l;
    public static int m;
    public static long n;
    public static String o;
    
    static {
        final StringBuilder sb = new StringBuilder();
        sb.append("Tencent");
        sb.append(File.separator);
        sb.append("msflogs");
        sb.append(File.separator);
        sb.append("com");
        sb.append(File.separator);
        sb.append("tencent");
        sb.append(File.separator);
        sb.append("mobileqq");
        sb.append(File.separator);
        com.tencent.open.log.c.d = sb.toString();
        com.tencent.open.log.c.e = ".log";
        com.tencent.open.log.c.f = 8388608L;
        com.tencent.open.log.c.g = 262144;
        com.tencent.open.log.c.h = 1024;
        com.tencent.open.log.c.i = 10000;
        com.tencent.open.log.c.j = "debug.file.blockcount";
        com.tencent.open.log.c.k = "debug.file.keepperiod";
        com.tencent.open.log.c.l = "debug.file.tracelevel";
        com.tencent.open.log.c.m = 24;
        com.tencent.open.log.c.n = 604800000L;
        final StringBuilder sb2 = new StringBuilder();
        sb2.append(Constants.APP_SPECIFIC_ROOT);
        sb2.append(File.separator);
        sb2.append("logs");
        com.tencent.open.log.c.o = sb2.toString();
    }
}

package com.tencent.open.log;

import java.text.SimpleDateFormat;
import android.os.StatFs;
import java.io.File;
import android.os.Environment;
import android.os.Bundle;

public class d
{
    public static boolean a(final Bundle bundle) {
        return bundle.containsKey("access_token") || bundle.containsKey("pay_token") || bundle.containsKey("pfkey") || bundle.containsKey("expires_in") || bundle.containsKey("openid") || bundle.containsKey("proxy_code") || bundle.containsKey("proxy_expires_in");
    }
    
    public static boolean a(final String s) {
        return s.contains((CharSequence)"access_token") || s.contains((CharSequence)"pay_token") || s.contains((CharSequence)"pfkey") || s.contains((CharSequence)"expires_in") || s.contains((CharSequence)"openid") || s.contains((CharSequence)"proxy_code") || s.contains((CharSequence)"proxy_expires_in");
    }
    
    public static Bundle b(Bundle bundle) {
        if (!a(bundle)) {
            return bundle;
        }
        bundle = new Bundle(bundle);
        bundle.remove("access_token");
        bundle.remove("pay_token");
        bundle.remove("pfkey");
        bundle.remove("expires_in");
        bundle.remove("openid");
        bundle.remove("proxy_code");
        bundle.remove("proxy_expires_in");
        return bundle;
    }
    
    public static final class a
    {
        public static final boolean a(final int n, final int n2) {
            return n2 == (n & n2);
        }
    }
    
    public static final class b
    {
        public static boolean a() {
            final String externalStorageState = Environment.getExternalStorageState();
            return "mounted".equals((Object)externalStorageState) || "mounted_ro".equals((Object)externalStorageState);
        }
        
        public static c b() {
            if (!a()) {
                return null;
            }
            return c.b(Environment.getExternalStorageDirectory());
        }
    }
    
    public static class c
    {
        private File a;
        private long b;
        private long c;
        
        public static c b(final File file) {
            final c c = new c();
            c.a(file);
            final StatFs statFs = new StatFs(file.getAbsolutePath());
            final long n = statFs.getBlockSize();
            final long n2 = statFs.getBlockCount();
            final long n3 = statFs.getAvailableBlocks();
            c.a(n2 * n);
            c.b(n3 * n);
            return c;
        }
        
        public File a() {
            return this.a;
        }
        
        public void a(final long b) {
            this.b = b;
        }
        
        public void a(final File a) {
            this.a = a;
        }
        
        public long b() {
            return this.b;
        }
        
        public void b(final long c) {
            this.c = c;
        }
        
        public long c() {
            return this.c;
        }
        
        @Override
        public String toString() {
            return String.format("[%s : %d / %d]", new Object[] { this.a().getAbsolutePath(), this.c(), this.b() });
        }
    }
    
    public static final class d
    {
        public static SimpleDateFormat a(final String s) {
            return new SimpleDateFormat(s);
        }
    }
}
